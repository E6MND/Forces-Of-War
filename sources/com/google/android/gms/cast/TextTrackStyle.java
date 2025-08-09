package com.google.android.gms.cast;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.accessibility.CaptioningManager;
import com.google.android.gms.internal.gj;
import com.google.android.gms.internal.hl;
import com.google.android.gms.internal.io;
import com.google.android.gms.internal.iq;
import org.json.JSONException;
import org.json.JSONObject;

public final class TextTrackStyle {
    public static final int COLOR_UNSPECIFIED = 0;
    public static final float DEFAULT_FONT_SCALE = 1.0f;
    public static final int EDGE_TYPE_DEPRESSED = 4;
    public static final int EDGE_TYPE_DROP_SHADOW = 2;
    public static final int EDGE_TYPE_NONE = 0;
    public static final int EDGE_TYPE_OUTLINE = 1;
    public static final int EDGE_TYPE_RAISED = 3;
    public static final int EDGE_TYPE_UNSPECIFIED = -1;
    public static final int FONT_FAMILY_CASUAL = 4;
    public static final int FONT_FAMILY_CURSIVE = 5;
    public static final int FONT_FAMILY_MONOSPACED_SANS_SERIF = 1;
    public static final int FONT_FAMILY_MONOSPACED_SERIF = 3;
    public static final int FONT_FAMILY_SANS_SERIF = 0;
    public static final int FONT_FAMILY_SERIF = 2;
    public static final int FONT_FAMILY_SMALL_CAPITALS = 6;
    public static final int FONT_FAMILY_UNSPECIFIED = -1;
    public static final int FONT_STYLE_BOLD = 1;
    public static final int FONT_STYLE_BOLD_ITALIC = 3;
    public static final int FONT_STYLE_ITALIC = 2;
    public static final int FONT_STYLE_NORMAL = 0;
    public static final int FONT_STYLE_UNSPECIFIED = -1;
    public static final int WINDOW_TYPE_NONE = 0;
    public static final int WINDOW_TYPE_NORMAL = 1;
    public static final int WINDOW_TYPE_ROUNDED = 2;
    public static final int WINDOW_TYPE_UNSPECIFIED = -1;
    private JSONObject Ax;
    private float Bp;
    private int Bq;
    private int Br;
    private int Bs;
    private int Bt;
    private int Bu;
    private int Bv;
    private String Bw;
    private int Bx;
    private int By;
    private int ta;

    public TextTrackStyle() {
        clear();
    }

    private int ah(String str) {
        if (str == null || str.length() != 9 || str.charAt(0) != '#') {
            return 0;
        }
        try {
            return Color.argb(Integer.parseInt(str.substring(7, 9), 16), Integer.parseInt(str.substring(1, 3), 16), Integer.parseInt(str.substring(3, 5), 16), Integer.parseInt(str.substring(5, 7), 16));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void clear() {
        this.Bp = 1.0f;
        this.Bq = 0;
        this.ta = 0;
        this.Br = -1;
        this.Bs = 0;
        this.Bt = -1;
        this.Bu = 0;
        this.Bv = 0;
        this.Bw = null;
        this.Bx = -1;
        this.By = -1;
        this.Ax = null;
    }

    public static TextTrackStyle fromSystemSettings(Context context) {
        TextTrackStyle textTrackStyle = new TextTrackStyle();
        if (!iq.gd()) {
            return textTrackStyle;
        }
        CaptioningManager captioningManager = (CaptioningManager) context.getSystemService("captioning");
        textTrackStyle.setFontScale(captioningManager.getFontScale());
        CaptioningManager.CaptionStyle userStyle = captioningManager.getUserStyle();
        textTrackStyle.setBackgroundColor(userStyle.backgroundColor);
        textTrackStyle.setForegroundColor(userStyle.foregroundColor);
        switch (userStyle.edgeType) {
            case 1:
                textTrackStyle.setEdgeType(1);
                break;
            case 2:
                textTrackStyle.setEdgeType(2);
                break;
            default:
                textTrackStyle.setEdgeType(0);
                break;
        }
        textTrackStyle.setEdgeColor(userStyle.edgeColor);
        Typeface typeface = userStyle.getTypeface();
        if (typeface != null) {
            if (Typeface.MONOSPACE.equals(typeface)) {
                textTrackStyle.setFontGenericFamily(1);
            } else if (Typeface.SANS_SERIF.equals(typeface)) {
                textTrackStyle.setFontGenericFamily(0);
            } else if (Typeface.SERIF.equals(typeface)) {
                textTrackStyle.setFontGenericFamily(2);
            } else {
                textTrackStyle.setFontGenericFamily(0);
            }
            boolean isBold = typeface.isBold();
            boolean isItalic = typeface.isItalic();
            if (isBold && isItalic) {
                textTrackStyle.setFontStyle(3);
            } else if (isBold) {
                textTrackStyle.setFontStyle(1);
            } else if (isItalic) {
                textTrackStyle.setFontStyle(2);
            } else {
                textTrackStyle.setFontStyle(0);
            }
        }
        return textTrackStyle;
    }

    private String o(int i) {
        return String.format("#%02X%02X%02X%02X", new Object[]{Integer.valueOf(Color.red(i)), Integer.valueOf(Color.green(i)), Integer.valueOf(Color.blue(i)), Integer.valueOf(Color.alpha(i))});
    }

    public void b(JSONObject jSONObject) throws JSONException {
        clear();
        this.Bp = (float) jSONObject.optDouble("fontScale", 1.0d);
        this.Bq = ah(jSONObject.optString("foregroundColor"));
        this.ta = ah(jSONObject.optString("backgroundColor"));
        if (jSONObject.has("edgeType")) {
            String string = jSONObject.getString("edgeType");
            if ("NONE".equals(string)) {
                this.Br = 0;
            } else if ("OUTLINE".equals(string)) {
                this.Br = 1;
            } else if ("DROP_SHADOW".equals(string)) {
                this.Br = 2;
            } else if ("RAISED".equals(string)) {
                this.Br = 3;
            } else if ("DEPRESSED".equals(string)) {
                this.Br = 4;
            }
        }
        this.Bs = ah(jSONObject.optString("edgeColor"));
        if (jSONObject.has("windowType")) {
            String string2 = jSONObject.getString("windowType");
            if ("NONE".equals(string2)) {
                this.Bt = 0;
            } else if ("NORMAL".equals(string2)) {
                this.Bt = 1;
            } else if ("ROUNDED_CORNERS".equals(string2)) {
                this.Bt = 2;
            }
        }
        this.Bu = ah(jSONObject.optString("windowColor"));
        if (this.Bt == 2) {
            this.Bv = jSONObject.optInt("windowRoundedCornerRadius", 0);
        }
        this.Bw = jSONObject.optString("fontFamily", (String) null);
        if (jSONObject.has("fontGenericFamily")) {
            String string3 = jSONObject.getString("fontGenericFamily");
            if ("SANS_SERIF".equals(string3)) {
                this.Bx = 0;
            } else if ("MONOSPACED_SANS_SERIF".equals(string3)) {
                this.Bx = 1;
            } else if ("SERIF".equals(string3)) {
                this.Bx = 2;
            } else if ("MONOSPACED_SERIF".equals(string3)) {
                this.Bx = 3;
            } else if ("CASUAL".equals(string3)) {
                this.Bx = 4;
            } else if ("CURSIVE".equals(string3)) {
                this.Bx = 5;
            } else if ("SMALL_CAPITALS".equals(string3)) {
                this.Bx = 6;
            }
        }
        if (jSONObject.has("fontStyle")) {
            String string4 = jSONObject.getString("fontStyle");
            if ("NORMAL".equals(string4)) {
                this.By = 0;
            } else if ("BOLD".equals(string4)) {
                this.By = 1;
            } else if ("ITALIC".equals(string4)) {
                this.By = 2;
            } else if ("BOLD_ITALIC".equals(string4)) {
                this.By = 3;
            }
        }
        this.Ax = jSONObject.optJSONObject("customData");
    }

    public JSONObject dU() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("fontScale", (double) this.Bp);
            if (this.Bq != 0) {
                jSONObject.put("foregroundColor", o(this.Bq));
            }
            if (this.ta != 0) {
                jSONObject.put("backgroundColor", o(this.ta));
            }
            switch (this.Br) {
                case 0:
                    jSONObject.put("edgeType", "NONE");
                    break;
                case 1:
                    jSONObject.put("edgeType", "OUTLINE");
                    break;
                case 2:
                    jSONObject.put("edgeType", "DROP_SHADOW");
                    break;
                case 3:
                    jSONObject.put("edgeType", "RAISED");
                    break;
                case 4:
                    jSONObject.put("edgeType", "DEPRESSED");
                    break;
            }
            if (this.Bs != 0) {
                jSONObject.put("edgeColor", o(this.Bs));
            }
            switch (this.Bt) {
                case 0:
                    jSONObject.put("windowType", "NONE");
                    break;
                case 1:
                    jSONObject.put("windowType", "NORMAL");
                    break;
                case 2:
                    jSONObject.put("windowType", "ROUNDED_CORNERS");
                    break;
            }
            if (this.Bu != 0) {
                jSONObject.put("windowColor", o(this.Bu));
            }
            if (this.Bt == 2) {
                jSONObject.put("windowRoundedCornerRadius", this.Bv);
            }
            if (this.Bw != null) {
                jSONObject.put("fontFamily", this.Bw);
            }
            switch (this.Bx) {
                case 0:
                    jSONObject.put("fontGenericFamily", "SANS_SERIF");
                    break;
                case 1:
                    jSONObject.put("fontGenericFamily", "MONOSPACED_SANS_SERIF");
                    break;
                case 2:
                    jSONObject.put("fontGenericFamily", "SERIF");
                    break;
                case 3:
                    jSONObject.put("fontGenericFamily", "MONOSPACED_SERIF");
                    break;
                case 4:
                    jSONObject.put("fontGenericFamily", "CASUAL");
                    break;
                case 5:
                    jSONObject.put("fontGenericFamily", "CURSIVE");
                    break;
                case 6:
                    jSONObject.put("fontGenericFamily", "SMALL_CAPITALS");
                    break;
            }
            switch (this.By) {
                case 0:
                    jSONObject.put("fontStyle", "NORMAL");
                    break;
                case 1:
                    jSONObject.put("fontStyle", "BOLD");
                    break;
                case 2:
                    jSONObject.put("fontStyle", "ITALIC");
                    break;
                case 3:
                    jSONObject.put("fontStyle", "BOLD_ITALIC");
                    break;
            }
            if (this.Ax != null) {
                jSONObject.put("customData", this.Ax);
            }
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    public boolean equals(Object other) {
        boolean z = true;
        if (this == other) {
            return true;
        }
        if (!(other instanceof TextTrackStyle)) {
            return false;
        }
        TextTrackStyle textTrackStyle = (TextTrackStyle) other;
        if ((this.Ax == null) != (textTrackStyle.Ax == null)) {
            return false;
        }
        if (this.Ax != null && textTrackStyle.Ax != null && !io.d(this.Ax, textTrackStyle.Ax)) {
            return false;
        }
        if (!(this.Bp == textTrackStyle.Bp && this.Bq == textTrackStyle.Bq && this.ta == textTrackStyle.ta && this.Br == textTrackStyle.Br && this.Bs == textTrackStyle.Bs && this.Bt == textTrackStyle.Bt && this.Bv == textTrackStyle.Bv && gj.a(this.Bw, textTrackStyle.Bw) && this.Bx == textTrackStyle.Bx && this.By == textTrackStyle.By)) {
            z = false;
        }
        return z;
    }

    public int getBackgroundColor() {
        return this.ta;
    }

    public JSONObject getCustomData() {
        return this.Ax;
    }

    public int getEdgeColor() {
        return this.Bs;
    }

    public int getEdgeType() {
        return this.Br;
    }

    public String getFontFamily() {
        return this.Bw;
    }

    public int getFontGenericFamily() {
        return this.Bx;
    }

    public float getFontScale() {
        return this.Bp;
    }

    public int getFontStyle() {
        return this.By;
    }

    public int getForegroundColor() {
        return this.Bq;
    }

    public int getWindowColor() {
        return this.Bu;
    }

    public int getWindowCornerRadius() {
        return this.Bv;
    }

    public int getWindowType() {
        return this.Bt;
    }

    public int hashCode() {
        return hl.hashCode(Float.valueOf(this.Bp), Integer.valueOf(this.Bq), Integer.valueOf(this.ta), Integer.valueOf(this.Br), Integer.valueOf(this.Bs), Integer.valueOf(this.Bt), Integer.valueOf(this.Bu), Integer.valueOf(this.Bv), this.Bw, Integer.valueOf(this.Bx), Integer.valueOf(this.By), this.Ax);
    }

    public void setBackgroundColor(int backgroundColor) {
        this.ta = backgroundColor;
    }

    public void setCustomData(JSONObject customData) {
        this.Ax = customData;
    }

    public void setEdgeColor(int edgeColor) {
        this.Bs = edgeColor;
    }

    public void setEdgeType(int edgeType) {
        if (edgeType < 0 || edgeType > 4) {
            throw new IllegalArgumentException("invalid edgeType");
        }
        this.Br = edgeType;
    }

    public void setFontFamily(String fontFamily) {
        this.Bw = fontFamily;
    }

    public void setFontGenericFamily(int fontGenericFamily) {
        if (fontGenericFamily < 0 || fontGenericFamily > 6) {
            throw new IllegalArgumentException("invalid fontGenericFamily");
        }
        this.Bx = fontGenericFamily;
    }

    public void setFontScale(float fontScale) {
        this.Bp = fontScale;
    }

    public void setFontStyle(int fontStyle) {
        if (fontStyle < 0 || fontStyle > 3) {
            throw new IllegalArgumentException("invalid fontStyle");
        }
        this.By = fontStyle;
    }

    public void setForegroundColor(int foregroundColor) {
        this.Bq = foregroundColor;
    }

    public void setWindowColor(int windowColor) {
        this.Bu = windowColor;
    }

    public void setWindowCornerRadius(int windowCornerRadius) {
        if (windowCornerRadius < 0) {
            throw new IllegalArgumentException("invalid windowCornerRadius");
        }
        this.Bv = windowCornerRadius;
    }

    public void setWindowType(int windowType) {
        if (windowType < 0 || windowType > 2) {
            throw new IllegalArgumentException("invalid windowType");
        }
        this.Bt = windowType;
    }
}
