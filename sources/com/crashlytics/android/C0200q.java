package com.crashlytics.android;

import android.content.DialogInterface;

/* renamed from: com.crashlytics.android.q  reason: case insensitive filesystem */
final class C0200q implements DialogInterface.OnClickListener {
    private /* synthetic */ C0199p a;

    C0200q(C0199p pVar) {
        this.a = pVar;
    }

    public final void onClick(DialogInterface dialog, int i) {
        this.a.a.a(true);
        dialog.dismiss();
    }
}
