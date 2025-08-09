package org.chromium.net;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.http.X509TrustManagerExtensions;
import android.os.Build;
import android.util.Log;
import android.util.Pair;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;
import org.chromium.base.JNINamespace;

@JNINamespace("net")
public class X509Util {
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final String OID_ANY_EKU = "2.5.29.37.0";
    private static final String OID_SERVER_GATED_MICROSOFT = "1.3.6.1.4.1.311.10.3.3";
    private static final String OID_SERVER_GATED_NETSCAPE = "2.16.840.1.113730.4.1";
    private static final String OID_TLS_SERVER_AUTH = "1.3.6.1.5.5.7.3.1";
    private static final String TAG = "X509Util";
    private static CertificateFactory sCertificateFactory;
    private static X509TrustManagerImplementation sDefaultTrustManager;
    private static boolean sDisableNativeCodeForTest = false;
    private static boolean sLoadedSystemKeyStore;
    private static final Object sLock = new Object();
    private static File sSystemCertificateDirectory;
    private static KeyStore sSystemKeyStore;
    private static Set<Pair<X500Principal, PublicKey>> sSystemTrustAnchorCache;
    private static KeyStore sTestKeyStore;
    private static X509TrustManagerImplementation sTestTrustManager;
    private static TrustStorageListener sTrustStorageListener;

    private interface X509TrustManagerImplementation {
        List<X509Certificate> checkServerTrusted(X509Certificate[] x509CertificateArr, String str, String str2) throws CertificateException;
    }

    private static native Context nativeGetApplicationContext();

    private static native void nativeNotifyKeyChainChanged();

    private static native void nativeRecordCertVerifyCapabilitiesHistogram(boolean z);

    private static final class TrustStorageListener extends BroadcastReceiver {
        private TrustStorageListener() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.security.STORAGE_CHANGED")) {
                try {
                    X509Util.reloadDefaultTrustManager();
                } catch (CertificateException e) {
                    Log.e(X509Util.TAG, "Unable to reload the default TrustManager", e);
                } catch (KeyStoreException e2) {
                    Log.e(X509Util.TAG, "Unable to reload the default TrustManager", e2);
                } catch (NoSuchAlgorithmException e3) {
                    Log.e(X509Util.TAG, "Unable to reload the default TrustManager", e3);
                }
            }
        }
    }

    private static final class X509TrustManagerIceCreamSandwich implements X509TrustManagerImplementation {
        private final X509TrustManager mTrustManager;

        public X509TrustManagerIceCreamSandwich(X509TrustManager trustManager) {
            this.mTrustManager = trustManager;
        }

        public List<X509Certificate> checkServerTrusted(X509Certificate[] chain, String authType, String host) throws CertificateException {
            this.mTrustManager.checkServerTrusted(chain, authType);
            return Collections.emptyList();
        }
    }

    private static final class X509TrustManagerJellyBean implements X509TrustManagerImplementation {
        private final X509TrustManagerExtensions mTrustManagerExtensions;

        @SuppressLint({"NewApi"})
        public X509TrustManagerJellyBean(X509TrustManager trustManager) {
            this.mTrustManagerExtensions = new X509TrustManagerExtensions(trustManager);
        }

        public List<X509Certificate> checkServerTrusted(X509Certificate[] chain, String authType, String host) throws CertificateException {
            return this.mTrustManagerExtensions.checkServerTrusted(chain, authType, host);
        }
    }

    private static void ensureInitialized() throws CertificateException, KeyStoreException, NoSuchAlgorithmException {
        boolean z = true;
        synchronized (sLock) {
            if (sCertificateFactory == null) {
                sCertificateFactory = CertificateFactory.getInstance("X.509");
            }
            if (sDefaultTrustManager == null) {
                sDefaultTrustManager = createTrustManager((KeyStore) null);
            }
            if (!sLoadedSystemKeyStore) {
                try {
                    sSystemKeyStore = KeyStore.getInstance("AndroidCAStore");
                    try {
                        sSystemKeyStore.load((KeyStore.LoadStoreParameter) null);
                    } catch (IOException e) {
                    }
                    sSystemCertificateDirectory = new File(System.getenv("ANDROID_ROOT") + "/etc/security/cacerts");
                } catch (KeyStoreException e2) {
                }
                if (!sDisableNativeCodeForTest) {
                    if (sSystemKeyStore == null) {
                        z = false;
                    }
                    nativeRecordCertVerifyCapabilitiesHistogram(z);
                }
                sLoadedSystemKeyStore = true;
            }
            if (sSystemTrustAnchorCache == null) {
                sSystemTrustAnchorCache = new HashSet();
            }
            if (sTestKeyStore == null) {
                sTestKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
                try {
                    sTestKeyStore.load((KeyStore.LoadStoreParameter) null);
                } catch (IOException e3) {
                }
            }
            if (sTestTrustManager == null) {
                sTestTrustManager = createTrustManager(sTestKeyStore);
            }
            if (!sDisableNativeCodeForTest && sTrustStorageListener == null) {
                sTrustStorageListener = new TrustStorageListener();
                nativeGetApplicationContext().registerReceiver(sTrustStorageListener, new IntentFilter("android.security.STORAGE_CHANGED"));
            }
        }
    }

    private static X509TrustManagerImplementation createTrustManager(KeyStore keyStore) throws KeyStoreException, NoSuchAlgorithmException {
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(keyStore);
        TrustManager[] arr$ = tmf.getTrustManagers();
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            TrustManager tm = arr$[i$];
            if (tm instanceof X509TrustManager) {
                try {
                    if (Build.VERSION.SDK_INT >= 17) {
                        return new X509TrustManagerJellyBean((X509TrustManager) tm);
                    }
                    return new X509TrustManagerIceCreamSandwich((X509TrustManager) tm);
                } catch (IllegalArgumentException e) {
                    Log.e(TAG, "Error creating trust manager (" + tm.getClass().getName() + "): " + e);
                }
            } else {
                i$++;
            }
        }
        Log.e(TAG, "Could not find suitable trust manager");
        return null;
    }

    private static void reloadTestTrustManager() throws KeyStoreException, NoSuchAlgorithmException {
        sTestTrustManager = createTrustManager(sTestKeyStore);
    }

    /* access modifiers changed from: private */
    public static void reloadDefaultTrustManager() throws KeyStoreException, NoSuchAlgorithmException, CertificateException {
        sDefaultTrustManager = null;
        sSystemTrustAnchorCache = null;
        nativeNotifyKeyChainChanged();
        ensureInitialized();
    }

    public static X509Certificate createCertificateFromBytes(byte[] derBytes) throws CertificateException, KeyStoreException, NoSuchAlgorithmException {
        ensureInitialized();
        return (X509Certificate) sCertificateFactory.generateCertificate(new ByteArrayInputStream(derBytes));
    }

    public static void addTestRootCertificate(byte[] rootCertBytes) throws CertificateException, KeyStoreException, NoSuchAlgorithmException {
        ensureInitialized();
        X509Certificate rootCert = createCertificateFromBytes(rootCertBytes);
        synchronized (sLock) {
            sTestKeyStore.setCertificateEntry("root_cert_" + Integer.toString(sTestKeyStore.size()), rootCert);
            reloadTestTrustManager();
        }
    }

    public static void clearTestRootCertificates() throws NoSuchAlgorithmException, CertificateException, KeyStoreException {
        ensureInitialized();
        synchronized (sLock) {
            try {
                sTestKeyStore.load((KeyStore.LoadStoreParameter) null);
                reloadTestTrustManager();
            } catch (IOException e) {
            }
        }
    }

    private static String hashPrincipal(X500Principal principal) throws NoSuchAlgorithmException {
        byte[] digest = MessageDigest.getInstance("MD5").digest(principal.getEncoded());
        char[] hexChars = new char[8];
        for (int i = 0; i < 4; i++) {
            hexChars[i * 2] = HEX_DIGITS[(digest[3 - i] >> 4) & 15];
            hexChars[(i * 2) + 1] = HEX_DIGITS[digest[3 - i] & 15];
        }
        return new String(hexChars);
    }

    private static boolean isKnownRoot(X509Certificate root) throws NoSuchAlgorithmException, KeyStoreException {
        if (sSystemKeyStore == null) {
            return false;
        }
        Pair<X500Principal, PublicKey> key = new Pair<>(root.getSubjectX500Principal(), root.getPublicKey());
        if (sSystemTrustAnchorCache.contains(key)) {
            return true;
        }
        String hash = hashPrincipal(root.getSubjectX500Principal());
        int i = 0;
        while (true) {
            String alias = hash + '.' + i;
            if (!new File(sSystemCertificateDirectory, alias).exists()) {
                return false;
            }
            Certificate anchor = sSystemKeyStore.getCertificate("system:" + alias);
            if (anchor != null) {
                if (!(anchor instanceof X509Certificate)) {
                    Log.e(TAG, "Anchor " + alias + " not an X509Certificate: " + anchor.getClass().getName());
                } else {
                    X509Certificate anchorX509 = (X509Certificate) anchor;
                    if (root.getSubjectX500Principal().equals(anchorX509.getSubjectX500Principal()) && root.getPublicKey().equals(anchorX509.getPublicKey())) {
                        sSystemTrustAnchorCache.add(key);
                        return true;
                    }
                }
            }
            i++;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x0016  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean verifyKeyUsage(java.security.cert.X509Certificate r7) throws java.security.cert.CertificateException {
        /*
            r4 = 1
            r5 = 0
            java.util.List r2 = r7.getExtendedKeyUsage()     // Catch:{ NullPointerException -> 0x0009 }
            if (r2 != 0) goto L_0x000c
        L_0x0008:
            return r4
        L_0x0009:
            r0 = move-exception
            r4 = r5
            goto L_0x0008
        L_0x000c:
            java.util.Iterator r3 = r2.iterator()
        L_0x0010:
            boolean r6 = r3.hasNext()
            if (r6 == 0) goto L_0x003d
            java.lang.Object r1 = r3.next()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r6 = "1.3.6.1.5.5.7.3.1"
            boolean r6 = r1.equals(r6)
            if (r6 != 0) goto L_0x0008
            java.lang.String r6 = "2.5.29.37.0"
            boolean r6 = r1.equals(r6)
            if (r6 != 0) goto L_0x0008
            java.lang.String r6 = "2.16.840.1.113730.4.1"
            boolean r6 = r1.equals(r6)
            if (r6 != 0) goto L_0x0008
            java.lang.String r6 = "1.3.6.1.4.1.311.10.3.3"
            boolean r6 = r1.equals(r6)
            if (r6 == 0) goto L_0x0010
            goto L_0x0008
        L_0x003d:
            r4 = r5
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.net.X509Util.verifyKeyUsage(java.security.cert.X509Certificate):boolean");
    }

    public static AndroidCertVerifyResult verifyServerCertificates(byte[][] certChain, String authType, String host) throws KeyStoreException, NoSuchAlgorithmException {
        List<X509Certificate> verifiedChain;
        if (certChain == null || certChain.length == 0 || certChain[0] == null) {
            throw new IllegalArgumentException("Expected non-null and non-empty certificate chain passed as |certChain|. |certChain|=" + Arrays.deepToString(certChain));
        }
        try {
            ensureInitialized();
            X509Certificate[] serverCertificates = new X509Certificate[certChain.length];
            int i = 0;
            while (i < certChain.length) {
                try {
                    serverCertificates[i] = createCertificateFromBytes(certChain[i]);
                    i++;
                } catch (CertificateException e) {
                    return new AndroidCertVerifyResult(-5);
                }
            }
            try {
                serverCertificates[0].checkValidity();
                if (!verifyKeyUsage(serverCertificates[0])) {
                    return new AndroidCertVerifyResult(-6);
                }
                synchronized (sLock) {
                    if (sDefaultTrustManager == null) {
                        AndroidCertVerifyResult androidCertVerifyResult = new AndroidCertVerifyResult(-1);
                        return androidCertVerifyResult;
                    }
                    try {
                        verifiedChain = sDefaultTrustManager.checkServerTrusted(serverCertificates, authType, host);
                    } catch (CertificateException eDefaultManager) {
                        try {
                            verifiedChain = sTestTrustManager.checkServerTrusted(serverCertificates, authType, host);
                        } catch (CertificateException e2) {
                            Log.i(TAG, "Failed to validate the certificate chain, error: " + eDefaultManager.getMessage());
                            return new AndroidCertVerifyResult(-2);
                        }
                    }
                    boolean isIssuedByKnownRoot = false;
                    if (verifiedChain.size() > 0) {
                        isIssuedByKnownRoot = isKnownRoot(verifiedChain.get(verifiedChain.size() - 1));
                    }
                    AndroidCertVerifyResult androidCertVerifyResult2 = new AndroidCertVerifyResult(0, isIssuedByKnownRoot, verifiedChain);
                    return androidCertVerifyResult2;
                }
            } catch (CertificateExpiredException e3) {
                return new AndroidCertVerifyResult(-3);
            } catch (CertificateNotYetValidException e4) {
                return new AndroidCertVerifyResult(-4);
            } catch (CertificateException e5) {
                return new AndroidCertVerifyResult(-1);
            }
        } catch (CertificateException e6) {
            return new AndroidCertVerifyResult(-1);
        }
    }

    public static void setDisableNativeCodeForTest(boolean disabled) {
        sDisableNativeCodeForTest = disabled;
    }
}
