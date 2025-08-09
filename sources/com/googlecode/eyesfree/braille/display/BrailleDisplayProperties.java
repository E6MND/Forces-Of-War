package com.googlecode.eyesfree.braille.display;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BrailleDisplayProperties implements Parcelable {
    public static final Parcelable.Creator<BrailleDisplayProperties> CREATOR = new Parcelable.Creator<BrailleDisplayProperties>() {
        public BrailleDisplayProperties createFromParcel(Parcel in) {
            return new BrailleDisplayProperties(in);
        }

        public BrailleDisplayProperties[] newArray(int size) {
            return new BrailleDisplayProperties[size];
        }
    };
    private final Map<String, String> mFriendlyKeyNames;
    private final BrailleKeyBinding[] mKeyBindings;
    private final int mNumStatusCells;
    private final int mNumTextCells;

    public BrailleDisplayProperties(int numTextCells, int numStatusCells, BrailleKeyBinding[] keyBindings, Map<String, String> friendlyKeyNames) {
        this.mNumTextCells = numTextCells;
        this.mNumStatusCells = numStatusCells;
        this.mKeyBindings = keyBindings;
        this.mFriendlyKeyNames = friendlyKeyNames;
    }

    public int getNumTextCells() {
        return this.mNumTextCells;
    }

    public int getNumStatusCells() {
        return this.mNumStatusCells;
    }

    public BrailleKeyBinding[] getKeyBindings() {
        return this.mKeyBindings;
    }

    public Map<String, String> getFriendlyKeyNames() {
        return this.mFriendlyKeyNames;
    }

    public String toString() {
        return String.format("BrailleDisplayProperties [numTextCells: %d, numStatusCells: %d, keyBindings: %d]", new Object[]{Integer.valueOf(this.mNumTextCells), Integer.valueOf(this.mNumStatusCells), Integer.valueOf(this.mKeyBindings.length)});
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(this.mNumTextCells);
        out.writeInt(this.mNumStatusCells);
        out.writeTypedArray(this.mKeyBindings, flags);
        out.writeInt(this.mFriendlyKeyNames.size());
        for (Map.Entry<String, String> entry : this.mFriendlyKeyNames.entrySet()) {
            out.writeString(entry.getKey());
            out.writeString(entry.getValue());
        }
    }

    private BrailleDisplayProperties(Parcel in) {
        this.mNumTextCells = in.readInt();
        this.mNumStatusCells = in.readInt();
        this.mKeyBindings = (BrailleKeyBinding[]) in.createTypedArray(BrailleKeyBinding.CREATOR);
        int size = in.readInt();
        Map<String, String> names = new HashMap<>(size);
        for (int i = 0; i < size; i++) {
            names.put(in.readString(), in.readString());
        }
        this.mFriendlyKeyNames = Collections.unmodifiableMap(names);
    }
}
