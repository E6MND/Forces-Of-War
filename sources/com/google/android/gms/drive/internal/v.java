package com.google.android.gms.drive.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.drive.Metadata;
import com.google.android.gms.drive.MetadataBuffer;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.DriveEvent;
import com.google.android.gms.drive.internal.p;

public class v implements DriveResource {
    protected final DriveId Hw;

    private abstract class a extends q<DriveResource.MetadataResult> {
        private a() {
        }

        /* renamed from: u */
        public DriveResource.MetadataResult c(Status status) {
            return new e(status, (Metadata) null);
        }
    }

    private static class b extends c {
        private final a.d<DriveApi.MetadataBufferResult> yO;

        public b(a.d<DriveApi.MetadataBufferResult> dVar) {
            this.yO = dVar;
        }

        public void a(OnListParentsResponse onListParentsResponse) throws RemoteException {
            this.yO.a(new p.e(Status.Ek, new MetadataBuffer(onListParentsResponse.gv(), (String) null), false));
        }

        public void o(Status status) throws RemoteException {
            this.yO.a(new p.e(status, (MetadataBuffer) null, false));
        }
    }

    private abstract class c extends q<DriveApi.MetadataBufferResult> {
        private c() {
        }

        /* renamed from: r */
        public DriveApi.MetadataBufferResult c(Status status) {
            return new p.e(status, (MetadataBuffer) null, false);
        }
    }

    private static class d extends c {
        private final a.d<DriveResource.MetadataResult> yO;

        public d(a.d<DriveResource.MetadataResult> dVar) {
            this.yO = dVar;
        }

        public void a(OnMetadataResponse onMetadataResponse) throws RemoteException {
            this.yO.a(new e(Status.Ek, new l(onMetadataResponse.gw())));
        }

        public void o(Status status) throws RemoteException {
            this.yO.a(new e(status, (Metadata) null));
        }
    }

    private static class e implements DriveResource.MetadataResult {
        private final Metadata Jj;
        private final Status yw;

        public e(Status status, Metadata metadata) {
            this.yw = status;
            this.Jj = metadata;
        }

        public Metadata getMetadata() {
            return this.Jj;
        }

        public Status getStatus() {
            return this.yw;
        }
    }

    private abstract class f extends q<DriveResource.MetadataResult> {
        private f() {
        }

        /* renamed from: u */
        public DriveResource.MetadataResult c(Status status) {
            return new e(status, (Metadata) null);
        }
    }

    protected v(DriveId driveId) {
        this.Hw = driveId;
    }

    public PendingResult<Status> addChangeListener(GoogleApiClient apiClient, DriveEvent.Listener<ChangeEvent> listener) {
        return ((r) apiClient.a(Drive.yE)).a(apiClient, this.Hw, 1, listener);
    }

    public DriveId getDriveId() {
        return this.Hw;
    }

    public PendingResult<DriveResource.MetadataResult> getMetadata(GoogleApiClient apiClient) {
        return apiClient.a(new a() {
            /* access modifiers changed from: protected */
            public void a(r rVar) throws RemoteException {
                rVar.gk().a(new GetMetadataRequest(v.this.Hw), (ab) new d(this));
            }
        });
    }

    public PendingResult<DriveApi.MetadataBufferResult> listParents(GoogleApiClient apiClient) {
        return apiClient.a(new c() {
            /* access modifiers changed from: protected */
            public void a(r rVar) throws RemoteException {
                rVar.gk().a(new ListParentsRequest(v.this.Hw), (ab) new b(this));
            }
        });
    }

    public PendingResult<Status> removeChangeListener(GoogleApiClient apiClient, DriveEvent.Listener<ChangeEvent> listener) {
        return ((r) apiClient.a(Drive.yE)).b(apiClient, this.Hw, 1, listener);
    }

    public PendingResult<DriveResource.MetadataResult> updateMetadata(GoogleApiClient apiClient, final MetadataChangeSet changeSet) {
        if (changeSet != null) {
            return apiClient.b(new f() {
                /* access modifiers changed from: protected */
                public void a(r rVar) throws RemoteException {
                    rVar.gk().a(new UpdateMetadataRequest(v.this.Hw, changeSet.gh()), (ab) new d(this));
                }
            });
        }
        throw new IllegalArgumentException("ChangeSet must be provided.");
    }
}
