package com.uken.android.util;

import bolts.MeasurementEvent;
import com.uken.android.common.Utils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: UkenEventLog */
class UkenEventLogMessage {
    private static int idCounter = 1;
    private HashMap<String, String> context_data;
    private HashMap<String, String> event_data;
    private String event_name;
    private String event_time;
    private String event_type;
    private int id;
    private String source = "native";
    private String user_id;
    private String version = "1.0";

    public JSONObject toJSON() {
        JSONObject message = new JSONObject();
        try {
            message.put("version", this.version);
            message.put("source", this.source);
            message.put("event_type", this.event_type);
            message.put(MeasurementEvent.MEASUREMENT_EVENT_NAME_KEY, this.event_name);
            message.put("event_data", new JSONObject(this.event_data));
            message.put("context_data", new JSONObject(this.context_data));
            message.put("event_time", this.event_time);
        } catch (JSONException e) {
            Utils.logCrashlytics(e);
        }
        return message;
    }

    UkenEventLogMessage(String event_type2, String event_name2, HashMap<String, String> event_data2, HashMap<String, String> context_data2) {
        this.event_type = event_type2;
        this.event_name = event_name2;
        this.event_data = event_data2 == null ? new HashMap<>() : event_data2;
        this.context_data = context_data2;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        this.event_time = df.format(new Date());
        int i = idCounter;
        idCounter = i + 1;
        this.id = i;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (this.id != ((UkenEventLogMessage) o).id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.id;
    }
}
