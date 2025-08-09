package org.xwalk.core.internal.extension.api.device_capabilities;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import java.util.HashSet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xwalk.core.internal.extension.api.device_capabilities.XWalkMediaCodec;

class MediaCodec extends XWalkMediaCodec {
    public MediaCodec(DeviceCapabilities instance) {
        this.mDeviceCapabilities = instance;
        this.mAudioCodecsSet = new HashSet();
        this.mVideoCodecsSet = new HashSet();
        getCodecsList();
    }

    public JSONObject getCodecsInfo() {
        JSONObject outputObject = new JSONObject();
        JSONArray audioCodecsArray = new JSONArray();
        JSONArray videoCodecsArray = new JSONArray();
        try {
            for (XWalkMediaCodec.AudioCodecElement codecToAdd : this.mAudioCodecsSet) {
                JSONObject codecsObject = new JSONObject();
                codecsObject.put("format", codecToAdd.codecName);
                audioCodecsArray.put(codecsObject);
            }
            for (XWalkMediaCodec.VideoCodecElement codecToAdd2 : this.mVideoCodecsSet) {
                JSONObject codecsObject2 = new JSONObject();
                codecsObject2.put("format", codecToAdd2.codecName);
                codecsObject2.put("encode", codecToAdd2.isEncoder);
                codecsObject2.put("hwAccel", codecToAdd2.hwAccel);
                videoCodecsArray.put(codecsObject2);
            }
            outputObject.put("audioCodecs", audioCodecsArray);
            outputObject.put("videoCodecs", videoCodecsArray);
            return outputObject;
        } catch (JSONException e) {
            return this.mDeviceCapabilities.setErrorMessage(e.toString());
        }
    }

    public void getCodecsList() {
        int numCodecs = MediaCodecList.getCodecCount();
        for (int i = 0; i < numCodecs; i++) {
            MediaCodecInfo codecInfo = MediaCodecList.getCodecInfoAt(i);
            String name = codecInfo.getName().toUpperCase();
            boolean hasAdded = false;
            String[] arr$ = AUDIO_CODEC_NAMES;
            int len$ = arr$.length;
            int i$ = 0;
            while (true) {
                if (i$ >= len$) {
                    break;
                }
                String nameListElement = arr$[i$];
                if (name.contains(nameListElement)) {
                    this.mAudioCodecsSet.add(new XWalkMediaCodec.AudioCodecElement(nameListElement));
                    hasAdded = true;
                    break;
                }
                i$++;
            }
            if (!hasAdded) {
                String[] arr$2 = VIDEO_CODEC_NAMES;
                int len$2 = arr$2.length;
                int i$2 = 0;
                while (true) {
                    if (i$2 >= len$2) {
                        break;
                    }
                    String nameListElement2 = arr$2[i$2];
                    if (name.contains(nameListElement2)) {
                        this.mVideoCodecsSet.add(new XWalkMediaCodec.VideoCodecElement(nameListElement2, codecInfo.isEncoder(), false));
                        break;
                    }
                    i$2++;
                }
            }
        }
    }
}
