package com.google.android.gms.analytics;

import android.util.Log;

class l implements Logger {
    private int tK = 1;

    l() {
    }

    private String L(String str) {
        return Thread.currentThread().toString() + ": " + str;
    }

    public void error(Exception exception) {
        if (this.tK <= 3) {
            Log.e("GAV4", (String) null, exception);
        }
    }

    public void error(String msg) {
        if (this.tK <= 3) {
            Log.e("GAV4", L(msg));
        }
    }

    public int getLogLevel() {
        return this.tK;
    }

    public void info(String msg) {
        if (this.tK <= 1) {
            Log.i("GAV4", L(msg));
        }
    }

    public void setLogLevel(int level) {
        this.tK = level;
    }

    public void verbose(String msg) {
        if (this.tK <= 0) {
            Log.v("GAV4", L(msg));
        }
    }

    public void warn(String msg) {
        if (this.tK <= 2) {
            Log.w("GAV4", L(msg));
        }
    }
}
