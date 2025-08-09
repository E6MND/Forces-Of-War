package com.google.android.gms.tagmanager;

class dg extends Number implements Comparable<dg> {
    private double aih;
    private long aii;
    private boolean aij = false;

    private dg(double d) {
        this.aih = d;
    }

    private dg(long j) {
        this.aii = j;
    }

    public static dg a(Double d) {
        return new dg(d.doubleValue());
    }

    public static dg co(String str) throws NumberFormatException {
        try {
            return new dg(Long.parseLong(str));
        } catch (NumberFormatException e) {
            try {
                return new dg(Double.parseDouble(str));
            } catch (NumberFormatException e2) {
                throw new NumberFormatException(str + " is not a valid TypedNumber");
            }
        }
    }

    public static dg z(long j) {
        return new dg(j);
    }

    /* renamed from: a */
    public int compareTo(dg dgVar) {
        return (!mO() || !dgVar.mO()) ? Double.compare(doubleValue(), dgVar.doubleValue()) : new Long(this.aii).compareTo(Long.valueOf(dgVar.aii));
    }

    public byte byteValue() {
        return (byte) ((int) longValue());
    }

    public double doubleValue() {
        return mO() ? (double) this.aii : this.aih;
    }

    public boolean equals(Object other) {
        return (other instanceof dg) && compareTo((dg) other) == 0;
    }

    public float floatValue() {
        return (float) doubleValue();
    }

    public int hashCode() {
        return new Long(longValue()).hashCode();
    }

    public int intValue() {
        return mQ();
    }

    public long longValue() {
        return mP();
    }

    public boolean mN() {
        return !mO();
    }

    public boolean mO() {
        return this.aij;
    }

    public long mP() {
        return mO() ? this.aii : (long) this.aih;
    }

    public int mQ() {
        return (int) longValue();
    }

    public short mR() {
        return (short) ((int) longValue());
    }

    public short shortValue() {
        return mR();
    }

    public String toString() {
        return mO() ? Long.toString(this.aii) : Double.toString(this.aih);
    }
}
