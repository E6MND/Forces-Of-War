package com.chartboost.sdk.impl;

import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import com.chartboost.sdk.Libraries.d;
import com.chartboost.sdk.impl.h;
import org.json.JSONObject;

public class i extends c implements h.b {
    private WebView b;

    public i(Context context) {
        super(context);
        this.b = new WebView(context);
        addView(this.b, new LinearLayout.LayoutParams(-1, -1));
        this.b.setBackgroundColor(0);
        this.b.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url == null) {
                    return false;
                }
                if (url.contains("chartboost") && url.contains("click") && i.this.a != null) {
                    i.this.a.onClick(i.this);
                }
                return true;
            }
        });
    }

    public void a(JSONObject jSONObject, int i) {
        String optString = jSONObject.optString("html");
        if (optString != null) {
            try {
                this.b.loadDataWithBaseURL("file:///android_res/", optString, "text/html", "UTF-8", (String) null);
            } catch (Exception e) {
            }
        }
    }

    public int a() {
        return d.a(100, getContext());
    }
}
