package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.dynamic.g;
import com.google.android.gms.internal.de;
import com.google.android.gms.internal.df;

public final class dj extends g<df> {
    private static final dj pt = new dj();

    private static final class a extends Exception {
        public a(String str) {
            super(str);
        }
    }

    private dj() {
        super("com.google.android.gms.ads.InAppPurchaseManagerCreatorImpl");
    }

    private static boolean b(Activity activity) throws a {
        Intent intent = activity.getIntent();
        if (intent.hasExtra("com.google.android.gms.ads.internal.purchase.useClientJar")) {
            return intent.getBooleanExtra("com.google.android.gms.ads.internal.purchase.useClientJar", false);
        }
        throw new a("InAppPurchaseManager requires the useClientJar flag in intent extras.");
    }

    public static de d(Activity activity) {
        try {
            if (!b(activity)) {
                return pt.e(activity);
            }
            ev.z("Using AdOverlay from the client jar.");
            return new cv(activity);
        } catch (a e) {
            ev.D(e.getMessage());
            return null;
        }
    }

    private de e(Activity activity) {
        try {
            return de.a.r(((df) D(activity)).b(e.h(activity)));
        } catch (RemoteException e) {
            ev.c("Could not create remote InAppPurchaseManager.", e);
            return null;
        } catch (g.a e2) {
            ev.c("Could not create remote InAppPurchaseManager.", e2);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: v */
    public df d(IBinder iBinder) {
        return df.a.s(iBinder);
    }
}
