package org.chromium.mojo.bindings;

import java.nio.ByteBuffer;
import org.chromium.mojo.system.AsyncWaiter;
import org.chromium.mojo.system.Core;
import org.chromium.mojo.system.MessagePipeHandle;
import org.chromium.mojo.system.MojoException;

public class Connector implements MessageReceiver, HandleOwner<MessagePipeHandle> {
    static final /* synthetic */ boolean $assertionsDisabled = (!Connector.class.desiredAssertionStatus());
    private final AsyncWaiter mAsyncWaiter;
    private final AsyncWaiterCallback mAsyncWaiterCallback;
    private AsyncWaiter.Cancellable mCancellable;
    private ConnectionErrorHandler mErrorHandler;
    private MessageReceiver mIncomingMessageReceiver;
    private final MessagePipeHandle mMessagePipeHandle;

    public Connector(MessagePipeHandle messagePipeHandle) {
        this(messagePipeHandle, BindingsHelper.getDefaultAsyncWaiterForHandle(messagePipeHandle));
    }

    public Connector(MessagePipeHandle messagePipeHandle, AsyncWaiter asyncWaiter) {
        this.mAsyncWaiterCallback = new AsyncWaiterCallback();
        this.mCancellable = null;
        this.mMessagePipeHandle = messagePipeHandle;
        this.mAsyncWaiter = asyncWaiter;
    }

    public void setIncomingMessageReceiver(MessageReceiver incomingMessageReceiver) {
        this.mIncomingMessageReceiver = incomingMessageReceiver;
    }

    public void setErrorHandler(ConnectionErrorHandler errorHandler) {
        this.mErrorHandler = errorHandler;
    }

    public void start() {
        if ($assertionsDisabled || this.mCancellable == null) {
            registerAsyncWaiterForRead();
            return;
        }
        throw new AssertionError();
    }

    public boolean accept(Message message) {
        try {
            this.mMessagePipeHandle.writeMessage(message.getData(), message.getHandles(), MessagePipeHandle.WriteFlags.NONE);
            return true;
        } catch (MojoException e) {
            onError(e);
            return false;
        }
    }

    public MessagePipeHandle passHandle() {
        cancelIfActive();
        MessagePipeHandle handle = this.mMessagePipeHandle.pass();
        if (this.mIncomingMessageReceiver != null) {
            this.mIncomingMessageReceiver.close();
        }
        return handle;
    }

    public void close() {
        cancelIfActive();
        this.mMessagePipeHandle.close();
        if (this.mIncomingMessageReceiver != null) {
            this.mIncomingMessageReceiver.close();
        }
    }

    private class AsyncWaiterCallback implements AsyncWaiter.Callback {
        private AsyncWaiterCallback() {
        }

        public void onResult(int result) {
            Connector.this.onAsyncWaiterResult(result);
        }

        public void onError(MojoException exception) {
            Connector.this.onError(exception);
        }
    }

    /* access modifiers changed from: private */
    public void onAsyncWaiterResult(int result) {
        this.mCancellable = null;
        if (result == 0) {
            readOutstandingMessages();
        } else {
            onError(new MojoException(result));
        }
    }

    /* access modifiers changed from: private */
    public void onError(MojoException exception) {
        this.mCancellable = null;
        close();
        if (this.mErrorHandler != null) {
            this.mErrorHandler.onConnectionError(exception);
        }
    }

    private void registerAsyncWaiterForRead() {
        if (!$assertionsDisabled && this.mCancellable != null) {
            throw new AssertionError();
        } else if (this.mAsyncWaiter != null) {
            this.mCancellable = this.mAsyncWaiter.asyncWait(this.mMessagePipeHandle, Core.HandleSignals.READABLE, -1, this.mAsyncWaiterCallback);
        } else {
            onError(new MojoException(-3));
        }
    }

    private void readOutstandingMessages() {
        int result;
        do {
            try {
                result = readAndDispatchMessage(this.mMessagePipeHandle, this.mIncomingMessageReceiver);
            } catch (MojoException e) {
                onError(e);
                return;
            }
        } while (result == 0);
        if (result == -17) {
            registerAsyncWaiterForRead();
        } else {
            onError(new MojoException(result));
        }
    }

    private void cancelIfActive() {
        if (this.mCancellable != null) {
            this.mCancellable.cancel();
            this.mCancellable = null;
        }
    }

    static int readAndDispatchMessage(MessagePipeHandle handle, MessageReceiver receiver) {
        MessagePipeHandle.ReadMessageResult result = handle.readMessage((ByteBuffer) null, 0, MessagePipeHandle.ReadFlags.NONE);
        if (result.getMojoResult() != -8) {
            return result.getMojoResult();
        }
        ByteBuffer buffer = ByteBuffer.allocateDirect(result.getMessageSize());
        MessagePipeHandle.ReadMessageResult result2 = handle.readMessage(buffer, result.getHandlesCount(), MessagePipeHandle.ReadFlags.NONE);
        if (receiver != null && result2.getMojoResult() == 0) {
            receiver.accept(new Message(buffer, result2.getHandles()));
        }
        return result2.getMojoResult();
    }
}
