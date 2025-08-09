package com.crashlytics.android;

import com.crashlytics.android.internal.C0188v;
import java.util.concurrent.Callable;

final class B implements Callable<Boolean> {
    private /* synthetic */ C0205v a;

    B(C0205v vVar) {
        this.a = vVar;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public Boolean call() throws Exception {
        try {
            boolean delete = this.a.l.delete();
            C0188v.a().b().a(Crashlytics.TAG, "Initialization marker file removed: " + delete);
            return Boolean.valueOf(delete);
        } catch (Exception e) {
            C0188v.a().b().a(Crashlytics.TAG, "Problem encountered deleting Crashlytics initialization marker.", (Throwable) e);
            return false;
        }
    }
}
