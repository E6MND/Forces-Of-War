package org.chromium.mojo.system;

import java.io.Closeable;
import org.chromium.mojo.system.Core;

public interface Handle extends Closeable {
    void close();

    Core getCore();

    boolean isValid();

    Handle pass();

    int releaseNativeHandle();

    UntypedHandle toUntypedHandle();

    int wait(Core.HandleSignals handleSignals, long j);
}
