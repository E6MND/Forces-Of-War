package com.crashlytics.android.internal;

import java.io.IOException;

public final class aD extends RuntimeException {
    private static final long serialVersionUID = -1170466989781746231L;

    public final /* bridge */ /* synthetic */ Throwable getCause() {
        return (IOException) super.getCause();
    }

    protected aD(IOException iOException) {
        super(iOException);
    }
}
