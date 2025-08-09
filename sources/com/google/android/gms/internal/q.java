package com.google.android.gms.internal;

import java.io.IOException;

class q implements o {
    private ma kl;
    private byte[] km;
    private final int kn;

    public q(int i) {
        this.kn = i;
        reset();
    }

    public void b(int i, long j) throws IOException {
        this.kl.b(i, j);
    }

    public void b(int i, String str) throws IOException {
        this.kl.b(i, str);
    }

    public void reset() {
        this.km = new byte[this.kn];
        this.kl = ma.q(this.km);
    }

    public byte[] z() throws IOException {
        int nL = this.kl.nL();
        if (nL < 0) {
            throw new IOException();
        } else if (nL == 0) {
            return this.km;
        } else {
            byte[] bArr = new byte[(this.km.length - nL)];
            System.arraycopy(this.km, 0, bArr, 0, bArr.length);
            return bArr;
        }
    }
}
