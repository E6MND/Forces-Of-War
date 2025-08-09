package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

abstract class aj {
    private final String afA;
    private final Set<String> afz;

    public aj(String str, String... strArr) {
        this.afA = str;
        this.afz = new HashSet(strArr.length);
        for (String add : strArr) {
            this.afz.add(add);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(Set<String> set) {
        return set.containsAll(this.afz);
    }

    public String lG() {
        return this.afA;
    }

    public Set<String> lH() {
        return this.afz;
    }

    public abstract boolean lc();

    public abstract d.a w(Map<String, d.a> map);
}
