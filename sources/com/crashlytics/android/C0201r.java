package com.crashlytics.android;

import android.content.DialogInterface;

/* renamed from: com.crashlytics.android.r  reason: case insensitive filesystem */
final class C0201r implements DialogInterface.OnClickListener {
    private /* synthetic */ C0199p a;

    C0201r(C0199p pVar) {
        this.a = pVar;
    }

    public final void onClick(DialogInterface dialog, int i) {
        this.a.a.a(false);
        dialog.dismiss();
    }
}
