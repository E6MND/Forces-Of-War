package org.chromium.mojo.bindings;

import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.system.Core;
import org.chromium.mojo.system.MessagePipeHandle;
import org.chromium.mojo.system.Pair;

public interface InterfaceWithClient<CI extends Interface> extends Interface {

    public interface Proxy<CI extends Interface> extends Interface.Proxy, InterfaceWithClient<CI> {
    }

    void setClient(CI ci);

    public static abstract class AbstractProxy<CI extends Interface> extends Interface.AbstractProxy implements Proxy<CI> {
        public AbstractProxy(Core core, MessageReceiverWithResponder messageReceiver) {
            super(core, messageReceiver);
        }

        public void setClient(CI ci) {
            throw new UnsupportedOperationException("Setting the client on a proxy is not supported");
        }
    }

    public static abstract class Manager<I extends InterfaceWithClient<CI>, P extends Proxy<CI>, CI extends Interface> extends Interface.Manager<I, P> {
        /* access modifiers changed from: protected */
        public abstract Interface.Manager<CI, ?> getClientManager();

        public final void bind(I impl, MessagePipeHandle handle) {
            Router router = new RouterImpl(handle);
            super.bind(handle.getCore(), impl, router);
            impl.setClient(getClientManager().attachProxy(handle.getCore(), router));
            router.start();
        }

        public P attachProxy(MessagePipeHandle handle, CI client) {
            Router router = new RouterImpl(handle);
            DelegatingConnectionErrorHandler handlers = new DelegatingConnectionErrorHandler();
            handlers.addConnectionErrorHandler(client);
            router.setErrorHandler(handlers);
            getClientManager().bind(handle.getCore(), client, router);
            P proxy = (Proxy) super.attachProxy(handle.getCore(), router);
            handlers.addConnectionErrorHandler(proxy);
            router.start();
            return proxy;
        }

        public final Pair<P, InterfaceRequest<I>> getInterfaceRequest(Core core, CI client) {
            Pair<MessagePipeHandle, MessagePipeHandle> handles = core.createMessagePipe((MessagePipeHandle.CreateOptions) null);
            return Pair.create(attachProxy((MessagePipeHandle) handles.first, client), new InterfaceRequest((MessagePipeHandle) handles.second));
        }
    }
}
