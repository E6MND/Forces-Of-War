package com.chartboost.sdk.impl;

import java.io.Serializable;

public class ax implements Serializable {
    public boolean equals(Object o) {
        return o instanceof ax;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        return "MinKey";
    }
}
