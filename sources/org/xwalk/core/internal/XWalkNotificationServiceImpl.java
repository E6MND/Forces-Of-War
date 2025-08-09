package org.xwalk.core.internal;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.AndroidRuntimeException;
import android.util.Log;
import java.util.HashMap;
import org.chromium.ui.base.PageTransition;

public class XWalkNotificationServiceImpl implements XWalkNotificationService {
    private static final String TAG = "XWalkNotificationServiceImpl";
    private static final String XWALK_ACTION_CLICK_NOTIFICATION_SUFFIX = ".notification.click";
    private static final String XWALK_ACTION_CLOSE_NOTIFICATION_SUFFIX = ".notification.close";
    private static final String XWALK_INTENT_CATEGORY_NOTIFICATION_PREFIX = "notification_";
    private static final String XWALK_INTENT_EXTRA_KEY_NOTIFICATION_ID = "xwalk.NOTIFICATION_ID";
    private XWalkContentsClientBridge mBridge;
    private Context mContext;
    private HashMap<Integer, WebNotification> mExistNotificationIds;
    private HashMap<String, WebNotification> mExistReplaceIds;
    private BroadcastReceiver mNotificationCloseReceiver;
    private NotificationManager mNotificationManager = ((NotificationManager) this.mContext.getSystemService("notification"));
    /* access modifiers changed from: private */
    public XWalkViewInternal mView;

    private class WebNotification {
        public Notification.Builder mBuilder;
        public Integer mMessageNum = 1;
        public Integer mNotificationId;
        public String mReplaceId;

        WebNotification() {
        }
    }

    public XWalkNotificationServiceImpl(Context context, XWalkViewInternal view) {
        this.mContext = context;
        this.mView = view;
        this.mNotificationManager.cancelAll();
        this.mNotificationCloseReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                XWalkNotificationServiceImpl.this.mView.onNewIntent(intent);
            }
        };
        this.mExistNotificationIds = new HashMap<>();
        this.mExistReplaceIds = new HashMap<>();
    }

    private static String getCategoryFromNotificationId(int id) {
        return XWALK_INTENT_CATEGORY_NOTIFICATION_PREFIX + id;
    }

    public void setBridge(XWalkContentsClientBridge bridge) {
        this.mBridge = bridge;
    }

    public void shutdown() {
        if (!this.mExistNotificationIds.isEmpty()) {
            unregisterReceiver();
        }
        this.mBridge = null;
    }

    public boolean maybeHandleIntent(Intent intent) {
        int notificationId;
        if (intent.getAction() == null || (notificationId = intent.getIntExtra(XWALK_INTENT_EXTRA_KEY_NOTIFICATION_ID, -1)) <= 0) {
            return false;
        }
        if (intent.getAction().equals(this.mView.getActivity().getPackageName() + XWALK_ACTION_CLOSE_NOTIFICATION_SUFFIX)) {
            onNotificationClose(notificationId, true);
            return true;
        } else if (!intent.getAction().equals(this.mView.getActivity().getPackageName() + XWALK_ACTION_CLICK_NOTIFICATION_SUFFIX)) {
            return false;
        } else {
            onNotificationClick(notificationId);
            return true;
        }
    }

    public Bitmap getNotificationIcon(Bitmap icon) {
        if (icon == null) {
            return null;
        }
        int originalWidth = icon.getWidth();
        int originalHeight = icon.getHeight();
        if (originalWidth == 0 || originalHeight == 0) {
            return icon;
        }
        int targetWidth = this.mContext.getResources().getDimensionPixelSize(17104901);
        int targetHeight = this.mContext.getResources().getDimensionPixelSize(17104902);
        if (originalWidth > targetWidth && originalHeight > targetHeight) {
            if (originalWidth * targetHeight > originalHeight * targetWidth) {
                targetHeight = (originalHeight * targetWidth) / originalWidth;
            } else {
                targetWidth = (originalWidth * targetHeight) / originalHeight;
            }
        }
        return Bitmap.createScaledBitmap(icon, targetWidth, targetHeight, true);
    }

    public void showNotification(String title, String message, String replaceId, Bitmap icon, int notificationId) {
        Notification.Builder builder;
        if (replaceId.isEmpty() || !this.mExistReplaceIds.containsKey(replaceId)) {
            builder = new Notification.Builder(this.mContext.getApplicationContext()).setAutoCancel(true);
            WebNotification webNotification = new WebNotification();
            webNotification.mNotificationId = Integer.valueOf(notificationId);
            webNotification.mReplaceId = replaceId;
            webNotification.mBuilder = builder;
            this.mExistNotificationIds.put(Integer.valueOf(notificationId), webNotification);
            if (!replaceId.isEmpty()) {
                this.mExistReplaceIds.put(replaceId, webNotification);
            }
        } else {
            WebNotification webNotification2 = this.mExistReplaceIds.get(replaceId);
            notificationId = webNotification2.mNotificationId.intValue();
            builder = webNotification2.mBuilder;
            Integer valueOf = Integer.valueOf(webNotification2.mMessageNum.intValue() + 1);
            webNotification2.mMessageNum = valueOf;
            builder.setNumber(valueOf.intValue());
        }
        builder.setContentTitle(title);
        builder.setContentText(message);
        int iconRes = this.mContext.getApplicationInfo().icon;
        if (iconRes == 0) {
            iconRes = 17301651;
        }
        builder.setSmallIcon(iconRes);
        Bitmap bigIcon = getNotificationIcon(icon);
        if (bigIcon != null) {
            builder.setLargeIcon(bigIcon);
        }
        Context activity = this.mView.getActivity();
        String category = getCategoryFromNotificationId(notificationId);
        Intent clickIntent = new Intent(activity, activity.getClass()).setAction(activity.getPackageName() + XWALK_ACTION_CLICK_NOTIFICATION_SUFFIX).putExtra(XWALK_INTENT_EXTRA_KEY_NOTIFICATION_ID, notificationId).setFlags(537919488).addCategory(category);
        Intent closeIntent = new Intent(activity.getPackageName() + XWALK_ACTION_CLOSE_NOTIFICATION_SUFFIX).putExtra(XWALK_INTENT_EXTRA_KEY_NOTIFICATION_ID, notificationId).addCategory(category);
        builder.setContentIntent(PendingIntent.getActivity(activity, 0, clickIntent, PageTransition.FROM_API));
        builder.setDeleteIntent(PendingIntent.getBroadcast(activity, 0, closeIntent, PageTransition.FROM_API));
        doShowNotification(notificationId, Build.VERSION.SDK_INT >= 16 ? builder.build() : builder.getNotification());
        notificationChanged();
        onNotificationShown(notificationId);
    }

    public void cancelNotification(int notificationId) {
        this.mNotificationManager.cancel(notificationId);
        onNotificationClose(notificationId, false);
    }

    public void doShowNotification(int notificationId, Notification notification) {
        this.mNotificationManager.notify(notificationId, notification);
    }

    public void onNotificationShown(int notificationId) {
        if (this.mExistNotificationIds.get(Integer.valueOf(notificationId)) != null && this.mBridge != null) {
            this.mBridge.notificationDisplayed(notificationId);
        }
    }

    public void onNotificationClick(int notificationId) {
        WebNotification webNotification = this.mExistNotificationIds.get(Integer.valueOf(notificationId));
        if (webNotification != null) {
            this.mExistNotificationIds.remove(Integer.valueOf(notificationId));
            this.mExistReplaceIds.remove(webNotification.mReplaceId);
            notificationChanged();
            if (this.mBridge != null) {
                this.mBridge.notificationClicked(notificationId);
            }
        }
    }

    public void onNotificationClose(int notificationId, boolean byUser) {
        WebNotification webNotification = this.mExistNotificationIds.get(Integer.valueOf(notificationId));
        if (webNotification != null) {
            this.mExistNotificationIds.remove(Integer.valueOf(notificationId));
            this.mExistReplaceIds.remove(webNotification.mReplaceId);
            notificationChanged();
            if (this.mBridge != null) {
                this.mBridge.notificationClosed(notificationId, byUser);
            }
        }
    }

    private void notificationChanged() {
        if (this.mExistNotificationIds.isEmpty()) {
            Log.i(TAG, "notifications are all cleared,unregister broadcast receiver for close pending intent");
            unregisterReceiver();
            return;
        }
        registerReceiver();
    }

    private void registerReceiver() {
        IntentFilter filter = new IntentFilter(this.mView.getActivity().getPackageName() + XWALK_ACTION_CLOSE_NOTIFICATION_SUFFIX);
        for (Integer id : this.mExistNotificationIds.keySet()) {
            filter.addCategory(getCategoryFromNotificationId(id.intValue()));
        }
        try {
            this.mView.getActivity().registerReceiver(this.mNotificationCloseReceiver, filter);
        } catch (AndroidRuntimeException e) {
            Log.w(TAG, e.getLocalizedMessage());
        }
    }

    private void unregisterReceiver() {
        this.mView.getActivity().unregisterReceiver(this.mNotificationCloseReceiver);
    }
}
