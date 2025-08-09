package org.xwalk.core.internal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.http.SslError;
import android.webkit.ValueCallback;
import android.webkit.WebResourceResponse;

@XWalkAPI(createExternally = true)
public class XWalkResourceClientInternal {
    @XWalkAPI
    public static final int ERROR_AUTHENTICATION = -4;
    @XWalkAPI
    public static final int ERROR_BAD_URL = -12;
    @XWalkAPI
    public static final int ERROR_CONNECT = -6;
    @XWalkAPI
    public static final int ERROR_FAILED_SSL_HANDSHAKE = -11;
    @XWalkAPI
    public static final int ERROR_FILE = -13;
    @XWalkAPI
    public static final int ERROR_FILE_NOT_FOUND = -14;
    @XWalkAPI
    public static final int ERROR_HOST_LOOKUP = -2;
    @XWalkAPI
    public static final int ERROR_IO = -7;
    @XWalkAPI
    public static final int ERROR_OK = 0;
    @XWalkAPI
    public static final int ERROR_PROXY_AUTHENTICATION = -5;
    @XWalkAPI
    public static final int ERROR_REDIRECT_LOOP = -9;
    @XWalkAPI
    public static final int ERROR_TIMEOUT = -8;
    @XWalkAPI
    public static final int ERROR_TOO_MANY_REQUESTS = -15;
    @XWalkAPI
    public static final int ERROR_UNKNOWN = -1;
    @XWalkAPI
    public static final int ERROR_UNSUPPORTED_AUTH_SCHEME = -3;
    @XWalkAPI
    public static final int ERROR_UNSUPPORTED_SCHEME = -10;

    @XWalkAPI
    public XWalkResourceClientInternal(XWalkViewInternal view) {
    }

    @XWalkAPI
    public void onLoadStarted(XWalkViewInternal view, String url) {
    }

    @XWalkAPI
    public void onLoadFinished(XWalkViewInternal view, String url) {
    }

    @XWalkAPI
    public void onProgressChanged(XWalkViewInternal view, int progressInPercent) {
    }

    @XWalkAPI
    public WebResourceResponse shouldInterceptLoadRequest(XWalkViewInternal view, String url) {
        return null;
    }

    @XWalkAPI
    public void onReceivedLoadError(XWalkViewInternal view, int errorCode, String description, String failingUrl) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(view.getContext());
        dialogBuilder.setTitle(17039380).setMessage(description).setCancelable(false).setPositiveButton(17039370, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogBuilder.create().show();
    }

    @XWalkAPI
    public boolean shouldOverrideUrlLoading(XWalkViewInternal view, String url) {
        return false;
    }

    @XWalkAPI
    public void onReceivedSslError(XWalkViewInternal view, ValueCallback<Boolean> callback, SslError error) {
        final ValueCallback<Boolean> valueCallback = callback;
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(view.getContext());
        dialogBuilder.setTitle(R.string.ssl_alert_title).setPositiveButton(17039370, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                valueCallback.onReceiveValue(true);
                dialog.dismiss();
            }
        }).setNegativeButton(17039360, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                valueCallback.onReceiveValue(false);
                dialog.dismiss();
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                valueCallback.onReceiveValue(false);
            }
        });
        dialogBuilder.create().show();
    }
}
