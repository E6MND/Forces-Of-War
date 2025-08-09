package com.google.android.gms.analytics;

import android.content.Context;
import com.facebook.AppEventsConstants;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

class h implements m {
    private static h tE;
    private static final Object tn = new Object();
    private final Context mContext;
    /* access modifiers changed from: private */
    public String tF;
    /* access modifiers changed from: private */
    public boolean tG = false;
    /* access modifiers changed from: private */
    public final Object tH = new Object();

    protected h(Context context) {
        this.mContext = context;
        cu();
    }

    private boolean K(String str) {
        try {
            aa.C("Storing clientId.");
            FileOutputStream openFileOutput = this.mContext.openFileOutput("gaClientId", 0);
            openFileOutput.write(str.getBytes());
            openFileOutput.close();
            return true;
        } catch (FileNotFoundException e) {
            aa.A("Error creating clientId file.");
            return false;
        } catch (IOException e2) {
            aa.A("Error writing to clientId file.");
            return false;
        }
    }

    public static h cq() {
        h hVar;
        synchronized (tn) {
            hVar = tE;
        }
        return hVar;
    }

    private String cs() {
        if (!this.tG) {
            synchronized (this.tH) {
                if (!this.tG) {
                    aa.C("Waiting for clientId to load");
                    do {
                        try {
                            this.tH.wait();
                        } catch (InterruptedException e) {
                            aa.A("Exception while waiting for clientId: " + e);
                        }
                    } while (!this.tG);
                }
            }
        }
        aa.C("Loaded clientId");
        return this.tF;
    }

    private void cu() {
        new Thread("client_id_fetcher") {
            public void run() {
                synchronized (h.this.tH) {
                    String unused = h.this.tF = h.this.cv();
                    boolean unused2 = h.this.tG = true;
                    h.this.tH.notifyAll();
                }
            }
        }.start();
    }

    public static void r(Context context) {
        synchronized (tn) {
            if (tE == null) {
                tE = new h(context);
            }
        }
    }

    public boolean J(String str) {
        return "&cid".equals(str);
    }

    /* access modifiers changed from: package-private */
    public String cr() {
        String str;
        synchronized (this.tH) {
            this.tF = ct();
            str = this.tF;
        }
        return str;
    }

    /* access modifiers changed from: protected */
    public String ct() {
        String lowerCase = UUID.randomUUID().toString().toLowerCase();
        try {
            return !K(lowerCase) ? AppEventsConstants.EVENT_PARAM_VALUE_NO : lowerCase;
        } catch (Exception e) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public String cv() {
        String str = null;
        try {
            FileInputStream openFileInput = this.mContext.openFileInput("gaClientId");
            byte[] bArr = new byte[128];
            int read = openFileInput.read(bArr, 0, 128);
            if (openFileInput.available() > 0) {
                aa.A("clientId file seems corrupted, deleting it.");
                openFileInput.close();
                this.mContext.deleteFile("gaClientId");
            } else if (read <= 0) {
                aa.A("clientId file seems empty, deleting it.");
                openFileInput.close();
                this.mContext.deleteFile("gaClientId");
            } else {
                String str2 = new String(bArr, 0, read);
                try {
                    openFileInput.close();
                    aa.C("Loaded client id from disk.");
                    str = str2;
                } catch (FileNotFoundException e) {
                    str = str2;
                } catch (IOException e2) {
                    str = str2;
                    aa.A("Error reading clientId file, deleting it.");
                    this.mContext.deleteFile("gaClientId");
                }
            }
        } catch (FileNotFoundException e3) {
        } catch (IOException e4) {
            aa.A("Error reading clientId file, deleting it.");
            this.mContext.deleteFile("gaClientId");
        }
        return str == null ? ct() : str;
    }

    public String getValue(String field) {
        if ("&cid".equals(field)) {
            return cs();
        }
        return null;
    }
}
