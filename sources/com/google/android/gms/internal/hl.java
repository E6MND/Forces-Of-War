package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class hl {

    public static final class a {
        private final List<String> GG;
        private final Object GH;

        private a(Object obj) {
            this.GH = hn.f(obj);
            this.GG = new ArrayList();
        }

        public a a(String str, Object obj) {
            this.GG.add(((String) hn.f(str)) + "=" + String.valueOf(obj));
            return this;
        }

        public String toString() {
            StringBuilder append = new StringBuilder(100).append(this.GH.getClass().getSimpleName()).append('{');
            int size = this.GG.size();
            for (int i = 0; i < size; i++) {
                append.append(this.GG.get(i));
                if (i < size - 1) {
                    append.append(", ");
                }
            }
            return append.append('}').toString();
        }
    }

    public static a e(Object obj) {
        return new a(obj);
    }

    public static boolean equal(Object a2, Object b) {
        return a2 == b || (a2 != null && a2.equals(b));
    }

    public static int hashCode(Object... objects) {
        return Arrays.hashCode(objects);
    }
}
