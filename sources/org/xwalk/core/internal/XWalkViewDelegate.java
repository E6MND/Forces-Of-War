package org.xwalk.core.internal;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import org.chromium.base.ApplicationStatusManager;
import org.chromium.base.CommandLine;
import org.chromium.base.JNINamespace;
import org.chromium.base.PathUtils;
import org.chromium.base.ResourceExtractor;
import org.chromium.base.ThreadUtils;
import org.chromium.base.library_loader.LibraryLoader;
import org.chromium.base.library_loader.ProcessInitException;
import org.chromium.content.browser.BrowserStartupController;
import org.chromium.content.browser.DeviceUtils;
import org.chromium.net.NetworkChangeNotifier;

@JNINamespace("xwalk")
class XWalkViewDelegate {
    private static final String COMMAND_LINE_FILE = "xwalk-command-line";
    private static final String[] MANDATORY_LIBRARIES = {"libxwalkcore.so"};
    private static final String[] MANDATORY_PAKS = {"xwalk.pak", "en-US.pak", "icudtl.dat"};
    private static final String PRIVATE_DATA_DIRECTORY_SUFFIX = "xwalkcore";
    private static final String TAG = "XWalkViewDelegate";
    private static final String XWALK_RESOURCES_LIST_RES_NAME = "xwalk_resources_list";
    private static boolean sInitialized = false;
    private static boolean sLibraryLoaded = false;
    private static boolean sRunningOnIA;

    private static native boolean nativeIsLibraryBuiltForIA();

    XWalkViewDelegate() {
    }

    static {
        boolean z = false;
        sRunningOnIA = true;
        if (Build.CPU_ABI.equalsIgnoreCase("x86") || Build.CPU_ABI.equalsIgnoreCase("x86_64")) {
            z = true;
        }
        sRunningOnIA = z;
        if (!sRunningOnIA) {
            try {
                InputStreamReader ir = new InputStreamReader(Runtime.getRuntime().exec("getprop ro.product.cpu.abi").getInputStream());
                BufferedReader input = new BufferedReader(ir);
                sRunningOnIA = input.readLine().contains("x86");
                input.close();
                ir.close();
            } catch (IOException e) {
                Log.w(TAG, Log.getStackTraceString(e));
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x005a A[SYNTHETIC, Splitter:B:27:0x005a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String[] readCommandLine(android.content.Context r11) {
        /*
            r5 = 0
            android.content.res.AssetManager r8 = r11.getAssets()     // Catch:{ IOException -> 0x006a, all -> 0x0057 }
            java.lang.String r9 = "xwalk-command-line"
            r10 = 3
            java.io.InputStream r3 = r8.open(r9, r10)     // Catch:{ IOException -> 0x006a, all -> 0x0057 }
            r7 = 1024(0x400, float:1.435E-42)
            char[] r0 = new char[r7]     // Catch:{ IOException -> 0x006a, all -> 0x0057 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x006a, all -> 0x0057 }
            r1.<init>()     // Catch:{ IOException -> 0x006a, all -> 0x0057 }
            java.io.InputStreamReader r6 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x006a, all -> 0x0057 }
            java.lang.String r8 = "UTF-8"
            r6.<init>(r3, r8)     // Catch:{ IOException -> 0x006a, all -> 0x0057 }
        L_0x001c:
            r8 = 0
            int r4 = r6.read(r0, r8, r7)     // Catch:{ IOException -> 0x0029, all -> 0x0067 }
            r8 = -1
            if (r4 == r8) goto L_0x0032
            r8 = 0
            r1.append(r0, r8, r4)     // Catch:{ IOException -> 0x0029, all -> 0x0067 }
            goto L_0x001c
        L_0x0029:
            r2 = move-exception
            r5 = r6
        L_0x002b:
            r8 = 0
            if (r5 == 0) goto L_0x0031
            r5.close()     // Catch:{ IOException -> 0x004e }
        L_0x0031:
            return r8
        L_0x0032:
            java.lang.String r8 = r1.toString()     // Catch:{ IOException -> 0x0029, all -> 0x0067 }
            char[] r8 = r8.toCharArray()     // Catch:{ IOException -> 0x0029, all -> 0x0067 }
            java.lang.String[] r8 = org.chromium.base.CommandLine.tokenizeQuotedAruments(r8)     // Catch:{ IOException -> 0x0029, all -> 0x0067 }
            if (r6 == 0) goto L_0x0043
            r6.close()     // Catch:{ IOException -> 0x0045 }
        L_0x0043:
            r5 = r6
            goto L_0x0031
        L_0x0045:
            r2 = move-exception
            java.lang.String r9 = "XWalkViewDelegate"
            java.lang.String r10 = "Unable to close file reader."
            android.util.Log.e(r9, r10, r2)
            goto L_0x0043
        L_0x004e:
            r2 = move-exception
            java.lang.String r9 = "XWalkViewDelegate"
            java.lang.String r10 = "Unable to close file reader."
            android.util.Log.e(r9, r10, r2)
            goto L_0x0031
        L_0x0057:
            r8 = move-exception
        L_0x0058:
            if (r5 == 0) goto L_0x005d
            r5.close()     // Catch:{ IOException -> 0x005e }
        L_0x005d:
            throw r8
        L_0x005e:
            r2 = move-exception
            java.lang.String r9 = "XWalkViewDelegate"
            java.lang.String r10 = "Unable to close file reader."
            android.util.Log.e(r9, r10, r2)
            goto L_0x005d
        L_0x0067:
            r8 = move-exception
            r5 = r6
            goto L_0x0058
        L_0x006a:
            r2 = move-exception
            goto L_0x002b
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xwalk.core.internal.XWalkViewDelegate.readCommandLine(android.content.Context):java.lang.String[]");
    }

    public static void loadXWalkLibrary(Context context) throws UnsatisfiedLinkError {
        if (!sLibraryLoaded) {
            if (Build.VERSION.SDK_INT < 17 && context != null && !context.getApplicationContext().getPackageName().equals(context.getPackageName())) {
                String[] arr$ = MANDATORY_LIBRARIES;
                int len$ = arr$.length;
                for (int i$ = 0; i$ < len$; i$++) {
                    System.load("/data/data/" + context.getPackageName() + "/lib/" + arr$[i$]);
                }
            }
            loadLibrary(context);
            if (!sRunningOnIA || nativeIsLibraryBuiltForIA()) {
                sLibraryLoaded = true;
                return;
            }
            throw new UnsatisfiedLinkError();
        }
    }

    public static void init(XWalkViewInternal xwalkView) throws UnsatisfiedLinkError {
        if (!sInitialized) {
            loadXWalkLibrary(xwalkView.getContext());
            ApplicationStatusManager.init(xwalkView.getActivity().getApplication());
            NetworkChangeNotifier.init(xwalkView.getActivity());
            NetworkChangeNotifier.setAutoDetectConnectivityState(true);
            ApplicationStatusManager.informActivityStarted(xwalkView.getActivity());
            final Context context = xwalkView.getViewContext();
            if (!CommandLine.isInitialized()) {
                CommandLine.init(readCommandLine(context.getApplicationContext()));
            }
            ResourceExtractor.setMandatoryPaksToExtract(MANDATORY_PAKS);
            final int resourcesListResId = context.getResources().getIdentifier(XWALK_RESOURCES_LIST_RES_NAME, "array", context.getPackageName());
            final AssetManager assets = context.getAssets();
            if (!context.getPackageName().equals(context.getApplicationContext().getPackageName()) || resourcesListResId != 0) {
                ResourceExtractor.setResourceIntercepter(new ResourceExtractor.ResourceIntercepter() {
                    public Set<String> getInterceptableResourceList() {
                        Set<String> resourcesList = new HashSet<>();
                        if (!context.getPackageName().equals(context.getApplicationContext().getPackageName())) {
                            try {
                                for (String resource : assets.list("")) {
                                    resourcesList.add(resource);
                                }
                            } catch (IOException e) {
                            }
                        }
                        if (resourcesListResId != 0) {
                            try {
                                for (String resource2 : context.getResources().getStringArray(resourcesListResId)) {
                                    resourcesList.add(resource2);
                                }
                            } catch (Resources.NotFoundException e2) {
                                Log.w(XWalkViewDelegate.TAG, "R.array.xwalk_resources_list can't be found.");
                            }
                        }
                        return resourcesList;
                    }

                    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0040, code lost:
                        r3 = r8.split("\\.")[0];
                     */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public java.io.InputStream interceptLoadingForResource(java.lang.String r8) {
                        /*
                            r7 = this;
                            android.content.Context r4 = r1
                            java.lang.String r4 = r4.getPackageName()
                            android.content.Context r5 = r1
                            android.content.Context r5 = r5.getApplicationContext()
                            java.lang.String r5 = r5.getPackageName()
                            boolean r4 = r4.equals(r5)
                            if (r4 != 0) goto L_0x003c
                            android.content.Context r4 = r1     // Catch:{ IOException -> 0x0023 }
                            android.content.res.AssetManager r4 = r4.getAssets()     // Catch:{ IOException -> 0x0023 }
                            java.io.InputStream r1 = r4.open(r8)     // Catch:{ IOException -> 0x0023 }
                            if (r1 == 0) goto L_0x003c
                        L_0x0022:
                            return r1
                        L_0x0023:
                            r0 = move-exception
                            java.lang.String r4 = "XWalkViewDelegate"
                            java.lang.StringBuilder r5 = new java.lang.StringBuilder
                            r5.<init>()
                            java.lang.StringBuilder r5 = r5.append(r8)
                            java.lang.String r6 = " can't be found in assets."
                            java.lang.StringBuilder r5 = r5.append(r6)
                            java.lang.String r5 = r5.toString()
                            android.util.Log.w(r4, r5)
                        L_0x003c:
                            int r4 = r2
                            if (r4 == 0) goto L_0x0087
                            java.lang.String r4 = "\\."
                            java.lang.String[] r4 = r8.split(r4)
                            r5 = 0
                            r3 = r4[r5]
                            android.content.Context r4 = r1
                            android.content.res.Resources r4 = r4.getResources()
                            java.lang.String r5 = "raw"
                            android.content.Context r6 = r1
                            java.lang.String r6 = r6.getPackageName()
                            int r2 = r4.getIdentifier(r3, r5, r6)
                            if (r2 == 0) goto L_0x0087
                            android.content.Context r4 = r1     // Catch:{ NotFoundException -> 0x0068 }
                            android.content.res.Resources r4 = r4.getResources()     // Catch:{ NotFoundException -> 0x0068 }
                            java.io.InputStream r1 = r4.openRawResource(r2)     // Catch:{ NotFoundException -> 0x0068 }
                            goto L_0x0022
                        L_0x0068:
                            r0 = move-exception
                            java.lang.String r4 = "XWalkViewDelegate"
                            java.lang.StringBuilder r5 = new java.lang.StringBuilder
                            r5.<init>()
                            java.lang.String r6 = "R.raw."
                            java.lang.StringBuilder r5 = r5.append(r6)
                            java.lang.StringBuilder r5 = r5.append(r3)
                            java.lang.String r6 = " can't be found."
                            java.lang.StringBuilder r5 = r5.append(r6)
                            java.lang.String r5 = r5.toString()
                            android.util.Log.w(r4, r5)
                        L_0x0087:
                            r1 = 0
                            goto L_0x0022
                        */
                        throw new UnsupportedOperationException("Method not decompiled: org.xwalk.core.internal.XWalkViewDelegate.AnonymousClass1.interceptLoadingForResource(java.lang.String):java.io.InputStream");
                    }
                });
            }
            ResourceExtractor.setExtractImplicitLocaleForTesting(false);
            ResourceExtractor.get(context);
            startBrowserProcess(context);
            sInitialized = true;
        }
    }

    private static void loadLibrary(Context context) {
        PathUtils.setPrivateDataDirectorySuffix(PRIVATE_DATA_DIRECTORY_SUFFIX);
        try {
            LibraryLoader.loadNow(context, true);
        } catch (ProcessInitException e) {
            throw new RuntimeException("Cannot load Crosswalk Core", e);
        }
    }

    private static void startBrowserProcess(final Context context) {
        ThreadUtils.runOnUiThreadBlocking((Runnable) new Runnable() {
            public void run() {
                try {
                    LibraryLoader.ensureInitialized();
                    DeviceUtils.addDeviceSpecificUserAgentSwitch(context);
                    CommandLine.getInstance().appendSwitchWithValue("profile-name", XWalkPreferencesInternal.getStringValue("profile-name"));
                    try {
                        BrowserStartupController.get(context).startBrowserProcessesSync(true);
                    } catch (ProcessInitException e) {
                        throw new RuntimeException("Cannot initialize Crosswalk Core", e);
                    }
                } catch (ProcessInitException e2) {
                    throw new RuntimeException("Cannot initialize Crosswalk Core", e2);
                }
            }
        });
    }

    public static boolean isRunningOnIA() {
        return sRunningOnIA;
    }
}
