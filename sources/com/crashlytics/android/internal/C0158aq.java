package com.crashlytics.android.internal;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: com.crashlytics.android.internal.aq  reason: case insensitive filesystem */
public class C0158aq implements Closeable {
    private static final Logger a = Logger.getLogger(C0158aq.class.getName());
    /* access modifiers changed from: private */
    public final RandomAccessFile b;
    private int c;
    private int d;
    private C0160as e;
    private C0160as f;
    private final byte[] g = new byte[16];

    /* JADX INFO: finally extract failed */
    public C0158aq(File file) throws IOException {
        if (!file.exists()) {
            File file2 = new File(file.getPath() + ".tmp");
            RandomAccessFile a2 = a(file2);
            try {
                a2.setLength(4096);
                a2.seek(0);
                byte[] bArr = new byte[16];
                a(bArr, 4096, 0, 0, 0);
                a2.write(bArr);
                a2.close();
                if (!file2.renameTo(file)) {
                    throw new IOException("Rename failed!");
                }
            } catch (Throwable th) {
                a2.close();
                throw th;
            }
        }
        this.b = a(file);
        this.b.seek(0);
        this.b.readFully(this.g);
        this.c = a(this.g, 0);
        if (((long) this.c) > this.b.length()) {
            throw new IOException("File is truncated. Expected length: " + this.c + ", Actual length: " + this.b.length());
        }
        this.d = a(this.g, 4);
        int a3 = a(this.g, 8);
        int a4 = a(this.g, 12);
        this.e = a(a3);
        this.f = a(a4);
    }

    private static void a(byte[] bArr, int i, int i2) {
        bArr[i] = i2 >> 24;
        bArr[i + 1] = (byte) (i2 >> 16);
        bArr[i + 2] = (byte) (i2 >> 8);
        bArr[i + 3] = (byte) i2;
    }

    private static void a(byte[] bArr, int... iArr) {
        int i = 0;
        for (int a2 : iArr) {
            a(bArr, i, a2);
            i += 4;
        }
    }

    private static int a(byte[] bArr, int i) {
        return ((bArr[i] & 255) << 24) + ((bArr[i + 1] & 255) << 16) + ((bArr[i + 2] & 255) << 8) + (bArr[i + 3] & 255);
    }

    private void a(int i, int i2, int i3, int i4) throws IOException {
        a(this.g, i, i2, i3, i4);
        this.b.seek(0);
        this.b.write(this.g);
    }

    private C0160as a(int i) throws IOException {
        if (i == 0) {
            return C0160as.a;
        }
        this.b.seek((long) i);
        return new C0160as(i, this.b.readInt());
    }

    private static RandomAccessFile a(File file) throws FileNotFoundException {
        return new RandomAccessFile(file, "rwd");
    }

    /* access modifiers changed from: private */
    public int b(int i) {
        return i < this.c ? i : (i + 16) - this.c;
    }

    private void a(int i, byte[] bArr, int i2, int i3) throws IOException {
        int b2 = b(i);
        if (b2 + i3 <= this.c) {
            this.b.seek((long) b2);
            this.b.write(bArr, i2, i3);
            return;
        }
        int i4 = this.c - b2;
        this.b.seek((long) b2);
        this.b.write(bArr, i2, i4);
        this.b.seek(16);
        this.b.write(bArr, i2 + i4, i3 - i4);
    }

    /* access modifiers changed from: private */
    public void b(int i, byte[] bArr, int i2, int i3) throws IOException {
        int b2 = b(i);
        if (b2 + i3 <= this.c) {
            this.b.seek((long) b2);
            this.b.readFully(bArr, i2, i3);
            return;
        }
        int i4 = this.c - b2;
        this.b.seek((long) b2);
        this.b.readFully(bArr, i2, i4);
        this.b.seek(16);
        this.b.readFully(bArr, i2 + i4, i3 - i4);
    }

    public final void a(byte[] bArr) throws IOException {
        b(bArr, 0, bArr.length);
    }

    private synchronized void b(byte[] bArr, int i, int i2) throws IOException {
        int b2;
        b(bArr, "buffer");
        if ((i2 | 0) < 0 || i2 > bArr.length) {
            throw new IndexOutOfBoundsException();
        }
        c(i2);
        boolean b3 = b();
        if (b3) {
            b2 = 16;
        } else {
            b2 = b(this.f.b + 4 + this.f.c);
        }
        C0160as asVar = new C0160as(b2, i2);
        a(this.g, 0, i2);
        a(asVar.b, this.g, 0, 4);
        a(asVar.b + 4, bArr, 0, i2);
        a(this.c, this.d + 1, b3 ? asVar.b : this.e.b, asVar.b);
        this.f = asVar;
        this.d++;
        if (b3) {
            this.e = this.f;
        }
    }

    public final int a() {
        if (this.d == 0) {
            return 16;
        }
        if (this.f.b >= this.e.b) {
            return (this.f.b - this.e.b) + 4 + this.f.c + 16;
        }
        return (((this.f.b + 4) + this.f.c) + this.c) - this.e.b;
    }

    public final synchronized boolean b() {
        return this.d == 0;
    }

    private void c(int i) throws IOException {
        int i2 = i + 4;
        int a2 = this.c - a();
        if (a2 < i2) {
            int i3 = this.c;
            do {
                a2 += i3;
                i3 <<= 1;
            } while (a2 < i2);
            d(i3);
            int b2 = b(this.f.b + 4 + this.f.c);
            if (b2 < this.e.b) {
                FileChannel channel = this.b.getChannel();
                channel.position((long) this.c);
                int i4 = b2 - 4;
                if (channel.transferTo(16, (long) i4, channel) != ((long) i4)) {
                    throw new AssertionError("Copied insufficient number of bytes!");
                }
            }
            if (this.f.b < this.e.b) {
                int i5 = (this.c + this.f.b) - 16;
                a(i3, this.d, this.e.b, i5);
                this.f = new C0160as(i5, this.f.c);
            } else {
                a(i3, this.d, this.e.b, this.f.b);
            }
            this.c = i3;
        }
    }

    private void d(int i) throws IOException {
        this.b.setLength((long) i);
        this.b.getChannel().force(true);
    }

    public final synchronized void a(C0162au auVar) throws IOException {
        synchronized (this) {
            int i = this.e.b;
            for (int i2 = 0; i2 < this.d; i2++) {
                C0160as a2 = a(i);
                auVar.a(new C0161at(this, a2, (byte) 0), a2.c);
                i = b(a2.c + a2.b + 4);
            }
        }
    }

    /* access modifiers changed from: private */
    public static <T> T b(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public final synchronized void c() throws IOException {
        if (b()) {
            throw new NoSuchElementException();
        } else if (this.d == 1) {
            d();
        } else {
            int b2 = b(this.e.b + 4 + this.e.c);
            b(b2, this.g, 0, 4);
            int a2 = a(this.g, 0);
            a(this.c, this.d - 1, b2, this.f.b);
            this.d--;
            this.e = new C0160as(b2, a2);
        }
    }

    private synchronized void d() throws IOException {
        a(4096, 0, 0, 0);
        this.d = 0;
        this.e = C0160as.a;
        this.f = C0160as.a;
        if (this.c > 4096) {
            d(4096);
        }
        this.c = 4096;
    }

    public synchronized void close() throws IOException {
        this.b.close();
    }

    public final boolean a(int i, int i2) {
        return (a() + 4) + i <= i2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()).append('[');
        sb.append("fileLength=").append(this.c);
        sb.append(", size=").append(this.d);
        sb.append(", first=").append(this.e);
        sb.append(", last=").append(this.f);
        sb.append(", element lengths=[");
        try {
            a((C0162au) new C0159ar(this, sb));
        } catch (IOException e2) {
            a.log(Level.WARNING, "read error", e2);
        }
        sb.append("]]");
        return sb.toString();
    }
}
