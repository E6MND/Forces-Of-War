package com.crashlytics.android;

import java.io.File;
import java.io.FilenameFilter;

final class K implements FilenameFilter {
    K() {
    }

    public final boolean accept(File file, String filename) {
        return C0205v.d.matcher(filename).matches();
    }
}
