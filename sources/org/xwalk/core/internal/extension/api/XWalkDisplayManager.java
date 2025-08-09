package org.xwalk.core.internal.extension.api;

import android.content.Context;
import android.os.Build;
import android.view.Display;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class XWalkDisplayManager {
    static final /* synthetic */ boolean $assertionsDisabled = (!XWalkDisplayManager.class.desiredAssertionStatus());
    private static Context mContext;
    private static XWalkDisplayManager mInstance;
    protected final ArrayList<DisplayListener> mListeners = new ArrayList<>();

    public interface DisplayListener {
        void onDisplayAdded(int i);

        void onDisplayChanged(int i);

        void onDisplayRemoved(int i);
    }

    public abstract Display getDisplay(int i);

    public abstract Display[] getDisplays();

    public abstract Display[] getPresentationDisplays();

    public static XWalkDisplayManager getInstance(Context context) {
        if (mContext == null) {
            mContext = context.getApplicationContext();
        } else if (!$assertionsDisabled && context.getApplicationContext() != mContext) {
            throw new AssertionError();
        }
        if (mInstance == null) {
            if (Build.VERSION.SDK_INT >= 17) {
                mInstance = new DisplayManagerJBMR1(mContext);
            } else {
                mInstance = new DisplayManagerNull();
            }
        }
        return mInstance;
    }

    public void registerDisplayListener(DisplayListener listener) {
        this.mListeners.add(listener);
    }

    public void unregisterDisplayListener(DisplayListener listener) {
        this.mListeners.remove(listener);
    }

    /* access modifiers changed from: protected */
    public void notifyDisplayAdded(int displayId) {
        Iterator i$ = this.mListeners.iterator();
        while (i$.hasNext()) {
            i$.next().onDisplayAdded(displayId);
        }
    }

    /* access modifiers changed from: protected */
    public void notifyDisplayRemoved(int displayId) {
        Iterator i$ = this.mListeners.iterator();
        while (i$.hasNext()) {
            i$.next().onDisplayRemoved(displayId);
        }
    }

    /* access modifiers changed from: protected */
    public void notifyDisplayChanged(int displayId) {
        Iterator i$ = this.mListeners.iterator();
        while (i$.hasNext()) {
            i$.next().onDisplayChanged(displayId);
        }
    }
}
