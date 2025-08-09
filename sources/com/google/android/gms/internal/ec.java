package com.google.android.gms.internal;

import com.facebook.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.xwalk.core.internal.extension.api.messaging.MessagingSmsConsts;

class ec {
    private final List<String> qP;
    private final List<String> qQ;
    private final String qR;
    private final String qS;
    private final String qT;
    private final String qU;
    private final String qV;
    private final boolean qW;
    private final int qX;

    public ec(Map<String, String> map) {
        this.qV = map.get("url");
        this.qS = map.get("base_uri");
        this.qT = map.get("post_parameters");
        this.qW = parseBoolean(map.get("drt_include"));
        this.qR = map.get("activation_overlay_url");
        this.qQ = t(map.get("check_packages"));
        this.qX = parseInt(map.get("request_id"));
        this.qU = map.get(MessagingSmsConsts.TYPE);
        this.qP = t(map.get("errors"));
    }

    private static boolean parseBoolean(String bool) {
        return bool != null && (bool.equals(AppEventsConstants.EVENT_PARAM_VALUE_YES) || bool.equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE));
    }

    private int parseInt(String i) {
        if (i == null) {
            return 0;
        }
        return Integer.parseInt(i);
    }

    private List<String> t(String str) {
        if (str == null) {
            return null;
        }
        return Arrays.asList(str.split(","));
    }

    public List<String> bt() {
        return this.qP;
    }

    public String bu() {
        return this.qT;
    }

    public boolean bv() {
        return this.qW;
    }

    public String getType() {
        return this.qU;
    }

    public String getUrl() {
        return this.qV;
    }
}
