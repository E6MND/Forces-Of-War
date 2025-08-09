package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;

public final class aw {
    private AdListener lO;
    private PlayStorePurchaseListener mA;
    private final Context mContext;
    private AppEventListener mf;
    private String mh;
    private final bt mu;
    private final al mv;
    private ar mw;
    private String mx;
    private InAppPurchaseListener mz;

    public aw(Context context) {
        this(context, al.aA());
    }

    public aw(Context context, al alVar) {
        this.mu = new bt();
        this.mContext = context;
        this.mv = alVar;
    }

    private void k(String str) throws RemoteException {
        if (this.mh == null) {
            l(str);
        }
        this.mw = ai.a(this.mContext, new am(), this.mh, this.mu);
        if (this.lO != null) {
            this.mw.a((aq) new ah(this.lO));
        }
        if (this.mf != null) {
            this.mw.a((at) new ao(this.mf));
        }
        if (this.mz != null) {
            this.mw.a((dd) new di(this.mz));
        }
        if (this.mA != null) {
            this.mw.a(new dm(this.mA), this.mx);
        }
    }

    private void l(String str) {
        if (this.mw == null) {
            throw new IllegalStateException("The ad unit ID must be set on InterstitialAd before " + str + " is called.");
        }
    }

    public void a(au auVar) {
        try {
            if (this.mw == null) {
                k("loadAd");
            }
            if (this.mw.a(this.mv.a(this.mContext, auVar))) {
                this.mu.c(auVar.aD());
            }
        } catch (RemoteException e) {
            ev.c("Failed to load ad.", e);
        }
    }

    public AdListener getAdListener() {
        return this.lO;
    }

    public String getAdUnitId() {
        return this.mh;
    }

    public AppEventListener getAppEventListener() {
        return this.mf;
    }

    public InAppPurchaseListener getInAppPurchaseListener() {
        return this.mz;
    }

    public boolean isLoaded() {
        try {
            if (this.mw == null) {
                return false;
            }
            return this.mw.isReady();
        } catch (RemoteException e) {
            ev.c("Failed to check if ad is ready.", e);
            return false;
        }
    }

    public void setAdListener(AdListener adListener) {
        try {
            this.lO = adListener;
            if (this.mw != null) {
                this.mw.a((aq) adListener != null ? new ah(adListener) : null);
            }
        } catch (RemoteException e) {
            ev.c("Failed to set the AdListener.", e);
        }
    }

    public void setAdUnitId(String adUnitId) {
        if (this.mh != null) {
            throw new IllegalStateException("The ad unit ID can only be set once on InterstitialAd.");
        }
        this.mh = adUnitId;
    }

    public void setAppEventListener(AppEventListener appEventListener) {
        try {
            this.mf = appEventListener;
            if (this.mw != null) {
                this.mw.a((at) appEventListener != null ? new ao(appEventListener) : null);
            }
        } catch (RemoteException e) {
            ev.c("Failed to set the AppEventListener.", e);
        }
    }

    public void setInAppPurchaseListener(InAppPurchaseListener inAppPurchaseListener) {
        if (this.mA != null) {
            throw new IllegalStateException("Play store purchase parameter has already been set.");
        }
        try {
            this.mz = inAppPurchaseListener;
            if (this.mw != null) {
                this.mw.a((dd) inAppPurchaseListener != null ? new di(inAppPurchaseListener) : null);
            }
        } catch (RemoteException e) {
            ev.c("Failed to set the InAppPurchaseListener.", e);
        }
    }

    public void setPlayStorePurchaseParams(PlayStorePurchaseListener playStorePurchaseListener, String publicKey) {
        try {
            this.mA = playStorePurchaseListener;
            if (this.mw != null) {
                this.mw.a(playStorePurchaseListener != null ? new dm(playStorePurchaseListener) : null, publicKey);
            }
        } catch (RemoteException e) {
            ev.c("Failed to set the play store purchase parameter.", e);
        }
    }

    public void show() {
        try {
            l("show");
            this.mw.showInterstitial();
        } catch (RemoteException e) {
            ev.c("Failed to show interstitial.", e);
        }
    }
}
