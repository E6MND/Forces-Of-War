package org.xwalk.core.internal.extension.api.messaging;

import android.app.Activity;
import java.util.HashMap;
import org.json.JSONObject;
import org.xwalk.core.internal.extension.XWalkExtensionWithActivityStateListener;

public class Messaging extends XWalkExtensionWithActivityStateListener {
    public static final String JS_API_PATH = "jsapi/messaging_api.js";
    private static final String NAME = "xwalk.experimental.messaging";
    private static HashMap<String, Command> sMethodMap = new HashMap<>();
    private boolean isIntentFiltersRegistered = false;
    /* access modifiers changed from: private */
    public MessagingManager mMessagingManager;
    /* access modifiers changed from: private */
    public MessagingSmsManager mSmsManager;

    private void initMethodMap() {
        sMethodMap.put("msg_smsSend", new Command() {
            public void runCommand(int instanceID, JSONObject jsonMsg) {
                Messaging.this.mSmsManager.onSmsSend(instanceID, jsonMsg);
            }
        });
        sMethodMap.put("msg_smsClear", new Command() {
            public void runCommand(int instanceID, JSONObject jsonMsg) {
                Messaging.this.mSmsManager.onSmsClear(instanceID, jsonMsg);
            }
        });
        sMethodMap.put("msg_smsSegmentInfo", new Command() {
            public void runCommand(int instanceID, JSONObject jsonMsg) {
                Messaging.this.mSmsManager.onSmsSegmentInfo(instanceID, jsonMsg);
            }
        });
        sMethodMap.put("msg_findMessages", new Command() {
            public void runCommand(int instanceID, JSONObject jsonMsg) {
                Messaging.this.mMessagingManager.onMsgFindMessages(instanceID, jsonMsg);
            }
        });
        sMethodMap.put("msg_getMessage", new Command() {
            public void runCommand(int instanceID, JSONObject jsonMsg) {
                Messaging.this.mMessagingManager.onMsgGetMessage(instanceID, jsonMsg);
            }
        });
        sMethodMap.put("msg_deleteMessage", new Command() {
            public void runCommand(int instanceID, JSONObject jsonMsg) {
                Messaging.this.mMessagingManager.onMsgDeleteMessage(instanceID, jsonMsg);
            }
        });
        sMethodMap.put("msg_deleteConversation", new Command() {
            public void runCommand(int instanceID, JSONObject jsonMsg) {
                Messaging.this.mMessagingManager.onMsgDeleteConversation(instanceID, jsonMsg);
            }
        });
        sMethodMap.put("msg_markMessageRead", new Command() {
            public void runCommand(int instanceID, JSONObject jsonMsg) {
                Messaging.this.mMessagingManager.onMsgMarkMessageRead(instanceID, jsonMsg);
            }
        });
        sMethodMap.put("msg_markConversationRead", new Command() {
            public void runCommand(int instanceID, JSONObject jsonMsg) {
                Messaging.this.mMessagingManager.onMsgMarkConversationRead(instanceID, jsonMsg);
            }
        });
    }

    private String getCommandString(String message) {
        if (message.isEmpty()) {
            return "";
        }
        try {
            return new JSONObject(message).getString("cmd");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public Messaging(String jsApiContent, Activity activity) {
        super(NAME, jsApiContent, activity);
        this.mSmsManager = new MessagingSmsManager(activity, this);
        this.mMessagingManager = new MessagingManager(activity, this);
        if (!this.isIntentFiltersRegistered) {
            this.mSmsManager.registerIntentFilters();
            this.isIntentFiltersRegistered = true;
        }
        initMethodMap();
    }

    public void onMessage(int instanceID, String message) {
        Command command = sMethodMap.get(getCommandString(message));
        if (command != null) {
            try {
                command.runCommand(instanceID, new JSONObject(message));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String onSyncMessage(int instanceID, String message) {
        if (getCommandString(message).equals("msg_smsServiceId")) {
            return this.mSmsManager.getServiceIds();
        }
        return "";
    }

    public void onActivityStateChange(Activity activity, int newState) {
        if (newState == 5 && this.isIntentFiltersRegistered) {
            this.mSmsManager.unregisterIntentFilters();
            this.isIntentFiltersRegistered = false;
        } else if (newState == 2 && !this.isIntentFiltersRegistered) {
            this.mSmsManager.registerIntentFilters();
            this.isIntentFiltersRegistered = true;
        }
    }
}
