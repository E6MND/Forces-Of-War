package com.crashlytics.android.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import com.crashlytics.android.Crashlytics;
import java.io.File;

public class D extends C0187u {
    private String a;
    private String b;
    private String c;
    private C0156ao d;
    private aJ e;
    private long f;
    private C0163av g;
    private O h;

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0083 A[Catch:{ Exception -> 0x00f1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00cd A[Catch:{ Exception -> 0x0108 }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void a(com.crashlytics.android.internal.D r14) {
        /*
            r11 = 1
            r12 = 0
            android.content.Context r13 = r14.getContext()
            com.crashlytics.android.internal.X r0 = new com.crashlytics.android.internal.X     // Catch:{ Exception -> 0x00f1 }
            r0.<init>()     // Catch:{ Exception -> 0x00f1 }
            com.crashlytics.android.internal.ah r1 = new com.crashlytics.android.internal.ah     // Catch:{ Exception -> 0x00f1 }
            r1.<init>()     // Catch:{ Exception -> 0x00f1 }
            com.crashlytics.android.internal.aj r2 = new com.crashlytics.android.internal.aj     // Catch:{ Exception -> 0x00f1 }
            com.crashlytics.android.internal.v r3 = com.crashlytics.android.internal.C0188v.a()     // Catch:{ Exception -> 0x00f1 }
            java.io.File r3 = r3.h()     // Catch:{ Exception -> 0x00f1 }
            java.lang.String r4 = "session_analytics.tap"
            java.lang.String r5 = "session_analytics_to_send"
            r2.<init>(r3, r4, r5)     // Catch:{ Exception -> 0x00f1 }
            com.crashlytics.android.internal.K r9 = new com.crashlytics.android.internal.K     // Catch:{ Exception -> 0x00f1 }
            r9.<init>(r0, r1, r2)     // Catch:{ Exception -> 0x00f1 }
            com.crashlytics.android.internal.ao r0 = r14.d     // Catch:{ Exception -> 0x00f1 }
            java.lang.String r3 = r0.b()     // Catch:{ Exception -> 0x00f1 }
            com.crashlytics.android.internal.ao r0 = r14.d     // Catch:{ Exception -> 0x00f1 }
            java.lang.String r4 = r0.g()     // Catch:{ Exception -> 0x00f1 }
            com.crashlytics.android.internal.ao r0 = r14.d     // Catch:{ Exception -> 0x00f1 }
            java.lang.String r5 = r0.c()     // Catch:{ Exception -> 0x00f1 }
            com.crashlytics.android.internal.ao r0 = r14.d     // Catch:{ Exception -> 0x00f1 }
            java.lang.String r6 = r0.d()     // Catch:{ Exception -> 0x00f1 }
            com.crashlytics.android.internal.v r0 = com.crashlytics.android.internal.C0188v.a()     // Catch:{ Exception -> 0x00f1 }
            android.app.Application r1 = r0.d()     // Catch:{ Exception -> 0x00f1 }
            if (r1 == 0) goto L_0x00de
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x00f1 }
            r2 = 14
            if (r0 < r2) goto L_0x00de
            com.crashlytics.android.internal.F r0 = new com.crashlytics.android.internal.F     // Catch:{ Exception -> 0x00f1 }
            java.lang.String r2 = r13.getPackageName()     // Catch:{ Exception -> 0x00f1 }
            java.lang.String r7 = r14.b     // Catch:{ Exception -> 0x00f1 }
            java.lang.String r8 = r14.c     // Catch:{ Exception -> 0x00f1 }
            com.crashlytics.android.internal.av r10 = r14.g     // Catch:{ Exception -> 0x00f1 }
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ Exception -> 0x00f1 }
            r14.h = r0     // Catch:{ Exception -> 0x00f1 }
        L_0x005f:
            long r0 = r14.f     // Catch:{ Exception -> 0x00f1 }
            com.crashlytics.android.internal.aJ r2 = r14.e     // Catch:{ Exception -> 0x00f1 }
            android.content.SharedPreferences r2 = r2.a()     // Catch:{ Exception -> 0x00f1 }
            java.lang.String r3 = "analytics_launched"
            r4 = 0
            boolean r2 = r2.getBoolean(r3, r4)     // Catch:{ Exception -> 0x00f1 }
            if (r2 != 0) goto L_0x00fa
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x00f1 }
            long r0 = r2 - r0
            r2 = 3600000(0x36ee80, double:1.7786363E-317)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 >= 0) goto L_0x00f8
            r0 = r11
        L_0x007e:
            if (r0 == 0) goto L_0x00fa
            r0 = r11
        L_0x0081:
            if (r0 == 0) goto L_0x00ad
            com.crashlytics.android.internal.v r0 = com.crashlytics.android.internal.C0188v.a()     // Catch:{ Exception -> 0x00f1 }
            com.crashlytics.android.internal.q r0 = r0.b()     // Catch:{ Exception -> 0x00f1 }
            java.lang.String r1 = "Crashlytics"
            java.lang.String r2 = "First launch"
            r0.a(r1, r2)     // Catch:{ Exception -> 0x00f1 }
            com.crashlytics.android.internal.O r0 = r14.h     // Catch:{ Exception -> 0x00f1 }
            if (r0 == 0) goto L_0x00ad
            com.crashlytics.android.internal.O r0 = r14.h     // Catch:{ Exception -> 0x00f1 }
            r0.b()     // Catch:{ Exception -> 0x00f1 }
            com.crashlytics.android.internal.aJ r0 = r14.e     // Catch:{ Exception -> 0x00f1 }
            com.crashlytics.android.internal.aJ r1 = r14.e     // Catch:{ Exception -> 0x00f1 }
            android.content.SharedPreferences$Editor r1 = r1.b()     // Catch:{ Exception -> 0x00f1 }
            java.lang.String r2 = "analytics_launched"
            r3 = 1
            android.content.SharedPreferences$Editor r1 = r1.putBoolean(r2, r3)     // Catch:{ Exception -> 0x00f1 }
            r0.a(r1)     // Catch:{ Exception -> 0x00f1 }
        L_0x00ad:
            com.crashlytics.android.internal.aS r0 = com.crashlytics.android.internal.aS.a()     // Catch:{ Exception -> 0x0108 }
            com.crashlytics.android.internal.av r2 = r14.g     // Catch:{ Exception -> 0x0108 }
            java.lang.String r3 = r14.b     // Catch:{ Exception -> 0x0108 }
            java.lang.String r4 = r14.c     // Catch:{ Exception -> 0x0108 }
            java.lang.String r5 = r14.b()     // Catch:{ Exception -> 0x0108 }
            r1 = r13
            com.crashlytics.android.internal.aS r0 = r0.a(r1, r2, r3, r4, r5)     // Catch:{ Exception -> 0x0108 }
            r0.c()     // Catch:{ Exception -> 0x0108 }
            com.crashlytics.android.internal.aS r0 = com.crashlytics.android.internal.aS.a()     // Catch:{ Exception -> 0x0108 }
            com.crashlytics.android.internal.aX r0 = r0.b()     // Catch:{ Exception -> 0x0108 }
            if (r0 == 0) goto L_0x00dd
            com.crashlytics.android.internal.aP r1 = r0.d     // Catch:{ Exception -> 0x0108 }
            boolean r1 = r1.c     // Catch:{ Exception -> 0x0108 }
            if (r1 != 0) goto L_0x00fc
            java.lang.String r0 = "Disabling analytics collection based on settings flag value."
            com.crashlytics.android.internal.C0143ab.c((java.lang.String) r0)     // Catch:{ Exception -> 0x0108 }
            com.crashlytics.android.internal.O r0 = r14.h     // Catch:{ Exception -> 0x0108 }
            r0.a()     // Catch:{ Exception -> 0x0108 }
        L_0x00dd:
            return
        L_0x00de:
            com.crashlytics.android.internal.O r1 = new com.crashlytics.android.internal.O     // Catch:{ Exception -> 0x00f1 }
            java.lang.String r2 = r13.getPackageName()     // Catch:{ Exception -> 0x00f1 }
            java.lang.String r7 = r14.b     // Catch:{ Exception -> 0x00f1 }
            java.lang.String r8 = r14.c     // Catch:{ Exception -> 0x00f1 }
            com.crashlytics.android.internal.av r10 = r14.g     // Catch:{ Exception -> 0x00f1 }
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ Exception -> 0x00f1 }
            r14.h = r1     // Catch:{ Exception -> 0x00f1 }
            goto L_0x005f
        L_0x00f1:
            r0 = move-exception
            java.lang.String r0 = "Crashlytics failed to initialize session analytics."
            com.crashlytics.android.internal.C0143ab.d((java.lang.String) r0)
            goto L_0x00ad
        L_0x00f8:
            r0 = r12
            goto L_0x007e
        L_0x00fa:
            r0 = r12
            goto L_0x0081
        L_0x00fc:
            com.crashlytics.android.internal.O r1 = r14.h     // Catch:{ Exception -> 0x0108 }
            com.crashlytics.android.internal.aK r0 = r0.e     // Catch:{ Exception -> 0x0108 }
            java.lang.String r2 = r14.b()     // Catch:{ Exception -> 0x0108 }
            r1.a((com.crashlytics.android.internal.aK) r0, (java.lang.String) r2)     // Catch:{ Exception -> 0x0108 }
            goto L_0x00dd
        L_0x0108:
            r0 = move-exception
            com.crashlytics.android.internal.v r1 = com.crashlytics.android.internal.C0188v.a()
            com.crashlytics.android.internal.q r1 = r1.b()
            java.lang.String r2 = "Crashlytics"
            java.lang.String r3 = "Error dealing with settings"
            r1.a((java.lang.String) r2, (java.lang.String) r3, (java.lang.Throwable) r0)
            goto L_0x00dd
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crashlytics.android.internal.D.a(com.crashlytics.android.internal.D):void");
    }

    public static D a() {
        return (D) C0188v.a().a(D.class);
    }

    /* access modifiers changed from: protected */
    public final void c() {
        try {
            this.g = new C0163av(C0188v.a().b());
            this.e = new aJ(C0188v.a().a(D.class));
            Context context = getContext();
            PackageManager packageManager = context.getPackageManager();
            this.d = new C0156ao(context);
            this.a = context.getPackageName();
            PackageInfo packageInfo = packageManager.getPackageInfo(this.a, 0);
            this.b = Integer.toString(packageInfo.versionCode);
            this.c = packageInfo.versionName == null ? "0.0" : packageInfo.versionName;
            if (Build.VERSION.SDK_INT >= 9) {
                this.f = packageInfo.firstInstallTime;
            } else {
                this.f = new File(context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).sourceDir).lastModified();
            }
        } catch (Exception e2) {
            C0188v.a().b().a(Crashlytics.TAG, "Error setting up app properties", (Throwable) e2);
        }
        new Thread(new E(this), "Crashlytics Initializer").start();
    }

    public String getVersion() {
        return C0188v.a().getVersion();
    }

    private String b() {
        return C0143ab.a(getContext(), "com.crashlytics.ApiEndpoint");
    }

    public final void a(C0148ag agVar) {
        if (this.h != null) {
            this.h.b(agVar.a());
        }
    }

    public final void a(C0147af afVar) {
        if (this.h != null) {
            this.h.a(afVar.a());
        }
    }
}
