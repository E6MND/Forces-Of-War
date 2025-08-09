package com.google.android.gms.games.internal;

import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.games.GamesActivityResultCodes;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.games.internal.multiplayer.ZInvitationCluster;
import com.google.android.gms.games.internal.request.GameRequestCluster;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;

public interface IGamesService extends IInterface {

    public static abstract class Stub extends Binder implements IGamesService {

        private static class Proxy implements IGamesService {
            private IBinder ko;

            Proxy(IBinder remote) {
                this.ko = remote;
            }

            public void E(boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.ko.transact(5068, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void F(boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.ko.transact(12026, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int a(IGamesCallbacks iGamesCallbacks, byte[] bArr, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeByteArray(bArr);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.ko.transact(5033, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Intent a(int i, int i2, boolean z) throws RemoteException {
                int i3 = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (z) {
                        i3 = 1;
                    }
                    obtain.writeInt(i3);
                    this.ko.transact(9008, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Intent a(int i, byte[] bArr, int i2, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeInt(i);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    this.ko.transact(10012, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Intent a(ZInvitationCluster zInvitationCluster, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    if (zInvitationCluster != null) {
                        obtain.writeInt(1);
                        zInvitationCluster.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.ko.transact(10021, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Intent a(GameRequestCluster gameRequestCluster, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    if (gameRequestCluster != null) {
                        obtain.writeInt(1);
                        gameRequestCluster.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    this.ko.transact(10022, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Intent a(RoomEntity roomEntity, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    if (roomEntity != null) {
                        obtain.writeInt(1);
                        roomEntity.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    this.ko.transact(9011, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Intent a(String str, boolean z, boolean z2, int i) throws RemoteException {
                int i2 = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    if (!z2) {
                        i2 = 0;
                    }
                    obtain.writeInt(i2);
                    obtain.writeInt(i);
                    this.ko.transact(12001, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Intent a(int[] iArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeIntArray(iArr);
                    this.ko.transact(12030, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Intent a(ParticipantEntity[] participantEntityArr, String str, String str2, Uri uri, Uri uri2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeTypedArray(participantEntityArr, 0);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (uri != null) {
                        obtain.writeInt(1);
                        uri.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (uri2 != null) {
                        obtain.writeInt(1);
                        uri2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ko.transact(9031, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(long j, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    this.ko.transact(8019, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IBinder iBinder, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iBinder);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ko.transact(5005, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(Contents contents) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    if (contents != null) {
                        obtain.writeInt(1);
                        contents.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ko.transact(12019, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    this.ko.transact(5002, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeInt(i);
                    this.ko.transact(10016, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    this.ko.transact(10009, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, int i, int i2, boolean z, boolean z2) throws RemoteException {
                int i3 = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(z ? 1 : 0);
                    if (!z2) {
                        i3 = 0;
                    }
                    obtain.writeInt(i3);
                    this.ko.transact(5044, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, int i, int i2, String[] strArr, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeStringArray(strArr);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ko.transact(8004, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, int i, boolean z, boolean z2) throws RemoteException {
                int i2 = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    if (!z2) {
                        i2 = 0;
                    }
                    obtain.writeInt(i2);
                    this.ko.transact(5015, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, int i, int[] iArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeIntArray(iArr);
                    this.ko.transact(10018, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeLong(j);
                    this.ko.transact(5058, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, long j, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    this.ko.transact(8018, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, Bundle bundle, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.ko.transact(5021, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, IBinder iBinder, int i, String[] strArr, Bundle bundle, boolean z, long j) throws RemoteException {
                int i2 = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeInt(i);
                    obtain.writeStringArray(strArr);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!z) {
                        i2 = 0;
                    }
                    obtain.writeInt(i2);
                    obtain.writeLong(j);
                    this.ko.transact(5030, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, IBinder iBinder, String str, boolean z, long j) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeString(str);
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    this.ko.transact(5031, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    this.ko.transact(5014, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.ko.transact(10011, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, String str, int i, int i2, int i3, boolean z) throws RemoteException {
                int i4 = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    if (z) {
                        i4 = 1;
                    }
                    obtain.writeInt(i4);
                    this.ko.transact(5019, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, String str, int i, IBinder iBinder, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iBinder);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ko.transact(5025, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, String str, int i, boolean z) throws RemoteException {
                int i2 = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (z) {
                        i2 = 1;
                    }
                    obtain.writeInt(i2);
                    this.ko.transact(8023, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, String str, int i, boolean z, boolean z2) throws RemoteException {
                int i2 = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    if (!z2) {
                        i2 = 0;
                    }
                    obtain.writeInt(i2);
                    this.ko.transact(5045, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, String str, int i, boolean z, boolean z2, boolean z3, boolean z4) throws RemoteException {
                int i2 = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(z2 ? 1 : 0);
                    obtain.writeInt(z3 ? 1 : 0);
                    if (!z4) {
                        i2 = 0;
                    }
                    obtain.writeInt(i2);
                    this.ko.transact(GamesStatusCodes.STATUS_MATCH_ERROR_INACTIVE_MATCH, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, String str, int i, int[] iArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeIntArray(iArr);
                    this.ko.transact(10019, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, String str, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeLong(j);
                    this.ko.transact(5016, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, String str, long j, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeLong(j);
                    obtain.writeString(str2);
                    this.ko.transact(GamesStatusCodes.STATUS_INVALID_REAL_TIME_ROOM_ID, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, String str, IBinder iBinder, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iBinder);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ko.transact(5023, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, String str, SnapshotMetadataChange snapshotMetadataChange, Contents contents) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    if (snapshotMetadataChange != null) {
                        obtain.writeInt(1);
                        snapshotMetadataChange.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (contents != null) {
                        obtain.writeInt(1);
                        contents.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ko.transact(12007, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.ko.transact(5038, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, String str, String str2, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.ko.transact(GamesStatusCodes.STATUS_MILESTONE_CLAIM_FAILED, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, String str, String str2, int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    this.ko.transact(10010, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, String str, String str2, int i, int i2, int i3, boolean z) throws RemoteException {
                int i4 = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    if (z) {
                        i4 = 1;
                    }
                    obtain.writeInt(i4);
                    this.ko.transact(5039, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, String str, String str2, int i, boolean z, boolean z2) throws RemoteException {
                int i2 = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    if (!z2) {
                        i2 = 0;
                    }
                    obtain.writeInt(i2);
                    this.ko.transact(9028, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, String str, String str2, SnapshotMetadataChange snapshotMetadataChange, Contents contents) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (snapshotMetadataChange != null) {
                        obtain.writeInt(1);
                        snapshotMetadataChange.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (contents != null) {
                        obtain.writeInt(1);
                        contents.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ko.transact(12033, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, String str, String str2, boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.ko.transact(GamesStatusCodes.STATUS_MULTIPLAYER_ERROR_INVALID_MULTIPLAYER_TYPE, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, String str, String str2, int[] iArr, int i, boolean z) throws RemoteException {
                int i2 = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeIntArray(iArr);
                    obtain.writeInt(i);
                    if (z) {
                        i2 = 1;
                    }
                    obtain.writeInt(i2);
                    this.ko.transact(12015, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, String str, String str2, String[] strArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStringArray(strArr);
                    this.ko.transact(GamesActivityResultCodes.RESULT_INVALID_ROOM, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, String str, String str2, String[] strArr, boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStringArray(strArr);
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.ko.transact(12028, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, String str, boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.ko.transact(5054, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, String str, byte[] bArr, String str2, ParticipantResult[] participantResultArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeByteArray(bArr);
                    obtain.writeString(str2);
                    obtain.writeTypedArray(participantResultArr, 0);
                    this.ko.transact(8007, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, String str, byte[] bArr, ParticipantResult[] participantResultArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeByteArray(bArr);
                    obtain.writeTypedArray(participantResultArr, 0);
                    this.ko.transact(8008, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, String str, int[] iArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeIntArray(iArr);
                    this.ko.transact(8017, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, String str, String[] strArr, int i, byte[] bArr, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeStringArray(strArr);
                    obtain.writeInt(i);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(i2);
                    this.ko.transact(GamesActivityResultCodes.RESULT_LEFT_ROOM, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.ko.transact(GamesStatusCodes.STATUS_MULTIPLAYER_ERROR_NOT_TRUSTED_TESTER, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, boolean z, Bundle bundle) throws RemoteException {
                int i = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    if (!z) {
                        i = 0;
                    }
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ko.transact(5063, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, boolean z, String[] strArr) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    obtain.writeStringArray(strArr);
                    this.ko.transact(12031, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, int[] iArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeIntArray(iArr);
                    this.ko.transact(GamesStatusCodes.STATUS_QUEST_NOT_STARTED, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, int[] iArr, int i, boolean z) throws RemoteException {
                int i2 = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeIntArray(iArr);
                    obtain.writeInt(i);
                    if (z) {
                        i2 = 1;
                    }
                    obtain.writeInt(i2);
                    this.ko.transact(12010, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, String[] strArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeStringArray(strArr);
                    this.ko.transact(GamesActivityResultCodes.RESULT_NETWORK_FAILURE, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(IGamesCallbacks iGamesCallbacks, String[] strArr, boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeStringArray(strArr);
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.ko.transact(12029, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Intent aR(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(str);
                    this.ko.transact(9004, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Intent aU(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(str);
                    this.ko.transact(12034, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String aV(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(str);
                    this.ko.transact(5064, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String aW(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(str);
                    this.ko.transact(5035, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void aX(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(str);
                    this.ko.transact(5050, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int aY(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(str);
                    this.ko.transact(5060, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Uri aZ(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(str);
                    this.ko.transact(5066, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.ko;
            }

            public int b(byte[] bArr, String str, String[] strArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeByteArray(bArr);
                    obtain.writeString(str);
                    obtain.writeStringArray(strArr);
                    this.ko.transact(5034, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Intent b(int i, int i2, boolean z) throws RemoteException {
                int i3 = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (z) {
                        i3 = 1;
                    }
                    obtain.writeInt(i3);
                    this.ko.transact(9009, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(long j, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    this.ko.transact(8021, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(IGamesCallbacks iGamesCallbacks) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    this.ko.transact(5017, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(IGamesCallbacks iGamesCallbacks, int i, boolean z, boolean z2) throws RemoteException {
                int i2 = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    if (!z2) {
                        i2 = 0;
                    }
                    obtain.writeInt(i2);
                    this.ko.transact(5046, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(IGamesCallbacks iGamesCallbacks, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeLong(j);
                    this.ko.transact(8012, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(IGamesCallbacks iGamesCallbacks, long j, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    this.ko.transact(8020, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    this.ko.transact(5018, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(IGamesCallbacks iGamesCallbacks, String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.ko.transact(12023, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(IGamesCallbacks iGamesCallbacks, String str, int i, int i2, int i3, boolean z) throws RemoteException {
                int i4 = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    if (z) {
                        i4 = 1;
                    }
                    obtain.writeInt(i4);
                    this.ko.transact(5020, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(IGamesCallbacks iGamesCallbacks, String str, int i, IBinder iBinder, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iBinder);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ko.transact(GamesStatusCodes.STATUS_PARTICIPANT_NOT_CONNECTED, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(IGamesCallbacks iGamesCallbacks, String str, int i, boolean z) throws RemoteException {
                int i2 = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (z) {
                        i2 = 1;
                    }
                    obtain.writeInt(i2);
                    this.ko.transact(10017, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(IGamesCallbacks iGamesCallbacks, String str, int i, boolean z, boolean z2) throws RemoteException {
                int i2 = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    if (!z2) {
                        i2 = 0;
                    }
                    obtain.writeInt(i2);
                    this.ko.transact(5501, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(IGamesCallbacks iGamesCallbacks, String str, IBinder iBinder, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iBinder);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ko.transact(5024, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(IGamesCallbacks iGamesCallbacks, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.ko.transact(5041, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(IGamesCallbacks iGamesCallbacks, String str, String str2, int i, int i2, int i3, boolean z) throws RemoteException {
                int i4 = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    if (z) {
                        i4 = 1;
                    }
                    obtain.writeInt(i4);
                    this.ko.transact(5040, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(IGamesCallbacks iGamesCallbacks, String str, String str2, int i, boolean z, boolean z2) throws RemoteException {
                int i2 = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    if (!z2) {
                        i2 = 0;
                    }
                    obtain.writeInt(i2);
                    this.ko.transact(12018, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(IGamesCallbacks iGamesCallbacks, String str, String str2, boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.ko.transact(GamesStatusCodes.STATUS_MATCH_NOT_FOUND, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(IGamesCallbacks iGamesCallbacks, String str, boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.ko.transact(GamesStatusCodes.STATUS_MATCH_ERROR_INVALID_MATCH_STATE, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(IGamesCallbacks iGamesCallbacks, boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.ko.transact(GamesStatusCodes.STATUS_MATCH_ERROR_OUT_OF_DATE_VERSION, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(IGamesCallbacks iGamesCallbacks, String[] strArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeStringArray(strArr);
                    this.ko.transact(GamesActivityResultCodes.RESULT_SEND_REQUEST_FAILED, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(String str, String str2, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    this.ko.transact(5051, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void ba(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(str);
                    this.ko.transact(GamesStatusCodes.STATUS_QUEST_NO_LONGER_AVAILABLE, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public ParcelFileDescriptor bb(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(str);
                    this.ko.transact(9030, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void c(long j, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    this.ko.transact(GamesActivityResultCodes.RESULT_APP_MISCONFIGURED, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void c(IGamesCallbacks iGamesCallbacks) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    this.ko.transact(5022, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void c(IGamesCallbacks iGamesCallbacks, int i, boolean z, boolean z2) throws RemoteException {
                int i2 = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    if (!z2) {
                        i2 = 0;
                    }
                    obtain.writeInt(i2);
                    this.ko.transact(5048, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void c(IGamesCallbacks iGamesCallbacks, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeLong(j);
                    this.ko.transact(GamesActivityResultCodes.RESULT_RECONNECT_REQUIRED, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void c(IGamesCallbacks iGamesCallbacks, long j, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    this.ko.transact(GamesActivityResultCodes.RESULT_LICENSE_FAILED, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void c(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    this.ko.transact(5032, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void c(IGamesCallbacks iGamesCallbacks, String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.ko.transact(12024, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void c(IGamesCallbacks iGamesCallbacks, String str, int i, boolean z, boolean z2) throws RemoteException {
                int i2 = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    if (!z2) {
                        i2 = 0;
                    }
                    obtain.writeInt(i2);
                    this.ko.transact(9001, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void c(IGamesCallbacks iGamesCallbacks, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.ko.transact(8011, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void c(IGamesCallbacks iGamesCallbacks, String str, String str2, boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.ko.transact(12003, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void c(IGamesCallbacks iGamesCallbacks, String str, boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.ko.transact(GamesStatusCodes.STATUS_MATCH_ERROR_INVALID_MATCH_RESULTS, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void c(IGamesCallbacks iGamesCallbacks, boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.ko.transact(8027, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void c(IGamesCallbacks iGamesCallbacks, String[] strArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeStringArray(strArr);
                    this.ko.transact(10020, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void c(String str, String str2, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    this.ko.transact(8026, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void ch(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeInt(i);
                    this.ko.transact(5036, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void d(long j, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    this.ko.transact(12014, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void d(IGamesCallbacks iGamesCallbacks) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    this.ko.transact(5026, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void d(IGamesCallbacks iGamesCallbacks, int i, boolean z, boolean z2) throws RemoteException {
                int i2 = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    if (!z2) {
                        i2 = 0;
                    }
                    obtain.writeInt(i2);
                    this.ko.transact(GamesStatusCodes.STATUS_MULTIPLAYER_DISABLED, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void d(IGamesCallbacks iGamesCallbacks, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeLong(j);
                    this.ko.transact(12011, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void d(IGamesCallbacks iGamesCallbacks, long j, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    this.ko.transact(12013, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void d(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    this.ko.transact(5037, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void d(IGamesCallbacks iGamesCallbacks, String str, int i, boolean z, boolean z2) throws RemoteException {
                int i2 = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    if (!z2) {
                        i2 = 0;
                    }
                    obtain.writeInt(i2);
                    this.ko.transact(9020, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void d(IGamesCallbacks iGamesCallbacks, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.ko.transact(8015, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void d(IGamesCallbacks iGamesCallbacks, String str, boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.ko.transact(GamesStatusCodes.STATUS_MATCH_ERROR_ALREADY_REMATCHED, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void d(IGamesCallbacks iGamesCallbacks, boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.ko.transact(12002, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void e(IGamesCallbacks iGamesCallbacks) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    this.ko.transact(5027, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void e(IGamesCallbacks iGamesCallbacks, int i, boolean z, boolean z2) throws RemoteException {
                int i2 = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    if (!z2) {
                        i2 = 0;
                    }
                    obtain.writeInt(i2);
                    this.ko.transact(GamesStatusCodes.STATUS_MULTIPLAYER_ERROR_INVALID_OPERATION, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void e(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    this.ko.transact(5042, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void e(IGamesCallbacks iGamesCallbacks, String str, int i, boolean z, boolean z2) throws RemoteException {
                int i2 = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    if (!z2) {
                        i2 = 0;
                    }
                    obtain.writeInt(i2);
                    this.ko.transact(12021, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void e(IGamesCallbacks iGamesCallbacks, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.ko.transact(8016, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void e(IGamesCallbacks iGamesCallbacks, String str, boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.ko.transact(12006, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void e(IGamesCallbacks iGamesCallbacks, boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.ko.transact(12032, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle ea() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.ko.transact(5004, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void f(IGamesCallbacks iGamesCallbacks) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    this.ko.transact(5047, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void f(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    this.ko.transact(5043, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void f(IGamesCallbacks iGamesCallbacks, String str, int i, boolean z, boolean z2) throws RemoteException {
                int i2 = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    if (!z2) {
                        i2 = 0;
                    }
                    obtain.writeInt(i2);
                    this.ko.transact(12022, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void f(IGamesCallbacks iGamesCallbacks, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.ko.transact(12009, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void f(IGamesCallbacks iGamesCallbacks, boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.ko.transact(12016, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public ParcelFileDescriptor g(Uri uri) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    if (uri != null) {
                        obtain.writeInt(1);
                        uri.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ko.transact(GamesStatusCodes.STATUS_MATCH_ERROR_LOCALLY_MODIFIED, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void g(IGamesCallbacks iGamesCallbacks) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    this.ko.transact(5049, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void g(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    this.ko.transact(5052, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String gU() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.ko.transact(5007, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String gV() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.ko.transact(5012, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Intent gY() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.ko.transact(9003, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Intent gZ() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.ko.transact(9005, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public RoomEntity h(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException {
                RoomEntity roomEntity = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    this.ko.transact(5053, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        roomEntity = RoomEntity.CREATOR.createFromParcel(obtain2);
                    }
                    return roomEntity;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void h(IGamesCallbacks iGamesCallbacks) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    this.ko.transact(5056, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Intent ha() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.ko.transact(9006, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Intent hb() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.ko.transact(9007, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Intent hg() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.ko.transact(9010, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Intent hh() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.ko.transact(9012, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int hi() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.ko.transact(9019, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String hj() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.ko.transact(5003, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int hk() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.ko.transact(8024, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Intent hl() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.ko.transact(10015, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int hm() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.ko.transact(10013, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int hn() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.ko.transact(10023, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int ho() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.ko.transact(12035, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int hp() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.ko.transact(12036, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void hr() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.ko.transact(5006, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public DataHolder ht() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.ko.transact(5013, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? DataHolder.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean hu() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.ko.transact(5067, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public DataHolder hv() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.ko.transact(5502, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? DataHolder.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void hw() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.ko.transact(8022, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Intent hx() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.ko.transact(9013, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void hy() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.ko.transact(11002, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean hz() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    this.ko.transact(12025, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void i(IGamesCallbacks iGamesCallbacks) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    this.ko.transact(5062, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void i(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    this.ko.transact(5061, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void j(IGamesCallbacks iGamesCallbacks) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    this.ko.transact(11001, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void j(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    this.ko.transact(5057, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void k(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    this.ko.transact(GamesStatusCodes.STATUS_REAL_TIME_MESSAGE_SEND_FAILED, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void l(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    this.ko.transact(8005, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void l(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.ko.transact(12017, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    this.ko.transact(8006, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.ko.transact(5029, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void m(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.ko.transact(5065, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void n(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    this.ko.transact(8009, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void n(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.ko.transact(5028, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void n(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.ko.transact(8025, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void o(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    this.ko.transact(8010, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void p(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    this.ko.transact(8014, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void p(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.ko.transact(5055, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void q(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeLong(j);
                    this.ko.transact(5001, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void q(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    this.ko.transact(9002, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void q(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.ko.transact(10014, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void r(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeLong(j);
                    this.ko.transact(5059, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void r(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    this.ko.transact(12020, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void s(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeLong(j);
                    this.ko.transact(8013, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void s(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    this.ko.transact(12005, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void t(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeLong(j);
                    this.ko.transact(GamesActivityResultCodes.RESULT_SIGN_IN_FAILED, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void t(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    this.ko.transact(12027, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void u(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeLong(j);
                    this.ko.transact(12012, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void u(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeStrongBinder(iGamesCallbacks != null ? iGamesCallbacks.asBinder() : null);
                    obtain.writeString(str);
                    this.ko.transact(12008, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "com.google.android.gms.games.internal.IGamesService");
        }

        public static IGamesService aj(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.games.internal.IGamesService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IGamesService)) ? new Proxy(iBinder) : (IGamesService) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v7, resolved type: com.google.android.gms.games.internal.multiplayer.ZInvitationCluster} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v9, resolved type: com.google.android.gms.games.internal.request.GameRequestCluster} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v23, resolved type: android.os.Bundle} */
        /* JADX WARNING: type inference failed for: r6v0 */
        /* JADX WARNING: type inference failed for: r6v24 */
        /* JADX WARNING: type inference failed for: r6v31 */
        /* JADX WARNING: type inference failed for: r6v32 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r12, android.os.Parcel r13, android.os.Parcel r14, int r15) throws android.os.RemoteException {
            /*
                r11 = this;
                r6 = 0
                r7 = 0
                r10 = 1
                switch(r12) {
                    case 5001: goto L_0x0011;
                    case 5002: goto L_0x0021;
                    case 5003: goto L_0x0035;
                    case 5004: goto L_0x0045;
                    case 5005: goto L_0x005e;
                    case 5006: goto L_0x007e;
                    case 5007: goto L_0x008a;
                    case 5012: goto L_0x00c5;
                    case 5013: goto L_0x00d6;
                    case 5014: goto L_0x00f1;
                    case 5015: goto L_0x010a;
                    case 5016: goto L_0x0133;
                    case 5017: goto L_0x0150;
                    case 5018: goto L_0x0165;
                    case 5019: goto L_0x017e;
                    case 5020: goto L_0x01ad;
                    case 5021: goto L_0x01dc;
                    case 5022: goto L_0x0209;
                    case 5023: goto L_0x021e;
                    case 5024: goto L_0x024b;
                    case 5025: goto L_0x0278;
                    case 5026: goto L_0x02ab;
                    case 5027: goto L_0x02c0;
                    case 5028: goto L_0x02d5;
                    case 5029: goto L_0x02ea;
                    case 5030: goto L_0x0329;
                    case 5031: goto L_0x0365;
                    case 5032: goto L_0x0390;
                    case 5033: goto L_0x03a9;
                    case 5034: goto L_0x03ce;
                    case 5035: goto L_0x03eb;
                    case 5036: goto L_0x0400;
                    case 5037: goto L_0x0411;
                    case 5038: goto L_0x042a;
                    case 5039: goto L_0x0447;
                    case 5040: goto L_0x0478;
                    case 5041: goto L_0x04a9;
                    case 5042: goto L_0x04c6;
                    case 5043: goto L_0x04df;
                    case 5044: goto L_0x04f8;
                    case 5045: goto L_0x0528;
                    case 5046: goto L_0x0558;
                    case 5047: goto L_0x0581;
                    case 5048: goto L_0x0596;
                    case 5049: goto L_0x05bf;
                    case 5050: goto L_0x05d4;
                    case 5051: goto L_0x05e5;
                    case 5052: goto L_0x05fe;
                    case 5053: goto L_0x0617;
                    case 5054: goto L_0x0653;
                    case 5055: goto L_0x068c;
                    case 5056: goto L_0x06c9;
                    case 5057: goto L_0x06de;
                    case 5058: goto L_0x02ff;
                    case 5059: goto L_0x0318;
                    case 5060: goto L_0x063e;
                    case 5061: goto L_0x0673;
                    case 5062: goto L_0x06f7;
                    case 5063: goto L_0x070c;
                    case 5064: goto L_0x009b;
                    case 5065: goto L_0x00b0;
                    case 5066: goto L_0x0738;
                    case 5067: goto L_0x06a1;
                    case 5068: goto L_0x06b5;
                    case 5501: goto L_0x0757;
                    case 5502: goto L_0x0787;
                    case 6001: goto L_0x07a2;
                    case 6002: goto L_0x07be;
                    case 6003: goto L_0x07e2;
                    case 6004: goto L_0x080b;
                    case 6501: goto L_0x0834;
                    case 6502: goto L_0x0874;
                    case 6503: goto L_0x0894;
                    case 6504: goto L_0x08b0;
                    case 6505: goto L_0x08d0;
                    case 6506: goto L_0x08f0;
                    case 6507: goto L_0x0914;
                    case 7001: goto L_0x093f;
                    case 7002: goto L_0x0958;
                    case 7003: goto L_0x097a;
                    case 8001: goto L_0x09ad;
                    case 8002: goto L_0x09d3;
                    case 8003: goto L_0x09e4;
                    case 8004: goto L_0x09fd;
                    case 8005: goto L_0x0a30;
                    case 8006: goto L_0x0a49;
                    case 8007: goto L_0x0a62;
                    case 8008: goto L_0x0a8c;
                    case 8009: goto L_0x0ab1;
                    case 8010: goto L_0x0aca;
                    case 8011: goto L_0x0ae3;
                    case 8012: goto L_0x0b00;
                    case 8013: goto L_0x0b19;
                    case 8014: goto L_0x0b2a;
                    case 8015: goto L_0x0b69;
                    case 8016: goto L_0x0b86;
                    case 8017: goto L_0x0ba3;
                    case 8018: goto L_0x0bd9;
                    case 8019: goto L_0x0bf6;
                    case 8020: goto L_0x0c0b;
                    case 8021: goto L_0x0c28;
                    case 8022: goto L_0x0c3d;
                    case 8023: goto L_0x0c4a;
                    case 8024: goto L_0x0b43;
                    case 8025: goto L_0x0b54;
                    case 8026: goto L_0x0bc0;
                    case 8027: goto L_0x0c6e;
                    case 9001: goto L_0x0c8a;
                    case 9002: goto L_0x0cba;
                    case 9003: goto L_0x0cd3;
                    case 9004: goto L_0x0cee;
                    case 9005: goto L_0x0d0d;
                    case 9006: goto L_0x0d28;
                    case 9007: goto L_0x0d43;
                    case 9008: goto L_0x0d5e;
                    case 9009: goto L_0x0d8a;
                    case 9010: goto L_0x0db6;
                    case 9011: goto L_0x0dd1;
                    case 9012: goto L_0x0e00;
                    case 9013: goto L_0x0e1b;
                    case 9019: goto L_0x0e84;
                    case 9020: goto L_0x0e95;
                    case 9028: goto L_0x0ec5;
                    case 9030: goto L_0x0ef9;
                    case 9031: goto L_0x0e36;
                    case 10001: goto L_0x0f18;
                    case 10002: goto L_0x0f31;
                    case 10003: goto L_0x0f42;
                    case 10004: goto L_0x0f5f;
                    case 10005: goto L_0x0f74;
                    case 10006: goto L_0x0f9e;
                    case 10007: goto L_0x0fb7;
                    case 10008: goto L_0x0fd0;
                    case 10009: goto L_0x0ff1;
                    case 10010: goto L_0x1012;
                    case 10011: goto L_0x103c;
                    case 10012: goto L_0x1059;
                    case 10013: goto L_0x1084;
                    case 10014: goto L_0x10ec;
                    case 10015: goto L_0x10a6;
                    case 10016: goto L_0x1101;
                    case 10017: goto L_0x111a;
                    case 10018: goto L_0x116d;
                    case 10019: goto L_0x118a;
                    case 10020: goto L_0x11ab;
                    case 10021: goto L_0x113e;
                    case 10022: goto L_0x10c1;
                    case 10023: goto L_0x1095;
                    case 11001: goto L_0x11c4;
                    case 11002: goto L_0x11d9;
                    case 12001: goto L_0x11e6;
                    case 12002: goto L_0x121b;
                    case 12003: goto L_0x1237;
                    case 12005: goto L_0x1348;
                    case 12006: goto L_0x125b;
                    case 12007: goto L_0x127b;
                    case 12008: goto L_0x14a9;
                    case 12009: goto L_0x14c2;
                    case 12010: goto L_0x14df;
                    case 12011: goto L_0x157d;
                    case 12012: goto L_0x15b3;
                    case 12013: goto L_0x1596;
                    case 12014: goto L_0x15c4;
                    case 12015: goto L_0x1523;
                    case 12016: goto L_0x1458;
                    case 12017: goto L_0x1494;
                    case 12018: goto L_0x1617;
                    case 12019: goto L_0x12b3;
                    case 12020: goto L_0x12d0;
                    case 12021: goto L_0x139b;
                    case 12022: goto L_0x13cb;
                    case 12023: goto L_0x1361;
                    case 12024: goto L_0x137e;
                    case 12025: goto L_0x13fb;
                    case 12026: goto L_0x140f;
                    case 12027: goto L_0x1423;
                    case 12028: goto L_0x1552;
                    case 12029: goto L_0x1503;
                    case 12030: goto L_0x15d9;
                    case 12031: goto L_0x1474;
                    case 12032: goto L_0x143c;
                    case 12033: goto L_0x12e9;
                    case 12034: goto L_0x15f8;
                    case 12035: goto L_0x1326;
                    case 12036: goto L_0x1337;
                    case 1598968902: goto L_0x000b;
                    default: goto L_0x0006;
                }
            L_0x0006:
                boolean r10 = super.onTransact(r12, r13, r14, r15)
            L_0x000a:
                return r10
            L_0x000b:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r14.writeString(r0)
                goto L_0x000a
            L_0x0011:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                long r0 = r13.readLong()
                r11.q(r0)
                r14.writeNoException()
                goto L_0x000a
            L_0x0021:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                r11.a((com.google.android.gms.games.internal.IGamesCallbacks) r0)
                r14.writeNoException()
                goto L_0x000a
            L_0x0035:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                java.lang.String r0 = r11.hj()
                r14.writeNoException()
                r14.writeString(r0)
                goto L_0x000a
            L_0x0045:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.Bundle r0 = r11.ea()
                r14.writeNoException()
                if (r0 == 0) goto L_0x005a
                r14.writeInt(r10)
                r0.writeToParcel(r14, r10)
                goto L_0x000a
            L_0x005a:
                r14.writeInt(r7)
                goto L_0x000a
            L_0x005e:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r1 = r13.readStrongBinder()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x007c
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r13)
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x0075:
                r11.a((android.os.IBinder) r1, (android.os.Bundle) r0)
                r14.writeNoException()
                goto L_0x000a
            L_0x007c:
                r0 = r6
                goto L_0x0075
            L_0x007e:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                r11.hr()
                r14.writeNoException()
                goto L_0x000a
            L_0x008a:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                java.lang.String r0 = r11.gU()
                r14.writeNoException()
                r14.writeString(r0)
                goto L_0x000a
            L_0x009b:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                java.lang.String r0 = r13.readString()
                java.lang.String r0 = r11.aV(r0)
                r14.writeNoException()
                r14.writeString(r0)
                goto L_0x000a
            L_0x00b0:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                java.lang.String r0 = r13.readString()
                java.lang.String r1 = r13.readString()
                r11.m((java.lang.String) r0, (java.lang.String) r1)
                r14.writeNoException()
                goto L_0x000a
            L_0x00c5:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                java.lang.String r0 = r11.gV()
                r14.writeNoException()
                r14.writeString(r0)
                goto L_0x000a
            L_0x00d6:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                com.google.android.gms.common.data.DataHolder r0 = r11.ht()
                r14.writeNoException()
                if (r0 == 0) goto L_0x00ec
                r14.writeInt(r10)
                r0.writeToParcel(r14, r10)
                goto L_0x000a
            L_0x00ec:
                r14.writeInt(r7)
                goto L_0x000a
            L_0x00f1:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                r11.a((com.google.android.gms.games.internal.IGamesCallbacks) r0, (java.lang.String) r1)
                r14.writeNoException()
                goto L_0x000a
            L_0x010a:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r1 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                int r2 = r13.readInt()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0131
                r0 = r10
            L_0x0122:
                int r3 = r13.readInt()
                if (r3 == 0) goto L_0x0129
                r7 = r10
            L_0x0129:
                r11.a((com.google.android.gms.games.internal.IGamesCallbacks) r1, (int) r2, (boolean) r0, (boolean) r7)
                r14.writeNoException()
                goto L_0x000a
            L_0x0131:
                r0 = r7
                goto L_0x0122
            L_0x0133:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                long r2 = r13.readLong()
                r11.a((com.google.android.gms.games.internal.IGamesCallbacks) r0, (java.lang.String) r1, (long) r2)
                r14.writeNoException()
                goto L_0x000a
            L_0x0150:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                r11.b(r0)
                r14.writeNoException()
                goto L_0x000a
            L_0x0165:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                r11.b((com.google.android.gms.games.internal.IGamesCallbacks) r0, (java.lang.String) r1)
                r14.writeNoException()
                goto L_0x000a
            L_0x017e:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r1 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r2 = r13.readString()
                int r3 = r13.readInt()
                int r4 = r13.readInt()
                int r5 = r13.readInt()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x01ab
                r6 = r10
            L_0x01a2:
                r0 = r11
                r0.a((com.google.android.gms.games.internal.IGamesCallbacks) r1, (java.lang.String) r2, (int) r3, (int) r4, (int) r5, (boolean) r6)
                r14.writeNoException()
                goto L_0x000a
            L_0x01ab:
                r6 = r7
                goto L_0x01a2
            L_0x01ad:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r1 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r2 = r13.readString()
                int r3 = r13.readInt()
                int r4 = r13.readInt()
                int r5 = r13.readInt()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x01da
                r6 = r10
            L_0x01d1:
                r0 = r11
                r0.b((com.google.android.gms.games.internal.IGamesCallbacks) r1, (java.lang.String) r2, (int) r3, (int) r4, (int) r5, (boolean) r6)
                r14.writeNoException()
                goto L_0x000a
            L_0x01da:
                r6 = r7
                goto L_0x01d1
            L_0x01dc:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r1 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0207
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r13)
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x01f7:
                int r2 = r13.readInt()
                int r3 = r13.readInt()
                r11.a((com.google.android.gms.games.internal.IGamesCallbacks) r1, (android.os.Bundle) r0, (int) r2, (int) r3)
                r14.writeNoException()
                goto L_0x000a
            L_0x0207:
                r0 = r6
                goto L_0x01f7
            L_0x0209:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                r11.c(r0)
                r14.writeNoException()
                goto L_0x000a
            L_0x021e:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r1 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r2 = r13.readString()
                android.os.IBinder r3 = r13.readStrongBinder()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0249
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r13)
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x0241:
                r11.a((com.google.android.gms.games.internal.IGamesCallbacks) r1, (java.lang.String) r2, (android.os.IBinder) r3, (android.os.Bundle) r0)
                r14.writeNoException()
                goto L_0x000a
            L_0x0249:
                r0 = r6
                goto L_0x0241
            L_0x024b:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r1 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r2 = r13.readString()
                android.os.IBinder r3 = r13.readStrongBinder()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0276
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r13)
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x026e:
                r11.b((com.google.android.gms.games.internal.IGamesCallbacks) r1, (java.lang.String) r2, (android.os.IBinder) r3, (android.os.Bundle) r0)
                r14.writeNoException()
                goto L_0x000a
            L_0x0276:
                r0 = r6
                goto L_0x026e
            L_0x0278:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r1 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r2 = r13.readString()
                int r3 = r13.readInt()
                android.os.IBinder r4 = r13.readStrongBinder()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x02a9
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r13)
                android.os.Bundle r0 = (android.os.Bundle) r0
                r5 = r0
            L_0x02a0:
                r0 = r11
                r0.a((com.google.android.gms.games.internal.IGamesCallbacks) r1, (java.lang.String) r2, (int) r3, (android.os.IBinder) r4, (android.os.Bundle) r5)
                r14.writeNoException()
                goto L_0x000a
            L_0x02a9:
                r5 = r6
                goto L_0x02a0
            L_0x02ab:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                r11.d(r0)
                r14.writeNoException()
                goto L_0x000a
            L_0x02c0:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                r11.e(r0)
                r14.writeNoException()
                goto L_0x000a
            L_0x02d5:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                java.lang.String r0 = r13.readString()
                int r1 = r13.readInt()
                r11.n((java.lang.String) r0, (int) r1)
                r14.writeNoException()
                goto L_0x000a
            L_0x02ea:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                java.lang.String r0 = r13.readString()
                int r1 = r13.readInt()
                r11.m((java.lang.String) r0, (int) r1)
                r14.writeNoException()
                goto L_0x000a
            L_0x02ff:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                long r2 = r13.readLong()
                r11.a((com.google.android.gms.games.internal.IGamesCallbacks) r0, (long) r2)
                r14.writeNoException()
                goto L_0x000a
            L_0x0318:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                long r0 = r13.readLong()
                r11.r(r0)
                r14.writeNoException()
                goto L_0x000a
            L_0x0329:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r2 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                android.os.IBinder r3 = r13.readStrongBinder()
                int r4 = r13.readInt()
                java.lang.String[] r5 = r13.createStringArray()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0351
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r13)
                android.os.Bundle r0 = (android.os.Bundle) r0
                r6 = r0
            L_0x0351:
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0358
                r7 = r10
            L_0x0358:
                long r8 = r13.readLong()
                r1 = r11
                r1.a((com.google.android.gms.games.internal.IGamesCallbacks) r2, (android.os.IBinder) r3, (int) r4, (java.lang.String[]) r5, (android.os.Bundle) r6, (boolean) r7, (long) r8)
                r14.writeNoException()
                goto L_0x000a
            L_0x0365:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r2 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                android.os.IBinder r3 = r13.readStrongBinder()
                java.lang.String r4 = r13.readString()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x038e
                r5 = r10
            L_0x0381:
                long r6 = r13.readLong()
                r1 = r11
                r1.a((com.google.android.gms.games.internal.IGamesCallbacks) r2, (android.os.IBinder) r3, (java.lang.String) r4, (boolean) r5, (long) r6)
                r14.writeNoException()
                goto L_0x000a
            L_0x038e:
                r5 = r7
                goto L_0x0381
            L_0x0390:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                r11.c((com.google.android.gms.games.internal.IGamesCallbacks) r0, (java.lang.String) r1)
                r14.writeNoException()
                goto L_0x000a
            L_0x03a9:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                byte[] r1 = r13.createByteArray()
                java.lang.String r2 = r13.readString()
                java.lang.String r3 = r13.readString()
                int r0 = r11.a((com.google.android.gms.games.internal.IGamesCallbacks) r0, (byte[]) r1, (java.lang.String) r2, (java.lang.String) r3)
                r14.writeNoException()
                r14.writeInt(r0)
                goto L_0x000a
            L_0x03ce:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                byte[] r0 = r13.createByteArray()
                java.lang.String r1 = r13.readString()
                java.lang.String[] r2 = r13.createStringArray()
                int r0 = r11.b((byte[]) r0, (java.lang.String) r1, (java.lang.String[]) r2)
                r14.writeNoException()
                r14.writeInt(r0)
                goto L_0x000a
            L_0x03eb:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                java.lang.String r0 = r13.readString()
                java.lang.String r0 = r11.aW(r0)
                r14.writeNoException()
                r14.writeString(r0)
                goto L_0x000a
            L_0x0400:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                int r0 = r13.readInt()
                r11.ch(r0)
                r14.writeNoException()
                goto L_0x000a
            L_0x0411:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                r11.d((com.google.android.gms.games.internal.IGamesCallbacks) r0, (java.lang.String) r1)
                r14.writeNoException()
                goto L_0x000a
            L_0x042a:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                java.lang.String r2 = r13.readString()
                r11.a((com.google.android.gms.games.internal.IGamesCallbacks) r0, (java.lang.String) r1, (java.lang.String) r2)
                r14.writeNoException()
                goto L_0x000a
            L_0x0447:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r1 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r2 = r13.readString()
                java.lang.String r3 = r13.readString()
                int r4 = r13.readInt()
                int r5 = r13.readInt()
                int r6 = r13.readInt()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x046f
                r7 = r10
            L_0x046f:
                r0 = r11
                r0.a((com.google.android.gms.games.internal.IGamesCallbacks) r1, (java.lang.String) r2, (java.lang.String) r3, (int) r4, (int) r5, (int) r6, (boolean) r7)
                r14.writeNoException()
                goto L_0x000a
            L_0x0478:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r1 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r2 = r13.readString()
                java.lang.String r3 = r13.readString()
                int r4 = r13.readInt()
                int r5 = r13.readInt()
                int r6 = r13.readInt()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x04a0
                r7 = r10
            L_0x04a0:
                r0 = r11
                r0.b(r1, r2, r3, r4, r5, r6, r7)
                r14.writeNoException()
                goto L_0x000a
            L_0x04a9:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                java.lang.String r2 = r13.readString()
                r11.b((com.google.android.gms.games.internal.IGamesCallbacks) r0, (java.lang.String) r1, (java.lang.String) r2)
                r14.writeNoException()
                goto L_0x000a
            L_0x04c6:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                r11.e((com.google.android.gms.games.internal.IGamesCallbacks) r0, (java.lang.String) r1)
                r14.writeNoException()
                goto L_0x000a
            L_0x04df:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                r11.f((com.google.android.gms.games.internal.IGamesCallbacks) r0, (java.lang.String) r1)
                r14.writeNoException()
                goto L_0x000a
            L_0x04f8:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r1 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                int r2 = r13.readInt()
                int r3 = r13.readInt()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0524
                r4 = r10
            L_0x0514:
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0526
                r5 = r10
            L_0x051b:
                r0 = r11
                r0.a((com.google.android.gms.games.internal.IGamesCallbacks) r1, (int) r2, (int) r3, (boolean) r4, (boolean) r5)
                r14.writeNoException()
                goto L_0x000a
            L_0x0524:
                r4 = r7
                goto L_0x0514
            L_0x0526:
                r5 = r7
                goto L_0x051b
            L_0x0528:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r1 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r2 = r13.readString()
                int r3 = r13.readInt()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0554
                r4 = r10
            L_0x0544:
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0556
                r5 = r10
            L_0x054b:
                r0 = r11
                r0.a((com.google.android.gms.games.internal.IGamesCallbacks) r1, (java.lang.String) r2, (int) r3, (boolean) r4, (boolean) r5)
                r14.writeNoException()
                goto L_0x000a
            L_0x0554:
                r4 = r7
                goto L_0x0544
            L_0x0556:
                r5 = r7
                goto L_0x054b
            L_0x0558:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r1 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                int r2 = r13.readInt()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x057f
                r0 = r10
            L_0x0570:
                int r3 = r13.readInt()
                if (r3 == 0) goto L_0x0577
                r7 = r10
            L_0x0577:
                r11.b((com.google.android.gms.games.internal.IGamesCallbacks) r1, (int) r2, (boolean) r0, (boolean) r7)
                r14.writeNoException()
                goto L_0x000a
            L_0x057f:
                r0 = r7
                goto L_0x0570
            L_0x0581:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                r11.f(r0)
                r14.writeNoException()
                goto L_0x000a
            L_0x0596:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r1 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                int r2 = r13.readInt()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x05bd
                r0 = r10
            L_0x05ae:
                int r3 = r13.readInt()
                if (r3 == 0) goto L_0x05b5
                r7 = r10
            L_0x05b5:
                r11.c((com.google.android.gms.games.internal.IGamesCallbacks) r1, (int) r2, (boolean) r0, (boolean) r7)
                r14.writeNoException()
                goto L_0x000a
            L_0x05bd:
                r0 = r7
                goto L_0x05ae
            L_0x05bf:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                r11.g((com.google.android.gms.games.internal.IGamesCallbacks) r0)
                r14.writeNoException()
                goto L_0x000a
            L_0x05d4:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                java.lang.String r0 = r13.readString()
                r11.aX(r0)
                r14.writeNoException()
                goto L_0x000a
            L_0x05e5:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                java.lang.String r0 = r13.readString()
                java.lang.String r1 = r13.readString()
                int r2 = r13.readInt()
                r11.b((java.lang.String) r0, (java.lang.String) r1, (int) r2)
                r14.writeNoException()
                goto L_0x000a
            L_0x05fe:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                r11.g(r0, r1)
                r14.writeNoException()
                goto L_0x000a
            L_0x0617:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                com.google.android.gms.games.multiplayer.realtime.RoomEntity r0 = r11.h(r0, r1)
                r14.writeNoException()
                if (r0 == 0) goto L_0x0639
                r14.writeInt(r10)
                r0.writeToParcel(r14, r10)
                goto L_0x000a
            L_0x0639:
                r14.writeInt(r7)
                goto L_0x000a
            L_0x063e:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                java.lang.String r0 = r13.readString()
                int r0 = r11.aY(r0)
                r14.writeNoException()
                r14.writeInt(r0)
                goto L_0x000a
            L_0x0653:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                int r2 = r13.readInt()
                if (r2 == 0) goto L_0x066b
                r7 = r10
            L_0x066b:
                r11.a((com.google.android.gms.games.internal.IGamesCallbacks) r0, (java.lang.String) r1, (boolean) r7)
                r14.writeNoException()
                goto L_0x000a
            L_0x0673:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                r11.i(r0, r1)
                r14.writeNoException()
                goto L_0x000a
            L_0x068c:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                java.lang.String r0 = r13.readString()
                int r1 = r13.readInt()
                r11.p((java.lang.String) r0, (int) r1)
                r14.writeNoException()
                goto L_0x000a
            L_0x06a1:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                boolean r0 = r11.hu()
                r14.writeNoException()
                if (r0 == 0) goto L_0x06b0
                r7 = r10
            L_0x06b0:
                r14.writeInt(r7)
                goto L_0x000a
            L_0x06b5:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x06c1
                r7 = r10
            L_0x06c1:
                r11.E(r7)
                r14.writeNoException()
                goto L_0x000a
            L_0x06c9:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                r11.h(r0)
                r14.writeNoException()
                goto L_0x000a
            L_0x06de:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                r11.j(r0, r1)
                r14.writeNoException()
                goto L_0x000a
            L_0x06f7:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                r11.i(r0)
                r14.writeNoException()
                goto L_0x000a
            L_0x070c:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r1 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0720
                r7 = r10
            L_0x0720:
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0736
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r13)
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x072e:
                r11.a((com.google.android.gms.games.internal.IGamesCallbacks) r1, (boolean) r7, (android.os.Bundle) r0)
                r14.writeNoException()
                goto L_0x000a
            L_0x0736:
                r0 = r6
                goto L_0x072e
            L_0x0738:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                java.lang.String r0 = r13.readString()
                android.net.Uri r0 = r11.aZ(r0)
                r14.writeNoException()
                if (r0 == 0) goto L_0x0752
                r14.writeInt(r10)
                r0.writeToParcel(r14, r10)
                goto L_0x000a
            L_0x0752:
                r14.writeInt(r7)
                goto L_0x000a
            L_0x0757:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r1 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r2 = r13.readString()
                int r3 = r13.readInt()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0783
                r4 = r10
            L_0x0773:
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0785
                r5 = r10
            L_0x077a:
                r0 = r11
                r0.b((com.google.android.gms.games.internal.IGamesCallbacks) r1, (java.lang.String) r2, (int) r3, (boolean) r4, (boolean) r5)
                r14.writeNoException()
                goto L_0x000a
            L_0x0783:
                r4 = r7
                goto L_0x0773
            L_0x0785:
                r5 = r7
                goto L_0x077a
            L_0x0787:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                com.google.android.gms.common.data.DataHolder r0 = r11.hv()
                r14.writeNoException()
                if (r0 == 0) goto L_0x079d
                r14.writeInt(r10)
                r0.writeToParcel(r14, r10)
                goto L_0x000a
            L_0x079d:
                r14.writeInt(r7)
                goto L_0x000a
            L_0x07a2:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                int r1 = r13.readInt()
                if (r1 == 0) goto L_0x07b6
                r7 = r10
            L_0x07b6:
                r11.a((com.google.android.gms.games.internal.IGamesCallbacks) r0, (boolean) r7)
                r14.writeNoException()
                goto L_0x000a
            L_0x07be:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                java.lang.String r2 = r13.readString()
                int r3 = r13.readInt()
                if (r3 == 0) goto L_0x07da
                r7 = r10
            L_0x07da:
                r11.a((com.google.android.gms.games.internal.IGamesCallbacks) r0, (java.lang.String) r1, (java.lang.String) r2, (boolean) r7)
                r14.writeNoException()
                goto L_0x000a
            L_0x07e2:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r1 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                int r2 = r13.readInt()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0809
                r0 = r10
            L_0x07fa:
                int r3 = r13.readInt()
                if (r3 == 0) goto L_0x0801
                r7 = r10
            L_0x0801:
                r11.d(r1, r2, r0, r7)
                r14.writeNoException()
                goto L_0x000a
            L_0x0809:
                r0 = r7
                goto L_0x07fa
            L_0x080b:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r1 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                int r2 = r13.readInt()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0832
                r0 = r10
            L_0x0823:
                int r3 = r13.readInt()
                if (r3 == 0) goto L_0x082a
                r7 = r10
            L_0x082a:
                r11.e(r1, r2, r0, r7)
                r14.writeNoException()
                goto L_0x000a
            L_0x0832:
                r0 = r7
                goto L_0x0823
            L_0x0834:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r1 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r2 = r13.readString()
                int r3 = r13.readInt()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x086e
                r4 = r10
            L_0x0850:
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0870
                r5 = r10
            L_0x0857:
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0872
                r6 = r10
            L_0x085e:
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0865
                r7 = r10
            L_0x0865:
                r0 = r11
                r0.a((com.google.android.gms.games.internal.IGamesCallbacks) r1, (java.lang.String) r2, (int) r3, (boolean) r4, (boolean) r5, (boolean) r6, (boolean) r7)
                r14.writeNoException()
                goto L_0x000a
            L_0x086e:
                r4 = r7
                goto L_0x0850
            L_0x0870:
                r5 = r7
                goto L_0x0857
            L_0x0872:
                r6 = r7
                goto L_0x085e
            L_0x0874:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                int r2 = r13.readInt()
                if (r2 == 0) goto L_0x088c
                r7 = r10
            L_0x088c:
                r11.b((com.google.android.gms.games.internal.IGamesCallbacks) r0, (java.lang.String) r1, (boolean) r7)
                r14.writeNoException()
                goto L_0x000a
            L_0x0894:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                int r1 = r13.readInt()
                if (r1 == 0) goto L_0x08a8
                r7 = r10
            L_0x08a8:
                r11.b((com.google.android.gms.games.internal.IGamesCallbacks) r0, (boolean) r7)
                r14.writeNoException()
                goto L_0x000a
            L_0x08b0:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                int r2 = r13.readInt()
                if (r2 == 0) goto L_0x08c8
                r7 = r10
            L_0x08c8:
                r11.c((com.google.android.gms.games.internal.IGamesCallbacks) r0, (java.lang.String) r1, (boolean) r7)
                r14.writeNoException()
                goto L_0x000a
            L_0x08d0:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                int r2 = r13.readInt()
                if (r2 == 0) goto L_0x08e8
                r7 = r10
            L_0x08e8:
                r11.d((com.google.android.gms.games.internal.IGamesCallbacks) r0, (java.lang.String) r1, (boolean) r7)
                r14.writeNoException()
                goto L_0x000a
            L_0x08f0:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                java.lang.String r2 = r13.readString()
                int r3 = r13.readInt()
                if (r3 == 0) goto L_0x090c
                r7 = r10
            L_0x090c:
                r11.b((com.google.android.gms.games.internal.IGamesCallbacks) r0, (java.lang.String) r1, (java.lang.String) r2, (boolean) r7)
                r14.writeNoException()
                goto L_0x000a
            L_0x0914:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0938
                android.os.Parcelable$Creator r0 = android.net.Uri.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r13)
                android.net.Uri r0 = (android.net.Uri) r0
            L_0x0927:
                android.os.ParcelFileDescriptor r0 = r11.g((android.net.Uri) r0)
                r14.writeNoException()
                if (r0 == 0) goto L_0x093a
                r14.writeInt(r10)
                r0.writeToParcel(r14, r10)
                goto L_0x000a
            L_0x0938:
                r0 = r6
                goto L_0x0927
            L_0x093a:
                r14.writeInt(r7)
                goto L_0x000a
            L_0x093f:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                r11.k(r0, r1)
                r14.writeNoException()
                goto L_0x000a
            L_0x0958:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r2 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r3 = r13.readString()
                long r4 = r13.readLong()
                java.lang.String r6 = r13.readString()
                r1 = r11
                r1.a((com.google.android.gms.games.internal.IGamesCallbacks) r2, (java.lang.String) r3, (long) r4, (java.lang.String) r6)
                r14.writeNoException()
                goto L_0x000a
            L_0x097a:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r1 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r2 = r13.readString()
                int r3 = r13.readInt()
                android.os.IBinder r4 = r13.readStrongBinder()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x09ab
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r13)
                android.os.Bundle r0 = (android.os.Bundle) r0
                r5 = r0
            L_0x09a2:
                r0 = r11
                r0.b((com.google.android.gms.games.internal.IGamesCallbacks) r1, (java.lang.String) r2, (int) r3, (android.os.IBinder) r4, (android.os.Bundle) r5)
                r14.writeNoException()
                goto L_0x000a
            L_0x09ab:
                r5 = r6
                goto L_0x09a2
            L_0x09ad:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r1 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r2 = r13.readString()
                java.lang.String r3 = r13.readString()
                int r4 = r13.readInt()
                int r5 = r13.readInt()
                r0 = r11
                r0.a((com.google.android.gms.games.internal.IGamesCallbacks) r1, (java.lang.String) r2, (java.lang.String) r3, (int) r4, (int) r5)
                r14.writeNoException()
                goto L_0x000a
            L_0x09d3:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                java.lang.String r0 = r13.readString()
                r11.ba(r0)
                r14.writeNoException()
                goto L_0x000a
            L_0x09e4:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                int[] r1 = r13.createIntArray()
                r11.a((com.google.android.gms.games.internal.IGamesCallbacks) r0, (int[]) r1)
                r14.writeNoException()
                goto L_0x000a
            L_0x09fd:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r1 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                int r2 = r13.readInt()
                int r3 = r13.readInt()
                java.lang.String[] r4 = r13.createStringArray()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0a2e
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r13)
                android.os.Bundle r0 = (android.os.Bundle) r0
                r5 = r0
            L_0x0a25:
                r0 = r11
                r0.a((com.google.android.gms.games.internal.IGamesCallbacks) r1, (int) r2, (int) r3, (java.lang.String[]) r4, (android.os.Bundle) r5)
                r14.writeNoException()
                goto L_0x000a
            L_0x0a2e:
                r5 = r6
                goto L_0x0a25
            L_0x0a30:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                r11.l((com.google.android.gms.games.internal.IGamesCallbacks) r0, (java.lang.String) r1)
                r14.writeNoException()
                goto L_0x000a
            L_0x0a49:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                r11.m((com.google.android.gms.games.internal.IGamesCallbacks) r0, (java.lang.String) r1)
                r14.writeNoException()
                goto L_0x000a
            L_0x0a62:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r1 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r2 = r13.readString()
                byte[] r3 = r13.createByteArray()
                java.lang.String r4 = r13.readString()
                com.google.android.gms.games.multiplayer.ParticipantResultCreator r0 = com.google.android.gms.games.multiplayer.ParticipantResult.CREATOR
                java.lang.Object[] r5 = r13.createTypedArray(r0)
                com.google.android.gms.games.multiplayer.ParticipantResult[] r5 = (com.google.android.gms.games.multiplayer.ParticipantResult[]) r5
                r0 = r11
                r0.a((com.google.android.gms.games.internal.IGamesCallbacks) r1, (java.lang.String) r2, (byte[]) r3, (java.lang.String) r4, (com.google.android.gms.games.multiplayer.ParticipantResult[]) r5)
                r14.writeNoException()
                goto L_0x000a
            L_0x0a8c:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r1 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r2 = r13.readString()
                byte[] r3 = r13.createByteArray()
                com.google.android.gms.games.multiplayer.ParticipantResultCreator r0 = com.google.android.gms.games.multiplayer.ParticipantResult.CREATOR
                java.lang.Object[] r0 = r13.createTypedArray(r0)
                com.google.android.gms.games.multiplayer.ParticipantResult[] r0 = (com.google.android.gms.games.multiplayer.ParticipantResult[]) r0
                r11.a((com.google.android.gms.games.internal.IGamesCallbacks) r1, (java.lang.String) r2, (byte[]) r3, (com.google.android.gms.games.multiplayer.ParticipantResult[]) r0)
                r14.writeNoException()
                goto L_0x000a
            L_0x0ab1:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                r11.n((com.google.android.gms.games.internal.IGamesCallbacks) r0, (java.lang.String) r1)
                r14.writeNoException()
                goto L_0x000a
            L_0x0aca:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                r11.o(r0, r1)
                r14.writeNoException()
                goto L_0x000a
            L_0x0ae3:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                java.lang.String r2 = r13.readString()
                r11.c((com.google.android.gms.games.internal.IGamesCallbacks) r0, (java.lang.String) r1, (java.lang.String) r2)
                r14.writeNoException()
                goto L_0x000a
            L_0x0b00:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                long r2 = r13.readLong()
                r11.b((com.google.android.gms.games.internal.IGamesCallbacks) r0, (long) r2)
                r14.writeNoException()
                goto L_0x000a
            L_0x0b19:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                long r0 = r13.readLong()
                r11.s(r0)
                r14.writeNoException()
                goto L_0x000a
            L_0x0b2a:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                r11.p((com.google.android.gms.games.internal.IGamesCallbacks) r0, (java.lang.String) r1)
                r14.writeNoException()
                goto L_0x000a
            L_0x0b43:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                int r0 = r11.hk()
                r14.writeNoException()
                r14.writeInt(r0)
                goto L_0x000a
            L_0x0b54:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                java.lang.String r0 = r13.readString()
                java.lang.String r1 = r13.readString()
                r11.n((java.lang.String) r0, (java.lang.String) r1)
                r14.writeNoException()
                goto L_0x000a
            L_0x0b69:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                java.lang.String r2 = r13.readString()
                r11.d((com.google.android.gms.games.internal.IGamesCallbacks) r0, (java.lang.String) r1, (java.lang.String) r2)
                r14.writeNoException()
                goto L_0x000a
            L_0x0b86:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                java.lang.String r2 = r13.readString()
                r11.e((com.google.android.gms.games.internal.IGamesCallbacks) r0, (java.lang.String) r1, (java.lang.String) r2)
                r14.writeNoException()
                goto L_0x000a
            L_0x0ba3:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                int[] r2 = r13.createIntArray()
                r11.a((com.google.android.gms.games.internal.IGamesCallbacks) r0, (java.lang.String) r1, (int[]) r2)
                r14.writeNoException()
                goto L_0x000a
            L_0x0bc0:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                java.lang.String r0 = r13.readString()
                java.lang.String r1 = r13.readString()
                int r2 = r13.readInt()
                r11.c((java.lang.String) r0, (java.lang.String) r1, (int) r2)
                r14.writeNoException()
                goto L_0x000a
            L_0x0bd9:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                long r2 = r13.readLong()
                java.lang.String r1 = r13.readString()
                r11.a((com.google.android.gms.games.internal.IGamesCallbacks) r0, (long) r2, (java.lang.String) r1)
                r14.writeNoException()
                goto L_0x000a
            L_0x0bf6:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                long r0 = r13.readLong()
                java.lang.String r2 = r13.readString()
                r11.a((long) r0, (java.lang.String) r2)
                r14.writeNoException()
                goto L_0x000a
            L_0x0c0b:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                long r2 = r13.readLong()
                java.lang.String r1 = r13.readString()
                r11.b((com.google.android.gms.games.internal.IGamesCallbacks) r0, (long) r2, (java.lang.String) r1)
                r14.writeNoException()
                goto L_0x000a
            L_0x0c28:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                long r0 = r13.readLong()
                java.lang.String r2 = r13.readString()
                r11.b((long) r0, (java.lang.String) r2)
                r14.writeNoException()
                goto L_0x000a
            L_0x0c3d:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                r11.hw()
                r14.writeNoException()
                goto L_0x000a
            L_0x0c4a:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                int r2 = r13.readInt()
                int r3 = r13.readInt()
                if (r3 == 0) goto L_0x0c66
                r7 = r10
            L_0x0c66:
                r11.a((com.google.android.gms.games.internal.IGamesCallbacks) r0, (java.lang.String) r1, (int) r2, (boolean) r7)
                r14.writeNoException()
                goto L_0x000a
            L_0x0c6e:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                int r1 = r13.readInt()
                if (r1 == 0) goto L_0x0c82
                r7 = r10
            L_0x0c82:
                r11.c((com.google.android.gms.games.internal.IGamesCallbacks) r0, (boolean) r7)
                r14.writeNoException()
                goto L_0x000a
            L_0x0c8a:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r1 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r2 = r13.readString()
                int r3 = r13.readInt()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0cb6
                r4 = r10
            L_0x0ca6:
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0cb8
                r5 = r10
            L_0x0cad:
                r0 = r11
                r0.c(r1, r2, r3, r4, r5)
                r14.writeNoException()
                goto L_0x000a
            L_0x0cb6:
                r4 = r7
                goto L_0x0ca6
            L_0x0cb8:
                r5 = r7
                goto L_0x0cad
            L_0x0cba:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                r11.q((com.google.android.gms.games.internal.IGamesCallbacks) r0, (java.lang.String) r1)
                r14.writeNoException()
                goto L_0x000a
            L_0x0cd3:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.content.Intent r0 = r11.gY()
                r14.writeNoException()
                if (r0 == 0) goto L_0x0ce9
                r14.writeInt(r10)
                r0.writeToParcel(r14, r10)
                goto L_0x000a
            L_0x0ce9:
                r14.writeInt(r7)
                goto L_0x000a
            L_0x0cee:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                java.lang.String r0 = r13.readString()
                android.content.Intent r0 = r11.aR(r0)
                r14.writeNoException()
                if (r0 == 0) goto L_0x0d08
                r14.writeInt(r10)
                r0.writeToParcel(r14, r10)
                goto L_0x000a
            L_0x0d08:
                r14.writeInt(r7)
                goto L_0x000a
            L_0x0d0d:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.content.Intent r0 = r11.gZ()
                r14.writeNoException()
                if (r0 == 0) goto L_0x0d23
                r14.writeInt(r10)
                r0.writeToParcel(r14, r10)
                goto L_0x000a
            L_0x0d23:
                r14.writeInt(r7)
                goto L_0x000a
            L_0x0d28:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.content.Intent r0 = r11.ha()
                r14.writeNoException()
                if (r0 == 0) goto L_0x0d3e
                r14.writeInt(r10)
                r0.writeToParcel(r14, r10)
                goto L_0x000a
            L_0x0d3e:
                r14.writeInt(r7)
                goto L_0x000a
            L_0x0d43:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.content.Intent r0 = r11.hb()
                r14.writeNoException()
                if (r0 == 0) goto L_0x0d59
                r14.writeInt(r10)
                r0.writeToParcel(r14, r10)
                goto L_0x000a
            L_0x0d59:
                r14.writeInt(r7)
                goto L_0x000a
            L_0x0d5e:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                int r1 = r13.readInt()
                int r2 = r13.readInt()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0d83
                r0 = r10
            L_0x0d72:
                android.content.Intent r0 = r11.a((int) r1, (int) r2, (boolean) r0)
                r14.writeNoException()
                if (r0 == 0) goto L_0x0d85
                r14.writeInt(r10)
                r0.writeToParcel(r14, r10)
                goto L_0x000a
            L_0x0d83:
                r0 = r7
                goto L_0x0d72
            L_0x0d85:
                r14.writeInt(r7)
                goto L_0x000a
            L_0x0d8a:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                int r1 = r13.readInt()
                int r2 = r13.readInt()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0daf
                r0 = r10
            L_0x0d9e:
                android.content.Intent r0 = r11.b((int) r1, (int) r2, (boolean) r0)
                r14.writeNoException()
                if (r0 == 0) goto L_0x0db1
                r14.writeInt(r10)
                r0.writeToParcel(r14, r10)
                goto L_0x000a
            L_0x0daf:
                r0 = r7
                goto L_0x0d9e
            L_0x0db1:
                r14.writeInt(r7)
                goto L_0x000a
            L_0x0db6:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.content.Intent r0 = r11.hg()
                r14.writeNoException()
                if (r0 == 0) goto L_0x0dcc
                r14.writeInt(r10)
                r0.writeToParcel(r14, r10)
                goto L_0x000a
            L_0x0dcc:
                r14.writeInt(r7)
                goto L_0x000a
            L_0x0dd1:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0df9
                android.os.Parcelable$Creator<com.google.android.gms.games.multiplayer.realtime.RoomEntity> r0 = com.google.android.gms.games.multiplayer.realtime.RoomEntity.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r13)
                com.google.android.gms.games.multiplayer.realtime.RoomEntity r0 = (com.google.android.gms.games.multiplayer.realtime.RoomEntity) r0
            L_0x0de4:
                int r1 = r13.readInt()
                android.content.Intent r0 = r11.a((com.google.android.gms.games.multiplayer.realtime.RoomEntity) r0, (int) r1)
                r14.writeNoException()
                if (r0 == 0) goto L_0x0dfb
                r14.writeInt(r10)
                r0.writeToParcel(r14, r10)
                goto L_0x000a
            L_0x0df9:
                r0 = r6
                goto L_0x0de4
            L_0x0dfb:
                r14.writeInt(r7)
                goto L_0x000a
            L_0x0e00:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.content.Intent r0 = r11.hh()
                r14.writeNoException()
                if (r0 == 0) goto L_0x0e16
                r14.writeInt(r10)
                r0.writeToParcel(r14, r10)
                goto L_0x000a
            L_0x0e16:
                r14.writeInt(r7)
                goto L_0x000a
            L_0x0e1b:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.content.Intent r0 = r11.hx()
                r14.writeNoException()
                if (r0 == 0) goto L_0x0e31
                r14.writeInt(r10)
                r0.writeToParcel(r14, r10)
                goto L_0x000a
            L_0x0e31:
                r14.writeInt(r7)
                goto L_0x000a
            L_0x0e36:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.Parcelable$Creator<com.google.android.gms.games.multiplayer.ParticipantEntity> r0 = com.google.android.gms.games.multiplayer.ParticipantEntity.CREATOR
                java.lang.Object[] r1 = r13.createTypedArray(r0)
                com.google.android.gms.games.multiplayer.ParticipantEntity[] r1 = (com.google.android.gms.games.multiplayer.ParticipantEntity[]) r1
                java.lang.String r2 = r13.readString()
                java.lang.String r3 = r13.readString()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0e7b
                android.os.Parcelable$Creator r0 = android.net.Uri.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r13)
                android.net.Uri r0 = (android.net.Uri) r0
                r4 = r0
            L_0x0e5a:
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0e7d
                android.os.Parcelable$Creator r0 = android.net.Uri.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r13)
                android.net.Uri r0 = (android.net.Uri) r0
                r5 = r0
            L_0x0e69:
                r0 = r11
                android.content.Intent r0 = r0.a((com.google.android.gms.games.multiplayer.ParticipantEntity[]) r1, (java.lang.String) r2, (java.lang.String) r3, (android.net.Uri) r4, (android.net.Uri) r5)
                r14.writeNoException()
                if (r0 == 0) goto L_0x0e7f
                r14.writeInt(r10)
                r0.writeToParcel(r14, r10)
                goto L_0x000a
            L_0x0e7b:
                r4 = r6
                goto L_0x0e5a
            L_0x0e7d:
                r5 = r6
                goto L_0x0e69
            L_0x0e7f:
                r14.writeInt(r7)
                goto L_0x000a
            L_0x0e84:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                int r0 = r11.hi()
                r14.writeNoException()
                r14.writeInt(r0)
                goto L_0x000a
            L_0x0e95:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r1 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r2 = r13.readString()
                int r3 = r13.readInt()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0ec1
                r4 = r10
            L_0x0eb1:
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0ec3
                r5 = r10
            L_0x0eb8:
                r0 = r11
                r0.d(r1, r2, r3, r4, r5)
                r14.writeNoException()
                goto L_0x000a
            L_0x0ec1:
                r4 = r7
                goto L_0x0eb1
            L_0x0ec3:
                r5 = r7
                goto L_0x0eb8
            L_0x0ec5:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r1 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r2 = r13.readString()
                java.lang.String r3 = r13.readString()
                int r4 = r13.readInt()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0ef5
                r5 = r10
            L_0x0ee5:
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0ef7
                r6 = r10
            L_0x0eec:
                r0 = r11
                r0.a((com.google.android.gms.games.internal.IGamesCallbacks) r1, (java.lang.String) r2, (java.lang.String) r3, (int) r4, (boolean) r5, (boolean) r6)
                r14.writeNoException()
                goto L_0x000a
            L_0x0ef5:
                r5 = r7
                goto L_0x0ee5
            L_0x0ef7:
                r6 = r7
                goto L_0x0eec
            L_0x0ef9:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                java.lang.String r0 = r13.readString()
                android.os.ParcelFileDescriptor r0 = r11.bb(r0)
                r14.writeNoException()
                if (r0 == 0) goto L_0x0f13
                r14.writeInt(r10)
                r0.writeToParcel(r14, r10)
                goto L_0x000a
            L_0x0f13:
                r14.writeInt(r7)
                goto L_0x000a
            L_0x0f18:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                long r2 = r13.readLong()
                r11.c((com.google.android.gms.games.internal.IGamesCallbacks) r0, (long) r2)
                r14.writeNoException()
                goto L_0x000a
            L_0x0f31:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                long r0 = r13.readLong()
                r11.t(r0)
                r14.writeNoException()
                goto L_0x000a
            L_0x0f42:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                long r2 = r13.readLong()
                java.lang.String r1 = r13.readString()
                r11.c((com.google.android.gms.games.internal.IGamesCallbacks) r0, (long) r2, (java.lang.String) r1)
                r14.writeNoException()
                goto L_0x000a
            L_0x0f5f:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                long r0 = r13.readLong()
                java.lang.String r2 = r13.readString()
                r11.c((long) r0, (java.lang.String) r2)
                r14.writeNoException()
                goto L_0x000a
            L_0x0f74:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r1 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r2 = r13.readString()
                java.lang.String[] r3 = r13.createStringArray()
                int r4 = r13.readInt()
                byte[] r5 = r13.createByteArray()
                int r6 = r13.readInt()
                r0 = r11
                r0.a((com.google.android.gms.games.internal.IGamesCallbacks) r1, (java.lang.String) r2, (java.lang.String[]) r3, (int) r4, (byte[]) r5, (int) r6)
                r14.writeNoException()
                goto L_0x000a
            L_0x0f9e:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String[] r1 = r13.createStringArray()
                r11.a((com.google.android.gms.games.internal.IGamesCallbacks) r0, (java.lang.String[]) r1)
                r14.writeNoException()
                goto L_0x000a
            L_0x0fb7:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String[] r1 = r13.createStringArray()
                r11.b((com.google.android.gms.games.internal.IGamesCallbacks) r0, (java.lang.String[]) r1)
                r14.writeNoException()
                goto L_0x000a
            L_0x0fd0:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                java.lang.String r2 = r13.readString()
                java.lang.String[] r3 = r13.createStringArray()
                r11.a((com.google.android.gms.games.internal.IGamesCallbacks) r0, (java.lang.String) r1, (java.lang.String) r2, (java.lang.String[]) r3)
                r14.writeNoException()
                goto L_0x000a
            L_0x0ff1:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                int r1 = r13.readInt()
                int r2 = r13.readInt()
                int r3 = r13.readInt()
                r11.a((com.google.android.gms.games.internal.IGamesCallbacks) r0, (int) r1, (int) r2, (int) r3)
                r14.writeNoException()
                goto L_0x000a
            L_0x1012:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r1 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r2 = r13.readString()
                java.lang.String r3 = r13.readString()
                int r4 = r13.readInt()
                int r5 = r13.readInt()
                int r6 = r13.readInt()
                r0 = r11
                r0.a((com.google.android.gms.games.internal.IGamesCallbacks) r1, (java.lang.String) r2, (java.lang.String) r3, (int) r4, (int) r5, (int) r6)
                r14.writeNoException()
                goto L_0x000a
            L_0x103c:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                int r2 = r13.readInt()
                r11.a((com.google.android.gms.games.internal.IGamesCallbacks) r0, (java.lang.String) r1, (int) r2)
                r14.writeNoException()
                goto L_0x000a
            L_0x1059:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                int r0 = r13.readInt()
                byte[] r1 = r13.createByteArray()
                int r2 = r13.readInt()
                java.lang.String r3 = r13.readString()
                android.content.Intent r0 = r11.a((int) r0, (byte[]) r1, (int) r2, (java.lang.String) r3)
                r14.writeNoException()
                if (r0 == 0) goto L_0x107f
                r14.writeInt(r10)
                r0.writeToParcel(r14, r10)
                goto L_0x000a
            L_0x107f:
                r14.writeInt(r7)
                goto L_0x000a
            L_0x1084:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                int r0 = r11.hm()
                r14.writeNoException()
                r14.writeInt(r0)
                goto L_0x000a
            L_0x1095:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                int r0 = r11.hn()
                r14.writeNoException()
                r14.writeInt(r0)
                goto L_0x000a
            L_0x10a6:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.content.Intent r0 = r11.hl()
                r14.writeNoException()
                if (r0 == 0) goto L_0x10bc
                r14.writeInt(r10)
                r0.writeToParcel(r14, r10)
                goto L_0x000a
            L_0x10bc:
                r14.writeInt(r7)
                goto L_0x000a
            L_0x10c1:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x10d2
                com.google.android.gms.games.internal.request.GameRequestClusterCreator r0 = com.google.android.gms.games.internal.request.GameRequestCluster.CREATOR
                com.google.android.gms.games.internal.request.GameRequestCluster r6 = r0.createFromParcel(r13)
            L_0x10d2:
                java.lang.String r0 = r13.readString()
                android.content.Intent r0 = r11.a((com.google.android.gms.games.internal.request.GameRequestCluster) r6, (java.lang.String) r0)
                r14.writeNoException()
                if (r0 == 0) goto L_0x10e7
                r14.writeInt(r10)
                r0.writeToParcel(r14, r10)
                goto L_0x000a
            L_0x10e7:
                r14.writeInt(r7)
                goto L_0x000a
            L_0x10ec:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                java.lang.String r0 = r13.readString()
                int r1 = r13.readInt()
                r11.q((java.lang.String) r0, (int) r1)
                r14.writeNoException()
                goto L_0x000a
            L_0x1101:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                int r1 = r13.readInt()
                r11.a((com.google.android.gms.games.internal.IGamesCallbacks) r0, (int) r1)
                r14.writeNoException()
                goto L_0x000a
            L_0x111a:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                int r2 = r13.readInt()
                int r3 = r13.readInt()
                if (r3 == 0) goto L_0x1136
                r7 = r10
            L_0x1136:
                r11.b((com.google.android.gms.games.internal.IGamesCallbacks) r0, (java.lang.String) r1, (int) r2, (boolean) r7)
                r14.writeNoException()
                goto L_0x000a
            L_0x113e:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x114f
                com.google.android.gms.games.internal.multiplayer.InvitationClusterCreator r0 = com.google.android.gms.games.internal.multiplayer.ZInvitationCluster.CREATOR
                com.google.android.gms.games.internal.multiplayer.ZInvitationCluster r6 = r0.createFromParcel(r13)
            L_0x114f:
                java.lang.String r0 = r13.readString()
                java.lang.String r1 = r13.readString()
                android.content.Intent r0 = r11.a((com.google.android.gms.games.internal.multiplayer.ZInvitationCluster) r6, (java.lang.String) r0, (java.lang.String) r1)
                r14.writeNoException()
                if (r0 == 0) goto L_0x1168
                r14.writeInt(r10)
                r0.writeToParcel(r14, r10)
                goto L_0x000a
            L_0x1168:
                r14.writeInt(r7)
                goto L_0x000a
            L_0x116d:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                int r1 = r13.readInt()
                int[] r2 = r13.createIntArray()
                r11.a((com.google.android.gms.games.internal.IGamesCallbacks) r0, (int) r1, (int[]) r2)
                r14.writeNoException()
                goto L_0x000a
            L_0x118a:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                int r2 = r13.readInt()
                int[] r3 = r13.createIntArray()
                r11.a((com.google.android.gms.games.internal.IGamesCallbacks) r0, (java.lang.String) r1, (int) r2, (int[]) r3)
                r14.writeNoException()
                goto L_0x000a
            L_0x11ab:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String[] r1 = r13.createStringArray()
                r11.c((com.google.android.gms.games.internal.IGamesCallbacks) r0, (java.lang.String[]) r1)
                r14.writeNoException()
                goto L_0x000a
            L_0x11c4:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                r11.j(r0)
                r14.writeNoException()
                goto L_0x000a
            L_0x11d9:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                r11.hy()
                r14.writeNoException()
                goto L_0x000a
            L_0x11e6:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                java.lang.String r2 = r13.readString()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x1212
                r0 = r10
            L_0x11f6:
                int r1 = r13.readInt()
                if (r1 == 0) goto L_0x1214
                r1 = r10
            L_0x11fd:
                int r3 = r13.readInt()
                android.content.Intent r0 = r11.a((java.lang.String) r2, (boolean) r0, (boolean) r1, (int) r3)
                r14.writeNoException()
                if (r0 == 0) goto L_0x1216
                r14.writeInt(r10)
                r0.writeToParcel(r14, r10)
                goto L_0x000a
            L_0x1212:
                r0 = r7
                goto L_0x11f6
            L_0x1214:
                r1 = r7
                goto L_0x11fd
            L_0x1216:
                r14.writeInt(r7)
                goto L_0x000a
            L_0x121b:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                int r1 = r13.readInt()
                if (r1 == 0) goto L_0x122f
                r7 = r10
            L_0x122f:
                r11.d((com.google.android.gms.games.internal.IGamesCallbacks) r0, (boolean) r7)
                r14.writeNoException()
                goto L_0x000a
            L_0x1237:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                java.lang.String r2 = r13.readString()
                int r3 = r13.readInt()
                if (r3 == 0) goto L_0x1253
                r7 = r10
            L_0x1253:
                r11.c((com.google.android.gms.games.internal.IGamesCallbacks) r0, (java.lang.String) r1, (java.lang.String) r2, (boolean) r7)
                r14.writeNoException()
                goto L_0x000a
            L_0x125b:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                int r2 = r13.readInt()
                if (r2 == 0) goto L_0x1273
                r7 = r10
            L_0x1273:
                r11.e((com.google.android.gms.games.internal.IGamesCallbacks) r0, (java.lang.String) r1, (boolean) r7)
                r14.writeNoException()
                goto L_0x000a
            L_0x127b:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r2 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r3 = r13.readString()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x12af
                com.google.android.gms.games.snapshot.SnapshotMetadataChangeCreator r0 = com.google.android.gms.games.snapshot.SnapshotMetadataChange.CREATOR
                com.google.android.gms.games.snapshot.SnapshotMetadataChange r0 = r0.createFromParcel((android.os.Parcel) r13)
                r1 = r0
            L_0x1299:
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x12b1
                android.os.Parcelable$Creator<com.google.android.gms.drive.Contents> r0 = com.google.android.gms.drive.Contents.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r13)
                com.google.android.gms.drive.Contents r0 = (com.google.android.gms.drive.Contents) r0
            L_0x12a7:
                r11.a((com.google.android.gms.games.internal.IGamesCallbacks) r2, (java.lang.String) r3, (com.google.android.gms.games.snapshot.SnapshotMetadataChange) r1, (com.google.android.gms.drive.Contents) r0)
                r14.writeNoException()
                goto L_0x000a
            L_0x12af:
                r1 = r6
                goto L_0x1299
            L_0x12b1:
                r0 = r6
                goto L_0x12a7
            L_0x12b3:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x12ce
                android.os.Parcelable$Creator<com.google.android.gms.drive.Contents> r0 = com.google.android.gms.drive.Contents.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r13)
                com.google.android.gms.drive.Contents r0 = (com.google.android.gms.drive.Contents) r0
            L_0x12c6:
                r11.a((com.google.android.gms.drive.Contents) r0)
                r14.writeNoException()
                goto L_0x000a
            L_0x12ce:
                r0 = r6
                goto L_0x12c6
            L_0x12d0:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                r11.r(r0, r1)
                r14.writeNoException()
                goto L_0x000a
            L_0x12e9:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r1 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r2 = r13.readString()
                java.lang.String r3 = r13.readString()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x1322
                com.google.android.gms.games.snapshot.SnapshotMetadataChangeCreator r0 = com.google.android.gms.games.snapshot.SnapshotMetadataChange.CREATOR
                com.google.android.gms.games.snapshot.SnapshotMetadataChange r4 = r0.createFromParcel((android.os.Parcel) r13)
            L_0x130a:
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x1324
                android.os.Parcelable$Creator<com.google.android.gms.drive.Contents> r0 = com.google.android.gms.drive.Contents.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r13)
                com.google.android.gms.drive.Contents r0 = (com.google.android.gms.drive.Contents) r0
                r5 = r0
            L_0x1319:
                r0 = r11
                r0.a((com.google.android.gms.games.internal.IGamesCallbacks) r1, (java.lang.String) r2, (java.lang.String) r3, (com.google.android.gms.games.snapshot.SnapshotMetadataChange) r4, (com.google.android.gms.drive.Contents) r5)
                r14.writeNoException()
                goto L_0x000a
            L_0x1322:
                r4 = r6
                goto L_0x130a
            L_0x1324:
                r5 = r6
                goto L_0x1319
            L_0x1326:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                int r0 = r11.ho()
                r14.writeNoException()
                r14.writeInt(r0)
                goto L_0x000a
            L_0x1337:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                int r0 = r11.hp()
                r14.writeNoException()
                r14.writeInt(r0)
                goto L_0x000a
            L_0x1348:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                r11.s(r0, r1)
                r14.writeNoException()
                goto L_0x000a
            L_0x1361:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                int r2 = r13.readInt()
                r11.b((com.google.android.gms.games.internal.IGamesCallbacks) r0, (java.lang.String) r1, (int) r2)
                r14.writeNoException()
                goto L_0x000a
            L_0x137e:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                int r2 = r13.readInt()
                r11.c((com.google.android.gms.games.internal.IGamesCallbacks) r0, (java.lang.String) r1, (int) r2)
                r14.writeNoException()
                goto L_0x000a
            L_0x139b:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r1 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r2 = r13.readString()
                int r3 = r13.readInt()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x13c7
                r4 = r10
            L_0x13b7:
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x13c9
                r5 = r10
            L_0x13be:
                r0 = r11
                r0.e(r1, r2, r3, r4, r5)
                r14.writeNoException()
                goto L_0x000a
            L_0x13c7:
                r4 = r7
                goto L_0x13b7
            L_0x13c9:
                r5 = r7
                goto L_0x13be
            L_0x13cb:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r1 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r2 = r13.readString()
                int r3 = r13.readInt()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x13f7
                r4 = r10
            L_0x13e7:
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x13f9
                r5 = r10
            L_0x13ee:
                r0 = r11
                r0.f(r1, r2, r3, r4, r5)
                r14.writeNoException()
                goto L_0x000a
            L_0x13f7:
                r4 = r7
                goto L_0x13e7
            L_0x13f9:
                r5 = r7
                goto L_0x13ee
            L_0x13fb:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                boolean r0 = r11.hz()
                r14.writeNoException()
                if (r0 == 0) goto L_0x140a
                r7 = r10
            L_0x140a:
                r14.writeInt(r7)
                goto L_0x000a
            L_0x140f:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x141b
                r7 = r10
            L_0x141b:
                r11.F(r7)
                r14.writeNoException()
                goto L_0x000a
            L_0x1423:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                r11.t(r0, r1)
                r14.writeNoException()
                goto L_0x000a
            L_0x143c:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                int r1 = r13.readInt()
                if (r1 == 0) goto L_0x1450
                r7 = r10
            L_0x1450:
                r11.e((com.google.android.gms.games.internal.IGamesCallbacks) r0, (boolean) r7)
                r14.writeNoException()
                goto L_0x000a
            L_0x1458:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                int r1 = r13.readInt()
                if (r1 == 0) goto L_0x146c
                r7 = r10
            L_0x146c:
                r11.f((com.google.android.gms.games.internal.IGamesCallbacks) r0, (boolean) r7)
                r14.writeNoException()
                goto L_0x000a
            L_0x1474:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                int r1 = r13.readInt()
                if (r1 == 0) goto L_0x1488
                r7 = r10
            L_0x1488:
                java.lang.String[] r1 = r13.createStringArray()
                r11.a((com.google.android.gms.games.internal.IGamesCallbacks) r0, (boolean) r7, (java.lang.String[]) r1)
                r14.writeNoException()
                goto L_0x000a
            L_0x1494:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                java.lang.String r0 = r13.readString()
                int r1 = r13.readInt()
                r11.l((java.lang.String) r0, (int) r1)
                r14.writeNoException()
                goto L_0x000a
            L_0x14a9:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                r11.u(r0, r1)
                r14.writeNoException()
                goto L_0x000a
            L_0x14c2:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r1 = r13.readString()
                java.lang.String r2 = r13.readString()
                r11.f(r0, r1, r2)
                r14.writeNoException()
                goto L_0x000a
            L_0x14df:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                int[] r1 = r13.createIntArray()
                int r2 = r13.readInt()
                int r3 = r13.readInt()
                if (r3 == 0) goto L_0x14fb
                r7 = r10
            L_0x14fb:
                r11.a((com.google.android.gms.games.internal.IGamesCallbacks) r0, (int[]) r1, (int) r2, (boolean) r7)
                r14.writeNoException()
                goto L_0x000a
            L_0x1503:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String[] r1 = r13.createStringArray()
                int r2 = r13.readInt()
                if (r2 == 0) goto L_0x151b
                r7 = r10
            L_0x151b:
                r11.a((com.google.android.gms.games.internal.IGamesCallbacks) r0, (java.lang.String[]) r1, (boolean) r7)
                r14.writeNoException()
                goto L_0x000a
            L_0x1523:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r1 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r2 = r13.readString()
                java.lang.String r3 = r13.readString()
                int[] r4 = r13.createIntArray()
                int r5 = r13.readInt()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x1550
                r6 = r10
            L_0x1547:
                r0 = r11
                r0.a((com.google.android.gms.games.internal.IGamesCallbacks) r1, (java.lang.String) r2, (java.lang.String) r3, (int[]) r4, (int) r5, (boolean) r6)
                r14.writeNoException()
                goto L_0x000a
            L_0x1550:
                r6 = r7
                goto L_0x1547
            L_0x1552:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r1 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r2 = r13.readString()
                java.lang.String r3 = r13.readString()
                java.lang.String[] r4 = r13.createStringArray()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x157b
                r5 = r10
            L_0x1572:
                r0 = r11
                r0.a((com.google.android.gms.games.internal.IGamesCallbacks) r1, (java.lang.String) r2, (java.lang.String) r3, (java.lang.String[]) r4, (boolean) r5)
                r14.writeNoException()
                goto L_0x000a
            L_0x157b:
                r5 = r7
                goto L_0x1572
            L_0x157d:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                long r2 = r13.readLong()
                r11.d((com.google.android.gms.games.internal.IGamesCallbacks) r0, (long) r2)
                r14.writeNoException()
                goto L_0x000a
            L_0x1596:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r0 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                long r2 = r13.readLong()
                java.lang.String r1 = r13.readString()
                r11.d((com.google.android.gms.games.internal.IGamesCallbacks) r0, (long) r2, (java.lang.String) r1)
                r14.writeNoException()
                goto L_0x000a
            L_0x15b3:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                long r0 = r13.readLong()
                r11.u(r0)
                r14.writeNoException()
                goto L_0x000a
            L_0x15c4:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                long r0 = r13.readLong()
                java.lang.String r2 = r13.readString()
                r11.d((long) r0, (java.lang.String) r2)
                r14.writeNoException()
                goto L_0x000a
            L_0x15d9:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                int[] r0 = r13.createIntArray()
                android.content.Intent r0 = r11.a((int[]) r0)
                r14.writeNoException()
                if (r0 == 0) goto L_0x15f3
                r14.writeInt(r10)
                r0.writeToParcel(r14, r10)
                goto L_0x000a
            L_0x15f3:
                r14.writeInt(r7)
                goto L_0x000a
            L_0x15f8:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                java.lang.String r0 = r13.readString()
                android.content.Intent r0 = r11.aU(r0)
                r14.writeNoException()
                if (r0 == 0) goto L_0x1612
                r14.writeInt(r10)
                r0.writeToParcel(r14, r10)
                goto L_0x000a
            L_0x1612:
                r14.writeInt(r7)
                goto L_0x000a
            L_0x1617:
                java.lang.String r0 = "com.google.android.gms.games.internal.IGamesService"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.games.internal.IGamesCallbacks r1 = com.google.android.gms.games.internal.IGamesCallbacks.Stub.ai(r0)
                java.lang.String r2 = r13.readString()
                java.lang.String r3 = r13.readString()
                int r4 = r13.readInt()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x1647
                r5 = r10
            L_0x1637:
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x1649
                r6 = r10
            L_0x163e:
                r0 = r11
                r0.b((com.google.android.gms.games.internal.IGamesCallbacks) r1, (java.lang.String) r2, (java.lang.String) r3, (int) r4, (boolean) r5, (boolean) r6)
                r14.writeNoException()
                goto L_0x000a
            L_0x1647:
                r5 = r7
                goto L_0x1637
            L_0x1649:
                r6 = r7
                goto L_0x163e
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.games.internal.IGamesService.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    void E(boolean z) throws RemoteException;

    void F(boolean z) throws RemoteException;

    int a(IGamesCallbacks iGamesCallbacks, byte[] bArr, String str, String str2) throws RemoteException;

    Intent a(int i, int i2, boolean z) throws RemoteException;

    Intent a(int i, byte[] bArr, int i2, String str) throws RemoteException;

    Intent a(ZInvitationCluster zInvitationCluster, String str, String str2) throws RemoteException;

    Intent a(GameRequestCluster gameRequestCluster, String str) throws RemoteException;

    Intent a(RoomEntity roomEntity, int i) throws RemoteException;

    Intent a(String str, boolean z, boolean z2, int i) throws RemoteException;

    Intent a(int[] iArr) throws RemoteException;

    Intent a(ParticipantEntity[] participantEntityArr, String str, String str2, Uri uri, Uri uri2) throws RemoteException;

    void a(long j, String str) throws RemoteException;

    void a(IBinder iBinder, Bundle bundle) throws RemoteException;

    void a(Contents contents) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, int i) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, int i, int i2, int i3) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, int i, int i2, boolean z, boolean z2) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, int i, int i2, String[] strArr, Bundle bundle) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, int i, boolean z, boolean z2) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, int i, int[] iArr) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, long j) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, long j, String str) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, Bundle bundle, int i, int i2) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, IBinder iBinder, int i, String[] strArr, Bundle bundle, boolean z, long j) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, IBinder iBinder, String str, boolean z, long j) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, String str, int i) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, String str, int i, int i2, int i3, boolean z) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, String str, int i, IBinder iBinder, Bundle bundle) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, String str, int i, boolean z) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, String str, int i, boolean z, boolean z2) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, String str, int i, boolean z, boolean z2, boolean z3, boolean z4) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, String str, int i, int[] iArr) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, String str, long j) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, String str, long j, String str2) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, String str, IBinder iBinder, Bundle bundle) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, String str, SnapshotMetadataChange snapshotMetadataChange, Contents contents) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, String str, String str2) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, String str, String str2, int i, int i2) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, String str, String str2, int i, int i2, int i3) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, String str, String str2, int i, int i2, int i3, boolean z) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, String str, String str2, int i, boolean z, boolean z2) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, String str, String str2, SnapshotMetadataChange snapshotMetadataChange, Contents contents) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, String str, String str2, boolean z) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, String str, String str2, int[] iArr, int i, boolean z) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, String str, String str2, String[] strArr) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, String str, String str2, String[] strArr, boolean z) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, String str, boolean z) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, String str, byte[] bArr, String str2, ParticipantResult[] participantResultArr) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, String str, byte[] bArr, ParticipantResult[] participantResultArr) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, String str, int[] iArr) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, String str, String[] strArr, int i, byte[] bArr, int i2) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, boolean z) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, boolean z, Bundle bundle) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, boolean z, String[] strArr) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, int[] iArr) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, int[] iArr, int i, boolean z) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, String[] strArr) throws RemoteException;

    void a(IGamesCallbacks iGamesCallbacks, String[] strArr, boolean z) throws RemoteException;

    Intent aR(String str) throws RemoteException;

    Intent aU(String str) throws RemoteException;

    String aV(String str) throws RemoteException;

    String aW(String str) throws RemoteException;

    void aX(String str) throws RemoteException;

    int aY(String str) throws RemoteException;

    Uri aZ(String str) throws RemoteException;

    int b(byte[] bArr, String str, String[] strArr) throws RemoteException;

    Intent b(int i, int i2, boolean z) throws RemoteException;

    void b(long j, String str) throws RemoteException;

    void b(IGamesCallbacks iGamesCallbacks) throws RemoteException;

    void b(IGamesCallbacks iGamesCallbacks, int i, boolean z, boolean z2) throws RemoteException;

    void b(IGamesCallbacks iGamesCallbacks, long j) throws RemoteException;

    void b(IGamesCallbacks iGamesCallbacks, long j, String str) throws RemoteException;

    void b(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException;

    void b(IGamesCallbacks iGamesCallbacks, String str, int i) throws RemoteException;

    void b(IGamesCallbacks iGamesCallbacks, String str, int i, int i2, int i3, boolean z) throws RemoteException;

    void b(IGamesCallbacks iGamesCallbacks, String str, int i, IBinder iBinder, Bundle bundle) throws RemoteException;

    void b(IGamesCallbacks iGamesCallbacks, String str, int i, boolean z) throws RemoteException;

    void b(IGamesCallbacks iGamesCallbacks, String str, int i, boolean z, boolean z2) throws RemoteException;

    void b(IGamesCallbacks iGamesCallbacks, String str, IBinder iBinder, Bundle bundle) throws RemoteException;

    void b(IGamesCallbacks iGamesCallbacks, String str, String str2) throws RemoteException;

    void b(IGamesCallbacks iGamesCallbacks, String str, String str2, int i, int i2, int i3, boolean z) throws RemoteException;

    void b(IGamesCallbacks iGamesCallbacks, String str, String str2, int i, boolean z, boolean z2) throws RemoteException;

    void b(IGamesCallbacks iGamesCallbacks, String str, String str2, boolean z) throws RemoteException;

    void b(IGamesCallbacks iGamesCallbacks, String str, boolean z) throws RemoteException;

    void b(IGamesCallbacks iGamesCallbacks, boolean z) throws RemoteException;

    void b(IGamesCallbacks iGamesCallbacks, String[] strArr) throws RemoteException;

    void b(String str, String str2, int i) throws RemoteException;

    void ba(String str) throws RemoteException;

    ParcelFileDescriptor bb(String str) throws RemoteException;

    void c(long j, String str) throws RemoteException;

    void c(IGamesCallbacks iGamesCallbacks) throws RemoteException;

    void c(IGamesCallbacks iGamesCallbacks, int i, boolean z, boolean z2) throws RemoteException;

    void c(IGamesCallbacks iGamesCallbacks, long j) throws RemoteException;

    void c(IGamesCallbacks iGamesCallbacks, long j, String str) throws RemoteException;

    void c(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException;

    void c(IGamesCallbacks iGamesCallbacks, String str, int i) throws RemoteException;

    void c(IGamesCallbacks iGamesCallbacks, String str, int i, boolean z, boolean z2) throws RemoteException;

    void c(IGamesCallbacks iGamesCallbacks, String str, String str2) throws RemoteException;

    void c(IGamesCallbacks iGamesCallbacks, String str, String str2, boolean z) throws RemoteException;

    void c(IGamesCallbacks iGamesCallbacks, String str, boolean z) throws RemoteException;

    void c(IGamesCallbacks iGamesCallbacks, boolean z) throws RemoteException;

    void c(IGamesCallbacks iGamesCallbacks, String[] strArr) throws RemoteException;

    void c(String str, String str2, int i) throws RemoteException;

    void ch(int i) throws RemoteException;

    void d(long j, String str) throws RemoteException;

    void d(IGamesCallbacks iGamesCallbacks) throws RemoteException;

    void d(IGamesCallbacks iGamesCallbacks, int i, boolean z, boolean z2) throws RemoteException;

    void d(IGamesCallbacks iGamesCallbacks, long j) throws RemoteException;

    void d(IGamesCallbacks iGamesCallbacks, long j, String str) throws RemoteException;

    void d(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException;

    void d(IGamesCallbacks iGamesCallbacks, String str, int i, boolean z, boolean z2) throws RemoteException;

    void d(IGamesCallbacks iGamesCallbacks, String str, String str2) throws RemoteException;

    void d(IGamesCallbacks iGamesCallbacks, String str, boolean z) throws RemoteException;

    void d(IGamesCallbacks iGamesCallbacks, boolean z) throws RemoteException;

    void e(IGamesCallbacks iGamesCallbacks) throws RemoteException;

    void e(IGamesCallbacks iGamesCallbacks, int i, boolean z, boolean z2) throws RemoteException;

    void e(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException;

    void e(IGamesCallbacks iGamesCallbacks, String str, int i, boolean z, boolean z2) throws RemoteException;

    void e(IGamesCallbacks iGamesCallbacks, String str, String str2) throws RemoteException;

    void e(IGamesCallbacks iGamesCallbacks, String str, boolean z) throws RemoteException;

    void e(IGamesCallbacks iGamesCallbacks, boolean z) throws RemoteException;

    Bundle ea() throws RemoteException;

    void f(IGamesCallbacks iGamesCallbacks) throws RemoteException;

    void f(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException;

    void f(IGamesCallbacks iGamesCallbacks, String str, int i, boolean z, boolean z2) throws RemoteException;

    void f(IGamesCallbacks iGamesCallbacks, String str, String str2) throws RemoteException;

    void f(IGamesCallbacks iGamesCallbacks, boolean z) throws RemoteException;

    ParcelFileDescriptor g(Uri uri) throws RemoteException;

    void g(IGamesCallbacks iGamesCallbacks) throws RemoteException;

    void g(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException;

    String gU() throws RemoteException;

    String gV() throws RemoteException;

    Intent gY() throws RemoteException;

    Intent gZ() throws RemoteException;

    RoomEntity h(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException;

    void h(IGamesCallbacks iGamesCallbacks) throws RemoteException;

    Intent ha() throws RemoteException;

    Intent hb() throws RemoteException;

    Intent hg() throws RemoteException;

    Intent hh() throws RemoteException;

    int hi() throws RemoteException;

    String hj() throws RemoteException;

    int hk() throws RemoteException;

    Intent hl() throws RemoteException;

    int hm() throws RemoteException;

    int hn() throws RemoteException;

    int ho() throws RemoteException;

    int hp() throws RemoteException;

    void hr() throws RemoteException;

    DataHolder ht() throws RemoteException;

    boolean hu() throws RemoteException;

    DataHolder hv() throws RemoteException;

    void hw() throws RemoteException;

    Intent hx() throws RemoteException;

    void hy() throws RemoteException;

    boolean hz() throws RemoteException;

    void i(IGamesCallbacks iGamesCallbacks) throws RemoteException;

    void i(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException;

    void j(IGamesCallbacks iGamesCallbacks) throws RemoteException;

    void j(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException;

    void k(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException;

    void l(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException;

    void l(String str, int i) throws RemoteException;

    void m(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException;

    void m(String str, int i) throws RemoteException;

    void m(String str, String str2) throws RemoteException;

    void n(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException;

    void n(String str, int i) throws RemoteException;

    void n(String str, String str2) throws RemoteException;

    void o(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException;

    void p(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException;

    void p(String str, int i) throws RemoteException;

    void q(long j) throws RemoteException;

    void q(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException;

    void q(String str, int i) throws RemoteException;

    void r(long j) throws RemoteException;

    void r(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException;

    void s(long j) throws RemoteException;

    void s(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException;

    void t(long j) throws RemoteException;

    void t(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException;

    void u(long j) throws RemoteException;

    void u(IGamesCallbacks iGamesCallbacks, String str) throws RemoteException;
}
