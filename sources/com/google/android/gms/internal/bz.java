package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;

public final class bz implements MediationBannerListener, MediationInterstitialListener {
    private final bw nR;

    public bz(bw bwVar) {
        this.nR = bwVar;
    }

    public void onAdClicked(MediationBannerAdapter adapter) {
        hn.ay("onAdClicked must be called on the main UI thread.");
        ev.z("Adapter called onAdClicked.");
        try {
            this.nR.onAdClicked();
        } catch (RemoteException e) {
            ev.c("Could not call onAdClicked.", e);
        }
    }

    public void onAdClicked(MediationInterstitialAdapter adapter) {
        hn.ay("onAdClicked must be called on the main UI thread.");
        ev.z("Adapter called onAdClicked.");
        try {
            this.nR.onAdClicked();
        } catch (RemoteException e) {
            ev.c("Could not call onAdClicked.", e);
        }
    }

    public void onAdClosed(MediationBannerAdapter adapter) {
        hn.ay("onAdClosed must be called on the main UI thread.");
        ev.z("Adapter called onAdClosed.");
        try {
            this.nR.onAdClosed();
        } catch (RemoteException e) {
            ev.c("Could not call onAdClosed.", e);
        }
    }

    public void onAdClosed(MediationInterstitialAdapter adapter) {
        hn.ay("onAdClosed must be called on the main UI thread.");
        ev.z("Adapter called onAdClosed.");
        try {
            this.nR.onAdClosed();
        } catch (RemoteException e) {
            ev.c("Could not call onAdClosed.", e);
        }
    }

    public void onAdFailedToLoad(MediationBannerAdapter adapter, int errorCode) {
        hn.ay("onAdFailedToLoad must be called on the main UI thread.");
        ev.z("Adapter called onAdFailedToLoad with error. " + errorCode);
        try {
            this.nR.onAdFailedToLoad(errorCode);
        } catch (RemoteException e) {
            ev.c("Could not call onAdFailedToLoad.", e);
        }
    }

    public void onAdFailedToLoad(MediationInterstitialAdapter adapter, int errorCode) {
        hn.ay("onAdFailedToLoad must be called on the main UI thread.");
        ev.z("Adapter called onAdFailedToLoad with error " + errorCode + ".");
        try {
            this.nR.onAdFailedToLoad(errorCode);
        } catch (RemoteException e) {
            ev.c("Could not call onAdFailedToLoad.", e);
        }
    }

    public void onAdLeftApplication(MediationBannerAdapter adapter) {
        hn.ay("onAdLeftApplication must be called on the main UI thread.");
        ev.z("Adapter called onAdLeftApplication.");
        try {
            this.nR.onAdLeftApplication();
        } catch (RemoteException e) {
            ev.c("Could not call onAdLeftApplication.", e);
        }
    }

    public void onAdLeftApplication(MediationInterstitialAdapter adapter) {
        hn.ay("onAdLeftApplication must be called on the main UI thread.");
        ev.z("Adapter called onAdLeftApplication.");
        try {
            this.nR.onAdLeftApplication();
        } catch (RemoteException e) {
            ev.c("Could not call onAdLeftApplication.", e);
        }
    }

    public void onAdLoaded(MediationBannerAdapter adapter) {
        hn.ay("onAdLoaded must be called on the main UI thread.");
        ev.z("Adapter called onAdLoaded.");
        try {
            this.nR.onAdLoaded();
        } catch (RemoteException e) {
            ev.c("Could not call onAdLoaded.", e);
        }
    }

    public void onAdLoaded(MediationInterstitialAdapter adapter) {
        hn.ay("onAdLoaded must be called on the main UI thread.");
        ev.z("Adapter called onAdLoaded.");
        try {
            this.nR.onAdLoaded();
        } catch (RemoteException e) {
            ev.c("Could not call onAdLoaded.", e);
        }
    }

    public void onAdOpened(MediationBannerAdapter adapter) {
        hn.ay("onAdOpened must be called on the main UI thread.");
        ev.z("Adapter called onAdOpened.");
        try {
            this.nR.onAdOpened();
        } catch (RemoteException e) {
            ev.c("Could not call onAdOpened.", e);
        }
    }

    public void onAdOpened(MediationInterstitialAdapter adapter) {
        hn.ay("onAdOpened must be called on the main UI thread.");
        ev.z("Adapter called onAdOpened.");
        try {
            this.nR.onAdOpened();
        } catch (RemoteException e) {
            ev.c("Could not call onAdOpened.", e);
        }
    }
}
