package com.google.android.gms.internal;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import com.google.android.gms.internal.p;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.ArrayList;

public abstract class j extends i {
    private static Method jS;
    private static Method jT;
    private static Method jU;
    private static Method jV;
    private static Method jW;
    private static Method jX;
    private static String jY;
    private static p jZ;
    static boolean ka = false;
    private static long startTime = 0;

    static class a extends Exception {
        public a() {
        }

        public a(Throwable th) {
            super(th);
        }
    }

    protected j(Context context, n nVar, o oVar) {
        super(context, nVar, oVar);
    }

    static String a(Context context, n nVar) throws a {
        if (jU == null) {
            throw new a();
        }
        try {
            ByteBuffer byteBuffer = (ByteBuffer) jU.invoke((Object) null, new Object[]{context});
            if (byteBuffer != null) {
                return nVar.a(byteBuffer.array(), true);
            }
            throw new a();
        } catch (IllegalAccessException e) {
            throw new a(e);
        } catch (InvocationTargetException e2) {
            throw new a(e2);
        }
    }

    static ArrayList<Long> a(MotionEvent motionEvent, DisplayMetrics displayMetrics) throws a {
        if (jV == null || motionEvent == null) {
            throw new a();
        }
        try {
            return (ArrayList) jV.invoke((Object) null, new Object[]{motionEvent, displayMetrics});
        } catch (IllegalAccessException e) {
            throw new a(e);
        } catch (InvocationTargetException e2) {
            throw new a(e2);
        }
    }

    protected static synchronized void a(String str, Context context, n nVar) {
        synchronized (j.class) {
            if (!ka) {
                try {
                    jZ = new p(nVar, (SecureRandom) null);
                    jY = str;
                    e(context);
                    startTime = w().longValue();
                    ka = true;
                } catch (a | UnsupportedOperationException e) {
                }
            }
        }
    }

    static String b(Context context, n nVar) throws a {
        if (jX == null) {
            throw new a();
        }
        try {
            ByteBuffer byteBuffer = (ByteBuffer) jX.invoke((Object) null, new Object[]{context});
            if (byteBuffer != null) {
                return nVar.a(byteBuffer.array(), true);
            }
            throw new a();
        } catch (IllegalAccessException e) {
            throw new a(e);
        } catch (InvocationTargetException e2) {
            throw new a(e2);
        }
    }

    private static String b(byte[] bArr, String str) throws a {
        try {
            return new String(jZ.c(bArr, str), "UTF-8");
        } catch (p.a e) {
            throw new a(e);
        } catch (UnsupportedEncodingException e2) {
            throw new a(e2);
        }
    }

    static String d(Context context) throws a {
        if (jW == null) {
            throw new a();
        }
        try {
            String str = (String) jW.invoke((Object) null, new Object[]{context});
            if (str != null) {
                return str;
            }
            throw new a();
        } catch (IllegalAccessException e) {
            throw new a(e);
        } catch (InvocationTargetException e2) {
            throw new a(e2);
        }
    }

    private static void e(Context context) throws a {
        try {
            byte[] b = jZ.b(r.getKey());
            byte[] c = jZ.c(b, r.A());
            File cacheDir = context.getCacheDir();
            if (cacheDir == null && (cacheDir = context.getDir("dex", 0)) == null) {
                throw new a();
            }
            File createTempFile = File.createTempFile("ads", ".jar", cacheDir);
            FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
            fileOutputStream.write(c, 0, c.length);
            fileOutputStream.close();
            DexClassLoader dexClassLoader = new DexClassLoader(createTempFile.getAbsolutePath(), cacheDir.getAbsolutePath(), (String) null, context.getClassLoader());
            Class loadClass = dexClassLoader.loadClass(b(b, r.B()));
            Class loadClass2 = dexClassLoader.loadClass(b(b, r.H()));
            Class loadClass3 = dexClassLoader.loadClass(b(b, r.F()));
            Class loadClass4 = dexClassLoader.loadClass(b(b, r.L()));
            Class loadClass5 = dexClassLoader.loadClass(b(b, r.D()));
            Class loadClass6 = dexClassLoader.loadClass(b(b, r.J()));
            jS = loadClass.getMethod(b(b, r.C()), new Class[0]);
            jT = loadClass2.getMethod(b(b, r.I()), new Class[0]);
            jU = loadClass3.getMethod(b(b, r.G()), new Class[]{Context.class});
            jV = loadClass4.getMethod(b(b, r.M()), new Class[]{MotionEvent.class, DisplayMetrics.class});
            jW = loadClass5.getMethod(b(b, r.E()), new Class[]{Context.class});
            jX = loadClass6.getMethod(b(b, r.K()), new Class[]{Context.class});
            String name = createTempFile.getName();
            createTempFile.delete();
            new File(cacheDir, name.replace(".jar", ".dex")).delete();
        } catch (FileNotFoundException e) {
            throw new a(e);
        } catch (IOException e2) {
            throw new a(e2);
        } catch (ClassNotFoundException e3) {
            throw new a(e3);
        } catch (p.a e4) {
            throw new a(e4);
        } catch (NoSuchMethodException e5) {
            throw new a(e5);
        } catch (NullPointerException e6) {
            throw new a(e6);
        }
    }

    static String v() throws a {
        if (jY != null) {
            return jY;
        }
        throw new a();
    }

    static Long w() throws a {
        if (jS == null) {
            throw new a();
        }
        try {
            return (Long) jS.invoke((Object) null, new Object[0]);
        } catch (IllegalAccessException e) {
            throw new a(e);
        } catch (InvocationTargetException e2) {
            throw new a(e2);
        }
    }

    static String x() throws a {
        if (jT == null) {
            throw new a();
        }
        try {
            return (String) jT.invoke((Object) null, new Object[0]);
        } catch (IllegalAccessException e) {
            throw new a(e);
        } catch (InvocationTargetException e2) {
            throw new a(e2);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0027 A[ExcHandler: IOException (e java.io.IOException), Splitter:B:1:0x0001] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(android.content.Context r5) {
        /*
            r4 = this;
            r0 = 1
            java.lang.String r1 = x()     // Catch:{ a -> 0x002f, IOException -> 0x0027 }
            r4.a((int) r0, (java.lang.String) r1)     // Catch:{ a -> 0x002f, IOException -> 0x0027 }
        L_0x0008:
            r0 = 2
            java.lang.String r1 = v()     // Catch:{ a -> 0x002d, IOException -> 0x0027 }
            r4.a((int) r0, (java.lang.String) r1)     // Catch:{ a -> 0x002d, IOException -> 0x0027 }
        L_0x0010:
            r0 = 25
            java.lang.Long r1 = w()     // Catch:{ a -> 0x002b, IOException -> 0x0027 }
            long r2 = r1.longValue()     // Catch:{ a -> 0x002b, IOException -> 0x0027 }
            r4.a((int) r0, (long) r2)     // Catch:{ a -> 0x002b, IOException -> 0x0027 }
        L_0x001d:
            r0 = 24
            java.lang.String r1 = d(r5)     // Catch:{ a -> 0x0029, IOException -> 0x0027 }
            r4.a((int) r0, (java.lang.String) r1)     // Catch:{ a -> 0x0029, IOException -> 0x0027 }
        L_0x0026:
            return
        L_0x0027:
            r0 = move-exception
            goto L_0x0026
        L_0x0029:
            r0 = move-exception
            goto L_0x0026
        L_0x002b:
            r0 = move-exception
            goto L_0x001d
        L_0x002d:
            r0 = move-exception
            goto L_0x0010
        L_0x002f:
            r0 = move-exception
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.j.b(android.content.Context):void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x008a A[ExcHandler: IOException (e java.io.IOException), Splitter:B:6:0x0010] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void c(android.content.Context r7) {
        /*
            r6 = this;
            r0 = 2
            java.lang.String r1 = v()     // Catch:{ a -> 0x0097, IOException -> 0x008a }
            r6.a((int) r0, (java.lang.String) r1)     // Catch:{ a -> 0x0097, IOException -> 0x008a }
        L_0x0008:
            r0 = 1
            java.lang.String r1 = x()     // Catch:{ a -> 0x0094, IOException -> 0x008a }
            r6.a((int) r0, (java.lang.String) r1)     // Catch:{ a -> 0x0094, IOException -> 0x008a }
        L_0x0010:
            java.lang.Long r0 = w()     // Catch:{ a -> 0x0092, IOException -> 0x008a }
            long r0 = r0.longValue()     // Catch:{ a -> 0x0092, IOException -> 0x008a }
            r2 = 25
            r6.a((int) r2, (long) r0)     // Catch:{ a -> 0x0092, IOException -> 0x008a }
            long r2 = startTime     // Catch:{ a -> 0x0092, IOException -> 0x008a }
            r4 = 0
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 == 0) goto L_0x0034
            r2 = 17
            long r4 = startTime     // Catch:{ a -> 0x0092, IOException -> 0x008a }
            long r0 = r0 - r4
            r6.a((int) r2, (long) r0)     // Catch:{ a -> 0x0092, IOException -> 0x008a }
            r0 = 23
            long r2 = startTime     // Catch:{ a -> 0x0092, IOException -> 0x008a }
            r6.a((int) r0, (long) r2)     // Catch:{ a -> 0x0092, IOException -> 0x008a }
        L_0x0034:
            android.view.MotionEvent r0 = r6.jO     // Catch:{ a -> 0x0090, IOException -> 0x008a }
            android.util.DisplayMetrics r1 = r6.jP     // Catch:{ a -> 0x0090, IOException -> 0x008a }
            java.util.ArrayList r1 = a((android.view.MotionEvent) r0, (android.util.DisplayMetrics) r1)     // Catch:{ a -> 0x0090, IOException -> 0x008a }
            r2 = 14
            r0 = 0
            java.lang.Object r0 = r1.get(r0)     // Catch:{ a -> 0x0090, IOException -> 0x008a }
            java.lang.Long r0 = (java.lang.Long) r0     // Catch:{ a -> 0x0090, IOException -> 0x008a }
            long r4 = r0.longValue()     // Catch:{ a -> 0x0090, IOException -> 0x008a }
            r6.a((int) r2, (long) r4)     // Catch:{ a -> 0x0090, IOException -> 0x008a }
            r2 = 15
            r0 = 1
            java.lang.Object r0 = r1.get(r0)     // Catch:{ a -> 0x0090, IOException -> 0x008a }
            java.lang.Long r0 = (java.lang.Long) r0     // Catch:{ a -> 0x0090, IOException -> 0x008a }
            long r4 = r0.longValue()     // Catch:{ a -> 0x0090, IOException -> 0x008a }
            r6.a((int) r2, (long) r4)     // Catch:{ a -> 0x0090, IOException -> 0x008a }
            int r0 = r1.size()     // Catch:{ a -> 0x0090, IOException -> 0x008a }
            r2 = 3
            if (r0 < r2) goto L_0x0073
            r2 = 16
            r0 = 2
            java.lang.Object r0 = r1.get(r0)     // Catch:{ a -> 0x0090, IOException -> 0x008a }
            java.lang.Long r0 = (java.lang.Long) r0     // Catch:{ a -> 0x0090, IOException -> 0x008a }
            long r0 = r0.longValue()     // Catch:{ a -> 0x0090, IOException -> 0x008a }
            r6.a((int) r2, (long) r0)     // Catch:{ a -> 0x0090, IOException -> 0x008a }
        L_0x0073:
            r0 = 27
            com.google.android.gms.internal.n r1 = r6.jQ     // Catch:{ a -> 0x008e, IOException -> 0x008a }
            java.lang.String r1 = a((android.content.Context) r7, (com.google.android.gms.internal.n) r1)     // Catch:{ a -> 0x008e, IOException -> 0x008a }
            r6.a((int) r0, (java.lang.String) r1)     // Catch:{ a -> 0x008e, IOException -> 0x008a }
        L_0x007e:
            r0 = 29
            com.google.android.gms.internal.n r1 = r6.jQ     // Catch:{ a -> 0x008c, IOException -> 0x008a }
            java.lang.String r1 = b((android.content.Context) r7, (com.google.android.gms.internal.n) r1)     // Catch:{ a -> 0x008c, IOException -> 0x008a }
            r6.a((int) r0, (java.lang.String) r1)     // Catch:{ a -> 0x008c, IOException -> 0x008a }
        L_0x0089:
            return
        L_0x008a:
            r0 = move-exception
            goto L_0x0089
        L_0x008c:
            r0 = move-exception
            goto L_0x0089
        L_0x008e:
            r0 = move-exception
            goto L_0x007e
        L_0x0090:
            r0 = move-exception
            goto L_0x0073
        L_0x0092:
            r0 = move-exception
            goto L_0x0034
        L_0x0094:
            r0 = move-exception
            goto L_0x0010
        L_0x0097:
            r0 = move-exception
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.j.c(android.content.Context):void");
    }
}
