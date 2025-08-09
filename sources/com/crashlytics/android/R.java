package com.crashlytics.android;

import java.io.File;
import java.io.FilenameFilter;

final class R implements FilenameFilter {
    private final String a;

    public R(String str) {
        this.a = str;
    }

    public final boolean accept(File file, String filename) {
        return filename.contains(this.a) && !filename.endsWith(".cls_temp");
    }
}
