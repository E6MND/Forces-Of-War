package com.crashlytics.android;

import java.io.File;
import java.io.FilenameFilter;

final class F implements FilenameFilter {
    private /* synthetic */ String a;

    F(C0205v vVar, String str) {
        this.a = str;
    }

    public final boolean accept(File file, String name) {
        return name.startsWith(this.a);
    }
}
