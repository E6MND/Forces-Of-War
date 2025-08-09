package com.aarki;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class AarkiOfferActivity extends Activity {
    private WebView a;
    /* access modifiers changed from: private */
    public boolean b = false;
    private String c;

    public static class PlacementRequest implements Serializable {
        private static final long serialVersionUID = 1;
        String a;
        Map<String, String> b;
    }

    /* access modifiers changed from: package-private */
    public final boolean a(String str) {
        if (str == null || (!str.startsWith("market://") && !str.startsWith("ext-") && !str.endsWith(".apk"))) {
            return false;
        }
        Uri parse = Uri.parse(str);
        String scheme = parse.getScheme();
        if (scheme != null && scheme.startsWith("ext-")) {
            parse = Uri.parse(str.substring(4));
        }
        try {
            "Opening externally: " + str;
            startActivity(new Intent("android.intent.action.VIEW", parse));
        } catch (ActivityNotFoundException e) {
            String str2 = "Unable to open " + parse.getScheme() + ": URL.";
            if ("generic".equals(Build.BRAND)) {
                str2 = str2 + " This is a normal behavior for Android Emulator environment";
            }
            AlertDialog create = new AlertDialog.Builder(this).create();
            create.setTitle("Link Error");
            create.setMessage(str2);
            create.setButton(-3, "OK", new DialogInterface.OnClickListener(this) {
                public final void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            create.show();
        }
        return true;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        b.a(this, (String) null);
        this.a = new WebView(this);
        this.a.setScrollBarStyle(0);
        this.a.setWebViewClient(new WebViewClient() {
            public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
                return AarkiOfferActivity.this.a(str);
            }

            public final void onReceivedError(WebView webView, int i, String str, String str2) {
                "Error loading URL: " + str2 + " code:" + i;
                super.onReceivedError(webView, i, str, str2);
            }

            public final void onPageFinished(WebView webView, String str) {
                boolean z = str != null && str.startsWith("data:");
                if (z) {
                    webView.loadUrl("javascript:" + String.format("var hydraUrlParams = { platform: 'android', version: '%s' };", new Object[]{"2.7"}));
                    boolean unused = AarkiOfferActivity.this.b = false;
                    AarkiOfferActivity.this.a();
                }
                StringBuilder sb = new StringBuilder("Loaded page with URL: ");
                if (z) {
                    str = "data";
                }
                sb.append(str).toString();
            }
        });
        this.a.addJavascriptInterface(new HydraJsInterface(), "hydra");
        WebSettings settings = this.a.getSettings();
        settings.setSavePassword(false);
        settings.setJavaScriptEnabled(true);
        settings.setPluginsEnabled(true);
        settings.setSupportZoom(false);
        setContentView(this.a);
        PlacementRequest placementRequest = (PlacementRequest) getIntent().getSerializableExtra("com.aarkiAarkiOfferActivity.EXTRA_AARKI_PLACEMENT_REQUEST");
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("src", placementRequest.a));
        arrayList.add(new BasicNameValuePair("xbtn", "y"));
        arrayList.add(new BasicNameValuePair("platform", "android"));
        if (placementRequest.b != null) {
            for (Map.Entry next : placementRequest.b.entrySet()) {
                arrayList.add(new BasicNameValuePair((String) next.getKey(), (String) next.getValue()));
            }
        }
        a.a((List<NameValuePair>) arrayList);
        this.c = c.a("http://hs.aarki.net/adpick/garden", arrayList);
        a();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (this.a.canGoBack()) {
                if (!(this.c != null && this.c.equals(this.a.getUrl()))) {
                    this.a.goBack();
                    return true;
                }
            } else {
                AarkiContact.restartRewardListener();
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    /* access modifiers changed from: package-private */
    public final void a() {
        String str;
        if (this.b || (str = this.c) == null || a(str)) {
            return;
        }
        if (URLUtil.isHttpUrl(str) || URLUtil.isHttpsUrl(str)) {
            this.a.loadUrl(str);
            return;
        }
        "Invalid URL: " + str;
        AlertDialog create = new AlertDialog.Builder(this).create();
        create.setTitle("Service Error");
        create.setMessage("The service is currently not available. Please try again later." + " Error code: " + "IU");
        create.setButton(-3, "Exit", new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                AarkiOfferActivity.this.finish();
            }
        });
        create.show();
    }

    public static Intent intentForPlacement(Activity activity, String str, Map<String, String> map) {
        b.a(activity, (String) null);
        PlacementRequest placementRequest = new PlacementRequest();
        placementRequest.a = str;
        placementRequest.b = map;
        Intent intent = new Intent(activity, AarkiOfferActivity.class);
        intent.putExtra("com.aarkiAarkiOfferActivity.EXTRA_AARKI_PLACEMENT_REQUEST", placementRequest);
        return intent;
    }

    public static void launch(Activity activity, String str, Map<String, String> map) {
        activity.startActivity(intentForPlacement(activity, str, map));
    }

    public static void launch(Activity activity, String str) {
        launch(activity, str, (Map<String, String>) null);
    }

    public final class HydraJsInterface {
        public HydraJsInterface() {
        }

        public final void exit() {
            AarkiContact.restartRewardListener();
            AarkiOfferActivity.this.finish();
        }
    }
}
