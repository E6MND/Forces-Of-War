package org.chromium.content.browser;

import android.util.Log;
import android.util.SparseArray;
import org.chromium.base.SysUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.VisibleForTesting;

class BindingManagerImpl implements BindingManager {
    static final /* synthetic */ boolean $assertionsDisabled = (!BindingManagerImpl.class.desiredAssertionStatus());
    private static final long DETACH_AS_ACTIVE_HIGH_END_DELAY_MILLIS = 1000;
    private static final String TAG = "BindingManager";
    private ManagedConnection mBoundForBackgroundPeriod;
    /* access modifiers changed from: private */
    public final boolean mIsLowMemoryDevice;
    private ManagedConnection mLastInForeground;
    private final Object mLastInForegroundLock = new Object();
    private final SparseArray<ManagedConnection> mManagedConnections = new SparseArray<>();
    /* access modifiers changed from: private */
    public final long mRemoveStrongBindingDelay;

    private class ManagedConnection {
        static final /* synthetic */ boolean $assertionsDisabled = (!BindingManagerImpl.class.desiredAssertionStatus());
        private boolean mBoundForBackgroundPeriod;
        private ChildProcessConnection mConnection;
        private boolean mInForeground;
        private boolean mWasOomProtected;

        private void removeInitialBinding() {
            if (this.mConnection != null && this.mConnection.isInitialBindingBound()) {
                this.mConnection.removeInitialBinding();
            }
        }

        private void addStrongBinding() {
            ChildProcessConnection connection = this.mConnection;
            if (connection != null) {
                connection.addStrongBinding();
            }
        }

        private void removeStrongBinding() {
            final ChildProcessConnection connection = this.mConnection;
            if (connection != null && connection.isStrongBindingBound()) {
                Runnable doUnbind = new Runnable() {
                    public void run() {
                        if (connection.isStrongBindingBound()) {
                            connection.removeStrongBinding();
                        }
                    }
                };
                if (BindingManagerImpl.this.mIsLowMemoryDevice) {
                    doUnbind.run();
                } else {
                    ThreadUtils.postOnUiThreadDelayed(doUnbind, BindingManagerImpl.this.mRemoveStrongBindingDelay);
                }
            }
        }

        /* access modifiers changed from: private */
        public void dropBindings() {
            if ($assertionsDisabled || BindingManagerImpl.this.mIsLowMemoryDevice) {
                ChildProcessConnection connection = this.mConnection;
                if (connection != null) {
                    connection.dropOomBindings();
                    return;
                }
                return;
            }
            throw new AssertionError();
        }

        ManagedConnection(ChildProcessConnection connection) {
            this.mConnection = connection;
        }

        /* access modifiers changed from: package-private */
        public void setInForeground(boolean nextInForeground) {
            if (!this.mInForeground && nextInForeground) {
                addStrongBinding();
            } else if (this.mInForeground && !nextInForeground) {
                removeStrongBinding();
            }
            this.mInForeground = nextInForeground;
        }

        /* access modifiers changed from: package-private */
        public void determinedVisibility() {
            removeInitialBinding();
        }

        /* access modifiers changed from: package-private */
        public void setBoundForBackgroundPeriod(boolean nextBound) {
            if (!this.mBoundForBackgroundPeriod && nextBound) {
                addStrongBinding();
            } else if (this.mBoundForBackgroundPeriod && !nextBound) {
                removeStrongBinding();
            }
            this.mBoundForBackgroundPeriod = nextBound;
        }

        /* access modifiers changed from: package-private */
        public boolean isOomProtected() {
            return this.mConnection != null ? this.mConnection.isOomProtectedOrWasWhenDied() : this.mWasOomProtected;
        }

        /* access modifiers changed from: package-private */
        public void clearConnection() {
            this.mWasOomProtected = this.mConnection.isOomProtectedOrWasWhenDied();
            this.mConnection = null;
        }

        /* access modifiers changed from: package-private */
        @VisibleForTesting
        public boolean isConnectionCleared() {
            return this.mConnection == null;
        }
    }

    private BindingManagerImpl(boolean isLowMemoryDevice, long removeStrongBindingDelay) {
        this.mIsLowMemoryDevice = isLowMemoryDevice;
        this.mRemoveStrongBindingDelay = removeStrongBindingDelay;
    }

    public static BindingManagerImpl createBindingManager() {
        return new BindingManagerImpl(SysUtils.isLowEndDevice(), DETACH_AS_ACTIVE_HIGH_END_DELAY_MILLIS);
    }

    public static BindingManagerImpl createBindingManagerForTesting(boolean isLowEndDevice) {
        return new BindingManagerImpl(isLowEndDevice, 0);
    }

    public void addNewConnection(int pid, ChildProcessConnection connection) {
        synchronized (this.mLastInForegroundLock) {
            if (this.mIsLowMemoryDevice && this.mLastInForeground != null) {
                this.mLastInForeground.dropBindings();
            }
        }
        synchronized (this.mManagedConnections) {
            this.mManagedConnections.put(pid, new ManagedConnection(connection));
        }
    }

    public void setInForeground(int pid, boolean inForeground) {
        ManagedConnection managedConnection;
        synchronized (this.mManagedConnections) {
            managedConnection = this.mManagedConnections.get(pid);
        }
        if (managedConnection == null) {
            Log.w(TAG, "Cannot setInForeground() - never saw a connection for the pid: " + Integer.toString(pid));
            return;
        }
        synchronized (this.mLastInForegroundLock) {
            managedConnection.setInForeground(inForeground);
            if (inForeground) {
                this.mLastInForeground = managedConnection;
            }
        }
    }

    public void determinedVisibility(int pid) {
        ManagedConnection managedConnection;
        synchronized (this.mManagedConnections) {
            managedConnection = this.mManagedConnections.get(pid);
        }
        if (managedConnection == null) {
            Log.w(TAG, "Cannot call determinedVisibility() - never saw a connection for the pid: " + Integer.toString(pid));
        } else {
            managedConnection.determinedVisibility();
        }
    }

    public void onSentToBackground() {
        if ($assertionsDisabled || this.mBoundForBackgroundPeriod == null) {
            synchronized (this.mLastInForegroundLock) {
                if (this.mLastInForeground != null) {
                    this.mLastInForeground.setBoundForBackgroundPeriod(true);
                    this.mBoundForBackgroundPeriod = this.mLastInForeground;
                }
            }
            return;
        }
        throw new AssertionError();
    }

    public void onBroughtToForeground() {
        if (this.mBoundForBackgroundPeriod != null) {
            this.mBoundForBackgroundPeriod.setBoundForBackgroundPeriod(false);
            this.mBoundForBackgroundPeriod = null;
        }
    }

    public boolean isOomProtected(int pid) {
        ManagedConnection managedConnection;
        synchronized (this.mManagedConnections) {
            managedConnection = this.mManagedConnections.get(pid);
        }
        if (managedConnection != null) {
            return managedConnection.isOomProtected();
        }
        return false;
    }

    public void clearConnection(int pid) {
        ManagedConnection managedConnection;
        synchronized (this.mManagedConnections) {
            managedConnection = this.mManagedConnections.get(pid);
        }
        if (managedConnection != null) {
            managedConnection.clearConnection();
        }
    }

    @VisibleForTesting
    public boolean isConnectionCleared(int pid) {
        boolean isConnectionCleared;
        synchronized (this.mManagedConnections) {
            isConnectionCleared = this.mManagedConnections.get(pid).isConnectionCleared();
        }
        return isConnectionCleared;
    }
}
