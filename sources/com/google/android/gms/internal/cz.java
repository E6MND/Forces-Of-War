package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Bundle;
import com.uken.android.util.IabHelper;
import org.json.JSONException;
import org.json.JSONObject;

public final class cz {
    public static int a(Bundle bundle) {
        Object obj = bundle.get(IabHelper.RESPONSE_CODE);
        if (obj == null) {
            ev.D("Bundle with null response code, assuming OK (known issue)");
            return 0;
        } else if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        } else {
            if (obj instanceof Long) {
                return (int) ((Long) obj).longValue();
            }
            ev.D("Unexpected type for intent response code. " + obj.getClass().getName());
            return 5;
        }
    }

    public static int c(Intent intent) {
        Object obj = intent.getExtras().get(IabHelper.RESPONSE_CODE);
        if (obj == null) {
            ev.D("Intent with no response code, assuming OK (known issue)");
            return 0;
        } else if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        } else {
            if (obj instanceof Long) {
                return (int) ((Long) obj).longValue();
            }
            ev.D("Unexpected type for intent response code. " + obj.getClass().getName());
            return 5;
        }
    }

    public static String d(Intent intent) {
        if (intent == null) {
            return null;
        }
        return intent.getStringExtra(IabHelper.RESPONSE_INAPP_PURCHASE_DATA);
    }

    public static String e(Intent intent) {
        if (intent == null) {
            return null;
        }
        return intent.getStringExtra(IabHelper.RESPONSE_INAPP_SIGNATURE);
    }

    public static String p(String str) {
        if (str == null) {
            return null;
        }
        try {
            return new JSONObject(str).getString("developerPayload");
        } catch (JSONException e) {
            ev.D("Fail to parse purchase data");
            return null;
        }
    }

    public static String q(String str) {
        if (str == null) {
            return null;
        }
        try {
            return new JSONObject(str).getString("purchaseToken");
        } catch (JSONException e) {
            ev.D("Fail to parse purchase data");
            return null;
        }
    }
}
