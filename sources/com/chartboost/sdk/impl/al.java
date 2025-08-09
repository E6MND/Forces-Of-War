package com.chartboost.sdk.impl;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

public class al implements ai {
    protected ap a;

    public byte[] a(aj ajVar) {
        ao aoVar = new ao();
        a((ap) aoVar);
        b(ajVar);
        a();
        return aoVar.c();
    }

    public void a(ap apVar) {
        if (this.a != null) {
            throw new IllegalStateException("in the middle of something");
        }
        this.a = apVar;
    }

    public void a() {
        this.a = null;
    }

    /* access modifiers changed from: protected */
    public boolean a(String str, aj ajVar) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean a(String str, Object obj) {
        return false;
    }

    public int b(aj ajVar) {
        return b((String) null, ajVar);
    }

    /* access modifiers changed from: protected */
    public int b(String str, aj ajVar) {
        byte b;
        if (ajVar == null) {
            throw new NullPointerException("can't save a null object");
        }
        int a2 = this.a.a();
        if (ajVar instanceof List) {
            b = 4;
        } else {
            b = 3;
        }
        if (a(str, ajVar)) {
            return this.a.a() - a2;
        }
        if (str != null) {
            a(b, str);
        }
        int a3 = this.a.a();
        this.a.c(0);
        List list = null;
        boolean z = b == 3 && str == null;
        if (b == 3) {
            if (z && ajVar.b("_id")) {
                b("_id", ajVar.a("_id"));
            }
            Object a4 = ajVar.a("_transientFields");
            if (a4 instanceof List) {
                list = (List) a4;
            }
        }
        if (ajVar instanceof Map) {
            for (Map.Entry entry : ((Map) ajVar).entrySet()) {
                if ((!z || !((String) entry.getKey()).equals("_id")) && (list == null || !list.contains(entry.getKey()))) {
                    b((String) entry.getKey(), entry.getValue());
                }
            }
        } else {
            for (String next : ajVar.keySet()) {
                if ((!z || !next.equals("_id")) && (list == null || !list.contains(next))) {
                    b(next, ajVar.a(next));
                }
            }
        }
        this.a.write(0);
        this.a.a(a3, this.a.a() - a3);
        return this.a.a() - a2;
    }

    /* access modifiers changed from: protected */
    public void b(String str, Object obj) {
        if (!str.equals("_transientFields")) {
            if (!str.equals("$where") || !(obj instanceof String)) {
                Object a2 = ag.a(obj);
                if (a2 == null) {
                    a(str);
                } else if (a2 instanceof Date) {
                    a(str, (Date) a2);
                } else if (a2 instanceof Number) {
                    a(str, (Number) a2);
                } else if (a2 instanceof Character) {
                    a(str, a2.toString());
                } else if (a2 instanceof String) {
                    a(str, a2.toString());
                } else if (a2 instanceof ay) {
                    a(str, (ay) a2);
                } else if (a2 instanceof aj) {
                    b(str, (aj) a2);
                } else if (a2 instanceof Boolean) {
                    a(str, (Boolean) a2);
                } else if (a2 instanceof Pattern) {
                    a(str, (Pattern) a2);
                } else if (a2 instanceof Map) {
                    a(str, (Map) a2);
                } else if (a2 instanceof Iterable) {
                    a(str, (Iterable) a2);
                } else if (a2 instanceof byte[]) {
                    a(str, (byte[]) a2);
                } else if (a2 instanceof at) {
                    a(str, (at) a2);
                } else if (a2 instanceof UUID) {
                    a(str, (UUID) a2);
                } else if (a2.getClass().isArray()) {
                    c(str, a2);
                } else if (a2 instanceof az) {
                    a(str, (az) a2);
                } else if (a2 instanceof as) {
                    a(str, (as) a2);
                } else if (a2 instanceof av) {
                    a(str, (av) a2);
                } else if (a2 instanceof au) {
                    a(str, (au) a2);
                } else if (a2 instanceof z) {
                    am amVar = new am();
                    amVar.a("$ref", ((z) a2).b());
                    amVar.a("$id", ((z) a2).a());
                    b(str, (aj) amVar);
                } else if (a2 instanceof ax) {
                    d(str);
                } else if (a2 instanceof aw) {
                    e(str);
                } else if (!a(str, a2)) {
                    throw new IllegalArgumentException("can't serialize " + a2.getClass());
                }
            } else {
                a((byte) 13, str);
                b(obj.toString());
            }
        }
    }

    private void c(String str, Object obj) {
        a((byte) 4, str);
        int a2 = this.a.a();
        this.a.c(0);
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            b(String.valueOf(i), Array.get(obj, i));
        }
        this.a.write(0);
        this.a.a(a2, this.a.a() - a2);
    }

    private void a(String str, Iterable iterable) {
        a((byte) 4, str);
        int a2 = this.a.a();
        this.a.c(0);
        int i = 0;
        for (Object b : iterable) {
            b(String.valueOf(i), b);
            i++;
        }
        this.a.write(0);
        this.a.a(a2, this.a.a() - a2);
    }

    private void a(String str, Map map) {
        a((byte) 3, str);
        int a2 = this.a.a();
        this.a.c(0);
        for (Map.Entry entry : map.entrySet()) {
            b(entry.getKey().toString(), entry.getValue());
        }
        this.a.write(0);
        this.a.a(a2, this.a.a() - a2);
    }

    /* access modifiers changed from: protected */
    public void a(String str) {
        a((byte) 10, str);
    }

    /* access modifiers changed from: protected */
    public void a(String str, as asVar) {
        a((byte) 17, str);
        this.a.c(asVar.b());
        this.a.c(asVar.a());
    }

    /* access modifiers changed from: protected */
    public void a(String str, av avVar) {
        a((byte) 15, str);
        int a2 = this.a.a();
        this.a.c(0);
        b(avVar.a());
        b(avVar.b());
        this.a.a(a2, this.a.a() - a2);
    }

    /* access modifiers changed from: protected */
    public void a(String str, au auVar) {
        a((byte) 13, str);
        this.a.a();
        b(auVar.a());
    }

    /* access modifiers changed from: protected */
    public void a(String str, Boolean bool) {
        a((byte) 8, str);
        this.a.write(bool.booleanValue() ? 1 : 0);
    }

    /* access modifiers changed from: protected */
    public void a(String str, Date date) {
        a((byte) 9, str);
        this.a.a(date.getTime());
    }

    /* access modifiers changed from: protected */
    public void a(String str, Number number) {
        if ((number instanceof Integer) || (number instanceof Short) || (number instanceof Byte) || (number instanceof AtomicInteger)) {
            a((byte) 16, str);
            this.a.c(number.intValue());
        } else if ((number instanceof Long) || (number instanceof AtomicLong)) {
            a((byte) 18, str);
            this.a.a(number.longValue());
        } else if ((number instanceof Float) || (number instanceof Double)) {
            a((byte) 1, str);
            this.a.a(number.doubleValue());
        } else {
            throw new IllegalArgumentException("can't serialize " + number.getClass());
        }
    }

    /* access modifiers changed from: protected */
    public void a(String str, byte[] bArr) {
        a(str, 0, bArr);
    }

    /* access modifiers changed from: protected */
    public void a(String str, at atVar) {
        a(str, (int) atVar.a(), atVar.b());
    }

    private void a(String str, int i, byte[] bArr) {
        a((byte) 5, str);
        int length = bArr.length;
        if (i == 2) {
            length += 4;
        }
        this.a.c(length);
        this.a.write(i);
        if (i == 2) {
            this.a.c(length - 4);
        }
        int a2 = this.a.a();
        this.a.write(bArr);
        ae.a(this.a.a() - a2, bArr.length);
    }

    /* access modifiers changed from: protected */
    public void a(String str, UUID uuid) {
        a((byte) 5, str);
        this.a.c(16);
        this.a.write(3);
        this.a.a(uuid.getMostSignificantBits());
        this.a.a(uuid.getLeastSignificantBits());
    }

    /* access modifiers changed from: protected */
    public void a(String str, az azVar) {
        a(str, azVar.a(), (byte) 14);
    }

    /* access modifiers changed from: protected */
    public void a(String str, String str2) {
        a(str, str2, (byte) 2);
    }

    private void a(String str, String str2, byte b) {
        a(b, str);
        b(str2);
    }

    /* access modifiers changed from: protected */
    public void a(String str, ay ayVar) {
        a((byte) 7, str);
        this.a.d(ayVar.c());
        this.a.d(ayVar.d());
        this.a.d(ayVar.e());
    }

    private void a(String str, Pattern pattern) {
        a((byte) 11, str);
        c(pattern.pattern());
        c(ag.a(pattern.flags()));
    }

    private void d(String str) {
        a((byte) -1, str);
    }

    private void e(String str) {
        a(Byte.MAX_VALUE, str);
    }

    /* access modifiers changed from: protected */
    public void a(byte b, String str) {
        this.a.write((int) b);
        c(str);
    }

    /* access modifiers changed from: protected */
    public void b(String str) {
        int a2 = this.a.a();
        this.a.c(0);
        this.a.a(a2, c(str));
    }

    /* access modifiers changed from: protected */
    public int c(String str) {
        int length = str.length();
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int codePointAt = Character.codePointAt(str, i);
            if (codePointAt < 128) {
                this.a.write((int) (byte) codePointAt);
                i2++;
            } else if (codePointAt < 2048) {
                this.a.write((int) (byte) ((codePointAt >> 6) + 192));
                this.a.write((int) (byte) ((codePointAt & 63) + 128));
                i2 += 2;
            } else if (codePointAt < 65536) {
                this.a.write((int) (byte) ((codePointAt >> 12) + 224));
                this.a.write((int) (byte) (((codePointAt >> 6) & 63) + 128));
                this.a.write((int) (byte) ((codePointAt & 63) + 128));
                i2 += 3;
            } else {
                this.a.write((int) (byte) ((codePointAt >> 18) + 240));
                this.a.write((int) (byte) (((codePointAt >> 12) & 63) + 128));
                this.a.write((int) (byte) (((codePointAt >> 6) & 63) + 128));
                this.a.write((int) (byte) ((codePointAt & 63) + 128));
                i2 += 4;
            }
            i += Character.charCount(codePointAt);
        }
        this.a.write(0);
        return i2 + 1;
    }
}
