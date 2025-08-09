package com.google.android.gms.appindexing;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.fg;
import com.google.android.gms.internal.fz;

public final class AppIndex {
    public static final Api<Api.ApiOptions.NoOptions> APP_INDEX_API = fg.xH;
    public static final AppIndexApi AppIndexApi = new fz();

    private AppIndex() {
    }
}
