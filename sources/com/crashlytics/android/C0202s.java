package com.crashlytics.android;

import android.content.DialogInterface;

/* renamed from: com.crashlytics.android.s  reason: case insensitive filesystem */
final class C0202s implements DialogInterface.OnClickListener {
    private /* synthetic */ C0199p a;

    C0202s(C0199p pVar) {
        this.a = pVar;
    }

    public final void onClick(DialogInterface dialog, int i) {
        Crashlytics crashlytics = this.a.b;
        Crashlytics.a(true);
        this.a.a.a(true);
        dialog.dismiss();
    }
}
