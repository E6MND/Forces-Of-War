package com.crashlytics.android.internal;

import com.google.android.gms.games.quest.Quests;

/* renamed from: com.crashlytics.android.internal.ap  reason: case insensitive filesystem */
public enum C0157ap {
    WIFI_MAC_ADDRESS(1),
    BLUETOOTH_MAC_ADDRESS(2),
    ANDROID_ID(100),
    ANDROID_DEVICE_ID(Quests.SELECT_COMPLETED_UNCLAIMED),
    ANDROID_SERIAL(102);
    
    public final int f;

    private C0157ap(int i) {
        this.f = i;
    }
}
