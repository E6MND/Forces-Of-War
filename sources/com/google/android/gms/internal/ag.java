package com.google.android.gms.internal;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.gms.internal.af;
import com.google.android.gms.internal.ez;
import org.json.JSONObject;

public class ag implements af {
    private final ey lL;

    public ag(Context context, ew ewVar) {
        this.lL = ey.a(context, new am(), false, false, (l) null, ewVar);
    }

    public void a(final af.a aVar) {
        this.lL.bW().a((ez.a) new ez.a() {
            public void a(ey eyVar) {
                aVar.az();
            }
        });
    }

    public void a(String str, bd bdVar) {
        this.lL.bW().a(str, bdVar);
    }

    public void a(String str, JSONObject jSONObject) {
        this.lL.a(str, jSONObject);
    }

    public void d(String str) {
        this.lL.loadUrl(str);
    }

    public void e(String str) {
        this.lL.bW().a(str, (bd) null);
    }

    public void pause() {
        ep.a((WebView) this.lL);
    }

    public void resume() {
        ep.b((WebView) this.lL);
    }
}
