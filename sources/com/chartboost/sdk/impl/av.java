package com.chartboost.sdk.impl;

public class av extends au {
    final aj b;

    public aj b() {
        return this.b;
    }

    public boolean equals(Object o) {
        if (!(o instanceof av)) {
            return false;
        }
        av avVar = (av) o;
        if (!this.a.equals(avVar.a) || !this.b.equals(avVar.b)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.a.hashCode() ^ this.b.hashCode();
    }
}
