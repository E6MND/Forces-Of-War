package org.chromium.mojo.bindings;

import org.chromium.mojo.system.Core;

public abstract class Struct {
    protected final int mEncodedBaseSize;

    /* access modifiers changed from: protected */
    public abstract void encode(Encoder encoder);

    public static final class DataHeader {
        public static final int HEADER_SIZE = 8;
        public static final int NUM_FIELDS_OFFSET = 4;
        public static final int SIZE_OFFSET = 0;
        public final int numFields;
        public final int size;

        public DataHeader(int size2, int numFields2) {
            this.size = size2;
            this.numFields = numFields2;
        }

        public int hashCode() {
            return ((this.numFields + 31) * 31) + this.size;
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
            DataHeader other = (DataHeader) object;
            if (this.numFields == other.numFields && this.size == other.size) {
                return true;
            }
            return false;
        }
    }

    protected Struct(int encodedBaseSize) {
        this.mEncodedBaseSize = encodedBaseSize;
    }

    public Message serialize(Core core) {
        Encoder encoder = new Encoder(core, this.mEncodedBaseSize);
        encode(encoder);
        return encoder.getMessage();
    }

    public ServiceMessage serializeWithHeader(Core core, MessageHeader header) {
        Encoder encoder = new Encoder(core, this.mEncodedBaseSize + header.getSize());
        header.encode(encoder);
        encode(encoder);
        return new ServiceMessage(encoder.getMessage(), header);
    }
}
