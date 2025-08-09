package org.chromium.mojo.bindings;

import java.nio.ByteBuffer;
import org.chromium.mojo.bindings.Struct;

public class MessageHeader {
    static final /* synthetic */ boolean $assertionsDisabled = (!MessageHeader.class.desiredAssertionStatus());
    private static final int FLAGS_OFFSET = 12;
    public static final int MESSAGE_EXPECTS_RESPONSE_FLAG = 1;
    public static final int MESSAGE_IS_RESPONSE_FLAG = 2;
    private static final int MESSAGE_WITH_REQUEST_ID_NUM_FIELDS = 3;
    private static final int MESSAGE_WITH_REQUEST_ID_SIZE = 24;
    private static final Struct.DataHeader MESSAGE_WITH_REQUEST_ID_STRUCT_INFO = new Struct.DataHeader(MESSAGE_WITH_REQUEST_ID_SIZE, 3);
    public static final int NO_FLAG = 0;
    private static final int REQUEST_ID_OFFSET = 16;
    private static final int SIMPLE_MESSAGE_NUM_FIELDS = 2;
    private static final int SIMPLE_MESSAGE_SIZE = 16;
    private static final Struct.DataHeader SIMPLE_MESSAGE_STRUCT_INFO = new Struct.DataHeader(16, 2);
    private static final int TYPE_OFFSET = 8;
    private final Struct.DataHeader mDataHeader;
    private final int mFlags;
    private long mRequestId;
    private final int mType;

    public MessageHeader(int type) {
        this.mDataHeader = SIMPLE_MESSAGE_STRUCT_INFO;
        this.mType = type;
        this.mFlags = 0;
        this.mRequestId = 0;
    }

    public MessageHeader(int type, int flags, long requestId) {
        if ($assertionsDisabled || mustHaveRequestId(flags)) {
            this.mDataHeader = MESSAGE_WITH_REQUEST_ID_STRUCT_INFO;
            this.mType = type;
            this.mFlags = flags;
            this.mRequestId = requestId;
            return;
        }
        throw new AssertionError();
    }

    MessageHeader(Message message) {
        Decoder decoder = new Decoder(message);
        this.mDataHeader = decoder.readDataHeader();
        validateDataHeader(this.mDataHeader);
        this.mType = decoder.readInt(8);
        this.mFlags = decoder.readInt(12);
        if (!mustHaveRequestId(this.mFlags)) {
            this.mRequestId = 0;
        } else if (this.mDataHeader.size < MESSAGE_WITH_REQUEST_ID_SIZE) {
            throw new DeserializationException("Incorrect message size, expecting at least 24 for a message with a request identifier, but got: " + this.mDataHeader.size);
        } else {
            this.mRequestId = decoder.readLong(16);
        }
    }

    public int getSize() {
        return this.mDataHeader.size;
    }

    public int getType() {
        return this.mType;
    }

    public int getFlags() {
        return this.mFlags;
    }

    public boolean hasFlag(int flag) {
        return (this.mFlags & flag) == flag;
    }

    public boolean hasRequestId() {
        return mustHaveRequestId(this.mFlags);
    }

    public long getRequestId() {
        if ($assertionsDisabled || hasRequestId()) {
            return this.mRequestId;
        }
        throw new AssertionError();
    }

    public void encode(Encoder encoder) {
        encoder.encode(this.mDataHeader);
        encoder.encode(getType(), 8);
        encoder.encode(getFlags(), 12);
        if (hasRequestId()) {
            encoder.encode(getRequestId(), 16);
        }
    }

    public boolean validateHeader(int expectedFlags) {
        return (getFlags() & 3) == expectedFlags;
    }

    public boolean validateHeader(int expectedType, int expectedFlags) {
        return getType() == expectedType && validateHeader(expectedFlags);
    }

    public int hashCode() {
        return (((((((this.mDataHeader == null ? 0 : this.mDataHeader.hashCode()) + 31) * 31) + this.mFlags) * 31) + ((int) (this.mRequestId ^ (this.mRequestId >>> 32)))) * 31) + this.mType;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (getClass() != object.getClass()) {
            return false;
        }
        MessageHeader other = (MessageHeader) object;
        if (BindingsHelper.equals(this.mDataHeader, other.mDataHeader) && this.mFlags == other.mFlags && this.mRequestId == other.mRequestId && this.mType == other.mType) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void setRequestId(ByteBuffer buffer, long requestId) {
        if ($assertionsDisabled || mustHaveRequestId(buffer.getInt(12))) {
            buffer.putLong(16, requestId);
            this.mRequestId = requestId;
            return;
        }
        throw new AssertionError();
    }

    private static boolean mustHaveRequestId(int flags) {
        return (flags & 3) != 0;
    }

    private static void validateDataHeader(Struct.DataHeader dataHeader) {
        if (dataHeader.numFields < 2) {
            throw new DeserializationException("Incorrect number of fields, expecting at least 2, but got: " + dataHeader.numFields);
        } else if (dataHeader.size < 16) {
            throw new DeserializationException("Incorrect message size, expecting at least 16, but got: " + dataHeader.size);
        } else if (dataHeader.numFields == 2 && dataHeader.size != 16) {
            throw new DeserializationException("Incorrect message size for a message with 2 fields, expecting 16, but got: " + dataHeader.size);
        } else if (dataHeader.numFields == 3 && dataHeader.size != MESSAGE_WITH_REQUEST_ID_SIZE) {
            throw new DeserializationException("Incorrect message size for a message with 3 fields, expecting 24, but got: " + dataHeader.size);
        }
    }
}
