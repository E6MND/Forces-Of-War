package com.sponsorpay.sdk.android;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import java.util.Locale;

public class HostInfo {
    private static final String ANDROID_OS_PREFIX = "Android OS ";
    private static final String SPONSORPAY_APP_ID_KEY = "SPONSORPAY_APP_ID";
    private static boolean sSimulateInvalidAndroidId = false;
    private static boolean sSimulateNoAccessWifiStatePermission = false;
    private static boolean sSimulateNoHardwareSerialNumber = false;
    private static boolean sSimulateNoReadPhoneStatePermission = false;
    private String mAndroidId;
    private String mAppId;
    private Context mContext;
    private String mHardwareSerialNumber;
    private String mLanguageSetting;
    private String mOsVersion;
    private String mPhoneVersion;
    private String mUDID;
    private String mWifiMacAddress;

    public static void setSimulateNoReadPhoneStatePermission(boolean value) {
        sSimulateNoReadPhoneStatePermission = value;
    }

    public static void setSimulateNoAccessWifiStatePermission(boolean value) {
        sSimulateNoAccessWifiStatePermission = value;
    }

    public static void setSimulateInvalidAndroidId(boolean value) {
        sSimulateInvalidAndroidId = value;
    }

    public static void setSimulateNoHardwareSerialNumber(boolean value) {
        sSimulateNoHardwareSerialNumber = value;
    }

    public String getUDID() {
        return this.mUDID;
    }

    public String getOsVersion() {
        return this.mOsVersion;
    }

    public String getPhoneVersion() {
        return this.mPhoneVersion;
    }

    public String getHardwareSerialNumber() {
        if (this.mHardwareSerialNumber == null) {
            if (!sSimulateNoHardwareSerialNumber) {
                try {
                    Object serialValue = Build.class.getField("SERIAL").get((Object) null);
                    if (serialValue != null && serialValue.getClass().equals(String.class)) {
                        this.mHardwareSerialNumber = (String) serialValue;
                    }
                } catch (Exception e) {
                    this.mHardwareSerialNumber = "";
                }
            } else {
                this.mHardwareSerialNumber = "";
            }
        }
        return this.mHardwareSerialNumber;
    }

    public String getLanguageSetting() {
        return this.mLanguageSetting;
    }

    public String getAndroidId() {
        return this.mAndroidId;
    }

    public String getWifiMacAddress() {
        return this.mWifiMacAddress;
    }

    public HostInfo(Context context) {
        this.mContext = context;
        if (!sSimulateNoReadPhoneStatePermission) {
            try {
                this.mUDID = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            } catch (SecurityException e) {
                this.mUDID = "";
            }
        } else {
            this.mUDID = "";
        }
        this.mLanguageSetting = Locale.getDefault().toString();
        this.mOsVersion = ANDROID_OS_PREFIX + Build.VERSION.RELEASE;
        this.mPhoneVersion = String.valueOf(Build.MANUFACTURER) + "_" + Build.MODEL;
        if (!sSimulateInvalidAndroidId) {
            this.mAndroidId = Settings.Secure.getString(this.mContext.getContentResolver(), "android_id");
            if (this.mAndroidId == null) {
                this.mAndroidId = "";
            }
        } else {
            this.mAndroidId = "";
        }
        if (!sSimulateNoAccessWifiStatePermission) {
            try {
                this.mWifiMacAddress = ((WifiManager) this.mContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            } catch (RuntimeException e2) {
                this.mWifiMacAddress = "";
            }
        } else {
            this.mWifiMacAddress = "";
        }
    }

    private String getValueFromAppMetadata(String key) {
        Object retrievedValue;
        try {
            Bundle appMetadata = this.mContext.getPackageManager().getApplicationInfo(this.mContext.getPackageName(), 128).metaData;
            if (appMetadata == null || (retrievedValue = appMetadata.get(key)) == null) {
                return null;
            }
            return retrievedValue.toString();
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public String getAppId() {
        if (this.mAppId == null || this.mAppId.equals("")) {
            this.mAppId = getValueFromAppMetadata(SPONSORPAY_APP_ID_KEY);
            if (this.mAppId == null || this.mAppId.equals("")) {
                throw new RuntimeException("SponsorPay SDK: no valid App ID has been provided. Please set a valid App ID in your application manifest or provide one at runtime. See the integration guide or the SDK javadoc for more information.");
            }
        }
        return this.mAppId;
    }

    public void setOverriddenAppId(String appId) {
        this.mAppId = appId;
    }
}
