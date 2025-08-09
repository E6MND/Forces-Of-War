package com.crashlytics.android;

import android.support.v4.media.TransportMediator;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.chromium.net.NetError;

/* renamed from: com.crashlytics.android.h  reason: case insensitive filesystem */
final class C0139h implements Flushable {
    private final byte[] a;
    private final int b;
    private int c = 0;
    private final OutputStream d;

    private C0139h(OutputStream outputStream, byte[] bArr) {
        this.d = outputStream;
        this.a = bArr;
        this.b = bArr.length;
    }

    public static C0139h a(OutputStream outputStream) {
        return new C0139h(outputStream, new byte[4096]);
    }

    public final void a(int i, float f) throws IOException {
        g(1, 5);
        int floatToRawIntBits = Float.floatToRawIntBits(f);
        d(floatToRawIntBits & 255);
        d((floatToRawIntBits >> 8) & 255);
        d((floatToRawIntBits >> 16) & 255);
        d(floatToRawIntBits >>> 24);
    }

    public final void a(int i, long j) throws IOException {
        g(i, 0);
        a(j);
    }

    public final void a(int i, boolean z) throws IOException {
        int i2 = 0;
        g(i, 0);
        if (z) {
            i2 = 1;
        }
        d(i2);
    }

    public final void a(int i, String str) throws IOException {
        g(1, 2);
        byte[] bytes = str.getBytes("UTF-8");
        b(bytes.length);
        a(bytes);
    }

    public final void a(int i, C0135d dVar) throws IOException {
        g(i, 2);
        b(dVar.a());
        int a2 = dVar.a();
        if (this.b - this.c >= a2) {
            dVar.a(this.a, 0, this.c, a2);
            this.c = a2 + this.c;
            return;
        }
        int i2 = this.b - this.c;
        dVar.a(this.a, 0, this.c, i2);
        int i3 = i2 + 0;
        int i4 = a2 - i2;
        this.c = this.b;
        a();
        if (i4 <= this.b) {
            dVar.a(this.a, i3, 0, i4);
            this.c = i4;
            return;
        }
        InputStream b2 = dVar.b();
        if (((long) i3) != b2.skip((long) i3)) {
            throw new IllegalStateException("Skip failed.");
        }
        while (i4 > 0) {
            int min = Math.min(i4, this.b);
            int read = b2.read(this.a, 0, min);
            if (read != min) {
                throw new IllegalStateException("Read failed.");
            }
            this.d.write(this.a, 0, read);
            i4 -= read;
        }
    }

    public final void a(int i, int i2) throws IOException {
        g(i, 0);
        b(i2);
    }

    public final void b(int i, int i2) throws IOException {
        g(i, 0);
        if (i2 >= 0) {
            b(i2);
        } else {
            a((long) i2);
        }
    }

    public final void c(int i, int i2) throws IOException {
        g(2, 0);
        b(e(i2));
    }

    public static int b(int i, float f) {
        return a(1) + 4;
    }

    public static int b(int i, long j) {
        return ((-128 & j) == 0 ? 1 : (-16384 & j) == 0 ? 2 : (-2097152 & j) == 0 ? 3 : (-268435456 & j) == 0 ? 4 : (-34359738368L & j) == 0 ? 5 : (-4398046511104L & j) == 0 ? 6 : (-562949953421312L & j) == 0 ? 7 : (-72057594037927936L & j) == 0 ? 8 : (Long.MIN_VALUE & j) == 0 ? 9 : 10) + a(i);
    }

    public static int b(int i, boolean z) {
        return a(i) + 1;
    }

    public static int b(int i, C0135d dVar) {
        return a(i) + c(dVar.a()) + dVar.a();
    }

    public static int d(int i, int i2) {
        return a(i) + c(i2);
    }

    public static int e(int i, int i2) {
        return (i2 >= 0 ? c(i2) : 10) + a(i);
    }

    public static int f(int i, int i2) {
        return a(2) + c(e(i2));
    }

    private void a() throws IOException {
        if (this.d == null) {
            throw new C0140i();
        }
        this.d.write(this.a, 0, this.c);
        this.c = 0;
    }

    public final void flush() throws IOException {
        if (this.d != null) {
            a();
        }
    }

    private void d(int i) throws IOException {
        byte b2 = (byte) i;
        if (this.c == this.b) {
            a();
        }
        byte[] bArr = this.a;
        int i2 = this.c;
        this.c = i2 + 1;
        bArr[i2] = b2;
    }

    public final void a(byte[] bArr) throws IOException {
        int length = bArr.length;
        if (this.b - this.c >= length) {
            System.arraycopy(bArr, 0, this.a, this.c, length);
            this.c = length + this.c;
            return;
        }
        int i = this.b - this.c;
        System.arraycopy(bArr, 0, this.a, this.c, i);
        int i2 = i + 0;
        int i3 = length - i;
        this.c = this.b;
        a();
        if (i3 <= this.b) {
            System.arraycopy(bArr, i2, this.a, 0, i3);
            this.c = i3;
            return;
        }
        this.d.write(bArr, i2, i3);
    }

    public final void g(int i, int i2) throws IOException {
        b(ae.a(i, i2));
    }

    public static int a(int i) {
        return c(ae.a(i, 0));
    }

    public final void b(int i) throws IOException {
        while ((i & NetError.ERR_SSL_UNSAFE_NEGOTIATION) != 0) {
            d((i & TransportMediator.KEYCODE_MEDIA_PAUSE) | 128);
            i >>>= 7;
        }
        d(i);
    }

    public static int c(int i) {
        if ((i & NetError.ERR_SSL_UNSAFE_NEGOTIATION) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        if ((-268435456 & i) == 0) {
            return 4;
        }
        return 5;
    }

    private void a(long j) throws IOException {
        while ((-128 & j) != 0) {
            d((((int) j) & TransportMediator.KEYCODE_MEDIA_PAUSE) | 128);
            j >>>= 7;
        }
        d((int) j);
    }

    private static int e(int i) {
        return (i << 1) ^ (i >> 31);
    }
}
