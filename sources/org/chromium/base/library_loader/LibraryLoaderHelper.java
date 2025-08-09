package org.chromium.base.library_loader;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class LibraryLoaderHelper {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static final int BUFFER_SIZE = 16384;
    public static final String LOAD_FROM_APK_FALLBACK_DIR = "fallback";
    public static final String PACKAGE_MANAGER_WORKAROUND_DIR = "lib";
    private static final String TAG = "LibraryLoaderHelper";
    private static boolean sLibrariesWereUnpacked = false;

    static {
        boolean z;
        if (!LibraryLoaderHelper.class.desiredAssertionStatus()) {
            z = true;
        } else {
            z = false;
        }
        $assertionsDisabled = z;
    }

    public static boolean loadNativeLibrariesUsingWorkaroundForTesting(Context context) {
        for (String library : NativeLibraries.LIBRARIES) {
            if (!tryLoadLibraryUsingWorkaround(context, library)) {
                return false;
            }
        }
        return true;
    }

    static boolean tryLoadLibraryUsingWorkaround(Context context, String library) {
        if ($assertionsDisabled || context != null) {
            File libFile = new File(getLibDir(context, PACKAGE_MANAGER_WORKAROUND_DIR), System.mapLibraryName(library));
            if (!libFile.exists() && !unpackWorkaroundLibrariesOnce(context)) {
                return false;
            }
            try {
                System.load(libFile.getAbsolutePath());
                return true;
            } catch (UnsatisfiedLinkError e) {
                return false;
            }
        } else {
            throw new AssertionError();
        }
    }

    public static File getLibDir(Context context, String dirName) {
        return context.getDir(dirName, 0);
    }

    private static String getJniNameInApk(String libName) {
        return "lib/" + Build.CPU_ABI + "/" + libName;
    }

    private static boolean unpackWorkaroundLibrariesOnce(Context context) {
        if (sLibrariesWereUnpacked) {
            return false;
        }
        sLibrariesWereUnpacked = true;
        deleteLibrariesSynchronously(context, PACKAGE_MANAGER_WORKAROUND_DIR);
        File libDir = getLibDir(context, PACKAGE_MANAGER_WORKAROUND_DIR);
        try {
            Map<String, File> dstFiles = new HashMap<>();
            for (String library : NativeLibraries.LIBRARIES) {
                String libName = System.mapLibraryName(library);
                dstFiles.put(getJniNameInApk(libName), new File(libDir, libName));
            }
            unpackLibraries(context, dstFiles);
            return true;
        } catch (UnpackingException e) {
            Log.e(TAG, "Failed to unpack native libraries", e);
            deleteLibrariesSynchronously(context, PACKAGE_MANAGER_WORKAROUND_DIR);
            return false;
        }
    }

    public static void deleteLibrariesSynchronously(Context context, String dirName) {
        deleteObsoleteLibraries(getLibDir(context, dirName), Collections.emptyList());
    }

    static void deleteLibrariesAsynchronously(final Context context, final String dirName) {
        new Thread() {
            public void run() {
                LibraryLoaderHelper.deleteLibrariesSynchronously(context, dirName);
            }
        }.start();
    }

    public static String buildFallbackLibrary(Context context, String library) {
        try {
            String libName = System.mapLibraryName(library);
            File fallbackLibDir = getLibDir(context, LOAD_FROM_APK_FALLBACK_DIR);
            File fallbackLibFile = new File(fallbackLibDir, libName);
            Map<String, File> dstFiles = Collections.singletonMap(Linker.getLibraryFilePathInZipFile(libName), fallbackLibFile);
            deleteObsoleteLibraries(fallbackLibDir, dstFiles.values());
            unpackLibraries(context, dstFiles);
            return fallbackLibFile.getAbsolutePath();
        } catch (Exception e) {
            String errorMessage = "Unable to load fallback for library " + library + " (" + (e.getMessage() == null ? e.toString() : e.getMessage()) + ")";
            Log.e(TAG, errorMessage, e);
            throw new UnsatisfiedLinkError(errorMessage);
        }
    }

    private static void deleteObsoleteLibraries(File libDir, Collection<File> keptFiles) {
        try {
            Set<String> keptFileNames = new HashSet<>();
            for (File k : keptFiles) {
                keptFileNames.add(k.getName());
            }
            Log.i(TAG, "Deleting obsolete libraries in " + libDir.getPath());
            File[] files = libDir.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (!keptFileNames.contains(f.getName())) {
                        delete(f);
                    }
                }
            } else {
                Log.e(TAG, "Failed to list files in " + libDir.getPath());
            }
            if (keptFileNames.isEmpty()) {
                delete(libDir);
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to remove obsolete libraries from " + libDir.getPath());
        }
    }

    private static void unpackLibraries(Context context, Map<String, File> dstFiles) throws UnpackingException {
        String zipFilePath = context.getApplicationInfo().sourceDir;
        Log.i(TAG, "Opening zip file " + zipFilePath);
        File zipFile = new File(zipFilePath);
        ZipFile zipArchive = openZipFile(zipFile);
        try {
            for (Map.Entry<String, File> d : dstFiles.entrySet()) {
                String pathInZipFile = d.getKey();
                File dstFile = d.getValue();
                Log.i(TAG, "Unpacking " + pathInZipFile + " to " + dstFile.getAbsolutePath());
                ZipEntry packedLib = zipArchive.getEntry(pathInZipFile);
                if (needToUnpackLibrary(zipFile, packedLib, dstFile)) {
                    unpackLibraryFromZipFile(zipArchive, packedLib, dstFile);
                    setLibraryFilePermissions(dstFile);
                }
            }
        } finally {
            closeZipFile(zipArchive);
        }
    }

    private static ZipFile openZipFile(File zipFile) throws UnpackingException {
        try {
            return new ZipFile(zipFile);
        } catch (ZipException e) {
            throw new UnpackingException("Failed to open zip file " + zipFile.getPath());
        } catch (IOException e2) {
            throw new UnpackingException("Failed to open zip file " + zipFile.getPath());
        }
    }

    private static boolean needToUnpackLibrary(File zipFile, ZipEntry packedLib, File dstFile) {
        if (!dstFile.exists()) {
            Log.i(TAG, "File " + dstFile.getPath() + " does not exist yet");
            return true;
        }
        long zipTime = zipFile.lastModified();
        long fallbackLibTime = dstFile.lastModified();
        if (zipTime > fallbackLibTime) {
            Log.i(TAG, "Not using existing fallback file because the APK file " + zipFile.getPath() + " (timestamp=" + zipTime + ") is newer than " + "the fallback library " + dstFile.getPath() + "(timestamp=" + fallbackLibTime + ")");
            return true;
        }
        long packedLibSize = packedLib.getSize();
        long fallbackLibSize = dstFile.length();
        if (fallbackLibSize != packedLibSize) {
            Log.i(TAG, "Not using existing fallback file because the library in the APK " + zipFile.getPath() + " (" + packedLibSize + "B) has a different size than " + "the fallback library " + dstFile.getPath() + "(" + fallbackLibSize + "B)");
            return true;
        }
        Log.i(TAG, "Reusing existing file " + dstFile.getPath());
        return false;
    }

    private static void unpackLibraryFromZipFile(ZipFile zipArchive, ZipEntry packedLib, File dstFile) throws UnpackingException {
        try {
            InputStream in = zipArchive.getInputStream(packedLib);
            try {
                if (dstFile.exists()) {
                    Log.i(TAG, "Deleting existing unpacked library file " + dstFile.getPath());
                    if (!dstFile.delete()) {
                        throw new UnpackingException("Failed to delete existing unpacked library file " + dstFile.getPath());
                    }
                }
                try {
                    dstFile.getParentFile().mkdirs();
                } catch (Exception e) {
                    Log.e(TAG, "Failed to make library folder", e);
                }
                if (!dstFile.createNewFile()) {
                    throw new UnpackingException("existing unpacked library file was not deleted");
                }
                OutputStream out = new BufferedOutputStream(new FileOutputStream(dstFile));
                try {
                    Log.i(TAG, "Copying " + packedLib.getName() + " from " + zipArchive.getName() + " to " + dstFile.getPath());
                    byte[] buffer = new byte[16384];
                    while (true) {
                        int len = in.read(buffer);
                        if (len != -1) {
                            out.write(buffer, 0, len);
                        } else {
                            close(out, "output stream");
                            close(in, "input stream");
                            return;
                        }
                    }
                } catch (IOException e2) {
                    throw new UnpackingException("failed to copy the library from the zip file", e2);
                } catch (Throwable th) {
                    close(out, "output stream");
                    throw th;
                }
            } catch (FileNotFoundException e3) {
                throw new UnpackingException("failed to open output stream for unpacked library file", e3);
            } catch (IOException e4) {
                throw new UnpackingException("failed to create unpacked library file", e4);
            } catch (Throwable th2) {
                close(in, "input stream");
                throw th2;
            }
        } catch (IOException e5) {
            throw new UnpackingException("IO exception when locating library in the zip file", e5);
        }
    }

    private static void setLibraryFilePermissions(File libFile) {
        Log.i(TAG, "Setting file permissions for " + libFile.getPath());
        if (!libFile.setReadable(true, false)) {
            Log.e(TAG, "failed to chmod a+r the temporary file");
        }
        if (!libFile.setExecutable(true, false)) {
            Log.e(TAG, "failed to chmod a+x the temporary file");
        }
        if (!libFile.setWritable(true)) {
            Log.e(TAG, "failed to chmod +w the temporary file");
        }
    }

    private static void close(Closeable closeable, String name) {
        try {
            closeable.close();
        } catch (IOException e) {
            Log.w(TAG, "IO exception when closing " + name, e);
        }
    }

    private static void closeZipFile(ZipFile file) {
        try {
            file.close();
        } catch (IOException e) {
            Log.w(TAG, "IO exception when closing zip file", e);
        }
    }

    private static void delete(File file) {
        if (file.delete()) {
            Log.i(TAG, "Deleted " + file.getPath());
        } else {
            Log.w(TAG, "Failed to delete " + file.getPath());
        }
    }
}
