package org.xwalk.core.internal.extension.api.presentation;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonWriter;
import android.util.Log;
import android.view.Display;
import android.view.ViewGroup;
import com.facebook.internal.ServerProtocol;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.ref.WeakReference;
import java.net.URI;
import java.net.URISyntaxException;
import org.chromium.base.ThreadUtils;
import org.xwalk.core.internal.extension.XWalkExtensionWithActivityStateListener;
import org.xwalk.core.internal.extension.api.XWalkDisplayManager;
import org.xwalk.core.internal.extension.api.presentation.PresentationView;
import org.xwalk.core.internal.extension.api.presentation.XWalkPresentationContent;

public class PresentationExtension extends XWalkExtensionWithActivityStateListener {
    private static final String CMD_DISPLAY_AVAILABLE_CHANGE = "DisplayAvailableChange";
    private static final String CMD_QUERY_DISPLAY_AVAILABILITY = "QueryDisplayAvailability";
    private static final String CMD_REQUEST_SHOW = "RequestShow";
    private static final String CMD_SHOW_FAILED = "ShowFailed";
    private static final String CMD_SHOW_SUCCEEDED = "ShowSucceeded";
    private static final String ERROR_INVALID_ACCESS = "InvalidAccessError";
    private static final String ERROR_INVALID_PARAMETER = "InvalidParameterError";
    private static final String ERROR_INVALID_STATE = "InvalidStateError";
    private static final String ERROR_NOT_FOUND = "NotFoundError";
    private static final String ERROR_NOT_SUPPORTED = "NotSupportedError";
    public static final String JS_API_PATH = "jsapi/presentation_api.js";
    private static final String NAME = "navigator.presentation";
    private static final String TAG = "PresentationExtension";
    private static final String TAG_BASE_URL = "baseUrl";
    private static final String TAG_CMD = "cmd";
    private static final String TAG_DATA = "data";
    private static final String TAG_REQUEST_ID = "requestId";
    private static final String TAG_URL = "url";
    /* access modifiers changed from: private */
    public WeakReference<Activity> mActivity;
    /* access modifiers changed from: private */
    public int mAvailableDisplayCount = 0;
    /* access modifiers changed from: private */
    public Context mContext;
    private final XWalkDisplayManager.DisplayListener mDisplayListener = new XWalkDisplayManager.DisplayListener() {
        public void onDisplayAdded(int displayId) {
            PresentationExtension.access$004(PresentationExtension.this);
            if (PresentationExtension.this.mAvailableDisplayCount == 1) {
                PresentationExtension.this.notifyAvailabilityChanged(true);
            }
        }

        public void onDisplayRemoved(int displayId) {
            PresentationExtension.access$006(PresentationExtension.this);
            if (PresentationExtension.this.mAvailableDisplayCount == 0) {
                PresentationExtension.this.notifyAvailabilityChanged(false);
                PresentationExtension.this.closePresentationContent();
            }
        }

        public void onDisplayChanged(int displayId) {
        }
    };
    private XWalkDisplayManager mDisplayManager;
    /* access modifiers changed from: private */
    public XWalkPresentationContent mPresentationContent;
    private XWalkPresentationContent.PresentationDelegate mPresentationDelegate;
    /* access modifiers changed from: private */
    public PresentationView mPresentationView;

    static /* synthetic */ int access$004(PresentationExtension x0) {
        int i = x0.mAvailableDisplayCount + 1;
        x0.mAvailableDisplayCount = i;
        return i;
    }

    static /* synthetic */ int access$006(PresentationExtension x0) {
        int i = x0.mAvailableDisplayCount - 1;
        x0.mAvailableDisplayCount = i;
        return i;
    }

    public PresentationExtension(String jsApi, Activity activity) {
        super(NAME, jsApi, activity);
        this.mContext = activity.getApplicationContext();
        this.mActivity = new WeakReference<>(activity);
        this.mDisplayManager = XWalkDisplayManager.getInstance(activity.getApplicationContext());
        this.mAvailableDisplayCount = this.mDisplayManager.getPresentationDisplays().length;
    }

    /* access modifiers changed from: private */
    public Display getPreferredDisplay() {
        Display[] displays = this.mDisplayManager.getPresentationDisplays();
        if (displays.length > 0) {
            return displays[0];
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void notifyAvailabilityChanged(boolean isAvailable) {
        StringWriter contents = new StringWriter();
        JsonWriter writer = new JsonWriter(contents);
        try {
            writer.beginObject();
            writer.name(TAG_CMD).value(CMD_DISPLAY_AVAILABLE_CHANGE);
            writer.name(TAG_DATA).value(isAvailable);
            writer.endObject();
            writer.close();
            broadcastMessage(contents.toString());
        } catch (IOException e) {
            Log.e(TAG, "Error: " + e.toString());
        }
    }

    /* access modifiers changed from: private */
    public void notifyRequestShowSucceed(int instanceId, int requestId, int presentationId) {
        StringWriter contents = new StringWriter();
        JsonWriter writer = new JsonWriter(contents);
        try {
            writer.beginObject();
            writer.name(TAG_CMD).value(CMD_SHOW_SUCCEEDED);
            writer.name(TAG_REQUEST_ID).value((long) requestId);
            writer.name(TAG_DATA).value((long) presentationId);
            writer.endObject();
            writer.close();
            postMessage(instanceId, contents.toString());
        } catch (IOException e) {
            Log.e(TAG, "Error: " + e.toString());
        }
    }

    /* access modifiers changed from: private */
    public void notifyRequestShowFail(int instanceId, int requestId, String errorMessage) {
        StringWriter contents = new StringWriter();
        JsonWriter writer = new JsonWriter(contents);
        try {
            writer.beginObject();
            writer.name(TAG_CMD).value(CMD_SHOW_FAILED);
            writer.name(TAG_REQUEST_ID).value((long) requestId);
            writer.name(TAG_DATA).value(errorMessage);
            writer.endObject();
            writer.close();
            postMessage(instanceId, contents.toString());
        } catch (IOException e) {
            Log.e(TAG, "Error: " + e.toString());
        }
    }

    public void onMessage(int instanceId, String message) {
        JsonReader reader = new JsonReader(new StringReader(message));
        int requestId = -1;
        String cmd = null;
        String url = null;
        String baseUrl = null;
        try {
            reader.beginObject();
            while (reader.hasNext()) {
                String name = reader.nextName();
                if (name.equals(TAG_CMD)) {
                    cmd = reader.nextString();
                } else if (name.equals(TAG_REQUEST_ID)) {
                    requestId = reader.nextInt();
                } else if (name.equals("url")) {
                    url = reader.nextString();
                } else if (name.equals(TAG_BASE_URL)) {
                    baseUrl = reader.nextString();
                } else {
                    reader.skipValue();
                }
            }
            reader.endObject();
            reader.close();
            if (cmd != null && cmd.equals(CMD_REQUEST_SHOW) && requestId >= 0) {
                handleRequestShow(instanceId, requestId, url, baseUrl);
            }
        } catch (IOException e) {
            Log.d(TAG, "Error: " + e);
        }
    }

    private void handleRequestShow(int instanceId, int requestId, String url, String baseUrl) {
        if (Build.VERSION.SDK_INT < 17) {
            notifyRequestShowFail(instanceId, requestId, ERROR_NOT_SUPPORTED);
        } else if (this.mAvailableDisplayCount == 0) {
            Log.d(TAG, "No available presentation display is found.");
            notifyRequestShowFail(instanceId, requestId, ERROR_NOT_FOUND);
        } else {
            final int i = instanceId;
            final int i2 = requestId;
            final String str = url;
            final String str2 = baseUrl;
            ThreadUtils.runOnUiThread((Runnable) new Runnable() {
                public void run() {
                    Display preferredDisplay = PresentationExtension.this.getPreferredDisplay();
                    if (preferredDisplay == null) {
                        PresentationExtension.this.notifyRequestShowFail(i, i2, PresentationExtension.ERROR_NOT_FOUND);
                    } else if (PresentationExtension.this.mPresentationContent != null) {
                        PresentationExtension.this.notifyRequestShowFail(i, i2, PresentationExtension.ERROR_INVALID_ACCESS);
                    } else {
                        String targetUrl = str;
                        try {
                            URI targetUri = new URI(str);
                            try {
                                if (!targetUri.isAbsolute()) {
                                    targetUrl = new URI(str2).resolve(targetUri).toString();
                                }
                                XWalkPresentationContent unused = PresentationExtension.this.mPresentationContent = new XWalkPresentationContent(PresentationExtension.this.mContext, PresentationExtension.this.mActivity, new XWalkPresentationContent.PresentationDelegate() {
                                    public void onContentLoaded(XWalkPresentationContent content) {
                                        PresentationExtension.this.notifyRequestShowSucceed(i, i2, content.getPresentationId());
                                    }

                                    public void onContentClosed(XWalkPresentationContent content) {
                                        if (content == PresentationExtension.this.mPresentationContent) {
                                            PresentationExtension.this.closePresentationContent();
                                            if (PresentationExtension.this.mPresentationView != null) {
                                                PresentationExtension.this.mPresentationView.cancel();
                                            }
                                        }
                                    }
                                });
                                PresentationExtension.this.mPresentationContent.load(targetUrl);
                                PresentationExtension.this.updatePresentationView(preferredDisplay);
                            } catch (URISyntaxException e) {
                                URI uri = targetUri;
                                Log.e(PresentationExtension.TAG, "Invalid url passed to requestShow");
                                PresentationExtension.this.notifyRequestShowFail(i, i2, PresentationExtension.ERROR_INVALID_PARAMETER);
                            }
                        } catch (URISyntaxException e2) {
                            Log.e(PresentationExtension.TAG, "Invalid url passed to requestShow");
                            PresentationExtension.this.notifyRequestShowFail(i, i2, PresentationExtension.ERROR_INVALID_PARAMETER);
                        }
                    }
                }
            });
        }
    }

    public String onSyncMessage(int instanceId, String message) {
        if (message.equals(CMD_QUERY_DISPLAY_AVAILABILITY)) {
            return this.mAvailableDisplayCount != 0 ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false";
        }
        Log.e(TAG, "Unexpected sync message received: " + message);
        return "";
    }

    public void onResume() {
        Display[] displays = this.mDisplayManager.getPresentationDisplays();
        if (displays.length == 0 && this.mAvailableDisplayCount > 0) {
            notifyAvailabilityChanged(false);
            this.mAvailableDisplayCount = 0;
            closePresentationContent();
        }
        if (displays.length > 0 && this.mAvailableDisplayCount == 0) {
            notifyAvailabilityChanged(true);
            this.mAvailableDisplayCount = displays.length;
        }
        if (displays.length > 0 && this.mAvailableDisplayCount > 0) {
            this.mAvailableDisplayCount = displays.length;
        }
        if (this.mPresentationContent != null) {
            this.mPresentationContent.onResume();
        }
        updatePresentationView(getPreferredDisplay());
        this.mDisplayManager.registerDisplayListener(this.mDisplayListener);
    }

    /* access modifiers changed from: private */
    public void updatePresentationView(Display preferredDisplay) {
        Activity activity = (Activity) this.mActivity.get();
        if (activity != null && Build.VERSION.SDK_INT >= 17 && preferredDisplay != null) {
            if (this.mPresentationView != null || this.mPresentationContent != null) {
                if (!(this.mPresentationView == null || this.mPresentationView.getDisplay() == preferredDisplay)) {
                    dismissPresentationView();
                }
                if (this.mPresentationView == null && this.mPresentationContent != null) {
                    ViewGroup parent = (ViewGroup) this.mPresentationContent.getContentView().getParent();
                    if (parent != null) {
                        parent.removeView(this.mPresentationContent.getContentView());
                    }
                    this.mPresentationView = PresentationView.createInstance(activity, preferredDisplay);
                    this.mPresentationView.setContentView(this.mPresentationContent.getContentView());
                    this.mPresentationView.setPresentationListener(new PresentationView.PresentationListener() {
                        public void onDismiss(PresentationView view) {
                            if (view == PresentationExtension.this.mPresentationView) {
                                if (PresentationExtension.this.mPresentationContent != null) {
                                    PresentationExtension.this.mPresentationContent.onPause();
                                }
                                PresentationView unused = PresentationExtension.this.mPresentationView = null;
                            }
                        }

                        public void onShow(PresentationView view) {
                            if (view == PresentationExtension.this.mPresentationView && PresentationExtension.this.mPresentationContent != null) {
                                PresentationExtension.this.mPresentationContent.onResume();
                            }
                        }
                    });
                }
                this.mPresentationView.show();
            }
        }
    }

    private void dismissPresentationView() {
        if (this.mPresentationView != null) {
            this.mPresentationView.dismiss();
            this.mPresentationView = null;
        }
    }

    /* access modifiers changed from: private */
    public void closePresentationContent() {
        if (this.mPresentationContent != null) {
            this.mPresentationContent.close();
            this.mPresentationContent = null;
        }
    }

    public void onActivityStateChange(Activity activity, int newState) {
        switch (newState) {
            case 3:
                onResume();
                return;
            case 4:
                dismissPresentationView();
                if (this.mPresentationContent != null) {
                    this.mPresentationContent.onPause();
                }
                this.mDisplayManager.unregisterDisplayListener(this.mDisplayListener);
                return;
            case 6:
                closePresentationContent();
                return;
            default:
                return;
        }
    }
}
