package org.chromium.ui.base;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.chromium.base.CalledByNative;
import org.chromium.base.ContentUriUtils;
import org.chromium.base.JNINamespace;
import org.chromium.ui.R;
import org.chromium.ui.base.WindowAndroid;

@JNINamespace("ui")
class SelectFileDialog implements WindowAndroid.IntentCallback {
    private static final String ALL_AUDIO_TYPES = "audio/*";
    private static final String ALL_IMAGE_TYPES = "image/*";
    private static final String ALL_VIDEO_TYPES = "video/*";
    private static final String ANY_TYPES = "*/*";
    private static final String AUDIO_TYPE = "audio/";
    private static final String CAPTURE_IMAGE_DIRECTORY = "browser-photos";
    private static final String IMAGE_FILE_PATH = "images";
    private static final String IMAGE_TYPE = "image/";
    private static final String TAG = "SelectFileDialog";
    private static final String VIDEO_TYPE = "video/";
    private Uri mCameraOutputUri;
    private boolean mCapture;
    private List<String> mFileTypes;
    /* access modifiers changed from: private */
    public final long mNativeSelectFileDialog;

    private native void nativeOnFileNotSelected(long j);

    /* access modifiers changed from: private */
    public native void nativeOnFileSelected(long j, String str, String str2);

    /* access modifiers changed from: private */
    public native void nativeOnMultipleFilesSelected(long j, String[] strArr, String[] strArr2);

    private SelectFileDialog(long nativeSelectFileDialog) {
        this.mNativeSelectFileDialog = nativeSelectFileDialog;
    }

    @TargetApi(18)
    @CalledByNative
    private void selectFile(String[] fileTypes, boolean capture, boolean multiple, WindowAndroid window) {
        this.mFileTypes = new ArrayList(Arrays.asList(fileTypes));
        this.mCapture = capture;
        Intent chooser = new Intent("android.intent.action.CHOOSER");
        Intent camera = new Intent("android.media.action.IMAGE_CAPTURE");
        Context context = window.getApplicationContext();
        camera.setFlags(3);
        try {
            if (Build.VERSION.SDK_INT >= 18) {
                this.mCameraOutputUri = ContentUriUtils.getContentUriFromFile(context, getFileForImageCapture(context));
            } else {
                this.mCameraOutputUri = Uri.fromFile(getFileForImageCapture(context));
            }
        } catch (IOException e) {
            Log.e(TAG, "Cannot retrieve content uri from file", e);
        }
        if (this.mCameraOutputUri == null) {
            onFileNotSelected();
            return;
        }
        camera.putExtra("output", this.mCameraOutputUri);
        if (Build.VERSION.SDK_INT >= 18) {
            camera.setClipData(ClipData.newUri(context.getContentResolver(), IMAGE_FILE_PATH, this.mCameraOutputUri));
        }
        Intent camcorder = new Intent("android.media.action.VIDEO_CAPTURE");
        Intent soundRecorder = new Intent("android.provider.MediaStore.RECORD_SOUND");
        if (captureCamera()) {
            if (window.showIntent(camera, (WindowAndroid.IntentCallback) this, R.string.low_memory_error)) {
                return;
            }
        } else if (captureCamcorder()) {
            if (window.showIntent(camcorder, (WindowAndroid.IntentCallback) this, R.string.low_memory_error)) {
                return;
            }
        } else if (captureMicrophone() && window.showIntent(soundRecorder, (WindowAndroid.IntentCallback) this, R.string.low_memory_error)) {
            return;
        }
        Intent getContentIntent = new Intent("android.intent.action.GET_CONTENT");
        getContentIntent.addCategory("android.intent.category.OPENABLE");
        if (Build.VERSION.SDK_INT >= 18 && multiple) {
            getContentIntent.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
        }
        ArrayList<Intent> extraIntents = new ArrayList<>();
        if (!noSpecificType()) {
            if (shouldShowImageTypes()) {
                extraIntents.add(camera);
                getContentIntent.setType(ALL_IMAGE_TYPES);
            } else if (shouldShowVideoTypes()) {
                extraIntents.add(camcorder);
                getContentIntent.setType(ALL_VIDEO_TYPES);
            } else if (shouldShowAudioTypes()) {
                extraIntents.add(soundRecorder);
                getContentIntent.setType(ALL_AUDIO_TYPES);
            }
        }
        if (extraIntents.isEmpty()) {
            getContentIntent.setType(ANY_TYPES);
            extraIntents.add(camera);
            extraIntents.add(camcorder);
            extraIntents.add(soundRecorder);
        }
        chooser.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) extraIntents.toArray(new Intent[0]));
        chooser.putExtra("android.intent.extra.INTENT", getContentIntent);
        if (!window.showIntent(chooser, (WindowAndroid.IntentCallback) this, R.string.low_memory_error)) {
            onFileNotSelected();
        }
    }

    private File getFileForImageCapture(Context context) throws IOException {
        File path;
        if (Build.VERSION.SDK_INT >= 18) {
            path = new File(context.getFilesDir(), IMAGE_FILE_PATH);
            if (!path.exists() && !path.mkdir()) {
                throw new IOException("Folder cannot be created.");
            }
        } else {
            File externalDataDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            path = new File(externalDataDir.getAbsolutePath() + File.separator + CAPTURE_IMAGE_DIRECTORY);
            if (!path.exists() && !path.mkdirs()) {
                path = externalDataDir;
            }
        }
        return File.createTempFile(String.valueOf(System.currentTimeMillis()), ".jpg", path);
    }

    @TargetApi(18)
    public void onIntentCompleted(WindowAndroid window, int resultCode, ContentResolver contentResolver, Intent results) {
        String path;
        if (resultCode != -1) {
            onFileNotSelected();
        } else if (results == null) {
            if (AndroidProtocolHandler.FILE_SCHEME.equals(this.mCameraOutputUri.getScheme())) {
                path = this.mCameraOutputUri.getPath();
            } else {
                path = this.mCameraOutputUri.toString();
            }
            nativeOnFileSelected(this.mNativeSelectFileDialog, path, this.mCameraOutputUri.getLastPathSegment());
            window.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", this.mCameraOutputUri));
        } else if (Build.VERSION.SDK_INT >= 18 && results.getData() == null && results.getClipData() != null) {
            ClipData clipData = results.getClipData();
            int itemCount = clipData.getItemCount();
            if (itemCount == 0) {
                onFileNotSelected();
                return;
            }
            Uri[] filePathArray = new Uri[itemCount];
            for (int i = 0; i < itemCount; i++) {
                filePathArray[i] = clipData.getItemAt(i).getUri();
            }
            new GetDisplayNameTask(contentResolver, true).execute(filePathArray);
        } else if (AndroidProtocolHandler.FILE_SCHEME.equals(results.getData().getScheme())) {
            nativeOnFileSelected(this.mNativeSelectFileDialog, results.getData().getSchemeSpecificPart(), "");
        } else if ("content".equals(results.getScheme())) {
            new GetDisplayNameTask(contentResolver, false).execute(new Uri[]{results.getData()});
        } else {
            onFileNotSelected();
            window.showError(R.string.opening_file_error);
        }
    }

    /* access modifiers changed from: private */
    public void onFileNotSelected() {
        nativeOnFileNotSelected(this.mNativeSelectFileDialog);
    }

    private boolean noSpecificType() {
        return this.mFileTypes.size() != 1 || this.mFileTypes.contains(ANY_TYPES);
    }

    private boolean shouldShowTypes(String allTypes, String specificType) {
        if (noSpecificType() || this.mFileTypes.contains(allTypes)) {
            return true;
        }
        return acceptSpecificType(specificType);
    }

    private boolean shouldShowImageTypes() {
        return shouldShowTypes(ALL_IMAGE_TYPES, IMAGE_TYPE);
    }

    private boolean shouldShowVideoTypes() {
        return shouldShowTypes(ALL_VIDEO_TYPES, VIDEO_TYPE);
    }

    private boolean shouldShowAudioTypes() {
        return shouldShowTypes(ALL_AUDIO_TYPES, AUDIO_TYPE);
    }

    private boolean acceptsSpecificType(String type) {
        return this.mFileTypes.size() == 1 && TextUtils.equals(this.mFileTypes.get(0), type);
    }

    private boolean captureCamera() {
        return this.mCapture && acceptsSpecificType(ALL_IMAGE_TYPES);
    }

    private boolean captureCamcorder() {
        return this.mCapture && acceptsSpecificType(ALL_VIDEO_TYPES);
    }

    private boolean captureMicrophone() {
        return this.mCapture && acceptsSpecificType(ALL_AUDIO_TYPES);
    }

    private boolean acceptSpecificType(String accept) {
        for (String type : this.mFileTypes) {
            if (type.startsWith(accept)) {
                return true;
            }
        }
        return false;
    }

    private class GetDisplayNameTask extends AsyncTask<Uri, Void, String[]> {
        final ContentResolver mContentResolver;
        String[] mFilePaths;
        final boolean mIsMultiple;

        public GetDisplayNameTask(ContentResolver contentResolver, boolean isMultiple) {
            this.mContentResolver = contentResolver;
            this.mIsMultiple = isMultiple;
        }

        /* access modifiers changed from: protected */
        public String[] doInBackground(Uri... uris) {
            this.mFilePaths = new String[uris.length];
            String[] displayNames = new String[uris.length];
            int i = 0;
            while (i < uris.length) {
                try {
                    this.mFilePaths[i] = uris[i].toString();
                    displayNames[i] = ContentUriUtils.getDisplayName(uris[i], this.mContentResolver, "_display_name");
                    i++;
                } catch (SecurityException e) {
                    Log.w(SelectFileDialog.TAG, "Unable to extract results from the content provider");
                    return null;
                }
            }
            return displayNames;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(String[] result) {
            if (result == null) {
                SelectFileDialog.this.onFileNotSelected();
            } else if (this.mIsMultiple) {
                SelectFileDialog.this.nativeOnMultipleFilesSelected(SelectFileDialog.this.mNativeSelectFileDialog, this.mFilePaths, result);
            } else {
                SelectFileDialog.this.nativeOnFileSelected(SelectFileDialog.this.mNativeSelectFileDialog, this.mFilePaths[0], result[0]);
            }
        }
    }

    @CalledByNative
    private static SelectFileDialog create(long nativeSelectFileDialog) {
        return new SelectFileDialog(nativeSelectFileDialog);
    }
}
