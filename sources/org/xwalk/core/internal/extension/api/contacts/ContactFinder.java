package org.xwalk.core.internal.extension.api.contacts;

import android.content.ContentResolver;
import android.database.Cursor;
import android.util.Log;
import android.util.Pair;
import com.facebook.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xwalk.core.internal.extension.api.messaging.MessagingSmsConsts;

public class ContactFinder {
    private static final String TAG = "ContactFinder";
    private ContactUtils mUtils;

    public ContactFinder(ContentResolver resolver) {
        this.mUtils = new ContactUtils(resolver);
    }

    public static class FindOption {
        public String mSortOrder;
        public String mWhere;
        public String[] mWhereArgs;

        public FindOption(String where, String[] whereArgs, String sortOrder) {
            this.mWhere = where;
            this.mWhereArgs = whereArgs;
            this.mSortOrder = sortOrder;
        }
    }

    private class ContactData {
        public JSONArray aAddresses;
        public JSONArray aCategories;
        public JSONArray aEmails;
        public JSONArray aImpp;
        public JSONArray aJobTitles;
        public JSONArray aNotes;
        public JSONArray aNumbers;
        public JSONArray aOrganizations;
        public JSONArray aPhotos;
        public JSONArray aUrls;
        public String anniversary;
        public String birthday;
        public String gender;
        public String lastUpdated;
        public JSONObject oName;

        private ContactData() {
        }

        public JSONObject ensurePut(long id) {
            JSONObject o = new JSONObject();
            try {
                o.put("id", id);
            } catch (JSONException e) {
                Log.e(ContactFinder.TAG, "ensurePut - Failed to build json data: " + e.toString());
            }
            ensurePut(o, "name", this.oName);
            ensurePut(o, "lastUpdated", this.lastUpdated);
            ensurePut(o, "emails", this.aEmails);
            ensurePut(o, "photos", this.aPhotos);
            ensurePut(o, "urls", this.aUrls);
            ensurePut(o, "categories", this.aCategories);
            ensurePut(o, "addresses", this.aAddresses);
            ensurePut(o, "phoneNumbers", this.aNumbers);
            ensurePut(o, "organizations", this.aOrganizations);
            ensurePut(o, "jobTitles", this.aJobTitles);
            ensurePut(o, "birthday", this.birthday);
            ensurePut(o, "notes", this.aNotes);
            ensurePut(o, "impp", this.aImpp);
            ensurePut(o, "anniversary", this.anniversary);
            ensurePut(o, "gender", this.gender);
            return o;
        }

        private <T> void ensurePut(JSONObject o, String jsonName, T t) {
            if (t != null) {
                try {
                    o.put(jsonName, t);
                } catch (JSONException e) {
                    Log.e(ContactFinder.TAG, "ensurePut - Failed to add json data: " + e.toString());
                }
            }
        }
    }

    private JSONObject addString(JSONObject o, Cursor c, String jsonName, String dataName) {
        try {
            String value = c.getString(c.getColumnIndex(dataName));
            if (o == null) {
                o = new JSONObject();
            }
            if (value != null) {
                o.put(jsonName, value);
            }
        } catch (JSONException e) {
            Log.e(TAG, "addString - Failed to build json data: " + e.toString());
        }
        return o;
    }

    private JSONArray addString(JSONArray array, Cursor c, String dataName) {
        if (array == null) {
            array = new JSONArray();
        }
        String value = c.getString(c.getColumnIndex(dataName));
        if (value != null) {
            array.put(value);
        }
        return array;
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [java.util.Map, java.util.Map<java.lang.String, java.lang.Integer>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.json.JSONObject addArrayTop(org.json.JSONObject r3, android.database.Cursor r4, java.lang.String r5, java.lang.String r6, java.util.Map<java.lang.String, java.lang.Integer> r7) {
        /*
            r2 = this;
            int r1 = r4.getColumnIndex(r6)
            java.lang.String r1 = r4.getString(r1)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.Object r0 = org.xwalk.core.internal.extension.api.contacts.ContactUtils.getKeyFromValue(r7, r1)
            java.lang.String r0 = (java.lang.String) r0
            org.json.JSONObject r1 = r2.ensureAddArrayTop(r3, r4, r5, r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xwalk.core.internal.extension.api.contacts.ContactFinder.addArrayTop(org.json.JSONObject, android.database.Cursor, java.lang.String, java.lang.String, java.util.Map):org.json.JSONObject");
    }

    private JSONObject addArrayTop(JSONObject o, Cursor c, String jsonName, String dataName) {
        return ensureAddArrayTop(o, c, jsonName, c.getString(c.getColumnIndex(dataName)));
    }

    private JSONObject ensureAddArrayTop(JSONObject o, Cursor c, String jsonName, String nameString) {
        if (o == null) {
            try {
                o = new JSONObject();
            } catch (JSONException e) {
                Log.e(TAG, "ensureAddArrayTop - Failed to build json data: " + e.toString());
            }
        }
        if (nameString != null) {
            JSONArray nameArray = new JSONArray();
            nameArray.put(nameString);
            o.put(jsonName, nameArray);
        }
        return o;
    }

    private JSONArray addTypeArray(JSONArray array, Cursor c, String data, Map<String, String> typeMap, Map<String, Integer> typeValuesMap) {
        if (array == null) {
            try {
                array = new JSONArray();
            } catch (JSONException e) {
                Log.e(TAG, "addTypeArray - Failed to build json data: " + e.toString());
            }
        }
        String preferred = c.getString(c.getColumnIndex(typeMap.get("isSuperPrimary"))).equals(AppEventsConstants.EVENT_PARAM_VALUE_YES) ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false";
        JSONObject o = new JSONObject();
        o.put("preferred", preferred);
        addArrayTop(o, c, "types", typeMap.get(MessagingSmsConsts.TYPE), typeValuesMap);
        String value = c.getString(c.getColumnIndex(data));
        if (c.getString(c.getColumnIndex("mimetype")).equals("vnd.android.cursor.item/im")) {
            value = ((String) ContactUtils.getKeyFromValue(ContactConstants.imProtocolMap, Integer.valueOf(c.getInt(c.getColumnIndex("data5"))))) + ':' + value;
        }
        o.put("value", value);
        array.put(o);
        return array;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0060  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Set<java.lang.String> getContactIds(org.xwalk.core.internal.extension.api.contacts.ContactFinder.FindOption r12) {
        /*
            r11 = this;
            r10 = 0
            r8 = 0
            r6 = 0
            org.xwalk.core.internal.extension.api.contacts.ContactUtils r0 = r11.mUtils     // Catch:{ SecurityException -> 0x0067 }
            android.content.ContentResolver r0 = r0.mResolver     // Catch:{ SecurityException -> 0x0067 }
            android.net.Uri r1 = android.provider.ContactsContract.Data.CONTENT_URI     // Catch:{ SecurityException -> 0x0067 }
            r2 = 0
            java.lang.String r3 = r12.mWhere     // Catch:{ SecurityException -> 0x0067 }
            java.lang.String[] r4 = r12.mWhereArgs     // Catch:{ SecurityException -> 0x0067 }
            java.lang.String r5 = r12.mSortOrder     // Catch:{ SecurityException -> 0x0067 }
            android.database.Cursor r6 = r0.query(r1, r2, r3, r4, r5)     // Catch:{ SecurityException -> 0x0067 }
            java.util.HashSet r9 = new java.util.HashSet     // Catch:{ SecurityException -> 0x0067 }
            r9.<init>()     // Catch:{ SecurityException -> 0x0067 }
        L_0x0019:
            boolean r0 = r6.moveToNext()     // Catch:{ SecurityException -> 0x0031, all -> 0x0064 }
            if (r0 == 0) goto L_0x0056
            java.lang.String r0 = "contact_id"
            int r0 = r6.getColumnIndex(r0)     // Catch:{ SecurityException -> 0x0031, all -> 0x0064 }
            long r0 = r6.getLong(r0)     // Catch:{ SecurityException -> 0x0031, all -> 0x0064 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ SecurityException -> 0x0031, all -> 0x0064 }
            r9.add(r0)     // Catch:{ SecurityException -> 0x0031, all -> 0x0064 }
            goto L_0x0019
        L_0x0031:
            r7 = move-exception
            r8 = r9
        L_0x0033:
            java.lang.String r0 = "ContactFinder"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x005d }
            r1.<init>()     // Catch:{ all -> 0x005d }
            java.lang.String r2 = "getContactIds: "
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x005d }
            java.lang.String r2 = r7.toString()     // Catch:{ all -> 0x005d }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x005d }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x005d }
            android.util.Log.e(r0, r1)     // Catch:{ all -> 0x005d }
            if (r6 == 0) goto L_0x0054
            r6.close()
        L_0x0054:
            r9 = r10
        L_0x0055:
            return r9
        L_0x0056:
            if (r6 == 0) goto L_0x005b
            r6.close()
        L_0x005b:
            r8 = r9
            goto L_0x0055
        L_0x005d:
            r0 = move-exception
        L_0x005e:
            if (r6 == 0) goto L_0x0063
            r6.close()
        L_0x0063:
            throw r0
        L_0x0064:
            r0 = move-exception
            r8 = r9
            goto L_0x005e
        L_0x0067:
            r7 = move-exception
            goto L_0x0033
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xwalk.core.internal.extension.api.contacts.ContactFinder.getContactIds(org.xwalk.core.internal.extension.api.contacts.ContactFinder$FindOption):java.util.Set");
    }

    private String getSortOrder(List<String> sortBy, String sortOrder) {
        if (sortOrder == null) {
            return null;
        }
        String suffix = "";
        if (sortOrder.equals("ascending")) {
            suffix = " ASC";
        } else if (sortOrder.equals("descending")) {
            suffix = " DESC";
        }
        String order = "";
        for (String s : sortBy) {
            Pair<String, String> fields = ContactConstants.contactDataMap.get(s);
            if (fields != null) {
                order = order + ((String) fields.first) + suffix + ",";
            }
        }
        return order != "" ? order.substring(0, order.length() - 1) : null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:108:0x0340 A[SYNTHETIC, Splitter:B:108:0x0340] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0097 A[Catch:{ all -> 0x0348 }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0178  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.json.JSONArray getContacts(java.util.Set<java.lang.String> r28, java.lang.String r29, java.lang.String r30, java.lang.Long r31) {
        /*
            r27 = this;
            r5 = 0
            r6 = 0
            int r2 = r28.size()
            if (r2 == 0) goto L_0x0033
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "contact_id in ("
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = org.xwalk.core.internal.extension.api.contacts.ContactUtils.makeQuestionMarkList(r28)
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = ")"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r5 = r2.toString()
            int r2 = r28.size()
            java.lang.String[] r2 = new java.lang.String[r2]
            r0 = r28
            java.lang.Object[] r6 = r0.toArray(r2)
            java.lang.String[] r6 = (java.lang.String[]) r6
        L_0x0033:
            r9 = 0
            r14 = 0
            r0 = r27
            org.xwalk.core.internal.extension.api.contacts.ContactUtils r2 = r0.mUtils     // Catch:{ Exception -> 0x0367 }
            android.content.ContentResolver r2 = r2.mResolver     // Catch:{ Exception -> 0x0367 }
            android.net.Uri r3 = android.provider.ContactsContract.Data.CONTENT_URI     // Catch:{ Exception -> 0x0367 }
            r4 = 0
            r7 = r29
            android.database.Cursor r9 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x0367 }
            java.util.LinkedHashMap r15 = new java.util.LinkedHashMap     // Catch:{ Exception -> 0x0367 }
            r15.<init>()     // Catch:{ Exception -> 0x0367 }
            if (r29 == 0) goto L_0x00c1
        L_0x004b:
            boolean r2 = r9.moveToNext()     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            if (r2 == 0) goto L_0x00be
            java.lang.String r2 = "mimetype"
            int r2 = r9.getColumnIndex(r2)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            java.lang.String r22 = r9.getString(r2)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            r0 = r22
            r1 = r30
            boolean r2 = r0.equals(r1)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            if (r2 == 0) goto L_0x004b
            java.lang.String r2 = "contact_id"
            int r2 = r9.getColumnIndex(r2)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            long r20 = r9.getLong(r2)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            java.lang.Long r2 = java.lang.Long.valueOf(r20)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            boolean r2 = r15.containsKey(r2)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            if (r2 != 0) goto L_0x004b
            java.lang.Long r2 = java.lang.Long.valueOf(r20)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            org.xwalk.core.internal.extension.api.contacts.ContactFinder$ContactData r3 = new org.xwalk.core.internal.extension.api.contacts.ContactFinder$ContactData     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            r4 = 0
            r0 = r27
            r3.<init>()     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            r15.put(r2, r3)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            goto L_0x004b
        L_0x0089:
            r16 = move-exception
            r14 = r15
        L_0x008b:
            r0 = r16
            boolean r2 = r0 instanceof java.lang.NumberFormatException     // Catch:{ all -> 0x0348 }
            if (r2 != 0) goto L_0x0097
            r0 = r16
            boolean r2 = r0 instanceof java.lang.SecurityException     // Catch:{ all -> 0x0348 }
            if (r2 == 0) goto L_0x0340
        L_0x0097:
            java.lang.String r2 = "ContactFinder"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0348 }
            r3.<init>()     // Catch:{ all -> 0x0348 }
            java.lang.String r4 = "getContacts: "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ all -> 0x0348 }
            java.lang.String r4 = r16.toString()     // Catch:{ all -> 0x0348 }
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ all -> 0x0348 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0348 }
            android.util.Log.e(r2, r3)     // Catch:{ all -> 0x0348 }
            org.json.JSONArray r24 = new org.json.JSONArray     // Catch:{ all -> 0x0348 }
            r24.<init>()     // Catch:{ all -> 0x0348 }
            if (r9 == 0) goto L_0x00bd
            r9.close()
        L_0x00bd:
            return r24
        L_0x00be:
            r9.moveToFirst()     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
        L_0x00c1:
            boolean r2 = r9.moveToNext()     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            if (r2 == 0) goto L_0x030e
            java.lang.String r2 = "contact_id"
            int r2 = r9.getColumnIndex(r2)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            long r20 = r9.getLong(r2)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            java.lang.Long r2 = java.lang.Long.valueOf(r20)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            boolean r2 = r15.containsKey(r2)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            if (r2 != 0) goto L_0x00ea
            java.lang.Long r2 = java.lang.Long.valueOf(r20)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            org.xwalk.core.internal.extension.api.contacts.ContactFinder$ContactData r3 = new org.xwalk.core.internal.extension.api.contacts.ContactFinder$ContactData     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            r4 = 0
            r0 = r27
            r3.<init>()     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            r15.put(r2, r3)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
        L_0x00ea:
            java.lang.Long r2 = java.lang.Long.valueOf(r20)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            java.lang.Object r13 = r15.get(r2)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            org.xwalk.core.internal.extension.api.contacts.ContactFinder$ContactData r13 = (org.xwalk.core.internal.extension.api.contacts.ContactFinder.ContactData) r13     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            java.lang.String r2 = r13.lastUpdated     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            if (r2 != 0) goto L_0x010a
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            r3 = 18
            if (r2 < r3) goto L_0x010a
            r0 = r27
            org.xwalk.core.internal.extension.api.contacts.ContactUtils r2 = r0.mUtils     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            r0 = r20
            java.lang.String r2 = r2.getLastUpdated(r0)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            r13.lastUpdated = r2     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
        L_0x010a:
            java.lang.String r2 = "mimetype"
            int r2 = r9.getColumnIndex(r2)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            java.lang.String r22 = r9.getString(r2)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            java.lang.String r2 = "vnd.android.cursor.item/name"
            r0 = r22
            boolean r2 = r0.equals(r2)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            if (r2 == 0) goto L_0x017c
            org.json.JSONObject r2 = r13.oName     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            java.lang.String r3 = "displayName"
            java.lang.String r4 = "data1"
            r0 = r27
            org.json.JSONObject r2 = r0.addString(r2, r9, r3, r4)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            r13.oName = r2     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            org.json.JSONObject r2 = r13.oName     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            java.lang.String r3 = "honorificPrefixes"
            java.lang.String r4 = "data4"
            r0 = r27
            org.json.JSONObject r2 = r0.addArrayTop(r2, r9, r3, r4)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            r13.oName = r2     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            org.json.JSONObject r2 = r13.oName     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            java.lang.String r3 = "givenNames"
            java.lang.String r4 = "data2"
            r0 = r27
            org.json.JSONObject r2 = r0.addArrayTop(r2, r9, r3, r4)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            r13.oName = r2     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            org.json.JSONObject r2 = r13.oName     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            java.lang.String r3 = "additionalNames"
            java.lang.String r4 = "data5"
            r0 = r27
            org.json.JSONObject r2 = r0.addArrayTop(r2, r9, r3, r4)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            r13.oName = r2     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            org.json.JSONObject r2 = r13.oName     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            java.lang.String r3 = "familyNames"
            java.lang.String r4 = "data3"
            r0 = r27
            org.json.JSONObject r2 = r0.addArrayTop(r2, r9, r3, r4)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            r13.oName = r2     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            org.json.JSONObject r2 = r13.oName     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            java.lang.String r3 = "honorificSuffixes"
            java.lang.String r4 = "data6"
            r0 = r27
            org.json.JSONObject r2 = r0.addArrayTop(r2, r9, r3, r4)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            r13.oName = r2     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            goto L_0x00c1
        L_0x0174:
            r2 = move-exception
            r14 = r15
        L_0x0176:
            if (r9 == 0) goto L_0x017b
            r9.close()
        L_0x017b:
            throw r2
        L_0x017c:
            java.lang.String r2 = "vnd.android.cursor.item/nickname"
            r0 = r22
            boolean r2 = r0.equals(r2)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            if (r2 == 0) goto L_0x0196
            org.json.JSONObject r2 = r13.oName     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            java.lang.String r3 = "nicknames"
            java.lang.String r4 = "data1"
            r0 = r27
            org.json.JSONObject r2 = r0.addArrayTop(r2, r9, r3, r4)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            r13.oName = r2     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            goto L_0x00c1
        L_0x0196:
            java.lang.String r2 = "vnd.android.cursor.item/email_v2"
            r0 = r22
            boolean r2 = r0.equals(r2)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            if (r2 == 0) goto L_0x01b2
            org.json.JSONArray r8 = r13.aEmails     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            java.lang.String r10 = "data1"
            java.util.Map<java.lang.String, java.lang.String> r11 = org.xwalk.core.internal.extension.api.contacts.ContactConstants.emailTypeMap     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            java.util.Map<java.lang.String, java.lang.Integer> r12 = org.xwalk.core.internal.extension.api.contacts.ContactConstants.emailTypeValuesMap     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            r7 = r27
            org.json.JSONArray r2 = r7.addTypeArray(r8, r9, r10, r11, r12)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            r13.aEmails = r2     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            goto L_0x00c1
        L_0x01b2:
            java.lang.String r2 = "vnd.android.cursor.item/photo"
            r0 = r22
            boolean r2 = r0.equals(r2)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            if (r2 == 0) goto L_0x01ca
            org.json.JSONArray r2 = r13.aPhotos     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            java.lang.String r3 = "data15"
            r0 = r27
            org.json.JSONArray r2 = r0.addString(r2, r9, r3)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            r13.aPhotos = r2     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            goto L_0x00c1
        L_0x01ca:
            java.lang.String r2 = "vnd.android.cursor.item/website"
            r0 = r22
            boolean r2 = r0.equals(r2)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            if (r2 == 0) goto L_0x01e6
            org.json.JSONArray r8 = r13.aUrls     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            java.lang.String r10 = "data1"
            java.util.Map<java.lang.String, java.lang.String> r11 = org.xwalk.core.internal.extension.api.contacts.ContactConstants.websiteTypeMap     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            java.util.Map<java.lang.String, java.lang.Integer> r12 = org.xwalk.core.internal.extension.api.contacts.ContactConstants.websiteTypeValuesMap     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            r7 = r27
            org.json.JSONArray r2 = r7.addTypeArray(r8, r9, r10, r11, r12)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            r13.aUrls = r2     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            goto L_0x00c1
        L_0x01e6:
            java.lang.String r2 = "vnd.android.cursor.item/group_membership"
            r0 = r22
            boolean r2 = r0.equals(r2)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            if (r2 == 0) goto L_0x0218
            r0 = r27
            org.xwalk.core.internal.extension.api.contacts.ContactUtils r2 = r0.mUtils     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            java.lang.String r3 = "data1"
            int r3 = r9.getColumnIndex(r3)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            java.lang.String r3 = r9.getString(r3)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            java.lang.String r25 = r2.getGroupTitle(r3)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            if (r25 == 0) goto L_0x00c1
            org.json.JSONArray r2 = r13.aCategories     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            if (r2 != 0) goto L_0x020f
            org.json.JSONArray r2 = new org.json.JSONArray     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            r2.<init>()     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            r13.aCategories = r2     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
        L_0x020f:
            org.json.JSONArray r2 = r13.aCategories     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            r0 = r25
            r2.put(r0)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            goto L_0x00c1
        L_0x0218:
            java.lang.String r2 = "vnd.android.cursor.item/postal-address_v2"
            r0 = r22
            boolean r2 = r0.equals(r2)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            if (r2 == 0) goto L_0x0234
            org.json.JSONArray r8 = r13.aAddresses     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            java.lang.String r10 = "data1"
            java.util.Map<java.lang.String, java.lang.String> r11 = org.xwalk.core.internal.extension.api.contacts.ContactConstants.addressTypeMap     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            java.util.Map<java.lang.String, java.lang.Integer> r12 = org.xwalk.core.internal.extension.api.contacts.ContactConstants.addressTypeValuesMap     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            r7 = r27
            org.json.JSONArray r2 = r7.addTypeArray(r8, r9, r10, r11, r12)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            r13.aAddresses = r2     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            goto L_0x00c1
        L_0x0234:
            java.lang.String r2 = "vnd.android.cursor.item/phone_v2"
            r0 = r22
            boolean r2 = r0.equals(r2)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            if (r2 == 0) goto L_0x0250
            org.json.JSONArray r8 = r13.aNumbers     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            java.lang.String r10 = "data1"
            java.util.Map<java.lang.String, java.lang.String> r11 = org.xwalk.core.internal.extension.api.contacts.ContactConstants.phoneTypeMap     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            java.util.Map<java.lang.String, java.lang.Integer> r12 = org.xwalk.core.internal.extension.api.contacts.ContactConstants.phoneTypeValuesMap     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            r7 = r27
            org.json.JSONArray r2 = r7.addTypeArray(r8, r9, r10, r11, r12)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            r13.aNumbers = r2     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            goto L_0x00c1
        L_0x0250:
            java.lang.String r2 = "vnd.android.cursor.item/organization"
            r0 = r22
            boolean r2 = r0.equals(r2)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            if (r2 == 0) goto L_0x0268
            org.json.JSONArray r2 = r13.aOrganizations     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            java.lang.String r3 = "data1"
            r0 = r27
            org.json.JSONArray r2 = r0.addString(r2, r9, r3)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            r13.aOrganizations = r2     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            goto L_0x00c1
        L_0x0268:
            java.lang.String r2 = "vnd.android.cursor.item/organization"
            r0 = r22
            boolean r2 = r0.equals(r2)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            if (r2 == 0) goto L_0x0280
            org.json.JSONArray r2 = r13.aJobTitles     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            java.lang.String r3 = "data4"
            r0 = r27
            org.json.JSONArray r2 = r0.addString(r2, r9, r3)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            r13.aJobTitles = r2     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            goto L_0x00c1
        L_0x0280:
            java.lang.String r2 = "vnd.android.cursor.item/contact_event"
            r0 = r22
            boolean r2 = r0.equals(r2)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            if (r2 == 0) goto L_0x02c2
            java.lang.String r2 = "data2"
            int r2 = r9.getColumnIndex(r2)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            java.lang.String r2 = r9.getString(r2)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            int r26 = r2.intValue()     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            r2 = 3
            r0 = r26
            if (r0 != r2) goto L_0x02af
            java.lang.String r2 = "data1"
            int r2 = r9.getColumnIndex(r2)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            java.lang.String r2 = r9.getString(r2)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            r13.birthday = r2     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            goto L_0x00c1
        L_0x02af:
            r2 = 1
            r0 = r26
            if (r0 != r2) goto L_0x00c1
            java.lang.String r2 = "data1"
            int r2 = r9.getColumnIndex(r2)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            java.lang.String r2 = r9.getString(r2)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            r13.anniversary = r2     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            goto L_0x00c1
        L_0x02c2:
            java.lang.String r2 = "vnd.android.cursor.item/note"
            r0 = r22
            boolean r2 = r0.equals(r2)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            if (r2 == 0) goto L_0x02da
            org.json.JSONArray r2 = r13.aNotes     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            java.lang.String r3 = "data1"
            r0 = r27
            org.json.JSONArray r2 = r0.addString(r2, r9, r3)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            r13.aNotes = r2     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            goto L_0x00c1
        L_0x02da:
            java.lang.String r2 = "vnd.android.cursor.item/im"
            r0 = r22
            boolean r2 = r0.equals(r2)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            if (r2 == 0) goto L_0x02f6
            org.json.JSONArray r8 = r13.aImpp     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            java.lang.String r10 = "data1"
            java.util.Map<java.lang.String, java.lang.String> r11 = org.xwalk.core.internal.extension.api.contacts.ContactConstants.imTypeMap     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            java.util.Map<java.lang.String, java.lang.Integer> r12 = org.xwalk.core.internal.extension.api.contacts.ContactConstants.imTypeValuesMap     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            r7 = r27
            org.json.JSONArray r2 = r7.addTypeArray(r8, r9, r10, r11, r12)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            r13.aImpp = r2     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            goto L_0x00c1
        L_0x02f6:
            java.lang.String r2 = "vnd.android.cursor.item/contact_custom_gender"
            r0 = r22
            boolean r2 = r0.equals(r2)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            if (r2 == 0) goto L_0x00c1
            java.lang.String r2 = "data1"
            int r2 = r9.getColumnIndex(r2)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            java.lang.String r2 = r9.getString(r2)     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            r13.gender = r2     // Catch:{ Exception -> 0x0089, all -> 0x0174 }
            goto L_0x00c1
        L_0x030e:
            if (r9 == 0) goto L_0x0313
            r9.close()
        L_0x0313:
            r18 = 0
            org.json.JSONArray r24 = new org.json.JSONArray
            r24.<init>()
            java.util.Set r2 = r15.entrySet()
            java.util.Iterator r19 = r2.iterator()
        L_0x0322:
            boolean r2 = r19.hasNext()
            if (r2 == 0) goto L_0x033d
            java.lang.Object r17 = r19.next()
            java.util.Map$Entry r17 = (java.util.Map.Entry) r17
            if (r31 == 0) goto L_0x034b
            int r18 = r18 + 1
            r0 = r18
            long r2 = (long) r0
            long r10 = r31.longValue()
            int r2 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1))
            if (r2 <= 0) goto L_0x034b
        L_0x033d:
            r14 = r15
            goto L_0x00bd
        L_0x0340:
            java.lang.RuntimeException r2 = new java.lang.RuntimeException     // Catch:{ all -> 0x0348 }
            r0 = r16
            r2.<init>(r0)     // Catch:{ all -> 0x0348 }
            throw r2     // Catch:{ all -> 0x0348 }
        L_0x0348:
            r2 = move-exception
            goto L_0x0176
        L_0x034b:
            java.lang.Object r2 = r17.getValue()
            org.xwalk.core.internal.extension.api.contacts.ContactFinder$ContactData r2 = (org.xwalk.core.internal.extension.api.contacts.ContactFinder.ContactData) r2
            java.lang.Object r3 = r17.getKey()
            java.lang.Long r3 = (java.lang.Long) r3
            long r10 = r3.longValue()
            org.json.JSONObject r23 = r2.ensurePut(r10)
            r0 = r24
            r1 = r23
            r0.put(r1)
            goto L_0x0322
        L_0x0367:
            r16 = move-exception
            goto L_0x008b
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xwalk.core.internal.extension.api.contacts.ContactFinder.getContacts(java.util.Set, java.lang.String, java.lang.String, java.lang.Long):org.json.JSONArray");
    }

    private FindOption createFindIDOption(String findString) {
        String operator;
        ContactJson findJson = new ContactJson(findString);
        String value = findString != null ? findJson.getString("value") : null;
        if (value == null || value.equals("") || findString.equals("")) {
            return new FindOption((String) null, (String[]) null, (String) null);
        }
        List<String> args = new ArrayList<>();
        List<String> fields = findJson.getStringArray("fields");
        String operator2 = findJson.getString("operator");
        if (operator2 == null) {
            return new FindOption((String) null, (String[]) null, (String) null);
        }
        if (operator2.equals("is")) {
            operator = " = ";
        } else if (operator2.equals("contains")) {
            operator = " LIKE ";
            value = "%" + value + "%";
        } else {
            Log.e(TAG, "find - Wrong Operator: [" + operator2 + "], should be 'is' or 'contains'");
            return null;
        }
        String where = "";
        for (String field : fields) {
            String column = ContactConstants.findFieldMap.get(field);
            if (column != null) {
                Pair<String, String> name = ContactConstants.contactDataMap.get(column);
                where = where + ((String) name.first) + operator + " ? AND " + "mimetype" + " = ? or ";
                args.add(value);
                args.add(name.second);
            }
        }
        if (where == "") {
            return new FindOption((String) null, (String[]) null, (String) null);
        }
        return new FindOption(where.substring(0, where.length() - 3), (String[]) args.toArray(new String[args.size()]), (String) null);
    }

    public JSONArray find(String findString) {
        Set<String> ids = getContactIds(createFindIDOption(findString));
        if (ids == null) {
            return new JSONArray();
        }
        ContactJson findJson = new ContactJson(findString);
        List<String> sortBy = findJson.getStringArray("sortBy");
        String order = getSortOrder(sortBy, findJson.getString("sortOrder"));
        String orderMimeType = order == null ? null : (String) ContactConstants.contactDataMap.get(sortBy.get(0)).second;
        String resultsLimit = findJson.getString("resultsLimit");
        return getContacts(ids, order, orderMimeType, resultsLimit == null ? null : Long.valueOf(resultsLimit));
    }
}
