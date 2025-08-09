package org.chromium.net;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import org.chromium.net.IRemoteAndroidKeyStoreCallbacks;

public interface IRemoteAndroidKeyStore extends IInterface {
    String getClientCertificateAlias() throws RemoteException;

    byte[] getDSAKeyParamQ(int i) throws RemoteException;

    byte[] getECKeyOrder(int i) throws RemoteException;

    byte[] getEncodedCertificateChain(String str) throws RemoteException;

    byte[] getPrivateKeyEncodedBytes(int i) throws RemoteException;

    int getPrivateKeyHandle(String str) throws RemoteException;

    int getPrivateKeyType(int i) throws RemoteException;

    byte[] getRSAKeyModulus(int i) throws RemoteException;

    byte[] rawSignDigestWithPrivateKey(int i, byte[] bArr) throws RemoteException;

    void releaseKey(int i) throws RemoteException;

    void setClientCallbacks(IRemoteAndroidKeyStoreCallbacks iRemoteAndroidKeyStoreCallbacks) throws RemoteException;

    public static abstract class Stub extends Binder implements IRemoteAndroidKeyStore {
        private static final String DESCRIPTOR = "org.chromium.net.IRemoteAndroidKeyStore";
        static final int TRANSACTION_getClientCertificateAlias = 1;
        static final int TRANSACTION_getDSAKeyParamQ = 7;
        static final int TRANSACTION_getECKeyOrder = 8;
        static final int TRANSACTION_getEncodedCertificateChain = 2;
        static final int TRANSACTION_getPrivateKeyEncodedBytes = 6;
        static final int TRANSACTION_getPrivateKeyHandle = 3;
        static final int TRANSACTION_getPrivateKeyType = 10;
        static final int TRANSACTION_getRSAKeyModulus = 5;
        static final int TRANSACTION_rawSignDigestWithPrivateKey = 9;
        static final int TRANSACTION_releaseKey = 11;
        static final int TRANSACTION_setClientCallbacks = 4;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRemoteAndroidKeyStore asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IRemoteAndroidKeyStore)) {
                return new Proxy(obj);
            }
            return (IRemoteAndroidKeyStore) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    String _result = getClientCertificateAlias();
                    reply.writeNoException();
                    reply.writeString(_result);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _result2 = getEncodedCertificateChain(data.readString());
                    reply.writeNoException();
                    reply.writeByteArray(_result2);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    int _result3 = getPrivateKeyHandle(data.readString());
                    reply.writeNoException();
                    reply.writeInt(_result3);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    setClientCallbacks(IRemoteAndroidKeyStoreCallbacks.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _result4 = getRSAKeyModulus(data.readInt());
                    reply.writeNoException();
                    reply.writeByteArray(_result4);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _result5 = getPrivateKeyEncodedBytes(data.readInt());
                    reply.writeNoException();
                    reply.writeByteArray(_result5);
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _result6 = getDSAKeyParamQ(data.readInt());
                    reply.writeNoException();
                    reply.writeByteArray(_result6);
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _result7 = getECKeyOrder(data.readInt());
                    reply.writeNoException();
                    reply.writeByteArray(_result7);
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _result8 = rawSignDigestWithPrivateKey(data.readInt(), data.createByteArray());
                    reply.writeNoException();
                    reply.writeByteArray(_result8);
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    int _result9 = getPrivateKeyType(data.readInt());
                    reply.writeNoException();
                    reply.writeInt(_result9);
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    releaseKey(data.readInt());
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IRemoteAndroidKeyStore {
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

            public String getClientCertificateAlias() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public byte[] getEncodedCertificateChain(String alias) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(alias);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                    return _reply.createByteArray();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getPrivateKeyHandle(String alias) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(alias);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void setClientCallbacks(IRemoteAndroidKeyStoreCallbacks callbacks) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public byte[] getRSAKeyModulus(int handle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(handle);
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                    return _reply.createByteArray();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public byte[] getPrivateKeyEncodedBytes(int handle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(handle);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                    return _reply.createByteArray();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public byte[] getDSAKeyParamQ(int handle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(handle);
                    this.mRemote.transact(7, _data, _reply, 0);
                    _reply.readException();
                    return _reply.createByteArray();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public byte[] getECKeyOrder(int handle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(handle);
                    this.mRemote.transact(8, _data, _reply, 0);
                    _reply.readException();
                    return _reply.createByteArray();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public byte[] rawSignDigestWithPrivateKey(int handle, byte[] message) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(handle);
                    _data.writeByteArray(message);
                    this.mRemote.transact(9, _data, _reply, 0);
                    _reply.readException();
                    return _reply.createByteArray();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getPrivateKeyType(int handle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(handle);
                    this.mRemote.transact(10, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void releaseKey(int handle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(handle);
                    this.mRemote.transact(11, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
