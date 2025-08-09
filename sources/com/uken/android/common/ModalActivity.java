package com.uken.android.common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.ValueCallback;
import android.widget.RelativeLayout;
import com.uken.android.forcesofwar.R;
import org.xwalk.core.internal.XWalkResourceClientInternal;
import org.xwalk.core.internal.XWalkViewInternal;

@SuppressLint({"SetJavaScriptEnabled"})
public class ModalActivity extends Activity {
    public static final String CALLBACK = "callback";
    Activity activity = this;
    /* access modifiers changed from: private */
    public XWalkViewInternal mWebView = null;
    ProgressDialog progressDialog;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(R.layout.modal);
        this.progressDialog = new ProgressDialog(this);
        this.progressDialog.setProgressStyle(1);
        this.progressDialog.setCancelable(false);
        this.progressDialog.show();
        this.mWebView = (XWalkViewInternal) findViewById(R.id.webView);
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
        this.mWebView.getSettings().setSupportMultipleWindows(false);
        this.mWebView.setVerticalScrollBarEnabled(true);
        this.mWebView.setHorizontalScrollBarEnabled(false);
        this.mWebView.setXWalkClient(new UkenXWalkClient(this.mWebView));
        this.mWebView.setResourceClient(new XWalkResourceClientInternal(this.mWebView) {
            public void onLoadStarted(XWalkViewInternal view, String url) {
                if (url.contains("/command.modalwebview.hide/")) {
                    ModalActivity.this.slideOutTransition(Uri.parse(url).getQueryParameter(ModalActivity.CALLBACK));
                }
            }

            public boolean shouldOverrideUrlLoading(XWalkViewInternal view, String url) {
                if (url.contains("/command.")) {
                    return true;
                }
                return false;
            }

            public void onLoadFinished(XWalkViewInternal view, String url) {
                super.onLoadFinished(view, url);
                if (ModalActivity.this.progressDialog.isShowing()) {
                    ModalActivity.this.progressDialog.dismiss();
                }
            }

            public void onProgressChanged(XWalkViewInternal view, int progressInPercent) {
                super.onProgressChanged(view, progressInPercent);
                ModalActivity.this.progressDialog.setProgress(progressInPercent);
            }
        });
        this.mWebView.setBackgroundColor(UkenActivity.MODAL_BACKGROUND_COLOR);
        loadUrl(getIntent().getExtras().getString(UkenActivity.MODAL_URL_STRING));
        slideInTransition();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4) {
            return super.onKeyDown(keyCode, event);
        }
        this.mWebView.evaluateJavascript("closeMessageCenter()", (ValueCallback<String>) null);
        return true;
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
    }

    public void loadUrl(String urlString) {
        if (this.mWebView != null) {
            this.mWebView.load(urlString, (String) null);
        }
    }

    public void slideInTransition() {
        ((RelativeLayout) this.activity.findViewById(R.id.MainRelativeLayout)).startAnimation(AnimationUtils.loadAnimation(this.activity.getBaseContext(), R.anim.slidein));
    }

    public void slideOutTransition(final String callback) {
        Animation animation = AnimationUtils.loadAnimation(this.activity.getBaseContext(), R.anim.slideout);
        animation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                ModalActivity.this.mWebView.setVisibility(8);
                Intent resultIntent = new Intent();
                resultIntent.putExtra(ModalActivity.CALLBACK, callback);
                ModalActivity.this.activity.setResult(-1, resultIntent);
                ModalActivity.this.activity.finish();
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
        ((RelativeLayout) this.activity.findViewById(R.id.MainRelativeLayout)).startAnimation(animation);
    }
}
