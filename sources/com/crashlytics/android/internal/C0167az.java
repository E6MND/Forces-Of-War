package com.crashlytics.android.internal;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* renamed from: com.crashlytics.android.internal.az  reason: case insensitive filesystem */
final class C0167az extends aA<C0166ay> {
    private /* synthetic */ InputStream a;
    private /* synthetic */ OutputStream b;
    private /* synthetic */ C0166ay c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0167az(C0166ay ayVar, Closeable closeable, boolean z, InputStream inputStream, OutputStream outputStream) {
        super(closeable, z);
        this.c = ayVar;
        this.a = inputStream;
        this.b = outputStream;
    }

    public final /* synthetic */ Object a() throws aD, IOException {
        byte[] bArr = new byte[this.c.i];
        while (true) {
            int read = this.a.read(bArr);
            if (read == -1) {
                return this.c;
            }
            this.b.write(bArr, 0, read);
        }
    }
}
