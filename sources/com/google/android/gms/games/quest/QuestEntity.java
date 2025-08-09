package com.google.android.gms.games.quest;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.internal.hl;
import com.google.android.gms.internal.il;
import java.util.ArrayList;
import java.util.List;

public final class QuestEntity implements SafeParcelable, Quest {
    public static final QuestEntityCreator CREATOR = new QuestEntityCreator();
    private final int AQ;
    private final String Mm;
    private final GameEntity Rq;
    private final String TM;
    private final long TN;
    private final Uri TO;
    private final String TP;
    private final long TQ;
    private final Uri TR;
    private final String TS;
    private final long TT;
    private final long TU;
    private final ArrayList<MilestoneEntity> TV;
    private final long Ty;
    private final String mName;
    private final int mState;
    private final int xJ;

    QuestEntity(int versionCode, GameEntity game, String questId, long acceptedTimestamp, Uri bannerImageUri, String bannerImageUrl, String description, long endTimestamp, long lastUpdatedTimestamp, Uri iconImageUri, String iconImageUrl, String name, long notifyTimestamp, long startTimestamp, int state, int type, ArrayList<MilestoneEntity> milestones) {
        this.xJ = versionCode;
        this.Rq = game;
        this.TM = questId;
        this.TN = acceptedTimestamp;
        this.TO = bannerImageUri;
        this.TP = bannerImageUrl;
        this.Mm = description;
        this.TQ = endTimestamp;
        this.Ty = lastUpdatedTimestamp;
        this.TR = iconImageUri;
        this.TS = iconImageUrl;
        this.mName = name;
        this.TT = notifyTimestamp;
        this.TU = startTimestamp;
        this.mState = state;
        this.AQ = type;
        this.TV = milestones;
    }

    public QuestEntity(Quest quest) {
        this.xJ = 2;
        this.Rq = new GameEntity(quest.getGame());
        this.TM = quest.getQuestId();
        this.TN = quest.getAcceptedTimestamp();
        this.Mm = quest.getDescription();
        this.TO = quest.getBannerImageUri();
        this.TP = quest.getBannerImageUrl();
        this.TQ = quest.getEndTimestamp();
        this.TR = quest.getIconImageUri();
        this.TS = quest.getIconImageUrl();
        this.Ty = quest.getLastUpdatedTimestamp();
        this.mName = quest.getName();
        this.TT = quest.iF();
        this.TU = quest.getStartTimestamp();
        this.mState = quest.getState();
        this.AQ = quest.getType();
        List<Milestone> iE = quest.iE();
        int size = iE.size();
        this.TV = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            this.TV.add((MilestoneEntity) iE.get(i).freeze());
        }
    }

    static int a(Quest quest) {
        return hl.hashCode(quest.getGame(), quest.getQuestId(), Long.valueOf(quest.getAcceptedTimestamp()), quest.getBannerImageUri(), quest.getDescription(), Long.valueOf(quest.getEndTimestamp()), quest.getIconImageUri(), Long.valueOf(quest.getLastUpdatedTimestamp()), quest.iE(), quest.getName(), Long.valueOf(quest.iF()), Long.valueOf(quest.getStartTimestamp()), Integer.valueOf(quest.getState()));
    }

    static boolean a(Quest quest, Object obj) {
        if (!(obj instanceof Quest)) {
            return false;
        }
        if (quest == obj) {
            return true;
        }
        Quest quest2 = (Quest) obj;
        return hl.equal(quest2.getGame(), quest.getGame()) && hl.equal(quest2.getQuestId(), quest.getQuestId()) && hl.equal(Long.valueOf(quest2.getAcceptedTimestamp()), Long.valueOf(quest.getAcceptedTimestamp())) && hl.equal(quest2.getBannerImageUri(), quest.getBannerImageUri()) && hl.equal(quest2.getDescription(), quest.getDescription()) && hl.equal(Long.valueOf(quest2.getEndTimestamp()), Long.valueOf(quest.getEndTimestamp())) && hl.equal(quest2.getIconImageUri(), quest.getIconImageUri()) && hl.equal(Long.valueOf(quest2.getLastUpdatedTimestamp()), Long.valueOf(quest.getLastUpdatedTimestamp())) && hl.equal(quest2.iE(), quest.iE()) && hl.equal(quest2.getName(), quest.getName()) && hl.equal(Long.valueOf(quest2.iF()), Long.valueOf(quest.iF())) && hl.equal(Long.valueOf(quest2.getStartTimestamp()), Long.valueOf(quest.getStartTimestamp())) && hl.equal(Integer.valueOf(quest2.getState()), Integer.valueOf(quest.getState()));
    }

    static String b(Quest quest) {
        return hl.e(quest).a("Game", quest.getGame()).a("QuestId", quest.getQuestId()).a("AcceptedTimestamp", Long.valueOf(quest.getAcceptedTimestamp())).a("BannerImageUri", quest.getBannerImageUri()).a("BannerImageUrl", quest.getBannerImageUrl()).a("Description", quest.getDescription()).a("EndTimestamp", Long.valueOf(quest.getEndTimestamp())).a("IconImageUri", quest.getIconImageUri()).a("IconImageUrl", quest.getIconImageUrl()).a("LastUpdatedTimestamp", Long.valueOf(quest.getLastUpdatedTimestamp())).a("Milestones", quest.iE()).a("Name", quest.getName()).a("NotifyTimestamp", Long.valueOf(quest.iF())).a("StartTimestamp", Long.valueOf(quest.getStartTimestamp())).a("State", Integer.valueOf(quest.getState())).toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public Quest freeze() {
        return this;
    }

    public long getAcceptedTimestamp() {
        return this.TN;
    }

    public Uri getBannerImageUri() {
        return this.TO;
    }

    public String getBannerImageUrl() {
        return this.TP;
    }

    public Milestone getCurrentMilestone() {
        return iE().get(0);
    }

    public String getDescription() {
        return this.Mm;
    }

    public void getDescription(CharArrayBuffer dataOut) {
        il.b(this.Mm, dataOut);
    }

    public long getEndTimestamp() {
        return this.TQ;
    }

    public Game getGame() {
        return this.Rq;
    }

    public Uri getIconImageUri() {
        return this.TR;
    }

    public String getIconImageUrl() {
        return this.TS;
    }

    public long getLastUpdatedTimestamp() {
        return this.Ty;
    }

    public String getName() {
        return this.mName;
    }

    public void getName(CharArrayBuffer dataOut) {
        il.b(this.mName, dataOut);
    }

    public String getQuestId() {
        return this.TM;
    }

    public long getStartTimestamp() {
        return this.TU;
    }

    public int getState() {
        return this.mState;
    }

    public int getType() {
        return this.AQ;
    }

    public int getVersionCode() {
        return this.xJ;
    }

    public int hashCode() {
        return a(this);
    }

    public List<Milestone> iE() {
        return new ArrayList(this.TV);
    }

    public long iF() {
        return this.TT;
    }

    public boolean isDataValid() {
        return true;
    }

    public boolean isEndingSoon() {
        return this.TT <= System.currentTimeMillis() + 1800000;
    }

    public String toString() {
        return b(this);
    }

    public void writeToParcel(Parcel out, int flags) {
        QuestEntityCreator.a(this, out, flags);
    }
}
