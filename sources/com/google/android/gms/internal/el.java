package com.google.android.gms.internal;

import android.os.Bundle;

public class el {
    private final Object lq;
    private final ei rA;
    private final String rD;
    private int rZ;
    private int sa;

    el(ei eiVar, String str) {
        this.lq = new Object();
        this.rA = eiVar;
        this.rD = str;
    }

    public el(String str) {
        this(ei.bC(), str);
    }

    public void a(int i, int i2) {
        synchronized (this.lq) {
            this.rZ = i;
            this.sa = i2;
            this.rA.a(this.rD, this);
        }
    }

    public Bundle toBundle() {
        Bundle bundle;
        synchronized (this.lq) {
            bundle = new Bundle();
            bundle.putInt("pmnli", this.rZ);
            bundle.putInt("pmnll", this.sa);
        }
        return bundle;
    }
}
