package org.chromium.net;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.security.KeyChain;
import android.telephony.TelephonyManager;
import android.util.Log;
import java.net.URLConnection;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import org.chromium.base.CalledByNative;
import org.chromium.base.CalledByNativeUnchecked;

class AndroidNetworkLibrary {
    private static final String TAG = "AndroidNetworkLibrary";

    AndroidNetworkLibrary() {
    }

    @CalledByNative
    public static boolean storeKeyPair(Context context, byte[] publicKey, byte[] privateKey) {
        try {
            Intent intent = KeyChain.createInstallIntent();
            intent.putExtra("PKEY", privateKey);
            intent.putExtra("KEY", publicKey);
            intent.addFlags(268435456);
            context.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e) {
            Log.w(TAG, "could not store key pair: " + e);
            return false;
        }
    }

    @CalledByNative
    public static boolean storeCertificate(Context context, int certType, byte[] data) {
        try {
            Intent intent = KeyChain.createInstallIntent();
            intent.addFlags(268435456);
            switch (certType) {
                case 1:
                case 2:
                    intent.putExtra("CERT", data);
                    break;
                case 3:
                    intent.putExtra("PKCS12", data);
                    break;
                default:
                    Log.w(TAG, "invalid certificate type: " + certType);
                    return false;
            }
            context.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e) {
            Log.w(TAG, "could not store crypto file: " + e);
            return false;
        }
    }

    @CalledByNative
    public static String getMimeTypeFromExtension(String extension) {
        return URLConnection.guessContentTypeFromName("foo." + extension);
    }

    /* JADX WARNING: CFG modification limit reached, blocks count: 119 */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0034, code lost:
        if (r2.isUp() == false) goto L_0x0024;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003a, code lost:
        if (r2.isLoopback() != false) goto L_0x0024;
     */
    @org.chromium.base.CalledByNative
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean haveOnlyLoopbackAddresses() {
        /*
            r3 = 0
            r1 = 0
            java.util.Enumeration r1 = java.net.NetworkInterface.getNetworkInterfaces()     // Catch:{ Exception -> 0x0009 }
            if (r1 != 0) goto L_0x0024
        L_0x0008:
            return r3
        L_0x0009:
            r0 = move-exception
            java.lang.String r4 = "AndroidNetworkLibrary"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "could not get network interfaces: "
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.String r5 = r5.toString()
            android.util.Log.w(r4, r5)
            goto L_0x0008
        L_0x0023:
            r0 = move-exception
        L_0x0024:
            boolean r4 = r1.hasMoreElements()
            if (r4 == 0) goto L_0x003d
            java.lang.Object r2 = r1.nextElement()
            java.net.NetworkInterface r2 = (java.net.NetworkInterface) r2
            boolean r4 = r2.isUp()     // Catch:{ SocketException -> 0x0023 }
            if (r4 == 0) goto L_0x0024
            boolean r4 = r2.isLoopback()     // Catch:{ SocketException -> 0x0023 }
            if (r4 != 0) goto L_0x0024
            goto L_0x0008
        L_0x003d:
            r3 = 1
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.net.AndroidNetworkLibrary.haveOnlyLoopbackAddresses():boolean");
    }

    @CalledByNative
    public static AndroidCertVerifyResult verifyServerCertificates(byte[][] certChain, String authType, String host) {
        try {
            return X509Util.verifyServerCertificates(certChain, authType, host);
        } catch (KeyStoreException e) {
            return new AndroidCertVerifyResult(-1);
        } catch (NoSuchAlgorithmException e2) {
            return new AndroidCertVerifyResult(-1);
        }
    }

    @CalledByNativeUnchecked
    public static void addTestRootCertificate(byte[] rootCert) throws CertificateException, KeyStoreException, NoSuchAlgorithmException {
        X509Util.addTestRootCertificate(rootCert);
    }

    @CalledByNativeUnchecked
    public static void clearTestRootCertificates() throws NoSuchAlgorithmException, CertificateException, KeyStoreException {
        X509Util.clearTestRootCertificates();
    }

    @CalledByNative
    private static String getNetworkCountryIso(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            return "";
        }
        return telephonyManager.getNetworkCountryIso();
    }

    @CalledByNative
    private static String getNetworkOperator(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            return "";
        }
        return telephonyManager.getNetworkOperator();
    }
}
