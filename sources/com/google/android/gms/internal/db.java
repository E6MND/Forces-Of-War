package com.google.android.gms.internal;

import android.text.TextUtils;
import android.util.Base64;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class db {
    public static boolean a(PublicKey publicKey, String str, String str2) {
        try {
            Signature instance = Signature.getInstance("SHA1withRSA");
            instance.initVerify(publicKey);
            instance.update(str.getBytes());
            if (instance.verify(Base64.decode(str2, 0))) {
                return true;
            }
            ev.A("Signature verification failed.");
            return false;
        } catch (NoSuchAlgorithmException e) {
            ev.A("NoSuchAlgorithmException.");
            return false;
        } catch (InvalidKeyException e2) {
            ev.A("Invalid key specification.");
            return false;
        } catch (SignatureException e3) {
            ev.A("Signature exception.");
            return false;
        }
    }

    public static boolean b(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str3)) {
            return a(r(str), str2, str3);
        }
        ev.A("Purchase verification failed: missing data.");
        return false;
    }

    public static PublicKey r(String str) {
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str, 0)));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e2) {
            ev.A("Invalid key specification.");
            throw new IllegalArgumentException(e2);
        }
    }
}
