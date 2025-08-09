package org.chromium.base;

import android.os.Handler;
import android.os.HandlerThread;

@JNINamespace("base::android")
class JavaHandlerThread {
    final HandlerThread mThread;

    /* access modifiers changed from: private */
    public native void nativeInitializeThread(long j, long j2);

    /* access modifiers changed from: private */
    public native void nativeStopThread(long j, long j2);

    private JavaHandlerThread(String name) {
        this.mThread = new HandlerThread(name);
    }

    @CalledByNative
    private static JavaHandlerThread create(String name) {
        return new JavaHandlerThread(name);
    }

    @CalledByNative
    private void start(long nativeThread, long nativeEvent) {
        this.mThread.start();
        final long j = nativeThread;
        final long j2 = nativeEvent;
        new Handler(this.mThread.getLooper()).post(new Runnable() {
            public void run() {
                JavaHandlerThread.this.nativeInitializeThread(j, j2);
            }
        });
    }

    @CalledByNative
    private void stop(long nativeThread, long nativeEvent) {
        final long j = nativeThread;
        final long j2 = nativeEvent;
        new Handler(this.mThread.getLooper()).post(new Runnable() {
            public void run() {
                JavaHandlerThread.this.nativeStopThread(j, j2);
            }
        });
        this.mThread.quitSafely();
    }
}
