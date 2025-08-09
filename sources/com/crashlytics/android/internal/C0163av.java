package com.crashlytics.android.internal;

import com.crashlytics.android.Crashlytics;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/* renamed from: com.crashlytics.android.internal.av  reason: case insensitive filesystem */
public class C0163av {
    private final C0183q a;
    private aG b;
    private SSLSocketFactory c;
    private boolean d;

    public static X509Certificate[] a(X509Certificate[] x509CertificateArr, aI aIVar) throws CertificateException {
        boolean z;
        boolean z2 = true;
        LinkedList linkedList = new LinkedList();
        if (aIVar.a(x509CertificateArr[0])) {
            z = true;
        } else {
            z = false;
        }
        linkedList.add(x509CertificateArr[0]);
        boolean z3 = z;
        int i = 1;
        while (i < x509CertificateArr.length) {
            if (aIVar.a(x509CertificateArr[i])) {
                z3 = true;
            }
            if (!a(x509CertificateArr[i], x509CertificateArr[i - 1])) {
                break;
            }
            linkedList.add(x509CertificateArr[i]);
            i++;
        }
        X509Certificate b2 = aIVar.b(x509CertificateArr[i - 1]);
        if (b2 != null) {
            linkedList.add(b2);
        } else {
            z2 = z3;
        }
        if (z2) {
            return (X509Certificate[]) linkedList.toArray(new X509Certificate[linkedList.size()]);
        }
        throw new CertificateException("Didn't find a trust anchor in chain cleanup!");
    }

    public C0163av() {
        this(new C0184r());
    }

    public C0163av(C0183q qVar) {
        this.a = qVar;
    }

    public void a(aG aGVar) {
        if (this.b != aGVar) {
            this.b = aGVar;
            a();
        }
    }

    private synchronized void a() {
        this.d = false;
        this.c = null;
    }

    public C0166ay a(C0165ax axVar, String str) {
        return a(axVar, str, Collections.emptyMap());
    }

    private static boolean a(X509Certificate x509Certificate, X509Certificate x509Certificate2) {
        if (!x509Certificate.getSubjectX500Principal().equals(x509Certificate2.getIssuerX500Principal())) {
            return false;
        }
        try {
            x509Certificate2.verify(x509Certificate.getPublicKey());
            return true;
        } catch (GeneralSecurityException e) {
            return false;
        }
    }

    public C0166ay a(C0165ax axVar, String str, Map<String, String> map) {
        C0166ay b2;
        boolean startsWith;
        SSLSocketFactory b3;
        switch (C0164aw.a[axVar.ordinal()]) {
            case 1:
                b2 = C0166ay.a((CharSequence) str, (Map<?, ?>) map, true);
                break;
            case 2:
                b2 = C0166ay.b((CharSequence) str, (Map<?, ?>) map, true);
                break;
            case 3:
                b2 = C0166ay.a((CharSequence) str);
                break;
            case 4:
                b2 = C0166ay.b((CharSequence) str);
                break;
            default:
                throw new IllegalArgumentException("Unsupported HTTP method!");
        }
        if (str == null) {
            startsWith = false;
        } else {
            startsWith = str.toLowerCase().startsWith("https");
        }
        if (!(!startsWith || this.b == null || (b3 = b()) == null)) {
            ((HttpsURLConnection) b2.a()).setSSLSocketFactory(b3);
        }
        return b2;
    }

    private synchronized SSLSocketFactory b() {
        if (this.c == null && !this.d) {
            this.c = c();
        }
        return this.c;
    }

    private synchronized SSLSocketFactory c() {
        SSLSocketFactory sSLSocketFactory;
        this.d = true;
        try {
            aG aGVar = this.b;
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init((KeyManager[]) null, new TrustManager[]{new aH(new aI(aGVar.a(), aGVar.b()), aGVar)}, (SecureRandom) null);
            sSLSocketFactory = instance.getSocketFactory();
            this.a.a(Crashlytics.TAG, "Custom SSL pinning enabled");
        } catch (Exception e) {
            this.a.a(Crashlytics.TAG, "Exception while validating pinned certs", (Throwable) e);
            sSLSocketFactory = null;
        }
        return sSLSocketFactory;
    }
}
