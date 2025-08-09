package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.internal.dt;

public final class dn {

    public interface a {
        void a(eg egVar);
    }

    public static en a(Context context, dt.a aVar, l lVar, ey eyVar, bu buVar, a aVar2) {
        Cdo doVar = new Cdo(context, aVar, lVar, eyVar, buVar, aVar2);
        doVar.start();
        return doVar;
    }
}
