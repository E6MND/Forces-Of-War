package com.google.android.gms.common;

import android.content.Intent;

public class GooglePlayServicesRepairableException extends UserRecoverableException {
    private final int yV;

    GooglePlayServicesRepairableException(int connectionStatusCode, String msg, Intent intent) {
        super(msg, intent);
        this.yV = connectionStatusCode;
    }

    public int getConnectionStatusCode() {
        return this.yV;
    }
}
