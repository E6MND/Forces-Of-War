package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.view.View;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.dynamic.g;
import com.google.android.gms.internal.hk;

public final class ho extends g<hk> {
    private static final ho GI = new ho();

    private ho() {
        super("com.google.android.gms.common.ui.SignInButtonCreatorImpl");
    }

    public static View b(Context context, int i, int i2) throws g.a {
        return GI.c(context, i, i2);
    }

    private View c(Context context, int i, int i2) throws g.a {
        try {
            return (View) e.e(((hk) D(context)).a(e.h(context), i, i2));
        } catch (Exception e) {
            throw new g.a("Could not get button with size " + i + " and color " + i2, e);
        }
    }

    /* renamed from: N */
    public hk d(IBinder iBinder) {
        return hk.a.M(iBinder);
    }
}
