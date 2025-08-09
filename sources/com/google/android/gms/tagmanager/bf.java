package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.ik;

class bf implements cf {
    private final ik aec;
    private final long agc;
    private final long wB;
    private final int wC;
    private double wD;
    private long wE;
    private final Object wF = new Object();
    private final String wG;

    public bf(int i, long j, long j2, String str, ik ikVar) {
        this.wC = i;
        this.wD = (double) this.wC;
        this.wB = j;
        this.agc = j2;
        this.wG = str;
        this.aec = ikVar;
    }

    public boolean dj() {
        boolean z = false;
        synchronized (this.wF) {
            long currentTimeMillis = this.aec.currentTimeMillis();
            if (currentTimeMillis - this.wE < this.agc) {
                bh.D("Excessive " + this.wG + " detected; call ignored.");
            } else {
                if (this.wD < ((double) this.wC)) {
                    double d = ((double) (currentTimeMillis - this.wE)) / ((double) this.wB);
                    if (d > 0.0d) {
                        this.wD = Math.min((double) this.wC, d + this.wD);
                    }
                }
                this.wE = currentTimeMillis;
                if (this.wD >= 1.0d) {
                    this.wD -= 1.0d;
                    z = true;
                } else {
                    bh.D("Excessive " + this.wG + " detected; call ignored.");
                }
            }
        }
        return z;
    }
}
