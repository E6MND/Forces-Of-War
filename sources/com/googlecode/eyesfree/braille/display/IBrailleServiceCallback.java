package com.googlecode.eyesfree.braille.display;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IBrailleServiceCallback extends IInterface {
    void onDisplayConnected(BrailleDisplayProperties brailleDisplayProperties) throws RemoteException;

    void onDisplayDisconnected() throws RemoteException;

    void onInput(BrailleInputEvent brailleInputEvent) throws RemoteException;

    public static abstract class Stub extends Binder implements IBrailleServiceCallback {
        private static final String DESCRIPTOR = "com.googlecode.eyesfree.braille.display.IBrailleServiceCallback";
        static final int TRANSACTION_onDisplayConnected = 1;
        static final int TRANSACTION_onDisplayDisconnected = 2;
        static final int TRANSACTION_onInput = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IBrailleServiceCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IBrailleServiceCallback)) {
                return new Proxy(obj);
            }
            return (IBrailleServiceCallback) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            BrailleInputEvent _arg0;
            BrailleDisplayProperties _arg02;
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg02 = BrailleDisplayProperties.CREATOR.createFromParcel(data);
                    } else {
                        _arg02 = null;
                    }
                    onDisplayConnected(_arg02);
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    onDisplayDisconnected();
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = BrailleInputEvent.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    onInput(_arg0);
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IBrailleServiceCallback {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            public void onDisplayConnected(BrailleDisplayProperties displayProperties) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (displayProperties != null) {
                        _data.writeInt(1);
                        displayProperties.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onDisplayDisconnected() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onInput(BrailleInputEvent inputEvent) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (inputEvent != null) {
                        _data.writeInt(1);
                        inputEvent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
