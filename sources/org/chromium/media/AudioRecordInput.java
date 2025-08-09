package org.chromium.media;

import android.annotation.SuppressLint;
import android.media.AudioRecord;
import android.media.audiofx.AcousticEchoCanceler;
import android.os.Process;
import android.util.Log;
import java.nio.ByteBuffer;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;

@JNINamespace("media")
class AudioRecordInput {
    private static final boolean DEBUG = false;
    private static final int HARDWARE_DELAY_MS = 100;
    private static final String TAG = "AudioRecordInput";
    private AcousticEchoCanceler mAEC;
    /* access modifiers changed from: private */
    public AudioRecord mAudioRecord;
    private AudioRecordThread mAudioRecordThread;
    private final int mBitsPerSample;
    /* access modifiers changed from: private */
    public ByteBuffer mBuffer;
    private final int mChannels;
    /* access modifiers changed from: private */
    public final int mHardwareDelayBytes;
    /* access modifiers changed from: private */
    public final long mNativeAudioRecordInputStream;
    private final int mSampleRate;
    private final boolean mUsePlatformAEC;

    private native void nativeCacheDirectBufferAddress(long j, ByteBuffer byteBuffer);

    /* access modifiers changed from: private */
    public native void nativeOnData(long j, int i, int i2);

    private class AudioRecordThread extends Thread {
        private volatile boolean mKeepAlive;

        private AudioRecordThread() {
            this.mKeepAlive = true;
        }

        public void run() {
            Process.setThreadPriority(-19);
            try {
                AudioRecordInput.this.mAudioRecord.startRecording();
                while (this.mKeepAlive) {
                    int bytesRead = AudioRecordInput.this.mAudioRecord.read(AudioRecordInput.this.mBuffer, AudioRecordInput.this.mBuffer.capacity());
                    if (bytesRead > 0) {
                        AudioRecordInput.this.nativeOnData(AudioRecordInput.this.mNativeAudioRecordInputStream, bytesRead, AudioRecordInput.this.mHardwareDelayBytes);
                    } else {
                        Log.e(AudioRecordInput.TAG, "read failed: " + bytesRead);
                        if (bytesRead == -3) {
                            this.mKeepAlive = false;
                        }
                    }
                }
                try {
                    AudioRecordInput.this.mAudioRecord.stop();
                } catch (IllegalStateException e) {
                    Log.e(AudioRecordInput.TAG, "stop failed", e);
                }
            } catch (IllegalStateException e2) {
                Log.e(AudioRecordInput.TAG, "startRecording failed", e2);
            }
        }

        public void joinRecordThread() {
            this.mKeepAlive = false;
            while (isAlive()) {
                try {
                    join();
                } catch (InterruptedException e) {
                }
            }
        }
    }

    @CalledByNative
    private static AudioRecordInput createAudioRecordInput(long nativeAudioRecordInputStream, int sampleRate, int channels, int bitsPerSample, int bytesPerBuffer, boolean usePlatformAEC) {
        return new AudioRecordInput(nativeAudioRecordInputStream, sampleRate, channels, bitsPerSample, bytesPerBuffer, usePlatformAEC);
    }

    private AudioRecordInput(long nativeAudioRecordInputStream, int sampleRate, int channels, int bitsPerSample, int bytesPerBuffer, boolean usePlatformAEC) {
        this.mNativeAudioRecordInputStream = nativeAudioRecordInputStream;
        this.mSampleRate = sampleRate;
        this.mChannels = channels;
        this.mBitsPerSample = bitsPerSample;
        this.mHardwareDelayBytes = (((sampleRate * 100) / 1000) * bitsPerSample) / 8;
        this.mUsePlatformAEC = usePlatformAEC;
        this.mBuffer = ByteBuffer.allocateDirect(bytesPerBuffer);
        nativeCacheDirectBufferAddress(this.mNativeAudioRecordInputStream, this.mBuffer);
    }

    @SuppressLint({"NewApi"})
    @CalledByNative
    private boolean open() {
        int channelConfig;
        int audioFormat;
        if (this.mAudioRecord != null) {
            Log.e(TAG, "open() called twice without a close()");
            return false;
        }
        if (this.mChannels == 1) {
            channelConfig = 16;
        } else if (this.mChannels == 2) {
            channelConfig = 12;
        } else {
            Log.e(TAG, "Unsupported number of channels: " + this.mChannels);
            return false;
        }
        if (this.mBitsPerSample == 8) {
            audioFormat = 3;
        } else if (this.mBitsPerSample == 16) {
            audioFormat = 2;
        } else {
            Log.e(TAG, "Unsupported bits per sample: " + this.mBitsPerSample);
            return false;
        }
        int minBufferSize = AudioRecord.getMinBufferSize(this.mSampleRate, channelConfig, audioFormat);
        if (minBufferSize < 0) {
            Log.e(TAG, "getMinBufferSize error: " + minBufferSize);
            return false;
        }
        try {
            this.mAudioRecord = new AudioRecord(7, this.mSampleRate, channelConfig, audioFormat, Math.max(this.mBuffer.capacity(), minBufferSize));
            if (AcousticEchoCanceler.isAvailable()) {
                this.mAEC = AcousticEchoCanceler.create(this.mAudioRecord.getAudioSessionId());
                if (this.mAEC == null) {
                    Log.e(TAG, "AcousticEchoCanceler.create failed");
                    return false;
                }
                int ret = this.mAEC.setEnabled(this.mUsePlatformAEC);
                if (ret != 0) {
                    Log.e(TAG, "setEnabled error: " + ret);
                    return false;
                }
            }
            return true;
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "AudioRecord failed", e);
            return false;
        }
    }

    @CalledByNative
    private void start() {
        if (this.mAudioRecord == null) {
            Log.e(TAG, "start() called before open().");
        } else if (this.mAudioRecordThread == null) {
            this.mAudioRecordThread = new AudioRecordThread();
            this.mAudioRecordThread.start();
        }
    }

    @CalledByNative
    private void stop() {
        if (this.mAudioRecordThread != null) {
            this.mAudioRecordThread.joinRecordThread();
            this.mAudioRecordThread = null;
        }
    }

    @SuppressLint({"NewApi"})
    @CalledByNative
    private void close() {
        if (this.mAudioRecordThread != null) {
            Log.e(TAG, "close() called before stop().");
        } else if (this.mAudioRecord != null) {
            if (this.mAEC != null) {
                this.mAEC.release();
                this.mAEC = null;
            }
            this.mAudioRecord.release();
            this.mAudioRecord = null;
        }
    }
}
