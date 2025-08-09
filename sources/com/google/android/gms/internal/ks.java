package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import com.google.android.gms.plus.model.moments.ItemScope;
import com.google.android.gms.plus.model.moments.Moment;

public final class ks extends d implements Moment {
    private kq adm;

    public ks(DataHolder dataHolder, int i) {
        super(dataHolder, i);
    }

    private kq kz() {
        synchronized (this) {
            if (this.adm == null) {
                byte[] byteArray = getByteArray("momentImpl");
                Parcel obtain = Parcel.obtain();
                obtain.unmarshall(byteArray, 0, byteArray.length);
                obtain.setDataPosition(0);
                this.adm = kq.CREATOR.createFromParcel(obtain);
                obtain.recycle();
            }
        }
        return this.adm;
    }

    public String getId() {
        return kz().getId();
    }

    public ItemScope getResult() {
        return kz().getResult();
    }

    public String getStartDate() {
        return kz().getStartDate();
    }

    public ItemScope getTarget() {
        return kz().getTarget();
    }

    public String getType() {
        return kz().getType();
    }

    public boolean hasId() {
        return kz().hasId();
    }

    public boolean hasResult() {
        return kz().hasId();
    }

    public boolean hasStartDate() {
        return kz().hasStartDate();
    }

    public boolean hasTarget() {
        return kz().hasTarget();
    }

    public boolean hasType() {
        return kz().hasType();
    }

    /* renamed from: ky */
    public kq freeze() {
        return kz();
    }
}
