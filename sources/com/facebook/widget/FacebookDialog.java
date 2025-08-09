package com.facebook.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import com.facebook.AppEventsLogger;
import com.facebook.FacebookException;
import com.facebook.FacebookGraphObjectException;
import com.facebook.NativeAppCallAttachmentStore;
import com.facebook.NativeAppCallContentProvider;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphObjectList;
import com.facebook.model.OpenGraphAction;
import com.facebook.model.OpenGraphObject;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FacebookDialog {
    public static final String COMPLETION_GESTURE_CANCEL = "cancel";
    private static final String EXTRA_DIALOG_COMPLETE_KEY = "com.facebook.platform.extra.DID_COMPLETE";
    private static final String EXTRA_DIALOG_COMPLETION_GESTURE_KEY = "com.facebook.platform.extra.COMPLETION_GESTURE";
    private static final String EXTRA_DIALOG_COMPLETION_ID_KEY = "com.facebook.platform.extra.POST_ID";
    private static NativeAppCallAttachmentStore attachmentStore;
    private Activity activity;
    private PendingCall appCall;
    private Fragment fragment;
    private OnPresentCallback onPresentCallback;

    public interface Callback {
        void onComplete(PendingCall pendingCall, Bundle bundle);

        void onError(PendingCall pendingCall, Exception exc, Bundle bundle);
    }

    private interface DialogFeature {
        String getAction();

        int getMinVersion();
    }

    interface OnPresentCallback {
        void onPresent(Context context) throws Exception;
    }

    public enum ShareDialogFeature implements DialogFeature {
        SHARE_DIALOG(NativeProtocol.PROTOCOL_VERSION_20130618),
        PHOTOS(NativeProtocol.PROTOCOL_VERSION_20140204);
        
        private int minVersion;

        private ShareDialogFeature(int minVersion2) {
            this.minVersion = minVersion2;
        }

        public String getAction() {
            return NativeProtocol.ACTION_FEED_DIALOG;
        }

        public int getMinVersion() {
            return this.minVersion;
        }
    }

    public enum MessageDialogFeature implements DialogFeature {
        MESSAGE_DIALOG(NativeProtocol.PROTOCOL_VERSION_20140204),
        PHOTOS(NativeProtocol.PROTOCOL_VERSION_20140324);
        
        private int minVersion;

        private MessageDialogFeature(int minVersion2) {
            this.minVersion = minVersion2;
        }

        public String getAction() {
            return NativeProtocol.ACTION_MESSAGE_DIALOG;
        }

        public int getMinVersion() {
            return this.minVersion;
        }
    }

    public enum OpenGraphActionDialogFeature implements DialogFeature {
        OG_ACTION_DIALOG(NativeProtocol.PROTOCOL_VERSION_20130618);
        
        private int minVersion;

        private OpenGraphActionDialogFeature(int minVersion2) {
            this.minVersion = minVersion2;
        }

        public String getAction() {
            return NativeProtocol.ACTION_OGACTIONPUBLISH_DIALOG;
        }

        public int getMinVersion() {
            return this.minVersion;
        }
    }

    public enum OpenGraphMessageDialogFeature implements DialogFeature {
        OG_MESSAGE_DIALOG(NativeProtocol.PROTOCOL_VERSION_20140204);
        
        private int minVersion;

        private OpenGraphMessageDialogFeature(int minVersion2) {
            this.minVersion = minVersion2;
        }

        public String getAction() {
            return NativeProtocol.ACTION_OGMESSAGEPUBLISH_DIALOG;
        }

        public int getMinVersion() {
            return this.minVersion;
        }
    }

    public static boolean getNativeDialogDidComplete(Bundle result) {
        return result.getBoolean(EXTRA_DIALOG_COMPLETE_KEY, false);
    }

    public static String getNativeDialogCompletionGesture(Bundle result) {
        return result.getString(EXTRA_DIALOG_COMPLETION_GESTURE_KEY);
    }

    public static String getNativeDialogPostId(Bundle result) {
        return result.getString(EXTRA_DIALOG_COMPLETION_ID_KEY);
    }

    private FacebookDialog(Activity activity2, Fragment fragment2, PendingCall appCall2, OnPresentCallback onPresentCallback2) {
        this.activity = activity2;
        this.fragment = fragment2;
        this.appCall = appCall2;
        this.onPresentCallback = onPresentCallback2;
    }

    public PendingCall present() {
        logDialogActivity(this.activity, this.fragment, getEventName(this.appCall.getRequestIntent()), AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_COMPLETED);
        if (this.onPresentCallback != null) {
            try {
                this.onPresentCallback.onPresent(this.activity);
            } catch (Exception e) {
                throw new FacebookException((Throwable) e);
            }
        }
        if (this.fragment != null) {
            this.fragment.startActivityForResult(this.appCall.getRequestIntent(), this.appCall.getRequestCode());
        } else {
            this.activity.startActivityForResult(this.appCall.getRequestIntent(), this.appCall.getRequestCode());
        }
        return this.appCall;
    }

    public static boolean handleActivityResult(Context context, PendingCall appCall2, int requestCode, Intent data, Callback callback) {
        if (requestCode != appCall2.getRequestCode()) {
            return false;
        }
        if (attachmentStore != null) {
            attachmentStore.cleanupAttachmentsForCall(context, appCall2.getCallId());
        }
        if (callback != null) {
            if (NativeProtocol.isErrorResult(data)) {
                callback.onError(appCall2, NativeProtocol.getErrorFromResult(data), data.getExtras());
            } else {
                callback.onComplete(appCall2, NativeProtocol.getSuccessResultsFromIntent(data));
            }
        }
        return true;
    }

    public static boolean canPresentShareDialog(Context context, ShareDialogFeature... features) {
        return handleCanPresent(context, EnumSet.of(ShareDialogFeature.SHARE_DIALOG, features));
    }

    public static boolean canPresentMessageDialog(Context context, MessageDialogFeature... features) {
        return handleCanPresent(context, EnumSet.of(MessageDialogFeature.MESSAGE_DIALOG, features));
    }

    public static boolean canPresentOpenGraphActionDialog(Context context, OpenGraphActionDialogFeature... features) {
        return handleCanPresent(context, EnumSet.of(OpenGraphActionDialogFeature.OG_ACTION_DIALOG, features));
    }

    public static boolean canPresentOpenGraphMessageDialog(Context context, OpenGraphMessageDialogFeature... features) {
        return handleCanPresent(context, EnumSet.of(OpenGraphMessageDialogFeature.OG_MESSAGE_DIALOG, features));
    }

    /* access modifiers changed from: private */
    public static boolean handleCanPresent(Context context, Iterable<? extends DialogFeature> features) {
        return getProtocolVersionForNativeDialog(context, getActionForFeatures(features), getMinVersionForFeatures(features)) != -1;
    }

    /* access modifiers changed from: private */
    public static int getProtocolVersionForNativeDialog(Context context, String action, int requiredVersion) {
        return NativeProtocol.getLatestAvailableProtocolVersionForAction(context, action, requiredVersion);
    }

    /* access modifiers changed from: private */
    public static NativeAppCallAttachmentStore getAttachmentStore() {
        if (attachmentStore == null) {
            attachmentStore = new NativeAppCallAttachmentStore();
        }
        return attachmentStore;
    }

    /* access modifiers changed from: private */
    public static int getMinVersionForFeatures(Iterable<? extends DialogFeature> features) {
        int minVersion = Integer.MIN_VALUE;
        for (DialogFeature feature : features) {
            minVersion = Math.max(minVersion, feature.getMinVersion());
        }
        return minVersion;
    }

    /* access modifiers changed from: private */
    public static String getActionForFeatures(Iterable<? extends DialogFeature> features) {
        Iterator i$ = features.iterator();
        if (i$.hasNext()) {
            return ((DialogFeature) i$.next()).getAction();
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static void logDialogActivity(Activity activity2, Fragment fragment2, String eventName, String outcome) {
        if (fragment2 != null) {
            activity2 = fragment2.getActivity();
        }
        AppEventsLogger logger = AppEventsLogger.newLogger(activity2);
        Bundle parameters = new Bundle();
        parameters.putString(AnalyticsEvents.PARAMETER_DIALOG_OUTCOME, outcome);
        logger.logSdkEvent(eventName, (Double) null, parameters);
    }

    private static String getEventName(Intent intent) {
        return getEventName(intent.getStringExtra(NativeProtocol.EXTRA_PROTOCOL_ACTION), intent.hasExtra(NativeProtocol.EXTRA_PHOTOS));
    }

    /* access modifiers changed from: private */
    public static String getEventName(String action, boolean hasPhotos) {
        if (action.equals(NativeProtocol.ACTION_FEED_DIALOG)) {
            if (hasPhotos) {
                return AnalyticsEvents.EVENT_NATIVE_DIALOG_TYPE_PHOTO_SHARE;
            }
            return AnalyticsEvents.EVENT_NATIVE_DIALOG_TYPE_SHARE;
        } else if (action.equals(NativeProtocol.ACTION_MESSAGE_DIALOG)) {
            return hasPhotos ? AnalyticsEvents.EVENT_NATIVE_DIALOG_TYPE_PHOTO_MESSAGE : AnalyticsEvents.EVENT_NATIVE_DIALOG_TYPE_MESSAGE;
        } else {
            if (action.equals(NativeProtocol.ACTION_OGACTIONPUBLISH_DIALOG)) {
                return AnalyticsEvents.EVENT_NATIVE_DIALOG_TYPE_OG_SHARE;
            }
            if (action.equals(NativeProtocol.ACTION_OGMESSAGEPUBLISH_DIALOG)) {
                return AnalyticsEvents.EVENT_NATIVE_DIALOG_TYPE_OG_MESSAGE;
            }
            throw new FacebookException("An unspecified action was presented");
        }
    }

    public static abstract class Builder<CONCRETE extends Builder<?>> {
        protected final Activity activity;
        protected final PendingCall appCall;
        protected final String applicationId;
        protected String applicationName;
        protected Fragment fragment;
        protected HashMap<String, File> imageAttachmentFiles = new HashMap<>();
        protected HashMap<String, Bitmap> imageAttachments = new HashMap<>();

        /* access modifiers changed from: package-private */
        public abstract EnumSet<? extends DialogFeature> getDialogFeatures();

        /* access modifiers changed from: protected */
        public abstract Bundle getMethodArguments();

        /* access modifiers changed from: protected */
        public abstract Bundle setBundleExtras(Bundle bundle);

        public Builder(Activity activity2) {
            Validate.notNull(activity2, "activity");
            this.activity = activity2;
            this.applicationId = Utility.getMetadataApplicationId(activity2);
            this.appCall = new PendingCall((int) NativeProtocol.DIALOG_REQUEST_CODE);
        }

        public CONCRETE setRequestCode(int requestCode) {
            this.appCall.setRequestCode(requestCode);
            return this;
        }

        public CONCRETE setApplicationName(String applicationName2) {
            this.applicationName = applicationName2;
            return this;
        }

        public CONCRETE setFragment(Fragment fragment2) {
            this.fragment = fragment2;
            return this;
        }

        public FacebookDialog build() {
            Bundle extras;
            validate();
            String action = FacebookDialog.getActionForFeatures(getDialogFeatures());
            int protocolVersion = FacebookDialog.getProtocolVersionForNativeDialog(this.activity, action, FacebookDialog.getMinVersionForFeatures(getDialogFeatures()));
            if (NativeProtocol.isVersionCompatibleWithBucketedIntent(protocolVersion)) {
                extras = getMethodArguments();
            } else {
                extras = setBundleExtras(new Bundle());
            }
            Intent intent = NativeProtocol.createPlatformActivityIntent(this.activity, this.appCall.getCallId().toString(), action, protocolVersion, this.applicationName, extras);
            if (intent == null) {
                FacebookDialog.logDialogActivity(this.activity, this.fragment, FacebookDialog.getEventName(action, extras.containsKey(NativeProtocol.EXTRA_PHOTOS)), AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_FAILED);
                throw new FacebookException("Unable to create Intent; this likely means the Facebook app is not installed.");
            }
            this.appCall.setRequestIntent(intent);
            return new FacebookDialog(this.activity, this.fragment, this.appCall, getOnPresentCallback());
        }

        public boolean canPresent() {
            return FacebookDialog.handleCanPresent(this.activity, getDialogFeatures());
        }

        /* access modifiers changed from: package-private */
        public void validate() {
        }

        /* access modifiers changed from: package-private */
        public OnPresentCallback getOnPresentCallback() {
            return new OnPresentCallback() {
                public void onPresent(Context context) throws Exception {
                    if (Builder.this.imageAttachments != null && Builder.this.imageAttachments.size() > 0) {
                        FacebookDialog.getAttachmentStore().addAttachmentsForCall(context, Builder.this.appCall.getCallId(), Builder.this.imageAttachments);
                    }
                    if (Builder.this.imageAttachmentFiles != null && Builder.this.imageAttachmentFiles.size() > 0) {
                        FacebookDialog.getAttachmentStore().addAttachmentFilesForCall(context, Builder.this.appCall.getCallId(), Builder.this.imageAttachmentFiles);
                    }
                }
            };
        }

        /* access modifiers changed from: protected */
        public List<String> addImageAttachments(Collection<Bitmap> bitmaps) {
            ArrayList<String> attachmentUrls = new ArrayList<>();
            for (Bitmap bitmap : bitmaps) {
                String attachmentName = UUID.randomUUID().toString();
                addImageAttachment(attachmentName, bitmap);
                attachmentUrls.add(NativeAppCallContentProvider.getAttachmentUrl(this.applicationId, this.appCall.getCallId(), attachmentName));
            }
            return attachmentUrls;
        }

        /* access modifiers changed from: protected */
        public List<String> addImageAttachmentFiles(Collection<File> bitmapFiles) {
            ArrayList<String> attachmentUrls = new ArrayList<>();
            for (File bitmapFile : bitmapFiles) {
                String attachmentName = UUID.randomUUID().toString();
                addImageAttachment(attachmentName, bitmapFile);
                attachmentUrls.add(NativeAppCallContentProvider.getAttachmentUrl(this.applicationId, this.appCall.getCallId(), attachmentName));
            }
            return attachmentUrls;
        }

        /* access modifiers changed from: package-private */
        public List<String> getImageAttachmentNames() {
            return new ArrayList(this.imageAttachments.keySet());
        }

        /* access modifiers changed from: protected */
        public void putExtra(Bundle extras, String key, String value) {
            if (value != null) {
                extras.putString(key, value);
            }
        }

        /* access modifiers changed from: protected */
        public CONCRETE addImageAttachment(String imageName, Bitmap bitmap) {
            this.imageAttachments.put(imageName, bitmap);
            return this;
        }

        /* access modifiers changed from: protected */
        public CONCRETE addImageAttachment(String imageName, File attachment) {
            this.imageAttachmentFiles.put(imageName, attachment);
            return this;
        }
    }

    private static abstract class ShareDialogBuilderBase<CONCRETE extends ShareDialogBuilderBase<?>> extends Builder<CONCRETE> {
        private String caption;
        private boolean dataErrorsFatal;
        private String description;
        private ArrayList<String> friends;
        protected String link;
        private String name;
        private String picture;
        private String place;
        private String ref;

        public ShareDialogBuilderBase(Activity activity) {
            super(activity);
        }

        public CONCRETE setName(String name2) {
            this.name = name2;
            return this;
        }

        public CONCRETE setCaption(String caption2) {
            this.caption = caption2;
            return this;
        }

        public CONCRETE setDescription(String description2) {
            this.description = description2;
            return this;
        }

        public CONCRETE setLink(String link2) {
            this.link = link2;
            return this;
        }

        public CONCRETE setPicture(String picture2) {
            this.picture = picture2;
            return this;
        }

        public CONCRETE setPlace(String place2) {
            this.place = place2;
            return this;
        }

        public CONCRETE setFriends(List<String> friends2) {
            this.friends = new ArrayList<>(friends2);
            return this;
        }

        public CONCRETE setRef(String ref2) {
            this.ref = ref2;
            return this;
        }

        public CONCRETE setDataErrorsFatal(boolean dataErrorsFatal2) {
            this.dataErrorsFatal = dataErrorsFatal2;
            return this;
        }

        /* access modifiers changed from: protected */
        public Bundle setBundleExtras(Bundle extras) {
            putExtra(extras, NativeProtocol.EXTRA_APPLICATION_ID, this.applicationId);
            putExtra(extras, NativeProtocol.EXTRA_APPLICATION_NAME, this.applicationName);
            putExtra(extras, NativeProtocol.EXTRA_TITLE, this.name);
            putExtra(extras, NativeProtocol.EXTRA_SUBTITLE, this.caption);
            putExtra(extras, NativeProtocol.EXTRA_DESCRIPTION, this.description);
            putExtra(extras, NativeProtocol.EXTRA_LINK, this.link);
            putExtra(extras, NativeProtocol.EXTRA_IMAGE, this.picture);
            putExtra(extras, NativeProtocol.EXTRA_PLACE_TAG, this.place);
            putExtra(extras, NativeProtocol.EXTRA_TITLE, this.name);
            putExtra(extras, NativeProtocol.EXTRA_REF, this.ref);
            extras.putBoolean(NativeProtocol.EXTRA_DATA_FAILURES_FATAL, this.dataErrorsFatal);
            if (!Utility.isNullOrEmpty(this.friends)) {
                extras.putStringArrayList(NativeProtocol.EXTRA_FRIEND_TAGS, this.friends);
            }
            return extras;
        }

        /* access modifiers changed from: protected */
        public Bundle getMethodArguments() {
            Bundle methodArguments = new Bundle();
            putExtra(methodArguments, NativeProtocol.METHOD_ARGS_TITLE, this.name);
            putExtra(methodArguments, NativeProtocol.METHOD_ARGS_SUBTITLE, this.caption);
            putExtra(methodArguments, NativeProtocol.METHOD_ARGS_DESCRIPTION, this.description);
            putExtra(methodArguments, NativeProtocol.METHOD_ARGS_LINK, this.link);
            putExtra(methodArguments, NativeProtocol.METHOD_ARGS_IMAGE, this.picture);
            putExtra(methodArguments, NativeProtocol.METHOD_ARGS_PLACE_TAG, this.place);
            putExtra(methodArguments, NativeProtocol.METHOD_ARGS_TITLE, this.name);
            putExtra(methodArguments, NativeProtocol.METHOD_ARGS_REF, this.ref);
            methodArguments.putBoolean(NativeProtocol.METHOD_ARGS_DATA_FAILURES_FATAL, this.dataErrorsFatal);
            if (!Utility.isNullOrEmpty(this.friends)) {
                methodArguments.putStringArrayList(NativeProtocol.METHOD_ARGS_FRIEND_TAGS, this.friends);
            }
            return methodArguments;
        }
    }

    public static class ShareDialogBuilder extends ShareDialogBuilderBase<ShareDialogBuilder> {
        public ShareDialogBuilder(Activity activity) {
            super(activity);
        }

        /* access modifiers changed from: package-private */
        public EnumSet<? extends DialogFeature> getDialogFeatures() {
            return EnumSet.of(ShareDialogFeature.SHARE_DIALOG);
        }
    }

    private static abstract class PhotoDialogBuilderBase<CONCRETE extends PhotoDialogBuilderBase<?>> extends Builder<CONCRETE> {
        static int MAXIMUM_PHOTO_COUNT = 6;
        private ArrayList<String> friends;
        private ArrayList<String> imageAttachmentUrls = new ArrayList<>();
        private String place;

        /* access modifiers changed from: package-private */
        public abstract int getMaximumNumberOfPhotos();

        public PhotoDialogBuilderBase(Activity activity) {
            super(activity);
        }

        public CONCRETE setPlace(String place2) {
            this.place = place2;
            return this;
        }

        public CONCRETE setFriends(List<String> friends2) {
            this.friends = new ArrayList<>(friends2);
            return this;
        }

        public CONCRETE addPhotos(Collection<Bitmap> photos) {
            this.imageAttachmentUrls.addAll(addImageAttachments(photos));
            return this;
        }

        public CONCRETE addPhotoFiles(Collection<File> photos) {
            this.imageAttachmentUrls.addAll(addImageAttachmentFiles(photos));
            return this;
        }

        /* access modifiers changed from: package-private */
        public void validate() {
            super.validate();
            if (this.imageAttachmentUrls.isEmpty()) {
                throw new FacebookException("Must specify at least one photo.");
            } else if (this.imageAttachmentUrls.size() > getMaximumNumberOfPhotos()) {
                throw new FacebookException(String.format("Cannot add more than %d photos.", new Object[]{Integer.valueOf(getMaximumNumberOfPhotos())}));
            }
        }

        /* access modifiers changed from: protected */
        public Bundle setBundleExtras(Bundle extras) {
            putExtra(extras, NativeProtocol.EXTRA_APPLICATION_ID, this.applicationId);
            putExtra(extras, NativeProtocol.EXTRA_APPLICATION_NAME, this.applicationName);
            putExtra(extras, NativeProtocol.EXTRA_PLACE_TAG, this.place);
            extras.putStringArrayList(NativeProtocol.EXTRA_PHOTOS, this.imageAttachmentUrls);
            if (!Utility.isNullOrEmpty(this.friends)) {
                extras.putStringArrayList(NativeProtocol.EXTRA_FRIEND_TAGS, this.friends);
            }
            return extras;
        }

        /* access modifiers changed from: protected */
        public Bundle getMethodArguments() {
            Bundle methodArgs = new Bundle();
            putExtra(methodArgs, NativeProtocol.METHOD_ARGS_PLACE_TAG, this.place);
            methodArgs.putStringArrayList(NativeProtocol.METHOD_ARGS_PHOTOS, this.imageAttachmentUrls);
            if (!Utility.isNullOrEmpty(this.friends)) {
                methodArgs.putStringArrayList(NativeProtocol.METHOD_ARGS_FRIEND_TAGS, this.friends);
            }
            return methodArgs;
        }
    }

    public static class PhotoShareDialogBuilder extends PhotoDialogBuilderBase<PhotoShareDialogBuilder> {
        public PhotoShareDialogBuilder(Activity activity) {
            super(activity);
        }

        /* access modifiers changed from: package-private */
        public EnumSet<? extends DialogFeature> getDialogFeatures() {
            return EnumSet.of(ShareDialogFeature.SHARE_DIALOG, ShareDialogFeature.PHOTOS);
        }

        /* access modifiers changed from: package-private */
        public int getMaximumNumberOfPhotos() {
            return MAXIMUM_PHOTO_COUNT;
        }
    }

    public static class PhotoMessageDialogBuilder extends PhotoDialogBuilderBase<PhotoMessageDialogBuilder> {
        public PhotoMessageDialogBuilder(Activity activity) {
            super(activity);
        }

        /* access modifiers changed from: package-private */
        public EnumSet<? extends DialogFeature> getDialogFeatures() {
            return EnumSet.of(MessageDialogFeature.MESSAGE_DIALOG, MessageDialogFeature.PHOTOS);
        }

        /* access modifiers changed from: package-private */
        public int getMaximumNumberOfPhotos() {
            return MAXIMUM_PHOTO_COUNT;
        }
    }

    public static class MessageDialogBuilder extends ShareDialogBuilderBase<MessageDialogBuilder> {
        public MessageDialogBuilder(Activity activity) {
            super(activity);
        }

        /* access modifiers changed from: package-private */
        public EnumSet<? extends DialogFeature> getDialogFeatures() {
            return EnumSet.of(MessageDialogFeature.MESSAGE_DIALOG);
        }
    }

    private static abstract class OpenGraphDialogBuilderBase<CONCRETE extends OpenGraphDialogBuilderBase<?>> extends Builder<CONCRETE> {
        private OpenGraphAction action;
        private String actionType;
        private boolean dataErrorsFatal;
        private String previewPropertyName;

        @Deprecated
        public OpenGraphDialogBuilderBase(Activity activity, OpenGraphAction action2, String actionType2, String previewPropertyName2) {
            super(activity);
            Validate.notNull(action2, "action");
            Validate.notNullOrEmpty(actionType2, "actionType");
            Validate.notNullOrEmpty(previewPropertyName2, "previewPropertyName");
            if (action2.getProperty(previewPropertyName2) == null) {
                throw new IllegalArgumentException("A property named \"" + previewPropertyName2 + "\" was not found on the action.  The name of " + "the preview property must match the name of an action property.");
            }
            String typeOnAction = action2.getType();
            if (Utility.isNullOrEmpty(typeOnAction) || typeOnAction.equals(actionType2)) {
                this.action = action2;
                this.actionType = actionType2;
                this.previewPropertyName = previewPropertyName2;
                return;
            }
            throw new IllegalArgumentException("'actionType' must match the type of 'action' if it is specified. Consider using OpenGraphDialogBuilderBase(Activity activity, OpenGraphAction action, String previewPropertyName) instead.");
        }

        public OpenGraphDialogBuilderBase(Activity activity, OpenGraphAction action2, String previewPropertyName2) {
            super(activity);
            Validate.notNull(action2, "action");
            Validate.notNullOrEmpty(action2.getType(), "action.getType()");
            Validate.notNullOrEmpty(previewPropertyName2, "previewPropertyName");
            if (action2.getProperty(previewPropertyName2) == null) {
                throw new IllegalArgumentException("A property named \"" + previewPropertyName2 + "\" was not found on the action.  The name of " + "the preview property must match the name of an action property.");
            }
            this.action = action2;
            this.actionType = action2.getType();
            this.previewPropertyName = previewPropertyName2;
        }

        public CONCRETE setDataErrorsFatal(boolean dataErrorsFatal2) {
            this.dataErrorsFatal = dataErrorsFatal2;
            return this;
        }

        public CONCRETE setImageAttachmentsForAction(List<Bitmap> bitmaps) {
            return setImageAttachmentsForAction(bitmaps, false);
        }

        public CONCRETE setImageAttachmentsForAction(List<Bitmap> bitmaps, boolean isUserGenerated) {
            Validate.containsNoNulls(bitmaps, "bitmaps");
            if (this.action == null) {
                throw new FacebookException("Can not set attachments prior to setting action.");
            }
            updateActionAttachmentUrls(addImageAttachments(bitmaps), isUserGenerated);
            return this;
        }

        public CONCRETE setImageAttachmentFilesForAction(List<File> bitmapFiles) {
            return setImageAttachmentFilesForAction(bitmapFiles, false);
        }

        public CONCRETE setImageAttachmentFilesForAction(List<File> bitmapFiles, boolean isUserGenerated) {
            Validate.containsNoNulls(bitmapFiles, "bitmapFiles");
            if (this.action == null) {
                throw new FacebookException("Can not set attachments prior to setting action.");
            }
            updateActionAttachmentUrls(addImageAttachmentFiles(bitmapFiles), isUserGenerated);
            return this;
        }

        private void updateActionAttachmentUrls(List<String> attachmentUrls, boolean isUserGenerated) {
            List<JSONObject> attachments = this.action.getImage();
            if (attachments == null) {
                attachments = new ArrayList<>(attachmentUrls.size());
            }
            for (String url : attachmentUrls) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("url", url);
                    if (isUserGenerated) {
                        jsonObject.put(NativeProtocol.IMAGE_USER_GENERATED_KEY, true);
                    }
                    attachments.add(jsonObject);
                } catch (JSONException e) {
                    throw new FacebookException("Unable to attach images", e);
                }
            }
            this.action.setImage(attachments);
        }

        public CONCRETE setImageAttachmentsForObject(String objectProperty, List<Bitmap> bitmaps) {
            return setImageAttachmentsForObject(objectProperty, bitmaps, false);
        }

        public CONCRETE setImageAttachmentsForObject(String objectProperty, List<Bitmap> bitmaps, boolean isUserGenerated) {
            Validate.notNull(objectProperty, "objectProperty");
            Validate.containsNoNulls(bitmaps, "bitmaps");
            if (this.action == null) {
                throw new FacebookException("Can not set attachments prior to setting action.");
            }
            updateObjectAttachmentUrls(objectProperty, addImageAttachments(bitmaps), isUserGenerated);
            return this;
        }

        public CONCRETE setImageAttachmentFilesForObject(String objectProperty, List<File> bitmapFiles) {
            return setImageAttachmentFilesForObject(objectProperty, bitmapFiles, false);
        }

        public CONCRETE setImageAttachmentFilesForObject(String objectProperty, List<File> bitmapFiles, boolean isUserGenerated) {
            Validate.notNull(objectProperty, "objectProperty");
            Validate.containsNoNulls(bitmapFiles, "bitmapFiles");
            if (this.action == null) {
                throw new FacebookException("Can not set attachments prior to setting action.");
            }
            updateObjectAttachmentUrls(objectProperty, addImageAttachmentFiles(bitmapFiles), isUserGenerated);
            return this;
        }

        /* access modifiers changed from: package-private */
        public void updateObjectAttachmentUrls(String objectProperty, List<String> attachmentUrls, boolean isUserGenerated) {
            try {
                OpenGraphObject object = (OpenGraphObject) this.action.getPropertyAs(objectProperty, OpenGraphObject.class);
                if (object == null) {
                    throw new IllegalArgumentException("Action does not contain a property '" + objectProperty + "'");
                } else if (!object.getCreateObject()) {
                    throw new IllegalArgumentException("The Open Graph object in '" + objectProperty + "' is not marked for creation");
                } else {
                    GraphObjectList<GraphObject> attachments = object.getImage();
                    if (attachments == null) {
                        attachments = GraphObject.Factory.createList(GraphObject.class);
                    }
                    for (String url : attachmentUrls) {
                        GraphObject graphObject = GraphObject.Factory.create();
                        graphObject.setProperty("url", url);
                        if (isUserGenerated) {
                            graphObject.setProperty(NativeProtocol.IMAGE_USER_GENERATED_KEY, true);
                        }
                        attachments.add(graphObject);
                    }
                    object.setImage(attachments);
                }
            } catch (FacebookGraphObjectException e) {
                throw new IllegalArgumentException("Property '" + objectProperty + "' is not a graph object");
            }
        }

        /* access modifiers changed from: protected */
        public Bundle setBundleExtras(Bundle extras) {
            putExtra(extras, NativeProtocol.EXTRA_PREVIEW_PROPERTY_NAME, this.previewPropertyName);
            putExtra(extras, NativeProtocol.EXTRA_ACTION_TYPE, this.actionType);
            extras.putBoolean(NativeProtocol.EXTRA_DATA_FAILURES_FATAL, this.dataErrorsFatal);
            putExtra(extras, NativeProtocol.EXTRA_ACTION, flattenChildrenOfGraphObject(this.action.getInnerJSONObject()).toString());
            return extras;
        }

        /* access modifiers changed from: protected */
        public Bundle getMethodArguments() {
            Bundle methodArgs = new Bundle();
            putExtra(methodArgs, NativeProtocol.METHOD_ARGS_PREVIEW_PROPERTY_NAME, this.previewPropertyName);
            putExtra(methodArgs, NativeProtocol.METHOD_ARGS_ACTION_TYPE, this.actionType);
            methodArgs.putBoolean(NativeProtocol.METHOD_ARGS_DATA_FAILURES_FATAL, this.dataErrorsFatal);
            putExtra(methodArgs, NativeProtocol.METHOD_ARGS_ACTION, flattenChildrenOfGraphObject(this.action.getInnerJSONObject()).toString());
            return methodArgs;
        }

        private JSONObject flattenChildrenOfGraphObject(JSONObject graphObject) {
            try {
                JSONObject graphObject2 = new JSONObject(graphObject.toString());
                try {
                    Iterator<String> keys = graphObject2.keys();
                    while (keys.hasNext()) {
                        String key = keys.next();
                        if (!key.equalsIgnoreCase("image")) {
                            graphObject2.put(key, flattenObject(graphObject2.get(key)));
                        }
                    }
                    return graphObject2;
                } catch (JSONException e) {
                    e = e;
                    JSONObject jSONObject = graphObject2;
                    throw new FacebookException((Throwable) e);
                }
            } catch (JSONException e2) {
                e = e2;
                throw new FacebookException((Throwable) e);
            }
        }

        private Object flattenObject(Object object) throws JSONException {
            if (object == null) {
                return null;
            }
            if (object instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) object;
                if (jsonObject.optBoolean(NativeProtocol.OPEN_GRAPH_CREATE_OBJECT_KEY)) {
                    return object;
                }
                if (jsonObject.has("id")) {
                    return jsonObject.getString("id");
                }
                if (jsonObject.has("url")) {
                    return jsonObject.getString("url");
                }
                return object;
            } else if (!(object instanceof JSONArray)) {
                return object;
            } else {
                JSONArray jsonArray = (JSONArray) object;
                JSONArray newArray = new JSONArray();
                int length = jsonArray.length();
                for (int i = 0; i < length; i++) {
                    newArray.put(flattenObject(jsonArray.get(i)));
                }
                return newArray;
            }
        }
    }

    public static class OpenGraphActionDialogBuilder extends OpenGraphDialogBuilderBase<OpenGraphActionDialogBuilder> {
        @Deprecated
        public OpenGraphActionDialogBuilder(Activity activity, OpenGraphAction action, String actionType, String previewPropertyName) {
            super(activity, action, actionType, previewPropertyName);
        }

        public OpenGraphActionDialogBuilder(Activity activity, OpenGraphAction action, String previewPropertyName) {
            super(activity, action, previewPropertyName);
        }

        /* access modifiers changed from: package-private */
        public EnumSet<? extends DialogFeature> getDialogFeatures() {
            return EnumSet.of(OpenGraphActionDialogFeature.OG_ACTION_DIALOG);
        }
    }

    public static class OpenGraphMessageDialogBuilder extends OpenGraphDialogBuilderBase<OpenGraphMessageDialogBuilder> {
        public OpenGraphMessageDialogBuilder(Activity activity, OpenGraphAction action, String previewPropertyName) {
            super(activity, action, previewPropertyName);
        }

        /* access modifiers changed from: package-private */
        public EnumSet<? extends DialogFeature> getDialogFeatures() {
            return EnumSet.of(OpenGraphMessageDialogFeature.OG_MESSAGE_DIALOG);
        }
    }

    public static class PendingCall implements Parcelable {
        public static final Parcelable.Creator<PendingCall> CREATOR = new Parcelable.Creator<PendingCall>() {
            public PendingCall createFromParcel(Parcel in) {
                return new PendingCall(in);
            }

            public PendingCall[] newArray(int size) {
                return new PendingCall[size];
            }
        };
        private UUID callId;
        private int requestCode;
        private Intent requestIntent;

        public PendingCall(int requestCode2) {
            this.callId = UUID.randomUUID();
            this.requestCode = requestCode2;
        }

        private PendingCall(Parcel in) {
            this.callId = UUID.fromString(in.readString());
            this.requestIntent = (Intent) in.readParcelable((ClassLoader) null);
            this.requestCode = in.readInt();
        }

        /* access modifiers changed from: private */
        public void setRequestIntent(Intent requestIntent2) {
            this.requestIntent = requestIntent2;
        }

        public Intent getRequestIntent() {
            return this.requestIntent;
        }

        public UUID getCallId() {
            return this.callId;
        }

        /* access modifiers changed from: private */
        public void setRequestCode(int requestCode2) {
            this.requestCode = requestCode2;
        }

        public int getRequestCode() {
            return this.requestCode;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.callId.toString());
            parcel.writeParcelable(this.requestIntent, 0);
            parcel.writeInt(this.requestCode);
        }
    }
}
