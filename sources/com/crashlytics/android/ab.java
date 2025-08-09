package com.crashlytics.android;

import java.io.File;
import java.io.FilenameFilter;

final class ab implements FilenameFilter {
    ab() {
    }

    public final boolean accept(File file, String filename) {
        return filename.endsWith(".cls") && !filename.contains("Session");
    }
}
