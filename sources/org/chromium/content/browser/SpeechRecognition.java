package org.chromium.content.browser;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import java.util.ArrayList;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;

@JNINamespace("content")
public class SpeechRecognition {
    private static final int PROVIDER_MIN_VERSION = 300207030;
    private static final String PROVIDER_PACKAGE_NAME = "com.google.android.googlequicksearchbox";
    private static final int STATE_AWAITING_SPEECH = 1;
    private static final int STATE_CAPTURING_SPEECH = 2;
    private static final int STATE_IDLE = 0;
    private static ComponentName sRecognitionProvider;
    private final Context mContext;
    /* access modifiers changed from: private */
    public boolean mContinuous = false;
    private final Intent mIntent;
    private final RecognitionListener mListener;
    /* access modifiers changed from: private */
    public long mNativeSpeechRecognizerImplAndroid;
    private SpeechRecognizer mRecognizer;
    /* access modifiers changed from: private */
    public int mState;

    /* access modifiers changed from: private */
    public native void nativeOnAudioEnd(long j);

    /* access modifiers changed from: private */
    public native void nativeOnAudioStart(long j);

    private native void nativeOnRecognitionEnd(long j);

    private native void nativeOnRecognitionError(long j, int i);

    /* access modifiers changed from: private */
    public native void nativeOnRecognitionResults(long j, String[] strArr, float[] fArr, boolean z);

    /* access modifiers changed from: private */
    public native void nativeOnSoundEnd(long j);

    /* access modifiers changed from: private */
    public native void nativeOnSoundStart(long j);

    class Listener implements RecognitionListener {
        static final /* synthetic */ boolean $assertionsDisabled = (!SpeechRecognition.class.desiredAssertionStatus());

        Listener() {
        }

        public void onBeginningOfSpeech() {
            int unused = SpeechRecognition.this.mState = 2;
            SpeechRecognition.this.nativeOnSoundStart(SpeechRecognition.this.mNativeSpeechRecognizerImplAndroid);
        }

        public void onBufferReceived(byte[] buffer) {
        }

        public void onEndOfSpeech() {
            if (!SpeechRecognition.this.mContinuous) {
                SpeechRecognition.this.nativeOnSoundEnd(SpeechRecognition.this.mNativeSpeechRecognizerImplAndroid);
                SpeechRecognition.this.nativeOnAudioEnd(SpeechRecognition.this.mNativeSpeechRecognizerImplAndroid);
                int unused = SpeechRecognition.this.mState = 0;
            }
        }

        public void onError(int error) {
            int code;
            switch (error) {
                case 1:
                case 2:
                case 4:
                    code = 3;
                    break;
                case 3:
                    code = 2;
                    break;
                case 5:
                    code = 1;
                    break;
                case 6:
                    code = 5;
                    break;
                case 7:
                    code = 6;
                    break;
                case 8:
                case 9:
                    code = 4;
                    break;
                default:
                    if (!$assertionsDisabled) {
                        throw new AssertionError();
                    }
                    return;
            }
            SpeechRecognition.this.terminate(code);
        }

        public void onEvent(int event, Bundle bundle) {
        }

        public void onPartialResults(Bundle bundle) {
            handleResults(bundle, true);
        }

        public void onReadyForSpeech(Bundle bundle) {
            int unused = SpeechRecognition.this.mState = 1;
            SpeechRecognition.this.nativeOnAudioStart(SpeechRecognition.this.mNativeSpeechRecognizerImplAndroid);
        }

        public void onResults(Bundle bundle) {
            handleResults(bundle, false);
            SpeechRecognition.this.terminate(0);
        }

        public void onRmsChanged(float rms) {
        }

        private void handleResults(Bundle bundle, boolean provisional) {
            if (SpeechRecognition.this.mContinuous && provisional) {
                provisional = false;
            }
            ArrayList<String> list = bundle.getStringArrayList("results_recognition");
            float[] scores = bundle.getFloatArray("confidence_scores");
            SpeechRecognition.this.nativeOnRecognitionResults(SpeechRecognition.this.mNativeSpeechRecognizerImplAndroid, (String[]) list.toArray(new String[list.size()]), scores, provisional);
        }
    }

    public static boolean initialize(Context context) {
        if (!SpeechRecognizer.isRecognitionAvailable(context)) {
            return false;
        }
        PackageManager pm = context.getPackageManager();
        for (ResolveInfo resolve : pm.queryIntentServices(new Intent("android.speech.RecognitionService"), 4)) {
            ServiceInfo service = resolve.serviceInfo;
            if (service.packageName.equals(PROVIDER_PACKAGE_NAME)) {
                try {
                    if (pm.getPackageInfo(service.packageName, 0).versionCode >= PROVIDER_MIN_VERSION) {
                        sRecognitionProvider = new ComponentName(service.packageName, service.name);
                        return true;
                    }
                } catch (PackageManager.NameNotFoundException e) {
                }
            }
        }
        return false;
    }

    private SpeechRecognition(Context context, long nativeSpeechRecognizerImplAndroid) {
        this.mContext = context;
        this.mNativeSpeechRecognizerImplAndroid = nativeSpeechRecognizerImplAndroid;
        this.mListener = new Listener();
        this.mIntent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        if (sRecognitionProvider != null) {
            this.mRecognizer = SpeechRecognizer.createSpeechRecognizer(this.mContext, sRecognitionProvider);
        } else {
            this.mRecognizer = SpeechRecognizer.createSpeechRecognizer(this.mContext);
        }
        this.mRecognizer.setRecognitionListener(this.mListener);
    }

    /* access modifiers changed from: private */
    public void terminate(int error) {
        if (this.mState != 0) {
            if (this.mState == 2) {
                nativeOnSoundEnd(this.mNativeSpeechRecognizerImplAndroid);
            }
            nativeOnAudioEnd(this.mNativeSpeechRecognizerImplAndroid);
            this.mState = 0;
        }
        if (error != 0) {
            nativeOnRecognitionError(this.mNativeSpeechRecognizerImplAndroid, error);
        }
        this.mRecognizer.destroy();
        this.mRecognizer = null;
        nativeOnRecognitionEnd(this.mNativeSpeechRecognizerImplAndroid);
        this.mNativeSpeechRecognizerImplAndroid = 0;
    }

    @CalledByNative
    private static SpeechRecognition createSpeechRecognition(Context context, long nativeSpeechRecognizerImplAndroid) {
        return new SpeechRecognition(context, nativeSpeechRecognizerImplAndroid);
    }

    @CalledByNative
    private void startRecognition(String language, boolean continuous, boolean interimResults) {
        if (this.mRecognizer != null) {
            this.mContinuous = continuous;
            this.mIntent.putExtra("android.speech.extra.DICTATION_MODE", continuous);
            this.mIntent.putExtra("android.speech.extra.LANGUAGE", language);
            this.mIntent.putExtra("android.speech.extra.PARTIAL_RESULTS", interimResults);
            this.mRecognizer.startListening(this.mIntent);
        }
    }

    @CalledByNative
    private void abortRecognition() {
        if (this.mRecognizer != null) {
            this.mRecognizer.cancel();
            terminate(1);
        }
    }

    @CalledByNative
    private void stopRecognition() {
        if (this.mRecognizer != null) {
            this.mContinuous = false;
            this.mRecognizer.stopListening();
        }
    }
}
