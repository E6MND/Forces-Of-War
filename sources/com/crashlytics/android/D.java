package com.crashlytics.android;

import com.crashlytics.android.internal.C0142aa;
import java.io.File;

final class D extends C0142aa {
    private /* synthetic */ File a;

    D(C0205v vVar, File file) {
        this.a = file;
    }

    public final void a() {
        V q = Crashlytics.getInstance().q();
        if (q != null) {
            new aa(q).a(new Z(this.a, C0205v.e));
        }
    }
}
