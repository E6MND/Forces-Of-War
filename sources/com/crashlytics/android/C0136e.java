package com.crashlytics.android;

import android.os.Process;
import com.crashlytics.android.internal.C0143ab;
import com.crashlytics.android.internal.C0156ao;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: com.crashlytics.android.e  reason: case insensitive filesystem */
final class C0136e {
    private static final AtomicLong a = new AtomicLong(0);
    private static String b;

    public C0136e(C0156ao aoVar) {
        long time = new Date().getTime();
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt((int) (time / 1000));
        allocate.order(ByteOrder.BIG_ENDIAN);
        allocate.position(0);
        byte[] array = allocate.array();
        byte[] a2 = a(time % 1000);
        byte[] a3 = a(a.incrementAndGet());
        byte[] a4 = a((long) Integer.valueOf(Process.myPid()).shortValue());
        byte[] bArr = {array[0], array[1], array[2], array[3], a2[0], a2[1], a3[0], a3[1], a4[0], a4[1]};
        String a5 = C0143ab.a(aoVar.b());
        String a6 = C0143ab.a(bArr);
        b = String.format(Locale.US, "%s-%s-%s-%s", new Object[]{a6.substring(0, 12), a6.substring(12, 16), a6.subSequence(16, 20), a5.substring(0, 12)}).toUpperCase(Locale.US);
    }

    private static byte[] a(long j) {
        ByteBuffer allocate = ByteBuffer.allocate(2);
        allocate.putShort((short) ((int) j));
        allocate.order(ByteOrder.BIG_ENDIAN);
        allocate.position(0);
        return allocate.array();
    }

    public final String toString() {
        return b;
    }
}
