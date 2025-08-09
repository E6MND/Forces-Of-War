package com.google.android.gms.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.MutableContextWrapper;
import android.net.Uri;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class ey extends WebView implements DownloadListener {
    private final WindowManager lA;
    private final Object lq = new Object();
    private am nD;
    private final ew nE;
    private final l py;
    private boolean sA;
    private boolean sB;
    private boolean sC;
    private final ez sx;
    private final a sy;
    private cg sz;

    private static class a extends MutableContextWrapper {
        private Context lx;
        private Activity sD;

        public a(Context context) {
            super(context);
            setBaseContext(context);
        }

        public Context ca() {
            return this.sD;
        }

        public void setBaseContext(Context base) {
            this.lx = base.getApplicationContext();
            this.sD = base instanceof Activity ? (Activity) base : null;
            super.setBaseContext(this.lx);
        }

        public void startActivity(Intent intent) {
            if (this.sD != null) {
                this.sD.startActivity(intent);
                return;
            }
            intent.setFlags(268435456);
            this.lx.startActivity(intent);
        }
    }

    private ey(a aVar, am amVar, boolean z, boolean z2, l lVar, ew ewVar) {
        super(aVar);
        this.sy = aVar;
        this.nD = amVar;
        this.sA = z;
        this.py = lVar;
        this.nE = ewVar;
        this.lA = (WindowManager) getContext().getSystemService("window");
        setBackgroundColor(0);
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        ep.a((Context) aVar, ewVar.st, settings);
        if (Build.VERSION.SDK_INT >= 17) {
            es.a(getContext(), settings);
        } else if (Build.VERSION.SDK_INT >= 11) {
            er.a(getContext(), settings);
        }
        setDownloadListener(this);
        if (Build.VERSION.SDK_INT >= 11) {
            this.sx = new fb(this, z2);
        } else {
            this.sx = new ez(this, z2);
        }
        setWebViewClient(this.sx);
        if (Build.VERSION.SDK_INT >= 14) {
            setWebChromeClient(new fc(this));
        } else if (Build.VERSION.SDK_INT >= 11) {
            setWebChromeClient(new fa(this));
        }
        cb();
    }

    public static ey a(Context context, am amVar, boolean z, boolean z2, l lVar, ew ewVar) {
        return new ey(new a(context), amVar, z, z2, lVar, ewVar);
    }

    private void cb() {
        synchronized (this.lq) {
            if (this.sA || this.nD.md) {
                if (Build.VERSION.SDK_INT < 14) {
                    ev.z("Disabling hardware acceleration on an overlay.");
                    cc();
                } else {
                    ev.z("Enabling hardware acceleration on an overlay.");
                    cd();
                }
            } else if (Build.VERSION.SDK_INT < 18) {
                ev.z("Disabling hardware acceleration on an AdView.");
                cc();
            } else {
                ev.z("Enabling hardware acceleration on an AdView.");
                cd();
            }
        }
    }

    private void cc() {
        synchronized (this.lq) {
            if (!this.sB && Build.VERSION.SDK_INT >= 11) {
                er.d(this);
            }
            this.sB = true;
        }
    }

    private void cd() {
        synchronized (this.lq) {
            if (this.sB && Build.VERSION.SDK_INT >= 11) {
                er.e(this);
            }
            this.sB = false;
        }
    }

    /* access modifiers changed from: protected */
    public void E(String str) {
        synchronized (this.lq) {
            if (!isDestroyed()) {
                loadUrl(str);
            } else {
                ev.D("The webview is destroyed. Ignoring action.");
            }
        }
    }

    public am Q() {
        am amVar;
        synchronized (this.lq) {
            amVar = this.nD;
        }
        return amVar;
    }

    public void a(Context context, am amVar) {
        synchronized (this.lq) {
            this.sy.setBaseContext(context);
            this.sz = null;
            this.nD = amVar;
            this.sA = false;
            ep.b((WebView) this);
            loadUrl("about:blank");
            this.sx.reset();
        }
    }

    public void a(am amVar) {
        synchronized (this.lq) {
            this.nD = amVar;
            requestLayout();
        }
    }

    public void a(cg cgVar) {
        synchronized (this.lq) {
            this.sz = cgVar;
        }
    }

    public void a(String str, Map<String, ?> map) {
        try {
            b(str, ep.o(map));
        } catch (JSONException e) {
            ev.D("Could not convert parameters to JSON.");
        }
    }

    public void a(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String jSONObject2 = jSONObject.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("javascript:" + str + "(");
        sb.append(jSONObject2);
        sb.append(");");
        E(sb.toString());
    }

    public void b(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String jSONObject2 = jSONObject.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("javascript:AFMA_ReceiveMessage('");
        sb.append(str);
        sb.append("'");
        sb.append(",");
        sb.append(jSONObject2);
        sb.append(");");
        ev.C("Dispatching AFMA event: " + sb);
        E(sb.toString());
    }

    public void bS() {
        if (bW().ce()) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            Display defaultDisplay = this.lA.getDefaultDisplay();
            defaultDisplay.getMetrics(displayMetrics);
            int m = ep.m(getContext());
            float f = 160.0f / ((float) displayMetrics.densityDpi);
            int i = (int) (((float) displayMetrics.widthPixels) * f);
            try {
                b("onScreenInfoChanged", new JSONObject().put("width", i).put("height", (int) (((float) (displayMetrics.heightPixels - m)) * f)).put("density", (double) displayMetrics.density).put("rotation", defaultDisplay.getRotation()));
            } catch (JSONException e) {
                ev.b("Error occured while obtaining screen information.", e);
            }
        }
    }

    public void bT() {
        HashMap hashMap = new HashMap(1);
        hashMap.put("version", this.nE.st);
        a("onhide", (Map<String, ?>) hashMap);
    }

    public void bU() {
        HashMap hashMap = new HashMap(1);
        hashMap.put("version", this.nE.st);
        a("onshow", (Map<String, ?>) hashMap);
    }

    public cg bV() {
        cg cgVar;
        synchronized (this.lq) {
            cgVar = this.sz;
        }
        return cgVar;
    }

    public ez bW() {
        return this.sx;
    }

    public l bX() {
        return this.py;
    }

    public ew bY() {
        return this.nE;
    }

    public boolean bZ() {
        boolean z;
        synchronized (this.lq) {
            z = this.sA;
        }
        return z;
    }

    public Context ca() {
        return this.sy.ca();
    }

    public void destroy() {
        synchronized (this.lq) {
            super.destroy();
            this.sC = true;
        }
    }

    public boolean isDestroyed() {
        boolean z;
        synchronized (this.lq) {
            z = this.sC;
        }
        return z;
    }

    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimeType, long size) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(url), mimeType);
            getContext().startActivity(intent);
        } catch (ActivityNotFoundException e) {
            ev.z("Couldn't find an Activity to view url/mimetype: " + url + " / " + mimeType);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r10, int r11) {
        /*
            r9 = this;
            r0 = 2147483647(0x7fffffff, float:NaN)
            r8 = 1073741824(0x40000000, float:2.0)
            r7 = 8
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            java.lang.Object r4 = r9.lq
            monitor-enter(r4)
            boolean r1 = r9.isInEditMode()     // Catch:{ all -> 0x00ae }
            if (r1 != 0) goto L_0x0016
            boolean r1 = r9.sA     // Catch:{ all -> 0x00ae }
            if (r1 == 0) goto L_0x001b
        L_0x0016:
            super.onMeasure(r10, r11)     // Catch:{ all -> 0x00ae }
            monitor-exit(r4)     // Catch:{ all -> 0x00ae }
        L_0x001a:
            return
        L_0x001b:
            int r2 = android.view.View.MeasureSpec.getMode(r10)     // Catch:{ all -> 0x00ae }
            int r3 = android.view.View.MeasureSpec.getSize(r10)     // Catch:{ all -> 0x00ae }
            int r5 = android.view.View.MeasureSpec.getMode(r11)     // Catch:{ all -> 0x00ae }
            int r1 = android.view.View.MeasureSpec.getSize(r11)     // Catch:{ all -> 0x00ae }
            if (r2 == r6) goto L_0x002f
            if (r2 != r8) goto L_0x00c7
        L_0x002f:
            r2 = r3
        L_0x0030:
            if (r5 == r6) goto L_0x0034
            if (r5 != r8) goto L_0x0035
        L_0x0034:
            r0 = r1
        L_0x0035:
            com.google.android.gms.internal.am r5 = r9.nD     // Catch:{ all -> 0x00ae }
            int r5 = r5.widthPixels     // Catch:{ all -> 0x00ae }
            if (r5 > r2) goto L_0x0041
            com.google.android.gms.internal.am r2 = r9.nD     // Catch:{ all -> 0x00ae }
            int r2 = r2.heightPixels     // Catch:{ all -> 0x00ae }
            if (r2 <= r0) goto L_0x00b1
        L_0x0041:
            com.google.android.gms.internal.ey$a r0 = r9.sy     // Catch:{ all -> 0x00ae }
            android.content.res.Resources r0 = r0.getResources()     // Catch:{ all -> 0x00ae }
            android.util.DisplayMetrics r0 = r0.getDisplayMetrics()     // Catch:{ all -> 0x00ae }
            float r0 = r0.density     // Catch:{ all -> 0x00ae }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ae }
            r2.<init>()     // Catch:{ all -> 0x00ae }
            java.lang.String r5 = "Not enough space to show ad. Needs "
            java.lang.StringBuilder r2 = r2.append(r5)     // Catch:{ all -> 0x00ae }
            com.google.android.gms.internal.am r5 = r9.nD     // Catch:{ all -> 0x00ae }
            int r5 = r5.widthPixels     // Catch:{ all -> 0x00ae }
            float r5 = (float) r5     // Catch:{ all -> 0x00ae }
            float r5 = r5 / r0
            int r5 = (int) r5     // Catch:{ all -> 0x00ae }
            java.lang.StringBuilder r2 = r2.append(r5)     // Catch:{ all -> 0x00ae }
            java.lang.String r5 = "x"
            java.lang.StringBuilder r2 = r2.append(r5)     // Catch:{ all -> 0x00ae }
            com.google.android.gms.internal.am r5 = r9.nD     // Catch:{ all -> 0x00ae }
            int r5 = r5.heightPixels     // Catch:{ all -> 0x00ae }
            float r5 = (float) r5     // Catch:{ all -> 0x00ae }
            float r5 = r5 / r0
            int r5 = (int) r5     // Catch:{ all -> 0x00ae }
            java.lang.StringBuilder r2 = r2.append(r5)     // Catch:{ all -> 0x00ae }
            java.lang.String r5 = " dp, but only has "
            java.lang.StringBuilder r2 = r2.append(r5)     // Catch:{ all -> 0x00ae }
            float r3 = (float) r3     // Catch:{ all -> 0x00ae }
            float r3 = r3 / r0
            int r3 = (int) r3     // Catch:{ all -> 0x00ae }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x00ae }
            java.lang.String r3 = "x"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x00ae }
            float r1 = (float) r1     // Catch:{ all -> 0x00ae }
            float r0 = r1 / r0
            int r0 = (int) r0     // Catch:{ all -> 0x00ae }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ all -> 0x00ae }
            java.lang.String r1 = " dp."
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x00ae }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00ae }
            com.google.android.gms.internal.ev.D(r0)     // Catch:{ all -> 0x00ae }
            int r0 = r9.getVisibility()     // Catch:{ all -> 0x00ae }
            if (r0 == r7) goto L_0x00a6
            r0 = 4
            r9.setVisibility(r0)     // Catch:{ all -> 0x00ae }
        L_0x00a6:
            r0 = 0
            r1 = 0
            r9.setMeasuredDimension(r0, r1)     // Catch:{ all -> 0x00ae }
        L_0x00ab:
            monitor-exit(r4)     // Catch:{ all -> 0x00ae }
            goto L_0x001a
        L_0x00ae:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x00ae }
            throw r0
        L_0x00b1:
            int r0 = r9.getVisibility()     // Catch:{ all -> 0x00ae }
            if (r0 == r7) goto L_0x00bb
            r0 = 0
            r9.setVisibility(r0)     // Catch:{ all -> 0x00ae }
        L_0x00bb:
            com.google.android.gms.internal.am r0 = r9.nD     // Catch:{ all -> 0x00ae }
            int r0 = r0.widthPixels     // Catch:{ all -> 0x00ae }
            com.google.android.gms.internal.am r1 = r9.nD     // Catch:{ all -> 0x00ae }
            int r1 = r1.heightPixels     // Catch:{ all -> 0x00ae }
            r9.setMeasuredDimension(r0, r1)     // Catch:{ all -> 0x00ae }
            goto L_0x00ab
        L_0x00c7:
            r2 = r0
            goto L_0x0030
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ey.onMeasure(int, int):void");
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (this.py != null) {
            this.py.a(event);
        }
        return super.onTouchEvent(event);
    }

    public void q(boolean z) {
        synchronized (this.lq) {
            this.sA = z;
            cb();
        }
    }

    public void setContext(Context context) {
        this.sy.setBaseContext(context);
    }
}
