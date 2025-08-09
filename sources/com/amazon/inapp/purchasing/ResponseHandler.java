package com.amazon.inapp.purchasing;

import android.content.Context;
import android.content.Intent;

interface ResponseHandler {
    void handleResponse(Context context, Intent intent);
}
