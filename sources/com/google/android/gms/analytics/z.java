package com.google.android.gms.analytics;

class z implements ad {
    private final long wB;
    private final int wC;
    private double wD;
    private long wE;
    private final Object wF;
    private final String wG;

    public z(int i, long j, String str) {
        this.wF = new Object();
        this.wC = i;
        this.wD = (double) this.wC;
        this.wB = j;
        this.wG = str;
    }

    public z(String str) {
        this(60, 2000, str);
    }

    public boolean dj() {
        boolean z;
        synchronized (this.wF) {
            long currentTimeMillis = System.currentTimeMillis();
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
                aa.D("Excessive " + this.wG + " detected; call ignored.");
                z = false;
            }
        }
        return z;
    }
}
