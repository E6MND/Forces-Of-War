package com.google.android.gms.plus.model.people;

import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.e;
import com.google.android.gms.internal.kt;
import com.google.android.gms.internal.le;

public final class PersonBuffer extends DataBuffer<Person> {
    private final e<kt> adW;

    public PersonBuffer(DataHolder dataHolder) {
        super(dataHolder);
        if (dataHolder.eP() == null || !dataHolder.eP().getBoolean("com.google.android.gms.plus.IsSafeParcelable", false)) {
            this.adW = null;
        } else {
            this.adW = new e<>(dataHolder, kt.CREATOR);
        }
    }

    public Person get(int position) {
        return this.adW != null ? this.adW.get(position) : new le(this.DD, position);
    }
}
