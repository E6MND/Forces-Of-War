package com.crashlytics.android;

import com.crashlytics.android.internal.C0188v;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class Z {
    private final File a;
    private final Map<String, String> b;

    public Z(File file) {
        this(file, Collections.emptyMap());
    }

    public Z(File file, Map<String, String> map) {
        this.a = file;
        this.b = new HashMap(map);
        if (this.a.length() == 0) {
            this.b.putAll(aa.a);
        }
    }

    public File d() {
        return this.a;
    }

    public String b() {
        return d().getName();
    }

    public String c() {
        String b2 = b();
        return b2.substring(0, b2.lastIndexOf(46));
    }

    public Map<String, String> e() {
        return Collections.unmodifiableMap(this.b);
    }

    public boolean a() {
        C0188v.a().b().a(Crashlytics.TAG, "Removing report at " + this.a.getPath());
        return this.a.delete();
    }
}
