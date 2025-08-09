package com.chartboost.sdk.impl;

public class z {
    final Object a;
    final String b;

    public String toString() {
        return "{ \"$ref\" : \"" + this.b + "\", \"$id\" : \"" + this.a + "\" }";
    }

    public Object a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        z zVar = (z) o;
        if (this.a == null ? zVar.a != null : !this.a.equals(zVar.a)) {
            return false;
        }
        if (this.b != null) {
            if (this.b.equals(zVar.b)) {
                return true;
            }
        } else if (zVar.b == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        if (this.a != null) {
            i = this.a.hashCode();
        } else {
            i = 0;
        }
        int i3 = i * 31;
        if (this.b != null) {
            i2 = this.b.hashCode();
        }
        return i3 + i2;
    }
}
