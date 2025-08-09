package com.google.android.gms.games.snapshot;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.internal.hl;
import com.google.android.gms.internal.il;

public final class SnapshotMetadataEntity implements SafeParcelable, SnapshotMetadata {
    public static final SnapshotMetadataEntityCreator CREATOR = new SnapshotMetadataEntityCreator();
    private final String HV;
    private final String Mm;
    private final String NH;
    private final GameEntity Rq;
    private final Uri Ue;
    private final PlayerEntity Ui;
    private final String Uj;
    private final long Uk;
    private final long Ul;
    private final float Um;
    private final String Un;
    private final int xJ;

    SnapshotMetadataEntity(int versionCode, GameEntity game, PlayerEntity owner, String snapshotId, Uri coverImageUri, String coverImageUrl, String title, String description, long lastModifiedTimestamp, long playedTime, float coverImageAspectRatio, String uniqueName) {
        this.xJ = versionCode;
        this.Rq = game;
        this.Ui = owner;
        this.NH = snapshotId;
        this.Ue = coverImageUri;
        this.Uj = coverImageUrl;
        this.Um = coverImageAspectRatio;
        this.HV = title;
        this.Mm = description;
        this.Uk = lastModifiedTimestamp;
        this.Ul = playedTime;
        this.Un = uniqueName;
    }

    public SnapshotMetadataEntity(SnapshotMetadata snapshotMetadata) {
        this.xJ = 3;
        this.Rq = new GameEntity(snapshotMetadata.getGame());
        this.Ui = new PlayerEntity(snapshotMetadata.getOwner());
        this.NH = snapshotMetadata.getSnapshotId();
        this.Ue = snapshotMetadata.getCoverImageUri();
        this.Uj = snapshotMetadata.getCoverImageUrl();
        this.Um = snapshotMetadata.getCoverImageAspectRatio();
        this.HV = snapshotMetadata.getTitle();
        this.Mm = snapshotMetadata.getDescription();
        this.Uk = snapshotMetadata.getLastModifiedTimestamp();
        this.Ul = snapshotMetadata.getPlayedTime();
        this.Un = snapshotMetadata.getUniqueName();
    }

    static int a(SnapshotMetadata snapshotMetadata) {
        return hl.hashCode(snapshotMetadata.getGame(), snapshotMetadata.getOwner(), snapshotMetadata.getSnapshotId(), snapshotMetadata.getCoverImageUri(), Float.valueOf(snapshotMetadata.getCoverImageAspectRatio()), snapshotMetadata.getTitle(), snapshotMetadata.getDescription(), Long.valueOf(snapshotMetadata.getLastModifiedTimestamp()), Long.valueOf(snapshotMetadata.getPlayedTime()), snapshotMetadata.getUniqueName());
    }

    static boolean a(SnapshotMetadata snapshotMetadata, Object obj) {
        if (!(obj instanceof SnapshotMetadata)) {
            return false;
        }
        if (snapshotMetadata == obj) {
            return true;
        }
        SnapshotMetadata snapshotMetadata2 = (SnapshotMetadata) obj;
        return hl.equal(snapshotMetadata2.getGame(), snapshotMetadata.getGame()) && hl.equal(snapshotMetadata2.getOwner(), snapshotMetadata.getOwner()) && hl.equal(snapshotMetadata2.getSnapshotId(), snapshotMetadata.getSnapshotId()) && hl.equal(snapshotMetadata2.getCoverImageUri(), snapshotMetadata.getCoverImageUri()) && hl.equal(Float.valueOf(snapshotMetadata2.getCoverImageAspectRatio()), Float.valueOf(snapshotMetadata.getCoverImageAspectRatio())) && hl.equal(snapshotMetadata2.getTitle(), snapshotMetadata.getTitle()) && hl.equal(snapshotMetadata2.getDescription(), snapshotMetadata.getDescription()) && hl.equal(Long.valueOf(snapshotMetadata2.getLastModifiedTimestamp()), Long.valueOf(snapshotMetadata.getLastModifiedTimestamp())) && hl.equal(Long.valueOf(snapshotMetadata2.getPlayedTime()), Long.valueOf(snapshotMetadata.getPlayedTime())) && hl.equal(snapshotMetadata2.getUniqueName(), snapshotMetadata.getUniqueName());
    }

    static String b(SnapshotMetadata snapshotMetadata) {
        return hl.e(snapshotMetadata).a("Game", snapshotMetadata.getGame()).a("Owner", snapshotMetadata.getOwner()).a("SnapshotId", snapshotMetadata.getSnapshotId()).a("CoverImageUri", snapshotMetadata.getCoverImageUri()).a("CoverImageUrl", snapshotMetadata.getCoverImageUrl()).a("CoverImageAspectRatio", Float.valueOf(snapshotMetadata.getCoverImageAspectRatio())).a("Description", snapshotMetadata.getDescription()).a("LastModifiedTimestamp", Long.valueOf(snapshotMetadata.getLastModifiedTimestamp())).a("PlayedTime", Long.valueOf(snapshotMetadata.getPlayedTime())).a("UniqueName", snapshotMetadata.getUniqueName()).toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public SnapshotMetadata freeze() {
        return this;
    }

    public float getCoverImageAspectRatio() {
        return this.Um;
    }

    public Uri getCoverImageUri() {
        return this.Ue;
    }

    public String getCoverImageUrl() {
        return this.Uj;
    }

    public String getDescription() {
        return this.Mm;
    }

    public void getDescription(CharArrayBuffer dataOut) {
        il.b(this.Mm, dataOut);
    }

    public Game getGame() {
        return this.Rq;
    }

    public long getLastModifiedTimestamp() {
        return this.Uk;
    }

    public Player getOwner() {
        return this.Ui;
    }

    public long getPlayedTime() {
        return this.Ul;
    }

    public String getSnapshotId() {
        return this.NH;
    }

    public String getTitle() {
        return this.HV;
    }

    public String getUniqueName() {
        return this.Un;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public int hashCode() {
        return a(this);
    }

    public boolean isDataValid() {
        return true;
    }

    public String toString() {
        return b(this);
    }

    public void writeToParcel(Parcel out, int flags) {
        SnapshotMetadataEntityCreator.a(this, out, flags);
    }
}
