package com.google.android.gms.drive.internal;

import android.content.IntentSender;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface aa extends IInterface {

    public static abstract class a extends Binder implements aa {

        /* renamed from: com.google.android.gms.drive.internal.aa$a$a  reason: collision with other inner class name */
        private static class C0015a implements aa {
            private IBinder ko;

            C0015a(IBinder iBinder) {
                this.ko = iBinder;
            }

            public IntentSender a(CreateFileIntentSenderRequest createFileIntentSenderRequest) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (createFileIntentSenderRequest != null) {
                        obtain.writeInt(1);
                        createFileIntentSenderRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ko.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (IntentSender) IntentSender.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IntentSender a(OpenFileIntentSenderRequest openFileIntentSenderRequest) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (openFileIntentSenderRequest != null) {
                        obtain.writeInt(1);
                        openFileIntentSenderRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ko.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (IntentSender) IntentSender.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(AddEventListenerRequest addEventListenerRequest, ac acVar, String str, ab abVar) throws RemoteException {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (addEventListenerRequest != null) {
                        obtain.writeInt(1);
                        addEventListenerRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(acVar != null ? acVar.asBinder() : null);
                    obtain.writeString(str);
                    if (abVar != null) {
                        iBinder = abVar.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.ko.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(AuthorizeAccessRequest authorizeAccessRequest, ab abVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (authorizeAccessRequest != null) {
                        obtain.writeInt(1);
                        authorizeAccessRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    this.ko.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(CheckResourceIdsExistRequest checkResourceIdsExistRequest, ab abVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (checkResourceIdsExistRequest != null) {
                        obtain.writeInt(1);
                        checkResourceIdsExistRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    this.ko.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(CloseContentsAndUpdateMetadataRequest closeContentsAndUpdateMetadataRequest, ab abVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (closeContentsAndUpdateMetadataRequest != null) {
                        obtain.writeInt(1);
                        closeContentsAndUpdateMetadataRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    this.ko.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(CloseContentsRequest closeContentsRequest, ab abVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (closeContentsRequest != null) {
                        obtain.writeInt(1);
                        closeContentsRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    this.ko.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(CreateContentsRequest createContentsRequest, ab abVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (createContentsRequest != null) {
                        obtain.writeInt(1);
                        createContentsRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    this.ko.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(CreateFileRequest createFileRequest, ab abVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (createFileRequest != null) {
                        obtain.writeInt(1);
                        createFileRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    this.ko.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(CreateFolderRequest createFolderRequest, ab abVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (createFolderRequest != null) {
                        obtain.writeInt(1);
                        createFolderRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    this.ko.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(DeleteCustomPropertyRequest deleteCustomPropertyRequest, ab abVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (deleteCustomPropertyRequest != null) {
                        obtain.writeInt(1);
                        deleteCustomPropertyRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    this.ko.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(DeleteResourceRequest deleteResourceRequest, ab abVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (deleteResourceRequest != null) {
                        obtain.writeInt(1);
                        deleteResourceRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    this.ko.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(DisconnectRequest disconnectRequest) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (disconnectRequest != null) {
                        obtain.writeInt(1);
                        disconnectRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ko.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(GetDriveIdFromUniqueIdentifierRequest getDriveIdFromUniqueIdentifierRequest, ab abVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (getDriveIdFromUniqueIdentifierRequest != null) {
                        obtain.writeInt(1);
                        getDriveIdFromUniqueIdentifierRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    this.ko.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(GetMetadataRequest getMetadataRequest, ab abVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (getMetadataRequest != null) {
                        obtain.writeInt(1);
                        getMetadataRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    this.ko.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(ListParentsRequest listParentsRequest, ab abVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (listParentsRequest != null) {
                        obtain.writeInt(1);
                        listParentsRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    this.ko.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(LoadRealtimeRequest loadRealtimeRequest, ab abVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (loadRealtimeRequest != null) {
                        obtain.writeInt(1);
                        loadRealtimeRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    this.ko.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(OpenContentsRequest openContentsRequest, ab abVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (openContentsRequest != null) {
                        obtain.writeInt(1);
                        openContentsRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    this.ko.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(QueryRequest queryRequest, ab abVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (queryRequest != null) {
                        obtain.writeInt(1);
                        queryRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    this.ko.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(RemoveEventListenerRequest removeEventListenerRequest, ac acVar, String str, ab abVar) throws RemoteException {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (removeEventListenerRequest != null) {
                        obtain.writeInt(1);
                        removeEventListenerRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(acVar != null ? acVar.asBinder() : null);
                    obtain.writeString(str);
                    if (abVar != null) {
                        iBinder = abVar.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.ko.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(SetResourceParentsRequest setResourceParentsRequest, ab abVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (setResourceParentsRequest != null) {
                        obtain.writeInt(1);
                        setResourceParentsRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    this.ko.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(TrashResourceRequest trashResourceRequest, ab abVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (trashResourceRequest != null) {
                        obtain.writeInt(1);
                        trashResourceRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    this.ko.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(UpdateMetadataRequest updateMetadataRequest, ab abVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (updateMetadataRequest != null) {
                        obtain.writeInt(1);
                        updateMetadataRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    this.ko.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(ab abVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    this.ko.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.ko;
            }

            public void b(QueryRequest queryRequest, ab abVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    if (queryRequest != null) {
                        obtain.writeInt(1);
                        queryRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    this.ko.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(ab abVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    this.ko.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void c(ab abVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    this.ko.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void d(ab abVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    this.ko.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void e(ab abVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    this.ko.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static aa P(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.drive.internal.IDriveService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof aa)) ? new C0015a(iBinder) : (aa) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: com.google.android.gms.drive.internal.CheckResourceIdsExistRequest} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: com.google.android.gms.drive.internal.GetDriveIdFromUniqueIdentifierRequest} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: com.google.android.gms.drive.internal.SetResourceParentsRequest} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v16, resolved type: com.google.android.gms.drive.internal.LoadRealtimeRequest} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v21, resolved type: com.google.android.gms.drive.internal.DeleteCustomPropertyRequest} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v26, resolved type: com.google.android.gms.drive.internal.DeleteResourceRequest} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v47, resolved type: com.google.android.gms.drive.internal.QueryRequest} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v52, resolved type: com.google.android.gms.drive.internal.CloseContentsAndUpdateMetadataRequest} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v57, resolved type: com.google.android.gms.drive.internal.TrashResourceRequest} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v62, resolved type: com.google.android.gms.drive.internal.DisconnectRequest} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v67, resolved type: com.google.android.gms.drive.internal.RemoveEventListenerRequest} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v72, resolved type: com.google.android.gms.drive.internal.AddEventListenerRequest} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v77, resolved type: com.google.android.gms.drive.internal.ListParentsRequest} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v82, resolved type: com.google.android.gms.drive.internal.AuthorizeAccessRequest} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v87, resolved type: com.google.android.gms.drive.internal.CreateFileIntentSenderRequest} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v93, resolved type: com.google.android.gms.drive.internal.OpenFileIntentSenderRequest} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v103, resolved type: com.google.android.gms.drive.internal.CloseContentsRequest} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v108, resolved type: com.google.android.gms.drive.internal.OpenContentsRequest} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v113, resolved type: com.google.android.gms.drive.internal.CreateFolderRequest} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v118, resolved type: com.google.android.gms.drive.internal.CreateFileRequest} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v123, resolved type: com.google.android.gms.drive.internal.CreateContentsRequest} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v128, resolved type: com.google.android.gms.drive.internal.UpdateMetadataRequest} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v133, resolved type: com.google.android.gms.drive.internal.QueryRequest} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v138, resolved type: com.google.android.gms.drive.internal.GetMetadataRequest} */
        /* JADX WARNING: type inference failed for: r0v0 */
        /* JADX WARNING: type inference failed for: r0v146 */
        /* JADX WARNING: type inference failed for: r0v147 */
        /* JADX WARNING: type inference failed for: r0v148 */
        /* JADX WARNING: type inference failed for: r0v149 */
        /* JADX WARNING: type inference failed for: r0v150 */
        /* JADX WARNING: type inference failed for: r0v151 */
        /* JADX WARNING: type inference failed for: r0v152 */
        /* JADX WARNING: type inference failed for: r0v153 */
        /* JADX WARNING: type inference failed for: r0v154 */
        /* JADX WARNING: type inference failed for: r0v155 */
        /* JADX WARNING: type inference failed for: r0v156 */
        /* JADX WARNING: type inference failed for: r0v157 */
        /* JADX WARNING: type inference failed for: r0v158 */
        /* JADX WARNING: type inference failed for: r0v159 */
        /* JADX WARNING: type inference failed for: r0v160 */
        /* JADX WARNING: type inference failed for: r0v161 */
        /* JADX WARNING: type inference failed for: r0v162 */
        /* JADX WARNING: type inference failed for: r0v163 */
        /* JADX WARNING: type inference failed for: r0v164 */
        /* JADX WARNING: type inference failed for: r0v165 */
        /* JADX WARNING: type inference failed for: r0v166 */
        /* JADX WARNING: type inference failed for: r0v167 */
        /* JADX WARNING: type inference failed for: r0v168 */
        /* JADX WARNING: type inference failed for: r0v169 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r6, android.os.Parcel r7, android.os.Parcel r8, int r9) throws android.os.RemoteException {
            /*
                r5 = this;
                r3 = 0
                r0 = 0
                r1 = 1
                switch(r6) {
                    case 1: goto L_0x0012;
                    case 2: goto L_0x0035;
                    case 3: goto L_0x0058;
                    case 4: goto L_0x007b;
                    case 5: goto L_0x009f;
                    case 6: goto L_0x00c3;
                    case 7: goto L_0x00e7;
                    case 8: goto L_0x010b;
                    case 9: goto L_0x012f;
                    case 10: goto L_0x0145;
                    case 11: goto L_0x016e;
                    case 12: goto L_0x0197;
                    case 13: goto L_0x01bb;
                    case 14: goto L_0x01df;
                    case 15: goto L_0x020f;
                    case 16: goto L_0x023f;
                    case 17: goto L_0x025b;
                    case 18: goto L_0x027f;
                    case 19: goto L_0x02a3;
                    case 20: goto L_0x02c7;
                    case 21: goto L_0x02dd;
                    case 22: goto L_0x02f3;
                    case 23: goto L_0x0309;
                    case 24: goto L_0x031f;
                    case 26: goto L_0x0343;
                    case 27: goto L_0x0367;
                    case 28: goto L_0x038b;
                    case 29: goto L_0x03af;
                    case 30: goto L_0x03d3;
                    case 1598968902: goto L_0x000b;
                    default: goto L_0x0006;
                }
            L_0x0006:
                boolean r0 = super.onTransact(r6, r7, r8, r9)
            L_0x000a:
                return r0
            L_0x000b:
                java.lang.String r0 = "com.google.android.gms.drive.internal.IDriveService"
                r8.writeString(r0)
                r0 = r1
                goto L_0x000a
            L_0x0012:
                java.lang.String r2 = "com.google.android.gms.drive.internal.IDriveService"
                r7.enforceInterface(r2)
                int r2 = r7.readInt()
                if (r2 == 0) goto L_0x0025
                android.os.Parcelable$Creator<com.google.android.gms.drive.internal.GetMetadataRequest> r0 = com.google.android.gms.drive.internal.GetMetadataRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                com.google.android.gms.drive.internal.GetMetadataRequest r0 = (com.google.android.gms.drive.internal.GetMetadataRequest) r0
            L_0x0025:
                android.os.IBinder r2 = r7.readStrongBinder()
                com.google.android.gms.drive.internal.ab r2 = com.google.android.gms.drive.internal.ab.a.Q(r2)
                r5.a((com.google.android.gms.drive.internal.GetMetadataRequest) r0, (com.google.android.gms.drive.internal.ab) r2)
                r8.writeNoException()
                r0 = r1
                goto L_0x000a
            L_0x0035:
                java.lang.String r2 = "com.google.android.gms.drive.internal.IDriveService"
                r7.enforceInterface(r2)
                int r2 = r7.readInt()
                if (r2 == 0) goto L_0x0048
                android.os.Parcelable$Creator<com.google.android.gms.drive.internal.QueryRequest> r0 = com.google.android.gms.drive.internal.QueryRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                com.google.android.gms.drive.internal.QueryRequest r0 = (com.google.android.gms.drive.internal.QueryRequest) r0
            L_0x0048:
                android.os.IBinder r2 = r7.readStrongBinder()
                com.google.android.gms.drive.internal.ab r2 = com.google.android.gms.drive.internal.ab.a.Q(r2)
                r5.a((com.google.android.gms.drive.internal.QueryRequest) r0, (com.google.android.gms.drive.internal.ab) r2)
                r8.writeNoException()
                r0 = r1
                goto L_0x000a
            L_0x0058:
                java.lang.String r2 = "com.google.android.gms.drive.internal.IDriveService"
                r7.enforceInterface(r2)
                int r2 = r7.readInt()
                if (r2 == 0) goto L_0x006b
                android.os.Parcelable$Creator<com.google.android.gms.drive.internal.UpdateMetadataRequest> r0 = com.google.android.gms.drive.internal.UpdateMetadataRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                com.google.android.gms.drive.internal.UpdateMetadataRequest r0 = (com.google.android.gms.drive.internal.UpdateMetadataRequest) r0
            L_0x006b:
                android.os.IBinder r2 = r7.readStrongBinder()
                com.google.android.gms.drive.internal.ab r2 = com.google.android.gms.drive.internal.ab.a.Q(r2)
                r5.a((com.google.android.gms.drive.internal.UpdateMetadataRequest) r0, (com.google.android.gms.drive.internal.ab) r2)
                r8.writeNoException()
                r0 = r1
                goto L_0x000a
            L_0x007b:
                java.lang.String r2 = "com.google.android.gms.drive.internal.IDriveService"
                r7.enforceInterface(r2)
                int r2 = r7.readInt()
                if (r2 == 0) goto L_0x008e
                android.os.Parcelable$Creator<com.google.android.gms.drive.internal.CreateContentsRequest> r0 = com.google.android.gms.drive.internal.CreateContentsRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                com.google.android.gms.drive.internal.CreateContentsRequest r0 = (com.google.android.gms.drive.internal.CreateContentsRequest) r0
            L_0x008e:
                android.os.IBinder r2 = r7.readStrongBinder()
                com.google.android.gms.drive.internal.ab r2 = com.google.android.gms.drive.internal.ab.a.Q(r2)
                r5.a((com.google.android.gms.drive.internal.CreateContentsRequest) r0, (com.google.android.gms.drive.internal.ab) r2)
                r8.writeNoException()
                r0 = r1
                goto L_0x000a
            L_0x009f:
                java.lang.String r2 = "com.google.android.gms.drive.internal.IDriveService"
                r7.enforceInterface(r2)
                int r2 = r7.readInt()
                if (r2 == 0) goto L_0x00b2
                android.os.Parcelable$Creator<com.google.android.gms.drive.internal.CreateFileRequest> r0 = com.google.android.gms.drive.internal.CreateFileRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                com.google.android.gms.drive.internal.CreateFileRequest r0 = (com.google.android.gms.drive.internal.CreateFileRequest) r0
            L_0x00b2:
                android.os.IBinder r2 = r7.readStrongBinder()
                com.google.android.gms.drive.internal.ab r2 = com.google.android.gms.drive.internal.ab.a.Q(r2)
                r5.a((com.google.android.gms.drive.internal.CreateFileRequest) r0, (com.google.android.gms.drive.internal.ab) r2)
                r8.writeNoException()
                r0 = r1
                goto L_0x000a
            L_0x00c3:
                java.lang.String r2 = "com.google.android.gms.drive.internal.IDriveService"
                r7.enforceInterface(r2)
                int r2 = r7.readInt()
                if (r2 == 0) goto L_0x00d6
                android.os.Parcelable$Creator<com.google.android.gms.drive.internal.CreateFolderRequest> r0 = com.google.android.gms.drive.internal.CreateFolderRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                com.google.android.gms.drive.internal.CreateFolderRequest r0 = (com.google.android.gms.drive.internal.CreateFolderRequest) r0
            L_0x00d6:
                android.os.IBinder r2 = r7.readStrongBinder()
                com.google.android.gms.drive.internal.ab r2 = com.google.android.gms.drive.internal.ab.a.Q(r2)
                r5.a((com.google.android.gms.drive.internal.CreateFolderRequest) r0, (com.google.android.gms.drive.internal.ab) r2)
                r8.writeNoException()
                r0 = r1
                goto L_0x000a
            L_0x00e7:
                java.lang.String r2 = "com.google.android.gms.drive.internal.IDriveService"
                r7.enforceInterface(r2)
                int r2 = r7.readInt()
                if (r2 == 0) goto L_0x00fa
                android.os.Parcelable$Creator<com.google.android.gms.drive.internal.OpenContentsRequest> r0 = com.google.android.gms.drive.internal.OpenContentsRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                com.google.android.gms.drive.internal.OpenContentsRequest r0 = (com.google.android.gms.drive.internal.OpenContentsRequest) r0
            L_0x00fa:
                android.os.IBinder r2 = r7.readStrongBinder()
                com.google.android.gms.drive.internal.ab r2 = com.google.android.gms.drive.internal.ab.a.Q(r2)
                r5.a((com.google.android.gms.drive.internal.OpenContentsRequest) r0, (com.google.android.gms.drive.internal.ab) r2)
                r8.writeNoException()
                r0 = r1
                goto L_0x000a
            L_0x010b:
                java.lang.String r2 = "com.google.android.gms.drive.internal.IDriveService"
                r7.enforceInterface(r2)
                int r2 = r7.readInt()
                if (r2 == 0) goto L_0x011e
                android.os.Parcelable$Creator<com.google.android.gms.drive.internal.CloseContentsRequest> r0 = com.google.android.gms.drive.internal.CloseContentsRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                com.google.android.gms.drive.internal.CloseContentsRequest r0 = (com.google.android.gms.drive.internal.CloseContentsRequest) r0
            L_0x011e:
                android.os.IBinder r2 = r7.readStrongBinder()
                com.google.android.gms.drive.internal.ab r2 = com.google.android.gms.drive.internal.ab.a.Q(r2)
                r5.a((com.google.android.gms.drive.internal.CloseContentsRequest) r0, (com.google.android.gms.drive.internal.ab) r2)
                r8.writeNoException()
                r0 = r1
                goto L_0x000a
            L_0x012f:
                java.lang.String r0 = "com.google.android.gms.drive.internal.IDriveService"
                r7.enforceInterface(r0)
                android.os.IBinder r0 = r7.readStrongBinder()
                com.google.android.gms.drive.internal.ab r0 = com.google.android.gms.drive.internal.ab.a.Q(r0)
                r5.a((com.google.android.gms.drive.internal.ab) r0)
                r8.writeNoException()
                r0 = r1
                goto L_0x000a
            L_0x0145:
                java.lang.String r2 = "com.google.android.gms.drive.internal.IDriveService"
                r7.enforceInterface(r2)
                int r2 = r7.readInt()
                if (r2 == 0) goto L_0x0158
                android.os.Parcelable$Creator<com.google.android.gms.drive.internal.OpenFileIntentSenderRequest> r0 = com.google.android.gms.drive.internal.OpenFileIntentSenderRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                com.google.android.gms.drive.internal.OpenFileIntentSenderRequest r0 = (com.google.android.gms.drive.internal.OpenFileIntentSenderRequest) r0
            L_0x0158:
                android.content.IntentSender r0 = r5.a((com.google.android.gms.drive.internal.OpenFileIntentSenderRequest) r0)
                r8.writeNoException()
                if (r0 == 0) goto L_0x016a
                r8.writeInt(r1)
                r0.writeToParcel(r8, r1)
            L_0x0167:
                r0 = r1
                goto L_0x000a
            L_0x016a:
                r8.writeInt(r3)
                goto L_0x0167
            L_0x016e:
                java.lang.String r2 = "com.google.android.gms.drive.internal.IDriveService"
                r7.enforceInterface(r2)
                int r2 = r7.readInt()
                if (r2 == 0) goto L_0x0181
                android.os.Parcelable$Creator<com.google.android.gms.drive.internal.CreateFileIntentSenderRequest> r0 = com.google.android.gms.drive.internal.CreateFileIntentSenderRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                com.google.android.gms.drive.internal.CreateFileIntentSenderRequest r0 = (com.google.android.gms.drive.internal.CreateFileIntentSenderRequest) r0
            L_0x0181:
                android.content.IntentSender r0 = r5.a((com.google.android.gms.drive.internal.CreateFileIntentSenderRequest) r0)
                r8.writeNoException()
                if (r0 == 0) goto L_0x0193
                r8.writeInt(r1)
                r0.writeToParcel(r8, r1)
            L_0x0190:
                r0 = r1
                goto L_0x000a
            L_0x0193:
                r8.writeInt(r3)
                goto L_0x0190
            L_0x0197:
                java.lang.String r2 = "com.google.android.gms.drive.internal.IDriveService"
                r7.enforceInterface(r2)
                int r2 = r7.readInt()
                if (r2 == 0) goto L_0x01aa
                android.os.Parcelable$Creator<com.google.android.gms.drive.internal.AuthorizeAccessRequest> r0 = com.google.android.gms.drive.internal.AuthorizeAccessRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                com.google.android.gms.drive.internal.AuthorizeAccessRequest r0 = (com.google.android.gms.drive.internal.AuthorizeAccessRequest) r0
            L_0x01aa:
                android.os.IBinder r2 = r7.readStrongBinder()
                com.google.android.gms.drive.internal.ab r2 = com.google.android.gms.drive.internal.ab.a.Q(r2)
                r5.a((com.google.android.gms.drive.internal.AuthorizeAccessRequest) r0, (com.google.android.gms.drive.internal.ab) r2)
                r8.writeNoException()
                r0 = r1
                goto L_0x000a
            L_0x01bb:
                java.lang.String r2 = "com.google.android.gms.drive.internal.IDriveService"
                r7.enforceInterface(r2)
                int r2 = r7.readInt()
                if (r2 == 0) goto L_0x01ce
                android.os.Parcelable$Creator<com.google.android.gms.drive.internal.ListParentsRequest> r0 = com.google.android.gms.drive.internal.ListParentsRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                com.google.android.gms.drive.internal.ListParentsRequest r0 = (com.google.android.gms.drive.internal.ListParentsRequest) r0
            L_0x01ce:
                android.os.IBinder r2 = r7.readStrongBinder()
                com.google.android.gms.drive.internal.ab r2 = com.google.android.gms.drive.internal.ab.a.Q(r2)
                r5.a((com.google.android.gms.drive.internal.ListParentsRequest) r0, (com.google.android.gms.drive.internal.ab) r2)
                r8.writeNoException()
                r0 = r1
                goto L_0x000a
            L_0x01df:
                java.lang.String r2 = "com.google.android.gms.drive.internal.IDriveService"
                r7.enforceInterface(r2)
                int r2 = r7.readInt()
                if (r2 == 0) goto L_0x01f2
                android.os.Parcelable$Creator<com.google.android.gms.drive.internal.AddEventListenerRequest> r0 = com.google.android.gms.drive.internal.AddEventListenerRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                com.google.android.gms.drive.internal.AddEventListenerRequest r0 = (com.google.android.gms.drive.internal.AddEventListenerRequest) r0
            L_0x01f2:
                android.os.IBinder r2 = r7.readStrongBinder()
                com.google.android.gms.drive.internal.ac r2 = com.google.android.gms.drive.internal.ac.a.R(r2)
                java.lang.String r3 = r7.readString()
                android.os.IBinder r4 = r7.readStrongBinder()
                com.google.android.gms.drive.internal.ab r4 = com.google.android.gms.drive.internal.ab.a.Q(r4)
                r5.a((com.google.android.gms.drive.internal.AddEventListenerRequest) r0, (com.google.android.gms.drive.internal.ac) r2, (java.lang.String) r3, (com.google.android.gms.drive.internal.ab) r4)
                r8.writeNoException()
                r0 = r1
                goto L_0x000a
            L_0x020f:
                java.lang.String r2 = "com.google.android.gms.drive.internal.IDriveService"
                r7.enforceInterface(r2)
                int r2 = r7.readInt()
                if (r2 == 0) goto L_0x0222
                android.os.Parcelable$Creator<com.google.android.gms.drive.internal.RemoveEventListenerRequest> r0 = com.google.android.gms.drive.internal.RemoveEventListenerRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                com.google.android.gms.drive.internal.RemoveEventListenerRequest r0 = (com.google.android.gms.drive.internal.RemoveEventListenerRequest) r0
            L_0x0222:
                android.os.IBinder r2 = r7.readStrongBinder()
                com.google.android.gms.drive.internal.ac r2 = com.google.android.gms.drive.internal.ac.a.R(r2)
                java.lang.String r3 = r7.readString()
                android.os.IBinder r4 = r7.readStrongBinder()
                com.google.android.gms.drive.internal.ab r4 = com.google.android.gms.drive.internal.ab.a.Q(r4)
                r5.a((com.google.android.gms.drive.internal.RemoveEventListenerRequest) r0, (com.google.android.gms.drive.internal.ac) r2, (java.lang.String) r3, (com.google.android.gms.drive.internal.ab) r4)
                r8.writeNoException()
                r0 = r1
                goto L_0x000a
            L_0x023f:
                java.lang.String r2 = "com.google.android.gms.drive.internal.IDriveService"
                r7.enforceInterface(r2)
                int r2 = r7.readInt()
                if (r2 == 0) goto L_0x0252
                android.os.Parcelable$Creator<com.google.android.gms.drive.internal.DisconnectRequest> r0 = com.google.android.gms.drive.internal.DisconnectRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                com.google.android.gms.drive.internal.DisconnectRequest r0 = (com.google.android.gms.drive.internal.DisconnectRequest) r0
            L_0x0252:
                r5.a((com.google.android.gms.drive.internal.DisconnectRequest) r0)
                r8.writeNoException()
                r0 = r1
                goto L_0x000a
            L_0x025b:
                java.lang.String r2 = "com.google.android.gms.drive.internal.IDriveService"
                r7.enforceInterface(r2)
                int r2 = r7.readInt()
                if (r2 == 0) goto L_0x026e
                android.os.Parcelable$Creator<com.google.android.gms.drive.internal.TrashResourceRequest> r0 = com.google.android.gms.drive.internal.TrashResourceRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                com.google.android.gms.drive.internal.TrashResourceRequest r0 = (com.google.android.gms.drive.internal.TrashResourceRequest) r0
            L_0x026e:
                android.os.IBinder r2 = r7.readStrongBinder()
                com.google.android.gms.drive.internal.ab r2 = com.google.android.gms.drive.internal.ab.a.Q(r2)
                r5.a((com.google.android.gms.drive.internal.TrashResourceRequest) r0, (com.google.android.gms.drive.internal.ab) r2)
                r8.writeNoException()
                r0 = r1
                goto L_0x000a
            L_0x027f:
                java.lang.String r2 = "com.google.android.gms.drive.internal.IDriveService"
                r7.enforceInterface(r2)
                int r2 = r7.readInt()
                if (r2 == 0) goto L_0x0292
                android.os.Parcelable$Creator<com.google.android.gms.drive.internal.CloseContentsAndUpdateMetadataRequest> r0 = com.google.android.gms.drive.internal.CloseContentsAndUpdateMetadataRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                com.google.android.gms.drive.internal.CloseContentsAndUpdateMetadataRequest r0 = (com.google.android.gms.drive.internal.CloseContentsAndUpdateMetadataRequest) r0
            L_0x0292:
                android.os.IBinder r2 = r7.readStrongBinder()
                com.google.android.gms.drive.internal.ab r2 = com.google.android.gms.drive.internal.ab.a.Q(r2)
                r5.a((com.google.android.gms.drive.internal.CloseContentsAndUpdateMetadataRequest) r0, (com.google.android.gms.drive.internal.ab) r2)
                r8.writeNoException()
                r0 = r1
                goto L_0x000a
            L_0x02a3:
                java.lang.String r2 = "com.google.android.gms.drive.internal.IDriveService"
                r7.enforceInterface(r2)
                int r2 = r7.readInt()
                if (r2 == 0) goto L_0x02b6
                android.os.Parcelable$Creator<com.google.android.gms.drive.internal.QueryRequest> r0 = com.google.android.gms.drive.internal.QueryRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                com.google.android.gms.drive.internal.QueryRequest r0 = (com.google.android.gms.drive.internal.QueryRequest) r0
            L_0x02b6:
                android.os.IBinder r2 = r7.readStrongBinder()
                com.google.android.gms.drive.internal.ab r2 = com.google.android.gms.drive.internal.ab.a.Q(r2)
                r5.b(r0, r2)
                r8.writeNoException()
                r0 = r1
                goto L_0x000a
            L_0x02c7:
                java.lang.String r0 = "com.google.android.gms.drive.internal.IDriveService"
                r7.enforceInterface(r0)
                android.os.IBinder r0 = r7.readStrongBinder()
                com.google.android.gms.drive.internal.ab r0 = com.google.android.gms.drive.internal.ab.a.Q(r0)
                r5.b(r0)
                r8.writeNoException()
                r0 = r1
                goto L_0x000a
            L_0x02dd:
                java.lang.String r0 = "com.google.android.gms.drive.internal.IDriveService"
                r7.enforceInterface(r0)
                android.os.IBinder r0 = r7.readStrongBinder()
                com.google.android.gms.drive.internal.ab r0 = com.google.android.gms.drive.internal.ab.a.Q(r0)
                r5.c(r0)
                r8.writeNoException()
                r0 = r1
                goto L_0x000a
            L_0x02f3:
                java.lang.String r0 = "com.google.android.gms.drive.internal.IDriveService"
                r7.enforceInterface(r0)
                android.os.IBinder r0 = r7.readStrongBinder()
                com.google.android.gms.drive.internal.ab r0 = com.google.android.gms.drive.internal.ab.a.Q(r0)
                r5.d(r0)
                r8.writeNoException()
                r0 = r1
                goto L_0x000a
            L_0x0309:
                java.lang.String r0 = "com.google.android.gms.drive.internal.IDriveService"
                r7.enforceInterface(r0)
                android.os.IBinder r0 = r7.readStrongBinder()
                com.google.android.gms.drive.internal.ab r0 = com.google.android.gms.drive.internal.ab.a.Q(r0)
                r5.e(r0)
                r8.writeNoException()
                r0 = r1
                goto L_0x000a
            L_0x031f:
                java.lang.String r2 = "com.google.android.gms.drive.internal.IDriveService"
                r7.enforceInterface(r2)
                int r2 = r7.readInt()
                if (r2 == 0) goto L_0x0332
                android.os.Parcelable$Creator<com.google.android.gms.drive.internal.DeleteResourceRequest> r0 = com.google.android.gms.drive.internal.DeleteResourceRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                com.google.android.gms.drive.internal.DeleteResourceRequest r0 = (com.google.android.gms.drive.internal.DeleteResourceRequest) r0
            L_0x0332:
                android.os.IBinder r2 = r7.readStrongBinder()
                com.google.android.gms.drive.internal.ab r2 = com.google.android.gms.drive.internal.ab.a.Q(r2)
                r5.a((com.google.android.gms.drive.internal.DeleteResourceRequest) r0, (com.google.android.gms.drive.internal.ab) r2)
                r8.writeNoException()
                r0 = r1
                goto L_0x000a
            L_0x0343:
                java.lang.String r2 = "com.google.android.gms.drive.internal.IDriveService"
                r7.enforceInterface(r2)
                int r2 = r7.readInt()
                if (r2 == 0) goto L_0x0356
                android.os.Parcelable$Creator<com.google.android.gms.drive.internal.DeleteCustomPropertyRequest> r0 = com.google.android.gms.drive.internal.DeleteCustomPropertyRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                com.google.android.gms.drive.internal.DeleteCustomPropertyRequest r0 = (com.google.android.gms.drive.internal.DeleteCustomPropertyRequest) r0
            L_0x0356:
                android.os.IBinder r2 = r7.readStrongBinder()
                com.google.android.gms.drive.internal.ab r2 = com.google.android.gms.drive.internal.ab.a.Q(r2)
                r5.a((com.google.android.gms.drive.internal.DeleteCustomPropertyRequest) r0, (com.google.android.gms.drive.internal.ab) r2)
                r8.writeNoException()
                r0 = r1
                goto L_0x000a
            L_0x0367:
                java.lang.String r2 = "com.google.android.gms.drive.internal.IDriveService"
                r7.enforceInterface(r2)
                int r2 = r7.readInt()
                if (r2 == 0) goto L_0x037a
                android.os.Parcelable$Creator<com.google.android.gms.drive.internal.LoadRealtimeRequest> r0 = com.google.android.gms.drive.internal.LoadRealtimeRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                com.google.android.gms.drive.internal.LoadRealtimeRequest r0 = (com.google.android.gms.drive.internal.LoadRealtimeRequest) r0
            L_0x037a:
                android.os.IBinder r2 = r7.readStrongBinder()
                com.google.android.gms.drive.internal.ab r2 = com.google.android.gms.drive.internal.ab.a.Q(r2)
                r5.a((com.google.android.gms.drive.internal.LoadRealtimeRequest) r0, (com.google.android.gms.drive.internal.ab) r2)
                r8.writeNoException()
                r0 = r1
                goto L_0x000a
            L_0x038b:
                java.lang.String r2 = "com.google.android.gms.drive.internal.IDriveService"
                r7.enforceInterface(r2)
                int r2 = r7.readInt()
                if (r2 == 0) goto L_0x039e
                android.os.Parcelable$Creator<com.google.android.gms.drive.internal.SetResourceParentsRequest> r0 = com.google.android.gms.drive.internal.SetResourceParentsRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                com.google.android.gms.drive.internal.SetResourceParentsRequest r0 = (com.google.android.gms.drive.internal.SetResourceParentsRequest) r0
            L_0x039e:
                android.os.IBinder r2 = r7.readStrongBinder()
                com.google.android.gms.drive.internal.ab r2 = com.google.android.gms.drive.internal.ab.a.Q(r2)
                r5.a((com.google.android.gms.drive.internal.SetResourceParentsRequest) r0, (com.google.android.gms.drive.internal.ab) r2)
                r8.writeNoException()
                r0 = r1
                goto L_0x000a
            L_0x03af:
                java.lang.String r2 = "com.google.android.gms.drive.internal.IDriveService"
                r7.enforceInterface(r2)
                int r2 = r7.readInt()
                if (r2 == 0) goto L_0x03c2
                android.os.Parcelable$Creator<com.google.android.gms.drive.internal.GetDriveIdFromUniqueIdentifierRequest> r0 = com.google.android.gms.drive.internal.GetDriveIdFromUniqueIdentifierRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                com.google.android.gms.drive.internal.GetDriveIdFromUniqueIdentifierRequest r0 = (com.google.android.gms.drive.internal.GetDriveIdFromUniqueIdentifierRequest) r0
            L_0x03c2:
                android.os.IBinder r2 = r7.readStrongBinder()
                com.google.android.gms.drive.internal.ab r2 = com.google.android.gms.drive.internal.ab.a.Q(r2)
                r5.a((com.google.android.gms.drive.internal.GetDriveIdFromUniqueIdentifierRequest) r0, (com.google.android.gms.drive.internal.ab) r2)
                r8.writeNoException()
                r0 = r1
                goto L_0x000a
            L_0x03d3:
                java.lang.String r2 = "com.google.android.gms.drive.internal.IDriveService"
                r7.enforceInterface(r2)
                int r2 = r7.readInt()
                if (r2 == 0) goto L_0x03e6
                android.os.Parcelable$Creator<com.google.android.gms.drive.internal.CheckResourceIdsExistRequest> r0 = com.google.android.gms.drive.internal.CheckResourceIdsExistRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                com.google.android.gms.drive.internal.CheckResourceIdsExistRequest r0 = (com.google.android.gms.drive.internal.CheckResourceIdsExistRequest) r0
            L_0x03e6:
                android.os.IBinder r2 = r7.readStrongBinder()
                com.google.android.gms.drive.internal.ab r2 = com.google.android.gms.drive.internal.ab.a.Q(r2)
                r5.a((com.google.android.gms.drive.internal.CheckResourceIdsExistRequest) r0, (com.google.android.gms.drive.internal.ab) r2)
                r8.writeNoException()
                r0 = r1
                goto L_0x000a
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.drive.internal.aa.a.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    IntentSender a(CreateFileIntentSenderRequest createFileIntentSenderRequest) throws RemoteException;

    IntentSender a(OpenFileIntentSenderRequest openFileIntentSenderRequest) throws RemoteException;

    void a(AddEventListenerRequest addEventListenerRequest, ac acVar, String str, ab abVar) throws RemoteException;

    void a(AuthorizeAccessRequest authorizeAccessRequest, ab abVar) throws RemoteException;

    void a(CheckResourceIdsExistRequest checkResourceIdsExistRequest, ab abVar) throws RemoteException;

    void a(CloseContentsAndUpdateMetadataRequest closeContentsAndUpdateMetadataRequest, ab abVar) throws RemoteException;

    void a(CloseContentsRequest closeContentsRequest, ab abVar) throws RemoteException;

    void a(CreateContentsRequest createContentsRequest, ab abVar) throws RemoteException;

    void a(CreateFileRequest createFileRequest, ab abVar) throws RemoteException;

    void a(CreateFolderRequest createFolderRequest, ab abVar) throws RemoteException;

    void a(DeleteCustomPropertyRequest deleteCustomPropertyRequest, ab abVar) throws RemoteException;

    void a(DeleteResourceRequest deleteResourceRequest, ab abVar) throws RemoteException;

    void a(DisconnectRequest disconnectRequest) throws RemoteException;

    void a(GetDriveIdFromUniqueIdentifierRequest getDriveIdFromUniqueIdentifierRequest, ab abVar) throws RemoteException;

    void a(GetMetadataRequest getMetadataRequest, ab abVar) throws RemoteException;

    void a(ListParentsRequest listParentsRequest, ab abVar) throws RemoteException;

    void a(LoadRealtimeRequest loadRealtimeRequest, ab abVar) throws RemoteException;

    void a(OpenContentsRequest openContentsRequest, ab abVar) throws RemoteException;

    void a(QueryRequest queryRequest, ab abVar) throws RemoteException;

    void a(RemoveEventListenerRequest removeEventListenerRequest, ac acVar, String str, ab abVar) throws RemoteException;

    void a(SetResourceParentsRequest setResourceParentsRequest, ab abVar) throws RemoteException;

    void a(TrashResourceRequest trashResourceRequest, ab abVar) throws RemoteException;

    void a(UpdateMetadataRequest updateMetadataRequest, ab abVar) throws RemoteException;

    void a(ab abVar) throws RemoteException;

    void b(QueryRequest queryRequest, ab abVar) throws RemoteException;

    void b(ab abVar) throws RemoteException;

    void c(ab abVar) throws RemoteException;

    void d(ab abVar) throws RemoteException;

    void e(ab abVar) throws RemoteException;
}
