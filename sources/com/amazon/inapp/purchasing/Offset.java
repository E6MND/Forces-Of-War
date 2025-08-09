package com.amazon.inapp.purchasing;

public final class Offset {
    public static final Offset BEGINNING = new Offset(BEGINNING_ENCODED);
    private static final String BEGINNING_ENCODED = "BEGINNING";
    private String _encodedOffset;

    Offset(String str) {
        this._encodedOffset = str;
    }

    public static Offset fromString(String str) {
        return BEGINNING_ENCODED.equals(str) ? BEGINNING : new Offset(str);
    }

    public String toString() {
        return this._encodedOffset;
    }
}
