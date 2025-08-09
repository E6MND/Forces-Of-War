package com.google.android.gms.internal;

import com.facebook.AppEventsConstants;
import java.util.Map;

public class bf implements bd {
    private final bg na;

    public bf(bg bgVar) {
        this.na = bgVar;
    }

    public void b(ey eyVar, Map<String, String> map) {
        this.na.b(AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("transparentBackground")));
    }
}
