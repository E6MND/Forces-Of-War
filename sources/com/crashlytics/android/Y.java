package com.crashlytics.android;

import android.content.Context;
import android.graphics.BitmapFactory;
import com.crashlytics.android.internal.C0143ab;
import com.crashlytics.android.internal.C0188v;

final class Y {
    public final String a;
    public final int b;
    public final int c;
    public final int d;

    private Y(String str, int i, int i2, int i3) {
        this.a = str;
        this.b = i;
        this.c = i2;
        this.d = i3;
    }

    public static Y a(Context context, String str) {
        if (str != null) {
            try {
                int h = C0143ab.h(context);
                C0188v.a().b().a(Crashlytics.TAG, "App icon resource ID is " + h);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeResource(context.getResources(), h, options);
                return new Y(str, h, options.outWidth, options.outHeight);
            } catch (Exception e) {
                C0188v.a().b().a(Crashlytics.TAG, "Failed to load icon", (Throwable) e);
            }
        }
        return null;
    }
}
