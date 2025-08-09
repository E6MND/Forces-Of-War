package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.gms.internal.c;
import com.google.android.gms.tagmanager.bg;
import com.google.android.gms.tagmanager.cd;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

class cn implements Runnable {
    private volatile String aeM;
    private final String aeq;
    private final bm agI;
    private final String agJ;
    private bg<c.j> agK;
    private volatile r agL;
    private volatile String agM;
    private final Context mContext;

    cn(Context context, String str, bm bmVar, r rVar) {
        this.mContext = context;
        this.agI = bmVar;
        this.aeq = str;
        this.agL = rVar;
        this.agJ = "/r?id=" + str;
        this.aeM = this.agJ;
        this.agM = null;
    }

    public cn(Context context, String str, r rVar) {
        this(context, str, new bm(), rVar);
    }

    private boolean mb() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        bh.C("...no network connectivity");
        return false;
    }

    private void mc() {
        if (!mb()) {
            this.agK.a(bg.a.NOT_AVAILABLE);
            return;
        }
        bh.C("Start loading resource from network ...");
        String md = md();
        bl lM = this.agI.lM();
        try {
            InputStream bV = lM.bV(md);
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                cq.b(bV, byteArrayOutputStream);
                c.j b = c.j.b(byteArrayOutputStream.toByteArray());
                bh.C("Successfully loaded supplemented resource: " + b);
                if (b.fK == null && b.fJ.length == 0) {
                    bh.C("No change for container: " + this.aeq);
                }
                this.agK.i(b);
                lM.close();
                bh.C("Load resource from network finished.");
            } catch (IOException e) {
                bh.c("Error when parsing downloaded resources from url: " + md + " " + e.getMessage(), e);
                this.agK.a(bg.a.SERVER_ERROR);
            }
        } catch (FileNotFoundException e2) {
            bh.D("No data is retrieved from the given url: " + md + ". Make sure container_id: " + this.aeq + " is correct.");
            this.agK.a(bg.a.SERVER_ERROR);
        } catch (IOException e3) {
            bh.c("Error when loading resources from url: " + md + " " + e3.getMessage(), e3);
            this.agK.a(bg.a.IO_ERROR);
        } finally {
            lM.close();
        }
    }

    /* access modifiers changed from: package-private */
    public void a(bg<c.j> bgVar) {
        this.agK = bgVar;
    }

    /* access modifiers changed from: package-private */
    public void bM(String str) {
        if (str == null) {
            this.aeM = this.agJ;
            return;
        }
        bh.z("Setting CTFE URL path: " + str);
        this.aeM = str;
    }

    /* access modifiers changed from: package-private */
    public void cb(String str) {
        bh.z("Setting previous container version: " + str);
        this.agM = str;
    }

    /* access modifiers changed from: package-private */
    public String md() {
        String str = this.agL.ls() + this.aeM + "&v=a65833898";
        if (this.agM != null && !this.agM.trim().equals("")) {
            str = str + "&pv=" + this.agM;
        }
        return cd.lY().lZ().equals(cd.a.CONTAINER_DEBUG) ? str + "&gtm_debug=x" : str;
    }

    public void run() {
        if (this.agK == null) {
            throw new IllegalStateException("callback must be set before execute");
        }
        this.agK.lq();
        mc();
    }
}
