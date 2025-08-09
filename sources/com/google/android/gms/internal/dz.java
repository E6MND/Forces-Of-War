package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import com.sponsorpay.sdk.android.UrlBuilder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class dz {
    private static final SimpleDateFormat qx = new SimpleDateFormat("yyyyMMdd");

    public static dv a(Context context, dt dtVar, String str) {
        dv dvVar;
        List<String> list;
        List<String> list2;
        List<String> list3;
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("ad_base_url", (String) null);
            String optString2 = jSONObject.optString("ad_url", (String) null);
            String optString3 = jSONObject.optString("ad_size", (String) null);
            String optString4 = jSONObject.optString("ad_html", (String) null);
            long j = -1;
            String optString5 = jSONObject.optString("debug_dialog", (String) null);
            long j2 = jSONObject.has("interstitial_timeout") ? (long) (jSONObject.getDouble("interstitial_timeout") * 1000.0d) : -1;
            String optString6 = jSONObject.optString("orientation", (String) null);
            int i = -1;
            if ("portrait".equals(optString6)) {
                i = ep.bN();
            } else if ("landscape".equals(optString6)) {
                i = ep.bM();
            }
            if (!TextUtils.isEmpty(optString4)) {
                if (TextUtils.isEmpty(optString)) {
                    ev.D("Could not parse the mediation config: Missing required ad_base_url field");
                    return new dv(0);
                }
                dvVar = null;
            } else if (!TextUtils.isEmpty(optString2)) {
                dv a = dy.a(context, dtVar.kO.st, optString2, (String) null, (ec) null);
                optString = a.oy;
                optString4 = a.qb;
                j = a.qh;
                dvVar = a;
            } else {
                ev.D("Could not parse the mediation config: Missing required ad_html or ad_url field.");
                return new dv(0);
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("click_urls");
            List<String> list4 = dvVar == null ? null : dvVar.nr;
            if (optJSONArray != null) {
                if (list4 == null) {
                    list4 = new LinkedList<>();
                }
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    list4.add(optJSONArray.getString(i2));
                }
                list = list4;
            } else {
                list = list4;
            }
            JSONArray optJSONArray2 = jSONObject.optJSONArray("impression_urls");
            List<String> list5 = dvVar == null ? null : dvVar.ns;
            if (optJSONArray2 != null) {
                if (list5 == null) {
                    list5 = new LinkedList<>();
                }
                for (int i3 = 0; i3 < optJSONArray2.length(); i3++) {
                    list5.add(optJSONArray2.getString(i3));
                }
                list2 = list5;
            } else {
                list2 = list5;
            }
            JSONArray optJSONArray3 = jSONObject.optJSONArray("manual_impression_urls");
            List<String> list6 = dvVar == null ? null : dvVar.qf;
            if (optJSONArray3 != null) {
                if (list6 == null) {
                    list6 = new LinkedList<>();
                }
                for (int i4 = 0; i4 < optJSONArray3.length(); i4++) {
                    list6.add(optJSONArray3.getString(i4));
                }
                list3 = list6;
            } else {
                list3 = list6;
            }
            if (dvVar != null) {
                if (dvVar.orientation != -1) {
                    i = dvVar.orientation;
                }
                if (dvVar.qc > 0) {
                    j2 = dvVar.qc;
                }
            }
            String optString7 = jSONObject.optString("active_view");
            String str2 = null;
            boolean optBoolean = jSONObject.optBoolean("ad_is_javascript", false);
            if (optBoolean) {
                str2 = jSONObject.optString("ad_passback_url", (String) null);
            }
            return new dv(optString, optString4, list, list2, j2, false, -1, list3, -1, i, optString3, j, optString5, optBoolean, str2, optString7);
        } catch (JSONException e) {
            ev.D("Could not parse the mediation config: " + e.getMessage());
            return new dv(0);
        }
    }

    public static String a(dt dtVar, ed edVar, Location location, String str) {
        try {
            HashMap hashMap = new HashMap();
            if (!(str == null || str.trim() == "")) {
                hashMap.put("eid", str);
            }
            if (dtVar.pU != null) {
                hashMap.put("ad_pos", dtVar.pU);
            }
            a((HashMap<String, Object>) hashMap, dtVar.pV);
            hashMap.put("format", dtVar.kR.mc);
            if (dtVar.kR.width == -1) {
                hashMap.put("smart_w", "full");
            }
            if (dtVar.kR.height == -2) {
                hashMap.put("smart_h", "auto");
            }
            if (dtVar.kR.me != null) {
                StringBuilder sb = new StringBuilder();
                for (am amVar : dtVar.kR.me) {
                    if (sb.length() != 0) {
                        sb.append("|");
                    }
                    sb.append(amVar.width == -1 ? (int) (((float) amVar.widthPixels) / edVar.ro) : amVar.width);
                    sb.append("x");
                    sb.append(amVar.height == -2 ? (int) (((float) amVar.heightPixels) / edVar.ro) : amVar.height);
                }
                hashMap.put("sz", sb);
            }
            hashMap.put("slotname", dtVar.kL);
            hashMap.put("pn", dtVar.applicationInfo.packageName);
            if (dtVar.pW != null) {
                hashMap.put("vc", Integer.valueOf(dtVar.pW.versionCode));
            }
            hashMap.put("ms", dtVar.pX);
            hashMap.put("seq_num", dtVar.pY);
            hashMap.put("session_id", dtVar.pZ);
            hashMap.put("js", dtVar.kO.st);
            a((HashMap<String, Object>) hashMap, edVar);
            if (dtVar.pV.versionCode >= 2 && dtVar.pV.lY != null) {
                a((HashMap<String, Object>) hashMap, dtVar.pV.lY);
            }
            if (dtVar.versionCode >= 2) {
                hashMap.put("quality_signals", dtVar.qa);
            }
            if (ev.p(2)) {
                ev.C("Ad Request JSON: " + ep.o(hashMap).toString(2));
            }
            return ep.o(hashMap).toString();
        } catch (JSONException e) {
            ev.D("Problem serializing ad request to JSON: " + e.getMessage());
            return null;
        }
    }

    private static void a(HashMap<String, Object> hashMap, Location location) {
        HashMap hashMap2 = new HashMap();
        Float valueOf = Float.valueOf(location.getAccuracy() * 1000.0f);
        Long valueOf2 = Long.valueOf(location.getTime() * 1000);
        Long valueOf3 = Long.valueOf((long) (location.getLatitude() * 1.0E7d));
        Long valueOf4 = Long.valueOf((long) (location.getLongitude() * 1.0E7d));
        hashMap2.put("radius", valueOf);
        hashMap2.put("lat", valueOf3);
        hashMap2.put("long", valueOf4);
        hashMap2.put("time", valueOf2);
        hashMap.put("uule", hashMap2);
    }

    private static void a(HashMap<String, Object> hashMap, aj ajVar) {
        String bK = em.bK();
        if (bK != null) {
            hashMap.put("abf", bK);
        }
        if (ajVar.lQ != -1) {
            hashMap.put("cust_age", qx.format(new Date(ajVar.lQ)));
        }
        if (ajVar.extras != null) {
            hashMap.put("extras", ajVar.extras);
        }
        if (ajVar.lR != -1) {
            hashMap.put("cust_gender", Integer.valueOf(ajVar.lR));
        }
        if (ajVar.lS != null) {
            hashMap.put("kw", ajVar.lS);
        }
        if (ajVar.lU != -1) {
            hashMap.put("tag_for_child_directed_treatment", Integer.valueOf(ajVar.lU));
        }
        if (ajVar.lT) {
            hashMap.put("adtest", UrlBuilder.URL_PARAM_VALUE_ON);
        }
        if (ajVar.versionCode >= 2) {
            if (ajVar.lV) {
                hashMap.put("d_imp_hdr", 1);
            }
            if (!TextUtils.isEmpty(ajVar.lW)) {
                hashMap.put("ppid", ajVar.lW);
            }
            if (ajVar.lX != null) {
                a(hashMap, ajVar.lX);
            }
        }
        if (ajVar.versionCode >= 3 && ajVar.lZ != null) {
            hashMap.put("url", ajVar.lZ);
        }
    }

    private static void a(HashMap<String, Object> hashMap, ax axVar) {
        String str;
        String str2 = null;
        if (Color.alpha(axVar.mB) != 0) {
            hashMap.put("acolor", o(axVar.mB));
        }
        if (Color.alpha(axVar.backgroundColor) != 0) {
            hashMap.put("bgcolor", o(axVar.backgroundColor));
        }
        if (!(Color.alpha(axVar.mC) == 0 || Color.alpha(axVar.mD) == 0)) {
            hashMap.put("gradientto", o(axVar.mC));
            hashMap.put("gradientfrom", o(axVar.mD));
        }
        if (Color.alpha(axVar.mE) != 0) {
            hashMap.put("bcolor", o(axVar.mE));
        }
        hashMap.put("bthick", Integer.toString(axVar.mF));
        switch (axVar.mG) {
            case 0:
                str = "none";
                break;
            case 1:
                str = "dashed";
                break;
            case 2:
                str = "dotted";
                break;
            case 3:
                str = "solid";
                break;
            default:
                str = null;
                break;
        }
        if (str != null) {
            hashMap.put("btype", str);
        }
        switch (axVar.mH) {
            case 0:
                str2 = "light";
                break;
            case 1:
                str2 = "medium";
                break;
            case 2:
                str2 = "dark";
                break;
        }
        if (str2 != null) {
            hashMap.put("callbuttoncolor", str2);
        }
        if (axVar.mI != null) {
            hashMap.put("channel", axVar.mI);
        }
        if (Color.alpha(axVar.mJ) != 0) {
            hashMap.put("dcolor", o(axVar.mJ));
        }
        if (axVar.mK != null) {
            hashMap.put("font", axVar.mK);
        }
        if (Color.alpha(axVar.mL) != 0) {
            hashMap.put("hcolor", o(axVar.mL));
        }
        hashMap.put("headersize", Integer.toString(axVar.mM));
        if (axVar.mN != null) {
            hashMap.put("q", axVar.mN);
        }
    }

    private static void a(HashMap<String, Object> hashMap, ed edVar) {
        hashMap.put("am", Integer.valueOf(edVar.qY));
        hashMap.put("cog", m(edVar.qZ));
        hashMap.put("coh", m(edVar.ra));
        if (!TextUtils.isEmpty(edVar.rb)) {
            hashMap.put("carrier", edVar.rb);
        }
        hashMap.put("gl", edVar.rc);
        if (edVar.rd) {
            hashMap.put("simulator", 1);
        }
        hashMap.put("ma", m(edVar.re));
        hashMap.put("sp", m(edVar.rf));
        hashMap.put("hl", edVar.rg);
        if (!TextUtils.isEmpty(edVar.rh)) {
            hashMap.put("mv", edVar.rh);
        }
        hashMap.put("muv", Integer.valueOf(edVar.ri));
        if (edVar.rj != -2) {
            hashMap.put("cnt", Integer.valueOf(edVar.rj));
        }
        hashMap.put("gnt", Integer.valueOf(edVar.rk));
        hashMap.put("pt", Integer.valueOf(edVar.rl));
        hashMap.put("rm", Integer.valueOf(edVar.rm));
        hashMap.put("riv", Integer.valueOf(edVar.rn));
        hashMap.put("u_sd", Float.valueOf(edVar.ro));
        hashMap.put("sh", Integer.valueOf(edVar.rq));
        hashMap.put("sw", Integer.valueOf(edVar.rp));
        Bundle bundle = new Bundle();
        bundle.putInt("active_network_state", edVar.ru);
        bundle.putBoolean("active_network_metered", edVar.rt);
        hashMap.put("connectivity", bundle);
        Bundle bundle2 = new Bundle();
        bundle2.putBoolean("is_charging", edVar.rs);
        bundle2.putDouble("battery_level", edVar.rr);
        hashMap.put("battery", bundle2);
    }

    private static Integer m(boolean z) {
        return Integer.valueOf(z ? 1 : 0);
    }

    private static String o(int i) {
        return String.format(Locale.US, "#%06x", new Object[]{Integer.valueOf(16777215 & i)});
    }
}
