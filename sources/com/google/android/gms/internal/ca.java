package com.google.android.gms.internal;

import android.app.Activity;
import android.os.RemoteException;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.internal.bv;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;

public final class ca<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> extends bv.a {
    private final MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> nS;
    private final NETWORK_EXTRAS nT;

    public ca(MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> mediationAdapter, NETWORK_EXTRAS network_extras) {
        this.nS = mediationAdapter;
        this.nT = network_extras;
    }

    private SERVER_PARAMETERS b(String str, int i, String str2) throws RemoteException {
        HashMap hashMap;
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                hashMap = new HashMap(jSONObject.length());
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    hashMap.put(next, jSONObject.getString(next));
                }
            } catch (Throwable th) {
                ev.c("Could not get MediationServerParameters.", th);
                throw new RemoteException();
            }
        } else {
            hashMap = new HashMap(0);
        }
        Class<SERVER_PARAMETERS> serverParametersType = this.nS.getServerParametersType();
        if (serverParametersType == null) {
            return null;
        }
        SERVER_PARAMETERS server_parameters = (MediationServerParameters) serverParametersType.newInstance();
        server_parameters.load(hashMap);
        return server_parameters;
    }

    public void a(d dVar, aj ajVar, String str, bw bwVar) throws RemoteException {
        a(dVar, ajVar, str, (String) null, bwVar);
    }

    public void a(d dVar, aj ajVar, String str, String str2, bw bwVar) throws RemoteException {
        if (!(this.nS instanceof MediationInterstitialAdapter)) {
            ev.D("MediationAdapter is not a MediationInterstitialAdapter: " + this.nS.getClass().getCanonicalName());
            throw new RemoteException();
        }
        ev.z("Requesting interstitial ad from adapter.");
        try {
            ((MediationInterstitialAdapter) this.nS).requestInterstitialAd(new cb(bwVar), (Activity) e.e(dVar), b(str, ajVar.lU, str2), cc.e(ajVar), this.nT);
        } catch (Throwable th) {
            ev.c("Could not request interstitial ad from adapter.", th);
            throw new RemoteException();
        }
    }

    public void a(d dVar, am amVar, aj ajVar, String str, bw bwVar) throws RemoteException {
        a(dVar, amVar, ajVar, str, (String) null, bwVar);
    }

    public void a(d dVar, am amVar, aj ajVar, String str, String str2, bw bwVar) throws RemoteException {
        if (!(this.nS instanceof MediationBannerAdapter)) {
            ev.D("MediationAdapter is not a MediationBannerAdapter: " + this.nS.getClass().getCanonicalName());
            throw new RemoteException();
        }
        ev.z("Requesting banner ad from adapter.");
        try {
            ((MediationBannerAdapter) this.nS).requestBannerAd(new cb(bwVar), (Activity) e.e(dVar), b(str, ajVar.lU, str2), cc.b(amVar), cc.e(ajVar), this.nT);
        } catch (Throwable th) {
            ev.c("Could not request banner ad from adapter.", th);
            throw new RemoteException();
        }
    }

    public void destroy() throws RemoteException {
        try {
            this.nS.destroy();
        } catch (Throwable th) {
            ev.c("Could not destroy adapter.", th);
            throw new RemoteException();
        }
    }

    public d getView() throws RemoteException {
        if (!(this.nS instanceof MediationBannerAdapter)) {
            ev.D("MediationAdapter is not a MediationBannerAdapter: " + this.nS.getClass().getCanonicalName());
            throw new RemoteException();
        }
        try {
            return e.h(((MediationBannerAdapter) this.nS).getBannerView());
        } catch (Throwable th) {
            ev.c("Could not get banner view from adapter.", th);
            throw new RemoteException();
        }
    }

    public void pause() throws RemoteException {
        throw new RemoteException();
    }

    public void resume() throws RemoteException {
        throw new RemoteException();
    }

    public void showInterstitial() throws RemoteException {
        if (!(this.nS instanceof MediationInterstitialAdapter)) {
            ev.D("MediationAdapter is not a MediationInterstitialAdapter: " + this.nS.getClass().getCanonicalName());
            throw new RemoteException();
        }
        ev.z("Showing interstitial from adapter.");
        try {
            ((MediationInterstitialAdapter) this.nS).showInterstitial();
        } catch (Throwable th) {
            ev.c("Could not show interstitial from adapter.", th);
            throw new RemoteException();
        }
    }
}
