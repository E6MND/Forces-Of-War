package com.crashlytics.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

final class L extends BroadcastReceiver {
    private /* synthetic */ C0205v a;

    L(C0205v vVar) {
        this.a = vVar;
    }

    public final void onReceive(Context context, Intent intent) {
        boolean unused = this.a.v = true;
    }
}
