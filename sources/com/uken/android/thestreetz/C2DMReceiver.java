package com.uken.android.thestreetz;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.crashlytics.android.internal.C0168b;
import com.google.android.c2dm.C2DMBaseReceiver;
import com.uken.android.common.DeviceRegistrar;
import com.uken.android.common.GameActivity;
import com.uken.android.common.UkenActivity;
import com.uken.android.forcesofwar.R;
import org.chromium.ui.base.PageTransition;

public class C2DMReceiver extends C2DMBaseReceiver {
    public C2DMReceiver() {
        super(UkenActivity.NOTIFICATION_SENDER_ID);
    }

    public void onRegistered(Context context, String registration) {
        Log.d("C2DM", "onRegistered: " + registration);
        DeviceRegistrar.registerWithServer(context, registration);
    }

    public void onUnregistered(Context context) {
        Log.d("C2DM", "onUnregistered");
    }

    public void onError(Context context, String errorId) {
        Log.d("C2DM", "onError");
    }

    public void onMessage(Context context, Intent intent) {
        CharSequence contentTitle;
        CharSequence contentText;
        Log.d("C2DM", "onMessage");
        Bundle extras = intent.getExtras();
        if (extras != null) {
            CharSequence notification_title = (String) extras.get("notification_title");
            String notification_message = (String) extras.get("notification_message");
            String notification_sound_url = (String) extras.get("notification_sound_url");
            String notification_badge = (String) extras.get("notification_badge");
            String str = (String) extras.get("uid");
            String notification_json = getJsonNotification(extras);
            if ((notification_title != null && !notification_title.equals("")) || (notification_message != null && !notification_message.equals(""))) {
                NotificationManager mNotificationManager = (NotificationManager) getSystemService("notification");
                Notification notification = new Notification(R.drawable.icon, notification_message, System.currentTimeMillis());
                notification.flags = 20;
                Context appContext = getApplicationContext();
                boolean isTitleEmpty = false;
                if (notification_title == null || notification_title.equals("")) {
                    contentTitle = "The Streetz";
                    String[] splitMessage = notification_message.split("^.*?[\\.!\\?](?:\\s|$)");
                    if (splitMessage.length > 1) {
                        int sentenceLength = splitMessage[1].toString().length();
                        if ((notification_message.length() - sentenceLength) - 1 <= 30) {
                            isTitleEmpty = true;
                            contentTitle = notification_message.substring(0, (notification_message.length() - sentenceLength) - 1);
                        }
                    }
                } else {
                    contentTitle = notification_title;
                }
                if (notification_message == null || notification_message.equals("")) {
                    contentText = "Tap to launch game";
                } else {
                    contentText = notification_message;
                    if (isTitleEmpty) {
                        contentText = notification_message.substring(contentTitle.length());
                    }
                }
                Intent notificationIntent = new Intent(this, GameActivity.class);
                notificationIntent.setAction(context.getPackageName() + System.currentTimeMillis());
                notificationIntent.setFlags(PageTransition.CLIENT_REDIRECT);
                notificationIntent.putExtra(UkenActivity.NOTIFICATION_TOKEN, System.currentTimeMillis());
                notificationIntent.putExtra(UkenActivity.NOTIFICATION_JSON, notification_json);
                notificationIntent.putExtra(UkenActivity.APP_STATE, UkenActivity.getApplicationState());
                notification.setLatestEventInfo(appContext, contentTitle, contentText, PendingIntent.getActivity(this, (int) System.currentTimeMillis(), notificationIntent, PageTransition.CLIENT_REDIRECT));
                notification.flags = 25;
                if (notification_sound_url != null && !notification_sound_url.equals("")) {
                    if (notification_sound_url.equals(C0168b.a)) {
                        notification.defaults |= 1;
                    } else {
                        notification.sound = Uri.parse(notification_sound_url);
                    }
                }
                if (notification_badge != null && !notification_badge.equals("")) {
                    notification.number = Integer.parseInt(notification_badge);
                }
                notification.vibrate = new long[]{0, 250, 200, 350};
                mNotificationManager.notify((int) System.currentTimeMillis(), notification);
            }
        }
    }
}
