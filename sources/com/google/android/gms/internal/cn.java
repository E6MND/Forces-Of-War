package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.dynamic.g;
import com.google.android.gms.internal.co;
import com.google.android.gms.internal.cp;

public final class cn extends g<cp> {
    private static final cn oQ = new cn();

    private static final class a extends Exception {
        public a(String str) {
            super(str);
        }
    }

    private cn() {
        super("com.google.android.gms.ads.AdOverlayCreatorImpl");
    }

    public static co a(Activity activity) {
        try {
            if (!b(activity)) {
                return oQ.c(activity);
            }
            ev.z("Using AdOverlay from the client jar.");
            return new cg(activity);
        } catch (a e) {
            ev.D(e.getMessage());
            return null;
        }
    }

    private static boolean b(Activity activity) throws a {
        Intent intent = activity.getIntent();
        if (intent.hasExtra("com.google.android.gms.ads.internal.overlay.useClientJar")) {
            return intent.getBooleanExtra("com.google.android.gms.ads.internal.overlay.useClientJar", false);
        }
        throw new a("Ad overlay requires the useClientJar flag in intent extras.");
    }

    private co c(Activity activity) {
        try {
            return co.a.m(((cp) D(activity)).a(e.h(activity)));
        } catch (RemoteException e) {
            ev.c("Could not create remote AdOverlay.", e);
            return null;
        } catch (g.a e2) {
            ev.c("Could not create remote AdOverlay.", e2);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: l */
    public cp d(IBinder iBinder) {
        return cp.a.n(iBinder);
    }
}
