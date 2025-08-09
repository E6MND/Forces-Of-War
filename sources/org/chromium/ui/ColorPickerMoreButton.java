package org.chromium.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.Button;

public class ColorPickerMoreButton extends Button {
    private Paint mBorderPaint;

    public ColorPickerMoreButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ColorPickerMoreButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void init() {
        this.mBorderPaint = new Paint();
        this.mBorderPaint.setStyle(Paint.Style.STROKE);
        this.mBorderPaint.setColor(-1);
        this.mBorderPaint.setStrokeWidth(1.0f);
        this.mBorderPaint.setAntiAlias(false);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        canvas.drawRect(0.5f, 0.5f, ((float) getWidth()) - 1.5f, ((float) getHeight()) - 1.5f, this.mBorderPaint);
        super.onDraw(canvas);
    }
}
