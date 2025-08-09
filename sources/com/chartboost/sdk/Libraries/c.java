package com.chartboost.sdk.Libraries;

import android.net.wifi.WifiManager;
import android.provider.Settings;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.impl.aj;
import com.chartboost.sdk.impl.al;
import com.chartboost.sdk.impl.am;

public class c {
    private static String b() {
        if (d.c()) {
            return null;
        }
        return b.b(b.a(e()));
    }

    public static String a() {
        return b.b(c());
    }

    private static byte[] c() {
        String d = d();
        String b = b();
        am amVar = new am();
        amVar.put("uuid", d);
        amVar.put("macid", b);
        return new al().a((aj) amVar);
    }

    private static String d() {
        if (d.c()) {
            return null;
        }
        return Settings.Secure.getString(Chartboost.sharedChartboost().getContext().getContentResolver(), "android_id");
    }

    private static byte[] e() {
        try {
            String macAddress = ((WifiManager) Chartboost.sharedChartboost().getContext().getSystemService("wifi")).getConnectionInfo().getMacAddress();
            if (macAddress == null || macAddress.equals("")) {
                return null;
            }
            String[] split = macAddress.split(":");
            byte[] bArr = new byte[6];
            for (int i = 0; i < split.length; i++) {
                bArr[i] = Integer.valueOf(Integer.parseInt(split[i], 16)).byteValue();
            }
            return bArr;
        } catch (Exception e) {
            return null;
        }
    }
}
