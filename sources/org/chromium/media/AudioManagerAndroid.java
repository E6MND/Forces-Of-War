package org.chromium.media;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.audiofx.AcousticEchoCanceler;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.provider.Settings;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;

@JNINamespace("media")
class AudioManagerAndroid {
    private static final boolean DEBUG = false;
    private static final int DEFAULT_FRAME_PER_BUFFER = 256;
    private static final int DEFAULT_SAMPLING_RATE = 44100;
    private static final int DEVICE_BLUETOOTH_HEADSET = 3;
    private static final int DEVICE_COUNT = 4;
    private static final int DEVICE_DEFAULT = -2;
    private static final int DEVICE_EARPIECE = 2;
    private static final int DEVICE_INVALID = -1;
    private static final String[] DEVICE_NAMES = {"Speakerphone", "Wired headset", "Headset earpiece", "Bluetooth headset"};
    private static final int DEVICE_SPEAKERPHONE = 0;
    private static final int DEVICE_WIRED_HEADSET = 1;
    private static final int STATE_BLUETOOTH_SCO_INVALID = -1;
    private static final int STATE_BLUETOOTH_SCO_OFF = 0;
    private static final int STATE_BLUETOOTH_SCO_ON = 1;
    private static final int STATE_BLUETOOTH_SCO_TURNING_OFF = 3;
    private static final int STATE_BLUETOOTH_SCO_TURNING_ON = 2;
    private static final String[] SUPPORTED_AEC_MODELS = {"GT-I9300", "GT-I9500", "GT-N7105", "Nexus 4", "Nexus 5", "Nexus 7", "SM-N9005", "SM-T310"};
    private static final String TAG = "AudioManagerAndroid";
    private static final Integer[] VALID_DEVICES = {0, 1, 2, 3};
    /* access modifiers changed from: private */
    public boolean[] mAudioDevices = new boolean[4];
    /* access modifiers changed from: private */
    public final AudioManager mAudioManager;
    private BroadcastReceiver mBluetoothHeadsetReceiver;
    private BroadcastReceiver mBluetoothScoReceiver;
    /* access modifiers changed from: private */
    public int mBluetoothScoState = -1;
    private final ContentResolver mContentResolver;
    private final Context mContext;
    private int mCurrentVolume;
    private boolean mHasBluetoothPermission = false;
    private boolean mHasModifyAudioSettingsPermission = false;
    private boolean mHasRecordAudioPermission = false;
    private boolean mIsInitialized = false;
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    /* access modifiers changed from: private */
    public final long mNativeAudioManagerAndroid;
    private final NonThreadSafe mNonThreadSafe = new NonThreadSafe();
    private int mRequestedAudioDevice = -1;
    private int mSavedAudioMode = -2;
    private boolean mSavedIsMicrophoneMute;
    private boolean mSavedIsSpeakerphoneOn;
    private ContentObserver mSettingsObserver = null;
    private HandlerThread mSettingsObserverThread = null;
    private BroadcastReceiver mWiredHeadsetReceiver;

    /* access modifiers changed from: private */
    public native void nativeSetMute(long j, boolean z);

    private static class NonThreadSafe {
        private final Long mThreadId = 0L;

        public boolean calledOnValidThread() {
            return true;
        }
    }

    private static boolean runningOnJellyBeanOrHigher() {
        return Build.VERSION.SDK_INT >= 16;
    }

    private static boolean runningOnJellyBeanMR1OrHigher() {
        return Build.VERSION.SDK_INT >= 17;
    }

    private static boolean runningOnJellyBeanMR2OrHigher() {
        return Build.VERSION.SDK_INT >= 18;
    }

    private static class AudioDeviceName {
        private final int mId;
        private final String mName;

        private AudioDeviceName(int id, String name) {
            this.mId = id;
            this.mName = name;
        }

        @CalledByNative("AudioDeviceName")
        private String id() {
            return String.valueOf(this.mId);
        }

        @CalledByNative("AudioDeviceName")
        private String name() {
            return this.mName;
        }
    }

    @CalledByNative
    private static AudioManagerAndroid createAudioManagerAndroid(Context context, long nativeAudioManagerAndroid) {
        return new AudioManagerAndroid(context, nativeAudioManagerAndroid);
    }

    private AudioManagerAndroid(Context context, long nativeAudioManagerAndroid) {
        this.mContext = context;
        this.mNativeAudioManagerAndroid = nativeAudioManagerAndroid;
        this.mAudioManager = (AudioManager) this.mContext.getSystemService("audio");
        this.mContentResolver = this.mContext.getContentResolver();
    }

    @CalledByNative
    private void init() {
        checkIfCalledOnValidThread();
        if (!this.mIsInitialized) {
            this.mHasModifyAudioSettingsPermission = hasPermission("android.permission.MODIFY_AUDIO_SETTINGS");
            this.mHasRecordAudioPermission = hasPermission("android.permission.RECORD_AUDIO");
            this.mAudioDevices[2] = hasEarpiece();
            this.mAudioDevices[1] = hasWiredHeadset();
            this.mAudioDevices[0] = true;
            registerBluetoothIntentsIfNeeded();
            registerForWiredHeadsetIntentBroadcast();
            this.mIsInitialized = true;
        }
    }

    @CalledByNative
    private void close() {
        checkIfCalledOnValidThread();
        if (this.mIsInitialized) {
            stopObservingVolumeChanges();
            unregisterForWiredHeadsetIntentBroadcast();
            unregisterBluetoothIntentsIfNeeded();
            this.mIsInitialized = false;
        }
    }

    @CalledByNative
    private void setCommunicationAudioModeOn(boolean on) {
        if (!this.mHasModifyAudioSettingsPermission) {
            Log.w(TAG, "MODIFY_AUDIO_SETTINGS is missing => client will run with reduced functionality");
        } else if (on) {
            if (this.mSavedAudioMode != -2) {
                throw new IllegalStateException("Audio mode has already been set");
            }
            try {
                this.mSavedAudioMode = this.mAudioManager.getMode();
                this.mSavedIsSpeakerphoneOn = this.mAudioManager.isSpeakerphoneOn();
                this.mSavedIsMicrophoneMute = this.mAudioManager.isMicrophoneMute();
                try {
                    this.mAudioManager.setMode(3);
                    startObservingVolumeChanges();
                } catch (SecurityException e) {
                    logDeviceInfo();
                    throw e;
                }
            } catch (SecurityException e2) {
                logDeviceInfo();
                throw e2;
            }
        } else if (this.mSavedAudioMode == -2) {
            throw new IllegalStateException("Audio mode has not yet been set");
        } else {
            stopObservingVolumeChanges();
            setMicrophoneMute(this.mSavedIsMicrophoneMute);
            setSpeakerphoneOn(this.mSavedIsSpeakerphoneOn);
            try {
                this.mAudioManager.setMode(this.mSavedAudioMode);
                this.mSavedAudioMode = -2;
            } catch (SecurityException e3) {
                logDeviceInfo();
                throw e3;
            }
        }
    }

    @CalledByNative
    private boolean setDevice(String deviceId) {
        boolean[] devices;
        if (!this.mIsInitialized) {
            return false;
        }
        if (!this.mHasModifyAudioSettingsPermission || !this.mHasRecordAudioPermission) {
            Log.w(TAG, "Requires MODIFY_AUDIO_SETTINGS and RECORD_AUDIO");
            Log.w(TAG, "Selected device will not be available for recording");
            return false;
        }
        int intDeviceId = deviceId.isEmpty() ? -2 : Integer.parseInt(deviceId);
        if (intDeviceId == -2) {
            synchronized (this.mLock) {
                devices = (boolean[]) this.mAudioDevices.clone();
                this.mRequestedAudioDevice = -2;
            }
            setAudioDevice(selectDefaultDevice(devices));
            return true;
        } else if (!Arrays.asList(VALID_DEVICES).contains(Integer.valueOf(intDeviceId)) || !this.mAudioDevices[intDeviceId]) {
            return false;
        } else {
            synchronized (this.mLock) {
                this.mRequestedAudioDevice = intDeviceId;
            }
            setAudioDevice(intDeviceId);
            return true;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: boolean[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    @org.chromium.base.CalledByNative
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.chromium.media.AudioManagerAndroid.AudioDeviceName[] getAudioInputDeviceNames() {
        /*
            r9 = this;
            r7 = 0
            boolean r6 = r9.mIsInitialized
            if (r6 != 0) goto L_0x0007
            r1 = r7
        L_0x0006:
            return r1
        L_0x0007:
            boolean r6 = r9.mHasModifyAudioSettingsPermission
            if (r6 == 0) goto L_0x000f
            boolean r6 = r9.mHasRecordAudioPermission
            if (r6 != 0) goto L_0x001f
        L_0x000f:
            java.lang.String r6 = "AudioManagerAndroid"
            java.lang.String r8 = "Requires MODIFY_AUDIO_SETTINGS and RECORD_AUDIO"
            android.util.Log.w(r6, r8)
            java.lang.String r6 = "AudioManagerAndroid"
            java.lang.String r8 = "No audio device will be available for recording"
            android.util.Log.w(r6, r8)
            r1 = r7
            goto L_0x0006
        L_0x001f:
            r2 = 0
            java.lang.Object r8 = r9.mLock
            monitor-enter(r8)
            boolean[] r6 = r9.mAudioDevices     // Catch:{ all -> 0x0059 }
            java.lang.Object r6 = r6.clone()     // Catch:{ all -> 0x0059 }
            r0 = r6
            boolean[] r0 = (boolean[]) r0     // Catch:{ all -> 0x0059 }
            r2 = r0
            monitor-exit(r8)     // Catch:{ all -> 0x0059 }
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            int r6 = getNumOfAudioDevices(r2)
            org.chromium.media.AudioManagerAndroid$AudioDeviceName[] r1 = new org.chromium.media.AudioManagerAndroid.AudioDeviceName[r6]
            r3 = 0
            r4 = 0
        L_0x003b:
            r6 = 4
            if (r4 >= r6) goto L_0x0006
            boolean r6 = r2[r4]
            if (r6 == 0) goto L_0x0056
            org.chromium.media.AudioManagerAndroid$AudioDeviceName r6 = new org.chromium.media.AudioManagerAndroid$AudioDeviceName
            java.lang.String[] r8 = DEVICE_NAMES
            r8 = r8[r4]
            r6.<init>(r4, r8)
            r1[r3] = r6
            java.lang.String[] r6 = DEVICE_NAMES
            r6 = r6[r4]
            r5.add(r6)
            int r3 = r3 + 1
        L_0x0056:
            int r4 = r4 + 1
            goto L_0x003b
        L_0x0059:
            r6 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x0059 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.media.AudioManagerAndroid.getAudioInputDeviceNames():org.chromium.media.AudioManagerAndroid$AudioDeviceName[]");
    }

    @CalledByNative
    private int getNativeOutputSampleRate() {
        String sampleRateString;
        if (!runningOnJellyBeanMR1OrHigher() || (sampleRateString = this.mAudioManager.getProperty("android.media.property.OUTPUT_SAMPLE_RATE")) == null) {
            return DEFAULT_SAMPLING_RATE;
        }
        return Integer.parseInt(sampleRateString);
    }

    @CalledByNative
    private static int getMinInputFrameSize(int sampleRate, int channels) {
        int channelConfig;
        if (channels == 1) {
            channelConfig = 16;
        } else if (channels != 2) {
            return -1;
        } else {
            channelConfig = 12;
        }
        return (AudioRecord.getMinBufferSize(sampleRate, channelConfig, 2) / 2) / channels;
    }

    @CalledByNative
    private static int getMinOutputFrameSize(int sampleRate, int channels) {
        int channelConfig;
        if (channels == 1) {
            channelConfig = 4;
        } else if (channels != 2) {
            return -1;
        } else {
            channelConfig = 12;
        }
        return (AudioTrack.getMinBufferSize(sampleRate, channelConfig, 2) / 2) / channels;
    }

    @CalledByNative
    private boolean isAudioLowLatencySupported() {
        return this.mContext.getPackageManager().hasSystemFeature("android.hardware.audio.low_latency");
    }

    @CalledByNative
    private int getAudioLowLatencyOutputFrameSize() {
        String framesPerBuffer = this.mAudioManager.getProperty("android.media.property.OUTPUT_FRAMES_PER_BUFFER");
        if (framesPerBuffer == null) {
            return 256;
        }
        return Integer.parseInt(framesPerBuffer);
    }

    @CalledByNative
    private static boolean shouldUseAcousticEchoCanceler() {
        if (runningOnJellyBeanOrHigher() && Arrays.asList(SUPPORTED_AEC_MODELS).contains(Build.MODEL)) {
            return AcousticEchoCanceler.isAvailable();
        }
        return false;
    }

    private void checkIfCalledOnValidThread() {
    }

    private void registerBluetoothIntentsIfNeeded() {
        this.mHasBluetoothPermission = hasPermission("android.permission.BLUETOOTH");
        if (!this.mHasBluetoothPermission) {
            Log.w(TAG, "Requires BLUETOOTH permission");
            return;
        }
        this.mAudioDevices[3] = hasBluetoothHeadset();
        registerForBluetoothHeadsetIntentBroadcast();
        registerForBluetoothScoIntentBroadcast();
    }

    private void unregisterBluetoothIntentsIfNeeded() {
        if (this.mHasBluetoothPermission) {
            this.mAudioManager.stopBluetoothSco();
            unregisterForBluetoothHeadsetIntentBroadcast();
            unregisterForBluetoothScoIntentBroadcast();
        }
    }

    private void setSpeakerphoneOn(boolean on) {
        if (this.mAudioManager.isSpeakerphoneOn() != on) {
            this.mAudioManager.setSpeakerphoneOn(on);
        }
    }

    private void setMicrophoneMute(boolean on) {
        if (this.mAudioManager.isMicrophoneMute() != on) {
            this.mAudioManager.setMicrophoneMute(on);
        }
    }

    private boolean isMicrophoneMute() {
        return this.mAudioManager.isMicrophoneMute();
    }

    /* access modifiers changed from: private */
    public boolean hasEarpiece() {
        return this.mContext.getPackageManager().hasSystemFeature("android.hardware.telephony");
    }

    @Deprecated
    private boolean hasWiredHeadset() {
        return this.mAudioManager.isWiredHeadsetOn();
    }

    private boolean hasPermission(String permission) {
        return this.mContext.checkPermission(permission, Process.myPid(), Process.myUid()) == 0;
    }

    private boolean hasBluetoothHeadset() {
        BluetoothAdapter btAdapter;
        boolean z = true;
        if (!this.mHasBluetoothPermission) {
            Log.w(TAG, "hasBluetoothHeadset() requires BLUETOOTH permission");
            return false;
        }
        if (runningOnJellyBeanMR2OrHigher()) {
            btAdapter = ((BluetoothManager) this.mContext.getSystemService("bluetooth")).getAdapter();
        } else {
            btAdapter = BluetoothAdapter.getDefaultAdapter();
        }
        if (btAdapter == null) {
            return false;
        }
        int profileConnectionState = btAdapter.getProfileConnectionState(1);
        if (!btAdapter.isEnabled() || profileConnectionState != 2) {
            z = false;
        }
        return z;
    }

    private void registerForWiredHeadsetIntentBroadcast() {
        IntentFilter filter = new IntentFilter("android.intent.action.HEADSET_PLUG");
        this.mWiredHeadsetReceiver = new BroadcastReceiver() {
            private static final int HAS_MIC = 1;
            private static final int HAS_NO_MIC = 0;
            private static final int STATE_PLUGGED = 1;
            private static final int STATE_UNPLUGGED = 0;

            public void onReceive(Context context, Intent intent) {
                switch (intent.getIntExtra("state", 0)) {
                    case 0:
                        synchronized (AudioManagerAndroid.this.mLock) {
                            AudioManagerAndroid.this.mAudioDevices[1] = false;
                            if (AudioManagerAndroid.this.hasEarpiece()) {
                                AudioManagerAndroid.this.mAudioDevices[2] = true;
                            }
                        }
                        break;
                    case 1:
                        synchronized (AudioManagerAndroid.this.mLock) {
                            AudioManagerAndroid.this.mAudioDevices[1] = true;
                            AudioManagerAndroid.this.mAudioDevices[2] = false;
                        }
                        break;
                    default:
                        AudioManagerAndroid.loge("Invalid state");
                        break;
                }
                if (AudioManagerAndroid.this.deviceHasBeenRequested()) {
                    AudioManagerAndroid.this.updateDeviceActivation();
                }
            }
        };
        this.mContext.registerReceiver(this.mWiredHeadsetReceiver, filter);
    }

    private void unregisterForWiredHeadsetIntentBroadcast() {
        this.mContext.unregisterReceiver(this.mWiredHeadsetReceiver);
        this.mWiredHeadsetReceiver = null;
    }

    private void registerForBluetoothHeadsetIntentBroadcast() {
        IntentFilter filter = new IntentFilter("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
        this.mBluetoothHeadsetReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                switch (intent.getIntExtra("android.bluetooth.profile.extra.STATE", 0)) {
                    case 0:
                        synchronized (AudioManagerAndroid.this.mLock) {
                            AudioManagerAndroid.this.mAudioDevices[3] = false;
                        }
                        break;
                    case 1:
                    case 3:
                        break;
                    case 2:
                        synchronized (AudioManagerAndroid.this.mLock) {
                            AudioManagerAndroid.this.mAudioDevices[3] = true;
                        }
                        break;
                    default:
                        AudioManagerAndroid.loge("Invalid state");
                        break;
                }
                if (AudioManagerAndroid.this.deviceHasBeenRequested()) {
                    AudioManagerAndroid.this.updateDeviceActivation();
                }
            }
        };
        this.mContext.registerReceiver(this.mBluetoothHeadsetReceiver, filter);
    }

    private void unregisterForBluetoothHeadsetIntentBroadcast() {
        this.mContext.unregisterReceiver(this.mBluetoothHeadsetReceiver);
        this.mBluetoothHeadsetReceiver = null;
    }

    private void registerForBluetoothScoIntentBroadcast() {
        IntentFilter filter = new IntentFilter("android.media.ACTION_SCO_AUDIO_STATE_UPDATED");
        this.mBluetoothScoReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                switch (intent.getIntExtra("android.media.extra.SCO_AUDIO_STATE", 0)) {
                    case 0:
                        int unused = AudioManagerAndroid.this.mBluetoothScoState = 0;
                        return;
                    case 1:
                        int unused2 = AudioManagerAndroid.this.mBluetoothScoState = 1;
                        return;
                    case 2:
                        return;
                    default:
                        AudioManagerAndroid.loge("Invalid state");
                        return;
                }
            }
        };
        this.mContext.registerReceiver(this.mBluetoothScoReceiver, filter);
    }

    private void unregisterForBluetoothScoIntentBroadcast() {
        this.mContext.unregisterReceiver(this.mBluetoothScoReceiver);
        this.mBluetoothScoReceiver = null;
    }

    private void startBluetoothSco() {
        if (this.mHasBluetoothPermission && this.mBluetoothScoState != 1 && this.mBluetoothScoState != 2) {
            if (this.mAudioManager.isBluetoothScoOn()) {
                this.mBluetoothScoState = 1;
                return;
            }
            this.mBluetoothScoState = 2;
            this.mAudioManager.startBluetoothSco();
        }
    }

    private void stopBluetoothSco() {
        if (this.mHasBluetoothPermission) {
            if (this.mBluetoothScoState != 1 && this.mBluetoothScoState != 2) {
                return;
            }
            if (!this.mAudioManager.isBluetoothScoOn()) {
                loge("Unable to stop BT SCO since it is already disabled");
                return;
            }
            this.mBluetoothScoState = 3;
            this.mAudioManager.stopBluetoothSco();
        }
    }

    private void setAudioDevice(int device) {
        if (device == 3) {
            startBluetoothSco();
        } else {
            stopBluetoothSco();
        }
        switch (device) {
            case 0:
                setSpeakerphoneOn(true);
                break;
            case 1:
                setSpeakerphoneOn(false);
                break;
            case 2:
                setSpeakerphoneOn(false);
                break;
            case 3:
                break;
            default:
                loge("Invalid audio device selection");
                break;
        }
        reportUpdate();
    }

    private static int selectDefaultDevice(boolean[] devices) {
        if (devices[1]) {
            return 1;
        }
        if (devices[3]) {
            return 3;
        }
        return 0;
    }

    /* access modifiers changed from: private */
    public boolean deviceHasBeenRequested() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mRequestedAudioDevice != -1;
        }
        return z;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: boolean[]} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateDeviceActivation() {
        /*
            r6 = this;
            r2 = 0
            r3 = -1
            java.lang.Object r5 = r6.mLock
            monitor-enter(r5)
            int r3 = r6.mRequestedAudioDevice     // Catch:{ all -> 0x001b }
            boolean[] r4 = r6.mAudioDevices     // Catch:{ all -> 0x001b }
            java.lang.Object r4 = r4.clone()     // Catch:{ all -> 0x001b }
            r0 = r4
            boolean[] r0 = (boolean[]) r0     // Catch:{ all -> 0x001b }
            r2 = r0
            monitor-exit(r5)     // Catch:{ all -> 0x001b }
            r4 = -1
            if (r3 != r4) goto L_0x001e
            java.lang.String r4 = "Unable to activate device since no device is selected"
            loge(r4)
        L_0x001a:
            return
        L_0x001b:
            r4 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x001b }
            throw r4
        L_0x001e:
            r4 = -2
            if (r3 == r4) goto L_0x0025
            boolean r4 = r2[r3]
            if (r4 != 0) goto L_0x002d
        L_0x0025:
            int r1 = selectDefaultDevice(r2)
            r6.setAudioDevice(r1)
            goto L_0x001a
        L_0x002d:
            r6.setAudioDevice(r3)
            goto L_0x001a
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.media.AudioManagerAndroid.updateDeviceActivation():void");
    }

    private static int getNumOfAudioDevices(boolean[] devices) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (devices[i]) {
                count++;
            }
        }
        return count;
    }

    private void reportUpdate() {
        synchronized (this.mLock) {
            List<String> devices = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                if (this.mAudioDevices[i]) {
                    devices.add(DEVICE_NAMES[i]);
                }
            }
        }
    }

    private void logDeviceInfo() {
        logd("Android SDK: " + Build.VERSION.SDK_INT + ", " + "Release: " + Build.VERSION.RELEASE + ", " + "Brand: " + Build.BRAND + ", " + "Device: " + Build.DEVICE + ", " + "Id: " + Build.ID + ", " + "Hardware: " + Build.HARDWARE + ", " + "Manufacturer: " + Build.MANUFACTURER + ", " + "Model: " + Build.MODEL + ", " + "Product: " + Build.PRODUCT);
    }

    private static void logd(String msg) {
        Log.d(TAG, msg);
    }

    /* access modifiers changed from: private */
    public static void loge(String msg) {
        Log.e(TAG, msg);
    }

    private void startObservingVolumeChanges() {
        if (this.mSettingsObserverThread == null) {
            this.mSettingsObserverThread = new HandlerThread("SettingsObserver");
            this.mSettingsObserverThread.start();
            this.mSettingsObserver = new ContentObserver(new Handler(this.mSettingsObserverThread.getLooper())) {
                public void onChange(boolean selfChange) {
                    boolean z = false;
                    super.onChange(selfChange);
                    if (AudioManagerAndroid.this.mAudioManager.getMode() != 3) {
                        throw new IllegalStateException("Only enable SettingsObserver in COMM mode");
                    }
                    int volume = AudioManagerAndroid.this.mAudioManager.getStreamVolume(0);
                    AudioManagerAndroid audioManagerAndroid = AudioManagerAndroid.this;
                    long access$900 = AudioManagerAndroid.this.mNativeAudioManagerAndroid;
                    if (volume == 0) {
                        z = true;
                    }
                    audioManagerAndroid.nativeSetMute(access$900, z);
                }
            };
            this.mContentResolver.registerContentObserver(Settings.System.CONTENT_URI, true, this.mSettingsObserver);
        }
    }

    private void stopObservingVolumeChanges() {
        if (this.mSettingsObserverThread != null) {
            this.mContentResolver.unregisterContentObserver(this.mSettingsObserver);
            this.mSettingsObserver = null;
            this.mSettingsObserverThread.quit();
            try {
                this.mSettingsObserverThread.join();
            } catch (InterruptedException e) {
                Log.e(TAG, "Thread.join() exception: ", e);
            }
            this.mSettingsObserverThread = null;
        }
    }
}
