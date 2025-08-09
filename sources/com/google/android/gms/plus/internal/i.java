package com.google.android.gms.plus.internal;

import android.content.Context;
import com.google.android.gms.common.Scopes;
import java.util.ArrayList;
import java.util.Arrays;

public class i {
    private String[] abS;
    private String abT;
    private String abU;
    private String abV;
    private PlusCommonExtras abX;
    private final ArrayList<String> abY = new ArrayList<>();
    private String[] abZ;
    private String yN;

    public i(Context context) {
        this.abU = context.getPackageName();
        this.abT = context.getPackageName();
        this.abX = new PlusCommonExtras();
        this.abY.add(Scopes.PLUS_LOGIN);
    }

    public i bz(String str) {
        this.yN = str;
        return this;
    }

    public i e(String... strArr) {
        this.abY.clear();
        this.abY.addAll(Arrays.asList(strArr));
        return this;
    }

    public i f(String... strArr) {
        this.abZ = strArr;
        return this;
    }

    public i kd() {
        this.abY.clear();
        return this;
    }

    public h ke() {
        if (this.yN == null) {
            this.yN = "<<default account>>";
        }
        return new h(this.yN, (String[]) this.abY.toArray(new String[this.abY.size()]), this.abZ, this.abS, this.abT, this.abU, this.abV, this.abX);
    }
}
