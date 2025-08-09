package org.chromium.content.browser;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;
import org.chromium.content.R;

@JNINamespace("content")
public class TracingControllerAndroid {
    static final /* synthetic */ boolean $assertionsDisabled = (!TracingControllerAndroid.class.desiredAssertionStatus());
    private static final String ACTION_LIST_CATEGORIES = "GPU_PROFILER_LIST_CATEGORIES";
    private static final String ACTION_START = "GPU_PROFILER_START";
    private static final String ACTION_STOP = "GPU_PROFILER_STOP";
    private static final String CATEGORIES_EXTRA = "categories";
    private static final String DEFAULT_CHROME_CATEGORIES_PLACE_HOLDER = "_DEFAULT_CHROME_CATEGORIES";
    private static final String FILE_EXTRA = "file";
    private static final String PROFILER_FINISHED_FMT = "Profiler finished. Results are in %s.";
    private static final String PROFILER_STARTED_FMT = "Profiler started: %s";
    private static final String RECORD_CONTINUOUSLY_EXTRA = "continuous";
    private static final String TAG = "TracingControllerAndroid";
    private final TracingBroadcastReceiver mBroadcastReceiver;
    private final Context mContext;
    private String mFilename;
    private final TracingIntentFilter mIntentFilter;
    private boolean mIsTracing;
    private long mNativeTracingControllerAndroid;
    private boolean mShowToasts = true;

    private native void nativeDestroy(long j);

    /* access modifiers changed from: private */
    public native String nativeGetDefaultCategories();

    private native boolean nativeGetKnownCategoryGroupsAsync(long j);

    private native long nativeInit();

    private native boolean nativeStartTracing(long j, String str, String str2);

    private native void nativeStopTracing(long j, String str);

    public TracingControllerAndroid(Context context) {
        this.mContext = context;
        this.mBroadcastReceiver = new TracingBroadcastReceiver();
        this.mIntentFilter = new TracingIntentFilter(context);
    }

    public BroadcastReceiver getBroadcastReceiver() {
        return this.mBroadcastReceiver;
    }

    public IntentFilter getIntentFilter() {
        return this.mIntentFilter;
    }

    public void registerReceiver(Context context) {
        context.registerReceiver(getBroadcastReceiver(), getIntentFilter());
    }

    public void unregisterReceiver(Context context) {
        context.unregisterReceiver(getBroadcastReceiver());
    }

    public boolean isTracing() {
        return this.mIsTracing;
    }

    public String getOutputPath() {
        return this.mFilename;
    }

    @CalledByNative
    private static String generateTracingFilePath() {
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HHmmss", Locale.US);
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "chrome-profile-results-" + formatter.format(new Date())).getPath();
    }

    public boolean startTracing(boolean showToasts, String categories, String traceOptions) {
        this.mShowToasts = showToasts;
        String filePath = generateTracingFilePath();
        if (filePath == null) {
            logAndToastError(this.mContext.getString(R.string.profiler_no_storage_toast));
        }
        return startTracing(filePath, showToasts, categories, traceOptions);
    }

    private void initializeNativeControllerIfNeeded() {
        if (this.mNativeTracingControllerAndroid == 0) {
            this.mNativeTracingControllerAndroid = nativeInit();
        }
    }

    public boolean startTracing(String filename, boolean showToasts, String categories, String traceOptions) {
        this.mShowToasts = showToasts;
        if (isTracing()) {
            Log.e(TAG, "Received startTracing, but we're already tracing");
            return false;
        }
        initializeNativeControllerIfNeeded();
        if (!nativeStartTracing(this.mNativeTracingControllerAndroid, categories, traceOptions.toString())) {
            logAndToastError(this.mContext.getString(R.string.profiler_error_toast));
            return false;
        }
        logForProfiler(String.format(PROFILER_STARTED_FMT, new Object[]{categories}));
        showToast(this.mContext.getString(R.string.profiler_started_toast) + ": " + categories);
        this.mFilename = filename;
        this.mIsTracing = true;
        return true;
    }

    public void stopTracing() {
        if (isTracing()) {
            nativeStopTracing(this.mNativeTracingControllerAndroid, this.mFilename);
        }
    }

    /* access modifiers changed from: protected */
    @CalledByNative
    public void onTracingStopped() {
        if (!isTracing()) {
            Log.e(TAG, "Received onTracingStopped, but we aren't tracing");
            return;
        }
        logForProfiler(String.format(PROFILER_FINISHED_FMT, new Object[]{this.mFilename}));
        showToast(this.mContext.getString(R.string.profiler_stopped_toast, new Object[]{this.mFilename}));
        this.mIsTracing = false;
        this.mFilename = null;
    }

    public void getCategoryGroups() {
        initializeNativeControllerIfNeeded();
        if (!nativeGetKnownCategoryGroupsAsync(this.mNativeTracingControllerAndroid)) {
            Log.e(TAG, "Unable to fetch tracing record groups list.");
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        if (!$assertionsDisabled && this.mNativeTracingControllerAndroid != 0) {
            throw new AssertionError();
        }
    }

    public void destroy() {
        if (this.mNativeTracingControllerAndroid != 0) {
            nativeDestroy(this.mNativeTracingControllerAndroid);
            this.mNativeTracingControllerAndroid = 0;
        }
    }

    private void logAndToastError(String str) {
        Log.e(TAG, str);
        if (this.mShowToasts) {
            Toast.makeText(this.mContext, str, 0).show();
        }
    }

    private void logForProfiler(String str) {
        Log.i(TAG, str);
    }

    private void showToast(String str) {
        if (this.mShowToasts) {
            Toast.makeText(this.mContext, str, 0).show();
        }
    }

    private static class TracingIntentFilter extends IntentFilter {
        TracingIntentFilter(Context context) {
            addAction(context.getPackageName() + "." + TracingControllerAndroid.ACTION_START);
            addAction(context.getPackageName() + "." + TracingControllerAndroid.ACTION_STOP);
            addAction(context.getPackageName() + "." + TracingControllerAndroid.ACTION_LIST_CATEGORIES);
        }
    }

    class TracingBroadcastReceiver extends BroadcastReceiver {
        TracingBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            String categories;
            if (intent.getAction().endsWith(TracingControllerAndroid.ACTION_START)) {
                String categories2 = intent.getStringExtra(TracingControllerAndroid.CATEGORIES_EXTRA);
                if (TextUtils.isEmpty(categories2)) {
                    categories = TracingControllerAndroid.this.nativeGetDefaultCategories();
                } else {
                    categories = categories2.replaceFirst(TracingControllerAndroid.DEFAULT_CHROME_CATEGORIES_PLACE_HOLDER, TracingControllerAndroid.this.nativeGetDefaultCategories());
                }
                String traceOptions = intent.getStringExtra(TracingControllerAndroid.RECORD_CONTINUOUSLY_EXTRA) == null ? "record-until-full" : "record-continuously";
                String filename = intent.getStringExtra("file");
                if (filename != null) {
                    TracingControllerAndroid.this.startTracing(filename, true, categories, traceOptions);
                } else {
                    TracingControllerAndroid.this.startTracing(true, categories, traceOptions);
                }
            } else if (intent.getAction().endsWith(TracingControllerAndroid.ACTION_STOP)) {
                TracingControllerAndroid.this.stopTracing();
            } else if (intent.getAction().endsWith(TracingControllerAndroid.ACTION_LIST_CATEGORIES)) {
                TracingControllerAndroid.this.getCategoryGroups();
            } else {
                Log.e(TracingControllerAndroid.TAG, "Unexpected intent: " + intent);
            }
        }
    }
}
