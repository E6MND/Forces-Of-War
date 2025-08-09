package com.googlecode.eyesfree.braille.selfbraille;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;

public class WriteData implements Parcelable {
    public static final Parcelable.Creator<WriteData> CREATOR = new Parcelable.Creator<WriteData>() {
        public WriteData createFromParcel(Parcel in) {
            return new WriteData(in);
        }

        public WriteData[] newArray(int size) {
            return new WriteData[size];
        }
    };
    private static final String PROP_SELECTION_END = "selectionEnd";
    private static final String PROP_SELECTION_START = "selectionStart";
    private AccessibilityNodeInfo mAccessibilityNodeInfo;
    private Bundle mProperties;
    private CharSequence mText;

    public static WriteData forView(View view) {
        AccessibilityNodeInfo node = AccessibilityNodeInfo.obtain(view);
        WriteData writeData = new WriteData();
        writeData.mAccessibilityNodeInfo = node;
        return writeData;
    }

    public AccessibilityNodeInfo getAccessibilityNodeInfo() {
        return this.mAccessibilityNodeInfo;
    }

    public WriteData setText(CharSequence text) {
        this.mText = text;
        return this;
    }

    public CharSequence getText() {
        return this.mText;
    }

    public WriteData setSelectionStart(int v) {
        writableProperties().putInt(PROP_SELECTION_START, v);
        return this;
    }

    public int getSelectionStart() {
        return this.mProperties.getInt(PROP_SELECTION_START, -1);
    }

    public WriteData setSelectionEnd(int v) {
        writableProperties().putInt(PROP_SELECTION_END, v);
        return this;
    }

    public int getSelectionEnd() {
        return this.mProperties.getInt(PROP_SELECTION_END, -1);
    }

    private Bundle writableProperties() {
        if (this.mProperties == Bundle.EMPTY) {
            this.mProperties = new Bundle();
        }
        return this.mProperties;
    }

    public void validate() throws IllegalStateException {
        if (this.mAccessibilityNodeInfo == null) {
            throw new IllegalStateException("Accessibility node info can't be null");
        }
        int selectionStart = getSelectionStart();
        int selectionEnd = getSelectionEnd();
        if (this.mText == null) {
            if (selectionStart > 0 || selectionEnd > 0) {
                throw new IllegalStateException("Selection can't be set without text");
            }
        } else if (selectionStart >= 0 || selectionEnd < 0) {
            int textLength = this.mText.length();
            if (selectionStart > textLength || selectionEnd > textLength) {
                throw new IllegalStateException("Selection out of bounds");
            }
        } else {
            throw new IllegalStateException("Selection end without start");
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        this.mAccessibilityNodeInfo.writeToParcel(out, flags);
        this.mAccessibilityNodeInfo = null;
        out.writeString(this.mText.toString());
        out.writeBundle(this.mProperties);
    }

    private WriteData() {
        this.mProperties = Bundle.EMPTY;
    }

    private WriteData(Parcel in) {
        this.mProperties = Bundle.EMPTY;
        this.mAccessibilityNodeInfo = (AccessibilityNodeInfo) AccessibilityNodeInfo.CREATOR.createFromParcel(in);
        this.mText = in.readString();
        this.mProperties = in.readBundle();
    }
}
