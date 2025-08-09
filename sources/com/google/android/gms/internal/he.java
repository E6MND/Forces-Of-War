package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.internal.hc;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public final class he implements Handler.Callback {
    private static final Object Gs = new Object();
    private static he Gt;
    /* access modifiers changed from: private */
    public final HashMap<String, a> Gu = new HashMap<>();
    private final Context lx;
    private final Handler mHandler;

    final class a {
        /* access modifiers changed from: private */
        public ComponentName GA;
        private final String Gv;
        private final C0059a Gw = new C0059a();
        /* access modifiers changed from: private */
        public final HashSet<hc<?>.f> Gx = new HashSet<>();
        private boolean Gy;
        /* access modifiers changed from: private */
        public IBinder Gz;
        /* access modifiers changed from: private */
        public int mState = 0;

        /* renamed from: com.google.android.gms.internal.he$a$a  reason: collision with other inner class name */
        public class C0059a implements ServiceConnection {
            public C0059a() {
            }

            public void onServiceConnected(ComponentName component, IBinder binder) {
                synchronized (he.this.Gu) {
                    IBinder unused = a.this.Gz = binder;
                    ComponentName unused2 = a.this.GA = component;
                    Iterator it = a.this.Gx.iterator();
                    while (it.hasNext()) {
                        ((hc.f) it.next()).onServiceConnected(component, binder);
                    }
                    int unused3 = a.this.mState = 1;
                }
            }

            public void onServiceDisconnected(ComponentName component) {
                synchronized (he.this.Gu) {
                    IBinder unused = a.this.Gz = null;
                    ComponentName unused2 = a.this.GA = component;
                    Iterator it = a.this.Gx.iterator();
                    while (it.hasNext()) {
                        ((hc.f) it.next()).onServiceDisconnected(component);
                    }
                    int unused3 = a.this.mState = 2;
                }
            }
        }

        public a(String str) {
            this.Gv = str;
        }

        public void B(boolean z) {
            this.Gy = z;
        }

        public void a(hc<?>.f fVar) {
            this.Gx.add(fVar);
        }

        public void b(hc<?>.f fVar) {
            this.Gx.remove(fVar);
        }

        public boolean c(hc<?>.f fVar) {
            return this.Gx.contains(fVar);
        }

        public C0059a fs() {
            return this.Gw;
        }

        public String ft() {
            return this.Gv;
        }

        public boolean fu() {
            return this.Gx.isEmpty();
        }

        public IBinder getBinder() {
            return this.Gz;
        }

        public ComponentName getComponentName() {
            return this.GA;
        }

        public int getState() {
            return this.mState;
        }

        public boolean isBound() {
            return this.Gy;
        }
    }

    private he(Context context) {
        this.mHandler = new Handler(context.getMainLooper(), this);
        this.lx = context.getApplicationContext();
    }

    public static he B(Context context) {
        synchronized (Gs) {
            if (Gt == null) {
                Gt = new he(context.getApplicationContext());
            }
        }
        return Gt;
    }

    public boolean a(String str, hc<?>.f fVar) {
        boolean isBound;
        synchronized (this.Gu) {
            a aVar = this.Gu.get(str);
            if (aVar != null) {
                this.mHandler.removeMessages(0, aVar);
                if (!aVar.c(fVar)) {
                    aVar.a(fVar);
                    switch (aVar.getState()) {
                        case 1:
                            fVar.onServiceConnected(aVar.getComponentName(), aVar.getBinder());
                            break;
                        case 2:
                            aVar.B(this.lx.bindService(new Intent(str).setPackage(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE), aVar.fs(), 129));
                            break;
                    }
                } else {
                    throw new IllegalStateException("Trying to bind a GmsServiceConnection that was already connected before.  startServiceAction=" + str);
                }
            } else {
                aVar = new a(str);
                aVar.a(fVar);
                aVar.B(this.lx.bindService(new Intent(str).setPackage(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE), aVar.fs(), 129));
                this.Gu.put(str, aVar);
            }
            isBound = aVar.isBound();
        }
        return isBound;
    }

    public void b(String str, hc<?>.f fVar) {
        synchronized (this.Gu) {
            a aVar = this.Gu.get(str);
            if (aVar == null) {
                throw new IllegalStateException("Nonexistent connection status for service action: " + str);
            } else if (!aVar.c(fVar)) {
                throw new IllegalStateException("Trying to unbind a GmsServiceConnection  that was not bound before.  startServiceAction=" + str);
            } else {
                aVar.b(fVar);
                if (aVar.fu()) {
                    this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0, aVar), 5000);
                }
            }
        }
    }

    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 0:
                a aVar = (a) msg.obj;
                synchronized (this.Gu) {
                    if (aVar.fu()) {
                        this.lx.unbindService(aVar.fs());
                        this.Gu.remove(aVar.ft());
                    }
                }
                return true;
            default:
                return false;
        }
    }
}
