package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.a;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.internal.bv;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONObject;

public final class by extends bv.a {
    private final MediationAdapter nQ;

    public by(MediationAdapter mediationAdapter) {
        this.nQ = mediationAdapter;
    }

    private Bundle a(String str, int i, String str2) throws RemoteException {
        ev.D("Server parameters: " + str);
        try {
            Bundle bundle = new Bundle();
            if (str != null) {
                JSONObject jSONObject = new JSONObject(str);
                Bundle bundle2 = new Bundle();
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    bundle2.putString(next, jSONObject.getString(next));
                }
                bundle = bundle2;
            }
            if (this.nQ instanceof AdMobAdapter) {
                bundle.putString("adJson", str2);
                bundle.putInt("tagForChildDirectedTreatment", i);
            }
            return bundle;
        } catch (Throwable th) {
            ev.c("Could not get Server Parameters Bundle.", th);
            throw new RemoteException();
        }
    }

    public void a(d dVar, aj ajVar, String str, bw bwVar) throws RemoteException {
        a(dVar, ajVar, str, (String) null, bwVar);
    }

    public void a(d dVar, aj ajVar, String str, String str2, bw bwVar) throws RemoteException {
        Bundle bundle = null;
        if (!(this.nQ instanceof MediationInterstitialAdapter)) {
            ev.D("MediationAdapter is not a MediationInterstitialAdapter: " + this.nQ.getClass().getCanonicalName());
            throw new RemoteException();
        }
        ev.z("Requesting interstitial ad from adapter.");
        try {
            MediationInterstitialAdapter mediationInterstitialAdapter = (MediationInterstitialAdapter) this.nQ;
            bx bxVar = new bx(new Date(ajVar.lQ), ajVar.lR, ajVar.lS != null ? new HashSet(ajVar.lS) : null, ajVar.lT, ajVar.lU);
            if (ajVar.ma != null) {
                bundle = ajVar.ma.getBundle(mediationInterstitialAdapter.getClass().getName());
            }
            mediationInterstitialAdapter.requestInterstitialAd((Context) e.e(dVar), new bz(bwVar), a(str, ajVar.lU, str2), bxVar, bundle);
        } catch (Throwable th) {
            ev.c("Could not request interstitial ad from adapter.", th);
            throw new RemoteException();
        }
    }

    public void a(d dVar, am amVar, aj ajVar, String str, bw bwVar) throws RemoteException {
        a(dVar, amVar, ajVar, str, (String) null, bwVar);
    }

    public void a(d dVar, am amVar, aj ajVar, String str, String str2, bw bwVar) throws RemoteException {
        Bundle bundle = null;
        if (!(this.nQ instanceof MediationBannerAdapter)) {
            ev.D("MediationAdapter is not a MediationBannerAdapter: " + this.nQ.getClass().getCanonicalName());
            throw new RemoteException();
        }
        ev.z("Requesting banner ad from adapter.");
        try {
            MediationBannerAdapter mediationBannerAdapter = (MediationBannerAdapter) this.nQ;
            bx bxVar = new bx(new Date(ajVar.lQ), ajVar.lR, ajVar.lS != null ? new HashSet(ajVar.lS) : null, ajVar.lT, ajVar.lU);
            if (ajVar.ma != null) {
                bundle = ajVar.ma.getBundle(mediationBannerAdapter.getClass().getName());
            }
            mediationBannerAdapter.requestBannerAd((Context) e.e(dVar), new bz(bwVar), a(str, ajVar.lU, str2), a.a(amVar.width, amVar.height, amVar.mc), bxVar, bundle);
        } catch (Throwable th) {
            ev.c("Could not request banner ad from adapter.", th);
            throw new RemoteException();
        }
    }

    public void destroy() throws RemoteException {
        try {
            this.nQ.onDestroy();
        } catch (Throwable th) {
            ev.c("Could not destroy adapter.", th);
            throw new RemoteException();
        }
    }

    public d getView() throws RemoteException {
        if (!(this.nQ instanceof MediationBannerAdapter)) {
            ev.D("MediationAdapter is not a MediationBannerAdapter: " + this.nQ.getClass().getCanonicalName());
            throw new RemoteException();
        }
        try {
            return e.h(((MediationBannerAdapter) this.nQ).getBannerView());
        } catch (Throwable th) {
            ev.c("Could not get banner view from adapter.", th);
            throw new RemoteException();
        }
    }

    public void pause() throws RemoteException {
        try {
            this.nQ.onPause();
        } catch (Throwable th) {
            ev.c("Could not pause adapter.", th);
            throw new RemoteException();
        }
    }

    public void resume() throws RemoteException {
        try {
            this.nQ.onResume();
        } catch (Throwable th) {
            ev.c("Could not resume adapter.", th);
            throw new RemoteException();
        }
    }

    public void showInterstitial() throws RemoteException {
        if (!(this.nQ instanceof MediationInterstitialAdapter)) {
            ev.D("MediationAdapter is not a MediationInterstitialAdapter: " + this.nQ.getClass().getCanonicalName());
            throw new RemoteException();
        }
        ev.z("Showing interstitial from adapter.");
        try {
            ((MediationInterstitialAdapter) this.nQ).showInterstitial();
        } catch (Throwable th) {
            ev.c("Could not show interstitial from adapter.", th);
            throw new RemoteException();
        }
    }
}
