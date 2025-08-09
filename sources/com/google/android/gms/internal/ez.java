package com.google.android.gms.internal;

import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.HashMap;
import java.util.Map;

public class ez extends WebViewClient {
    protected final ey lL;
    private final Object lq = new Object();
    private bb mQ;
    private bg na;
    private be nb;
    private a pL;
    private final HashMap<String, bd> sE = new HashMap<>();
    private u sF;
    private cj sG;
    private boolean sH = false;
    private boolean sI;
    private cm sJ;

    public interface a {
        void a(ey eyVar);
    }

    public ez(ey eyVar, boolean z) {
        this.lL = eyVar;
        this.sI = z;
    }

    private static boolean c(Uri uri) {
        String scheme = uri.getScheme();
        return "http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme);
    }

    private void d(Uri uri) {
        String path = uri.getPath();
        bd bdVar = this.sE.get(path);
        if (bdVar != null) {
            Map<String, String> b = ep.b(uri);
            if (ev.p(2)) {
                ev.C("Received GMSG: " + path);
                for (String next : b.keySet()) {
                    ev.C("  " + next + ": " + b.get(next));
                }
            }
            bdVar.b(this.lL, b);
            return;
        }
        ev.C("No GMSG handler found for GMSG: " + uri);
    }

    public final void a(cf cfVar) {
        cj cjVar = null;
        boolean bZ = this.lL.bZ();
        u uVar = (!bZ || this.lL.Q().md) ? this.sF : null;
        if (!bZ) {
            cjVar = this.sG;
        }
        a(new ci(cfVar, uVar, cjVar, this.sJ, this.lL.bY()));
    }

    /* access modifiers changed from: protected */
    public void a(ci ciVar) {
        cg.a(this.lL.getContext(), ciVar);
    }

    public final void a(a aVar) {
        this.pL = aVar;
    }

    public void a(u uVar, cj cjVar, bb bbVar, cm cmVar, boolean z, be beVar) {
        a("/appEvent", (bd) new ba(bbVar));
        a("/canOpenURLs", bc.mS);
        a("/click", bc.mT);
        a("/close", bc.mU);
        a("/customClose", bc.mV);
        a("/httpTrack", bc.mW);
        a("/log", bc.mX);
        a("/open", (bd) new bh(beVar));
        a("/touch", bc.mY);
        a("/video", bc.mZ);
        this.sF = uVar;
        this.sG = cjVar;
        this.mQ = bbVar;
        this.nb = beVar;
        this.sJ = cmVar;
        r(z);
    }

    public void a(u uVar, cj cjVar, bb bbVar, cm cmVar, boolean z, be beVar, bg bgVar) {
        a(uVar, cjVar, bbVar, cmVar, z, beVar);
        a("/setInterstitialProperties", (bd) new bf(bgVar));
        this.na = bgVar;
    }

    public final void a(String str, bd bdVar) {
        this.sE.put(str, bdVar);
    }

    public final void a(boolean z, int i) {
        a(new ci((!this.lL.bZ() || this.lL.Q().md) ? this.sF : null, this.sG, this.sJ, this.lL, z, i, this.lL.bY()));
    }

    public final void a(boolean z, int i, String str) {
        cj cjVar = null;
        boolean bZ = this.lL.bZ();
        u uVar = (!bZ || this.lL.Q().md) ? this.sF : null;
        if (!bZ) {
            cjVar = this.sG;
        }
        a(new ci(uVar, cjVar, this.mQ, this.sJ, this.lL, z, i, str, this.lL.bY(), this.nb));
    }

    public final void a(boolean z, int i, String str, String str2) {
        boolean bZ = this.lL.bZ();
        a(new ci((!bZ || this.lL.Q().md) ? this.sF : null, bZ ? null : this.sG, this.mQ, this.sJ, this.lL, z, i, str, str2, this.lL.bY(), this.nb));
    }

    public final void aN() {
        synchronized (this.lq) {
            this.sH = false;
            this.sI = true;
            final cg bV = this.lL.bV();
            if (bV != null) {
                if (!eu.bR()) {
                    eu.ss.post(new Runnable() {
                        public void run() {
                            bV.aN();
                        }
                    });
                } else {
                    bV.aN();
                }
            }
        }
    }

    public boolean ce() {
        boolean z;
        synchronized (this.lq) {
            z = this.sI;
        }
        return z;
    }

    public final void onLoadResource(WebView webView, String url) {
        ev.C("Loading resource: " + url);
        Uri parse = Uri.parse(url);
        if ("gmsg".equalsIgnoreCase(parse.getScheme()) && "mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            d(parse);
        }
    }

    public final void onPageFinished(WebView webView, String url) {
        if (this.pL != null) {
            this.pL.a(this.lL);
            this.pL = null;
        }
    }

    public final void r(boolean z) {
        this.sH = z;
    }

    public final void reset() {
        synchronized (this.lq) {
            this.sE.clear();
            this.sF = null;
            this.sG = null;
            this.pL = null;
            this.mQ = null;
            this.sH = false;
            this.sI = false;
            this.nb = null;
            this.sJ = null;
        }
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String url) {
        Uri uri;
        ev.C("AdWebView shouldOverrideUrlLoading: " + url);
        Uri parse = Uri.parse(url);
        if ("gmsg".equalsIgnoreCase(parse.getScheme()) && "mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            d(parse);
        } else if (this.sH && webView == this.lL && c(parse)) {
            return super.shouldOverrideUrlLoading(webView, url);
        } else {
            if (!this.lL.willNotDraw()) {
                try {
                    l bX = this.lL.bX();
                    if (bX != null && bX.a(parse)) {
                        parse = bX.a(parse, this.lL.getContext());
                    }
                    uri = parse;
                } catch (m e) {
                    ev.D("Unable to append parameter to URL: " + url);
                    uri = parse;
                }
                a(new cf("android.intent.action.VIEW", uri.toString(), (String) null, (String) null, (String) null, (String) null, (String) null));
            } else {
                ev.D("AdWebView unable to handle URL: " + url);
            }
        }
        return true;
    }
}
