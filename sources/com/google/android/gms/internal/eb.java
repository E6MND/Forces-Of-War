package com.google.android.gms.internal;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class eb {
    private int mOrientation = -1;
    private String qD;
    private String qE;
    private String qF;
    private List<String> qG;
    private String qH;
    private String qI;
    private List<String> qJ;
    private long qK = -1;
    private boolean qL = false;
    private final long qM = -1;
    private List<String> qN;
    private long qO = -1;

    private static String a(Map<String, List<String>> map, String str) {
        List list = map.get(str);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return (String) list.get(0);
    }

    private static long b(Map<String, List<String>> map, String str) {
        List list = map.get(str);
        if (list != null && !list.isEmpty()) {
            String str2 = (String) list.get(0);
            try {
                return (long) (Float.parseFloat(str2) * 1000.0f);
            } catch (NumberFormatException e) {
                ev.D("Could not parse float from " + str + " header: " + str2);
            }
        }
        return -1;
    }

    private static List<String> c(Map<String, List<String>> map, String str) {
        String str2;
        List list = map.get(str);
        if (list == null || list.isEmpty() || (str2 = (String) list.get(0)) == null) {
            return null;
        }
        return Arrays.asList(str2.trim().split("\\s+"));
    }

    private void e(Map<String, List<String>> map) {
        this.qD = a(map, "X-Afma-Ad-Size");
    }

    private void f(Map<String, List<String>> map) {
        List<String> c = c(map, "X-Afma-Click-Tracking-Urls");
        if (c != null) {
            this.qG = c;
        }
    }

    private void g(Map<String, List<String>> map) {
        List list = map.get("X-Afma-Debug-Dialog");
        if (list != null && !list.isEmpty()) {
            this.qH = (String) list.get(0);
        }
    }

    private void h(Map<String, List<String>> map) {
        List<String> c = c(map, "X-Afma-Tracking-Urls");
        if (c != null) {
            this.qJ = c;
        }
    }

    private void i(Map<String, List<String>> map) {
        long b = b(map, "X-Afma-Interstitial-Timeout");
        if (b != -1) {
            this.qK = b;
        }
    }

    private void j(Map<String, List<String>> map) {
        this.qI = a(map, "X-Afma-ActiveView");
    }

    private void k(Map<String, List<String>> map) {
        List list = map.get("X-Afma-Mediation");
        if (list != null && !list.isEmpty()) {
            this.qL = Boolean.valueOf((String) list.get(0)).booleanValue();
        }
    }

    private void l(Map<String, List<String>> map) {
        List<String> c = c(map, "X-Afma-Manual-Tracking-Urls");
        if (c != null) {
            this.qN = c;
        }
    }

    private void m(Map<String, List<String>> map) {
        long b = b(map, "X-Afma-Refresh-Rate");
        if (b != -1) {
            this.qO = b;
        }
    }

    private void n(Map<String, List<String>> map) {
        List list = map.get("X-Afma-Orientation");
        if (list != null && !list.isEmpty()) {
            String str = (String) list.get(0);
            if ("portrait".equalsIgnoreCase(str)) {
                this.mOrientation = ep.bN();
            } else if ("landscape".equalsIgnoreCase(str)) {
                this.mOrientation = ep.bM();
            }
        }
    }

    public void a(String str, Map<String, List<String>> map, String str2) {
        this.qE = str;
        this.qF = str2;
        d(map);
    }

    public void d(Map<String, List<String>> map) {
        e(map);
        f(map);
        g(map);
        h(map);
        i(map);
        k(map);
        l(map);
        m(map);
        n(map);
        j(map);
    }

    public dv i(long j) {
        return new dv(this.qE, this.qF, this.qG, this.qJ, this.qK, this.qL, -1, this.qN, this.qO, this.mOrientation, this.qD, j, this.qH, this.qI);
    }
}
