package com.google.android.gms.analytics;

import java.util.HashMap;
import java.util.Map;

class ab {
    private final Map<String, Integer> wI = new HashMap();
    private final Map<String, String> wJ = new HashMap();
    private final boolean wK;
    private final String wL;

    ab(String str, boolean z) {
        this.wK = z;
        this.wL = str;
    }

    /* access modifiers changed from: package-private */
    public void c(String str, int i) {
        if (this.wK) {
            Integer num = this.wI.get(str);
            if (num == null) {
                num = 0;
            }
            this.wI.put(str, Integer.valueOf(num.intValue() + i));
        }
    }

    /* access modifiers changed from: package-private */
    public String dl() {
        if (!this.wK) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.wL);
        for (String next : this.wI.keySet()) {
            sb.append("&").append(next).append("=").append(this.wI.get(next));
        }
        for (String next2 : this.wJ.keySet()) {
            sb.append("&").append(next2).append("=").append(this.wJ.get(next2));
        }
        return sb.toString();
    }
}
