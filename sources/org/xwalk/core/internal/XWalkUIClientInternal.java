package org.xwalk.core.internal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ValueCallback;
import android.widget.EditText;

@XWalkAPI(createExternally = true)
public class XWalkUIClientInternal {
    static final /* synthetic */ boolean $assertionsDisabled = (!XWalkUIClientInternal.class.desiredAssertionStatus());
    private static String mCancelButton;
    private static String mJSAlertTitle;
    private static String mJSConfirmTitle;
    private static String mJSPromptTitle;
    private static String mOKButton;
    private Context mContext;
    private View mDecorView;
    private AlertDialog mDialog;
    private boolean mIsFullscreen = false;
    private boolean mOriginalForceNotFullscreen;
    private boolean mOriginalFullscreen;
    /* access modifiers changed from: private */
    public EditText mPromptText;
    private int mSystemUiFlag;
    private XWalkViewInternal mXWalkView;

    @XWalkAPI
    public enum InitiateByInternal {
        BY_USER_GESTURE,
        BY_JAVASCRIPT
    }

    @XWalkAPI
    public enum JavascriptMessageTypeInternal {
        JAVASCRIPT_ALERT,
        JAVASCRIPT_CONFIRM,
        JAVASCRIPT_PROMPT,
        JAVASCRIPT_BEFOREUNLOAD
    }

    @XWalkAPI
    public enum LoadStatusInternal {
        FINISHED,
        FAILED,
        CANCELLED
    }

    @XWalkAPI
    public XWalkUIClientInternal(XWalkViewInternal view) {
        this.mContext = view.getContext();
        this.mDecorView = view.getActivity().getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= 19) {
            this.mSystemUiFlag = 1792;
        }
        this.mXWalkView = view;
        initResources();
    }

    private void initResources() {
        if (mJSAlertTitle == null) {
            mJSAlertTitle = this.mContext.getString(R.string.js_alert_title);
            mJSConfirmTitle = this.mContext.getString(R.string.js_confirm_title);
            mJSPromptTitle = this.mContext.getString(R.string.js_prompt_title);
            mOKButton = this.mContext.getString(17039370);
            mCancelButton = this.mContext.getString(17039360);
        }
    }

    @XWalkAPI
    public boolean onCreateWindowRequested(XWalkViewInternal view, InitiateByInternal initiator, ValueCallback<XWalkViewInternal> valueCallback) {
        return false;
    }

    @XWalkAPI
    public void onIconAvailable(XWalkViewInternal view, String url, Message startDownload) {
    }

    @XWalkAPI
    public void onReceivedIcon(XWalkViewInternal view, String url, Bitmap icon) {
    }

    @XWalkAPI
    public void onRequestFocus(XWalkViewInternal view) {
    }

    @XWalkAPI
    public void onJavascriptCloseWindow(XWalkViewInternal view) {
        if (view != null && view.getActivity() != null) {
            view.getActivity().finish();
        }
    }

    @XWalkAPI
    public boolean onJavascriptModalDialog(XWalkViewInternal view, JavascriptMessageTypeInternal type, String url, String message, String defaultValue, XWalkJavascriptResultInternal result) {
        switch (type) {
            case JAVASCRIPT_ALERT:
                return onJsAlert(view, url, message, result);
            case JAVASCRIPT_CONFIRM:
                return onJsConfirm(view, url, message, result);
            case JAVASCRIPT_PROMPT:
                return onJsPrompt(view, url, message, defaultValue, result);
            case JAVASCRIPT_BEFOREUNLOAD:
                return onJsConfirm(view, url, message, result);
            default:
                if ($assertionsDisabled) {
                    return false;
                }
                throw new AssertionError();
        }
    }

    @XWalkAPI
    public void onFullscreenToggled(XWalkViewInternal view, boolean enterFullscreen) {
        Activity activity = view.getActivity();
        if (enterFullscreen) {
            if ((activity.getWindow().getAttributes().flags & 2048) != 0) {
                this.mOriginalForceNotFullscreen = true;
                activity.getWindow().clearFlags(2048);
            } else {
                this.mOriginalForceNotFullscreen = false;
            }
            if (!this.mIsFullscreen) {
                if (Build.VERSION.SDK_INT >= 19) {
                    this.mSystemUiFlag = this.mDecorView.getSystemUiVisibility();
                    this.mDecorView.setSystemUiVisibility(5894);
                } else if ((activity.getWindow().getAttributes().flags & 1024) != 0) {
                    this.mOriginalFullscreen = true;
                } else {
                    this.mOriginalFullscreen = false;
                    activity.getWindow().addFlags(1024);
                }
                this.mIsFullscreen = true;
                return;
            }
            return;
        }
        if (this.mOriginalForceNotFullscreen) {
            activity.getWindow().addFlags(2048);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            this.mDecorView.setSystemUiVisibility(this.mSystemUiFlag);
        } else if (!this.mOriginalFullscreen) {
            activity.getWindow().clearFlags(1024);
        }
        this.mIsFullscreen = false;
    }

    @XWalkAPI
    public void openFileChooser(XWalkViewInternal view, ValueCallback<Uri> uploadFile, String acceptType, String capture) {
        uploadFile.onReceiveValue((Object) null);
    }

    @XWalkAPI
    public void onScaleChanged(XWalkViewInternal view, float oldScale, float newScale) {
    }

    @XWalkAPI
    public boolean shouldOverrideKeyEvent(XWalkViewInternal view, KeyEvent event) {
        return false;
    }

    @XWalkAPI
    public void onUnhandledKeyEvent(XWalkViewInternal view, KeyEvent event) {
    }

    @XWalkAPI
    public void onReceivedTitle(XWalkViewInternal view, String title) {
    }

    @XWalkAPI
    public void onPageLoadStarted(XWalkViewInternal view, String url) {
    }

    @XWalkAPI
    public void onPageLoadStopped(XWalkViewInternal view, String url, LoadStatusInternal status) {
    }

    private boolean onJsAlert(XWalkViewInternal view, String url, String message, XWalkJavascriptResultInternal result) {
        final XWalkJavascriptResultInternal fResult = result;
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this.mContext);
        dialogBuilder.setTitle(mJSAlertTitle).setMessage(message).setCancelable(true).setPositiveButton(mOKButton, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                fResult.confirm();
                dialog.dismiss();
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                fResult.cancel();
            }
        });
        this.mDialog = dialogBuilder.create();
        this.mDialog.show();
        return false;
    }

    private boolean onJsConfirm(XWalkViewInternal view, String url, String message, XWalkJavascriptResultInternal result) {
        final XWalkJavascriptResultInternal fResult = result;
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this.mContext);
        dialogBuilder.setTitle(mJSConfirmTitle).setMessage(message).setCancelable(true).setPositiveButton(mOKButton, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                fResult.confirm();
                dialog.dismiss();
            }
        }).setNegativeButton(mCancelButton, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                fResult.cancel();
            }
        });
        this.mDialog = dialogBuilder.create();
        this.mDialog.show();
        return false;
    }

    private boolean onJsPrompt(XWalkViewInternal view, String url, String message, String defaultValue, XWalkJavascriptResultInternal result) {
        final XWalkJavascriptResultInternal fResult = result;
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this.mContext);
        dialogBuilder.setTitle(mJSPromptTitle).setMessage(message).setPositiveButton(mOKButton, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                fResult.confirmWithResult(XWalkUIClientInternal.this.mPromptText.getText().toString());
                dialog.dismiss();
            }
        }).setNegativeButton(mCancelButton, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                fResult.cancel();
            }
        });
        this.mPromptText = new EditText(this.mContext);
        this.mPromptText.setVisibility(0);
        this.mPromptText.setText(defaultValue);
        this.mPromptText.selectAll();
        dialogBuilder.setView(this.mPromptText);
        this.mDialog = dialogBuilder.create();
        this.mDialog.show();
        return false;
    }
}
