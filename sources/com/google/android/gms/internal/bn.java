package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class bn {
    public final List<bm> np;
    public final long nq;
    public final List<String> nr;
    public final List<String> ns;
    public final List<String> nt;
    public final String nu;
    public final long nv;
    public int nw;
    public int nx;

    public bn(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        if (ev.p(2)) {
            ev.C("Mediation Response JSON: " + jSONObject.toString(2));
        }
        JSONArray jSONArray = jSONObject.getJSONArray("ad_networks");
        ArrayList arrayList = new ArrayList(jSONArray.length());
        int i = -1;
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            bm bmVar = new bm(jSONArray.getJSONObject(i2));
            arrayList.add(bmVar);
            if (i < 0 && a(bmVar)) {
                i = i2;
            }
        }
        this.nw = i;
        this.nx = jSONArray.length();
        this.np = Collections.unmodifiableList(arrayList);
        this.nu = jSONObject.getString("qdata");
        JSONObject optJSONObject = jSONObject.optJSONObject("settings");
        if (optJSONObject != null) {
            this.nq = optJSONObject.optLong("ad_network_timeout_millis", -1);
            this.nr = bs.a(optJSONObject, "click_urls");
            this.ns = bs.a(optJSONObject, "imp_urls");
            this.nt = bs.a(optJSONObject, "nofill_urls");
            long optLong = optJSONObject.optLong("refresh", -1);
            this.nv = optLong > 0 ? optLong * 1000 : -1;
            return;
        }
        this.nq = -1;
        this.nr = null;
        this.ns = null;
        this.nt = null;
        this.nv = -1;
    }

    private boolean a(bm bmVar) {
        for (String equals : bmVar.nk) {
            if (equals.equals("com.google.ads.mediation.admob.AdMobAdapter")) {
                return true;
            }
        }
        return false;
    }
}
