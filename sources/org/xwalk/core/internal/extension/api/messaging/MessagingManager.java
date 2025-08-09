package org.xwalk.core.internal.extension.api.messaging;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.facebook.AppEventsConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MessagingManager {
    private static final String TAG = "MessagingManager";
    private final Activity mMainActivity;
    private final Messaging mMessagingHandler;

    MessagingManager(Activity activity, Messaging messaging) {
        this.mMainActivity = activity;
        this.mMessagingHandler = messaging;
    }

    public void onMsgFindMessages(int instanceID, JSONObject jsonMsg) {
        queryMessage(instanceID, jsonMsg);
    }

    public void onMsgGetMessage(int instanceID, JSONObject jsonMsg) {
        queryMessage(instanceID, jsonMsg);
    }

    public void onMsgDeleteMessage(int instanceID, JSONObject jsonMsg) {
        operation(instanceID, jsonMsg);
    }

    public void onMsgDeleteConversation(int instanceID, JSONObject jsonMsg) {
        operation(instanceID, jsonMsg);
    }

    public void onMsgMarkMessageRead(int instanceID, JSONObject jsonMsg) {
        operation(instanceID, jsonMsg);
    }

    public void onMsgMarkConversationRead(int instanceID, JSONObject jsonMsg) {
        operation(instanceID, jsonMsg);
    }

    private Uri getUri(String type) {
        if (type.equals("mms")) {
            return Uri.parse("content://mms");
        }
        return Uri.parse("content://sms");
    }

    /* JADX INFO: finally extract failed */
    private void queryMessage(int instanceID, JSONObject jsonMsg) {
        String msgType;
        String sqlString;
        String[] sqlArgs;
        JSONArray results;
        String messageID = null;
        JSONObject filter = null;
        JSONObject filterOption = null;
        try {
            String asyncCallId = jsonMsg.getString("asyncCallId");
            String cmd = jsonMsg.getString("cmd");
            JSONObject eventBody = jsonMsg.getJSONObject("data");
            if (eventBody.has("messageID")) {
                messageID = eventBody.getString("messageID");
            }
            if (eventBody.has("filter")) {
                filter = eventBody.getJSONObject("filter");
            }
            if (eventBody.has("options")) {
                filterOption = eventBody.getJSONObject("options");
            }
            if (filter != null) {
                msgType = filter.getString(MessagingSmsConsts.TYPE);
            } else {
                msgType = eventBody.getString(MessagingSmsConsts.TYPE);
            }
            if (msgType.equals("sms") || msgType.equals("mms")) {
                ContentResolver cr = this.mMainActivity.getContentResolver();
                Uri contentUri = getUri(msgType);
                String sqlOption = null;
                if (cmd.equals("msg_findMessages")) {
                    Object[] retValue = MessagingHelpers.buildSqlFilterString(filter);
                    sqlString = (String) retValue[0];
                    sqlArgs = (String[]) retValue[1];
                    sqlOption = MessagingHelpers.buildSqlFilterOptionString(filterOption);
                } else {
                    sqlString = String.format("%s = ?", new Object[]{"_id"});
                    sqlArgs = new String[]{messageID};
                }
                Cursor cursor = cr.query(contentUri, (String[]) null, sqlString, sqlArgs, sqlOption);
                try {
                    JSONObject jsonMsgRet = new JSONObject();
                    try {
                        results = new JSONArray();
                    } catch (JSONException e) {
                        e = e;
                        JSONObject jSONObject = jsonMsgRet;
                        e.printStackTrace();
                    }
                    try {
                        jsonMsgRet.put("asyncCallId", asyncCallId);
                        jsonMsgRet.put("cmd", cmd + "_ret");
                        JSONObject jsData = new JSONObject();
                        jsonMsgRet.put("data", jsData);
                        jsData.put("error", false);
                        JSONObject jsBody = new JSONObject();
                        jsData.put(MessagingSmsConsts.BODY, jsBody);
                        jsBody.put("results", results);
                        try {
                            if (!msgType.equals("mms")) {
                                if (cursor.getCount() > 0) {
                                    while (cursor.moveToNext()) {
                                        JSONObject jsonSmsObj = MessagingHelpers.SmsMessageCursor2Json(cursor);
                                        if (jsonSmsObj != null) {
                                            results.put(jsonSmsObj);
                                        }
                                    }
                                }
                            }
                            cursor.close();
                            this.mMessagingHandler.postMessage(instanceID, jsonMsgRet.toString());
                        } catch (Throwable th) {
                            cursor.close();
                            throw th;
                        }
                    } catch (JSONException e2) {
                        e = e2;
                        JSONArray jSONArray = results;
                        JSONObject jSONObject2 = jsonMsgRet;
                        e.printStackTrace();
                    }
                } catch (JSONException e3) {
                    e = e3;
                    e.printStackTrace();
                }
            } else {
                Log.e(TAG, "Invalidate message type: " + msgType);
            }
        } catch (JSONException e4) {
            e4.printStackTrace();
        }
    }

    private void operation(int instanceID, JSONObject jsonMsg) {
        String id;
        String selString;
        boolean isRead = false;
        try {
            String asyncCallId = jsonMsg.getString("asyncCallId");
            JSONObject eventBody = jsonMsg.getJSONObject("data");
            if (eventBody.has("messageID")) {
                id = eventBody.getString("messageID");
            } else {
                id = eventBody.getString("conversationID");
            }
            String cmd = jsonMsg.getString("cmd");
            if (eventBody.has("value")) {
                isRead = eventBody.getBoolean("value");
            }
            String msgType = eventBody.getString(MessagingSmsConsts.TYPE);
            if (eventBody.has("messageID")) {
                selString = String.format("%s = ?", new Object[]{"_id"});
            } else {
                selString = String.format("%s = ?", new Object[]{MessagingSmsConsts.THREAD_ID});
            }
            String[] selArgs = {id};
            ContentResolver cr = this.mMainActivity.getContentResolver();
            Uri contentUri = getUri(msgType);
            if (cmd.equals("msg_deleteMessage") || cmd.equals("msg_deleteConversation")) {
                cr.delete(contentUri, selString, selArgs);
            } else if (cmd.equals("msg_markMessageRead") || cmd.equals("msg_markConversationRead")) {
                ContentValues values = new ContentValues();
                values.put(MessagingSmsConsts.READ, isRead ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
                cr.update(contentUri, values, selString, selArgs);
            }
            try {
                JSONObject jsonMsgRet = new JSONObject();
                try {
                    jsonMsgRet.put("asyncCallId", asyncCallId);
                    JSONObject jsData = new JSONObject();
                    jsonMsgRet.put("data", jsData);
                    jsData.put("error", false);
                    JSONObject jsBody = new JSONObject();
                    jsData.put(MessagingSmsConsts.BODY, jsBody);
                    if (eventBody.has("messageID")) {
                        jsBody.put("messageID", id);
                    } else {
                        jsBody.put("conversationID", id);
                    }
                    jsonMsgRet.put("cmd", cmd + "_ret");
                    this.mMessagingHandler.postMessage(instanceID, jsonMsgRet.toString());
                } catch (JSONException e) {
                    e = e;
                    JSONObject jSONObject = jsonMsgRet;
                }
            } catch (JSONException e2) {
                e = e2;
                e.printStackTrace();
            }
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
    }
}
