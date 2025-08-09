package com.google.android.gms.analytics;

import android.content.Context;
import com.google.android.gms.analytics.HitBuilders;
import java.lang.Thread;
import java.util.ArrayList;

public class ExceptionReporter implements Thread.UncaughtExceptionHandler {
    private final Context mContext;
    private final Thread.UncaughtExceptionHandler tL;
    private final Tracker tM;
    private ExceptionParser tN;

    public ExceptionReporter(Tracker tracker, Thread.UncaughtExceptionHandler originalHandler, Context context) {
        if (tracker == null) {
            throw new NullPointerException("tracker cannot be null");
        } else if (context == null) {
            throw new NullPointerException("context cannot be null");
        } else {
            this.tL = originalHandler;
            this.tM = tracker;
            this.tN = new StandardExceptionParser(context, new ArrayList());
            this.mContext = context.getApplicationContext();
            aa.C("ExceptionReporter created, original handler is " + (originalHandler == null ? "null" : originalHandler.getClass().getName()));
        }
    }

    /* access modifiers changed from: package-private */
    public Thread.UncaughtExceptionHandler cy() {
        return this.tL;
    }

    public ExceptionParser getExceptionParser() {
        return this.tN;
    }

    public void setExceptionParser(ExceptionParser exceptionParser) {
        this.tN = exceptionParser;
    }

    public void uncaughtException(Thread t, Throwable e) {
        String str = "UncaughtException";
        if (this.tN != null) {
            str = this.tN.getDescription(t != null ? t.getName() : null, e);
        }
        aa.C("Tracking Exception: " + str);
        this.tM.send(new HitBuilders.ExceptionBuilder().setDescription(str).setFatal(true).build());
        GoogleAnalytics.getInstance(this.mContext).dispatchLocalHits();
        if (this.tL != null) {
            aa.C("Passing exception to original handler.");
            this.tL.uncaughtException(t, e);
        }
    }
}
