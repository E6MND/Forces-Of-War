package com.google.android.gms.tagmanager;

class cv implements cf {
    private long ahG;
    private final long wB;
    private final int wC;
    private double wD;
    private final Object wF;

    public cv() {
        this(60, 2000);
    }

    public cv(int i, long j) {
        this.wF = new Object();
        this.wC = i;
        this.wD = (double) this.wC;
        this.wB = j;
    }

    public boolean dj() {
        boolean z;
        synchronized (this.wF) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.wD < ((double) this.wC)) {
                double d = ((double) (currentTimeMillis - this.ahG)) / ((double) this.wB);
                if (d > 0.0d) {
                    this.wD = Math.min((double) this.wC, d + this.wD);
                }
            }
            this.ahG = currentTimeMillis;
            if (this.wD >= 1.0d) {
                this.wD -= 1.0d;
                z = true;
            } else {
                bh.D("No more tokens available.");
                z = false;
            }
        }
        return z;
    }
}
