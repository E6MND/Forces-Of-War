package org.chromium.content_public.browser;

import java.util.Locale;
import java.util.Map;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;
import org.chromium.content_public.common.Referrer;

@JNINamespace("content")
public class LoadUrlParams {
    static final /* synthetic */ boolean $assertionsDisabled = (!LoadUrlParams.class.desiredAssertionStatus());
    public static final int LOAD_TYPE_BROWSER_INITIATED_HTTP_POST = 1;
    public static final int LOAD_TYPE_DATA = 2;
    public static final int LOAD_TYPE_DEFAULT = 0;
    public static final int UA_OVERRIDE_FALSE = 1;
    public static final int UA_OVERRIDE_INHERIT = 0;
    public static final int UA_OVERRIDE_TRUE = 2;
    String mBaseUrlForDataUrl;
    boolean mCanLoadLocalResources;
    private Map<String, String> mExtraHeaders;
    boolean mIsRendererInitiated;
    int mLoadUrlType;
    byte[] mPostData;
    Referrer mReferrer;
    int mTransitionType;
    int mUaOverrideOption;
    String mUrl;
    private String mVerbatimHeaders;
    String mVirtualUrlForDataUrl;

    private static native boolean nativeIsDataScheme(String str);

    public LoadUrlParams(String url) {
        this(url, 0);
    }

    public LoadUrlParams(String url, int transitionType) {
        this.mUrl = url;
        this.mTransitionType = transitionType;
        this.mLoadUrlType = 0;
        this.mUaOverrideOption = 0;
        this.mPostData = null;
        this.mBaseUrlForDataUrl = null;
        this.mVirtualUrlForDataUrl = null;
    }

    public static LoadUrlParams createLoadDataParams(String data, String mimeType, boolean isBase64Encoded) {
        return createLoadDataParams(data, mimeType, isBase64Encoded, (String) null);
    }

    public static LoadUrlParams createLoadDataParams(String data, String mimeType, boolean isBase64Encoded, String charset) {
        StringBuilder dataUrl = new StringBuilder("data:");
        dataUrl.append(mimeType);
        if (charset != null && !charset.isEmpty()) {
            dataUrl.append(";charset=" + charset);
        }
        if (isBase64Encoded) {
            dataUrl.append(";base64");
        }
        dataUrl.append(",");
        dataUrl.append(data);
        LoadUrlParams params = new LoadUrlParams(dataUrl.toString());
        params.setLoadType(2);
        params.setTransitionType(1);
        return params;
    }

    public static LoadUrlParams createLoadDataParamsWithBaseUrl(String data, String mimeType, boolean isBase64Encoded, String baseUrl, String historyUrl) {
        return createLoadDataParamsWithBaseUrl(data, mimeType, isBase64Encoded, baseUrl, historyUrl, (String) null);
    }

    public static LoadUrlParams createLoadDataParamsWithBaseUrl(String data, String mimeType, boolean isBase64Encoded, String baseUrl, String historyUrl, String charset) {
        LoadUrlParams params = createLoadDataParams(data, mimeType, isBase64Encoded, charset);
        if (baseUrl == null || !baseUrl.toLowerCase(Locale.US).startsWith("data:")) {
            if (baseUrl == null) {
                baseUrl = "about:blank";
            }
            params.setBaseUrlForDataUrl(baseUrl);
            if (historyUrl == null) {
                historyUrl = "about:blank";
            }
            params.setVirtualUrlForDataUrl(historyUrl);
        }
        return params;
    }

    public static LoadUrlParams createLoadHttpPostParams(String url, byte[] postData) {
        LoadUrlParams params = new LoadUrlParams(url);
        params.setLoadType(1);
        params.setTransitionType(1);
        params.setPostData(postData);
        return params;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public String getBaseUrl() {
        return this.mBaseUrlForDataUrl;
    }

    public void setLoadType(int loadType) {
        this.mLoadUrlType = loadType;
    }

    public void setTransitionType(int transitionType) {
        this.mTransitionType = transitionType;
    }

    public int getTransitionType() {
        return this.mTransitionType;
    }

    public void setReferrer(Referrer referrer) {
        this.mReferrer = referrer;
    }

    public Referrer getReferrer() {
        return this.mReferrer;
    }

    public void setExtraHeaders(Map<String, String> extraHeaders) {
        this.mExtraHeaders = extraHeaders;
    }

    public Map<String, String> getExtraHeaders() {
        return this.mExtraHeaders;
    }

    public String getExtraHeadersString() {
        return getExtraHeadersString("\n", false);
    }

    public String getExtraHttpRequestHeadersString() {
        return getExtraHeadersString("\r\n", true);
    }

    private String getExtraHeadersString(String delimiter, boolean addTerminator) {
        if (this.mExtraHeaders == null) {
            return null;
        }
        StringBuilder headerBuilder = new StringBuilder();
        for (Map.Entry<String, String> header : this.mExtraHeaders.entrySet()) {
            if (headerBuilder.length() > 0) {
                headerBuilder.append(delimiter);
            }
            headerBuilder.append(header.getKey().toLowerCase(Locale.US));
            headerBuilder.append(":");
            headerBuilder.append(header.getValue());
        }
        if (addTerminator) {
            headerBuilder.append(delimiter);
        }
        return headerBuilder.toString();
    }

    public void setVerbatimHeaders(String headers) {
        this.mVerbatimHeaders = headers;
    }

    public String getVerbatimHeaders() {
        return this.mVerbatimHeaders;
    }

    public void setOverrideUserAgent(int uaOption) {
        this.mUaOverrideOption = uaOption;
    }

    public int getUserAgentOverrideOption() {
        return this.mUaOverrideOption;
    }

    public void setPostData(byte[] postData) {
        this.mPostData = postData;
    }

    public byte[] getPostData() {
        return this.mPostData;
    }

    public void setBaseUrlForDataUrl(String baseUrl) {
        this.mBaseUrlForDataUrl = baseUrl;
    }

    public String getVirtualUrlForDataUrl() {
        return this.mVirtualUrlForDataUrl;
    }

    public void setVirtualUrlForDataUrl(String virtualUrl) {
        this.mVirtualUrlForDataUrl = virtualUrl;
    }

    public void setCanLoadLocalResources(boolean canLoad) {
        this.mCanLoadLocalResources = canLoad;
    }

    public boolean getCanLoadLocalResources() {
        return this.mCanLoadLocalResources;
    }

    public int getLoadUrlType() {
        return this.mLoadUrlType;
    }

    public void setIsRendererInitiated(boolean rendererInitiated) {
        this.mIsRendererInitiated = rendererInitiated;
    }

    public boolean getIsRendererInitiated() {
        return this.mIsRendererInitiated;
    }

    public boolean isBaseUrlDataScheme() {
        if (this.mBaseUrlForDataUrl == null && this.mLoadUrlType == 2) {
            return true;
        }
        return nativeIsDataScheme(this.mBaseUrlForDataUrl);
    }

    @CalledByNative
    private static void initializeConstants(int loadTypeDefault, int loadTypeBrowserInitiatedHttpPost, int loadTypeData, int uaOverrideInherit, int uaOverrideFalse, int uaOverrideTrue) {
        if (!$assertionsDisabled && loadTypeDefault != 0) {
            throw new AssertionError();
        } else if (!$assertionsDisabled && 1 != loadTypeBrowserInitiatedHttpPost) {
            throw new AssertionError();
        } else if (!$assertionsDisabled && 2 != loadTypeData) {
            throw new AssertionError();
        } else if (!$assertionsDisabled && uaOverrideInherit != 0) {
            throw new AssertionError();
        } else if (!$assertionsDisabled && 1 != uaOverrideFalse) {
            throw new AssertionError();
        } else if (!$assertionsDisabled && 2 != uaOverrideTrue) {
            throw new AssertionError();
        }
    }
}
