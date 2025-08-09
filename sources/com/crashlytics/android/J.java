package com.crashlytics.android;

import java.io.File;
import java.util.Comparator;

final class J implements Comparator<File> {
    J() {
    }

    public final /* synthetic */ int compare(Object x0, Object x1) {
        return ((File) x0).getName().compareTo(((File) x1).getName());
    }
}
