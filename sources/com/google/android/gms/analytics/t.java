package com.google.android.gms.analytics;

import android.content.Context;
import android.os.Process;
import android.support.v4.os.EnvironmentCompat;
import android.text.TextUtils;
import com.facebook.AppEventsConstants;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.internal.fe;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

class t extends Thread implements f {
    private static t uL;
    private volatile boolean mClosed = false;
    /* access modifiers changed from: private */
    public final Context mContext;
    private final LinkedBlockingQueue<Runnable> uH = new LinkedBlockingQueue<>();
    private volatile boolean uI = false;
    /* access modifiers changed from: private */
    public volatile List<fe> uJ;
    /* access modifiers changed from: private */
    public volatile String uK;
    /* access modifiers changed from: private */
    public volatile ag uM;

    private t(Context context) {
        super("GAThread");
        if (context != null) {
            this.mContext = context.getApplicationContext();
        } else {
            this.mContext = context;
        }
        start();
    }

    static int O(String str) {
        int i = 1;
        if (!TextUtils.isEmpty(str)) {
            i = 0;
            for (int length = str.length() - 1; length >= 0; length--) {
                char charAt = str.charAt(length);
                i = ((i << 6) & 65535) + charAt + (charAt << 14);
                int i2 = 266338304 & i;
                if (i2 != 0) {
                    i ^= i2 >> 21;
                }
            }
        }
        return i;
    }

    private String a(Throwable th) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        th.printStackTrace(printStream);
        printStream.flush();
        return new String(byteArrayOutputStream.toByteArray());
    }

    /* access modifiers changed from: private */
    public String q(Map<String, String> map) {
        return (!map.containsKey("useSecure") || ak.d(map.get("useSecure"), true)) ? "https:" : "http:";
    }

    /* access modifiers changed from: private */
    public boolean r(Map<String, String> map) {
        if (map.get("&sf") == null) {
            return false;
        }
        double a = ak.a(map.get("&sf"), 100.0d);
        if (a >= 100.0d) {
            return false;
        }
        if (((double) (O(map.get("&cid")) % 10000)) < a * 100.0d) {
            return false;
        }
        aa.C(String.format("%s hit sampled out", new Object[]{map.get("&t") == null ? EnvironmentCompat.MEDIA_UNKNOWN : map.get("&t")}));
        return true;
    }

    /* access modifiers changed from: private */
    public void s(Map<String, String> map) {
        m p = a.p(this.mContext);
        ak.a(map, "&adid", p.getValue("&adid"));
        ak.a(map, "&ate", p.getValue("&ate"));
    }

    /* access modifiers changed from: private */
    public void t(Map<String, String> map) {
        g cp = g.cp();
        ak.a(map, "&an", cp.getValue("&an"));
        ak.a(map, "&av", cp.getValue("&av"));
        ak.a(map, "&aid", cp.getValue("&aid"));
        ak.a(map, "&aiid", cp.getValue("&aiid"));
        map.put("&v", AppEventsConstants.EVENT_PARAM_VALUE_YES);
    }

    static t u(Context context) {
        if (uL == null) {
            uL = new t(context);
        }
        return uL;
    }

    static String v(Context context) {
        try {
            FileInputStream openFileInput = context.openFileInput("gaInstallData");
            byte[] bArr = new byte[8192];
            int read = openFileInput.read(bArr, 0, 8192);
            if (openFileInput.available() > 0) {
                aa.A("Too much campaign data, ignoring it.");
                openFileInput.close();
                context.deleteFile("gaInstallData");
                return null;
            }
            openFileInput.close();
            context.deleteFile("gaInstallData");
            if (read <= 0) {
                aa.D("Campaign file is empty.");
                return null;
            }
            String str = new String(bArr, 0, read);
            aa.B("Campaign found: " + str);
            return str;
        } catch (FileNotFoundException e) {
            aa.B("No campaign data found.");
            return null;
        } catch (IOException e2) {
            aa.A("Error reading campaign data.");
            context.deleteFile("gaInstallData");
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Runnable runnable) {
        this.uH.add(runnable);
    }

    public void cg() {
        a((Runnable) new Runnable() {
            public void run() {
                t.this.uM.cg();
            }
        });
    }

    public void cl() {
        a((Runnable) new Runnable() {
            public void run() {
                t.this.uM.cl();
            }
        });
    }

    public void cn() {
        a((Runnable) new Runnable() {
            public void run() {
                t.this.uM.cn();
            }
        });
    }

    public LinkedBlockingQueue<Runnable> co() {
        return this.uH;
    }

    public Thread getThread() {
        return this;
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.uM.cG();
        this.uJ = new ArrayList();
        this.uJ.add(new fe("appendVersion", "&_v".substring(1), "ma4.0.2"));
        this.uJ.add(new fe("appendQueueTime", "&qt".substring(1), (String) null));
        this.uJ.add(new fe("appendCacheBuster", "&z".substring(1), (String) null));
    }

    public void p(Map<String, String> map) {
        final HashMap hashMap = new HashMap(map);
        String str = map.get("&ht");
        if (str != null) {
            try {
                Long.valueOf(str);
            } catch (NumberFormatException e) {
                str = null;
            }
        }
        if (str == null) {
            hashMap.put("&ht", Long.toString(System.currentTimeMillis()));
        }
        a((Runnable) new Runnable() {
            public void run() {
                t.this.s(hashMap);
                if (TextUtils.isEmpty((CharSequence) hashMap.get("&cid"))) {
                    hashMap.put("&cid", h.cq().getValue("&cid"));
                }
                if (!GoogleAnalytics.getInstance(t.this.mContext).getAppOptOut() && !t.this.r(hashMap)) {
                    if (!TextUtils.isEmpty(t.this.uK)) {
                        u.cP().u(true);
                        hashMap.putAll(new HitBuilders.HitBuilder().setCampaignParamsFromUrl(t.this.uK).build());
                        u.cP().u(false);
                        String unused = t.this.uK = null;
                    }
                    t.this.t(hashMap);
                    t.this.uM.b(y.u(hashMap), Long.valueOf((String) hashMap.get("&ht")).longValue(), t.this.q(hashMap), t.this.uJ);
                }
            }
        });
    }

    public void run() {
        Process.setThreadPriority(10);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            aa.D("sleep interrupted in GAThread initialize");
        }
        try {
            if (this.uM == null) {
                this.uM = new s(this.mContext, this);
            }
            init();
            this.uK = v(this.mContext);
            aa.C("Initialized GA Thread");
        } catch (Throwable th) {
            aa.A("Error initializing the GAThread: " + a(th));
            aa.A("Google Analytics will not start up.");
            this.uI = true;
        }
        while (!this.mClosed) {
            try {
                Runnable take = this.uH.take();
                if (!this.uI) {
                    take.run();
                }
            } catch (InterruptedException e2) {
                aa.B(e2.toString());
            } catch (Throwable th2) {
                aa.A("Error on GAThread: " + a(th2));
                aa.A("Google Analytics is shutting down.");
                this.uI = true;
            }
        }
    }
}
