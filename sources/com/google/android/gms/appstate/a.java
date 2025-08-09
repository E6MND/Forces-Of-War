package com.google.android.gms.appstate;

import com.google.android.gms.internal.hl;

public final class a implements AppState {
    private final byte[] yA;
    private final boolean yB;
    private final String yC;
    private final byte[] yD;
    private final int yy;
    private final String yz;

    public a(AppState appState) {
        this.yy = appState.getKey();
        this.yz = appState.getLocalVersion();
        this.yA = appState.getLocalData();
        this.yB = appState.hasConflict();
        this.yC = appState.getConflictVersion();
        this.yD = appState.getConflictData();
    }

    static int a(AppState appState) {
        return hl.hashCode(Integer.valueOf(appState.getKey()), appState.getLocalVersion(), appState.getLocalData(), Boolean.valueOf(appState.hasConflict()), appState.getConflictVersion(), appState.getConflictData());
    }

    static boolean a(AppState appState, Object obj) {
        if (!(obj instanceof AppState)) {
            return false;
        }
        if (appState == obj) {
            return true;
        }
        AppState appState2 = (AppState) obj;
        return hl.equal(Integer.valueOf(appState2.getKey()), Integer.valueOf(appState.getKey())) && hl.equal(appState2.getLocalVersion(), appState.getLocalVersion()) && hl.equal(appState2.getLocalData(), appState.getLocalData()) && hl.equal(Boolean.valueOf(appState2.hasConflict()), Boolean.valueOf(appState.hasConflict())) && hl.equal(appState2.getConflictVersion(), appState.getConflictVersion()) && hl.equal(appState2.getConflictData(), appState.getConflictData());
    }

    static String b(AppState appState) {
        return hl.e(appState).a("Key", Integer.valueOf(appState.getKey())).a("LocalVersion", appState.getLocalVersion()).a("LocalData", appState.getLocalData()).a("HasConflict", Boolean.valueOf(appState.hasConflict())).a("ConflictVersion", appState.getConflictVersion()).a("ConflictData", appState.getConflictData()).toString();
    }

    /* renamed from: dN */
    public AppState freeze() {
        return this;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public byte[] getConflictData() {
        return this.yD;
    }

    public String getConflictVersion() {
        return this.yC;
    }

    public int getKey() {
        return this.yy;
    }

    public byte[] getLocalData() {
        return this.yA;
    }

    public String getLocalVersion() {
        return this.yz;
    }

    public boolean hasConflict() {
        return this.yB;
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
}
