package com.crashlytics.android.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import java.io.File;

/* renamed from: com.crashlytics.android.internal.z  reason: case insensitive filesystem */
final class C0192z extends ContextWrapper {
    private final String a;

    public C0192z(Context context, String str) {
        super(context);
        this.a = ".TwitterSdk/" + str;
    }

    public final File getDatabasePath(String name) {
        File file = new File(super.getDatabasePath(name).getParentFile(), this.a);
        file.mkdirs();
        return new File(file, name);
    }

    public final SQLiteDatabase openOrCreateDatabase(String name, int i, SQLiteDatabase.CursorFactory factory) {
        return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);
    }

    @TargetApi(11)
    public final SQLiteDatabase openOrCreateDatabase(String name, int i, SQLiteDatabase.CursorFactory factory, DatabaseErrorHandler errorHandler) {
        return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name).getPath(), factory, errorHandler);
    }

    public final File getFilesDir() {
        return new File(super.getFilesDir(), this.a);
    }

    public final File getExternalFilesDir(String type) {
        return new File(super.getExternalFilesDir(type), this.a);
    }

    public final File getCacheDir() {
        return new File(super.getCacheDir(), this.a);
    }

    public final File getExternalCacheDir() {
        return new File(super.getExternalCacheDir(), this.a);
    }
}
