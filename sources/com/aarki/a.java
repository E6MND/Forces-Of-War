package com.aarki;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import com.aarki.c;
import com.google.android.gms.tagmanager.DataLayer;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

final class a extends HandlerThread {
    /* access modifiers changed from: private */
    public final SharedPreferences a;
    private final Date b = new Date();
    /* access modifiers changed from: private */
    public boolean c;
    /* access modifiers changed from: private */
    public long d;
    private String e;
    /* access modifiers changed from: private */
    public Handler f;
    private Runnable g = new Runnable() {

        /* renamed from: com.aarki.a$1$a  reason: collision with other inner class name */
        class C0000a implements c.b {
            final /* synthetic */ String a;
            final /* synthetic */ String b;

            static String a(String str) {
                return str == null ? "" : str;
            }

            static String b(String str) {
                try {
                    return URLEncoder.encode(str, "utf-8").replaceAll("\\+", "%20");
                } catch (UnsupportedEncodingException e) {
                    return null;
                }
            }

            static String c(String str) {
                try {
                    return URLDecoder.decode(str, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    return null;
                }
            }

            C0000a(String str, String str2) {
                this.a = str;
                this.b = str2;
            }

            public void a(JSONObject jSONObject) {
                "Successful contact for " + this.a;
                SharedPreferences.Editor edit = a.this.a.edit();
                edit.putString(this.a, "c" + this.b.substring(1));
                edit.commit();
            }

            public void a(int i) {
                "Unable to reach Aarki to report " + this.a;
                SharedPreferences.Editor edit = a.this.a.edit();
                edit.putString(this.a, "w" + this.b.substring(1));
                edit.commit();
            }
        }

        public final void run() {
            boolean z;
            "Stored referrer: " + a.this.a.getString("referrer", (String) null);
            HashMap hashMap = new HashMap();
            Map<String, ?> all = a.this.a.getAll();
            for (Map.Entry next : all.entrySet()) {
                String str = (String) next.getKey();
                if (str.startsWith("event:")) {
                    String str2 = (String) next.getValue();
                    "Event: " + str + " status: " + (str2 == null ? "null" : str2.substring(0, 1));
                    "Event val = " + str2;
                    if (str2.startsWith("w:")) {
                        "Found: " + ((String) next.getKey());
                        C0001a aVar = new C0001a(str2);
                        if (aVar.b != null) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (z) {
                            hashMap.put(str, aVar);
                        }
                    }
                }
            }
            Date date = new Date();
            DateFormat dateTimeInstance = DateFormat.getDateTimeInstance();
            dateTimeInstance.setTimeZone(TimeZone.getTimeZone("gmt"));
            "Checking " + hashMap.size() + " events from " + all.size() + " prefs at: " + dateTimeInstance.format(date);
            if (a.this.c && hashMap.size() == 0) {
                boolean unused = a.this.c = false;
            }
            for (Map.Entry entry : hashMap.entrySet()) {
                String str3 = (String) entry.getKey();
                C0001a aVar2 = (C0001a) entry.getValue();
                String a2 = aVar2.a();
                if (aVar2.a.before(date)) {
                    "Reporting event " + str3;
                    SharedPreferences.Editor edit = a.this.a.edit();
                    edit.putString(str3, "p" + a2.substring(1));
                    edit.commit();
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(new BasicNameValuePair("ofr", aVar2.b));
                    arrayList.add(new BasicNameValuePair("sdk_version", "2.7"));
                    new c(str3).a(c.a("http://postback.aarki.net/appcontact/v1" + (aVar2.c ? "/activity.json" : "/event.json"), arrayList), aVar2.d, new C0000a(str3, a2));
                }
            }
            if (a.this.c) {
                long unused2 = a.this.d = Math.min(a.this.d << 1, 3600);
            } else {
                long unused3 = a.this.d = 3600;
            }
            a.this.f.postDelayed(this, a.this.d * 1000);
        }
    };

    /* renamed from: com.aarki.a$a  reason: collision with other inner class name */
    static class C0001a {
        final Date a;
        final String b;
        final boolean c;
        final List<NameValuePair> d;
        private String e;

        C0001a(String str, Date date, boolean z, List<NameValuePair> list) {
            this.e = "w";
            this.b = str;
            this.a = date;
            this.c = z;
            this.d = list;
        }

        C0001a(String str) {
            List<NameValuePair> list;
            boolean z;
            Date date;
            String str2;
            Date date2;
            String str3 = null;
            String[] split = str.split(":", 5);
            boolean z2 = split.length == 5;
            if (z2) {
                try {
                    date2 = new Date(Long.parseLong(split[2]));
                } catch (NumberFormatException e2) {
                    e2.getLocalizedMessage();
                    date2 = null;
                }
                z = "activity".equals(split[3]);
                list = c.b(split[4]);
                date = date2;
            } else {
                list = null;
                z = false;
                date = null;
            }
            if (z2) {
                str2 = split[0];
            } else {
                str2 = null;
            }
            this.e = str2;
            this.b = z2 ? split[1] : str3;
            this.c = z;
            this.a = date;
            this.d = list;
        }

        /* access modifiers changed from: package-private */
        public final String a() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.e);
            sb.append(':');
            sb.append(this.b);
            sb.append(':');
            sb.append(this.a.getTime());
            sb.append(':');
            sb.append(this.c ? "activity" : DataLayer.EVENT_KEY);
            sb.append(':');
            sb.append(c.a((String) null, this.d));
            return sb.toString();
        }
    }

    a(Context context) {
        super("AarkiContact", 10);
        this.a = context.getSharedPreferences("aarki", 0);
        SharedPreferences.Editor edit = this.a.edit();
        if (this.a.getInt("preferences", 2) < 2) {
            edit.clear();
        }
        edit.putInt("preferences", 2);
        edit.putString("sdk", "2.7");
        edit.commit();
        this.d = 10;
        this.c = true;
        this.e = null;
    }

    /* access modifiers changed from: package-private */
    public final synchronized void a(String str) {
        SharedPreferences.Editor edit = this.a.edit();
        edit.putString("referrer", str);
        edit.commit();
        "Storing referrer: " + str;
    }

    /* access modifiers changed from: package-private */
    public final synchronized void a() {
        if (this.f == null) {
            this.f = new Handler(getLooper());
        }
        this.f.post(this.g);
    }

    private synchronized void c() {
        this.f.removeCallbacks(this.g);
        this.d = 10;
        this.f.post(this.g);
    }

    private static void a(List<NameValuePair> list, String str, String str2) {
        if (str2 != null) {
            list.add(new BasicNameValuePair(str, str2));
        }
    }

    private static String c(String str) {
        String a2 = AnonymousClass1.C0000a.a(str);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a2.length(); i++) {
            sb.append(String.format("%02x", new Object[]{Integer.valueOf(a2.charAt(i) ^ ("Z.*m_R/u%#6wX]^Rk-phjk}>kn[RNw=*w|x;NoE@".charAt(i % "Z.*m_R/u%#6wX]^Rk-phjk}>kn[RNw=*w|x;NoE@".length()) & 255))}));
        }
        return sb.toString();
    }

    static void a(List<NameValuePair> list) {
        b a2 = b.a();
        list.add(new BasicNameValuePair("sdk_version", "2.7"));
        list.add(new BasicNameValuePair("device_platform", b.m()));
        list.add(new BasicNameValuePair("device_model", Build.MODEL));
        list.add(new BasicNameValuePair("device_manufacturer", Build.MANUFACTURER));
        list.add(new BasicNameValuePair("device_brand", Build.BRAND));
        list.add(new BasicNameValuePair("device_product", Build.PRODUCT));
        a(list, "client_type", a2.k());
        a(list, "uixe", c(a2.i()));
        a(list, "pixe", c(a2.b()));
        a(list, "dixe", c(a2.c()));
        a(list, "nixe", c(a2.d()));
        list.add(new BasicNameValuePair("package_name", AnonymousClass1.C0000a.a(a2.h())));
        list.add(new BasicNameValuePair("country_code", AnonymousClass1.C0000a.a(a2.e())));
        list.add(new BasicNameValuePair("current_locale", AnonymousClass1.C0000a.a(a2.f())));
    }

    private void a(String str, String str2, long j, boolean z) {
        String string = this.a.getString("event:" + str, (String) null);
        if (string != null && string.contains("activity")) {
            "ACTIVITY: " + string;
        }
        if (string == null || !string.contains(DataLayer.EVENT_KEY) || !str.equalsIgnoreCase("install")) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new BasicNameValuePair("app_key", this.e));
            a((List<NameValuePair>) arrayList);
            a(arrayList, "user_agent", b.a().g());
            a(arrayList, "referrer", this.a.getString("referrer", (String) null));
            DateFormat dateTimeInstance = DateFormat.getDateTimeInstance();
            dateTimeInstance.setTimeZone(TimeZone.getTimeZone("gmt"));
            arrayList.add(new BasicNameValuePair("launch_time", dateTimeInstance.format(this.b)));
            Date date = new Date();
            "Now: " + dateTimeInstance.format(date);
            if (j > 0) {
                date.setTime(date.getTime() + (1000 * j));
            }
            arrayList.add(new BasicNameValuePair("event_queue_id", str + ":" + new Random().nextInt(AccessibilityEventCompat.TYPE_TOUCH_INTERACTION_START)));
            arrayList.add(new BasicNameValuePair("scheduled_time", dateTimeInstance.format(date)));
            "Queueing contact at: " + dateTimeInstance.format(date);
            C0001a aVar = new C0001a(str2, date, z, arrayList);
            SharedPreferences.Editor edit = this.a.edit();
            edit.putString("event:" + str, aVar.a());
            edit.commit();
            this.c = true;
            c();
            return;
        }
        "INSTALL: " + string;
        "Previously queued event '" + str + "': " + string.charAt(0);
    }

    /* access modifiers changed from: package-private */
    public final synchronized void a(String str, boolean z) {
        long j = 0;
        synchronized (this) {
            this.e = str;
            if (z) {
                j = 910;
            }
            a("install", str, j, false);
            a("activity", str, 0, true);
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized void b(String str) {
        a("appevent-" + str, str, 0, false);
    }

    /* access modifiers changed from: package-private */
    public final synchronized void b() {
    }
}
