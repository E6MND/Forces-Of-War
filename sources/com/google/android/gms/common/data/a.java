package com.google.android.gms.common.data;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class a implements SafeParcelable {
    public static final Parcelable.Creator<a> CREATOR = new b();
    final int AQ;
    ParcelFileDescriptor Et;
    private Bitmap Eu;
    private boolean Ev;
    private File Ew;
    final int xJ;

    a(int i, ParcelFileDescriptor parcelFileDescriptor, int i2) {
        this.xJ = i;
        this.Et = parcelFileDescriptor;
        this.AQ = i2;
        this.Eu = null;
        this.Ev = false;
    }

    public a(Bitmap bitmap) {
        this.xJ = 1;
        this.Et = null;
        this.AQ = 0;
        this.Eu = bitmap;
        this.Ev = true;
    }

    private void a(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            Log.w("BitmapTeleporter", "Could not close stream", e);
        }
    }

    private FileOutputStream eO() {
        if (this.Ew == null) {
            throw new IllegalStateException("setTempDir() must be called before writing this object to a parcel");
        }
        try {
            File createTempFile = File.createTempFile("teleporter", ".tmp", this.Ew);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
                this.Et = ParcelFileDescriptor.open(createTempFile, 268435456);
                createTempFile.delete();
                return fileOutputStream;
            } catch (FileNotFoundException e) {
                throw new IllegalStateException("Temporary file is somehow already deleted");
            }
        } catch (IOException e2) {
            throw new IllegalStateException("Could not create temporary file", e2);
        }
    }

    public void a(File file) {
        if (file == null) {
            throw new NullPointerException("Cannot set null temp directory");
        }
        this.Ew = file;
    }

    public int describeContents() {
        return 0;
    }

    public Bitmap eN() {
        if (!this.Ev) {
            DataInputStream dataInputStream = new DataInputStream(new ParcelFileDescriptor.AutoCloseInputStream(this.Et));
            try {
                byte[] bArr = new byte[dataInputStream.readInt()];
                int readInt = dataInputStream.readInt();
                int readInt2 = dataInputStream.readInt();
                Bitmap.Config valueOf = Bitmap.Config.valueOf(dataInputStream.readUTF());
                dataInputStream.read(bArr);
                a((Closeable) dataInputStream);
                ByteBuffer wrap = ByteBuffer.wrap(bArr);
                Bitmap createBitmap = Bitmap.createBitmap(readInt, readInt2, valueOf);
                createBitmap.copyPixelsFromBuffer(wrap);
                this.Eu = createBitmap;
                this.Ev = true;
            } catch (IOException e) {
                throw new IllegalStateException("Could not read from parcel file descriptor", e);
            } catch (Throwable th) {
                a((Closeable) dataInputStream);
                throw th;
            }
        }
        return this.Eu;
    }

    public void writeToParcel(Parcel dest, int flags) {
        if (this.Et == null) {
            Bitmap bitmap = this.Eu;
            ByteBuffer allocate = ByteBuffer.allocate(bitmap.getRowBytes() * bitmap.getHeight());
            bitmap.copyPixelsToBuffer(allocate);
            byte[] array = allocate.array();
            DataOutputStream dataOutputStream = new DataOutputStream(eO());
            try {
                dataOutputStream.writeInt(array.length);
                dataOutputStream.writeInt(bitmap.getWidth());
                dataOutputStream.writeInt(bitmap.getHeight());
                dataOutputStream.writeUTF(bitmap.getConfig().toString());
                dataOutputStream.write(array);
                a((Closeable) dataOutputStream);
            } catch (IOException e) {
                throw new IllegalStateException("Could not write into unlinked file", e);
            } catch (Throwable th) {
                a((Closeable) dataOutputStream);
                throw th;
            }
        }
        b.a(this, dest, flags);
    }
}
