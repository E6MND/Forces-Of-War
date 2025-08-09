package com.crashlytics.android;

import java.io.File;
import java.io.FilenameFilter;

final class S implements FilenameFilter {
    private final String a;

    public S(String str) {
        this.a = str;
    }

    public final boolean accept(File file, String fileName) {
        if (!fileName.equals(this.a + ".cls") && fileName.contains(this.a) && !fileName.endsWith(".cls_temp")) {
            return true;
        }
        return false;
    }
}
