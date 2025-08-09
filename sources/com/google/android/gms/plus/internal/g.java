package com.google.android.gms.plus.internal;

import android.content.Context;
import android.os.IBinder;
import android.view.View;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.internal.hn;
import com.google.android.gms.plus.PlusOneDummyView;
import com.google.android.gms.plus.internal.c;

public final class g {
    private static Context aag;
    private static c abP;

    public static class a extends Exception {
        public a(String str) {
            super(str);
        }
    }

    private static c H(Context context) throws a {
        hn.f(context);
        if (abP == null) {
            if (aag == null) {
                aag = GooglePlayServicesUtil.getRemoteContext(context);
                if (aag == null) {
                    throw new a("Could not get remote context.");
                }
            }
            try {
                abP = c.a.bl((IBinder) aag.getClassLoader().loadClass("com.google.android.gms.plus.plusone.PlusOneButtonCreatorImpl").newInstance());
            } catch (ClassNotFoundException e) {
                throw new a("Could not load creator class.");
            } catch (InstantiationException e2) {
                throw new a("Could not instantiate creator.");
            } catch (IllegalAccessException e3) {
                throw new a("Could not access creator.");
            }
        }
        return abP;
    }

    public static View a(Context context, int i, int i2, String str, int i3) {
        if (str != null) {
            return (View) e.e(H(context).a(e.h(context), i, i2, str, i3));
        }
        try {
            throw new NullPointerException();
        } catch (Exception e) {
            return new PlusOneDummyView(context, i);
        }
    }
}
