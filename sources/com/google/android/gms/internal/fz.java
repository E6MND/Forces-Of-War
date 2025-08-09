package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.google.android.gms.appindexing.AppIndexApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import com.google.android.gms.internal.fu;
import java.util.List;

public final class fz implements AppIndexApi, fu {

    /* renamed from: com.google.android.gms.internal.fz$1  reason: invalid class name */
    class AnonymousClass1 extends c<fu.a> {
        /* access modifiers changed from: protected */
        public void a(fv fvVar) throws RemoteException {
            fvVar.a(new fx<fu.a>(this) {
                public void a(Status status, ParcelFileDescriptor parcelFileDescriptor) {
                    this.yr.a(new b(status, parcelFileDescriptor));
                }
            });
        }

        /* renamed from: b */
        public fu.a c(Status status) {
            return new b(status, (ParcelFileDescriptor) null);
        }
    }

    private static abstract class a<T> implements Result {
        private final Status yw;
        protected final T yx;

        public a(Status status, T t) {
            this.yw = status;
            this.yx = t;
        }

        public Status getStatus() {
            return this.yw;
        }
    }

    static class b extends a<ParcelFileDescriptor> implements fu.a {
        public b(Status status, ParcelFileDescriptor parcelFileDescriptor) {
            super(status, parcelFileDescriptor);
        }
    }

    private static abstract class c<T extends Result> extends a.b<T, fy> {
        public c() {
            super(fg.xF);
        }

        /* access modifiers changed from: protected */
        public abstract void a(fv fvVar) throws RemoteException;

        /* access modifiers changed from: protected */
        public final void a(fy fyVar) throws RemoteException {
            a(fyVar.dM());
        }
    }

    private static abstract class d<T extends Result> extends c<Status> {
        private d() {
        }

        /* synthetic */ d(AnonymousClass1 r1) {
            this();
        }

        /* access modifiers changed from: protected */
        /* renamed from: d */
        public Status c(Status status) {
            return status;
        }
    }

    private static final class e extends fx<Status> {
        public e(a.d<Status> dVar) {
            super(dVar);
        }

        public void a(Status status) {
            this.yr.a(status);
        }
    }

    static Uri a(String str, Uri uri) {
        if (!"android-app".equals(uri.getScheme())) {
            throw new IllegalArgumentException("Uri scheme must be android-app: " + uri);
        } else if (!str.equals(uri.getHost())) {
            throw new IllegalArgumentException("Uri host must match package name: " + uri);
        } else {
            List<String> pathSegments = uri.getPathSegments();
            if (pathSegments.isEmpty() || pathSegments.get(0).isEmpty()) {
                throw new IllegalArgumentException("Uri path must exist: " + uri);
            }
            Uri.Builder builder = new Uri.Builder();
            builder.scheme(pathSegments.get(0));
            if (pathSegments.size() > 1) {
                builder.authority(pathSegments.get(1));
                int i = 2;
                while (true) {
                    int i2 = i;
                    if (i2 >= pathSegments.size()) {
                        break;
                    }
                    builder.appendPath(pathSegments.get(i2));
                    i = i2 + 1;
                }
            }
            builder.encodedQuery(uri.getEncodedQuery());
            builder.encodedFragment(uri.getEncodedFragment());
            return builder.build();
        }
    }

    public PendingResult<Status> a(GoogleApiClient googleApiClient, final fs... fsVarArr) {
        final String packageName = ((fy) googleApiClient.a(fg.xF)).getContext().getPackageName();
        return googleApiClient.a(new d<Status>() {
            /* access modifiers changed from: protected */
            public void a(fv fvVar) throws RemoteException {
                fvVar.a(new e(this), packageName, fsVarArr);
            }
        });
    }

    public PendingResult<Status> view(GoogleApiClient apiClient, Activity activity, Intent viewIntent, String title, Uri webUrl, List<AppIndexApi.AppIndexingLink> outLinks) {
        return a(apiClient, new fs(((fy) apiClient.a(fg.xF)).getContext().getPackageName(), viewIntent, title, webUrl, (String) null, outLinks));
    }

    public PendingResult<Status> view(GoogleApiClient apiClient, Activity activity, Uri appIndexingUrl, String title, Uri webUrl, List<AppIndexApi.AppIndexingLink> outLinks) {
        return view(apiClient, activity, new Intent("android.intent.action.VIEW", a(((fy) apiClient.a(fg.xF)).getContext().getPackageName(), appIndexingUrl)), title, webUrl, outLinks);
    }

    public PendingResult<Status> viewEnd(GoogleApiClient apiClient, Activity activity, Intent viewIntent) {
        return a(apiClient, new fs(fs.a(((fy) apiClient.a(fg.xF)).getContext().getPackageName(), viewIntent), System.currentTimeMillis(), 3));
    }

    public PendingResult<Status> viewEnd(GoogleApiClient apiClient, Activity activity, Uri appIndexingUrl) {
        return viewEnd(apiClient, activity, new Intent("android.intent.action.VIEW", a(((fy) apiClient.a(fg.xF)).getContext().getPackageName(), appIndexingUrl)));
    }
}
