package com.chartboost.sdk;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.chartboost.sdk.impl.a;
import com.chartboost.sdk.impl.n;
import com.chartboost.sdk.impl.r;
import com.chartboost.sdk.impl.s;

public final class a {
    /* access modifiers changed from: private */
    public Chartboost a;
    private Activity b;
    private r c;
    private boolean d = false;
    private boolean e = false;
    private boolean f = false;
    private s g;
    private s h;

    protected a(Chartboost chartboost, Activity activity) {
        this.a = chartboost;
        this.b = activity;
    }

    /* access modifiers changed from: protected */
    public void a(Activity activity) {
        this.b = activity;
    }

    /* access modifiers changed from: protected */
    public void a(C0005a aVar) {
        if (aVar.a) {
            b();
        } else if (aVar.b != null) {
            b(aVar.b);
        }
    }

    private void b(com.chartboost.sdk.impl.a aVar) {
        if (!this.e) {
            aVar.c = a.b.CBImpressionStateWaitingForDisplay;
            if (!aVar.f.b()) {
                if (aVar.f.d != null) {
                    aVar.f.d.a();
                }
            } else if (aVar.i) {
                aVar.i = false;
                this.g = new s(this.b, aVar.f.d());
                this.b.addContentView(this.g, new FrameLayout.LayoutParams(-1, -1));
                aVar.c = a.b.CBImpressionStateDisplayedByDefaultController;
                aVar.h = this.g;
                this.e = true;
            } else {
                this.g = new s(this.b, aVar.f.d());
                this.b.addContentView(this.g, new FrameLayout.LayoutParams(-1, -1));
                this.g.b().a();
                n.b bVar = n.b.CBAnimationTypePerspectiveRotate;
                if (aVar.d == a.c.CBImpressionTypeMoreApps) {
                    bVar = n.b.CBAnimationTypePerspectiveZoom;
                }
                if (aVar.a.optInt("animation") != 0) {
                    bVar = n.b.values()[aVar.a.optInt("animation")];
                }
                aVar.c = a.b.CBImpressionStateDisplayedByDefaultController;
                aVar.h = this.g;
                n.a(bVar, aVar);
                this.e = true;
                ChartboostDelegate delegate = this.a.getDelegate();
                if (delegate == null) {
                    return;
                }
                if (aVar.d == a.c.CBImpressionTypeInterstitial) {
                    delegate.didShowInterstitial(aVar.e);
                } else if (aVar.d == a.c.CBImpressionTypeMoreApps) {
                    delegate.didShowMoreApps();
                }
            }
        }
    }

    public void a(com.chartboost.sdk.impl.a aVar, boolean z) {
        this.e = false;
        if (!z) {
            this.f = true;
        }
        aVar.c = a.b.CBImpressionStateWaitingForDismissal;
        n.b bVar = n.b.CBAnimationTypePerspectiveRotate;
        if (aVar.d == a.c.CBImpressionTypeMoreApps) {
            bVar = n.b.CBAnimationTypePerspectiveZoom;
        }
        if (aVar.a.optInt("animation") != 0) {
            bVar = n.b.values()[aVar.a.optInt("animation")];
        }
        n.b(bVar, aVar, c(aVar));
    }

    public void a(com.chartboost.sdk.impl.a aVar) {
        this.e = false;
        new b(aVar, true).run();
        if (aVar.c == a.b.CBImpressionStateDisplayedByDefaultController) {
            aVar.c = a.b.CBImpressionStateWaitingForDisplay;
        } else {
            aVar.c = a.b.CBImpressionStateOther;
        }
        aVar.c();
        try {
            ((ViewGroup) this.g.getParent()).removeView(this.g);
        } catch (Exception e2) {
        }
    }

    private void b() {
        this.c = new r(this.b);
        this.h = new s(this.b, this.c);
        this.h.b().a(true);
        this.b.addContentView(this.h, new FrameLayout.LayoutParams(-1, -1));
        this.h.b().a();
        this.h.b().a((View) this.c.getParent());
        this.d = true;
    }

    public void a(boolean z) {
        if (a()) {
            try {
                ((ViewGroup) this.h.getParent()).removeView(this.h);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.c = null;
            this.h = null;
            this.d = false;
            if (!this.f && z && this.a.getImpressionsUseActivities() && !this.e && (this.b instanceof CBImpressionActivity)) {
                this.a.c();
                this.b.finish();
            }
        }
    }

    public void b(com.chartboost.sdk.impl.a aVar, boolean z) {
        if (this.g != null) {
            try {
                ((ViewGroup) this.g.getParent()).removeView(this.g);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            aVar.b();
            this.g = null;
            this.f = false;
            if (this.a.getImpressionsUseActivities() && !z && !this.d && (this.b instanceof CBImpressionActivity)) {
                this.a.c();
                this.b.finish();
            }
        }
    }

    private class b implements Runnable {
        private com.chartboost.sdk.impl.a b;
        private boolean c;

        public b(com.chartboost.sdk.impl.a aVar, boolean z) {
            this.b = aVar;
            this.c = z;
        }

        public void run() {
            if (this.b.c == a.b.CBImpressionStateWaitingForDismissal) {
                this.b.c = a.b.CBImpressionStateOther;
                a.this.b(this.b, this.c);
            }
        }
    }

    private n.a c(com.chartboost.sdk.impl.a aVar) {
        return new n.a() {
            public void a(com.chartboost.sdk.impl.a aVar) {
                a.this.a.a.post(new b(aVar, false));
            }
        };
    }

    /* access modifiers changed from: protected */
    public boolean a() {
        return this.d;
    }

    /* renamed from: com.chartboost.sdk.a$a  reason: collision with other inner class name */
    protected static final class C0005a {
        protected boolean a;
        protected com.chartboost.sdk.impl.a b;

        protected C0005a(boolean z, com.chartboost.sdk.impl.a aVar) {
            this.a = z;
            this.b = aVar;
        }

        public C0005a(com.chartboost.sdk.impl.a aVar) {
            this.a = false;
            this.b = aVar;
        }
    }
}
