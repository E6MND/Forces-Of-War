package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.Uri;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class i extends df {
    private static final String ID = com.google.android.gms.internal.a.ARBITRARY_PIXEL.toString();
    private static final String URL = b.URL.toString();
    private static final String aej = b.ADDITIONAL_PARAMS.toString();
    private static final String aek = b.UNREPEATABLE.toString();
    static final String ael = ("gtm_" + ID + "_unrepeatable");
    private static final Set<String> aem = new HashSet();
    private final a aen;
    private final Context mContext;

    public interface a {
        aq ld();
    }

    public i(final Context context) {
        this(context, new a() {
            public aq ld() {
                return y.K(context);
            }
        });
    }

    i(Context context, a aVar) {
        super(ID, URL);
        this.aen = aVar;
        this.mContext = context;
    }

    private synchronized boolean bB(String str) {
        boolean z = true;
        synchronized (this) {
            if (!bD(str)) {
                if (bC(str)) {
                    aem.add(str);
                } else {
                    z = false;
                }
            }
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public boolean bC(String str) {
        return this.mContext.getSharedPreferences(ael, 0).contains(str);
    }

    /* access modifiers changed from: package-private */
    public boolean bD(String str) {
        return aem.contains(str);
    }

    public void y(Map<String, d.a> map) {
        String j = map.get(aek) != null ? dh.j(map.get(aek)) : null;
        if (j == null || !bB(j)) {
            Uri.Builder buildUpon = Uri.parse(dh.j(map.get(URL))).buildUpon();
            d.a aVar = map.get(aej);
            if (aVar != null) {
                Object o = dh.o(aVar);
                if (!(o instanceof List)) {
                    bh.A("ArbitraryPixel: additional params not a list: not sending partial hit: " + buildUpon.build().toString());
                    return;
                }
                for (Object next : (List) o) {
                    if (!(next instanceof Map)) {
                        bh.A("ArbitraryPixel: additional params contains non-map: not sending partial hit: " + buildUpon.build().toString());
                        return;
                    }
                    for (Map.Entry entry : ((Map) next).entrySet()) {
                        buildUpon.appendQueryParameter(entry.getKey().toString(), entry.getValue().toString());
                    }
                }
            }
            String uri = buildUpon.build().toString();
            this.aen.ld().bR(uri);
            bh.C("ArbitraryPixel: url = " + uri);
            if (j != null) {
                synchronized (i.class) {
                    aem.add(j);
                    cy.a(this.mContext, ael, j, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                }
            }
        }
    }
}
