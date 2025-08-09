package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Region;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.CBOrientation;
import org.chromium.ui.base.PageTransition;

public class t extends AbsoluteLayout {
    private Matrix a = new Matrix();
    private Matrix b = new Matrix();
    private float[] c = new float[2];
    private View d;
    private RelativeLayout e;

    public t(Context context) {
        super(context);
        this.e = new RelativeLayout(context);
        addView(this.e, new AbsoluteLayout.LayoutParams(-1, -1, 0, 0));
        this.d = this.e;
    }

    public void a(View view) {
        a(view, new RelativeLayout.LayoutParams(-2, -2));
    }

    public void a(View view, RelativeLayout.LayoutParams layoutParams) {
        if (this.e != null) {
            this.e.addView(view, layoutParams);
            return;
        }
        throw new IllegalStateException("cannot call addViewToContainer() on CBRotatableContainer that was set up with a default view");
    }

    public t(Context context, View view) {
        super(context);
        addView(view, new AbsoluteLayout.LayoutParams(-1, -1, 0, 0));
        this.d = view;
    }

    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        int i;
        super.onSizeChanged(w, h, oldw, oldh);
        CBOrientation.Difference forcedOrientationDifference = Chartboost.sharedChartboost().getForcedOrientationDifference();
        ViewGroup.LayoutParams layoutParams = this.d.getLayoutParams();
        if (forcedOrientationDifference.isOdd()) {
            i = h;
        } else {
            i = w;
        }
        layoutParams.width = i;
        if (!forcedOrientationDifference.isOdd()) {
            w = h;
        }
        layoutParams.height = w;
        this.d.setLayoutParams(layoutParams);
        this.d.measure(View.MeasureSpec.makeMeasureSpec(layoutParams.width, PageTransition.CLIENT_REDIRECT), View.MeasureSpec.makeMeasureSpec(layoutParams.height, PageTransition.CLIENT_REDIRECT));
        this.d.layout(0, 0, layoutParams.width, layoutParams.height);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (Chartboost.sharedChartboost().getForcedOrientationDifference().isOdd()) {
            super.onMeasure(heightMeasureSpec, widthMeasureSpec);
            setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
            return;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        CBOrientation.Difference forcedOrientationDifference = Chartboost.sharedChartboost().getForcedOrientationDifference();
        int asInt = forcedOrientationDifference.getAsInt();
        if (forcedOrientationDifference == CBOrientation.Difference.ANGLE_0) {
            super.dispatchDraw(canvas);
            return;
        }
        canvas.save();
        canvas.clipRect(0.0f, 0.0f, (float) getWidth(), (float) getHeight(), Region.Op.REPLACE);
        try {
            ViewGroup viewGroup = (ViewGroup) getParent();
            try {
                ViewGroup viewGroup2 = (ViewGroup) viewGroup.getParent();
                if ((viewGroup2 instanceof ScrollView) || (viewGroup2 instanceof HorizontalScrollView)) {
                    viewGroup = viewGroup2;
                }
            } catch (Exception e2) {
            }
            int left = getLeft() - viewGroup.getScrollX();
            int top = getTop() - viewGroup.getScrollY();
            canvas.clipRect((float) (0 - left), (float) (0 - top), (float) (viewGroup.getWidth() - left), (float) (viewGroup.getHeight() - top), Region.Op.INTERSECT);
        } catch (Exception e3) {
        }
        canvas.translate(((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f);
        canvas.rotate((float) asInt);
        if (forcedOrientationDifference.isOdd()) {
            canvas.translate(((float) (-getHeight())) / 2.0f, ((float) (-getWidth())) / 2.0f);
        } else {
            canvas.translate(((float) (-getWidth())) / 2.0f, ((float) (-getHeight())) / 2.0f);
        }
        this.a = canvas.getMatrix();
        this.a.invert(this.b);
        super.dispatchDraw(canvas);
        canvas.restore();
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        if (Chartboost.sharedChartboost().getForcedOrientationDifference() == CBOrientation.Difference.ANGLE_0) {
            return super.dispatchTouchEvent(event);
        }
        float[] fArr = this.c;
        fArr[0] = event.getRawX();
        fArr[1] = event.getRawY();
        this.b.mapPoints(fArr);
        event.setLocation(fArr[0], fArr[1]);
        return super.dispatchTouchEvent(event);
    }

    public View a() {
        return this.d;
    }
}
