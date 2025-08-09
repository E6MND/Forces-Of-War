package com.chartboost.sdk.Libraries;

public enum CBOrientation {
    UNSPECIFIED,
    PORTRAIT,
    LANDSCAPE,
    PORTRAIT_REVERSE,
    LANDSCAPE_REVERSE;
    
    public static final CBOrientation LANDSCAPE_LEFT = null;
    public static final CBOrientation LANDSCAPE_RIGHT = null;
    public static final CBOrientation PORTRAIT_LEFT = null;
    public static final CBOrientation PORTRAIT_RIGHT = null;

    static {
        PORTRAIT_LEFT = PORTRAIT_REVERSE;
        PORTRAIT_RIGHT = PORTRAIT;
        LANDSCAPE_LEFT = LANDSCAPE;
        LANDSCAPE_RIGHT = LANDSCAPE_REVERSE;
    }

    public CBOrientation rotate90() {
        switch (a()[ordinal()]) {
            case 2:
                return LANDSCAPE_LEFT;
            case 3:
                return PORTRAIT_LEFT;
            case 4:
                return LANDSCAPE_RIGHT;
            case 5:
                return PORTRAIT_RIGHT;
            default:
                return UNSPECIFIED;
        }
    }

    public CBOrientation rotate180() {
        return rotate90().rotate90();
    }

    public CBOrientation rotate270() {
        return rotate90().rotate90().rotate90();
    }

    public boolean isPortrait() {
        return this == PORTRAIT || this == PORTRAIT_REVERSE;
    }

    public boolean isLandscape() {
        return this == LANDSCAPE || this == LANDSCAPE_REVERSE;
    }

    public enum Difference {
        ANGLE_0,
        ANGLE_90,
        ANGLE_180,
        ANGLE_270;

        public int getAsInt() {
            switch (a()[ordinal()]) {
                case 2:
                    return 90;
                case 3:
                    return 180;
                case 4:
                    return 270;
                default:
                    return 0;
            }
        }

        public boolean isOdd() {
            return this == ANGLE_90 || this == ANGLE_270;
        }

        public boolean isReverse() {
            return this == ANGLE_180 || this == ANGLE_270;
        }
    }
}
