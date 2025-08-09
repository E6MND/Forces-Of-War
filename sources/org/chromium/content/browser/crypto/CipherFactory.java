package org.chromium.content.browser.crypto;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import javax.annotation.concurrent.ThreadSafe;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.chromium.base.SecureRandomInitializer;

@ThreadSafe
public class CipherFactory {
    static final String BUNDLE_IV = "org.chromium.content.browser.crypto.CipherFactory.IV";
    static final String BUNDLE_KEY = "org.chromium.content.browser.crypto.CipherFactory.KEY";
    static final int NUM_BYTES = 16;
    private static final String TAG = "CipherFactory";
    private CipherData mData;
    private FutureTask<CipherData> mDataGenerator;
    private final Object mDataLock;
    /* access modifiers changed from: private */
    public ByteArrayGenerator mRandomNumberProvider;

    private static class CipherData {
        public final byte[] iv;
        public final Key key;

        public CipherData(Key key2, byte[] iv2) {
            this.key = key2;
            this.iv = iv2;
        }
    }

    private static class LazyHolder {
        /* access modifiers changed from: private */
        public static CipherFactory sInstance = new CipherFactory();

        private LazyHolder() {
        }
    }

    public static CipherFactory getInstance() {
        return LazyHolder.sInstance;
    }

    public Cipher getCipher(int opmode) {
        CipherData data = getCipherData(true);
        if (data != null) {
            try {
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(opmode, data.key, new IvParameterSpec(data.iv));
                return cipher;
            } catch (GeneralSecurityException e) {
            }
        }
        Log.e(TAG, "Error in creating cipher instance.");
        return null;
    }

    /* access modifiers changed from: package-private */
    public CipherData getCipherData(boolean generateIfNeeded) {
        if (this.mData == null && generateIfNeeded) {
            triggerKeyGeneration();
            try {
                CipherData data = this.mDataGenerator.get();
                synchronized (this.mDataLock) {
                    if (this.mData == null) {
                        this.mData = data;
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e2) {
                throw new RuntimeException(e2);
            }
        }
        return this.mData;
    }

    private Callable<CipherData> createGeneratorCallable() {
        return new Callable<CipherData>() {
            public CipherData call() {
                try {
                    byte[] iv = CipherFactory.this.mRandomNumberProvider.getBytes(16);
                    try {
                        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
                        SecureRandomInitializer.initialize(random);
                        KeyGenerator generator = KeyGenerator.getInstance("AES");
                        generator.init(128, random);
                        return new CipherData(generator.generateKey(), iv);
                    } catch (IOException e) {
                        Log.e(CipherFactory.TAG, "Couldn't get generator data.");
                        return null;
                    } catch (GeneralSecurityException e2) {
                        Log.e(CipherFactory.TAG, "Couldn't get generator instances.");
                        return null;
                    }
                } catch (IOException e3) {
                    Log.e(CipherFactory.TAG, "Couldn't get generator data.");
                    return null;
                } catch (GeneralSecurityException e4) {
                    Log.e(CipherFactory.TAG, "Couldn't get generator data.");
                    return null;
                }
            }
        };
    }

    public void triggerKeyGeneration() {
        if (this.mData == null) {
            synchronized (this.mDataLock) {
                if (this.mDataGenerator == null) {
                    this.mDataGenerator = new FutureTask<>(createGeneratorCallable());
                    AsyncTask.THREAD_POOL_EXECUTOR.execute(this.mDataGenerator);
                }
            }
        }
    }

    public void saveToBundle(Bundle outState) {
        byte[] wrappedKey;
        CipherData data = getCipherData(false);
        if (data != null && (wrappedKey = data.key.getEncoded()) != null && data.iv != null) {
            outState.putByteArray(BUNDLE_KEY, wrappedKey);
            outState.putByteArray(BUNDLE_IV, data.iv);
        }
    }

    public boolean restoreFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return false;
        }
        byte[] wrappedKey = savedInstanceState.getByteArray(BUNDLE_KEY);
        byte[] iv = savedInstanceState.getByteArray(BUNDLE_IV);
        if (wrappedKey == null || iv == null) {
            return false;
        }
        try {
            Key bundledKey = new SecretKeySpec(wrappedKey, "AES");
            synchronized (this.mDataLock) {
                if (this.mData == null) {
                    this.mData = new CipherData(bundledKey, iv);
                    return true;
                } else if (this.mData.key.equals(bundledKey) && Arrays.equals(this.mData.iv, iv)) {
                    return true;
                } else {
                    Log.e(TAG, "Attempted to restore different cipher data.");
                    return false;
                }
            }
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Error in restoring the key from the bundle.");
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void setRandomNumberProviderForTests(ByteArrayGenerator mockProvider) {
        this.mRandomNumberProvider = mockProvider;
    }

    private CipherFactory() {
        this.mDataLock = new Object();
        this.mRandomNumberProvider = new ByteArrayGenerator();
    }
}
