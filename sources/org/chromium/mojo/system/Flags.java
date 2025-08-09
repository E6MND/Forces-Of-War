package org.chromium.mojo.system;

import org.chromium.mojo.system.Flags;

public abstract class Flags<F extends Flags<F>> {
    private int mFlags;
    private boolean mImmutable = false;

    protected Flags(int flags) {
        this.mFlags = flags;
    }

    public int getFlags() {
        return this.mFlags;
    }

    /* access modifiers changed from: protected */
    public F setFlag(int flag, boolean value) {
        if (this.mImmutable) {
            throw new UnsupportedOperationException("Flags is immutable.");
        }
        if (value) {
            this.mFlags |= flag;
        } else {
            this.mFlags &= flag ^ -1;
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public F immutable() {
        this.mImmutable = true;
        return this;
    }
}
