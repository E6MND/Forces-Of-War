package com.crashlytics.android;

import java.io.File;
import java.util.Comparator;

final class H implements Comparator<File> {
    H() {
    }

    public final /* synthetic */ int compare(Object x0, Object x1) {
        return ((File) x1).getName().compareTo(((File) x0).getName());
    }
}
