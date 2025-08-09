package org.chromium.mojo.system.impl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;
import org.chromium.mojo.system.AsyncWaiter;
import org.chromium.mojo.system.Core;
import org.chromium.mojo.system.DataPipe;
import org.chromium.mojo.system.Handle;
import org.chromium.mojo.system.MessagePipeHandle;
import org.chromium.mojo.system.MojoException;
import org.chromium.mojo.system.Pair;
import org.chromium.mojo.system.SharedBufferHandle;
import org.chromium.mojo.system.UntypedHandle;

@JNINamespace("mojo::android")
public class CoreImpl implements Core, AsyncWaiter {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreImpl.class.desiredAssertionStatus());
    private static final int FLAG_SIZE = 4;
    private static final int HANDLE_SIZE = 4;
    static final int INVALID_HANDLE = 0;
    private static final int MOJO_READ_DATA_FLAG_DISCARD = 2;

    private native AsyncWaiterCancellableImpl nativeAsyncWait(int i, int i2, long j, AsyncWaiter.Callback callback);

    private native NativeCodeAndBufferResult nativeBeginReadData(int i, int i2, int i3);

    private native NativeCodeAndBufferResult nativeBeginWriteData(int i, int i2, int i3);

    /* access modifiers changed from: private */
    public native void nativeCancelAsyncWait(long j, long j2);

    private native int nativeClose(int i);

    private native NativeCreationResult nativeCreateDataPipe(ByteBuffer byteBuffer);

    private native NativeCreationResult nativeCreateMessagePipe(ByteBuffer byteBuffer);

    private native NativeCreationResult nativeCreateSharedBuffer(ByteBuffer byteBuffer, long j);

    private native NativeCreationResult nativeDuplicate(int i, ByteBuffer byteBuffer);

    private native int nativeEndReadData(int i, int i2);

    private native int nativeEndWriteData(int i, int i2);

    private native long nativeGetTimeTicksNow();

    private native NativeCodeAndBufferResult nativeMap(int i, long j, long j2, int i2);

    private native int nativeReadData(int i, ByteBuffer byteBuffer, int i2, int i3);

    private native MessagePipeHandle.ReadMessageResult nativeReadMessage(int i, ByteBuffer byteBuffer, ByteBuffer byteBuffer2, int i2);

    private native int nativeUnmap(ByteBuffer byteBuffer);

    private native int nativeWait(int i, int i2, long j);

    private native int nativeWaitMany(ByteBuffer byteBuffer, long j);

    private native int nativeWriteData(int i, ByteBuffer byteBuffer, int i2, int i3);

    private native int nativeWriteMessage(int i, ByteBuffer byteBuffer, int i2, ByteBuffer byteBuffer2, int i3);

    private static class LazyHolder {
        /* access modifiers changed from: private */
        public static final Core INSTANCE = new CoreImpl();

        private LazyHolder() {
        }
    }

    public static Core getInstance() {
        return LazyHolder.INSTANCE;
    }

    private CoreImpl() {
    }

    public long getTimeTicksNow() {
        return nativeGetTimeTicksNow();
    }

    public Core.WaitManyResult waitMany(List<Pair<Handle, Core.HandleSignals>> handles, long deadline) {
        ByteBuffer buffer = allocateDirectBuffer(handles.size() * 8);
        int index = 0;
        for (Pair<Handle, Core.HandleSignals> handle : handles) {
            buffer.putInt(index * 4, getMojoHandle((Handle) handle.first));
            buffer.putInt((index * 4) + (handles.size() * 4), ((Core.HandleSignals) handle.second).getFlags());
            index++;
        }
        int code = nativeWaitMany(buffer, deadline);
        Core.WaitManyResult result = new Core.WaitManyResult();
        result.setHandleIndex(code);
        result.setMojoResult(filterMojoResultForWait(code));
        return result;
    }

    public int wait(Handle handle, Core.HandleSignals signals, long deadline) {
        return filterMojoResultForWait(nativeWait(getMojoHandle(handle), signals.getFlags(), deadline));
    }

    public Pair<MessagePipeHandle, MessagePipeHandle> createMessagePipe(MessagePipeHandle.CreateOptions options) {
        ByteBuffer optionsBuffer = null;
        if (options != null) {
            optionsBuffer = allocateDirectBuffer(8);
            optionsBuffer.putInt(0, 8);
            optionsBuffer.putInt(4, options.getFlags().getFlags());
        }
        NativeCreationResult result = nativeCreateMessagePipe(optionsBuffer);
        if (result.getMojoResult() == 0) {
            return Pair.create(new MessagePipeHandleImpl(this, result.getMojoHandle1()), new MessagePipeHandleImpl(this, result.getMojoHandle2()));
        }
        throw new MojoException(result.getMojoResult());
    }

    public Pair<DataPipe.ProducerHandle, DataPipe.ConsumerHandle> createDataPipe(DataPipe.CreateOptions options) {
        ByteBuffer optionsBuffer = null;
        if (options != null) {
            optionsBuffer = allocateDirectBuffer(16);
            optionsBuffer.putInt(0, 16);
            optionsBuffer.putInt(4, options.getFlags().getFlags());
            optionsBuffer.putInt(8, options.getElementNumBytes());
            optionsBuffer.putInt(12, options.getCapacityNumBytes());
        }
        NativeCreationResult result = nativeCreateDataPipe(optionsBuffer);
        if (result.getMojoResult() == 0) {
            return Pair.create(new DataPipeProducerHandleImpl(this, result.getMojoHandle1()), new DataPipeConsumerHandleImpl(this, result.getMojoHandle2()));
        }
        throw new MojoException(result.getMojoResult());
    }

    public SharedBufferHandle createSharedBuffer(SharedBufferHandle.CreateOptions options, long numBytes) {
        ByteBuffer optionsBuffer = null;
        if (options != null) {
            optionsBuffer = allocateDirectBuffer(8);
            optionsBuffer.putInt(0, 8);
            optionsBuffer.putInt(4, options.getFlags().getFlags());
        }
        NativeCreationResult result = nativeCreateSharedBuffer(optionsBuffer, numBytes);
        if (result.getMojoResult() != 0) {
            throw new MojoException(result.getMojoResult());
        } else if ($assertionsDisabled || result.getMojoHandle2() == 0) {
            return new SharedBufferHandleImpl(this, result.getMojoHandle1());
        } else {
            throw new AssertionError();
        }
    }

    public UntypedHandle acquireNativeHandle(int handle) {
        return new UntypedHandleImpl(this, handle);
    }

    public AsyncWaiter getDefaultAsyncWaiter() {
        return this;
    }

    public AsyncWaiter.Cancellable asyncWait(Handle handle, Core.HandleSignals signals, long deadline, AsyncWaiter.Callback callback) {
        return nativeAsyncWait(getMojoHandle(handle), signals.getFlags(), deadline, callback);
    }

    /* access modifiers changed from: package-private */
    public int closeWithResult(int mojoHandle) {
        return nativeClose(mojoHandle);
    }

    /* access modifiers changed from: package-private */
    public void close(int mojoHandle) {
        int mojoResult = nativeClose(mojoHandle);
        if (mojoResult != 0) {
            throw new MojoException(mojoResult);
        }
    }

    /* access modifiers changed from: package-private */
    public void writeMessage(MessagePipeHandleImpl pipeHandle, ByteBuffer bytes, List<? extends Handle> handles, MessagePipeHandle.WriteFlags flags) {
        int i = 0;
        ByteBuffer handlesBuffer = null;
        if (handles != null && !handles.isEmpty()) {
            handlesBuffer = allocateDirectBuffer(handles.size() * 4);
            for (Handle handle : handles) {
                handlesBuffer.putInt(getMojoHandle(handle));
            }
            handlesBuffer.position(0);
        }
        int mojoHandle = pipeHandle.getMojoHandle();
        if (bytes != null) {
            i = bytes.limit();
        }
        int mojoResult = nativeWriteMessage(mojoHandle, bytes, i, handlesBuffer, flags.getFlags());
        if (mojoResult != 0) {
            throw new MojoException(mojoResult);
        } else if (handles != null) {
            for (Handle handle2 : handles) {
                if (handle2.isValid()) {
                    ((HandleBase) handle2).invalidateHandle();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public MessagePipeHandle.ReadMessageResult readMessage(MessagePipeHandleImpl handle, ByteBuffer bytes, int maxNumberOfHandles, MessagePipeHandle.ReadFlags flags) {
        ByteBuffer handlesBuffer = null;
        if (maxNumberOfHandles > 0) {
            handlesBuffer = allocateDirectBuffer(maxNumberOfHandles * 4);
        }
        MessagePipeHandle.ReadMessageResult result = nativeReadMessage(handle.getMojoHandle(), bytes, handlesBuffer, flags.getFlags());
        if (result.getMojoResult() == 0 || result.getMojoResult() == -8 || result.getMojoResult() == -17) {
            if (result.getMojoResult() == 0) {
                if (bytes != null) {
                    bytes.position(0);
                    bytes.limit(result.getMessageSize());
                }
                List<UntypedHandle> handles = new ArrayList<>(result.getHandlesCount());
                for (int i = 0; i < result.getHandlesCount(); i++) {
                    handles.add(new UntypedHandleImpl(this, handlesBuffer.getInt(i * 4)));
                }
                result.setHandles(handles);
            }
            return result;
        }
        throw new MojoException(result.getMojoResult());
    }

    /* access modifiers changed from: package-private */
    public int discardData(DataPipeConsumerHandleImpl handle, int numBytes, DataPipe.ReadFlags flags) {
        int result = nativeReadData(handle.getMojoHandle(), (ByteBuffer) null, numBytes, flags.getFlags() | 2);
        if (result >= 0) {
            return result;
        }
        throw new MojoException(result);
    }

    /* access modifiers changed from: package-private */
    public int readData(DataPipeConsumerHandleImpl handle, ByteBuffer elements, DataPipe.ReadFlags flags) {
        int result = nativeReadData(handle.getMojoHandle(), elements, elements == null ? 0 : elements.capacity(), flags.getFlags());
        if (result < 0) {
            throw new MojoException(result);
        }
        if (elements != null) {
            elements.limit(result);
        }
        return result;
    }

    /* access modifiers changed from: package-private */
    public ByteBuffer beginReadData(DataPipeConsumerHandleImpl handle, int numBytes, DataPipe.ReadFlags flags) {
        NativeCodeAndBufferResult result = nativeBeginReadData(handle.getMojoHandle(), numBytes, flags.getFlags());
        if (result.getMojoResult() == 0) {
            return result.getBuffer().asReadOnlyBuffer();
        }
        throw new MojoException(result.getMojoResult());
    }

    /* access modifiers changed from: package-private */
    public void endReadData(DataPipeConsumerHandleImpl handle, int numBytesRead) {
        int result = nativeEndReadData(handle.getMojoHandle(), numBytesRead);
        if (result != 0) {
            throw new MojoException(result);
        }
    }

    /* access modifiers changed from: package-private */
    public int writeData(DataPipeProducerHandleImpl handle, ByteBuffer elements, DataPipe.WriteFlags flags) {
        return nativeWriteData(handle.getMojoHandle(), elements, elements.limit(), flags.getFlags());
    }

    /* access modifiers changed from: package-private */
    public ByteBuffer beginWriteData(DataPipeProducerHandleImpl handle, int numBytes, DataPipe.WriteFlags flags) {
        NativeCodeAndBufferResult result = nativeBeginWriteData(handle.getMojoHandle(), numBytes, flags.getFlags());
        if (result.getMojoResult() == 0) {
            return result.getBuffer();
        }
        throw new MojoException(result.getMojoResult());
    }

    /* access modifiers changed from: package-private */
    public void endWriteData(DataPipeProducerHandleImpl handle, int numBytesWritten) {
        int result = nativeEndWriteData(handle.getMojoHandle(), numBytesWritten);
        if (result != 0) {
            throw new MojoException(result);
        }
    }

    /* access modifiers changed from: package-private */
    public SharedBufferHandle duplicate(SharedBufferHandleImpl handle, SharedBufferHandle.DuplicateOptions options) {
        ByteBuffer optionsBuffer = null;
        if (options != null) {
            optionsBuffer = allocateDirectBuffer(8);
            optionsBuffer.putInt(0, 8);
            optionsBuffer.putInt(4, options.getFlags().getFlags());
        }
        NativeCreationResult result = nativeDuplicate(handle.getMojoHandle(), optionsBuffer);
        if (result.getMojoResult() != 0) {
            throw new MojoException(result.getMojoResult());
        } else if ($assertionsDisabled || result.getMojoHandle2() == 0) {
            return new SharedBufferHandleImpl(this, result.getMojoHandle1());
        } else {
            throw new AssertionError();
        }
    }

    /* access modifiers changed from: package-private */
    public ByteBuffer map(SharedBufferHandleImpl handle, long offset, long numBytes, SharedBufferHandle.MapFlags flags) {
        NativeCodeAndBufferResult result = nativeMap(handle.getMojoHandle(), offset, numBytes, flags.getFlags());
        if (result.getMojoResult() == 0) {
            return result.getBuffer();
        }
        throw new MojoException(result.getMojoResult());
    }

    /* access modifiers changed from: package-private */
    public void unmap(ByteBuffer buffer) {
        int result = nativeUnmap(buffer);
        if (result != 0) {
            throw new MojoException(result);
        }
    }

    private int getMojoHandle(Handle handle) {
        if (handle.isValid()) {
            return ((HandleBase) handle).getMojoHandle();
        }
        return 0;
    }

    private static boolean isUnrecoverableError(int code) {
        switch (code) {
            case -9:
            case -4:
            case -1:
            case 0:
                return false;
            default:
                return true;
        }
    }

    private static int filterMojoResult(int code) {
        if (code >= 0) {
            return 0;
        }
        return code;
    }

    private static int filterMojoResultForWait(int code) {
        int finalCode = filterMojoResult(code);
        if (!isUnrecoverableError(finalCode)) {
            return finalCode;
        }
        throw new MojoException(finalCode);
    }

    private static ByteBuffer allocateDirectBuffer(int capacity) {
        ByteBuffer buffer = ByteBuffer.allocateDirect(capacity);
        buffer.order(ByteOrder.nativeOrder());
        return buffer;
    }

    private static class NativeCodeAndBufferResult {
        private ByteBuffer mBuffer;
        private int mMojoResult;

        private NativeCodeAndBufferResult() {
        }

        public int getMojoResult() {
            return this.mMojoResult;
        }

        public void setMojoResult(int mojoResult) {
            this.mMojoResult = mojoResult;
        }

        public ByteBuffer getBuffer() {
            return this.mBuffer;
        }

        public void setBuffer(ByteBuffer buffer) {
            this.mBuffer = buffer;
        }
    }

    private class AsyncWaiterCancellableImpl implements AsyncWaiter.Cancellable {
        private boolean mActive;
        private final long mDataPtr;
        private final long mId;

        private AsyncWaiterCancellableImpl(long id, long dataPtr) {
            this.mActive = true;
            this.mId = id;
            this.mDataPtr = dataPtr;
        }

        public void cancel() {
            if (this.mActive) {
                this.mActive = false;
                CoreImpl.this.nativeCancelAsyncWait(this.mId, this.mDataPtr);
            }
        }

        /* access modifiers changed from: private */
        public boolean isActive() {
            return this.mActive;
        }

        /* access modifiers changed from: private */
        public void deactivate() {
            this.mActive = false;
        }
    }

    @CalledByNative
    private AsyncWaiterCancellableImpl newAsyncWaiterCancellableImpl(long id, long dataPtr) {
        return new AsyncWaiterCancellableImpl(id, dataPtr);
    }

    @CalledByNative
    private void onAsyncWaitResult(int mojoResult, AsyncWaiter.Callback callback, AsyncWaiterCancellableImpl cancellable) {
        if (cancellable.isActive()) {
            cancellable.deactivate();
            int finalCode = filterMojoResult(mojoResult);
            if (isUnrecoverableError(finalCode)) {
                callback.onError(new MojoException(finalCode));
            } else {
                callback.onResult(finalCode);
            }
        }
    }

    @CalledByNative
    private static NativeCodeAndBufferResult newNativeCodeAndBufferResult(int mojoResult, ByteBuffer buffer) {
        NativeCodeAndBufferResult result = new NativeCodeAndBufferResult();
        result.setMojoResult(mojoResult);
        result.setBuffer(buffer);
        return result;
    }

    @CalledByNative
    private static MessagePipeHandle.ReadMessageResult newReadMessageResult(int mojoResult, int messageSize, int handlesCount) {
        MessagePipeHandle.ReadMessageResult result = new MessagePipeHandle.ReadMessageResult();
        if (mojoResult >= 0) {
            result.setMojoResult(0);
        } else {
            result.setMojoResult(mojoResult);
        }
        result.setMessageSize(messageSize);
        result.setHandlesCount(handlesCount);
        return result;
    }

    private static class NativeCreationResult {
        private int mMojoHandle1;
        private int mMojoHandle2;
        private int mMojoResult;

        private NativeCreationResult() {
        }

        public int getMojoResult() {
            return this.mMojoResult;
        }

        public void setMojoResult(int mojoResult) {
            this.mMojoResult = mojoResult;
        }

        public int getMojoHandle1() {
            return this.mMojoHandle1;
        }

        public void setMojoHandle1(int mojoHandle1) {
            this.mMojoHandle1 = mojoHandle1;
        }

        public int getMojoHandle2() {
            return this.mMojoHandle2;
        }

        public void setMojoHandle2(int mojoHandle2) {
            this.mMojoHandle2 = mojoHandle2;
        }
    }

    @CalledByNative
    private static NativeCreationResult newNativeCreationResult(int mojoResult, int mojoHandle1, int mojoHandle2) {
        NativeCreationResult result = new NativeCreationResult();
        result.setMojoResult(mojoResult);
        result.setMojoHandle1(mojoHandle1);
        result.setMojoHandle2(mojoHandle2);
        return result;
    }
}
