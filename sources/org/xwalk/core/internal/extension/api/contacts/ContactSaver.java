package org.xwalk.core.internal.extension.api.contacts;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.OperationApplicationException;
import android.os.Build;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.v4.os.EnvironmentCompat;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xwalk.core.internal.extension.api.contacts.ContactConstants;
import org.xwalk.core.internal.extension.api.messaging.MessagingSmsConsts;

public class ContactSaver {
    private static final String TAG = "ContactSaver";
    private JSONObject mContact;
    private String mId;
    private boolean mIsUpdate;
    private ContactJson mJson;
    private ArrayList<ContentProviderOperation> mOps;
    private ContactUtils mUtils;

    public ContactSaver(ContentResolver resolver) {
        this.mUtils = new ContactUtils(resolver);
    }

    private ContentProviderOperation.Builder newUpdateBuilder(String mimeType) {
        ContentProviderOperation.Builder builder = ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI);
        builder.withSelection("contact_id=? AND mimetype=?", new String[]{this.mId, mimeType});
        return builder;
    }

    private ContentProviderOperation.Builder newInsertBuilder(String mimeType) {
        ContentProviderOperation.Builder builder = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
        builder.withValueBackReference("raw_contact_id", 0);
        builder.withValue("mimetype", mimeType);
        return builder;
    }

    private ContentProviderOperation.Builder newInsertFieldBuilder(String mimeType) {
        if (this.mUtils.getRawId(this.mId) == null) {
            Log.e(TAG, "Failed to create builder to insert field of " + this.mId);
            return null;
        }
        ContentProviderOperation.Builder builder = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
        builder.withValue("raw_contact_id", this.mUtils.getRawId(this.mId));
        builder.withValue("mimetype", mimeType);
        return builder;
    }

    private ContentProviderOperation.Builder newInsertContactOrFieldBuilder(String mimeType) {
        return this.mIsUpdate ? newInsertFieldBuilder(mimeType) : newInsertBuilder(mimeType);
    }

    private ContentProviderOperation.Builder newBuilder(String mimeType) {
        return this.mIsUpdate ? newUpdateBuilder(mimeType) : newInsertBuilder(mimeType);
    }

    private void buildByArray(ContactConstants.ContactMap contactMap) {
        if (this.mContact.has(contactMap.mName)) {
            if (this.mIsUpdate) {
                this.mUtils.cleanByMimeType(this.mId, contactMap.mMimeType);
            }
            try {
                JSONArray fields = this.mContact.getJSONArray(contactMap.mName);
                for (int i = 0; i < fields.length(); i++) {
                    ContactJson json = new ContactJson(fields.getJSONObject(i));
                    List<String> typeList = json.getStringArray("types");
                    if (typeList != null && !typeList.isEmpty()) {
                        Integer iType = contactMap.mTypeValueMap.get(typeList.get(0));
                        ContentProviderOperation.Builder builder = newInsertContactOrFieldBuilder(contactMap.mMimeType);
                        if (builder != null) {
                            if (json.getBoolean("preferred")) {
                                builder.withValue(contactMap.mTypeMap.get("isPrimary"), 1);
                                builder.withValue(contactMap.mTypeMap.get("isSuperPrimary"), 1);
                            }
                            if (iType != null) {
                                builder.withValue(contactMap.mTypeMap.get(MessagingSmsConsts.TYPE), iType);
                            }
                            for (Map.Entry<String, String> entry : contactMap.mDataMap.entrySet()) {
                                String value = json.getString(entry.getValue());
                                if (contactMap.mName.equals("impp")) {
                                    int colonIdx = value.indexOf(58);
                                    if (-1 != colonIdx) {
                                        builder.withValue("data5", ContactConstants.imProtocolMap.get(value.substring(0, colonIdx)));
                                        value = value.substring(colonIdx + 1);
                                    }
                                }
                                builder.withValue(entry.getKey(), value);
                            }
                            this.mOps.add(builder.build());
                        } else {
                            return;
                        }
                    }
                }
            } catch (JSONException e) {
                Log.e(TAG, "Failed to parse json data of " + contactMap.mName + ": " + e.toString());
            }
        }
    }

    private void buildByArray(String mimeType, String data, List<String> dataEntries) {
        if (this.mIsUpdate) {
            this.mUtils.cleanByMimeType(this.mId, mimeType);
        }
        for (String entry : dataEntries) {
            ContentProviderOperation.Builder builder = newInsertContactOrFieldBuilder(mimeType);
            if (builder != null) {
                builder.withValue(data, entry);
                this.mOps.add(builder.build());
            } else {
                return;
            }
        }
    }

    private void buildByArray(ContactConstants.ContactMap contactMap, String data, List<String> dataEntries) {
        if (this.mContact.has(contactMap.mName)) {
            buildByArray(contactMap.mMimeType, data, dataEntries);
        }
    }

    private void buildByDate(String name, String mimeType, String data, String type, int dateType) {
        if (this.mContact.has(name)) {
            String dateString = this.mUtils.dateTrim(this.mJson.getString(name));
            ContentProviderOperation.Builder builder = newBuilder(mimeType);
            builder.withValue(data, dateString);
            if (type != null) {
                builder.withValue(type, Integer.valueOf(dateType));
            }
            this.mOps.add(builder.build());
        }
    }

    private void buildByEvent(String eventName, int eventType) {
        buildByDate(eventName, "vnd.android.cursor.item/contact_event", "data1", "data2", eventType);
    }

    private void buildByContactMapList() {
        for (ContactConstants.ContactMap contactMap : ContactConstants.contactMapList) {
            if (contactMap.mTypeMap != null) {
                buildByArray(contactMap);
            } else {
                buildByArray(contactMap, contactMap.mDataMap.get("data"), this.mJson.getStringArray(contactMap.mName));
            }
        }
    }

    private void PutToContact(String name, String value) {
        if (name != null) {
            try {
                this.mContact.put(name, value);
            } catch (JSONException e) {
                Log.e(TAG, "Failed to set " + name + " = " + value + " for contact" + e.toString());
            }
        }
    }

    public JSONObject save(String saveString) {
        this.mOps = new ArrayList<>();
        try {
            this.mContact = new JSONObject(saveString);
            this.mJson = new ContactJson(this.mContact);
            this.mId = this.mJson.getString("id");
            this.mIsUpdate = this.mUtils.hasID(this.mId);
            Set<String> oldRawIds = null;
            if (!this.mIsUpdate) {
                oldRawIds = this.mUtils.getCurrentRawIds();
                this.mId = null;
                ContentProviderOperation.Builder builder = ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI);
                builder.withValue("account_type", (Object) null);
                builder.withValue("account_name", (Object) null);
                this.mOps.add(builder.build());
            }
            if (this.mContact.has("name")) {
                JSONObject name = this.mJson.getObject("name");
                ContactJson nameJson = new ContactJson(name);
                ContentProviderOperation.Builder builder2 = newBuilder("vnd.android.cursor.item/name");
                builder2.withValue("data1", nameJson.getString("displayName"));
                builder2.withValue("data3", nameJson.getFirstValue("familyNames"));
                builder2.withValue("data2", nameJson.getFirstValue("givenNames"));
                builder2.withValue("data5", nameJson.getFirstValue("additionalNames"));
                builder2.withValue("data4", nameJson.getFirstValue("honorificPrefixes"));
                builder2.withValue("data6", nameJson.getFirstValue("honorificSuffixes"));
                this.mOps.add(builder2.build());
                if (name.has("nicknames")) {
                    ContentProviderOperation.Builder builder3 = newBuilder("vnd.android.cursor.item/nickname");
                    builder3.withValue("data1", nameJson.getFirstValue("nicknames"));
                    this.mOps.add(builder3.build());
                }
            }
            if (this.mContact.has("categories")) {
                List<String> groupIds = new ArrayList<>();
                for (String groupTitle : this.mJson.getStringArray("categories")) {
                    groupIds.add(this.mUtils.getEnsuredGroupId(groupTitle));
                }
                buildByArray("vnd.android.cursor.item/group_membership", "data1", groupIds);
            }
            if (this.mContact.has("gender")) {
                String gender = this.mJson.getString("gender");
                if (Arrays.asList(new String[]{"male", "female", "other", "none", EnvironmentCompat.MEDIA_UNKNOWN}).contains(gender)) {
                    ContentProviderOperation.Builder builder4 = newBuilder(ContactConstants.CUSTOM_MIMETYPE_GENDER);
                    builder4.withValue("data1", gender);
                    this.mOps.add(builder4.build());
                }
            }
            buildByEvent("birthday", 3);
            buildByEvent("anniversary", 1);
            buildByContactMapList();
            try {
                this.mUtils.mResolver.applyBatch("com.android.contacts", this.mOps);
                if (!this.mIsUpdate) {
                    Set<String> newRawIds = this.mUtils.getCurrentRawIds();
                    if (newRawIds == null) {
                        return new JSONObject();
                    }
                    newRawIds.removeAll(oldRawIds);
                    if (newRawIds.size() != 1) {
                        Log.e(TAG, "Something wrong after batch applied, new raw ids are: " + newRawIds.toString());
                        return this.mContact;
                    }
                    this.mId = this.mUtils.getId(newRawIds.iterator().next());
                    PutToContact("id", this.mId);
                }
                if (Build.VERSION.SDK_INT >= 18) {
                    PutToContact("lastUpdated", String.valueOf(this.mUtils.getLastUpdated(Long.valueOf(this.mId).longValue())));
                }
                return this.mContact;
            } catch (Exception e) {
                if ((e instanceof RemoteException) || (e instanceof OperationApplicationException) || (e instanceof SecurityException)) {
                    Log.e(TAG, "Failed to apply batch: " + e.toString());
                    return new JSONObject();
                }
                throw new RuntimeException(e);
            }
        } catch (JSONException e2) {
            Log.e(TAG, "Failed to parse json data: " + e2.toString());
            return new JSONObject();
        }
    }
}
