package org.chromium.mojo.system;

import org.chromium.mojo.system.DataPipe;

public interface UntypedHandle extends Handle {
    UntypedHandle pass();

    DataPipe.ConsumerHandle toDataPipeConsumerHandle();

    DataPipe.ProducerHandle toDataPipeProducerHandle();

    MessagePipeHandle toMessagePipeHandle();

    SharedBufferHandle toSharedBufferHandle();
}
