package org.chromium.content.common;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;

public class CleanupReference extends WeakReference<Object> {
    private static final int ADD_REF = 1;
    private static final boolean DEBUG = false;
    private static final int REMOVE_REF = 2;
    private static final String TAG = "CleanupReference";
    /* access modifiers changed from: private */
    public static Object sCleanupMonitor = new Object();
    /* access modifiers changed from: private */
    public static ReferenceQueue<Object> sGcQueue = new ReferenceQueue<>();
    private static final Thread sReaperThread = new Thread(TAG) {
        public void run() {
            while (true) {
                try {
                    CleanupReference ref = (CleanupReference) CleanupReference.sGcQueue.remove();
                    synchronized (CleanupReference.sCleanupMonitor) {
                        Message.obtain(LazyHolder.sHandler, 2, ref).sendToTarget();
                        CleanupReference.sCleanupMonitor.wait(500);
                    }
                } catch (Exception e) {
                    Log.e(CleanupReference.TAG, "Queue remove exception:", e);
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public static Set<CleanupReference> sRefs = new HashSet();
    private Runnable mCleanupTask;

    static {
        sReaperThread.setDaemon(true);
        sReaperThread.start();
    }

    private static class LazyHolder {
        static final Handler sHandler = new Handler(ThreadUtils.getUiThreadLooper()) {
            public void handleMessage(Message msg) {
                TraceEvent.begin();
                CleanupReference ref = (CleanupReference) msg.obj;
                switch (msg.what) {
                    case 1:
                        CleanupReference.sRefs.add(ref);
                        break;
                    case 2:
                        ref.runCleanupTaskInternal();
                        break;
                    default:
                        Log.e(CleanupReference.TAG, "Bad message=" + msg.what);
                        break;
                }
                synchronized (CleanupReference.sCleanupMonitor) {
                    while (true) {
                        CleanupReference ref2 = (CleanupReference) CleanupReference.sGcQueue.poll();
                        if (ref2 != null) {
                            ref2.runCleanupTaskInternal();
                        } else {
                            CleanupReference.sCleanupMonitor.notifyAll();
                        }
                    }
                }
                TraceEvent.end();
            }
        };

        private LazyHolder() {
        }
    }

    public CleanupReference(Object obj, Runnable cleanupTask) {
        super(obj, sGcQueue);
        this.mCleanupTask = cleanupTask;
        handleOnUiThread(1);
    }

    public void cleanupNow() {
        handleOnUiThread(2);
    }

    private void handleOnUiThread(int what) {
        Message msg = Message.obtain(LazyHolder.sHandler, what, this);
        if (Looper.myLooper() == msg.getTarget().getLooper()) {
            msg.getTarget().handleMessage(msg);
            msg.recycle();
            return;
        }
        msg.sendToTarget();
    }

    /* access modifiers changed from: private */
    public void runCleanupTaskInternal() {
        sRefs.remove(this);
        if (this.mCleanupTask != null) {
            this.mCleanupTask.run();
            this.mCleanupTask = null;
        }
        clear();
    }
}
