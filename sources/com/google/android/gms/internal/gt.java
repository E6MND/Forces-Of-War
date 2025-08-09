package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.common.images.WebImage;
import com.googlecode.eyesfree.braille.display.BrailleInputEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class gt {
    private static final go BD = new go("MetadataUtils");
    private static final String[] CL = {"Z", "+hh", "+hhmm", "+hh:mm"};
    private static final String CM = ("yyyyMMdd'T'HHmmss" + CL[0]);

    public static String a(Calendar calendar) {
        if (calendar == null) {
            BD.b("Calendar object cannot be null", new Object[0]);
            return null;
        }
        String str = CM;
        if (calendar.get(11) == 0 && calendar.get(12) == 0 && calendar.get(13) == 0) {
            str = "yyyyMMdd";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        simpleDateFormat.setTimeZone(calendar.getTimeZone());
        String format = simpleDateFormat.format(calendar.getTime());
        return format.endsWith("+0000") ? format.replace("+0000", CL[0]) : format;
    }

    public static void a(List<WebImage> list, JSONObject jSONObject) {
        try {
            list.clear();
            JSONArray jSONArray = jSONObject.getJSONArray("images");
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                try {
                    list.add(new WebImage(jSONArray.getJSONObject(i)));
                } catch (IllegalArgumentException e) {
                }
            }
        } catch (JSONException e2) {
        }
    }

    public static void a(JSONObject jSONObject, List<WebImage> list) {
        if (list != null && !list.isEmpty()) {
            JSONArray jSONArray = new JSONArray();
            for (WebImage dU : list) {
                jSONArray.put(dU.dU());
            }
            try {
                jSONObject.put("images", jSONArray);
            } catch (JSONException e) {
            }
        }
    }

    public static Calendar aq(String str) {
        if (TextUtils.isEmpty(str)) {
            BD.b("Input string is empty or null", new Object[0]);
            return null;
        }
        String ar = ar(str);
        if (TextUtils.isEmpty(ar)) {
            BD.b("Invalid date format", new Object[0]);
            return null;
        }
        String as = as(str);
        String str2 = "yyyyMMdd";
        if (!TextUtils.isEmpty(as)) {
            ar = ar + "T" + as;
            str2 = as.length() == "HHmmss".length() ? "yyyyMMdd'T'HHmmss" : CM;
        }
        Calendar instance = GregorianCalendar.getInstance();
        try {
            instance.setTime(new SimpleDateFormat(str2).parse(ar));
            return instance;
        } catch (ParseException e) {
            BD.b("Error parsing string: %s", e.getMessage());
            return null;
        }
    }

    private static String ar(String str) {
        if (TextUtils.isEmpty(str)) {
            BD.b("Input string is empty or null", new Object[0]);
            return null;
        }
        try {
            return str.substring(0, "yyyyMMdd".length());
        } catch (IndexOutOfBoundsException e) {
            BD.c("Error extracting the date: %s", e.getMessage());
            return null;
        }
    }

    private static String as(String str) {
        if (TextUtils.isEmpty(str)) {
            BD.b("string is empty or null", new Object[0]);
            return null;
        }
        int indexOf = str.indexOf(84);
        int i = indexOf + 1;
        if (indexOf != "yyyyMMdd".length()) {
            BD.b("T delimeter is not found", new Object[0]);
            return null;
        }
        try {
            String substring = str.substring(i);
            if (substring.length() == "HHmmss".length()) {
                return substring;
            }
            switch (substring.charAt("HHmmss".length())) {
                case BrailleInputEvent.CMD_SELECTION_CUT /*43*/:
                case BrailleInputEvent.CMD_SELECTION_PASTE /*45*/:
                    if (at(substring)) {
                        return substring.replaceAll("([\\+\\-]\\d\\d):(\\d\\d)", "$1$2");
                    }
                    return null;
                case 'Z':
                    if (substring.length() == "HHmmss".length() + CL[0].length()) {
                        return substring.substring(0, substring.length() - 1) + "+0000";
                    }
                    return null;
                default:
                    return null;
            }
        } catch (IndexOutOfBoundsException e) {
            BD.b("Error extracting the time substring: %s", e.getMessage());
            return null;
        }
    }

    private static boolean at(String str) {
        int length = str.length();
        int length2 = "HHmmss".length();
        return length == CL[1].length() + length2 || length == CL[2].length() + length2 || length == length2 + CL[3].length();
    }
}
