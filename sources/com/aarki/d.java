package com.aarki;

import android.os.Handler;
import android.os.HandlerThread;
import com.aarki.AarkiContact;
import com.aarki.c;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xwalk.core.internal.extension.api.messaging.MessagingSmsConsts;

final class d extends HandlerThread {
    public static boolean a = true;
    /* access modifiers changed from: private */
    public AarkiContact.RewardListener b = null;
    /* access modifiers changed from: private */
    public long c = 10;
    /* access modifiers changed from: private */
    public String d;
    /* access modifiers changed from: private */
    public Handler e;
    private Runnable f = new Runnable() {
        public final void run() {
            b a2 = b.a();
            if (a2 != null && d.this.d != null && d.this.b != null) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new BasicNameValuePair("ofr", d.this.d));
                arrayList.add(new BasicNameValuePair("sdk_version", "2.7"));
                arrayList.add(new BasicNameValuePair("device_platform", b.m()));
                arrayList.add(new BasicNameValuePair("user_id", a2.i()));
                ArrayList arrayList2 = new ArrayList();
                String str = "";
                try {
                    str = c.a(d.this.d + a2.i() + b.l());
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e2) {
                    e2.printStackTrace();
                }
                arrayList2.add(new BasicNameValuePair("sha1_signature", str));
                new c("checkUserRewards").a(c.a("https://hs.aarki.net/user/balance/drain.json", arrayList), arrayList2, new c.b() {
                    public final void a(JSONObject jSONObject) {
                        String str;
                        "Result: " + jSONObject;
                        b a2 = b.a();
                        if (a2 != null) {
                            try {
                                if (jSONObject.getString(MessagingSmsConsts.STATUS).equals("OK")) {
                                    JSONArray jSONArray = jSONObject.getJSONArray("rewards");
                                    "JSONArray: " + jSONArray;
                                    if (jSONArray.length() > 0) {
                                        for (int i = 0; i < jSONArray.length(); i++) {
                                            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                                            int i2 = jSONObject2.getInt("reward");
                                            String string = jSONObject2.getString("user_id");
                                            String string2 = jSONObject2.getString("placement_id");
                                            String string3 = jSONObject2.getString("sha1_signature");
                                            try {
                                                str = c.a(string2 + a2.i() + i2 + b.l());
                                            } catch (NoSuchAlgorithmException e) {
                                                e.printStackTrace();
                                                str = "";
                                            } catch (UnsupportedEncodingException e2) {
                                                e2.printStackTrace();
                                                str = "";
                                            }
                                            if (str.equals(string3)) {
                                                "Reward: user_id: " + string + ", placement_id: " + string2 + ", rewards: " + i2 + ", sha1_signature: " + string3;
                                                d.this.b.onFinished(string2, i2);
                                                long unused = d.this.c = 3600;
                                                "Set timer to MAX: " + d.this.c + " sec.";
                                            } else {
                                                str + " != " + string3;
                                            }
                                        }
                                    } else if (d.a) {
                                        long unused2 = d.this.c = 3600;
                                        "Set timer to MAX: " + d.this.c + " sec.";
                                    } else {
                                        long unused3 = d.this.c = Math.min(d.this.c << 1, 3600);
                                        "Not justLaunched. Increase timer interval to : " + d.this.c + " sec.";
                                    }
                                }
                            } catch (JSONException e3) {
                            }
                        }
                    }

                    public final void a(int i) {
                        "Unable to get user rewards. Http status: " + i;
                        long unused = d.this.c = Math.min(d.this.c << 1, 3600);
                    }
                });
                "Checking rewards. Time interval: " + d.this.c + " sec.";
                if (d.a) {
                    d.this.e.removeCallbacks(this);
                } else if (d.this.c == 3600) {
                    d.this.e.removeCallbacks(this);
                } else {
                    d.this.e.postDelayed(this, d.this.c * 1000);
                }
            }
        }
    };

    d(String str) {
        super("AarkiRewards", 10);
        this.d = str;
    }

    /* access modifiers changed from: package-private */
    public final synchronized void a(AarkiContact.RewardListener rewardListener) {
        if (this.e == null) {
            this.e = new Handler(getLooper());
            this.b = rewardListener;
            this.e.post(this.f);
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized void a() {
        if (this.b != null) {
            this.e.removeCallbacks(this.f);
            this.c = 10;
            a = false;
            this.e.post(this.f);
        }
    }
}
