package com.google.android.gms.drive.realtime.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;

public interface m extends IInterface {

    public static abstract class a extends Binder implements m {

        /* renamed from: com.google.android.gms.drive.realtime.internal.m$a$a  reason: collision with other inner class name */
        private static class C0028a implements m {
            private IBinder ko;

            C0028a(IBinder iBinder) {
                this.ko = iBinder;
            }

            public void a(int i, j jVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(jVar != null ? jVar.asBinder() : null);
                    this.ko.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(BeginCompoundOperationRequest beginCompoundOperationRequest, o oVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    if (beginCompoundOperationRequest != null) {
                        obtain.writeInt(1);
                        beginCompoundOperationRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(oVar != null ? oVar.asBinder() : null);
                    this.ko.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(EndCompoundOperationRequest endCompoundOperationRequest, o oVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    if (endCompoundOperationRequest != null) {
                        obtain.writeInt(1);
                        endCompoundOperationRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(oVar != null ? oVar.asBinder() : null);
                    this.ko.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(ParcelableIndexReference parcelableIndexReference, n nVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    if (parcelableIndexReference != null) {
                        obtain.writeInt(1);
                        parcelableIndexReference.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(nVar != null ? nVar.asBinder() : null);
                    this.ko.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(c cVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeStrongBinder(cVar != null ? cVar.asBinder() : null);
                    this.ko.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.ko.transact(32, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(e eVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeStrongBinder(eVar != null ? eVar.asBinder() : null);
                    this.ko.transact(31, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(h hVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeStrongBinder(hVar != null ? hVar.asBinder() : null);
                    this.ko.transact(36, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(i iVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeStrongBinder(iVar != null ? iVar.asBinder() : null);
                    this.ko.transact(34, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(j jVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeStrongBinder(jVar != null ? jVar.asBinder() : null);
                    this.ko.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(l lVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeStrongBinder(lVar != null ? lVar.asBinder() : null);
                    this.ko.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(o oVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeStrongBinder(oVar != null ? oVar.asBinder() : null);
                    this.ko.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(String str, int i, int i2, g gVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeStrongBinder(gVar != null ? gVar.asBinder() : null);
                    this.ko.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(String str, int i, int i2, j jVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeStrongBinder(jVar != null ? jVar.asBinder() : null);
                    this.ko.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(String str, int i, DataHolder dataHolder, g gVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(gVar != null ? gVar.asBinder() : null);
                    this.ko.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(String str, int i, DataHolder dataHolder, j jVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(jVar != null ? jVar.asBinder() : null);
                    this.ko.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(String str, int i, o oVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(oVar != null ? oVar.asBinder() : null);
                    this.ko.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(String str, int i, String str2, int i2, j jVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeString(str2);
                    obtain.writeInt(i2);
                    obtain.writeStrongBinder(jVar != null ? jVar.asBinder() : null);
                    this.ko.transact(37, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(String str, int i, String str2, j jVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(jVar != null ? jVar.asBinder() : null);
                    this.ko.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(String str, DataHolder dataHolder, j jVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(jVar != null ? jVar.asBinder() : null);
                    this.ko.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(String str, f fVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(fVar != null ? fVar.asBinder() : null);
                    this.ko.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(String str, j jVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(jVar != null ? jVar.asBinder() : null);
                    this.ko.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(String str, k kVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(kVar != null ? kVar.asBinder() : null);
                    this.ko.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(String str, l lVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(lVar != null ? lVar.asBinder() : null);
                    this.ko.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(String str, n nVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(nVar != null ? nVar.asBinder() : null);
                    this.ko.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(String str, o oVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(oVar != null ? oVar.asBinder() : null);
                    this.ko.transact(38, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(String str, String str2, f fVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(fVar != null ? fVar.asBinder() : null);
                    this.ko.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(String str, String str2, g gVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(gVar != null ? gVar.asBinder() : null);
                    this.ko.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(String str, String str2, j jVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(jVar != null ? jVar.asBinder() : null);
                    this.ko.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.ko;
            }

            public void b(c cVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeStrongBinder(cVar != null ? cVar.asBinder() : null);
                    this.ko.transact(33, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(j jVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeStrongBinder(jVar != null ? jVar.asBinder() : null);
                    this.ko.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(o oVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeStrongBinder(oVar != null ? oVar.asBinder() : null);
                    this.ko.transact(35, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(String str, f fVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(fVar != null ? fVar.asBinder() : null);
                    this.ko.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(String str, l lVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(lVar != null ? lVar.asBinder() : null);
                    this.ko.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(String str, n nVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(nVar != null ? nVar.asBinder() : null);
                    this.ko.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(String str, o oVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(oVar != null ? oVar.asBinder() : null);
                    this.ko.transact(39, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void c(c cVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeStrongBinder(cVar != null ? cVar.asBinder() : null);
                    this.ko.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void c(String str, l lVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(lVar != null ? lVar.asBinder() : null);
                    this.ko.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void d(c cVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.drive.realtime.internal.IRealtimeService");
                    obtain.writeStrongBinder(cVar != null ? cVar.asBinder() : null);
                    this.ko.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static m ac(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof m)) ? new C0028a(iBinder) : (m) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v36, resolved type: com.google.android.gms.drive.realtime.internal.ParcelableIndexReference} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v57, resolved type: com.google.android.gms.drive.realtime.internal.EndCompoundOperationRequest} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v62, resolved type: com.google.android.gms.drive.realtime.internal.BeginCompoundOperationRequest} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v74, resolved type: com.google.android.gms.common.data.DataHolder} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v78, resolved type: com.google.android.gms.common.data.DataHolder} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v112, resolved type: com.google.android.gms.common.data.DataHolder} */
        /* JADX WARNING: type inference failed for: r0v0 */
        /* JADX WARNING: type inference failed for: r0v144 */
        /* JADX WARNING: type inference failed for: r0v145 */
        /* JADX WARNING: type inference failed for: r0v146 */
        /* JADX WARNING: type inference failed for: r0v147 */
        /* JADX WARNING: type inference failed for: r0v148 */
        /* JADX WARNING: type inference failed for: r0v149 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r8, android.os.Parcel r9, android.os.Parcel r10, int r11) throws android.os.RemoteException {
            /*
                r7 = this;
                r0 = 0
                r6 = 1
                switch(r8) {
                    case 1: goto L_0x0011;
                    case 2: goto L_0x002a;
                    case 3: goto L_0x003f;
                    case 4: goto L_0x007e;
                    case 5: goto L_0x009c;
                    case 6: goto L_0x00b6;
                    case 7: goto L_0x00dc;
                    case 8: goto L_0x012e;
                    case 9: goto L_0x0148;
                    case 10: goto L_0x0162;
                    case 11: goto L_0x0184;
                    case 12: goto L_0x01a6;
                    case 13: goto L_0x01c4;
                    case 14: goto L_0x01de;
                    case 15: goto L_0x01f8;
                    case 16: goto L_0x0222;
                    case 17: goto L_0x024c;
                    case 18: goto L_0x0295;
                    case 19: goto L_0x02b9;
                    case 20: goto L_0x00f6;
                    case 21: goto L_0x0110;
                    case 22: goto L_0x02dd;
                    case 23: goto L_0x02f3;
                    case 24: goto L_0x0309;
                    case 25: goto L_0x031f;
                    case 26: goto L_0x0335;
                    case 27: goto L_0x0359;
                    case 28: goto L_0x0373;
                    case 29: goto L_0x0391;
                    case 30: goto L_0x03a7;
                    case 31: goto L_0x03c1;
                    case 32: goto L_0x03d7;
                    case 33: goto L_0x0054;
                    case 34: goto L_0x03ed;
                    case 35: goto L_0x0069;
                    case 36: goto L_0x0403;
                    case 37: goto L_0x026e;
                    case 38: goto L_0x0419;
                    case 39: goto L_0x0433;
                    case 1598968902: goto L_0x000a;
                    default: goto L_0x0005;
                }
            L_0x0005:
                boolean r0 = super.onTransact(r8, r9, r10, r11)
            L_0x0009:
                return r0
            L_0x000a:
                java.lang.String r0 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r10.writeString(r0)
                r0 = r6
                goto L_0x0009
            L_0x0011:
                java.lang.String r0 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r0)
                java.lang.String r0 = r9.readString()
                android.os.IBinder r1 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.n r1 = com.google.android.gms.drive.realtime.internal.n.a.ad(r1)
                r7.a((java.lang.String) r0, (com.google.android.gms.drive.realtime.internal.n) r1)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x002a:
                java.lang.String r0 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r0)
                android.os.IBinder r0 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.c r0 = com.google.android.gms.drive.realtime.internal.c.a.S(r0)
                r7.a((com.google.android.gms.drive.realtime.internal.c) r0)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x003f:
                java.lang.String r0 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r0)
                android.os.IBinder r0 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.o r0 = com.google.android.gms.drive.realtime.internal.o.a.ae(r0)
                r7.a((com.google.android.gms.drive.realtime.internal.o) r0)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x0054:
                java.lang.String r0 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r0)
                android.os.IBinder r0 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.c r0 = com.google.android.gms.drive.realtime.internal.c.a.S(r0)
                r7.b((com.google.android.gms.drive.realtime.internal.c) r0)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x0069:
                java.lang.String r0 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r0)
                android.os.IBinder r0 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.o r0 = com.google.android.gms.drive.realtime.internal.o.a.ae(r0)
                r7.b((com.google.android.gms.drive.realtime.internal.o) r0)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x007e:
                java.lang.String r0 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r0)
                java.lang.String r0 = r9.readString()
                java.lang.String r1 = r9.readString()
                android.os.IBinder r2 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.f r2 = com.google.android.gms.drive.realtime.internal.f.a.V(r2)
                r7.a((java.lang.String) r0, (java.lang.String) r1, (com.google.android.gms.drive.realtime.internal.f) r2)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x009c:
                java.lang.String r0 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r0)
                java.lang.String r0 = r9.readString()
                android.os.IBinder r1 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.l r1 = com.google.android.gms.drive.realtime.internal.l.a.ab(r1)
                r7.a((java.lang.String) r0, (com.google.android.gms.drive.realtime.internal.l) r1)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x00b6:
                java.lang.String r1 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r1)
                java.lang.String r1 = r9.readString()
                int r2 = r9.readInt()
                if (r2 == 0) goto L_0x00cb
                com.google.android.gms.common.data.f r0 = com.google.android.gms.common.data.DataHolder.CREATOR
                com.google.android.gms.common.data.DataHolder r0 = r0.createFromParcel(r9)
            L_0x00cb:
                android.os.IBinder r2 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.j r2 = com.google.android.gms.drive.realtime.internal.j.a.Z(r2)
                r7.a((java.lang.String) r1, (com.google.android.gms.common.data.DataHolder) r0, (com.google.android.gms.drive.realtime.internal.j) r2)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x00dc:
                java.lang.String r0 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r0)
                java.lang.String r0 = r9.readString()
                android.os.IBinder r1 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.j r1 = com.google.android.gms.drive.realtime.internal.j.a.Z(r1)
                r7.a((java.lang.String) r0, (com.google.android.gms.drive.realtime.internal.j) r1)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x00f6:
                java.lang.String r0 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r0)
                java.lang.String r0 = r9.readString()
                android.os.IBinder r1 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.f r1 = com.google.android.gms.drive.realtime.internal.f.a.V(r1)
                r7.a((java.lang.String) r0, (com.google.android.gms.drive.realtime.internal.f) r1)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x0110:
                java.lang.String r0 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r0)
                java.lang.String r0 = r9.readString()
                java.lang.String r1 = r9.readString()
                android.os.IBinder r2 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.g r2 = com.google.android.gms.drive.realtime.internal.g.a.W(r2)
                r7.a((java.lang.String) r0, (java.lang.String) r1, (com.google.android.gms.drive.realtime.internal.g) r2)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x012e:
                java.lang.String r0 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r0)
                java.lang.String r0 = r9.readString()
                android.os.IBinder r1 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.l r1 = com.google.android.gms.drive.realtime.internal.l.a.ab(r1)
                r7.b((java.lang.String) r0, (com.google.android.gms.drive.realtime.internal.l) r1)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x0148:
                java.lang.String r0 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r0)
                java.lang.String r0 = r9.readString()
                android.os.IBinder r1 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.n r1 = com.google.android.gms.drive.realtime.internal.n.a.ad(r1)
                r7.b((java.lang.String) r0, (com.google.android.gms.drive.realtime.internal.n) r1)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x0162:
                java.lang.String r0 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r0)
                java.lang.String r0 = r9.readString()
                int r1 = r9.readInt()
                java.lang.String r2 = r9.readString()
                android.os.IBinder r3 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.j r3 = com.google.android.gms.drive.realtime.internal.j.a.Z(r3)
                r7.a((java.lang.String) r0, (int) r1, (java.lang.String) r2, (com.google.android.gms.drive.realtime.internal.j) r3)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x0184:
                java.lang.String r0 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r0)
                java.lang.String r0 = r9.readString()
                int r1 = r9.readInt()
                int r2 = r9.readInt()
                android.os.IBinder r3 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.j r3 = com.google.android.gms.drive.realtime.internal.j.a.Z(r3)
                r7.a((java.lang.String) r0, (int) r1, (int) r2, (com.google.android.gms.drive.realtime.internal.j) r3)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x01a6:
                java.lang.String r0 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r0)
                java.lang.String r0 = r9.readString()
                java.lang.String r1 = r9.readString()
                android.os.IBinder r2 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.j r2 = com.google.android.gms.drive.realtime.internal.j.a.Z(r2)
                r7.a((java.lang.String) r0, (java.lang.String) r1, (com.google.android.gms.drive.realtime.internal.j) r2)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x01c4:
                java.lang.String r0 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r0)
                java.lang.String r0 = r9.readString()
                android.os.IBinder r1 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.f r1 = com.google.android.gms.drive.realtime.internal.f.a.V(r1)
                r7.b((java.lang.String) r0, (com.google.android.gms.drive.realtime.internal.f) r1)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x01de:
                java.lang.String r0 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r0)
                java.lang.String r0 = r9.readString()
                android.os.IBinder r1 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.l r1 = com.google.android.gms.drive.realtime.internal.l.a.ab(r1)
                r7.c(r0, r1)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x01f8:
                java.lang.String r1 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r1)
                java.lang.String r1 = r9.readString()
                int r2 = r9.readInt()
                int r3 = r9.readInt()
                if (r3 == 0) goto L_0x0211
                com.google.android.gms.common.data.f r0 = com.google.android.gms.common.data.DataHolder.CREATOR
                com.google.android.gms.common.data.DataHolder r0 = r0.createFromParcel(r9)
            L_0x0211:
                android.os.IBinder r3 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.j r3 = com.google.android.gms.drive.realtime.internal.j.a.Z(r3)
                r7.a((java.lang.String) r1, (int) r2, (com.google.android.gms.common.data.DataHolder) r0, (com.google.android.gms.drive.realtime.internal.j) r3)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x0222:
                java.lang.String r1 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r1)
                java.lang.String r1 = r9.readString()
                int r2 = r9.readInt()
                int r3 = r9.readInt()
                if (r3 == 0) goto L_0x023b
                com.google.android.gms.common.data.f r0 = com.google.android.gms.common.data.DataHolder.CREATOR
                com.google.android.gms.common.data.DataHolder r0 = r0.createFromParcel(r9)
            L_0x023b:
                android.os.IBinder r3 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.g r3 = com.google.android.gms.drive.realtime.internal.g.a.W(r3)
                r7.a((java.lang.String) r1, (int) r2, (com.google.android.gms.common.data.DataHolder) r0, (com.google.android.gms.drive.realtime.internal.g) r3)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x024c:
                java.lang.String r0 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r0)
                java.lang.String r0 = r9.readString()
                int r1 = r9.readInt()
                int r2 = r9.readInt()
                android.os.IBinder r3 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.g r3 = com.google.android.gms.drive.realtime.internal.g.a.W(r3)
                r7.a((java.lang.String) r0, (int) r1, (int) r2, (com.google.android.gms.drive.realtime.internal.g) r3)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x026e:
                java.lang.String r0 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r0)
                java.lang.String r1 = r9.readString()
                int r2 = r9.readInt()
                java.lang.String r3 = r9.readString()
                int r4 = r9.readInt()
                android.os.IBinder r0 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.j r5 = com.google.android.gms.drive.realtime.internal.j.a.Z(r0)
                r0 = r7
                r0.a(r1, r2, r3, r4, r5)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x0295:
                java.lang.String r1 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r1)
                int r1 = r9.readInt()
                if (r1 == 0) goto L_0x02a8
                android.os.Parcelable$Creator<com.google.android.gms.drive.realtime.internal.BeginCompoundOperationRequest> r0 = com.google.android.gms.drive.realtime.internal.BeginCompoundOperationRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r9)
                com.google.android.gms.drive.realtime.internal.BeginCompoundOperationRequest r0 = (com.google.android.gms.drive.realtime.internal.BeginCompoundOperationRequest) r0
            L_0x02a8:
                android.os.IBinder r1 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.o r1 = com.google.android.gms.drive.realtime.internal.o.a.ae(r1)
                r7.a((com.google.android.gms.drive.realtime.internal.BeginCompoundOperationRequest) r0, (com.google.android.gms.drive.realtime.internal.o) r1)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x02b9:
                java.lang.String r1 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r1)
                int r1 = r9.readInt()
                if (r1 == 0) goto L_0x02cc
                android.os.Parcelable$Creator<com.google.android.gms.drive.realtime.internal.EndCompoundOperationRequest> r0 = com.google.android.gms.drive.realtime.internal.EndCompoundOperationRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r9)
                com.google.android.gms.drive.realtime.internal.EndCompoundOperationRequest r0 = (com.google.android.gms.drive.realtime.internal.EndCompoundOperationRequest) r0
            L_0x02cc:
                android.os.IBinder r1 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.o r1 = com.google.android.gms.drive.realtime.internal.o.a.ae(r1)
                r7.a((com.google.android.gms.drive.realtime.internal.EndCompoundOperationRequest) r0, (com.google.android.gms.drive.realtime.internal.o) r1)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x02dd:
                java.lang.String r0 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r0)
                android.os.IBinder r0 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.j r0 = com.google.android.gms.drive.realtime.internal.j.a.Z(r0)
                r7.a((com.google.android.gms.drive.realtime.internal.j) r0)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x02f3:
                java.lang.String r0 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r0)
                android.os.IBinder r0 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.j r0 = com.google.android.gms.drive.realtime.internal.j.a.Z(r0)
                r7.b((com.google.android.gms.drive.realtime.internal.j) r0)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x0309:
                java.lang.String r0 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r0)
                android.os.IBinder r0 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.c r0 = com.google.android.gms.drive.realtime.internal.c.a.S(r0)
                r7.c(r0)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x031f:
                java.lang.String r0 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r0)
                android.os.IBinder r0 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.c r0 = com.google.android.gms.drive.realtime.internal.c.a.S(r0)
                r7.d(r0)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x0335:
                java.lang.String r1 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r1)
                int r1 = r9.readInt()
                if (r1 == 0) goto L_0x0348
                android.os.Parcelable$Creator<com.google.android.gms.drive.realtime.internal.ParcelableIndexReference> r0 = com.google.android.gms.drive.realtime.internal.ParcelableIndexReference.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r9)
                com.google.android.gms.drive.realtime.internal.ParcelableIndexReference r0 = (com.google.android.gms.drive.realtime.internal.ParcelableIndexReference) r0
            L_0x0348:
                android.os.IBinder r1 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.n r1 = com.google.android.gms.drive.realtime.internal.n.a.ad(r1)
                r7.a((com.google.android.gms.drive.realtime.internal.ParcelableIndexReference) r0, (com.google.android.gms.drive.realtime.internal.n) r1)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x0359:
                java.lang.String r0 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r0)
                java.lang.String r0 = r9.readString()
                android.os.IBinder r1 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.k r1 = com.google.android.gms.drive.realtime.internal.k.a.aa(r1)
                r7.a((java.lang.String) r0, (com.google.android.gms.drive.realtime.internal.k) r1)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x0373:
                java.lang.String r0 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r0)
                java.lang.String r0 = r9.readString()
                int r1 = r9.readInt()
                android.os.IBinder r2 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.o r2 = com.google.android.gms.drive.realtime.internal.o.a.ae(r2)
                r7.a((java.lang.String) r0, (int) r1, (com.google.android.gms.drive.realtime.internal.o) r2)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x0391:
                java.lang.String r0 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r0)
                android.os.IBinder r0 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.l r0 = com.google.android.gms.drive.realtime.internal.l.a.ab(r0)
                r7.a((com.google.android.gms.drive.realtime.internal.l) r0)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x03a7:
                java.lang.String r0 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r0)
                int r0 = r9.readInt()
                android.os.IBinder r1 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.j r1 = com.google.android.gms.drive.realtime.internal.j.a.Z(r1)
                r7.a((int) r0, (com.google.android.gms.drive.realtime.internal.j) r1)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x03c1:
                java.lang.String r0 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r0)
                android.os.IBinder r0 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.e r0 = com.google.android.gms.drive.realtime.internal.e.a.U(r0)
                r7.a((com.google.android.gms.drive.realtime.internal.e) r0)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x03d7:
                java.lang.String r0 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r0)
                android.os.IBinder r0 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.d r0 = com.google.android.gms.drive.realtime.internal.d.a.T(r0)
                r7.a((com.google.android.gms.drive.realtime.internal.d) r0)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x03ed:
                java.lang.String r0 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r0)
                android.os.IBinder r0 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.i r0 = com.google.android.gms.drive.realtime.internal.i.a.Y(r0)
                r7.a((com.google.android.gms.drive.realtime.internal.i) r0)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x0403:
                java.lang.String r0 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r0)
                android.os.IBinder r0 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.h r0 = com.google.android.gms.drive.realtime.internal.h.a.X(r0)
                r7.a((com.google.android.gms.drive.realtime.internal.h) r0)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x0419:
                java.lang.String r0 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r0)
                java.lang.String r0 = r9.readString()
                android.os.IBinder r1 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.o r1 = com.google.android.gms.drive.realtime.internal.o.a.ae(r1)
                r7.a((java.lang.String) r0, (com.google.android.gms.drive.realtime.internal.o) r1)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            L_0x0433:
                java.lang.String r0 = "com.google.android.gms.drive.realtime.internal.IRealtimeService"
                r9.enforceInterface(r0)
                java.lang.String r0 = r9.readString()
                android.os.IBinder r1 = r9.readStrongBinder()
                com.google.android.gms.drive.realtime.internal.o r1 = com.google.android.gms.drive.realtime.internal.o.a.ae(r1)
                r7.b((java.lang.String) r0, (com.google.android.gms.drive.realtime.internal.o) r1)
                r10.writeNoException()
                r0 = r6
                goto L_0x0009
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.drive.realtime.internal.m.a.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    void a(int i, j jVar) throws RemoteException;

    void a(BeginCompoundOperationRequest beginCompoundOperationRequest, o oVar) throws RemoteException;

    void a(EndCompoundOperationRequest endCompoundOperationRequest, o oVar) throws RemoteException;

    void a(ParcelableIndexReference parcelableIndexReference, n nVar) throws RemoteException;

    void a(c cVar) throws RemoteException;

    void a(d dVar) throws RemoteException;

    void a(e eVar) throws RemoteException;

    void a(h hVar) throws RemoteException;

    void a(i iVar) throws RemoteException;

    void a(j jVar) throws RemoteException;

    void a(l lVar) throws RemoteException;

    void a(o oVar) throws RemoteException;

    void a(String str, int i, int i2, g gVar) throws RemoteException;

    void a(String str, int i, int i2, j jVar) throws RemoteException;

    void a(String str, int i, DataHolder dataHolder, g gVar) throws RemoteException;

    void a(String str, int i, DataHolder dataHolder, j jVar) throws RemoteException;

    void a(String str, int i, o oVar) throws RemoteException;

    void a(String str, int i, String str2, int i2, j jVar) throws RemoteException;

    void a(String str, int i, String str2, j jVar) throws RemoteException;

    void a(String str, DataHolder dataHolder, j jVar) throws RemoteException;

    void a(String str, f fVar) throws RemoteException;

    void a(String str, j jVar) throws RemoteException;

    void a(String str, k kVar) throws RemoteException;

    void a(String str, l lVar) throws RemoteException;

    void a(String str, n nVar) throws RemoteException;

    void a(String str, o oVar) throws RemoteException;

    void a(String str, String str2, f fVar) throws RemoteException;

    void a(String str, String str2, g gVar) throws RemoteException;

    void a(String str, String str2, j jVar) throws RemoteException;

    void b(c cVar) throws RemoteException;

    void b(j jVar) throws RemoteException;

    void b(o oVar) throws RemoteException;

    void b(String str, f fVar) throws RemoteException;

    void b(String str, l lVar) throws RemoteException;

    void b(String str, n nVar) throws RemoteException;

    void b(String str, o oVar) throws RemoteException;

    void c(c cVar) throws RemoteException;

    void c(String str, l lVar) throws RemoteException;

    void d(c cVar) throws RemoteException;
}
