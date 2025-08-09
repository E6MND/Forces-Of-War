package com.chartboost.sdk.impl;

class ab extends aa {
    private bd<af> a = new bd<>();

    ab() {
    }

    /* access modifiers changed from: package-private */
    public void a(Class cls, af afVar) {
        this.a.a(cls, afVar);
    }

    /* JADX WARNING: Removed duplicated region for block: B:4:0x0019 A[LOOP:0: B:4:0x0019->B:7:0x002d, LOOP_START, PHI: r0 
      PHI: (r0v1 com.chartboost.sdk.impl.af) = (r0v0 com.chartboost.sdk.impl.af), (r0v11 com.chartboost.sdk.impl.af) binds: [B:3:0x000c, B:7:0x002d] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.Object r5, java.lang.StringBuilder r6) {
        /*
            r4 = this;
            java.lang.Object r1 = com.chartboost.sdk.impl.x.a((java.lang.Object) r5)
            if (r1 != 0) goto L_0x000c
            java.lang.String r0 = " null "
            r6.append(r0)
        L_0x000b:
            return
        L_0x000c:
            r0 = 0
            java.lang.Class r2 = r1.getClass()
            java.util.List r2 = com.chartboost.sdk.impl.bd.a(r2)
            java.util.Iterator r2 = r2.iterator()
        L_0x0019:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x002f
            java.lang.Object r0 = r2.next()
            java.lang.Class r0 = (java.lang.Class) r0
            com.chartboost.sdk.impl.bd<com.chartboost.sdk.impl.af> r3 = r4.a
            java.lang.Object r0 = r3.a((java.lang.Object) r0)
            com.chartboost.sdk.impl.af r0 = (com.chartboost.sdk.impl.af) r0
            if (r0 == 0) goto L_0x0019
        L_0x002f:
            if (r0 != 0) goto L_0x0045
            java.lang.Class r2 = r1.getClass()
            boolean r2 = r2.isArray()
            if (r2 == 0) goto L_0x0045
            com.chartboost.sdk.impl.bd<com.chartboost.sdk.impl.af> r0 = r4.a
            java.lang.Class<java.lang.Object[]> r2 = java.lang.Object[].class
            java.lang.Object r0 = r0.a((java.lang.Object) r2)
            com.chartboost.sdk.impl.af r0 = (com.chartboost.sdk.impl.af) r0
        L_0x0045:
            if (r0 != 0) goto L_0x0064
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "json can't serialize type : "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.Class r1 = r1.getClass()
            java.lang.StringBuilder r1 = r2.append(r1)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0064:
            r0.a(r1, r6)
            goto L_0x000b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.chartboost.sdk.impl.ab.a(java.lang.Object, java.lang.StringBuilder):void");
    }
}
