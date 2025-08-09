package com.google.android.gms.plus.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.Cif;
import com.google.android.gms.internal.hc;
import com.google.android.gms.internal.hh;
import com.google.android.gms.internal.hj;
import com.google.android.gms.internal.kq;
import com.google.android.gms.internal.kt;
import com.google.android.gms.plus.Moments;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.internal.d;
import com.google.android.gms.plus.model.moments.Moment;
import com.google.android.gms.plus.model.moments.MomentBuffer;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.model.people.PersonBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class e extends hc<d> {
    private Person abG;
    private final h abH;

    final class a extends a {
        private final a.d<Status> abI;

        public a(a.d<Status> dVar) {
            this.abI = dVar;
        }

        public void am(Status status) {
            e.this.a((hc<T>.b<?>) new d(this.abI, status));
        }
    }

    final class b extends a {
        private final a.d<Moments.LoadMomentsResult> abI;

        public b(a.d<Moments.LoadMomentsResult> dVar) {
            this.abI = dVar;
        }

        public void a(DataHolder dataHolder, String str, String str2) {
            DataHolder dataHolder2;
            Status status = new Status(dataHolder.getStatusCode(), (String) null, dataHolder.eP() != null ? (PendingIntent) dataHolder.eP().getParcelable("pendingIntent") : null);
            if (status.isSuccess() || dataHolder == null) {
                dataHolder2 = dataHolder;
            } else {
                if (!dataHolder.isClosed()) {
                    dataHolder.close();
                }
                dataHolder2 = null;
            }
            e.this.a((hc<T>.b<?>) new c(this.abI, status, dataHolder2, str, str2));
        }
    }

    final class c extends hc<d>.d<a.d<Moments.LoadMomentsResult>> implements Moments.LoadMomentsResult {
        private final String HP;
        private final String abK;
        private MomentBuffer abL;
        private final Status yw;

        public c(a.d<Moments.LoadMomentsResult> dVar, Status status, DataHolder dataHolder, String str, String str2) {
            super(dVar, dataHolder);
            this.yw = status;
            this.HP = str;
            this.abK = str2;
        }

        /* access modifiers changed from: protected */
        public void a(a.d<Moments.LoadMomentsResult> dVar, DataHolder dataHolder) {
            this.abL = dataHolder != null ? new MomentBuffer(dataHolder) : null;
            dVar.a(this);
        }

        public MomentBuffer getMomentBuffer() {
            return this.abL;
        }

        public String getNextPageToken() {
            return this.HP;
        }

        public Status getStatus() {
            return this.yw;
        }

        public String getUpdated() {
            return this.abK;
        }

        public void release() {
            if (this.abL != null) {
                this.abL.close();
            }
        }
    }

    final class d extends hc<d>.b<a.d<Status>> {
        private final Status yw;

        public d(a.d<Status> dVar, Status status) {
            super(dVar);
            this.yw = status;
        }

        /* access modifiers changed from: protected */
        public void fp() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: n */
        public void d(a.d<Status> dVar) {
            if (dVar != null) {
                dVar.a(this.yw);
            }
        }
    }

    /* renamed from: com.google.android.gms.plus.internal.e$e  reason: collision with other inner class name */
    final class C0127e extends a {
        private final a.d<People.LoadPeopleResult> abI;

        public C0127e(a.d<People.LoadPeopleResult> dVar) {
            this.abI = dVar;
        }

        public void a(DataHolder dataHolder, String str) {
            DataHolder dataHolder2;
            Status status = new Status(dataHolder.getStatusCode(), (String) null, dataHolder.eP() != null ? (PendingIntent) dataHolder.eP().getParcelable("pendingIntent") : null);
            if (status.isSuccess() || dataHolder == null) {
                dataHolder2 = dataHolder;
            } else {
                if (!dataHolder.isClosed()) {
                    dataHolder.close();
                }
                dataHolder2 = null;
            }
            e.this.a((hc<T>.b<?>) new f(this.abI, status, dataHolder2, str));
        }
    }

    final class f extends hc<d>.d<a.d<People.LoadPeopleResult>> implements People.LoadPeopleResult {
        private final String HP;
        private PersonBuffer abM;
        private final Status yw;

        public f(a.d<People.LoadPeopleResult> dVar, Status status, DataHolder dataHolder, String str) {
            super(dVar, dataHolder);
            this.yw = status;
            this.HP = str;
        }

        /* access modifiers changed from: protected */
        public void a(a.d<People.LoadPeopleResult> dVar, DataHolder dataHolder) {
            this.abM = dataHolder != null ? new PersonBuffer(dataHolder) : null;
            dVar.a(this);
        }

        public String getNextPageToken() {
            return this.HP;
        }

        public PersonBuffer getPersonBuffer() {
            return this.abM;
        }

        public Status getStatus() {
            return this.yw;
        }

        public void release() {
            if (this.abM != null) {
                this.abM.close();
            }
        }
    }

    final class g extends a {
        private final a.d<Status> abI;

        public g(a.d<Status> dVar) {
            this.abI = dVar;
        }

        public void h(int i, Bundle bundle) {
            e.this.a((hc<T>.b<?>) new h(this.abI, new Status(i, (String) null, bundle != null ? (PendingIntent) bundle.getParcelable("pendingIntent") : null)));
        }
    }

    final class h extends hc<d>.b<a.d<Status>> {
        private final Status yw;

        public h(a.d<Status> dVar, Status status) {
            super(dVar);
            this.yw = status;
        }

        /* access modifiers changed from: protected */
        public void fp() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: n */
        public void d(a.d<Status> dVar) {
            e.this.disconnect();
            if (dVar != null) {
                dVar.a(this.yw);
            }
        }
    }

    public e(Context context, Looper looper, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, h hVar) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, hVar.jU());
        this.abH = hVar;
    }

    @Deprecated
    public e(Context context, GooglePlayServicesClient.ConnectionCallbacks connectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener onConnectionFailedListener, h hVar) {
        this(context, context.getMainLooper(), new hc.c(connectionCallbacks), new hc.g(onConnectionFailedListener), hVar);
    }

    public hh a(a.d<People.LoadPeopleResult> dVar, int i, String str) {
        ci();
        C0127e eVar = new C0127e(dVar);
        try {
            return ((d) fo()).a(eVar, 1, i, -1, str);
        } catch (RemoteException e) {
            eVar.a(DataHolder.af(8), (String) null);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void a(int i, IBinder iBinder, Bundle bundle) {
        if (i == 0 && bundle != null && bundle.containsKey("loaded_person")) {
            this.abG = kt.i(bundle.getByteArray("loaded_person"));
        }
        super.a(i, iBinder, bundle);
    }

    public void a(a.d<Moments.LoadMomentsResult> dVar, int i, String str, Uri uri, String str2, String str3) {
        ci();
        b bVar = dVar != null ? new b(dVar) : null;
        try {
            ((d) fo()).a(bVar, i, str, uri, str2, str3);
        } catch (RemoteException e) {
            bVar.a(DataHolder.af(8), (String) null, (String) null);
        }
    }

    public void a(a.d<Status> dVar, Moment moment) {
        ci();
        a aVar = dVar != null ? new a(dVar) : null;
        try {
            ((d) fo()).a((b) aVar, Cif.a((kq) moment));
        } catch (RemoteException e) {
            if (aVar == null) {
                throw new IllegalStateException(e);
            }
            aVar.am(new Status(8, (String) null, (PendingIntent) null));
        }
    }

    public void a(a.d<People.LoadPeopleResult> dVar, Collection<String> collection) {
        ci();
        C0127e eVar = new C0127e(dVar);
        try {
            ((d) fo()).a((b) eVar, (List<String>) new ArrayList(collection));
        } catch (RemoteException e) {
            eVar.a(DataHolder.af(8), (String) null);
        }
    }

    /* access modifiers changed from: protected */
    public void a(hj hjVar, hc.e eVar) throws RemoteException {
        Bundle kc = this.abH.kc();
        kc.putStringArray("request_visible_actions", this.abH.jV());
        hjVar.a(eVar, GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE, this.abH.jY(), this.abH.jX(), fn(), this.abH.getAccountName(), kc);
    }

    /* access modifiers changed from: protected */
    /* renamed from: bn */
    public d x(IBinder iBinder) {
        return d.a.bm(iBinder);
    }

    /* access modifiers changed from: protected */
    public String bp() {
        return "com.google.android.gms.plus.service.START";
    }

    /* access modifiers changed from: protected */
    public String bq() {
        return "com.google.android.gms.plus.internal.IPlusService";
    }

    public boolean by(String str) {
        return Arrays.asList(fn()).contains(str);
    }

    public void clearDefaultAccount() {
        ci();
        try {
            this.abG = null;
            ((d) fo()).clearDefaultAccount();
        } catch (RemoteException e) {
            throw new IllegalStateException(e);
        }
    }

    public void d(a.d<People.LoadPeopleResult> dVar, String[] strArr) {
        a(dVar, (Collection<String>) Arrays.asList(strArr));
    }

    public String getAccountName() {
        ci();
        try {
            return ((d) fo()).getAccountName();
        } catch (RemoteException e) {
            throw new IllegalStateException(e);
        }
    }

    public Person getCurrentPerson() {
        ci();
        return this.abG;
    }

    public void k(a.d<Moments.LoadMomentsResult> dVar) {
        a(dVar, 20, (String) null, (Uri) null, (String) null, "me");
    }

    public void l(a.d<People.LoadPeopleResult> dVar) {
        ci();
        C0127e eVar = new C0127e(dVar);
        try {
            ((d) fo()).a(eVar, 2, 1, -1, (String) null);
        } catch (RemoteException e) {
            eVar.a(DataHolder.af(8), (String) null);
        }
    }

    public void m(a.d<Status> dVar) {
        ci();
        clearDefaultAccount();
        g gVar = new g(dVar);
        try {
            ((d) fo()).b(gVar);
        } catch (RemoteException e) {
            gVar.h(8, (Bundle) null);
        }
    }

    public hh r(a.d<People.LoadPeopleResult> dVar, String str) {
        return a(dVar, 0, str);
    }

    public void removeMoment(String momentId) {
        ci();
        try {
            ((d) fo()).removeMoment(momentId);
        } catch (RemoteException e) {
            throw new IllegalStateException(e);
        }
    }
}
