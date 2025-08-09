package com.sponsorpay.sdk.android.advertiser;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemClock;
import android.util.Log;
import com.sponsorpay.sdk.android.HostInfo;
import com.sponsorpay.sdk.android.UrlBuilder;
import java.util.HashMap;
import org.chromium.ui.base.PageTransition;

public class SponsorPayCallbackDelayer extends BroadcastReceiver {
    public static final String ACTION_TRIGGER_SPONSORPAY_CALLBACK = "ACTION_TRIGGER_SPONSORPAY_CALLBACK";
    public static final String EXTRA_APPID_KEY = "EXTRA_APPID_KEY";
    public static final String EXTRA_CUSTOM_PARAMETERS = "EXTRA_CUSTOM_PARAMETERS";
    public static final int MILLISECONDS_IN_MINUTE = 60000;

    public static void callWithDelay(Context context, String appId, long delayMinutes) {
        callWithDelay(context, appId, delayMinutes, (HashMap<String, String>) null);
    }

    public static void callWithDelay(Context context, String appId, long delayMinutes, HashMap<String, String> customParams) {
        Log.d(SponsorPayCallbackDelayer.class.toString(), "callWithDelay called");
        if (appId == null || appId.equals("")) {
            new HostInfo(context).getAppId();
        }
        context.registerReceiver(new SponsorPayCallbackDelayer(), new IntentFilter(ACTION_TRIGGER_SPONSORPAY_CALLBACK));
        Intent intent = new Intent(ACTION_TRIGGER_SPONSORPAY_CALLBACK);
        intent.putExtra(EXTRA_APPID_KEY, appId);
        if (customParams != null) {
            UrlBuilder.validateKeyValueParams(customParams);
            intent.putExtra(EXTRA_CUSTOM_PARAMETERS, customParams);
        }
        PendingIntent triggerCallbackPendingIntent = PendingIntent.getBroadcast(context, 0, intent, PageTransition.CLIENT_REDIRECT);
        ((AlarmManager) context.getSystemService("alarm")).set(2, SystemClock.elapsedRealtime() + (60000 * delayMinutes), triggerCallbackPendingIntent);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.io.Serializable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onReceive(android.content.Context r5, android.content.Intent r6) {
        /*
            r4 = this;
            java.lang.Class r2 = r4.getClass()
            java.lang.String r2 = r2.toString()
            java.lang.String r3 = "Calling SponsorPayAdvertiser.register"
            android.util.Log.d(r2, r3)
            r0 = 0
            java.lang.String r2 = "EXTRA_CUSTOM_PARAMETERS"
            java.io.Serializable r1 = r6.getSerializableExtra(r2)
            boolean r2 = r1 instanceof java.util.HashMap
            if (r2 == 0) goto L_0x001b
            r0 = r1
            java.util.HashMap r0 = (java.util.HashMap) r0
        L_0x001b:
            java.lang.String r2 = "EXTRA_APPID_KEY"
            java.lang.String r2 = r6.getStringExtra(r2)
            com.sponsorpay.sdk.android.advertiser.SponsorPayAdvertiser.register(r5, r2, r0)
            r5.unregisterReceiver(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sponsorpay.sdk.android.advertiser.SponsorPayCallbackDelayer.onReceive(android.content.Context, android.content.Intent):void");
    }
}
