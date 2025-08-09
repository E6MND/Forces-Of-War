package com.amazon.inapp.purchasing;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public final class ResponseReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        ImplementationFactory.getResponseHandler().handleResponse(context, intent);
    }
}
