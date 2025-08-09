package com.crashlytics.android;

import java.io.IOException;

/* renamed from: com.crashlytics.android.i  reason: case insensitive filesystem */
final class C0140i extends IOException {
    private static final long serialVersionUID = -6947486886997889499L;

    C0140i() {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.");
    }
}
