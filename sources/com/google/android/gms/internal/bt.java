package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.internal.bu;
import java.util.Map;

public final class bt extends bu.a {
    private Map<Class<? extends NetworkExtras>, NetworkExtras> nO;

    private <NETWORK_EXTRAS extends com.google.ads.mediation.NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> bv n(String str) throws RemoteException {
        try {
            Class<?> cls = Class.forName(str, false, bt.class.getClassLoader());
            if (MediationAdapter.class.isAssignableFrom(cls)) {
                MediationAdapter mediationAdapter = (MediationAdapter) cls.newInstance();
                return new ca(mediationAdapter, (com.google.ads.mediation.NetworkExtras) this.nO.get(mediationAdapter.getAdditionalParametersType()));
            } else if (com.google.android.gms.ads.mediation.MediationAdapter.class.isAssignableFrom(cls)) {
                return new by((com.google.android.gms.ads.mediation.MediationAdapter) cls.newInstance());
            } else {
                ev.D("Could not instantiate mediation adapter: " + str + " (not a valid adapter).");
                throw new RemoteException();
            }
        } catch (Throwable th) {
            ev.D("Could not instantiate mediation adapter: " + str + ". " + th.getMessage());
            throw new RemoteException();
        }
    }

    public void c(Map<Class<? extends NetworkExtras>, NetworkExtras> map) {
        this.nO = map;
    }

    public bv m(String str) throws RemoteException {
        return n(str);
    }
}
