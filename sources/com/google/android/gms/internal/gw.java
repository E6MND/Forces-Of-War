package com.google.android.gms.internal;

import android.graphics.Canvas;
import android.graphics.Path;
import android.net.Uri;
import android.widget.ImageView;

public final class gw extends ImageView {
    private Uri FL;
    private int FM;
    private int FN;
    private a FO;

    public interface a {
        Path d(int i, int i2);
    }

    public void al(int i) {
        this.FM = i;
    }

    public void f(Uri uri) {
        this.FL = uri;
    }

    public int fd() {
        return this.FM;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.FO != null) {
            canvas.clipPath(this.FO.d(getWidth(), getHeight()));
        }
        super.onDraw(canvas);
        if (this.FN != 0) {
            canvas.drawColor(this.FN);
        }
    }
}
