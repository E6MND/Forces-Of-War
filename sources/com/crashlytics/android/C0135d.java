package com.crashlytics.android;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/* renamed from: com.crashlytics.android.d  reason: case insensitive filesystem */
final class C0135d {
    private final byte[] a;
    private volatile int b = 0;

    private C0135d(byte[] bArr) {
        this.a = bArr;
    }

    public final int a() {
        return this.a.length;
    }

    static {
        new C0135d(new byte[0]);
    }

    public static C0135d a(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, 0, bArr2, 0, i2);
        return new C0135d(bArr2);
    }

    public static C0135d a(String str) {
        try {
            return new C0135d(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported.", e);
        }
    }

    public final void a(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.a, i, bArr, i2, i3);
    }

    public final boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof C0135d)) {
            return false;
        }
        C0135d dVar = (C0135d) o;
        int length = this.a.length;
        if (length != dVar.a.length) {
            return false;
        }
        byte[] bArr = this.a;
        byte[] bArr2 = dVar.a;
        for (int i = 0; i < length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = this.b;
        if (i == 0) {
            byte[] bArr = this.a;
            int length = this.a.length;
            int i2 = 0;
            i = length;
            while (i2 < length) {
                i2++;
                i = bArr[i2] + (i * 31);
            }
            if (i == 0) {
                i = 1;
            }
            this.b = i;
        }
        return i;
    }

    public final InputStream b() {
        return new ByteArrayInputStream(this.a);
    }
}
