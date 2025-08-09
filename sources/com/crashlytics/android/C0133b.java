package com.crashlytics.android;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Comparator;

/* renamed from: com.crashlytics.android.b  reason: case insensitive filesystem */
class C0133b {
    public final String a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final int g;
    public final String h;
    public final String i;
    public final Y j;

    public static void a(File file, FilenameFilter filenameFilter, int i2, Comparator<File> comparator) {
        File[] listFiles = file.listFiles(filenameFilter);
        if (listFiles != null && listFiles.length > i2) {
            Arrays.sort(listFiles, comparator);
            int length = listFiles.length;
            int length2 = listFiles.length;
            int i3 = 0;
            while (i3 < length2) {
                File file2 = listFiles[i3];
                if (length > i2) {
                    file2.delete();
                    length--;
                    i3++;
                } else {
                    return;
                }
            }
        }
    }

    public C0133b(String str, String str2, String str3, String str4, String str5, String str6, int i2, String str7, String str8, Y y) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = str6;
        this.g = i2;
        this.h = str7;
        this.i = str8;
        this.j = y;
    }
}
