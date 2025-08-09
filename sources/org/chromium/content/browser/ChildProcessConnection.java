package org.chromium.content.browser;

import android.os.Bundle;
import org.chromium.content.common.IChildProcessCallback;
import org.chromium.content.common.IChildProcessService;

public interface ChildProcessConnection {
    public static final String EXTRA_COMMAND_LINE = "com.google.android.apps.chrome.extra.command_line";
    public static final String EXTRA_CPU_COUNT = "com.google.android.apps.chrome.extra.cpu_count";
    public static final String EXTRA_CPU_FEATURES = "com.google.android.apps.chrome.extra.cpu_features";
    public static final String EXTRA_FILES_FD_SUFFIX = "_fd";
    public static final String EXTRA_FILES_ID_SUFFIX = "_id";
    public static final String EXTRA_FILES_PREFIX = "com.google.android.apps.chrome.extra.extraFile_";

    public interface ConnectionCallback {
        void onConnected(int i);
    }

    public interface DeathCallback {
        void onChildProcessDied(ChildProcessConnection childProcessConnection);
    }

    void addStrongBinding();

    void dropOomBindings();

    int getPid();

    IChildProcessService getService();

    int getServiceNumber();

    boolean isInSandbox();

    boolean isInitialBindingBound();

    boolean isOomProtectedOrWasWhenDied();

    boolean isStrongBindingBound();

    void removeInitialBinding();

    void removeStrongBinding();

    void setupConnection(String[] strArr, FileDescriptorInfo[] fileDescriptorInfoArr, IChildProcessCallback iChildProcessCallback, ConnectionCallback connectionCallback, Bundle bundle);

    void start(String[] strArr);

    void stop();
}
