package com.amazon.inapp.purchasing;

import android.os.RemoteException;
import com.amazon.android.framework.exception.KiwiException;
import com.amazon.inapp.purchasing.GetUserIdResponse;
import com.amazon.venezia.command.SuccessResult;
import java.util.Map;

final class KiwiGetUserIdCommandTask extends KiwiBaseCommandTask {
    private static final String COMMAND_NAME = "get_userId";
    private static final String COMMAND_VERSION = "1.0";
    private static final String TAG = "KiwiGetUserIdCommandTask";

    KiwiGetUserIdCommandTask(String str) {
        super(COMMAND_NAME, COMMAND_VERSION, str);
    }

    private void notifyObserver(final GetUserIdResponse getUserIdResponse) {
        postRunnableToMainLooper(new Runnable() {
            public void run() {
                PurchasingObserver purchasingObserver = PurchasingManager.getPurchasingObserver();
                if (purchasingObserver != null) {
                    if (Logger.isTraceOn()) {
                        Logger.trace(KiwiGetUserIdCommandTask.TAG, "Invoking onGetUserIdResponse with " + getUserIdResponse);
                    }
                    purchasingObserver.onGetUserIdResponse(getUserIdResponse);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onSuccess(SuccessResult successResult) throws RemoteException, KiwiException {
        String str;
        GetUserIdResponse.GetUserIdRequestStatus getUserIdRequestStatus;
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "onSuccess");
        }
        Map data = successResult.getData();
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "data: " + data);
        }
        String str2 = (String) data.get("userId");
        if (!isNullOrEmpty(str2)) {
            str = str2;
            getUserIdRequestStatus = GetUserIdResponse.GetUserIdRequestStatus.SUCCESSFUL;
        } else {
            if (Logger.isTraceOn()) {
                Logger.trace(TAG, "found null or empty user ID");
            }
            str = null;
            getUserIdRequestStatus = GetUserIdResponse.GetUserIdRequestStatus.FAILED;
        }
        notifyObserver(new GetUserIdResponse(getRequestId(), getUserIdRequestStatus, str));
    }

    /* access modifiers changed from: protected */
    public void sendFailedResponse() {
        notifyObserver(new GetUserIdResponse(getRequestId(), GetUserIdResponse.GetUserIdRequestStatus.FAILED, (String) null));
    }
}
