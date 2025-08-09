package org.xwalk.core.internal;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ApplicationErrorReport;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.ValueCallback;
import android.widget.FrameLayout;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.CommandLine;
import org.chromium.content.browser.ContentViewCore;
import org.xwalk.core.internal.extension.BuiltinXWalkExtensions;

@XWalkAPI(createExternally = true, extendClass = FrameLayout.class)
public class XWalkViewInternal extends FrameLayout {
    static final /* synthetic */ boolean $assertionsDisabled;
    public static final int INPUT_FILE_REQUEST_CODE = 1;
    static final String PLAYSTORE_DETAIL_URI = "market://details?id=";
    @XWalkAPI
    public static final int RELOAD_IGNORE_CACHE = 1;
    @XWalkAPI
    public static final int RELOAD_NORMAL = 0;
    private static final String TAG = XWalkViewInternal.class.getSimpleName();
    private Activity mActivity;
    private XWalkActivityStateListener mActivityStateListener;
    private String mCameraPhotoPath;
    private XWalkContent mContent;
    private Context mContext = getContext();
    private ValueCallback<Uri> mFilePathCallback;
    private boolean mIsHidden;

    static {
        boolean z;
        if (!XWalkViewInternal.class.desiredAssertionStatus()) {
            z = true;
        } else {
            z = false;
        }
        $assertionsDisabled = z;
    }

    private class XWalkActivityStateListener implements ApplicationStatus.ActivityStateListener {
        WeakReference<XWalkViewInternal> mXWalkViewRef;

        XWalkActivityStateListener(XWalkViewInternal view) {
            this.mXWalkViewRef = new WeakReference<>(view);
        }

        public void onActivityStateChange(Activity activity, int newState) {
            XWalkViewInternal view = (XWalkViewInternal) this.mXWalkViewRef.get();
            if (view != null) {
                view.onActivityStateChange(activity, newState);
            }
        }
    }

    @XWalkAPI(postWrapperLines = {"        if (bridge == null) return;", "        addView((FrameLayout)bridge, new FrameLayout.LayoutParams(", "                FrameLayout.LayoutParams.MATCH_PARENT,", "                FrameLayout.LayoutParams.MATCH_PARENT));"}, preWrapperLines = {"        super(${param1}, ${param2});"})
    public XWalkViewInternal(Context context, AttributeSet attrs) {
        super(convertContext(context), attrs);
        checkThreadSafety();
        this.mActivity = (Activity) context;
        init(this.mContext, attrs);
    }

    @XWalkAPI(postWrapperLines = {"        if (bridge == null) return;", "        addView((FrameLayout)bridge, new FrameLayout.LayoutParams(", "                FrameLayout.LayoutParams.MATCH_PARENT,", "                FrameLayout.LayoutParams.MATCH_PARENT));"}, preWrapperLines = {"        super(${param1}, null);"})
    public XWalkViewInternal(Context context, Activity activity) {
        super(convertContext(context), (AttributeSet) null);
        checkThreadSafety();
        this.mActivity = activity;
        init(this.mContext, (AttributeSet) null);
    }

    private static Context convertContext(Context context) {
        Context context2 = context;
        Context bridgeContext = ReflectionHelper.getBridgeContext();
        if (bridgeContext == null || context == null || bridgeContext.getPackageName().equals(context.getPackageName())) {
            return context;
        }
        return new MixedContext(bridgeContext, context);
    }

    public Activity getActivity() {
        if (this.mActivity != null) {
            return this.mActivity;
        }
        if (getContext() instanceof Activity) {
            return (Activity) getContext();
        }
        if ($assertionsDisabled) {
            return null;
        }
        throw new AssertionError();
    }

    public Context getViewContext() {
        return this.mContext;
    }

    public void completeWindowCreation(XWalkViewInternal newXWalkView) {
        this.mContent.supplyContentsForPopup(newXWalkView == null ? null : newXWalkView.mContent);
    }

    private void init(Context context, AttributeSet attrs) {
        XWalkInternalResources.resetIds(context);
        try {
            XWalkViewDelegate.init(this);
            this.mActivityStateListener = new XWalkActivityStateListener(this);
            ApplicationStatus.registerStateListenerForActivity(this.mActivityStateListener, getActivity());
            initXWalkContent(context, attrs);
        } catch (Throwable e) {
            Throwable linkError = e;
            while (linkError != null) {
                if (linkError instanceof UnsatisfiedLinkError) {
                    final UnsatisfiedLinkError err = (UnsatisfiedLinkError) linkError;
                    final Activity activity = getActivity();
                    final String packageName = context.getPackageName();
                    String message = context.getString(R.string.cpu_arch_mismatch_message, new Object[]{XWalkViewDelegate.isRunningOnIA() ? "Intel" : "ARM"});
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setTitle(R.string.cpu_arch_mismatch_title).setMessage(message).setOnCancelListener(new DialogInterface.OnCancelListener() {
                        public void onCancel(DialogInterface dialog) {
                            activity.finish();
                        }
                    }).setPositiveButton(R.string.goto_store_button_label, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(XWalkViewInternal.PLAYSTORE_DETAIL_URI + packageName)));
                            activity.finish();
                        }
                    }).setNeutralButton(R.string.report_feedback_button_label, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            ApplicationErrorReport report = new ApplicationErrorReport();
                            report.type = 1;
                            String str = packageName;
                            report.processName = str;
                            report.packageName = str;
                            ApplicationErrorReport.CrashInfo crash = new ApplicationErrorReport.CrashInfo();
                            crash.exceptionClassName = err.getClass().getSimpleName();
                            crash.exceptionMessage = "CPU architecture mismatch";
                            StringWriter writer = new StringWriter();
                            err.printStackTrace(new PrintWriter(writer));
                            crash.stackTrace = writer.toString();
                            StackTraceElement stack = err.getStackTrace()[0];
                            crash.throwClassName = stack.getClassName();
                            crash.throwFileName = stack.getFileName();
                            crash.throwLineNumber = stack.getLineNumber();
                            crash.throwMethodName = stack.getMethodName();
                            report.crashInfo = crash;
                            report.systemApp = false;
                            report.time = System.currentTimeMillis();
                            Intent intent = new Intent("android.intent.action.APP_ERROR");
                            intent.putExtra("android.intent.extra.BUG_REPORT", report);
                            activity.startActivity(intent);
                            activity.finish();
                        }
                    });
                    builder.create().show();
                    return;
                } else if (linkError.getCause() == null || linkError.getCause().equals(linkError)) {
                    throw new RuntimeException(e);
                } else {
                    linkError = linkError.getCause();
                }
            }
            throw new RuntimeException(e);
        }
    }

    private void initXWalkContent(Context context, AttributeSet attrs) {
        File extCacheDir;
        this.mIsHidden = false;
        this.mContent = new XWalkContent(context, attrs, this);
        addView(this.mContent, new FrameLayout.LayoutParams(-1, -1));
        setXWalkClient(new XWalkClient(this));
        setXWalkWebChromeClient(new XWalkWebChromeClient(this));
        setUIClient(new XWalkUIClientInternal(this));
        setResourceClient(new XWalkResourceClientInternal(this));
        setDownloadListener(new XWalkDownloadListenerImpl(context));
        setNavigationHandler(new XWalkNavigationHandlerImpl(context));
        setNotificationService(new XWalkNotificationServiceImpl(context, this));
        if (!CommandLine.getInstance().hasSwitch("disable-xwalk-extensions")) {
            BuiltinXWalkExtensions.load(context, getActivity());
        } else {
            XWalkPreferencesInternal.setValue("enable-extensions", false);
        }
        XWalkPathHelper.initialize();
        XWalkPathHelper.setCacheDirectory(this.mContext.getApplicationContext().getCacheDir().getPath());
        String state = Environment.getExternalStorageState();
        if (("mounted".equals(state) || "mounted_ro".equals(state)) && (extCacheDir = this.mContext.getApplicationContext().getExternalCacheDir()) != null) {
            XWalkPathHelper.setExternalCacheDirectory(extCacheDir.getPath());
        }
    }

    @XWalkAPI
    public void load(String url, String content) {
        if (this.mContent != null) {
            checkThreadSafety();
            this.mContent.loadUrl(url, content);
        }
    }

    @XWalkAPI
    public void loadAppFromManifest(String url, String content) {
        if (this.mContent != null) {
            checkThreadSafety();
            this.mContent.loadAppFromManifest(url, content);
        }
    }

    @XWalkAPI
    public void reload(int mode) {
        if (this.mContent != null) {
            checkThreadSafety();
            this.mContent.reload(mode);
        }
    }

    @XWalkAPI
    public void stopLoading() {
        if (this.mContent != null) {
            checkThreadSafety();
            this.mContent.stopLoading();
        }
    }

    @XWalkAPI
    public String getUrl() {
        if (this.mContent == null) {
            return null;
        }
        checkThreadSafety();
        return this.mContent.getUrl();
    }

    @XWalkAPI
    public String getTitle() {
        if (this.mContent == null) {
            return null;
        }
        checkThreadSafety();
        return this.mContent.getTitle();
    }

    @XWalkAPI
    public String getOriginalUrl() {
        if (this.mContent == null) {
            return null;
        }
        checkThreadSafety();
        return this.mContent.getOriginalUrl();
    }

    @XWalkAPI
    public XWalkNavigationHistoryInternal getNavigationHistory() {
        if (this.mContent == null) {
            return null;
        }
        checkThreadSafety();
        return this.mContent.getNavigationHistory();
    }

    @XWalkAPI
    public void addJavascriptInterface(Object object, String name) {
        if (this.mContent != null) {
            checkThreadSafety();
            this.mContent.addJavascriptInterface(object, name);
        }
    }

    @XWalkAPI
    public void evaluateJavascript(String script, ValueCallback<String> callback) {
        if (this.mContent != null) {
            checkThreadSafety();
            this.mContent.evaluateJavascript(script, callback);
        }
    }

    @XWalkAPI
    public void clearCache(boolean includeDiskFiles) {
        if (this.mContent != null) {
            checkThreadSafety();
            this.mContent.clearCache(includeDiskFiles);
        }
    }

    @XWalkAPI
    public boolean hasEnteredFullscreen() {
        if (this.mContent == null) {
            return false;
        }
        checkThreadSafety();
        return this.mContent.hasEnteredFullscreen();
    }

    @XWalkAPI
    public void leaveFullscreen() {
        if (this.mContent != null) {
            checkThreadSafety();
            this.mContent.exitFullscreen();
        }
    }

    @XWalkAPI
    public void pauseTimers() {
        if (this.mContent != null) {
            checkThreadSafety();
            this.mContent.pauseTimers();
        }
    }

    @XWalkAPI
    public void resumeTimers() {
        if (this.mContent != null) {
            checkThreadSafety();
            this.mContent.resumeTimers();
        }
    }

    @XWalkAPI
    public void onHide() {
        if (this.mContent != null && !this.mIsHidden) {
            this.mContent.onPause();
            this.mIsHidden = true;
        }
    }

    @XWalkAPI
    public void onShow() {
        if (this.mContent != null && this.mIsHidden) {
            this.mContent.onResume();
            this.mIsHidden = false;
        }
    }

    @XWalkAPI
    public void onDestroy() {
        destroy();
    }

    @XWalkAPI
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (this.mContent != null) {
            if (requestCode != 1 || this.mFilePathCallback == null) {
                this.mContent.onActivityResult(requestCode, resultCode, data);
                return;
            }
            Uri results = null;
            if (-1 == resultCode) {
                if (data != null) {
                    String dataString = data.getDataString();
                    if (dataString != null) {
                        results = Uri.parse(dataString);
                    }
                } else if (this.mCameraPhotoPath != null) {
                    results = Uri.parse(this.mCameraPhotoPath);
                }
            }
            this.mFilePathCallback.onReceiveValue(results);
            this.mFilePathCallback = null;
        }
    }

    @XWalkAPI
    public boolean onNewIntent(Intent intent) {
        if (this.mContent == null) {
            return false;
        }
        return this.mContent.onNewIntent(intent);
    }

    @XWalkAPI
    public boolean saveState(Bundle outState) {
        if (this.mContent == null) {
            return false;
        }
        this.mContent.saveState(outState);
        return true;
    }

    @XWalkAPI
    public boolean restoreState(Bundle inState) {
        if (this.mContent == null || this.mContent.restoreState(inState) == null) {
            return false;
        }
        return true;
    }

    @XWalkAPI
    public String getAPIVersion() {
        return "4.1";
    }

    @XWalkAPI
    public String getXWalkVersion() {
        if (this.mContent == null) {
            return null;
        }
        return this.mContent.getXWalkVersion();
    }

    @XWalkAPI
    public void setUIClient(XWalkUIClientInternal client) {
        if (this.mContent != null) {
            checkThreadSafety();
            this.mContent.setUIClient(client);
        }
    }

    @XWalkAPI
    public void setResourceClient(XWalkResourceClientInternal client) {
        if (this.mContent != null) {
            checkThreadSafety();
            this.mContent.setResourceClient(client);
        }
    }

    @XWalkAPI
    public void setBackgroundColor(int color) {
        if (this.mContent != null) {
            checkThreadSafety();
            this.mContent.setBackgroundColor(color);
        }
    }

    @XWalkAPI
    public void setLayerType(int layerType, Paint paint) {
        if (layerType != 1) {
            super.setLayerType(layerType, paint);
        } else {
            Log.w(TAG, "LAYER_TYPE_SOFTWARE is not supported by XwalkView");
        }
    }

    public XWalkSettings getSettings() {
        if (this.mContent == null) {
            return null;
        }
        checkThreadSafety();
        return this.mContent.getSettings();
    }

    @XWalkAPI
    public void setNetworkAvailable(boolean networkUp) {
        if (this.mContent != null) {
            checkThreadSafety();
            this.mContent.setNetworkAvailable(networkUp);
        }
    }

    public void enableRemoteDebugging() {
        if (this.mContent != null) {
            checkThreadSafety();
            this.mContent.enableRemoteDebugging();
        }
    }

    @XWalkAPI
    public Uri getRemoteDebuggingUrl() {
        if (this.mContent == null) {
            return null;
        }
        checkThreadSafety();
        String wsUrl = this.mContent.getRemoteDebuggingUrl();
        if (wsUrl == null || wsUrl.isEmpty()) {
            return null;
        }
        return Uri.parse(wsUrl);
    }

    public int getContentID() {
        if (this.mContent == null) {
            return -1;
        }
        return this.mContent.getRoutingID();
    }

    /* access modifiers changed from: package-private */
    public boolean canGoBack() {
        if (this.mContent == null) {
            return false;
        }
        checkThreadSafety();
        return this.mContent.canGoBack();
    }

    /* access modifiers changed from: package-private */
    public void goBack() {
        if (this.mContent != null) {
            checkThreadSafety();
            this.mContent.goBack();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean canGoForward() {
        if (this.mContent == null) {
            return false;
        }
        checkThreadSafety();
        return this.mContent.canGoForward();
    }

    /* access modifiers changed from: package-private */
    public void goForward() {
        if (this.mContent != null) {
            checkThreadSafety();
            this.mContent.goForward();
        }
    }

    /* access modifiers changed from: package-private */
    public void clearHistory() {
        if (this.mContent != null) {
            checkThreadSafety();
            this.mContent.clearHistory();
        }
    }

    /* access modifiers changed from: package-private */
    public void destroy() {
        if (this.mContent != null) {
            ApplicationStatus.unregisterActivityStateListener(this.mActivityStateListener);
            this.mActivityStateListener = null;
            this.mContent.destroy();
            disableRemoteDebugging();
        }
    }

    /* access modifiers changed from: package-private */
    public void disableRemoteDebugging() {
        if (this.mContent != null) {
            checkThreadSafety();
            this.mContent.disableRemoteDebugging();
        }
    }

    private static void checkThreadSafety() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new RuntimeException(new Throwable("Warning: A XWalkViewInternal method was called on thread '" + Thread.currentThread().getName() + "'. " + "All XWalkViewInternal methods must be called on the UI thread. "));
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isOwnerActivityRunning() {
        if (ApplicationStatus.getStateForActivity(getActivity()) == 6) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void navigateTo(int offset) {
        if (this.mContent != null) {
            this.mContent.navigateTo(offset);
        }
    }

    /* access modifiers changed from: package-private */
    public void setOverlayVideoMode(boolean enabled) {
        this.mContent.setOverlayVideoMode(enabled);
    }

    public void setXWalkClient(XWalkClient client) {
        if (this.mContent != null) {
            checkThreadSafety();
            this.mContent.setXWalkClient(client);
        }
    }

    public void setXWalkWebChromeClient(XWalkWebChromeClient client) {
        if (this.mContent != null) {
            checkThreadSafety();
            this.mContent.setXWalkWebChromeClient(client);
        }
    }

    public void setDownloadListener(DownloadListener listener) {
        if (this.mContent != null) {
            checkThreadSafety();
            this.mContent.setDownloadListener(listener);
        }
    }

    public void setNavigationHandler(XWalkNavigationHandler handler) {
        if (this.mContent != null) {
            checkThreadSafety();
            this.mContent.setNavigationHandler(handler);
        }
    }

    public void setNotificationService(XWalkNotificationService service) {
        if (this.mContent != null) {
            checkThreadSafety();
            this.mContent.setNotificationService(service);
        }
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == 1 && event.getKeyCode() == 4) {
            if (hasEnteredFullscreen()) {
                leaveFullscreen();
                return true;
            } else if (canGoBack()) {
                goBack();
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    /* access modifiers changed from: private */
    public void onActivityStateChange(Activity activity, int newState) {
        if ($assertionsDisabled || getActivity() == activity) {
            switch (newState) {
                case 2:
                    onShow();
                    return;
                case 3:
                    resumeTimers();
                    return;
                case 4:
                    pauseTimers();
                    return;
                case 5:
                    onHide();
                    return;
                case 6:
                    onDestroy();
                    return;
                default:
                    return;
            }
        } else {
            throw new AssertionError();
        }
    }

    public boolean showFileChooser(ValueCallback<Uri> uploadFile, String acceptType, String capture) {
        this.mFilePathCallback = uploadFile;
        Intent takePictureIntent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
                takePictureIntent.putExtra("PhotoPath", this.mCameraPhotoPath);
            } catch (IOException ex) {
                Log.e(TAG, "Unable to create Image File", ex);
            }
            if (photoFile != null) {
                this.mCameraPhotoPath = "file:" + photoFile.getAbsolutePath();
                takePictureIntent.putExtra("output", Uri.fromFile(photoFile));
            } else {
                takePictureIntent = null;
            }
        }
        Intent contentSelectionIntent = new Intent("android.intent.action.GET_CONTENT");
        contentSelectionIntent.addCategory("android.intent.category.OPENABLE");
        contentSelectionIntent.setType("*/*");
        Intent camcorder = new Intent("android.media.action.VIDEO_CAPTURE");
        Intent soundRecorder = new Intent("android.provider.MediaStore.RECORD_SOUND");
        ArrayList<Intent> extraIntents = new ArrayList<>();
        extraIntents.add(takePictureIntent);
        extraIntents.add(camcorder);
        extraIntents.add(soundRecorder);
        Intent chooserIntent = new Intent("android.intent.action.CHOOSER");
        chooserIntent.putExtra("android.intent.extra.INTENT", contentSelectionIntent);
        chooserIntent.putExtra("android.intent.extra.TITLE", "Choose an action");
        chooserIntent.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) extraIntents.toArray(new Intent[0]));
        getActivity().startActivityForResult(chooserIntent, 1);
        return true;
    }

    private File createImageFile() throws IOException {
        return File.createTempFile("JPEG_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + "_", ".jpg", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
    }

    public ContentViewCore getXWalkContentForTest() {
        return this.mContent.getContentViewCoreForTest();
    }
}
