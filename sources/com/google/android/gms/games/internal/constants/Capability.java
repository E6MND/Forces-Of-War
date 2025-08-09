package com.google.android.gms.games.internal.constants;

import java.util.ArrayList;

public class Capability {
    public static final ArrayList<String> Rf = new ArrayList<>();

    static {
        Rf.add("ibb");
        Rf.add("rtp");
        Rf.add("unreliable_ping");
    }
}
