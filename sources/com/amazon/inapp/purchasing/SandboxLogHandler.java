package com.amazon.inapp.purchasing;

import android.util.Log;

class SandboxLogHandler implements LogHandler {
    private static final boolean ERROR_ON = true;
    private static final boolean TEST_ON = true;
    private static final boolean TRACE_ON = true;

    SandboxLogHandler() {
    }

    private static String buildLogMessage(String str) {
        return "In App Purchasing SDK - Sandbox Mode: " + str;
    }

    public void error(String str, String str2) {
        Log.e(str, buildLogMessage(str2));
    }

    public boolean isErrorOn() {
        return true;
    }

    public boolean isTestOn() {
        return true;
    }

    public boolean isTraceOn() {
        return true;
    }

    public void test(String str, String str2) {
        Log.v(str, buildLogMessage(str2));
    }

    public void trace(String str, String str2) {
        Log.d(str, buildLogMessage(str2));
    }
}
