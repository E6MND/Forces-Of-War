package org.xwalk.core.internal.extension.api.messaging;

import com.facebook.Response;
import java.util.HashMap;

public class MessagingSmsConstMaps {
    public static final HashMap<String, Integer> smsDeliveryStatusDictS2I = new HashMap<>();
    public static final HashMap<Integer, String> smsDiliveryStatusDictI2S = new HashMap<>();
    public static final HashMap<Integer, String> smsStateDictI2S = new HashMap<>();
    public static final HashMap<String, Integer> smsStateDictS2I = new HashMap<>();
    public static final HashMap<String, String> smsTableColumnDict = new HashMap<>();
    public static final HashMap<String, String> sortOrderDict = new HashMap<>();

    static {
        smsTableColumnDict.put("id", "_id");
        smsTableColumnDict.put(MessagingSmsConsts.DATE, MessagingSmsConsts.DATE);
        smsTableColumnDict.put("from", MessagingSmsConsts.ADDRESS);
        smsTableColumnDict.put("state", MessagingSmsConsts.STATUS);
        smsTableColumnDict.put("error", MessagingSmsConsts.READ);
        smsDeliveryStatusDictS2I.put(Response.SUCCESS_KEY, -1);
        smsDeliveryStatusDictS2I.put("pending", 64);
        smsDeliveryStatusDictS2I.put(Response.SUCCESS_KEY, 0);
        smsDeliveryStatusDictS2I.put("error", 128);
        smsDiliveryStatusDictI2S.put(-1, Response.SUCCESS_KEY);
        smsDiliveryStatusDictI2S.put(64, "pending");
        smsDiliveryStatusDictI2S.put(0, Response.SUCCESS_KEY);
        smsDiliveryStatusDictI2S.put(128, "error");
        smsStateDictI2S.put(1, "received");
        smsStateDictI2S.put(3, "draft");
        smsStateDictI2S.put(4, "sending");
        smsStateDictI2S.put(6, "sending");
        smsStateDictI2S.put(2, "sent");
        smsStateDictI2S.put(5, "failed");
        smsStateDictS2I.put("received", 1);
        smsStateDictS2I.put("draft", 3);
        smsStateDictS2I.put("sending", 4);
        smsStateDictS2I.put("sent", 2);
        smsStateDictS2I.put("failed", 5);
        sortOrderDict.put("ascending", "ASC");
        sortOrderDict.put("descending", "DESC");
    }
}
