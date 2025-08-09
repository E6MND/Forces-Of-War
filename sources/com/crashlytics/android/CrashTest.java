package com.crashlytics.android;

import com.crashlytics.android.internal.C0188v;
import com.facebook.AppEventsConstants;

public class CrashTest {
    public void throwRuntimeException(String message) {
        throw new RuntimeException(message);
    }

    public void stackOverflow() {
        stackOverflow();
    }

    public void indexOutOfBounds() {
        C0188v.a().b().a(Crashlytics.TAG, "Out of bounds value: " + new int[2][10]);
    }

    public void crashAsyncTask(long delayMs) {
        new C0193j(this, delayMs).execute(new Void[]{null});
    }

    public void throwFiveChainedExceptions() {
        try {
            throw new RuntimeException(AppEventsConstants.EVENT_PARAM_VALUE_YES);
        } catch (Exception e) {
            throw new RuntimeException("2", e);
        } catch (Exception e2) {
            try {
                throw new RuntimeException("3", e2);
            } catch (Exception e3) {
                try {
                    throw new RuntimeException("4", e3);
                } catch (Exception e4) {
                    throw new RuntimeException("5", e4);
                }
            }
        }
    }
}
