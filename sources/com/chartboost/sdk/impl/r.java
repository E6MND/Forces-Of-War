package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.CBOrientation;
import com.chartboost.sdk.impl.s;

public class r extends LinearLayout implements s.a {
    private static /* synthetic */ int[] d;
    private TextView a;
    private t b;
    private u c;

    static /* synthetic */ int[] b() {
        int[] iArr = d;
        if (iArr == null) {
            iArr = new int[CBOrientation.Difference.values().length];
            try {
                iArr[CBOrientation.Difference.ANGLE_0.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[CBOrientation.Difference.ANGLE_180.ordinal()] = 3;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[CBOrientation.Difference.ANGLE_270.ordinal()] = 4;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[CBOrientation.Difference.ANGLE_90.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            d = iArr;
        }
        return iArr;
    }

    public r(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        setGravity(17);
        this.a = new TextView(getContext());
        this.a.setTextColor(-1);
        this.a.setTextSize(2, 16.0f);
        this.a.setTypeface((Typeface) null, 1);
        this.a.setText("Loading...");
        this.a.setGravity(17);
        this.b = new t(context, this.a);
        this.c = new u(getContext());
        addView(this.b);
        addView(this.c);
        a();
    }

    public void a() {
        removeView(this.b);
        removeView(this.c);
        float f = getContext().getResources().getDisplayMetrics().density;
        int round = Math.round(20.0f * f);
        switch (b()[Chartboost.sharedChartboost().getForcedOrientationDifference().ordinal()]) {
            case 2:
                setOrientation(0);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Math.round(f * 32.0f), -1);
                layoutParams.setMargins(round, round, 0, round);
                addView(this.c, layoutParams);
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -1);
                layoutParams2.setMargins(round, round, round, round);
                addView(this.b, layoutParams2);
                return;
            case 3:
                setOrientation(1);
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, Math.round(f * 32.0f));
                layoutParams3.setMargins(round, round, round, 0);
                addView(this.c, layoutParams3);
                LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, -2);
                layoutParams4.setMargins(round, round, round, round);
                addView(this.b, layoutParams4);
                return;
            case 4:
                setOrientation(0);
                LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-2, -1);
                layoutParams5.setMargins(round, round, 0, round);
                addView(this.b, layoutParams5);
                LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(Math.round(f * 32.0f), -1);
                layoutParams6.setMargins(round, round, round, round);
                addView(this.c, layoutParams6);
                return;
            default:
                setOrientation(1);
                LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(-1, -2);
                layoutParams7.setMargins(round, round, round, 0);
                addView(this.b, layoutParams7);
                LinearLayout.LayoutParams layoutParams8 = new LinearLayout.LayoutParams(-1, Math.round(f * 32.0f));
                layoutParams8.setMargins(round, round, round, round);
                addView(this.c, layoutParams8);
                return;
        }
    }
}
