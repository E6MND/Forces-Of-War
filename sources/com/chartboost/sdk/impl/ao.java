package com.chartboost.sdk.impl;

import java.io.IOException;
import java.io.OutputStream;

public class ao extends ap {
    private int a;
    private int b;
    private byte[] c = new byte[512];

    public void write(byte[] b2) {
        write(b2, 0, b2.length);
    }

    public void write(byte[] b2, int off, int len) {
        b(len);
        System.arraycopy(b2, off, this.c, this.a, len);
        this.a += len;
        this.b = Math.max(this.a, this.b);
    }

    public void write(int b2) {
        b(1);
        byte[] bArr = this.c;
        int i = this.a;
        this.a = i + 1;
        bArr[i] = (byte) (b2 & 255);
        this.b = Math.max(this.a, this.b);
    }

    public int a() {
        return this.a;
    }

    public void a(int i) {
        this.a = i;
    }

    public int b() {
        return this.b;
    }

    public int a(OutputStream outputStream) throws IOException {
        outputStream.write(this.c, 0, this.b);
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public void b(int i) {
        int i2 = this.a + i;
        if (i2 >= this.c.length) {
            int length = this.c.length * 2;
            if (length <= i2) {
                length = i2 + 128;
            }
            byte[] bArr = new byte[length];
            System.arraycopy(this.c, 0, bArr, 0, this.b);
            this.c = bArr;
        }
    }
}
