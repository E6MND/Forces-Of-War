package org.chromium.mojo.bindings;

import java.io.Closeable;
import org.chromium.mojo.system.Core;
import org.chromium.mojo.system.MessagePipeHandle;
import org.chromium.mojo.system.MojoException;
import org.chromium.mojo.system.Pair;

public interface Interface extends ConnectionErrorHandler, Closeable {

    public interface Proxy extends Interface {
        void setErrorHandler(ConnectionErrorHandler connectionErrorHandler);
    }

    void close();

    public static abstract class AbstractProxy implements Proxy {
        private final Core mCore;
        private ConnectionErrorHandler mErrorHandler = null;
        private final MessageReceiverWithResponder mMessageReceiver;

        protected AbstractProxy(Core core, MessageReceiverWithResponder messageReceiver) {
            this.mCore = core;
            this.mMessageReceiver = messageReceiver;
        }

        /* access modifiers changed from: protected */
        public MessageReceiverWithResponder getMessageReceiver() {
            return this.mMessageReceiver;
        }

        /* access modifiers changed from: protected */
        public Core getCore() {
            return this.mCore;
        }

        public void setErrorHandler(ConnectionErrorHandler errorHandler) {
            this.mErrorHandler = errorHandler;
        }

        public void onConnectionError(MojoException e) {
            if (this.mErrorHandler != null) {
                this.mErrorHandler.onConnectionError(e);
            }
        }

        public void close() {
            this.mMessageReceiver.close();
        }
    }

    public static abstract class Stub<I extends Interface> implements MessageReceiverWithResponder {
        private final Core mCore;
        private final I mImpl;

        public Stub(Core core, I impl) {
            this.mCore = core;
            this.mImpl = impl;
        }

        /* access modifiers changed from: protected */
        public Core getCore() {
            return this.mCore;
        }

        /* access modifiers changed from: protected */
        public I getImpl() {
            return this.mImpl;
        }

        public void close() {
            this.mImpl.close();
        }
    }

    public static abstract class Manager<I extends Interface, P extends Proxy> {
        /* access modifiers changed from: protected */
        public abstract I[] buildArray(int i);

        /* access modifiers changed from: protected */
        public abstract P buildProxy(Core core, MessageReceiverWithResponder messageReceiverWithResponder);

        /* access modifiers changed from: protected */
        public abstract Stub<I> buildStub(Core core, I i);

        public abstract String getName();

        public void bind(I impl, MessagePipeHandle handle) {
            Router router = new RouterImpl(handle);
            bind(handle.getCore(), impl, router);
            router.start();
        }

        public final void bind(I impl, InterfaceRequest<I> request) {
            bind(impl, request.passHandle());
        }

        public final P attachProxy(MessagePipeHandle handle) {
            RouterImpl router = new RouterImpl(handle);
            P proxy = attachProxy(handle.getCore(), router);
            DelegatingConnectionErrorHandler handlers = new DelegatingConnectionErrorHandler();
            handlers.addConnectionErrorHandler(proxy);
            router.setErrorHandler(handlers);
            router.start();
            return proxy;
        }

        public final Pair<P, InterfaceRequest<I>> getInterfaceRequest(Core core) {
            Pair<MessagePipeHandle, MessagePipeHandle> handles = core.createMessagePipe((MessagePipeHandle.CreateOptions) null);
            return Pair.create(attachProxy((MessagePipeHandle) handles.first), new InterfaceRequest((MessagePipeHandle) handles.second));
        }

        /* access modifiers changed from: package-private */
        public final void bind(Core core, I impl, Router router) {
            router.setErrorHandler(impl);
            router.setIncomingMessageReceiver(buildStub(core, impl));
        }

        /* access modifiers changed from: package-private */
        public final P attachProxy(Core core, Router router) {
            return buildProxy(core, new AutoCloseableRouter(core, router));
        }
    }
}
