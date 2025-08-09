package com.chartboost.sdk.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class aq extends ap {
    private static bh<byte[]> g = new bh<byte[]>(640) {
        /* access modifiers changed from: protected */
        /* renamed from: a */
        public byte[] b() {
            return new byte[16384];
        }
    };
    final byte[] a = new byte[16384];
    final char[] b = new char[16384];
    final List<byte[]> c = new ArrayList();
    final ar d = new ar();
    private final a e = new a();
    private final a f = new a();

    public aq() {
        d();
    }

    public void d() {
        this.e.a();
        this.f.a();
        for (int i = 0; i < this.c.size(); i++) {
            g.b(this.c.get(i));
        }
        this.c.clear();
    }

    public int a() {
        return this.e.b();
    }

    public void a(int i) {
        this.e.a(i);
    }

    public int b() {
        return this.f.b();
    }

    public void write(byte[] b2) {
        write(b2, 0, b2.length);
    }

    public void write(byte[] b2, int off, int len) {
        while (len > 0) {
            byte[] f2 = f();
            int min = Math.min(f2.length - this.e.b, len);
            System.arraycopy(b2, off, f2, this.e.b, min);
            this.e.b(min);
            len -= min;
            off += min;
            e();
        }
    }

    public void write(int b2) {
        f()[this.e.c()] = (byte) (b2 & 255);
        e();
    }

    /* access modifiers changed from: package-private */
    public void e() {
        if (this.e.b() >= this.f.b()) {
            this.f.a(this.e);
            if (this.f.b >= 16384) {
                this.c.add(g.c());
                this.f.d();
                this.e.a(this.f);
            }
        } else if (this.e.b == 16384) {
            this.e.d();
        }
    }

    /* access modifiers changed from: package-private */
    public byte[] f() {
        return b(this.e.a);
    }

    /* access modifiers changed from: package-private */
    public byte[] b(int i) {
        if (i < 0) {
            return this.a;
        }
        return this.c.get(i);
    }

    public int a(OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new NullPointerException("out is null");
        }
        int i = 0;
        for (int i2 = -1; i2 < this.c.size(); i2++) {
            byte[] b2 = b(i2);
            int c2 = this.f.c(i2);
            outputStream.write(b2, 0, c2);
            i += c2;
        }
        return i;
    }

    static class a {
        int a;
        int b;

        a() {
            a();
        }

        /* access modifiers changed from: package-private */
        public void a() {
            this.a = -1;
            this.b = 0;
        }

        /* access modifiers changed from: package-private */
        public void a(a aVar) {
            this.a = aVar.a;
            this.b = aVar.b;
        }

        /* access modifiers changed from: package-private */
        public void a(int i) {
            this.a = (i / 16384) - 1;
            this.b = i % 16384;
        }

        /* access modifiers changed from: package-private */
        public int b() {
            return ((this.a + 1) * 16384) + this.b;
        }

        /* access modifiers changed from: package-private */
        public int c() {
            int i = this.b;
            this.b = i + 1;
            return i;
        }

        /* access modifiers changed from: package-private */
        public void b(int i) {
            this.b += i;
            if (this.b > 16384) {
                throw new IllegalArgumentException("something is wrong");
            }
        }

        /* access modifiers changed from: package-private */
        public void d() {
            if (this.b != 16384) {
                throw new IllegalArgumentException("broken");
            }
            this.a++;
            this.b = 0;
        }

        /* access modifiers changed from: package-private */
        public int c(int i) {
            if (i < this.a) {
                return 16384;
            }
            return this.b;
        }

        public String toString() {
            return this.a + "," + this.b;
        }
    }
}
