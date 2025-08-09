package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.dynamic.g;
import com.google.android.gms.internal.ar;
import com.google.android.gms.internal.as;

public final class ai extends g<as> {
    private static final ai lP = new ai();

    private ai() {
        super("com.google.android.gms.ads.AdManagerCreatorImpl");
    }

    public static ar a(Context context, am amVar, String str, bt btVar) {
        ar b;
        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(context) == 0 && (b = lP.b(context, amVar, str, btVar)) != null) {
            return b;
        }
        ev.z("Using AdManager from the client jar.");
        return new v(context, amVar, str, btVar, new ew(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE, GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE, true));
    }

    private ar b(Context context, am amVar, String str, bt btVar) {
        try {
            return ar.a.f(((as) D(context)).a(e.h(context), amVar, str, btVar, GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE));
        } catch (RemoteException e) {
            ev.c("Could not create remote AdManager.", e);
            return null;
        } catch (g.a e2) {
            ev.c("Could not create remote AdManager.", e2);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public as d(IBinder iBinder) {
        return as.a.g(iBinder);
    }
}
