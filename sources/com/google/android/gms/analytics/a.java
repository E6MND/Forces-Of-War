package com.google.android.gms.analytics;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.AppEventsConstants;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Locale;

class a implements m {
    private static Object tn = new Object();
    private static a to;
    private Context mContext;
    private AdvertisingIdClient.Info tp;
    private long tq;
    private String tr;
    private boolean ts = false;
    private Object tt = new Object();

    a(Context context) {
        this.mContext = context.getApplicationContext();
    }

    static String H(String str) {
        MessageDigest W = ak.W("MD5");
        if (W == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new Object[]{new BigInteger(1, W.digest(str.getBytes()))});
    }

    private boolean I(String str) {
        try {
            String H = H(str);
            aa.C("Storing hashed adid.");
            FileOutputStream openFileOutput = this.mContext.openFileOutput("gaClientIdData", 0);
            openFileOutput.write(H.getBytes());
            openFileOutput.close();
            this.tr = H;
            return true;
        } catch (FileNotFoundException e) {
            aa.A("Error creating hash file.");
            return false;
        } catch (IOException e2) {
            aa.A("Error writing to hash file.");
            return false;
        }
    }

    private boolean a(AdvertisingIdClient.Info info, AdvertisingIdClient.Info info2) {
        String str;
        String str2 = null;
        String id = info2 == null ? null : info2.getId();
        if (TextUtils.isEmpty(id)) {
            return true;
        }
        h.r(this.mContext);
        h cq = h.cq();
        String value = cq.getValue("&cid");
        synchronized (this.tt) {
            if (!this.ts) {
                this.tr = q(this.mContext);
                this.ts = true;
            } else if (TextUtils.isEmpty(this.tr)) {
                if (info != null) {
                    str2 = info.getId();
                }
                if (str2 == null) {
                    boolean I = I(id + value);
                    return I;
                }
                this.tr = H(str2 + value);
            }
            String H = H(id + value);
            if (TextUtils.isEmpty(H)) {
                return false;
            }
            if (H.equals(this.tr)) {
                return true;
            }
            if (!TextUtils.isEmpty(this.tr)) {
                aa.C("Resetting the client id because Advertising Id changed.");
                str = cq.cr();
                aa.C("New client Id: " + str);
            } else {
                str = value;
            }
            boolean I2 = I(id + str);
            return I2;
        }
    }

    public static m p(Context context) {
        if (to == null) {
            synchronized (tn) {
                if (to == null) {
                    to = new a(context);
                }
            }
        }
        return to;
    }

    static String q(Context context) {
        String str = null;
        try {
            FileInputStream openFileInput = context.openFileInput("gaClientIdData");
            byte[] bArr = new byte[128];
            int read = openFileInput.read(bArr, 0, 128);
            if (openFileInput.available() > 0) {
                aa.D("Hash file seems corrupted, deleting it.");
                openFileInput.close();
                context.deleteFile("gaClientIdData");
                return null;
            } else if (read <= 0) {
                aa.B("Hash file is empty.");
                openFileInput.close();
                return null;
            } else {
                String str2 = new String(bArr, 0, read);
                try {
                    openFileInput.close();
                    return str2;
                } catch (FileNotFoundException e) {
                    return str2;
                } catch (IOException e2) {
                    str = str2;
                    aa.D("Error reading Hash file, deleting it.");
                    context.deleteFile("gaClientIdData");
                    return str;
                }
            }
        } catch (FileNotFoundException e3) {
            return null;
        } catch (IOException e4) {
            aa.D("Error reading Hash file, deleting it.");
            context.deleteFile("gaClientIdData");
            return str;
        }
    }

    /* access modifiers changed from: package-private */
    public AdvertisingIdClient.Info cf() {
        try {
            return AdvertisingIdClient.getAdvertisingIdInfo(this.mContext);
        } catch (IllegalStateException e) {
            aa.D("IllegalStateException getting Ad Id Info. If you would like to see Audience reports, please ensure that you have added '<meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />' to your application manifest file. See http://goo.gl/naFqQk for details.");
            return null;
        } catch (GooglePlayServicesRepairableException e2) {
            aa.D("GooglePlayServicesRepairableException getting Ad Id Info");
            return null;
        } catch (IOException e3) {
            aa.D("IOException getting Ad Id Info");
            return null;
        } catch (GooglePlayServicesNotAvailableException e4) {
            aa.D("GooglePlayServicesNotAvailableException getting Ad Id Info");
            return null;
        } catch (Exception e5) {
            aa.D("Unknown exception. Could not get the ad Id.");
            return null;
        }
    }

    public String getValue(String field) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.tq > 1000) {
            AdvertisingIdClient.Info cf = cf();
            if (a(this.tp, cf)) {
                this.tp = cf;
            } else {
                this.tp = new AdvertisingIdClient.Info("", false);
            }
            this.tq = currentTimeMillis;
        }
        if (this.tp != null) {
            if ("&adid".equals(field)) {
                return this.tp.getId();
            }
            if ("&ate".equals(field)) {
                return this.tp.isLimitAdTrackingEnabled() ? AppEventsConstants.EVENT_PARAM_VALUE_NO : AppEventsConstants.EVENT_PARAM_VALUE_YES;
            }
        }
        return null;
    }
}
