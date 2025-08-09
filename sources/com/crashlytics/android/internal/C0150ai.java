package com.crashlytics.android.internal;

/* renamed from: com.crashlytics.android.internal.ai  reason: case insensitive filesystem */
public enum C0150ai {
    DEVELOPER(1),
    USER_SIDELOAD(2),
    TEST_DISTRIBUTION(3),
    APP_STORE(4);
    
    private final int e;

    private C0150ai(int i) {
        this.e = i;
    }

    public final int a() {
        return this.e;
    }

    public final String toString() {
        return Integer.toString(this.e);
    }

    public static C0150ai a(String str) {
        if (str != null) {
            return APP_STORE;
        }
        return DEVELOPER;
    }
}
