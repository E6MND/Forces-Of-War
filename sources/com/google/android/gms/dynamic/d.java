package com.google.android.gms.dynamic;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface d extends IInterface {

    public static abstract class a extends Binder implements d {

        /* renamed from: com.google.android.gms.dynamic.d$a$a  reason: collision with other inner class name */
        private static class C0033a implements d {
            private IBinder ko;

            C0033a(IBinder iBinder) {
                this.ko = iBinder;
            }

            public IBinder asBinder() {
                return this.ko;
            }
        }

        public a() {
            attachInterface(this, "com.google.android.gms.dynamic.IObjectWrapper");
        }

        public static d ag(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof d)) ? new C0033a(iBinder) : (d) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1598968902:
                    reply.writeString("com.google.android.gms.dynamic.IObjectWrapper");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }
}
