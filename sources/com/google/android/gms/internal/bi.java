package com.google.android.gms.internal;

import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import java.util.Map;

public final class bi implements bd {
    private static int a(DisplayMetrics displayMetrics, Map<String, String> map, String str, int i) {
        String str2 = map.get(str);
        if (str2 == null) {
            return i;
        }
        try {
            return eu.a(displayMetrics, Integer.parseInt(str2));
        } catch (NumberFormatException e) {
            ev.D("Could not parse " + str + " in a video GMSG: " + str2);
            return i;
        }
    }

    public void b(ey eyVar, Map<String, String> map) {
        String str = map.get("action");
        if (str == null) {
            ev.D("Action missing from video GMSG.");
            return;
        }
        cg bV = eyVar.bV();
        if (bV == null) {
            ev.D("Could not get ad overlay for a video GMSG.");
            return;
        }
        boolean equalsIgnoreCase = "new".equalsIgnoreCase(str);
        boolean equalsIgnoreCase2 = "position".equalsIgnoreCase(str);
        if (equalsIgnoreCase || equalsIgnoreCase2) {
            DisplayMetrics displayMetrics = eyVar.getContext().getResources().getDisplayMetrics();
            int a = a(displayMetrics, map, "x", 0);
            int a2 = a(displayMetrics, map, "y", 0);
            int a3 = a(displayMetrics, map, "w", -1);
            int a4 = a(displayMetrics, map, "h", -1);
            if (!equalsIgnoreCase || bV.aL() != null) {
                bV.b(a, a2, a3, a4);
            } else {
                bV.c(a, a2, a3, a4);
            }
        } else {
            ck aL = bV.aL();
            if (aL == null) {
                ck.a(eyVar, "no_video_view", (String) null);
            } else if ("click".equalsIgnoreCase(str)) {
                DisplayMetrics displayMetrics2 = eyVar.getContext().getResources().getDisplayMetrics();
                int a5 = a(displayMetrics2, map, "x", 0);
                int a6 = a(displayMetrics2, map, "y", 0);
                long uptimeMillis = SystemClock.uptimeMillis();
                MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, (float) a5, (float) a6, 0);
                aL.b(obtain);
                obtain.recycle();
            } else if ("controls".equalsIgnoreCase(str)) {
                String str2 = map.get("enabled");
                if (str2 == null) {
                    ev.D("Enabled parameter missing from controls video GMSG.");
                } else {
                    aL.l(Boolean.parseBoolean(str2));
                }
            } else if ("currentTime".equalsIgnoreCase(str)) {
                String str3 = map.get("time");
                if (str3 == null) {
                    ev.D("Time parameter missing from currentTime video GMSG.");
                    return;
                }
                try {
                    aL.seekTo((int) (Float.parseFloat(str3) * 1000.0f));
                } catch (NumberFormatException e) {
                    ev.D("Could not parse time parameter from currentTime video GMSG: " + str3);
                }
            } else if ("hide".equalsIgnoreCase(str)) {
                aL.setVisibility(4);
            } else if ("load".equalsIgnoreCase(str)) {
                aL.aV();
            } else if ("pause".equalsIgnoreCase(str)) {
                aL.pause();
            } else if ("play".equalsIgnoreCase(str)) {
                aL.play();
            } else if ("show".equalsIgnoreCase(str)) {
                aL.setVisibility(0);
            } else if ("src".equalsIgnoreCase(str)) {
                aL.o(map.get("src"));
            } else {
                ev.D("Unknown video action: " + str);
            }
        }
    }
}
