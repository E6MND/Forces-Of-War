package com.google.android.gms.internal;

import com.facebook.AppEventsConstants;
import java.util.HashMap;
import java.util.Map;

class y implements aa {
    private ey lc;

    public y(ey eyVar) {
        this.lc = eyVar;
    }

    public void a(ad adVar, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("isVisible", z ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        this.lc.a("onAdVisibilityChanged", (Map<String, ?>) hashMap);
    }
}
