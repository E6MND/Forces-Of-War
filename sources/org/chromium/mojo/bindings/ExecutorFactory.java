package org.chromium.mojo.bindings;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import org.chromium.mojo.system.AsyncWaiter;
import org.chromium.mojo.system.Core;
import org.chromium.mojo.system.Handle;
import org.chromium.mojo.system.MessagePipeHandle;
import org.chromium.mojo.system.MojoException;
import org.chromium.mojo.system.Pair;

class ExecutorFactory {
    private static final ThreadLocal<Executor> EXECUTORS = new ThreadLocal<>();
    /* access modifiers changed from: private */
    public static final ByteBuffer NOTIFY_BUFFER = null;

    ExecutorFactory() {
    }

    private static class PipedExecutor implements Executor, AsyncWaiter.Callback {
        static final /* synthetic */ boolean $assertionsDisabled = (!ExecutorFactory.class.desiredAssertionStatus());
        private final Object mLock;
        private final List<Runnable> mPendingActions;
        private final MessagePipeHandle mReadHandle;
        private final AsyncWaiter mWaiter;
        private final MessagePipeHandle mWriteHandle;

        public PipedExecutor(Core core) {
            this.mWaiter = core.getDefaultAsyncWaiter();
            if ($assertionsDisabled || this.mWaiter != null) {
                this.mLock = new Object();
                Pair<MessagePipeHandle, MessagePipeHandle> handles = core.createMessagePipe(new MessagePipeHandle.CreateOptions());
                this.mReadHandle = (MessagePipeHandle) handles.first;
                this.mWriteHandle = (MessagePipeHandle) handles.second;
                this.mPendingActions = new ArrayList();
                asyncWait();
                return;
            }
            throw new AssertionError();
        }

        private void asyncWait() {
            this.mWaiter.asyncWait(this.mReadHandle, Core.HandleSignals.READABLE, -1, this);
        }

        public void onResult(int result) {
            if (result != 0 || !readNotifyBufferMessage()) {
                close();
            } else {
                runNextAction();
            }
        }

        public void onError(MojoException exception) {
            close();
        }

        private void close() {
            synchronized (this.mLock) {
                this.mWriteHandle.close();
                this.mPendingActions.clear();
            }
            this.mReadHandle.close();
        }

        private boolean readNotifyBufferMessage() {
            try {
                if (this.mReadHandle.readMessage(ExecutorFactory.NOTIFY_BUFFER, 0, MessagePipeHandle.ReadFlags.NONE).getMojoResult() != 0) {
                    return false;
                }
                asyncWait();
                return true;
            } catch (MojoException e) {
                return false;
            }
        }

        private void runNextAction() {
            Runnable toRun;
            synchronized (this.mWriteHandle) {
                toRun = this.mPendingActions.remove(0);
            }
            toRun.run();
        }

        public void execute(Runnable command) {
            synchronized (this.mLock) {
                if (!this.mWriteHandle.isValid()) {
                    throw new IllegalStateException("Trying to execute an action on a closed executor.");
                }
                this.mPendingActions.add(command);
                this.mWriteHandle.writeMessage(ExecutorFactory.NOTIFY_BUFFER, (List<? extends Handle>) null, MessagePipeHandle.WriteFlags.NONE);
            }
        }
    }

    public static Executor getExecutorForCurrentThread(Core core) {
        Executor executor = EXECUTORS.get();
        if (executor != null) {
            return executor;
        }
        Executor executor2 = new PipedExecutor(core);
        EXECUTORS.set(executor2);
        return executor2;
    }
}
