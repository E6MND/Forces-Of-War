package com.google.android.gms.games.internal;

import android.net.LocalSocket;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.games.multiplayer.realtime.RealTimeSocket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

final class RealTimeSocketImpl implements RealTimeSocket {
    private ParcelFileDescriptor Fg;
    private final String On;
    private final LocalSocket Pa;

    RealTimeSocketImpl(LocalSocket localSocket, String participantId) {
        this.Pa = localSocket;
        this.On = participantId;
    }

    public void close() throws IOException {
        this.Pa.close();
    }

    public InputStream getInputStream() throws IOException {
        return this.Pa.getInputStream();
    }

    public OutputStream getOutputStream() throws IOException {
        return this.Pa.getOutputStream();
    }

    public ParcelFileDescriptor getParcelFileDescriptor() throws IOException {
        if (this.Fg == null && !isClosed()) {
            Parcel obtain = Parcel.obtain();
            obtain.writeFileDescriptor(this.Pa.getFileDescriptor());
            obtain.setDataPosition(0);
            this.Fg = obtain.readFileDescriptor();
        }
        return this.Fg;
    }

    public boolean isClosed() {
        return !this.Pa.isConnected() && !this.Pa.isBound();
    }
}
