package com.chartboost.sdk.impl;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.UUID;
import java.util.regex.Pattern;

public class ad {
    public static af a() {
        ab b2 = b();
        b2.a(Date.class, (af) new i(b2));
        b2.a(as.class, (af) new g(b2));
        b2.a(at.class, (af) new h());
        b2.a(byte[].class, (af) new h());
        return b2;
    }

    static ab b() {
        ab abVar = new ab();
        abVar.a(Object[].class, (af) new m(abVar));
        abVar.a(Boolean.class, (af) new q());
        abVar.a(au.class, (af) new a(abVar));
        abVar.a(av.class, (af) new b(abVar));
        abVar.a(y.class, (af) new d(abVar));
        abVar.a(z.class, (af) new e(abVar));
        abVar.a(Iterable.class, (af) new f(abVar));
        abVar.a(Map.class, (af) new j(abVar));
        abVar.a(aw.class, (af) new k(abVar));
        abVar.a(ax.class, (af) new l(abVar));
        abVar.a(Number.class, (af) new q());
        abVar.a(ay.class, (af) new n(abVar));
        abVar.a(Pattern.class, (af) new o(abVar));
        abVar.a(String.class, (af) new p());
        abVar.a(UUID.class, (af) new r(abVar));
        return abVar;
    }

    private static abstract class c extends aa {
        protected final af a;

        c(af afVar) {
            this.a = afVar;
        }
    }

    private static class h extends aa {
        private h() {
        }

        public void a(Object obj, StringBuilder sb) {
            sb.append("<Binary Data>");
        }
    }

    private static class m extends c {
        m(af afVar) {
            super(afVar);
        }

        public void a(Object obj, StringBuilder sb) {
            sb.append("[ ");
            for (int i = 0; i < Array.getLength(obj); i++) {
                if (i > 0) {
                    sb.append(" , ");
                }
                this.a.a(Array.get(obj, i), sb);
            }
            sb.append("]");
        }
    }

    private static class q extends aa {
        private q() {
        }

        public void a(Object obj, StringBuilder sb) {
            sb.append(obj.toString());
        }
    }

    private static class g extends c {
        g(af afVar) {
            super(afVar);
        }

        public void a(Object obj, StringBuilder sb) {
            as asVar = (as) obj;
            w wVar = new w();
            wVar.put("$ts", Integer.valueOf(asVar.a()));
            wVar.put("$inc", Integer.valueOf(asVar.b()));
            this.a.a(wVar, sb);
        }
    }

    private static class a extends c {
        a(af afVar) {
            super(afVar);
        }

        public void a(Object obj, StringBuilder sb) {
            w wVar = new w();
            wVar.put("$code", ((au) obj).a());
            this.a.a(wVar, sb);
        }
    }

    private static class b extends c {
        b(af afVar) {
            super(afVar);
        }

        public void a(Object obj, StringBuilder sb) {
            av avVar = (av) obj;
            w wVar = new w();
            wVar.put("$code", avVar.a());
            wVar.put("$scope", avVar.b());
            this.a.a(wVar, sb);
        }
    }

    private static class i extends c {
        i(af afVar) {
            super(afVar);
        }

        public void a(Object obj, StringBuilder sb) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            simpleDateFormat.setCalendar(new GregorianCalendar(new SimpleTimeZone(0, "GMT")));
            this.a.a(new w("$date", simpleDateFormat.format((Date) obj)), sb);
        }
    }

    private static class d extends c {
        d(af afVar) {
            super(afVar);
        }

        public void a(Object obj, StringBuilder sb) {
            sb.append("{ ");
            y yVar = (y) obj;
            boolean z = true;
            for (String next : yVar.keySet()) {
                if (z) {
                    z = false;
                } else {
                    sb.append(" , ");
                }
                ac.a(sb, next);
                sb.append(" : ");
                this.a.a(yVar.a(next), sb);
            }
            sb.append("}");
        }
    }

    private static class e extends c {
        e(af afVar) {
            super(afVar);
        }

        public void a(Object obj, StringBuilder sb) {
            z zVar = (z) obj;
            w wVar = new w();
            wVar.put("$ref", zVar.b());
            wVar.put("$id", zVar.a());
            this.a.a(wVar, sb);
        }
    }

    private static class f extends c {
        f(af afVar) {
            super(afVar);
        }

        public void a(Object obj, StringBuilder sb) {
            boolean z = true;
            sb.append("[ ");
            for (Object next : (Iterable) obj) {
                if (z) {
                    z = false;
                } else {
                    sb.append(" , ");
                }
                this.a.a(next, sb);
            }
            sb.append("]");
        }
    }

    private static class j extends c {
        j(af afVar) {
            super(afVar);
        }

        public void a(Object obj, StringBuilder sb) {
            sb.append("{ ");
            boolean z = true;
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                if (z) {
                    z = false;
                } else {
                    sb.append(" , ");
                }
                ac.a(sb, entry.getKey().toString());
                sb.append(" : ");
                this.a.a(entry.getValue(), sb);
            }
            sb.append("}");
        }
    }

    private static class k extends c {
        k(af afVar) {
            super(afVar);
        }

        public void a(Object obj, StringBuilder sb) {
            this.a.a(new w("$maxKey", 1), sb);
        }
    }

    private static class l extends c {
        l(af afVar) {
            super(afVar);
        }

        public void a(Object obj, StringBuilder sb) {
            this.a.a(new w("$minKey", 1), sb);
        }
    }

    private static class n extends c {
        n(af afVar) {
            super(afVar);
        }

        public void a(Object obj, StringBuilder sb) {
            this.a.a(new w("$oid", obj.toString()), sb);
        }
    }

    private static class o extends c {
        o(af afVar) {
            super(afVar);
        }

        public void a(Object obj, StringBuilder sb) {
            w wVar = new w();
            wVar.a("$regex", obj.toString());
            if (((Pattern) obj).flags() != 0) {
                wVar.a("$options", x.a(((Pattern) obj).flags()));
            }
            this.a.a(wVar, sb);
        }
    }

    private static class p extends aa {
        private p() {
        }

        public void a(Object obj, StringBuilder sb) {
            ac.a(sb, (String) obj);
        }
    }

    private static class r extends c {
        r(af afVar) {
            super(afVar);
        }

        public void a(Object obj, StringBuilder sb) {
            w wVar = new w();
            wVar.put("$uuid", ((UUID) obj).toString());
            this.a.a(wVar, sb);
        }
    }
}
