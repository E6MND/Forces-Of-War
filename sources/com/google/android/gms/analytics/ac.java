package com.google.android.gms.analytics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.text.TextUtils;
import com.google.android.gms.internal.fe;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.apache.http.impl.client.DefaultHttpClient;

class ac implements d {
    /* access modifiers changed from: private */
    public static final String wM = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL, '%s' TEXT NOT NULL, '%s' INTEGER);", new Object[]{"hits2", "hit_id", "hit_time", "hit_url", "hit_string", "hit_app_id"});
    /* access modifiers changed from: private */
    public final Context mContext;
    private final e tZ;
    /* access modifiers changed from: private */
    public i ur;
    private final a wN;
    private volatile n wO;
    /* access modifiers changed from: private */
    public final String wP;
    private ab wQ;
    private long wR;
    private final int wS;

    class a extends SQLiteOpenHelper {
        private boolean wU;
        private long wV = 0;

        a(Context context, String str) {
            super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
        }

        /* JADX INFO: finally extract failed */
        private void a(SQLiteDatabase sQLiteDatabase) {
            boolean z = false;
            Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT * FROM hits2 WHERE 0", (String[]) null);
            HashSet hashSet = new HashSet();
            try {
                String[] columnNames = rawQuery.getColumnNames();
                for (String add : columnNames) {
                    hashSet.add(add);
                }
                rawQuery.close();
                if (!hashSet.remove("hit_id") || !hashSet.remove("hit_url") || !hashSet.remove("hit_string") || !hashSet.remove("hit_time")) {
                    throw new SQLiteException("Database column missing");
                }
                if (!hashSet.remove("hit_app_id")) {
                    z = true;
                }
                if (!hashSet.isEmpty()) {
                    throw new SQLiteException("Database has extra columns");
                } else if (z) {
                    sQLiteDatabase.execSQL("ALTER TABLE hits2 ADD COLUMN hit_app_id");
                }
            } catch (Throwable th) {
                rawQuery.close();
                throw th;
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0048  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private boolean a(java.lang.String r11, android.database.sqlite.SQLiteDatabase r12) {
            /*
                r10 = this;
                r8 = 0
                r9 = 0
                java.lang.String r1 = "SQLITE_MASTER"
                r0 = 1
                java.lang.String[] r2 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x0026, all -> 0x0045 }
                r0 = 0
                java.lang.String r3 = "name"
                r2[r0] = r3     // Catch:{ SQLiteException -> 0x0026, all -> 0x0045 }
                java.lang.String r3 = "name=?"
                r0 = 1
                java.lang.String[] r4 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x0026, all -> 0x0045 }
                r0 = 0
                r4[r0] = r11     // Catch:{ SQLiteException -> 0x0026, all -> 0x0045 }
                r5 = 0
                r6 = 0
                r7 = 0
                r0 = r12
                android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ SQLiteException -> 0x0026, all -> 0x0045 }
                boolean r0 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0053, all -> 0x004c }
                if (r1 == 0) goto L_0x0025
                r1.close()
            L_0x0025:
                return r0
            L_0x0026:
                r0 = move-exception
                r0 = r9
            L_0x0028:
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x004f }
                r1.<init>()     // Catch:{ all -> 0x004f }
                java.lang.String r2 = "Error querying for table "
                java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x004f }
                java.lang.StringBuilder r1 = r1.append(r11)     // Catch:{ all -> 0x004f }
                java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x004f }
                com.google.android.gms.analytics.aa.D(r1)     // Catch:{ all -> 0x004f }
                if (r0 == 0) goto L_0x0043
                r0.close()
            L_0x0043:
                r0 = r8
                goto L_0x0025
            L_0x0045:
                r0 = move-exception
            L_0x0046:
                if (r9 == 0) goto L_0x004b
                r9.close()
            L_0x004b:
                throw r0
            L_0x004c:
                r0 = move-exception
                r9 = r1
                goto L_0x0046
            L_0x004f:
                r1 = move-exception
                r9 = r0
                r0 = r1
                goto L_0x0046
            L_0x0053:
                r0 = move-exception
                r0 = r1
                goto L_0x0028
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.ac.a.a(java.lang.String, android.database.sqlite.SQLiteDatabase):boolean");
        }

        public SQLiteDatabase getWritableDatabase() {
            if (!this.wU || this.wV + 3600000 <= ac.this.ur.currentTimeMillis()) {
                SQLiteDatabase sQLiteDatabase = null;
                this.wU = true;
                this.wV = ac.this.ur.currentTimeMillis();
                try {
                    sQLiteDatabase = super.getWritableDatabase();
                } catch (SQLiteException e) {
                    ac.this.mContext.getDatabasePath(ac.this.wP).delete();
                }
                if (sQLiteDatabase == null) {
                    sQLiteDatabase = super.getWritableDatabase();
                }
                this.wU = false;
                return sQLiteDatabase;
            }
            throw new SQLiteException("Database creation failed");
        }

        public void onCreate(SQLiteDatabase db) {
            p.N(db.getPath());
        }

        public void onOpen(SQLiteDatabase db) {
            if (Build.VERSION.SDK_INT < 15) {
                Cursor rawQuery = db.rawQuery("PRAGMA journal_mode=memory", (String[]) null);
                try {
                    rawQuery.moveToFirst();
                } finally {
                    rawQuery.close();
                }
            }
            if (!a("hits2", db)) {
                db.execSQL(ac.wM);
            } else {
                a(db);
            }
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }

    ac(e eVar, Context context) {
        this(eVar, context, "google_analytics_v4.db", 2000);
    }

    ac(e eVar, Context context, String str, int i) {
        this.mContext = context.getApplicationContext();
        this.wP = str;
        this.tZ = eVar;
        this.ur = new i() {
            public long currentTimeMillis() {
                return System.currentTimeMillis();
            }
        };
        this.wN = new a(this.mContext, this.wP);
        this.wO = new ah(new DefaultHttpClient(), this.mContext);
        this.wR = 0;
        this.wS = i;
    }

    private SQLiteDatabase S(String str) {
        try {
            return this.wN.getWritableDatabase();
        } catch (SQLiteException e) {
            aa.D(str);
            return null;
        }
    }

    private void a(Map<String, String> map, long j, String str) {
        long j2;
        SQLiteDatabase S = S("Error opening database for putHit");
        if (S != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("hit_string", v(map));
            contentValues.put("hit_time", Long.valueOf(j));
            if (map.containsKey("AppUID")) {
                try {
                    j2 = Long.parseLong(map.get("AppUID"));
                } catch (NumberFormatException e) {
                    j2 = 0;
                }
            } else {
                j2 = 0;
            }
            contentValues.put("hit_app_id", Long.valueOf(j2));
            if (str == null) {
                str = "http://www.google-analytics.com/collect";
            }
            if (str.length() == 0) {
                aa.D("Empty path: not sending hit");
                return;
            }
            contentValues.put("hit_url", str);
            try {
                S.insert("hits2", (String) null, contentValues);
                this.tZ.s(false);
            } catch (SQLiteException e2) {
                aa.D("Error storing hit");
            }
        }
    }

    private void a(Map<String, String> map, Collection<fe> collection) {
        String substring = "&_v".substring(1);
        if (collection != null) {
            for (fe next : collection) {
                if ("appendVersion".equals(next.getId())) {
                    map.put(substring, next.getValue());
                    return;
                }
            }
        }
    }

    private void dm() {
        int i = (m0do() - this.wS) + 1;
        if (i > 0) {
            List<String> A = A(i);
            aa.C("Store full, deleting " + A.size() + " hits to make room.");
            a((String[]) A.toArray(new String[0]));
        }
    }

    static String v(Map<String, String> map) {
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry next : map.entrySet()) {
            arrayList.add(y.encode((String) next.getKey()) + "=" + y.encode((String) next.getValue()));
        }
        return TextUtils.join("&", arrayList);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0082  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<java.lang.String> A(int r14) {
        /*
            r13 = this;
            r10 = 0
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            if (r14 > 0) goto L_0x000f
            java.lang.String r0 = "Invalid maxHits specified. Skipping"
            com.google.android.gms.analytics.aa.D(r0)
            r0 = r9
        L_0x000e:
            return r0
        L_0x000f:
            java.lang.String r0 = "Error opening database for peekHitIds."
            android.database.sqlite.SQLiteDatabase r0 = r13.S(r0)
            if (r0 != 0) goto L_0x0019
            r0 = r9
            goto L_0x000e
        L_0x0019:
            java.lang.String r1 = "hits2"
            r2 = 1
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x005c, all -> 0x007e }
            r3 = 0
            java.lang.String r4 = "hit_id"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x005c, all -> 0x007e }
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            java.lang.String r7 = "%s ASC"
            r8 = 1
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ SQLiteException -> 0x005c, all -> 0x007e }
            r11 = 0
            java.lang.String r12 = "hit_id"
            r8[r11] = r12     // Catch:{ SQLiteException -> 0x005c, all -> 0x007e }
            java.lang.String r7 = java.lang.String.format(r7, r8)     // Catch:{ SQLiteException -> 0x005c, all -> 0x007e }
            java.lang.String r8 = java.lang.Integer.toString(r14)     // Catch:{ SQLiteException -> 0x005c, all -> 0x007e }
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x005c, all -> 0x007e }
            boolean r0 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0088 }
            if (r0 == 0) goto L_0x0055
        L_0x0043:
            r0 = 0
            long r2 = r1.getLong(r0)     // Catch:{ SQLiteException -> 0x0088 }
            java.lang.String r0 = java.lang.String.valueOf(r2)     // Catch:{ SQLiteException -> 0x0088 }
            r9.add(r0)     // Catch:{ SQLiteException -> 0x0088 }
            boolean r0 = r1.moveToNext()     // Catch:{ SQLiteException -> 0x0088 }
            if (r0 != 0) goto L_0x0043
        L_0x0055:
            if (r1 == 0) goto L_0x005a
            r1.close()
        L_0x005a:
            r0 = r9
            goto L_0x000e
        L_0x005c:
            r0 = move-exception
            r1 = r10
        L_0x005e:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0086 }
            r2.<init>()     // Catch:{ all -> 0x0086 }
            java.lang.String r3 = "Error in peekHits fetching hitIds: "
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x0086 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0086 }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ all -> 0x0086 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0086 }
            com.google.android.gms.analytics.aa.D(r0)     // Catch:{ all -> 0x0086 }
            if (r1 == 0) goto L_0x005a
            r1.close()
            goto L_0x005a
        L_0x007e:
            r0 = move-exception
            r1 = r10
        L_0x0080:
            if (r1 == 0) goto L_0x0085
            r1.close()
        L_0x0085:
            throw r0
        L_0x0086:
            r0 = move-exception
            goto L_0x0080
        L_0x0088:
            r0 = move-exception
            goto L_0x005e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.ac.A(int):java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00f4, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00fc, code lost:
        r12.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0175, code lost:
        r2 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0176, code lost:
        r12 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x017b, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x017c, code lost:
        r3 = r2;
        r4 = r13;
        r2 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:?, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:?, code lost:
        return r2;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00f4  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00fc  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0175 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:6:0x003b] */
    /* JADX WARNING: Removed duplicated region for block: B:80:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.google.android.gms.analytics.x> B(int r17) {
        /*
            r16 = this;
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            java.lang.String r2 = "Error opening database for peekHits"
            r0 = r16
            android.database.sqlite.SQLiteDatabase r2 = r0.S(r2)
            if (r2 != 0) goto L_0x0011
            r2 = r11
        L_0x0010:
            return r2
        L_0x0011:
            r12 = 0
            java.lang.String r3 = "hits2"
            r4 = 2
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x00d4, all -> 0x00f9 }
            r5 = 0
            java.lang.String r6 = "hit_id"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x00d4, all -> 0x00f9 }
            r5 = 1
            java.lang.String r6 = "hit_time"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x00d4, all -> 0x00f9 }
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            java.lang.String r9 = "%s ASC"
            r10 = 1
            java.lang.Object[] r10 = new java.lang.Object[r10]     // Catch:{ SQLiteException -> 0x00d4, all -> 0x00f9 }
            r13 = 0
            java.lang.String r14 = "hit_id"
            r10[r13] = r14     // Catch:{ SQLiteException -> 0x00d4, all -> 0x00f9 }
            java.lang.String r9 = java.lang.String.format(r9, r10)     // Catch:{ SQLiteException -> 0x00d4, all -> 0x00f9 }
            java.lang.String r10 = java.lang.Integer.toString(r17)     // Catch:{ SQLiteException -> 0x00d4, all -> 0x00f9 }
            android.database.Cursor r13 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x00d4, all -> 0x00f9 }
            java.util.ArrayList r12 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x017b, all -> 0x0175 }
            r12.<init>()     // Catch:{ SQLiteException -> 0x017b, all -> 0x0175 }
            boolean r3 = r13.moveToFirst()     // Catch:{ SQLiteException -> 0x0181, all -> 0x0175 }
            if (r3 == 0) goto L_0x005f
        L_0x0046:
            com.google.android.gms.analytics.x r4 = new com.google.android.gms.analytics.x     // Catch:{ SQLiteException -> 0x0181, all -> 0x0175 }
            r5 = 0
            r3 = 0
            long r6 = r13.getLong(r3)     // Catch:{ SQLiteException -> 0x0181, all -> 0x0175 }
            r3 = 1
            long r8 = r13.getLong(r3)     // Catch:{ SQLiteException -> 0x0181, all -> 0x0175 }
            r4.<init>(r5, r6, r8)     // Catch:{ SQLiteException -> 0x0181, all -> 0x0175 }
            r12.add(r4)     // Catch:{ SQLiteException -> 0x0181, all -> 0x0175 }
            boolean r3 = r13.moveToNext()     // Catch:{ SQLiteException -> 0x0181, all -> 0x0175 }
            if (r3 != 0) goto L_0x0046
        L_0x005f:
            if (r13 == 0) goto L_0x0064
            r13.close()
        L_0x0064:
            r11 = 0
            java.lang.String r3 = "hits2"
            r4 = 3
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x0173 }
            r5 = 0
            java.lang.String r6 = "hit_id"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x0173 }
            r5 = 1
            java.lang.String r6 = "hit_string"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x0173 }
            r5 = 2
            java.lang.String r6 = "hit_url"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x0173 }
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            java.lang.String r9 = "%s ASC"
            r10 = 1
            java.lang.Object[] r10 = new java.lang.Object[r10]     // Catch:{ SQLiteException -> 0x0173 }
            r14 = 0
            java.lang.String r15 = "hit_id"
            r10[r14] = r15     // Catch:{ SQLiteException -> 0x0173 }
            java.lang.String r9 = java.lang.String.format(r9, r10)     // Catch:{ SQLiteException -> 0x0173 }
            java.lang.String r10 = java.lang.Integer.toString(r17)     // Catch:{ SQLiteException -> 0x0173 }
            android.database.Cursor r3 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x0173 }
            boolean r2 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
            if (r2 == 0) goto L_0x00cc
            r4 = r11
        L_0x009a:
            r0 = r3
            android.database.sqlite.SQLiteCursor r0 = (android.database.sqlite.SQLiteCursor) r0     // Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
            r2 = r0
            android.database.CursorWindow r2 = r2.getWindow()     // Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
            int r2 = r2.getNumRows()     // Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
            if (r2 <= 0) goto L_0x0100
            java.lang.Object r2 = r12.get(r4)     // Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
            com.google.android.gms.analytics.x r2 = (com.google.android.gms.analytics.x) r2     // Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
            r5 = 1
            java.lang.String r5 = r3.getString(r5)     // Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
            r2.Q(r5)     // Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
            java.lang.Object r2 = r12.get(r4)     // Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
            com.google.android.gms.analytics.x r2 = (com.google.android.gms.analytics.x) r2     // Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
            r5 = 2
            java.lang.String r5 = r3.getString(r5)     // Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
            r2.R(r5)     // Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
        L_0x00c4:
            int r2 = r4 + 1
            boolean r4 = r3.moveToNext()     // Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
            if (r4 != 0) goto L_0x0187
        L_0x00cc:
            if (r3 == 0) goto L_0x00d1
            r3.close()
        L_0x00d1:
            r2 = r12
            goto L_0x0010
        L_0x00d4:
            r2 = move-exception
            r3 = r2
            r4 = r12
            r2 = r11
        L_0x00d8:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0178 }
            r5.<init>()     // Catch:{ all -> 0x0178 }
            java.lang.String r6 = "Error in peekHits fetching hitIds: "
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x0178 }
            java.lang.String r3 = r3.getMessage()     // Catch:{ all -> 0x0178 }
            java.lang.StringBuilder r3 = r5.append(r3)     // Catch:{ all -> 0x0178 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0178 }
            com.google.android.gms.analytics.aa.D(r3)     // Catch:{ all -> 0x0178 }
            if (r4 == 0) goto L_0x0010
            r4.close()
            goto L_0x0010
        L_0x00f9:
            r2 = move-exception
        L_0x00fa:
            if (r12 == 0) goto L_0x00ff
            r12.close()
        L_0x00ff:
            throw r2
        L_0x0100:
            java.lang.String r5 = "HitString for hitId %d too large.  Hit will be deleted."
            r2 = 1
            java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
            r7 = 0
            java.lang.Object r2 = r12.get(r4)     // Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
            com.google.android.gms.analytics.x r2 = (com.google.android.gms.analytics.x) r2     // Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
            long r8 = r2.dg()     // Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
            java.lang.Long r2 = java.lang.Long.valueOf(r8)     // Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
            r6[r7] = r2     // Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
            java.lang.String r2 = java.lang.String.format(r5, r6)     // Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
            com.google.android.gms.analytics.aa.D(r2)     // Catch:{ SQLiteException -> 0x011e, all -> 0x0170 }
            goto L_0x00c4
        L_0x011e:
            r2 = move-exception
            r13 = r3
        L_0x0120:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0169 }
            r3.<init>()     // Catch:{ all -> 0x0169 }
            java.lang.String r4 = "Error in peekHits fetching hitString: "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ all -> 0x0169 }
            java.lang.String r2 = r2.getMessage()     // Catch:{ all -> 0x0169 }
            java.lang.StringBuilder r2 = r3.append(r2)     // Catch:{ all -> 0x0169 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0169 }
            com.google.android.gms.analytics.aa.D(r2)     // Catch:{ all -> 0x0169 }
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ all -> 0x0169 }
            r3.<init>()     // Catch:{ all -> 0x0169 }
            r4 = 0
            java.util.Iterator r5 = r12.iterator()     // Catch:{ all -> 0x0169 }
        L_0x0144:
            boolean r2 = r5.hasNext()     // Catch:{ all -> 0x0169 }
            if (r2 == 0) goto L_0x015c
            java.lang.Object r2 = r5.next()     // Catch:{ all -> 0x0169 }
            com.google.android.gms.analytics.x r2 = (com.google.android.gms.analytics.x) r2     // Catch:{ all -> 0x0169 }
            java.lang.String r6 = r2.df()     // Catch:{ all -> 0x0169 }
            boolean r6 = android.text.TextUtils.isEmpty(r6)     // Catch:{ all -> 0x0169 }
            if (r6 == 0) goto L_0x0165
            if (r4 == 0) goto L_0x0164
        L_0x015c:
            if (r13 == 0) goto L_0x0161
            r13.close()
        L_0x0161:
            r2 = r3
            goto L_0x0010
        L_0x0164:
            r4 = 1
        L_0x0165:
            r3.add(r2)     // Catch:{ all -> 0x0169 }
            goto L_0x0144
        L_0x0169:
            r2 = move-exception
        L_0x016a:
            if (r13 == 0) goto L_0x016f
            r13.close()
        L_0x016f:
            throw r2
        L_0x0170:
            r2 = move-exception
            r13 = r3
            goto L_0x016a
        L_0x0173:
            r2 = move-exception
            goto L_0x0120
        L_0x0175:
            r2 = move-exception
            r12 = r13
            goto L_0x00fa
        L_0x0178:
            r2 = move-exception
            r12 = r4
            goto L_0x00fa
        L_0x017b:
            r2 = move-exception
            r3 = r2
            r4 = r13
            r2 = r11
            goto L_0x00d8
        L_0x0181:
            r2 = move-exception
            r3 = r2
            r4 = r13
            r2 = r12
            goto L_0x00d8
        L_0x0187:
            r4 = r2
            goto L_0x009a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.ac.B(int):java.util.List");
    }

    public void a(Map<String, String> map, long j, String str, Collection<fe> collection) {
        dn();
        dm();
        a(map, collection);
        a(map, j, str);
    }

    /* access modifiers changed from: package-private */
    public void a(String[] strArr) {
        boolean z = true;
        if (strArr == null || strArr.length == 0) {
            aa.D("Empty hitIds passed to deleteHits.");
            return;
        }
        SQLiteDatabase S = S("Error opening database for deleteHits.");
        if (S != null) {
            try {
                S.delete("hits2", String.format("HIT_ID in (%s)", new Object[]{TextUtils.join(",", Collections.nCopies(strArr.length, "?"))}), strArr);
                e eVar = this.tZ;
                if (m0do() != 0) {
                    z = false;
                }
                eVar.s(z);
            } catch (SQLiteException e) {
                aa.D("Error deleting hits " + strArr);
            }
        }
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void b(Collection<x> collection) {
        if (collection == null || collection.isEmpty()) {
            aa.D("Empty/Null collection passed to deleteHits.");
            return;
        }
        String[] strArr = new String[collection.size()];
        int i = 0;
        for (x dg : collection) {
            strArr[i] = String.valueOf(dg.dg());
            i++;
        }
        a(strArr);
    }

    public void cl() {
        boolean z = true;
        aa.C("Dispatch running...");
        if (this.wO.cx()) {
            List<x> B = B(40);
            if (B.isEmpty()) {
                aa.C("...nothing to dispatch");
                this.tZ.s(true);
                return;
            }
            if (this.wQ == null) {
                this.wQ = new ab("_t=dispatch&_v=ma4.0.2", true);
            }
            if (m0do() > B.size()) {
                z = false;
            }
            int a2 = this.wO.a(B, this.wQ, z);
            aa.C("sent " + a2 + " of " + B.size() + " hits");
            b((Collection<x>) B.subList(0, Math.min(a2, B.size())));
            if (a2 != B.size() || m0do() <= 0) {
                this.wQ = null;
            } else {
                GoogleAnalytics.getInstance(this.mContext).dispatchLocalHits();
            }
        }
    }

    public n cm() {
        return this.wO;
    }

    /* access modifiers changed from: package-private */
    public int dn() {
        boolean z = true;
        long currentTimeMillis = this.ur.currentTimeMillis();
        if (currentTimeMillis <= this.wR + 86400000) {
            return 0;
        }
        this.wR = currentTimeMillis;
        SQLiteDatabase S = S("Error opening database for deleteStaleHits.");
        if (S == null) {
            return 0;
        }
        int delete = S.delete("hits2", "HIT_TIME < ?", new String[]{Long.toString(this.ur.currentTimeMillis() - 2592000000L)});
        e eVar = this.tZ;
        if (m0do() != 0) {
            z = false;
        }
        eVar.s(z);
        return delete;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: do  reason: not valid java name */
    public int m0do() {
        Cursor cursor = null;
        int i = 0;
        SQLiteDatabase S = S("Error opening database for getNumStoredHits.");
        if (S != null) {
            try {
                Cursor rawQuery = S.rawQuery("SELECT COUNT(*) from hits2", (String[]) null);
                if (rawQuery.moveToFirst()) {
                    i = (int) rawQuery.getLong(0);
                }
                if (rawQuery != null) {
                    rawQuery.close();
                }
            } catch (SQLiteException e) {
                aa.D("Error getting numStoredHits");
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }
        return i;
    }

    public void l(long j) {
        boolean z = true;
        SQLiteDatabase S = S("Error opening database for clearHits");
        if (S != null) {
            if (j == 0) {
                S.delete("hits2", (String) null, (String[]) null);
            } else {
                S.delete("hits2", "hit_app_id = ?", new String[]{Long.valueOf(j).toString()});
            }
            e eVar = this.tZ;
            if (m0do() != 0) {
                z = false;
            }
            eVar.s(z);
        }
    }
}
