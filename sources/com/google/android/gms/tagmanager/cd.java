package com.google.android.gms.tagmanager;

import android.net.Uri;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

class cd {
    private static cd agu;
    private volatile String aeq;
    private volatile a agv;
    private volatile String agw;
    private volatile String agx;

    enum a {
        NONE,
        CONTAINER,
        CONTAINER_DEBUG
    }

    cd() {
        clear();
    }

    private String ca(String str) {
        return str.split("&")[0].split("=")[1];
    }

    private String i(Uri uri) {
        return uri.getQuery().replace("&gtm_debug=x", "");
    }

    static cd lY() {
        cd cdVar;
        synchronized (cd.class) {
            if (agu == null) {
                agu = new cd();
            }
            cdVar = agu;
        }
        return cdVar;
    }

    /* access modifiers changed from: package-private */
    public void clear() {
        this.agv = a.NONE;
        this.agw = null;
        this.aeq = null;
        this.agx = null;
    }

    /* access modifiers changed from: package-private */
    public String getContainerId() {
        return this.aeq;
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean h(Uri uri) {
        boolean z = true;
        synchronized (this) {
            try {
                String decode = URLDecoder.decode(uri.toString(), "UTF-8");
                if (decode.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_auth=\\S+&gtm_preview=\\d+(&gtm_debug=x)?$")) {
                    bh.C("Container preview url: " + decode);
                    if (decode.matches(".*?&gtm_debug=x$")) {
                        this.agv = a.CONTAINER_DEBUG;
                    } else {
                        this.agv = a.CONTAINER;
                    }
                    this.agx = i(uri);
                    if (this.agv == a.CONTAINER || this.agv == a.CONTAINER_DEBUG) {
                        this.agw = "/r?" + this.agx;
                    }
                    this.aeq = ca(this.agx);
                } else if (!decode.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_preview=$")) {
                    bh.D("Invalid preview uri: " + decode);
                    z = false;
                } else if (ca(uri.getQuery()).equals(this.aeq)) {
                    bh.C("Exit preview mode for container: " + this.aeq);
                    this.agv = a.NONE;
                    this.agw = null;
                } else {
                    z = false;
                }
            } catch (UnsupportedEncodingException e) {
                z = false;
            }
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public a lZ() {
        return this.agv;
    }

    /* access modifiers changed from: package-private */
    public String ma() {
        return this.agw;
    }
}
