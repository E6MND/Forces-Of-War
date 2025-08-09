package com.google.android.gms.ads.mediation.customevent;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.internal.ev;

public final class CustomEventAdapter implements MediationBannerAdapter, MediationInterstitialAdapter {
    private View n;
    private CustomEventBanner sT;
    private CustomEventInterstitial sU;

    private static final class a implements CustomEventBannerListener {
        private final MediationBannerListener l;
        private final CustomEventAdapter sV;

        public a(CustomEventAdapter customEventAdapter, MediationBannerListener mediationBannerListener) {
            this.sV = customEventAdapter;
            this.l = mediationBannerListener;
        }

        public void onAdClicked() {
            ev.z("Custom event adapter called onAdClicked.");
            this.l.onAdClicked(this.sV);
        }

        public void onAdClosed() {
            ev.z("Custom event adapter called onAdClosed.");
            this.l.onAdClosed(this.sV);
        }

        public void onAdFailedToLoad(int errorCode) {
            ev.z("Custom event adapter called onAdFailedToLoad.");
            this.l.onAdFailedToLoad(this.sV, errorCode);
        }

        public void onAdLeftApplication() {
            ev.z("Custom event adapter called onAdLeftApplication.");
            this.l.onAdLeftApplication(this.sV);
        }

        public void onAdLoaded(View view) {
            ev.z("Custom event adapter called onAdLoaded.");
            this.sV.a(view);
            this.l.onAdLoaded(this.sV);
        }

        public void onAdOpened() {
            ev.z("Custom event adapter called onAdOpened.");
            this.l.onAdOpened(this.sV);
        }
    }

    private class b implements CustomEventInterstitialListener {
        private final MediationInterstitialListener m;
        private final CustomEventAdapter sV;

        public b(CustomEventAdapter customEventAdapter, MediationInterstitialListener mediationInterstitialListener) {
            this.sV = customEventAdapter;
            this.m = mediationInterstitialListener;
        }

        public void onAdClicked() {
            ev.z("Custom event adapter called onAdClicked.");
            this.m.onAdClicked(this.sV);
        }

        public void onAdClosed() {
            ev.z("Custom event adapter called onAdClosed.");
            this.m.onAdClosed(this.sV);
        }

        public void onAdFailedToLoad(int errorCode) {
            ev.z("Custom event adapter called onFailedToReceiveAd.");
            this.m.onAdFailedToLoad(this.sV, errorCode);
        }

        public void onAdLeftApplication() {
            ev.z("Custom event adapter called onAdLeftApplication.");
            this.m.onAdLeftApplication(this.sV);
        }

        public void onAdLoaded() {
            ev.z("Custom event adapter called onReceivedAd.");
            this.m.onAdLoaded(CustomEventAdapter.this);
        }

        public void onAdOpened() {
            ev.z("Custom event adapter called onAdOpened.");
            this.m.onAdOpened(this.sV);
        }
    }

    private static <T> T a(String str) {
        try {
            return Class.forName(str).newInstance();
        } catch (Throwable th) {
            ev.D("Could not instantiate custom event adapter: " + str + ". " + th.getMessage());
            return null;
        }
    }

    /* access modifiers changed from: private */
    public void a(View view) {
        this.n = view;
    }

    public View getBannerView() {
        return this.n;
    }

    public void onDestroy() {
        if (this.sT != null) {
            this.sT.onDestroy();
        }
        if (this.sU != null) {
            this.sU.onDestroy();
        }
    }

    public void onPause() {
        if (this.sT != null) {
            this.sT.onPause();
        }
        if (this.sU != null) {
            this.sU.onPause();
        }
    }

    public void onResume() {
        if (this.sT != null) {
            this.sT.onResume();
        }
        if (this.sU != null) {
            this.sU.onResume();
        }
    }

    public void requestBannerAd(Context context, MediationBannerListener listener, Bundle serverParameters, AdSize adSize, MediationAdRequest mediationAdRequest, Bundle customEventExtras) {
        this.sT = (CustomEventBanner) a(serverParameters.getString("class_name"));
        if (this.sT == null) {
            listener.onAdFailedToLoad(this, 0);
        } else {
            this.sT.requestBannerAd(context, new a(this, listener), serverParameters.getString("parameter"), adSize, mediationAdRequest, customEventExtras == null ? null : customEventExtras.getBundle(serverParameters.getString("class_name")));
        }
    }

    public void requestInterstitialAd(Context context, MediationInterstitialListener listener, Bundle serverParameters, MediationAdRequest mediationAdRequest, Bundle customEventExtras) {
        this.sU = (CustomEventInterstitial) a(serverParameters.getString("class_name"));
        if (this.sU == null) {
            listener.onAdFailedToLoad(this, 0);
        } else {
            this.sU.requestInterstitialAd(context, new b(this, listener), serverParameters.getString("parameter"), mediationAdRequest, customEventExtras == null ? null : customEventExtras.getBundle(serverParameters.getString("class_name")));
        }
    }

    public void showInterstitial() {
        this.sU.showInterstitial();
    }
}
