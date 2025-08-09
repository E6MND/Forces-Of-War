package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.d;

public interface bv extends IInterface {

    public static abstract class a extends Binder implements bv {

        /* renamed from: com.google.android.gms.internal.bv$a$a  reason: collision with other inner class name */
        private static class C0039a implements bv {
            private IBinder ko;

            C0039a(IBinder iBinder) {
                this.ko = iBinder;
            }

            public void a(d dVar, aj ajVar, String str, bw bwVar) throws RemoteException {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    if (ajVar != null) {
                        obtain.writeInt(1);
                        ajVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    if (bwVar != null) {
                        iBinder = bwVar.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.ko.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(d dVar, aj ajVar, String str, String str2, bw bwVar) throws RemoteException {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    if (ajVar != null) {
                        obtain.writeInt(1);
                        ajVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (bwVar != null) {
                        iBinder = bwVar.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.ko.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(d dVar, am amVar, aj ajVar, String str, bw bwVar) throws RemoteException {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    if (amVar != null) {
                        obtain.writeInt(1);
                        amVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (ajVar != null) {
                        obtain.writeInt(1);
                        ajVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    if (bwVar != null) {
                        iBinder = bwVar.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.ko.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(d dVar, am amVar, aj ajVar, String str, String str2, bw bwVar) throws RemoteException {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    if (amVar != null) {
                        obtain.writeInt(1);
                        amVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (ajVar != null) {
                        obtain.writeInt(1);
                        ajVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (bwVar != null) {
                        iBinder = bwVar.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.ko.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.ko;
            }

            public void destroy() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.ko.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public d getView() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.ko.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return d.a.ag(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void pause() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.ko.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void resume() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.ko.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void showInterstitial() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.ko.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public a() {
            attachInterface(this, "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
        }

        public static bv j(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof bv)) ? new C0039a(iBinder) : (bv) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v17, resolved type: com.google.android.gms.internal.aj} */
        /* JADX WARNING: type inference failed for: r0v0 */
        /* JADX WARNING: type inference failed for: r0v21, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r0v31 */
        /* JADX WARNING: type inference failed for: r0v32 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r9, android.os.Parcel r10, android.os.Parcel r11, int r12) throws android.os.RemoteException {
            /*
                r8 = this;
                r0 = 0
                r7 = 1
                switch(r9) {
                    case 1: goto L_0x0011;
                    case 2: goto L_0x004f;
                    case 3: goto L_0x0066;
                    case 4: goto L_0x0094;
                    case 5: goto L_0x00a2;
                    case 6: goto L_0x00b0;
                    case 7: goto L_0x00f3;
                    case 8: goto L_0x0128;
                    case 9: goto L_0x0136;
                    case 1598968902: goto L_0x000a;
                    default: goto L_0x0005;
                }
            L_0x0005:
                boolean r0 = super.onTransact(r9, r10, r11, r12)
            L_0x0009:
                return r0
            L_0x000a:
                java.lang.String r0 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r11.writeString(r0)
                r0 = r7
                goto L_0x0009
            L_0x0011:
                java.lang.String r1 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r10.enforceInterface(r1)
                android.os.IBinder r1 = r10.readStrongBinder()
                com.google.android.gms.dynamic.d r1 = com.google.android.gms.dynamic.d.a.ag(r1)
                int r2 = r10.readInt()
                if (r2 == 0) goto L_0x004b
                com.google.android.gms.internal.an r2 = com.google.android.gms.internal.am.CREATOR
                com.google.android.gms.internal.am r2 = r2.createFromParcel(r10)
            L_0x002a:
                int r3 = r10.readInt()
                if (r3 == 0) goto L_0x004d
                com.google.android.gms.internal.ak r0 = com.google.android.gms.internal.aj.CREATOR
                com.google.android.gms.internal.aj r3 = r0.createFromParcel(r10)
            L_0x0036:
                java.lang.String r4 = r10.readString()
                android.os.IBinder r0 = r10.readStrongBinder()
                com.google.android.gms.internal.bw r5 = com.google.android.gms.internal.bw.a.k(r0)
                r0 = r8
                r0.a((com.google.android.gms.dynamic.d) r1, (com.google.android.gms.internal.am) r2, (com.google.android.gms.internal.aj) r3, (java.lang.String) r4, (com.google.android.gms.internal.bw) r5)
                r11.writeNoException()
                r0 = r7
                goto L_0x0009
            L_0x004b:
                r2 = r0
                goto L_0x002a
            L_0x004d:
                r3 = r0
                goto L_0x0036
            L_0x004f:
                java.lang.String r1 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r10.enforceInterface(r1)
                com.google.android.gms.dynamic.d r1 = r8.getView()
                r11.writeNoException()
                if (r1 == 0) goto L_0x0061
                android.os.IBinder r0 = r1.asBinder()
            L_0x0061:
                r11.writeStrongBinder(r0)
                r0 = r7
                goto L_0x0009
            L_0x0066:
                java.lang.String r1 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r10.enforceInterface(r1)
                android.os.IBinder r1 = r10.readStrongBinder()
                com.google.android.gms.dynamic.d r1 = com.google.android.gms.dynamic.d.a.ag(r1)
                int r2 = r10.readInt()
                if (r2 == 0) goto L_0x007f
                com.google.android.gms.internal.ak r0 = com.google.android.gms.internal.aj.CREATOR
                com.google.android.gms.internal.aj r0 = r0.createFromParcel(r10)
            L_0x007f:
                java.lang.String r2 = r10.readString()
                android.os.IBinder r3 = r10.readStrongBinder()
                com.google.android.gms.internal.bw r3 = com.google.android.gms.internal.bw.a.k(r3)
                r8.a(r1, r0, r2, r3)
                r11.writeNoException()
                r0 = r7
                goto L_0x0009
            L_0x0094:
                java.lang.String r0 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r10.enforceInterface(r0)
                r8.showInterstitial()
                r11.writeNoException()
                r0 = r7
                goto L_0x0009
            L_0x00a2:
                java.lang.String r0 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r10.enforceInterface(r0)
                r8.destroy()
                r11.writeNoException()
                r0 = r7
                goto L_0x0009
            L_0x00b0:
                java.lang.String r1 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r10.enforceInterface(r1)
                android.os.IBinder r1 = r10.readStrongBinder()
                com.google.android.gms.dynamic.d r1 = com.google.android.gms.dynamic.d.a.ag(r1)
                int r2 = r10.readInt()
                if (r2 == 0) goto L_0x00ef
                com.google.android.gms.internal.an r2 = com.google.android.gms.internal.am.CREATOR
                com.google.android.gms.internal.am r2 = r2.createFromParcel(r10)
            L_0x00c9:
                int r3 = r10.readInt()
                if (r3 == 0) goto L_0x00f1
                com.google.android.gms.internal.ak r0 = com.google.android.gms.internal.aj.CREATOR
                com.google.android.gms.internal.aj r3 = r0.createFromParcel(r10)
            L_0x00d5:
                java.lang.String r4 = r10.readString()
                java.lang.String r5 = r10.readString()
                android.os.IBinder r0 = r10.readStrongBinder()
                com.google.android.gms.internal.bw r6 = com.google.android.gms.internal.bw.a.k(r0)
                r0 = r8
                r0.a(r1, r2, r3, r4, r5, r6)
                r11.writeNoException()
                r0 = r7
                goto L_0x0009
            L_0x00ef:
                r2 = r0
                goto L_0x00c9
            L_0x00f1:
                r3 = r0
                goto L_0x00d5
            L_0x00f3:
                java.lang.String r1 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r10.enforceInterface(r1)
                android.os.IBinder r1 = r10.readStrongBinder()
                com.google.android.gms.dynamic.d r1 = com.google.android.gms.dynamic.d.a.ag(r1)
                int r2 = r10.readInt()
                if (r2 == 0) goto L_0x0126
                com.google.android.gms.internal.ak r0 = com.google.android.gms.internal.aj.CREATOR
                com.google.android.gms.internal.aj r2 = r0.createFromParcel(r10)
            L_0x010c:
                java.lang.String r3 = r10.readString()
                java.lang.String r4 = r10.readString()
                android.os.IBinder r0 = r10.readStrongBinder()
                com.google.android.gms.internal.bw r5 = com.google.android.gms.internal.bw.a.k(r0)
                r0 = r8
                r0.a((com.google.android.gms.dynamic.d) r1, (com.google.android.gms.internal.aj) r2, (java.lang.String) r3, (java.lang.String) r4, (com.google.android.gms.internal.bw) r5)
                r11.writeNoException()
                r0 = r7
                goto L_0x0009
            L_0x0126:
                r2 = r0
                goto L_0x010c
            L_0x0128:
                java.lang.String r0 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r10.enforceInterface(r0)
                r8.pause()
                r11.writeNoException()
                r0 = r7
                goto L_0x0009
            L_0x0136:
                java.lang.String r0 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r10.enforceInterface(r0)
                r8.resume()
                r11.writeNoException()
                r0 = r7
                goto L_0x0009
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.bv.a.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    void a(d dVar, aj ajVar, String str, bw bwVar) throws RemoteException;

    void a(d dVar, aj ajVar, String str, String str2, bw bwVar) throws RemoteException;

    void a(d dVar, am amVar, aj ajVar, String str, bw bwVar) throws RemoteException;

    void a(d dVar, am amVar, aj ajVar, String str, String str2, bw bwVar) throws RemoteException;

    void destroy() throws RemoteException;

    d getView() throws RemoteException;

    void pause() throws RemoteException;

    void resume() throws RemoteException;

    void showInterstitial() throws RemoteException;
}
