package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.internal.c;
import com.google.android.gms.internal.lf;
import com.google.android.gms.internal.me;
import com.google.android.gms.internal.mf;
import com.google.android.gms.tagmanager.bg;
import com.google.android.gms.tagmanager.cd;
import com.google.android.gms.tagmanager.cq;
import com.google.android.gms.tagmanager.o;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;

class cp implements o.f {
    private final String aeq;
    private bg<lf.a> agK;
    private final ExecutorService agR = Executors.newSingleThreadExecutor();
    private final Context mContext;

    cp(Context context, String str) {
        this.mContext = context;
        this.aeq = str;
    }

    private cq.c a(ByteArrayOutputStream byteArrayOutputStream) {
        try {
            return ba.bY(byteArrayOutputStream.toString("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            bh.z("Tried to convert binary resource to string for JSON parsing; not UTF-8 format");
            return null;
        } catch (JSONException e2) {
            bh.D("Resource is a UTF-8 encoded string but doesn't contain a JSON container");
            return null;
        }
    }

    private cq.c k(byte[] bArr) {
        try {
            return cq.b(c.f.a(bArr));
        } catch (me e) {
            bh.D("Resource doesn't contain a binary container");
            return null;
        } catch (cq.g e2) {
            bh.D("Resource doesn't contain a binary container");
            return null;
        }
    }

    public void a(bg<lf.a> bgVar) {
        this.agK = bgVar;
    }

    public void b(final lf.a aVar) {
        this.agR.execute(new Runnable() {
            public void run() {
                cp.this.c(aVar);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public boolean c(lf.a aVar) {
        boolean z = false;
        File mh = mh();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(mh);
            try {
                fileOutputStream.write(mf.d(aVar));
                z = true;
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    bh.D("error closing stream for writing resource to disk");
                }
            } catch (IOException e2) {
                bh.D("Error writing resource to disk. Removing resource from disk.");
                mh.delete();
                try {
                    fileOutputStream.close();
                } catch (IOException e3) {
                    bh.D("error closing stream for writing resource to disk");
                }
            } catch (Throwable th) {
                try {
                    fileOutputStream.close();
                } catch (IOException e4) {
                    bh.D("error closing stream for writing resource to disk");
                }
                throw th;
            }
        } catch (FileNotFoundException e5) {
            bh.A("Error opening resource file for writing");
        }
        return z;
    }

    public cq.c dn(int i) {
        bh.C("Atttempting to load container from resource ID " + i);
        try {
            InputStream openRawResource = this.mContext.getResources().openRawResource(i);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            cq.b(openRawResource, byteArrayOutputStream);
            cq.c a = a(byteArrayOutputStream);
            return a != null ? a : k(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            bh.D("Error reading default container resource with ID " + i);
            return null;
        } catch (Resources.NotFoundException e2) {
            bh.D("No default container resource found.");
            return null;
        }
    }

    public void lr() {
        this.agR.execute(new Runnable() {
            public void run() {
                cp.this.mg();
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void mg() {
        if (this.agK == null) {
            throw new IllegalStateException("callback must be set before execute");
        }
        this.agK.lq();
        bh.C("Start loading resource from disk ...");
        if ((cd.lY().lZ() == cd.a.CONTAINER || cd.lY().lZ() == cd.a.CONTAINER_DEBUG) && this.aeq.equals(cd.lY().getContainerId())) {
            this.agK.a(bg.a.NOT_AVAILABLE);
            return;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(mh());
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                cq.b(fileInputStream, byteArrayOutputStream);
                this.agK.i(lf.a.l(byteArrayOutputStream.toByteArray()));
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    bh.D("error closing stream for reading resource from disk");
                }
            } catch (IOException e2) {
                bh.D("error reading resource from disk");
                this.agK.a(bg.a.IO_ERROR);
                try {
                    fileInputStream.close();
                } catch (IOException e3) {
                    bh.D("error closing stream for reading resource from disk");
                }
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (IOException e4) {
                    bh.D("error closing stream for reading resource from disk");
                }
                throw th;
            }
            bh.C("Load resource from disk finished.");
        } catch (FileNotFoundException e5) {
            bh.z("resource not on disk");
            this.agK.a(bg.a.NOT_AVAILABLE);
        }
    }

    /* access modifiers changed from: package-private */
    public File mh() {
        return new File(this.mContext.getDir("google_tagmanager", 0), "resource_" + this.aeq);
    }

    public synchronized void release() {
        this.agR.shutdown();
    }
}
