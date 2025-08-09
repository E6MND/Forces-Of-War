package com.google.android.gms.games.internal.data;

import android.net.Uri;
import com.google.android.gms.games.Games;

public final class GamesDataChangeUris {
    private static final Uri Rg = Uri.parse("content://com.google.android.gms.games/").buildUpon().appendPath("data_change").build();
    public static final Uri Rh = Rg.buildUpon().appendPath("invitations").build();
    public static final Uri Ri = Rg.buildUpon().appendEncodedPath(Games.EXTRA_PLAYER_IDS).build();
}
