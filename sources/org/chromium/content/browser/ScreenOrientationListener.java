package org.chromium.content.browser;

import android.annotation.SuppressLint;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import com.facebook.internal.ServerProtocol;
import java.util.Iterator;
import org.chromium.base.ObserverList;
import org.chromium.base.ThreadUtils;
import org.chromium.base.VisibleForTesting;
import org.chromium.ui.gfx.DeviceDisplayInfo;

@VisibleForTesting
public class ScreenOrientationListener {
    static final /* synthetic */ boolean $assertionsDisabled = (!ScreenOrientationListener.class.desiredAssertionStatus());
    private static final String TAG = "ScreenOrientationListener";
    private static ScreenOrientationListener sInstance;
    /* access modifiers changed from: private */
    public Context mAppContext;
    private ScreenOrientationListenerBackend mBackend;
    private final ObserverList<ScreenOrientationObserver> mObservers = new ObserverList<>();
    /* access modifiers changed from: private */
    public int mOrientation;

    private interface ScreenOrientationListenerBackend {
        void startAccurateListening();

        void startListening();

        void stopAccurateListening();

        void stopListening();
    }

    public interface ScreenOrientationObserver {
        void onScreenOrientationChanged(int i);
    }

    private class ScreenOrientationConfigurationListener implements ScreenOrientationListenerBackend, ComponentCallbacks {
        static final /* synthetic */ boolean $assertionsDisabled = (!ScreenOrientationListener.class.desiredAssertionStatus());
        private static final long POLLING_DELAY = 500;
        /* access modifiers changed from: private */
        public int mAccurateCount;

        private ScreenOrientationConfigurationListener() {
            this.mAccurateCount = 0;
        }

        public void startListening() {
            ScreenOrientationListener.this.mAppContext.registerComponentCallbacks(this);
        }

        public void stopListening() {
            ScreenOrientationListener.this.mAppContext.unregisterComponentCallbacks(this);
        }

        public void startAccurateListening() {
            this.mAccurateCount++;
            if (this.mAccurateCount <= 1) {
                ThreadUtils.postOnUiThreadDelayed(new Runnable() {
                    public void run() {
                        this.onConfigurationChanged((Configuration) null);
                        if (this.mAccurateCount >= 1) {
                            ThreadUtils.postOnUiThreadDelayed(this, ScreenOrientationConfigurationListener.POLLING_DELAY);
                        }
                    }
                }, POLLING_DELAY);
            }
        }

        public void stopAccurateListening() {
            this.mAccurateCount--;
            if (!$assertionsDisabled && this.mAccurateCount < 0) {
                throw new AssertionError();
            }
        }

        public void onConfigurationChanged(Configuration newConfig) {
            ScreenOrientationListener.this.notifyObservers();
        }

        public void onLowMemory() {
        }
    }

    @SuppressLint({"NewApi"})
    private class ScreenOrientationDisplayListener implements ScreenOrientationListenerBackend, DisplayManager.DisplayListener {
        private ScreenOrientationDisplayListener() {
        }

        public void startListening() {
            ((DisplayManager) ScreenOrientationListener.this.mAppContext.getSystemService(ServerProtocol.DIALOG_PARAM_DISPLAY)).registerDisplayListener(this, (Handler) null);
        }

        public void stopListening() {
            ((DisplayManager) ScreenOrientationListener.this.mAppContext.getSystemService(ServerProtocol.DIALOG_PARAM_DISPLAY)).unregisterDisplayListener(this);
        }

        public void startAccurateListening() {
        }

        public void stopAccurateListening() {
        }

        public void onDisplayAdded(int displayId) {
        }

        public void onDisplayRemoved(int displayId) {
        }

        public void onDisplayChanged(int displayId) {
            ScreenOrientationListener.this.notifyObservers();
        }
    }

    public static ScreenOrientationListener getInstance() {
        ThreadUtils.assertOnUiThread();
        if (sInstance == null) {
            sInstance = new ScreenOrientationListener();
        }
        return sInstance;
    }

    private ScreenOrientationListener() {
        this.mBackend = Build.VERSION.SDK_INT >= 17 ? new ScreenOrientationDisplayListener() : new ScreenOrientationConfigurationListener();
    }

    public void addObserver(ScreenOrientationObserver observer, Context context) {
        if (this.mAppContext == null) {
            this.mAppContext = context.getApplicationContext();
        }
        if (!$assertionsDisabled && this.mAppContext != context.getApplicationContext()) {
            throw new AssertionError();
        } else if (!$assertionsDisabled && this.mAppContext == null) {
            throw new AssertionError();
        } else if (!this.mObservers.addObserver(observer)) {
            Log.w(TAG, "Adding an observer that is already present!");
        } else {
            if (this.mObservers.size() == 1) {
                updateOrientation();
                this.mBackend.startListening();
            }
            final ScreenOrientationObserver obs = observer;
            ThreadUtils.assertOnUiThread();
            ThreadUtils.postOnUiThread((Runnable) new Runnable() {
                public void run() {
                    obs.onScreenOrientationChanged(ScreenOrientationListener.this.mOrientation);
                }
            });
        }
    }

    public void removeObserver(ScreenOrientationObserver observer) {
        if (!this.mObservers.removeObserver(observer)) {
            Log.w(TAG, "Removing an inexistent observer!");
        } else if (this.mObservers.isEmpty()) {
            this.mBackend.stopListening();
        }
    }

    public void startAccurateListening() {
        this.mBackend.startAccurateListening();
    }

    public void stopAccurateListening() {
        this.mBackend.stopAccurateListening();
    }

    /* access modifiers changed from: private */
    public void notifyObservers() {
        int previousOrientation = this.mOrientation;
        updateOrientation();
        if (this.mOrientation != previousOrientation) {
            DeviceDisplayInfo.create(this.mAppContext).updateNativeSharedDisplayInfo();
            Iterator i$ = this.mObservers.iterator();
            while (i$.hasNext()) {
                i$.next().onScreenOrientationChanged(this.mOrientation);
            }
        }
    }

    private void updateOrientation() {
        switch (((WindowManager) this.mAppContext.getSystemService("window")).getDefaultDisplay().getRotation()) {
            case 0:
                this.mOrientation = 0;
                return;
            case 1:
                this.mOrientation = 90;
                return;
            case 2:
                this.mOrientation = 180;
                return;
            case 3:
                this.mOrientation = -90;
                return;
            default:
                throw new IllegalStateException("Display.getRotation() shouldn't return that value");
        }
    }
}
