package com.aarki;

import android.content.Context;

public class AarkiContact {
    private static a a;
    private static d b;
    private static String c;

    public interface RewardListener {
        void onFinished(String str, int i);
    }

    private static synchronized void a(Context context) {
        synchronized (AarkiContact.class) {
            a aVar = new a(context);
            a = aVar;
            aVar.start();
            a.a();
        }
    }

    private static synchronized d a() {
        d dVar;
        synchronized (AarkiContact.class) {
            dVar = b;
        }
        return dVar;
    }

    private static synchronized a b() {
        a aVar;
        synchronized (AarkiContact.class) {
            aVar = a;
        }
        return aVar;
    }

    public static String version() {
        return "2.7";
    }

    public static void setUserId(String str) {
        b.a(str);
    }

    public static void setClientType(String str) {
        b.b(str);
    }

    public static String userId() {
        b a2 = b.a();
        if (a2 == null) {
            return null;
        }
        return a2.j();
    }

    public static void setRewardListener(RewardListener rewardListener) {
        if (c != null) {
            if (a() == null || !a().isAlive()) {
                d dVar = new d(c);
                b = dVar;
                dVar.start();
                b.a(rewardListener);
            }
        }
    }

    public static void restartRewardListener() {
        if (a() != null) {
            a().a();
        }
    }

    public static void registerInstall(Context context, String str) {
        b.a(context, (String) null);
        a(context);
        b().a(str);
    }

    public static void registerApp(Context context, String str, String str2) {
        b.a(context, str);
        if ((b() == null || !b().isAlive()) && str2 != null) {
            c = str2;
            a(context);
            if (b() != null) {
                b().a(str2, false);
            }
        }
    }

    public static void registerPaidApp(Context context, String str, String str2) {
        b.a(context, str);
        a(context);
        if (b() != null) {
            b().a(str2, true);
        }
    }

    public static boolean registerEvent(String str) {
        a b2 = b();
        if (b2 == null) {
            return false;
        }
        b2.b(str);
        return true;
    }

    public static void stop() {
        if (b() != null) {
            b().b();
        }
    }
}
