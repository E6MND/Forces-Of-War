package com.crashlytics.android;

import com.crashlytics.android.internal.C0188v;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class CrashlyticsMissingDependencyException extends RuntimeException {
    private static final long serialVersionUID = -1151536370019872859L;

    CrashlyticsMissingDependencyException(String apiKey, String packageName) {
        super(a(apiKey, packageName));
    }

    private static String a(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("\nThis app relies on Crashlytics. Configure your build environment here: \n");
            sb.append(String.format("https://crashlytics.com/register/%s/android/%s", new Object[]{URLEncoder.encode(str, "UTF-8"), URLEncoder.encode(str2, "UTF-8")}) + "\n");
        } catch (UnsupportedEncodingException e) {
            C0188v.a().b().a(Crashlytics.TAG, "Could not find UTF-8 encoding.", (Throwable) e);
        }
        return sb.toString();
    }
}
