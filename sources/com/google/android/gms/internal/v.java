package com.google.android.gms.internal;

import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ViewSwitcher;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.internal.ar;
import com.google.android.gms.internal.dn;
import com.google.android.gms.internal.dt;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

public class v extends ar.a implements bb, be, bg, bo, cj, cm, dn.a, ek, u {
    /* access modifiers changed from: private */
    public final c kA;
    private final z kB;
    private final ac kC;
    private boolean kD;
    private final ComponentCallbacks kE = new ComponentCallbacks() {
        public void onConfigurationChanged(Configuration newConfig) {
            if (v.this.kA != null && v.this.kA.kS != null && v.this.kA.kS.ow != null) {
                v.this.kA.kS.ow.bS();
            }
        }

        public void onLowMemory() {
        }
    };
    private final bu kz;

    private static final class a extends ViewSwitcher {
        /* access modifiers changed from: private */
        public final eq kG;

        public a(Context context) {
            super(context);
            this.kG = new eq(context);
        }

        public boolean onInterceptTouchEvent(MotionEvent event) {
            this.kG.c(event);
            return false;
        }
    }

    private static final class b implements h, Runnable {
        private c kA;
        private final List<Object[]> kH = new Vector();
        private final CountDownLatch kI = new CountDownLatch(1);
        private final AtomicReference<h> kJ = new AtomicReference<>();

        public b(c cVar) {
            this.kA = cVar;
            if (eu.bR()) {
                eo.execute(this);
            } else {
                run();
            }
        }

        private void aj() {
            try {
                this.kI.await();
            } catch (InterruptedException e) {
                ev.c("Interrupted during GADSignals creation.", e);
            }
        }

        private void ak() {
            if (!this.kH.isEmpty()) {
                for (Object[] next : this.kH) {
                    if (next.length == 1) {
                        this.kJ.get().a((MotionEvent) next[0]);
                    } else if (next.length == 3) {
                        this.kJ.get().a(((Integer) next[0]).intValue(), ((Integer) next[1]).intValue(), ((Integer) next[2]).intValue());
                    }
                }
            }
        }

        public String a(Context context) {
            aj();
            ak();
            return this.kJ.get().a(context);
        }

        public String a(Context context, String str) {
            aj();
            ak();
            return this.kJ.get().a(context, str);
        }

        public void a(int i, int i2, int i3) {
            h hVar = this.kJ.get();
            if (hVar != null) {
                ak();
                hVar.a(i, i2, i3);
                return;
            }
            this.kH.add(new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)});
        }

        public void a(MotionEvent motionEvent) {
            h hVar = this.kJ.get();
            if (hVar != null) {
                ak();
                hVar.a(motionEvent);
                return;
            }
            this.kH.add(new Object[]{motionEvent});
        }

        public void run() {
            try {
                this.kJ.set(k.a(this.kA.kO.st, this.kA.kM));
            } finally {
                this.kI.countDown();
                this.kA = null;
            }
        }
    }

    private static final class c {
        public final a kK;
        public final String kL;
        public final Context kM;
        public final l kN;
        public final ew kO;
        public aq kP;
        public en kQ;
        public am kR;
        public eg kS;
        public eh kT;
        public at kU;
        public dh kV;
        public dd kW;
        public da kX;
        public el kY = null;
        public boolean kZ = false;
        private HashSet<eh> la = null;

        public c(Context context, am amVar, String str, ew ewVar) {
            if (amVar.md) {
                this.kK = null;
            } else {
                this.kK = new a(context);
                this.kK.setMinimumWidth(amVar.widthPixels);
                this.kK.setMinimumHeight(amVar.heightPixels);
                this.kK.setVisibility(4);
            }
            this.kR = amVar;
            this.kL = str;
            this.kM = context;
            this.kO = ewVar;
            this.kN = new l(new b(this));
        }

        public void a(HashSet<eh> hashSet) {
            this.la = hashSet;
        }

        public HashSet<eh> al() {
            return this.la;
        }
    }

    public v(Context context, am amVar, String str, bu buVar, ew ewVar) {
        this.kA = new c(context, amVar, str, ewVar);
        this.kz = buVar;
        this.kB = new z(this);
        this.kC = new ac();
        ep.k(context);
        R();
    }

    private void R() {
        if (Build.VERSION.SDK_INT >= 14 && this.kA != null && this.kA.kM != null) {
            this.kA.kM.registerComponentCallbacks(this.kE);
        }
    }

    private void S() {
        if (Build.VERSION.SDK_INT >= 14 && this.kA != null && this.kA.kM != null) {
            this.kA.kM.unregisterComponentCallbacks(this.kE);
        }
    }

    private void a(int i) {
        ev.D("Failed to load ad: " + i);
        if (this.kA.kP != null) {
            try {
                this.kA.kP.onAdFailedToLoad(i);
            } catch (RemoteException e) {
                ev.c("Could not call AdListener.onAdFailedToLoad().", e);
            }
        }
    }

    private void ac() {
        ev.B("Ad closing.");
        if (this.kA.kP != null) {
            try {
                this.kA.kP.onAdClosed();
            } catch (RemoteException e) {
                ev.c("Could not call AdListener.onAdClosed().", e);
            }
        }
    }

    private void ad() {
        ev.B("Ad leaving application.");
        if (this.kA.kP != null) {
            try {
                this.kA.kP.onAdLeftApplication();
            } catch (RemoteException e) {
                ev.c("Could not call AdListener.onAdLeftApplication().", e);
            }
        }
    }

    private void ae() {
        ev.B("Ad opening.");
        if (this.kA.kP != null) {
            try {
                this.kA.kP.onAdOpened();
            } catch (RemoteException e) {
                ev.c("Could not call AdListener.onAdOpened().", e);
            }
        }
    }

    private void af() {
        ev.B("Ad finished loading.");
        if (this.kA.kP != null) {
            try {
                this.kA.kP.onAdLoaded();
            } catch (RemoteException e) {
                ev.c("Could not call AdListener.onAdLoaded().", e);
            }
        }
    }

    private boolean ag() {
        boolean z = true;
        if (!ep.a(this.kA.kM.getPackageManager(), this.kA.kM.getPackageName(), "android.permission.INTERNET")) {
            if (!this.kA.kR.md) {
                eu.a(this.kA.kK, this.kA.kR, "Missing internet permission in AndroidManifest.xml.", "Missing internet permission in AndroidManifest.xml. You must have the following declaration: <uses-permission android:name=\"android.permission.INTERNET\" />");
            }
            z = false;
        }
        if (!ep.j(this.kA.kM)) {
            if (!this.kA.kR.md) {
                eu.a(this.kA.kK, this.kA.kR, "Missing AdActivity with android:configChanges in AndroidManifest.xml.", "Missing AdActivity with android:configChanges in AndroidManifest.xml. You must have the following declaration within the <application> element: <activity android:name=\"com.google.android.gms.ads.AdActivity\" android:configChanges=\"keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize\" />");
            }
            z = false;
        }
        if (!z && !this.kA.kR.md) {
            this.kA.kK.setVisibility(0);
        }
        return z;
    }

    private void ah() {
        if (this.kA.kS == null) {
            ev.D("Ad state was null when trying to ping click URLs.");
            return;
        }
        ev.z("Pinging click URLs.");
        this.kA.kT.bx();
        if (this.kA.kS.nr != null) {
            ep.a(this.kA.kM, this.kA.kO.st, this.kA.kS.nr);
        }
        if (this.kA.kS.rw != null && this.kA.kS.rw.nr != null) {
            bs.a(this.kA.kM, this.kA.kO.st, this.kA.kS, this.kA.kL, false, this.kA.kS.rw.nr);
        }
    }

    private void ai() {
        if (this.kA.kS != null) {
            this.kA.kS.ow.destroy();
            this.kA.kS = null;
        }
    }

    private void b(View view) {
        this.kA.kK.addView(view, new ViewGroup.LayoutParams(-2, -2));
    }

    private boolean b(eg egVar) {
        if (egVar.qd) {
            try {
                View view = (View) e.e(egVar.nL.getView());
                View nextView = this.kA.kK.getNextView();
                if (nextView != null) {
                    this.kA.kK.removeView(nextView);
                }
                try {
                    b(view);
                } catch (Throwable th) {
                    ev.c("Could not add mediation view to view hierarchy.", th);
                    return false;
                }
            } catch (RemoteException e) {
                ev.c("Could not get View from mediation adapter.", e);
                return false;
            }
        } else if (egVar.rx != null) {
            egVar.ow.a(egVar.rx);
            this.kA.kK.removeAllViews();
            this.kA.kK.setMinimumWidth(egVar.rx.widthPixels);
            this.kA.kK.setMinimumHeight(egVar.rx.heightPixels);
            b((View) egVar.ow);
        }
        if (this.kA.kK.getChildCount() > 1) {
            this.kA.kK.showNext();
        }
        if (this.kA.kS != null) {
            View nextView2 = this.kA.kK.getNextView();
            if (nextView2 instanceof ey) {
                ((ey) nextView2).a(this.kA.kM, this.kA.kR);
            } else if (nextView2 != null) {
                this.kA.kK.removeView(nextView2);
            }
            if (this.kA.kS.nL != null) {
                try {
                    this.kA.kS.nL.destroy();
                } catch (RemoteException e2) {
                    ev.D("Could not destroy previous mediation adapter.");
                }
            }
        }
        this.kA.kK.setVisibility(0);
        return true;
    }

    private dt.a c(aj ajVar) {
        PackageInfo packageInfo;
        Bundle bundle;
        ApplicationInfo applicationInfo = this.kA.kM.getApplicationInfo();
        try {
            packageInfo = this.kA.kM.getPackageManager().getPackageInfo(applicationInfo.packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
        }
        if (this.kA.kR.md || this.kA.kK.getParent() == null) {
            bundle = null;
        } else {
            int[] iArr = new int[2];
            this.kA.kK.getLocationOnScreen(iArr);
            int i = iArr[0];
            int i2 = iArr[1];
            DisplayMetrics displayMetrics = this.kA.kM.getResources().getDisplayMetrics();
            int width = this.kA.kK.getWidth();
            int height = this.kA.kK.getHeight();
            int i3 = (!this.kA.kK.isShown() || i + width <= 0 || i2 + height <= 0 || i > displayMetrics.widthPixels || i2 > displayMetrics.heightPixels) ? 0 : 1;
            bundle = new Bundle(5);
            bundle.putInt("x", i);
            bundle.putInt("y", i2);
            bundle.putInt("width", width);
            bundle.putInt("height", height);
            bundle.putInt("visible", i3);
        }
        String bD = ei.bD();
        this.kA.kT = new eh(bD, this.kA.kL);
        this.kA.kT.f(ajVar);
        return new dt.a(bundle, ajVar, this.kA.kR, this.kA.kL, applicationInfo, packageInfo, bD, ei.rN, this.kA.kO, ei.a(this.kA.kM, this, bD));
    }

    private void c(boolean z) {
        if (this.kA.kS == null) {
            ev.D("Ad state was null when trying to ping impression URLs.");
            return;
        }
        ev.z("Pinging Impression URLs.");
        this.kA.kT.bw();
        if (this.kA.kS.ns != null) {
            ep.a(this.kA.kM, this.kA.kO.st, this.kA.kS.ns);
        }
        if (!(this.kA.kS.rw == null || this.kA.kS.rw.ns == null)) {
            bs.a(this.kA.kM, this.kA.kO.st, this.kA.kS, this.kA.kL, z, this.kA.kS.rw.ns);
        }
        if (this.kA.kS.nK != null && this.kA.kS.nK.nn != null) {
            bs.a(this.kA.kM, this.kA.kO.st, this.kA.kS, this.kA.kL, z, this.kA.kS.nK.nn);
        }
    }

    public d P() {
        hn.ay("getAdFrame must be called on the main UI thread.");
        return e.h(this.kA.kK);
    }

    public am Q() {
        hn.ay("getAdSize must be called on the main UI thread.");
        return this.kA.kR;
    }

    public void T() {
        ad();
    }

    public void U() {
        this.kC.d(this.kA.kS);
        if (this.kA.kR.md) {
            ai();
        }
        this.kD = false;
        ac();
        this.kA.kT.by();
    }

    public void V() {
        if (this.kA.kR.md) {
            c(false);
        }
        this.kD = true;
        ae();
    }

    public void W() {
        onAdClicked();
    }

    public void X() {
        U();
    }

    public void Y() {
        T();
    }

    public void Z() {
        V();
    }

    public void a(am amVar) {
        hn.ay("setAdSize must be called on the main UI thread.");
        this.kA.kR = amVar;
        if (this.kA.kS != null) {
            this.kA.kS.ow.a(amVar);
        }
        if (this.kA.kK.getChildCount() > 1) {
            this.kA.kK.removeView(this.kA.kK.getNextView());
        }
        this.kA.kK.setMinimumWidth(amVar.widthPixels);
        this.kA.kK.setMinimumHeight(amVar.heightPixels);
        this.kA.kK.requestLayout();
    }

    public void a(aq aqVar) {
        hn.ay("setAdListener must be called on the main UI thread.");
        this.kA.kP = aqVar;
    }

    public void a(at atVar) {
        hn.ay("setAppEventListener must be called on the main UI thread.");
        this.kA.kU = atVar;
    }

    public void a(dd ddVar) {
        hn.ay("setInAppPurchaseListener must be called on the main UI thread.");
        this.kA.kW = ddVar;
    }

    public void a(dh dhVar, String str) {
        hn.ay("setPlayStorePurchaseParams must be called on the main UI thread.");
        this.kA.kX = new da(str);
        this.kA.kV = dhVar;
        if (!ei.bH() && dhVar != null) {
            new ct(this.kA.kM, this.kA.kV, this.kA.kX).start();
        }
    }

    public void a(eg egVar) {
        int i;
        int i2 = 0;
        this.kA.kQ = null;
        if (!(egVar.errorCode == -2 || egVar.errorCode == 3)) {
            ei.b(this.kA.al());
        }
        if (egVar.errorCode != -1) {
            boolean z = egVar.pV.extras != null ? egVar.pV.extras.getBoolean("_noRefresh", false) : false;
            if (this.kA.kR.md) {
                ep.a((WebView) egVar.ow);
            } else if (!z) {
                if (egVar.nv > 0) {
                    this.kB.a(egVar.pV, egVar.nv);
                } else if (egVar.rw != null && egVar.rw.nv > 0) {
                    this.kB.a(egVar.pV, egVar.rw.nv);
                } else if (!egVar.qd && egVar.errorCode == 2) {
                    this.kB.d(egVar.pV);
                }
            }
            if (!(egVar.errorCode != 3 || egVar.rw == null || egVar.rw.nt == null)) {
                ev.z("Pinging no fill URLs.");
                bs.a(this.kA.kM, this.kA.kO.st, egVar, this.kA.kL, false, egVar.rw.nt);
            }
            if (egVar.errorCode != -2) {
                a(egVar.errorCode);
                return;
            }
            if (!this.kA.kR.md) {
                if (!b(egVar)) {
                    a(0);
                    return;
                } else if (this.kA.kK != null) {
                    this.kA.kK.kG.x(egVar.qi);
                }
            }
            if (!(this.kA.kS == null || this.kA.kS.nN == null)) {
                this.kA.kS.nN.a((bo) null);
            }
            if (egVar.nN != null) {
                egVar.nN.a((bo) this);
            }
            this.kC.d(this.kA.kS);
            this.kA.kS = egVar;
            if (egVar.rx != null) {
                this.kA.kR = egVar.rx;
            }
            this.kA.kT.j(egVar.ry);
            this.kA.kT.k(egVar.rz);
            this.kA.kT.n(this.kA.kR.md);
            this.kA.kT.o(egVar.qd);
            if (!this.kA.kR.md) {
                c(false);
            }
            if (this.kA.kY == null) {
                this.kA.kY = new el(this.kA.kL);
            }
            if (egVar.rw != null) {
                i = egVar.rw.nw;
                i2 = egVar.rw.nx;
            } else {
                i = 0;
            }
            this.kA.kY.a(i, i2);
            if (!this.kA.kR.md && egVar.ow != null && (egVar.ow.bW().ce() || egVar.rv != null)) {
                ad a2 = this.kC.a(this.kA.kR, this.kA.kS);
                if (egVar.ow.bW().ce() && a2 != null) {
                    a2.a((aa) new y(egVar.ow));
                }
            }
            this.kA.kS.ow.bS();
            af();
        }
    }

    public void a(String str, ArrayList<String> arrayList) {
        cu cuVar = new cu(str, arrayList, this.kA.kM, this.kA.kO.st);
        if (this.kA.kW == null) {
            ev.D("InAppPurchaseListener is not set. Try to launch default purchase flow.");
            if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.kA.kM) != 0) {
                ev.D("Google Play Service unavailable, cannot launch default purchase flow.");
            } else if (this.kA.kV == null) {
                ev.D("PlayStorePurchaseListener is not set.");
            } else if (this.kA.kX == null) {
                ev.D("PlayStorePurchaseVerifier is not initialized.");
            } else {
                try {
                    if (!this.kA.kV.isValidPurchase(str)) {
                        return;
                    }
                } catch (RemoteException e) {
                    ev.D("Could not start In-App purchase.");
                }
                cv.a(this.kA.kM, this.kA.kO.sw, new cr(cuVar, this.kA.kV, this.kA.kX, this.kA.kM));
            }
        } else {
            try {
                this.kA.kW.a(cuVar);
            } catch (RemoteException e2) {
                ev.D("Could not start In-App purchase.");
            }
        }
    }

    public void a(HashSet<eh> hashSet) {
        this.kA.a(hashSet);
    }

    public boolean a(aj ajVar) {
        ey a2;
        ey eyVar;
        hn.ay("loadAd must be called on the main UI thread.");
        if (this.kA.kQ != null) {
            ev.D("An ad request is already in progress. Aborting.");
            return false;
        } else if (this.kA.kR.md && this.kA.kS != null) {
            ev.D("An interstitial is already loading. Aborting.");
            return false;
        } else if (!ag()) {
            return false;
        } else {
            ev.B("Starting ad request.");
            if (!ajVar.lT) {
                ev.B("Use AdRequest.Builder.addTestDevice(\"" + eu.o(this.kA.kM) + "\") to get test ads on this device.");
            }
            this.kB.cancel();
            this.kA.kZ = false;
            dt.a c2 = c(ajVar);
            if (this.kA.kR.md) {
                ey a3 = ey.a(this.kA.kM, this.kA.kR, false, false, this.kA.kN, this.kA.kO);
                a3.bW().a(this, (cj) null, this, this, true, this, this);
                eyVar = a3;
            } else {
                View nextView = this.kA.kK.getNextView();
                if (nextView instanceof ey) {
                    a2 = (ey) nextView;
                    a2.a(this.kA.kM, this.kA.kR);
                } else {
                    if (nextView != null) {
                        this.kA.kK.removeView(nextView);
                    }
                    a2 = ey.a(this.kA.kM, this.kA.kR, false, false, this.kA.kN, this.kA.kO);
                    if (this.kA.kR.me == null) {
                        b((View) a2);
                    }
                }
                a2.bW().a(this, this, this, this, false, this);
                eyVar = a2;
            }
            this.kA.kQ = dn.a(this.kA.kM, c2, this.kA.kN, eyVar, this.kz, this);
            return true;
        }
    }

    public void aa() {
        if (this.kA.kS != null) {
            ev.D("Mediation adapter " + this.kA.kS.nM + " refreshed, but mediation adapters should never refresh.");
        }
        c(true);
        af();
    }

    public void ab() {
        hn.ay("recordManualImpression must be called on the main UI thread.");
        if (this.kA.kS == null) {
            ev.D("Ad state was null when trying to ping manual tracking URLs.");
            return;
        }
        ev.z("Pinging manual tracking URLs.");
        if (this.kA.kS.qf != null) {
            ep.a(this.kA.kM, this.kA.kO.st, this.kA.kS.qf);
        }
    }

    public void b(aj ajVar) {
        ViewParent parent = this.kA.kK.getParent();
        if (!(parent instanceof View) || !((View) parent).isShown() || !ep.bL() || this.kD) {
            ev.B("Ad is not visible. Not refreshing ad.");
            this.kB.d(ajVar);
            return;
        }
        a(ajVar);
    }

    public void b(boolean z) {
        this.kA.kZ = z;
    }

    public void destroy() {
        hn.ay("destroy must be called on the main UI thread.");
        S();
        this.kA.kP = null;
        this.kA.kU = null;
        this.kB.cancel();
        this.kC.stop();
        stopLoading();
        if (this.kA.kK != null) {
            this.kA.kK.removeAllViews();
        }
        if (!(this.kA.kS == null || this.kA.kS.ow == null)) {
            this.kA.kS.ow.destroy();
        }
        if (this.kA.kS != null && this.kA.kS.nL != null) {
            try {
                this.kA.kS.nL.destroy();
            } catch (RemoteException e) {
                ev.D("Could not destroy mediation adapter.");
            }
        }
    }

    public boolean isReady() {
        hn.ay("isLoaded must be called on the main UI thread.");
        return this.kA.kQ == null && this.kA.kS != null;
    }

    public void onAdClicked() {
        ah();
    }

    public void onAppEvent(String name, String info) {
        if (this.kA.kU != null) {
            try {
                this.kA.kU.onAppEvent(name, info);
            } catch (RemoteException e) {
                ev.c("Could not call the AppEventListener.", e);
            }
        }
    }

    public void pause() {
        hn.ay("pause must be called on the main UI thread.");
        if (this.kA.kS != null) {
            ep.a((WebView) this.kA.kS.ow);
        }
        if (!(this.kA.kS == null || this.kA.kS.nL == null)) {
            try {
                this.kA.kS.nL.pause();
            } catch (RemoteException e) {
                ev.D("Could not pause mediation adapter.");
            }
        }
        this.kC.pause();
        this.kB.pause();
    }

    public void resume() {
        hn.ay("resume must be called on the main UI thread.");
        if (this.kA.kS != null) {
            ep.b((WebView) this.kA.kS.ow);
        }
        if (!(this.kA.kS == null || this.kA.kS.nL == null)) {
            try {
                this.kA.kS.nL.resume();
            } catch (RemoteException e) {
                ev.D("Could not resume mediation adapter.");
            }
        }
        this.kB.resume();
        this.kC.resume();
    }

    public void showInterstitial() {
        hn.ay("showInterstitial must be called on the main UI thread.");
        if (!this.kA.kR.md) {
            ev.D("Cannot call showInterstitial on a banner ad.");
        } else if (this.kA.kS == null) {
            ev.D("The interstitial has not loaded.");
        } else if (this.kA.kS.ow.bZ()) {
            ev.D("The interstitial is already showing.");
        } else {
            this.kA.kS.ow.q(true);
            if (this.kA.kS.ow.bW().ce() || this.kA.kS.rv != null) {
                ad a2 = this.kC.a(this.kA.kR, this.kA.kS);
                if (this.kA.kS.ow.bW().ce() && a2 != null) {
                    a2.a((aa) new y(this.kA.kS.ow));
                }
            }
            if (this.kA.kS.qd) {
                try {
                    this.kA.kS.nL.showInterstitial();
                } catch (RemoteException e) {
                    ev.c("Could not show interstitial.", e);
                    ai();
                }
            } else {
                w wVar = new w(this.kA.kZ, false);
                if (this.kA.kM instanceof Activity) {
                    Window window = ((Activity) this.kA.kM).getWindow();
                    Rect rect = new Rect();
                    Rect rect2 = new Rect();
                    window.getDecorView().getGlobalVisibleRect(rect);
                    window.getDecorView().getWindowVisibleDisplayFrame(rect2);
                    if (!(rect.bottom == 0 || rect2.bottom == 0)) {
                        wVar = new w(this.kA.kZ, rect.top == rect2.top);
                    }
                }
                cg.a(this.kA.kM, new ci(this, this, this, this.kA.kS.ow, this.kA.kS.orientation, this.kA.kO, this.kA.kS.qi, wVar));
            }
        }
    }

    public void stopLoading() {
        hn.ay("stopLoading must be called on the main UI thread.");
        if (this.kA.kS != null) {
            this.kA.kS.ow.stopLoading();
            this.kA.kS = null;
        }
        if (this.kA.kQ != null) {
            this.kA.kQ.cancel();
        }
    }
}
