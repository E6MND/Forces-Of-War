package org.xwalk.core.internal.extension.api;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.support.v4.hardware.display.DisplayManagerCompat;
import android.view.Display;
import com.facebook.internal.ServerProtocol;
import org.xwalk.core.internal.extension.api.XWalkDisplayManager;

public class DisplayManagerJBMR1 extends XWalkDisplayManager implements DisplayManager.DisplayListener {
    private DisplayManager mDisplayManager;

    public DisplayManagerJBMR1(Context context) {
        this.mDisplayManager = (DisplayManager) context.getSystemService(ServerProtocol.DIALOG_PARAM_DISPLAY);
    }

    public Display getDisplay(int displayId) {
        return this.mDisplayManager.getDisplay(displayId);
    }

    public Display[] getDisplays() {
        return this.mDisplayManager.getDisplays();
    }

    public Display[] getPresentationDisplays() {
        return this.mDisplayManager.getDisplays(DisplayManagerCompat.DISPLAY_CATEGORY_PRESENTATION);
    }

    public void registerDisplayListener(XWalkDisplayManager.DisplayListener listener) {
        super.registerDisplayListener(listener);
        if (this.mListeners.size() == 1) {
            this.mDisplayManager.registerDisplayListener(this, (Handler) null);
        }
    }

    public void unregisterDisplayListener(XWalkDisplayManager.DisplayListener listener) {
        super.unregisterDisplayListener(listener);
        if (this.mListeners.size() == 0) {
            this.mDisplayManager.unregisterDisplayListener(this);
        }
    }

    public void onDisplayAdded(int displayId) {
        notifyDisplayAdded(displayId);
    }

    public void onDisplayRemoved(int displayId) {
        notifyDisplayRemoved(displayId);
    }

    public void onDisplayChanged(int displayId) {
        notifyDisplayChanged(displayId);
    }
}
