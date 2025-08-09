package com.crashlytics.android;

import android.app.Activity;
import com.crashlytics.android.internal.C0188v;
import com.crashlytics.android.internal.aU;
import com.crashlytics.android.internal.aX;

/* renamed from: com.crashlytics.android.m  reason: case insensitive filesystem */
final class C0196m implements aU<Boolean> {
    private /* synthetic */ Crashlytics a;

    C0196m(Crashlytics crashlytics) {
        this.a = crashlytics;
    }

    public final /* synthetic */ Object a(aX aXVar) {
        boolean z = true;
        Activity e = C0188v.a().e();
        if (e != null && !e.isFinishing() && this.a.j()) {
            z = Crashlytics.a(this.a, e, aXVar.c);
        }
        return Boolean.valueOf(z);
    }
}
