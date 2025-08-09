package org.chromium.mojo.system.impl;

import java.nio.ByteBuffer;
import org.chromium.mojo.system.DataPipe;

class DataPipeConsumerHandleImpl extends HandleBase implements DataPipe.ConsumerHandle {
    DataPipeConsumerHandleImpl(CoreImpl core, int mojoHandle) {
        super(core, mojoHandle);
    }

    DataPipeConsumerHandleImpl(HandleBase other) {
        super(other);
    }

    public DataPipe.ConsumerHandle pass() {
        return new DataPipeConsumerHandleImpl(this);
    }

    public int discardData(int numBytes, DataPipe.ReadFlags flags) {
        return this.mCore.discardData(this, numBytes, flags);
    }

    public int readData(ByteBuffer elements, DataPipe.ReadFlags flags) {
        return this.mCore.readData(this, elements, flags);
    }

    public ByteBuffer beginReadData(int numBytes, DataPipe.ReadFlags flags) {
        return this.mCore.beginReadData(this, numBytes, flags);
    }

    public void endReadData(int numBytesRead) {
        this.mCore.endReadData(this, numBytesRead);
    }
}
