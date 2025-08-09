package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.games.internal.GamesLog;
import com.google.android.gms.internal.hl;
import com.google.android.gms.internal.hn;
import com.google.android.gms.internal.il;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public final class SnapshotEntity implements SafeParcelable, Snapshot {
    public static final SnapshotEntityCreator CREATOR = new SnapshotEntityCreator();
    private static final Object Ub = new Object();
    private Contents HD;
    private final SnapshotMetadataEntity Uc;
    private final int xJ;

    SnapshotEntity(int versionCode, SnapshotMetadata metadata, Contents contents) {
        this.xJ = versionCode;
        this.Uc = new SnapshotMetadataEntity(metadata);
        this.HD = contents;
    }

    public SnapshotEntity(SnapshotMetadata metadata, Contents contents) {
        this(1, metadata, contents);
    }

    private boolean a(int i, byte[] bArr, int i2, int i3, boolean z) {
        boolean z2;
        hn.b(this.HD, (Object) "Must provide a previously opened Snapshot");
        synchronized (Ub) {
            FileOutputStream fileOutputStream = new FileOutputStream(this.HD.getParcelFileDescriptor().getFileDescriptor());
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            try {
                FileChannel channel = fileOutputStream.getChannel();
                channel.position((long) i);
                bufferedOutputStream.write(bArr, i2, i3);
                if (z) {
                    channel.truncate((long) bArr.length);
                }
                bufferedOutputStream.flush();
                z2 = true;
            } catch (IOException e) {
                GamesLog.a("Snapshot", "Failed to write snapshot data", e);
                z2 = false;
            }
        }
        return z2;
    }

    static boolean a(Snapshot snapshot, Object obj) {
        if (!(obj instanceof Snapshot)) {
            return false;
        }
        if (snapshot == obj) {
            return true;
        }
        Snapshot snapshot2 = (Snapshot) obj;
        return hl.equal(snapshot2.getMetadata(), snapshot.getMetadata()) && hl.equal(snapshot2.getContents(), snapshot.getContents());
    }

    static int b(Snapshot snapshot) {
        return hl.hashCode(snapshot.getMetadata(), snapshot.getContents());
    }

    static String c(Snapshot snapshot) {
        return hl.e(snapshot).a("Metadata", snapshot.getMetadata()).a("HasContents", Boolean.valueOf(snapshot.getContents() != null)).toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public Snapshot freeze() {
        return this;
    }

    public Contents getContents() {
        return this.HD;
    }

    public SnapshotMetadata getMetadata() {
        return this.Uc;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public int hashCode() {
        return b(this);
    }

    public void iH() {
        this.HD.close();
        this.HD = null;
    }

    public boolean isDataValid() {
        return true;
    }

    public boolean modifyBytes(int dstOffset, byte[] content, int srcOffset, int count) {
        return a(dstOffset, content, srcOffset, content.length, false);
    }

    public byte[] readFully() {
        byte[] a;
        hn.b(this.HD, (Object) "Must provide a previously opened Snapshot");
        synchronized (Ub) {
            FileInputStream fileInputStream = new FileInputStream(this.HD.getParcelFileDescriptor().getFileDescriptor());
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            try {
                fileInputStream.getChannel().position(0);
            } catch (IOException e) {
                GamesLog.a("Snapshot", "Failed to read snapshot data", e);
            }
            a = il.a(bufferedInputStream, false);
        }
        return a;
    }

    public String toString() {
        return c(this);
    }

    public boolean writeBytes(byte[] content) {
        return a(0, content, 0, content.length, true);
    }

    public void writeToParcel(Parcel out, int flags) {
        SnapshotEntityCreator.a(this, out, flags);
    }
}
