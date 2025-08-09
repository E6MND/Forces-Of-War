package com.google.android.gms.plus.model.moments;

import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.internal.ko;
import com.google.android.gms.internal.kq;
import java.util.HashSet;
import java.util.Set;

public interface Moment extends Freezable<Moment> {

    public static class Builder {
        private final Set<Integer> acp = new HashSet();
        private String adc;
        private ko adk;
        private ko adl;
        private String qU;
        private String xD;

        public Moment build() {
            return new kq(this.acp, this.xD, this.adk, this.adc, this.adl, this.qU);
        }

        public Builder setId(String id) {
            this.xD = id;
            this.acp.add(2);
            return this;
        }

        public Builder setResult(ItemScope result) {
            this.adk = (ko) result;
            this.acp.add(4);
            return this;
        }

        public Builder setStartDate(String startDate) {
            this.adc = startDate;
            this.acp.add(5);
            return this;
        }

        public Builder setTarget(ItemScope target) {
            this.adl = (ko) target;
            this.acp.add(6);
            return this;
        }

        public Builder setType(String type) {
            this.qU = type;
            this.acp.add(7);
            return this;
        }
    }

    String getId();

    ItemScope getResult();

    String getStartDate();

    ItemScope getTarget();

    String getType();

    boolean hasId();

    boolean hasResult();

    boolean hasStartDate();

    boolean hasTarget();

    boolean hasType();
}
