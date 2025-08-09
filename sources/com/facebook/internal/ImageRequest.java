package com.facebook.internal;

import android.content.Context;
import android.net.Uri;
import java.net.URI;
import java.net.URISyntaxException;

public class ImageRequest {
    private static final String HEIGHT_PARAM = "height";
    private static final String MIGRATION_PARAM = "migration_overrides";
    private static final String MIGRATION_VALUE = "{october_2012:true}";
    private static final String PROFILEPIC_URL_FORMAT = "https://graph.facebook.com/%s/picture";
    public static final int UNSPECIFIED_DIMENSION = 0;
    private static final String WIDTH_PARAM = "width";
    private boolean allowCachedRedirects;
    private Callback callback;
    private Object callerTag;
    private Context context;
    private URI imageUri;

    public interface Callback {
        void onCompleted(ImageResponse imageResponse);
    }

    public static URI getProfilePictureUrl(String userId, int width, int height) throws URISyntaxException {
        Validate.notNullOrEmpty(userId, "userId");
        int width2 = Math.max(width, 0);
        int height2 = Math.max(height, 0);
        if (width2 == 0 && height2 == 0) {
            throw new IllegalArgumentException("Either width or height must be greater than 0");
        }
        Uri.Builder builder = new Uri.Builder().encodedPath(String.format(PROFILEPIC_URL_FORMAT, new Object[]{userId}));
        if (height2 != 0) {
            builder.appendQueryParameter(HEIGHT_PARAM, String.valueOf(height2));
        }
        if (width2 != 0) {
            builder.appendQueryParameter(WIDTH_PARAM, String.valueOf(width2));
        }
        builder.appendQueryParameter(MIGRATION_PARAM, MIGRATION_VALUE);
        return new URI(builder.toString());
    }

    private ImageRequest(Builder builder) {
        this.context = builder.context;
        this.imageUri = builder.imageUrl;
        this.callback = builder.callback;
        this.allowCachedRedirects = builder.allowCachedRedirects;
        this.callerTag = builder.callerTag == null ? new Object() : builder.callerTag;
    }

    public Context getContext() {
        return this.context;
    }

    public URI getImageUri() {
        return this.imageUri;
    }

    public Callback getCallback() {
        return this.callback;
    }

    public boolean isCachedRedirectAllowed() {
        return this.allowCachedRedirects;
    }

    public Object getCallerTag() {
        return this.callerTag;
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public boolean allowCachedRedirects;
        /* access modifiers changed from: private */
        public Callback callback;
        /* access modifiers changed from: private */
        public Object callerTag;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public URI imageUrl;

        public Builder(Context context2, URI imageUrl2) {
            Validate.notNull(imageUrl2, "imageUrl");
            this.context = context2;
            this.imageUrl = imageUrl2;
        }

        public Builder setCallback(Callback callback2) {
            this.callback = callback2;
            return this;
        }

        public Builder setCallerTag(Object callerTag2) {
            this.callerTag = callerTag2;
            return this;
        }

        public Builder setAllowCachedRedirects(boolean allowCachedRedirects2) {
            this.allowCachedRedirects = allowCachedRedirects2;
            return this;
        }

        public ImageRequest build() {
            return new ImageRequest(this);
        }
    }
}
