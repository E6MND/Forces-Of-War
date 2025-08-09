package org.xwalk.core.internal.extensions;

import android.util.Log;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;

@JNINamespace("xwalk::extensions")
public abstract class XWalkExtensionAndroid {
    private static final String TAG = "XWalkExtensionAndroid";
    private long mXWalkExtension;

    private native void nativeBroadcastMessage(long j, String str);

    private native void nativeDestroyExtension(long j);

    private native long nativeGetOrCreateExtension(String str, String str2, String[] strArr);

    private native void nativePostMessage(long j, int i, String str);

    @CalledByNative
    public abstract void onMessage(int i, String str);

    @CalledByNative
    public abstract String onSyncMessage(int i, String str);

    public XWalkExtensionAndroid(String name, String jsApi) {
        this.mXWalkExtension = nativeGetOrCreateExtension(name, jsApi, (String[]) null);
    }

    public XWalkExtensionAndroid(String name, String jsApi, String[] entryPoints) {
        this.mXWalkExtension = nativeGetOrCreateExtension(name, jsApi, entryPoints);
    }

    /* access modifiers changed from: protected */
    public void destroyExtension() {
        if (this.mXWalkExtension == 0) {
            Log.e(TAG, "The extension to be destroyed is invalid!");
            return;
        }
        nativeDestroyExtension(this.mXWalkExtension);
        this.mXWalkExtension = 0;
    }

    public void postMessage(int instanceID, String message) {
        if (this.mXWalkExtension == 0) {
            Log.e(TAG, "Can not post a message to an invalid extension!");
        } else {
            nativePostMessage(this.mXWalkExtension, instanceID, message);
        }
    }

    public void broadcastMessage(String message) {
        if (this.mXWalkExtension == 0) {
            Log.e(TAG, "Can not broadcast message to an invalid extension!");
        } else {
            nativeBroadcastMessage(this.mXWalkExtension, message);
        }
    }
}
