package com.chartboost.sdk.Libraries;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.Signature;
import android.os.Looper;
import android.view.Display;
import android.view.WindowManager;
import com.chartboost.sdk.Chartboost;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.security.auth.x500.X500Principal;
import org.json.JSONArray;
import org.json.JSONObject;

public class d {
    private static String a = null;
    private static final X500Principal b = new X500Principal("CN=Android Debug,O=Android,C=US");

    public static SharedPreferences a() {
        return Chartboost.sharedChartboost().getContext().getSharedPreferences("cbPrefs", 0);
    }

    public static String b() {
        if (c()) {
            return null;
        }
        if (a != null) {
            return a;
        }
        a = c.a();
        return a;
    }

    public static boolean c() {
        return a().getBoolean("cbIdentityTrackingDisabled", false);
    }

    public static boolean d() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static boolean a(Context context) {
        boolean z;
        try {
            Signature[] signatureArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures;
            int i = 0;
            boolean z2 = false;
            while (true) {
                try {
                    if (i < signatureArr.length) {
                        z = ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(signatureArr[i].toByteArray()))).getSubjectX500Principal().equals(b);
                        if (z) {
                            break;
                        }
                        i++;
                        z2 = z;
                    } else {
                        z = z2;
                        break;
                    }
                } catch (Exception e) {
                    z = z2;
                }
            }
        } catch (Exception e2) {
            z = false;
        }
        return z | ((context.getApplicationInfo().flags & 2) != 0);
    }

    public static String a(Map<String, String> map) {
        String str;
        if (map == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (!map.keySet().isEmpty()) {
            sb.append("?");
        }
        for (String next : map.keySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            String str2 = map.get(next);
            if (next != null) {
                try {
                    str = URLEncoder.encode(next, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException("This method requires UTF-8 encoding support", e);
                }
            } else {
                str = "";
            }
            sb.append(str);
            sb.append("=");
            sb.append(str2 != null ? URLEncoder.encode(str2, "UTF-8") : "");
        }
        return sb.toString();
    }

    public static List<String> a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                arrayList.add(jSONArray.getString(i));
            } catch (Exception e) {
            }
        }
        return arrayList;
    }

    public static Map<String, String> a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            try {
                String next = keys.next();
                hashMap.put(next, jSONObject.getString(next));
            } catch (Exception e) {
            }
        }
        return hashMap;
    }

    public static JSONArray a(List<String> list) {
        if (list == null) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            try {
                jSONArray.put(list.get(i));
            } catch (Exception e) {
            }
        }
        return jSONArray;
    }

    public static JSONObject b(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry next : map.entrySet()) {
            try {
                jSONObject.put((String) next.getKey(), next.getValue());
            } catch (Exception e) {
            }
        }
        return jSONObject;
    }

    public static float b(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static int a(int i, Context context) {
        return Math.round(((float) i) * b(context));
    }

    public static float b(int i, Context context) {
        return ((float) i) * b(context);
    }

    public static CBOrientation c(Context context) {
        boolean z;
        boolean z2;
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        int i = context.getResources().getConfiguration().orientation;
        int rotation = defaultDisplay.getRotation();
        if (defaultDisplay.getWidth() == defaultDisplay.getHeight()) {
            z = true;
        } else if (defaultDisplay.getWidth() < defaultDisplay.getHeight()) {
            z = true;
        } else {
            z = true;
        }
        if (z) {
            z2 = true;
        } else if (z) {
            z2 = false;
        } else {
            if (z) {
                if (i == 1) {
                    z2 = true;
                } else if (i == 2) {
                    z2 = false;
                }
            }
            z2 = true;
        }
        if (!(rotation == 0 || rotation == 2)) {
            z2 = !z2;
        }
        if (z2) {
            switch (rotation) {
                case 1:
                    return CBOrientation.LANDSCAPE_LEFT;
                case 2:
                    return CBOrientation.PORTRAIT_REVERSE;
                case 3:
                    return CBOrientation.LANDSCAPE_RIGHT;
                default:
                    return CBOrientation.PORTRAIT;
            }
        } else {
            switch (rotation) {
                case 1:
                    return CBOrientation.PORTRAIT_LEFT;
                case 2:
                    return CBOrientation.LANDSCAPE_REVERSE;
                case 3:
                    return CBOrientation.PORTRAIT_RIGHT;
                default:
                    return CBOrientation.LANDSCAPE;
            }
        }
    }
}
