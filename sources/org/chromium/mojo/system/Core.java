package org.chromium.mojo.system;

import java.util.List;
import org.chromium.mojo.system.DataPipe;
import org.chromium.mojo.system.MessagePipeHandle;
import org.chromium.mojo.system.SharedBufferHandle;

public interface Core {
    public static final long DEADLINE_INFINITE = -1;

    UntypedHandle acquireNativeHandle(int i);

    Pair<DataPipe.ProducerHandle, DataPipe.ConsumerHandle> createDataPipe(DataPipe.CreateOptions createOptions);

    Pair<MessagePipeHandle, MessagePipeHandle> createMessagePipe(MessagePipeHandle.CreateOptions createOptions);

    SharedBufferHandle createSharedBuffer(SharedBufferHandle.CreateOptions createOptions, long j);

    AsyncWaiter getDefaultAsyncWaiter();

    long getTimeTicksNow();

    int wait(Handle handle, HandleSignals handleSignals, long j);

    WaitManyResult waitMany(List<Pair<Handle, HandleSignals>> list, long j);

    public static class HandleSignals extends Flags<HandleSignals> {
        private static final int FLAG_NONE = 0;
        private static final int FLAG_READABLE = 1;
        private static final int FLAG_WRITABLE = 2;
        public static final HandleSignals NONE = ((HandleSignals) none().immutable());
        public static final HandleSignals READABLE = ((HandleSignals) none().setReadable(true).immutable());
        public static final HandleSignals WRITABLE = ((HandleSignals) none().setWritable(true).immutable());

        private HandleSignals(int signals) {
            super(signals);
        }

        public HandleSignals setReadable(boolean readable) {
            return (HandleSignals) setFlag(1, readable);
        }

        public HandleSignals setWritable(boolean writable) {
            return (HandleSignals) setFlag(2, writable);
        }

        public static HandleSignals none() {
            return new HandleSignals(0);
        }
    }

    public static class WaitManyResult {
        private int mHandleIndex;
        private int mMojoResult;

        public int getMojoResult() {
            return this.mMojoResult;
        }

        public void setMojoResult(int mojoResult) {
            this.mMojoResult = mojoResult;
        }

        public int getHandleIndex() {
            return this.mHandleIndex;
        }

        public void setHandleIndex(int handleIndex) {
            this.mHandleIndex = handleIndex;
        }
    }
}
