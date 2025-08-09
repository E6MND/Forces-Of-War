package com.crashlytics.android.internal;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.GZIPOutputStream;

/* renamed from: com.crashlytics.android.internal.aj  reason: case insensitive filesystem */
public class C0151aj {
    private final File a;
    private final String b;
    private C0158aq c = new C0158aq(this.d);
    private File d;
    private File e = new File(this.a, this.b);

    public C0151aj(File file, String str, String str2) throws IOException {
        this.a = file;
        this.b = str2;
        this.d = new File(file, str);
        if (!this.e.exists()) {
            this.e.mkdirs();
        }
    }

    public void a(byte[] bArr) throws IOException {
        this.c.a(bArr);
    }

    public int a() {
        return this.c.a();
    }

    public void a(String str) throws IOException {
        FileInputStream fileInputStream;
        GZIPOutputStream gZIPOutputStream = null;
        this.c.close();
        File file = this.d;
        File file2 = new File(this.e, str);
        try {
            fileInputStream = new FileInputStream(file);
            try {
                GZIPOutputStream gZIPOutputStream2 = new GZIPOutputStream(new FileOutputStream(file2));
                try {
                    C0143ab.a((InputStream) fileInputStream, (OutputStream) gZIPOutputStream2, new byte[1024]);
                    C0143ab.a((Closeable) fileInputStream, "Failed to close file input stream");
                    C0143ab.a((Closeable) gZIPOutputStream2, "Failed to close gzip output stream");
                    file.delete();
                    this.c = new C0158aq(this.d);
                } catch (Throwable th) {
                    th = th;
                    gZIPOutputStream = gZIPOutputStream2;
                    C0143ab.a((Closeable) fileInputStream, "Failed to close file input stream");
                    C0143ab.a((Closeable) gZIPOutputStream, "Failed to close gzip output stream");
                    file.delete();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                C0143ab.a((Closeable) fileInputStream, "Failed to close file input stream");
                C0143ab.a((Closeable) gZIPOutputStream, "Failed to close gzip output stream");
                file.delete();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
            C0143ab.a((Closeable) fileInputStream, "Failed to close file input stream");
            C0143ab.a((Closeable) gZIPOutputStream, "Failed to close gzip output stream");
            file.delete();
            throw th;
        }
    }

    public List<File> a(int i) {
        ArrayList arrayList = new ArrayList();
        for (File add : this.e.listFiles()) {
            arrayList.add(add);
            if (arrayList.size() > 0) {
                break;
            }
        }
        return arrayList;
    }

    public void a(List<File> list) {
        for (File next : list) {
            C0143ab.c(String.format("deleting sent analytics file %s", new Object[]{next.getName()}));
            next.delete();
        }
    }

    public List<File> c() {
        return Arrays.asList(this.e.listFiles());
    }

    public void d() {
        try {
            this.c.close();
        } catch (IOException e2) {
        }
        this.d.delete();
    }

    public boolean b() {
        return this.c.b();
    }

    public boolean a(int i, int i2) {
        return this.c.a(i, i2);
    }
}
