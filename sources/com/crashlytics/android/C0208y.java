package com.crashlytics.android;

import com.crashlytics.android.internal.C0162au;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.crashlytics.android.y  reason: case insensitive filesystem */
final class C0208y implements C0162au {
    private /* synthetic */ byte[] a;
    private /* synthetic */ int[] b;

    C0208y(C0205v vVar, byte[] bArr, int[] iArr) {
        this.a = bArr;
        this.b = iArr;
    }

    public final void a(InputStream inputStream, int i) throws IOException {
        try {
            inputStream.read(this.a, this.b[0], i);
            int[] iArr = this.b;
            iArr[0] = iArr[0] + i;
        } finally {
            inputStream.close();
        }
    }
}
