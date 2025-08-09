package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.internal.dr;

public final class dq {

    public interface a {
        void a(dv dvVar);
    }

    public static en a(Context context, dt dtVar, a aVar) {
        return dtVar.kO.sw ? b(context, dtVar, aVar) : c(context, dtVar, aVar);
    }

    private static en b(Context context, dt dtVar, a aVar) {
        ev.z("Fetching ad response from local ad request service.");
        dr.a aVar2 = new dr.a(context, dtVar, aVar);
        aVar2.start();
        return aVar2;
    }

    private static en c(Context context, dt dtVar, a aVar) {
        ev.z("Fetching ad response from remote ad request service.");
        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(context) == 0) {
            return new dr.b(context, dtVar, aVar);
        }
        ev.D("Failed to connect to remote ad request service.");
        return null;
    }
}
