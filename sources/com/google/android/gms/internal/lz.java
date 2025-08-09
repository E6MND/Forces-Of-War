package com.google.android.gms.internal;

import java.io.IOException;
import org.chromium.ui.base.PageTransition;

public final class lz {
    private int amK;
    private int amL;
    private int amM;
    private int amN;
    private int amO;
    private int amP = Integer.MAX_VALUE;
    private int amQ;
    private int amR = 64;
    private int amS = PageTransition.HOME_PAGE;
    private final byte[] buffer;

    private lz(byte[] bArr, int i, int i2) {
        this.buffer = bArr;
        this.amK = i;
        this.amL = i + i2;
        this.amN = i;
    }

    public static long A(long j) {
        return (j >>> 1) ^ (-(1 & j));
    }

    public static lz a(byte[] bArr, int i, int i2) {
        return new lz(bArr, i, i2);
    }

    public static int ew(int i) {
        return (i >>> 1) ^ (-(i & 1));
    }

    private void nH() {
        this.amL += this.amM;
        int i = this.amL;
        if (i > this.amP) {
            this.amM = i - this.amP;
            this.amL -= this.amM;
            return;
        }
        this.amM = 0;
    }

    public static lz p(byte[] bArr) {
        return a(bArr, 0, bArr.length);
    }

    public void a(mf mfVar) throws IOException {
        int nD = nD();
        if (this.amQ >= this.amR) {
            throw me.nT();
        }
        int ex = ex(nD);
        this.amQ++;
        mfVar.b(this);
        eu(0);
        this.amQ--;
        ey(ex);
    }

    public void a(mf mfVar, int i) throws IOException {
        if (this.amQ >= this.amR) {
            throw me.nT();
        }
        this.amQ++;
        mfVar.b(this);
        eu(mi.u(i, 4));
        this.amQ--;
    }

    public byte[] eA(int i) throws IOException {
        if (i < 0) {
            throw me.nO();
        } else if (this.amN + i > this.amP) {
            eB(this.amP - this.amN);
            throw me.nN();
        } else if (i <= this.amL - this.amN) {
            byte[] bArr = new byte[i];
            System.arraycopy(this.buffer, this.amN, bArr, 0, i);
            this.amN += i;
            return bArr;
        } else {
            throw me.nN();
        }
    }

    public void eB(int i) throws IOException {
        if (i < 0) {
            throw me.nO();
        } else if (this.amN + i > this.amP) {
            eB(this.amP - this.amN);
            throw me.nN();
        } else if (i <= this.amL - this.amN) {
            this.amN += i;
        } else {
            throw me.nN();
        }
    }

    public void eu(int i) throws me {
        if (this.amO != i) {
            throw me.nR();
        }
    }

    public boolean ev(int i) throws IOException {
        switch (mi.eN(i)) {
            case 0:
                nz();
                return true;
            case 1:
                nG();
                return true;
            case 2:
                eB(nD());
                return true;
            case 3:
                nx();
                eu(mi.u(mi.eO(i), 4));
                return true;
            case 4:
                return false;
            case 5:
                nF();
                return true;
            default:
                throw me.nS();
        }
    }

    public int ex(int i) throws me {
        if (i < 0) {
            throw me.nO();
        }
        int i2 = this.amN + i;
        int i3 = this.amP;
        if (i2 > i3) {
            throw me.nN();
        }
        this.amP = i2;
        nH();
        return i3;
    }

    public void ey(int i) {
        this.amP = i;
        nH();
    }

    public void ez(int i) {
        if (i > this.amN - this.amK) {
            throw new IllegalArgumentException("Position " + i + " is beyond current " + (this.amN - this.amK));
        } else if (i < 0) {
            throw new IllegalArgumentException("Bad position " + i);
        } else {
            this.amN = this.amK + i;
        }
    }

    public int getPosition() {
        return this.amN - this.amK;
    }

    public boolean nA() throws IOException {
        return nD() != 0;
    }

    public int nB() throws IOException {
        return ew(nD());
    }

    public long nC() throws IOException {
        return A(nE());
    }

    public int nD() throws IOException {
        byte nK = nK();
        if (nK >= 0) {
            return nK;
        }
        byte b = nK & Byte.MAX_VALUE;
        byte nK2 = nK();
        if (nK2 >= 0) {
            return b | (nK2 << 7);
        }
        byte b2 = b | ((nK2 & Byte.MAX_VALUE) << 7);
        byte nK3 = nK();
        if (nK3 >= 0) {
            return b2 | (nK3 << 14);
        }
        byte b3 = b2 | ((nK3 & Byte.MAX_VALUE) << 14);
        byte nK4 = nK();
        if (nK4 >= 0) {
            return b3 | (nK4 << 21);
        }
        byte b4 = b3 | ((nK4 & Byte.MAX_VALUE) << 21);
        byte nK5 = nK();
        byte b5 = b4 | (nK5 << 28);
        if (nK5 >= 0) {
            return b5;
        }
        for (int i = 0; i < 5; i++) {
            if (nK() >= 0) {
                return b5;
            }
        }
        throw me.nP();
    }

    public long nE() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte nK = nK();
            j |= ((long) (nK & Byte.MAX_VALUE)) << i;
            if ((nK & 128) == 0) {
                return j;
            }
        }
        throw me.nP();
    }

    public int nF() throws IOException {
        return (nK() & 255) | ((nK() & 255) << 8) | ((nK() & 255) << 16) | ((nK() & 255) << 24);
    }

    public long nG() throws IOException {
        byte nK = nK();
        byte nK2 = nK();
        return ((((long) nK2) & 255) << 8) | (((long) nK) & 255) | ((((long) nK()) & 255) << 16) | ((((long) nK()) & 255) << 24) | ((((long) nK()) & 255) << 32) | ((((long) nK()) & 255) << 40) | ((((long) nK()) & 255) << 48) | ((((long) nK()) & 255) << 56);
    }

    public int nI() {
        if (this.amP == Integer.MAX_VALUE) {
            return -1;
        }
        return this.amP - this.amN;
    }

    public boolean nJ() {
        return this.amN == this.amL;
    }

    public byte nK() throws IOException {
        if (this.amN == this.amL) {
            throw me.nN();
        }
        byte[] bArr = this.buffer;
        int i = this.amN;
        this.amN = i + 1;
        return bArr[i];
    }

    public int nw() throws IOException {
        if (nJ()) {
            this.amO = 0;
            return 0;
        }
        this.amO = nD();
        if (this.amO != 0) {
            return this.amO;
        }
        throw me.nQ();
    }

    /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public void nx() throws java.io.IOException {
        /*
            r1 = this;
        L_0x0000:
            int r0 = r1.nw()
            if (r0 == 0) goto L_0x000c
            boolean r0 = r1.ev(r0)
            if (r0 != 0) goto L_0x0000
        L_0x000c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.lz.nx():void");
    }

    public long ny() throws IOException {
        return nE();
    }

    public int nz() throws IOException {
        return nD();
    }

    public byte[] o(int i, int i2) {
        if (i2 == 0) {
            return mi.anh;
        }
        byte[] bArr = new byte[i2];
        System.arraycopy(this.buffer, this.amK + i, bArr, 0, i2);
        return bArr;
    }

    public byte[] readBytes() throws IOException {
        int nD = nD();
        if (nD > this.amL - this.amN || nD <= 0) {
            return eA(nD);
        }
        byte[] bArr = new byte[nD];
        System.arraycopy(this.buffer, this.amN, bArr, 0, nD);
        this.amN = nD + this.amN;
        return bArr;
    }

    public double readDouble() throws IOException {
        return Double.longBitsToDouble(nG());
    }

    public float readFloat() throws IOException {
        return Float.intBitsToFloat(nF());
    }

    public String readString() throws IOException {
        int nD = nD();
        if (nD > this.amL - this.amN || nD <= 0) {
            return new String(eA(nD), "UTF-8");
        }
        String str = new String(this.buffer, this.amN, nD, "UTF-8");
        this.amN = nD + this.amN;
        return str;
    }
}
