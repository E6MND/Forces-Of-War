package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.c;
import com.google.android.gms.internal.hh;
import com.google.android.gms.internal.hn;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class a {

    /* renamed from: com.google.android.gms.common.api.a$a  reason: collision with other inner class name */
    public static abstract class C0011a<R extends Result> implements PendingResult<R>, d<R> {
        private final Object Dm = new Object();
        private c<R> Dn;
        private final ArrayList<PendingResult.a> Do = new ArrayList<>();
        private ResultCallback<R> Dp;
        private volatile R Dq;
        private volatile boolean Dr;
        private boolean Ds;
        private boolean Dt;
        private hh Du;
        private final CountDownLatch kI = new CountDownLatch(1);

        C0011a() {
        }

        public C0011a(Looper looper) {
            this.Dn = new c<>(looper);
        }

        public C0011a(c<R> cVar) {
            this.Dn = cVar;
        }

        private void c(R r) {
            this.Dq = r;
            this.Du = null;
            this.kI.countDown();
            Status status = this.Dq.getStatus();
            if (this.Dp != null) {
                this.Dn.eC();
                if (!this.Ds) {
                    this.Dn.a(this.Dp, ex());
                }
            }
            Iterator<PendingResult.a> it = this.Do.iterator();
            while (it.hasNext()) {
                it.next().n(status);
            }
            this.Do.clear();
        }

        /* access modifiers changed from: private */
        public void eA() {
            synchronized (this.Dm) {
                if (!isReady()) {
                    a(c(Status.En));
                    this.Dt = true;
                }
            }
        }

        private R ex() {
            R r;
            synchronized (this.Dm) {
                hn.a(!this.Dr, "Result has already been consumed.");
                hn.a(isReady(), "Result is not ready.");
                r = this.Dq;
                ey();
            }
            return r;
        }

        private void ez() {
            synchronized (this.Dm) {
                if (!isReady()) {
                    a(c(Status.El));
                    this.Dt = true;
                }
            }
        }

        public final void a(PendingResult.a aVar) {
            hn.a(!this.Dr, "Result has already been consumed.");
            synchronized (this.Dm) {
                if (isReady()) {
                    aVar.n(this.Dq.getStatus());
                } else {
                    this.Do.add(aVar);
                }
            }
        }

        /* access modifiers changed from: protected */
        public void a(c<R> cVar) {
            this.Dn = cVar;
        }

        /* access modifiers changed from: protected */
        public final void a(hh hhVar) {
            synchronized (this.Dm) {
                this.Du = hhVar;
            }
        }

        public final R await() {
            boolean z = true;
            hn.a(Looper.myLooper() != Looper.getMainLooper(), "await must not be called on the UI thread");
            if (this.Dr) {
                z = false;
            }
            hn.a(z, "Result has already been consumed");
            try {
                this.kI.await();
            } catch (InterruptedException e) {
                ez();
            }
            hn.a(isReady(), "Result is not ready.");
            return ex();
        }

        public final R await(long time, TimeUnit units) {
            boolean z = true;
            hn.a(time <= 0 || Looper.myLooper() != Looper.getMainLooper(), "await must not be called on the UI thread when time is greater than zero.");
            if (this.Dr) {
                z = false;
            }
            hn.a(z, "Result has already been consumed.");
            try {
                if (!this.kI.await(time, units)) {
                    eA();
                }
            } catch (InterruptedException e) {
                ez();
            }
            hn.a(isReady(), "Result is not ready.");
            return ex();
        }

        /* renamed from: b */
        public final void a(R r) {
            boolean z = true;
            synchronized (this.Dm) {
                if (this.Dt || this.Ds) {
                    a.a(r);
                    return;
                }
                hn.a(!isReady(), "Results have already been set");
                if (this.Dr) {
                    z = false;
                }
                hn.a(z, "Result has already been consumed");
                c(r);
            }
        }

        /* access modifiers changed from: protected */
        public abstract R c(Status status);

        /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void cancel() {
            /*
                r2 = this;
                java.lang.Object r1 = r2.Dm
                monitor-enter(r1)
                boolean r0 = r2.Ds     // Catch:{ all -> 0x002c }
                if (r0 != 0) goto L_0x000b
                boolean r0 = r2.Dr     // Catch:{ all -> 0x002c }
                if (r0 == 0) goto L_0x000d
            L_0x000b:
                monitor-exit(r1)     // Catch:{ all -> 0x002c }
            L_0x000c:
                return
            L_0x000d:
                com.google.android.gms.internal.hh r0 = r2.Du     // Catch:{ all -> 0x002c }
                if (r0 == 0) goto L_0x0016
                com.google.android.gms.internal.hh r0 = r2.Du     // Catch:{ RemoteException -> 0x002f }
                r0.cancel()     // Catch:{ RemoteException -> 0x002f }
            L_0x0016:
                R r0 = r2.Dq     // Catch:{ all -> 0x002c }
                com.google.android.gms.common.api.a.a(r0)     // Catch:{ all -> 0x002c }
                r0 = 0
                r2.Dp = r0     // Catch:{ all -> 0x002c }
                r0 = 1
                r2.Ds = r0     // Catch:{ all -> 0x002c }
                com.google.android.gms.common.api.Status r0 = com.google.android.gms.common.api.Status.Eo     // Catch:{ all -> 0x002c }
                com.google.android.gms.common.api.Result r0 = r2.c((com.google.android.gms.common.api.Status) r0)     // Catch:{ all -> 0x002c }
                r2.c(r0)     // Catch:{ all -> 0x002c }
                monitor-exit(r1)     // Catch:{ all -> 0x002c }
                goto L_0x000c
            L_0x002c:
                r0 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x002c }
                throw r0
            L_0x002f:
                r0 = move-exception
                goto L_0x0016
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.a.C0011a.cancel():void");
        }

        /* access modifiers changed from: protected */
        public void ey() {
            this.Dr = true;
            this.Dq = null;
            this.Dp = null;
        }

        public boolean isCanceled() {
            boolean z;
            synchronized (this.Dm) {
                z = this.Ds;
            }
            return z;
        }

        public final boolean isReady() {
            return this.kI.getCount() == 0;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void setResultCallback(com.google.android.gms.common.api.ResultCallback<R> r4) {
            /*
                r3 = this;
                boolean r0 = r3.Dr
                if (r0 != 0) goto L_0x0015
                r0 = 1
            L_0x0005:
                java.lang.String r1 = "Result has already been consumed."
                com.google.android.gms.internal.hn.a(r0, r1)
                java.lang.Object r1 = r3.Dm
                monitor-enter(r1)
                boolean r0 = r3.isCanceled()     // Catch:{ all -> 0x0028 }
                if (r0 == 0) goto L_0x0017
                monitor-exit(r1)     // Catch:{ all -> 0x0028 }
            L_0x0014:
                return
            L_0x0015:
                r0 = 0
                goto L_0x0005
            L_0x0017:
                boolean r0 = r3.isReady()     // Catch:{ all -> 0x0028 }
                if (r0 == 0) goto L_0x002b
                com.google.android.gms.common.api.a$c<R> r0 = r3.Dn     // Catch:{ all -> 0x0028 }
                com.google.android.gms.common.api.Result r2 = r3.ex()     // Catch:{ all -> 0x0028 }
                r0.a(r4, r2)     // Catch:{ all -> 0x0028 }
            L_0x0026:
                monitor-exit(r1)     // Catch:{ all -> 0x0028 }
                goto L_0x0014
            L_0x0028:
                r0 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x0028 }
                throw r0
            L_0x002b:
                r3.Dp = r4     // Catch:{ all -> 0x0028 }
                goto L_0x0026
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.a.C0011a.setResultCallback(com.google.android.gms.common.api.ResultCallback):void");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void setResultCallback(com.google.android.gms.common.api.ResultCallback<R> r5, long r6, java.util.concurrent.TimeUnit r8) {
            /*
                r4 = this;
                boolean r0 = r4.Dr
                if (r0 != 0) goto L_0x0015
                r0 = 1
            L_0x0005:
                java.lang.String r1 = "Result has already been consumed."
                com.google.android.gms.internal.hn.a(r0, r1)
                java.lang.Object r1 = r4.Dm
                monitor-enter(r1)
                boolean r0 = r4.isCanceled()     // Catch:{ all -> 0x0028 }
                if (r0 == 0) goto L_0x0017
                monitor-exit(r1)     // Catch:{ all -> 0x0028 }
            L_0x0014:
                return
            L_0x0015:
                r0 = 0
                goto L_0x0005
            L_0x0017:
                boolean r0 = r4.isReady()     // Catch:{ all -> 0x0028 }
                if (r0 == 0) goto L_0x002b
                com.google.android.gms.common.api.a$c<R> r0 = r4.Dn     // Catch:{ all -> 0x0028 }
                com.google.android.gms.common.api.Result r2 = r4.ex()     // Catch:{ all -> 0x0028 }
                r0.a(r5, r2)     // Catch:{ all -> 0x0028 }
            L_0x0026:
                monitor-exit(r1)     // Catch:{ all -> 0x0028 }
                goto L_0x0014
            L_0x0028:
                r0 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x0028 }
                throw r0
            L_0x002b:
                r4.Dp = r5     // Catch:{ all -> 0x0028 }
                com.google.android.gms.common.api.a$c<R> r0 = r4.Dn     // Catch:{ all -> 0x0028 }
                long r2 = r8.toMillis(r6)     // Catch:{ all -> 0x0028 }
                r0.a(r4, (long) r2)     // Catch:{ all -> 0x0028 }
                goto L_0x0026
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.a.C0011a.setResultCallback(com.google.android.gms.common.api.ResultCallback, long, java.util.concurrent.TimeUnit):void");
        }
    }

    public static abstract class b<R extends Result, A extends Api.a> extends C0011a<R> implements c.C0012c<A> {
        private final Api.c<A> Dk;
        private c.a Dv;

        protected b(Api.c<A> cVar) {
            this.Dk = (Api.c) hn.f(cVar);
        }

        private void a(RemoteException remoteException) {
            m(new Status(8, remoteException.getLocalizedMessage(), (PendingIntent) null));
        }

        /* access modifiers changed from: protected */
        public abstract void a(A a) throws RemoteException;

        public void a(c.a aVar) {
            this.Dv = aVar;
        }

        public final void b(A a) throws DeadObjectException {
            a(new c(a.getLooper()));
            try {
                a(a);
            } catch (DeadObjectException e) {
                a((RemoteException) e);
                throw e;
            } catch (RemoteException e2) {
                a(e2);
            }
        }

        public int eB() {
            return 0;
        }

        public final Api.c<A> ew() {
            return this.Dk;
        }

        /* access modifiers changed from: protected */
        public void ey() {
            super.ey();
            if (this.Dv != null) {
                this.Dv.b(this);
                this.Dv = null;
            }
        }

        public final void m(Status status) {
            hn.b(!status.isSuccess(), (Object) "Failed result must not be success");
            a(c(status));
        }
    }

    public static class c<R extends Result> extends Handler {
        public c() {
            this(Looper.getMainLooper());
        }

        public c(Looper looper) {
            super(looper);
        }

        public void a(ResultCallback<R> resultCallback, R r) {
            sendMessage(obtainMessage(1, new Pair(resultCallback, r)));
        }

        public void a(C0011a<R> aVar, long j) {
            sendMessageDelayed(obtainMessage(2, aVar), j);
        }

        /* access modifiers changed from: protected */
        public void b(ResultCallback<R> resultCallback, R r) {
            try {
                resultCallback.onResult(r);
            } catch (RuntimeException e) {
                a.a(r);
                throw e;
            }
        }

        public void eC() {
            removeMessages(2);
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Pair pair = (Pair) msg.obj;
                    b((ResultCallback) pair.first, (Result) pair.second);
                    return;
                case 2:
                    ((C0011a) msg.obj).eA();
                    return;
                default:
                    Log.wtf("GoogleApi", "Don't know how to handle this message.");
                    return;
            }
        }
    }

    public interface d<R> {
        void a(R r);
    }

    static void a(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (RuntimeException e) {
                Log.w("GoogleApi", "Unable to release " + result, e);
            }
        }
    }
}
