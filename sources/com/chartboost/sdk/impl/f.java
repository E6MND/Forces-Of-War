package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.widget.Button;

public class f extends Button {
    /* access modifiers changed from: private */
    public Path a;
    /* access modifiers changed from: private */
    public Path b;
    /* access modifiers changed from: private */
    public Path c;
    /* access modifiers changed from: private */
    public RectF d;
    /* access modifiers changed from: private */
    public Paint e;
    /* access modifiers changed from: private */
    public Paint f;
    /* access modifiers changed from: private */
    public Shader g;
    /* access modifiers changed from: private */
    public Shader h;
    private int i;

    public f(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        float f2 = context.getResources().getDisplayMetrics().density;
        setTextSize(2, 13.0f);
        setShadowLayer(1.0f * f2, 1.0f * f2, 1.0f * f2, -16757901);
        setTypeface((Typeface) null, 1);
        setTextColor(-1);
        setClickable(true);
        setGravity(17);
        setPadding(Math.round(12.0f * f2), Math.round(5.0f * f2), Math.round(12.0f * f2), Math.round(f2 * 5.0f));
        this.a = new Path();
        this.b = new Path();
        this.c = new Path();
        this.d = new RectF();
        this.e = new Paint();
        this.e.setStyle(Paint.Style.STROKE);
        this.e.setColor(-4496384);
        this.e.setAntiAlias(true);
        this.i = -1;
        this.f = new Paint();
        this.f.setStyle(Paint.Style.FILL);
        this.f.setAntiAlias(true);
        setBackgroundDrawable(new Drawable() {
            boolean a = false;

            public void draw(Canvas canvas) {
                float f = f.this.getContext().getResources().getDisplayMetrics().density;
                int[] state = getState();
                boolean z = false;
                for (int i : state) {
                    if (i == 16842919) {
                        z = true;
                    }
                }
                float f2 = 6.0f * f;
                f.this.a.reset();
                f.this.d.set(getBounds());
                f.this.a.addRoundRect(f.this.d, f2, f2, Path.Direction.CW);
                f.this.a();
                f.this.f.setShader(z ? f.this.h : f.this.g);
                canvas.drawPath(f.this.a, f.this.f);
                float f3 = 1.0f * f;
                f.this.b.reset();
                f.this.d.inset(f3 / 2.0f, f3 / 2.0f);
                f.this.b.addRoundRect(f.this.d, f2, f2, Path.Direction.CW);
                f.this.c.reset();
                f.this.d.offset(0.0f, f3 / 2.0f);
                f.this.c.addRoundRect(f.this.d, f2, f2, Path.Direction.CW);
                if (!z) {
                    f.this.e.setColor(-1);
                    canvas.drawPath(f.this.c, f.this.e);
                }
                f.this.e.setColor(-4496384);
                canvas.drawPath(f.this.b, f.this.e);
            }

            public void setAlpha(int alpha) {
                f.this.e.setAlpha(alpha);
                f.this.f.setAlpha(alpha);
            }

            public void setColorFilter(ColorFilter cf) {
                f.this.e.setColorFilter(cf);
                f.this.f.setColorFilter(cf);
            }

            public int getOpacity() {
                return 1;
            }

            /* access modifiers changed from: protected */
            public boolean onStateChange(int[] states) {
                boolean z = false;
                for (int i : states) {
                    if (i == 16842919) {
                        z = true;
                    }
                }
                if (this.a == z) {
                    return false;
                }
                this.a = z;
                invalidateSelf();
                return true;
            }

            public boolean isStateful() {
                return true;
            }
        });
    }

    /* access modifiers changed from: private */
    public void a() {
        int height = getHeight();
        if (height != this.i) {
            this.i = height;
            this.g = new LinearGradient(0.0f, 0.0f, 0.0f, (float) getHeight(), new int[]{-81366, -89600, -97280}, (float[]) null, Shader.TileMode.CLAMP);
            this.h = new LinearGradient(0.0f, 0.0f, 0.0f, (float) getHeight(), new int[]{-97280, -89600, -81366}, (float[]) null, Shader.TileMode.CLAMP);
        }
    }
}
