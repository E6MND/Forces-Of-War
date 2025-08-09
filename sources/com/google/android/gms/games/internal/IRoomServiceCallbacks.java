package com.google.android.gms.games.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public interface IRoomServiceCallbacks extends IInterface {

    public static abstract class Stub extends Binder implements IRoomServiceCallbacks {

        private static class Proxy implements IRoomServiceCallbacks {
            private IBinder ko;

            Proxy(IBinder remote) {
                this.ko = remote;
            }

            public void a(ParcelFileDescriptor parcelFileDescriptor, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    if (parcelFileDescriptor != null) {
                        obtain.writeInt(1);
                        parcelFileDescriptor.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    this.ko.transact(1024, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void a(ConnectionInfo connectionInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    if (connectionInfo != null) {
                        obtain.writeInt(1);
                        connectionInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ko.transact(1022, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void a(String str, byte[] bArr, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(str);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(i);
                    this.ko.transact(1002, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void a(String str, String[] strArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(str);
                    obtain.writeStringArray(strArr);
                    this.ko.transact(1008, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void al(IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeStrongBinder(iBinder);
                    this.ko.transact(1021, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.ko;
            }

            public void b(String str, String[] strArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(str);
                    obtain.writeStringArray(strArr);
                    this.ko.transact(1009, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void bg(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(str);
                    this.ko.transact(1003, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void bh(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(str);
                    this.ko.transact(1004, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void bi(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(str);
                    this.ko.transact(1005, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void bj(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(str);
                    this.ko.transact(1006, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void bk(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(str);
                    this.ko.transact(1007, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void bl(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(str);
                    this.ko.transact(1018, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void bm(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(str);
                    this.ko.transact(1019, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void c(int i, int i2, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    this.ko.transact(1001, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void c(String str, String[] strArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(str);
                    obtain.writeStringArray(strArr);
                    this.ko.transact(1010, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void ck(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeInt(i);
                    this.ko.transact(1020, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void d(String str, String[] strArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(str);
                    obtain.writeStringArray(strArr);
                    this.ko.transact(1011, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void e(String str, String[] strArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(str);
                    obtain.writeStringArray(strArr);
                    this.ko.transact(1012, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void f(String str, String[] strArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(str);
                    obtain.writeStringArray(strArr);
                    this.ko.transact(1013, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void g(String str, String[] strArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(str);
                    obtain.writeStringArray(strArr);
                    this.ko.transact(1017, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void hE() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.ko.transact(1016, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void hF() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    this.ko.transact(1023, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onP2PConnected(String participantId) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(participantId);
                    this.ko.transact(1014, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onP2PDisconnected(String participantId) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(participantId);
                    this.ko.transact(1015, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void t(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IRoomServiceCallbacks");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.ko.transact(1025, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "com.google.android.gms.games.internal.IRoomServiceCallbacks");
        }

        public static IRoomServiceCallbacks am(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.games.internal.IRoomServiceCallbacks");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IRoomServiceCallbacks)) ? new Proxy(iBinder) : (IRoomServiceCallbacks) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: android.os.ParcelFileDescriptor} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: com.google.android.gms.games.internal.ConnectionInfo} */
        /* JADX WARNING: type inference failed for: r0v0 */
        /* JADX WARNING: type inference failed for: r0v80 */
        /* JADX WARNING: type inference failed for: r0v81 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r5, android.os.Parcel r6, android.os.Parcel r7, int r8) throws android.os.RemoteException {
            /*
                r4 = this;
                r0 = 0
                r1 = 1
                switch(r5) {
                    case 1001: goto L_0x0011;
                    case 1002: goto L_0x0027;
                    case 1003: goto L_0x003d;
                    case 1004: goto L_0x004b;
                    case 1005: goto L_0x0059;
                    case 1006: goto L_0x0067;
                    case 1007: goto L_0x0075;
                    case 1008: goto L_0x0083;
                    case 1009: goto L_0x0096;
                    case 1010: goto L_0x00a9;
                    case 1011: goto L_0x00bc;
                    case 1012: goto L_0x00cf;
                    case 1013: goto L_0x00e2;
                    case 1014: goto L_0x00f5;
                    case 1015: goto L_0x0104;
                    case 1016: goto L_0x0113;
                    case 1017: goto L_0x011e;
                    case 1018: goto L_0x0131;
                    case 1019: goto L_0x0140;
                    case 1020: goto L_0x014f;
                    case 1021: goto L_0x015e;
                    case 1022: goto L_0x016d;
                    case 1023: goto L_0x0184;
                    case 1024: goto L_0x018f;
                    case 1025: goto L_0x01ac;
                    case 1598968902: goto L_0x000a;
                    default: goto L_0x0005;
                }
            L_0x0005:
                boolean r0 = super.onTransact(r5, r6, r7, r8)
            L_0x0009:
                return r0
            L_0x000a:
                java.lang.String r0 = "com.google.android.gms.games.internal.IRoomServiceCallbacks"
                r7.writeString(r0)
                r0 = r1
                goto L_0x0009
            L_0x0011:
                java.lang.String r0 = "com.google.android.gms.games.internal.IRoomServiceCallbacks"
                r6.enforceInterface(r0)
                int r0 = r6.readInt()
                int r2 = r6.readInt()
                java.lang.String r3 = r6.readString()
                r4.c(r0, r2, r3)
                r0 = r1
                goto L_0x0009
            L_0x0027:
                java.lang.String r0 = "com.google.android.gms.games.internal.IRoomServiceCallbacks"
                r6.enforceInterface(r0)
                java.lang.String r0 = r6.readString()
                byte[] r2 = r6.createByteArray()
                int r3 = r6.readInt()
                r4.a(r0, r2, r3)
                r0 = r1
                goto L_0x0009
            L_0x003d:
                java.lang.String r0 = "com.google.android.gms.games.internal.IRoomServiceCallbacks"
                r6.enforceInterface(r0)
                java.lang.String r0 = r6.readString()
                r4.bg(r0)
                r0 = r1
                goto L_0x0009
            L_0x004b:
                java.lang.String r0 = "com.google.android.gms.games.internal.IRoomServiceCallbacks"
                r6.enforceInterface(r0)
                java.lang.String r0 = r6.readString()
                r4.bh(r0)
                r0 = r1
                goto L_0x0009
            L_0x0059:
                java.lang.String r0 = "com.google.android.gms.games.internal.IRoomServiceCallbacks"
                r6.enforceInterface(r0)
                java.lang.String r0 = r6.readString()
                r4.bi(r0)
                r0 = r1
                goto L_0x0009
            L_0x0067:
                java.lang.String r0 = "com.google.android.gms.games.internal.IRoomServiceCallbacks"
                r6.enforceInterface(r0)
                java.lang.String r0 = r6.readString()
                r4.bj(r0)
                r0 = r1
                goto L_0x0009
            L_0x0075:
                java.lang.String r0 = "com.google.android.gms.games.internal.IRoomServiceCallbacks"
                r6.enforceInterface(r0)
                java.lang.String r0 = r6.readString()
                r4.bk(r0)
                r0 = r1
                goto L_0x0009
            L_0x0083:
                java.lang.String r0 = "com.google.android.gms.games.internal.IRoomServiceCallbacks"
                r6.enforceInterface(r0)
                java.lang.String r0 = r6.readString()
                java.lang.String[] r2 = r6.createStringArray()
                r4.a((java.lang.String) r0, (java.lang.String[]) r2)
                r0 = r1
                goto L_0x0009
            L_0x0096:
                java.lang.String r0 = "com.google.android.gms.games.internal.IRoomServiceCallbacks"
                r6.enforceInterface(r0)
                java.lang.String r0 = r6.readString()
                java.lang.String[] r2 = r6.createStringArray()
                r4.b(r0, r2)
                r0 = r1
                goto L_0x0009
            L_0x00a9:
                java.lang.String r0 = "com.google.android.gms.games.internal.IRoomServiceCallbacks"
                r6.enforceInterface(r0)
                java.lang.String r0 = r6.readString()
                java.lang.String[] r2 = r6.createStringArray()
                r4.c(r0, r2)
                r0 = r1
                goto L_0x0009
            L_0x00bc:
                java.lang.String r0 = "com.google.android.gms.games.internal.IRoomServiceCallbacks"
                r6.enforceInterface(r0)
                java.lang.String r0 = r6.readString()
                java.lang.String[] r2 = r6.createStringArray()
                r4.d(r0, r2)
                r0 = r1
                goto L_0x0009
            L_0x00cf:
                java.lang.String r0 = "com.google.android.gms.games.internal.IRoomServiceCallbacks"
                r6.enforceInterface(r0)
                java.lang.String r0 = r6.readString()
                java.lang.String[] r2 = r6.createStringArray()
                r4.e(r0, r2)
                r0 = r1
                goto L_0x0009
            L_0x00e2:
                java.lang.String r0 = "com.google.android.gms.games.internal.IRoomServiceCallbacks"
                r6.enforceInterface(r0)
                java.lang.String r0 = r6.readString()
                java.lang.String[] r2 = r6.createStringArray()
                r4.f(r0, r2)
                r0 = r1
                goto L_0x0009
            L_0x00f5:
                java.lang.String r0 = "com.google.android.gms.games.internal.IRoomServiceCallbacks"
                r6.enforceInterface(r0)
                java.lang.String r0 = r6.readString()
                r4.onP2PConnected(r0)
                r0 = r1
                goto L_0x0009
            L_0x0104:
                java.lang.String r0 = "com.google.android.gms.games.internal.IRoomServiceCallbacks"
                r6.enforceInterface(r0)
                java.lang.String r0 = r6.readString()
                r4.onP2PDisconnected(r0)
                r0 = r1
                goto L_0x0009
            L_0x0113:
                java.lang.String r0 = "com.google.android.gms.games.internal.IRoomServiceCallbacks"
                r6.enforceInterface(r0)
                r4.hE()
                r0 = r1
                goto L_0x0009
            L_0x011e:
                java.lang.String r0 = "com.google.android.gms.games.internal.IRoomServiceCallbacks"
                r6.enforceInterface(r0)
                java.lang.String r0 = r6.readString()
                java.lang.String[] r2 = r6.createStringArray()
                r4.g(r0, r2)
                r0 = r1
                goto L_0x0009
            L_0x0131:
                java.lang.String r0 = "com.google.android.gms.games.internal.IRoomServiceCallbacks"
                r6.enforceInterface(r0)
                java.lang.String r0 = r6.readString()
                r4.bl(r0)
                r0 = r1
                goto L_0x0009
            L_0x0140:
                java.lang.String r0 = "com.google.android.gms.games.internal.IRoomServiceCallbacks"
                r6.enforceInterface(r0)
                java.lang.String r0 = r6.readString()
                r4.bm(r0)
                r0 = r1
                goto L_0x0009
            L_0x014f:
                java.lang.String r0 = "com.google.android.gms.games.internal.IRoomServiceCallbacks"
                r6.enforceInterface(r0)
                int r0 = r6.readInt()
                r4.ck(r0)
                r0 = r1
                goto L_0x0009
            L_0x015e:
                java.lang.String r0 = "com.google.android.gms.games.internal.IRoomServiceCallbacks"
                r6.enforceInterface(r0)
                android.os.IBinder r0 = r6.readStrongBinder()
                r4.al(r0)
                r0 = r1
                goto L_0x0009
            L_0x016d:
                java.lang.String r2 = "com.google.android.gms.games.internal.IRoomServiceCallbacks"
                r6.enforceInterface(r2)
                int r2 = r6.readInt()
                if (r2 == 0) goto L_0x017e
                com.google.android.gms.games.internal.ConnectionInfoCreator r0 = com.google.android.gms.games.internal.ConnectionInfo.CREATOR
                com.google.android.gms.games.internal.ConnectionInfo r0 = r0.createFromParcel(r6)
            L_0x017e:
                r4.a(r0)
                r0 = r1
                goto L_0x0009
            L_0x0184:
                java.lang.String r0 = "com.google.android.gms.games.internal.IRoomServiceCallbacks"
                r6.enforceInterface(r0)
                r4.hF()
                r0 = r1
                goto L_0x0009
            L_0x018f:
                java.lang.String r2 = "com.google.android.gms.games.internal.IRoomServiceCallbacks"
                r6.enforceInterface(r2)
                int r2 = r6.readInt()
                if (r2 == 0) goto L_0x01a2
                android.os.Parcelable$Creator r0 = android.os.ParcelFileDescriptor.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r6)
                android.os.ParcelFileDescriptor r0 = (android.os.ParcelFileDescriptor) r0
            L_0x01a2:
                int r2 = r6.readInt()
                r4.a((android.os.ParcelFileDescriptor) r0, (int) r2)
                r0 = r1
                goto L_0x0009
            L_0x01ac:
                java.lang.String r0 = "com.google.android.gms.games.internal.IRoomServiceCallbacks"
                r6.enforceInterface(r0)
                java.lang.String r0 = r6.readString()
                int r2 = r6.readInt()
                r4.t(r0, r2)
                r0 = r1
                goto L_0x0009
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.games.internal.IRoomServiceCallbacks.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    void a(ParcelFileDescriptor parcelFileDescriptor, int i) throws RemoteException;

    void a(ConnectionInfo connectionInfo) throws RemoteException;

    void a(String str, byte[] bArr, int i) throws RemoteException;

    void a(String str, String[] strArr) throws RemoteException;

    void al(IBinder iBinder) throws RemoteException;

    void b(String str, String[] strArr) throws RemoteException;

    void bg(String str) throws RemoteException;

    void bh(String str) throws RemoteException;

    void bi(String str) throws RemoteException;

    void bj(String str) throws RemoteException;

    void bk(String str) throws RemoteException;

    void bl(String str) throws RemoteException;

    void bm(String str) throws RemoteException;

    void c(int i, int i2, String str) throws RemoteException;

    void c(String str, String[] strArr) throws RemoteException;

    void ck(int i) throws RemoteException;

    void d(String str, String[] strArr) throws RemoteException;

    void e(String str, String[] strArr) throws RemoteException;

    void f(String str, String[] strArr) throws RemoteException;

    void g(String str, String[] strArr) throws RemoteException;

    void hE() throws RemoteException;

    void hF() throws RemoteException;

    void onP2PConnected(String str) throws RemoteException;

    void onP2PDisconnected(String str) throws RemoteException;

    void t(String str, int i) throws RemoteException;
}
