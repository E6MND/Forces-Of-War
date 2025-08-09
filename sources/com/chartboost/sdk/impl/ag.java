package com.chartboost.sdk.impl;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ag {
    private static boolean a = false;
    private static boolean b = false;
    static final Logger f = Logger.getLogger("org.bson.BSON");
    static bd<List<an>> g = new bd<>();
    static bd<List<an>> h = new bd<>();
    protected static Charset i = Charset.forName("UTF-8");
    static ThreadLocal<ai> j = new ThreadLocal<ai>() {
        /* access modifiers changed from: protected */
        /* renamed from: a */
        public ai initialValue() {
            return new al();
        }
    };
    static ThreadLocal<ah> k = new ThreadLocal<ah>() {
        /* access modifiers changed from: protected */
        /* renamed from: a */
        public ah initialValue() {
            return new ak();
        }
    };

    public static String a(int i2) {
        StringBuilder sb = new StringBuilder();
        int i3 = i2;
        for (a aVar : a.values()) {
            if ((aVar.j & i3) > 0) {
                sb.append(aVar.k);
                i3 -= aVar.j;
            }
        }
        if (i3 <= 0) {
            return sb.toString();
        }
        throw new IllegalArgumentException("some flags could not be recognized.");
    }

    private enum a {
        CANON_EQ(128, 'c', "Pattern.CANON_EQ"),
        UNIX_LINES(1, 'd', "Pattern.UNIX_LINES"),
        GLOBAL(256, 'g', (int) null),
        CASE_INSENSITIVE(2, 'i', (int) null),
        MULTILINE(8, 'm', (int) null),
        DOTALL(32, 's', "Pattern.DOTALL"),
        LITERAL(16, 't', "Pattern.LITERAL"),
        UNICODE_CASE(64, 'u', "Pattern.UNICODE_CASE"),
        COMMENTS(4, 'x', (int) null);
        
        private static final Map<Character, a> m = null;
        public final int j;
        public final char k;
        public final String l;

        static {
            int i;
            m = new HashMap();
            for (a aVar : values()) {
                m.put(Character.valueOf(aVar.k), aVar);
            }
        }

        private a(int i, char c, String str) {
            this.j = i;
            this.k = c;
            this.l = str;
        }
    }

    public static Object a(Object obj) {
        List<an> a2;
        if (!(!a() || g.a() == 0 || obj == null || (a2 = g.a(obj.getClass())) == null)) {
            for (an a3 : a2) {
                obj = a3.a(obj);
            }
        }
        return obj;
    }

    private static boolean a() {
        return a || b;
    }
}
