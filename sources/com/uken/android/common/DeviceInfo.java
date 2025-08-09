package com.uken.android.common;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import java.util.TimeZone;

public class DeviceInfo {
    public static String devicePhoneIdentifier(Context mContext) {
        String deviceId;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService("phone");
            if (telephonyManager == null || (deviceId = telephonyManager.getDeviceId()) == null || deviceId.length() == 0) {
                return "";
            }
            return deviceId;
        } catch (SecurityException e) {
            return "";
        }
    }

    public static String deviceAndroidIdentifier(Context mContext) {
        String aid = Settings.Secure.getString(mContext.getContentResolver(), "android_id");
        if (aid == null) {
            return "";
        }
        return aid;
    }

    public static String deviceModel() {
        return Build.MODEL;
    }

    public static String deviceManufacturer() {
        return Build.MANUFACTURER;
    }

    public static String deviceMacAddress(Context mContext) {
        String wifiMacAddress;
        try {
            wifiMacAddress = ((WifiManager) mContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
        } catch (RuntimeException e) {
            wifiMacAddress = "";
        }
        if (wifiMacAddress == null) {
            return "";
        }
        return wifiMacAddress;
    }

    public static String systemName() {
        return Build.PRODUCT;
    }

    public static String systemVersion() {
        return Build.VERSION.RELEASE;
    }

    public static String systemSDKVersion() {
        return Integer.toString(Build.VERSION.SDK_INT);
    }

    public static String localeCountryCode(Context mContext) {
        return mContext.getResources().getConfiguration().locale.getCountry();
    }

    public static String localeLanguageCode(Context mContext) {
        return mContext.getResources().getConfiguration().locale.getLanguage();
    }

    public static String localeTimeZone() {
        return TimeZone.getDefault().getDisplayName();
    }

    public static int localeTimeGMTOffset() {
        return TimeZone.getDefault().getRawOffset() / 1000;
    }

    public static String localeTimeGMTOffsetString() {
        return Integer.toString(localeTimeGMTOffset());
    }

    public static String appIdentifier(Context mContext) {
        return mContext.getPackageName();
    }

    public static String appName(Context mContext) throws PackageManager.NameNotFoundException {
        PackageManager packageManager = mContext.getPackageManager();
        return packageManager.getApplicationLabel(packageManager.getApplicationInfo(mContext.getPackageName(), 0)).toString();
    }

    public static String appVersionName(Context mContext) throws PackageManager.NameNotFoundException {
        return mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionName;
    }

    public static int appVersionCode(Context mContext) throws PackageManager.NameNotFoundException {
        return mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionCode;
    }
}
