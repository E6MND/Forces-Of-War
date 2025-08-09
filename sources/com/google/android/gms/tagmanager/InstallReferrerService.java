package com.google.android.gms.tagmanager;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.analytics.CampaignTrackingService;

public final class InstallReferrerService extends IntentService {
    CampaignTrackingService afN;
    Context afO;

    public InstallReferrerService() {
        super("InstallReferrerService");
    }

    public InstallReferrerService(String name) {
        super(name);
    }

    private void a(Context context, Intent intent) {
        if (this.afN == null) {
            this.afN = new CampaignTrackingService();
        }
        this.afN.processIntent(context, intent);
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        String stringExtra = intent.getStringExtra("referrer");
        Context applicationContext = this.afO != null ? this.afO : getApplicationContext();
        ay.d(applicationContext, stringExtra);
        a(applicationContext, intent);
    }
}
