package org.chromium.mojo.bindings;

import java.nio.ByteOrder;
import java.nio.charset.Charset;
import org.chromium.mojo.bindings.Interface;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.system.DataPipe;
import org.chromium.mojo.system.Handle;
import org.chromium.mojo.system.InvalidHandle;
import org.chromium.mojo.system.MessagePipeHandle;
import org.chromium.mojo.system.SharedBufferHandle;
import org.chromium.mojo.system.UntypedHandle;

public class Decoder {
    private final int mBaseOffset;
    private final Message mMessage;
    private final Validator mValidator;

    static final class Validator {
        private final long mMaxMemory;
        private int mMinNextClaimedHandle = 0;
        private long mMinNextMemory = 0;
        private final long mNumberOfHandles;

        Validator(long maxMemory, int numberOfHandles) {
            this.mMaxMemory = maxMemory;
            this.mNumberOfHandles = (long) numberOfHandles;
        }

        public void claimHandle(int handle) {
            if (handle < this.mMinNextClaimedHandle) {
                throw new DeserializationException("Trying to access handle out of order.");
            } else if (((long) handle) >= this.mNumberOfHandles) {
                throw new DeserializationException("Trying to access non present handle.");
            } else {
                this.mMinNextClaimedHandle = handle + 1;
            }
        }

        public void claimMemory(long start, long end) {
            if (start % 8 != 0) {
                throw new DeserializationException("Incorrect starting alignment: " + start + ".");
            } else if (start < this.mMinNextMemory) {
                throw new DeserializationException("Trying to access memory out of order.");
            } else if (end < start) {
                throw new DeserializationException("Incorrect memory range.");
            } else if (end > this.mMaxMemory) {
                throw new DeserializationException("Trying to access out of range memory.");
            } else {
                this.mMinNextMemory = BindingsHelper.align(end);
            }
        }
    }

    public Decoder(Message message) {
        this(message, new Validator((long) message.getData().limit(), message.getHandles().size()), 0);
    }

    private Decoder(Message message, Validator validator, int baseOffset) {
        this.mMessage = message;
        this.mMessage.getData().order(ByteOrder.nativeOrder());
        this.mBaseOffset = baseOffset;
        this.mValidator = validator;
    }

    public Struct.DataHeader readDataHeader() {
        this.mValidator.claimMemory((long) this.mBaseOffset, (long) (this.mBaseOffset + 8));
        int size = readInt(0);
        int numFields = readInt(4);
        if (size < 0) {
            throw new DeserializationException("Negative size. Unsigned integers are not valid for java.");
        } else if (numFields < 0) {
            throw new DeserializationException("Negative number of fields. Unsigned integers are not valid for java.");
        } else {
            this.mValidator.claimMemory((long) (this.mBaseOffset + 8), (long) (this.mBaseOffset + size));
            return new Struct.DataHeader(size, numFields);
        }
    }

    public Struct.DataHeader readDataHeaderForPointerArray(int expectedLength) {
        return readDataHeaderForArray(8, expectedLength);
    }

    public void readDataHeaderForMap() {
        Struct.DataHeader si = readDataHeader();
        if (si.size != BindingsHelper.MAP_STRUCT_HEADER.size) {
            throw new DeserializationException("Incorrect header for map. The size is incorrect.");
        } else if (si.numFields != BindingsHelper.MAP_STRUCT_HEADER.numFields) {
            throw new DeserializationException("Incorrect header for map. The number of fields is incorrect.");
        }
    }

    public byte readByte(int offset) {
        validateBufferSize(offset, 1);
        return this.mMessage.getData().get(this.mBaseOffset + offset);
    }

    public boolean readBoolean(int offset, int bit) {
        validateBufferSize(offset, 1);
        if ((readByte(offset) & (1 << bit)) != 0) {
            return true;
        }
        return false;
    }

    public short readShort(int offset) {
        validateBufferSize(offset, 2);
        return this.mMessage.getData().getShort(this.mBaseOffset + offset);
    }

    public int readInt(int offset) {
        validateBufferSize(offset, 4);
        return this.mMessage.getData().getInt(this.mBaseOffset + offset);
    }

    public float readFloat(int offset) {
        validateBufferSize(offset, 4);
        return this.mMessage.getData().getFloat(this.mBaseOffset + offset);
    }

    public long readLong(int offset) {
        validateBufferSize(offset, 8);
        return this.mMessage.getData().getLong(this.mBaseOffset + offset);
    }

    public double readDouble(int offset) {
        validateBufferSize(offset, 8);
        return this.mMessage.getData().getDouble(this.mBaseOffset + offset);
    }

    public Decoder readPointer(int offset, boolean nullable) {
        int basePosition = this.mBaseOffset + offset;
        long pointerOffset = readLong(offset);
        if (pointerOffset != 0) {
            return getDecoderAtPosition((int) (((long) basePosition) + pointerOffset));
        }
        if (nullable) {
            return null;
        }
        throw new DeserializationException("Trying to decode null pointer for a non-nullable type.");
    }

    public boolean[] readBooleans(int offset, int arrayNullability, int expectedLength) {
        Decoder d = readPointer(offset, BindingsHelper.isArrayNullable(arrayNullability));
        if (d == null) {
            return null;
        }
        Struct.DataHeader si = d.readDataHeaderForBooleanArray(expectedLength);
        byte[] bytes = new byte[((si.numFields + 7) / 8)];
        d.mMessage.getData().position(d.mBaseOffset + 8);
        d.mMessage.getData().get(bytes);
        boolean[] result = new boolean[si.numFields];
        for (int i = 0; i < bytes.length; i++) {
            for (int j = 0; j < 8; j++) {
                int booleanIndex = (i * 8) + j;
                if (booleanIndex < result.length) {
                    result[booleanIndex] = (bytes[i] & (1 << j)) != 0;
                }
            }
        }
        return result;
    }

    public byte[] readBytes(int offset, int arrayNullability, int expectedLength) {
        Decoder d = readPointer(offset, BindingsHelper.isArrayNullable(arrayNullability));
        if (d == null) {
            return null;
        }
        byte[] result = new byte[d.readDataHeaderForArray(1, expectedLength).numFields];
        d.mMessage.getData().position(d.mBaseOffset + 8);
        d.mMessage.getData().get(result);
        return result;
    }

    public short[] readShorts(int offset, int arrayNullability, int expectedLength) {
        Decoder d = readPointer(offset, BindingsHelper.isArrayNullable(arrayNullability));
        if (d == null) {
            return null;
        }
        short[] result = new short[d.readDataHeaderForArray(2, expectedLength).numFields];
        d.mMessage.getData().position(d.mBaseOffset + 8);
        d.mMessage.getData().asShortBuffer().get(result);
        return result;
    }

    public int[] readInts(int offset, int arrayNullability, int expectedLength) {
        Decoder d = readPointer(offset, BindingsHelper.isArrayNullable(arrayNullability));
        if (d == null) {
            return null;
        }
        int[] result = new int[d.readDataHeaderForArray(4, expectedLength).numFields];
        d.mMessage.getData().position(d.mBaseOffset + 8);
        d.mMessage.getData().asIntBuffer().get(result);
        return result;
    }

    public float[] readFloats(int offset, int arrayNullability, int expectedLength) {
        Decoder d = readPointer(offset, BindingsHelper.isArrayNullable(arrayNullability));
        if (d == null) {
            return null;
        }
        float[] result = new float[d.readDataHeaderForArray(4, expectedLength).numFields];
        d.mMessage.getData().position(d.mBaseOffset + 8);
        d.mMessage.getData().asFloatBuffer().get(result);
        return result;
    }

    public long[] readLongs(int offset, int arrayNullability, int expectedLength) {
        Decoder d = readPointer(offset, BindingsHelper.isArrayNullable(arrayNullability));
        if (d == null) {
            return null;
        }
        long[] result = new long[d.readDataHeaderForArray(8, expectedLength).numFields];
        d.mMessage.getData().position(d.mBaseOffset + 8);
        d.mMessage.getData().asLongBuffer().get(result);
        return result;
    }

    public double[] readDoubles(int offset, int arrayNullability, int expectedLength) {
        Decoder d = readPointer(offset, BindingsHelper.isArrayNullable(arrayNullability));
        if (d == null) {
            return null;
        }
        double[] result = new double[d.readDataHeaderForArray(8, expectedLength).numFields];
        d.mMessage.getData().position(d.mBaseOffset + 8);
        d.mMessage.getData().asDoubleBuffer().get(result);
        return result;
    }

    public Handle readHandle(int offset, boolean nullable) {
        int index = readInt(offset);
        if (index != -1) {
            this.mValidator.claimHandle(index);
            return (Handle) this.mMessage.getHandles().get(index);
        } else if (nullable) {
            return InvalidHandle.INSTANCE;
        } else {
            throw new DeserializationException("Trying to decode an invalid handle for a non-nullable type.");
        }
    }

    public UntypedHandle readUntypedHandle(int offset, boolean nullable) {
        return readHandle(offset, nullable).toUntypedHandle();
    }

    public DataPipe.ConsumerHandle readConsumerHandle(int offset, boolean nullable) {
        return readUntypedHandle(offset, nullable).toDataPipeConsumerHandle();
    }

    public DataPipe.ProducerHandle readProducerHandle(int offset, boolean nullable) {
        return readUntypedHandle(offset, nullable).toDataPipeProducerHandle();
    }

    public MessagePipeHandle readMessagePipeHandle(int offset, boolean nullable) {
        return readUntypedHandle(offset, nullable).toMessagePipeHandle();
    }

    public SharedBufferHandle readSharedBufferHandle(int offset, boolean nullable) {
        return readUntypedHandle(offset, nullable).toSharedBufferHandle();
    }

    public <P extends Interface.Proxy> P readServiceInterface(int offset, boolean nullable, Interface.Manager<?, P> manager) {
        MessagePipeHandle handle = readMessagePipeHandle(offset, nullable);
        if (!handle.isValid()) {
            return null;
        }
        return manager.attachProxy(handle);
    }

    public <I extends Interface> InterfaceRequest<I> readInterfaceRequest(int offset, boolean nullable) {
        MessagePipeHandle handle = readMessagePipeHandle(offset, nullable);
        if (handle == null) {
            return null;
        }
        return new InterfaceRequest<>(handle);
    }

    public String readString(int offset, boolean nullable) {
        byte[] bytes = readBytes(offset, nullable ? 1 : 0, -1);
        if (bytes == null) {
            return null;
        }
        return new String(bytes, Charset.forName("utf8"));
    }

    public Handle[] readHandles(int offset, int arrayNullability, int expectedLength) {
        Decoder d = readPointer(offset, BindingsHelper.isArrayNullable(arrayNullability));
        if (d == null) {
            return null;
        }
        Handle[] result = new Handle[d.readDataHeaderForArray(4, expectedLength).numFields];
        for (int i = 0; i < result.length; i++) {
            result[i] = d.readHandle((i * 4) + 8, BindingsHelper.isElementNullable(arrayNullability));
        }
        return result;
    }

    public UntypedHandle[] readUntypedHandles(int offset, int arrayNullability, int expectedLength) {
        Decoder d = readPointer(offset, BindingsHelper.isArrayNullable(arrayNullability));
        if (d == null) {
            return null;
        }
        UntypedHandle[] result = new UntypedHandle[d.readDataHeaderForArray(4, expectedLength).numFields];
        for (int i = 0; i < result.length; i++) {
            result[i] = d.readUntypedHandle((i * 4) + 8, BindingsHelper.isElementNullable(arrayNullability));
        }
        return result;
    }

    public DataPipe.ConsumerHandle[] readConsumerHandles(int offset, int arrayNullability, int expectedLength) {
        Decoder d = readPointer(offset, BindingsHelper.isArrayNullable(arrayNullability));
        if (d == null) {
            return null;
        }
        DataPipe.ConsumerHandle[] result = new DataPipe.ConsumerHandle[d.readDataHeaderForArray(4, expectedLength).numFields];
        for (int i = 0; i < result.length; i++) {
            result[i] = d.readConsumerHandle((i * 4) + 8, BindingsHelper.isElementNullable(arrayNullability));
        }
        return result;
    }

    public DataPipe.ProducerHandle[] readProducerHandles(int offset, int arrayNullability, int expectedLength) {
        Decoder d = readPointer(offset, BindingsHelper.isArrayNullable(arrayNullability));
        if (d == null) {
            return null;
        }
        DataPipe.ProducerHandle[] result = new DataPipe.ProducerHandle[d.readDataHeaderForArray(4, expectedLength).numFields];
        for (int i = 0; i < result.length; i++) {
            result[i] = d.readProducerHandle((i * 4) + 8, BindingsHelper.isElementNullable(arrayNullability));
        }
        return result;
    }

    public MessagePipeHandle[] readMessagePipeHandles(int offset, int arrayNullability, int expectedLength) {
        Decoder d = readPointer(offset, BindingsHelper.isArrayNullable(arrayNullability));
        if (d == null) {
            return null;
        }
        MessagePipeHandle[] result = new MessagePipeHandle[d.readDataHeaderForArray(4, expectedLength).numFields];
        for (int i = 0; i < result.length; i++) {
            result[i] = d.readMessagePipeHandle((i * 4) + 8, BindingsHelper.isElementNullable(arrayNullability));
        }
        return result;
    }

    public SharedBufferHandle[] readSharedBufferHandles(int offset, int arrayNullability, int expectedLength) {
        Decoder d = readPointer(offset, BindingsHelper.isArrayNullable(arrayNullability));
        if (d == null) {
            return null;
        }
        SharedBufferHandle[] result = new SharedBufferHandle[d.readDataHeaderForArray(4, expectedLength).numFields];
        for (int i = 0; i < result.length; i++) {
            result[i] = d.readSharedBufferHandle((i * 4) + 8, BindingsHelper.isElementNullable(arrayNullability));
        }
        return result;
    }

    public <S extends Interface, P extends Interface.Proxy> S[] readServiceInterfaces(int offset, int arrayNullability, int expectedLength, Interface.Manager<S, P> manager) {
        Decoder d = readPointer(offset, BindingsHelper.isArrayNullable(arrayNullability));
        if (d == null) {
            return null;
        }
        S[] result = manager.buildArray(d.readDataHeaderForArray(4, expectedLength).numFields);
        for (int i = 0; i < result.length; i++) {
            result[i] = d.readServiceInterface((i * 4) + 8, BindingsHelper.isElementNullable(arrayNullability), manager);
        }
        return result;
    }

    public <I extends Interface> InterfaceRequest<I>[] readInterfaceRequests(int offset, int arrayNullability, int expectedLength) {
        Decoder d = readPointer(offset, BindingsHelper.isArrayNullable(arrayNullability));
        if (d == null) {
            return null;
        }
        InterfaceRequest<I>[] result = new InterfaceRequest[d.readDataHeaderForArray(4, expectedLength).numFields];
        for (int i = 0; i < result.length; i++) {
            result[i] = d.readInterfaceRequest((i * 4) + 8, BindingsHelper.isElementNullable(arrayNullability));
        }
        return result;
    }

    private Decoder getDecoderAtPosition(int offset) {
        return new Decoder(this.mMessage, this.mValidator, offset);
    }

    private Struct.DataHeader readDataHeaderForBooleanArray(int expectedLength) {
        Struct.DataHeader dataHeader = readDataHeader();
        if (dataHeader.size < ((dataHeader.numFields + 7) / 8) + 8) {
            throw new DeserializationException("Array header is incorrect.");
        } else if (expectedLength == -1 || dataHeader.numFields == expectedLength) {
            return dataHeader;
        } else {
            throw new DeserializationException("Incorrect array length. Expected: " + expectedLength + ", but got: " + dataHeader.numFields + ".");
        }
    }

    private Struct.DataHeader readDataHeaderForArray(long elementSize, int expectedLength) {
        Struct.DataHeader dataHeader = readDataHeader();
        if (((long) dataHeader.size) < 8 + (((long) dataHeader.numFields) * elementSize)) {
            throw new DeserializationException("Array header is incorrect.");
        } else if (expectedLength == -1 || dataHeader.numFields == expectedLength) {
            return dataHeader;
        } else {
            throw new DeserializationException("Incorrect array length. Expected: " + expectedLength + ", but got: " + dataHeader.numFields + ".");
        }
    }

    private void validateBufferSize(int offset, int size) {
        if (this.mMessage.getData().limit() < offset + size) {
            throw new DeserializationException("Buffer is smaller than expected.");
        }
    }
}
