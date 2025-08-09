package com.google.android.gms.internal;

import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.net.URI;
import java.net.URISyntaxException;

public class fd extends WebViewClient {
    private final ey lL;
    private final String sQ;
    private boolean sR = false;
    private final dp sS;

    public fd(dp dpVar, ey eyVar, String str) {
        this.sQ = G(str);
        this.lL = eyVar;
        this.sS = dpVar;
    }

    private String G(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            return str.endsWith("/") ? str.substring(0, str.length() - 1) : str;
        } catch (IndexOutOfBoundsException e) {
            ev.A(e.getMessage());
            return str;
        }
    }

    /* access modifiers changed from: protected */
    public boolean F(String str) {
        String G = G(str);
        if (TextUtils.isEmpty(G)) {
            return false;
        }
        try {
            URI uri = new URI(G);
            if ("passback".equals(uri.getScheme())) {
                ev.z("Passback received");
                this.sS.bk();
                return true;
            } else if (TextUtils.isEmpty(this.sQ)) {
                return false;
            } else {
                URI uri2 = new URI(this.sQ);
                String host = uri2.getHost();
                String host2 = uri.getHost();
                String path = uri2.getPath();
                String path2 = uri.getPath();
                if (!hl.equal(host, host2) || !hl.equal(path, path2)) {
                    return false;
                }
                ev.z("Passback received");
                this.sS.bk();
                return true;
            }
        } catch (URISyntaxException e) {
            ev.A(e.getMessage());
            return false;
        }
    }

    public void onLoadResource(WebView view, String url) {
        ev.z("JavascriptAdWebViewClient::onLoadResource: " + url);
        if (!F(url)) {
            this.lL.bW().onLoadResource(this.lL, url);
        }
    }

    public void onPageFinished(WebView view, String url) {
        ev.z("JavascriptAdWebViewClient::onPageFinished: " + url);
        if (!this.sR) {
            this.sS.bj();
            this.sR = true;
        }
    }

    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        ev.z("JavascriptAdWebViewClient::shouldOverrideUrlLoading: " + url);
        if (!F(url)) {
            return this.lL.bW().shouldOverrideUrlLoading(this.lL, url);
        }
        ev.z("shouldOverrideUrlLoading: received passback url");
        return true;
    }
}
