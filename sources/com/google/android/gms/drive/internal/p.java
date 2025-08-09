package com.google.android.gms.drive.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.CreateFileActivityBuilder;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.MetadataBuffer;
import com.google.android.gms.drive.OpenFileActivityBuilder;
import com.google.android.gms.drive.query.Query;

public class p implements DriveApi {

    public static class a implements DriveApi.ContentsResult {
        private final Contents HD;
        private final Status yw;

        public a(Status status, Contents contents) {
            this.yw = status;
            this.HD = contents;
        }

        public Contents getContents() {
            return this.HD;
        }

        public Status getStatus() {
            return this.yw;
        }
    }

    private static class b extends c {
        private final a.d<DriveApi.DriveIdResult> yO;

        public b(a.d<DriveApi.DriveIdResult> dVar) {
            this.yO = dVar;
        }

        public void a(OnDriveIdResponse onDriveIdResponse) throws RemoteException {
            this.yO.a(new c(Status.Ek, onDriveIdResponse.getDriveId()));
        }

        public void a(OnMetadataResponse onMetadataResponse) throws RemoteException {
            this.yO.a(new c(Status.Ek, new l(onMetadataResponse.gw()).getDriveId()));
        }

        public void o(Status status) throws RemoteException {
            this.yO.a(new c(status, (DriveId) null));
        }
    }

    static class c implements DriveApi.DriveIdResult {
        private final DriveId Hw;
        private final Status yw;

        public c(Status status, DriveId driveId) {
            this.yw = status;
            this.Hw = driveId;
        }

        public DriveId getDriveId() {
            return this.Hw;
        }

        public Status getStatus() {
            return this.yw;
        }
    }

    abstract class d extends q<DriveApi.DriveIdResult> {
        d() {
        }

        /* renamed from: p */
        public DriveApi.DriveIdResult c(Status status) {
            return new c(status, (DriveId) null);
        }
    }

    static class e implements DriveApi.MetadataBufferResult {
        private final MetadataBuffer IL;
        private final boolean IM;
        private final Status yw;

        public e(Status status, MetadataBuffer metadataBuffer, boolean z) {
            this.yw = status;
            this.IL = metadataBuffer;
            this.IM = z;
        }

        public MetadataBuffer getMetadataBuffer() {
            return this.IL;
        }

        public Status getStatus() {
            return this.yw;
        }
    }

    private static class f extends c {
        private final a.d<DriveApi.ContentsResult> yO;

        public f(a.d<DriveApi.ContentsResult> dVar) {
            this.yO = dVar;
        }

        public void a(OnContentsResponse onContentsResponse) throws RemoteException {
            this.yO.a(new a(Status.Ek, onContentsResponse.go()));
        }

        public void o(Status status) throws RemoteException {
            this.yO.a(new a(status, (Contents) null));
        }
    }

    abstract class g extends q<DriveApi.ContentsResult> {
        g() {
        }

        /* renamed from: q */
        public DriveApi.ContentsResult c(Status status) {
            return new a(status, (Contents) null);
        }
    }

    static class h extends c {
        private final a.d<DriveApi.MetadataBufferResult> yO;

        public h(a.d<DriveApi.MetadataBufferResult> dVar) {
            this.yO = dVar;
        }

        public void a(OnListEntriesResponse onListEntriesResponse) throws RemoteException {
            this.yO.a(new e(Status.Ek, new MetadataBuffer(onListEntriesResponse.gt(), (String) null), onListEntriesResponse.gu()));
        }

        public void o(Status status) throws RemoteException {
            this.yO.a(new e(status, (MetadataBuffer) null, false));
        }
    }

    abstract class i extends q<DriveApi.MetadataBufferResult> {
        i() {
        }

        /* renamed from: r */
        public DriveApi.MetadataBufferResult c(Status status) {
            return new e(status, (MetadataBuffer) null, false);
        }
    }

    static abstract class j extends q<Status> {
        j() {
        }

        /* renamed from: d */
        public Status c(Status status) {
            return status;
        }
    }

    static class k extends j {
        k(GoogleApiClient googleApiClient, Status status) {
            a(new a.c(((r) googleApiClient.a(Drive.yE)).getLooper()));
            a(status);
        }

        /* access modifiers changed from: protected */
        public void a(r rVar) {
        }
    }

    abstract class l extends q<Status> {
        l() {
        }

        /* renamed from: d */
        public Status c(Status status) {
            return status;
        }
    }

    public PendingResult<Status> discardContents(GoogleApiClient apiClient, final Contents contents) {
        return apiClient.b(new j() {
            /* access modifiers changed from: protected */
            public void a(r rVar) throws RemoteException {
                rVar.gk().a(new CloseContentsRequest(contents, false), (ab) new aw(this));
            }
        });
    }

    public PendingResult<DriveApi.DriveIdResult> fetchDriveId(GoogleApiClient apiClient, final String resourceId) {
        return apiClient.a(new d() {
            /* access modifiers changed from: protected */
            public void a(r rVar) throws RemoteException {
                rVar.gk().a(new GetMetadataRequest(DriveId.aL(resourceId)), (ab) new b(this));
            }
        });
    }

    public DriveFolder getAppFolder(GoogleApiClient apiClient) {
        if (!apiClient.isConnected()) {
            throw new IllegalStateException("Client must be connected");
        }
        DriveId gm = ((r) apiClient.a(Drive.yE)).gm();
        if (gm != null) {
            return new u(gm);
        }
        return null;
    }

    public DriveFile getFile(GoogleApiClient apiClient, DriveId id) {
        if (id == null) {
            throw new IllegalArgumentException("Id must be provided.");
        } else if (apiClient.isConnected()) {
            return new s(id);
        } else {
            throw new IllegalStateException("Client must be connected");
        }
    }

    public DriveFolder getFolder(GoogleApiClient apiClient, DriveId id) {
        if (id == null) {
            throw new IllegalArgumentException("Id must be provided.");
        } else if (apiClient.isConnected()) {
            return new u(id);
        } else {
            throw new IllegalStateException("Client must be connected");
        }
    }

    public DriveFolder getRootFolder(GoogleApiClient apiClient) {
        if (apiClient.isConnected()) {
            return new u(((r) apiClient.a(Drive.yE)).gl());
        }
        throw new IllegalStateException("Client must be connected");
    }

    public PendingResult<DriveApi.ContentsResult> newContents(GoogleApiClient apiClient) {
        return apiClient.a(new g() {
            /* access modifiers changed from: protected */
            public void a(r rVar) throws RemoteException {
                rVar.gk().a(new CreateContentsRequest(), (ab) new f(this));
            }
        });
    }

    public CreateFileActivityBuilder newCreateFileActivityBuilder() {
        return new CreateFileActivityBuilder();
    }

    public OpenFileActivityBuilder newOpenFileActivityBuilder() {
        return new OpenFileActivityBuilder();
    }

    public PendingResult<DriveApi.MetadataBufferResult> query(GoogleApiClient apiClient, final Query query) {
        if (query != null) {
            return apiClient.a(new i() {
                /* access modifiers changed from: protected */
                public void a(r rVar) throws RemoteException {
                    rVar.gk().a(new QueryRequest(query), (ab) new h(this));
                }
            });
        }
        throw new IllegalArgumentException("Query must be provided.");
    }

    public PendingResult<Status> requestSync(GoogleApiClient client) {
        return client.b(new l() {
            /* access modifiers changed from: protected */
            public void a(r rVar) throws RemoteException {
                rVar.gk().a((ab) new aw(this));
            }
        });
    }
}
