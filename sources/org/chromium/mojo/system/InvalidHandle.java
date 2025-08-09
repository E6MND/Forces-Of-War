package org.chromium.mojo.system;

import java.nio.ByteBuffer;
import java.util.List;
import org.chromium.mojo.system.Core;
import org.chromium.mojo.system.DataPipe;
import org.chromium.mojo.system.MessagePipeHandle;
import org.chromium.mojo.system.SharedBufferHandle;

public class InvalidHandle implements UntypedHandle, MessagePipeHandle, DataPipe.ConsumerHandle, DataPipe.ProducerHandle, SharedBufferHandle {
    public static final InvalidHandle INSTANCE = new InvalidHandle();

    private InvalidHandle() {
    }

    public void close() {
    }

    public int wait(Core.HandleSignals signals, long deadline) {
        throw new MojoException(-3);
    }

    public boolean isValid() {
        return false;
    }

    public Core getCore() {
        return null;
    }

    public InvalidHandle pass() {
        return this;
    }

    public UntypedHandle toUntypedHandle() {
        return this;
    }

    public int releaseNativeHandle() {
        return -1;
    }

    public MessagePipeHandle toMessagePipeHandle() {
        return this;
    }

    public DataPipe.ConsumerHandle toDataPipeConsumerHandle() {
        return this;
    }

    public DataPipe.ProducerHandle toDataPipeProducerHandle() {
        return this;
    }

    public SharedBufferHandle toSharedBufferHandle() {
        return this;
    }

    public SharedBufferHandle duplicate(SharedBufferHandle.DuplicateOptions options) {
        throw new MojoException(-3);
    }

    public ByteBuffer map(long offset, long numBytes, SharedBufferHandle.MapFlags flags) {
        throw new MojoException(-3);
    }

    public void unmap(ByteBuffer buffer) {
        throw new MojoException(-3);
    }

    public int writeData(ByteBuffer elements, DataPipe.WriteFlags flags) {
        throw new MojoException(-3);
    }

    public ByteBuffer beginWriteData(int numBytes, DataPipe.WriteFlags flags) {
        throw new MojoException(-3);
    }

    public void endWriteData(int numBytesWritten) {
        throw new MojoException(-3);
    }

    public int discardData(int numBytes, DataPipe.ReadFlags flags) {
        throw new MojoException(-3);
    }

    public int readData(ByteBuffer elements, DataPipe.ReadFlags flags) {
        throw new MojoException(-3);
    }

    public ByteBuffer beginReadData(int numBytes, DataPipe.ReadFlags flags) {
        throw new MojoException(-3);
    }

    public void endReadData(int numBytesRead) {
        throw new MojoException(-3);
    }

    public void writeMessage(ByteBuffer bytes, List<? extends Handle> list, MessagePipeHandle.WriteFlags flags) {
        throw new MojoException(-3);
    }

    public MessagePipeHandle.ReadMessageResult readMessage(ByteBuffer bytes, int maxNumberOfHandles, MessagePipeHandle.ReadFlags flags) {
        throw new MojoException(-3);
    }
}
