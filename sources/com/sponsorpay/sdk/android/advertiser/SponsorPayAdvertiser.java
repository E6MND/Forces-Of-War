package com.sponsorpay.sdk.android.advertiser;

import android.content.Context;
import com.sponsorpay.sdk.android.HostInfo;
import com.sponsorpay.sdk.android.UrlBuilder;
import com.sponsorpay.sdk.android.advertiser.AdvertiserCallbackSender;
import java.util.HashMap;
import java.util.Map;

public class SponsorPayAdvertiser implements AdvertiserCallbackSender.APIResultListener {
    private static SponsorPayAdvertiser mInstance;
    private static Map<String, String> sCustomParameters;
    private static boolean sShouldUseStagingUrls = false;
    private AdvertiserCallbackSender mAPICaller;
    private Context mContext;
    private HostInfo mHostInfo;
    private SponsorPayAdvertiserState mPersistedState = new SponsorPayAdvertiserState(this.mContext);

    public static void setShouldUseStagingUrls(boolean value) {
        sShouldUseStagingUrls = value;
    }

    public static boolean shouldUseStagingUrls() {
        return sShouldUseStagingUrls;
    }

    public static void setCustomParameters(Map<String, String> params) {
        sCustomParameters = params;
    }

    public static void setCustomParameters(String[] keys, String[] values) {
        sCustomParameters = UrlBuilder.mapKeysToValues(keys, values);
    }

    public static void clearCustomParameters() {
        sCustomParameters = null;
    }

    private static HashMap<String, String> getCustomParameters(Map<String, String> passedParameters) {
        if (passedParameters != null) {
            return new HashMap<>(passedParameters);
        }
        if (sCustomParameters != null) {
            return new HashMap<>(sCustomParameters);
        }
        return null;
    }

    private SponsorPayAdvertiser(Context context) {
        this.mContext = context;
    }

    public static void register(Context context) {
        register(context, (String) null, (Map<String, String>) null);
    }

    public static void registerWithDelay(Context context, int delayMin) {
        registerWithDelay(context, delayMin, (String) null, (Map<String, String>) null);
    }

    public static void registerWithDelay(Context context, int delayMin, String overrideAppId) {
        registerWithDelay(context, delayMin, overrideAppId, (Map<String, String>) null);
    }

    public static void registerWithDelay(Context context, int delayMin, String overrideAppId, Map<String, String> customParams) {
        SponsorPayCallbackDelayer.callWithDelay(context, overrideAppId, (long) delayMin, getCustomParameters(customParams));
    }

    public static void register(Context context, String overrideAppId) {
        register(context, overrideAppId, (Map<String, String>) null);
    }

    public static void register(Context context, String overrideAppId, Map<String, String> customParams) {
        if (mInstance == null) {
            mInstance = new SponsorPayAdvertiser(context);
        }
        mInstance.register(overrideAppId, (Map<String, String>) getCustomParameters(customParams));
    }

    private void register(String overrideAppId, Map<String, String> customParams) {
        this.mHostInfo = new HostInfo(this.mContext);
        if (overrideAppId != null && !overrideAppId.equals("")) {
            this.mHostInfo.setOverriddenAppId(overrideAppId);
        }
        boolean gotSuccessfulResponseYet = this.mPersistedState.getHasAdvertiserCallbackReceivedSuccessfulResponse();
        this.mAPICaller = new AdvertiserCallbackSender(this.mHostInfo, this);
        this.mAPICaller.setWasAlreadySuccessful(gotSuccessfulResponseYet);
        this.mAPICaller.setInstallSubId(this.mPersistedState.getInstallSubId());
        this.mAPICaller.setCustomParams(customParams);
        this.mAPICaller.trigger();
    }

    public void onAPIResponse(boolean wasSuccessful) {
        if (wasSuccessful) {
            this.mPersistedState.setHasAdvertiserCallbackReceivedSuccessfulResponse(true);
        }
    }
}
