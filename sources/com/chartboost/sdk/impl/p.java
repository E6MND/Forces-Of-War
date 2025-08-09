package com.chartboost.sdk.impl;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class p extends Animation {
    private final float a;
    private final float b;
    private final float c;
    private final float d;
    private boolean e = true;
    private Camera f;

    public p(float f2, float f3, float f4, float f5, boolean z) {
        this.a = f2;
        this.b = f3;
        this.c = f4;
        this.d = f5;
        this.e = z;
    }

    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        this.f = new Camera();
    }

    /* access modifiers changed from: protected */
    public void applyTransformation(float interpolatedTime, Transformation t) {
        float f2 = this.a + ((this.b - this.a) * interpolatedTime);
        Camera camera = this.f;
        Matrix matrix = t.getMatrix();
        camera.save();
        if (this.e) {
            camera.rotateY(f2);
        } else {
            camera.rotateX(f2);
        }
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-this.c, -this.d);
        matrix.postTranslate(this.c, this.d);
    }
}
