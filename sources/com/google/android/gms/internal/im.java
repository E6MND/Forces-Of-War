package com.google.android.gms.internal;

public final class im implements ik {
    private static im Hs;

    public static synchronized ik fW() {
        im imVar;
        synchronized (im.class) {
            if (Hs == null) {
                Hs = new im();
            }
            imVar = Hs;
        }
        return imVar;
    }

    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }
}
