package com.googlecode.eyesfree.braille.display;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import java.util.HashMap;

public class BrailleInputEvent implements Parcelable {
    public static final int ARGUMENT_DOTS = 1;
    public static final int ARGUMENT_NONE = 0;
    public static final int ARGUMENT_POSITION = 2;
    public static final int CMD_ACTIVATE_CURRENT = 20;
    public static final int CMD_BRAILLE_KEY = 60;
    public static final int CMD_GLOBAL_BACK = 90;
    public static final int CMD_GLOBAL_HOME = 91;
    public static final int CMD_GLOBAL_NOTIFICATIONS = 93;
    public static final int CMD_GLOBAL_RECENTS = 92;
    public static final int CMD_HELP = 100;
    public static final int CMD_KEY_DEL = 71;
    public static final int CMD_KEY_ENTER = 70;
    public static final int CMD_KEY_FORWARD_DEL = 72;
    private static final SparseArray<String> CMD_NAMES = new SparseArray<>();
    public static final int CMD_NAV_BOTTOM = 8;
    public static final int CMD_NAV_ITEM_NEXT = 4;
    public static final int CMD_NAV_ITEM_PREVIOUS = 3;
    public static final int CMD_NAV_LINE_NEXT = 2;
    public static final int CMD_NAV_LINE_PREVIOUS = 1;
    public static final int CMD_NAV_PAN_LEFT = 5;
    public static final int CMD_NAV_PAN_RIGHT = 6;
    public static final int CMD_NAV_TOP = 7;
    public static final int CMD_NONE = -1;
    public static final int CMD_ROUTE = 50;
    public static final int CMD_SCROLL_BACKWARD = 30;
    public static final int CMD_SCROLL_FORWARD = 31;
    public static final int CMD_SELECTION_COPY = 44;
    public static final int CMD_SELECTION_CUT = 43;
    public static final int CMD_SELECTION_END = 41;
    public static final int CMD_SELECTION_PASTE = 45;
    public static final int CMD_SELECTION_SELECT_ALL = 42;
    public static final int CMD_SELECTION_START = 40;
    public static final Parcelable.Creator<BrailleInputEvent> CREATOR = new Parcelable.Creator<BrailleInputEvent>() {
        public BrailleInputEvent createFromParcel(Parcel in) {
            return new BrailleInputEvent(in);
        }

        public BrailleInputEvent[] newArray(int size) {
            return new BrailleInputEvent[size];
        }
    };
    private static final HashMap<String, Integer> NAMES_TO_CMDS = new HashMap<>();
    private final int mArgument;
    private final int mCommand;
    private final long mEventTime;

    static {
        CMD_NAMES.append(1, "CMD_NAV_LINE_PREVIOUS");
        CMD_NAMES.append(2, "CMD_NAV_LINE_NEXT");
        CMD_NAMES.append(3, "CMD_NAV_ITEM_PREVIOUS");
        CMD_NAMES.append(4, "CMD_NAV_ITEM_NEXT");
        CMD_NAMES.append(5, "CMD_NAV_PAN_LEFT");
        CMD_NAMES.append(6, "CMD_NAV_PAN_RIGHT");
        CMD_NAMES.append(7, "CMD_NAV_TOP");
        CMD_NAMES.append(8, "CMD_NAV_BOTTOM");
        CMD_NAMES.append(20, "CMD_ACTIVATE_CURRENT");
        CMD_NAMES.append(30, "CMD_SCROLL_BACKWARD");
        CMD_NAMES.append(31, "CMD_SCROLL_FORWARD");
        CMD_NAMES.append(40, "CMD_SELECTION_START");
        CMD_NAMES.append(41, "CMD_SELECTION_END");
        CMD_NAMES.append(42, "CMD_SELECTION_SELECT_ALL");
        CMD_NAMES.append(43, "CMD_SELECTION_CUT");
        CMD_NAMES.append(44, "CMD_SELECTION_COPY");
        CMD_NAMES.append(45, "CMD_SELECTION_PASTE");
        CMD_NAMES.append(50, "CMD_ROUTE");
        CMD_NAMES.append(60, "CMD_BRAILLE_KEY");
        CMD_NAMES.append(70, "CMD_KEY_ENTER");
        CMD_NAMES.append(71, "CMD_KEY_DEL");
        CMD_NAMES.append(72, "CMD_KEY_FORWARD_DEL");
        CMD_NAMES.append(90, "CMD_GLOBAL_BACK");
        CMD_NAMES.append(91, "CMD_GLOBAL_HOME");
        CMD_NAMES.append(92, "CMD_GLOBAL_RECENTS");
        CMD_NAMES.append(93, "CMD_GLOBAL_NOTIFICATIONS");
        CMD_NAMES.append(100, "CMD_HELP");
        for (int i = 0; i < CMD_NAMES.size(); i++) {
            NAMES_TO_CMDS.put(CMD_NAMES.valueAt(i), Integer.valueOf(CMD_NAMES.keyAt(i)));
        }
    }

    public BrailleInputEvent(int command, int argument, long eventTime) {
        this.mCommand = command;
        this.mArgument = argument;
        this.mEventTime = eventTime;
    }

    public int getCommand() {
        return this.mCommand;
    }

    public int getArgument() {
        return this.mArgument;
    }

    public long getEventTime() {
        return this.mEventTime;
    }

    public static String commandToString(int command) {
        String ret = CMD_NAMES.get(command);
        return ret != null ? ret : "(unknown)";
    }

    public static int stringToCommand(String commandName) {
        Integer command = NAMES_TO_CMDS.get(commandName);
        if (command == null) {
            return -1;
        }
        return command.intValue();
    }

    public static int argumentType(int command) {
        switch (command) {
            case CMD_SELECTION_START /*40*/:
            case CMD_SELECTION_END /*41*/:
            case 50:
                return 2;
            case CMD_BRAILLE_KEY /*60*/:
                return 1;
            default:
                return 0;
        }
    }

    public String toString() {
        return "BrailleInputEvent {" + "amd=" + commandToString(this.mCommand) + ", arg=" + this.mArgument + "}";
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(this.mCommand);
        out.writeInt(this.mArgument);
        out.writeLong(this.mEventTime);
    }

    private BrailleInputEvent(Parcel in) {
        this.mCommand = in.readInt();
        this.mArgument = in.readInt();
        this.mEventTime = in.readLong();
    }
}
