package com.chartboost.sdk.impl;

import java.io.Serializable;

public class az implements Serializable {
    private final String a;

    public String a() {
        return this.a;
    }

    public boolean equals(Object o) {
        Object o2;
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o instanceof az) {
            o2 = ((az) o).a;
        } else if (!(o instanceof String)) {
            return false;
        } else {
            o2 = (String) o;
        }
        if (this.a != null) {
            if (this.a.equals(o2)) {
                return true;
            }
        } else if (o2 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.a != null) {
            return this.a.hashCode();
        }
        return 0;
    }

    public String toString() {
        return this.a;
    }
}
