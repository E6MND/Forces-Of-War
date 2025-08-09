package com.crashlytics.android;

import java.io.File;
import java.io.FilenameFilter;

final class Q implements FilenameFilter {
    private Q() {
    }

    /* synthetic */ Q(byte b) {
        this();
    }

    public final boolean accept(File file, String fileName) {
        return !C0205v.a.accept(file, fileName) && C0205v.d.matcher(fileName).matches();
    }
}
