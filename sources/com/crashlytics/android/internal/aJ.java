package com.crashlytics.android.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

public class aJ {
    private final String a;
    private final Context b;

    public aJ(C0187u uVar) {
        if (uVar.getContext() == null) {
            throw new IllegalStateException("Cannot get directory before context has been set. Call Sdk.start() first");
        }
        this.b = uVar.getContext();
        this.a = uVar.getClass().getName();
    }

    public SharedPreferences a() {
        return this.b.getSharedPreferences(this.a, 0);
    }

    public SharedPreferences.Editor b() {
        return a().edit();
    }

    public boolean a(SharedPreferences.Editor editor) {
        if (Build.VERSION.SDK_INT < 9) {
            return editor.commit();
        }
        editor.apply();
        return true;
    }
}
