package com.crashlytics.android.internal;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.util.Log;

/* renamed from: com.crashlytics.android.internal.r  reason: case insensitive filesystem */
public class C0184r implements C0183q {
    private static boolean b(int i) {
        return C0191y.a.g() <= i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(android.content.Context r6, boolean r7) {
        /*
            r1 = 0
            android.content.Context r0 = r6.getApplicationContext()     // Catch:{ Exception -> 0x004b }
            java.lang.String r2 = r0.getPackageName()     // Catch:{ Exception -> 0x004b }
            android.content.pm.PackageManager r0 = r0.getPackageManager()     // Catch:{ Exception -> 0x004b }
            r3 = 128(0x80, float:1.794E-43)
            android.content.pm.ApplicationInfo r0 = r0.getApplicationInfo(r2, r3)     // Catch:{ Exception -> 0x004b }
            android.os.Bundle r0 = r0.metaData     // Catch:{ Exception -> 0x004b }
            if (r0 == 0) goto L_0x0068
            java.lang.String r2 = "com.crashlytics.ApiKey"
            java.lang.String r0 = r0.getString(r2)     // Catch:{ Exception -> 0x004b }
        L_0x001d:
            boolean r2 = com.crashlytics.android.internal.C0143ab.e((java.lang.String) r0)
            if (r2 == 0) goto L_0x0035
            java.lang.String r2 = "com.crashlytics.ApiKey"
            java.lang.String r3 = "string"
            int r2 = com.crashlytics.android.internal.C0143ab.a((android.content.Context) r6, (java.lang.String) r2, (java.lang.String) r3)
            if (r2 == 0) goto L_0x0035
            android.content.res.Resources r0 = r6.getResources()
            java.lang.String r0 = r0.getString(r2)
        L_0x0035:
            boolean r2 = com.crashlytics.android.internal.C0143ab.e((java.lang.String) r0)
            if (r2 == 0) goto L_0x0079
            if (r7 != 0) goto L_0x0043
            boolean r2 = com.crashlytics.android.internal.C0143ab.f(r6)
            if (r2 == 0) goto L_0x006a
        L_0x0043:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Crashlytics could not be initialized, API key missing from AndroidManifest.xml. Add the following tag to your Application element \n\t<meta-data android:name=\"com.crashlytics.ApiKey\" android:value=\"YOUR_API_KEY\"/>"
            r0.<init>(r1)
            throw r0
        L_0x004b:
            r0 = move-exception
            com.crashlytics.android.internal.v r2 = com.crashlytics.android.internal.C0191y.a
            com.crashlytics.android.internal.q r2 = r2.b()
            java.lang.String r3 = "Crashlytics"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Caught non-fatal exception while retrieving apiKey: "
            r4.<init>(r5)
            java.lang.StringBuilder r0 = r4.append(r0)
            java.lang.String r0 = r0.toString()
            r2.a(r3, r0)
        L_0x0068:
            r0 = r1
            goto L_0x001d
        L_0x006a:
            com.crashlytics.android.internal.v r2 = com.crashlytics.android.internal.C0191y.a
            com.crashlytics.android.internal.q r2 = r2.b()
            java.lang.String r3 = "Crashlytics"
            java.lang.String r4 = "Crashlytics could not be initialized, API key missing from AndroidManifest.xml. Add the following tag to your Application element \n\t<meta-data android:name=\"com.crashlytics.ApiKey\" android:value=\"YOUR_API_KEY\"/>"
            r2.a((java.lang.String) r3, (java.lang.String) r4, (java.lang.Throwable) r1)
        L_0x0079:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crashlytics.android.internal.C0184r.a(android.content.Context, boolean):java.lang.String");
    }

    public static int a(int i) {
        if (i >= 200 && i <= 299) {
            return 0;
        }
        if (i >= 300 && i <= 399) {
            return 1;
        }
        if (i >= 400 && i <= 499) {
            return 0;
        }
        if (i >= 500) {
            return 1;
        }
        return 1;
    }

    public final void a(String str, String str2, Throwable th) {
        if (b(6)) {
            Log.e(str, str2, th);
        }
    }

    public final void a(String str, String str2) {
        if (b(3)) {
            Log.d(str, str2, (Throwable) null);
        }
    }

    public final void b(String str, String str2) {
        if (b(4)) {
            Log.i(str, str2, (Throwable) null);
        }
    }

    public final void c(String str, String str2) {
        if (b(5)) {
            Log.w(str, str2, (Throwable) null);
        }
    }

    public final void d(String str, String str2) {
        a(str, str2, (Throwable) null);
    }

    public final void a(int i, String str, String str2) {
        a(i, str, str2, false);
    }

    public final void a(int i, String str, String str2, boolean z) {
        if (z || b(i)) {
            Log.println(i, str, str2);
        }
    }

    static /* synthetic */ Activity a(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        return null;
    }

    static /* synthetic */ Application b(Context context) {
        if (context instanceof Application) {
            return (Application) context;
        }
        if (context instanceof Activity) {
            return ((Activity) context).getApplication();
        }
        if (context instanceof Service) {
            return ((Service) context).getApplication();
        }
        if (context.getApplicationContext() instanceof Application) {
            return (Application) context.getApplicationContext();
        }
        return null;
    }
}
