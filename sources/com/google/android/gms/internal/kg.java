package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import com.google.android.gms.internal.ke;
import com.google.android.gms.panorama.Panorama;
import com.google.android.gms.panorama.PanoramaApi;

public class kg implements PanoramaApi {

    /* renamed from: com.google.android.gms.internal.kg$1  reason: invalid class name */
    class AnonymousClass1 extends d<PanoramaApi.a> {
        final /* synthetic */ Uri abh;
        final /* synthetic */ Bundle abi;

        /* access modifiers changed from: protected */
        public void a(Context context, kf kfVar) throws RemoteException {
            kg.a(context, kfVar, new a(this), this.abh, this.abi);
        }

        /* access modifiers changed from: protected */
        /* renamed from: aj */
        public PanoramaApi.a c(Status status) {
            return new kd(status, (Intent) null, 0);
        }
    }

    private static final class a extends ke.a {
        private final a.d<PanoramaApi.a> yO;

        public a(a.d<PanoramaApi.a> dVar) {
            this.yO = dVar;
        }

        public void a(int i, Bundle bundle, int i2, Intent intent) {
            this.yO.a(new kd(new Status(i, (String) null, bundle != null ? (PendingIntent) bundle.getParcelable("pendingIntent") : null), intent, i2));
        }
    }

    private static abstract class b extends d<PanoramaApi.PanoramaResult> {
        private b() {
        }

        /* synthetic */ b(AnonymousClass1 r1) {
            this();
        }

        /* access modifiers changed from: protected */
        /* renamed from: ak */
        public PanoramaApi.PanoramaResult c(Status status) {
            return new ki(status, (Intent) null);
        }
    }

    private static final class c extends ke.a {
        private final a.d<PanoramaApi.PanoramaResult> yO;

        public c(a.d<PanoramaApi.PanoramaResult> dVar) {
            this.yO = dVar;
        }

        public void a(int i, Bundle bundle, int i2, Intent intent) {
            this.yO.a(new ki(new Status(i, (String) null, bundle != null ? (PendingIntent) bundle.getParcelable("pendingIntent") : null), intent));
        }
    }

    private static abstract class d<R extends Result> extends a.b<R, kh> {
        protected d() {
            super(Panorama.yE);
        }

        /* access modifiers changed from: protected */
        public abstract void a(Context context, kf kfVar) throws RemoteException;

        /* access modifiers changed from: protected */
        public final void a(kh khVar) throws RemoteException {
            a(khVar.getContext(), (kf) khVar.fo());
        }
    }

    /* access modifiers changed from: private */
    public static void a(Context context, Uri uri) {
        context.revokeUriPermission(uri, 1);
    }

    /* access modifiers changed from: private */
    public static void a(final Context context, kf kfVar, final ke keVar, final Uri uri, Bundle bundle) throws RemoteException {
        context.grantUriPermission(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE, uri, 1);
        try {
            kfVar.a(new ke.a() {
                public void a(int i, Bundle bundle, int i2, Intent intent) throws RemoteException {
                    kg.a(context, uri);
                    keVar.a(i, bundle, i2, intent);
                }
            }, uri, bundle, true);
        } catch (RemoteException e) {
            a(context, uri);
            throw e;
        } catch (RuntimeException e2) {
            a(context, uri);
            throw e2;
        }
    }

    public PendingResult<PanoramaApi.PanoramaResult> loadPanoramaInfo(GoogleApiClient client, final Uri uri) {
        return client.a(new b() {
            /* access modifiers changed from: protected */
            public void a(Context context, kf kfVar) throws RemoteException {
                kfVar.a(new c(this), uri, (Bundle) null, false);
            }
        });
    }

    public PendingResult<PanoramaApi.PanoramaResult> loadPanoramaInfoAndGrantAccess(GoogleApiClient client, final Uri uri) {
        return client.a(new b() {
            /* access modifiers changed from: protected */
            public void a(Context context, kf kfVar) throws RemoteException {
                kg.a(context, kfVar, new c(this), uri, (Bundle) null);
            }
        });
    }
}
