package org.chromium.ui.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;
import org.chromium.ui.VSyncMonitor;

@JNINamespace("ui")
public class WindowAndroid {
    static final /* synthetic */ boolean $assertionsDisabled = (!WindowAndroid.class.desiredAssertionStatus());
    public static final int START_INTENT_FAILURE = -1;
    private static final String TAG = "WindowAndroid";
    static final String WINDOW_CALLBACK_ERRORS = "window_callback_errors";
    protected Context mApplicationContext;
    protected HashMap<Integer, String> mIntentErrors;
    /* access modifiers changed from: private */
    public long mNativeWindowAndroid = 0;
    protected SparseArray<IntentCallback> mOutstandingIntents;
    private final VSyncMonitor.Listener mVSyncListener = new VSyncMonitor.Listener() {
        public void onVSync(VSyncMonitor monitor, long vsyncTimeMicros) {
            if (WindowAndroid.this.mNativeWindowAndroid != 0) {
                WindowAndroid.this.nativeOnVSync(WindowAndroid.this.mNativeWindowAndroid, vsyncTimeMicros, WindowAndroid.this.mVSyncMonitor.getVSyncPeriodInMicroseconds());
            }
        }
    };
    /* access modifiers changed from: private */
    public final VSyncMonitor mVSyncMonitor;

    public interface IntentCallback {
        void onIntentCompleted(WindowAndroid windowAndroid, int i, ContentResolver contentResolver, Intent intent);
    }

    private native void nativeDestroy(long j);

    private native long nativeInit();

    /* access modifiers changed from: private */
    public native void nativeOnVSync(long j, long j2, long j3);

    public boolean isInsideVSync() {
        return this.mVSyncMonitor.isInsideVSync();
    }

    @SuppressLint({"UseSparseArrays"})
    public WindowAndroid(Context context) {
        if ($assertionsDisabled || context == context.getApplicationContext()) {
            this.mApplicationContext = context;
            this.mOutstandingIntents = new SparseArray<>();
            this.mIntentErrors = new HashMap<>();
            this.mVSyncMonitor = new VSyncMonitor(context, this.mVSyncListener);
            return;
        }
        throw new AssertionError();
    }

    public boolean showIntent(PendingIntent intent, IntentCallback callback, int errorId) {
        return showCancelableIntent(intent, callback, errorId) >= 0;
    }

    public boolean showIntent(Intent intent, IntentCallback callback, int errorId) {
        return showCancelableIntent(intent, callback, errorId) >= 0;
    }

    public int showCancelableIntent(PendingIntent intent, IntentCallback callback, int errorId) {
        Log.d(TAG, "Can't show intent as context is not an Activity: " + intent);
        return -1;
    }

    public int showCancelableIntent(Intent intent, IntentCallback callback, int errorId) {
        Log.d(TAG, "Can't show intent as context is not an Activity: " + intent);
        return -1;
    }

    public void cancelIntent(int requestCode) {
        Log.d(TAG, "Can't cancel intent as context is not an Activity: " + requestCode);
    }

    public boolean removeIntentCallback(IntentCallback callback) {
        int requestCode = this.mOutstandingIntents.indexOfValue(callback);
        if (requestCode < 0) {
            return false;
        }
        this.mOutstandingIntents.remove(requestCode);
        this.mIntentErrors.remove(Integer.valueOf(requestCode));
        return true;
    }

    public void showError(String error) {
        if (error != null) {
            Toast.makeText(this.mApplicationContext, error, 0).show();
        }
    }

    public void showError(int resId) {
        showError(this.mApplicationContext.getString(resId));
    }

    /* access modifiers changed from: protected */
    public void showCallbackNonExistentError(String error) {
        showError(error);
    }

    public void sendBroadcast(Intent intent) {
        this.mApplicationContext.sendBroadcast(intent);
    }

    public WeakReference<Activity> getActivity() {
        return new WeakReference<>((Object) null);
    }

    public Context getApplicationContext() {
        return this.mApplicationContext;
    }

    public void saveInstanceState(Bundle bundle) {
        bundle.putSerializable(WINDOW_CALLBACK_ERRORS, this.mIntentErrors);
    }

    public void restoreInstanceState(Bundle bundle) {
        if (bundle != null) {
            Serializable errors = bundle.getSerializable(WINDOW_CALLBACK_ERRORS);
            if (errors instanceof HashMap) {
                this.mIntentErrors = (HashMap) errors;
            }
        }
    }

    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        return false;
    }

    @CalledByNative
    private void requestVSyncUpdate() {
        this.mVSyncMonitor.requestUpdate();
    }

    public boolean canResolveActivity(Intent intent) {
        return this.mApplicationContext.getPackageManager().resolveActivity(intent, 0) != null;
    }

    public void destroy() {
        if (this.mNativeWindowAndroid != 0) {
            nativeDestroy(this.mNativeWindowAndroid);
            this.mNativeWindowAndroid = 0;
        }
    }

    public long getNativePointer() {
        if (this.mNativeWindowAndroid == 0) {
            this.mNativeWindowAndroid = nativeInit();
        }
        return this.mNativeWindowAndroid;
    }
}
