package com.crashlytics.android.internal;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Debug;
import android.os.StatFs;
import android.provider.Settings;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.text.TextUtils;
import com.crashlytics.android.Crashlytics;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.chromium.ui.base.PageTransition;

/* renamed from: com.crashlytics.android.internal.ab  reason: case insensitive filesystem */
public final class C0143ab {
    public static final Comparator<File> a = new C0144ac();
    private static Boolean b = null;
    private static final char[] c = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static long d = -1;
    private static Boolean e = null;

    public static SharedPreferences a() {
        return C0188v.a().getContext().getSharedPreferences("com.crashlytics.prefs", 0);
    }

    private static String a(File file, String str) {
        BufferedReader bufferedReader;
        Throwable th;
        String str2 = null;
        if (file.exists()) {
            try {
                bufferedReader = new BufferedReader(new FileReader(file), 1024);
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        String[] split = Pattern.compile("\\s*:\\s*").split(readLine, 2);
                        if (split.length > 1 && split[0].equals(str)) {
                            str2 = split[1];
                            break;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            C0188v.a().b().a(Crashlytics.TAG, "Error parsing " + file, (Throwable) e);
                            a((Closeable) bufferedReader, "Failed to close system file reader.");
                            return str2;
                        } catch (Throwable th2) {
                            th = th2;
                            a((Closeable) bufferedReader, "Failed to close system file reader.");
                            throw th;
                        }
                    }
                }
                a((Closeable) bufferedReader, "Failed to close system file reader.");
            } catch (Exception e3) {
                e = e3;
                bufferedReader = null;
                C0188v.a().b().a(Crashlytics.TAG, "Error parsing " + file, (Throwable) e);
                a((Closeable) bufferedReader, "Failed to close system file reader.");
                return str2;
            } catch (Throwable th3) {
                bufferedReader = null;
                th = th3;
                a((Closeable) bufferedReader, "Failed to close system file reader.");
                throw th;
            }
        }
        return str2;
    }

    public static int b() {
        return C0145ad.a().ordinal();
    }

    public static synchronized long c() {
        long j;
        long j2;
        synchronized (C0143ab.class) {
            if (d == -1) {
                String a2 = a(new File("/proc/meminfo"), "MemTotal");
                if (!TextUtils.isEmpty(a2)) {
                    String upperCase = a2.toUpperCase(Locale.US);
                    try {
                        if (upperCase.endsWith("KB")) {
                            j2 = a(upperCase, "KB", 1024);
                        } else if (upperCase.endsWith("MB")) {
                            j2 = a(upperCase, "MB", (int) AccessibilityEventCompat.TYPE_TOUCH_INTERACTION_START);
                        } else if (upperCase.endsWith("GB")) {
                            j2 = a(upperCase, "GB", (int) PageTransition.CLIENT_REDIRECT);
                        } else {
                            C0188v.a().b().a(Crashlytics.TAG, "Unexpected meminfo format while computing RAM: " + upperCase);
                            j2 = 0;
                        }
                    } catch (NumberFormatException e2) {
                        C0188v.a().b().a(Crashlytics.TAG, "Unexpected meminfo format while computing RAM: " + upperCase, (Throwable) e2);
                    }
                    d = j2;
                }
                j2 = 0;
                d = j2;
            }
            j = d;
        }
        return j;
    }

    private static long a(String str, String str2, int i) {
        return Long.parseLong(str.split(str2)[0].trim()) * ((long) i);
    }

    public static ActivityManager.RunningAppProcessInfo a(String str, Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
                if (next.processName.equals(str)) {
                    return next;
                }
            }
        }
        return null;
    }

    public static String a(InputStream inputStream) throws IOException {
        Scanner useDelimiter = new Scanner(inputStream).useDelimiter("\\A");
        return useDelimiter.hasNext() ? useDelimiter.next() : "";
    }

    public static String a(String str) {
        return a(str.getBytes(), "SHA-1");
    }

    private static String b(InputStream inputStream) {
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    return a(instance.digest());
                }
                instance.update(bArr, 0, read);
            }
        } catch (Exception e2) {
            C0188v.a().b().a(Crashlytics.TAG, "Could not calculate hash for app icon.", (Throwable) e2);
            return "";
        }
    }

    private static String a(byte[] bArr, String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            instance.update(bArr);
            return a(instance.digest());
        } catch (NoSuchAlgorithmException e2) {
            C0188v.a().b().a(Crashlytics.TAG, "Could not create hashing algorithm: " + str + ", returning empty string.", (Throwable) e2);
            return "";
        }
    }

    public static String a(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        for (String str : strArr) {
            if (str != null) {
                arrayList.add(str.replace("-", "").toLowerCase(Locale.US));
            }
        }
        Collections.sort(arrayList);
        StringBuilder sb = new StringBuilder();
        for (String append : arrayList) {
            sb.append(append);
        }
        String sb2 = sb.toString();
        if (sb2.length() > 0) {
            return a(sb2);
        }
        return null;
    }

    public static long a(Context context) {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

    public static long b(String str) {
        StatFs statFs = new StatFs(str);
        long blockSize = (long) statFs.getBlockSize();
        return (((long) statFs.getBlockCount()) * blockSize) - (((long) statFs.getAvailableBlocks()) * blockSize);
    }

    public static float b(Context context) {
        Intent registerReceiver = context.registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        return ((float) registerReceiver.getIntExtra("level", -1)) / ((float) registerReceiver.getIntExtra("scale", -1));
    }

    public static boolean c(Context context) {
        if (d()) {
            return false;
        }
        return ((SensorManager) context.getSystemService("sensor")).getDefaultSensor(8) != null;
    }

    public static void c(String str) {
        if (e(C0188v.a().getContext())) {
            C0188v.a().b().a(Crashlytics.TAG, str);
        }
    }

    public static void d(String str) {
        if (e(C0188v.a().getContext())) {
            C0188v.a().b().d(Crashlytics.TAG, str);
        }
    }

    public static void a(int i, String str) {
        if (e(C0188v.a().getContext())) {
            C0188v.a().b().a(4, Crashlytics.TAG, str);
        }
    }

    public static boolean d(Context context) {
        boolean z = false;
        if (e == null) {
            if (!a(context, "com.crashlytics.SilenceCrashlyticsLogCat", false)) {
                z = true;
            }
            e = Boolean.valueOf(z);
        }
        return e.booleanValue();
    }

    public static boolean e(Context context) {
        if (b == null) {
            b = Boolean.valueOf(a(context, "com.crashlytics.Trace", false));
        }
        return b.booleanValue();
    }

    public static boolean a(Context context, String str, boolean z) {
        Resources resources;
        if (context == null || (resources = context.getResources()) == null) {
            return z;
        }
        int a2 = a(context, str, "bool");
        if (a2 > 0) {
            return resources.getBoolean(a2);
        }
        int a3 = a(context, str, "string");
        if (a3 > 0) {
            return Boolean.parseBoolean(context.getString(a3));
        }
        return z;
    }

    public static int a(Context context, String str, String str2) {
        Resources resources = context.getResources();
        int i = context.getApplicationContext().getApplicationInfo().icon;
        return resources.getIdentifier(str, str2, i > 0 ? context.getResources().getResourcePackageName(i) : context.getPackageName());
    }

    public static boolean d() {
        return "sdk".equals(Build.PRODUCT) || "google_sdk".equals(Build.PRODUCT) || Settings.Secure.getString(C0188v.a().getContext().getContentResolver(), "android_id") == null;
    }

    public static boolean e() {
        boolean d2 = d();
        String str = Build.TAGS;
        if ((!d2 && str != null && str.contains("test-keys")) || new File("/system/app/Superuser.apk").exists()) {
            return true;
        }
        File file = new File("/system/xbin/su");
        if (d2 || !file.exists()) {
            return false;
        }
        return true;
    }

    public static int f() {
        int i;
        boolean z = false;
        if (d()) {
            i = 1;
        } else {
            i = 0;
        }
        if (e()) {
            i |= 2;
        }
        if (Debug.isDebuggerConnected() || Debug.waitingForDebugger()) {
            z = true;
        }
        if (z) {
            return i | 4;
        }
        return i;
    }

    public static int a(boolean z) {
        float b2 = b(C0188v.a().getContext());
        if (!z) {
            return 1;
        }
        if (z && ((double) b2) >= 99.0d) {
            return 3;
        }
        if (!z || ((double) b2) >= 99.0d) {
            return 0;
        }
        return 2;
    }

    public static Cipher b(int i, String str) throws InvalidKeyException {
        if (str.length() < 32) {
            throw new InvalidKeyException("Key must be at least 32 bytes.");
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes(), 0, 32, "AES/ECB/PKCS7Padding");
        try {
            Cipher instance = Cipher.getInstance("AES/ECB/PKCS7Padding");
            instance.init(1, secretKeySpec);
            return instance;
        } catch (GeneralSecurityException e2) {
            C0188v.a().b().a(Crashlytics.TAG, "Could not create Cipher for AES/ECB/PKCS7Padding - should never happen.", (Throwable) e2);
            throw new RuntimeException(e2);
        }
    }

    public static String a(byte[] bArr) {
        char[] cArr = new char[(bArr.length << 1)];
        for (int i = 0; i < bArr.length; i++) {
            byte b2 = bArr[i] & 255;
            cArr[i << 1] = c[b2 >>> 4];
            cArr[(i << 1) + 1] = c[b2 & 15];
        }
        return new String(cArr);
    }

    public static boolean f(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    public static String a(Context context, String str) {
        int a2 = a(context, str, "string");
        if (a2 > 0) {
            return context.getString(a2);
        }
        return "";
    }

    public static void a(Closeable closeable, String str) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e2) {
                C0188v.a().b().a(Crashlytics.TAG, str, (Throwable) e2);
            }
        }
    }

    public static void a(Flushable flushable, String str) {
        if (flushable != null) {
            try {
                flushable.flush();
            } catch (IOException e2) {
                C0188v.a().b().a(Crashlytics.TAG, str, (Throwable) e2);
            }
        }
    }

    public static boolean e(String str) {
        return str == null || str.length() == 0;
    }

    public static String a(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("value must be zero or greater");
        }
        return String.format(Locale.US, "%1$10s", new Object[]{Integer.valueOf(i)}).replace(' ', '0');
    }

    public static void a(InputStream inputStream, OutputStream outputStream, byte[] bArr) throws IOException {
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    public static String b(int i) {
        switch (i) {
            case 2:
                return "V";
            case 3:
                return "D";
            case 4:
                return "I";
            case 5:
                return "W";
            case 6:
                return "E";
            case 7:
                return "A";
            default:
                return "?";
        }
    }

    public static String g(Context context) {
        InputStream inputStream;
        Throwable th;
        String str = null;
        try {
            inputStream = context.getResources().openRawResource(h(context));
            try {
                String b2 = b(inputStream);
                if (!e(b2)) {
                    str = b2;
                }
                a((Closeable) inputStream, "Failed to close icon input stream.");
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            inputStream = null;
        } catch (Throwable th2) {
            inputStream = null;
            th = th2;
            a((Closeable) inputStream, "Failed to close icon input stream.");
            throw th;
        }
        return str;
        try {
            C0188v.a().b().a(Crashlytics.TAG, "Could not calculate hash for app icon.", (Throwable) e);
            a((Closeable) inputStream, "Failed to close icon input stream.");
            return str;
        } catch (Throwable th3) {
            th = th3;
            a((Closeable) inputStream, "Failed to close icon input stream.");
            throw th;
        }
    }

    public static int h(Context context) {
        return context.getApplicationContext().getApplicationInfo().icon;
    }

    public static String i(Context context) {
        int a2 = a(context, "com.crashlytics.android.build_id", "string");
        if (a2 == 0) {
            return null;
        }
        String string = context.getResources().getString(a2);
        C0188v.a().b().a(Crashlytics.TAG, "Build ID is: " + string);
        return string;
    }
}
