package com.google.android.gms.internal;

import android.content.Context;
import android.location.Location;
import android.text.TextUtils;
import com.google.android.gms.internal.dx;
import com.google.android.gms.internal.ez;
import java.util.List;
import java.util.Map;

public final class dy extends dx.a {
    private static final Object qm = new Object();
    private static dy qn;
    private final Context mContext;
    private final ee qo;
    private final bj qp;
    private final az qq;

    dy(Context context, az azVar, bj bjVar, ee eeVar) {
        this.mContext = context;
        this.qo = eeVar;
        this.qp = bjVar;
        this.qq = azVar;
    }

    private static dv a(Context context, az azVar, bj bjVar, ee eeVar, dt dtVar) {
        String string;
        ev.z("Starting ad request from service.");
        bjVar.init();
        ed edVar = new ed(context);
        if (edVar.rj == -1) {
            ev.z("Device is offline.");
            return new dv(2);
        }
        final ea eaVar = new ea(dtVar.applicationInfo.packageName);
        if (dtVar.pV.extras != null && (string = dtVar.pV.extras.getString("_ad")) != null) {
            return dz.a(context, dtVar, string);
        }
        Location a = bjVar.a(250);
        final String aI = azVar.aI();
        String a2 = dz.a(dtVar, edVar, a, azVar.aJ());
        if (a2 == null) {
            return new dv(0);
        }
        final ez.a s = s(a2);
        final Context context2 = context;
        final dt dtVar2 = dtVar;
        eu.ss.post(new Runnable() {
            public void run() {
                ey a = ey.a(context2, new am(), false, false, (l) null, dtVar2.kO);
                a.setWillNotDraw(true);
                eaVar.b(a);
                ez bW = a.bW();
                bW.a("/invalidRequest", eaVar.qA);
                bW.a("/loadAdURL", eaVar.qB);
                bW.a("/log", bc.mX);
                bW.a(s);
                ev.z("Loading the JS library.");
                a.loadUrl(aI);
            }
        });
        ec bs = eaVar.bs();
        if (bs == null || TextUtils.isEmpty(bs.getUrl())) {
            return new dv(eaVar.getErrorCode());
        }
        String str = null;
        if (bs.bv()) {
            str = eeVar.u(dtVar.pW.packageName);
        }
        return a(context, dtVar.kO.st, bs.getUrl(), str, bs);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        com.google.android.gms.internal.ev.D("Received error HTTP response code: " + r6);
        r1 = new com.google.android.gms.internal.dv(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        r0.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.dv a(android.content.Context r10, java.lang.String r11, java.lang.String r12, java.lang.String r13, com.google.android.gms.internal.ec r14) {
        /*
            r9 = 300(0x12c, float:4.2E-43)
            r0 = 0
            com.google.android.gms.internal.eb r3 = new com.google.android.gms.internal.eb     // Catch:{ IOException -> 0x00ee }
            r3.<init>()     // Catch:{ IOException -> 0x00ee }
            java.net.URL r1 = new java.net.URL     // Catch:{ IOException -> 0x00ee }
            r1.<init>(r12)     // Catch:{ IOException -> 0x00ee }
            long r4 = android.os.SystemClock.elapsedRealtime()     // Catch:{ IOException -> 0x00ee }
            r2 = r1
            r1 = r0
        L_0x0013:
            java.net.URLConnection r0 = r2.openConnection()     // Catch:{ IOException -> 0x00ee }
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ IOException -> 0x00ee }
            r6 = 0
            com.google.android.gms.internal.ep.a(r10, r11, r6, r0)     // Catch:{ all -> 0x0111 }
            boolean r6 = android.text.TextUtils.isEmpty(r13)     // Catch:{ all -> 0x0111 }
            if (r6 != 0) goto L_0x0028
            java.lang.String r6 = "x-afma-drt-cookie"
            r0.addRequestProperty(r6, r13)     // Catch:{ all -> 0x0111 }
        L_0x0028:
            if (r14 == 0) goto L_0x0053
            java.lang.String r6 = r14.bu()     // Catch:{ all -> 0x0111 }
            boolean r6 = android.text.TextUtils.isEmpty(r6)     // Catch:{ all -> 0x0111 }
            if (r6 != 0) goto L_0x0053
            r6 = 1
            r0.setDoOutput(r6)     // Catch:{ all -> 0x0111 }
            java.lang.String r6 = r14.bu()     // Catch:{ all -> 0x0111 }
            byte[] r6 = r6.getBytes()     // Catch:{ all -> 0x0111 }
            int r7 = r6.length     // Catch:{ all -> 0x0111 }
            r0.setFixedLengthStreamingMode(r7)     // Catch:{ all -> 0x0111 }
            java.io.BufferedOutputStream r7 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x0111 }
            java.io.OutputStream r8 = r0.getOutputStream()     // Catch:{ all -> 0x0111 }
            r7.<init>(r8)     // Catch:{ all -> 0x0111 }
            r7.write(r6)     // Catch:{ all -> 0x0111 }
            r7.close()     // Catch:{ all -> 0x0111 }
        L_0x0053:
            int r6 = r0.getResponseCode()     // Catch:{ all -> 0x0111 }
            java.util.Map r7 = r0.getHeaderFields()     // Catch:{ all -> 0x0111 }
            r8 = 200(0xc8, float:2.8E-43)
            if (r6 < r8) goto L_0x0081
            if (r6 >= r9) goto L_0x0081
            java.lang.String r1 = r2.toString()     // Catch:{ all -> 0x0111 }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ all -> 0x0111 }
            java.io.InputStream r8 = r0.getInputStream()     // Catch:{ all -> 0x0111 }
            r2.<init>(r8)     // Catch:{ all -> 0x0111 }
            java.lang.String r2 = com.google.android.gms.internal.ep.a((java.lang.Readable) r2)     // Catch:{ all -> 0x0111 }
            a((java.lang.String) r1, (java.util.Map<java.lang.String, java.util.List<java.lang.String>>) r7, (java.lang.String) r2, (int) r6)     // Catch:{ all -> 0x0111 }
            r3.a(r1, r7, r2)     // Catch:{ all -> 0x0111 }
            com.google.android.gms.internal.dv r1 = r3.i((long) r4)     // Catch:{ all -> 0x0111 }
            r0.disconnect()     // Catch:{ IOException -> 0x00ee }
            r0 = r1
        L_0x0080:
            return r0
        L_0x0081:
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0111 }
            r8 = 0
            a((java.lang.String) r2, (java.util.Map<java.lang.String, java.util.List<java.lang.String>>) r7, (java.lang.String) r8, (int) r6)     // Catch:{ all -> 0x0111 }
            if (r6 < r9) goto L_0x00c5
            r2 = 400(0x190, float:5.6E-43)
            if (r6 >= r2) goto L_0x00c5
            java.lang.String r2 = "Location"
            java.lang.String r6 = r0.getHeaderField(r2)     // Catch:{ all -> 0x0111 }
            boolean r2 = android.text.TextUtils.isEmpty(r6)     // Catch:{ all -> 0x0111 }
            if (r2 == 0) goto L_0x00ab
            java.lang.String r1 = "No location header to follow redirect."
            com.google.android.gms.internal.ev.D(r1)     // Catch:{ all -> 0x0111 }
            com.google.android.gms.internal.dv r1 = new com.google.android.gms.internal.dv     // Catch:{ all -> 0x0111 }
            r2 = 0
            r1.<init>(r2)     // Catch:{ all -> 0x0111 }
            r0.disconnect()     // Catch:{ IOException -> 0x00ee }
            r0 = r1
            goto L_0x0080
        L_0x00ab:
            java.net.URL r2 = new java.net.URL     // Catch:{ all -> 0x0111 }
            r2.<init>(r6)     // Catch:{ all -> 0x0111 }
            int r1 = r1 + 1
            r6 = 5
            if (r1 <= r6) goto L_0x00e6
            java.lang.String r1 = "Too many redirects."
            com.google.android.gms.internal.ev.D(r1)     // Catch:{ all -> 0x0111 }
            com.google.android.gms.internal.dv r1 = new com.google.android.gms.internal.dv     // Catch:{ all -> 0x0111 }
            r2 = 0
            r1.<init>(r2)     // Catch:{ all -> 0x0111 }
            r0.disconnect()     // Catch:{ IOException -> 0x00ee }
            r0 = r1
            goto L_0x0080
        L_0x00c5:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0111 }
            r1.<init>()     // Catch:{ all -> 0x0111 }
            java.lang.String r2 = "Received error HTTP response code: "
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x0111 }
            java.lang.StringBuilder r1 = r1.append(r6)     // Catch:{ all -> 0x0111 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0111 }
            com.google.android.gms.internal.ev.D(r1)     // Catch:{ all -> 0x0111 }
            com.google.android.gms.internal.dv r1 = new com.google.android.gms.internal.dv     // Catch:{ all -> 0x0111 }
            r2 = 0
            r1.<init>(r2)     // Catch:{ all -> 0x0111 }
            r0.disconnect()     // Catch:{ IOException -> 0x00ee }
            r0 = r1
            goto L_0x0080
        L_0x00e6:
            r3.d(r7)     // Catch:{ all -> 0x0111 }
            r0.disconnect()     // Catch:{ IOException -> 0x00ee }
            goto L_0x0013
        L_0x00ee:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Error while connecting to ad server: "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r0 = r0.getMessage()
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r0 = r0.toString()
            com.google.android.gms.internal.ev.D(r0)
            com.google.android.gms.internal.dv r0 = new com.google.android.gms.internal.dv
            r1 = 2
            r0.<init>(r1)
            goto L_0x0080
        L_0x0111:
            r1 = move-exception
            r0.disconnect()     // Catch:{ IOException -> 0x00ee }
            throw r1     // Catch:{ IOException -> 0x00ee }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.dy.a(android.content.Context, java.lang.String, java.lang.String, java.lang.String, com.google.android.gms.internal.ec):com.google.android.gms.internal.dv");
    }

    public static dy a(Context context, az azVar, bj bjVar, ee eeVar) {
        dy dyVar;
        synchronized (qm) {
            if (qn == null) {
                qn = new dy(context.getApplicationContext(), azVar, bjVar, eeVar);
            }
            dyVar = qn;
        }
        return dyVar;
    }

    private static void a(String str, Map<String, List<String>> map, String str2, int i) {
        if (ev.p(2)) {
            ev.C("Http Response: {\n  URL:\n    " + str + "\n  Headers:");
            if (map != null) {
                for (String next : map.keySet()) {
                    ev.C("    " + next + ":");
                    for (String str3 : map.get(next)) {
                        ev.C("      " + str3);
                    }
                }
            }
            ev.C("  Body:");
            if (str2 != null) {
                for (int i2 = 0; i2 < Math.min(str2.length(), 100000); i2 += 1000) {
                    ev.C(str2.substring(i2, Math.min(str2.length(), i2 + 1000)));
                }
            } else {
                ev.C("    null");
            }
            ev.C("  Response Code:\n    " + i + "\n}");
        }
    }

    private static ez.a s(final String str) {
        return new ez.a() {
            public void a(ey eyVar) {
                String format = String.format("javascript:%s(%s);", new Object[]{"AFMA_buildAdURL", str});
                ev.C("About to execute: " + format);
                eyVar.loadUrl(format);
            }
        };
    }

    public dv b(dt dtVar) {
        return a(this.mContext, this.qq, this.qp, this.qo, dtVar);
    }
}
