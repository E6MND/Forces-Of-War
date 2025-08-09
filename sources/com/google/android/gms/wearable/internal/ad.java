package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.c;

public interface ad extends IInterface {

    public static abstract class a extends Binder implements ad {

        /* renamed from: com.google.android.gms.wearable.internal.ad$a$a  reason: collision with other inner class name */
        private static class C0131a implements ad {
            private IBinder ko;

            C0131a(IBinder iBinder) {
                this.ko = iBinder;
            }

            public void a(ab abVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    this.ko.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(ab abVar, Uri uri) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    if (uri != null) {
                        obtain.writeInt(1);
                        uri.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ko.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(ab abVar, Asset asset) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    if (asset != null) {
                        obtain.writeInt(1);
                        asset.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ko.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(ab abVar, PutDataRequest putDataRequest) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    if (putDataRequest != null) {
                        obtain.writeInt(1);
                        putDataRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ko.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(ab abVar, c cVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    if (cVar != null) {
                        obtain.writeInt(1);
                        cVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ko.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(ab abVar, ao aoVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    if (aoVar != null) {
                        obtain.writeInt(1);
                        aoVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ko.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(ab abVar, b bVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    if (bVar != null) {
                        obtain.writeInt(1);
                        bVar.writeToParcel(obtain, 0);
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

            public void a(ab abVar, String str, String str2, byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeByteArray(bArr);
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

            public void b(ab abVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    this.ko.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(ab abVar, Uri uri) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    if (uri != null) {
                        obtain.writeInt(1);
                        uri.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ko.transact(9, obtain, obtain2, 0);
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
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    this.ko.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void c(ab abVar, Uri uri) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    if (uri != null) {
                        obtain.writeInt(1);
                        uri.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ko.transact(11, obtain, obtain2, 0);
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
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    this.ko.transact(8, obtain, obtain2, 0);
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
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    this.ko.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void f(ab abVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    this.ko.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void g(ab abVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    this.ko.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void h(ab abVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wearable.internal.IWearableService");
                    obtain.writeStrongBinder(abVar != null ? abVar.asBinder() : null);
                    this.ko.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static ad by(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.wearable.internal.IWearableService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ad)) ? new C0131a(iBinder) : (ad) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: com.google.android.gms.wearable.internal.ao} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v14, resolved type: com.google.android.gms.wearable.internal.b} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v27, resolved type: com.google.android.gms.wearable.Asset} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v36, resolved type: android.net.Uri} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v41, resolved type: android.net.Uri} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v50, resolved type: android.net.Uri} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v55, resolved type: com.google.android.gms.wearable.PutDataRequest} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v72, resolved type: com.google.android.gms.wearable.c} */
        /* JADX WARNING: type inference failed for: r0v0 */
        /* JADX WARNING: type inference failed for: r0v80 */
        /* JADX WARNING: type inference failed for: r0v81 */
        /* JADX WARNING: type inference failed for: r0v82 */
        /* JADX WARNING: type inference failed for: r0v83 */
        /* JADX WARNING: type inference failed for: r0v84 */
        /* JADX WARNING: type inference failed for: r0v85 */
        /* JADX WARNING: type inference failed for: r0v86 */
        /* JADX WARNING: type inference failed for: r0v87 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r6, android.os.Parcel r7, android.os.Parcel r8, int r9) throws android.os.RemoteException {
            /*
                r5 = this;
                r0 = 0
                r1 = 1
                switch(r6) {
                    case 2: goto L_0x0011;
                    case 3: goto L_0x0034;
                    case 4: goto L_0x0049;
                    case 5: goto L_0x005e;
                    case 6: goto L_0x0073;
                    case 7: goto L_0x0097;
                    case 8: goto L_0x00bb;
                    case 9: goto L_0x00d1;
                    case 11: goto L_0x00f5;
                    case 12: goto L_0x0119;
                    case 13: goto L_0x013b;
                    case 14: goto L_0x015f;
                    case 15: goto L_0x0175;
                    case 16: goto L_0x018b;
                    case 17: goto L_0x01af;
                    case 18: goto L_0x01d3;
                    case 19: goto L_0x01e9;
                    case 1598968902: goto L_0x000a;
                    default: goto L_0x0005;
                }
            L_0x0005:
                boolean r0 = super.onTransact(r6, r7, r8, r9)
            L_0x0009:
                return r0
            L_0x000a:
                java.lang.String r0 = "com.google.android.gms.wearable.internal.IWearableService"
                r8.writeString(r0)
                r0 = r1
                goto L_0x0009
            L_0x0011:
                java.lang.String r2 = "com.google.android.gms.wearable.internal.IWearableService"
                r7.enforceInterface(r2)
                android.os.IBinder r2 = r7.readStrongBinder()
                com.google.android.gms.wearable.internal.ab r2 = com.google.android.gms.wearable.internal.ab.a.bw(r2)
                int r3 = r7.readInt()
                if (r3 == 0) goto L_0x002c
                android.os.Parcelable$Creator<com.google.android.gms.wearable.c> r0 = com.google.android.gms.wearable.c.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                com.google.android.gms.wearable.c r0 = (com.google.android.gms.wearable.c) r0
            L_0x002c:
                r5.a((com.google.android.gms.wearable.internal.ab) r2, (com.google.android.gms.wearable.c) r0)
                r8.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x0034:
                java.lang.String r0 = "com.google.android.gms.wearable.internal.IWearableService"
                r7.enforceInterface(r0)
                android.os.IBinder r0 = r7.readStrongBinder()
                com.google.android.gms.wearable.internal.ab r0 = com.google.android.gms.wearable.internal.ab.a.bw(r0)
                r5.a(r0)
                r8.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x0049:
                java.lang.String r0 = "com.google.android.gms.wearable.internal.IWearableService"
                r7.enforceInterface(r0)
                android.os.IBinder r0 = r7.readStrongBinder()
                com.google.android.gms.wearable.internal.ab r0 = com.google.android.gms.wearable.internal.ab.a.bw(r0)
                r5.b(r0)
                r8.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x005e:
                java.lang.String r0 = "com.google.android.gms.wearable.internal.IWearableService"
                r7.enforceInterface(r0)
                android.os.IBinder r0 = r7.readStrongBinder()
                com.google.android.gms.wearable.internal.ab r0 = com.google.android.gms.wearable.internal.ab.a.bw(r0)
                r5.c(r0)
                r8.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x0073:
                java.lang.String r2 = "com.google.android.gms.wearable.internal.IWearableService"
                r7.enforceInterface(r2)
                android.os.IBinder r2 = r7.readStrongBinder()
                com.google.android.gms.wearable.internal.ab r2 = com.google.android.gms.wearable.internal.ab.a.bw(r2)
                int r3 = r7.readInt()
                if (r3 == 0) goto L_0x008e
                android.os.Parcelable$Creator<com.google.android.gms.wearable.PutDataRequest> r0 = com.google.android.gms.wearable.PutDataRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                com.google.android.gms.wearable.PutDataRequest r0 = (com.google.android.gms.wearable.PutDataRequest) r0
            L_0x008e:
                r5.a((com.google.android.gms.wearable.internal.ab) r2, (com.google.android.gms.wearable.PutDataRequest) r0)
                r8.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x0097:
                java.lang.String r2 = "com.google.android.gms.wearable.internal.IWearableService"
                r7.enforceInterface(r2)
                android.os.IBinder r2 = r7.readStrongBinder()
                com.google.android.gms.wearable.internal.ab r2 = com.google.android.gms.wearable.internal.ab.a.bw(r2)
                int r3 = r7.readInt()
                if (r3 == 0) goto L_0x00b2
                android.os.Parcelable$Creator r0 = android.net.Uri.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                android.net.Uri r0 = (android.net.Uri) r0
            L_0x00b2:
                r5.a((com.google.android.gms.wearable.internal.ab) r2, (android.net.Uri) r0)
                r8.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x00bb:
                java.lang.String r0 = "com.google.android.gms.wearable.internal.IWearableService"
                r7.enforceInterface(r0)
                android.os.IBinder r0 = r7.readStrongBinder()
                com.google.android.gms.wearable.internal.ab r0 = com.google.android.gms.wearable.internal.ab.a.bw(r0)
                r5.d(r0)
                r8.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x00d1:
                java.lang.String r2 = "com.google.android.gms.wearable.internal.IWearableService"
                r7.enforceInterface(r2)
                android.os.IBinder r2 = r7.readStrongBinder()
                com.google.android.gms.wearable.internal.ab r2 = com.google.android.gms.wearable.internal.ab.a.bw(r2)
                int r3 = r7.readInt()
                if (r3 == 0) goto L_0x00ec
                android.os.Parcelable$Creator r0 = android.net.Uri.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                android.net.Uri r0 = (android.net.Uri) r0
            L_0x00ec:
                r5.b(r2, r0)
                r8.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x00f5:
                java.lang.String r2 = "com.google.android.gms.wearable.internal.IWearableService"
                r7.enforceInterface(r2)
                android.os.IBinder r2 = r7.readStrongBinder()
                com.google.android.gms.wearable.internal.ab r2 = com.google.android.gms.wearable.internal.ab.a.bw(r2)
                int r3 = r7.readInt()
                if (r3 == 0) goto L_0x0110
                android.os.Parcelable$Creator r0 = android.net.Uri.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                android.net.Uri r0 = (android.net.Uri) r0
            L_0x0110:
                r5.c(r2, r0)
                r8.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x0119:
                java.lang.String r0 = "com.google.android.gms.wearable.internal.IWearableService"
                r7.enforceInterface(r0)
                android.os.IBinder r0 = r7.readStrongBinder()
                com.google.android.gms.wearable.internal.ab r0 = com.google.android.gms.wearable.internal.ab.a.bw(r0)
                java.lang.String r2 = r7.readString()
                java.lang.String r3 = r7.readString()
                byte[] r4 = r7.createByteArray()
                r5.a(r0, r2, r3, r4)
                r8.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x013b:
                java.lang.String r2 = "com.google.android.gms.wearable.internal.IWearableService"
                r7.enforceInterface(r2)
                android.os.IBinder r2 = r7.readStrongBinder()
                com.google.android.gms.wearable.internal.ab r2 = com.google.android.gms.wearable.internal.ab.a.bw(r2)
                int r3 = r7.readInt()
                if (r3 == 0) goto L_0x0156
                android.os.Parcelable$Creator<com.google.android.gms.wearable.Asset> r0 = com.google.android.gms.wearable.Asset.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                com.google.android.gms.wearable.Asset r0 = (com.google.android.gms.wearable.Asset) r0
            L_0x0156:
                r5.a((com.google.android.gms.wearable.internal.ab) r2, (com.google.android.gms.wearable.Asset) r0)
                r8.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x015f:
                java.lang.String r0 = "com.google.android.gms.wearable.internal.IWearableService"
                r7.enforceInterface(r0)
                android.os.IBinder r0 = r7.readStrongBinder()
                com.google.android.gms.wearable.internal.ab r0 = com.google.android.gms.wearable.internal.ab.a.bw(r0)
                r5.e(r0)
                r8.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x0175:
                java.lang.String r0 = "com.google.android.gms.wearable.internal.IWearableService"
                r7.enforceInterface(r0)
                android.os.IBinder r0 = r7.readStrongBinder()
                com.google.android.gms.wearable.internal.ab r0 = com.google.android.gms.wearable.internal.ab.a.bw(r0)
                r5.f(r0)
                r8.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x018b:
                java.lang.String r2 = "com.google.android.gms.wearable.internal.IWearableService"
                r7.enforceInterface(r2)
                android.os.IBinder r2 = r7.readStrongBinder()
                com.google.android.gms.wearable.internal.ab r2 = com.google.android.gms.wearable.internal.ab.a.bw(r2)
                int r3 = r7.readInt()
                if (r3 == 0) goto L_0x01a6
                android.os.Parcelable$Creator<com.google.android.gms.wearable.internal.b> r0 = com.google.android.gms.wearable.internal.b.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                com.google.android.gms.wearable.internal.b r0 = (com.google.android.gms.wearable.internal.b) r0
            L_0x01a6:
                r5.a((com.google.android.gms.wearable.internal.ab) r2, (com.google.android.gms.wearable.internal.b) r0)
                r8.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x01af:
                java.lang.String r2 = "com.google.android.gms.wearable.internal.IWearableService"
                r7.enforceInterface(r2)
                android.os.IBinder r2 = r7.readStrongBinder()
                com.google.android.gms.wearable.internal.ab r2 = com.google.android.gms.wearable.internal.ab.a.bw(r2)
                int r3 = r7.readInt()
                if (r3 == 0) goto L_0x01ca
                android.os.Parcelable$Creator<com.google.android.gms.wearable.internal.ao> r0 = com.google.android.gms.wearable.internal.ao.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                com.google.android.gms.wearable.internal.ao r0 = (com.google.android.gms.wearable.internal.ao) r0
            L_0x01ca:
                r5.a((com.google.android.gms.wearable.internal.ab) r2, (com.google.android.gms.wearable.internal.ao) r0)
                r8.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x01d3:
                java.lang.String r0 = "com.google.android.gms.wearable.internal.IWearableService"
                r7.enforceInterface(r0)
                android.os.IBinder r0 = r7.readStrongBinder()
                com.google.android.gms.wearable.internal.ab r0 = com.google.android.gms.wearable.internal.ab.a.bw(r0)
                r5.g(r0)
                r8.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x01e9:
                java.lang.String r0 = "com.google.android.gms.wearable.internal.IWearableService"
                r7.enforceInterface(r0)
                android.os.IBinder r0 = r7.readStrongBinder()
                com.google.android.gms.wearable.internal.ab r0 = com.google.android.gms.wearable.internal.ab.a.bw(r0)
                r5.h(r0)
                r8.writeNoException()
                r0 = r1
                goto L_0x0009
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.wearable.internal.ad.a.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    void a(ab abVar) throws RemoteException;

    void a(ab abVar, Uri uri) throws RemoteException;

    void a(ab abVar, Asset asset) throws RemoteException;

    void a(ab abVar, PutDataRequest putDataRequest) throws RemoteException;

    void a(ab abVar, c cVar) throws RemoteException;

    void a(ab abVar, ao aoVar) throws RemoteException;

    void a(ab abVar, b bVar) throws RemoteException;

    void a(ab abVar, String str, String str2, byte[] bArr) throws RemoteException;

    void b(ab abVar) throws RemoteException;

    void b(ab abVar, Uri uri) throws RemoteException;

    void c(ab abVar) throws RemoteException;

    void c(ab abVar, Uri uri) throws RemoteException;

    void d(ab abVar) throws RemoteException;

    void e(ab abVar) throws RemoteException;

    void f(ab abVar) throws RemoteException;

    void g(ab abVar) throws RemoteException;

    void h(ab abVar) throws RemoteException;
}
