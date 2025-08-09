package org.chromium.media;

import android.media.AudioTrack;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;

@JNINamespace("media")
class MediaCodecBridge {
    static final /* synthetic */ boolean $assertionsDisabled = (!MediaCodecBridge.class.desiredAssertionStatus());
    private static final String KEY_CROP_BOTTOM = "crop-bottom";
    private static final String KEY_CROP_LEFT = "crop-left";
    private static final String KEY_CROP_RIGHT = "crop-right";
    private static final String KEY_CROP_TOP = "crop-top";
    private static final int MAX_ADAPTIVE_PLAYBACK_HEIGHT = 1080;
    private static final int MAX_ADAPTIVE_PLAYBACK_WIDTH = 1920;
    private static final long MAX_PRESENTATION_TIMESTAMP_SHIFT_US = 100000;
    private static final int MEDIA_CODEC_DECODER = 0;
    private static final int MEDIA_CODEC_DEQUEUE_INPUT_AGAIN_LATER = 1;
    private static final int MEDIA_CODEC_DEQUEUE_OUTPUT_AGAIN_LATER = 2;
    private static final int MEDIA_CODEC_ENCODER = 1;
    private static final int MEDIA_CODEC_ERROR = 9;
    private static final int MEDIA_CODEC_INPUT_END_OF_STREAM = 5;
    private static final int MEDIA_CODEC_NO_KEY = 7;
    private static final int MEDIA_CODEC_OK = 0;
    private static final int MEDIA_CODEC_OUTPUT_BUFFERS_CHANGED = 3;
    private static final int MEDIA_CODEC_OUTPUT_END_OF_STREAM = 6;
    private static final int MEDIA_CODEC_OUTPUT_FORMAT_CHANGED = 4;
    private static final int MEDIA_CODEC_STOPPED = 8;
    private static final String TAG = "MediaCodecBridge";
    private boolean mAdaptivePlaybackSupported;
    private AudioTrack mAudioTrack;
    private boolean mFlushed;
    private ByteBuffer[] mInputBuffers;
    private long mLastPresentationTimeUs;
    private MediaCodec mMediaCodec;
    private String mMime;
    private ByteBuffer[] mOutputBuffers;

    private static class DequeueInputResult {
        private final int mIndex;
        private final int mStatus;

        private DequeueInputResult(int status, int index) {
            this.mStatus = status;
            this.mIndex = index;
        }

        @CalledByNative("DequeueInputResult")
        private int status() {
            return this.mStatus;
        }

        @CalledByNative("DequeueInputResult")
        private int index() {
            return this.mIndex;
        }
    }

    private static class CodecInfo {
        private final String mCodecName;
        private final String mCodecType;
        private final int mDirection;

        private CodecInfo(String codecType, String codecName, int direction) {
            this.mCodecType = codecType;
            this.mCodecName = codecName;
            this.mDirection = direction;
        }

        @CalledByNative("CodecInfo")
        private String codecType() {
            return this.mCodecType;
        }

        @CalledByNative("CodecInfo")
        private String codecName() {
            return this.mCodecName;
        }

        @CalledByNative("CodecInfo")
        private int direction() {
            return this.mDirection;
        }
    }

    private static class DequeueOutputResult {
        private final int mFlags;
        private final int mIndex;
        private final int mNumBytes;
        private final int mOffset;
        private final long mPresentationTimeMicroseconds;
        private final int mStatus;

        private DequeueOutputResult(int status, int index, int flags, int offset, long presentationTimeMicroseconds, int numBytes) {
            this.mStatus = status;
            this.mIndex = index;
            this.mFlags = flags;
            this.mOffset = offset;
            this.mPresentationTimeMicroseconds = presentationTimeMicroseconds;
            this.mNumBytes = numBytes;
        }

        @CalledByNative("DequeueOutputResult")
        private int status() {
            return this.mStatus;
        }

        @CalledByNative("DequeueOutputResult")
        private int index() {
            return this.mIndex;
        }

        @CalledByNative("DequeueOutputResult")
        private int flags() {
            return this.mFlags;
        }

        @CalledByNative("DequeueOutputResult")
        private int offset() {
            return this.mOffset;
        }

        @CalledByNative("DequeueOutputResult")
        private long presentationTimeMicroseconds() {
            return this.mPresentationTimeMicroseconds;
        }

        @CalledByNative("DequeueOutputResult")
        private int numBytes() {
            return this.mNumBytes;
        }
    }

    @CalledByNative
    private static CodecInfo[] getCodecsInfo() {
        Map<String, CodecInfo> map;
        Map<String, CodecInfo> encoderInfoMap = new HashMap<>();
        Map<String, CodecInfo> decoderInfoMap = new HashMap<>();
        int count = MediaCodecList.getCodecCount();
        for (int i = 0; i < count; i++) {
            MediaCodecInfo info = MediaCodecList.getCodecInfoAt(i);
            int direction = info.isEncoder() ? 1 : 0;
            String codecString = info.getName();
            String[] supportedTypes = info.getSupportedTypes();
            for (int j = 0; j < supportedTypes.length; j++) {
                if (info.isEncoder()) {
                    map = encoderInfoMap;
                } else {
                    map = decoderInfoMap;
                }
                if (!map.containsKey(supportedTypes[j])) {
                    map.put(supportedTypes[j], new CodecInfo(supportedTypes[j], codecString, direction));
                }
            }
        }
        ArrayList<CodecInfo> codecInfos = new ArrayList<>(decoderInfoMap.size() + encoderInfoMap.size());
        codecInfos.addAll(encoderInfoMap.values());
        codecInfos.addAll(decoderInfoMap.values());
        return (CodecInfo[]) codecInfos.toArray(new CodecInfo[codecInfos.size()]);
    }

    @CalledByNative
    private static String getDefaultCodecName(String mime, int direction) {
        MediaCodec mediaCodec;
        if (Build.VERSION.SDK_INT < 18) {
            return "";
        }
        if (direction == 1) {
            try {
                mediaCodec = MediaCodec.createEncoderByType(mime);
            } catch (Exception e) {
                Log.w(TAG, "getDefaultCodecName: Failed to create MediaCodec: " + mime + ", direction: " + direction, e);
                return "";
            }
        } else {
            mediaCodec = MediaCodec.createDecoderByType(mime);
        }
        String codecName = mediaCodec.getName();
        mediaCodec.release();
        return codecName;
    }

    @CalledByNative
    private static int[] getEncoderColorFormatsForMime(String mime) {
        int count = MediaCodecList.getCodecCount();
        for (int i = 0; i < count; i++) {
            MediaCodecInfo info = MediaCodecList.getCodecInfoAt(i);
            if (info.isEncoder()) {
                String[] supportedTypes = info.getSupportedTypes();
                for (String equalsIgnoreCase : supportedTypes) {
                    if (equalsIgnoreCase.equalsIgnoreCase(mime)) {
                        return info.getCapabilitiesForType(mime).colorFormats;
                    }
                }
                continue;
            }
        }
        return null;
    }

    private static String getDecoderNameForMime(String mime) {
        int count = MediaCodecList.getCodecCount();
        for (int i = 0; i < count; i++) {
            MediaCodecInfo info = MediaCodecList.getCodecInfoAt(i);
            if (!info.isEncoder()) {
                String[] supportedTypes = info.getSupportedTypes();
                for (String equalsIgnoreCase : supportedTypes) {
                    if (equalsIgnoreCase.equalsIgnoreCase(mime)) {
                        return info.getName();
                    }
                }
                continue;
            }
        }
        return null;
    }

    private MediaCodecBridge(MediaCodec mediaCodec, String mime, boolean adaptivePlaybackSupported) {
        if ($assertionsDisabled || mediaCodec != null) {
            this.mMediaCodec = mediaCodec;
            this.mMime = mime;
            this.mLastPresentationTimeUs = 0;
            this.mFlushed = true;
            this.mAdaptivePlaybackSupported = adaptivePlaybackSupported;
            return;
        }
        throw new AssertionError();
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    @org.chromium.base.CalledByNative
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static org.chromium.media.MediaCodecBridge create(java.lang.String r9, boolean r10, int r11) {
        /*
            r5 = 0
            if (r10 == 0) goto L_0x000a
            int r6 = android.os.Build.VERSION.SDK_INT
            r7 = 18
            if (r6 >= r7) goto L_0x000a
        L_0x0009:
            return r5
        L_0x000a:
            r4 = 0
            r0 = 0
            java.lang.String r6 = "video"
            boolean r6 = r9.startsWith(r6)     // Catch:{ Exception -> 0x005f }
            if (r6 == 0) goto L_0x004e
            if (r10 == 0) goto L_0x004e
            if (r11 != 0) goto L_0x004e
            java.lang.String r1 = getDecoderNameForMime(r9)     // Catch:{ Exception -> 0x005f }
            if (r1 == 0) goto L_0x0009
            int r6 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x005f }
            r7 = 19
            if (r6 < r7) goto L_0x002f
            android.media.MediaCodec r3 = android.media.MediaCodec.createByCodecName(r1)     // Catch:{ Exception -> 0x005f }
            boolean r0 = codecSupportsAdaptivePlayback(r3, r9)     // Catch:{ Exception -> 0x005f }
            r3.release()     // Catch:{ Exception -> 0x005f }
        L_0x002f:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x005f }
            r6.<init>()     // Catch:{ Exception -> 0x005f }
            java.lang.StringBuilder r6 = r6.append(r1)     // Catch:{ Exception -> 0x005f }
            java.lang.String r7 = ".secure"
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ Exception -> 0x005f }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x005f }
            android.media.MediaCodec r4 = android.media.MediaCodec.createByCodecName(r6)     // Catch:{ Exception -> 0x005f }
        L_0x0046:
            if (r4 == 0) goto L_0x0009
            org.chromium.media.MediaCodecBridge r5 = new org.chromium.media.MediaCodecBridge
            r5.<init>(r4, r9, r0)
            goto L_0x0009
        L_0x004e:
            r6 = 1
            if (r11 != r6) goto L_0x0056
            android.media.MediaCodec r4 = android.media.MediaCodec.createEncoderByType(r9)     // Catch:{ Exception -> 0x005f }
            goto L_0x0046
        L_0x0056:
            android.media.MediaCodec r4 = android.media.MediaCodec.createDecoderByType(r9)     // Catch:{ Exception -> 0x005f }
            boolean r0 = codecSupportsAdaptivePlayback(r4, r9)     // Catch:{ Exception -> 0x005f }
            goto L_0x0046
        L_0x005f:
            r2 = move-exception
            java.lang.String r6 = "MediaCodecBridge"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "Failed to create MediaCodec: "
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.StringBuilder r7 = r7.append(r9)
            java.lang.String r8 = ", isSecure: "
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.StringBuilder r7 = r7.append(r10)
            java.lang.String r8 = ", direction: "
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.StringBuilder r7 = r7.append(r11)
            java.lang.String r7 = r7.toString()
            android.util.Log.e(r6, r7, r2)
            goto L_0x0046
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.media.MediaCodecBridge.create(java.lang.String, boolean, int):org.chromium.media.MediaCodecBridge");
    }

    @CalledByNative
    private void release() {
        try {
            this.mMediaCodec.release();
        } catch (IllegalStateException e) {
            Log.e(TAG, "Cannot release media codec", e);
        }
        this.mMediaCodec = null;
        if (this.mAudioTrack != null) {
            this.mAudioTrack.release();
        }
    }

    @CalledByNative
    private boolean start() {
        try {
            this.mMediaCodec.start();
            this.mInputBuffers = this.mMediaCodec.getInputBuffers();
            return true;
        } catch (IllegalStateException e) {
            Log.e(TAG, "Cannot start the media codec", e);
            return false;
        }
    }

    @CalledByNative
    private DequeueInputResult dequeueInputBuffer(long timeoutUs) {
        int status = 9;
        int index = -1;
        try {
            int indexOrStatus = this.mMediaCodec.dequeueInputBuffer(timeoutUs);
            if (indexOrStatus >= 0) {
                status = 0;
                index = indexOrStatus;
            } else if (indexOrStatus == -1) {
                Log.e(TAG, "dequeueInputBuffer: MediaCodec.INFO_TRY_AGAIN_LATER");
                status = 1;
            } else {
                Log.e(TAG, "Unexpected index_or_status: " + indexOrStatus);
                if (!$assertionsDisabled) {
                    throw new AssertionError();
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to dequeue input buffer", e);
        }
        return new DequeueInputResult(status, index);
    }

    @CalledByNative
    private int flush() {
        try {
            this.mFlushed = true;
            if (this.mAudioTrack != null) {
                this.mAudioTrack.pause();
                this.mAudioTrack.flush();
            }
            this.mMediaCodec.flush();
            return 0;
        } catch (IllegalStateException e) {
            Log.e(TAG, "Failed to flush MediaCodec", e);
            return 9;
        }
    }

    @CalledByNative
    private void stop() {
        this.mMediaCodec.stop();
        if (this.mAudioTrack != null) {
            this.mAudioTrack.pause();
        }
    }

    private boolean outputFormatHasCropValues(MediaFormat format) {
        return format.containsKey(KEY_CROP_RIGHT) && format.containsKey(KEY_CROP_LEFT) && format.containsKey(KEY_CROP_BOTTOM) && format.containsKey(KEY_CROP_TOP);
    }

    @CalledByNative
    private int getOutputHeight() {
        MediaFormat format = this.mMediaCodec.getOutputFormat();
        return outputFormatHasCropValues(format) ? (format.getInteger(KEY_CROP_BOTTOM) - format.getInteger(KEY_CROP_TOP)) + 1 : format.getInteger("height");
    }

    @CalledByNative
    private int getOutputWidth() {
        MediaFormat format = this.mMediaCodec.getOutputFormat();
        return outputFormatHasCropValues(format) ? (format.getInteger(KEY_CROP_RIGHT) - format.getInteger(KEY_CROP_LEFT)) + 1 : format.getInteger("width");
    }

    @CalledByNative
    private ByteBuffer getInputBuffer(int index) {
        return this.mInputBuffers[index];
    }

    @CalledByNative
    private ByteBuffer getOutputBuffer(int index) {
        return this.mOutputBuffers[index];
    }

    @CalledByNative
    private int getInputBuffersCount() {
        return this.mInputBuffers.length;
    }

    @CalledByNative
    private int getOutputBuffersCount() {
        if (this.mOutputBuffers != null) {
            return this.mOutputBuffers.length;
        }
        return -1;
    }

    @CalledByNative
    private int getOutputBuffersCapacity() {
        if (this.mOutputBuffers != null) {
            return this.mOutputBuffers[0].capacity();
        }
        return -1;
    }

    @CalledByNative
    private boolean getOutputBuffers() {
        try {
            this.mOutputBuffers = this.mMediaCodec.getOutputBuffers();
            return true;
        } catch (IllegalStateException e) {
            Log.e(TAG, "Cannot get output buffers", e);
            return false;
        }
    }

    @CalledByNative
    private int queueInputBuffer(int index, int offset, int size, long presentationTimeUs, int flags) {
        resetLastPresentationTimeIfNeeded(presentationTimeUs);
        try {
            this.mMediaCodec.queueInputBuffer(index, offset, size, presentationTimeUs, flags);
            return 0;
        } catch (Exception e) {
            Log.e(TAG, "Failed to queue input buffer", e);
            return 9;
        }
    }

    @CalledByNative
    private void setVideoBitrate(int bps) {
        Bundle b = new Bundle();
        b.putInt("video-bitrate", bps);
        this.mMediaCodec.setParameters(b);
    }

    @CalledByNative
    private void requestKeyFrameSoon() {
        Bundle b = new Bundle();
        b.putInt("request-sync", 0);
        this.mMediaCodec.setParameters(b);
    }

    @CalledByNative
    private int queueSecureInputBuffer(int index, int offset, byte[] iv, byte[] keyId, int[] numBytesOfClearData, int[] numBytesOfEncryptedData, int numSubSamples, long presentationTimeUs) {
        resetLastPresentationTimeIfNeeded(presentationTimeUs);
        try {
            MediaCodec.CryptoInfo cryptoInfo = new MediaCodec.CryptoInfo();
            cryptoInfo.set(numSubSamples, numBytesOfClearData, numBytesOfEncryptedData, keyId, iv, 1);
            this.mMediaCodec.queueSecureInputBuffer(index, offset, cryptoInfo, presentationTimeUs, 0);
            return 0;
        } catch (MediaCodec.CryptoException e) {
            Log.e(TAG, "Failed to queue secure input buffer", e);
            if (e.getErrorCode() == 1) {
                Log.e(TAG, "MediaCodec.CryptoException.ERROR_NO_KEY");
                return 7;
            }
            Log.e(TAG, "MediaCodec.CryptoException with error code " + e.getErrorCode());
            return 9;
        } catch (IllegalStateException e2) {
            Log.e(TAG, "Failed to queue secure input buffer", e2);
            return 9;
        }
    }

    @CalledByNative
    private void releaseOutputBuffer(int index, boolean render) {
        try {
            this.mMediaCodec.releaseOutputBuffer(index, render);
        } catch (IllegalStateException e) {
            Log.e(TAG, "Failed to release output buffer", e);
        }
    }

    @CalledByNative
    private DequeueOutputResult dequeueOutputBuffer(long timeoutUs) {
        MediaCodec.BufferInfo info = new MediaCodec.BufferInfo();
        int status = 9;
        int index = -1;
        try {
            int indexOrStatus = this.mMediaCodec.dequeueOutputBuffer(info, timeoutUs);
            if (info.presentationTimeUs < this.mLastPresentationTimeUs) {
                info.presentationTimeUs = this.mLastPresentationTimeUs;
            }
            this.mLastPresentationTimeUs = info.presentationTimeUs;
            if (indexOrStatus >= 0) {
                status = 0;
                index = indexOrStatus;
            } else if (indexOrStatus == -3) {
                status = 3;
            } else if (indexOrStatus == -2) {
                status = 4;
            } else if (indexOrStatus == -1) {
                status = 2;
            } else {
                Log.e(TAG, "Unexpected index_or_status: " + indexOrStatus);
                if (!$assertionsDisabled) {
                    throw new AssertionError();
                }
            }
        } catch (IllegalStateException e) {
            Log.e(TAG, "Failed to dequeue output buffer", e);
        }
        return new DequeueOutputResult(status, index, info.flags, info.offset, info.presentationTimeUs, info.size);
    }

    @CalledByNative
    private boolean configureVideo(MediaFormat format, Surface surface, MediaCrypto crypto, int flags) {
        try {
            if (this.mAdaptivePlaybackSupported) {
                format.setInteger("max-width", MAX_ADAPTIVE_PLAYBACK_WIDTH);
                format.setInteger("max-height", MAX_ADAPTIVE_PLAYBACK_HEIGHT);
            }
            this.mMediaCodec.configure(format, surface, crypto, flags);
            return true;
        } catch (IllegalStateException e) {
            Log.e(TAG, "Cannot configure the video codec", e);
            return false;
        }
    }

    @CalledByNative
    private static MediaFormat createAudioFormat(String mime, int sampleRate, int channelCount) {
        return MediaFormat.createAudioFormat(mime, sampleRate, channelCount);
    }

    @CalledByNative
    private static MediaFormat createVideoDecoderFormat(String mime, int width, int height) {
        return MediaFormat.createVideoFormat(mime, width, height);
    }

    @CalledByNative
    private static MediaFormat createVideoEncoderFormat(String mime, int width, int height, int bitRate, int frameRate, int iFrameInterval, int colorFormat) {
        MediaFormat format = MediaFormat.createVideoFormat(mime, width, height);
        format.setInteger("bitrate", bitRate);
        format.setInteger("frame-rate", frameRate);
        format.setInteger("i-frame-interval", iFrameInterval);
        format.setInteger("color-format", colorFormat);
        return format;
    }

    @CalledByNative
    private boolean isAdaptivePlaybackSupported(int width, int height) {
        if (this.mAdaptivePlaybackSupported && width <= MAX_ADAPTIVE_PLAYBACK_WIDTH && height <= MAX_ADAPTIVE_PLAYBACK_HEIGHT) {
            return true;
        }
        return false;
    }

    private static boolean codecSupportsAdaptivePlayback(MediaCodec mediaCodec, String mime) {
        MediaCodecInfo.CodecCapabilities capabilities;
        if (Build.VERSION.SDK_INT < 19 || mediaCodec == null) {
            return false;
        }
        try {
            MediaCodecInfo info = mediaCodec.getCodecInfo();
            if (info.isEncoder() || (capabilities = info.getCapabilitiesForType(mime)) == null || !capabilities.isFeatureSupported("adaptive-playback")) {
                return false;
            }
            return true;
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Cannot retrieve codec information", e);
            return false;
        }
    }

    @CalledByNative
    private static void setCodecSpecificData(MediaFormat format, int index, byte[] bytes) {
        String name = null;
        if (index == 0) {
            name = "csd-0";
        } else if (index == 1) {
            name = "csd-1";
        }
        if (name != null) {
            format.setByteBuffer(name, ByteBuffer.wrap(bytes));
        }
    }

    @CalledByNative
    private static void setFrameHasADTSHeader(MediaFormat format) {
        format.setInteger("is-adts", 1);
    }

    @CalledByNative
    private boolean configureAudio(MediaFormat format, MediaCrypto crypto, int flags, boolean playAudio) {
        try {
            this.mMediaCodec.configure(format, (Surface) null, crypto, flags);
            if (playAudio) {
                int sampleRate = format.getInteger("sample-rate");
                int channelConfig = getAudioFormat(format.getInteger("channel-count"));
                this.mAudioTrack = new AudioTrack(3, sampleRate, channelConfig, 2, AudioTrack.getMinBufferSize(sampleRate, channelConfig, 2), 1);
                if (this.mAudioTrack.getState() == 0) {
                    this.mAudioTrack = null;
                    return false;
                }
            }
            return true;
        } catch (IllegalStateException e) {
            Log.e(TAG, "Cannot configure the audio codec", e);
            return false;
        }
    }

    @CalledByNative
    private long playOutputBuffer(byte[] buf) {
        if (this.mAudioTrack == null) {
            return 0;
        }
        if (3 != this.mAudioTrack.getPlayState()) {
            this.mAudioTrack.play();
        }
        int size = this.mAudioTrack.write(buf, 0, buf.length);
        if (buf.length != size) {
            Log.i(TAG, "Failed to send all data to audio output, expected size: " + buf.length + ", actual size: " + size);
        }
        return (long) this.mAudioTrack.getPlaybackHeadPosition();
    }

    @CalledByNative
    private void setVolume(double volume) {
        if (this.mAudioTrack != null) {
            this.mAudioTrack.setStereoVolume((float) volume, (float) volume);
        }
    }

    private void resetLastPresentationTimeIfNeeded(long presentationTimeUs) {
        if (this.mFlushed) {
            this.mLastPresentationTimeUs = Math.max(presentationTimeUs - MAX_PRESENTATION_TIMESTAMP_SHIFT_US, 0);
            this.mFlushed = false;
        }
    }

    private int getAudioFormat(int channelCount) {
        switch (channelCount) {
            case 1:
                return 4;
            case 2:
                return 12;
            case 4:
                return 204;
            case 6:
                return 252;
            case 8:
                return 1020;
            default:
                return 1;
        }
    }
}
