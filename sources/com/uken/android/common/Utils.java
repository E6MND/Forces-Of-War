package com.uken.android.common;

import android.util.Log;
import com.crashlytics.android.Crashlytics;
import com.uken.android.util.UkenEventLog;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

public class Utils {
    private static String TAG = "Utils";

    public static String parseJson(InputStream is) {
        Scanner data = new Scanner(is);
        StringBuilder jsonStringBuilder = new StringBuilder();
        while (data.hasNext()) {
            jsonStringBuilder.append(data.nextLine());
        }
        return jsonStringBuilder.toString();
    }

    public static void logCrashlytics(Exception exception) {
        try {
            if (!Consts.DEBUG) {
                Crashlytics.logException(exception);
                return;
            }
            Log.d(TAG, "DEBUG build, Crashlytics not enabled.");
            exception.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void logUkenEvent(String tag, String message) {
        logUkenEvent(tag, message, (Exception) null);
    }

    public static void logUkenEvent(String tag, final String message, final Exception e) {
        if (e != null) {
            logCrashlytics(e);
        }
        UkenEventLog.log("debug", tag, new HashMap<String, String>() {
            {
                put("native_message", message.trim());
                if (e != null) {
                    put("exception_message", e.getMessage());
                    StringBuffer stack_string = new StringBuffer();
                    StackTraceElement[] stack = e.getStackTrace();
                    for (StackTraceElement stackTraceElement : stack) {
                        stack_string.append(stackTraceElement.toString());
                        stack_string.append("\n");
                    }
                    put("exception_stack", stack_string.toString());
                }
            }
        });
    }
}
