package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.tagmanager.DataLayer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TagManager {
    private static TagManager aid;
    private final DataLayer aer;
    private final r agL;
    private final a aib;
    private final ConcurrentMap<n, Boolean> aic;
    private final Context mContext;

    interface a {
        o a(Context context, TagManager tagManager, Looper looper, String str, int i, r rVar);
    }

    TagManager(Context context, a containerHolderLoaderProvider, DataLayer dataLayer) {
        if (context == null) {
            throw new NullPointerException("context cannot be null");
        }
        this.mContext = context.getApplicationContext();
        this.aib = containerHolderLoaderProvider;
        this.aic = new ConcurrentHashMap();
        this.aer = dataLayer;
        this.aer.a((DataLayer.b) new DataLayer.b() {
            public void x(Map<String, Object> map) {
                Object obj = map.get(DataLayer.EVENT_KEY);
                if (obj != null) {
                    TagManager.this.cl(obj.toString());
                }
            }
        });
        this.aer.a((DataLayer.b) new d(this.mContext));
        this.agL = new r();
    }

    /* access modifiers changed from: private */
    public void cl(String str) {
        for (n bH : this.aic.keySet()) {
            bH.bH(str);
        }
    }

    public static TagManager getInstance(Context context) {
        TagManager tagManager;
        synchronized (TagManager.class) {
            if (aid == null) {
                if (context == null) {
                    bh.A("TagManager.getInstance requires non-null context.");
                    throw new NullPointerException();
                }
                aid = new TagManager(context, new a() {
                    public o a(Context context, TagManager tagManager, Looper looper, String str, int i, r rVar) {
                        return new o(context, tagManager, looper, str, i, rVar);
                    }
                }, new DataLayer(new v(context)));
            }
            tagManager = aid;
        }
        return tagManager;
    }

    /* access modifiers changed from: package-private */
    public void a(n nVar) {
        this.aic.put(nVar, true);
    }

    /* access modifiers changed from: package-private */
    public boolean b(n nVar) {
        return this.aic.remove(nVar) != null;
    }

    public DataLayer getDataLayer() {
        return this.aer;
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean h(Uri uri) {
        boolean z;
        cd lY = cd.lY();
        if (lY.h(uri)) {
            String containerId = lY.getContainerId();
            switch (lY.lZ()) {
                case NONE:
                    for (n nVar : this.aic.keySet()) {
                        if (nVar.getContainerId().equals(containerId)) {
                            nVar.bJ((String) null);
                            nVar.refresh();
                        }
                    }
                    break;
                case CONTAINER:
                case CONTAINER_DEBUG:
                    for (n nVar2 : this.aic.keySet()) {
                        if (nVar2.getContainerId().equals(containerId)) {
                            nVar2.bJ(lY.ma());
                            nVar2.refresh();
                        } else if (nVar2.lj() != null) {
                            nVar2.bJ((String) null);
                            nVar2.refresh();
                        }
                    }
                    break;
            }
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public PendingResult<ContainerHolder> loadContainerDefaultOnly(String containerId, int defaultContainerResourceId) {
        o a2 = this.aib.a(this.mContext, this, (Looper) null, containerId, defaultContainerResourceId, this.agL);
        a2.lm();
        return a2;
    }

    public PendingResult<ContainerHolder> loadContainerDefaultOnly(String containerId, int defaultContainerResourceId, Handler handler) {
        o a2 = this.aib.a(this.mContext, this, handler.getLooper(), containerId, defaultContainerResourceId, this.agL);
        a2.lm();
        return a2;
    }

    public PendingResult<ContainerHolder> loadContainerPreferFresh(String containerId, int defaultContainerResourceId) {
        o a2 = this.aib.a(this.mContext, this, (Looper) null, containerId, defaultContainerResourceId, this.agL);
        a2.lo();
        return a2;
    }

    public PendingResult<ContainerHolder> loadContainerPreferFresh(String containerId, int defaultContainerResourceId, Handler handler) {
        o a2 = this.aib.a(this.mContext, this, handler.getLooper(), containerId, defaultContainerResourceId, this.agL);
        a2.lo();
        return a2;
    }

    public PendingResult<ContainerHolder> loadContainerPreferNonDefault(String containerId, int defaultContainerResourceId) {
        o a2 = this.aib.a(this.mContext, this, (Looper) null, containerId, defaultContainerResourceId, this.agL);
        a2.ln();
        return a2;
    }

    public PendingResult<ContainerHolder> loadContainerPreferNonDefault(String containerId, int defaultContainerResourceId, Handler handler) {
        o a2 = this.aib.a(this.mContext, this, handler.getLooper(), containerId, defaultContainerResourceId, this.agL);
        a2.ln();
        return a2;
    }

    public void setVerboseLoggingEnabled(boolean enableVerboseLogging) {
        bh.setLogLevel(enableVerboseLogging ? 2 : 5);
    }
}
