package org.xwalk.core.internal.extension.api.contacts;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.util.Log;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import org.xwalk.core.internal.extension.XWalkExtensionWithActivityStateListener;

public class Contacts extends XWalkExtensionWithActivityStateListener {
    public static final String JS_API_PATH = "jsapi/contacts_api.js";
    private static final String NAME = "xwalk.experimental.contacts";
    private static final String TAG = "Contacts";
    private final ContactEventListener mObserver = new ContactEventListener(new Handler(), this, this.mResolver);
    private final ContentResolver mResolver;

    public Contacts(String jsApiContent, Activity activity) {
        super(NAME, jsApiContent, activity);
        this.mResolver = activity.getContentResolver();
        this.mResolver.registerContentObserver(ContactsContract.Contacts.CONTENT_URI, true, this.mObserver);
    }

    public String onSyncMessage(int instanceID, String message) {
        return null;
    }

    public void onMessage(int instanceID, String message) {
        if (!message.isEmpty()) {
            try {
                JSONObject jsonInput = new JSONObject(message);
                String cmd = jsonInput.getString("cmd");
                if (cmd.equals("addEventListener")) {
                    this.mObserver.startListening();
                    return;
                }
                JSONObject jsonOutput = new JSONObject();
                jsonOutput.put("asyncCallId", jsonInput.getString("asyncCallId"));
                if (cmd.equals("save")) {
                    jsonOutput.put("data", new ContactSaver(this.mResolver).save(jsonInput.getString("contact")));
                } else if (cmd.equals("find")) {
                    jsonOutput.put("data", new ContactFinder(this.mResolver).find(jsonInput.has("options") ? jsonInput.getString("options") : null));
                } else if (cmd.equals(ProductAction.ACTION_REMOVE)) {
                    ArrayList<ContentProviderOperation> ops = new ArrayList<>();
                    ops.add(ContentProviderOperation.newDelete(ContactsContract.RawContacts.CONTENT_URI).withSelection("contact_id=?", new String[]{jsonInput.getString("contactId")}).build());
                    this.mResolver.applyBatch("com.android.contacts", ops);
                } else if (cmd.equals("clear")) {
                    handleClear();
                } else {
                    Log.e(TAG, "Unexpected message received: " + message);
                    return;
                }
                postMessage(instanceID, jsonOutput.toString());
            } catch (Exception e) {
                if ((e instanceof RemoteException) || (e instanceof OperationApplicationException) || (e instanceof SecurityException)) {
                    Log.e(TAG, "onMessage - Failed to apply batch: " + e.toString());
                    return;
                }
                throw new RuntimeException(e);
            } catch (JSONException e2) {
                Log.e(TAG, e2.toString());
            }
        }
    }

    private void handleClear() {
        Cursor c = null;
        try {
            Cursor c2 = this.mResolver.query(ContactsContract.Contacts.CONTENT_URI, (String[]) null, (String) null, (String[]) null, (String) null);
            while (c2.moveToNext()) {
                this.mResolver.delete(Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, c2.getString(c2.getColumnIndex("lookup"))), (String) null, (String[]) null);
            }
            if (c2 != null) {
                c2.close();
            }
        } catch (SecurityException e) {
            Log.e(TAG, "handleClear - failed to query: " + e.toString());
            if (c != null) {
                c.close();
            }
        } catch (Throwable th) {
            if (c != null) {
                c.close();
            }
            throw th;
        }
    }

    public void onActivityStateChange(Activity activity, int newState) {
        switch (newState) {
            case 3:
                this.mObserver.onResume();
                this.mResolver.registerContentObserver(ContactsContract.Contacts.CONTENT_URI, true, this.mObserver);
                return;
            case 4:
            case 6:
                this.mResolver.unregisterContentObserver(this.mObserver);
                return;
            default:
                return;
        }
    }
}
