package org.chromium.content.browser;

import android.view.View;
import android.view.ViewTreeObserver;
import java.util.ArrayList;
import org.chromium.content.browser.PositionObserver;

public class ViewPositionObserver implements PositionObserver {
    private final ArrayList<PositionObserver.Listener> mListeners;
    private final int[] mPosition = new int[2];
    private ViewTreeObserver.OnPreDrawListener mPreDrawListener;
    private View mView;

    public ViewPositionObserver(View view) {
        this.mView = view;
        this.mListeners = new ArrayList<>();
        updatePosition();
        this.mPreDrawListener = new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                ViewPositionObserver.this.updatePosition();
                return true;
            }
        };
    }

    public int getPositionX() {
        updatePosition();
        return this.mPosition[0];
    }

    public int getPositionY() {
        updatePosition();
        return this.mPosition[1];
    }

    public void addListener(PositionObserver.Listener listener) {
        if (!this.mListeners.contains(listener)) {
            if (this.mListeners.isEmpty()) {
                this.mView.getViewTreeObserver().addOnPreDrawListener(this.mPreDrawListener);
                updatePosition();
            }
            this.mListeners.add(listener);
        }
    }

    public void removeListener(PositionObserver.Listener listener) {
        if (this.mListeners.contains(listener)) {
            this.mListeners.remove(listener);
            if (this.mListeners.isEmpty()) {
                this.mView.getViewTreeObserver().removeOnPreDrawListener(this.mPreDrawListener);
            }
        }
    }

    private void notifyListeners() {
        for (int i = 0; i < this.mListeners.size(); i++) {
            this.mListeners.get(i).onPositionChanged(this.mPosition[0], this.mPosition[1]);
        }
    }

    /* access modifiers changed from: private */
    public void updatePosition() {
        int previousPositionX = this.mPosition[0];
        int previousPositionY = this.mPosition[1];
        this.mView.getLocationInWindow(this.mPosition);
        if (this.mPosition[0] != previousPositionX || this.mPosition[1] != previousPositionY) {
            notifyListeners();
        }
    }

    public void clearListener() {
        this.mListeners.clear();
    }
}
