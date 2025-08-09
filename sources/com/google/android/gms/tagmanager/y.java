package com.google.android.gms.tagmanager;

import android.content.Context;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

class y implements aq {
    private static y afr;
    private static final Object tn = new Object();
    private cf aeG;
    private String afs;
    private String aft;
    private ar afu;

    private y(Context context) {
        this(as.M(context), new cv());
    }

    y(ar arVar, cf cfVar) {
        this.afu = arVar;
        this.aeG = cfVar;
    }

    public static aq K(Context context) {
        y yVar;
        synchronized (tn) {
            if (afr == null) {
                afr = new y(context);
            }
            yVar = afr;
        }
        return yVar;
    }

    public boolean bR(String str) {
        if (!this.aeG.dj()) {
            bh.D("Too many urls sent too quickly with the TagManagerSender, rate limiting invoked.");
            return false;
        }
        if (!(this.afs == null || this.aft == null)) {
            try {
                str = this.afs + "?" + this.aft + "=" + URLEncoder.encode(str, "UTF-8");
                bh.C("Sending wrapped url hit: " + str);
            } catch (UnsupportedEncodingException e) {
                bh.c("Error wrapping URL for testing.", e);
                return false;
            }
        }
        this.afu.bU(str);
        return true;
    }
}
