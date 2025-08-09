package com.google.android.gms.tagmanager;

import android.content.Context;
import android.provider.Settings;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.d;
import java.util.Map;

class z extends aj {
    private static final String ID = a.DEVICE_ID.toString();
    private final Context mContext;

    public z(Context context) {
        super(ID, new String[0]);
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    public String L(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), "android_id");
    }

    public boolean lc() {
        return true;
    }

    public d.a w(Map<String, d.a> map) {
        String L = L(this.mContext);
        return L == null ? dh.mY() : dh.r(L);
    }
}
