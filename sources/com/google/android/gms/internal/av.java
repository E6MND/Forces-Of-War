package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.dynamic.e;

public final class av {
    private AdListener lO;
    private PlayStorePurchaseListener mA;
    private AppEventListener mf;
    private AdSize[] mg;
    private String mh;
    private final bt mu;
    private final al mv;
    private ar mw;
    private String mx;
    private ViewGroup my;
    private InAppPurchaseListener mz;

    public av(ViewGroup viewGroup) {
        this(viewGroup, (AttributeSet) null, false, al.aA());
    }

    public av(ViewGroup viewGroup, AttributeSet attributeSet, boolean z) {
        this(viewGroup, attributeSet, z, al.aA());
    }

    av(ViewGroup viewGroup, AttributeSet attributeSet, boolean z, al alVar) {
        this.mu = new bt();
        this.my = viewGroup;
        this.mv = alVar;
        if (attributeSet != null) {
            Context context = viewGroup.getContext();
            try {
                ap apVar = new ap(context, attributeSet);
                this.mg = apVar.f(z);
                this.mh = apVar.getAdUnitId();
                if (viewGroup.isInEditMode()) {
                    eu.a(viewGroup, new am(context, this.mg[0]), "Ads by Google");
                }
            } catch (IllegalArgumentException e) {
                eu.a(viewGroup, new am(context, AdSize.BANNER), e.getMessage(), e.getMessage());
            }
        }
    }

    private void aG() {
        try {
            d P = this.mw.P();
            if (P != null) {
                this.my.addView((View) e.e(P));
            }
        } catch (RemoteException e) {
            ev.c("Failed to get an ad frame.", e);
        }
    }

    private void aH() throws RemoteException {
        if ((this.mg == null || this.mh == null) && this.mw == null) {
            throw new IllegalStateException("The ad size and ad unit ID must be set before loadAd is called.");
        }
        Context context = this.my.getContext();
        this.mw = ai.a(context, new am(context, this.mg), this.mh, this.mu);
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
        aG();
    }

    public void a(au auVar) {
        try {
            if (this.mw == null) {
                aH();
            }
            if (this.mw.a(this.mv.a(this.my.getContext(), auVar))) {
                this.mu.c(auVar.aD());
            }
        } catch (RemoteException e) {
            ev.c("Failed to load ad.", e);
        }
    }

    public void a(AdSize... adSizeArr) {
        this.mg = adSizeArr;
        try {
            if (this.mw != null) {
                this.mw.a(new am(this.my.getContext(), this.mg));
            }
        } catch (RemoteException e) {
            ev.c("Failed to set the ad size.", e);
        }
        this.my.requestLayout();
    }

    public void destroy() {
        try {
            if (this.mw != null) {
                this.mw.destroy();
            }
        } catch (RemoteException e) {
            ev.c("Failed to destroy AdView.", e);
        }
    }

    public AdListener getAdListener() {
        return this.lO;
    }

    public AdSize getAdSize() {
        try {
            if (this.mw != null) {
                return this.mw.Q().aB();
            }
        } catch (RemoteException e) {
            ev.c("Failed to get the current AdSize.", e);
        }
        if (this.mg != null) {
            return this.mg[0];
        }
        return null;
    }

    public AdSize[] getAdSizes() {
        return this.mg;
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

    public void pause() {
        try {
            if (this.mw != null) {
                this.mw.pause();
            }
        } catch (RemoteException e) {
            ev.c("Failed to call pause.", e);
        }
    }

    public void recordManualImpression() {
        try {
            this.mw.ab();
        } catch (RemoteException e) {
            ev.c("Failed to record impression.", e);
        }
    }

    public void resume() {
        try {
            if (this.mw != null) {
                this.mw.resume();
            }
        } catch (RemoteException e) {
            ev.c("Failed to call resume.", e);
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

    public void setAdSizes(AdSize... adSizes) {
        if (this.mg != null) {
            throw new IllegalStateException("The ad size can only be set once on AdView.");
        }
        a(adSizes);
    }

    public void setAdUnitId(String adUnitId) {
        if (this.mh != null) {
            throw new IllegalStateException("The ad unit ID can only be set once on AdView.");
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
        if (this.mz != null) {
            throw new IllegalStateException("InAppPurchaseListener has already been set.");
        }
        try {
            this.mA = playStorePurchaseListener;
            this.mx = publicKey;
            if (this.mw != null) {
                this.mw.a(playStorePurchaseListener != null ? new dm(playStorePurchaseListener) : null, publicKey);
            }
        } catch (RemoteException e) {
            ev.c("Failed to set the play store purchase parameter.", e);
        }
    }
}
