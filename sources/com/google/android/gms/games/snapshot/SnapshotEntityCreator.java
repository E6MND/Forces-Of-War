package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.Contents;

public class SnapshotEntityCreator implements Parcelable.Creator<SnapshotEntity> {
    public static final int CONTENT_DESCRIPTION = 0;

    static void a(SnapshotEntity snapshotEntity, Parcel parcel, int i) {
        int C = b.C(parcel);
        b.a(parcel, 1, (Parcelable) snapshotEntity.getMetadata(), i, false);
        b.c(parcel, 1000, snapshotEntity.getVersionCode());
        b.a(parcel, 2, (Parcelable) snapshotEntity.getContents(), i, false);
        b.G(parcel, C);
    }

    public SnapshotEntity createFromParcel(Parcel parcel) {
        Contents contents;
        SnapshotMetadataEntity snapshotMetadataEntity;
        int i;
        Contents contents2 = null;
        int B = a.B(parcel);
        int i2 = 0;
        SnapshotMetadataEntity snapshotMetadataEntity2 = null;
        while (parcel.dataPosition() < B) {
            int A = a.A(parcel);
            switch (a.ar(A)) {
                case 1:
                    i = i2;
                    SnapshotMetadataEntity snapshotMetadataEntity3 = (SnapshotMetadataEntity) a.a(parcel, A, SnapshotMetadataEntity.CREATOR);
                    contents = contents2;
                    snapshotMetadataEntity = snapshotMetadataEntity3;
                    break;
                case 2:
                    contents = (Contents) a.a(parcel, A, Contents.CREATOR);
                    snapshotMetadataEntity = snapshotMetadataEntity2;
                    i = i2;
                    break;
                case 1000:
                    Contents contents3 = contents2;
                    snapshotMetadataEntity = snapshotMetadataEntity2;
                    i = a.g(parcel, A);
                    contents = contents3;
                    break;
                default:
                    a.b(parcel, A);
                    contents = contents2;
                    snapshotMetadataEntity = snapshotMetadataEntity2;
                    i = i2;
                    break;
            }
            i2 = i;
            snapshotMetadataEntity2 = snapshotMetadataEntity;
            contents2 = contents;
        }
        if (parcel.dataPosition() == B) {
            return new SnapshotEntity(i2, snapshotMetadataEntity2, contents2);
        }
        throw new a.C0014a("Overread allowed size end=" + B, parcel);
    }

    public SnapshotEntity[] newArray(int size) {
        return new SnapshotEntity[size];
    }
}
