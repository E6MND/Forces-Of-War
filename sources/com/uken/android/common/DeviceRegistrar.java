package com.uken.android.common;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.util.Log;
import com.facebook.AppEventsConstants;
import com.google.android.c2dm.C2DMBaseReceiver;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class DeviceRegistrar {
    public static String BASEURL = null;
    private static final String REGISTER_PATH = "notifications/device";
    private static final String TAG = "DeviceRegistrar";
    /* access modifiers changed from: private */
    public static String androidId = AppEventsConstants.EVENT_PARAM_VALUE_NO;
    /* access modifiers changed from: private */
    public static String deviceId = AppEventsConstants.EVENT_PARAM_VALUE_NO;

    public static void registerWithServer(final Context context, final String deviceRegistrationID) {
        new Thread(new Runnable() {
            public void run() {
                String unused = DeviceRegistrar.deviceId = UkenUuid.getUdid((ContextWrapper) context);
                String unused2 = DeviceRegistrar.androidId = UkenUuid.getAndroidId((ContextWrapper) context);
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(DeviceRegistrar.BASEURL + DeviceRegistrar.REGISTER_PATH);
                try {
                    List<NameValuePair> nameValuePairs = new ArrayList<>(2);
                    nameValuePairs.add(new BasicNameValuePair("platform_type", "android"));
                    nameValuePairs.add(new BasicNameValuePair("android_id", DeviceRegistrar.androidId));
                    nameValuePairs.add(new BasicNameValuePair("udid", DeviceRegistrar.deviceId));
                    nameValuePairs.add(new BasicNameValuePair(C2DMBaseReceiver.EXTRA_REGISTRATION_ID, deviceRegistrationID));
                    nameValuePairs.add(new BasicNameValuePair("notification_type", GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE));
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpclient.execute(httppost);
                    if (response.getStatusLine().getStatusCode() == 202) {
                        SharedPreferences.Editor editor = Prefs.get(context).edit();
                        editor.putString("deviceRegistrationID", deviceRegistrationID);
                        editor.commit();
                        return;
                    }
                    Log.w(DeviceRegistrar.TAG, "Device Registration error " + String.valueOf(response.getStatusLine().getStatusCode()));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ClientProtocolException e2) {
                    e2.printStackTrace();
                } catch (IOException e3) {
                    e3.printStackTrace();
                } catch (Exception e4) {
                    Utils.logCrashlytics(e4);
                }
            }
        }).start();
    }
}
