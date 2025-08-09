package com.google.android.gms.games.internal;

import com.google.android.gms.internal.hg;

public final class GamesLog {
    private static final hg OS = new hg("Games");

    private GamesLog() {
    }

    public static void a(String str, String str2, Throwable th) {
        OS.a(str, str2, th);
    }

    public static void b(String str, String str2, Throwable th) {
        OS.b(str, str2, th);
    }

    public static void i(String str, String str2) {
        OS.i(str, str2);
    }

    public static void j(String str, String str2) {
        OS.j(str, str2);
    }

    public static void k(String str, String str2) {
        OS.k(str, str2);
    }
}
