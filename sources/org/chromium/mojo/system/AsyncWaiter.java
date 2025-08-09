package org.chromium.mojo.system;

import org.chromium.mojo.system.Core;

public interface AsyncWaiter {

    public interface Callback {
        void onError(MojoException mojoException);

        void onResult(int i);
    }

    public interface Cancellable {
        void cancel();
    }

    Cancellable asyncWait(Handle handle, Core.HandleSignals handleSignals, long j, Callback callback);
}
