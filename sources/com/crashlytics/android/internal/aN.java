package com.crashlytics.android.internal;

import com.crashlytics.android.Crashlytics;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import org.json.JSONObject;

public class aN {
    aN() {
    }

    public JSONObject a() {
        FileInputStream fileInputStream;
        JSONObject jSONObject;
        FileInputStream fileInputStream2 = null;
        C0188v.a().b().a(Crashlytics.TAG, "Reading cached settings...");
        try {
            File file = new File(C0188v.a().h(), "com.crashlytics.settings.json");
            if (file.exists()) {
                fileInputStream = new FileInputStream(file);
                try {
                    jSONObject = new JSONObject(C0143ab.a((InputStream) fileInputStream));
                    fileInputStream2 = fileInputStream;
                } catch (Exception e) {
                    e = e;
                    try {
                        C0188v.a().b().a(Crashlytics.TAG, "Failed to fetch cached settings", (Throwable) e);
                        C0143ab.a((Closeable) fileInputStream, "Error while closing settings cache file.");
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream2 = fileInputStream;
                        C0143ab.a((Closeable) fileInputStream2, "Error while closing settings cache file.");
                        throw th;
                    }
                }
            } else {
                C0188v.a().b().a(Crashlytics.TAG, "No cached settings found.");
                jSONObject = null;
            }
            C0143ab.a((Closeable) fileInputStream2, "Error while closing settings cache file.");
            return jSONObject;
        } catch (Exception e2) {
            e = e2;
            fileInputStream = null;
            C0188v.a().b().a(Crashlytics.TAG, "Failed to fetch cached settings", (Throwable) e);
            C0143ab.a((Closeable) fileInputStream, "Error while closing settings cache file.");
            return null;
        } catch (Throwable th2) {
            th = th2;
            C0143ab.a((Closeable) fileInputStream2, "Error while closing settings cache file.");
            throw th;
        }
    }

    public void a(long j, JSONObject jSONObject) {
        FileWriter fileWriter;
        C0188v.a().b().a(Crashlytics.TAG, "Writing settings to cache file...");
        if (jSONObject != null) {
            FileWriter fileWriter2 = null;
            try {
                jSONObject.put("expires_at", j);
                fileWriter = new FileWriter(new File(C0188v.a().h(), "com.crashlytics.settings.json"));
                try {
                    fileWriter.write(jSONObject.toString());
                    fileWriter.flush();
                    C0143ab.a((Closeable) fileWriter, "Failed to close settings writer.");
                } catch (Exception e) {
                    e = e;
                    try {
                        C0188v.a().b().a(Crashlytics.TAG, "Failed to cache settings", (Throwable) e);
                        C0143ab.a((Closeable) fileWriter, "Failed to close settings writer.");
                    } catch (Throwable th) {
                        th = th;
                        fileWriter2 = fileWriter;
                        C0143ab.a((Closeable) fileWriter2, "Failed to close settings writer.");
                        throw th;
                    }
                }
            } catch (Exception e2) {
                e = e2;
                fileWriter = null;
                C0188v.a().b().a(Crashlytics.TAG, "Failed to cache settings", (Throwable) e);
                C0143ab.a((Closeable) fileWriter, "Failed to close settings writer.");
            } catch (Throwable th2) {
                th = th2;
                C0143ab.a((Closeable) fileWriter2, "Failed to close settings writer.");
                throw th;
            }
        }
    }
}
