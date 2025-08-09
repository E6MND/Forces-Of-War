package org.chromium.mojo.bindings;

import java.util.HashMap;
import java.util.Map;
import org.chromium.mojo.system.AsyncWaiter;
import org.chromium.mojo.system.MessagePipeHandle;

public class RouterImpl implements Router {
    static final /* synthetic */ boolean $assertionsDisabled = (!RouterImpl.class.desiredAssertionStatus());
    private final Connector mConnector;
    private MessageReceiverWithResponder mIncomingMessageReceiver;
    private long mNextRequestId;
    private Map<Long, MessageReceiver> mResponders;

    private class ResponderThunk implements MessageReceiver {
        private ResponderThunk() {
        }

        public boolean accept(Message message) {
            return RouterImpl.this.handleIncomingMessage(message);
        }

        public void close() {
            RouterImpl.this.handleConnectorClose();
        }
    }

    public RouterImpl(MessagePipeHandle messagePipeHandle) {
        this(messagePipeHandle, BindingsHelper.getDefaultAsyncWaiterForHandle(messagePipeHandle));
    }

    public RouterImpl(MessagePipeHandle messagePipeHandle, AsyncWaiter asyncWaiter) {
        this.mNextRequestId = 1;
        this.mResponders = new HashMap();
        this.mConnector = new Connector(messagePipeHandle, asyncWaiter);
        this.mConnector.setIncomingMessageReceiver(new ResponderThunk());
    }

    public void start() {
        this.mConnector.start();
    }

    public void setIncomingMessageReceiver(MessageReceiverWithResponder incomingMessageReceiver) {
        this.mIncomingMessageReceiver = incomingMessageReceiver;
    }

    public boolean accept(Message message) {
        return this.mConnector.accept(message);
    }

    public boolean acceptWithResponder(Message message, MessageReceiver responder) {
        ServiceMessage messageWithHeader = message.asServiceMessage();
        if ($assertionsDisabled || messageWithHeader.getHeader().hasFlag(1)) {
            long requestId = this.mNextRequestId;
            this.mNextRequestId = requestId + 1;
            if (requestId == 0) {
                requestId = this.mNextRequestId;
                this.mNextRequestId = requestId + 1;
            }
            if (this.mResponders.containsKey(Long.valueOf(requestId))) {
                throw new IllegalStateException("Unable to find a new request identifier.");
            }
            messageWithHeader.setRequestId(requestId);
            if (!this.mConnector.accept(messageWithHeader)) {
                return false;
            }
            this.mResponders.put(Long.valueOf(requestId), responder);
            return true;
        }
        throw new AssertionError();
    }

    public MessagePipeHandle passHandle() {
        return this.mConnector.passHandle();
    }

    public void close() {
        this.mConnector.close();
    }

    public void setErrorHandler(ConnectionErrorHandler errorHandler) {
        this.mConnector.setErrorHandler(errorHandler);
    }

    /* access modifiers changed from: private */
    public boolean handleIncomingMessage(Message message) {
        MessageHeader header = message.asServiceMessage().getHeader();
        if (header.hasFlag(1)) {
            if (this.mIncomingMessageReceiver != null) {
                return this.mIncomingMessageReceiver.acceptWithResponder(message, this);
            }
            close();
            return false;
        } else if (header.hasFlag(2)) {
            long requestId = header.getRequestId();
            MessageReceiver responder = this.mResponders.get(Long.valueOf(requestId));
            if (responder == null) {
                return false;
            }
            this.mResponders.remove(Long.valueOf(requestId));
            return responder.accept(message);
        } else if (this.mIncomingMessageReceiver != null) {
            return this.mIncomingMessageReceiver.accept(message);
        } else {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void handleConnectorClose() {
        if (this.mIncomingMessageReceiver != null) {
            this.mIncomingMessageReceiver.close();
        }
    }
}
