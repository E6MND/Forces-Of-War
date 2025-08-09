package com.crashlytics.android;

import android.util.Log;
import com.crashlytics.android.internal.C0143ab;
import com.crashlytics.android.internal.C0188v;

/* renamed from: com.crashlytics.android.c  reason: case insensitive filesystem */
final class C0134c {
    private String a;
    private boolean b;

    public C0134c(String str, boolean z) {
        this.a = str;
        this.b = z;
    }

    public final void a(String str, String str2) {
        if (C0143ab.e(this.a) && this.b) {
            Log.e(Crashlytics.TAG, ".");
            Log.e(Crashlytics.TAG, ".     |  | ");
            Log.e(Crashlytics.TAG, ".     |  |");
            Log.e(Crashlytics.TAG, ".     |  |");
            Log.e(Crashlytics.TAG, ".   \\ |  | /");
            Log.e(Crashlytics.TAG, ".    \\    /");
            Log.e(Crashlytics.TAG, ".     \\  /");
            Log.e(Crashlytics.TAG, ".      \\/");
            Log.e(Crashlytics.TAG, ".");
            Log.e(Crashlytics.TAG, "This app relies on Crashlytics. Configure your build environment here: ");
            Log.e(Crashlytics.TAG, String.format("https://crashlytics.com/register/%s/android/%s", new Object[]{str, str2}));
            Log.e(Crashlytics.TAG, ".");
            Log.e(Crashlytics.TAG, ".      /\\");
            Log.e(Crashlytics.TAG, ".     /  \\");
            Log.e(Crashlytics.TAG, ".    /    \\");
            Log.e(Crashlytics.TAG, ".   / |  | \\");
            Log.e(Crashlytics.TAG, ".     |  |");
            Log.e(Crashlytics.TAG, ".     |  |");
            Log.e(Crashlytics.TAG, ".     |  |");
            Log.e(Crashlytics.TAG, ".");
            throw new CrashlyticsMissingDependencyException(str, str2);
        } else if (!this.b) {
            C0188v.a().b().a(Crashlytics.TAG, "Configured not to require a build ID.");
        }
    }
}
