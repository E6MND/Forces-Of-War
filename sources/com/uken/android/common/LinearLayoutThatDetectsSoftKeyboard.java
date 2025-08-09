package com.uken.android.common;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class LinearLayoutThatDetectsSoftKeyboard extends LinearLayout {
    private Listener listener;
    Rect rect = new Rect();

    public interface Listener {
        void onSoftKeyboardShown(boolean z);
    }

    public LinearLayoutThatDetectsSoftKeyboard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setListener(Listener listener2) {
        this.listener = listener2;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = View.MeasureSpec.getSize(heightMeasureSpec);
        Activity activity = (Activity) getContext();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(this.rect);
        int diff = (activity.getWindowManager().getDefaultDisplay().getHeight() - this.rect.top) - height;
        if (this.listener != null) {
            this.listener.onSoftKeyboardShown(diff > 100);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
