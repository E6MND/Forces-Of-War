package com.google.android.gms.internal;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.AppEventsConstants;
import java.util.HashMap;
import java.util.Map;

public final class bc {
    public static final bd mR = new bd() {
        public void b(ey eyVar, Map<String, String> map) {
        }
    };
    public static final bd mS = new bd() {
        public void b(ey eyVar, Map<String, String> map) {
            String str = map.get("urls");
            if (TextUtils.isEmpty(str)) {
                ev.D("URLs missing in canOpenURLs GMSG.");
                return;
            }
            String[] split = str.split(",");
            HashMap hashMap = new HashMap();
            PackageManager packageManager = eyVar.getContext().getPackageManager();
            for (String str2 : split) {
                String[] split2 = str2.split(";", 2);
                hashMap.put(str2, Boolean.valueOf(packageManager.resolveActivity(new Intent(split2.length > 1 ? split2[1].trim() : "android.intent.action.VIEW", Uri.parse(split2[0].trim())), 65536) != null));
            }
            eyVar.a("openableURLs", (Map<String, ?>) hashMap);
        }
    };
    public static final bd mT = new bd() {
        public void b(ey eyVar, Map<String, String> map) {
            Uri uri;
            String str = map.get("u");
            if (str == null) {
                ev.D("URL missing from click GMSG.");
                return;
            }
            Uri parse = Uri.parse(str);
            try {
                l bX = eyVar.bX();
                if (bX != null && bX.a(parse)) {
                    uri = bX.a(parse, eyVar.getContext());
                    new et(eyVar.getContext(), eyVar.bY().st, uri.toString()).start();
                }
            } catch (m e) {
                ev.D("Unable to append parameter to URL: " + str);
            }
            uri = parse;
            new et(eyVar.getContext(), eyVar.bY().st, uri.toString()).start();
        }
    };
    public static final bd mU = new bd() {
        public void b(ey eyVar, Map<String, String> map) {
            cg bV = eyVar.bV();
            if (bV == null) {
                ev.D("A GMSG tried to close something that wasn't an overlay.");
            } else {
                bV.close();
            }
        }
    };
    public static final bd mV = new bd() {
        public void b(ey eyVar, Map<String, String> map) {
            cg bV = eyVar.bV();
            if (bV == null) {
                ev.D("A GMSG tried to use a custom close button on something that wasn't an overlay.");
            } else {
                bV.j(AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("custom_close")));
            }
        }
    };
    public static final bd mW = new bd() {
        public void b(ey eyVar, Map<String, String> map) {
            String str = map.get("u");
            if (str == null) {
                ev.D("URL missing from httpTrack GMSG.");
            } else {
                new et(eyVar.getContext(), eyVar.bY().st, str).start();
            }
        }
    };
    public static final bd mX = new bd() {
        public void b(ey eyVar, Map<String, String> map) {
            ev.B("Received log message: " + map.get("string"));
        }
    };
    public static final bd mY = new bd() {
        public void b(ey eyVar, Map<String, String> map) {
            String str = map.get("tx");
            String str2 = map.get("ty");
            String str3 = map.get("td");
            try {
                int parseInt = Integer.parseInt(str);
                int parseInt2 = Integer.parseInt(str2);
                int parseInt3 = Integer.parseInt(str3);
                l bX = eyVar.bX();
                if (bX != null) {
                    bX.y().a(parseInt, parseInt2, parseInt3);
                }
            } catch (NumberFormatException e) {
                ev.D("Could not parse touch parameters from gmsg.");
            }
        }
    };
    public static final bd mZ = new bi();
}
