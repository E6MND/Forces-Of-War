package com.google.android.gms.internal;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import com.google.android.gms.internal.ez;

public class dp implements Runnable {
    /* access modifiers changed from: private */
    public final int ks;
    /* access modifiers changed from: private */
    public final int kt;
    protected final ey lL;
    /* access modifiers changed from: private */
    public final Handler pI;
    /* access modifiers changed from: private */
    public final long pJ;
    /* access modifiers changed from: private */
    public long pK;
    /* access modifiers changed from: private */
    public ez.a pL;
    protected boolean pM;
    protected boolean pN;

    protected final class a extends AsyncTask<Void, Void, Boolean> {
        private final WebView pO;
        private Bitmap pP;

        public a(WebView webView) {
            this.pO = webView;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public synchronized Boolean doInBackground(Void... voidArr) {
            boolean z;
            int width = this.pP.getWidth();
            int height = this.pP.getHeight();
            if (width == 0 || height == 0) {
                z = false;
            } else {
                int i = 0;
                for (int i2 = 0; i2 < width; i2 += 10) {
                    for (int i3 = 0; i3 < height; i3 += 10) {
                        if (this.pP.getPixel(i2, i3) != 0) {
                            i++;
                        }
                    }
                }
                z = Boolean.valueOf(((double) i) / (((double) (width * height)) / 100.0d) > 0.1d);
            }
            return z;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Boolean bool) {
            dp.c(dp.this);
            if (bool.booleanValue() || dp.this.bl() || dp.this.pK <= 0) {
                dp.this.pN = bool.booleanValue();
                dp.this.pL.a(dp.this.lL);
            } else if (dp.this.pK > 0) {
                if (ev.p(2)) {
                    ev.z("Ad not detected, scheduling another run.");
                }
                dp.this.pI.postDelayed(dp.this, dp.this.pJ);
            }
        }

        /* access modifiers changed from: protected */
        public synchronized void onPreExecute() {
            this.pP = Bitmap.createBitmap(dp.this.ks, dp.this.kt, Bitmap.Config.ARGB_8888);
            this.pO.setVisibility(0);
            this.pO.measure(View.MeasureSpec.makeMeasureSpec(dp.this.ks, 0), View.MeasureSpec.makeMeasureSpec(dp.this.kt, 0));
            this.pO.layout(0, 0, dp.this.ks, dp.this.kt);
            this.pO.draw(new Canvas(this.pP));
            this.pO.invalidate();
        }
    }

    public dp(ez.a aVar, ey eyVar, int i, int i2) {
        this(aVar, eyVar, i, i2, 200, 50);
    }

    public dp(ez.a aVar, ey eyVar, int i, int i2, long j, long j2) {
        this.pJ = j;
        this.pK = j2;
        this.pI = new Handler(Looper.getMainLooper());
        this.lL = eyVar;
        this.pL = aVar;
        this.pM = false;
        this.pN = false;
        this.kt = i2;
        this.ks = i;
    }

    static /* synthetic */ long c(dp dpVar) {
        long j = dpVar.pK - 1;
        dpVar.pK = j;
        return j;
    }

    public void a(dv dvVar, fd fdVar) {
        this.lL.setWebViewClient(fdVar);
        this.lL.loadDataWithBaseURL(TextUtils.isEmpty(dvVar.oy) ? null : ep.v(dvVar.oy), dvVar.qb, "text/html", "UTF-8", (String) null);
    }

    public void b(dv dvVar) {
        a(dvVar, new fd(this, this.lL, dvVar.qk));
    }

    public void bj() {
        this.pI.postDelayed(this, this.pJ);
    }

    public synchronized void bk() {
        this.pM = true;
    }

    public synchronized boolean bl() {
        return this.pM;
    }

    public boolean bm() {
        return this.pN;
    }

    public void run() {
        if (this.lL == null || bl()) {
            this.pL.a(this.lL);
        } else {
            new a(this.lL).execute(new Void[0]);
        }
    }
}
