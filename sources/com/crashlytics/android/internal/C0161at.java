package com.crashlytics.android.internal;

import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.crashlytics.android.internal.at  reason: case insensitive filesystem */
final class C0161at extends InputStream {
    private int a;
    private int b;
    private /* synthetic */ C0158aq c;

    /* synthetic */ C0161at(C0158aq aqVar, C0160as asVar, byte b2) {
        this(aqVar, asVar);
    }

    private C0161at(C0158aq aqVar, C0160as asVar) {
        this.c = aqVar;
        this.a = aqVar.b(asVar.b + 4);
        this.b = asVar.c;
    }

    public final int read(byte[] buffer, int offset, int length) throws IOException {
        Object unused = C0158aq.b(buffer, "buffer");
        if ((offset | length) < 0 || length > buffer.length - offset) {
            throw new ArrayIndexOutOfBoundsException();
        } else if (this.b <= 0) {
            return -1;
        } else {
            if (length > this.b) {
                length = this.b;
            }
            this.c.b(this.a, buffer, offset, length);
            this.a = this.c.b(this.a + length);
            this.b -= length;
            return length;
        }
    }

    public final int read() throws IOException {
        if (this.b == 0) {
            return -1;
        }
        this.c.b.seek((long) this.a);
        int read = this.c.b.read();
        this.a = this.c.b(this.a + 1);
        this.b--;
        return read;
    }
}
