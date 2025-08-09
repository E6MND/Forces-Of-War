package com.google.android.gms.tagmanager;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tagmanager.ContainerHolder;

class n implements ContainerHolder {
    private final Looper DC;
    private boolean Im;
    private b aeA;
    private a aeB;
    private TagManager aeC;
    private Container aey;
    private Container aez;
    private Status yw;

    public interface a {
        void bJ(String str);

        String lj();

        void ll();
    }

    private class b extends Handler {
        private final ContainerHolder.ContainerAvailableListener aeD;

        public b(ContainerHolder.ContainerAvailableListener containerAvailableListener, Looper looper) {
            super(looper);
            this.aeD = containerAvailableListener;
        }

        public void bK(String str) {
            sendMessage(obtainMessage(1, str));
        }

        /* access modifiers changed from: protected */
        public void bL(String str) {
            this.aeD.onContainerAvailable(n.this, str);
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    bL((String) msg.obj);
                    return;
                default:
                    bh.A("Don't know how to handle this message.");
                    return;
            }
        }
    }

    public n(Status status) {
        this.yw = status;
        this.DC = null;
    }

    public n(TagManager tagManager, Looper looper, Container container, a aVar) {
        this.aeC = tagManager;
        this.DC = looper == null ? Looper.getMainLooper() : looper;
        this.aey = container;
        this.aeB = aVar;
        this.yw = Status.Ek;
        tagManager.a(this);
    }

    private void lk() {
        if (this.aeA != null) {
            this.aeA.bK(this.aez.lh());
        }
    }

    public synchronized void a(Container container) {
        if (!this.Im) {
            if (container == null) {
                bh.A("Unexpected null container.");
            } else {
                this.aez = container;
                lk();
            }
        }
    }

    public synchronized void bH(String str) {
        if (!this.Im) {
            this.aey.bH(str);
        }
    }

    /* access modifiers changed from: package-private */
    public void bJ(String str) {
        if (this.Im) {
            bh.A("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
        } else {
            this.aeB.bJ(str);
        }
    }

    public synchronized Container getContainer() {
        Container container = null;
        synchronized (this) {
            if (this.Im) {
                bh.A("ContainerHolder is released.");
            } else {
                if (this.aez != null) {
                    this.aey = this.aez;
                    this.aez = null;
                }
                container = this.aey;
            }
        }
        return container;
    }

    /* access modifiers changed from: package-private */
    public String getContainerId() {
        if (!this.Im) {
            return this.aey.getContainerId();
        }
        bh.A("getContainerId called on a released ContainerHolder.");
        return "";
    }

    public Status getStatus() {
        return this.yw;
    }

    /* access modifiers changed from: package-private */
    public String lj() {
        if (!this.Im) {
            return this.aeB.lj();
        }
        bh.A("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
        return "";
    }

    public synchronized void refresh() {
        if (this.Im) {
            bh.A("Refreshing a released ContainerHolder.");
        } else {
            this.aeB.ll();
        }
    }

    public synchronized void release() {
        if (this.Im) {
            bh.A("Releasing a released ContainerHolder.");
        } else {
            this.Im = true;
            this.aeC.b(this);
            this.aey.release();
            this.aey = null;
            this.aez = null;
            this.aeB = null;
            this.aeA = null;
        }
    }

    public synchronized void setContainerAvailableListener(ContainerHolder.ContainerAvailableListener listener) {
        if (this.Im) {
            bh.A("ContainerHolder is released.");
        } else if (listener == null) {
            this.aeA = null;
        } else {
            this.aeA = new b(listener, this.DC);
            if (this.aez != null) {
                lk();
            }
        }
    }
}
