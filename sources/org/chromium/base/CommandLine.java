package org.chromium.base;

import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public abstract class CommandLine {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static final String SWITCH_PREFIX = "--";
    private static final String SWITCH_TERMINATOR = "--";
    private static final String SWITCH_VALUE_SEPARATOR = "=";
    private static final String TAG = "CommandLine";
    private static final AtomicReference<CommandLine> sCommandLine = new AtomicReference<>();

    /* access modifiers changed from: private */
    public static native void nativeAppendSwitch(String str);

    /* access modifiers changed from: private */
    public static native void nativeAppendSwitchWithValue(String str, String str2);

    /* access modifiers changed from: private */
    public static native void nativeAppendSwitchesAndArguments(String[] strArr);

    /* access modifiers changed from: private */
    public static native String nativeGetSwitchValue(String str);

    /* access modifiers changed from: private */
    public static native boolean nativeHasSwitch(String str);

    private static native void nativeReset();

    public abstract void appendSwitch(String str);

    public abstract void appendSwitchWithValue(String str, String str2);

    public abstract void appendSwitchesAndArguments(String[] strArr);

    public abstract String getSwitchValue(String str);

    public abstract boolean hasSwitch(String str);

    static {
        boolean z;
        if (!CommandLine.class.desiredAssertionStatus()) {
            z = true;
        } else {
            z = false;
        }
        $assertionsDisabled = z;
    }

    public String getSwitchValue(String switchString, String defaultValue) {
        String value = getSwitchValue(switchString);
        return TextUtils.isEmpty(value) ? defaultValue : value;
    }

    public boolean isNativeImplementation() {
        return false;
    }

    public static boolean isInitialized() {
        return sCommandLine.get() != null;
    }

    public static CommandLine getInstance() {
        CommandLine commandLine = sCommandLine.get();
        if ($assertionsDisabled || commandLine != null) {
            return commandLine;
        }
        throw new AssertionError();
    }

    public static void init(String[] args) {
        setInstance(new JavaCommandLine(args));
    }

    public static void initFromFile(String file) {
        char[] buffer = readUtf8FileFully(file, 8192);
        init(buffer == null ? null : tokenizeQuotedAruments(buffer));
    }

    @VisibleForTesting
    public static void reset() {
        setInstance((CommandLine) null);
    }

    public static String[] tokenizeQuotedAruments(char[] buffer) {
        ArrayList<String> args = new ArrayList<>();
        StringBuilder arg = null;
        char currentQuote = 0;
        for (char c : buffer) {
            if ((currentQuote == 0 && (c == '\'' || c == '\"')) || c == currentQuote) {
                if (arg == null || arg.length() <= 0 || arg.charAt(arg.length() - 1) != '\\') {
                    currentQuote = currentQuote == 0 ? c : 0;
                } else {
                    arg.setCharAt(arg.length() - 1, c);
                }
            } else if (currentQuote != 0 || !Character.isWhitespace(c)) {
                if (arg == null) {
                    arg = new StringBuilder();
                }
                arg.append(c);
            } else if (arg != null) {
                args.add(arg.toString());
                arg = null;
            }
        }
        if (arg != null) {
            if (currentQuote != 0) {
                Log.w(TAG, "Unterminated quoted string: " + arg);
            }
            args.add(arg.toString());
        }
        return (String[]) args.toArray(new String[args.size()]);
    }

    public static void enableNativeProxy() {
        sCommandLine.set(new NativeCommandLine());
    }

    public static String[] getJavaSwitchesOrNull() {
        CommandLine commandLine = sCommandLine.get();
        if (commandLine == null) {
            return null;
        }
        if ($assertionsDisabled || !commandLine.isNativeImplementation()) {
            return ((JavaCommandLine) commandLine).getCommandLineArguments();
        }
        throw new AssertionError();
    }

    private static void setInstance(CommandLine commandLine) {
        CommandLine oldCommandLine = sCommandLine.getAndSet(commandLine);
        if (oldCommandLine != null && oldCommandLine.isNativeImplementation()) {
            nativeReset();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x006d A[SYNTHETIC, Splitter:B:19:0x006d] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0096 A[SYNTHETIC, Splitter:B:36:0x0096] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00a8 A[SYNTHETIC, Splitter:B:43:0x00a8] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static char[] readUtf8FileFully(java.lang.String r12, int r13) {
        /*
            r8 = 0
            r6 = 0
            java.io.File r3 = new java.io.File
            r3.<init>(r12)
            long r4 = r3.length()
            r10 = 0
            int r9 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r9 != 0) goto L_0x0013
            r0 = r8
        L_0x0012:
            return r0
        L_0x0013:
            long r10 = (long) r13
            int r9 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r9 <= 0) goto L_0x0046
            java.lang.String r9 = "CommandLine"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "File "
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.StringBuilder r10 = r10.append(r12)
            java.lang.String r11 = " length "
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.StringBuilder r10 = r10.append(r4)
            java.lang.String r11 = " exceeds limit "
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.StringBuilder r10 = r10.append(r13)
            java.lang.String r10 = r10.toString()
            android.util.Log.w(r9, r10)
            r0 = r8
            goto L_0x0012
        L_0x0046:
            int r9 = (int) r4
            char[] r0 = new char[r9]     // Catch:{ FileNotFoundException -> 0x00bb, IOException -> 0x0093, all -> 0x00a5 }
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch:{ FileNotFoundException -> 0x00bb, IOException -> 0x0093, all -> 0x00a5 }
            java.io.FileInputStream r9 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x00bb, IOException -> 0x0093, all -> 0x00a5 }
            r9.<init>(r3)     // Catch:{ FileNotFoundException -> 0x00bb, IOException -> 0x0093, all -> 0x00a5 }
            java.lang.String r10 = "UTF-8"
            r7.<init>(r9, r10)     // Catch:{ FileNotFoundException -> 0x00bb, IOException -> 0x0093, all -> 0x00a5 }
            int r1 = r7.read(r0)     // Catch:{ FileNotFoundException -> 0x0069, IOException -> 0x00b8, all -> 0x00b5 }
            boolean r9 = $assertionsDisabled     // Catch:{ FileNotFoundException -> 0x0069, IOException -> 0x00b8, all -> 0x00b5 }
            if (r9 != 0) goto L_0x0072
            boolean r9 = r7.ready()     // Catch:{ FileNotFoundException -> 0x0069, IOException -> 0x00b8, all -> 0x00b5 }
            if (r9 == 0) goto L_0x0072
            java.lang.AssertionError r9 = new java.lang.AssertionError     // Catch:{ FileNotFoundException -> 0x0069, IOException -> 0x00b8, all -> 0x00b5 }
            r9.<init>()     // Catch:{ FileNotFoundException -> 0x0069, IOException -> 0x00b8, all -> 0x00b5 }
            throw r9     // Catch:{ FileNotFoundException -> 0x0069, IOException -> 0x00b8, all -> 0x00b5 }
        L_0x0069:
            r2 = move-exception
            r6 = r7
        L_0x006b:
            if (r6 == 0) goto L_0x0070
            r6.close()     // Catch:{ IOException -> 0x008a }
        L_0x0070:
            r0 = r8
            goto L_0x0012
        L_0x0072:
            int r9 = r0.length     // Catch:{ FileNotFoundException -> 0x0069, IOException -> 0x00b8, all -> 0x00b5 }
            if (r1 >= r9) goto L_0x007a
            r9 = 0
            char[] r0 = java.util.Arrays.copyOfRange(r0, r9, r1)     // Catch:{ FileNotFoundException -> 0x0069, IOException -> 0x00b8, all -> 0x00b5 }
        L_0x007a:
            if (r7 == 0) goto L_0x007f
            r7.close()     // Catch:{ IOException -> 0x0081 }
        L_0x007f:
            r6 = r7
            goto L_0x0012
        L_0x0081:
            r2 = move-exception
            java.lang.String r8 = "CommandLine"
            java.lang.String r9 = "Unable to close file reader."
            android.util.Log.e(r8, r9, r2)
            goto L_0x007f
        L_0x008a:
            r2 = move-exception
            java.lang.String r9 = "CommandLine"
            java.lang.String r10 = "Unable to close file reader."
            android.util.Log.e(r9, r10, r2)
            goto L_0x0070
        L_0x0093:
            r2 = move-exception
        L_0x0094:
            if (r6 == 0) goto L_0x0099
            r6.close()     // Catch:{ IOException -> 0x009c }
        L_0x0099:
            r0 = r8
            goto L_0x0012
        L_0x009c:
            r2 = move-exception
            java.lang.String r9 = "CommandLine"
            java.lang.String r10 = "Unable to close file reader."
            android.util.Log.e(r9, r10, r2)
            goto L_0x0099
        L_0x00a5:
            r8 = move-exception
        L_0x00a6:
            if (r6 == 0) goto L_0x00ab
            r6.close()     // Catch:{ IOException -> 0x00ac }
        L_0x00ab:
            throw r8
        L_0x00ac:
            r2 = move-exception
            java.lang.String r9 = "CommandLine"
            java.lang.String r10 = "Unable to close file reader."
            android.util.Log.e(r9, r10, r2)
            goto L_0x00ab
        L_0x00b5:
            r8 = move-exception
            r6 = r7
            goto L_0x00a6
        L_0x00b8:
            r2 = move-exception
            r6 = r7
            goto L_0x0094
        L_0x00bb:
            r2 = move-exception
            goto L_0x006b
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.base.CommandLine.readUtf8FileFully(java.lang.String, int):char[]");
    }

    private CommandLine() {
    }

    private static class JavaCommandLine extends CommandLine {
        static final /* synthetic */ boolean $assertionsDisabled = (!CommandLine.class.desiredAssertionStatus());
        private ArrayList<String> mArgs = new ArrayList<>();
        private int mArgsBegin = 1;
        private HashMap<String, String> mSwitches = new HashMap<>();

        JavaCommandLine(String[] args) {
            super();
            if (args == null || args.length == 0 || args[0] == null) {
                this.mArgs.add("");
            } else {
                this.mArgs.add(args[0]);
                appendSwitchesInternal(args, 1);
            }
            if (!$assertionsDisabled && this.mArgs.size() <= 0) {
                throw new AssertionError();
            }
        }

        /* access modifiers changed from: private */
        public String[] getCommandLineArguments() {
            return (String[]) this.mArgs.toArray(new String[this.mArgs.size()]);
        }

        public boolean hasSwitch(String switchString) {
            return this.mSwitches.containsKey(switchString);
        }

        public String getSwitchValue(String switchString) {
            String value = this.mSwitches.get(switchString);
            if (value == null || value.isEmpty()) {
                return null;
            }
            return value;
        }

        public void appendSwitch(String switchString) {
            appendSwitchWithValue(switchString, (String) null);
        }

        public void appendSwitchWithValue(String switchString, String value) {
            String str;
            HashMap<String, String> hashMap = this.mSwitches;
            if (value == null) {
                str = "";
            } else {
                str = value;
            }
            hashMap.put(switchString, str);
            String combinedSwitchString = "--" + switchString;
            if (value != null && !value.isEmpty()) {
                combinedSwitchString = combinedSwitchString + CommandLine.SWITCH_VALUE_SEPARATOR + value;
            }
            ArrayList<String> arrayList = this.mArgs;
            int i = this.mArgsBegin;
            this.mArgsBegin = i + 1;
            arrayList.add(i, combinedSwitchString);
        }

        public void appendSwitchesAndArguments(String[] array) {
            appendSwitchesInternal(array, 0);
        }

        private void appendSwitchesInternal(String[] array, int skipCount) {
            boolean parseSwitches = true;
            for (String arg : array) {
                if (skipCount > 0) {
                    skipCount--;
                } else {
                    if (arg.equals("--")) {
                        parseSwitches = false;
                    }
                    if (!parseSwitches || !arg.startsWith("--")) {
                        this.mArgs.add(arg);
                    } else {
                        String[] parts = arg.split(CommandLine.SWITCH_VALUE_SEPARATOR, 2);
                        appendSwitchWithValue(parts[0].substring("--".length()), parts.length > 1 ? parts[1] : null);
                    }
                }
            }
        }
    }

    private static class NativeCommandLine extends CommandLine {
        private NativeCommandLine() {
            super();
        }

        public boolean hasSwitch(String switchString) {
            return CommandLine.nativeHasSwitch(switchString);
        }

        public String getSwitchValue(String switchString) {
            return CommandLine.nativeGetSwitchValue(switchString);
        }

        public void appendSwitch(String switchString) {
            CommandLine.nativeAppendSwitch(switchString);
        }

        public void appendSwitchWithValue(String switchString, String value) {
            CommandLine.nativeAppendSwitchWithValue(switchString, value);
        }

        public void appendSwitchesAndArguments(String[] array) {
            CommandLine.nativeAppendSwitchesAndArguments(array);
        }

        public boolean isNativeImplementation() {
            return true;
        }
    }
}
