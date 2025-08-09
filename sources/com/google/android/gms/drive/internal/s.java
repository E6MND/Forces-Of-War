package com.google.android.gms.drive.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.internal.p;

public class s extends v implements DriveFile {

    private abstract class a extends q<Status> {
        private a() {
        }

        /* renamed from: d */
        public Status c(Status status) {
            return status;
        }
    }

    private abstract class b extends q<Status> {
        private b() {
        }

        /* renamed from: d */
        public Status c(Status status) {
            return status;
        }
    }

    private static class c extends c {
        private final DriveFile.DownloadProgressListener Jb;
        private final a.d<DriveApi.ContentsResult> yO;

        public c(a.d<DriveApi.ContentsResult> dVar, DriveFile.DownloadProgressListener downloadProgressListener) {
            this.yO = dVar;
            this.Jb = downloadProgressListener;
        }

        public void a(OnContentsResponse onContentsResponse) throws RemoteException {
            this.yO.a(new p.a(Status.Ek, onContentsResponse.go()));
        }

        public void a(OnDownloadProgressResponse onDownloadProgressResponse) throws RemoteException {
            if (this.Jb != null) {
                this.Jb.onProgress(onDownloadProgressResponse.gp(), onDownloadProgressResponse.gq());
            }
        }

        public void o(Status status) throws RemoteException {
            this.yO.a(new p.a(status, (Contents) null));
        }
    }

    private abstract class d extends q<DriveApi.ContentsResult> {
        private d() {
        }

        /* renamed from: q */
        public DriveApi.ContentsResult c(Status status) {
            return new p.a(status, (Contents) null);
        }
    }

    public s(DriveId driveId) {
        super(driveId);
    }

    public PendingResult<Status> commitAndCloseContents(GoogleApiClient apiClient, final Contents contents) {
        if (contents != null) {
            return apiClient.b(new b() {
                /* access modifiers changed from: protected */
                public void a(r rVar) throws RemoteException {
                    contents.close();
                    rVar.gk().a(new CloseContentsRequest(contents, true), (ab) new aw(this));
                }
            });
        }
        throw new IllegalArgumentException("Contents must be provided.");
    }

    public PendingResult<Status> commitAndCloseContents(GoogleApiClient apiClient, final Contents contents, final MetadataChangeSet changeSet) {
        if (contents != null) {
            return apiClient.b(new a() {
                /* access modifiers changed from: protected */
                public void a(r rVar) throws RemoteException {
                    contents.close();
                    rVar.gk().a(new CloseContentsAndUpdateMetadataRequest(s.this.Hw, changeSet.gh(), contents, false, (String) null), (ab) new aw(this));
                }
            });
        }
        throw new IllegalArgumentException("Contents must be provided.");
    }

    public PendingResult<Status> discardContents(GoogleApiClient apiClient, Contents contents) {
        return Drive.DriveApi.discardContents(apiClient, contents);
    }

    public PendingResult<DriveApi.ContentsResult> openContents(GoogleApiClient apiClient, final int mode, final DriveFile.DownloadProgressListener listener) {
        if (mode == 268435456 || mode == 536870912 || mode == 805306368) {
            return apiClient.a(new d() {
                /* access modifiers changed from: protected */
                public void a(r rVar) throws RemoteException {
                    rVar.gk().a(new OpenContentsRequest(s.this.getDriveId(), mode), (ab) new c(this, listener));
                }
            });
        }
        throw new IllegalArgumentException("Invalid mode provided.");
    }
}
