package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.location.Location;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.b;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.List;

public interface jf extends IInterface {

    public static abstract class a extends Binder implements jf {

        /* renamed from: com.google.android.gms.internal.jf$a$a  reason: collision with other inner class name */
        private static class C0068a implements jf {
            private IBinder ko;

            C0068a(IBinder iBinder) {
                this.ko = iBinder;
            }

            public void a(long j, boolean z, PendingIntent pendingIntent) throws RemoteException {
                int i = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeLong(j);
                    if (!z) {
                        i = 0;
                    }
                    obtain.writeInt(i);
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ko.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(PendingIntent pendingIntent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
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

            public void a(PendingIntent pendingIntent, je jeVar, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(jeVar != null ? jeVar.asBinder() : null);
                    obtain.writeString(str);
                    this.ko.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(Location location, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (location != null) {
                        obtain.writeInt(1);
                        location.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    this.ko.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(je jeVar, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeStrongBinder(jeVar != null ? jeVar.asBinder() : null);
                    obtain.writeString(str);
                    this.ko.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(jl jlVar, kb kbVar, PendingIntent pendingIntent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (jlVar != null) {
                        obtain.writeInt(1);
                        jlVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (kbVar != null) {
                        obtain.writeInt(1);
                        kbVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ko.transact(48, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(jn jnVar, kb kbVar, jz jzVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (jnVar != null) {
                        obtain.writeInt(1);
                        jnVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (kbVar != null) {
                        obtain.writeInt(1);
                        kbVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(jzVar != null ? jzVar.asBinder() : null);
                    this.ko.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(jp jpVar, kb kbVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (jpVar != null) {
                        obtain.writeInt(1);
                        jpVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (kbVar != null) {
                        obtain.writeInt(1);
                        kbVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ko.transact(25, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void a(jr jrVar, kb kbVar, PendingIntent pendingIntent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (jrVar != null) {
                        obtain.writeInt(1);
                        jrVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (kbVar != null) {
                        obtain.writeInt(1);
                        kbVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ko.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(jv jvVar, kb kbVar, jz jzVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (jvVar != null) {
                        obtain.writeInt(1);
                        jvVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (kbVar != null) {
                        obtain.writeInt(1);
                        kbVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(jzVar != null ? jzVar.asBinder() : null);
                    this.ko.transact(46, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(kb kbVar, PendingIntent pendingIntent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (kbVar != null) {
                        obtain.writeInt(1);
                        kbVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ko.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(LocationRequest locationRequest, PendingIntent pendingIntent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (locationRequest != null) {
                        obtain.writeInt(1);
                        locationRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
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

            public void a(LocationRequest locationRequest, com.google.android.gms.location.a aVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (locationRequest != null) {
                        obtain.writeInt(1);
                        locationRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    this.ko.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(LocationRequest locationRequest, com.google.android.gms.location.a aVar, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (locationRequest != null) {
                        obtain.writeInt(1);
                        locationRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    obtain.writeString(str);
                    this.ko.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(com.google.android.gms.location.a aVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    this.ko.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(LatLng latLng, jn jnVar, kb kbVar, jz jzVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (latLng != null) {
                        obtain.writeInt(1);
                        latLng.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (jnVar != null) {
                        obtain.writeInt(1);
                        jnVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (kbVar != null) {
                        obtain.writeInt(1);
                        kbVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(jzVar != null ? jzVar.asBinder() : null);
                    this.ko.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(LatLngBounds latLngBounds, int i, jn jnVar, kb kbVar, jz jzVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (latLngBounds != null) {
                        obtain.writeInt(1);
                        latLngBounds.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    if (jnVar != null) {
                        obtain.writeInt(1);
                        jnVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (kbVar != null) {
                        obtain.writeInt(1);
                        kbVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(jzVar != null ? jzVar.asBinder() : null);
                    this.ko.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(LatLngBounds latLngBounds, int i, String str, jn jnVar, kb kbVar, jz jzVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (latLngBounds != null) {
                        obtain.writeInt(1);
                        latLngBounds.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (jnVar != null) {
                        obtain.writeInt(1);
                        jnVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (kbVar != null) {
                        obtain.writeInt(1);
                        kbVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(jzVar != null ? jzVar.asBinder() : null);
                    this.ko.transact(47, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(String str, kb kbVar, jz jzVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeString(str);
                    if (kbVar != null) {
                        obtain.writeInt(1);
                        kbVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(jzVar != null ? jzVar.asBinder() : null);
                    this.ko.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(String str, LatLngBounds latLngBounds, jn jnVar, kb kbVar, jz jzVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeString(str);
                    if (latLngBounds != null) {
                        obtain.writeInt(1);
                        latLngBounds.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (jnVar != null) {
                        obtain.writeInt(1);
                        jnVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (kbVar != null) {
                        obtain.writeInt(1);
                        kbVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(jzVar != null ? jzVar.asBinder() : null);
                    this.ko.transact(45, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(String str, List<String> list, List<jx> list2, kb kbVar, jz jzVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeString(str);
                    obtain.writeStringList(list);
                    obtain.writeTypedList(list2);
                    if (kbVar != null) {
                        obtain.writeInt(1);
                        kbVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(jzVar != null ? jzVar.asBinder() : null);
                    this.ko.transact(50, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(List<ji> list, PendingIntent pendingIntent, je jeVar, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeTypedList(list);
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(jeVar != null ? jeVar.asBinder() : null);
                    obtain.writeString(str);
                    this.ko.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(String[] strArr, je jeVar, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeStringArray(strArr);
                    obtain.writeStrongBinder(jeVar != null ? jeVar.asBinder() : null);
                    obtain.writeString(str);
                    this.ko.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.ko;
            }

            public void b(kb kbVar, PendingIntent pendingIntent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (kbVar != null) {
                        obtain.writeInt(1);
                        kbVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ko.transact(49, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(String str, kb kbVar, jz jzVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeString(str);
                    if (kbVar != null) {
                        obtain.writeInt(1);
                        kbVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(jzVar != null ? jzVar.asBinder() : null);
                    this.ko.transact(42, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Location bo(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeString(str);
                    this.ko.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Location) Location.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public b bp(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    obtain.writeString(str);
                    this.ko.transact(34, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? b.CREATOR.bs(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Location iR() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    this.ko.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Location) Location.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder iS() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    this.ko.transact(51, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readStrongBinder();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void removeActivityUpdates(PendingIntent callbackIntent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (callbackIntent != null) {
                        obtain.writeInt(1);
                        callbackIntent.writeToParcel(obtain, 0);
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

            public void setMockLocation(Location location) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (location != null) {
                        obtain.writeInt(1);
                        location.writeToParcel(obtain, 0);
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

            public void setMockMode(boolean isMockMode) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                    if (isMockMode) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.ko.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static jf as(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof jf)) ? new C0068a(iBinder) : (jf) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: com.google.android.gms.internal.kb} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: com.google.android.gms.location.LocationRequest} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: com.google.android.gms.internal.kb} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v9, resolved type: com.google.android.gms.internal.kb} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v11, resolved type: com.google.android.gms.internal.kb} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v13, resolved type: com.google.android.gms.internal.kb} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v15, resolved type: com.google.android.gms.internal.kb} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v17, resolved type: com.google.android.gms.internal.kb} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v20, resolved type: com.google.android.gms.location.LocationRequest} */
        /* JADX WARNING: type inference failed for: r5v0 */
        /* JADX WARNING: type inference failed for: r5v22 */
        /* JADX WARNING: type inference failed for: r5v23 */
        /* JADX WARNING: type inference failed for: r5v24 */
        /* JADX WARNING: type inference failed for: r5v25 */
        /* JADX WARNING: type inference failed for: r5v26 */
        /* JADX WARNING: type inference failed for: r5v27 */
        /* JADX WARNING: type inference failed for: r5v28 */
        /* JADX WARNING: type inference failed for: r5v29 */
        /* JADX WARNING: type inference failed for: r5v30 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r9, android.os.Parcel r10, android.os.Parcel r11, int r12) throws android.os.RemoteException {
            /*
                r8 = this;
                r0 = 0
                r7 = 1
                r5 = 0
                switch(r9) {
                    case 1: goto L_0x0011;
                    case 2: goto L_0x003f;
                    case 3: goto L_0x0067;
                    case 4: goto L_0x0083;
                    case 5: goto L_0x009c;
                    case 6: goto L_0x00c6;
                    case 7: goto L_0x00e3;
                    case 8: goto L_0x00fe;
                    case 9: goto L_0x011f;
                    case 10: goto L_0x014b;
                    case 11: goto L_0x0160;
                    case 12: goto L_0x017d;
                    case 13: goto L_0x0191;
                    case 14: goto L_0x01ae;
                    case 15: goto L_0x0238;
                    case 16: goto L_0x025d;
                    case 17: goto L_0x029a;
                    case 18: goto L_0x0320;
                    case 19: goto L_0x035b;
                    case 20: goto L_0x0463;
                    case 21: goto L_0x0488;
                    case 25: goto L_0x04a7;
                    case 26: goto L_0x04cb;
                    case 34: goto L_0x04ec;
                    case 42: goto L_0x02c9;
                    case 45: goto L_0x03ee;
                    case 46: goto L_0x0432;
                    case 47: goto L_0x01f2;
                    case 48: goto L_0x0387;
                    case 49: goto L_0x03c2;
                    case 50: goto L_0x02ee;
                    case 51: goto L_0x050b;
                    case 1598968902: goto L_0x000b;
                    default: goto L_0x0006;
                }
            L_0x0006:
                boolean r7 = super.onTransact(r9, r10, r11, r12)
            L_0x000a:
                return r7
            L_0x000b:
                java.lang.String r0 = "com.google.android.gms.location.internal.IGoogleLocationManagerService"
                r11.writeString(r0)
                goto L_0x000a
            L_0x0011:
                java.lang.String r0 = "com.google.android.gms.location.internal.IGoogleLocationManagerService"
                r10.enforceInterface(r0)
                com.google.android.gms.internal.jj r0 = com.google.android.gms.internal.ji.CREATOR
                java.util.ArrayList r1 = r10.createTypedArrayList(r0)
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x003d
                android.os.Parcelable$Creator r0 = android.app.PendingIntent.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r10)
                android.app.PendingIntent r0 = (android.app.PendingIntent) r0
            L_0x002a:
                android.os.IBinder r2 = r10.readStrongBinder()
                com.google.android.gms.internal.je r2 = com.google.android.gms.internal.je.a.ar(r2)
                java.lang.String r3 = r10.readString()
                r8.a((java.util.List<com.google.android.gms.internal.ji>) r1, (android.app.PendingIntent) r0, (com.google.android.gms.internal.je) r2, (java.lang.String) r3)
                r11.writeNoException()
                goto L_0x000a
            L_0x003d:
                r0 = r5
                goto L_0x002a
            L_0x003f:
                java.lang.String r0 = "com.google.android.gms.location.internal.IGoogleLocationManagerService"
                r10.enforceInterface(r0)
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x0065
                android.os.Parcelable$Creator r0 = android.app.PendingIntent.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r10)
                android.app.PendingIntent r0 = (android.app.PendingIntent) r0
            L_0x0052:
                android.os.IBinder r1 = r10.readStrongBinder()
                com.google.android.gms.internal.je r1 = com.google.android.gms.internal.je.a.ar(r1)
                java.lang.String r2 = r10.readString()
                r8.a((android.app.PendingIntent) r0, (com.google.android.gms.internal.je) r1, (java.lang.String) r2)
                r11.writeNoException()
                goto L_0x000a
            L_0x0065:
                r0 = r5
                goto L_0x0052
            L_0x0067:
                java.lang.String r0 = "com.google.android.gms.location.internal.IGoogleLocationManagerService"
                r10.enforceInterface(r0)
                java.lang.String[] r0 = r10.createStringArray()
                android.os.IBinder r1 = r10.readStrongBinder()
                com.google.android.gms.internal.je r1 = com.google.android.gms.internal.je.a.ar(r1)
                java.lang.String r2 = r10.readString()
                r8.a((java.lang.String[]) r0, (com.google.android.gms.internal.je) r1, (java.lang.String) r2)
                r11.writeNoException()
                goto L_0x000a
            L_0x0083:
                java.lang.String r0 = "com.google.android.gms.location.internal.IGoogleLocationManagerService"
                r10.enforceInterface(r0)
                android.os.IBinder r0 = r10.readStrongBinder()
                com.google.android.gms.internal.je r0 = com.google.android.gms.internal.je.a.ar(r0)
                java.lang.String r1 = r10.readString()
                r8.a((com.google.android.gms.internal.je) r0, (java.lang.String) r1)
                r11.writeNoException()
                goto L_0x000a
            L_0x009c:
                java.lang.String r1 = "com.google.android.gms.location.internal.IGoogleLocationManagerService"
                r10.enforceInterface(r1)
                long r2 = r10.readLong()
                int r1 = r10.readInt()
                if (r1 == 0) goto L_0x00c2
                r1 = r7
            L_0x00ac:
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x00c4
                android.os.Parcelable$Creator r0 = android.app.PendingIntent.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r10)
                android.app.PendingIntent r0 = (android.app.PendingIntent) r0
            L_0x00ba:
                r8.a((long) r2, (boolean) r1, (android.app.PendingIntent) r0)
                r11.writeNoException()
                goto L_0x000a
            L_0x00c2:
                r1 = r0
                goto L_0x00ac
            L_0x00c4:
                r0 = r5
                goto L_0x00ba
            L_0x00c6:
                java.lang.String r0 = "com.google.android.gms.location.internal.IGoogleLocationManagerService"
                r10.enforceInterface(r0)
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x00e1
                android.os.Parcelable$Creator r0 = android.app.PendingIntent.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r10)
                android.app.PendingIntent r0 = (android.app.PendingIntent) r0
            L_0x00d9:
                r8.removeActivityUpdates(r0)
                r11.writeNoException()
                goto L_0x000a
            L_0x00e1:
                r0 = r5
                goto L_0x00d9
            L_0x00e3:
                java.lang.String r1 = "com.google.android.gms.location.internal.IGoogleLocationManagerService"
                r10.enforceInterface(r1)
                android.location.Location r1 = r8.iR()
                r11.writeNoException()
                if (r1 == 0) goto L_0x00f9
                r11.writeInt(r7)
                r1.writeToParcel(r11, r7)
                goto L_0x000a
            L_0x00f9:
                r11.writeInt(r0)
                goto L_0x000a
            L_0x00fe:
                java.lang.String r0 = "com.google.android.gms.location.internal.IGoogleLocationManagerService"
                r10.enforceInterface(r0)
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x010f
                com.google.android.gms.location.LocationRequestCreator r0 = com.google.android.gms.location.LocationRequest.CREATOR
                com.google.android.gms.location.LocationRequest r5 = r0.createFromParcel((android.os.Parcel) r10)
            L_0x010f:
                android.os.IBinder r0 = r10.readStrongBinder()
                com.google.android.gms.location.a r0 = com.google.android.gms.location.a.C0083a.aq(r0)
                r8.a((com.google.android.gms.location.LocationRequest) r5, (com.google.android.gms.location.a) r0)
                r11.writeNoException()
                goto L_0x000a
            L_0x011f:
                java.lang.String r0 = "com.google.android.gms.location.internal.IGoogleLocationManagerService"
                r10.enforceInterface(r0)
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x0147
                com.google.android.gms.location.LocationRequestCreator r0 = com.google.android.gms.location.LocationRequest.CREATOR
                com.google.android.gms.location.LocationRequest r0 = r0.createFromParcel((android.os.Parcel) r10)
                r1 = r0
            L_0x0131:
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x0149
                android.os.Parcelable$Creator r0 = android.app.PendingIntent.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r10)
                android.app.PendingIntent r0 = (android.app.PendingIntent) r0
            L_0x013f:
                r8.a((com.google.android.gms.location.LocationRequest) r1, (android.app.PendingIntent) r0)
                r11.writeNoException()
                goto L_0x000a
            L_0x0147:
                r1 = r5
                goto L_0x0131
            L_0x0149:
                r0 = r5
                goto L_0x013f
            L_0x014b:
                java.lang.String r0 = "com.google.android.gms.location.internal.IGoogleLocationManagerService"
                r10.enforceInterface(r0)
                android.os.IBinder r0 = r10.readStrongBinder()
                com.google.android.gms.location.a r0 = com.google.android.gms.location.a.C0083a.aq(r0)
                r8.a((com.google.android.gms.location.a) r0)
                r11.writeNoException()
                goto L_0x000a
            L_0x0160:
                java.lang.String r0 = "com.google.android.gms.location.internal.IGoogleLocationManagerService"
                r10.enforceInterface(r0)
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x017b
                android.os.Parcelable$Creator r0 = android.app.PendingIntent.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r10)
                android.app.PendingIntent r0 = (android.app.PendingIntent) r0
            L_0x0173:
                r8.a((android.app.PendingIntent) r0)
                r11.writeNoException()
                goto L_0x000a
            L_0x017b:
                r0 = r5
                goto L_0x0173
            L_0x017d:
                java.lang.String r1 = "com.google.android.gms.location.internal.IGoogleLocationManagerService"
                r10.enforceInterface(r1)
                int r1 = r10.readInt()
                if (r1 == 0) goto L_0x0189
                r0 = r7
            L_0x0189:
                r8.setMockMode(r0)
                r11.writeNoException()
                goto L_0x000a
            L_0x0191:
                java.lang.String r0 = "com.google.android.gms.location.internal.IGoogleLocationManagerService"
                r10.enforceInterface(r0)
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x01ac
                android.os.Parcelable$Creator r0 = android.location.Location.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r10)
                android.location.Location r0 = (android.location.Location) r0
            L_0x01a4:
                r8.setMockLocation(r0)
                r11.writeNoException()
                goto L_0x000a
            L_0x01ac:
                r0 = r5
                goto L_0x01a4
            L_0x01ae:
                java.lang.String r0 = "com.google.android.gms.location.internal.IGoogleLocationManagerService"
                r10.enforceInterface(r0)
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x01ec
                com.google.android.gms.maps.model.LatLngBoundsCreator r0 = com.google.android.gms.maps.model.LatLngBounds.CREATOR
                com.google.android.gms.maps.model.LatLngBounds r1 = r0.createFromParcel((android.os.Parcel) r10)
            L_0x01bf:
                int r2 = r10.readInt()
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x01ee
                com.google.android.gms.internal.jo r0 = com.google.android.gms.internal.jn.CREATOR
                com.google.android.gms.internal.jn r3 = r0.createFromParcel(r10)
            L_0x01cf:
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x01f0
                com.google.android.gms.internal.kc r0 = com.google.android.gms.internal.kb.CREATOR
                com.google.android.gms.internal.kb r4 = r0.createFromParcel(r10)
            L_0x01db:
                android.os.IBinder r0 = r10.readStrongBinder()
                com.google.android.gms.internal.jz r5 = com.google.android.gms.internal.jz.a.au(r0)
                r0 = r8
                r0.a((com.google.android.gms.maps.model.LatLngBounds) r1, (int) r2, (com.google.android.gms.internal.jn) r3, (com.google.android.gms.internal.kb) r4, (com.google.android.gms.internal.jz) r5)
                r11.writeNoException()
                goto L_0x000a
            L_0x01ec:
                r1 = r5
                goto L_0x01bf
            L_0x01ee:
                r3 = r5
                goto L_0x01cf
            L_0x01f0:
                r4 = r5
                goto L_0x01db
            L_0x01f2:
                java.lang.String r0 = "com.google.android.gms.location.internal.IGoogleLocationManagerService"
                r10.enforceInterface(r0)
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x0234
                com.google.android.gms.maps.model.LatLngBoundsCreator r0 = com.google.android.gms.maps.model.LatLngBounds.CREATOR
                com.google.android.gms.maps.model.LatLngBounds r1 = r0.createFromParcel((android.os.Parcel) r10)
            L_0x0203:
                int r2 = r10.readInt()
                java.lang.String r3 = r10.readString()
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x0236
                com.google.android.gms.internal.jo r0 = com.google.android.gms.internal.jn.CREATOR
                com.google.android.gms.internal.jn r4 = r0.createFromParcel(r10)
            L_0x0217:
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x0223
                com.google.android.gms.internal.kc r0 = com.google.android.gms.internal.kb.CREATOR
                com.google.android.gms.internal.kb r5 = r0.createFromParcel(r10)
            L_0x0223:
                android.os.IBinder r0 = r10.readStrongBinder()
                com.google.android.gms.internal.jz r6 = com.google.android.gms.internal.jz.a.au(r0)
                r0 = r8
                r0.a(r1, r2, r3, r4, r5, r6)
                r11.writeNoException()
                goto L_0x000a
            L_0x0234:
                r1 = r5
                goto L_0x0203
            L_0x0236:
                r4 = r5
                goto L_0x0217
            L_0x0238:
                java.lang.String r0 = "com.google.android.gms.location.internal.IGoogleLocationManagerService"
                r10.enforceInterface(r0)
                java.lang.String r0 = r10.readString()
                int r1 = r10.readInt()
                if (r1 == 0) goto L_0x024d
                com.google.android.gms.internal.kc r1 = com.google.android.gms.internal.kb.CREATOR
                com.google.android.gms.internal.kb r5 = r1.createFromParcel(r10)
            L_0x024d:
                android.os.IBinder r1 = r10.readStrongBinder()
                com.google.android.gms.internal.jz r1 = com.google.android.gms.internal.jz.a.au(r1)
                r8.a((java.lang.String) r0, (com.google.android.gms.internal.kb) r5, (com.google.android.gms.internal.jz) r1)
                r11.writeNoException()
                goto L_0x000a
            L_0x025d:
                java.lang.String r0 = "com.google.android.gms.location.internal.IGoogleLocationManagerService"
                r10.enforceInterface(r0)
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x0296
                com.google.android.gms.maps.model.LatLngCreator r0 = com.google.android.gms.maps.model.LatLng.CREATOR
                com.google.android.gms.maps.model.LatLng r0 = r0.createFromParcel((android.os.Parcel) r10)
            L_0x026e:
                int r1 = r10.readInt()
                if (r1 == 0) goto L_0x0298
                com.google.android.gms.internal.jo r1 = com.google.android.gms.internal.jn.CREATOR
                com.google.android.gms.internal.jn r1 = r1.createFromParcel(r10)
            L_0x027a:
                int r2 = r10.readInt()
                if (r2 == 0) goto L_0x0286
                com.google.android.gms.internal.kc r2 = com.google.android.gms.internal.kb.CREATOR
                com.google.android.gms.internal.kb r5 = r2.createFromParcel(r10)
            L_0x0286:
                android.os.IBinder r2 = r10.readStrongBinder()
                com.google.android.gms.internal.jz r2 = com.google.android.gms.internal.jz.a.au(r2)
                r8.a((com.google.android.gms.maps.model.LatLng) r0, (com.google.android.gms.internal.jn) r1, (com.google.android.gms.internal.kb) r5, (com.google.android.gms.internal.jz) r2)
                r11.writeNoException()
                goto L_0x000a
            L_0x0296:
                r0 = r5
                goto L_0x026e
            L_0x0298:
                r1 = r5
                goto L_0x027a
            L_0x029a:
                java.lang.String r0 = "com.google.android.gms.location.internal.IGoogleLocationManagerService"
                r10.enforceInterface(r0)
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x02c7
                com.google.android.gms.internal.jo r0 = com.google.android.gms.internal.jn.CREATOR
                com.google.android.gms.internal.jn r0 = r0.createFromParcel(r10)
            L_0x02ab:
                int r1 = r10.readInt()
                if (r1 == 0) goto L_0x02b7
                com.google.android.gms.internal.kc r1 = com.google.android.gms.internal.kb.CREATOR
                com.google.android.gms.internal.kb r5 = r1.createFromParcel(r10)
            L_0x02b7:
                android.os.IBinder r1 = r10.readStrongBinder()
                com.google.android.gms.internal.jz r1 = com.google.android.gms.internal.jz.a.au(r1)
                r8.a((com.google.android.gms.internal.jn) r0, (com.google.android.gms.internal.kb) r5, (com.google.android.gms.internal.jz) r1)
                r11.writeNoException()
                goto L_0x000a
            L_0x02c7:
                r0 = r5
                goto L_0x02ab
            L_0x02c9:
                java.lang.String r0 = "com.google.android.gms.location.internal.IGoogleLocationManagerService"
                r10.enforceInterface(r0)
                java.lang.String r0 = r10.readString()
                int r1 = r10.readInt()
                if (r1 == 0) goto L_0x02de
                com.google.android.gms.internal.kc r1 = com.google.android.gms.internal.kb.CREATOR
                com.google.android.gms.internal.kb r5 = r1.createFromParcel(r10)
            L_0x02de:
                android.os.IBinder r1 = r10.readStrongBinder()
                com.google.android.gms.internal.jz r1 = com.google.android.gms.internal.jz.a.au(r1)
                r8.b(r0, r5, r1)
                r11.writeNoException()
                goto L_0x000a
            L_0x02ee:
                java.lang.String r0 = "com.google.android.gms.location.internal.IGoogleLocationManagerService"
                r10.enforceInterface(r0)
                java.lang.String r1 = r10.readString()
                java.util.ArrayList r2 = r10.createStringArrayList()
                com.google.android.gms.internal.jy r0 = com.google.android.gms.internal.jx.CREATOR
                java.util.ArrayList r3 = r10.createTypedArrayList(r0)
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x031e
                com.google.android.gms.internal.kc r0 = com.google.android.gms.internal.kb.CREATOR
                com.google.android.gms.internal.kb r4 = r0.createFromParcel(r10)
            L_0x030d:
                android.os.IBinder r0 = r10.readStrongBinder()
                com.google.android.gms.internal.jz r5 = com.google.android.gms.internal.jz.a.au(r0)
                r0 = r8
                r0.a((java.lang.String) r1, (java.util.List<java.lang.String>) r2, (java.util.List<com.google.android.gms.internal.jx>) r3, (com.google.android.gms.internal.kb) r4, (com.google.android.gms.internal.jz) r5)
                r11.writeNoException()
                goto L_0x000a
            L_0x031e:
                r4 = r5
                goto L_0x030d
            L_0x0320:
                java.lang.String r0 = "com.google.android.gms.location.internal.IGoogleLocationManagerService"
                r10.enforceInterface(r0)
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x0355
                com.google.android.gms.internal.js r0 = com.google.android.gms.internal.jr.CREATOR
                com.google.android.gms.internal.jr r0 = r0.createFromParcel(r10)
                r1 = r0
            L_0x0332:
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x0357
                com.google.android.gms.internal.kc r0 = com.google.android.gms.internal.kb.CREATOR
                com.google.android.gms.internal.kb r0 = r0.createFromParcel(r10)
                r2 = r0
            L_0x033f:
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x0359
                android.os.Parcelable$Creator r0 = android.app.PendingIntent.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r10)
                android.app.PendingIntent r0 = (android.app.PendingIntent) r0
            L_0x034d:
                r8.a((com.google.android.gms.internal.jr) r1, (com.google.android.gms.internal.kb) r2, (android.app.PendingIntent) r0)
                r11.writeNoException()
                goto L_0x000a
            L_0x0355:
                r1 = r5
                goto L_0x0332
            L_0x0357:
                r2 = r5
                goto L_0x033f
            L_0x0359:
                r0 = r5
                goto L_0x034d
            L_0x035b:
                java.lang.String r0 = "com.google.android.gms.location.internal.IGoogleLocationManagerService"
                r10.enforceInterface(r0)
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x0383
                com.google.android.gms.internal.kc r0 = com.google.android.gms.internal.kb.CREATOR
                com.google.android.gms.internal.kb r0 = r0.createFromParcel(r10)
                r1 = r0
            L_0x036d:
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x0385
                android.os.Parcelable$Creator r0 = android.app.PendingIntent.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r10)
                android.app.PendingIntent r0 = (android.app.PendingIntent) r0
            L_0x037b:
                r8.a((com.google.android.gms.internal.kb) r1, (android.app.PendingIntent) r0)
                r11.writeNoException()
                goto L_0x000a
            L_0x0383:
                r1 = r5
                goto L_0x036d
            L_0x0385:
                r0 = r5
                goto L_0x037b
            L_0x0387:
                java.lang.String r0 = "com.google.android.gms.location.internal.IGoogleLocationManagerService"
                r10.enforceInterface(r0)
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x03bc
                com.google.android.gms.internal.jm r0 = com.google.android.gms.internal.jl.CREATOR
                com.google.android.gms.internal.jl r0 = r0.createFromParcel(r10)
                r1 = r0
            L_0x0399:
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x03be
                com.google.android.gms.internal.kc r0 = com.google.android.gms.internal.kb.CREATOR
                com.google.android.gms.internal.kb r0 = r0.createFromParcel(r10)
                r2 = r0
            L_0x03a6:
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x03c0
                android.os.Parcelable$Creator r0 = android.app.PendingIntent.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r10)
                android.app.PendingIntent r0 = (android.app.PendingIntent) r0
            L_0x03b4:
                r8.a((com.google.android.gms.internal.jl) r1, (com.google.android.gms.internal.kb) r2, (android.app.PendingIntent) r0)
                r11.writeNoException()
                goto L_0x000a
            L_0x03bc:
                r1 = r5
                goto L_0x0399
            L_0x03be:
                r2 = r5
                goto L_0x03a6
            L_0x03c0:
                r0 = r5
                goto L_0x03b4
            L_0x03c2:
                java.lang.String r0 = "com.google.android.gms.location.internal.IGoogleLocationManagerService"
                r10.enforceInterface(r0)
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x03ea
                com.google.android.gms.internal.kc r0 = com.google.android.gms.internal.kb.CREATOR
                com.google.android.gms.internal.kb r0 = r0.createFromParcel(r10)
                r1 = r0
            L_0x03d4:
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x03ec
                android.os.Parcelable$Creator r0 = android.app.PendingIntent.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r10)
                android.app.PendingIntent r0 = (android.app.PendingIntent) r0
            L_0x03e2:
                r8.b(r1, r0)
                r11.writeNoException()
                goto L_0x000a
            L_0x03ea:
                r1 = r5
                goto L_0x03d4
            L_0x03ec:
                r0 = r5
                goto L_0x03e2
            L_0x03ee:
                java.lang.String r0 = "com.google.android.gms.location.internal.IGoogleLocationManagerService"
                r10.enforceInterface(r0)
                java.lang.String r1 = r10.readString()
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x042c
                com.google.android.gms.maps.model.LatLngBoundsCreator r0 = com.google.android.gms.maps.model.LatLngBounds.CREATOR
                com.google.android.gms.maps.model.LatLngBounds r2 = r0.createFromParcel((android.os.Parcel) r10)
            L_0x0403:
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x042e
                com.google.android.gms.internal.jo r0 = com.google.android.gms.internal.jn.CREATOR
                com.google.android.gms.internal.jn r3 = r0.createFromParcel(r10)
            L_0x040f:
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x0430
                com.google.android.gms.internal.kc r0 = com.google.android.gms.internal.kb.CREATOR
                com.google.android.gms.internal.kb r4 = r0.createFromParcel(r10)
            L_0x041b:
                android.os.IBinder r0 = r10.readStrongBinder()
                com.google.android.gms.internal.jz r5 = com.google.android.gms.internal.jz.a.au(r0)
                r0 = r8
                r0.a((java.lang.String) r1, (com.google.android.gms.maps.model.LatLngBounds) r2, (com.google.android.gms.internal.jn) r3, (com.google.android.gms.internal.kb) r4, (com.google.android.gms.internal.jz) r5)
                r11.writeNoException()
                goto L_0x000a
            L_0x042c:
                r2 = r5
                goto L_0x0403
            L_0x042e:
                r3 = r5
                goto L_0x040f
            L_0x0430:
                r4 = r5
                goto L_0x041b
            L_0x0432:
                java.lang.String r0 = "com.google.android.gms.location.internal.IGoogleLocationManagerService"
                r10.enforceInterface(r0)
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x0461
                android.os.Parcelable$Creator<com.google.android.gms.internal.jv> r0 = com.google.android.gms.internal.jv.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r10)
                com.google.android.gms.internal.jv r0 = (com.google.android.gms.internal.jv) r0
            L_0x0445:
                int r1 = r10.readInt()
                if (r1 == 0) goto L_0x0451
                com.google.android.gms.internal.kc r1 = com.google.android.gms.internal.kb.CREATOR
                com.google.android.gms.internal.kb r5 = r1.createFromParcel(r10)
            L_0x0451:
                android.os.IBinder r1 = r10.readStrongBinder()
                com.google.android.gms.internal.jz r1 = com.google.android.gms.internal.jz.a.au(r1)
                r8.a((com.google.android.gms.internal.jv) r0, (com.google.android.gms.internal.kb) r5, (com.google.android.gms.internal.jz) r1)
                r11.writeNoException()
                goto L_0x000a
            L_0x0461:
                r0 = r5
                goto L_0x0445
            L_0x0463:
                java.lang.String r0 = "com.google.android.gms.location.internal.IGoogleLocationManagerService"
                r10.enforceInterface(r0)
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x0474
                com.google.android.gms.location.LocationRequestCreator r0 = com.google.android.gms.location.LocationRequest.CREATOR
                com.google.android.gms.location.LocationRequest r5 = r0.createFromParcel((android.os.Parcel) r10)
            L_0x0474:
                android.os.IBinder r0 = r10.readStrongBinder()
                com.google.android.gms.location.a r0 = com.google.android.gms.location.a.C0083a.aq(r0)
                java.lang.String r1 = r10.readString()
                r8.a((com.google.android.gms.location.LocationRequest) r5, (com.google.android.gms.location.a) r0, (java.lang.String) r1)
                r11.writeNoException()
                goto L_0x000a
            L_0x0488:
                java.lang.String r1 = "com.google.android.gms.location.internal.IGoogleLocationManagerService"
                r10.enforceInterface(r1)
                java.lang.String r1 = r10.readString()
                android.location.Location r1 = r8.bo(r1)
                r11.writeNoException()
                if (r1 == 0) goto L_0x04a2
                r11.writeInt(r7)
                r1.writeToParcel(r11, r7)
                goto L_0x000a
            L_0x04a2:
                r11.writeInt(r0)
                goto L_0x000a
            L_0x04a7:
                java.lang.String r0 = "com.google.android.gms.location.internal.IGoogleLocationManagerService"
                r10.enforceInterface(r0)
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x04c9
                com.google.android.gms.internal.jq r0 = com.google.android.gms.internal.jp.CREATOR
                com.google.android.gms.internal.jp r0 = r0.createFromParcel(r10)
            L_0x04b8:
                int r1 = r10.readInt()
                if (r1 == 0) goto L_0x04c4
                com.google.android.gms.internal.kc r1 = com.google.android.gms.internal.kb.CREATOR
                com.google.android.gms.internal.kb r5 = r1.createFromParcel(r10)
            L_0x04c4:
                r8.a((com.google.android.gms.internal.jp) r0, (com.google.android.gms.internal.kb) r5)
                goto L_0x000a
            L_0x04c9:
                r0 = r5
                goto L_0x04b8
            L_0x04cb:
                java.lang.String r0 = "com.google.android.gms.location.internal.IGoogleLocationManagerService"
                r10.enforceInterface(r0)
                int r0 = r10.readInt()
                if (r0 == 0) goto L_0x04ea
                android.os.Parcelable$Creator r0 = android.location.Location.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r10)
                android.location.Location r0 = (android.location.Location) r0
            L_0x04de:
                int r1 = r10.readInt()
                r8.a((android.location.Location) r0, (int) r1)
                r11.writeNoException()
                goto L_0x000a
            L_0x04ea:
                r0 = r5
                goto L_0x04de
            L_0x04ec:
                java.lang.String r1 = "com.google.android.gms.location.internal.IGoogleLocationManagerService"
                r10.enforceInterface(r1)
                java.lang.String r1 = r10.readString()
                com.google.android.gms.location.b r1 = r8.bp(r1)
                r11.writeNoException()
                if (r1 == 0) goto L_0x0506
                r11.writeInt(r7)
                r1.writeToParcel(r11, r7)
                goto L_0x000a
            L_0x0506:
                r11.writeInt(r0)
                goto L_0x000a
            L_0x050b:
                java.lang.String r0 = "com.google.android.gms.location.internal.IGoogleLocationManagerService"
                r10.enforceInterface(r0)
                android.os.IBinder r0 = r8.iS()
                r11.writeNoException()
                r11.writeStrongBinder(r0)
                goto L_0x000a
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.jf.a.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    void a(long j, boolean z, PendingIntent pendingIntent) throws RemoteException;

    void a(PendingIntent pendingIntent) throws RemoteException;

    void a(PendingIntent pendingIntent, je jeVar, String str) throws RemoteException;

    void a(Location location, int i) throws RemoteException;

    void a(je jeVar, String str) throws RemoteException;

    void a(jl jlVar, kb kbVar, PendingIntent pendingIntent) throws RemoteException;

    void a(jn jnVar, kb kbVar, jz jzVar) throws RemoteException;

    void a(jp jpVar, kb kbVar) throws RemoteException;

    void a(jr jrVar, kb kbVar, PendingIntent pendingIntent) throws RemoteException;

    void a(jv jvVar, kb kbVar, jz jzVar) throws RemoteException;

    void a(kb kbVar, PendingIntent pendingIntent) throws RemoteException;

    void a(LocationRequest locationRequest, PendingIntent pendingIntent) throws RemoteException;

    void a(LocationRequest locationRequest, com.google.android.gms.location.a aVar) throws RemoteException;

    void a(LocationRequest locationRequest, com.google.android.gms.location.a aVar, String str) throws RemoteException;

    void a(com.google.android.gms.location.a aVar) throws RemoteException;

    void a(LatLng latLng, jn jnVar, kb kbVar, jz jzVar) throws RemoteException;

    void a(LatLngBounds latLngBounds, int i, jn jnVar, kb kbVar, jz jzVar) throws RemoteException;

    void a(LatLngBounds latLngBounds, int i, String str, jn jnVar, kb kbVar, jz jzVar) throws RemoteException;

    void a(String str, kb kbVar, jz jzVar) throws RemoteException;

    void a(String str, LatLngBounds latLngBounds, jn jnVar, kb kbVar, jz jzVar) throws RemoteException;

    void a(String str, List<String> list, List<jx> list2, kb kbVar, jz jzVar) throws RemoteException;

    void a(List<ji> list, PendingIntent pendingIntent, je jeVar, String str) throws RemoteException;

    void a(String[] strArr, je jeVar, String str) throws RemoteException;

    void b(kb kbVar, PendingIntent pendingIntent) throws RemoteException;

    void b(String str, kb kbVar, jz jzVar) throws RemoteException;

    Location bo(String str) throws RemoteException;

    b bp(String str) throws RemoteException;

    Location iR() throws RemoteException;

    IBinder iS() throws RemoteException;

    void removeActivityUpdates(PendingIntent pendingIntent) throws RemoteException;

    void setMockLocation(Location location) throws RemoteException;

    void setMockMode(boolean z) throws RemoteException;
}
