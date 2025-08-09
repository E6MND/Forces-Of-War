package com.crashlytics.android;

import com.crashlytics.android.internal.aU;
import com.crashlytics.android.internal.aX;

/* renamed from: com.crashlytics.android.l  reason: case insensitive filesystem */
final class C0195l implements aU<Boolean> {
    private /* synthetic */ Crashlytics a;

    C0195l(Crashlytics crashlytics) {
        this.a = crashlytics;
    }

    public final /* synthetic */ Object a(aX aXVar) {
        boolean z = false;
        if (!aXVar.d.a) {
            return false;
        }
        Crashlytics crashlytics = this.a;
        if (!Crashlytics.k()) {
            z = true;
        }
        return Boolean.valueOf(z);
    }
}
