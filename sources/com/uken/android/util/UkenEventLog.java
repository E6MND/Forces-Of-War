package com.uken.android.util;

import android.content.Context;
import com.uken.android.common.AuthenticationManager;
import com.uken.android.common.Consts;
import com.uken.android.common.DeviceInfo;
import com.uken.android.common.UkenApplication;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

public class UkenEventLog {
    public static String BASEURL = null;
    private static final String PATH = "uken/log-event";
    private static String androidId;
    private static HashMap<String, String> context_data = new HashMap<>();
    private static String credentialToken;
    private static boolean initialized = false;
    private static ArrayList<UkenEventLogMessage> logs = new ArrayList<>();
    private static String phoneId;
    private static boolean running = false;

    private static void init() {
        Context context = UkenApplication.getContext();
        phoneId = DeviceInfo.devicePhoneIdentifier(context);
        androidId = DeviceInfo.deviceAndroidIdentifier(context);
        setCredentialToken();
        initialized = true;
    }

    public static void setCredentialToken() {
        credentialToken = AuthenticationManager.getInstance(UkenApplication.getContext()).getCredentialToken(false);
        if (credentialToken != null && credentialToken.length() > 0) {
            addContextData("credential_token", credentialToken);
        }
    }

    public static void addContextData(String key, String value) {
        context_data.put(key, value);
    }

    public static void log(String event_type, String event_name) {
        log(event_type, event_name, (HashMap<String, String>) null);
    }

    public static void log(String event_type, String event_name, HashMap<String, String> event_data) {
        logs.add(new UkenEventLogMessage(event_type, event_name, event_data, context_data));
        schedulePushToServer();
    }

    private static void schedulePushToServer() {
        if (!running) {
            new Thread(new Runnable() {
                public void run() {
                    UkenEventLog.pushLogsToServer();
                }
            }).start();
        }
    }

    /* access modifiers changed from: private */
    public static void pushLogsToServer() {
        if (!running) {
            if (!initialized) {
                init();
            }
            try {
                running = true;
                Thread.sleep(20);
            } catch (InterruptedException e) {
                if (Consts.DEBUG) {
                    e.printStackTrace();
                }
            } catch (Throwable th) {
                running = false;
                if (logs.size() > 0) {
                    schedulePushToServer();
                }
                throw th;
            }
            if (logs.size() > 0) {
                HttpClient httpclient = new DefaultHttpClient();
                httpclient.getParams().setParameter("http.useragent", System.getProperty("http.agent"));
                HttpPost httppost = new HttpPost(BASEURL + PATH);
                ArrayList<UkenEventLogMessage> logsSent = (ArrayList) logs.clone();
                JSONArray data = new JSONArray();
                for (int i = 0; i < logs.size(); i++) {
                    data.put(logs.get(i).toJSON());
                }
                try {
                    JSONObject jsonPayload = new JSONObject();
                    jsonPayload.put("logs", data);
                    jsonPayload.put("credential_token", credentialToken);
                    jsonPayload.put("android_id", androidId);
                    jsonPayload.put("phone_id", phoneId);
                    httppost.setEntity(new StringEntity(jsonPayload.toString()));
                    httppost.setHeader("Accept", "application/json");
                    httppost.setHeader("Content-type", "application/json");
                    if (httpclient.execute(httppost).getStatusLine().getStatusCode() == 200) {
                        logs.removeAll(logsSent);
                    }
                } catch (Exception e2) {
                    if (Consts.DEBUG) {
                        e2.printStackTrace();
                    }
                }
            }
            running = false;
            if (logs.size() > 0) {
                schedulePushToServer();
            }
        }
    }
}
