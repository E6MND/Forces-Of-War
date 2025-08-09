package org.xwalk.core.internal.extension.api.contacts;

import android.annotation.TargetApi;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.util.Log;
import com.facebook.AppEventsConstants;
import com.google.android.gms.plus.PlusShare;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class ContactUtils {
    private static final String TAG = "ContactUtils";
    public ContentResolver mResolver;

    public ContactUtils(ContentResolver resolver) {
        this.mResolver = resolver;
    }

    public static <K, V> K getKeyFromValue(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (value != null && value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static String makeQuestionMarkList(Set<String> strings) {
        String ret = "";
        for (int i = 0; i < strings.size(); i++) {
            ret = ret + "?,";
        }
        return ret.substring(0, ret.length() - 1);
    }

    public boolean hasID(String id) {
        if (id == null) {
            return false;
        }
        Cursor c = null;
        try {
            c = this.mResolver.query(ContactsContract.Contacts.CONTENT_URI, (String[]) null, "_id = ?", new String[]{id}, (String) null);
            boolean z = c.getCount() != 0;
            if (c != null) {
                c.close();
            }
            return z;
        } catch (SecurityException e) {
            Log.e(TAG, "hasID: " + e.toString());
            if (c == null) {
                return false;
            }
            c.close();
            return false;
        } catch (Throwable th) {
            if (c != null) {
                c.close();
            }
            throw th;
        }
    }

    public String getRawId(String id) {
        Cursor c = null;
        try {
            Cursor c2 = this.mResolver.query(ContactsContract.RawContacts.CONTENT_URI, new String[]{"_id"}, "contact_id=?", new String[]{id}, (String) null);
            if (c2.moveToFirst()) {
                String string = c2.getString(0);
                if (c2 == null) {
                    return string;
                }
                c2.close();
                return string;
            }
            if (c2 != null) {
                c2.close();
            }
            return null;
        } catch (SecurityException e) {
            Log.e(TAG, "getRawId: " + e.toString());
            if (c != null) {
                c.close();
            }
            return null;
        } catch (Throwable th) {
            if (c != null) {
                c.close();
            }
            throw th;
        }
    }

    public String getId(String rawId) {
        Cursor c = null;
        try {
            Cursor c2 = this.mResolver.query(ContactsContract.RawContacts.CONTENT_URI, new String[]{"contact_id"}, "_id=?", new String[]{rawId}, (String) null);
            if (c2.moveToFirst()) {
                String string = c2.getString(0);
                if (c2 == null) {
                    return string;
                }
                c2.close();
                return string;
            }
            if (c2 != null) {
                c2.close();
            }
            return null;
        } catch (SecurityException e) {
            Log.e(TAG, "getId: " + e.toString());
            if (c != null) {
                c.close();
            }
            return null;
        } catch (Throwable th) {
            if (c != null) {
                c.close();
            }
            throw th;
        }
    }

    @TargetApi(18)
    public String getLastUpdated(long contactId) {
        String str = null;
        Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactId);
        Cursor cursor = this.mResolver.query(uri, new String[]{"contact_last_updated_timestamp"}, (String) null, (String[]) null, (String) null);
        try {
            if (cursor.moveToNext()) {
                str = timeConvertToJS(cursor.getLong(0));
            } else if (cursor != null) {
                cursor.close();
            }
            return str;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public Set<String> getCurrentRawIds() {
        Cursor c = null;
        try {
            Cursor c2 = this.mResolver.query(ContactsContract.RawContacts.CONTENT_URI, new String[]{"_id"}, (String) null, (String[]) null, (String) null);
            Set<String> rawIds = new HashSet<>();
            while (c2.moveToNext()) {
                rawIds.add(c2.getString(0));
            }
            if (c2 == null) {
                return rawIds;
            }
            c2.close();
            return rawIds;
        } catch (SecurityException e) {
            Log.e(TAG, "getCurrentRawIds: " + e.toString());
            if (c != null) {
                c.close();
            }
            return null;
        } catch (Throwable th) {
            if (c != null) {
                c.close();
            }
            throw th;
        }
    }

    public String[] getDefaultAccountNameAndType() {
        ArrayList<ContentProviderOperation> ops = new ArrayList<>();
        ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI).withValue("account_name", (Object) null).withValue("account_type", (Object) null).build());
        try {
            Uri rawContactUri = null;
            long rawContactId = 0;
            for (ContentProviderResult result : this.mResolver.applyBatch("com.android.contacts", ops)) {
                rawContactUri = result.uri;
                rawContactId = ContentUris.parseId(rawContactUri);
            }
            Cursor c = null;
            String accountType = "";
            String accountName = "";
            try {
                Cursor c2 = this.mResolver.query(ContactsContract.RawContacts.CONTENT_URI, new String[]{"account_type", "account_name"}, "_id=?", new String[]{String.valueOf(rawContactId)}, (String) null);
                if (c2.moveToFirst() && !c2.isAfterLast()) {
                    accountType = c2.getString(c2.getColumnIndex("account_type"));
                    accountName = c2.getString(c2.getColumnIndex("account_name"));
                }
                if (c2 != null) {
                    c2.close();
                }
                this.mResolver.delete(rawContactUri, (String) null, (String[]) null);
                return new String[]{accountName, accountType};
            } catch (SecurityException e) {
                Log.e(TAG, "getDefaultAccountNameAndType: " + e.toString());
                if (c == null) {
                    return null;
                }
                c.close();
                return null;
            } catch (Throwable th) {
                if (c != null) {
                    c.close();
                }
                throw th;
            }
        } catch (Exception e2) {
            if ((e2 instanceof RemoteException) || (e2 instanceof OperationApplicationException) || (e2 instanceof SecurityException)) {
                Log.e(TAG, "getDefaultAccountNameAndType - Failed to apply batch: " + e2.toString());
                return null;
            }
            throw new RuntimeException(e2);
        }
    }

    public String getGroupId(String groupTitle) {
        Cursor c = null;
        try {
            c = this.mResolver.query(ContactsContract.Groups.CONTENT_URI, (String[]) null, "deleted=? and group_visible=?", new String[]{AppEventsConstants.EVENT_PARAM_VALUE_NO, AppEventsConstants.EVENT_PARAM_VALUE_YES}, (String) null);
            c.moveToFirst();
            for (int i = 0; i < c.getCount(); i++) {
                if (c.getString(c.getColumnIndex(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE)).equals(groupTitle)) {
                    String string = c.getString(c.getColumnIndex("_id"));
                    if (c == null) {
                        return string;
                    }
                    c.close();
                    return string;
                }
                c.moveToNext();
            }
            if (c != null) {
                c.close();
            }
            return null;
        } catch (SecurityException e) {
            Log.e(TAG, "getGroupId: " + e.toString());
            if (c != null) {
                c.close();
            }
            return null;
        } catch (Throwable th) {
            if (c != null) {
                c.close();
            }
            throw th;
        }
    }

    public String getGroupTitle(String groupId) {
        Cursor c = null;
        try {
            c = this.mResolver.query(ContactsContract.Groups.CONTENT_URI, (String[]) null, "deleted=? and group_visible=?", new String[]{AppEventsConstants.EVENT_PARAM_VALUE_NO, AppEventsConstants.EVENT_PARAM_VALUE_YES}, (String) null);
            c.moveToFirst();
            for (int i = 0; i < c.getCount(); i++) {
                if (c.getString(c.getColumnIndex("_id")).equals(groupId)) {
                    String string = c.getString(c.getColumnIndex(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE));
                    if (c == null) {
                        return string;
                    }
                    c.close();
                    return string;
                }
                c.moveToNext();
            }
            if (c != null) {
                c.close();
            }
            return null;
        } catch (SecurityException e) {
            Log.e(TAG, "getGroupTitle: " + e.toString());
            if (c != null) {
                c.close();
            }
            return null;
        } catch (Throwable th) {
            if (c != null) {
                c.close();
            }
            throw th;
        }
    }

    public String getEnsuredGroupId(String groupTitle) {
        String groupId = getGroupId(groupTitle);
        if (groupId == null) {
            newGroup(groupTitle);
            groupId = getGroupId(groupTitle);
            if (groupId == null) {
                return null;
            }
        }
        return groupId;
    }

    public void newGroup(String groupTitle) {
        String[] accountNameType = getDefaultAccountNameAndType();
        ArrayList<ContentProviderOperation> o = new ArrayList<>();
        o.add(ContentProviderOperation.newInsert(ContactsContract.Groups.CONTENT_URI).withValue(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, groupTitle).withValue("group_visible", true).withValue("account_name", accountNameType[0]).withValue("account_type", accountNameType[1]).build());
        try {
            this.mResolver.applyBatch("com.android.contacts", o);
        } catch (Exception e) {
            if ((e instanceof RemoteException) || (e instanceof OperationApplicationException) || (e instanceof SecurityException)) {
                Log.e(TAG, "newGroup - Failed to create new contact group: " + e.toString());
                return;
            }
            throw new RuntimeException(e);
        }
    }

    public void cleanByMimeType(String id, String mimeType) {
        this.mResolver.delete(ContactsContract.Data.CONTENT_URI, String.format("%s = ? AND %s = ?", new Object[]{"contact_id", "mimetype"}), new String[]{id, mimeType});
    }

    public String dateTrim(String string) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            return df.format(df.parse(string));
        } catch (ParseException e) {
            Log.e(TAG, "dateFormat - parse failed: " + e.toString());
            return null;
        }
    }

    private String timeConvertToJS(long seconds) {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).format(new Date(seconds));
    }
}
