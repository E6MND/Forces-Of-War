package com.aarki;

import com.aarki.c;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.xwalk.core.internal.extension.api.messaging.MessagingSmsConsts;

public class AarkiUserBalance {
    public static final String AARKI_USER_BALANCE_URL = "https://hs.aarki.net/user/balance";

    public interface Listener {
        void onFinished(Status status, Integer num);
    }

    public enum Status {
        ConnectionFailure,
        OtherFailure,
        OK,
        InsufficientFunds,
        UserNotFound
    }

    private static void a(final String str, String str2, List<NameValuePair> list, List<NameValuePair> list2, final Listener listener) {
        new c(str).a(c.a(AARKI_USER_BALANCE_URL + str2, list), list2, new c.b() {
            public final void a(JSONObject jSONObject) {
                Status status;
                Status status2 = Status.OtherFailure;
                try {
                    String string = jSONObject.getString(MessagingSmsConsts.STATUS);
                    if (string.equals("OK")) {
                        status = Status.OK;
                    } else if (string.equals("INSUFFICIENT_FUNDS")) {
                        status = Status.InsufficientFunds;
                    } else if (string.equals("USER_NOT_FOUND")) {
                        status = Status.UserNotFound;
                    } else {
                        status = status2;
                    }
                    if (listener != null) {
                        listener.onFinished(status, (status == Status.OK || status == Status.InsufficientFunds) ? Integer.valueOf(jSONObject.getInt("balance")) : null);
                    }
                } catch (JSONException e) {
                }
            }

            public final void a(int i) {
                "Unable to process " + str;
                if (listener != null) {
                    listener.onFinished(Status.ConnectionFailure, (Integer) null);
                }
            }
        });
    }

    private static void a(List<NameValuePair> list, String str, String str2) {
        if (str2 != null) {
            list.add(new BasicNameValuePair(str, str2));
        }
    }

    public static void check(String str, Listener listener) {
        b a = b.a();
        if (a != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new BasicNameValuePair("src", str));
            arrayList.add(new BasicNameValuePair("sdk_version", "2.7"));
            arrayList.add(new BasicNameValuePair("device_platform", b.m()));
            a(arrayList, "client_type", a.k());
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new BasicNameValuePair("user_id", a.i()));
            a("check:" + str, "/check.json", arrayList, arrayList2, listener);
        }
    }

    public static void add(String str, int i, Listener listener) {
        b a = b.a();
        if (a != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new BasicNameValuePair("src", str));
            arrayList.add(new BasicNameValuePair("sdk_version", "2.7"));
            arrayList.add(new BasicNameValuePair("device_platform", b.m()));
            a(arrayList, "client_type", a.k());
            ArrayList arrayList2 = new ArrayList();
            String i2 = a.i();
            String num = Integer.toString(i);
            arrayList2.add(new BasicNameValuePair("user_id", i2));
            arrayList2.add(new BasicNameValuePair("amount", num));
            try {
                arrayList2.add(new BasicNameValuePair("sha1_signature", c.a(str + i2 + num + b.l())));
            } catch (Exception e) {
            }
            a("add:" + str, "/add.json", arrayList, arrayList2, listener);
        }
    }

    public static void withdraw(String str, int i, Listener listener) {
        add(str, -i, listener);
    }
}
