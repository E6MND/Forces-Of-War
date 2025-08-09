package com.google.android.gms.internal;

import android.content.Intent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.panorama.PanoramaApi;

public class kd extends ki implements PanoramaApi.a {
    private final int abg;

    public kd(Status status, Intent intent, int i) {
        super(status, intent);
        this.abg = i;
    }

    public /* bridge */ /* synthetic */ Status getStatus() {
        return super.getStatus();
    }

    public /* bridge */ /* synthetic */ Intent getViewerIntent() {
        return super.getViewerIntent();
    }
}
