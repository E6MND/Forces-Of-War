package com.aarki;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class AarkiInstallReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        registerInstall(context, intent);
    }

    public static void registerInstall(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        String string = extras != null ? extras.getString("referrer") : null;
        "Install Referrer: " + string;
        AarkiContact.registerInstall(context, string);
    }
}
