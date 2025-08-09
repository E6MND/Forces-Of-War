package org.xwalk.core.internal.extension.api.messaging;

import android.database.Cursor;
import com.facebook.AppEventsConstants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class MessagingHelpers {
    private static String buildSqlClause(boolean hasAnd, String condition, String column) {
        return (hasAnd ? " AND " : "") + String.format(condition, new Object[]{column});
    }

    public static String convertJsDateString2Long(String date) {
        long time = 0;
        try {
            time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date.replace('T', ' ').replace('Z', ' ')).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return String.valueOf(time);
    }

    public static String convertDateLong2String(long time) {
        if (time <= 0) {
            return "";
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(time));
    }

    public static Object[] buildSqlFilterString(JSONObject filter) {
        String filterString = "";
        ArrayList<String> argsStringList = new ArrayList<>();
        boolean hasAnd = false;
        try {
            if (filter.has("startDate")) {
                filterString = filterString + buildSqlClause(false, "%s >= ?", MessagingSmsConsts.DATE);
                argsStringList.add(convertJsDateString2Long(filter.getString("startDate")));
                hasAnd = true;
            }
            if (filter.has("endDate")) {
                filterString = filterString + buildSqlClause(hasAnd, "%s <= ?", MessagingSmsConsts.DATE);
                argsStringList.add(convertJsDateString2Long(filter.getString("endDate")));
                hasAnd = true;
            }
            if (filter.has("from")) {
                filterString = filterString + buildSqlClause(hasAnd, "%s = ?", MessagingSmsConsts.ADDRESS);
                argsStringList.add(filter.getString("from"));
                hasAnd = true;
            }
            String msgType = "sms";
            if (filter.has(MessagingSmsConsts.TYPE)) {
                msgType = filter.getString(MessagingSmsConsts.TYPE);
            }
            if (filter.has("state") && msgType.equals("sms")) {
                filterString = filterString + buildSqlClause(hasAnd, "%s = ?", MessagingSmsConsts.TYPE);
                argsStringList.add(String.valueOf(MessagingSmsConstMaps.smsStateDictS2I.get(filter.getString("state"))));
                hasAnd = true;
            }
            if (filter.has(MessagingSmsConsts.READ)) {
                filterString = filterString + buildSqlClause(hasAnd, "%s = ?", MessagingSmsConsts.READ);
                argsStringList.add(filter.getBoolean(MessagingSmsConsts.READ) ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
            }
            return new Object[]{filterString, argsStringList.toArray(new String[argsStringList.size()])};
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String buildSqlFilterOptionString(JSONObject filterOption) {
        String filterOptionString = "";
        try {
            if (filterOption.has("sortBy")) {
                filterOptionString = filterOptionString + " " + MessagingSmsConstMaps.smsTableColumnDict.get(filterOption.getString("sortBy"));
            }
            if (filterOption.has("sortOrder")) {
                filterOptionString = filterOptionString + " " + MessagingSmsConstMaps.sortOrderDict.get(filterOption.getString("sortOrder"));
            }
            if (filterOption.has("limit")) {
                filterOptionString = filterOptionString + " LIMIT " + filterOption.getString("limit");
            }
            return filterOptionString;
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static JSONObject SmsMessageCursor2Json(Cursor c) {
        try {
            JSONObject jsonMsg = new JSONObject();
            try {
                jsonMsg.put("messageID", c.getString(c.getColumnIndex("_id")));
                jsonMsg.put("conversationID", c.getString(c.getColumnIndex(MessagingSmsConsts.THREAD_ID)));
                jsonMsg.put(MessagingSmsConsts.TYPE, "sms");
                jsonMsg.put("serviceID", "");
                jsonMsg.put("from", c.getString(c.getColumnIndex(MessagingSmsConsts.ADDRESS)));
                jsonMsg.put("timestamp", convertDateLong2String(c.getLong(c.getColumnIndex(MessagingSmsConsts.DATE))));
                jsonMsg.put(MessagingSmsConsts.READ, c.getString(c.getColumnIndex(MessagingSmsConsts.READ)));
                jsonMsg.put("to", "");
                jsonMsg.put(MessagingSmsConsts.BODY, c.getString(c.getColumnIndex(MessagingSmsConsts.BODY)));
                jsonMsg.put("state", MessagingSmsConstMaps.smsStateDictI2S.get(Integer.valueOf(c.getInt(c.getColumnIndex(MessagingSmsConsts.TYPE)))));
                jsonMsg.put("deliveryStatus", MessagingSmsConstMaps.smsDiliveryStatusDictI2S.get(Integer.valueOf(c.getInt(c.getColumnIndex(MessagingSmsConsts.STATUS)))));
                jsonMsg.put("deliveryTimestamp", "");
                jsonMsg.put("messageClass", "");
                JSONObject jSONObject = jsonMsg;
                return jsonMsg;
            } catch (JSONException e) {
                e = e;
                JSONObject jSONObject2 = jsonMsg;
                e.printStackTrace();
                return null;
            }
        } catch (JSONException e2) {
            e = e2;
            e.printStackTrace();
            return null;
        }
    }
}
