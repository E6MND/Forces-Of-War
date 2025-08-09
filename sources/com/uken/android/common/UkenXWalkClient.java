package com.uken.android.common;

import org.xwalk.core.internal.XWalkClient;
import org.xwalk.core.internal.XWalkViewInternal;

public final class UkenXWalkClient extends XWalkClient {
    public void onLoadResource(XWalkViewInternal view, String url) {
        super.onLoadResource(view, url);
    }

    public UkenXWalkClient(XWalkViewInternal xWalkView) {
        super(xWalkView);
    }
}
