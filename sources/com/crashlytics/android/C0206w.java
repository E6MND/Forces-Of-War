package com.crashlytics.android;

import java.io.File;
import java.io.FilenameFilter;

/* renamed from: com.crashlytics.android.w  reason: case insensitive filesystem */
final class C0206w implements FilenameFilter {
    C0206w() {
    }

    public final boolean accept(File file, String filename) {
        return filename.length() == 39 && filename.endsWith(".cls");
    }
}
