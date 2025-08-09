package com.crashlytics.android.internal;

import java.io.File;
import java.util.Comparator;

/* renamed from: com.crashlytics.android.internal.ac  reason: case insensitive filesystem */
final class C0144ac implements Comparator<File> {
    C0144ac() {
    }

    public final /* synthetic */ int compare(Object x0, Object x1) {
        return (int) (((File) x0).lastModified() - ((File) x1).lastModified());
    }
}
