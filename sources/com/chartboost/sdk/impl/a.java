package com.chartboost.sdk.impl;

import android.content.Intent;
import android.net.Uri;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.b;
import java.util.Date;
import org.json.JSONObject;
import org.xwalk.core.internal.extension.api.messaging.MessagingSmsConsts;

public class a {
    public JSONObject a;
    public Date b;
    public b c;
    public c d;
    public String e;
    public com.chartboost.sdk.b f;
    public C0007a g;
    public s h;
    public boolean i;
    public boolean j;
    public boolean k;

    /* renamed from: com.chartboost.sdk.impl.a$a  reason: collision with other inner class name */
    public interface C0007a {
        void a(a aVar);

        void a(a aVar, String str, JSONObject jSONObject);

        void b(a aVar);

        void c(a aVar);
    }

    public enum b {
        CBImpressionStateOther,
        CBImpressionStateWaitingForDisplay,
        CBImpressionStateDisplayedByDefaultController,
        CBImpressionStateWaitingForDismissal,
        CBImpressionStateWaitingForCaching,
        CBImpressionStateCached
    }

    public enum c {
        CBImpressionTypeOther,
        CBImpressionTypeInterstitial,
        CBImpressionTypeMoreApps
    }

    public a(JSONObject jSONObject, c cVar, C0007a aVar, b bVar, String str) {
        jSONObject = jSONObject == null ? new JSONObject() : jSONObject;
        this.c = bVar;
        this.e = str;
        this.a = jSONObject;
        this.b = new Date();
        this.g = aVar;
        this.d = cVar;
        this.i = false;
        this.j = false;
        this.k = false;
        boolean equals = jSONObject.optString(MessagingSmsConsts.TYPE, "").equals("native");
        if (equals && this.d == c.CBImpressionTypeInterstitial) {
            this.f = new b(this);
        } else if (!equals || this.d != c.CBImpressionTypeMoreApps) {
            this.f = new v(this);
        } else {
            this.f = new h(this);
        }
        this.f.c = new b.a() {
            public void a() {
                if (this.g != null) {
                    this.g.a(this);
                }
            }
        };
        this.f.a = new b.a() {
            public void a() {
                if (this.g != null) {
                    this.g.b(this);
                }
            }
        };
        this.f.b = new b.c() {
            public void a(String str, JSONObject jSONObject) {
                if (str == null) {
                    str = this.a.optString("link");
                }
                String optString = this.a.optString("deep-link");
                if (optString != null && !optString.equals("")) {
                    try {
                        if (Chartboost.sharedChartboost().getContext().getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse(optString)), 65536).size() > 0) {
                            str = optString;
                        }
                    } catch (Exception e) {
                    }
                }
                this.g.a(this, str, jSONObject);
            }
        };
        this.f.d = new b.a() {
            public void a() {
                this.g.c(this);
            }
        };
        this.f.a(jSONObject);
    }

    public boolean a() {
        this.i = true;
        this.j = true;
        this.k = true;
        this.f.a();
        if (this.f.d() != null) {
            return true;
        }
        this.i = false;
        this.j = false;
        this.k = false;
        return false;
    }

    public void b() {
        if (this.h != null) {
            this.h.a();
            try {
                if (this.f.d().getParent() != null) {
                    this.h.removeView(this.f.d());
                }
            } catch (Exception e2) {
            }
        }
        if (this.f != null) {
            this.f.c();
        }
        this.a = null;
        this.b = null;
        this.g = null;
        this.f = null;
        this.h = null;
    }

    public void c() {
        if (this.h != null) {
            this.h.a();
            try {
                if (this.f.d().getParent() != null) {
                    this.h.removeView(this.f.d());
                }
            } catch (Exception e2) {
            }
        }
        if (this.f != null) {
            this.f.e();
        }
    }
}
