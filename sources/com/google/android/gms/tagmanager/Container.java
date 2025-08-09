package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.c;
import com.google.android.gms.tagmanager.cd;
import com.google.android.gms.tagmanager.cq;
import com.google.android.gms.tagmanager.s;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Container {
    private final String aeq;
    private final DataLayer aer;
    private cs aes;
    private Map<String, FunctionCallMacroCallback> aet = new HashMap();
    private Map<String, FunctionCallTagCallback> aeu = new HashMap();
    private volatile long aev;
    private volatile String aew = "";
    private final Context mContext;

    public interface FunctionCallMacroCallback {
        Object getValue(String str, Map<String, Object> map);
    }

    public interface FunctionCallTagCallback {
        void execute(String str, Map<String, Object> map);
    }

    private class a implements s.a {
        private a() {
        }

        public Object b(String str, Map<String, Object> map) {
            FunctionCallMacroCallback bF = Container.this.bF(str);
            if (bF == null) {
                return null;
            }
            return bF.getValue(str, map);
        }
    }

    private class b implements s.a {
        private b() {
        }

        public Object b(String str, Map<String, Object> map) {
            FunctionCallTagCallback bG = Container.this.bG(str);
            if (bG != null) {
                bG.execute(str, map);
            }
            return dh.mX();
        }
    }

    Container(Context context, DataLayer dataLayer, String containerId, long lastRefreshTime, c.j resource) {
        this.mContext = context;
        this.aer = dataLayer;
        this.aeq = containerId;
        this.aev = lastRefreshTime;
        a(resource.fK);
        if (resource.fJ != null) {
            a(resource.fJ);
        }
    }

    Container(Context context, DataLayer dataLayer, String containerId, long lastRefreshTime, cq.c resource) {
        this.mContext = context;
        this.aer = dataLayer;
        this.aeq = containerId;
        this.aev = lastRefreshTime;
        a(resource);
    }

    private void a(c.f fVar) {
        if (fVar == null) {
            throw new NullPointerException();
        }
        try {
            a(cq.b(fVar));
        } catch (cq.g e) {
            bh.A("Not loading resource: " + fVar + " because it is invalid: " + e.toString());
        }
    }

    private void a(cq.c cVar) {
        this.aew = cVar.getVersion();
        cq.c cVar2 = cVar;
        a(new cs(this.mContext, cVar2, this.aer, new a(), new b(), bI(this.aew)));
    }

    private synchronized void a(cs csVar) {
        this.aes = csVar;
    }

    private void a(c.i[] iVarArr) {
        ArrayList arrayList = new ArrayList();
        for (c.i add : iVarArr) {
            arrayList.add(add);
        }
        li().h((List<c.i>) arrayList);
    }

    private synchronized cs li() {
        return this.aes;
    }

    /* access modifiers changed from: package-private */
    public FunctionCallMacroCallback bF(String str) {
        FunctionCallMacroCallback functionCallMacroCallback;
        synchronized (this.aet) {
            functionCallMacroCallback = this.aet.get(str);
        }
        return functionCallMacroCallback;
    }

    /* access modifiers changed from: package-private */
    public FunctionCallTagCallback bG(String str) {
        FunctionCallTagCallback functionCallTagCallback;
        synchronized (this.aeu) {
            functionCallTagCallback = this.aeu.get(str);
        }
        return functionCallTagCallback;
    }

    /* access modifiers changed from: package-private */
    public void bH(String str) {
        li().bH(str);
    }

    /* access modifiers changed from: package-private */
    public ag bI(String str) {
        if (cd.lY().lZ().equals(cd.a.CONTAINER_DEBUG)) {
        }
        return new bq();
    }

    public boolean getBoolean(String key) {
        cs li = li();
        if (li == null) {
            bh.A("getBoolean called for closed container.");
            return dh.mV().booleanValue();
        }
        try {
            return dh.n(li.cj(key).getObject()).booleanValue();
        } catch (Exception e) {
            bh.A("Calling getBoolean() threw an exception: " + e.getMessage() + " Returning default value.");
            return dh.mV().booleanValue();
        }
    }

    public String getContainerId() {
        return this.aeq;
    }

    public double getDouble(String key) {
        cs li = li();
        if (li == null) {
            bh.A("getDouble called for closed container.");
            return dh.mU().doubleValue();
        }
        try {
            return dh.m(li.cj(key).getObject()).doubleValue();
        } catch (Exception e) {
            bh.A("Calling getDouble() threw an exception: " + e.getMessage() + " Returning default value.");
            return dh.mU().doubleValue();
        }
    }

    public long getLastRefreshTime() {
        return this.aev;
    }

    public long getLong(String key) {
        cs li = li();
        if (li == null) {
            bh.A("getLong called for closed container.");
            return dh.mT().longValue();
        }
        try {
            return dh.l(li.cj(key).getObject()).longValue();
        } catch (Exception e) {
            bh.A("Calling getLong() threw an exception: " + e.getMessage() + " Returning default value.");
            return dh.mT().longValue();
        }
    }

    public String getString(String key) {
        cs li = li();
        if (li == null) {
            bh.A("getString called for closed container.");
            return dh.mX();
        }
        try {
            return dh.j(li.cj(key).getObject());
        } catch (Exception e) {
            bh.A("Calling getString() threw an exception: " + e.getMessage() + " Returning default value.");
            return dh.mX();
        }
    }

    public boolean isDefault() {
        return getLastRefreshTime() == 0;
    }

    /* access modifiers changed from: package-private */
    public String lh() {
        return this.aew;
    }

    public void registerFunctionCallMacroCallback(String customMacroName, FunctionCallMacroCallback customMacroCallback) {
        if (customMacroCallback == null) {
            throw new NullPointerException("Macro handler must be non-null");
        }
        synchronized (this.aet) {
            this.aet.put(customMacroName, customMacroCallback);
        }
    }

    public void registerFunctionCallTagCallback(String customTagName, FunctionCallTagCallback customTagCallback) {
        if (customTagCallback == null) {
            throw new NullPointerException("Tag callback must be non-null");
        }
        synchronized (this.aeu) {
            this.aeu.put(customTagName, customTagCallback);
        }
    }

    /* access modifiers changed from: package-private */
    public void release() {
        this.aes = null;
    }

    public void unregisterFunctionCallMacroCallback(String customMacroName) {
        synchronized (this.aet) {
            this.aet.remove(customMacroName);
        }
    }

    public void unregisterFunctionCallTagCallback(String customTagName) {
        synchronized (this.aeu) {
            this.aeu.remove(customTagName);
        }
    }
}
