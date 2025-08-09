package com.googlecode.eyesfree.braille.display;

import android.os.Parcel;
import android.os.Parcelable;

public class BrailleKeyBinding implements Parcelable {
    public static final Parcelable.Creator<BrailleKeyBinding> CREATOR = new Parcelable.Creator<BrailleKeyBinding>() {
        public BrailleKeyBinding createFromParcel(Parcel in) {
            return new BrailleKeyBinding(in);
        }

        public BrailleKeyBinding[] newArray(int size) {
            return new BrailleKeyBinding[size];
        }
    };
    private int mCommand;
    private String[] mKeyNames;

    public BrailleKeyBinding() {
    }

    public BrailleKeyBinding(int command, String[] keyNames) {
        this.mCommand = command;
        this.mKeyNames = keyNames;
    }

    public BrailleKeyBinding setCommand(int command) {
        this.mCommand = command;
        return this;
    }

    public BrailleKeyBinding setKeyNames(String[] keyNames) {
        this.mKeyNames = keyNames;
        return this;
    }

    public int getCommand() {
        return this.mCommand;
    }

    public String[] getKeyNames() {
        return this.mKeyNames;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(this.mCommand);
        out.writeStringArray(this.mKeyNames);
    }

    private BrailleKeyBinding(Parcel in) {
        this.mCommand = in.readInt();
        this.mKeyNames = in.createStringArray();
    }
}
