package org.xwalk.core.internal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.http.SslError;
import android.os.Message;
import android.widget.EditText;
import android.widget.LinearLayout;

public class XWalkClient {
    private Context mContext;
    private XWalkViewInternal mXWalkView;

    public XWalkClient(XWalkViewInternal view) {
        this.mContext = view.getContext();
        this.mXWalkView = view;
    }

    public void onRendererUnresponsive(XWalkViewInternal view) {
    }

    public void onRendererResponsive(XWalkViewInternal view) {
    }

    @Deprecated
    public void onTooManyRedirects(XWalkViewInternal view, Message cancelMsg, Message continueMsg) {
        cancelMsg.sendToTarget();
    }

    public void onFormResubmission(XWalkViewInternal view, Message dontResend, Message resend) {
        dontResend.sendToTarget();
    }

    public void doUpdateVisitedHistory(XWalkViewInternal view, String url, boolean isReload) {
    }

    public void onProceededAfterSslError(XWalkViewInternal view, SslError error) {
    }

    public void onReceivedHttpAuthRequest(XWalkViewInternal view, XWalkHttpAuthHandler handler, String host, String realm) {
        if (view != null) {
            final XWalkHttpAuthHandler haHandler = handler;
            LinearLayout layout = new LinearLayout(this.mContext);
            final EditText userNameEditText = new EditText(this.mContext);
            final EditText passwordEditText = new EditText(this.mContext);
            layout.setOrientation(1);
            layout.setPaddingRelative(10, 0, 10, 20);
            userNameEditText.setHint(R.string.http_auth_user_name);
            passwordEditText.setHint(R.string.http_auth_password);
            layout.addView(userNameEditText);
            layout.addView(passwordEditText);
            new AlertDialog.Builder(this.mXWalkView.getActivity()).setTitle(R.string.http_auth_title).setView(layout).setCancelable(false).setPositiveButton(R.string.http_auth_log_in, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    haHandler.proceed(userNameEditText.getText().toString(), passwordEditText.getText().toString());
                    dialog.dismiss();
                }
            }).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialog) {
                    haHandler.cancel();
                }
            }).create().show();
        }
    }

    public void onReceivedLoginRequest(XWalkViewInternal view, String realm, String account, String args) {
    }

    public void onLoadResource(XWalkViewInternal view, String url) {
    }
}
