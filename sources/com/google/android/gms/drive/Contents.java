package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Contents implements SafeParcelable {
    public static final Parcelable.Creator<Contents> CREATOR = new a();
    final ParcelFileDescriptor Fg;
    private boolean HA = false;
    private boolean HB = false;
    final int Hv;
    final DriveId Hw;
    String Hx;
    boolean Hy;
    private boolean Hz = false;
    private boolean mClosed = false;
    final int qX;
    final int xJ;

    Contents(int versionCode, ParcelFileDescriptor parcelFileDescriptor, int requestId, int mode, DriveId driveId, String baseContentHash, boolean validForConflictDetection) {
        this.xJ = versionCode;
        this.Fg = parcelFileDescriptor;
        this.qX = requestId;
        this.Hv = mode;
        this.Hw = driveId;
        this.Hx = baseContentHash;
        this.Hy = validForConflictDetection;
    }

    public void close() {
        this.mClosed = true;
    }

    public int describeContents() {
        return 0;
    }

    public DriveId getDriveId() {
        return this.Hw;
    }

    public InputStream getInputStream() {
        if (this.mClosed) {
            throw new IllegalStateException("Contents have been closed, cannot access the input stream.");
        } else if (this.Hv != 268435456) {
            throw new IllegalStateException("getInputStream() can only be used with contents opened with MODE_READ_ONLY.");
        } else if (this.Hz) {
            throw new IllegalStateException("getInputStream() can only be called once per Contents instance.");
        } else {
            this.Hz = true;
            return new FileInputStream(this.Fg.getFileDescriptor());
        }
    }

    public int getMode() {
        return this.Hv;
    }

    public OutputStream getOutputStream() {
        if (this.mClosed) {
            throw new IllegalStateException("Contents have been closed, cannot access the output stream.");
        } else if (this.Hv != 536870912) {
            throw new IllegalStateException("getOutputStream() can only be used with contents opened with MODE_WRITE_ONLY.");
        } else if (this.HA) {
            throw new IllegalStateException("getOutputStream() can only be called once per Contents instance.");
        } else {
            this.HA = true;
            return new FileOutputStream(this.Fg.getFileDescriptor());
        }
    }

    public ParcelFileDescriptor getParcelFileDescriptor() {
        if (!this.mClosed) {
            return this.Fg;
        }
        throw new IllegalStateException("Contents have been closed, cannot access the output stream.");
    }

    public int getRequestId() {
        return this.qX;
    }

    public void writeToParcel(Parcel dest, int flags) {
        a.a(this, dest, flags);
    }
}
