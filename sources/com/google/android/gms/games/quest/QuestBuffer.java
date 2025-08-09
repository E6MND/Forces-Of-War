package com.google.android.gms.games.quest;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.g;

public final class QuestBuffer extends g<Quest> {
    public QuestBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    /* access modifiers changed from: protected */
    public String eU() {
        return "external_quest_id";
    }

    /* access modifiers changed from: protected */
    /* renamed from: j */
    public Quest c(int i, int i2) {
        return new QuestRef(this.DD, i, i2);
    }
}
