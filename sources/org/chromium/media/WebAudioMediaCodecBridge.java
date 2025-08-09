package org.chromium.media;

import android.content.Context;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.Surface;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;

@JNINamespace("media")
class WebAudioMediaCodecBridge {
    static final String LOG_TAG = "WebAudioMediaCodec";
    static final long TIMEOUT_MICROSECONDS = 500;

    private static native void nativeInitializeDestination(long j, int i, int i2, long j2);

    private static native void nativeOnChunkDecoded(long j, ByteBuffer byteBuffer, int i, int i2, int i3);

    WebAudioMediaCodecBridge() {
    }

    @CalledByNative
    private static String createTempFile(Context ctx) throws IOException {
        return File.createTempFile("webaudio", ".dat", ctx.getCacheDir()).getAbsolutePath();
    }

    @CalledByNative
    private static boolean decodeAudioFile(Context ctx, long nativeMediaCodecBridge, int inputFD, long dataSize) {
        int inputBufIndex;
        if (dataSize < 0 || dataSize > 2147483647L) {
            return false;
        }
        MediaExtractor extractor = new MediaExtractor();
        ParcelFileDescriptor encodedFD = ParcelFileDescriptor.adoptFd(inputFD);
        try {
            extractor.setDataSource(encodedFD.getFileDescriptor(), 0, dataSize);
            if (extractor.getTrackCount() <= 0) {
                encodedFD.detachFd();
                return false;
            }
            MediaFormat format = extractor.getTrackFormat(0);
            int inputChannelCount = format.getInteger("channel-count");
            int outputChannelCount = inputChannelCount;
            int sampleRate = format.getInteger("sample-rate");
            String mime = format.getString("mime");
            long durationMicroseconds = 0;
            if (format.containsKey("durationUs")) {
                try {
                    durationMicroseconds = format.getLong("durationUs");
                } catch (Exception e) {
                    Log.d(LOG_TAG, "Cannot get duration");
                }
            }
            if (durationMicroseconds > 2147483647L) {
                durationMicroseconds = 0;
            }
            Log.d(LOG_TAG, "Initial: Tracks: " + extractor.getTrackCount() + " Format: " + format);
            try {
                MediaCodec codec = MediaCodec.createDecoderByType(mime);
                codec.configure(format, (Surface) null, (MediaCrypto) null, 0);
                codec.start();
                ByteBuffer[] codecInputBuffers = codec.getInputBuffers();
                ByteBuffer[] codecOutputBuffers = codec.getOutputBuffers();
                extractor.selectTrack(0);
                boolean sawInputEOS = false;
                boolean sawOutputEOS = false;
                boolean destinationInitialized = false;
                while (!sawOutputEOS) {
                    if (!sawInputEOS && (inputBufIndex = codec.dequeueInputBuffer(TIMEOUT_MICROSECONDS)) >= 0) {
                        int sampleSize = extractor.readSampleData(codecInputBuffers[inputBufIndex], 0);
                        long presentationTimeMicroSec = 0;
                        if (sampleSize < 0) {
                            sawInputEOS = true;
                            sampleSize = 0;
                        } else {
                            presentationTimeMicroSec = extractor.getSampleTime();
                        }
                        codec.queueInputBuffer(inputBufIndex, 0, sampleSize, presentationTimeMicroSec, sawInputEOS ? 4 : 0);
                        if (!sawInputEOS) {
                            extractor.advance();
                        }
                    }
                    MediaCodec.BufferInfo info = new MediaCodec.BufferInfo();
                    int outputBufIndex = codec.dequeueOutputBuffer(info, TIMEOUT_MICROSECONDS);
                    if (outputBufIndex >= 0) {
                        ByteBuffer buf = codecOutputBuffers[outputBufIndex];
                        if (!destinationInitialized) {
                            Log.d(LOG_TAG, "Final:  Rate: " + sampleRate + " Channels: " + inputChannelCount + " Mime: " + mime + " Duration: " + durationMicroseconds + " microsec");
                            nativeInitializeDestination(nativeMediaCodecBridge, inputChannelCount, sampleRate, durationMicroseconds);
                            destinationInitialized = true;
                        }
                        if (destinationInitialized && info.size > 0) {
                            nativeOnChunkDecoded(nativeMediaCodecBridge, buf, info.size, inputChannelCount, outputChannelCount);
                        }
                        buf.clear();
                        codec.releaseOutputBuffer(outputBufIndex, false);
                        if ((info.flags & 4) != 0) {
                            sawOutputEOS = true;
                        }
                    } else if (outputBufIndex == -3) {
                        codecOutputBuffers = codec.getOutputBuffers();
                    } else if (outputBufIndex == -2) {
                        MediaFormat newFormat = codec.getOutputFormat();
                        outputChannelCount = newFormat.getInteger("channel-count");
                        sampleRate = newFormat.getInteger("sample-rate");
                        Log.d(LOG_TAG, "output format changed to " + newFormat);
                    }
                }
                encodedFD.detachFd();
                codec.stop();
                codec.release();
                return true;
            } catch (Exception e2) {
                Log.w(LOG_TAG, "Failed to create MediaCodec for mime type: " + mime);
                encodedFD.detachFd();
                return false;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            encodedFD.detachFd();
            return false;
        }
    }
}
