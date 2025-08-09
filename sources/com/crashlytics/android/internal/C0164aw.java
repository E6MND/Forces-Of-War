package com.crashlytics.android.internal;

/* renamed from: com.crashlytics.android.internal.aw  reason: case insensitive filesystem */
final /* synthetic */ class C0164aw {
    static final /* synthetic */ int[] a = new int[C0165ax.values().length];

    static {
        try {
            a[C0165ax.GET.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[C0165ax.POST.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[C0165ax.PUT.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[C0165ax.DELETE.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
    }
}
