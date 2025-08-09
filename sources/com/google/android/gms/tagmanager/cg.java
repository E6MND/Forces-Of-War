package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

class cg extends aj {
    private static final String ID = a.REGEX_GROUP.toString();
    private static final String agE = b.ARG0.toString();
    private static final String agF = b.ARG1.toString();
    private static final String agG = b.IGNORE_CASE.toString();
    private static final String agH = b.GROUP.toString();

    public cg() {
        super(ID, agE, agF);
    }

    public boolean lc() {
        return true;
    }

    public d.a w(Map<String, d.a> map) {
        int i;
        d.a aVar = map.get(agE);
        d.a aVar2 = map.get(agF);
        if (aVar == null || aVar == dh.mY() || aVar2 == null || aVar2 == dh.mY()) {
            return dh.mY();
        }
        int i2 = 64;
        if (dh.n(map.get(agG)).booleanValue()) {
            i2 = 66;
        }
        d.a aVar3 = map.get(agH);
        if (aVar3 != null) {
            Long l = dh.l(aVar3);
            if (l == dh.mT()) {
                return dh.mY();
            }
            i = l.intValue();
            if (i < 0) {
                return dh.mY();
            }
        } else {
            i = 1;
        }
        try {
            String j = dh.j(aVar);
            String str = null;
            Matcher matcher = Pattern.compile(dh.j(aVar2), i2).matcher(j);
            if (matcher.find() && matcher.groupCount() >= i) {
                str = matcher.group(i);
            }
            return str == null ? dh.mY() : dh.r(str);
        } catch (PatternSyntaxException e) {
            return dh.mY();
        }
    }
}
