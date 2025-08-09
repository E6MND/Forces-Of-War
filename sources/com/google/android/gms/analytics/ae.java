package com.google.android.gms.analytics;

import android.content.Context;
import android.util.DisplayMetrics;

class ae implements m {
    private static Object tn = new Object();
    private static ae wW;
    private final Context mContext;

    protected ae(Context context) {
        this.mContext = context;
    }

    public static ae dq() {
        ae aeVar;
        synchronized (tn) {
            aeVar = wW;
        }
        return aeVar;
    }

    public static void r(Context context) {
        synchronized (tn) {
            if (wW == null) {
                wW = new ae(context);
            }
        }
    }

    public boolean J(String str) {
        return "&sr".equals(str);
    }

    /* access modifiers changed from: protected */
    public String dr() {
        DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels + "x" + displayMetrics.heightPixels;
    }

    public String getValue(String field) {
        if (field != null && field.equals("&sr")) {
            return dr();
        }
        return null;
    }
}
