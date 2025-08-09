package com.chartboost.sdk;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.chartboost.sdk.Libraries.a;
import com.chartboost.sdk.Libraries.e;
import com.chartboost.sdk.impl.a;
import com.chartboost.sdk.impl.s;
import org.json.JSONObject;

public abstract class b {
    public a a = null;
    public c b = null;
    public a c = null;
    public a d = null;
    protected int e = 0;
    protected JSONObject f;
    protected com.chartboost.sdk.impl.a g;
    private int h;
    private int i;
    private int j;
    private C0006b k;

    public interface a {
        void a();
    }

    public interface c {
        void a(String str, JSONObject jSONObject);
    }

    /* access modifiers changed from: protected */
    public abstract C0006b a(Context context);

    /* renamed from: com.chartboost.sdk.b$b  reason: collision with other inner class name */
    public abstract class C0006b extends RelativeLayout implements s.a {
        protected boolean a = false;

        /* access modifiers changed from: protected */
        public abstract void a(int i, int i2);

        public C0006b(Context context) {
            super(context);
            setFocusableInTouchMode(true);
            requestFocus();
            setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
        }

        /* access modifiers changed from: protected */
        public void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            if (!this.a) {
                if (Chartboost.sharedChartboost().getForcedOrientationDifference().isOdd()) {
                    b(h, w);
                } else {
                    b(w, h);
                }
            }
        }

        private boolean b(int i, int i2) {
            try {
                a(i, i2);
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        public void a() {
            a((Activity) getContext());
        }

        public void b() {
        }

        public boolean a(Activity activity) {
            int i;
            int i2;
            try {
                i2 = getWidth();
                i = getHeight();
                if (i2 == 0 || i == 0) {
                    View findViewById = activity.getWindow().findViewById(16908290);
                    if (findViewById == null) {
                        findViewById = activity.getWindow().getDecorView();
                    }
                    i2 = findViewById.getWidth();
                    i = findViewById.getHeight();
                }
            } catch (Exception e) {
                i = 0;
                i2 = 0;
            }
            if (i2 == 0 || i == 0) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                i2 = displayMetrics.widthPixels;
                i = displayMetrics.heightPixels;
            }
            if (!Chartboost.sharedChartboost().getForcedOrientationDifference().isOdd()) {
                int i3 = i;
                i = i2;
                i2 = i3;
            }
            return b(i, i2);
        }
    }

    public b(com.chartboost.sdk.impl.a aVar) {
        this.g = aVar;
        this.k = null;
    }

    public void a(JSONObject jSONObject) {
        this.i = 0;
        this.j = 0;
        this.h = 0;
        this.f = jSONObject.optJSONObject("assets");
        if (this.f == null && this.d != null) {
            this.d.a();
        }
    }

    /* access modifiers changed from: protected */
    public void a(String str, e.b bVar) {
        a(str, bVar, false);
    }

    /* access modifiers changed from: protected */
    public void a(String str, e.b bVar, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("paramNoMemoryCache", z);
        a(this.f, str, bVar, bundle);
    }

    /* access modifiers changed from: protected */
    public void a(JSONObject jSONObject, String str, e.b bVar, Bundle bundle) {
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        if (optJSONObject != null) {
            this.j++;
            e.a().a(optJSONObject.optString("url"), optJSONObject.optString("checksum"), bVar, (ImageView) null, bundle);
            return;
        }
        a((a.C0003a) null);
    }

    /* access modifiers changed from: protected */
    public void a(a.C0003a aVar) {
        if (aVar != null) {
            this.h++;
        }
        this.i++;
        if (this.i == this.e && !a() && this.d != null) {
            this.d.a();
        }
    }

    public boolean a() {
        if (this.h != this.j) {
            return false;
        }
        if (this.c != null) {
            this.c.a();
        }
        return true;
    }

    public boolean b() {
        if (this.g.c != a.b.CBImpressionStateWaitingForDisplay) {
            return false;
        }
        Chartboost.sharedChartboost().a(this.g);
        Activity b2 = Chartboost.sharedChartboost().b();
        if (b2 == null) {
            this.k = null;
            return false;
        }
        this.k = a((Context) b2);
        if (this.k.a(b2)) {
            return true;
        }
        this.k = null;
        return false;
    }

    public void c() {
        e();
        this.c = null;
        this.d = null;
        this.b = null;
        this.a = null;
        this.f = null;
    }

    public C0006b d() {
        return this.k;
    }

    public void e() {
        if (this.k != null) {
            this.k.b();
        }
        this.k = null;
    }
}
