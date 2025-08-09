package com.uken.android.common;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.facebook.AppEventsConstants;
import java.util.UUID;

public class UkenUuid {
    public static final String TAG = "UkenUuid";

    public static String getUuid(Activity activity) {
        SharedPreferences settings = activity.getPreferences(0);
        String uuidStr = settings.getString("uuid", "");
        if (Consts.DEBUG) {
            Log.d(TAG, "uuidStr: " + uuidStr);
        }
        if (uuidStr != "") {
            return uuidStr;
        }
        UUID uuid = UUID.randomUUID();
        SharedPreferences.Editor editor = settings.edit();
        String uuidStr2 = uuid.toString();
        editor.putString("uuid", uuidStr2);
        editor.commit();
        return uuidStr2;
    }

    public static String getUdid(ContextWrapper context) {
        TelephonyManager tManager = (TelephonyManager) context.getSystemService("phone");
        if (Consts.DEBUG) {
            Log.v(TAG, "Man: " + tManager.toString());
        }
        String udid = tManager.getDeviceId();
        String macAddress = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
        if (udid != null) {
            return udid;
        }
        try {
            return AeSimpleSHA1.SHA1(macAddress);
        } catch (Exception e) {
            Utils.logCrashlytics(e);
            return udid;
        }
    }

    public static String getAndroidId(ContextWrapper context) {
        try {
            String androidId = Settings.Secure.getString(context.getContentResolver(), "android_id");
            if (androidId == null) {
                return AppEventsConstants.EVENT_PARAM_VALUE_NO;
            }
            return androidId;
        } catch (Exception e) {
            return AppEventsConstants.EVENT_PARAM_VALUE_NO;
        }
    }
}
