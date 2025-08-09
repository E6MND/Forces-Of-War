package com.chartboost.sdk.impl;

import java.io.Serializable;
import java.util.Date;

public class as implements Serializable, Comparable<as> {
    static final boolean a = Boolean.getBoolean("DEBUG.DBTIMESTAMP");
    final int b = 0;
    final Date c = null;

    public int a() {
        if (this.c == null) {
            return 0;
        }
        return (int) (this.c.getTime() / 1000);
    }

    public int b() {
        return this.b;
    }

    public String toString() {
        return "TS time:" + this.c + " inc:" + this.b;
    }

    /* renamed from: a */
    public int compareTo(as asVar) {
        if (a() != asVar.a()) {
            return a() - asVar.a();
        }
        return b() - asVar.b();
    }

    public int hashCode() {
        return ((this.b + 31) * 31) + a();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof as)) {
            return false;
        }
        as asVar = (as) obj;
        if (a() == asVar.a() && b() == asVar.b()) {
            return true;
        }
        return false;
    }
}
