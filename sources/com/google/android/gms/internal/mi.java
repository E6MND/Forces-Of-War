package com.google.android.gms.internal;

import java.io.IOException;

public final class mi {
    public static final int[] ana = new int[0];
    public static final long[] anb = new long[0];
    public static final float[] anc = new float[0];
    public static final double[] and = new double[0];
    public static final boolean[] ane = new boolean[0];
    public static final String[] anf = new String[0];
    public static final byte[][] ang = new byte[0][];
    public static final byte[] anh = new byte[0];

    public static final int b(lz lzVar, int i) throws IOException {
        int i2 = 1;
        int position = lzVar.getPosition();
        lzVar.ev(i);
        while (lzVar.nI() > 0 && lzVar.nw() == i) {
            lzVar.ev(i);
            i2++;
        }
        lzVar.ez(position);
        return i2;
    }

    static int eN(int i) {
        return i & 7;
    }

    public static int eO(int i) {
        return i >>> 3;
    }

    static int u(int i, int i2) {
        return (i << 3) | i2;
    }
}
