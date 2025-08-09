package com.google.android.gms.games.internal.request;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.internal.constants.RequestUpdateResultOutcome;
import com.google.android.gms.internal.hn;
import java.util.HashMap;
import java.util.Set;

public final class RequestUpdateOutcomes {
    private static final String[] Sk = {"requestId", "outcome"};
    private final int CQ;
    private final HashMap<String, Integer> Sl;

    public static final class Builder {
        private int CQ = 0;
        private HashMap<String, Integer> Sl = new HashMap<>();

        public Builder cw(int i) {
            this.CQ = i;
            return this;
        }

        public RequestUpdateOutcomes it() {
            return new RequestUpdateOutcomes(this.CQ, this.Sl);
        }

        public Builder v(String str, int i) {
            if (RequestUpdateResultOutcome.isValid(i)) {
                this.Sl.put(str, Integer.valueOf(i));
            }
            return this;
        }
    }

    private RequestUpdateOutcomes(int statusCode, HashMap<String, Integer> outcomeMap) {
        this.CQ = statusCode;
        this.Sl = outcomeMap;
    }

    public static RequestUpdateOutcomes U(DataHolder dataHolder) {
        Builder builder = new Builder();
        builder.cw(dataHolder.getStatusCode());
        int count = dataHolder.getCount();
        for (int i = 0; i < count; i++) {
            int ae = dataHolder.ae(i);
            builder.v(dataHolder.c("requestId", i, ae), dataHolder.b("outcome", i, ae));
        }
        return builder.it();
    }

    public Set<String> getRequestIds() {
        return this.Sl.keySet();
    }

    public int getRequestOutcome(String requestId) {
        hn.b(this.Sl.containsKey(requestId), (Object) "Request " + requestId + " was not part of the update operation!");
        return this.Sl.get(requestId).intValue();
    }
}
