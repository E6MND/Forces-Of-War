package com.crashlytics.android.internal;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

/* renamed from: com.crashlytics.android.internal.ay  reason: case insensitive filesystem */
public final class C0166ay {
    private static aB a = aB.a;
    private HttpURLConnection b = null;
    private URL c;
    private final String d;
    private aF e;
    private boolean f;
    private boolean g = true;
    private boolean h = false;
    /* access modifiers changed from: private */
    public int i = 8192;

    /* access modifiers changed from: private */
    public static String c(String str) {
        return (str == null || str.length() <= 0) ? "UTF-8" : str;
    }

    private static String c(CharSequence charSequence) throws aD {
        try {
            URL url = new URL(charSequence.toString());
            String host = url.getHost();
            int port = url.getPort();
            if (port != -1) {
                host = host + ':' + Integer.toString(port);
            }
            try {
                String aSCIIString = new URI(url.getProtocol(), host, url.getPath(), url.getQuery(), (String) null).toASCIIString();
                int indexOf = aSCIIString.indexOf(63);
                if (indexOf <= 0 || indexOf + 1 >= aSCIIString.length()) {
                    return aSCIIString;
                }
                return aSCIIString.substring(0, indexOf + 1) + aSCIIString.substring(indexOf + 1).replace("+", "%2B");
            } catch (URISyntaxException e2) {
                IOException iOException = new IOException("Parsing URI failed");
                iOException.initCause(e2);
                throw new aD(iOException);
            }
        } catch (IOException e3) {
            throw new aD(e3);
        }
    }

    private static String a(CharSequence charSequence, Map<?, ?> map) {
        String charSequence2 = charSequence.toString();
        if (map == null || map.isEmpty()) {
            return charSequence2;
        }
        StringBuilder sb = new StringBuilder(charSequence2);
        if (charSequence2.indexOf(58) + 2 == charSequence2.lastIndexOf(47)) {
            sb.append('/');
        }
        int indexOf = charSequence2.indexOf(63);
        int length = sb.length() - 1;
        if (indexOf == -1) {
            sb.append('?');
        } else if (indexOf < length && charSequence2.charAt(length) != '&') {
            sb.append('&');
        }
        Iterator<Map.Entry<?, ?>> it = map.entrySet().iterator();
        Map.Entry next = it.next();
        sb.append(next.getKey().toString());
        sb.append('=');
        Object value = next.getValue();
        if (value != null) {
            sb.append(value);
        }
        while (it.hasNext()) {
            sb.append('&');
            Map.Entry next2 = it.next();
            sb.append(next2.getKey().toString());
            sb.append('=');
            Object value2 = next2.getValue();
            if (value2 != null) {
                sb.append(value2);
            }
        }
        return sb.toString();
    }

    public static C0166ay a(CharSequence charSequence, Map<?, ?> map, boolean z) {
        return new C0166ay(c((CharSequence) a(charSequence, map)), "GET");
    }

    public static C0166ay b(CharSequence charSequence, Map<?, ?> map, boolean z) {
        return new C0166ay(c((CharSequence) a(charSequence, map)), "POST");
    }

    public static C0166ay a(CharSequence charSequence) throws aD {
        return new C0166ay(charSequence, "PUT");
    }

    public static C0166ay b(CharSequence charSequence) throws aD {
        return new C0166ay(charSequence, "DELETE");
    }

    private C0166ay(CharSequence charSequence, String str) throws aD {
        try {
            this.c = new URL(charSequence.toString());
            this.d = str;
        } catch (MalformedURLException e2) {
            throw new aD(e2);
        }
    }

    private HttpURLConnection e() {
        try {
            HttpURLConnection a2 = a.a(this.c);
            a2.setRequestMethod(this.d);
            return a2;
        } catch (IOException e2) {
            throw new aD(e2);
        }
    }

    public final String toString() {
        return a().getRequestMethod() + ' ' + a().getURL();
    }

    public final HttpURLConnection a() {
        if (this.b == null) {
            this.b = e();
        }
        return this.b;
    }

    public final int b() throws aD {
        try {
            g();
            return a().getResponseCode();
        } catch (IOException e2) {
            throw new aD(e2);
        }
    }

    private String d(String str) throws aD {
        h();
        int headerFieldInt = a().getHeaderFieldInt("Content-Length", -1);
        ByteArrayOutputStream byteArrayOutputStream = headerFieldInt > 0 ? new ByteArrayOutputStream(headerFieldInt) : new ByteArrayOutputStream();
        try {
            a((InputStream) new BufferedInputStream(f(), this.i), (OutputStream) byteArrayOutputStream);
            return byteArrayOutputStream.toString(c(str));
        } catch (IOException e2) {
            throw new aD(e2);
        }
    }

    public final String c() throws aD {
        return d(c(a("Content-Type"), "charset"));
    }

    private InputStream f() throws aD {
        if (b() < 400) {
            try {
                return a().getInputStream();
            } catch (IOException e2) {
                throw new aD(e2);
            }
        } else {
            InputStream errorStream = a().getErrorStream();
            if (errorStream != null) {
                return errorStream;
            }
            try {
                return a().getInputStream();
            } catch (IOException e3) {
                throw new aD(e3);
            }
        }
    }

    public final C0166ay a(int i2) {
        a().setConnectTimeout(10000);
        return this;
    }

    public final C0166ay a(String str, String str2) {
        a().setRequestProperty(str, str2);
        return this;
    }

    public final C0166ay a(Map.Entry<String, String> entry) {
        return a(entry.getKey(), entry.getValue());
    }

    public final String a(String str) throws aD {
        h();
        return a().getHeaderField(str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0042, code lost:
        r0 = r9.substring(r4 + 1, r3).trim();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String c(java.lang.String r9, java.lang.String r10) {
        /*
            r7 = 34
            r2 = 0
            r6 = 59
            r5 = -1
            if (r9 == 0) goto L_0x000e
            int r0 = r9.length()
            if (r0 != 0) goto L_0x0010
        L_0x000e:
            r0 = r2
        L_0x000f:
            return r0
        L_0x0010:
            int r1 = r9.length()
            int r0 = r9.indexOf(r6)
            int r3 = r0 + 1
            if (r3 == 0) goto L_0x001e
            if (r3 != r1) goto L_0x0020
        L_0x001e:
            r0 = r2
            goto L_0x000f
        L_0x0020:
            int r0 = r9.indexOf(r6, r3)
            if (r0 != r5) goto L_0x007b
            r0 = r3
            r3 = r1
        L_0x0028:
            if (r0 >= r3) goto L_0x0079
            r4 = 61
            int r4 = r9.indexOf(r4, r0)
            if (r4 == r5) goto L_0x006c
            if (r4 >= r3) goto L_0x006c
            java.lang.String r0 = r9.substring(r0, r4)
            java.lang.String r0 = r0.trim()
            boolean r0 = r10.equals(r0)
            if (r0 == 0) goto L_0x006c
            int r0 = r4 + 1
            java.lang.String r0 = r9.substring(r0, r3)
            java.lang.String r0 = r0.trim()
            int r4 = r0.length()
            if (r4 == 0) goto L_0x006c
            r1 = 2
            if (r4 <= r1) goto L_0x000f
            r1 = 0
            char r1 = r0.charAt(r1)
            if (r7 != r1) goto L_0x000f
            int r1 = r4 + -1
            char r1 = r0.charAt(r1)
            if (r7 != r1) goto L_0x000f
            r1 = 1
            int r2 = r4 + -1
            java.lang.String r0 = r0.substring(r1, r2)
            goto L_0x000f
        L_0x006c:
            int r3 = r3 + 1
            int r0 = r9.indexOf(r6, r3)
            if (r0 != r5) goto L_0x0075
            r0 = r1
        L_0x0075:
            r8 = r0
            r0 = r3
            r3 = r8
            goto L_0x0028
        L_0x0079:
            r0 = r2
            goto L_0x000f
        L_0x007b:
            r8 = r0
            r0 = r3
            r3 = r8
            goto L_0x0028
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crashlytics.android.internal.C0166ay.c(java.lang.String, java.lang.String):java.lang.String");
    }

    public final C0166ay a(boolean z) {
        a().setUseCaches(false);
        return this;
    }

    private C0166ay a(InputStream inputStream, OutputStream outputStream) throws IOException {
        return (C0166ay) new C0167az(this, inputStream, this.g, inputStream, outputStream).call();
    }

    private C0166ay g() throws IOException {
        if (this.e != null) {
            if (this.f) {
                this.e.a("\r\n--00content0boundary00--\r\n");
            }
            if (this.g) {
                try {
                    this.e.close();
                } catch (IOException e2) {
                }
            } else {
                this.e.close();
            }
            this.e = null;
        }
        return this;
    }

    private C0166ay h() throws aD {
        try {
            return g();
        } catch (IOException e2) {
            throw new aD(e2);
        }
    }

    private C0166ay i() throws IOException {
        if (this.e == null) {
            a().setDoOutput(true);
            this.e = new aF(a().getOutputStream(), c(a().getRequestProperty("Content-Type"), "charset"), this.i);
        }
        return this;
    }

    private C0166ay j() throws IOException {
        if (!this.f) {
            this.f = true;
            a("Content-Type", "multipart/form-data; boundary=00content0boundary00").i();
            this.e.a("--00content0boundary00\r\n");
        } else {
            this.e.a("\r\n--00content0boundary00\r\n");
        }
        return this;
    }

    private C0166ay a(String str, String str2, String str3) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("form-data; name=\"").append(str);
        if (str2 != null) {
            sb.append("\"; filename=\"").append(str2);
        }
        sb.append('\"');
        d("Content-Disposition", sb.toString());
        if (str3 != null) {
            d("Content-Type", str3);
        }
        return d((CharSequence) "\r\n");
    }

    public final C0166ay b(String str, String str2) {
        return b(str, (String) null, str2);
    }

    private C0166ay b(String str, String str2, String str3) throws aD {
        return a(str, str2, (String) null, str3);
    }

    private C0166ay a(String str, String str2, String str3, String str4) throws aD {
        try {
            j();
            a(str, str2, (String) null);
            this.e.a(str4);
            return this;
        } catch (IOException e2) {
            throw new aD(e2);
        }
    }

    public final C0166ay a(String str, Number number) throws aD {
        return b(str, (String) null, number != null ? number.toString() : null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x001e A[SYNTHETIC, Splitter:B:15:0x001e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.crashlytics.android.internal.C0166ay a(java.lang.String r4, java.lang.String r5, java.lang.String r6, java.io.File r7) throws com.crashlytics.android.internal.aD {
        /*
            r3 = this;
            r2 = 0
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x0013, all -> 0x0026 }
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0013, all -> 0x0026 }
            r0.<init>(r7)     // Catch:{ IOException -> 0x0013, all -> 0x0026 }
            r1.<init>(r0)     // Catch:{ IOException -> 0x0013, all -> 0x0026 }
            com.crashlytics.android.internal.ay r0 = r3.a((java.lang.String) r4, (java.lang.String) r5, (java.lang.String) r6, (java.io.InputStream) r1)     // Catch:{ IOException -> 0x0029 }
            r1.close()     // Catch:{ IOException -> 0x0022 }
        L_0x0012:
            return r0
        L_0x0013:
            r0 = move-exception
            r1 = r2
        L_0x0015:
            com.crashlytics.android.internal.aD r2 = new com.crashlytics.android.internal.aD     // Catch:{ all -> 0x001b }
            r2.<init>(r0)     // Catch:{ all -> 0x001b }
            throw r2     // Catch:{ all -> 0x001b }
        L_0x001b:
            r0 = move-exception
        L_0x001c:
            if (r1 == 0) goto L_0x0021
            r1.close()     // Catch:{ IOException -> 0x0024 }
        L_0x0021:
            throw r0
        L_0x0022:
            r1 = move-exception
            goto L_0x0012
        L_0x0024:
            r1 = move-exception
            goto L_0x0021
        L_0x0026:
            r0 = move-exception
            r1 = r2
            goto L_0x001c
        L_0x0029:
            r0 = move-exception
            goto L_0x0015
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crashlytics.android.internal.C0166ay.a(java.lang.String, java.lang.String, java.lang.String, java.io.File):com.crashlytics.android.internal.ay");
    }

    public final C0166ay a(String str, String str2, String str3, InputStream inputStream) throws aD {
        try {
            j();
            a(str, str2, str3);
            a(inputStream, (OutputStream) this.e);
            return this;
        } catch (IOException e2) {
            throw new aD(e2);
        }
    }

    private C0166ay d(String str, String str2) throws aD {
        return d((CharSequence) str).d((CharSequence) ": ").d((CharSequence) str2).d((CharSequence) "\r\n");
    }

    private C0166ay d(CharSequence charSequence) throws aD {
        try {
            i();
            this.e.a(charSequence.toString());
            return this;
        } catch (IOException e2) {
            throw new aD(e2);
        }
    }

    public final String d() {
        return a().getRequestMethod();
    }
}
