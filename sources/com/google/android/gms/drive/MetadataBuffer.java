package com.google.android.gms.drive;

import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.internal.l;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.b;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.metadata.internal.e;
import com.google.android.gms.internal.ir;
import java.util.ArrayList;

public final class MetadataBuffer extends DataBuffer<Metadata> {
    private static final String[] HO;
    private final String HP;
    private a HQ;

    private static class a extends Metadata {
        private final DataHolder DD;
        private final int EA;
        /* access modifiers changed from: private */
        public final int HR;

        public a(DataHolder dataHolder, int i) {
            this.DD = dataHolder;
            this.HR = i;
            this.EA = dataHolder.ae(i);
        }

        /* access modifiers changed from: protected */
        public <T> T a(MetadataField<T> metadataField) {
            return metadataField.a(this.DD, this.HR, this.EA);
        }

        /* renamed from: gg */
        public Metadata freeze() {
            MetadataBundle gA = MetadataBundle.gA();
            for (MetadataField<com.google.android.gms.common.data.a> next : e.gz()) {
                if (!(next instanceof b) && next != ir.Kn) {
                    next.a(this.DD, gA, this.HR, this.EA);
                }
            }
            return new l(gA);
        }

        public boolean isDataValid() {
            return !this.DD.isClosed();
        }
    }

    static {
        ArrayList arrayList = new ArrayList();
        for (MetadataField<?> gx : e.gz()) {
            arrayList.addAll(gx.gx());
        }
        HO = (String[]) arrayList.toArray(new String[0]);
    }

    public MetadataBuffer(DataHolder dataHolder, String nextPageToken) {
        super(dataHolder);
        this.HP = nextPageToken;
        dataHolder.eP().setClassLoader(MetadataBuffer.class.getClassLoader());
    }

    public Metadata get(int row) {
        a aVar = this.HQ;
        if (aVar != null && aVar.HR == row) {
            return aVar;
        }
        a aVar2 = new a(this.DD, row);
        this.HQ = aVar2;
        return aVar2;
    }

    public String getNextPageToken() {
        return this.HP;
    }
}
