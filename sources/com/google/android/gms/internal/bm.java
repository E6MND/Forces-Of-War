package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class bm {
    public final String ni;
    public final String nj;
    public final List<String> nk;
    public final String nl;
    public final String nm;
    public final List<String> nn;
    public final String no;

    public bm(JSONObject jSONObject) throws JSONException {
        String str = null;
        this.nj = jSONObject.getString("id");
        JSONArray jSONArray = jSONObject.getJSONArray("adapters");
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.getString(i));
        }
        this.nk = Collections.unmodifiableList(arrayList);
        this.nl = jSONObject.optString("allocation_id", (String) null);
        this.nn = bs.a(jSONObject, "imp_urls");
        JSONObject optJSONObject = jSONObject.optJSONObject("ad");
        this.ni = optJSONObject != null ? optJSONObject.toString() : null;
        JSONObject optJSONObject2 = jSONObject.optJSONObject("data");
        this.no = optJSONObject2 != null ? optJSONObject2.toString() : null;
        this.nm = optJSONObject2 != null ? optJSONObject2.optString("class_name") : str;
    }
}
