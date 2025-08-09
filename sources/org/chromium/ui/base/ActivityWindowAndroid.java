package org.chromium.ui.base;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.IntentSender;
import java.lang.ref.WeakReference;
import org.chromium.ui.base.WindowAndroid;

public class ActivityWindowAndroid extends WindowAndroid {
    private static final int REQUEST_CODE_PREFIX = 1000;
    private static final int REQUEST_CODE_RANGE_SIZE = 100;
    private static final String TAG = "ActivityWindowAndroid";
    private final WeakReference<Activity> mActivityRef;
    private int mNextRequestCode = 0;

    public ActivityWindowAndroid(Activity activity) {
        super(activity.getApplicationContext());
        this.mActivityRef = new WeakReference<>(activity);
    }

    public int showCancelableIntent(PendingIntent intent, WindowAndroid.IntentCallback callback, int errorId) {
        Activity activity = (Activity) this.mActivityRef.get();
        if (activity == null) {
            return -1;
        }
        int requestCode = generateNextRequestCode();
        try {
            activity.startIntentSenderForResult(intent.getIntentSender(), requestCode, new Intent(), 0, 0, 0);
            storeCallbackData(requestCode, callback, errorId);
            return requestCode;
        } catch (IntentSender.SendIntentException e) {
            return -1;
        }
    }

    public int showCancelableIntent(Intent intent, WindowAndroid.IntentCallback callback, int errorId) {
        Activity activity = (Activity) this.mActivityRef.get();
        if (activity == null) {
            return -1;
        }
        int requestCode = generateNextRequestCode();
        try {
            activity.startActivityForResult(intent, requestCode);
            storeCallbackData(requestCode, callback, errorId);
            return requestCode;
        } catch (ActivityNotFoundException e) {
            return -1;
        }
    }

    public void cancelIntent(int requestCode) {
        Activity activity = (Activity) this.mActivityRef.get();
        if (activity != null) {
            activity.finishActivity(requestCode);
        }
    }

    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        WindowAndroid.IntentCallback callback = (WindowAndroid.IntentCallback) this.mOutstandingIntents.get(requestCode);
        this.mOutstandingIntents.delete(requestCode);
        String errorMessage = (String) this.mIntentErrors.remove(Integer.valueOf(requestCode));
        if (callback != null) {
            callback.onIntentCompleted(this, resultCode, this.mApplicationContext.getContentResolver(), data);
            return true;
        } else if (errorMessage == null) {
            return false;
        } else {
            showCallbackNonExistentError(errorMessage);
            return true;
        }
    }

    public WeakReference<Activity> getActivity() {
        return new WeakReference<>(this.mActivityRef.get());
    }

    private int generateNextRequestCode() {
        int requestCode = this.mNextRequestCode + 1000;
        this.mNextRequestCode = (this.mNextRequestCode + 1) % 100;
        return requestCode;
    }

    private void storeCallbackData(int requestCode, WindowAndroid.IntentCallback callback, int errorId) {
        this.mOutstandingIntents.put(requestCode, callback);
        this.mIntentErrors.put(Integer.valueOf(requestCode), this.mApplicationContext.getString(errorId));
    }
}
