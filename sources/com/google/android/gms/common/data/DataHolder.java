package com.google.android.gms.common.data;

import android.database.CharArrayBuffer;
import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.hn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class DataHolder implements SafeParcelable {
    public static final f CREATOR = new f();
    private static final a EL = new a(new String[0], (String) null) {
    };
    private final int CQ;
    private final String[] ED;
    Bundle EE;
    private final CursorWindow[] EF;
    private final Bundle EG;
    int[] EH;
    int EI;
    private Object EJ;
    private boolean EK;
    boolean mClosed;
    private final int xJ;

    public static class a {
        /* access modifiers changed from: private */
        public final String[] ED;
        /* access modifiers changed from: private */
        public final ArrayList<HashMap<String, Object>> EM;
        private final String EN;
        private final HashMap<Object, Integer> EO;
        private boolean EP;
        private String EQ;

        private a(String[] strArr, String str) {
            this.ED = (String[]) hn.f(strArr);
            this.EM = new ArrayList<>();
            this.EN = str;
            this.EO = new HashMap<>();
            this.EP = false;
            this.EQ = null;
        }
    }

    DataHolder(int versionCode, String[] columns, CursorWindow[] windows, int statusCode, Bundle metadata) {
        this.mClosed = false;
        this.EK = true;
        this.xJ = versionCode;
        this.ED = columns;
        this.EF = windows;
        this.CQ = statusCode;
        this.EG = metadata;
    }

    private DataHolder(a builder, int statusCode, Bundle metadata) {
        this(builder.ED, a(builder, -1), statusCode, metadata);
    }

    public DataHolder(String[] columns, CursorWindow[] windows, int statusCode, Bundle metadata) {
        this.mClosed = false;
        this.EK = true;
        this.xJ = 1;
        this.ED = (String[]) hn.f(columns);
        this.EF = (CursorWindow[]) hn.f(windows);
        this.CQ = statusCode;
        this.EG = metadata;
        eR();
    }

    public static DataHolder a(int i, Bundle bundle) {
        return new DataHolder(EL, i, bundle);
    }

    private static CursorWindow[] a(a aVar, int i) {
        int i2;
        int i3;
        int i4;
        CursorWindow cursorWindow;
        if (aVar.ED.length == 0) {
            return new CursorWindow[0];
        }
        ArrayList b = (i < 0 || i >= aVar.EM.size()) ? aVar.EM : aVar.EM.subList(0, i);
        int size = b.size();
        CursorWindow cursorWindow2 = new CursorWindow(false);
        ArrayList arrayList = new ArrayList();
        arrayList.add(cursorWindow2);
        cursorWindow2.setNumColumns(aVar.ED.length);
        int i5 = 0;
        int i6 = 0;
        while (i5 < size) {
            try {
                if (!cursorWindow2.allocRow()) {
                    Log.d("DataHolder", "Allocating additional cursor window for large data set (row " + i5 + ")");
                    cursorWindow2 = new CursorWindow(false);
                    cursorWindow2.setStartPosition(i5);
                    cursorWindow2.setNumColumns(aVar.ED.length);
                    arrayList.add(cursorWindow2);
                    if (!cursorWindow2.allocRow()) {
                        Log.e("DataHolder", "Unable to allocate row to hold data.");
                        arrayList.remove(cursorWindow2);
                        return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
                    }
                    i2 = 0;
                } else {
                    i2 = i6;
                }
                Map map = (Map) b.get(i5);
                boolean z = true;
                for (int i7 = 0; i7 < aVar.ED.length && z; i7++) {
                    String str = aVar.ED[i7];
                    Object obj = map.get(str);
                    if (obj == null) {
                        z = cursorWindow2.putNull(i2, i7);
                    } else if (obj instanceof String) {
                        z = cursorWindow2.putString((String) obj, i2, i7);
                    } else if (obj instanceof Long) {
                        z = cursorWindow2.putLong(((Long) obj).longValue(), i2, i7);
                    } else if (obj instanceof Integer) {
                        z = cursorWindow2.putLong((long) ((Integer) obj).intValue(), i2, i7);
                    } else if (obj instanceof Boolean) {
                        z = cursorWindow2.putLong(((Boolean) obj).booleanValue() ? 1 : 0, i2, i7);
                    } else if (obj instanceof byte[]) {
                        z = cursorWindow2.putBlob((byte[]) obj, i2, i7);
                    } else if (obj instanceof Double) {
                        z = cursorWindow2.putDouble(((Double) obj).doubleValue(), i2, i7);
                    } else if (obj instanceof Float) {
                        z = cursorWindow2.putDouble((double) ((Float) obj).floatValue(), i2, i7);
                    } else {
                        throw new IllegalArgumentException("Unsupported object for column " + str + ": " + obj);
                    }
                }
                if (!z) {
                    Log.d("DataHolder", "Couldn't populate window data for row " + i5 + " - allocating new window.");
                    cursorWindow2.freeLastRow();
                    CursorWindow cursorWindow3 = new CursorWindow(false);
                    cursorWindow3.setNumColumns(aVar.ED.length);
                    arrayList.add(cursorWindow3);
                    i4 = i5 - 1;
                    cursorWindow = cursorWindow3;
                    i3 = 0;
                } else {
                    i3 = i2 + 1;
                    i4 = i5;
                    cursorWindow = cursorWindow2;
                }
                cursorWindow2 = cursorWindow;
                i5 = i4 + 1;
                i6 = i3;
            } catch (RuntimeException e) {
                RuntimeException runtimeException = e;
                int size2 = arrayList.size();
                for (int i8 = 0; i8 < size2; i8++) {
                    ((CursorWindow) arrayList.get(i8)).close();
                }
                throw runtimeException;
            }
        }
        return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
    }

    public static DataHolder af(int i) {
        return a(i, (Bundle) null);
    }

    private void e(String str, int i) {
        if (this.EE == null || !this.EE.containsKey(str)) {
            throw new IllegalArgumentException("No such column: " + str);
        } else if (isClosed()) {
            throw new IllegalArgumentException("Buffer is closed.");
        } else if (i < 0 || i >= this.EI) {
            throw new CursorIndexOutOfBoundsException(i, this.EI);
        }
    }

    public long a(String str, int i, int i2) {
        e(str, i);
        return this.EF[i2].getLong(i, this.EE.getInt(str));
    }

    public void a(String str, int i, int i2, CharArrayBuffer charArrayBuffer) {
        e(str, i);
        this.EF[i2].copyStringToBuffer(i, this.EE.getInt(str), charArrayBuffer);
    }

    public int ae(int i) {
        int i2 = 0;
        hn.A(i >= 0 && i < this.EI);
        while (true) {
            if (i2 >= this.EH.length) {
                break;
            } else if (i < this.EH[i2]) {
                i2--;
                break;
            } else {
                i2++;
            }
        }
        return i2 == this.EH.length ? i2 - 1 : i2;
    }

    public boolean av(String str) {
        return this.EE.containsKey(str);
    }

    public int b(String str, int i, int i2) {
        e(str, i);
        return this.EF[i2].getInt(i, this.EE.getInt(str));
    }

    public void b(Object obj) {
        this.EJ = obj;
    }

    public String c(String str, int i, int i2) {
        e(str, i);
        return this.EF[i2].getString(i, this.EE.getInt(str));
    }

    public void close() {
        synchronized (this) {
            if (!this.mClosed) {
                this.mClosed = true;
                for (CursorWindow close : this.EF) {
                    close.close();
                }
            }
        }
    }

    public boolean d(String str, int i, int i2) {
        e(str, i);
        return Long.valueOf(this.EF[i2].getLong(i, this.EE.getInt(str))).longValue() == 1;
    }

    public int describeContents() {
        return 0;
    }

    public float e(String str, int i, int i2) {
        e(str, i);
        return this.EF[i2].getFloat(i, this.EE.getInt(str));
    }

    public Bundle eP() {
        return this.EG;
    }

    public void eR() {
        this.EE = new Bundle();
        for (int i = 0; i < this.ED.length; i++) {
            this.EE.putInt(this.ED[i], i);
        }
        this.EH = new int[this.EF.length];
        int i2 = 0;
        for (int i3 = 0; i3 < this.EF.length; i3++) {
            this.EH[i3] = i2;
            i2 += this.EF[i3].getNumRows() - (i2 - this.EF[i3].getStartPosition());
        }
        this.EI = i2;
    }

    /* access modifiers changed from: package-private */
    public String[] eS() {
        return this.ED;
    }

    /* access modifiers changed from: package-private */
    public CursorWindow[] eT() {
        return this.EF;
    }

    public byte[] f(String str, int i, int i2) {
        e(str, i);
        return this.EF[i2].getBlob(i, this.EE.getInt(str));
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            if (this.EK && this.EF.length > 0 && !isClosed()) {
                Log.e("DataBuffer", "Internal data leak within a DataBuffer object detected!  Be sure to explicitly call close() on all DataBuffer extending objects when you are done with them. (" + (this.EJ == null ? "internal object: " + toString() : this.EJ.toString()) + ")");
                close();
            }
        } finally {
            super.finalize();
        }
    }

    public Uri g(String str, int i, int i2) {
        String c = c(str, i, i2);
        if (c == null) {
            return null;
        }
        return Uri.parse(c);
    }

    public int getCount() {
        return this.EI;
    }

    public int getStatusCode() {
        return this.CQ;
    }

    /* access modifiers changed from: package-private */
    public int getVersionCode() {
        return this.xJ;
    }

    public boolean h(String str, int i, int i2) {
        e(str, i);
        return this.EF[i2].isNull(i, this.EE.getInt(str));
    }

    public boolean isClosed() {
        boolean z;
        synchronized (this) {
            z = this.mClosed;
        }
        return z;
    }

    public void writeToParcel(Parcel dest, int flags) {
        f.a(this, dest, flags);
    }
}
