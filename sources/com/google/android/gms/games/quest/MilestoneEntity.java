package com.google.android.gms.games.quest;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.hl;

public final class MilestoneEntity implements SafeParcelable, Milestone {
    public static final MilestoneEntityCreator CREATOR = new MilestoneEntityCreator();
    private final String Nf;
    private final String Oq;
    private final long TJ;
    private final long TK;
    private final byte[] TL;
    private final int mState;
    private final int xJ;

    MilestoneEntity(int versionCode, String milestoneId, long currentProgress, long targetProgress, byte[] completionBlob, int state, String eventId) {
        this.xJ = versionCode;
        this.Oq = milestoneId;
        this.TJ = currentProgress;
        this.TK = targetProgress;
        this.TL = completionBlob;
        this.mState = state;
        this.Nf = eventId;
    }

    public MilestoneEntity(Milestone milestone) {
        this.xJ = 4;
        this.Oq = milestone.getMilestoneId();
        this.TJ = milestone.getCurrentProgress();
        this.TK = milestone.getTargetProgress();
        this.mState = milestone.getState();
        this.Nf = milestone.getEventId();
        byte[] completionRewardData = milestone.getCompletionRewardData();
        if (completionRewardData == null) {
            this.TL = null;
            return;
        }
        this.TL = new byte[completionRewardData.length];
        System.arraycopy(completionRewardData, 0, this.TL, 0, completionRewardData.length);
    }

    static int a(Milestone milestone) {
        return hl.hashCode(milestone.getMilestoneId(), Long.valueOf(milestone.getCurrentProgress()), Long.valueOf(milestone.getTargetProgress()), Integer.valueOf(milestone.getState()), milestone.getEventId());
    }

    static boolean a(Milestone milestone, Object obj) {
        if (!(obj instanceof Milestone)) {
            return false;
        }
        if (milestone == obj) {
            return true;
        }
        Milestone milestone2 = (Milestone) obj;
        return hl.equal(milestone2.getMilestoneId(), milestone.getMilestoneId()) && hl.equal(Long.valueOf(milestone2.getCurrentProgress()), Long.valueOf(milestone.getCurrentProgress())) && hl.equal(Long.valueOf(milestone2.getTargetProgress()), Long.valueOf(milestone.getTargetProgress())) && hl.equal(Integer.valueOf(milestone2.getState()), Integer.valueOf(milestone.getState())) && hl.equal(milestone2.getEventId(), milestone.getEventId());
    }

    static String b(Milestone milestone) {
        return hl.e(milestone).a("MilestoneId", milestone.getMilestoneId()).a("CurrentProgress", Long.valueOf(milestone.getCurrentProgress())).a("TargetProgress", Long.valueOf(milestone.getTargetProgress())).a("State", Integer.valueOf(milestone.getState())).a("CompletionRewardData", milestone.getCompletionRewardData()).a("EventId", milestone.getEventId()).toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public Milestone freeze() {
        return this;
    }

    public byte[] getCompletionRewardData() {
        return this.TL;
    }

    public long getCurrentProgress() {
        return this.TJ;
    }

    public String getEventId() {
        return this.Nf;
    }

    public String getMilestoneId() {
        return this.Oq;
    }

    public int getState() {
        return this.mState;
    }

    public long getTargetProgress() {
        return this.TK;
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
        MilestoneEntityCreator.a(this, out, flags);
    }
}
