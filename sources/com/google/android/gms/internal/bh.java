package com.google.android.gms.internal;

import com.facebook.AppEventsConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public final class bh implements bd {
    private final be nb;

    public bh(be beVar) {
        this.nb = beVar;
    }

    private static boolean a(Map<String, String> map) {
        return AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("custom_close"));
    }

    private static int b(Map<String, String> map) {
        String str = map.get("o");
        if (str != null) {
            if ("p".equalsIgnoreCase(str)) {
                return ep.bN();
            }
            if ("l".equalsIgnoreCase(str)) {
                return ep.bM();
            }
        }
        return -1;
    }

    public void b(ey eyVar, Map<String, String> map) {
        String str = map.get("a");
        if (str == null) {
            ev.D("Action missing from an open GMSG.");
            return;
        }
        ez bW = eyVar.bW();
        if ("expand".equalsIgnoreCase(str)) {
            if (eyVar.bZ()) {
                ev.D("Cannot expand WebView that is already expanded.");
            } else {
                bW.a(a(map), b(map));
            }
        } else if ("webapp".equalsIgnoreCase(str)) {
            String str2 = map.get("u");
            if (str2 != null) {
                bW.a(a(map), b(map), str2);
            } else {
                bW.a(a(map), b(map), map.get("html"), map.get("baseurl"));
            }
        } else if ("in_app_purchase".equalsIgnoreCase(str)) {
            String str3 = map.get("product_id");
            String str4 = map.get("report_urls");
            if (this.nb == null) {
                return;
            }
            if (str4 == null || str4.isEmpty()) {
                this.nb.a(str3, new ArrayList());
                return;
            }
            this.nb.a(str3, new ArrayList(Arrays.asList(str4.split(" "))));
        } else {
            bW.a(new cf(map.get("i"), map.get("u"), map.get("m"), map.get("p"), map.get("c"), map.get("f"), map.get("e")));
        }
    }
}
