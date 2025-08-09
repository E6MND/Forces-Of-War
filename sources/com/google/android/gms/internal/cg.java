package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.google.android.gms.ads.AdActivity;
import com.google.android.gms.internal.co;
import com.google.android.gms.internal.ez;

public class cg extends co.a {
    private static final int od = Color.argb(0, 0, 0, 0);
    private ey lL;
    private final Activity oe;
    private ci of;
    private ck og;
    private c oh;
    private cl oi;
    private boolean oj;
    private FrameLayout ok;
    private WebChromeClient.CustomViewCallback ol;
    private boolean om = false;
    private boolean on = false;
    private boolean oo = false;
    private RelativeLayout op;

    private static final class a extends Exception {
        public a(String str) {
            super(str);
        }
    }

    private static final class b extends RelativeLayout {
        private final eq kG;

        public b(Context context, String str) {
            super(context);
            this.kG = new eq(context, str);
        }

        public boolean onInterceptTouchEvent(MotionEvent event) {
            this.kG.c(event);
            return false;
        }
    }

    private static final class c {
        public final int index;
        public final ViewGroup.LayoutParams or;
        public final ViewGroup os;

        public c(ey eyVar) throws a {
            this.or = eyVar.getLayoutParams();
            ViewParent parent = eyVar.getParent();
            if (parent instanceof ViewGroup) {
                this.os = (ViewGroup) parent;
                this.index = this.os.indexOfChild(eyVar);
                this.os.removeView(eyVar);
                eyVar.q(true);
                return;
            }
            throw new a("Could not get the parent of the WebView for an overlay.");
        }
    }

    public cg(Activity activity) {
        this.oe = activity;
    }

    private static RelativeLayout.LayoutParams a(int i, int i2, int i3, int i4) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i3, i4);
        layoutParams.setMargins(i, i2, 0, 0);
        layoutParams.addRule(10);
        layoutParams.addRule(9);
        return layoutParams;
    }

    public static void a(Context context, ci ciVar) {
        Intent intent = new Intent();
        intent.setClassName(context, AdActivity.CLASS_NAME);
        intent.putExtra("com.google.android.gms.ads.internal.overlay.useClientJar", ciVar.kO.sw);
        ci.a(intent, ciVar);
        intent.addFlags(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_END);
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        context.startActivity(intent);
    }

    private void aO() {
        if (this.oe.isFinishing() && !this.on) {
            this.on = true;
            if (this.oe.isFinishing()) {
                if (this.lL != null) {
                    this.lL.bT();
                    this.op.removeView(this.lL);
                    if (this.oh != null) {
                        this.lL.q(false);
                        this.oh.os.addView(this.lL, this.oh.index, this.oh.or);
                    }
                }
                if (this.of != null && this.of.ov != null) {
                    this.of.ov.U();
                }
            }
        }
    }

    private void k(boolean z) throws a {
        if (!this.oj) {
            this.oe.requestWindowFeature(1);
        }
        Window window = this.oe.getWindow();
        if (!this.oo || this.of.oF.lb) {
            window.setFlags(1024, 1024);
        }
        setRequestedOrientation(this.of.orientation);
        if (Build.VERSION.SDK_INT >= 11) {
            ev.z("Enabling hardware acceleration on the AdActivity window.");
            er.a(window);
        }
        this.op = new b(this.oe, this.of.oE);
        if (!this.oo) {
            this.op.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        } else {
            this.op.setBackgroundColor(od);
        }
        this.oe.setContentView(this.op);
        N();
        boolean ce = this.of.ow.bW().ce();
        if (z) {
            this.lL = ey.a(this.oe, this.of.ow.Q(), true, ce, (l) null, this.of.kO);
            this.lL.bW().a((u) null, (cj) null, this.of.ox, this.of.oB, true, this.of.oD);
            this.lL.bW().a((ez.a) new ez.a() {
                public void a(ey eyVar) {
                    eyVar.bU();
                }
            });
            if (this.of.nZ != null) {
                this.lL.loadUrl(this.of.nZ);
            } else if (this.of.oA != null) {
                this.lL.loadDataWithBaseURL(this.of.oy, this.of.oA, "text/html", "UTF-8", (String) null);
            } else {
                throw new a("No URL or HTML to display in ad overlay.");
            }
        } else {
            this.lL = this.of.ow;
            this.lL.setContext(this.oe);
        }
        this.lL.a(this);
        ViewParent parent = this.lL.getParent();
        if (parent != null && (parent instanceof ViewGroup)) {
            ((ViewGroup) parent).removeView(this.lL);
        }
        if (this.oo) {
            this.lL.setBackgroundColor(od);
        }
        this.op.addView(this.lL, -1, -1);
        if (!z) {
            this.lL.bU();
        }
        i(ce);
    }

    public void N() {
        this.oj = true;
    }

    public void a(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        this.ok = new FrameLayout(this.oe);
        this.ok.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.ok.addView(view, -1, -1);
        this.oe.setContentView(this.ok);
        N();
        this.ol = customViewCallback;
    }

    public ck aL() {
        return this.og;
    }

    public void aM() {
        if (this.of != null) {
            setRequestedOrientation(this.of.orientation);
        }
        if (this.ok != null) {
            this.oe.setContentView(this.op);
            N();
            this.ok.removeAllViews();
            this.ok = null;
        }
        if (this.ol != null) {
            this.ol.onCustomViewHidden();
            this.ol = null;
        }
    }

    public void aN() {
        this.op.removeView(this.oi);
        i(true);
    }

    public void b(int i, int i2, int i3, int i4) {
        if (this.og != null) {
            this.og.setLayoutParams(a(i, i2, i3, i4));
        }
    }

    public void c(int i, int i2, int i3, int i4) {
        if (this.og == null) {
            this.og = new ck(this.oe, this.lL);
            this.op.addView(this.og, 0, a(i, i2, i3, i4));
            this.lL.bW().r(false);
        }
    }

    public void close() {
        this.oe.finish();
    }

    public void i(boolean z) {
        this.oi = new cl(this.oe, z ? 50 : 32);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(10);
        layoutParams.addRule(z ? 11 : 9);
        this.oi.j(this.of.oz);
        this.op.addView(this.oi, layoutParams);
    }

    public void j(boolean z) {
        if (this.oi != null) {
            this.oi.j(z);
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        boolean z = false;
        if (savedInstanceState != null) {
            z = savedInstanceState.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false);
        }
        this.om = z;
        try {
            this.of = ci.a(this.oe.getIntent());
            if (this.of.oF != null) {
                this.oo = this.of.oF.kZ;
            } else {
                this.oo = false;
            }
            if (this.of == null) {
                throw new a("Could not get info for ad overlay.");
            }
            if (savedInstanceState == null) {
                if (this.of.ov != null) {
                    this.of.ov.V();
                }
                if (!(this.of.oC == 1 || this.of.ou == null)) {
                    this.of.ou.onAdClicked();
                }
            }
            switch (this.of.oC) {
                case 1:
                    k(false);
                    return;
                case 2:
                    this.oh = new c(this.of.ow);
                    k(false);
                    return;
                case 3:
                    k(true);
                    return;
                case 4:
                    if (this.om) {
                        this.oe.finish();
                        return;
                    } else if (!cd.a(this.oe, this.of.ot, this.of.oB)) {
                        this.oe.finish();
                        return;
                    } else {
                        return;
                    }
                default:
                    throw new a("Could not determine ad overlay type.");
            }
        } catch (a e) {
            ev.D(e.getMessage());
            this.oe.finish();
        }
    }

    public void onDestroy() {
        if (this.og != null) {
            this.og.destroy();
        }
        if (this.lL != null) {
            this.op.removeView(this.lL);
        }
        aO();
    }

    public void onPause() {
        if (this.og != null) {
            this.og.pause();
        }
        aM();
        if (this.lL != null && (!this.oe.isFinishing() || this.oh == null)) {
            ep.a((WebView) this.lL);
        }
        aO();
    }

    public void onRestart() {
    }

    public void onResume() {
        if (this.of != null && this.of.oC == 4) {
            if (this.om) {
                this.oe.finish();
            } else {
                this.om = true;
            }
        }
        if (this.lL != null) {
            ep.b((WebView) this.lL);
        }
    }

    public void onSaveInstanceState(Bundle outBundle) {
        outBundle.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.om);
    }

    public void onStart() {
    }

    public void onStop() {
        aO();
    }

    public void setRequestedOrientation(int requestedOrientation) {
        this.oe.setRequestedOrientation(requestedOrientation);
    }
}
