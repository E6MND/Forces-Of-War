package com.crashlytics.android.internal;

import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.crashlytics.android.internal.ar  reason: case insensitive filesystem */
final class C0159ar implements C0162au {
    private boolean a = true;
    private /* synthetic */ StringBuilder b;

    C0159ar(C0158aq aqVar, StringBuilder sb) {
        this.b = sb;
    }

    public final void a(InputStream inputStream, int i) throws IOException {
        if (this.a) {
            this.a = false;
        } else {
            this.b.append(", ");
        }
        this.b.append(i);
    }
}
