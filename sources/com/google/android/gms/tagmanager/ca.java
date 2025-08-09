package com.google.android.gms.tagmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.text.TextUtils;
import com.google.android.gms.internal.ik;
import com.google.android.gms.internal.im;
import com.google.android.gms.tagmanager.da;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import org.apache.http.impl.client.DefaultHttpClient;

class ca implements at {
    /* access modifiers changed from: private */
    public static final String wM = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL,'%s' INTEGER NOT NULL);", new Object[]{"gtm_hits", "hit_id", "hit_time", "hit_url", "hit_first_send_time"});
    /* access modifiers changed from: private */
    public ik aec;
    private final b agn;
    private volatile ab ago;
    private final au agp;
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public final String wP;
    private long wR;
    private final int wS;

    class a implements da.a {
        a() {
        }

        public void a(ap apVar) {
            ca.this.y(apVar.dg());
        }

        public void b(ap apVar) {
            ca.this.y(apVar.dg());
            bh.C("Permanent failure dispatching hitId: " + apVar.dg());
        }

        public void c(ap apVar) {
            long lI = apVar.lI();
            if (lI == 0) {
                ca.this.c(apVar.dg(), ca.this.aec.currentTimeMillis());
            } else if (lI + 14400000 < ca.this.aec.currentTimeMillis()) {
                ca.this.y(apVar.dg());
                bh.C("Giving up on failed hitId: " + apVar.dg());
            }
        }
    }

    class b extends SQLiteOpenHelper {
        private boolean wU;
        private long wV = 0;

        b(Context context, String str) {
            super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
        }

        /* JADX INFO: finally extract failed */
        private void a(SQLiteDatabase sQLiteDatabase) {
            Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT * FROM gtm_hits WHERE 0", (String[]) null);
            HashSet hashSet = new HashSet();
            try {
                String[] columnNames = rawQuery.getColumnNames();
                for (String add : columnNames) {
                    hashSet.add(add);
                }
                rawQuery.close();
                if (!hashSet.remove("hit_id") || !hashSet.remove("hit_url") || !hashSet.remove("hit_time") || !hashSet.remove("hit_first_send_time")) {
                    throw new SQLiteException("Database column missing");
                } else if (!hashSet.isEmpty()) {
                    throw new SQLiteException("Database has extra columns");
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
                com.google.android.gms.tagmanager.bh.D(r1)     // Catch:{ all -> 0x004f }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.ca.b.a(java.lang.String, android.database.sqlite.SQLiteDatabase):boolean");
        }

        public SQLiteDatabase getWritableDatabase() {
            if (!this.wU || this.wV + 3600000 <= ca.this.aec.currentTimeMillis()) {
                SQLiteDatabase sQLiteDatabase = null;
                this.wU = true;
                this.wV = ca.this.aec.currentTimeMillis();
                try {
                    sQLiteDatabase = super.getWritableDatabase();
                } catch (SQLiteException e) {
                    ca.this.mContext.getDatabasePath(ca.this.wP).delete();
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
            ak.N(db.getPath());
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
            if (!a("gtm_hits", db)) {
                db.execSQL(ca.wM);
            } else {
                a(db);
            }
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }

    ca(au auVar, Context context) {
        this(auVar, context, "gtm_urls.db", 2000);
    }

    ca(au auVar, Context context, String str, int i) {
        this.mContext = context.getApplicationContext();
        this.wP = str;
        this.agp = auVar;
        this.aec = im.fW();
        this.agn = new b(this.mContext, this.wP);
        this.ago = new da(new DefaultHttpClient(), this.mContext, new a());
        this.wR = 0;
        this.wS = i;
    }

    private SQLiteDatabase S(String str) {
        try {
            return this.agn.getWritableDatabase();
        } catch (SQLiteException e) {
            bh.D(str);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public void c(long j, long j2) {
        SQLiteDatabase S = S("Error opening database for getNumStoredHits.");
        if (S != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("hit_first_send_time", Long.valueOf(j2));
            try {
                S.update("gtm_hits", contentValues, "hit_id=?", new String[]{String.valueOf(j)});
            } catch (SQLiteException e) {
                bh.D("Error setting HIT_FIRST_DISPATCH_TIME for hitId: " + j);
                y(j);
            }
        }
    }

    private void dm() {
        int i = (m2do() - this.wS) + 1;
        if (i > 0) {
            List<String> A = A(i);
            bh.C("Store full, deleting " + A.size() + " hits to make room.");
            a((String[]) A.toArray(new String[0]));
        }
    }

    private void g(long j, String str) {
        SQLiteDatabase S = S("Error opening database for putHit");
        if (S != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("hit_time", Long.valueOf(j));
            contentValues.put("hit_url", str);
            contentValues.put("hit_first_send_time", 0);
            try {
                S.insert("gtm_hits", (String) null, contentValues);
                this.agp.s(false);
            } catch (SQLiteException e) {
                bh.D("Error storing hit");
            }
        }
    }

    /* access modifiers changed from: private */
    public void y(long j) {
        a(new String[]{String.valueOf(j)});
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
            com.google.android.gms.tagmanager.bh.D(r0)
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
            java.lang.String r1 = "gtm_hits"
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
            com.google.android.gms.tagmanager.bh.D(r0)     // Catch:{ all -> 0x0086 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.ca.A(int):java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00ea, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00f2, code lost:
        r12.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x016b, code lost:
        r2 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x016c, code lost:
        r12 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0171, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0172, code lost:
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
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x016b A[ExcHandler: all (th java.lang.Throwable), Splitter:B:6:0x0040] */
    /* JADX WARNING: Removed duplicated region for block: B:80:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.google.android.gms.tagmanager.ap> B(int r17) {
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
            java.lang.String r3 = "gtm_hits"
            r4 = 3
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x00ca, all -> 0x00ef }
            r5 = 0
            java.lang.String r6 = "hit_id"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x00ca, all -> 0x00ef }
            r5 = 1
            java.lang.String r6 = "hit_time"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x00ca, all -> 0x00ef }
            r5 = 2
            java.lang.String r6 = "hit_first_send_time"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x00ca, all -> 0x00ef }
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            java.lang.String r9 = "%s ASC"
            r10 = 1
            java.lang.Object[] r10 = new java.lang.Object[r10]     // Catch:{ SQLiteException -> 0x00ca, all -> 0x00ef }
            r13 = 0
            java.lang.String r14 = "hit_id"
            r10[r13] = r14     // Catch:{ SQLiteException -> 0x00ca, all -> 0x00ef }
            java.lang.String r9 = java.lang.String.format(r9, r10)     // Catch:{ SQLiteException -> 0x00ca, all -> 0x00ef }
            java.lang.String r10 = java.lang.Integer.toString(r17)     // Catch:{ SQLiteException -> 0x00ca, all -> 0x00ef }
            android.database.Cursor r13 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x00ca, all -> 0x00ef }
            java.util.ArrayList r12 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0171, all -> 0x016b }
            r12.<init>()     // Catch:{ SQLiteException -> 0x0171, all -> 0x016b }
            boolean r3 = r13.moveToFirst()     // Catch:{ SQLiteException -> 0x0177, all -> 0x016b }
            if (r3 == 0) goto L_0x0068
        L_0x004b:
            com.google.android.gms.tagmanager.ap r3 = new com.google.android.gms.tagmanager.ap     // Catch:{ SQLiteException -> 0x0177, all -> 0x016b }
            r4 = 0
            long r4 = r13.getLong(r4)     // Catch:{ SQLiteException -> 0x0177, all -> 0x016b }
            r6 = 1
            long r6 = r13.getLong(r6)     // Catch:{ SQLiteException -> 0x0177, all -> 0x016b }
            r8 = 2
            long r8 = r13.getLong(r8)     // Catch:{ SQLiteException -> 0x0177, all -> 0x016b }
            r3.<init>(r4, r6, r8)     // Catch:{ SQLiteException -> 0x0177, all -> 0x016b }
            r12.add(r3)     // Catch:{ SQLiteException -> 0x0177, all -> 0x016b }
            boolean r3 = r13.moveToNext()     // Catch:{ SQLiteException -> 0x0177, all -> 0x016b }
            if (r3 != 0) goto L_0x004b
        L_0x0068:
            if (r13 == 0) goto L_0x006d
            r13.close()
        L_0x006d:
            r11 = 0
            java.lang.String r3 = "gtm_hits"
            r4 = 2
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x0169 }
            r5 = 0
            java.lang.String r6 = "hit_id"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x0169 }
            r5 = 1
            java.lang.String r6 = "hit_url"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x0169 }
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            java.lang.String r9 = "%s ASC"
            r10 = 1
            java.lang.Object[] r10 = new java.lang.Object[r10]     // Catch:{ SQLiteException -> 0x0169 }
            r14 = 0
            java.lang.String r15 = "hit_id"
            r10[r14] = r15     // Catch:{ SQLiteException -> 0x0169 }
            java.lang.String r9 = java.lang.String.format(r9, r10)     // Catch:{ SQLiteException -> 0x0169 }
            java.lang.String r10 = java.lang.Integer.toString(r17)     // Catch:{ SQLiteException -> 0x0169 }
            android.database.Cursor r3 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x0169 }
            boolean r2 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x0114, all -> 0x0166 }
            if (r2 == 0) goto L_0x00c2
            r4 = r11
        L_0x009e:
            r0 = r3
            android.database.sqlite.SQLiteCursor r0 = (android.database.sqlite.SQLiteCursor) r0     // Catch:{ SQLiteException -> 0x0114, all -> 0x0166 }
            r2 = r0
            android.database.CursorWindow r2 = r2.getWindow()     // Catch:{ SQLiteException -> 0x0114, all -> 0x0166 }
            int r2 = r2.getNumRows()     // Catch:{ SQLiteException -> 0x0114, all -> 0x0166 }
            if (r2 <= 0) goto L_0x00f6
            java.lang.Object r2 = r12.get(r4)     // Catch:{ SQLiteException -> 0x0114, all -> 0x0166 }
            com.google.android.gms.tagmanager.ap r2 = (com.google.android.gms.tagmanager.ap) r2     // Catch:{ SQLiteException -> 0x0114, all -> 0x0166 }
            r5 = 1
            java.lang.String r5 = r3.getString(r5)     // Catch:{ SQLiteException -> 0x0114, all -> 0x0166 }
            r2.R(r5)     // Catch:{ SQLiteException -> 0x0114, all -> 0x0166 }
        L_0x00ba:
            int r2 = r4 + 1
            boolean r4 = r3.moveToNext()     // Catch:{ SQLiteException -> 0x0114, all -> 0x0166 }
            if (r4 != 0) goto L_0x017d
        L_0x00c2:
            if (r3 == 0) goto L_0x00c7
            r3.close()
        L_0x00c7:
            r2 = r12
            goto L_0x0010
        L_0x00ca:
            r2 = move-exception
            r3 = r2
            r4 = r12
            r2 = r11
        L_0x00ce:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x016e }
            r5.<init>()     // Catch:{ all -> 0x016e }
            java.lang.String r6 = "Error in peekHits fetching hitIds: "
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x016e }
            java.lang.String r3 = r3.getMessage()     // Catch:{ all -> 0x016e }
            java.lang.StringBuilder r3 = r5.append(r3)     // Catch:{ all -> 0x016e }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x016e }
            com.google.android.gms.tagmanager.bh.D(r3)     // Catch:{ all -> 0x016e }
            if (r4 == 0) goto L_0x0010
            r4.close()
            goto L_0x0010
        L_0x00ef:
            r2 = move-exception
        L_0x00f0:
            if (r12 == 0) goto L_0x00f5
            r12.close()
        L_0x00f5:
            throw r2
        L_0x00f6:
            java.lang.String r5 = "HitString for hitId %d too large.  Hit will be deleted."
            r2 = 1
            java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch:{ SQLiteException -> 0x0114, all -> 0x0166 }
            r7 = 0
            java.lang.Object r2 = r12.get(r4)     // Catch:{ SQLiteException -> 0x0114, all -> 0x0166 }
            com.google.android.gms.tagmanager.ap r2 = (com.google.android.gms.tagmanager.ap) r2     // Catch:{ SQLiteException -> 0x0114, all -> 0x0166 }
            long r8 = r2.dg()     // Catch:{ SQLiteException -> 0x0114, all -> 0x0166 }
            java.lang.Long r2 = java.lang.Long.valueOf(r8)     // Catch:{ SQLiteException -> 0x0114, all -> 0x0166 }
            r6[r7] = r2     // Catch:{ SQLiteException -> 0x0114, all -> 0x0166 }
            java.lang.String r2 = java.lang.String.format(r5, r6)     // Catch:{ SQLiteException -> 0x0114, all -> 0x0166 }
            com.google.android.gms.tagmanager.bh.D(r2)     // Catch:{ SQLiteException -> 0x0114, all -> 0x0166 }
            goto L_0x00ba
        L_0x0114:
            r2 = move-exception
            r13 = r3
        L_0x0116:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x015f }
            r3.<init>()     // Catch:{ all -> 0x015f }
            java.lang.String r4 = "Error in peekHits fetching hit url: "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ all -> 0x015f }
            java.lang.String r2 = r2.getMessage()     // Catch:{ all -> 0x015f }
            java.lang.StringBuilder r2 = r3.append(r2)     // Catch:{ all -> 0x015f }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x015f }
            com.google.android.gms.tagmanager.bh.D(r2)     // Catch:{ all -> 0x015f }
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ all -> 0x015f }
            r3.<init>()     // Catch:{ all -> 0x015f }
            r4 = 0
            java.util.Iterator r5 = r12.iterator()     // Catch:{ all -> 0x015f }
        L_0x013a:
            boolean r2 = r5.hasNext()     // Catch:{ all -> 0x015f }
            if (r2 == 0) goto L_0x0152
            java.lang.Object r2 = r5.next()     // Catch:{ all -> 0x015f }
            com.google.android.gms.tagmanager.ap r2 = (com.google.android.gms.tagmanager.ap) r2     // Catch:{ all -> 0x015f }
            java.lang.String r6 = r2.lJ()     // Catch:{ all -> 0x015f }
            boolean r6 = android.text.TextUtils.isEmpty(r6)     // Catch:{ all -> 0x015f }
            if (r6 == 0) goto L_0x015b
            if (r4 == 0) goto L_0x015a
        L_0x0152:
            if (r13 == 0) goto L_0x0157
            r13.close()
        L_0x0157:
            r2 = r3
            goto L_0x0010
        L_0x015a:
            r4 = 1
        L_0x015b:
            r3.add(r2)     // Catch:{ all -> 0x015f }
            goto L_0x013a
        L_0x015f:
            r2 = move-exception
        L_0x0160:
            if (r13 == 0) goto L_0x0165
            r13.close()
        L_0x0165:
            throw r2
        L_0x0166:
            r2 = move-exception
            r13 = r3
            goto L_0x0160
        L_0x0169:
            r2 = move-exception
            goto L_0x0116
        L_0x016b:
            r2 = move-exception
            r12 = r13
            goto L_0x00f0
        L_0x016e:
            r2 = move-exception
            r12 = r4
            goto L_0x00f0
        L_0x0171:
            r2 = move-exception
            r3 = r2
            r4 = r13
            r2 = r11
            goto L_0x00ce
        L_0x0177:
            r2 = move-exception
            r3 = r2
            r4 = r13
            r2 = r12
            goto L_0x00ce
        L_0x017d:
            r4 = r2
            goto L_0x009e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.ca.B(int):java.util.List");
    }

    /* access modifiers changed from: package-private */
    public void a(String[] strArr) {
        SQLiteDatabase S;
        boolean z = true;
        if (strArr != null && strArr.length != 0 && (S = S("Error opening database for deleteHits.")) != null) {
            try {
                S.delete("gtm_hits", String.format("HIT_ID in (%s)", new Object[]{TextUtils.join(",", Collections.nCopies(strArr.length, "?"))}), strArr);
                au auVar = this.agp;
                if (m2do() != 0) {
                    z = false;
                }
                auVar.s(z);
            } catch (SQLiteException e) {
                bh.D("Error deleting hits");
            }
        }
    }

    public void cl() {
        bh.C("GTM Dispatch running...");
        if (this.ago.cx()) {
            List<ap> B = B(40);
            if (B.isEmpty()) {
                bh.C("...nothing to dispatch");
                this.agp.s(true);
                return;
            }
            this.ago.g(B);
            if (lW() > 0) {
                cx.mL().cl();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int dn() {
        boolean z = true;
        long currentTimeMillis = this.aec.currentTimeMillis();
        if (currentTimeMillis <= this.wR + 86400000) {
            return 0;
        }
        this.wR = currentTimeMillis;
        SQLiteDatabase S = S("Error opening database for deleteStaleHits.");
        if (S == null) {
            return 0;
        }
        int delete = S.delete("gtm_hits", "HIT_TIME < ?", new String[]{Long.toString(this.aec.currentTimeMillis() - 2592000000L)});
        au auVar = this.agp;
        if (m2do() != 0) {
            z = false;
        }
        auVar.s(z);
        return delete;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: do  reason: not valid java name */
    public int m2do() {
        Cursor cursor = null;
        int i = 0;
        SQLiteDatabase S = S("Error opening database for getNumStoredHits.");
        if (S != null) {
            try {
                Cursor rawQuery = S.rawQuery("SELECT COUNT(*) from gtm_hits", (String[]) null);
                if (rawQuery.moveToFirst()) {
                    i = (int) rawQuery.getLong(0);
                }
                if (rawQuery != null) {
                    rawQuery.close();
                }
            } catch (SQLiteException e) {
                bh.D("Error getting numStoredHits");
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

    public void f(long j, String str) {
        dn();
        dm();
        g(j, str);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0040  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int lW() {
        /*
            r10 = this;
            r8 = 0
            r9 = 0
            java.lang.String r0 = "Error opening database for getNumStoredHits."
            android.database.sqlite.SQLiteDatabase r0 = r10.S(r0)
            if (r0 != 0) goto L_0x000b
        L_0x000a:
            return r8
        L_0x000b:
            java.lang.String r1 = "gtm_hits"
            r2 = 2
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x002f, all -> 0x003d }
            r3 = 0
            java.lang.String r4 = "hit_id"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x002f, all -> 0x003d }
            r3 = 1
            java.lang.String r4 = "hit_first_send_time"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x002f, all -> 0x003d }
            java.lang.String r3 = "hit_first_send_time=0"
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ SQLiteException -> 0x002f, all -> 0x003d }
            int r0 = r1.getCount()     // Catch:{ SQLiteException -> 0x004b, all -> 0x0044 }
            if (r1 == 0) goto L_0x002d
            r1.close()
        L_0x002d:
            r8 = r0
            goto L_0x000a
        L_0x002f:
            r0 = move-exception
            r0 = r9
        L_0x0031:
            java.lang.String r1 = "Error getting num untried hits"
            com.google.android.gms.tagmanager.bh.D(r1)     // Catch:{ all -> 0x0047 }
            if (r0 == 0) goto L_0x004e
            r0.close()
            r0 = r8
            goto L_0x002d
        L_0x003d:
            r0 = move-exception
        L_0x003e:
            if (r9 == 0) goto L_0x0043
            r9.close()
        L_0x0043:
            throw r0
        L_0x0044:
            r0 = move-exception
            r9 = r1
            goto L_0x003e
        L_0x0047:
            r1 = move-exception
            r9 = r0
            r0 = r1
            goto L_0x003e
        L_0x004b:
            r0 = move-exception
            r0 = r1
            goto L_0x0031
        L_0x004e:
            r0 = r8
            goto L_0x002d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.ca.lW():int");
    }
}
