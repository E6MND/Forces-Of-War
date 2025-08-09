package com.crashlytics.android;

import java.io.File;
import java.io.FilenameFilter;

/* renamed from: com.crashlytics.android.g  reason: case insensitive filesystem */
final class C0138g implements FilenameFilter {
    C0138g() {
    }

    public final boolean accept(File file, String filename) {
        return filename.endsWith(".cls_temp");
    }
}
