package com.uken.android.common;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import com.uken.android.common.UkenActivity;

public class GameActivity extends UkenActivity {
    public void onCreate(Bundle savedInstanceState) {
        this.buildType = UkenActivity.UkenBuild.valueOf("Production");
        BASEURL = "https://m-forces.uken.com/";
        FACEBOOK_APP_ID = "5161289059";
        GAME_NAME = "Forces Of War";
        GAME_NAMESPACE = "forces";
        GAME_NAME_CONSTANT = "war";
        AARKI_PLACEMENT = "4D84BEE53A4285ABAA";
        NOTIFICATION_SENDER_ID = "349915492560";
        MODAL_BACKGROUND_COLOR = ViewCompat.MEASURED_STATE_MASK;
        CHARTBOOST_APP_ID = "51dd841c17ba478440000005";
        CHARTBOOST_APP_SIGNATURE = "cf86449eb3926bb4ef3154d7a29b0fbc01eec40c";
        UkenApplication.GA_PROPERTY_ID = "UA-27433056-3";
        DISPLAY_NAV_BAR = true;
        IMAGES_PRELOADED = true;
        this.BASE64_ENCODED_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnu5xc9NDXPpaXd4e1Tu5E1Ivm5NLJsu8pPKtpUs2VZpEn/kzJ6DTVuVXOftlyrmPekpxuat0buFr1mb/8IqFxSFeDhscsu7e+1KPmD+xc/NSLwwQYwM9dHYsIGvq/fZvFv2fQ33ZENgncsFtFdGpUYqprBsbtbAp8ZIvmGep0XvEDhP3TwCgIiFk2hyoJymh1awthPYBDTroKUIjA2yRierssJfJINKPtg1z3aqARfJFVPGmHjhBGa65uTwmqZR+P5Eo7ZyARvGL+XlLDMYHvsZwZKKWPVfABaMMWP4VKonzhMiyN0LATvJvnqIgd2wWyE+G8TUML4jvd/5nUn5BqQIDAQAB";
        super.onCreate(savedInstanceState);
    }
}
