package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Handler;
import android.view.View;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.CBOrientation;
import java.lang.reflect.Method;

public class u extends View {
    private Handler a;
    /* access modifiers changed from: private */
    public float b;
    private long c;
    private Paint d;
    private Paint e;
    private Path f;
    private Path g;
    private RectF h;
    private RectF i;
    private Bitmap j = null;
    private Canvas k = null;
    private Runnable l = new Runnable() {
        public void run() {
            CBOrientation.Difference forcedOrientationDifference = Chartboost.sharedChartboost().getForcedOrientationDifference();
            float f = u.this.getContext().getResources().getDisplayMetrics().density;
            u uVar = u.this;
            uVar.b = uVar.b + (1.0f * f);
            float width = ((float) (forcedOrientationDifference.isOdd() ? u.this.getWidth() : u.this.getHeight())) - (f * 9.0f);
            if (u.this.b > width) {
                u uVar2 = u.this;
                uVar2.b = uVar2.b - (width * 2.0f);
            }
            if (u.this.getWindowVisibility() == 0) {
                u.this.invalidate();
            }
        }
    };

    public u(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        float f2 = context.getResources().getDisplayMetrics().density;
        this.b = 0.0f;
        this.a = new Handler();
        this.c = (long) (((double) System.nanoTime()) / 1000000.0d);
        this.d = new Paint();
        this.d.setColor(-1);
        this.d.setStyle(Paint.Style.STROKE);
        this.d.setStrokeWidth(f2 * 3.0f);
        this.d.setAntiAlias(true);
        this.e = new Paint();
        this.e.setColor(-1);
        this.e.setStyle(Paint.Style.FILL);
        this.e.setAntiAlias(true);
        this.f = new Path();
        this.g = new Path();
        this.i = new RectF();
        this.h = new RectF();
        try {
            Method method = getClass().getMethod("setLayerType", new Class[]{Integer.TYPE, Paint.class});
            Object[] objArr = new Object[2];
            objArr[0] = 1;
            method.invoke(this, objArr);
        } catch (Exception e2) {
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        float width;
        float f2 = getContext().getResources().getDisplayMetrics().density;
        if (!(this.j != null && this.j.getWidth() == canvas.getWidth() && this.j.getHeight() == canvas.getHeight())) {
            if (this.j != null && !this.j.isRecycled()) {
                this.j.recycle();
            }
            this.j = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
            this.k = new Canvas(this.j);
        }
        this.j.eraseColor(0);
        Canvas canvas2 = this.k;
        CBOrientation.Difference forcedOrientationDifference = Chartboost.sharedChartboost().getForcedOrientationDifference();
        canvas2.save();
        if (forcedOrientationDifference.isReverse()) {
            canvas2.rotate(180.0f, ((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f);
        }
        this.h.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
        this.h.inset(1.5f * f2, 1.5f * f2);
        float width2 = ((float) (forcedOrientationDifference.isOdd() ? getWidth() : getHeight())) / 2.0f;
        canvas2.drawRoundRect(this.h, width2, width2, this.d);
        this.i.set(this.h);
        this.i.inset(3.0f * f2, f2 * 3.0f);
        float width3 = (forcedOrientationDifference.isOdd() ? this.i.width() : this.i.height()) / 2.0f;
        this.f.reset();
        this.f.addRoundRect(this.i, width3, width3, Path.Direction.CW);
        float width4 = forcedOrientationDifference.isOdd() ? this.i.width() : this.i.height();
        this.g.reset();
        if (forcedOrientationDifference.isOdd()) {
            this.g.moveTo(width4, 0.0f);
            this.g.lineTo(width4, width4);
            this.g.lineTo(0.0f, width4 * 2.0f);
            this.g.lineTo(0.0f, width4);
        } else {
            this.g.moveTo(0.0f, width4);
            this.g.lineTo(width4, width4);
            this.g.lineTo(width4 * 2.0f, 0.0f);
            this.g.lineTo(width4, 0.0f);
        }
        this.g.close();
        canvas2.save();
        canvas2.clipPath(this.f);
        float f3 = -width4;
        float f4 = this.b;
        while (true) {
            f3 += f4;
            if (forcedOrientationDifference.isOdd()) {
                width = this.i.height();
            } else {
                width = this.i.width();
            }
            if (f3 >= width + width4) {
                break;
            }
            float f5 = (forcedOrientationDifference.isOdd() ? this.i.top : this.i.left) + f3;
            canvas2.save();
            if (forcedOrientationDifference.isOdd()) {
                canvas2.translate(this.i.left, f5);
            } else {
                canvas2.translate(f5, this.i.top);
            }
            canvas2.drawPath(this.g, this.e);
            canvas2.restore();
            f4 = 2.0f * width4;
        }
        canvas2.restore();
        canvas2.restore();
        if (canvas != null) {
            canvas.drawBitmap(this.j, 0.0f, 0.0f, (Paint) null);
        } else {
            Canvas canvas3 = canvas2;
        }
        long max = Math.max(0, 16 - (((long) (((double) System.nanoTime()) / 1000000.0d)) - this.c));
        this.a.removeCallbacks(this.l);
        this.a.postDelayed(this.l, max);
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        this.a.removeCallbacks(this.l);
        if (visibility == 0) {
            this.a.post(this.l);
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        this.a.removeCallbacks(this.l);
        this.a.post(this.l);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        this.a.removeCallbacks(this.l);
        if (this.j != null && !this.j.isRecycled()) {
            this.j.recycle();
        }
        this.j = null;
    }
}
