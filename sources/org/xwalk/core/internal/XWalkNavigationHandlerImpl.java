package org.xwalk.core.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.MimeTypeMap;
import org.chromium.components.navigation_interception.NavigationParams;
import org.xwalk.core.internal.extension.api.messaging.MessagingSmsConsts;

public class XWalkNavigationHandlerImpl implements XWalkNavigationHandler {
    private static final String ACTION_GEO_PREFIX = "geo:";
    private static final String ACTION_MAIL_PREFIX = "mailto:";
    private static final String ACTION_MARKET_PREFIX = "market:";
    private static final String ACTION_SMS_PREFIX = "sms:";
    private static final String ACTION_TEL_PREFIX = "tel:";
    private static final String PROTOCOL_WTAI_MC_PREFIX = "wtai://wp/mc;";
    private static final String PROTOCOL_WTAI_PREFIX = "wtai://";
    private static final String TAG = "XWalkNavigationHandlerImpl";
    private Context mContext;

    public XWalkNavigationHandlerImpl(Context context) {
        this.mContext = context;
    }

    public boolean handleNavigation(NavigationParams params) {
        Intent intent;
        String url = params.url;
        if (url.startsWith(PROTOCOL_WTAI_PREFIX)) {
            intent = createIntentForWTAI(url);
        } else {
            intent = createIntentForActionUri(url);
        }
        if (intent == null || !startActivity(intent)) {
            return handleUrlByMimeType(url);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean startActivity(Intent intent) {
        try {
            if (!(this.mContext instanceof Activity)) {
                intent.addFlags(268435456);
            }
            this.mContext.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e) {
            Log.w(TAG, "Activity not found for Intent:");
            Log.w(TAG, intent.toUri(0));
            return false;
        }
    }

    private Intent createIntentForWTAI(String url) {
        if (!url.startsWith(PROTOCOL_WTAI_MC_PREFIX)) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.DIAL");
        intent.setData(Uri.parse(ACTION_TEL_PREFIX + url.substring(PROTOCOL_WTAI_MC_PREFIX.length())));
        return intent;
    }

    private Intent createIntentForActionUri(String url) {
        String address;
        if (url.startsWith(ACTION_TEL_PREFIX)) {
            Intent intent = new Intent("android.intent.action.DIAL");
            intent.setData(Uri.parse(url));
            return intent;
        } else if (url.startsWith(ACTION_GEO_PREFIX)) {
            Intent intent2 = new Intent("android.intent.action.VIEW");
            intent2.setData(Uri.parse(url));
            return intent2;
        } else if (url.startsWith(ACTION_MAIL_PREFIX)) {
            Intent intent3 = new Intent("android.intent.action.VIEW");
            intent3.setData(Uri.parse(url));
            return intent3;
        } else if (url.startsWith(ACTION_SMS_PREFIX)) {
            Intent intent4 = new Intent("android.intent.action.VIEW");
            int parmIndex = url.indexOf(63);
            if (parmIndex == -1) {
                address = url.substring(4);
            } else {
                address = url.substring(4, parmIndex);
                String query = Uri.parse(url).getQuery();
                if (query != null && query.startsWith("body=")) {
                    intent4.putExtra("sms_body", query.substring(5));
                }
            }
            intent4.setData(Uri.parse(ACTION_SMS_PREFIX + address));
            intent4.putExtra(MessagingSmsConsts.ADDRESS, address);
            intent4.setType("vnd.android-dir/mms-sms");
            return intent4;
        } else if (!url.startsWith(ACTION_MARKET_PREFIX)) {
            return null;
        } else {
            Intent intent5 = new Intent("android.intent.action.VIEW");
            intent5.setData(Uri.parse(url));
            return intent5;
        }
    }

    private boolean handleUrlByMimeType(String url) {
        String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(url));
        if (shouldHandleMimeType(mimeType)) {
            Intent sendIntent = new Intent();
            sendIntent.setAction("android.intent.action.VIEW");
            sendIntent.setDataAndType(Uri.parse(url), mimeType);
            if (sendIntent.resolveActivity(this.mContext.getPackageManager()) != null) {
                startActivity(sendIntent);
                return true;
            }
        }
        return false;
    }

    private boolean shouldHandleMimeType(String mimeType) {
        if (mimeType == null || !mimeType.startsWith("application/") || mimeType == "application/xhtml+xml" || mimeType == "application/xml") {
            return false;
        }
        return true;
    }
}
