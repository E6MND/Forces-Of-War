package org.xwalk.core.internal.extension.api.contacts;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ContactEventListener extends ContentObserver {
    private static final String TAG = "ContactsEventListener";
    private HashSet<String> mContactIDs;
    private final Contacts mContacts;
    private boolean mIsListening = false;
    private HashMap<String, String> mRawID2ContactIDMaps;
    private HashMap<String, String> mRawID2VersionMaps;
    private final ContentResolver mResolver;

    public ContactEventListener(Handler handler, Contacts instance, ContentResolver resolver) {
        super(handler);
        this.mContacts = instance;
        this.mResolver = resolver;
    }

    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        if (this.mIsListening) {
            notifyChanges(false);
        }
    }

    /* access modifiers changed from: protected */
    public void startListening() {
        if (!this.mIsListening) {
            this.mIsListening = true;
            this.mContactIDs = getAllContactIDs();
            readAllRawContactInfo();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        if (this.mIsListening) {
            notifyChanges(true);
        }
    }

    private void notifyChanges(boolean bResume) {
        HashSet<String> commonIDs;
        try {
            JSONObject jsonOutput = new JSONObject();
            HashSet<String> contactIDs = getAllContactIDs();
            if (bResume || contactIDs.size() > this.mContactIDs.size()) {
                HashSet<String> addedIDs = getDiffSet(contactIDs, this.mContactIDs);
                if (!bResume || addedIDs.size() > 0) {
                    jsonOutput.put("added", convertSet2JSONArray(addedIDs));
                }
            } else if (bResume || contactIDs.size() < this.mContactIDs.size()) {
                HashSet<String> removedIDs = getDiffSet(this.mContactIDs, contactIDs);
                if (!bResume || contactIDs.size() < 0) {
                    jsonOutput.put("removed", convertSet2JSONArray(removedIDs));
                }
            } else {
                if (bResume) {
                    commonIDs = getIntersectSet(this.mContactIDs, contactIDs);
                } else {
                    commonIDs = contactIDs;
                }
                HashSet<String> modifiedIDs = compareAllRawContactInfo(commonIDs);
                if (modifiedIDs.size() != 0) {
                    jsonOutput.put("modified", convertSet2JSONArray(modifiedIDs));
                }
            }
            notifyContactChanged(jsonOutput);
            this.mContactIDs = contactIDs;
            readAllRawContactInfo();
        } catch (JSONException e) {
            Log.e(TAG, "notifyChanges: " + e.toString());
        }
    }

    private void notifyContactChanged(JSONObject outObject) {
        if (outObject != null && outObject.length() != 0) {
            try {
                JSONObject jsonOutput = new JSONObject();
                jsonOutput.put("reply", "contactschange");
                jsonOutput.put("data", outObject);
                this.mContacts.broadcastMessage(jsonOutput.toString());
            } catch (JSONException e) {
                Log.e(TAG, "notifyContactChanged: " + e.toString());
            }
        }
    }

    private JSONArray convertSet2JSONArray(HashSet<String> set) {
        JSONArray jsonArray = new JSONArray();
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            jsonArray.put(iterator.next());
        }
        return jsonArray;
    }

    private HashSet<String> getAllContactIDs() {
        Cursor c = null;
        try {
            Cursor c2 = this.mResolver.query(ContactsContract.Contacts.CONTENT_URI, (String[]) null, (String) null, (String[]) null, (String) null);
            HashSet<String> contactIDs = new HashSet<>();
            while (c2.moveToNext()) {
                contactIDs.add(c2.getString(c2.getColumnIndex("_id")));
            }
            if (c2 == null) {
                return contactIDs;
            }
            c2.close();
            return contactIDs;
        } catch (SecurityException e) {
            Log.e(TAG, "getAllContactIDs: " + e.toString());
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

    private HashSet<String> getIntersectSet(HashSet<String> setA, HashSet<String> setB) {
        HashSet<String> resultSet = new HashSet<>();
        resultSet.addAll(setA);
        resultSet.retainAll(setB);
        return resultSet;
    }

    private HashSet<String> getDiffSet(HashSet<String> setA, HashSet<String> setB) {
        HashSet<String> resultSet = new HashSet<>();
        resultSet.addAll(setA);
        resultSet.removeAll(setB);
        return resultSet;
    }

    private void readAllRawContactInfo() {
        Cursor c = null;
        try {
            Cursor c2 = this.mResolver.query(ContactsContract.RawContacts.CONTENT_URI, (String[]) null, (String) null, (String[]) null, (String) null);
            this.mRawID2ContactIDMaps = new HashMap<>();
            this.mRawID2VersionMaps = new HashMap<>();
            while (c2.moveToNext()) {
                String contactID = c2.getString(c2.getColumnIndex("contact_id"));
                String rawContactID = c2.getString(c2.getColumnIndex("_id"));
                String version = c2.getString(c2.getColumnIndex("version"));
                this.mRawID2ContactIDMaps.put(rawContactID, contactID);
                this.mRawID2VersionMaps.put(rawContactID, version);
            }
            if (c2 != null) {
                c2.close();
            }
        } catch (SecurityException e) {
            Log.e(TAG, "readAllRawContactInfo: " + e.toString());
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

    /* JADX WARNING: Removed duplicated region for block: B:16:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.HashSet<java.lang.String> compareAllRawContactInfo(java.util.HashSet<java.lang.String> r21) {
        /*
            r20 = this;
            r12 = 0
            r9 = 0
            r8 = 0
            r0 = r20
            android.content.ContentResolver r2 = r0.mResolver     // Catch:{ SecurityException -> 0x00c3 }
            android.net.Uri r3 = android.provider.ContactsContract.RawContacts.CONTENT_URI     // Catch:{ SecurityException -> 0x00c3 }
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r8 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ SecurityException -> 0x00c3 }
            java.util.HashSet r13 = new java.util.HashSet     // Catch:{ SecurityException -> 0x00c3 }
            r13.<init>()     // Catch:{ SecurityException -> 0x00c3 }
            java.util.HashMap r10 = new java.util.HashMap     // Catch:{ SecurityException -> 0x00c6, all -> 0x00bc }
            r10.<init>()     // Catch:{ SecurityException -> 0x00c6, all -> 0x00bc }
        L_0x001b:
            boolean r2 = r8.moveToNext()     // Catch:{ SecurityException -> 0x003d, all -> 0x00bf }
            if (r2 == 0) goto L_0x0063
            java.lang.String r2 = "_id"
            int r2 = r8.getColumnIndex(r2)     // Catch:{ SecurityException -> 0x003d, all -> 0x00bf }
            java.lang.String r18 = r8.getString(r2)     // Catch:{ SecurityException -> 0x003d, all -> 0x00bf }
            java.lang.String r2 = "version"
            int r2 = r8.getColumnIndex(r2)     // Catch:{ SecurityException -> 0x003d, all -> 0x00bf }
            java.lang.String r19 = r8.getString(r2)     // Catch:{ SecurityException -> 0x003d, all -> 0x00bf }
            r0 = r18
            r1 = r19
            r10.put(r0, r1)     // Catch:{ SecurityException -> 0x003d, all -> 0x00bf }
            goto L_0x001b
        L_0x003d:
            r14 = move-exception
            r9 = r10
            r12 = r13
        L_0x0040:
            java.lang.String r2 = "ContactsEventListener"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b2 }
            r3.<init>()     // Catch:{ all -> 0x00b2 }
            java.lang.String r4 = "compareAllRawContactInfo: "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ all -> 0x00b2 }
            java.lang.String r4 = r14.toString()     // Catch:{ all -> 0x00b2 }
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ all -> 0x00b2 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x00b2 }
            android.util.Log.e(r2, r3)     // Catch:{ all -> 0x00b2 }
            r13 = 0
            if (r8 == 0) goto L_0x0062
            r8.close()
        L_0x0062:
            return r13
        L_0x0063:
            if (r8 == 0) goto L_0x0068
            r8.close()
        L_0x0068:
            java.util.Set r2 = r10.keySet()
            java.util.Iterator r15 = r2.iterator()
        L_0x0070:
            boolean r2 = r15.hasNext()
            if (r2 == 0) goto L_0x00b9
            java.lang.Object r18 = r15.next()
            java.lang.String r18 = (java.lang.String) r18
            r0 = r18
            java.lang.Object r16 = r10.get(r0)
            java.lang.String r16 = (java.lang.String) r16
            r0 = r20
            java.util.HashMap<java.lang.String, java.lang.String> r2 = r0.mRawID2VersionMaps
            r0 = r18
            java.lang.Object r17 = r2.get(r0)
            java.lang.String r17 = (java.lang.String) r17
            if (r17 == 0) goto L_0x0098
            boolean r2 = r16.equals(r17)
            if (r2 != 0) goto L_0x0070
        L_0x0098:
            r0 = r20
            java.util.HashMap<java.lang.String, java.lang.String> r2 = r0.mRawID2ContactIDMaps
            r0 = r18
            java.lang.Object r11 = r2.get(r0)
            java.lang.String r11 = (java.lang.String) r11
            if (r11 == 0) goto L_0x0070
            r0 = r21
            boolean r2 = r0.contains(r11)
            if (r2 == 0) goto L_0x0070
            r13.add(r11)
            goto L_0x0070
        L_0x00b2:
            r2 = move-exception
        L_0x00b3:
            if (r8 == 0) goto L_0x00b8
            r8.close()
        L_0x00b8:
            throw r2
        L_0x00b9:
            r9 = r10
            r12 = r13
            goto L_0x0062
        L_0x00bc:
            r2 = move-exception
            r12 = r13
            goto L_0x00b3
        L_0x00bf:
            r2 = move-exception
            r9 = r10
            r12 = r13
            goto L_0x00b3
        L_0x00c3:
            r14 = move-exception
            goto L_0x0040
        L_0x00c6:
            r14 = move-exception
            r12 = r13
            goto L_0x0040
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xwalk.core.internal.extension.api.contacts.ContactEventListener.compareAllRawContactInfo(java.util.HashSet):java.util.HashSet");
    }
}
