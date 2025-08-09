package com.crashlytics.android;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import com.crashlytics.android.internal.C0143ab;
import com.crashlytics.android.internal.C0149ah;
import com.crashlytics.android.internal.C0156ao;
import com.crashlytics.android.internal.C0157ap;
import com.crashlytics.android.internal.C0158aq;
import com.crashlytics.android.internal.C0162au;
import com.crashlytics.android.internal.C0184r;
import com.crashlytics.android.internal.C0188v;
import com.facebook.AppEventsConstants;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.Flushable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.Thread;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

/* renamed from: com.crashlytics.android.v  reason: case insensitive filesystem */
final class C0205v implements Thread.UncaughtExceptionHandler {
    static final FilenameFilter a = new C0206w();
    private static Comparator<File> b = new H();
    private static Comparator<File> c = new J();
    /* access modifiers changed from: private */
    public static final Pattern d = Pattern.compile("([\\d|A-Z|a-z]{12}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{12}).+");
    /* access modifiers changed from: private */
    public static final Map<String, String> e = Collections.singletonMap("X-CRASHLYTICS-SEND-FLAGS", AppEventsConstants.EVENT_PARAM_VALUE_YES);
    private static final C0135d f = C0135d.a(AppEventsConstants.EVENT_PARAM_VALUE_NO);
    private final AtomicInteger g;
    private final AtomicBoolean h;
    private final int i;
    private final Thread.UncaughtExceptionHandler j;
    private final File k;
    /* access modifiers changed from: private */
    public final File l;
    /* access modifiers changed from: private */
    public final AtomicBoolean m;
    private final String n;
    private final BroadcastReceiver o;
    private final BroadcastReceiver p;
    private final C0135d q;
    private final C0135d r;
    private final ExecutorService s;
    private ActivityManager.RunningAppProcessInfo t;
    /* access modifiers changed from: private */
    public C0158aq u;
    /* access modifiers changed from: private */
    public boolean v;
    private Thread[] w;
    private List<StackTraceElement[]> x;
    private StackTraceElement[] y;

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void a(com.crashlytics.android.C0205v r8, java.util.Date r9, java.lang.Thread r10, java.lang.Throwable r11) throws java.lang.Exception {
        /*
            r2 = 0
            java.io.File r0 = new java.io.File     // Catch:{ Exception -> 0x0079, all -> 0x0098 }
            java.io.File r1 = r8.k     // Catch:{ Exception -> 0x0079, all -> 0x0098 }
            java.lang.String r3 = "crash_marker"
            r0.<init>(r1, r3)     // Catch:{ Exception -> 0x0079, all -> 0x0098 }
            r0.createNewFile()     // Catch:{ Exception -> 0x0079, all -> 0x0098 }
            java.lang.String r0 = r8.n()     // Catch:{ Exception -> 0x0079, all -> 0x0098 }
            if (r0 == 0) goto L_0x0067
            com.crashlytics.android.Crashlytics.b(r0)     // Catch:{ Exception -> 0x0079, all -> 0x0098 }
            com.crashlytics.android.f r7 = new com.crashlytics.android.f     // Catch:{ Exception -> 0x0079, all -> 0x0098 }
            java.io.File r1 = r8.k     // Catch:{ Exception -> 0x0079, all -> 0x0098 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0079, all -> 0x0098 }
            r3.<init>()     // Catch:{ Exception -> 0x0079, all -> 0x0098 }
            java.lang.StringBuilder r0 = r3.append(r0)     // Catch:{ Exception -> 0x0079, all -> 0x0098 }
            java.lang.String r3 = "SessionCrash"
            java.lang.StringBuilder r0 = r0.append(r3)     // Catch:{ Exception -> 0x0079, all -> 0x0098 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0079, all -> 0x0098 }
            r7.<init>(r1, r0)     // Catch:{ Exception -> 0x0079, all -> 0x0098 }
            com.crashlytics.android.h r2 = com.crashlytics.android.C0139h.a((java.io.OutputStream) r7)     // Catch:{ Exception -> 0x00a7 }
            java.lang.String r5 = "crash"
            r6 = 1
            r0 = r8
            r1 = r9
            r3 = r10
            r4 = r11
            r0.a((java.util.Date) r1, (com.crashlytics.android.C0139h) r2, (java.lang.Thread) r3, (java.lang.Throwable) r4, (java.lang.String) r5, (boolean) r6)     // Catch:{ Exception -> 0x00a7 }
            r0 = r7
        L_0x003f:
            java.lang.String r1 = "Failed to flush to session begin file."
            com.crashlytics.android.internal.C0143ab.a((java.io.Flushable) r2, (java.lang.String) r1)
            java.lang.String r1 = "Failed to close fatal exception file output stream."
            com.crashlytics.android.internal.C0143ab.a((java.io.Closeable) r0, (java.lang.String) r1)
        L_0x0049:
            r8.m()
            r8.l()
            java.io.File r0 = r8.k
            java.io.FilenameFilter r1 = a
            r2 = 4
            java.util.Comparator<java.io.File> r3 = c
            com.crashlytics.android.C0133b.a(r0, r1, r2, r3)
            com.crashlytics.android.Crashlytics r0 = com.crashlytics.android.Crashlytics.getInstance()
            boolean r0 = r0.j()
            if (r0 != 0) goto L_0x0066
            r8.p()
        L_0x0066:
            return
        L_0x0067:
            com.crashlytics.android.internal.v r0 = com.crashlytics.android.internal.C0188v.a()     // Catch:{ Exception -> 0x0079, all -> 0x0098 }
            com.crashlytics.android.internal.q r0 = r0.b()     // Catch:{ Exception -> 0x0079, all -> 0x0098 }
            java.lang.String r1 = "Crashlytics"
            java.lang.String r3 = "Tried to write a fatal exception while no session was open."
            r4 = 0
            r0.a((java.lang.String) r1, (java.lang.String) r3, (java.lang.Throwable) r4)     // Catch:{ Exception -> 0x0079, all -> 0x0098 }
            r0 = r2
            goto L_0x003f
        L_0x0079:
            r0 = move-exception
            r7 = r2
        L_0x007b:
            com.crashlytics.android.internal.v r1 = com.crashlytics.android.internal.C0188v.a()     // Catch:{ all -> 0x00a5 }
            com.crashlytics.android.internal.q r1 = r1.b()     // Catch:{ all -> 0x00a5 }
            java.lang.String r3 = "Crashlytics"
            java.lang.String r4 = "An error occurred in the fatal exception logger"
            r1.a((java.lang.String) r3, (java.lang.String) r4, (java.lang.Throwable) r0)     // Catch:{ all -> 0x00a5 }
            r8.a((java.lang.Throwable) r0, (java.io.OutputStream) r7)     // Catch:{ all -> 0x00a5 }
            java.lang.String r0 = "Failed to flush to session begin file."
            com.crashlytics.android.internal.C0143ab.a((java.io.Flushable) r2, (java.lang.String) r0)
            java.lang.String r0 = "Failed to close fatal exception file output stream."
            com.crashlytics.android.internal.C0143ab.a((java.io.Closeable) r7, (java.lang.String) r0)
            goto L_0x0049
        L_0x0098:
            r0 = move-exception
            r7 = r2
        L_0x009a:
            java.lang.String r1 = "Failed to flush to session begin file."
            com.crashlytics.android.internal.C0143ab.a((java.io.Flushable) r2, (java.lang.String) r1)
            java.lang.String r1 = "Failed to close fatal exception file output stream."
            com.crashlytics.android.internal.C0143ab.a((java.io.Closeable) r7, (java.lang.String) r1)
            throw r0
        L_0x00a5:
            r0 = move-exception
            goto L_0x009a
        L_0x00a7:
            r0 = move-exception
            goto L_0x007b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crashlytics.android.C0205v.a(com.crashlytics.android.v, java.util.Date, java.lang.Thread, java.lang.Throwable):void");
    }

    static /* synthetic */ void b(C0205v vVar, Date date, Thread thread, Throwable th) {
        C0137f fVar;
        C0139h hVar = null;
        String n2 = vVar.n();
        if (n2 != null) {
            Crashlytics.a(n2);
            try {
                C0188v.a().b().a(Crashlytics.TAG, "Crashlytics is logging non-fatal exception \"" + th + "\" from thread " + thread.getName());
                C0137f fVar2 = new C0137f(vVar.k, n2 + "SessionEvent" + C0143ab.a(vVar.g.getAndIncrement()));
                try {
                    hVar = C0139h.a((OutputStream) fVar2);
                    vVar.a(date, hVar, thread, th, "error", false);
                    C0143ab.a((Flushable) hVar, "Failed to flush to non-fatal file.");
                    C0143ab.a((Closeable) fVar2, "Failed to close non-fatal file output stream.");
                } catch (Exception e2) {
                    e = e2;
                    fVar = fVar2;
                    try {
                        C0188v.a().b().a(Crashlytics.TAG, "An error occurred in the non-fatal exception logger", (Throwable) e);
                        vVar.a((Throwable) e, (OutputStream) fVar);
                        C0143ab.a((Flushable) hVar, "Failed to flush to non-fatal file.");
                        C0143ab.a((Closeable) fVar, "Failed to close non-fatal file output stream.");
                        vVar.a(n2, 64);
                    } catch (Throwable th2) {
                        th = th2;
                        C0143ab.a((Flushable) hVar, "Failed to flush to non-fatal file.");
                        C0143ab.a((Closeable) fVar, "Failed to close non-fatal file output stream.");
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fVar = fVar2;
                    C0143ab.a((Flushable) hVar, "Failed to flush to non-fatal file.");
                    C0143ab.a((Closeable) fVar, "Failed to close non-fatal file output stream.");
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                fVar = null;
                C0188v.a().b().a(Crashlytics.TAG, "An error occurred in the non-fatal exception logger", (Throwable) e);
                vVar.a((Throwable) e, (OutputStream) fVar);
                C0143ab.a((Flushable) hVar, "Failed to flush to non-fatal file.");
                C0143ab.a((Closeable) fVar, "Failed to close non-fatal file output stream.");
                vVar.a(n2, 64);
            } catch (Throwable th4) {
                th = th4;
                fVar = null;
                C0143ab.a((Flushable) hVar, "Failed to flush to non-fatal file.");
                C0143ab.a((Closeable) fVar, "Failed to close non-fatal file output stream.");
                throw th;
            }
            try {
                vVar.a(n2, 64);
            } catch (Exception e4) {
                C0188v.a().b().a(Crashlytics.TAG, "An error occurred when trimming non-fatal files.", (Throwable) e4);
            }
        } else {
            C0188v.a().b().a(Crashlytics.TAG, "Tried to write a non-fatal exception while no session was open.", (Throwable) null);
        }
    }

    static {
        new K();
    }

    public C0205v(Thread.UncaughtExceptionHandler uncaughtExceptionHandler, CrashlyticsListener crashlyticsListener, String str) {
        this(uncaughtExceptionHandler, crashlyticsListener, C0149ah.a("Crashlytics Exception Handler"), str);
    }

    private C0205v(Thread.UncaughtExceptionHandler uncaughtExceptionHandler, CrashlyticsListener crashlyticsListener, ExecutorService executorService, String str) {
        this.g = new AtomicInteger(0);
        this.h = new AtomicBoolean(false);
        this.j = uncaughtExceptionHandler;
        this.s = executorService;
        this.m = new AtomicBoolean(false);
        this.k = C0188v.a().h();
        this.l = new File(this.k, "initialization_marker");
        this.n = String.format(Locale.US, "Crashlytics Android SDK/%s", new Object[]{Crashlytics.getInstance().getVersion()});
        this.i = 8;
        C0188v.a().b().a(Crashlytics.TAG, "Checking for previous crash marker.");
        File file = new File(C0188v.a().h(), "crash_marker");
        if (file.exists()) {
            file.delete();
            if (crashlyticsListener != null) {
                try {
                    crashlyticsListener.crashlyticsDidDetectCrashDuringPreviousExecution();
                } catch (Exception e2) {
                    C0188v.a().b().a(Crashlytics.TAG, "Exception thrown by CrashlyticsListener while notifying of previous crash.", (Throwable) e2);
                }
            }
        }
        this.q = C0135d.a(Crashlytics.d());
        this.r = str == null ? null : C0135d.a(str.replace("-", ""));
        this.p = new L(this);
        IntentFilter intentFilter = new IntentFilter("android.intent.action.ACTION_POWER_CONNECTED");
        this.o = new M(this);
        IntentFilter intentFilter2 = new IntentFilter("android.intent.action.ACTION_POWER_DISCONNECTED");
        Crashlytics.getInstance().getContext().registerReceiver(this.p, intentFilter);
        Crashlytics.getInstance().getContext().registerReceiver(this.o, intentFilter2);
        this.h.set(true);
    }

    /* access modifiers changed from: package-private */
    public final boolean a() {
        return this.m.get();
    }

    public final synchronized void uncaughtException(Thread thread, Throwable ex) {
        this.m.set(true);
        try {
            C0188v.a().b().a(Crashlytics.TAG, "Crashlytics is handling uncaught exception \"" + ex + "\" from thread " + thread.getName());
            if (!this.h.getAndSet(true)) {
                C0188v.a().b().a(Crashlytics.TAG, "Unregistering power receivers.");
                Crashlytics.getInstance().getContext().unregisterReceiver(this.p);
                Crashlytics.getInstance().getContext().unregisterReceiver(this.o);
            }
            a(new N(this, new Date(), thread, ex));
            C0188v.a().b().a(Crashlytics.TAG, "Crashlytics completed exception processing. Invoking default exception handler.");
            this.j.uncaughtException(thread, ex);
            this.m.set(false);
        } catch (Exception e2) {
            C0188v.a().b().a(Crashlytics.TAG, "An error occurred in the uncaught exception handler", (Throwable) e2);
            C0188v.a().b().a(Crashlytics.TAG, "Crashlytics completed exception processing. Invoking default exception handler.");
            this.j.uncaughtException(thread, ex);
            this.m.set(false);
        } catch (Throwable th) {
            C0188v.a().b().a(Crashlytics.TAG, "Crashlytics completed exception processing. Invoking default exception handler.");
            this.j.uncaughtException(thread, ex);
            this.m.set(false);
            throw th;
        }
        return;
    }

    /* access modifiers changed from: package-private */
    public final boolean b() {
        return ((Boolean) a(new O(this))).booleanValue();
    }

    /* access modifiers changed from: package-private */
    public final void a(Thread thread, Throwable th) {
        a((Runnable) new P(this, new Date(), thread, th));
    }

    /* access modifiers changed from: package-private */
    public final void a(long j2, String str) {
        b(new C0207x(this, j2, str));
    }

    private C0135d a(C0158aq aqVar) {
        if (aqVar == null) {
            return null;
        }
        int[] iArr = {0};
        byte[] bArr = new byte[aqVar.a()];
        try {
            aqVar.a((C0162au) new C0208y(this, bArr, iArr));
        } catch (IOException e2) {
            C0188v.a().b().a(Crashlytics.TAG, "A problem occurred while reading the Crashlytics log file.", (Throwable) e2);
        }
        return C0135d.a(bArr, 0, iArr[0]);
    }

    /* access modifiers changed from: package-private */
    public final void c() {
        b(new C0209z(this));
    }

    /* access modifiers changed from: package-private */
    public final void d() {
        b(new A(this));
    }

    /* access modifiers changed from: package-private */
    public final void e() {
        b(new B(this));
    }

    /* access modifiers changed from: package-private */
    public final boolean f() {
        return ((Boolean) a(new C(this))).booleanValue();
    }

    static void a(C0158aq aqVar, int i2, long j2, String str) {
        String str2;
        if (aqVar != null) {
            if (str == null) {
                str2 = "null";
            } else {
                str2 = str;
            }
            try {
                if (str2.length() > 16384) {
                    str2 = "..." + str2.substring(str2.length() - 16384);
                }
                aqVar.a(String.format(Locale.US, "%d %s%n", new Object[]{Long.valueOf(j2), str2.replaceAll("\r", " ").replaceAll("\n", " ")}).getBytes("UTF-8"));
                while (!aqVar.b() && aqVar.a() > 65536) {
                    aqVar.c();
                }
            } catch (IOException e2) {
                C0188v.a().b().a(Crashlytics.TAG, "There was a problem writing to the Crashlytics log.", (Throwable) e2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean g() {
        return o().length > 0;
    }

    /* access modifiers changed from: private */
    public boolean k() {
        File file;
        if (!C0143ab.a(Crashlytics.getInstance().getContext(), "com.crashlytics.CollectCustomLogs", true)) {
            C0188v.a().b().a(Crashlytics.TAG, "Preferences requested not to collect custom logs. Aborting log file creation.");
            return false;
        }
        C0143ab.a((Closeable) this.u, "Could not close log file: " + this.u);
        try {
            file = new File(C0188v.a().h(), "crashlytics-userlog-" + UUID.randomUUID().toString() + ".temp");
            try {
                this.u = new C0158aq(file);
                file.delete();
                return true;
            } catch (Exception e2) {
                e = e2;
                C0188v.a().b().a(Crashlytics.TAG, "Could not create log file: " + file, (Throwable) e);
                return false;
            }
        } catch (Exception e3) {
            e = e3;
            file = null;
            C0188v.a().b().a(Crashlytics.TAG, "Could not create log file: " + file, (Throwable) e);
            return false;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: com.crashlytics.android.f} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: com.crashlytics.android.f} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: com.crashlytics.android.f} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: com.crashlytics.android.f} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: com.crashlytics.android.h} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: com.crashlytics.android.h} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: com.crashlytics.android.f} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v10, resolved type: com.crashlytics.android.h} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: com.crashlytics.android.f} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: com.crashlytics.android.f} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v11, resolved type: com.crashlytics.android.h} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: com.crashlytics.android.f} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v15, resolved type: com.crashlytics.android.h} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v22, resolved type: com.crashlytics.android.h} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v23, resolved type: com.crashlytics.android.h} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: com.crashlytics.android.f} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: com.crashlytics.android.f} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v12, resolved type: com.crashlytics.android.f} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v13, resolved type: com.crashlytics.android.f} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v14, resolved type: com.crashlytics.android.h} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v15, resolved type: com.crashlytics.android.f} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v16, resolved type: com.crashlytics.android.f} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v17, resolved type: com.crashlytics.android.f} */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r1v12, types: [java.io.Flushable] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void l() throws java.lang.Exception {
        /*
            r13 = this;
            r2 = 0
            java.util.Date r0 = new java.util.Date
            r0.<init>()
            com.crashlytics.android.e r1 = new com.crashlytics.android.e
            com.crashlytics.android.Crashlytics r3 = com.crashlytics.android.Crashlytics.getInstance()
            com.crashlytics.android.internal.ao r3 = r3.b()
            r1.<init>(r3)
            java.lang.String r4 = r1.toString()
            com.crashlytics.android.internal.v r1 = com.crashlytics.android.internal.C0188v.a()
            com.crashlytics.android.internal.q r1 = r1.b()
            java.lang.String r3 = "Crashlytics"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "Opening an new session with ID "
            r5.<init>(r6)
            java.lang.StringBuilder r5 = r5.append(r4)
            java.lang.String r5 = r5.toString()
            r1.a(r3, r5)
            com.crashlytics.android.f r3 = new com.crashlytics.android.f     // Catch:{ Exception -> 0x01d6, all -> 0x0225 }
            com.crashlytics.android.internal.v r1 = com.crashlytics.android.internal.C0188v.a()     // Catch:{ Exception -> 0x01d6, all -> 0x0225 }
            java.io.File r1 = r1.h()     // Catch:{ Exception -> 0x01d6, all -> 0x0225 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01d6, all -> 0x0225 }
            r5.<init>()     // Catch:{ Exception -> 0x01d6, all -> 0x0225 }
            java.lang.StringBuilder r5 = r5.append(r4)     // Catch:{ Exception -> 0x01d6, all -> 0x0225 }
            java.lang.String r6 = "BeginSession"
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Exception -> 0x01d6, all -> 0x0225 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x01d6, all -> 0x0225 }
            r3.<init>(r1, r5)     // Catch:{ Exception -> 0x01d6, all -> 0x0225 }
            com.crashlytics.android.h r1 = com.crashlytics.android.C0139h.a((java.io.OutputStream) r3)     // Catch:{ Exception -> 0x022d, all -> 0x0228 }
            r5 = 1
            java.lang.String r6 = r13.n     // Catch:{ Exception -> 0x0231, all -> 0x022a }
            com.crashlytics.android.d r6 = com.crashlytics.android.C0135d.a(r6)     // Catch:{ Exception -> 0x0231, all -> 0x022a }
            r1.a((int) r5, (com.crashlytics.android.C0135d) r6)     // Catch:{ Exception -> 0x0231, all -> 0x022a }
            r5 = 2
            com.crashlytics.android.d r6 = com.crashlytics.android.C0135d.a(r4)     // Catch:{ Exception -> 0x0231, all -> 0x022a }
            r1.a((int) r5, (com.crashlytics.android.C0135d) r6)     // Catch:{ Exception -> 0x0231, all -> 0x022a }
            r5 = 3
            long r6 = r0.getTime()     // Catch:{ Exception -> 0x0231, all -> 0x022a }
            r8 = 1000(0x3e8, double:4.94E-321)
            long r6 = r6 / r8
            r1.a((int) r5, (long) r6)     // Catch:{ Exception -> 0x0231, all -> 0x022a }
            java.lang.String r0 = "Failed to flush to session begin file."
            com.crashlytics.android.internal.C0143ab.a((java.io.Flushable) r1, (java.lang.String) r0)
            java.lang.String r0 = "Failed to close begin session file."
            com.crashlytics.android.internal.C0143ab.a((java.io.Closeable) r3, (java.lang.String) r0)
            com.crashlytics.android.f r3 = new com.crashlytics.android.f     // Catch:{ Exception -> 0x01ea, all -> 0x0215 }
            com.crashlytics.android.internal.v r0 = com.crashlytics.android.internal.C0188v.a()     // Catch:{ Exception -> 0x01ea, all -> 0x0215 }
            java.io.File r0 = r0.h()     // Catch:{ Exception -> 0x01ea, all -> 0x0215 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01ea, all -> 0x0215 }
            r1.<init>()     // Catch:{ Exception -> 0x01ea, all -> 0x0215 }
            java.lang.StringBuilder r1 = r1.append(r4)     // Catch:{ Exception -> 0x01ea, all -> 0x0215 }
            java.lang.String r5 = "SessionApp"
            java.lang.StringBuilder r1 = r1.append(r5)     // Catch:{ Exception -> 0x01ea, all -> 0x0215 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x01ea, all -> 0x0215 }
            r3.<init>(r0, r1)     // Catch:{ Exception -> 0x01ea, all -> 0x0215 }
            com.crashlytics.android.h r1 = com.crashlytics.android.C0139h.a((java.io.OutputStream) r3)     // Catch:{ Exception -> 0x021e, all -> 0x0219 }
            java.lang.String r0 = com.crashlytics.android.Crashlytics.d()     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            com.crashlytics.android.d r0 = com.crashlytics.android.C0135d.a(r0)     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            java.lang.String r5 = com.crashlytics.android.Crashlytics.g()     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            com.crashlytics.android.d r5 = com.crashlytics.android.C0135d.a(r5)     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            java.lang.String r6 = com.crashlytics.android.Crashlytics.f()     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            com.crashlytics.android.d r6 = com.crashlytics.android.C0135d.a(r6)     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            java.lang.String r7 = com.crashlytics.android.Crashlytics.h()     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            com.crashlytics.android.C0135d.a(r7)     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            com.crashlytics.android.Crashlytics r7 = com.crashlytics.android.Crashlytics.getInstance()     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            android.content.Context r7 = r7.getContext()     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            java.lang.String r7 = r7.getPackageCodePath()     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            com.crashlytics.android.C0135d.a(r7)     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            com.crashlytics.android.Crashlytics r7 = com.crashlytics.android.Crashlytics.getInstance()     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            com.crashlytics.android.internal.ao r7 = r7.b()     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            java.lang.String r7 = r7.b()     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            com.crashlytics.android.d r7 = com.crashlytics.android.C0135d.a(r7)     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            java.lang.String r8 = com.crashlytics.android.Crashlytics.e()     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            com.crashlytics.android.internal.ai r8 = com.crashlytics.android.internal.C0150ai.a(r8)     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            int r8 = r8.a()     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            r9 = 7
            r10 = 2
            r1.g(r9, r10)     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            r9 = 1
            int r9 = com.crashlytics.android.C0139h.b((int) r9, (com.crashlytics.android.C0135d) r0)     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            int r9 = r9 + 0
            r10 = 2
            int r10 = com.crashlytics.android.C0139h.b((int) r10, (com.crashlytics.android.C0135d) r5)     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            int r9 = r9 + r10
            r10 = 3
            int r10 = com.crashlytics.android.C0139h.b((int) r10, (com.crashlytics.android.C0135d) r6)     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            int r9 = r9 + r10
            int r10 = q()     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            r11 = 5
            int r11 = com.crashlytics.android.C0139h.a((int) r11)     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            int r12 = com.crashlytics.android.C0139h.c(r10)     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            int r11 = r11 + r12
            int r10 = r10 + r11
            int r9 = r9 + r10
            r10 = 6
            int r10 = com.crashlytics.android.C0139h.b((int) r10, (com.crashlytics.android.C0135d) r7)     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            int r9 = r9 + r10
            r10 = 10
            int r10 = com.crashlytics.android.C0139h.e(r10, r8)     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            int r9 = r9 + r10
            r1.b(r9)     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            r9 = 1
            r1.a((int) r9, (com.crashlytics.android.C0135d) r0)     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            r0 = 2
            r1.a((int) r0, (com.crashlytics.android.C0135d) r5)     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            r0 = 3
            r1.a((int) r0, (com.crashlytics.android.C0135d) r6)     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            r0 = 5
            r5 = 2
            r1.g(r0, r5)     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            int r0 = q()     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            r1.b(r0)     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            r0 = 1
            com.crashlytics.android.Crashlytics r5 = com.crashlytics.android.Crashlytics.getInstance()     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            android.content.Context r5 = r5.getContext()     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            r6 = 0
            java.lang.String r5 = com.crashlytics.android.internal.C0184r.a((android.content.Context) r5, (boolean) r6)     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            r1.a((int) r0, (java.lang.String) r5)     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            r0 = 6
            r1.a((int) r0, (com.crashlytics.android.C0135d) r7)     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            r0 = 10
            r1.b((int) r0, (int) r8)     // Catch:{ Exception -> 0x0221, all -> 0x021c }
            java.lang.String r0 = "Failed to flush to session app file."
            com.crashlytics.android.internal.C0143ab.a((java.io.Flushable) r1, (java.lang.String) r0)
            java.lang.String r0 = "Failed to close session app file."
            com.crashlytics.android.internal.C0143ab.a((java.io.Closeable) r3, (java.lang.String) r0)
            com.crashlytics.android.f r1 = new com.crashlytics.android.f     // Catch:{ Exception -> 0x01fe, all -> 0x0210 }
            com.crashlytics.android.internal.v r0 = com.crashlytics.android.internal.C0188v.a()     // Catch:{ Exception -> 0x01fe, all -> 0x0210 }
            java.io.File r0 = r0.h()     // Catch:{ Exception -> 0x01fe, all -> 0x0210 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01fe, all -> 0x0210 }
            r3.<init>()     // Catch:{ Exception -> 0x01fe, all -> 0x0210 }
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Exception -> 0x01fe, all -> 0x0210 }
            java.lang.String r5 = "SessionOS"
            java.lang.StringBuilder r3 = r3.append(r5)     // Catch:{ Exception -> 0x01fe, all -> 0x0210 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x01fe, all -> 0x0210 }
            r1.<init>(r0, r3)     // Catch:{ Exception -> 0x01fe, all -> 0x0210 }
            com.crashlytics.android.h r2 = com.crashlytics.android.C0139h.a((java.io.OutputStream) r1)     // Catch:{ Exception -> 0x0213 }
            java.lang.String r0 = android.os.Build.VERSION.RELEASE     // Catch:{ Exception -> 0x0213 }
            com.crashlytics.android.d r0 = com.crashlytics.android.C0135d.a(r0)     // Catch:{ Exception -> 0x0213 }
            java.lang.String r3 = android.os.Build.VERSION.CODENAME     // Catch:{ Exception -> 0x0213 }
            com.crashlytics.android.d r3 = com.crashlytics.android.C0135d.a(r3)     // Catch:{ Exception -> 0x0213 }
            boolean r5 = com.crashlytics.android.internal.C0143ab.e()     // Catch:{ Exception -> 0x0213 }
            r6 = 8
            r7 = 2
            r2.g(r6, r7)     // Catch:{ Exception -> 0x0213 }
            r6 = 1
            r7 = 3
            int r6 = com.crashlytics.android.C0139h.e(r6, r7)     // Catch:{ Exception -> 0x0213 }
            int r6 = r6 + 0
            r7 = 2
            int r7 = com.crashlytics.android.C0139h.b((int) r7, (com.crashlytics.android.C0135d) r0)     // Catch:{ Exception -> 0x0213 }
            int r6 = r6 + r7
            r7 = 3
            int r7 = com.crashlytics.android.C0139h.b((int) r7, (com.crashlytics.android.C0135d) r3)     // Catch:{ Exception -> 0x0213 }
            int r6 = r6 + r7
            r7 = 4
            int r7 = com.crashlytics.android.C0139h.b((int) r7, (boolean) r5)     // Catch:{ Exception -> 0x0213 }
            int r6 = r6 + r7
            r2.b(r6)     // Catch:{ Exception -> 0x0213 }
            r6 = 1
            r7 = 3
            r2.b((int) r6, (int) r7)     // Catch:{ Exception -> 0x0213 }
            r6 = 2
            r2.a((int) r6, (com.crashlytics.android.C0135d) r0)     // Catch:{ Exception -> 0x0213 }
            r0 = 3
            r2.a((int) r0, (com.crashlytics.android.C0135d) r3)     // Catch:{ Exception -> 0x0213 }
            r0 = 4
            r2.a((int) r0, (boolean) r5)     // Catch:{ Exception -> 0x0213 }
            java.lang.String r0 = "Failed to flush to session OS file."
            com.crashlytics.android.internal.C0143ab.a((java.io.Flushable) r2, (java.lang.String) r0)
            java.lang.String r0 = "Failed to close session OS file."
            com.crashlytics.android.internal.C0143ab.a((java.io.Closeable) r1, (java.lang.String) r0)
            r13.c((java.lang.String) r4)
            return
        L_0x01d6:
            r0 = move-exception
            r1 = r2
        L_0x01d8:
            r13.a((java.lang.Throwable) r0, (java.io.OutputStream) r2)     // Catch:{ all -> 0x01dc }
            throw r0     // Catch:{ all -> 0x01dc }
        L_0x01dc:
            r0 = move-exception
            r3 = r2
            r2 = r1
        L_0x01df:
            java.lang.String r1 = "Failed to flush to session begin file."
            com.crashlytics.android.internal.C0143ab.a((java.io.Flushable) r2, (java.lang.String) r1)
            java.lang.String r1 = "Failed to close begin session file."
            com.crashlytics.android.internal.C0143ab.a((java.io.Closeable) r3, (java.lang.String) r1)
            throw r0
        L_0x01ea:
            r0 = move-exception
            r1 = r2
        L_0x01ec:
            r13.a((java.lang.Throwable) r0, (java.io.OutputStream) r1)     // Catch:{ all -> 0x01f0 }
            throw r0     // Catch:{ all -> 0x01f0 }
        L_0x01f0:
            r0 = move-exception
            r3 = r1
            r1 = r2
        L_0x01f3:
            java.lang.String r2 = "Failed to flush to session app file."
            com.crashlytics.android.internal.C0143ab.a((java.io.Flushable) r1, (java.lang.String) r2)
            java.lang.String r1 = "Failed to close session app file."
            com.crashlytics.android.internal.C0143ab.a((java.io.Closeable) r3, (java.lang.String) r1)
            throw r0
        L_0x01fe:
            r0 = move-exception
            r1 = r2
        L_0x0200:
            r13.a((java.lang.Throwable) r0, (java.io.OutputStream) r1)     // Catch:{ all -> 0x0204 }
            throw r0     // Catch:{ all -> 0x0204 }
        L_0x0204:
            r0 = move-exception
        L_0x0205:
            java.lang.String r3 = "Failed to flush to session OS file."
            com.crashlytics.android.internal.C0143ab.a((java.io.Flushable) r2, (java.lang.String) r3)
            java.lang.String r2 = "Failed to close session OS file."
            com.crashlytics.android.internal.C0143ab.a((java.io.Closeable) r1, (java.lang.String) r2)
            throw r0
        L_0x0210:
            r0 = move-exception
            r1 = r2
            goto L_0x0205
        L_0x0213:
            r0 = move-exception
            goto L_0x0200
        L_0x0215:
            r0 = move-exception
            r1 = r2
            r3 = r2
            goto L_0x01f3
        L_0x0219:
            r0 = move-exception
            r1 = r2
            goto L_0x01f3
        L_0x021c:
            r0 = move-exception
            goto L_0x01f3
        L_0x021e:
            r0 = move-exception
            r1 = r3
            goto L_0x01ec
        L_0x0221:
            r0 = move-exception
            r2 = r1
            r1 = r3
            goto L_0x01ec
        L_0x0225:
            r0 = move-exception
            r3 = r2
            goto L_0x01df
        L_0x0228:
            r0 = move-exception
            goto L_0x01df
        L_0x022a:
            r0 = move-exception
            r2 = r1
            goto L_0x01df
        L_0x022d:
            r0 = move-exception
            r1 = r2
            r2 = r3
            goto L_0x01d8
        L_0x0231:
            r0 = move-exception
            r2 = r3
            goto L_0x01d8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crashlytics.android.C0205v.l():void");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x038b, code lost:
        r2 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0395, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0396, code lost:
        r3 = r4;
        r4 = r5;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x038b A[ExcHandler: all (th java.lang.Throwable), Splitter:B:44:0x01cc] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m() throws java.lang.Exception {
        /*
            r20 = this;
            java.util.HashSet r3 = new java.util.HashSet
            r3.<init>()
            java.io.File[] r4 = r20.o()
            java.util.Comparator<java.io.File> r2 = b
            java.util.Arrays.sort(r4, r2)
            r2 = 8
            int r5 = r4.length
            int r5 = java.lang.Math.min(r2, r5)
            r2 = 0
        L_0x0016:
            if (r2 >= r5) goto L_0x0024
            r6 = r4[r2]
            java.lang.String r6 = a((java.io.File) r6)
            r3.add(r6)
            int r2 = r2 + 1
            goto L_0x0016
        L_0x0024:
            com.crashlytics.android.Q r2 = new com.crashlytics.android.Q
            r4 = 0
            r2.<init>(r4)
            r0 = r20
            java.io.File[] r4 = r0.a((java.io.FilenameFilter) r2)
            int r5 = r4.length
            r2 = 0
        L_0x0032:
            if (r2 >= r5) goto L_0x0070
            r6 = r4[r2]
            java.lang.String r7 = r6.getName()
            java.util.regex.Pattern r8 = d
            java.util.regex.Matcher r8 = r8.matcher(r7)
            r8.matches()
            r9 = 1
            java.lang.String r8 = r8.group(r9)
            boolean r8 = r3.contains(r8)
            if (r8 != 0) goto L_0x006d
            com.crashlytics.android.internal.v r8 = com.crashlytics.android.internal.C0188v.a()
            com.crashlytics.android.internal.q r8 = r8.b()
            java.lang.String r9 = "Crashlytics"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r11 = "Trimming open session file: "
            r10.<init>(r11)
            java.lang.StringBuilder r7 = r10.append(r7)
            java.lang.String r7 = r7.toString()
            r8.a(r9, r7)
            r6.delete()
        L_0x006d:
            int r2 = r2 + 1
            goto L_0x0032
        L_0x0070:
            java.lang.String r2 = r20.n()
            if (r2 == 0) goto L_0x037b
            r5 = 0
            r3 = 0
            com.crashlytics.android.f r4 = new com.crashlytics.android.f     // Catch:{ Exception -> 0x02f7, all -> 0x039a }
            r0 = r20
            java.io.File r6 = r0.k     // Catch:{ Exception -> 0x02f7, all -> 0x039a }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x02f7, all -> 0x039a }
            r7.<init>()     // Catch:{ Exception -> 0x02f7, all -> 0x039a }
            java.lang.StringBuilder r2 = r7.append(r2)     // Catch:{ Exception -> 0x02f7, all -> 0x039a }
            java.lang.String r7 = "SessionUser"
            java.lang.StringBuilder r2 = r2.append(r7)     // Catch:{ Exception -> 0x02f7, all -> 0x039a }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x02f7, all -> 0x039a }
            r4.<init>(r6, r2)     // Catch:{ Exception -> 0x02f7, all -> 0x039a }
            com.crashlytics.android.h r3 = com.crashlytics.android.C0139h.a((java.io.OutputStream) r4)     // Catch:{ Exception -> 0x039e }
            com.crashlytics.android.Crashlytics r2 = com.crashlytics.android.Crashlytics.getInstance()     // Catch:{ Exception -> 0x039e }
            java.lang.String r2 = r2.m()     // Catch:{ Exception -> 0x039e }
            com.crashlytics.android.Crashlytics r5 = com.crashlytics.android.Crashlytics.getInstance()     // Catch:{ Exception -> 0x039e }
            java.lang.String r5 = r5.o()     // Catch:{ Exception -> 0x039e }
            com.crashlytics.android.Crashlytics r6 = com.crashlytics.android.Crashlytics.getInstance()     // Catch:{ Exception -> 0x039e }
            java.lang.String r7 = r6.n()     // Catch:{ Exception -> 0x039e }
            if (r2 != 0) goto L_0x02a0
            if (r5 != 0) goto L_0x02a0
            if (r7 != 0) goto L_0x02a0
            java.lang.String r2 = "Failed to flush session user file."
            com.crashlytics.android.internal.C0143ab.a((java.io.Flushable) r3, (java.lang.String) r2)
            java.lang.String r2 = "Failed to close session user file."
            com.crashlytics.android.internal.C0143ab.a((java.io.Closeable) r4, (java.lang.String) r2)
        L_0x00c0:
            com.crashlytics.android.Crashlytics r2 = com.crashlytics.android.Crashlytics.getInstance()
            com.crashlytics.android.internal.aR r2 = r2.r()
            if (r2 == 0) goto L_0x036b
            int r9 = r2.a
            com.crashlytics.android.internal.v r2 = com.crashlytics.android.internal.C0188v.a()
            com.crashlytics.android.internal.q r2 = r2.b()
            java.lang.String r3 = "Crashlytics"
            java.lang.String r4 = "Closing all open sessions."
            r2.a(r3, r4)
            java.io.File[] r10 = r20.o()
            if (r10 == 0) goto L_0x037a
            int r2 = r10.length
            if (r2 <= 0) goto L_0x037a
            int r11 = r10.length
            r2 = 0
            r7 = r2
        L_0x00e7:
            if (r7 >= r11) goto L_0x037a
            r12 = r10[r7]
            java.lang.String r13 = a((java.io.File) r12)
            com.crashlytics.android.internal.v r2 = com.crashlytics.android.internal.C0188v.a()
            com.crashlytics.android.internal.q r2 = r2.b()
            java.lang.String r3 = "Crashlytics"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Closing session: "
            r4.<init>(r5)
            java.lang.StringBuilder r4 = r4.append(r13)
            java.lang.String r4 = r4.toString()
            r2.a(r3, r4)
            com.crashlytics.android.internal.v r2 = com.crashlytics.android.internal.C0188v.a()
            com.crashlytics.android.internal.q r2 = r2.b()
            java.lang.String r3 = "Crashlytics"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Collecting session parts for ID "
            r4.<init>(r5)
            java.lang.StringBuilder r4 = r4.append(r13)
            java.lang.String r4 = r4.toString()
            r2.a(r3, r4)
            com.crashlytics.android.R r2 = new com.crashlytics.android.R
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.StringBuilder r3 = r3.append(r13)
            java.lang.String r4 = "SessionCrash"
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            r2.<init>(r3)
            r0 = r20
            java.io.File[] r14 = r0.a((java.io.FilenameFilter) r2)
            if (r14 == 0) goto L_0x030b
            int r2 = r14.length
            if (r2 <= 0) goto L_0x030b
            r2 = 1
            r3 = r2
        L_0x014c:
            com.crashlytics.android.internal.v r2 = com.crashlytics.android.internal.C0188v.a()
            com.crashlytics.android.internal.q r2 = r2.b()
            java.lang.String r4 = "Crashlytics"
            java.util.Locale r5 = java.util.Locale.US
            java.lang.String r6 = "Session %s has fatal exception: %s"
            r8 = 2
            java.lang.Object[] r8 = new java.lang.Object[r8]
            r15 = 0
            r8[r15] = r13
            r15 = 1
            java.lang.Boolean r16 = java.lang.Boolean.valueOf(r3)
            r8[r15] = r16
            java.lang.String r5 = java.lang.String.format(r5, r6, r8)
            r2.a(r4, r5)
            com.crashlytics.android.R r2 = new com.crashlytics.android.R
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.StringBuilder r4 = r4.append(r13)
            java.lang.String r5 = "SessionEvent"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            r2.<init>(r4)
            r0 = r20
            java.io.File[] r8 = r0.a((java.io.FilenameFilter) r2)
            if (r8 == 0) goto L_0x030f
            int r2 = r8.length
            if (r2 <= 0) goto L_0x030f
            r2 = 1
        L_0x0192:
            com.crashlytics.android.internal.v r4 = com.crashlytics.android.internal.C0188v.a()
            com.crashlytics.android.internal.q r4 = r4.b()
            java.lang.String r5 = "Crashlytics"
            java.util.Locale r6 = java.util.Locale.US
            java.lang.String r15 = "Session %s has non-fatal exceptions: %s"
            r16 = 2
            r0 = r16
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r16 = r0
            r17 = 0
            r16[r17] = r13
            r17 = 1
            java.lang.Boolean r18 = java.lang.Boolean.valueOf(r2)
            r16[r17] = r18
            r0 = r16
            java.lang.String r6 = java.lang.String.format(r6, r15, r0)
            r4.a(r5, r6)
            if (r3 != 0) goto L_0x01c1
            if (r2 == 0) goto L_0x034d
        L_0x01c1:
            r6 = 0
            r4 = 0
            com.crashlytics.android.f r5 = new com.crashlytics.android.f     // Catch:{ Exception -> 0x0312, all -> 0x0340 }
            r0 = r20
            java.io.File r15 = r0.k     // Catch:{ Exception -> 0x0312, all -> 0x0340 }
            r5.<init>(r15, r13)     // Catch:{ Exception -> 0x0312, all -> 0x0340 }
            com.crashlytics.android.h r4 = com.crashlytics.android.C0139h.a((java.io.OutputStream) r5)     // Catch:{ Exception -> 0x0391, all -> 0x038b }
            com.crashlytics.android.internal.v r6 = com.crashlytics.android.internal.C0188v.a()     // Catch:{ Exception -> 0x0395, all -> 0x038b }
            com.crashlytics.android.internal.q r6 = r6.b()     // Catch:{ Exception -> 0x0395, all -> 0x038b }
            java.lang.String r15 = "Crashlytics"
            java.lang.StringBuilder r16 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0395, all -> 0x038b }
            java.lang.String r17 = "Collecting SessionStart data for session ID "
            r16.<init>(r17)     // Catch:{ Exception -> 0x0395, all -> 0x038b }
            r0 = r16
            java.lang.StringBuilder r16 = r0.append(r13)     // Catch:{ Exception -> 0x0395, all -> 0x038b }
            java.lang.String r16 = r16.toString()     // Catch:{ Exception -> 0x0395, all -> 0x038b }
            r0 = r16
            r6.a(r15, r0)     // Catch:{ Exception -> 0x0395, all -> 0x038b }
            a((com.crashlytics.android.C0139h) r4, (java.io.File) r12)     // Catch:{ Exception -> 0x0395, all -> 0x038b }
            r6 = 4
            java.util.Date r12 = new java.util.Date     // Catch:{ Exception -> 0x0395, all -> 0x038b }
            r12.<init>()     // Catch:{ Exception -> 0x0395, all -> 0x038b }
            long r16 = r12.getTime()     // Catch:{ Exception -> 0x0395, all -> 0x038b }
            r18 = 1000(0x3e8, double:4.94E-321)
            long r16 = r16 / r18
            r0 = r16
            r4.a((int) r6, (long) r0)     // Catch:{ Exception -> 0x0395, all -> 0x038b }
            r6 = 5
            r4.a((int) r6, (boolean) r3)     // Catch:{ Exception -> 0x0395, all -> 0x038b }
            r0 = r20
            r0.a((com.crashlytics.android.C0139h) r4, (java.lang.String) r13)     // Catch:{ Exception -> 0x0395, all -> 0x038b }
            if (r2 == 0) goto L_0x025c
            int r2 = r8.length     // Catch:{ Exception -> 0x0395, all -> 0x038b }
            if (r2 <= r9) goto L_0x03a1
            com.crashlytics.android.internal.v r2 = com.crashlytics.android.internal.C0188v.a()     // Catch:{ Exception -> 0x0395, all -> 0x038b }
            com.crashlytics.android.internal.q r2 = r2.b()     // Catch:{ Exception -> 0x0395, all -> 0x038b }
            java.lang.String r6 = "Crashlytics"
            java.util.Locale r8 = java.util.Locale.US     // Catch:{ Exception -> 0x0395, all -> 0x038b }
            java.lang.String r12 = "Trimming down to %d logged exceptions."
            r15 = 1
            java.lang.Object[] r15 = new java.lang.Object[r15]     // Catch:{ Exception -> 0x0395, all -> 0x038b }
            r16 = 0
            java.lang.Integer r17 = java.lang.Integer.valueOf(r9)     // Catch:{ Exception -> 0x0395, all -> 0x038b }
            r15[r16] = r17     // Catch:{ Exception -> 0x0395, all -> 0x038b }
            java.lang.String r8 = java.lang.String.format(r8, r12, r15)     // Catch:{ Exception -> 0x0395, all -> 0x038b }
            r2.a(r6, r8)     // Catch:{ Exception -> 0x0395, all -> 0x038b }
            r0 = r20
            r0.a((java.lang.String) r13, (int) r9)     // Catch:{ Exception -> 0x0395, all -> 0x038b }
            com.crashlytics.android.R r2 = new com.crashlytics.android.R     // Catch:{ Exception -> 0x0395, all -> 0x038b }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0395, all -> 0x038b }
            r6.<init>()     // Catch:{ Exception -> 0x0395, all -> 0x038b }
            java.lang.StringBuilder r6 = r6.append(r13)     // Catch:{ Exception -> 0x0395, all -> 0x038b }
            java.lang.String r8 = "SessionEvent"
            java.lang.StringBuilder r6 = r6.append(r8)     // Catch:{ Exception -> 0x0395, all -> 0x038b }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x0395, all -> 0x038b }
            r2.<init>(r6)     // Catch:{ Exception -> 0x0395, all -> 0x038b }
            r0 = r20
            java.io.File[] r2 = r0.a((java.io.FilenameFilter) r2)     // Catch:{ Exception -> 0x0395, all -> 0x038b }
        L_0x0257:
            r0 = r20
            r0.a((com.crashlytics.android.C0139h) r4, (java.io.File[]) r2, (java.lang.String) r13)     // Catch:{ Exception -> 0x0395, all -> 0x038b }
        L_0x025c:
            if (r3 == 0) goto L_0x0264
            r2 = 0
            r2 = r14[r2]     // Catch:{ Exception -> 0x0395, all -> 0x038b }
            a((com.crashlytics.android.C0139h) r4, (java.io.File) r2)     // Catch:{ Exception -> 0x0395, all -> 0x038b }
        L_0x0264:
            r2 = 11
            r3 = 1
            r4.a((int) r2, (int) r3)     // Catch:{ Exception -> 0x0395, all -> 0x038b }
            r2 = 12
            r3 = 3
            r4.b((int) r2, (int) r3)     // Catch:{ Exception -> 0x0395, all -> 0x038b }
            java.lang.String r2 = "Error flushing session file stream"
            com.crashlytics.android.internal.C0143ab.a((java.io.Flushable) r4, (java.lang.String) r2)
            java.lang.String r2 = "Failed to close CLS file"
            com.crashlytics.android.internal.C0143ab.a((java.io.Closeable) r5, (java.lang.String) r2)
        L_0x027a:
            com.crashlytics.android.internal.v r2 = com.crashlytics.android.internal.C0188v.a()
            com.crashlytics.android.internal.q r2 = r2.b()
            java.lang.String r3 = "Crashlytics"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Removing session part files for ID "
            r4.<init>(r5)
            java.lang.StringBuilder r4 = r4.append(r13)
            java.lang.String r4 = r4.toString()
            r2.a(r3, r4)
            r0 = r20
            r0.a((java.lang.String) r13)
            int r2 = r7 + 1
            r7 = r2
            goto L_0x00e7
        L_0x02a0:
            if (r2 != 0) goto L_0x02a4
            java.lang.String r2 = ""
        L_0x02a4:
            com.crashlytics.android.d r8 = com.crashlytics.android.C0135d.a(r2)     // Catch:{ Exception -> 0x039e }
            if (r5 != 0) goto L_0x02eb
            r2 = 0
            r6 = r2
        L_0x02ac:
            if (r7 != 0) goto L_0x02f1
            r2 = 0
            r5 = r2
        L_0x02b0:
            r2 = 1
            int r2 = com.crashlytics.android.C0139h.b((int) r2, (com.crashlytics.android.C0135d) r8)     // Catch:{ Exception -> 0x039e }
            int r2 = r2 + 0
            if (r6 == 0) goto L_0x02bf
            r7 = 2
            int r7 = com.crashlytics.android.C0139h.b((int) r7, (com.crashlytics.android.C0135d) r6)     // Catch:{ Exception -> 0x039e }
            int r2 = r2 + r7
        L_0x02bf:
            if (r5 == 0) goto L_0x02c7
            r7 = 3
            int r7 = com.crashlytics.android.C0139h.b((int) r7, (com.crashlytics.android.C0135d) r5)     // Catch:{ Exception -> 0x039e }
            int r2 = r2 + r7
        L_0x02c7:
            r7 = 6
            r9 = 2
            r3.g(r7, r9)     // Catch:{ Exception -> 0x039e }
            r3.b(r2)     // Catch:{ Exception -> 0x039e }
            r2 = 1
            r3.a((int) r2, (com.crashlytics.android.C0135d) r8)     // Catch:{ Exception -> 0x039e }
            if (r6 == 0) goto L_0x02d9
            r2 = 2
            r3.a((int) r2, (com.crashlytics.android.C0135d) r6)     // Catch:{ Exception -> 0x039e }
        L_0x02d9:
            if (r5 == 0) goto L_0x02df
            r2 = 3
            r3.a((int) r2, (com.crashlytics.android.C0135d) r5)     // Catch:{ Exception -> 0x039e }
        L_0x02df:
            java.lang.String r2 = "Failed to flush session user file."
            com.crashlytics.android.internal.C0143ab.a((java.io.Flushable) r3, (java.lang.String) r2)
            java.lang.String r2 = "Failed to close session user file."
            com.crashlytics.android.internal.C0143ab.a((java.io.Closeable) r4, (java.lang.String) r2)
            goto L_0x00c0
        L_0x02eb:
            com.crashlytics.android.d r2 = com.crashlytics.android.C0135d.a(r5)     // Catch:{ Exception -> 0x039e }
            r6 = r2
            goto L_0x02ac
        L_0x02f1:
            com.crashlytics.android.d r2 = com.crashlytics.android.C0135d.a(r7)     // Catch:{ Exception -> 0x039e }
            r5 = r2
            goto L_0x02b0
        L_0x02f7:
            r2 = move-exception
            r4 = r5
        L_0x02f9:
            r0 = r20
            r0.a((java.lang.Throwable) r2, (java.io.OutputStream) r4)     // Catch:{ all -> 0x02ff }
            throw r2     // Catch:{ all -> 0x02ff }
        L_0x02ff:
            r2 = move-exception
        L_0x0300:
            java.lang.String r5 = "Failed to flush session user file."
            com.crashlytics.android.internal.C0143ab.a((java.io.Flushable) r3, (java.lang.String) r5)
            java.lang.String r3 = "Failed to close session user file."
            com.crashlytics.android.internal.C0143ab.a((java.io.Closeable) r4, (java.lang.String) r3)
            throw r2
        L_0x030b:
            r2 = 0
            r3 = r2
            goto L_0x014c
        L_0x030f:
            r2 = 0
            goto L_0x0192
        L_0x0312:
            r2 = move-exception
            r3 = r4
            r4 = r6
        L_0x0315:
            com.crashlytics.android.internal.v r5 = com.crashlytics.android.internal.C0188v.a()     // Catch:{ all -> 0x038d }
            com.crashlytics.android.internal.q r5 = r5.b()     // Catch:{ all -> 0x038d }
            java.lang.String r6 = "Crashlytics"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x038d }
            java.lang.String r12 = "Failed to write session file for session ID: "
            r8.<init>(r12)     // Catch:{ all -> 0x038d }
            java.lang.StringBuilder r8 = r8.append(r13)     // Catch:{ all -> 0x038d }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x038d }
            r5.a((java.lang.String) r6, (java.lang.String) r8, (java.lang.Throwable) r2)     // Catch:{ all -> 0x038d }
            r0 = r20
            r0.a((java.lang.Throwable) r2, (java.io.OutputStream) r4)     // Catch:{ all -> 0x038d }
            java.lang.String r2 = "Error flushing session file stream"
            com.crashlytics.android.internal.C0143ab.a((java.io.Flushable) r3, (java.lang.String) r2)
            a((com.crashlytics.android.C0137f) r4)
            goto L_0x027a
        L_0x0340:
            r2 = move-exception
            r5 = r6
        L_0x0342:
            java.lang.String r3 = "Error flushing session file stream"
            com.crashlytics.android.internal.C0143ab.a((java.io.Flushable) r4, (java.lang.String) r3)
            java.lang.String r3 = "Failed to close CLS file"
            com.crashlytics.android.internal.C0143ab.a((java.io.Closeable) r5, (java.lang.String) r3)
            throw r2
        L_0x034d:
            com.crashlytics.android.internal.v r2 = com.crashlytics.android.internal.C0188v.a()
            com.crashlytics.android.internal.q r2 = r2.b()
            java.lang.String r3 = "Crashlytics"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "No events present for session ID "
            r4.<init>(r5)
            java.lang.StringBuilder r4 = r4.append(r13)
            java.lang.String r4 = r4.toString()
            r2.a(r3, r4)
            goto L_0x027a
        L_0x036b:
            com.crashlytics.android.internal.v r2 = com.crashlytics.android.internal.C0188v.a()
            com.crashlytics.android.internal.q r2 = r2.b()
            java.lang.String r3 = "Crashlytics"
            java.lang.String r4 = "No session begin files found."
            r2.a(r3, r4)
        L_0x037a:
            return
        L_0x037b:
            com.crashlytics.android.internal.v r2 = com.crashlytics.android.internal.C0188v.a()
            com.crashlytics.android.internal.q r2 = r2.b()
            java.lang.String r3 = "Crashlytics"
            java.lang.String r4 = "Unable to close session. Settings are not loaded."
            r2.a(r3, r4)
            goto L_0x037a
        L_0x038b:
            r2 = move-exception
            goto L_0x0342
        L_0x038d:
            r2 = move-exception
            r5 = r4
            r4 = r3
            goto L_0x0342
        L_0x0391:
            r2 = move-exception
            r3 = r4
            r4 = r5
            goto L_0x0315
        L_0x0395:
            r2 = move-exception
            r3 = r4
            r4 = r5
            goto L_0x0315
        L_0x039a:
            r2 = move-exception
            r4 = r5
            goto L_0x0300
        L_0x039e:
            r2 = move-exception
            goto L_0x02f9
        L_0x03a1:
            r2 = r8
            goto L_0x0257
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crashlytics.android.C0205v.m():void");
    }

    private String n() {
        File[] a2 = a((FilenameFilter) new R("BeginSession"));
        Arrays.sort(a2, b);
        if (a2.length > 0) {
            return a(a2[0]);
        }
        return null;
    }

    private static String a(File file) {
        return file.getName().substring(0, 35);
    }

    private static void a(C0137f fVar) {
        if (fVar != null) {
            try {
                fVar.a();
            } catch (IOException e2) {
                C0188v.a().b().a(Crashlytics.TAG, "Error closing session file stream in the presence of an exception", (Throwable) e2);
            }
        }
    }

    private void a(C0139h hVar, File[] fileArr, String str) {
        Arrays.sort(fileArr, C0143ab.a);
        for (File file : fileArr) {
            try {
                C0188v.a().b().a(Crashlytics.TAG, String.format(Locale.US, "Found Non Fatal for session ID %s in %s ", new Object[]{str, file.getName()}));
                a(hVar, file);
            } catch (Exception e2) {
                C0188v.a().b().a(Crashlytics.TAG, "Error writting non-fatal to session.", (Throwable) e2);
            }
        }
    }

    private void a(C0139h hVar, String str) throws IOException {
        for (String str2 : new String[]{"SessionUser", "SessionApp", "SessionOS", "SessionDevice"}) {
            File[] a2 = a((FilenameFilter) new R(str + str2));
            if (a2.length == 0) {
                C0188v.a().b().a(Crashlytics.TAG, "Can't find " + str2 + " data for session ID " + str, (Throwable) null);
            } else {
                C0188v.a().b().a(Crashlytics.TAG, "Collecting " + str2 + " data for session ID " + str);
                a(hVar, a2[0]);
            }
        }
    }

    private void a(String str) {
        for (File delete : a((FilenameFilter) new S(str))) {
            delete.delete();
        }
    }

    private File[] o() {
        return a((FilenameFilter) new R("BeginSession"));
    }

    /* access modifiers changed from: private */
    public File[] a(FilenameFilter filenameFilter) {
        File[] listFiles = this.k.listFiles(filenameFilter);
        return listFiles == null ? new File[0] : listFiles;
    }

    private void p() {
        for (File d2 : a(a)) {
            C0188v.a().b().a(Crashlytics.TAG, "Attempting to send crash report at time of crash...");
            new Thread(new D(this, d2), "Crashlytics Report Uploader").start();
        }
    }

    private void a(Throwable th, OutputStream outputStream) {
        PrintWriter printWriter;
        if (outputStream != null) {
            try {
                printWriter = new PrintWriter(outputStream);
                try {
                    a(th, (Writer) printWriter);
                    C0143ab.a((Closeable) printWriter, "Failed to close stack trace writer.");
                } catch (Exception e2) {
                    e = e2;
                    try {
                        C0188v.a().b().a(Crashlytics.TAG, "Failed to create PrintWriter", (Throwable) e);
                        C0143ab.a((Closeable) printWriter, "Failed to close stack trace writer.");
                    } catch (Throwable th2) {
                        th = th2;
                        C0143ab.a((Closeable) printWriter, "Failed to close stack trace writer.");
                        throw th;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                printWriter = null;
                C0188v.a().b().a(Crashlytics.TAG, "Failed to create PrintWriter", (Throwable) e);
                C0143ab.a((Closeable) printWriter, "Failed to close stack trace writer.");
            } catch (Throwable th3) {
                th = th3;
                printWriter = null;
                C0143ab.a((Closeable) printWriter, "Failed to close stack trace writer.");
                throw th;
            }
        }
    }

    private static void a(Throwable th, Writer writer) {
        boolean z = true;
        while (th != null) {
            try {
                String localizedMessage = th.getLocalizedMessage();
                String replaceAll = localizedMessage == null ? null : localizedMessage.replaceAll("(\r\n|\n|\f)", " ");
                writer.write((z ? "" : "Caused by: ") + th.getClass().getName() + ": " + (replaceAll != null ? replaceAll : "") + "\n");
                StackTraceElement[] stackTrace = th.getStackTrace();
                int length = stackTrace.length;
                for (int i2 = 0; i2 < length; i2++) {
                    writer.write("\tat " + stackTrace[i2].toString() + "\n");
                }
                th = th.getCause();
                z = false;
            } catch (Exception e2) {
                C0188v.a().b().a(Crashlytics.TAG, "Could not write stack trace", (Throwable) e2);
                return;
            }
        }
    }

    private static int q() {
        return C0139h.b(1, C0135d.a(C0184r.a(Crashlytics.getInstance().getContext(), C0188v.a().f()))) + 0;
    }

    private static C0135d b(String str) {
        if (str == null) {
            return null;
        }
        return C0135d.a(str);
    }

    private void c(String str) throws Exception {
        C0139h hVar;
        C0137f fVar;
        C0137f fVar2 = null;
        C0139h hVar2 = null;
        try {
            fVar = new C0137f(C0188v.a().h(), str + "SessionDevice");
            try {
                hVar = C0139h.a((OutputStream) fVar);
            } catch (Exception e2) {
                e = e2;
                fVar2 = fVar;
            } catch (Throwable th) {
                th = th;
                hVar = null;
                C0143ab.a((Flushable) hVar, "Failed to flush session device info.");
                C0143ab.a((Closeable) fVar, "Failed to close session device file.");
                throw th;
            }
            try {
                StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
                int b2 = C0143ab.b();
                C0135d b3 = b(Build.MODEL);
                C0135d b4 = b(Build.MANUFACTURER);
                C0135d b5 = b(Build.PRODUCT);
                int availableProcessors = Runtime.getRuntime().availableProcessors();
                long c2 = C0143ab.c();
                long blockCount = ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
                boolean d2 = C0143ab.d();
                C0156ao b6 = Crashlytics.getInstance().b();
                C0135d a2 = C0135d.a(b6.e());
                Map<C0157ap, String> f2 = b6.f();
                int f3 = C0143ab.f();
                hVar.g(9, 2);
                hVar.b(a(b2, a2, b3, availableProcessors, c2, blockCount, d2, f2, f3, b4, b5));
                hVar.a(1, a2);
                hVar.b(3, b2);
                hVar.a(4, b3);
                hVar.a(5, availableProcessors);
                hVar.a(6, c2);
                hVar.a(7, blockCount);
                hVar.a(10, d2);
                for (Map.Entry next : f2.entrySet()) {
                    hVar.g(11, 2);
                    hVar.b(a((C0157ap) next.getKey(), (String) next.getValue()));
                    hVar.b(1, ((C0157ap) next.getKey()).f);
                    hVar.a(2, C0135d.a((String) next.getValue()));
                }
                hVar.a(12, f3);
                if (b4 != null) {
                    hVar.a(13, b4);
                }
                if (b5 != null) {
                    hVar.a(14, b5);
                }
                C0143ab.a((Flushable) hVar, "Failed to flush session device info.");
                C0143ab.a((Closeable) fVar, "Failed to close session device file.");
            } catch (Exception e3) {
                e = e3;
                hVar2 = hVar;
                fVar2 = fVar;
                try {
                    a((Throwable) e, (OutputStream) fVar2);
                    throw e;
                } catch (Throwable th2) {
                    th = th2;
                    hVar = hVar2;
                    fVar = fVar2;
                    C0143ab.a((Flushable) hVar, "Failed to flush session device info.");
                    C0143ab.a((Closeable) fVar, "Failed to close session device file.");
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                C0143ab.a((Flushable) hVar, "Failed to flush session device info.");
                C0143ab.a((Closeable) fVar, "Failed to close session device file.");
                throw th;
            }
        } catch (Exception e4) {
            e = e4;
            a((Throwable) e, (OutputStream) fVar2);
            throw e;
        } catch (Throwable th4) {
            th = th4;
            hVar = null;
            fVar = null;
            C0143ab.a((Flushable) hVar, "Failed to flush session device info.");
            C0143ab.a((Closeable) fVar, "Failed to close session device file.");
            throw th;
        }
    }

    private static int a(C0157ap apVar, String str) {
        return C0139h.e(1, apVar.f) + C0139h.b(2, C0135d.a(str));
    }

    private int a(int i2, C0135d dVar, C0135d dVar2, int i3, long j2, long j3, boolean z, Map<C0157ap, String> map, int i4, C0135d dVar3, C0135d dVar4) {
        int b2;
        int i5;
        int e2 = C0139h.e(3, i2) + C0139h.b(1, dVar) + 0;
        if (dVar2 == null) {
            b2 = 0;
        } else {
            b2 = C0139h.b(4, dVar2);
        }
        int d2 = b2 + e2 + C0139h.d(5, i3) + C0139h.b(6, j2) + C0139h.b(7, j3) + C0139h.b(10, z);
        if (map != null) {
            Iterator<Map.Entry<C0157ap, String>> it = map.entrySet().iterator();
            while (true) {
                i5 = d2;
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry next = it.next();
                int a2 = a((C0157ap) next.getKey(), (String) next.getValue());
                d2 = a2 + C0139h.a(11) + C0139h.c(a2) + i5;
            }
        } else {
            i5 = d2;
        }
        return (dVar4 == null ? 0 : C0139h.b(14, dVar4)) + i5 + C0139h.d(12, i4) + (dVar3 == null ? 0 : C0139h.b(13, dVar3));
    }

    private static void a(C0139h hVar, File file) throws IOException {
        FileInputStream fileInputStream;
        int read;
        if (file.exists()) {
            byte[] bArr = new byte[((int) file.length())];
            try {
                fileInputStream = new FileInputStream(file);
                int i2 = 0;
                while (i2 < bArr.length && (read = fileInputStream.read(bArr, i2, bArr.length - i2)) >= 0) {
                    try {
                        i2 += read;
                    } catch (Throwable th) {
                        th = th;
                        C0143ab.a((Closeable) fileInputStream, "Failed to close file input stream.");
                        throw th;
                    }
                }
                C0143ab.a((Closeable) fileInputStream, "Failed to close file input stream.");
                hVar.a(bArr);
            } catch (Throwable th2) {
                th = th2;
                fileInputStream = null;
                C0143ab.a((Closeable) fileInputStream, "Failed to close file input stream.");
                throw th;
            }
        } else {
            C0188v.a().b().a(Crashlytics.TAG, "Tried to include a file that doesn't exist: " + file.getName(), (Throwable) null);
        }
    }

    private void a(String str, int i2) {
        C0133b.a(this.k, new R(str + "SessionEvent"), i2, c);
    }

    private void a(Date date, C0139h hVar, Thread thread, Throwable th, String str, boolean z) throws Exception {
        Map<String, String> treeMap;
        long time = date.getTime() / 1000;
        float b2 = C0143ab.b(Crashlytics.getInstance().getContext());
        int a2 = C0143ab.a(this.v);
        boolean c2 = C0143ab.c(Crashlytics.getInstance().getContext());
        int i2 = Crashlytics.getInstance().getContext().getResources().getConfiguration().orientation;
        long c3 = C0143ab.c() - C0143ab.a(Crashlytics.getInstance().getContext());
        long b3 = C0143ab.b(Environment.getDataDirectory().getPath());
        this.t = C0143ab.a(Crashlytics.d(), Crashlytics.getInstance().getContext());
        this.x = new LinkedList();
        this.y = th.getStackTrace();
        if (z) {
            Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
            this.w = new Thread[allStackTraces.size()];
            int i3 = 0;
            Iterator<Map.Entry<Thread, StackTraceElement[]>> it = allStackTraces.entrySet().iterator();
            while (true) {
                int i4 = i3;
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry next = it.next();
                this.w[i4] = (Thread) next.getKey();
                this.x.add(next.getValue());
                i3 = i4 + 1;
            }
        } else {
            this.w = new Thread[0];
        }
        C0135d a3 = a(this.u);
        if (a3 == null) {
            C0188v.a().b().a(Crashlytics.TAG, "No log data to include with this event.");
        }
        C0143ab.a((Closeable) this.u, "There was a problem closing the Crashlytics log file.");
        this.u = null;
        if (!C0143ab.a(Crashlytics.getInstance().getContext(), "com.crashlytics.CollectCustomKeys", true)) {
            treeMap = new TreeMap<>();
        } else {
            Map<String, String> a4 = Crashlytics.getInstance().a();
            treeMap = (a4 == null || a4.size() <= 1) ? a4 : new TreeMap<>(a4);
        }
        hVar.g(10, 2);
        int b4 = C0139h.b(1, time) + 0 + C0139h.b(2, C0135d.a(str));
        int a5 = a(thread, th, treeMap);
        int a6 = b4 + a5 + C0139h.a(3) + C0139h.c(a5);
        int a7 = a(b2, a2, c2, i2, c3, b3);
        int a8 = a6 + a7 + C0139h.a(5) + C0139h.c(a7);
        if (a3 != null) {
            int b5 = C0139h.b(1, a3);
            a8 += b5 + C0139h.a(6) + C0139h.c(b5);
        }
        hVar.b(a8);
        hVar.a(1, time);
        hVar.a(2, C0135d.a(str));
        hVar.g(3, 2);
        hVar.b(a(thread, th, treeMap));
        a(hVar, thread, th);
        if (treeMap != null && !treeMap.isEmpty()) {
            a(hVar, treeMap);
        }
        if (this.t != null) {
            hVar.a(3, this.t.importance != 100);
        }
        hVar.a(4, Crashlytics.getInstance().getContext().getResources().getConfiguration().orientation);
        hVar.g(5, 2);
        hVar.b(a(b2, a2, c2, i2, c3, b3));
        hVar.a(1, b2);
        hVar.c(2, a2);
        hVar.a(3, c2);
        hVar.a(4, i2);
        hVar.a(5, c3);
        hVar.a(6, b3);
        if (a3 != null) {
            hVar.g(6, 2);
            hVar.b(C0139h.b(1, a3));
            hVar.a(1, a3);
        }
    }

    private void a(C0139h hVar, Thread thread, Throwable th) throws Exception {
        hVar.g(1, 2);
        hVar.b(b(thread, th));
        a(hVar, thread, this.y, 4, true);
        int length = this.w.length;
        for (int i2 = 0; i2 < length; i2++) {
            a(hVar, this.w[i2], this.x.get(i2), 0, false);
        }
        a(hVar, th, 1, 2);
        hVar.g(3, 2);
        hVar.b(s());
        hVar.a(1, f);
        hVar.a(2, f);
        hVar.a(3, 0);
        hVar.g(4, 2);
        hVar.b(r());
        hVar.a(1, 0);
        hVar.a(2, 0);
        hVar.a(3, this.q);
        if (this.r != null) {
            hVar.a(4, this.r);
        }
    }

    private void a(C0139h hVar, Map<String, String> map) throws Exception {
        for (Map.Entry next : map.entrySet()) {
            hVar.g(2, 2);
            hVar.b(a((String) next.getKey(), (String) next.getValue()));
            hVar.a(1, C0135d.a((String) next.getKey()));
            String str = (String) next.getValue();
            if (str == null) {
                str = "";
            }
            hVar.a(2, C0135d.a(str));
        }
    }

    private int r() {
        int b2 = C0139h.b(1, 0) + 0 + C0139h.b(2, 0) + C0139h.b(3, this.q);
        if (this.r != null) {
            return b2 + C0139h.b(4, this.r);
        }
        return b2;
    }

    private void a(C0139h hVar, Throwable th, int i2, int i3) throws Exception {
        int i4 = 0;
        hVar.g(i3, 2);
        hVar.b(a(th, 1));
        hVar.a(1, C0135d.a(th.getClass().getName()));
        String localizedMessage = th.getLocalizedMessage();
        if (localizedMessage != null) {
            hVar.a(3, C0135d.a(localizedMessage));
        }
        for (StackTraceElement a2 : th.getStackTrace()) {
            a(hVar, 4, a2, true);
        }
        Throwable cause = th.getCause();
        if (cause == null) {
            return;
        }
        if (i2 < 8) {
            a(hVar, cause, i2 + 1, 6);
            return;
        }
        while (cause != null) {
            cause = cause.getCause();
            i4++;
        }
        hVar.a(7, i4);
    }

    private int a(Thread thread, StackTraceElement[] stackTraceElementArr, int i2, boolean z) {
        int d2 = C0139h.d(2, i2) + C0139h.b(1, C0135d.a(thread.getName()));
        for (StackTraceElement a2 : stackTraceElementArr) {
            int a3 = a(a2, z);
            d2 += a3 + C0139h.a(3) + C0139h.c(a3);
        }
        return d2;
    }

    private void a(C0139h hVar, Thread thread, StackTraceElement[] stackTraceElementArr, int i2, boolean z) throws Exception {
        hVar.g(1, 2);
        hVar.b(a(thread, stackTraceElementArr, i2, z));
        hVar.a(1, C0135d.a(thread.getName()));
        hVar.a(2, i2);
        for (StackTraceElement a2 : stackTraceElementArr) {
            a(hVar, 3, a2, z);
        }
    }

    private static int a(StackTraceElement stackTraceElement, boolean z) {
        int b2;
        int i2;
        if (stackTraceElement.isNativeMethod()) {
            b2 = C0139h.b(1, (long) Math.max(stackTraceElement.getLineNumber(), 0)) + 0;
        } else {
            b2 = C0139h.b(1, 0) + 0;
        }
        int b3 = b2 + C0139h.b(2, C0135d.a(stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName()));
        if (stackTraceElement.getFileName() != null) {
            b3 += C0139h.b(3, C0135d.a(stackTraceElement.getFileName()));
        }
        if (stackTraceElement.isNativeMethod() || stackTraceElement.getLineNumber() <= 0) {
            i2 = b3;
        } else {
            i2 = b3 + C0139h.b(4, (long) stackTraceElement.getLineNumber());
        }
        return C0139h.d(5, z ? 2 : 0) + i2;
    }

    private void a(C0139h hVar, int i2, StackTraceElement stackTraceElement, boolean z) throws Exception {
        int i3 = 4;
        hVar.g(i2, 2);
        hVar.b(a(stackTraceElement, z));
        if (stackTraceElement.isNativeMethod()) {
            hVar.a(1, (long) Math.max(stackTraceElement.getLineNumber(), 0));
        } else {
            hVar.a(1, 0);
        }
        hVar.a(2, C0135d.a(stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName()));
        if (stackTraceElement.getFileName() != null) {
            hVar.a(3, C0135d.a(stackTraceElement.getFileName()));
        }
        if (!stackTraceElement.isNativeMethod() && stackTraceElement.getLineNumber() > 0) {
            hVar.a(4, (long) stackTraceElement.getLineNumber());
        }
        if (!z) {
            i3 = 0;
        }
        hVar.a(5, i3);
    }

    private int a(Thread thread, Throwable th, Map<String, String> map) {
        int i2;
        int b2 = b(thread, th);
        int a2 = b2 + C0139h.a(1) + C0139h.c(b2) + 0;
        if (map != null) {
            Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
            while (true) {
                i2 = a2;
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry next = it.next();
                int a3 = a((String) next.getKey(), (String) next.getValue());
                a2 = a3 + C0139h.a(2) + C0139h.c(a3) + i2;
            }
        } else {
            i2 = a2;
        }
        if (this.t != null) {
            i2 += C0139h.b(3, this.t.importance != 100);
        }
        return C0139h.d(4, Crashlytics.getInstance().getContext().getResources().getConfiguration().orientation) + i2;
    }

    private int b(Thread thread, Throwable th) {
        int a2 = a(thread, this.y, 4, true);
        int length = this.w.length;
        int a3 = a2 + C0139h.a(1) + C0139h.c(a2) + 0;
        for (int i2 = 0; i2 < length; i2++) {
            int a4 = a(this.w[i2], this.x.get(i2), 0, false);
            a3 += a4 + C0139h.a(1) + C0139h.c(a4);
        }
        int a5 = a(th, 1);
        int s2 = s();
        int a6 = a5 + C0139h.a(2) + C0139h.c(a5) + a3 + s2 + C0139h.a(3) + C0139h.c(s2);
        int r2 = r();
        return a6 + r2 + C0139h.a(3) + C0139h.c(r2);
    }

    private static int a(String str, String str2) {
        int b2 = C0139h.b(1, C0135d.a(str));
        if (str2 == null) {
            str2 = "";
        }
        return b2 + C0139h.b(2, C0135d.a(str2));
    }

    private static int a(float f2, int i2, boolean z, int i3, long j2, long j3) {
        return C0139h.b(1, f2) + 0 + C0139h.f(2, i2) + C0139h.b(3, z) + C0139h.d(4, i3) + C0139h.b(5, j2) + C0139h.b(6, j3);
    }

    private int a(Throwable th, int i2) {
        int i3 = 0;
        int b2 = C0139h.b(1, C0135d.a(th.getClass().getName())) + 0;
        String localizedMessage = th.getLocalizedMessage();
        if (localizedMessage != null) {
            b2 += C0139h.b(3, C0135d.a(localizedMessage));
        }
        StackTraceElement[] stackTrace = th.getStackTrace();
        int length = stackTrace.length;
        int i4 = 0;
        while (i4 < length) {
            int a2 = a(stackTrace[i4], true);
            i4++;
            b2 = a2 + C0139h.a(4) + C0139h.c(a2) + b2;
        }
        Throwable cause = th.getCause();
        if (cause == null) {
            return b2;
        }
        if (i2 < 8) {
            int a3 = a(cause, i2 + 1);
            return b2 + a3 + C0139h.a(6) + C0139h.c(a3);
        }
        while (cause != null) {
            cause = cause.getCause();
            i3++;
        }
        return b2 + C0139h.d(7, i3);
    }

    private static int s() {
        return C0139h.b(1, f) + 0 + C0139h.b(2, f) + C0139h.b(3, 0);
    }

    /* access modifiers changed from: package-private */
    public final void h() {
        a((Runnable) new E(this));
    }

    /* access modifiers changed from: package-private */
    public final void a(File[] fileArr) {
        File file = new File(C0188v.a().h(), "invalidClsFiles");
        if (file.exists()) {
            if (file.isDirectory()) {
                for (File delete : file.listFiles()) {
                    delete.delete();
                }
            }
            file.delete();
        }
        for (File file2 : fileArr) {
            C0188v.a().b().a(Crashlytics.TAG, "Found invalid session part file: " + file2);
            String a2 = a(file2);
            F f2 = new F(this, a2);
            C0188v.a().b().a(Crashlytics.TAG, "Deleting all part files for invalid session: " + a2);
            for (File file3 : a((FilenameFilter) f2)) {
                C0188v.a().b().a(Crashlytics.TAG, "Deleting session file: " + file3);
                file3.delete();
            }
        }
    }

    private <T> T a(Callable<T> callable) {
        try {
            return this.s.submit(callable).get();
        } catch (RejectedExecutionException e2) {
            C0188v.a().b().a(Crashlytics.TAG, "Executor is shut down because we're handling a fatal crash.");
            return null;
        } catch (Exception e3) {
            C0188v.a().b().a(Crashlytics.TAG, "Failed to execute task.", (Throwable) e3);
            return null;
        }
    }

    private Future<?> a(Runnable runnable) {
        try {
            return this.s.submit(new G(this, runnable));
        } catch (RejectedExecutionException e2) {
            C0188v.a().b().a(Crashlytics.TAG, "Executor is shut down because we're handling a fatal crash.");
            return null;
        }
    }

    private <T> Future<T> b(Callable<T> callable) {
        try {
            return this.s.submit(new I(this, callable));
        } catch (RejectedExecutionException e2) {
            C0188v.a().b().a(Crashlytics.TAG, "Executor is shut down because we're handling a fatal crash.");
            return null;
        }
    }
}
