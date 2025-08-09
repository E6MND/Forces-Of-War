package com.sponsorpay.sdk.android;

import android.net.Uri;
import java.util.HashMap;
import java.util.Map;

public class UrlBuilder {
    private static final String ANDROID_ID_KEY = "android_id";
    private static final String APPID_KEY = "appid";
    private static final String LANGUAGE_KEY = "language";
    private static final String OS_VERSION_KEY = "os_version";
    private static final String PHONE_VERSION_KEY = "phone_version";
    private static final String SDK_RELEASE_VERSION_KEY = "sdk_version";
    private static final String UDID_KEY = "device_id";
    public static final String URL_PARAM_ALLOW_CAMPAIGN_KEY = "allow_campaign";
    public static final String URL_PARAM_CURRENCY_NAME_KEY = "currency";
    public static final String URL_PARAM_OFFSET_KEY = "offset";
    private static final String URL_PARAM_SIGNATURE = "signature";
    public static final String URL_PARAM_VALUE_ON = "on";
    private static final String USERID_KEY = "uid";
    private static final String WIFI_MAC_ADDRESS_KEY = "mac_address";

    public static String buildUrl(String resourceUrl, String userId, HostInfo hostInfo, Map<String, String> extraKeysValues) {
        return buildUrl(resourceUrl, userId, hostInfo, extraKeysValues, (String) null);
    }

    public static String buildUrl(String resourceUrl, HostInfo hostInfo, Map<String, String> extraKeysValues) {
        return buildUrl(resourceUrl, (String) null, hostInfo, extraKeysValues, (String) null);
    }

    public static String buildUrl(String resourceUrl, String userId, HostInfo hostInfo, Map<String, String> extraKeysValues, String secretKey) {
        HashMap<String, String> keyValueParams = new HashMap<>();
        if (userId != null) {
            keyValueParams.put(USERID_KEY, userId);
        }
        keyValueParams.put(UDID_KEY, hostInfo.getUDID());
        keyValueParams.put(APPID_KEY, String.valueOf(hostInfo.getAppId()));
        keyValueParams.put(OS_VERSION_KEY, hostInfo.getOsVersion());
        keyValueParams.put(PHONE_VERSION_KEY, hostInfo.getPhoneVersion());
        keyValueParams.put(LANGUAGE_KEY, hostInfo.getLanguageSetting());
        keyValueParams.put(SDK_RELEASE_VERSION_KEY, SponsorPay.RELEASE_VERSION_STRING);
        keyValueParams.put(ANDROID_ID_KEY, hostInfo.getAndroidId());
        keyValueParams.put(WIFI_MAC_ADDRESS_KEY, hostInfo.getWifiMacAddress());
        if (extraKeysValues != null) {
            validateKeyValueParams(extraKeysValues);
            keyValueParams.putAll(extraKeysValues);
        }
        Uri.Builder builder = Uri.parse(resourceUrl).buildUpon();
        for (String key : keyValueParams.keySet()) {
            builder.appendQueryParameter(key, keyValueParams.get(key));
        }
        if (secretKey != null) {
            builder.appendQueryParameter(URL_PARAM_SIGNATURE, SignatureTools.generateSignatureForParameters(keyValueParams, secretKey));
        }
        return builder.build().toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:4:0x0011  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void validateKeyValueParams(java.util.Map<java.lang.String, java.lang.String> r5) {
        /*
            if (r5 == 0) goto L_0x0010
            java.util.Set r0 = r5.keySet()
            java.util.Iterator r3 = r0.iterator()
        L_0x000a:
            boolean r4 = r3.hasNext()
            if (r4 != 0) goto L_0x0011
        L_0x0010:
            return
        L_0x0011:
            java.lang.Object r1 = r3.next()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r2 = r5.get(r1)
            java.lang.String r2 = (java.lang.String) r2
            if (r1 == 0) goto L_0x0031
            java.lang.String r4 = ""
            boolean r4 = r4.equals(r1)
            if (r4 != 0) goto L_0x0031
            if (r2 == 0) goto L_0x0031
            java.lang.String r4 = ""
            boolean r4 = r4.equals(r2)
            if (r4 == 0) goto L_0x000a
        L_0x0031:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.String r4 = "SponsorPay SDK: Custom Parameters cannot have an empty or null Key or Value."
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sponsorpay.sdk.android.UrlBuilder.validateKeyValueParams(java.util.Map):void");
    }

    public static Map<String, String> mapKeysToValues(String[] keys, String[] values) {
        if (keys.length != values.length) {
            throw new IllegalArgumentException("SponsorPay SDK: When specifying Custom Parameters using two arrays of Keys and Values, both must have the same length.");
        }
        HashMap<String, String> retval = new HashMap<>(keys.length);
        for (int i = 0; i < keys.length; i++) {
            String k = keys[i];
            String v = values[i];
            if (k == null || "".equals(k) || v == null || "".equals(v)) {
                throw new IllegalArgumentException("SponsorPay SDK: When specifying Custom Parameters using two arrays of Keys and Values, none of their elements can be empty or null.");
            }
            retval.put(k, v);
        }
        return retval;
    }
}
