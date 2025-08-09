package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.ads.AdRequest;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;

public final class cb<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> implements MediationBannerListener, MediationInterstitialListener {
    /* access modifiers changed from: private */
    public final bw nR;

    public cb(bw bwVar) {
        this.nR = bwVar;
    }

    public void onClick(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        ev.z("Adapter called onClick.");
        if (!eu.bR()) {
            ev.D("onClick must be called on the main UI thread.");
            eu.ss.post(new Runnable() {
                public void run() {
                    try {
                        cb.this.nR.onAdClicked();
                    } catch (RemoteException e) {
                        ev.c("Could not call onAdClicked.", e);
                    }
                }
            });
            return;
        }
        try {
            this.nR.onAdClicked();
        } catch (RemoteException e) {
            ev.c("Could not call onAdClicked.", e);
        }
    }

    public void onDismissScreen(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        ev.z("Adapter called onDismissScreen.");
        if (!eu.bR()) {
            ev.D("onDismissScreen must be called on the main UI thread.");
            eu.ss.post(new Runnable() {
                public void run() {
                    try {
                        cb.this.nR.onAdClosed();
                    } catch (RemoteException e) {
                        ev.c("Could not call onAdClosed.", e);
                    }
                }
            });
            return;
        }
        try {
            this.nR.onAdClosed();
        } catch (RemoteException e) {
            ev.c("Could not call onAdClosed.", e);
        }
    }

    public void onDismissScreen(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        ev.z("Adapter called onDismissScreen.");
        if (!eu.bR()) {
            ev.D("onDismissScreen must be called on the main UI thread.");
            eu.ss.post(new Runnable() {
                public void run() {
                    try {
                        cb.this.nR.onAdClosed();
                    } catch (RemoteException e) {
                        ev.c("Could not call onAdClosed.", e);
                    }
                }
            });
            return;
        }
        try {
            this.nR.onAdClosed();
        } catch (RemoteException e) {
            ev.c("Could not call onAdClosed.", e);
        }
    }

    public void onFailedToReceiveAd(MediationBannerAdapter<?, ?> mediationBannerAdapter, final AdRequest.ErrorCode errorCode) {
        ev.z("Adapter called onFailedToReceiveAd with error. " + errorCode);
        if (!eu.bR()) {
            ev.D("onFailedToReceiveAd must be called on the main UI thread.");
            eu.ss.post(new Runnable() {
                public void run() {
                    try {
                        cb.this.nR.onAdFailedToLoad(cc.a(errorCode));
                    } catch (RemoteException e) {
                        ev.c("Could not call onAdFailedToLoad.", e);
                    }
                }
            });
            return;
        }
        try {
            this.nR.onAdFailedToLoad(cc.a(errorCode));
        } catch (RemoteException e) {
            ev.c("Could not call onAdFailedToLoad.", e);
        }
    }

    public void onFailedToReceiveAd(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter, final AdRequest.ErrorCode errorCode) {
        ev.z("Adapter called onFailedToReceiveAd with error " + errorCode + ".");
        if (!eu.bR()) {
            ev.D("onFailedToReceiveAd must be called on the main UI thread.");
            eu.ss.post(new Runnable() {
                public void run() {
                    try {
                        cb.this.nR.onAdFailedToLoad(cc.a(errorCode));
                    } catch (RemoteException e) {
                        ev.c("Could not call onAdFailedToLoad.", e);
                    }
                }
            });
            return;
        }
        try {
            this.nR.onAdFailedToLoad(cc.a(errorCode));
        } catch (RemoteException e) {
            ev.c("Could not call onAdFailedToLoad.", e);
        }
    }

    public void onLeaveApplication(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        ev.z("Adapter called onLeaveApplication.");
        if (!eu.bR()) {
            ev.D("onLeaveApplication must be called on the main UI thread.");
            eu.ss.post(new Runnable() {
                public void run() {
                    try {
                        cb.this.nR.onAdLeftApplication();
                    } catch (RemoteException e) {
                        ev.c("Could not call onAdLeftApplication.", e);
                    }
                }
            });
            return;
        }
        try {
            this.nR.onAdLeftApplication();
        } catch (RemoteException e) {
            ev.c("Could not call onAdLeftApplication.", e);
        }
    }

    public void onLeaveApplication(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        ev.z("Adapter called onLeaveApplication.");
        if (!eu.bR()) {
            ev.D("onLeaveApplication must be called on the main UI thread.");
            eu.ss.post(new Runnable() {
                public void run() {
                    try {
                        cb.this.nR.onAdLeftApplication();
                    } catch (RemoteException e) {
                        ev.c("Could not call onAdLeftApplication.", e);
                    }
                }
            });
            return;
        }
        try {
            this.nR.onAdLeftApplication();
        } catch (RemoteException e) {
            ev.c("Could not call onAdLeftApplication.", e);
        }
    }

    public void onPresentScreen(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        ev.z("Adapter called onPresentScreen.");
        if (!eu.bR()) {
            ev.D("onPresentScreen must be called on the main UI thread.");
            eu.ss.post(new Runnable() {
                public void run() {
                    try {
                        cb.this.nR.onAdOpened();
                    } catch (RemoteException e) {
                        ev.c("Could not call onAdOpened.", e);
                    }
                }
            });
            return;
        }
        try {
            this.nR.onAdOpened();
        } catch (RemoteException e) {
            ev.c("Could not call onAdOpened.", e);
        }
    }

    public void onPresentScreen(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        ev.z("Adapter called onPresentScreen.");
        if (!eu.bR()) {
            ev.D("onPresentScreen must be called on the main UI thread.");
            eu.ss.post(new Runnable() {
                public void run() {
                    try {
                        cb.this.nR.onAdOpened();
                    } catch (RemoteException e) {
                        ev.c("Could not call onAdOpened.", e);
                    }
                }
            });
            return;
        }
        try {
            this.nR.onAdOpened();
        } catch (RemoteException e) {
            ev.c("Could not call onAdOpened.", e);
        }
    }

    public void onReceivedAd(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        ev.z("Adapter called onReceivedAd.");
        if (!eu.bR()) {
            ev.D("onReceivedAd must be called on the main UI thread.");
            eu.ss.post(new Runnable() {
                public void run() {
                    try {
                        cb.this.nR.onAdLoaded();
                    } catch (RemoteException e) {
                        ev.c("Could not call onAdLoaded.", e);
                    }
                }
            });
            return;
        }
        try {
            this.nR.onAdLoaded();
        } catch (RemoteException e) {
            ev.c("Could not call onAdLoaded.", e);
        }
    }

    public void onReceivedAd(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        ev.z("Adapter called onReceivedAd.");
        if (!eu.bR()) {
            ev.D("onReceivedAd must be called on the main UI thread.");
            eu.ss.post(new Runnable() {
                public void run() {
                    try {
                        cb.this.nR.onAdLoaded();
                    } catch (RemoteException e) {
                        ev.c("Could not call onAdLoaded.", e);
                    }
                }
            });
            return;
        }
        try {
            this.nR.onAdLoaded();
        } catch (RemoteException e) {
            ev.c("Could not call onAdLoaded.", e);
        }
    }
}
