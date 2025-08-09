package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.c;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.internal.ll;
import com.google.android.gms.internal.lm;
import com.google.android.gms.wallet.fragment.WalletFragmentOptions;

public interface lo extends IInterface {

    public static abstract class a extends Binder implements lo {

        /* renamed from: com.google.android.gms.internal.lo$a$a  reason: collision with other inner class name */
        private static class C0076a implements lo {
            private IBinder ko;

            C0076a(IBinder iBinder) {
                this.ko = iBinder;
            }

            public ll a(d dVar, c cVar, WalletFragmentOptions walletFragmentOptions, lm lmVar) throws RemoteException {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.wallet.internal.IWalletDynamiteCreator");
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    obtain.writeStrongBinder(cVar != null ? cVar.asBinder() : null);
                    if (walletFragmentOptions != null) {
                        obtain.writeInt(1);
                        walletFragmentOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (lmVar != null) {
                        iBinder = lmVar.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.ko.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return ll.a.bo(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.ko;
            }
        }

        public static lo br(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.wallet.internal.IWalletDynamiteCreator");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof lo)) ? new C0076a(iBinder) : (lo) queryLocalInterface;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            IBinder iBinder = null;
            switch (code) {
                case 1:
                    data.enforceInterface("com.google.android.gms.wallet.internal.IWalletDynamiteCreator");
                    ll a = a(d.a.ag(data.readStrongBinder()), c.a.af(data.readStrongBinder()), data.readInt() != 0 ? WalletFragmentOptions.CREATOR.createFromParcel(data) : null, lm.a.bp(data.readStrongBinder()));
                    reply.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    reply.writeStrongBinder(iBinder);
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.wallet.internal.IWalletDynamiteCreator");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    ll a(d dVar, c cVar, WalletFragmentOptions walletFragmentOptions, lm lmVar) throws RemoteException;
}
