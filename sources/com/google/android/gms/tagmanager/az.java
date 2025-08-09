package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class az extends aj {
    private static final String ID = com.google.android.gms.internal.a.JOINER.toString();
    private static final String afR = b.ITEM_SEPARATOR.toString();
    private static final String afS = b.KEY_VALUE_SEPARATOR.toString();
    private static final String afT = b.ESCAPE.toString();
    private static final String afv = b.ARG0.toString();

    private enum a {
        NONE,
        URL,
        BACKSLASH
    }

    public az() {
        super(ID, afv);
    }

    private String a(String str, a aVar, Set<Character> set) {
        switch (aVar) {
            case URL:
                try {
                    return dk.cv(str);
                } catch (UnsupportedEncodingException e) {
                    bh.b("Joiner: unsupported encoding", e);
                    return str;
                }
            case BACKSLASH:
                String replace = str.replace("\\", "\\\\");
                Iterator<Character> it = set.iterator();
                while (true) {
                    String str2 = replace;
                    if (!it.hasNext()) {
                        return str2;
                    }
                    String ch = it.next().toString();
                    replace = str2.replace(ch, "\\" + ch);
                }
            default:
                return str;
        }
    }

    private void a(StringBuilder sb, String str, a aVar, Set<Character> set) {
        sb.append(a(str, aVar, set));
    }

    private void a(Set<Character> set, String str) {
        for (int i = 0; i < str.length(); i++) {
            set.add(Character.valueOf(str.charAt(i)));
        }
    }

    public boolean lc() {
        return true;
    }

    public d.a w(Map<String, d.a> map) {
        HashSet hashSet;
        a aVar;
        d.a aVar2 = map.get(afv);
        if (aVar2 == null) {
            return dh.mY();
        }
        d.a aVar3 = map.get(afR);
        String j = aVar3 != null ? dh.j(aVar3) : "";
        d.a aVar4 = map.get(afS);
        String j2 = aVar4 != null ? dh.j(aVar4) : "=";
        a aVar5 = a.NONE;
        d.a aVar6 = map.get(afT);
        if (aVar6 != null) {
            String j3 = dh.j(aVar6);
            if ("url".equals(j3)) {
                aVar = a.URL;
                hashSet = null;
            } else if ("backslash".equals(j3)) {
                aVar = a.BACKSLASH;
                hashSet = new HashSet();
                a(hashSet, j);
                a(hashSet, j2);
                hashSet.remove('\\');
            } else {
                bh.A("Joiner: unsupported escape type: " + j3);
                return dh.mY();
            }
        } else {
            hashSet = null;
            aVar = aVar5;
        }
        StringBuilder sb = new StringBuilder();
        switch (aVar2.type) {
            case 2:
                boolean z = true;
                d.a[] aVarArr = aVar2.fO;
                int length = aVarArr.length;
                int i = 0;
                while (i < length) {
                    d.a aVar7 = aVarArr[i];
                    if (!z) {
                        sb.append(j);
                    }
                    a(sb, dh.j(aVar7), aVar, hashSet);
                    i++;
                    z = false;
                }
                break;
            case 3:
                for (int i2 = 0; i2 < aVar2.fP.length; i2++) {
                    if (i2 > 0) {
                        sb.append(j);
                    }
                    String j4 = dh.j(aVar2.fP[i2]);
                    String j5 = dh.j(aVar2.fQ[i2]);
                    a(sb, j4, aVar, hashSet);
                    sb.append(j2);
                    a(sb, j5, aVar, hashSet);
                }
                break;
            default:
                a(sb, dh.j(aVar2), aVar, hashSet);
                break;
        }
        return dh.r(sb.toString());
    }
}
