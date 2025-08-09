package com.crashlytics.android.internal;

import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.plus.PlusShare;
import org.json.JSONException;
import org.json.JSONObject;
import org.xwalk.core.internal.extension.api.messaging.MessagingSmsConsts;

public class aY {
    aY() {
    }

    public aX a(C0149ah ahVar, JSONObject jSONObject) throws JSONException {
        int optInt = jSONObject.optInt("settings_version", 0);
        int optInt2 = jSONObject.optInt("cache_duration", 3600);
        JSONObject jSONObject2 = jSONObject.getJSONObject("app");
        String string = jSONObject2.getString("identifier");
        String string2 = jSONObject2.getString(MessagingSmsConsts.STATUS);
        String string3 = jSONObject2.getString("url");
        String string4 = jSONObject2.getString("reports_url");
        boolean optBoolean = jSONObject2.optBoolean("update_required", false);
        aL aLVar = null;
        if (jSONObject2.has("icon") && jSONObject2.getJSONObject("icon").has("hash")) {
            JSONObject jSONObject3 = jSONObject2.getJSONObject("icon");
            aLVar = new aL(jSONObject3.getString("hash"), jSONObject3.getInt("width"), jSONObject3.getInt("height"));
        }
        aM aMVar = new aM(string, string2, string3, string4, optBoolean, aLVar);
        JSONObject jSONObject4 = jSONObject.getJSONObject("session");
        aR aRVar = new aR(jSONObject4.optInt("log_buffer_size", 64000), jSONObject4.optInt("max_chained_exception_depth", 8), jSONObject4.optInt("max_custom_exception_events", 64), jSONObject4.optInt("max_custom_key_value_pairs", 64), jSONObject4.optInt("identifier_mask", 255), jSONObject4.optBoolean("send_session_without_crash", false));
        JSONObject jSONObject5 = jSONObject.getJSONObject("prompt");
        aQ aQVar = new aQ(jSONObject5.optString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, "Send Crash Report?"), jSONObject5.optString("message", "Looks like we crashed! Please help us fix the problem by sending a crash report."), jSONObject5.optString("send_button_title", "Send"), jSONObject5.optBoolean("show_cancel_button", true), jSONObject5.optString("cancel_button_title", "Don't Send"), jSONObject5.optBoolean("show_always_send_button", true), jSONObject5.optString("always_send_button_title", "Always Send"));
        JSONObject jSONObject6 = jSONObject.getJSONObject("features");
        aP aPVar = new aP(jSONObject6.optBoolean("prompt_enabled", false), jSONObject6.optBoolean("collect_logged_exceptions", true), jSONObject6.optBoolean("collect_reports", true), jSONObject6.optBoolean("collect_analytics", false));
        JSONObject jSONObject7 = jSONObject.getJSONObject("analytics");
        return new aX(jSONObject.has("expires_at") ? jSONObject.getLong("expires_at") : ahVar.a() + (((long) optInt2) * 1000), aMVar, aRVar, aQVar, aPVar, new aK(jSONObject7.optString("url", "https://e.crashlytics.com/spi/v2/events"), jSONObject7.optInt("flush_interval_secs", 600), jSONObject7.optInt("max_byte_size_per_file", GamesStatusCodes.STATUS_MILESTONE_CLAIMED_PREVIOUSLY), jSONObject7.optInt("max_file_count_per_send", 1), jSONObject7.optInt("max_pending_send_file_count", 100)), optInt, optInt2);
    }

    public JSONObject a(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject(jSONObject.toString());
        jSONObject2.getJSONObject("features").remove("collect_analytics");
        jSONObject2.remove("analytics");
        return jSONObject2;
    }
}
