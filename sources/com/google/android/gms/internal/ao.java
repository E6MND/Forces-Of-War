package com.google.android.gms.internal;

import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.internal.at;

public final class ao extends at.a {
    private final AppEventListener mf;

    public ao(AppEventListener appEventListener) {
        this.mf = appEventListener;
    }

    public void onAppEvent(String name, String info) {
        this.mf.onAppEvent(name, info);
    }
}
