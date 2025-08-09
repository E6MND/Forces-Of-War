package com.amazon.inapp.purchasing;

import com.amazon.android.framework.util.KiwiLogger;

class KiwiLogHandler implements LogHandler {
    private static KiwiLogger LOGGER = new KiwiLogger("In App Purchasing SDK - Production Mode");

    KiwiLogHandler() {
    }

    private static String buildLogMessage(String str, String str2) {
        return str + ": " + str2;
    }

    public void error(String str, String str2) {
        LOGGER.error(buildLogMessage(str, str2));
    }

    public boolean isErrorOn() {
        return KiwiLogger.ERROR_ON;
    }

    public boolean isTestOn() {
        return KiwiLogger.isTestEnabled();
    }

    public boolean isTraceOn() {
        return KiwiLogger.TRACE_ON;
    }

    public void test(String str, String str2) {
        LOGGER.test(buildLogMessage(str, str2));
    }

    public void trace(String str, String str2) {
        LOGGER.trace(buildLogMessage(str, str2));
    }
}
