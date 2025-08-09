package com.google.android.gms.internal;

import android.content.Intent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.panorama.PanoramaApi;

class ki implements PanoramaApi.PanoramaResult {
    private final Intent abl;
    private final Status yw;

    public ki(Status status, Intent intent) {
        this.yw = (Status) hn.f(status);
        this.abl = intent;
    }

    public Status getStatus() {
        return this.yw;
    }

    public Intent getViewerIntent() {
        return this.abl;
    }
}
