package com.google.android.gms.internal;

import java.util.Map;

public final class ba implements bd {
    private final bb mQ;

    public ba(bb bbVar) {
        this.mQ = bbVar;
    }

    public void b(ey eyVar, Map<String, String> map) {
        String str = map.get("name");
        if (str == null) {
            ev.D("App event with no name parameter.");
        } else {
            this.mQ.onAppEvent(str, map.get("info"));
        }
    }
}
