package com.amazon.inapp.purchasing;

import android.content.Context;
import android.content.Intent;
import com.amazon.android.Kiwi;

final class KiwiResponseHandler implements ResponseHandler {
    static final String HANDLER_THREAD_NAME = "KiwiResponseHandlerHandlerThread";
    private static final String KEY_REQUEST_ID = "requestId";
    private static final String KEY_RESPONSE_TYPE = "response_type";
    private static final String TAG = "KiwiResponseHandler";
    private final HandlerAdapter _handler = HandlerManager.getHandlerAdapter(HANDLER_THREAD_NAME);

    private class PurchaseResponseHandlerRunnable extends ResponseHandlerRunnable {
        public PurchaseResponseHandlerRunnable(Context context, Intent intent) {
            super(context, intent);
        }

        public void run() {
            if (Logger.isTraceOn()) {
                Logger.trace(KiwiResponseHandler.TAG, "PurchaseResponseHandlerRunnable.run()");
            }
            String string = getIntent().getExtras().getString(KiwiResponseHandler.KEY_REQUEST_ID);
            if (Logger.isTraceOn()) {
                Logger.trace(KiwiResponseHandler.TAG, "PurchaseResponseHandlerRunnable.run: requestId: " + string);
            }
            if (string != null && string.trim().length() > 0) {
                Kiwi.addCommandToCommandTaskPipeline(new KiwiPurchaseResponseCommandTask(string));
            }
        }
    }

    private abstract class ResponseHandlerRunnable implements Runnable {
        private final Context _context;
        private final Intent _intent;

        public ResponseHandlerRunnable(Context context, Intent intent) {
            this._context = context;
            this._intent = intent;
        }

        /* access modifiers changed from: protected */
        public final Context getContext() {
            return this._context;
        }

        /* access modifiers changed from: protected */
        public final Intent getIntent() {
            return this._intent;
        }
    }

    enum ResponseType {
        purchase_response,
        item_response,
        updates_response
    }

    KiwiResponseHandler() {
    }

    public void handleResponse(Context context, Intent intent) {
        if (Logger.isTraceOn()) {
            Logger.trace(TAG, "handleResponse");
        }
        String string = intent.getExtras().getString("response_type");
        if (string != null) {
            try {
                ResponseType valueOf = ResponseType.valueOf(string);
                if (Logger.isTraceOn()) {
                    Logger.trace(TAG, "Found response type: " + valueOf);
                }
                PurchaseResponseHandlerRunnable purchaseResponseHandlerRunnable = null;
                switch (valueOf) {
                    case purchase_response:
                        purchaseResponseHandlerRunnable = new PurchaseResponseHandlerRunnable(context, intent);
                        break;
                }
                if (purchaseResponseHandlerRunnable != null) {
                    this._handler.post(purchaseResponseHandlerRunnable);
                }
            } catch (IllegalArgumentException e) {
                if (Logger.isTraceOn()) {
                    Logger.trace(TAG, "Invlid response type: " + string);
                }
            }
        } else if (Logger.isTraceOn()) {
            Logger.trace(TAG, "Invalid response type: null");
        }
    }
}
