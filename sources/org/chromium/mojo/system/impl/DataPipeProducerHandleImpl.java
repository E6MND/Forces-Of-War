package org.chromium.mojo.system.impl;

import java.nio.ByteBuffer;
import org.chromium.mojo.system.DataPipe;

class DataPipeProducerHandleImpl extends HandleBase implements DataPipe.ProducerHandle {
    DataPipeProducerHandleImpl(CoreImpl core, int mojoHandle) {
        super(core, mojoHandle);
    }

    DataPipeProducerHandleImpl(HandleBase handle) {
        super(handle);
    }

    public DataPipe.ProducerHandle pass() {
        return new DataPipeProducerHandleImpl(this);
    }

    public int writeData(ByteBuffer elements, DataPipe.WriteFlags flags) {
        return this.mCore.writeData(this, elements, flags);
    }

    public ByteBuffer beginWriteData(int numBytes, DataPipe.WriteFlags flags) {
        return this.mCore.beginWriteData(this, numBytes, flags);
    }

    public void endWriteData(int numBytesWritten) {
        this.mCore.endWriteData(this, numBytesWritten);
    }
}
