package com.google.android.gms.dynamic;

import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.internal.hn;

public abstract class g<T> {
    private final String Mi;
    private T Mj;

    public static class a extends Exception {
        public a(String str) {
            super(str);
        }

        public a(String str, Throwable th) {
            super(str, th);
        }
    }

    protected g(String str) {
        this.Mi = str;
    }

    /* access modifiers changed from: protected */
    public final T D(Context context) throws a {
        if (this.Mj == null) {
            hn.f(context);
            Context remoteContext = GooglePlayServicesUtil.getRemoteContext(context);
            if (remoteContext == null) {
                throw new a("Could not get remote context.");
            }
            try {
                this.Mj = d((IBinder) remoteContext.getClassLoader().loadClass(this.Mi).newInstance());
            } catch (ClassNotFoundException e) {
                throw new a("Could not load creator class.");
            } catch (InstantiationException e2) {
                throw new a("Could not instantiate creator.");
            } catch (IllegalAccessException e3) {
                throw new a("Could not access creator.");
            }
        }
        return this.Mj;
    }

    /* access modifiers changed from: protected */
    public abstract T d(IBinder iBinder);
}
