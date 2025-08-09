package org.chromium.mojo.bindings;

import org.chromium.mojo.system.MojoException;

interface ConnectionErrorHandler {
    void onConnectionError(MojoException mojoException);
}
