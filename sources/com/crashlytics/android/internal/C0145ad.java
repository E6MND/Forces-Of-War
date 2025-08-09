package com.crashlytics.android.internal;

import android.os.Build;
import android.text.TextUtils;
import com.crashlytics.android.Crashlytics;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* renamed from: com.crashlytics.android.internal.ad  reason: case insensitive filesystem */
enum C0145ad {
    X86_32,
    X86_64,
    ARM_UNKNOWN,
    PPC,
    PPC64,
    ARMV6,
    ARMV7,
    UNKNOWN,
    ARMV7S,
    ARM64;
    
    private static final Map<String, C0145ad> k = null;

    static {
        HashMap hashMap = new HashMap(4);
        k = hashMap;
        hashMap.put("armeabi-v7a", ARMV7);
        k.put("armeabi", ARMV6);
        k.put("x86", X86_32);
    }

    static C0145ad a() {
        String str = Build.CPU_ABI;
        if (TextUtils.isEmpty(str)) {
            C0188v.a().b().a(Crashlytics.TAG, "Architecture#getValue()::Build.CPU_ABI returned null or empty");
            return UNKNOWN;
        }
        C0145ad adVar = k.get(str.toLowerCase(Locale.US));
        if (adVar == null) {
            return UNKNOWN;
        }
        return adVar;
    }
}
