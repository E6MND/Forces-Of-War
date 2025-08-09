package com.uken.android.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class InstallTrackerReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String referrer;
        try {
            referrer = intent.getStringExtra("referrer");
            if (referrer == null) {
                referrer = "null_referrer_found";
            }
        } catch (Exception e) {
            referrer = "exception_found_retrieving_referrer";
        }
        SharedPreferences.Editor editor = Prefs.get(context).edit();
        editor.putString("referrer", referrer);
        editor.commit();
    }
}
