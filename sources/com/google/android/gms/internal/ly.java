package com.google.android.gms.internal;

public class ly {
    private final byte[] amH = new byte[256];
    private int amI;
    private int amJ;

    public ly(byte[] bArr) {
        for (int i = 0; i < 256; i++) {
            this.amH[i] = (byte) i;
        }
        byte b = 0;
        for (int i2 = 0; i2 < 256; i2++) {
            b = (b + this.amH[i2] + bArr[i2 % bArr.length]) & 255;
            byte b2 = this.amH[i2];
            this.amH[i2] = this.amH[b];
            this.amH[b] = b2;
        }
        this.amI = 0;
        this.amJ = 0;
    }

    public void o(byte[] bArr) {
        int i = this.amI;
        int i2 = this.amJ;
        for (int i3 = 0; i3 < bArr.length; i3++) {
            i = (i + 1) & 255;
            i2 = (i2 + this.amH[i]) & 255;
            byte b = this.amH[i];
            this.amH[i] = this.amH[i2];
            this.amH[i2] = b;
            bArr[i3] = (byte) (bArr[i3] ^ this.amH[(this.amH[i] + this.amH[i2]) & 255]);
        }
        this.amI = i;
        this.amJ = i2;
    }
}
