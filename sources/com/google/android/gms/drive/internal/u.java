package com.google.android.gms.drive.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.query.Filters;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.drive.query.SearchableField;

public class u extends v implements DriveFolder {

    private static class a extends c {
        private final a.d<DriveFolder.DriveFileResult> yO;

        public a(a.d<DriveFolder.DriveFileResult> dVar) {
            this.yO = dVar;
        }

        public void a(OnDriveIdResponse onDriveIdResponse) throws RemoteException {
            this.yO.a(new d(Status.Ek, new s(onDriveIdResponse.getDriveId())));
        }

        public void o(Status status) throws RemoteException {
            this.yO.a(new d(status, (DriveFile) null));
        }
    }

    private static class b extends c {
        private final a.d<DriveFolder.DriveFolderResult> yO;

        public b(a.d<DriveFolder.DriveFolderResult> dVar) {
            this.yO = dVar;
        }

        public void a(OnDriveIdResponse onDriveIdResponse) throws RemoteException {
            this.yO.a(new e(Status.Ek, new u(onDriveIdResponse.getDriveId())));
        }

        public void o(Status status) throws RemoteException {
            this.yO.a(new e(status, (DriveFolder) null));
        }
    }

    private abstract class c extends q<DriveFolder.DriveFolderResult> {
        private c() {
        }

        /* renamed from: t */
        public DriveFolder.DriveFolderResult c(Status status) {
            return new e(status, (DriveFolder) null);
        }
    }

    private static class d implements DriveFolder.DriveFileResult {
        private final DriveFile Jg;
        private final Status yw;

        public d(Status status, DriveFile driveFile) {
            this.yw = status;
            this.Jg = driveFile;
        }

        public DriveFile getDriveFile() {
            return this.Jg;
        }

        public Status getStatus() {
            return this.yw;
        }
    }

    private static class e implements DriveFolder.DriveFolderResult {
        private final DriveFolder Jh;
        private final Status yw;

        public e(Status status, DriveFolder driveFolder) {
            this.yw = status;
            this.Jh = driveFolder;
        }

        public DriveFolder getDriveFolder() {
            return this.Jh;
        }

        public Status getStatus() {
            return this.yw;
        }
    }

    public u(DriveId driveId) {
        super(driveId);
    }

    private PendingResult<DriveFolder.DriveFileResult> a(GoogleApiClient googleApiClient, MetadataChangeSet metadataChangeSet, Contents contents, int i, boolean z, String str) {
        final Contents contents2 = contents;
        final MetadataChangeSet metadataChangeSet2 = metadataChangeSet;
        final int i2 = i;
        final boolean z2 = z;
        final String str2 = str;
        return googleApiClient.b(new q<DriveFolder.DriveFileResult>() {
            /* access modifiers changed from: protected */
            public void a(r rVar) throws RemoteException {
                if (contents2 != null) {
                    contents2.close();
                }
                rVar.gk().a(new CreateFileRequest(u.this.getDriveId(), metadataChangeSet2.gh(), contents2, i2, z2, str2), (ab) new a(this));
            }

            /* renamed from: s */
            public DriveFolder.DriveFileResult c(Status status) {
                return new d(status, (DriveFile) null);
            }
        });
    }

    private PendingResult<DriveFolder.DriveFileResult> a(GoogleApiClient googleApiClient, MetadataChangeSet metadataChangeSet, Contents contents, boolean z, String str) {
        if (metadataChangeSet == null) {
            throw new IllegalArgumentException("MetadataChangeSet must be provided.");
        } else if (contents == null) {
            throw new IllegalArgumentException("Contents must be provided.");
        } else if (!DriveFolder.MIME_TYPE.equals(metadataChangeSet.getMimeType())) {
            return a(googleApiClient, metadataChangeSet, contents, 0, z, str);
        } else {
            throw new IllegalArgumentException("May not create folders (mimetype: application/vnd.google-apps.folder) using this method. Use DriveFolder.createFolder() instead.");
        }
    }

    public PendingResult<DriveFolder.DriveFileResult> createFile(GoogleApiClient apiClient, MetadataChangeSet changeSet, Contents contents) {
        return a(apiClient, changeSet, contents, false, (String) null);
    }

    public PendingResult<DriveFolder.DriveFolderResult> createFolder(GoogleApiClient apiClient, final MetadataChangeSet changeSet) {
        if (changeSet == null) {
            throw new IllegalArgumentException("MetadataChangeSet must be provided.");
        } else if (changeSet.getMimeType() == null || changeSet.getMimeType().equals(DriveFolder.MIME_TYPE)) {
            return apiClient.b(new c() {
                /* access modifiers changed from: protected */
                public void a(r rVar) throws RemoteException {
                    rVar.gk().a(new CreateFolderRequest(u.this.getDriveId(), changeSet.gh()), (ab) new b(this));
                }
            });
        } else {
            throw new IllegalArgumentException("The mimetype must be of type application/vnd.google-apps.folder");
        }
    }

    public PendingResult<DriveApi.MetadataBufferResult> listChildren(GoogleApiClient apiClient) {
        return queryChildren(apiClient, (Query) null);
    }

    public PendingResult<DriveApi.MetadataBufferResult> queryChildren(GoogleApiClient apiClient, Query query) {
        Query.Builder addFilter = new Query.Builder().addFilter(Filters.in(SearchableField.PARENTS, getDriveId()));
        if (query != null) {
            if (query.getFilter() != null) {
                addFilter.addFilter(query.getFilter());
            }
            addFilter.setPageToken(query.getPageToken());
            addFilter.setSortOrder(query.getSortOrder());
        }
        return new p().query(apiClient, addFilter.build());
    }
}
