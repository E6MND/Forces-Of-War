package org.chromium.content.browser;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.ActionMode;
import android.view.KeyEvent;
import java.net.URISyntaxException;
import org.chromium.content.browser.SelectActionModeCallback;

public class ContentViewClient {
    private static final String TAG = "ContentViewClient";

    public void onUpdateTitle(String title) {
    }

    public void onBackgroundColorChanged(int color) {
    }

    public void onOffsetsForFullscreenChanged(float topControlsOffsetYPix, float contentOffsetYPix, float overdrawBottomHeightPix) {
    }

    public boolean shouldOverrideKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (!shouldPropagateKey(keyCode)) {
            return true;
        }
        if (!event.isCtrlPressed() || (keyCode != 61 && keyCode != 51 && keyCode != 134)) {
            return false;
        }
        return true;
    }

    public void onImeEvent() {
    }

    public void onImeStateChangeRequested(boolean requestShow) {
    }

    public ActionMode.Callback getSelectActionModeCallback(Context context, SelectActionModeCallback.ActionHandler actionHandler, boolean incognito) {
        return new SelectActionModeCallback(context, actionHandler, incognito);
    }

    public void onContextualActionBarShown() {
    }

    public void onContextualActionBarHidden() {
    }

    public void performWebSearch(String searchQuery) {
    }

    public boolean doesPerformWebSearch() {
        return false;
    }

    public void onSelectionChanged(String selection) {
    }

    public void onSelectionEvent(int eventType) {
    }

    public void onSelectionEvent(int eventType, float posXPix, float posYPix) {
        onSelectionEvent(eventType);
    }

    public void onStartContentIntent(Context context, String intentUrl) {
        try {
            try {
                context.startActivity(Intent.parseUri(intentUrl, 1));
            } catch (ActivityNotFoundException e) {
                Log.w(TAG, "No application can handle " + intentUrl);
            }
        } catch (URISyntaxException ex) {
            Log.w(TAG, "Bad URI " + intentUrl + ": " + ex.getMessage());
        }
    }

    public ContentVideoViewClient getContentVideoViewClient() {
        return null;
    }

    public boolean shouldBlockMediaRequest(String url) {
        return false;
    }

    public static boolean shouldPropagateKey(int keyCode) {
        if (keyCode == 82 || keyCode == 3 || keyCode == 4 || keyCode == 5 || keyCode == 6 || keyCode == 26 || keyCode == 79 || keyCode == 27 || keyCode == 80 || keyCode == 25 || keyCode == 164 || keyCode == 24) {
            return false;
        }
        return true;
    }
}
