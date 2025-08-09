package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.appindexing.AppIndexApi;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.fq;
import com.google.android.gms.internal.iw;
import com.google.android.gms.plus.PlusShare;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;

public class fs implements SafeParcelable {
    public static final ft CREATOR = new ft();
    public final String mN;
    final int xJ;
    final fj yn;
    final long yo;
    final int yp;
    final fh yq;

    fs(int i, fj fjVar, long j, int i2, String str, fh fhVar) {
        this.xJ = i;
        this.yn = fjVar;
        this.yo = j;
        this.yp = i2;
        this.mN = str;
        this.yq = fhVar;
    }

    public fs(fj fjVar, long j, int i) {
        this(1, fjVar, j, i, (String) null, (fh) null);
    }

    public fs(String str, Intent intent, String str2, Uri uri, String str3, List<AppIndexApi.AppIndexingLink> list) {
        this(1, a(str, intent), System.currentTimeMillis(), 0, (String) null, a(intent, str2, uri, str3, list));
    }

    static fh a(Intent intent, String str, Uri uri, String str2, List<AppIndexApi.AppIndexingLink> list) {
        String string;
        ArrayList arrayList = new ArrayList();
        arrayList.add(ab(str));
        if (uri != null) {
            arrayList.add(e(uri));
        }
        if (list != null) {
            arrayList.add(a(list));
        }
        String action = intent.getAction();
        if (action != null) {
            arrayList.add(f("intent_action", action));
        }
        String dataString = intent.getDataString();
        if (dataString != null) {
            arrayList.add(f("intent_data", dataString));
        }
        ComponentName component = intent.getComponent();
        if (component != null) {
            arrayList.add(f("intent_activity", component.getClassName()));
        }
        Bundle extras = intent.getExtras();
        if (!(extras == null || (string = extras.getString("intent_extra_data_key")) == null)) {
            arrayList.add(f("intent_extra_data", string));
        }
        return new fh(str2, true, (fl[]) arrayList.toArray(new fl[arrayList.size()]));
    }

    public static fj a(String str, Intent intent) {
        return new fj(str, "", f(intent));
    }

    private static fl a(List<AppIndexApi.AppIndexingLink> list) {
        iw.a aVar = new iw.a();
        iw.a.C0064a[] aVarArr = new iw.a.C0064a[list.size()];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < aVarArr.length) {
                aVarArr[i2] = new iw.a.C0064a();
                AppIndexApi.AppIndexingLink appIndexingLink = list.get(i2);
                aVarArr[i2].Ux = appIndexingLink.appIndexingUrl.toString();
                aVarArr[i2].Uy = appIndexingLink.webUrl.toString();
                aVarArr[i2].viewId = appIndexingLink.viewId;
                i = i2 + 1;
            } else {
                aVar.Uv = aVarArr;
                return new fl(mf.d(aVar), new fq.a("outlinks").w(true).aa(".private:outLinks").Z("blob").dL());
            }
        }
    }

    private static fl ab(String str) {
        return new fl(str, new fq.a(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE).I(1).x(true).aa("name").dL(), "text1");
    }

    private static fl e(Uri uri) {
        return new fl(uri.toString(), new fq.a("web_url").I(4).w(true).aa("url").dL());
    }

    private static fl f(String str, String str2) {
        return new fl(str2, new fq.a(str).w(true).dL(), str);
    }

    private static String f(Intent intent) {
        String uri = intent.toUri(1);
        CRC32 crc32 = new CRC32();
        try {
            crc32.update(uri.getBytes("UTF-8"));
            return Long.toHexString(crc32.getValue());
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    public int describeContents() {
        ft ftVar = CREATOR;
        return 0;
    }

    public String toString() {
        return String.format("UsageInfo[documentId=%s, timestamp=%d, usageType=%d]", new Object[]{this.yn, Long.valueOf(this.yo), Integer.valueOf(this.yp)});
    }

    public void writeToParcel(Parcel dest, int flags) {
        ft ftVar = CREATOR;
        ft.a(this, dest, flags);
    }
}
