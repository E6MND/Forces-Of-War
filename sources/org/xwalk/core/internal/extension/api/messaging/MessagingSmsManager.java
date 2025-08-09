package org.xwalk.core.internal.extension.api.messaging;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.google.android.gms.tagmanager.DataLayer;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import org.chromium.ui.base.PageTransition;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MessagingSmsManager {
    private static final String DEFAULT_SERVICE_ID = "sim0";
    private static final String EXTRA_MSGID = "asyncCallId";
    private static final String EXTRA_MSGINSTANCEID = "instanceid";
    private static final String EXTRA_MSGTEXT = "message";
    private static final String EXTRA_MSGTO = "to";
    private static final String EXTRA_UUID = "UUID";
    private static final String TAG = "MessagingSmsManager";
    /* access modifiers changed from: private */
    public final WeakReference<Activity> mActivity;
    private final Messaging mMessagingHandler;
    private BroadcastReceiver mSmsDeliveredReceiver;
    private BroadcastReceiver mSmsReceiveReceiver;
    private BroadcastReceiver mSmsSentReceiver;
    private BroadcastReceiver mSmsServiceReceiver;
    /* access modifiers changed from: private */
    public String mUUID = UUID.randomUUID().toString();

    private abstract class MessagingReceiver extends BroadcastReceiver {
        protected Messaging mMessaging;

        public MessagingReceiver(Messaging messaging) {
            this.mMessaging = messaging;
        }
    }

    MessagingSmsManager(Activity activity, Messaging messaging) {
        this.mActivity = new WeakReference<>(activity);
        this.mMessagingHandler = messaging;
    }

    /* access modifiers changed from: private */
    public boolean checkService(String serviceID) {
        Activity activity = (Activity) this.mActivity.get();
        if (activity != null && 5 == ((TelephonyManager) activity.getSystemService("phone")).getSimState()) {
            return true;
        }
        return false;
    }

    public void onSmsSend(int instanceID, JSONObject jsonMsg) {
        if (!checkService(DEFAULT_SERVICE_ID)) {
            Log.e(TAG, "No Sim Card");
        }
        Activity activity = (Activity) this.mActivity.get();
        if (activity != null) {
            try {
                String asyncCallId = jsonMsg.getString(EXTRA_MSGID);
                JSONObject eventBody = jsonMsg.getJSONObject("data");
                String phone = eventBody.getString("phone");
                String smsMessage = eventBody.getString(EXTRA_MSGTEXT);
                SmsManager sms = SmsManager.getDefault();
                Intent intentSmsSent = new Intent("SMS_SENT");
                intentSmsSent.putExtra(EXTRA_MSGID, asyncCallId);
                intentSmsSent.putExtra(EXTRA_MSGTEXT, smsMessage);
                intentSmsSent.putExtra(EXTRA_MSGTO, phone);
                String instanceIDString = Integer.toString(instanceID);
                intentSmsSent.putExtra(EXTRA_MSGINSTANCEID, instanceIDString);
                intentSmsSent.putExtra(EXTRA_UUID, this.mUUID);
                int promiseIdInt = Integer.valueOf(asyncCallId).intValue();
                PendingIntent piSent = PendingIntent.getBroadcast(activity, promiseIdInt, intentSmsSent, PageTransition.CLIENT_REDIRECT);
                Intent intentSmsDelivered = new Intent("SMS_DELIVERED");
                intentSmsDelivered.putExtra(EXTRA_MSGID, asyncCallId);
                intentSmsDelivered.putExtra(EXTRA_MSGTEXT, smsMessage);
                intentSmsDelivered.putExtra(EXTRA_MSGINSTANCEID, instanceIDString);
                intentSmsDelivered.putExtra(EXTRA_UUID, this.mUUID);
                try {
                    sms.sendTextMessage(phone, (String) null, smsMessage, piSent, PendingIntent.getBroadcast(activity, -promiseIdInt, intentSmsDelivered, PageTransition.CLIENT_REDIRECT));
                } catch (Exception e) {
                    Log.e(TAG, "Failed to send SMS message.", e);
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void onSmsClear(int instanceID, JSONObject jsonMsg) {
        Activity activity = (Activity) this.mActivity.get();
        if (activity != null) {
            try {
                String asyncCallId = jsonMsg.getString(EXTRA_MSGID);
                String cmd = jsonMsg.getString("cmd");
                String serviceID = jsonMsg.getJSONObject("data").getString("serviceID");
                activity.getContentResolver().delete(Uri.parse("content://sms"), (String) null, (String[]) null);
                try {
                    JSONObject jsonMsgRet = new JSONObject();
                    try {
                        jsonMsgRet.put(EXTRA_MSGID, asyncCallId);
                        jsonMsgRet.put("cmd", cmd + "_ret");
                        JSONObject jsData = new JSONObject();
                        jsonMsgRet.put("data", jsData);
                        jsData.put("error", false);
                        JSONObject jsBody = new JSONObject();
                        jsData.put(MessagingSmsConsts.BODY, jsBody);
                        jsBody.put("value", serviceID);
                        this.mMessagingHandler.postMessage(instanceID, jsonMsgRet.toString());
                    } catch (JSONException e) {
                        e = e;
                        JSONObject jSONObject = jsonMsgRet;
                        e.printStackTrace();
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

    public void onSmsSegmentInfo(int instanceID, JSONObject jsonMsg) {
        try {
            String asyncCallId = jsonMsg.getString(EXTRA_MSGID);
            String text = jsonMsg.getJSONObject("data").getString("text");
            if (text == null) {
                Log.e(TAG, "No \"text\" attribute.");
                return;
            }
            ArrayList<String> segs = SmsManager.getDefault().divideMessage(text);
            try {
                JSONObject jsonMsgRet = new JSONObject();
                jsonMsgRet.put("cmd", "msg_smsSegmentInfo_ret");
                jsonMsgRet.put(EXTRA_MSGID, asyncCallId);
                JSONObject jsData = new JSONObject();
                jsonMsgRet.put("data", jsData);
                jsData.put("error", false);
                JSONObject jsBody = new JSONObject();
                jsData.put(MessagingSmsConsts.BODY, jsBody);
                jsBody.put("segments", segs.size());
                jsBody.put("charsPerSegment", segs.get(0).length());
                jsBody.put("charsAvailableInLastSegment", segs.get(segs.size() - 1).length());
                this.mMessagingHandler.postMessage(instanceID, jsonMsgRet.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void registerIntentFilters() {
        Activity activity = (Activity) this.mActivity.get();
        if (activity != null) {
            this.mSmsReceiveReceiver = new MessagingReceiver(this.mMessagingHandler) {
                public void onReceive(Context context, Intent intent) {
                    Bundle bundle = intent.getExtras();
                    if (bundle != null) {
                        Object[] pdus = (Object[]) bundle.get("pdus");
                        int i = 0;
                        while (i < pdus.length) {
                            try {
                                JSONObject jsonMsg = new JSONObject();
                                jsonMsg.put("cmd", "received");
                                SmsMessage msgs = SmsMessage.createFromPdu((byte[]) pdus[i]);
                                JSONObject jsData = new JSONObject();
                                jsonMsg.put("data", jsData);
                                JSONObject jsMsg = new JSONObject();
                                jsData.put(MessagingSmsManager.EXTRA_MSGTEXT, jsMsg);
                                jsMsg.put("messageID", "");
                                jsMsg.put(MessagingSmsConsts.TYPE, "sms");
                                jsMsg.put("serviceID", MessagingSmsManager.DEFAULT_SERVICE_ID);
                                jsMsg.put("from", msgs.getOriginatingAddress());
                                jsMsg.put("timestamp", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
                                jsMsg.put(MessagingSmsConsts.BODY, msgs.getMessageBody().toString());
                                this.mMessaging.broadcastMessage(jsonMsg.toString());
                                i++;
                            } catch (JSONException e) {
                                e.printStackTrace();
                                return;
                            }
                        }
                    }
                }
            };
            this.mSmsSentReceiver = new MessagingReceiver(this.mMessagingHandler) {
                public void onReceive(Context content, Intent intent) {
                    String uuid;
                    Activity activity = (Activity) MessagingSmsManager.this.mActivity.get();
                    if (activity != null && (uuid = intent.getStringExtra(MessagingSmsManager.EXTRA_UUID)) != null && uuid.equals(MessagingSmsManager.this.mUUID)) {
                        boolean error = getResultCode() != -1;
                        String asyncCallId = intent.getStringExtra(MessagingSmsManager.EXTRA_MSGID);
                        String smsMessage = intent.getStringExtra(MessagingSmsManager.EXTRA_MSGTEXT);
                        String to = intent.getStringExtra(MessagingSmsManager.EXTRA_MSGTO);
                        int instanceID = Integer.valueOf(intent.getStringExtra(MessagingSmsManager.EXTRA_MSGINSTANCEID)).intValue();
                        try {
                            JSONObject jsSentMsg = new JSONObject();
                            jsSentMsg.put(MessagingSmsConsts.TYPE, "sms");
                            jsSentMsg.put("from", "");
                            jsSentMsg.put(MessagingSmsConsts.READ, true);
                            jsSentMsg.put(MessagingSmsManager.EXTRA_MSGTO, to);
                            jsSentMsg.put(MessagingSmsConsts.BODY, smsMessage);
                            jsSentMsg.put("messageClass", "class1");
                            jsSentMsg.put("state", error ? "failed" : "sending");
                            jsSentMsg.put("deliveryStatus", error ? "error" : "pending");
                            JSONObject jsonMsgPromise = new JSONObject();
                            jsonMsgPromise.put(MessagingSmsManager.EXTRA_MSGID, asyncCallId);
                            jsonMsgPromise.put("cmd", "msg_smsSend_ret");
                            JSONObject jsData = new JSONObject();
                            jsonMsgPromise.put("data", jsData);
                            jsData.put("error", error);
                            jsData.put(MessagingSmsConsts.BODY, jsSentMsg);
                            this.mMessaging.postMessage(instanceID, jsonMsgPromise.toString());
                            JSONObject jsonMsgEvent = new JSONObject();
                            jsonMsgEvent.put("cmd", "sent");
                            jsonMsgEvent.put("data", jsSentMsg);
                            this.mMessaging.broadcastMessage(jsonMsgEvent.toString());
                            ContentValues values = new ContentValues();
                            values.put(MessagingSmsConsts.ADDRESS, to);
                            values.put(MessagingSmsConsts.BODY, smsMessage);
                            activity.getContentResolver().insert(Uri.parse("content://sms/sent"), values);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            this.mSmsDeliveredReceiver = new MessagingReceiver(this.mMessagingHandler) {
                public void onReceive(Context content, Intent intent) {
                    String uuid = intent.getStringExtra(MessagingSmsManager.EXTRA_UUID);
                    if (uuid != null && uuid.equals(MessagingSmsManager.this.mUUID)) {
                        boolean error = getResultCode() != -1;
                        String asyncCallId = intent.getStringExtra(MessagingSmsManager.EXTRA_MSGID);
                        int instanceID = Integer.valueOf(intent.getStringExtra(MessagingSmsManager.EXTRA_MSGINSTANCEID)).intValue();
                        try {
                            JSONObject jsonMsg = new JSONObject();
                            jsonMsg.put(MessagingSmsManager.EXTRA_MSGID, asyncCallId);
                            jsonMsg.put("cmd", error ? "deliveryerror" : "deliverysuccess");
                            JSONObject jsData = new JSONObject();
                            jsonMsg.put("data", jsData);
                            JSONObject jsEvent = new JSONObject();
                            jsData.put(DataLayer.EVENT_KEY, jsEvent);
                            jsEvent.put("serviceID", MessagingSmsManager.DEFAULT_SERVICE_ID);
                            jsEvent.put("messageID", "");
                            jsEvent.put("recipients", "");
                            jsEvent.put("deliveryTimestamps", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
                            jsData.put("error", error);
                            this.mMessaging.postMessage(instanceID, jsonMsg.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            this.mSmsServiceReceiver = new MessagingReceiver(this.mMessagingHandler) {
                public void onReceive(Context content, Intent intent) {
                    try {
                        JSONObject jsonMsg = new JSONObject();
                        jsonMsg.put("cmd", MessagingSmsManager.this.checkService(MessagingSmsManager.DEFAULT_SERVICE_ID) ? "serviceadded" : "serviceremoved");
                        JSONObject jsData = new JSONObject();
                        jsonMsg.put("data", jsData);
                        JSONObject jsEvent = new JSONObject();
                        jsData.put(DataLayer.EVENT_KEY, jsEvent);
                        jsEvent.put("serviceID", MessagingSmsManager.DEFAULT_SERVICE_ID);
                        this.mMessaging.broadcastMessage(jsonMsg.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            activity.registerReceiver(this.mSmsReceiveReceiver, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
            activity.registerReceiver(this.mSmsSentReceiver, new IntentFilter("SMS_SENT"));
            activity.registerReceiver(this.mSmsDeliveredReceiver, new IntentFilter("SMS_DELIVERED"));
            activity.registerReceiver(this.mSmsServiceReceiver, new IntentFilter("android.intent.action.SIM_STATE_CHANGED"));
        }
    }

    public void unregisterIntentFilters() {
        Activity activity = (Activity) this.mActivity.get();
        if (activity != null) {
            activity.unregisterReceiver(this.mSmsReceiveReceiver);
            activity.unregisterReceiver(this.mSmsSentReceiver);
            activity.unregisterReceiver(this.mSmsDeliveredReceiver);
            activity.unregisterReceiver(this.mSmsServiceReceiver);
        }
    }

    public String getServiceIds() {
        JSONArray serviceIds = new JSONArray();
        serviceIds.put(DEFAULT_SERVICE_ID);
        return serviceIds.toString();
    }
}
