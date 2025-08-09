package com.uken.android.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.SoundPool;
import android.util.Log;
import android.webkit.JavascriptInterface;
import java.util.HashMap;

public class UkenAudioManager {
    private static String SETTING_MUTE = "setting_mute";
    private static final String TAG = "UkenAudioManager";
    private static UkenAudioManager singleton = null;
    private boolean mForeground = true;
    private boolean mMuted = false;
    private Context mainContext = null;
    private SoundPool soundPool;
    private HashMap<String, Integer> soundsMap;

    private enum Sounds {
        achievement1,
        achievement2,
        achievement3,
        battlelose1,
        battlelose2,
        battlelose3,
        battlewin1,
        battlewin2,
        buy1,
        buy2,
        buy3,
        chatping1,
        chatping2,
        chatping3,
        collectincome1,
        collectincome2,
        collectincome3,
        craftfail1,
        craftfail2,
        genericalert1,
        genericalert2,
        genericalert3,
        heal1,
        heal2,
        heal3,
        levelup1,
        levelup2,
        levelup3,
        missionfail1,
        missionfail2,
        missionfail3,
        missionsuccess1,
        missionsuccess2,
        missionsuccess3,
        navigation1,
        navigation2,
        navigation3,
        select1,
        select2,
        select3,
        sell1,
        sell2,
        sell3,
        statusbaralert1,
        statusbaralert2,
        statusbaralert3,
        slotswin1,
        slotslose1,
        cash_1,
        cash_2,
        explode_1,
        explode_2,
        explode_3,
        fist_1,
        fist_2,
        fist_3,
        magnify_1,
        magnify_2,
        burst_shot_1,
        burst_shot_2,
        burst_shot_3,
        burst_shot_4,
        shotgun_1,
        shotgun_2,
        single_shot_1,
        single_shot_2,
        steps_1,
        steps_2,
        steps_3,
        mission_boss_1
    }

    @JavascriptInterface
    public static UkenAudioManager getInstance(Context context) {
        if (singleton == null) {
            singleton = new UkenAudioManager(context);
        }
        return singleton;
    }

    private UkenAudioManager(Context context) {
        this.mainContext = context;
        this.soundPool = new SoundPool(Sounds.values().length, 3, 100);
        this.soundsMap = new HashMap<>();
        String packageName = null;
        try {
            packageName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
        } catch (PackageManager.NameNotFoundException e) {
            Utils.logCrashlytics(e);
        }
        for (Sounds sound : Sounds.values()) {
            int resId = context.getResources().getIdentifier(sound.name(), "raw", packageName);
            if (resId > 0) {
                this.soundsMap.put(sound.name(), Integer.valueOf(this.soundPool.load(context, resId, 1)));
            }
        }
        loadSettings();
    }

    @JavascriptInterface
    public void playSound(String name) {
        loadSettings();
        float volume = 1.0f;
        if (this.mMuted) {
            volume = 0.0f;
        }
        if (Consts.DEBUG) {
            Log.d(TAG, String.format("audiofile name: %s, volume: %f", new Object[]{name, Float.valueOf(volume)}));
        }
        Integer soundId = this.soundsMap.get(name);
        if (soundId != null && this.mForeground) {
            if (Consts.DEBUG) {
                Log.d(TAG, String.format("playing sound ID: %s", new Object[]{soundId}));
            }
            this.soundPool.play(soundId.intValue(), volume, volume, 1, 0, 1.0f);
        }
    }

    @JavascriptInterface
    public boolean isMuted() {
        return this.mMuted;
    }

    @JavascriptInterface
    public void mute() {
        this.mMuted = true;
        saveSettings();
    }

    @JavascriptInterface
    public void unmute() {
        this.mMuted = false;
        saveSettings();
    }

    @JavascriptInterface
    public boolean isForeground() {
        return this.mForeground;
    }

    @JavascriptInterface
    public void setForeground(boolean value) {
        this.mForeground = value;
    }

    private void saveSettings() {
        SharedPreferences.Editor editor = Prefs.get(this.mainContext).edit();
        editor.putBoolean(SETTING_MUTE, this.mMuted);
        editor.commit();
    }

    private void loadSettings() {
        this.mMuted = Prefs.get(this.mainContext).getBoolean(SETTING_MUTE, false);
    }
}
