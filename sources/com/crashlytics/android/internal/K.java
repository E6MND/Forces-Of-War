package com.crashlytics.android.internal;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.games.GamesStatusCodes;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

final class K {
    private final X a;
    private final C0149ah b;
    private aK c;
    private final C0151aj d;
    private final int e;
    private List<C0152ak> f;

    private K(X x, C0149ah ahVar, C0151aj ajVar, int i) throws IOException {
        this.f = new CopyOnWriteArrayList();
        this.a = x;
        this.d = ajVar;
        this.b = ahVar;
        this.b.a();
        this.e = 100;
    }

    K(X x, C0149ah ahVar, C0151aj ajVar) throws IOException {
        this(x, ahVar, ajVar, 100);
    }

    /* access modifiers changed from: package-private */
    public final void a(V v) throws IOException {
        byte[] a2 = this.a.a(v);
        int length = a2.length;
        if (!this.d.a(length, e())) {
            C0143ab.a(4, String.format(Locale.US, "session analytics events file is %d bytes, new event is %d bytes, this is over flush limit of %d, rolling it over", new Object[]{Integer.valueOf(this.d.a()), Integer.valueOf(length), Integer.valueOf(e())}));
            a();
        }
        this.d.a(a2);
    }

    /* access modifiers changed from: package-private */
    public final void a(C0152ak akVar) {
        if (akVar != null) {
            this.f.add(akVar);
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean a() throws IOException {
        boolean z = true;
        String str = null;
        if (!this.d.b()) {
            UUID randomUUID = UUID.randomUUID();
            str = "sa" + "_" + randomUUID.toString() + "_" + this.b.a() + ".tap";
            this.d.a(str);
            C0143ab.a(4, String.format(Locale.US, "generated new to-send analytics file %s", new Object[]{str}));
            this.b.a();
        } else {
            z = false;
        }
        a(str);
        return z;
    }

    private int e() {
        return this.c == null ? GamesStatusCodes.STATUS_MILESTONE_CLAIMED_PREVIOUSLY : this.c.c;
    }

    /* access modifiers changed from: package-private */
    public final void a(aK aKVar) {
        this.c = aKVar;
    }

    private void a(String str) {
        for (C0152ak c2 : this.f) {
            try {
                c2.c();
            } catch (Exception e2) {
                C0188v.a().b().a(Crashlytics.TAG, "One of the roll over listeners threw an exception", (Throwable) e2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final List<File> b() {
        return this.d.a(1);
    }

    /* access modifiers changed from: package-private */
    public final void a(List<File> list) {
        this.d.a(list);
    }

    /* access modifiers changed from: package-private */
    public final void c() {
        this.d.a(this.d.c());
        this.d.d();
    }

    /* access modifiers changed from: package-private */
    public final void d() {
        List<File> c2 = this.d.c();
        if (c2.size() > this.e) {
            int size = c2.size() - this.e;
            C0143ab.c(String.format(Locale.US, "Found %d files in session analytics roll over directory, this is greater than %d, deleting %d oldest files", new Object[]{Integer.valueOf(c2.size()), Integer.valueOf(this.e), Integer.valueOf(size)}));
            TreeSet treeSet = new TreeSet(new L(this));
            for (File next : c2) {
                treeSet.add(new M(this, next, b(next.getName())));
            }
            ArrayList arrayList = new ArrayList();
            Iterator it = treeSet.iterator();
            while (it.hasNext()) {
                arrayList.add(((M) it.next()).a);
                if (arrayList.size() == size) {
                    break;
                }
            }
            this.d.a((List<File>) arrayList);
        }
    }

    private static long b(String str) {
        String[] split = str.split("_");
        if (split.length != 3) {
            return 0;
        }
        try {
            return Long.valueOf(split[2]).longValue();
        } catch (NumberFormatException e2) {
            return 0;
        }
    }
}
