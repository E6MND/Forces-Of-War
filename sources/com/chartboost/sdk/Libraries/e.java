package com.chartboost.sdk.Libraries;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.a;
import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;

public class e {
    private static e a = null;
    private d b = new d(Chartboost.sharedChartboost().getContext());
    /* access modifiers changed from: private */
    public a c = new a();

    public interface b {
        void a(a.C0003a aVar, Bundle bundle);
    }

    public static synchronized e a() {
        e eVar;
        synchronized (e.class) {
            if (a == null) {
                a = new e();
            }
            eVar = a;
        }
        return eVar;
    }

    private e() {
    }

    public void b() {
        this.b.a();
        this.c.a();
    }

    public void a(String str, String str2, b bVar, ImageView imageView, Bundle bundle) {
        boolean z;
        a.C0003a aVar = null;
        boolean z2 = bundle != null ? bundle.getBoolean("paramNoMemoryCache") : false;
        if (!z2) {
            try {
                aVar = this.c.a(str2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (aVar == null && (aVar = a(str2)) != null) {
            if (z2) {
                z = false;
            } else {
                z = true;
            }
            aVar.a(z);
            if (!z2) {
                this.c.a(str2, aVar);
            }
        }
        if (aVar != null) {
            if (imageView != null) {
                imageView.setImageBitmap(aVar.b());
            }
            if (bVar != null) {
                bVar.a(aVar, bundle);
                return;
            }
            return;
        }
        new a(imageView, bVar, str2, bundle).execute(new String[]{str});
    }

    class a extends AsyncTask<String, Void, a.C0003a> {
        private String b;
        private final WeakReference<ImageView> c;
        private b d;
        private String e;
        private Bundle f;

        public a(ImageView imageView, b bVar, String str, Bundle bundle) {
            this.c = new WeakReference<>(imageView);
            c cVar = new c(this);
            if (imageView != null) {
                imageView.setImageDrawable(cVar);
            }
            this.e = str;
            this.d = bVar;
            this.f = bundle;
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Removed duplicated region for block: B:18:0x0032  */
        /* JADX WARNING: Removed duplicated region for block: B:36:0x00a4 A[SYNTHETIC, Splitter:B:36:0x00a4] */
        /* JADX WARNING: Removed duplicated region for block: B:66:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
        /* renamed from: a */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.chartboost.sdk.Libraries.a.C0003a doInBackground(java.lang.String... r11) {
            /*
                r10 = this;
                r5 = 1
                r3 = 0
                r1 = 0
                r0 = r11[r1]
                r10.b = r0
                android.os.Bundle r0 = r10.f
                if (r0 == 0) goto L_0x0027
                android.os.Bundle r0 = r10.f
                java.lang.String r2 = "paramNoMemoryCache"
                boolean r0 = r0.getBoolean(r2)
            L_0x0013:
                com.chartboost.sdk.Libraries.e r2 = com.chartboost.sdk.Libraries.e.this     // Catch:{ Exception -> 0x002b }
                java.lang.String r4 = r10.e     // Catch:{ Exception -> 0x002b }
                com.chartboost.sdk.Libraries.a$a r4 = r2.a((java.lang.String) r4)     // Catch:{ Exception -> 0x002b }
                if (r4 == 0) goto L_0x0030
                if (r0 == 0) goto L_0x0029
                r2 = r1
            L_0x0020:
                r4.a((boolean) r2)     // Catch:{ Exception -> 0x011b }
                r2 = r4
            L_0x0024:
                if (r2 == 0) goto L_0x0032
            L_0x0026:
                return r2
            L_0x0027:
                r0 = r1
                goto L_0x0013
            L_0x0029:
                r2 = r5
                goto L_0x0020
            L_0x002b:
                r2 = move-exception
                r4 = r3
            L_0x002d:
                r2.printStackTrace()
            L_0x0030:
                r2 = r4
                goto L_0x0024
            L_0x0032:
                org.apache.http.client.HttpClient r4 = com.chartboost.sdk.impl.j.b()
                org.apache.http.client.methods.HttpGet r6 = new org.apache.http.client.methods.HttpGet
                java.lang.String r7 = r10.b
                r6.<init>(r7)
                org.apache.http.HttpResponse r4 = r4.execute(r6)     // Catch:{ IOException -> 0x00bf, IllegalStateException -> 0x00dc, Exception -> 0x00f8 }
                org.apache.http.StatusLine r7 = r4.getStatusLine()     // Catch:{ IOException -> 0x00bf, IllegalStateException -> 0x00dc, Exception -> 0x00f8 }
                int r7 = r7.getStatusCode()     // Catch:{ IOException -> 0x00bf, IllegalStateException -> 0x00dc, Exception -> 0x00f8 }
                r8 = 200(0xc8, float:2.8E-43)
                if (r7 == r8) goto L_0x006f
                java.lang.String r0 = "ImageDownloader"
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00bf, IllegalStateException -> 0x00dc, Exception -> 0x00f8 }
                java.lang.String r4 = "Error "
                r1.<init>(r4)     // Catch:{ IOException -> 0x00bf, IllegalStateException -> 0x00dc, Exception -> 0x00f8 }
                java.lang.StringBuilder r1 = r1.append(r7)     // Catch:{ IOException -> 0x00bf, IllegalStateException -> 0x00dc, Exception -> 0x00f8 }
                java.lang.String r4 = " while retrieving bitmap from "
                java.lang.StringBuilder r1 = r1.append(r4)     // Catch:{ IOException -> 0x00bf, IllegalStateException -> 0x00dc, Exception -> 0x00f8 }
                java.lang.String r4 = r10.b     // Catch:{ IOException -> 0x00bf, IllegalStateException -> 0x00dc, Exception -> 0x00f8 }
                java.lang.StringBuilder r1 = r1.append(r4)     // Catch:{ IOException -> 0x00bf, IllegalStateException -> 0x00dc, Exception -> 0x00f8 }
                java.lang.String r1 = r1.toString()     // Catch:{ IOException -> 0x00bf, IllegalStateException -> 0x00dc, Exception -> 0x00f8 }
                android.util.Log.w(r0, r1)     // Catch:{ IOException -> 0x00bf, IllegalStateException -> 0x00dc, Exception -> 0x00f8 }
                r2 = r3
                goto L_0x0026
            L_0x006f:
                org.apache.http.HttpEntity r4 = r4.getEntity()     // Catch:{ IOException -> 0x00bf, IllegalStateException -> 0x00dc, Exception -> 0x00f8 }
                if (r4 == 0) goto L_0x0120
                java.io.InputStream r3 = r4.getContent()     // Catch:{ all -> 0x00b5 }
                com.chartboost.sdk.Libraries.e r7 = com.chartboost.sdk.Libraries.e.this     // Catch:{ all -> 0x00b5 }
                java.lang.String r8 = r10.e     // Catch:{ all -> 0x00b5 }
                com.chartboost.sdk.Libraries.e$e r9 = new com.chartboost.sdk.Libraries.e$e     // Catch:{ all -> 0x00b5 }
                r9.<init>(r3)     // Catch:{ all -> 0x00b5 }
                r7.a(r8, r9)     // Catch:{ all -> 0x00b5 }
                com.chartboost.sdk.Libraries.e r7 = com.chartboost.sdk.Libraries.e.this     // Catch:{ Exception -> 0x00af }
                java.lang.String r8 = r10.e     // Catch:{ Exception -> 0x00af }
                com.chartboost.sdk.Libraries.a$a r2 = r7.a((java.lang.String) r8)     // Catch:{ Exception -> 0x00af }
                if (r2 == 0) goto L_0x011e
                if (r0 == 0) goto L_0x00ad
            L_0x0091:
                r2.a((boolean) r1)     // Catch:{ Exception -> 0x00af }
                if (r0 != 0) goto L_0x011e
                com.chartboost.sdk.Libraries.e r0 = com.chartboost.sdk.Libraries.e.this     // Catch:{ Exception -> 0x00af }
                com.chartboost.sdk.Libraries.a r0 = r0.c     // Catch:{ Exception -> 0x00af }
                java.lang.String r1 = r10.e     // Catch:{ Exception -> 0x00af }
                r0.a(r1, r2)     // Catch:{ Exception -> 0x00af }
                r0 = r2
            L_0x00a2:
                if (r3 == 0) goto L_0x00a7
                r3.close()     // Catch:{ IOException -> 0x0119, IllegalStateException -> 0x0117, Exception -> 0x0115 }
            L_0x00a7:
                r4.consumeContent()     // Catch:{ IOException -> 0x0119, IllegalStateException -> 0x0117, Exception -> 0x0115 }
            L_0x00aa:
                r2 = r0
                goto L_0x0026
            L_0x00ad:
                r1 = r5
                goto L_0x0091
            L_0x00af:
                r0 = move-exception
                r0.printStackTrace()     // Catch:{ all -> 0x00b5 }
                r0 = r2
                goto L_0x00a2
            L_0x00b5:
                r0 = move-exception
                if (r3 == 0) goto L_0x00bb
                r3.close()     // Catch:{ IOException -> 0x00bf, IllegalStateException -> 0x00dc, Exception -> 0x00f8 }
            L_0x00bb:
                r4.consumeContent()     // Catch:{ IOException -> 0x00bf, IllegalStateException -> 0x00dc, Exception -> 0x00f8 }
                throw r0     // Catch:{ IOException -> 0x00bf, IllegalStateException -> 0x00dc, Exception -> 0x00f8 }
            L_0x00bf:
                r0 = move-exception
                r1 = r0
                r0 = r2
            L_0x00c2:
                r6.abort()
                java.lang.String r2 = "CBWebImageCache"
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                java.lang.String r4 = "I/O error while retrieving bitmap from "
                r3.<init>(r4)
                java.lang.String r4 = r10.b
                java.lang.StringBuilder r3 = r3.append(r4)
                java.lang.String r3 = r3.toString()
                android.util.Log.w(r2, r3, r1)
                goto L_0x00aa
            L_0x00dc:
                r0 = move-exception
                r0 = r2
            L_0x00de:
                r6.abort()
                java.lang.String r1 = "CBWebImageCache"
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                java.lang.String r3 = "Incorrect URL: "
                r2.<init>(r3)
                java.lang.String r3 = r10.b
                java.lang.StringBuilder r2 = r2.append(r3)
                java.lang.String r2 = r2.toString()
                android.util.Log.w(r1, r2)
                goto L_0x00aa
            L_0x00f8:
                r0 = move-exception
                r1 = r0
                r0 = r2
            L_0x00fb:
                r6.abort()
                java.lang.String r2 = "CBWebImageCache"
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                java.lang.String r4 = "Error while retrieving bitmap from "
                r3.<init>(r4)
                java.lang.String r4 = r10.b
                java.lang.StringBuilder r3 = r3.append(r4)
                java.lang.String r3 = r3.toString()
                android.util.Log.w(r2, r3, r1)
                goto L_0x00aa
            L_0x0115:
                r1 = move-exception
                goto L_0x00fb
            L_0x0117:
                r1 = move-exception
                goto L_0x00de
            L_0x0119:
                r1 = move-exception
                goto L_0x00c2
            L_0x011b:
                r2 = move-exception
                goto L_0x002d
            L_0x011e:
                r0 = r2
                goto L_0x00a2
            L_0x0120:
                r0 = r2
                goto L_0x00aa
            */
            throw new UnsupportedOperationException("Method not decompiled: com.chartboost.sdk.Libraries.e.a.doInBackground(java.lang.String[]):com.chartboost.sdk.Libraries.a$a");
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(a.C0003a aVar) {
            if (!isCancelled()) {
                if (aVar != null) {
                    try {
                        e.this.c.a(this.e, aVar);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                if (this.c != null) {
                    ImageView imageView = (ImageView) this.c.get();
                    if (this == e.b(imageView)) {
                        imageView.setImageBitmap(aVar.b());
                    }
                }
                if (this.d != null) {
                    this.d.a(aVar, this.f);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static a b(ImageView imageView) {
        if (imageView != null) {
            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof c) {
                return ((c) drawable).a();
            }
        }
        return null;
    }

    static class c extends BitmapDrawable {
        private final WeakReference<a> a;

        public c(a aVar) {
            this.a = new WeakReference<>(aVar);
        }

        public a a() {
            return (a) this.a.get();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: com.chartboost.sdk.Libraries.a$a} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: com.chartboost.sdk.Libraries.a$a} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: android.graphics.Bitmap} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: android.graphics.Bitmap} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: com.chartboost.sdk.Libraries.a$a} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: android.graphics.Bitmap} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.chartboost.sdk.Libraries.a.C0003a a(java.lang.String r11) throws java.io.IOException {
        /*
            r10 = this;
            r0 = 0
            r9 = 1
            r8 = 0
            com.chartboost.sdk.Libraries.e$d r1 = r10.b
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = java.lang.String.valueOf(r11)
            r2.<init>(r3)
            java.lang.String r3 = ".png"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            java.io.File r1 = r1.a(r2)
            boolean r2 = r1.exists()
            if (r2 != 0) goto L_0x0023
        L_0x0022:
            return r0
        L_0x0023:
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream
            java.io.FileInputStream r3 = new java.io.FileInputStream
            r3.<init>(r1)
            r2.<init>(r3)
            long r4 = r1.length()
            r6 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 <= 0) goto L_0x0040
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "Cannot read files larger than 2147483647 bytes"
            r0.<init>(r1)
            throw r0
        L_0x0040:
            int r1 = (int) r4
            byte[] r3 = new byte[r1]
            r2.read(r3, r8, r1)
            r2.close()
            android.graphics.BitmapFactory$Options r1 = new android.graphics.BitmapFactory$Options
            r1.<init>()
            r1.inJustDecodeBounds = r9
            int r2 = r3.length
            android.graphics.BitmapFactory.decodeByteArray(r3, r8, r2, r1)
            android.graphics.BitmapFactory$Options r2 = new android.graphics.BitmapFactory$Options
            r2.<init>()
            r2.inJustDecodeBounds = r8
            r2.inDither = r8
            r2.inPurgeable = r9
            r2.inInputShareable = r9
            r1 = 32768(0x8000, float:4.5918E-41)
            byte[] r1 = new byte[r1]
            r2.inTempStorage = r1
            r2.inSampleSize = r9
        L_0x006a:
            int r1 = r2.inSampleSize
            r4 = 64
            if (r1 < r4) goto L_0x0079
        L_0x0070:
            com.chartboost.sdk.Libraries.a$a r1 = new com.chartboost.sdk.Libraries.a$a
            int r2 = r2.inSampleSize
            r1.<init>(r0, r2)
            r0 = r1
            goto L_0x0022
        L_0x0079:
            r1 = 0
            int r4 = r3.length     // Catch:{ OutOfMemoryError -> 0x0080, Exception -> 0x0088 }
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeByteArray(r3, r1, r4, r2)     // Catch:{ OutOfMemoryError -> 0x0080, Exception -> 0x0088 }
            goto L_0x0070
        L_0x0080:
            r1 = move-exception
            int r1 = r2.inSampleSize
            int r1 = r1 * 2
            r2.inSampleSize = r1
            goto L_0x006a
        L_0x0088:
            r1 = move-exception
            goto L_0x0022
        */
        throw new UnsupportedOperationException("Method not decompiled: com.chartboost.sdk.Libraries.e.a(java.lang.String):com.chartboost.sdk.Libraries.a$a");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0044 A[SYNTHETIC, Splitter:B:24:0x0044] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(java.lang.String r6, com.chartboost.sdk.Libraries.e.C0004e r7) throws java.io.IOException {
        /*
            r5 = this;
            r0 = 0
            com.chartboost.sdk.Libraries.e$d r1 = r5.b
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = java.lang.String.valueOf(r6)
            r2.<init>(r3)
            java.lang.String r3 = ".png"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            java.io.File r3 = r1.a(r2)
            r2 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x004e, all -> 0x0040 }
            r1.<init>(r3)     // Catch:{ Exception -> 0x004e, all -> 0x0040 }
            r2 = 4096(0x1000, float:5.74E-42)
            byte[] r2 = new byte[r2]     // Catch:{ Exception -> 0x0037, all -> 0x004c }
        L_0x0024:
            int r3 = r7.read(r2)     // Catch:{ Exception -> 0x0037, all -> 0x004c }
            r4 = -1
            if (r3 != r4) goto L_0x0032
            if (r1 == 0) goto L_0x0030
            r1.close()     // Catch:{ Exception -> 0x0048 }
        L_0x0030:
            r0 = 1
        L_0x0031:
            return r0
        L_0x0032:
            r4 = 0
            r1.write(r2, r4, r3)     // Catch:{ Exception -> 0x0037, all -> 0x004c }
            goto L_0x0024
        L_0x0037:
            r2 = move-exception
        L_0x0038:
            if (r1 == 0) goto L_0x0031
            r1.close()     // Catch:{ Exception -> 0x003e }
            goto L_0x0031
        L_0x003e:
            r1 = move-exception
            goto L_0x0031
        L_0x0040:
            r0 = move-exception
            r1 = r2
        L_0x0042:
            if (r1 == 0) goto L_0x0047
            r1.close()     // Catch:{ Exception -> 0x004a }
        L_0x0047:
            throw r0
        L_0x0048:
            r0 = move-exception
            goto L_0x0030
        L_0x004a:
            r1 = move-exception
            goto L_0x0047
        L_0x004c:
            r0 = move-exception
            goto L_0x0042
        L_0x004e:
            r1 = move-exception
            r1 = r2
            goto L_0x0038
        */
        throw new UnsupportedOperationException("Method not decompiled: com.chartboost.sdk.Libraries.e.a(java.lang.String, com.chartboost.sdk.Libraries.e$e):boolean");
    }

    /* renamed from: com.chartboost.sdk.Libraries.e$e  reason: collision with other inner class name */
    static class C0004e extends FilterInputStream {
        public C0004e(InputStream inputStream) {
            super(inputStream);
        }

        public long skip(long n) throws IOException {
            long j = 0;
            while (j < n) {
                long skip = this.in.skip(n - j);
                if (skip == 0) {
                    if (read() < 0) {
                        break;
                    }
                    skip = 1;
                }
                j = skip + j;
            }
            return j;
        }
    }

    private static class d {
        private File a = null;

        public d(Context context) {
            try {
                if ((context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) && Environment.getExternalStorageState().equals("mounted")) {
                    this.a = context.getExternalFilesDir("cache");
                }
                if (this.a != null && !this.a.exists()) {
                    this.a.mkdirs();
                }
            } catch (Exception e) {
                this.a = null;
            }
            if (this.a == null) {
                this.a = context.getCacheDir();
                if (!this.a.exists()) {
                    this.a.mkdirs();
                }
            }
        }

        public File a(String str) {
            return new File(this.a, str);
        }

        public void a() {
            try {
                File[] listFiles = this.a.listFiles();
                if (listFiles != null) {
                    for (File delete : listFiles) {
                        delete.delete();
                    }
                }
            } catch (Exception e) {
            }
        }
    }
}
