package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.chartboost.sdk.Libraries.d;
import com.chartboost.sdk.Libraries.e;
import com.chartboost.sdk.impl.h;
import org.json.JSONObject;

public class e extends c implements h.b {
    private static int c = 50;
    private static int d = 10;
    public f b;
    private g e;
    private TextView f;

    public e(Context context) {
        super(context);
        this.e = new g(context);
        this.b = new f(context);
        this.f = new TextView(context);
        this.f.setTypeface((Typeface) null, 1);
        this.f.setTextSize(2, 16.0f);
        this.f.setShadowLayer(1.0f, 1.0f, 1.0f, -1);
        this.f.setBackgroundColor(0);
        this.f.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.f.setEllipsize(TextUtils.TruncateAt.END);
        setBackgroundColor(-3355444);
        setFocusable(false);
        addView(this.e);
        addView(this.f);
        addView(this.b);
    }

    public void a(JSONObject jSONObject, int i) {
        JSONObject optJSONObject;
        this.f.setText(jSONObject.optString("name", "Unknown App"));
        String optString = jSONObject.optString("deep-text");
        if (optString == null || optString.equals("")) {
            this.b.setText(jSONObject.optString("text", "VIEW"));
        } else {
            this.b.setText(optString);
        }
        JSONObject optJSONObject2 = jSONObject.optJSONObject("assets");
        if (!(optJSONObject2 == null || (optJSONObject = optJSONObject2.optJSONObject("icon")) == null)) {
            Bundle bundle = new Bundle();
            bundle.putInt("index", i);
            com.chartboost.sdk.Libraries.e.a().a(optJSONObject.optString("url"), optJSONObject.optString("checksum"), (e.b) null, this.e, bundle);
        }
        b();
    }

    /* access modifiers changed from: protected */
    public void b() {
        int a = d.a(c, getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(a, a);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -1);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        int a2 = d.a(d, getContext());
        layoutParams.setMargins(a2, a2, a2, a2);
        layoutParams2.setMargins(a2, a2, a2, a2);
        layoutParams3.setMargins(a2, a2, a2, a2);
        layoutParams2.weight = 1.0f;
        this.f.setGravity(16);
        layoutParams3.gravity = 16;
        this.e.setLayoutParams(layoutParams);
        this.e.setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.f.setLayoutParams(layoutParams2);
        this.b.setLayoutParams(layoutParams3);
    }

    public int a() {
        return d.a(c + (d * 2), getContext());
    }
}
