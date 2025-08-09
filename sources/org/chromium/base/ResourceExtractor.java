package org.chromium.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

public class ResourceExtractor {
    static final /* synthetic */ boolean $assertionsDisabled = (!ResourceExtractor.class.desiredAssertionStatus());
    private static final String ICU_DATA_FILENAME = "icudtl.dat";
    private static final String LAST_LANGUAGE = "Last language";
    private static final String LOGTAG = "ResourceExtractor";
    private static final String PAK_FILENAMES = "Pak filenames";
    private static final String V8_NATIVES_DATA_FILENAME = "natives_blob.bin";
    private static final String V8_SNAPSHOT_DATA_FILENAME = "snapshot_blob.bin";
    /* access modifiers changed from: private */
    public static boolean sExtractImplicitLocalePak = true;
    private static ResourceExtractor sInstance;
    /* access modifiers changed from: private */
    public static ResourceIntercepter sIntercepter = null;
    /* access modifiers changed from: private */
    public static String[] sMandatoryPaks = null;
    /* access modifiers changed from: private */
    public final Context mContext;
    private ExtractTask mExtractTask;

    public interface ResourceIntercepter {
        Set<String> getInterceptableResourceList();

        InputStream interceptLoadingForResource(String str);
    }

    private class ExtractTask extends AsyncTask<Void, Void, Void> {
        private static final int BUFFER_SIZE = 16384;

        public ExtractTask() {
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... unused) {
            File file;
            OutputStream os;
            OutputStream os2;
            Set<String> filesIncludingInterceptableFiles;
            File outputDir = ResourceExtractor.this.getOutputDir();
            if (outputDir.exists() || outputDir.mkdirs()) {
                String timestampFile = checkPakTimestamp(outputDir);
                if (timestampFile != null) {
                    ResourceExtractor.this.deleteFiles();
                }
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ResourceExtractor.this.mContext.getApplicationContext());
                HashSet<String> filenames = (HashSet) prefs.getStringSet(ResourceExtractor.PAK_FILENAMES, new HashSet());
                String currentLanguage = LocaleUtils.getDefaultLocale().split("-", 2)[0];
                if (!prefs.getString(ResourceExtractor.LAST_LANGUAGE, "").equals(currentLanguage) || filenames.size() < ResourceExtractor.sMandatoryPaks.length) {
                    prefs.edit().putString(ResourceExtractor.LAST_LANGUAGE, currentLanguage).apply();
                } else {
                    boolean filesPresent = true;
                    Iterator i$ = filenames.iterator();
                    while (true) {
                        if (!i$.hasNext()) {
                            break;
                        }
                        if (!new File(outputDir, i$.next()).exists()) {
                            filesPresent = false;
                            break;
                        }
                    }
                    if (filesPresent) {
                        return null;
                    }
                }
                StringBuilder p = new StringBuilder();
                for (String mandatoryPak : ResourceExtractor.sMandatoryPaks) {
                    if (p.length() > 0) {
                        p.append('|');
                    }
                    p.append("\\Q" + mandatoryPak + "\\E");
                }
                if (ResourceExtractor.sExtractImplicitLocalePak) {
                    if (p.length() > 0) {
                        p.append('|');
                    }
                    p.append(currentLanguage);
                    p.append("(-\\w+)?\\.pak");
                }
                Pattern paksToInstall = Pattern.compile(p.toString());
                AssetManager manager = ResourceExtractor.this.mContext.getResources().getAssets();
                byte[] buffer = null;
                try {
                    String[] files = manager.list("");
                    if (!(ResourceExtractor.sIntercepter == null || (filesIncludingInterceptableFiles = ResourceExtractor.sIntercepter.getInterceptableResourceList()) == null || filesIncludingInterceptableFiles.isEmpty())) {
                        for (String file2 : files) {
                            filesIncludingInterceptableFiles.add(file2);
                        }
                        files = new String[filesIncludingInterceptableFiles.size()];
                        filesIncludingInterceptableFiles.toArray(files);
                    }
                    for (String file3 : files) {
                        if (paksToInstall.matcher(file3).matches()) {
                            boolean isAppDataFile = file3.equals(ResourceExtractor.ICU_DATA_FILENAME) || file3.equals(ResourceExtractor.V8_NATIVES_DATA_FILENAME) || file3.equals(ResourceExtractor.V8_SNAPSHOT_DATA_FILENAME);
                            if (isAppDataFile) {
                                file = ResourceExtractor.this.getAppDataDir();
                            } else {
                                file = outputDir;
                            }
                            File file4 = new File(file, file3);
                            if (!file4.exists()) {
                                InputStream is = null;
                                os = null;
                                try {
                                    if (ResourceExtractor.sIntercepter != null) {
                                        is = ResourceExtractor.sIntercepter.interceptLoadingForResource(file3);
                                    }
                                    if (is == null) {
                                        is = manager.open(file3);
                                    }
                                    os2 = new FileOutputStream(file4);
                                    try {
                                        Log.i(ResourceExtractor.LOGTAG, "Extracting resource " + file3);
                                        if (buffer == null) {
                                            buffer = new byte[16384];
                                        }
                                        while (true) {
                                            int count = is.read(buffer, 0, 16384);
                                            if (count == -1) {
                                                break;
                                            }
                                            os2.write(buffer, 0, count);
                                        }
                                        os2.flush();
                                        if (file4.length() == 0) {
                                            throw new IOException(file3 + " extracted with 0 length!");
                                        }
                                        if (!isAppDataFile) {
                                            filenames.add(file3);
                                        } else {
                                            file4.setReadable(true, false);
                                        }
                                        if (is != null) {
                                            is.close();
                                        }
                                        if (os2 != null) {
                                            os2.close();
                                        }
                                    } catch (Throwable th) {
                                        th = th;
                                        os = os2;
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    if (is != null) {
                                        is.close();
                                    }
                                    if (os != null) {
                                        os.close();
                                    }
                                    throw th;
                                }
                            } else {
                                continue;
                            }
                        }
                    }
                    if (timestampFile != null) {
                        try {
                            new File(outputDir, timestampFile).createNewFile();
                        } catch (IOException e) {
                            Log.w(ResourceExtractor.LOGTAG, "Failed to write resource pak timestamp!");
                        }
                    }
                    prefs.edit().remove(ResourceExtractor.PAK_FILENAMES).apply();
                    prefs.edit().putStringSet(ResourceExtractor.PAK_FILENAMES, filenames).apply();
                    return null;
                } catch (IOException e2) {
                    Log.w(ResourceExtractor.LOGTAG, "Exception unpacking required pak resources: " + e2.getMessage());
                    ResourceExtractor.this.deleteFiles();
                    return null;
                } catch (Throwable th3) {
                    if (os2 != null) {
                        os2.close();
                    }
                    throw th3;
                }
            } else {
                Log.e(ResourceExtractor.LOGTAG, "Unable to create pak resources directory!");
                return null;
            }
        }

        private String checkPakTimestamp(File outputDir) {
            try {
                PackageInfo pi = ResourceExtractor.this.mContext.getPackageManager().getPackageInfo(ResourceExtractor.this.mContext.getPackageName(), 0);
                if (pi == null) {
                    return "pak_timestamp-";
                }
                String expectedTimestamp = "pak_timestamp-" + pi.versionCode + "-" + pi.lastUpdateTime;
                String[] timestamps = outputDir.list(new FilenameFilter() {
                    public boolean accept(File dir, String name) {
                        return name.startsWith("pak_timestamp-");
                    }
                });
                if (timestamps.length != 1 || !expectedTimestamp.equals(timestamps[0])) {
                    return expectedTimestamp;
                }
                return null;
            } catch (PackageManager.NameNotFoundException e) {
                return "pak_timestamp-";
            }
        }
    }

    public static ResourceExtractor get(Context context) {
        if (sInstance == null) {
            sInstance = new ResourceExtractor(context);
        }
        return sInstance;
    }

    public static void setMandatoryPaksToExtract(String... mandatoryPaks) {
        if ($assertionsDisabled || sInstance == null || sInstance.mExtractTask == null) {
            sMandatoryPaks = mandatoryPaks;
            return;
        }
        throw new AssertionError("Must be called before startExtractingResources is called");
    }

    public static void setResourceIntercepter(ResourceIntercepter intercepter) {
        if ($assertionsDisabled || sInstance == null || sInstance.mExtractTask == null) {
            sIntercepter = intercepter;
            return;
        }
        throw new AssertionError("Must be called before startExtractingResources is called");
    }

    @VisibleForTesting
    public static void setExtractImplicitLocaleForTesting(boolean extract) {
        if ($assertionsDisabled || sInstance == null || sInstance.mExtractTask == null) {
            sExtractImplicitLocalePak = extract;
            return;
        }
        throw new AssertionError("Must be called before startExtractingResources is called");
    }

    @VisibleForTesting
    public void setExtractAllPaksAndV8SnapshotForTesting() {
        List<String> pakAndSnapshotFileAssets = new ArrayList<>();
        try {
            for (String file : this.mContext.getResources().getAssets().list("")) {
                if (file.endsWith(".pak")) {
                    pakAndSnapshotFileAssets.add(file);
                }
            }
        } catch (IOException e) {
            Log.w(LOGTAG, "Exception while accessing assets: " + e.getMessage(), e);
        }
        pakAndSnapshotFileAssets.add(V8_NATIVES_DATA_FILENAME);
        pakAndSnapshotFileAssets.add(V8_SNAPSHOT_DATA_FILENAME);
        setMandatoryPaksToExtract((String[]) pakAndSnapshotFileAssets.toArray(new String[pakAndSnapshotFileAssets.size()]));
    }

    private ResourceExtractor(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public void waitForCompletion() {
        if (!shouldSkipPakExtraction()) {
            if ($assertionsDisabled || this.mExtractTask != null) {
                try {
                    this.mExtractTask.get();
                    sIntercepter = null;
                    sInstance = null;
                } catch (CancellationException e) {
                    deleteFiles();
                } catch (ExecutionException e2) {
                    deleteFiles();
                } catch (InterruptedException e3) {
                    deleteFiles();
                }
            } else {
                throw new AssertionError();
            }
        }
    }

    public void startExtractingResources() {
        if (this.mExtractTask == null && !shouldSkipPakExtraction()) {
            this.mExtractTask = new ExtractTask();
            this.mExtractTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    /* access modifiers changed from: private */
    public File getAppDataDir() {
        return new File(PathUtils.getDataDirectory(this.mContext));
    }

    /* access modifiers changed from: private */
    public File getOutputDir() {
        return new File(getAppDataDir(), "paks");
    }

    /* access modifiers changed from: private */
    public void deleteFiles() {
        File icudata = new File(getAppDataDir(), ICU_DATA_FILENAME);
        if (icudata.exists() && !icudata.delete()) {
            Log.e(LOGTAG, "Unable to remove the icudata " + icudata.getName());
        }
        File v8_natives = new File(getAppDataDir(), V8_NATIVES_DATA_FILENAME);
        if (v8_natives.exists() && !v8_natives.delete()) {
            Log.e(LOGTAG, "Unable to remove the v8 data " + v8_natives.getName());
        }
        File v8_snapshot = new File(getAppDataDir(), V8_SNAPSHOT_DATA_FILENAME);
        if (v8_snapshot.exists() && !v8_snapshot.delete()) {
            Log.e(LOGTAG, "Unable to remove the v8 data " + v8_snapshot.getName());
        }
        File dir = getOutputDir();
        if (dir.exists()) {
            for (File file : dir.listFiles()) {
                if (!file.delete()) {
                    Log.e(LOGTAG, "Unable to remove existing resource " + file.getName());
                }
            }
        }
    }

    private static boolean shouldSkipPakExtraction() {
        if (!$assertionsDisabled && sMandatoryPaks == null) {
            throw new AssertionError();
        } else if (sMandatoryPaks.length != 1 || !"".equals(sMandatoryPaks[0])) {
            return false;
        } else {
            return true;
        }
    }
}
