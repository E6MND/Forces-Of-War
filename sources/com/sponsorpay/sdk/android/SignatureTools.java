package com.sponsorpay.sdk.android;

import android.util.Log;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

public class SignatureTools {
    public static final String NO_SHA1_RESULT = "nosha1";
    private static final String SHA1_ALGORITHM = "SHA1";

    public static String generateSignatureForParameters(Map<String, String> parameters, String secretToken) {
        TreeSet<String> orderedKeys = new TreeSet<>();
        orderedKeys.addAll(parameters.keySet());
        Iterator<String> orderedKeysIterator = orderedKeys.iterator();
        String concatenatedOrderedParams = "";
        while (orderedKeysIterator.hasNext()) {
            String key = orderedKeysIterator.next();
            concatenatedOrderedParams = String.valueOf(concatenatedOrderedParams) + String.format("%s=%s&", new Object[]{key, parameters.get(key)});
        }
        return generateSignatureForString(concatenatedOrderedParams, secretToken);
    }

    public static String generateSignatureForString(String text, String secretToken) {
        return generateSHA1ForString(String.valueOf(text) + secretToken);
    }

    public static String generateSHA1ForString(String text) {
        try {
            return byteArray2Hex(MessageDigest.getInstance(SHA1_ALGORITHM).digest(text.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            Log.e("UrlBuilder", "SHA1 algorithm not available.");
            e.printStackTrace();
            return NO_SHA1_RESULT;
        }
    }

    public static String byteArray2Hex(byte[] hash) {
        Formatter formatter = new Formatter();
        int length = hash.length;
        for (int i = 0; i < length; i++) {
            formatter.format("%02x", new Object[]{Byte.valueOf(hash[i])});
        }
        return formatter.toString();
    }
}
