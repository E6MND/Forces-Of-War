package com.google.android.gms.internal;

import java.io.IOException;

public abstract class mf {
    protected volatile int amY = -1;

    public static final <T extends mf> T a(T t, byte[] bArr) throws me {
        return b(t, bArr, 0, bArr.length);
    }

    public static final void a(mf mfVar, byte[] bArr, int i, int i2) {
        try {
            ma b = ma.b(bArr, i, i2);
            mfVar.a(b);
            b.nM();
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    public static final <T extends mf> T b(T t, byte[] bArr, int i, int i2) throws me {
        try {
            lz a = lz.a(bArr, i, i2);
            t.b(a);
            a.eu(0);
            return t;
        } catch (me e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).");
        }
    }

    public static final byte[] d(mf mfVar) {
        byte[] bArr = new byte[mfVar.nV()];
        a(mfVar, bArr, 0, bArr.length);
        return bArr;
    }

    public void a(ma maVar) throws IOException {
    }

    public abstract mf b(lz lzVar) throws IOException;

    /* access modifiers changed from: protected */
    public int c() {
        return 0;
    }

    public int nU() {
        if (this.amY < 0) {
            nV();
        }
        return this.amY;
    }

    public int nV() {
        int c = c();
        this.amY = c;
        return c;
    }

    public String toString() {
        return mg.e(this);
    }
}
