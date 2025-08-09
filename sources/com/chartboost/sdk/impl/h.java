package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.CBOrientation;
import com.chartboost.sdk.Libraries.a;
import com.chartboost.sdk.Libraries.d;
import com.chartboost.sdk.Libraries.e;
import com.chartboost.sdk.b;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xwalk.core.internal.extension.api.messaging.MessagingSmsConsts;

public class h extends com.chartboost.sdk.b {
    /* access modifiers changed from: private */
    public static int h = 50;
    /* access modifiers changed from: private */
    public static int i = 50;
    /* access modifiers changed from: private */
    public static int j = 30;
    /* access modifiers changed from: private */
    public List<JSONObject> k = new ArrayList();
    /* access modifiers changed from: private */
    public a.C0003a l;
    /* access modifiers changed from: private */
    public a.C0003a m;
    /* access modifiers changed from: private */
    public a.C0003a n;
    /* access modifiers changed from: private */
    public SparseArray<a.C0003a> o;

    public interface b {
        int a();

        void a(JSONObject jSONObject, int i);
    }

    public class a extends b.C0006b {
        private static /* synthetic */ int[] k;
        private ImageView d;
        private ImageView e;
        private FrameLayout f;
        /* access modifiers changed from: private */
        public q g;
        private t h;
        private t i;
        private C0008a j;

        static /* synthetic */ int[] c() {
            int[] iArr = k;
            if (iArr == null) {
                iArr = new int[CBOrientation.Difference.values().length];
                try {
                    iArr[CBOrientation.Difference.ANGLE_0.ordinal()] = 1;
                } catch (NoSuchFieldError e2) {
                }
                try {
                    iArr[CBOrientation.Difference.ANGLE_180.ordinal()] = 3;
                } catch (NoSuchFieldError e3) {
                }
                try {
                    iArr[CBOrientation.Difference.ANGLE_270.ordinal()] = 4;
                } catch (NoSuchFieldError e4) {
                }
                try {
                    iArr[CBOrientation.Difference.ANGLE_90.ordinal()] = 2;
                } catch (NoSuchFieldError e5) {
                }
                k = iArr;
            }
            return iArr;
        }

        private a(Context context) {
            super(context);
            setBackgroundColor(-1842205);
            this.f = new FrameLayout(context);
            this.e = new ImageView(context);
            this.d = new ImageView(context);
            this.g = new q(context, Chartboost.sharedChartboost().getForcedOrientationDifference().isOdd() ? 0 : 1);
            this.g.b().setBackgroundColor(-1842205);
            this.f.setFocusable(false);
            this.e.setFocusable(false);
            this.d.setFocusable(false);
            this.d.setClickable(true);
            this.h = new t(context, this.d);
            this.i = new t(context, this.f);
            addView(this.i);
            this.f.addView(this.e);
            addView(this.h);
            a((View) this.e);
            a((View) this.f);
            a((View) this.d);
            a((View) this.i);
            a((View) this.h);
            this.d.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (h.this.a != null) {
                        h.this.a.a();
                    }
                }
            });
            this.j = new C0008a(context);
        }

        /* synthetic */ a(h hVar, Context context, a aVar) {
            this(context);
        }

        private void a(View view) {
            int i2 = 200;
            if (200 == getId()) {
                i2 = 201;
            }
            int i3 = i2;
            View findViewById = findViewById(i2);
            while (findViewById != null) {
                i3++;
                findViewById = findViewById(i3);
            }
            view.setId(i3);
            view.setSaveEnabled(false);
        }

        /* access modifiers changed from: protected */
        public void a(int i2, int i3) {
            if (this.g.a() != null) {
                removeView(this.g.a());
            }
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2, 17);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
            RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
            final CBOrientation.Difference forcedOrientationDifference = Chartboost.sharedChartboost().getForcedOrientationDifference();
            layoutParams2.width = forcedOrientationDifference.isOdd() ? d.a(h.h, getContext()) : -1;
            layoutParams2.height = forcedOrientationDifference.isOdd() ? -1 : d.a(h.h, getContext());
            switch (c()[forcedOrientationDifference.ordinal()]) {
                case 2:
                    layoutParams2.addRule(11);
                    break;
                case 3:
                    layoutParams2.addRule(12);
                    break;
            }
            BitmapDrawable bitmapDrawable = new BitmapDrawable(h.this.n.b());
            bitmapDrawable.setTileModeX(Shader.TileMode.REPEAT);
            bitmapDrawable.setTileModeY(Shader.TileMode.CLAMP);
            this.f.setBackgroundDrawable(bitmapDrawable);
            if (h.this.m != null) {
                this.e.setImageBitmap(h.this.m.b());
                layoutParams.width = d.a(h.this.m.c(), getContext());
                layoutParams.height = d.a(Math.min(h.h, h.this.m.d()), getContext());
            }
            this.d.setImageBitmap(h.this.l.b());
            layoutParams3.width = d.a(forcedOrientationDifference.isOdd() ? h.j : h.i, getContext());
            layoutParams3.height = d.a(forcedOrientationDifference.isOdd() ? h.i : h.j, getContext());
            switch (c()[forcedOrientationDifference.ordinal()]) {
                case 2:
                    layoutParams3.bottomMargin = d.a(10, getContext());
                    layoutParams3.rightMargin = d.a((h.h - h.j) / 2, getContext());
                    layoutParams3.addRule(11);
                    layoutParams3.addRule(12);
                    break;
                case 3:
                    layoutParams3.leftMargin = d.a(10, getContext());
                    layoutParams3.bottomMargin = d.a((h.h - h.j) / 2, getContext());
                    layoutParams3.addRule(12);
                    break;
                case 4:
                    layoutParams3.topMargin = d.a(10, getContext());
                    layoutParams3.leftMargin = d.a((h.h - h.j) / 2, getContext());
                    break;
                default:
                    layoutParams3.rightMargin = d.a(10, getContext());
                    layoutParams3.topMargin = d.a((h.h - h.j) / 2, getContext());
                    layoutParams3.addRule(11);
                    break;
            }
            layoutParams4.width = -1;
            layoutParams4.height = -1;
            switch (c()[forcedOrientationDifference.ordinal()]) {
                case 2:
                    layoutParams4.addRule(0, this.i.getId());
                    break;
                case 3:
                    layoutParams4.addRule(2, this.i.getId());
                    break;
                case 4:
                    layoutParams4.addRule(1, this.i.getId());
                    break;
                default:
                    layoutParams4.addRule(3, this.i.getId());
                    break;
            }
            this.g.a(forcedOrientationDifference.isOdd() ? 0 : 1);
            a((View) this.g.a());
            this.g.a((BaseAdapter) this.j);
            addView(this.g.a(), layoutParams4);
            if (forcedOrientationDifference == CBOrientation.Difference.ANGLE_180) {
                this.g.b().setGravity(80);
            } else if (forcedOrientationDifference == CBOrientation.Difference.ANGLE_90) {
                this.g.b().setGravity(5);
            } else {
                this.g.b().setGravity(0);
            }
            this.i.setLayoutParams(layoutParams2);
            this.e.setLayoutParams(layoutParams);
            this.e.setScaleType(ImageView.ScaleType.FIT_CENTER);
            this.h.setLayoutParams(layoutParams3);
            this.d.setScaleType(ImageView.ScaleType.FIT_CENTER);
            post(new Runnable() {
                public void run() {
                    try {
                        a.this.a = true;
                        a.this.requestLayout();
                        a.this.g.a().requestLayout();
                        a.this.g.b().requestLayout();
                        a.this.a = false;
                        if (forcedOrientationDifference == CBOrientation.Difference.ANGLE_180 || forcedOrientationDifference == CBOrientation.Difference.ANGLE_90) {
                            a.this.g.c();
                        }
                    } catch (Exception e) {
                    }
                }
            });
        }

        public void b() {
            super.b();
            this.d = null;
            this.e = null;
            this.g = null;
        }

        /* renamed from: com.chartboost.sdk.impl.h$a$a  reason: collision with other inner class name */
        public class C0008a extends ArrayAdapter<JSONObject> {
            Context a;

            public C0008a(Context context) {
                super(context, 0, h.this.k);
                this.a = context;
            }

            public View getView(int position, View convertView, ViewGroup parent) {
                t tVar;
                CBOrientation.Difference forcedOrientationDifference = Chartboost.sharedChartboost().getForcedOrientationDifference();
                if (forcedOrientationDifference.isReverse()) {
                    position = (getCount() - 1) - position;
                }
                final JSONObject a2 = getItem(position);
                String optString = a2.optString(MessagingSmsConsts.TYPE, "");
                b bVar = null;
                if (convertView == null) {
                    if (optString.equals("featured")) {
                        bVar = new d(this.a);
                    } else if (optString.equals("regular")) {
                        bVar = new e(this.a);
                    } else if (optString.equals("webview")) {
                        bVar = new i(this.a);
                    }
                    tVar = new t(this.a, (View) bVar);
                } else {
                    tVar = (t) convertView;
                    bVar = (b) tVar.a();
                }
                bVar.a(a2, position);
                c cVar = (c) bVar;
                if (forcedOrientationDifference.isOdd()) {
                    tVar.setLayoutParams(new AbsListView.LayoutParams(bVar.a(), -1));
                } else {
                    tVar.setLayoutParams(new AbsListView.LayoutParams(-1, bVar.a()));
                }
                AnonymousClass1 r2 = new View.OnClickListener() {
                    public void onClick(View v) {
                        String optString = a2.optString("deep-link");
                        if (optString == null || optString.equals("")) {
                            optString = a2.optString("link");
                        }
                        if (h.this.b != null) {
                            h.this.b.a(optString, a2);
                        }
                    }
                };
                cVar.a = r2;
                cVar.setOnClickListener(r2);
                if (bVar instanceof e) {
                    ((e) bVar).b.setOnClickListener(r2);
                }
                return tVar;
            }

            public int getCount() {
                return h.this.k.size();
            }

            /* renamed from: a */
            public JSONObject getItem(int i) {
                return (JSONObject) h.this.k.get(i);
            }
        }
    }

    public h(a aVar) {
        super(aVar);
        this.e = 3;
    }

    /* access modifiers changed from: protected */
    public b.C0006b a(Context context) {
        return new a(this, context, (a) null);
    }

    public void a(JSONObject jSONObject) {
        super.a(jSONObject);
        JSONArray optJSONArray = jSONObject.optJSONArray("cells");
        if (optJSONArray != null) {
            this.o = new SparseArray<>();
            AnonymousClass1 r2 = new e.b() {
                public void a(a.C0003a aVar, Bundle bundle) {
                    h.this.o.put(bundle.getInt("index"), aVar);
                    h.this.a(aVar);
                }
            };
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                this.k.add(optJSONObject);
                String optString = optJSONObject.optString(MessagingSmsConsts.TYPE, "");
                if (optString.equals("regular")) {
                    JSONObject optJSONObject2 = optJSONObject.optJSONObject("assets");
                    if (optJSONObject2 != null) {
                        this.e++;
                        Bundle bundle = new Bundle();
                        bundle.putInt("index", i2);
                        a(optJSONObject2, "icon", r2, bundle);
                    }
                } else if (optString.equals("featured")) {
                    JSONObject optJSONObject3 = optJSONObject.optJSONObject("assets");
                    if (optJSONObject3 != null) {
                        this.e++;
                        Bundle bundle2 = new Bundle();
                        bundle2.putInt("index", i2);
                        a(optJSONObject3, "portrait", r2, bundle2);
                        this.e++;
                        Bundle bundle3 = new Bundle();
                        bundle3.putInt("index", i2);
                        a(optJSONObject3, "landscape", r2, bundle3);
                    }
                } else {
                    optString.equals("webview");
                }
            }
            AnonymousClass2 r0 = new e.b() {
                public void a(a.C0003a aVar, Bundle bundle) {
                    h.this.l = aVar;
                    h.this.a(aVar);
                }
            };
            AnonymousClass3 r1 = new e.b() {
                public void a(a.C0003a aVar, Bundle bundle) {
                    h.this.m = aVar;
                    h.this.a(aVar);
                }
            };
            AnonymousClass4 r22 = new e.b() {
                public void a(a.C0003a aVar, Bundle bundle) {
                    h.this.n = aVar;
                    h.this.a(aVar);
                }
            };
            a("close", r0);
            a("header-center", r1);
            a("header-tile", r22);
        } else if (this.d != null) {
            this.d.a();
        }
    }

    public void c() {
        super.c();
        this.k = null;
        this.l = null;
        this.n = null;
        this.m = null;
    }
}
