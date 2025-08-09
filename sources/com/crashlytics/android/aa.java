package com.crashlytics.android;

import com.crashlytics.android.internal.C0184r;
import com.crashlytics.android.internal.C0188v;
import com.facebook.AppEventsConstants;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

final class aa {
    static final Map<String, String> a = Collections.singletonMap("X-CRASHLYTICS-INVALID-SESSION", AppEventsConstants.EVENT_PARAM_VALUE_YES);
    private static final FilenameFilter b = new ab();
    /* access modifiers changed from: private */
    public static final short[] c = {10, 20, 30, 60, 120, 300};
    private final Object d = new Object();
    private final V e;
    /* access modifiers changed from: private */
    public Thread f;

    public aa(V v) {
        if (v == null) {
            throw new IllegalArgumentException("createReportCall must not be null.");
        }
        this.e = v;
    }

    public final synchronized void a(float f2) {
        if (this.f == null) {
            this.f = new Thread(new ac(this, f2), "Crashlytics Report Uploader");
            this.f.start();
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean a(Z z) {
        boolean z2 = false;
        synchronized (this.d) {
            try {
                boolean a2 = this.e.a(new U(C0184r.a(C0188v.a().getContext(), C0188v.a().f()), z));
                C0188v.a().b().b(Crashlytics.TAG, "Crashlytics report upload " + (a2 ? "complete: " : "FAILED: ") + z.b());
                if (a2) {
                    z.a();
                    z2 = true;
                }
            } catch (Exception e2) {
                C0188v.a().b().a(Crashlytics.TAG, "Error occurred sending report " + z, (Throwable) e2);
            }
        }
        return z2;
    }

    /* access modifiers changed from: package-private */
    public final List<Z> a() {
        File[] listFiles;
        C0188v.a().b().a(Crashlytics.TAG, "Checking for crash reports...");
        synchronized (this.d) {
            listFiles = C0188v.a().h().listFiles(b);
        }
        LinkedList linkedList = new LinkedList();
        for (File file : listFiles) {
            C0188v.a().b().a(Crashlytics.TAG, "Found crash report " + file.getPath());
            linkedList.add(new Z(file));
        }
        if (linkedList.size() == 0) {
            C0188v.a().b().a(Crashlytics.TAG, "No reports found.");
        }
        return linkedList;
    }
}
