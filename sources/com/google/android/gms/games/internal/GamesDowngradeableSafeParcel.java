package com.google.android.gms.games.internal;

import com.google.android.gms.internal.hb;
import com.google.android.gms.internal.in;

public abstract class GamesDowngradeableSafeParcel extends hb {
    /* access modifiers changed from: protected */
    public static boolean c(Integer num) {
        if (num == null) {
            return false;
        }
        return in.aE(num.intValue());
    }
}
