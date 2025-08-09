package com.google.android.gms.internal;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.PowerManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import com.facebook.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.internal.af;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class ad implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    private static final long lE = TimeUnit.MILLISECONDS.toNanos(100);
    private final WindowManager lA;
    private final PowerManager lB;
    private final KeyguardManager lC;
    private ae lD;
    private boolean lF;
    private long lG;
    private boolean lH;
    private BroadcastReceiver lI;
    private HashSet<aa> lJ;
    private boolean lh;
    private final Object lq;
    private final WeakReference<eg> lt;
    private WeakReference<ViewTreeObserver> lu;
    private final WeakReference<View> lv;
    /* access modifiers changed from: private */
    public final ab lw;
    private final Context lx;
    /* access modifiers changed from: private */
    public final af ly;
    /* access modifiers changed from: private */
    public boolean lz;

    public ad(am amVar, eg egVar) {
        this(amVar, egVar, egVar.ow.bY(), egVar.ow, new ag(egVar.ow.getContext(), egVar.ow.bY()));
    }

    public ad(am amVar, eg egVar, ew ewVar, View view, af afVar) {
        this.lq = new Object();
        this.lh = false;
        this.lF = false;
        this.lG = Long.MIN_VALUE;
        this.lJ = new HashSet<>();
        this.lt = new WeakReference<>(egVar);
        this.lv = new WeakReference<>(view);
        this.lu = new WeakReference<>((Object) null);
        this.lH = true;
        this.lw = new ab(Integer.toString(egVar.hashCode()), ewVar, amVar.mc, egVar.rv);
        this.ly = afVar;
        this.lA = (WindowManager) view.getContext().getSystemService("window");
        this.lB = (PowerManager) view.getContext().getApplicationContext().getSystemService("power");
        this.lC = (KeyguardManager) view.getContext().getSystemService("keyguard");
        this.lx = view.getContext().getApplicationContext();
        a(afVar);
        this.ly.a(new af.a() {
            public void az() {
                boolean unused = ad.this.lz = true;
                ad.this.e(false);
                ad.this.aq();
            }
        });
        b(this.ly);
        ev.B("Tracking ad unit: " + this.lw.ap());
    }

    /* access modifiers changed from: protected */
    public int a(int i, DisplayMetrics displayMetrics) {
        return (int) (((float) i) / displayMetrics.density);
    }

    public void a(aa aaVar) {
        this.lJ.add(aaVar);
    }

    public void a(ae aeVar) {
        synchronized (this.lq) {
            this.lD = aeVar;
        }
    }

    /* access modifiers changed from: protected */
    public void a(af afVar) {
        afVar.d("http://googleads.g.doubleclick.net/mads/static/sdk/native/sdk-core-v40.html");
    }

    /* access modifiers changed from: protected */
    public void a(ey eyVar, Map<String, String> map) {
        e(false);
    }

    /* access modifiers changed from: protected */
    public void a(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = new JSONObject();
        jSONArray.put(jSONObject);
        jSONObject2.put("units", jSONArray);
        this.ly.a("AFMA_updateActiveView", jSONObject2);
    }

    /* access modifiers changed from: protected */
    public boolean a(View view, boolean z) {
        return view.getVisibility() == 0 && z && view.isShown() && this.lB.isScreenOn() && !this.lC.inKeyguardRestrictedInputMode();
    }

    /* access modifiers changed from: protected */
    public void aq() {
        synchronized (this.lq) {
            if (this.lI == null) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.SCREEN_ON");
                intentFilter.addAction("android.intent.action.SCREEN_OFF");
                this.lI = new BroadcastReceiver() {
                    public void onReceive(Context context, Intent intent) {
                        ad.this.e(false);
                    }
                };
                this.lx.registerReceiver(this.lI, intentFilter);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void ar() {
        synchronized (this.lq) {
            if (this.lI != null) {
                this.lx.unregisterReceiver(this.lI);
                this.lI = null;
            }
        }
    }

    public void as() {
        synchronized (this.lq) {
            if (this.lH) {
                aw();
                ar();
                try {
                    a(ay());
                } catch (JSONException e) {
                    ev.b("JSON Failure while processing active view data.", e);
                }
                this.lH = false;
                at();
                ev.B("Untracked ad unit: " + this.lw.ap());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void at() {
        if (this.lD != null) {
            this.lD.a(this);
        }
    }

    public boolean au() {
        boolean z;
        synchronized (this.lq) {
            z = this.lH;
        }
        return z;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000b, code lost:
        r1 = (android.view.ViewTreeObserver) r2.lu.get();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void av() {
        /*
            r2 = this;
            java.lang.ref.WeakReference<android.view.View> r0 = r2.lv
            java.lang.Object r0 = r0.get()
            android.view.View r0 = (android.view.View) r0
            if (r0 != 0) goto L_0x000b
        L_0x000a:
            return
        L_0x000b:
            java.lang.ref.WeakReference<android.view.ViewTreeObserver> r1 = r2.lu
            java.lang.Object r1 = r1.get()
            android.view.ViewTreeObserver r1 = (android.view.ViewTreeObserver) r1
            android.view.ViewTreeObserver r0 = r0.getViewTreeObserver()
            if (r0 == r1) goto L_0x000a
            java.lang.ref.WeakReference r1 = new java.lang.ref.WeakReference
            r1.<init>(r0)
            r2.lu = r1
            r0.addOnScrollChangedListener(r2)
            r0.addOnGlobalLayoutListener(r2)
            goto L_0x000a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ad.av():void");
    }

    /* access modifiers changed from: protected */
    public void aw() {
        ViewTreeObserver viewTreeObserver = (ViewTreeObserver) this.lu.get();
        if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnScrollChangedListener(this);
            viewTreeObserver.removeGlobalOnLayoutListener(this);
        }
    }

    /* access modifiers changed from: protected */
    public JSONObject ax() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("afmaVersion", this.lw.an()).put("activeViewJSON", this.lw.ao()).put("timestamp", TimeUnit.NANOSECONDS.toMillis(System.nanoTime())).put("adFormat", this.lw.am()).put("hashCode", this.lw.ap());
        return jSONObject;
    }

    /* access modifiers changed from: protected */
    public JSONObject ay() throws JSONException {
        JSONObject ax = ax();
        ax.put("doneReasonCode", "u");
        return ax;
    }

    /* access modifiers changed from: protected */
    public void b(af afVar) {
        afVar.a("/updateActiveView", (bd) new bd() {
            public void b(ey eyVar, Map<String, String> map) {
                ad.this.a(eyVar, map);
            }
        });
        afVar.a("/activeViewPingSent", (bd) new bd() {
            public void b(ey eyVar, Map<String, String> map) {
                if (map.containsKey("pingType") && "unloadPing".equals(map.get("pingType"))) {
                    ad.this.c(ad.this.ly);
                    ev.B("Unregistered GMSG handlers for: " + ad.this.lw.ap());
                }
            }
        });
        afVar.a("/visibilityChanged", (bd) new bd() {
            public void b(ey eyVar, Map<String, String> map) {
                if (map.containsKey("isVisible")) {
                    ad.this.d(Boolean.valueOf(AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("isVisible")) || ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(map.get("isVisible"))).booleanValue());
                }
            }
        });
        afVar.a("/viewabilityChanged", bc.mR);
    }

    /* access modifiers changed from: protected */
    public JSONObject c(View view) throws JSONException {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        view.getLocationInWindow(new int[2]);
        JSONObject ax = ax();
        DisplayMetrics displayMetrics = view.getContext().getResources().getDisplayMetrics();
        Rect rect = new Rect();
        rect.left = iArr[0];
        rect.top = iArr[1];
        rect.right = rect.left + view.getWidth();
        rect.bottom = rect.top + view.getHeight();
        Rect rect2 = new Rect();
        rect2.right = this.lA.getDefaultDisplay().getWidth();
        rect2.bottom = this.lA.getDefaultDisplay().getHeight();
        Rect rect3 = new Rect();
        boolean globalVisibleRect = view.getGlobalVisibleRect(rect3, (Point) null);
        Rect rect4 = new Rect();
        view.getLocalVisibleRect(rect4);
        ax.put("viewBox", new JSONObject().put("top", a(rect2.top, displayMetrics)).put("bottom", a(rect2.bottom, displayMetrics)).put("left", a(rect2.left, displayMetrics)).put("right", a(rect2.right, displayMetrics))).put("adBox", new JSONObject().put("top", a(rect.top, displayMetrics)).put("bottom", a(rect.bottom, displayMetrics)).put("left", a(rect.left, displayMetrics)).put("right", a(rect.right, displayMetrics))).put("globalVisibleBox", new JSONObject().put("top", a(rect3.top, displayMetrics)).put("bottom", a(rect3.bottom, displayMetrics)).put("left", a(rect3.left, displayMetrics)).put("right", a(rect3.right, displayMetrics))).put("localVisibleBox", new JSONObject().put("top", a(rect4.top, displayMetrics)).put("bottom", a(rect4.bottom, displayMetrics)).put("left", a(rect4.left, displayMetrics)).put("right", a(rect4.right, displayMetrics))).put("screenDensity", (double) displayMetrics.density).put("isVisible", a(view, globalVisibleRect)).put("isStopped", this.lF).put("isPaused", this.lh);
        return ax;
    }

    /* access modifiers changed from: protected */
    public void c(af afVar) {
        afVar.e("/viewabilityChanged");
        afVar.e("/visibilityChanged");
        afVar.e("/activeViewPingSent");
        afVar.e("/updateActiveView");
    }

    /* access modifiers changed from: protected */
    public void d(boolean z) {
        Iterator<aa> it = this.lJ.iterator();
        while (it.hasNext()) {
            it.next().a(this, z);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        return;
     */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void e(boolean r9) {
        /*
            r8 = this;
            java.lang.Object r2 = r8.lq
            monitor-enter(r2)
            boolean r0 = r8.lz     // Catch:{ all -> 0x001e }
            if (r0 == 0) goto L_0x000b
            boolean r0 = r8.lH     // Catch:{ all -> 0x001e }
            if (r0 != 0) goto L_0x000d
        L_0x000b:
            monitor-exit(r2)     // Catch:{ all -> 0x001e }
        L_0x000c:
            return
        L_0x000d:
            long r0 = java.lang.System.nanoTime()     // Catch:{ all -> 0x001e }
            if (r9 == 0) goto L_0x0021
            long r4 = r8.lG     // Catch:{ all -> 0x001e }
            long r6 = lE     // Catch:{ all -> 0x001e }
            long r4 = r4 + r6
            int r3 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r3 <= 0) goto L_0x0021
            monitor-exit(r2)     // Catch:{ all -> 0x001e }
            goto L_0x000c
        L_0x001e:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x001e }
            throw r0
        L_0x0021:
            r8.lG = r0     // Catch:{ all -> 0x001e }
            java.lang.ref.WeakReference<com.google.android.gms.internal.eg> r0 = r8.lt     // Catch:{ all -> 0x001e }
            java.lang.Object r0 = r0.get()     // Catch:{ all -> 0x001e }
            com.google.android.gms.internal.eg r0 = (com.google.android.gms.internal.eg) r0     // Catch:{ all -> 0x001e }
            java.lang.ref.WeakReference<android.view.View> r1 = r8.lv     // Catch:{ all -> 0x001e }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x001e }
            android.view.View r1 = (android.view.View) r1     // Catch:{ all -> 0x001e }
            if (r1 == 0) goto L_0x0037
            if (r0 != 0) goto L_0x003f
        L_0x0037:
            r0 = 1
        L_0x0038:
            if (r0 == 0) goto L_0x0041
            r8.as()     // Catch:{ all -> 0x001e }
            monitor-exit(r2)     // Catch:{ all -> 0x001e }
            goto L_0x000c
        L_0x003f:
            r0 = 0
            goto L_0x0038
        L_0x0041:
            org.json.JSONObject r0 = r8.c((android.view.View) r1)     // Catch:{ JSONException -> 0x0050 }
            r8.a((org.json.JSONObject) r0)     // Catch:{ JSONException -> 0x0050 }
        L_0x0048:
            r8.av()     // Catch:{ all -> 0x001e }
            r8.at()     // Catch:{ all -> 0x001e }
            monitor-exit(r2)     // Catch:{ all -> 0x001e }
            goto L_0x000c
        L_0x0050:
            r0 = move-exception
            java.lang.String r1 = "Active view update failed."
            com.google.android.gms.internal.ev.b(r1, r0)     // Catch:{ all -> 0x001e }
            goto L_0x0048
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ad.e(boolean):void");
    }

    public void onGlobalLayout() {
        e(false);
    }

    public void onScrollChanged() {
        e(true);
    }

    public void pause() {
        synchronized (this.lq) {
            this.lh = true;
            e(false);
            this.ly.pause();
        }
    }

    public void resume() {
        synchronized (this.lq) {
            this.ly.resume();
            this.lh = false;
            e(false);
        }
    }

    public void stop() {
        synchronized (this.lq) {
            this.lF = true;
            e(false);
            this.ly.pause();
        }
    }
}
