package org.xwalk.core;

public abstract class SharedXWalkExceptionHandler {
    public abstract void onSharedLibraryNotFound();

    /* access modifiers changed from: package-private */
    public boolean handleException(Throwable e) {
        onSharedLibraryNotFound();
        return true;
    }
}
