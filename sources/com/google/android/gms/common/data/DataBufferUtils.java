package com.google.android.gms.common.data;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Iterator;

public final class DataBufferUtils {
    private DataBufferUtils() {
    }

    public static <T, E extends Freezable<T>> ArrayList<T> freezeAndClose(DataBuffer<E> buffer) {
        ArrayList<T> arrayList = new ArrayList<>(buffer.getCount());
        try {
            Iterator<E> it = buffer.iterator();
            while (it.hasNext()) {
                arrayList.add(((Freezable) it.next()).freeze());
            }
            return arrayList;
        } finally {
            buffer.close();
        }
    }

    public static boolean hasNextPage(DataBuffer<?> buffer) {
        Bundle eP = buffer.eP();
        return (eP == null || eP.getString("next_page_token") == null) ? false : true;
    }

    public static boolean hasPrevPage(DataBuffer<?> buffer) {
        Bundle eP = buffer.eP();
        return (eP == null || eP.getString("prev_page_token") == null) ? false : true;
    }
}
