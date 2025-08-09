package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.a;
import com.chartboost.sdk.Libraries.d;
import com.chartboost.sdk.Libraries.e;
import com.chartboost.sdk.b;
import com.sponsorpay.sdk.android.UrlBuilder;
import org.json.JSONObject;

public class b extends com.chartboost.sdk.b {
    public a.C0003a h = null;
    public a.C0003a i = null;
    public a.C0003a j = null;
    public a.C0003a k = null;
    public a.C0003a l = null;

    public class a extends b.C0006b {
        public ImageView c;
        public ImageView d;
        public Button e;
        public t f;

        private a(Context context) {
            super(context);
            setBackgroundColor(0);
            setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            this.f = new t(context);
            this.d = new ImageView(context);
            this.e = new Button(context);
            this.c = new ImageView(context);
            this.e.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (b.this.a != null) {
                        b.this.a.a();
                    }
                }
            });
            this.d.setClickable(true);
            this.d.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (b.this.b != null) {
                        b.this.b.a((String) null, (JSONObject) null);
                    }
                }
            });
            this.f.a(this.c);
            this.f.a(this.d);
            this.f.a(this.e);
            addView(this.f, new RelativeLayout.LayoutParams(-1, -1));
        }

        /* synthetic */ a(b bVar, Context context, a aVar) {
            this(context);
        }

        /* access modifiers changed from: protected */
        public void a(int i, int i2) {
            boolean isPortrait = Chartboost.sharedChartboost().orientation().isPortrait();
            a.C0003a aVar = isPortrait ? b.this.h : b.this.i;
            a.C0003a aVar2 = isPortrait ? b.this.j : b.this.k;
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
            float max = isPortrait ? Math.max(320.0f / ((float) i), 480.0f / ((float) i2)) : Math.max(320.0f / ((float) i2), 480.0f / ((float) i));
            layoutParams.width = (int) (((float) aVar2.c()) / max);
            layoutParams.height = (int) (((float) aVar2.d()) / max);
            Point a = a(isPortrait ? "frame-portrait" : "frame-landscape");
            layoutParams.leftMargin = Math.round((((float) (i - layoutParams.width)) / 2.0f) + (((float) a.x) / max));
            layoutParams.topMargin = Math.round((((float) a.y) / max) + (((float) (i2 - layoutParams.height)) / 2.0f));
            this.d.setId(100);
            layoutParams2.width = (int) (((float) aVar.c()) / max);
            layoutParams2.height = (int) (((float) aVar.d()) / max);
            Point a2 = a(isPortrait ? "ad-portrait" : "ad-landscape");
            layoutParams2.leftMargin = Math.round((((float) (i - layoutParams2.width)) / 2.0f) + (((float) a2.x) / max));
            layoutParams2.topMargin = Math.round((((float) a2.y) / max) + (((float) (i2 - layoutParams2.height)) / 2.0f));
            layoutParams3.width = (int) (((float) b.this.l.c()) / max);
            layoutParams3.height = (int) (((float) b.this.l.d()) / max);
            Point a3 = a("close");
            layoutParams3.leftMargin = layoutParams2.leftMargin + layoutParams2.width + Math.round((((float) a3.x) / max) - d.b(10, getContext()));
            layoutParams3.topMargin = Math.round((((float) a3.y) / max) - d.b(10, getContext())) + (layoutParams2.topMargin - layoutParams3.height);
            this.c.setLayoutParams(layoutParams);
            this.d.setLayoutParams(layoutParams2);
            this.e.setLayoutParams(layoutParams3);
            BitmapDrawable bitmapDrawable = new BitmapDrawable(aVar2.b());
            this.c.setScaleType(ImageView.ScaleType.FIT_CENTER);
            this.c.setImageDrawable(bitmapDrawable);
            BitmapDrawable bitmapDrawable2 = new BitmapDrawable(aVar.b());
            this.d.setScaleType(ImageView.ScaleType.FIT_CENTER);
            this.d.setImageDrawable(bitmapDrawable2);
            this.e.setBackgroundDrawable(new BitmapDrawable(b.this.l.b()));
        }

        public void b() {
            super.b();
            this.d = null;
            this.c = null;
            this.e = null;
        }

        private Point a(String str) {
            JSONObject optJSONObject;
            JSONObject optJSONObject2 = b.this.f.optJSONObject(str);
            if (optJSONObject2 == null || (optJSONObject = optJSONObject2.optJSONObject(UrlBuilder.URL_PARAM_OFFSET_KEY)) == null) {
                return new Point(0, 0);
            }
            return new Point(optJSONObject.optInt("x", 0), optJSONObject.optInt("y", 0));
        }
    }

    public b(a aVar) {
        super(aVar);
        this.e = 5;
    }

    /* access modifiers changed from: protected */
    public b.C0006b a(Context context) {
        return new a(this, context, (a) null);
    }

    public void a(JSONObject jSONObject) {
        super.a(jSONObject);
        AnonymousClass1 r0 = new e.b() {
            public void a(a.C0003a aVar, Bundle bundle) {
                b.this.i = aVar;
                b.this.a(aVar);
            }
        };
        AnonymousClass2 r1 = new e.b() {
            public void a(a.C0003a aVar, Bundle bundle) {
                b.this.h = aVar;
                b.this.a(aVar);
            }
        };
        AnonymousClass3 r2 = new e.b() {
            public void a(a.C0003a aVar, Bundle bundle) {
                b.this.k = aVar;
                b.this.a(aVar);
            }
        };
        AnonymousClass4 r3 = new e.b() {
            public void a(a.C0003a aVar, Bundle bundle) {
                b.this.j = aVar;
                b.this.a(aVar);
            }
        };
        AnonymousClass5 r4 = new e.b() {
            public void a(a.C0003a aVar, Bundle bundle) {
                b.this.l = aVar;
                b.this.a(aVar);
            }
        };
        a("ad-landscape", r0, true);
        a("ad-portrait", r1, true);
        a("frame-landscape", r2);
        a("frame-portrait", r3);
        a("close", r4);
    }

    public void c() {
        super.c();
        if (this.i != null) {
            this.i.a();
            this.i = null;
        }
        if (this.h != null) {
            this.h.a();
            this.h = null;
        }
        this.k = null;
        this.j = null;
        this.l = null;
    }
}
