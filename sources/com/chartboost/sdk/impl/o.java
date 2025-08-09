package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;

public class o extends RelativeLayout {
    public a a;
    /* access modifiers changed from: private */
    public boolean b;

    public o(Context context) {
        super(context);
        this.a = new a(context);
        this.a.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        addView(this.a);
        setFocusable(false);
    }

    public void a(View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(510);
        alphaAnimation.setFillAfter(true);
        view.startAnimation(alphaAnimation);
    }

    public void a() {
        a((View) this.a);
    }

    public void a(boolean z) {
        this.b = z;
        this.a.a();
    }

    private class a extends View {
        public a(Context context) {
            super(context);
            setFocusable(false);
        }

        /* access modifiers changed from: protected */
        public void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            a();
        }

        public void a() {
            int i = -872415232;
            int i2 = o.this.b ? -2013265920 : -872415232;
            if (!o.this.b) {
                i = -2013265920;
            }
            GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TL_BR, new int[]{i, i2});
            gradientDrawable.setGradientType(1);
            gradientDrawable.setGradientRadius(((float) Math.min(getWidth(), getHeight())) / 2.0f);
            gradientDrawable.setGradientCenter(((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f);
            setBackgroundDrawable(gradientDrawable);
        }
    }
}
