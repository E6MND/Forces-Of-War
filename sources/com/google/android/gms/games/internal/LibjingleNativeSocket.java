package com.google.android.gms.games.internal;

import android.os.ParcelFileDescriptor;
import com.google.android.gms.games.multiplayer.realtime.RealTimeSocket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class LibjingleNativeSocket implements RealTimeSocket {
    private static final String TAG = LibjingleNativeSocket.class.getSimpleName();
    private final ParcelFileDescriptor Fg;
    private final InputStream OT;
    private final OutputStream OU;

    LibjingleNativeSocket(ParcelFileDescriptor parcelFileDescriptor) {
        this.Fg = parcelFileDescriptor;
        this.OT = new ParcelFileDescriptor.AutoCloseInputStream(parcelFileDescriptor);
        this.OU = new ParcelFileDescriptor.AutoCloseOutputStream(parcelFileDescriptor);
    }

    public void close() throws IOException {
        this.Fg.close();
    }

    public InputStream getInputStream() throws IOException {
        return this.OT;
    }

    public OutputStream getOutputStream() throws IOException {
        return this.OU;
    }

    public ParcelFileDescriptor getParcelFileDescriptor() throws IOException {
        return this.Fg;
    }

    public boolean isClosed() {
        try {
            this.OT.available();
            return false;
        } catch (IOException e) {
            return true;
        }
    }
}
