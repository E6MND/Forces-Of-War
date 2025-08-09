package com.android.volley.toolbox;

import android.os.SystemClock;
import com.android.volley.Cache;
import com.android.volley.VolleyLog;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class DiskBasedCache implements Cache {
    private static final int CACHE_MAGIC = 538183203;
    private static final int DEFAULT_DISK_USAGE_BYTES = 5242880;
    private static final float HYSTERESIS_FACTOR = 0.9f;
    private final Map<String, CacheHeader> mEntries;
    private final int mMaxCacheSizeInBytes;
    private final File mRootDirectory;
    private long mTotalSize;

    public DiskBasedCache(File rootDirectory, int maxCacheSizeInBytes) {
        this.mEntries = new LinkedHashMap(16, 0.75f, true);
        this.mTotalSize = 0;
        this.mRootDirectory = rootDirectory;
        this.mMaxCacheSizeInBytes = maxCacheSizeInBytes;
    }

    public DiskBasedCache(File rootDirectory) {
        this(rootDirectory, DEFAULT_DISK_USAGE_BYTES);
    }

    public synchronized void clear() {
        synchronized (this) {
            File[] files = this.mRootDirectory.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
            this.mEntries.clear();
            this.mTotalSize = 0;
            VolleyLog.d("Cache cleared.", new Object[0]);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0062 A[SYNTHETIC, Splitter:B:29:0x0062] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.android.volley.Cache.Entry get(java.lang.String r13) {
        /*
            r12 = this;
            r7 = 0
            monitor-enter(r12)
            java.util.Map<java.lang.String, com.android.volley.toolbox.DiskBasedCache$CacheHeader> r8 = r12.mEntries     // Catch:{ all -> 0x0066 }
            java.lang.Object r4 = r8.get(r13)     // Catch:{ all -> 0x0066 }
            com.android.volley.toolbox.DiskBasedCache$CacheHeader r4 = (com.android.volley.toolbox.DiskBasedCache.CacheHeader) r4     // Catch:{ all -> 0x0066 }
            if (r4 != 0) goto L_0x000e
        L_0x000c:
            monitor-exit(r12)
            return r7
        L_0x000e:
            java.io.File r5 = r12.getFileForKey(r13)     // Catch:{ all -> 0x0066 }
            r0 = 0
            com.android.volley.toolbox.DiskBasedCache$CountingInputStream r1 = new com.android.volley.toolbox.DiskBasedCache$CountingInputStream     // Catch:{ IOException -> 0x003d }
            java.io.FileInputStream r8 = new java.io.FileInputStream     // Catch:{ IOException -> 0x003d }
            r8.<init>(r5)     // Catch:{ IOException -> 0x003d }
            r9 = 0
            r1.<init>(r8)     // Catch:{ IOException -> 0x003d }
            com.android.volley.toolbox.DiskBasedCache.CacheHeader.readHeader(r1)     // Catch:{ IOException -> 0x006e, all -> 0x006b }
            long r8 = r5.length()     // Catch:{ IOException -> 0x006e, all -> 0x006b }
            int r10 = r1.bytesRead     // Catch:{ IOException -> 0x006e, all -> 0x006b }
            long r10 = (long) r10     // Catch:{ IOException -> 0x006e, all -> 0x006b }
            long r8 = r8 - r10
            int r8 = (int) r8     // Catch:{ IOException -> 0x006e, all -> 0x006b }
            byte[] r2 = streamToBytes(r1, r8)     // Catch:{ IOException -> 0x006e, all -> 0x006b }
            com.android.volley.Cache$Entry r8 = r4.toCacheEntry(r2)     // Catch:{ IOException -> 0x006e, all -> 0x006b }
            if (r1 == 0) goto L_0x0039
            r1.close()     // Catch:{ IOException -> 0x003b }
        L_0x0039:
            r7 = r8
            goto L_0x000c
        L_0x003b:
            r6 = move-exception
            goto L_0x000c
        L_0x003d:
            r3 = move-exception
        L_0x003e:
            java.lang.String r8 = "%s: %s"
            r9 = 2
            java.lang.Object[] r9 = new java.lang.Object[r9]     // Catch:{ all -> 0x005f }
            r10 = 0
            java.lang.String r11 = r5.getAbsolutePath()     // Catch:{ all -> 0x005f }
            r9[r10] = r11     // Catch:{ all -> 0x005f }
            r10 = 1
            java.lang.String r11 = r3.toString()     // Catch:{ all -> 0x005f }
            r9[r10] = r11     // Catch:{ all -> 0x005f }
            com.android.volley.VolleyLog.d(r8, r9)     // Catch:{ all -> 0x005f }
            r12.remove(r13)     // Catch:{ all -> 0x005f }
            if (r0 == 0) goto L_0x000c
            r0.close()     // Catch:{ IOException -> 0x005d }
            goto L_0x000c
        L_0x005d:
            r6 = move-exception
            goto L_0x000c
        L_0x005f:
            r8 = move-exception
        L_0x0060:
            if (r0 == 0) goto L_0x0065
            r0.close()     // Catch:{ IOException -> 0x0069 }
        L_0x0065:
            throw r8     // Catch:{ all -> 0x0066 }
        L_0x0066:
            r7 = move-exception
            monitor-exit(r12)
            throw r7
        L_0x0069:
            r6 = move-exception
            goto L_0x000c
        L_0x006b:
            r8 = move-exception
            r0 = r1
            goto L_0x0060
        L_0x006e:
            r3 = move-exception
            r0 = r1
            goto L_0x003e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.DiskBasedCache.get(java.lang.String):com.android.volley.Cache$Entry");
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0056 A[SYNTHETIC, Splitter:B:30:0x0056] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x005b A[SYNTHETIC, Splitter:B:33:0x005b] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0064 A[SYNTHETIC, Splitter:B:38:0x0064] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x004d A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void initialize() {
        /*
            r10 = this;
            r6 = 0
            monitor-enter(r10)
            java.io.File r7 = r10.mRootDirectory     // Catch:{ all -> 0x0068 }
            boolean r7 = r7.exists()     // Catch:{ all -> 0x0068 }
            if (r7 != 0) goto L_0x0025
            java.io.File r6 = r10.mRootDirectory     // Catch:{ all -> 0x0068 }
            boolean r6 = r6.mkdirs()     // Catch:{ all -> 0x0068 }
            if (r6 != 0) goto L_0x0023
            java.lang.String r6 = "Unable to create cache dir %s"
            r7 = 1
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ all -> 0x0068 }
            r8 = 0
            java.io.File r9 = r10.mRootDirectory     // Catch:{ all -> 0x0068 }
            java.lang.String r9 = r9.getAbsolutePath()     // Catch:{ all -> 0x0068 }
            r7[r8] = r9     // Catch:{ all -> 0x0068 }
            com.android.volley.VolleyLog.e(r6, r7)     // Catch:{ all -> 0x0068 }
        L_0x0023:
            monitor-exit(r10)
            return
        L_0x0025:
            java.io.File r7 = r10.mRootDirectory     // Catch:{ all -> 0x0068 }
            java.io.File[] r3 = r7.listFiles()     // Catch:{ all -> 0x0068 }
            if (r3 == 0) goto L_0x0023
            int r7 = r3.length     // Catch:{ all -> 0x0068 }
        L_0x002e:
            if (r6 >= r7) goto L_0x0023
            r2 = r3[r6]     // Catch:{ all -> 0x0068 }
            r4 = 0
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0053 }
            r5.<init>(r2)     // Catch:{ IOException -> 0x0053 }
            com.android.volley.toolbox.DiskBasedCache$CacheHeader r1 = com.android.volley.toolbox.DiskBasedCache.CacheHeader.readHeader(r5)     // Catch:{ IOException -> 0x0070, all -> 0x006d }
            long r8 = r2.length()     // Catch:{ IOException -> 0x0070, all -> 0x006d }
            r1.size = r8     // Catch:{ IOException -> 0x0070, all -> 0x006d }
            java.lang.String r8 = r1.key     // Catch:{ IOException -> 0x0070, all -> 0x006d }
            r10.putEntry(r8, r1)     // Catch:{ IOException -> 0x0070, all -> 0x006d }
            if (r5 == 0) goto L_0x004c
            r5.close()     // Catch:{ IOException -> 0x0050 }
        L_0x004c:
            r4 = r5
        L_0x004d:
            int r6 = r6 + 1
            goto L_0x002e
        L_0x0050:
            r8 = move-exception
            r4 = r5
            goto L_0x004d
        L_0x0053:
            r0 = move-exception
        L_0x0054:
            if (r2 == 0) goto L_0x0059
            r2.delete()     // Catch:{ all -> 0x0061 }
        L_0x0059:
            if (r4 == 0) goto L_0x004d
            r4.close()     // Catch:{ IOException -> 0x005f }
            goto L_0x004d
        L_0x005f:
            r8 = move-exception
            goto L_0x004d
        L_0x0061:
            r6 = move-exception
        L_0x0062:
            if (r4 == 0) goto L_0x0067
            r4.close()     // Catch:{ IOException -> 0x006b }
        L_0x0067:
            throw r6     // Catch:{ all -> 0x0068 }
        L_0x0068:
            r6 = move-exception
            monitor-exit(r10)
            throw r6
        L_0x006b:
            r7 = move-exception
            goto L_0x0067
        L_0x006d:
            r6 = move-exception
            r4 = r5
            goto L_0x0062
        L_0x0070:
            r0 = move-exception
            r4 = r5
            goto L_0x0054
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.DiskBasedCache.initialize():void");
    }

    public synchronized void invalidate(String key, boolean fullExpire) {
        Cache.Entry entry = get(key);
        if (entry != null) {
            entry.softTtl = 0;
            if (fullExpire) {
                entry.ttl = 0;
            }
            put(key, entry);
        }
    }

    public synchronized void put(String key, Cache.Entry entry) {
        pruneIfNeeded(entry.data.length);
        File file = getFileForKey(key);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            CacheHeader e = new CacheHeader(key, entry);
            if (!e.writeHeader(fos)) {
                fos.close();
                VolleyLog.d("Failed to write header for %s", file.getAbsolutePath());
                throw new IOException();
            }
            fos.write(entry.data);
            fos.close();
            putEntry(key, e);
        } catch (IOException e2) {
            if (!file.delete()) {
                VolleyLog.d("Could not clean up file %s", file.getAbsolutePath());
            }
        }
    }

    public synchronized void remove(String key) {
        boolean deleted = getFileForKey(key).delete();
        removeEntry(key);
        if (!deleted) {
            VolleyLog.d("Could not delete cache entry for key=%s, filename=%s", key, getFilenameForKey(key));
        }
    }

    private String getFilenameForKey(String key) {
        int firstHalfLength = key.length() / 2;
        return String.valueOf(key.substring(0, firstHalfLength).hashCode()) + String.valueOf(key.substring(firstHalfLength).hashCode());
    }

    public File getFileForKey(String key) {
        return new File(this.mRootDirectory, getFilenameForKey(key));
    }

    private void pruneIfNeeded(int neededSpace) {
        if (this.mTotalSize + ((long) neededSpace) >= ((long) this.mMaxCacheSizeInBytes)) {
            if (VolleyLog.DEBUG) {
                VolleyLog.v("Pruning old cache entries.", new Object[0]);
            }
            long before = this.mTotalSize;
            int prunedFiles = 0;
            long startTime = SystemClock.elapsedRealtime();
            Iterator<Map.Entry<String, CacheHeader>> iterator = this.mEntries.entrySet().iterator();
            while (iterator.hasNext()) {
                CacheHeader e = iterator.next().getValue();
                if (getFileForKey(e.key).delete()) {
                    this.mTotalSize -= e.size;
                } else {
                    VolleyLog.d("Could not delete cache entry for key=%s, filename=%s", e.key, getFilenameForKey(e.key));
                }
                iterator.remove();
                prunedFiles++;
                if (((float) (this.mTotalSize + ((long) neededSpace))) < ((float) this.mMaxCacheSizeInBytes) * HYSTERESIS_FACTOR) {
                    break;
                }
            }
            if (VolleyLog.DEBUG) {
                VolleyLog.v("pruned %d files, %d bytes, %d ms", Integer.valueOf(prunedFiles), Long.valueOf(this.mTotalSize - before), Long.valueOf(SystemClock.elapsedRealtime() - startTime));
            }
        }
    }

    private void putEntry(String key, CacheHeader entry) {
        if (!this.mEntries.containsKey(key)) {
            this.mTotalSize += entry.size;
        } else {
            this.mTotalSize += entry.size - this.mEntries.get(key).size;
        }
        this.mEntries.put(key, entry);
    }

    private void removeEntry(String key) {
        CacheHeader entry = this.mEntries.get(key);
        if (entry != null) {
            this.mTotalSize -= entry.size;
            this.mEntries.remove(key);
        }
    }

    private static byte[] streamToBytes(InputStream in, int length) throws IOException {
        byte[] bytes = new byte[length];
        int pos = 0;
        while (pos < length) {
            int count = in.read(bytes, pos, length - pos);
            if (count == -1) {
                break;
            }
            pos += count;
        }
        if (pos == length) {
            return bytes;
        }
        throw new IOException("Expected " + length + " bytes, read " + pos + " bytes");
    }

    static class CacheHeader {
        public String etag;
        public String key;
        public Map<String, String> responseHeaders;
        public long serverDate;
        public long size;
        public long softTtl;
        public long ttl;

        private CacheHeader() {
        }

        public CacheHeader(String key2, Cache.Entry entry) {
            this.key = key2;
            this.size = (long) entry.data.length;
            this.etag = entry.etag;
            this.serverDate = entry.serverDate;
            this.ttl = entry.ttl;
            this.softTtl = entry.softTtl;
            this.responseHeaders = entry.responseHeaders;
        }

        public static CacheHeader readHeader(InputStream is) throws IOException {
            CacheHeader entry = new CacheHeader();
            if (DiskBasedCache.readInt(is) != DiskBasedCache.CACHE_MAGIC) {
                throw new IOException();
            }
            entry.key = DiskBasedCache.readString(is);
            entry.etag = DiskBasedCache.readString(is);
            if (entry.etag.equals("")) {
                entry.etag = null;
            }
            entry.serverDate = DiskBasedCache.readLong(is);
            entry.ttl = DiskBasedCache.readLong(is);
            entry.softTtl = DiskBasedCache.readLong(is);
            entry.responseHeaders = DiskBasedCache.readStringStringMap(is);
            return entry;
        }

        public Cache.Entry toCacheEntry(byte[] data) {
            Cache.Entry e = new Cache.Entry();
            e.data = data;
            e.etag = this.etag;
            e.serverDate = this.serverDate;
            e.ttl = this.ttl;
            e.softTtl = this.softTtl;
            e.responseHeaders = this.responseHeaders;
            return e;
        }

        public boolean writeHeader(OutputStream os) {
            try {
                DiskBasedCache.writeInt(os, DiskBasedCache.CACHE_MAGIC);
                DiskBasedCache.writeString(os, this.key);
                DiskBasedCache.writeString(os, this.etag == null ? "" : this.etag);
                DiskBasedCache.writeLong(os, this.serverDate);
                DiskBasedCache.writeLong(os, this.ttl);
                DiskBasedCache.writeLong(os, this.softTtl);
                DiskBasedCache.writeStringStringMap(this.responseHeaders, os);
                os.flush();
                return true;
            } catch (IOException e) {
                VolleyLog.d("%s", e.toString());
                return false;
            }
        }
    }

    private static class CountingInputStream extends FilterInputStream {
        /* access modifiers changed from: private */
        public int bytesRead;

        private CountingInputStream(InputStream in) {
            super(in);
            this.bytesRead = 0;
        }

        public int read() throws IOException {
            int result = super.read();
            if (result != -1) {
                this.bytesRead++;
            }
            return result;
        }

        public int read(byte[] buffer, int offset, int count) throws IOException {
            int result = super.read(buffer, offset, count);
            if (result != -1) {
                this.bytesRead += result;
            }
            return result;
        }
    }

    private static int read(InputStream is) throws IOException {
        int b = is.read();
        if (b != -1) {
            return b;
        }
        throw new EOFException();
    }

    static void writeInt(OutputStream os, int n) throws IOException {
        os.write((n >> 0) & 255);
        os.write((n >> 8) & 255);
        os.write((n >> 16) & 255);
        os.write((n >> 24) & 255);
    }

    static int readInt(InputStream is) throws IOException {
        return 0 | (read(is) << 0) | (read(is) << 8) | (read(is) << 16) | (read(is) << 24);
    }

    static void writeLong(OutputStream os, long n) throws IOException {
        os.write((byte) ((int) (n >>> 0)));
        os.write((byte) ((int) (n >>> 8)));
        os.write((byte) ((int) (n >>> 16)));
        os.write((byte) ((int) (n >>> 24)));
        os.write((byte) ((int) (n >>> 32)));
        os.write((byte) ((int) (n >>> 40)));
        os.write((byte) ((int) (n >>> 48)));
        os.write((byte) ((int) (n >>> 56)));
    }

    static long readLong(InputStream is) throws IOException {
        return 0 | ((((long) read(is)) & 255) << 0) | ((((long) read(is)) & 255) << 8) | ((((long) read(is)) & 255) << 16) | ((((long) read(is)) & 255) << 24) | ((((long) read(is)) & 255) << 32) | ((((long) read(is)) & 255) << 40) | ((((long) read(is)) & 255) << 48) | ((((long) read(is)) & 255) << 56);
    }

    static void writeString(OutputStream os, String s) throws IOException {
        byte[] b = s.getBytes("UTF-8");
        writeLong(os, (long) b.length);
        os.write(b, 0, b.length);
    }

    static String readString(InputStream is) throws IOException {
        return new String(streamToBytes(is, (int) readLong(is)), "UTF-8");
    }

    static void writeStringStringMap(Map<String, String> map, OutputStream os) throws IOException {
        if (map != null) {
            writeInt(os, map.size());
            for (Map.Entry<String, String> entry : map.entrySet()) {
                writeString(os, entry.getKey());
                writeString(os, entry.getValue());
            }
            return;
        }
        writeInt(os, 0);
    }

    static Map<String, String> readStringStringMap(InputStream is) throws IOException {
        int size = readInt(is);
        Map<String, String> result = size == 0 ? Collections.emptyMap() : new HashMap<>(size);
        for (int i = 0; i < size; i++) {
            result.put(readString(is).intern(), readString(is).intern());
        }
        return result;
    }
}
