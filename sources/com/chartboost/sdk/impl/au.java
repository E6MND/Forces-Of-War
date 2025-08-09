package com.chartboost.sdk.impl;

import java.io.Serializable;

public class au implements Serializable {
    final String a;

    public String a() {
        return this.a;
    }

    public boolean equals(Object o) {
        if (!(o instanceof au)) {
            return false;
        }
        return this.a.equals(((au) o).a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public String toString() {
        return a();
    }
}
