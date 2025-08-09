package com.google.android.gms.games.internal.player;

import android.text.TextUtils;

public final class PlayerColumnNames {
    public final String RL;
    public final String RM;
    public final String RN;
    public final String RO;
    public final String RP;
    public final String RQ;
    public final String RR;
    public final String RS;
    public final String RT;
    public final String RU;
    public final String RV;
    public final String RW;
    public final String RX;
    public final String RY;
    public final String RZ;
    public final String Sa;
    public final String Sb;
    public final String Sc;
    public final String Sd;
    public final String Se;
    public final String Sf;
    public final String Sg;
    public final String Sh;
    public final String Si;

    public PlayerColumnNames(String prefix) {
        if (TextUtils.isEmpty(prefix)) {
            this.RL = "external_player_id";
            this.RM = "profile_name";
            this.RN = "profile_icon_image_uri";
            this.RO = "profile_icon_image_url";
            this.RP = "profile_hi_res_image_uri";
            this.RQ = "profile_hi_res_image_url";
            this.RR = "last_updated";
            this.RS = "is_in_circles";
            this.RT = "played_with_timestamp";
            this.RU = "current_xp_total";
            this.RV = "current_level";
            this.RW = "current_level_min_xp";
            this.RX = "current_level_max_xp";
            this.RY = "next_level";
            this.RZ = "next_level_max_xp";
            this.Sa = "last_level_up_timestamp";
            this.Sb = "player_title";
            this.Sc = "has_all_public_acls";
            this.Sd = "most_recent_external_game_id";
            this.Se = "most_recent_game_name";
            this.Sf = "most_recent_activity_timestamp";
            this.Sg = "most_recent_game_icon_uri";
            this.Sh = "most_recent_game_hi_res_uri";
            this.Si = "most_recent_game_featured_uri";
            return;
        }
        this.RL = prefix + "external_player_id";
        this.RM = prefix + "profile_name";
        this.RN = prefix + "profile_icon_image_uri";
        this.RO = prefix + "profile_icon_image_url";
        this.RP = prefix + "profile_hi_res_image_uri";
        this.RQ = prefix + "profile_hi_res_image_url";
        this.RR = prefix + "last_updated";
        this.RS = prefix + "is_in_circles";
        this.RT = prefix + "played_with_timestamp";
        this.RU = prefix + "current_xp_total";
        this.RV = prefix + "current_level";
        this.RW = prefix + "current_level_min_xp";
        this.RX = prefix + "current_level_max_xp";
        this.RY = prefix + "next_level";
        this.RZ = prefix + "next_level_max_xp";
        this.Sa = prefix + "last_level_up_timestamp";
        this.Sb = prefix + "player_title";
        this.Sc = prefix + "has_all_public_acls";
        this.Sd = prefix + "most_recent_external_game_id";
        this.Se = prefix + "most_recent_game_name";
        this.Sf = prefix + "most_recent_activity_timestamp";
        this.Sg = prefix + "most_recent_game_icon_uri";
        this.Sh = prefix + "most_recent_game_hi_res_uri";
        this.Si = prefix + "most_recent_game_featured_uri";
    }
}
