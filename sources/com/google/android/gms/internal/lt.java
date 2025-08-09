package com.google.android.gms.internal;

import android.app.Activity;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.dynamic.c;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.dynamic.g;
import com.google.android.gms.internal.lo;
import com.google.android.gms.wallet.fragment.WalletFragmentOptions;

public class lt extends g<lo> {
    private static lt akK;

    protected lt() {
        super("com.google.android.gms.wallet.dynamite.WalletDynamiteCreatorImpl");
    }

    public static ll a(Activity activity, c cVar, WalletFragmentOptions walletFragmentOptions, lm lmVar) throws GooglePlayServicesNotAvailableException {
        int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity);
        if (isGooglePlayServicesAvailable != 0) {
            throw new GooglePlayServicesNotAvailableException(isGooglePlayServicesAvailable);
        }
        try {
            return ((lo) ne().D(activity)).a(e.h(activity), cVar, walletFragmentOptions, lmVar);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (g.a e2) {
            throw new RuntimeException(e2);
        }
    }

    private static lt ne() {
        if (akK == null) {
            akK = new lt();
        }
        return akK;
    }

    /* access modifiers changed from: protected */
    /* renamed from: bv */
    public lo d(IBinder iBinder) {
        return lo.a.br(iBinder);
    }
}
