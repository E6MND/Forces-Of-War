package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.internal.constants.TimeSpan;
import com.google.android.gms.internal.hl;
import com.google.android.gms.internal.hn;
import java.util.HashMap;

public final class ScoreSubmissionData {
    private static final String[] Sk = {"leaderboardId", "playerId", "timeSpan", "hasResult", "rawScore", "formattedScore", "newBest", "scoreTag"};
    private int CQ;
    private String MP;
    private HashMap<Integer, Result> SQ = new HashMap<>();
    private String Sm;

    public static final class Result {
        public final String formattedScore;
        public final boolean newBest;
        public final long rawScore;
        public final String scoreTag;

        public Result(long rawScore2, String formattedScore2, String scoreTag2, boolean newBest2) {
            this.rawScore = rawScore2;
            this.formattedScore = formattedScore2;
            this.scoreTag = scoreTag2;
            this.newBest = newBest2;
        }

        public String toString() {
            return hl.e(this).a("RawScore", Long.valueOf(this.rawScore)).a("FormattedScore", this.formattedScore).a("ScoreTag", this.scoreTag).a("NewBest", Boolean.valueOf(this.newBest)).toString();
        }
    }

    public ScoreSubmissionData(DataHolder dataHolder) {
        this.CQ = dataHolder.getStatusCode();
        int count = dataHolder.getCount();
        hn.C(count == 3);
        for (int i = 0; i < count; i++) {
            int ae = dataHolder.ae(i);
            if (i == 0) {
                this.Sm = dataHolder.c("leaderboardId", i, ae);
                this.MP = dataHolder.c("playerId", i, ae);
            }
            if (dataHolder.d("hasResult", i, ae)) {
                a(new Result(dataHolder.a("rawScore", i, ae), dataHolder.c("formattedScore", i, ae), dataHolder.c("scoreTag", i, ae), dataHolder.d("newBest", i, ae)), dataHolder.b("timeSpan", i, ae));
            }
        }
    }

    private void a(Result result, int i) {
        this.SQ.put(Integer.valueOf(i), result);
    }

    public String getLeaderboardId() {
        return this.Sm;
    }

    public String getPlayerId() {
        return this.MP;
    }

    public Result getScoreResult(int timeSpan) {
        return this.SQ.get(Integer.valueOf(timeSpan));
    }

    public String toString() {
        hl.a a = hl.e(this).a("PlayerId", this.MP).a("StatusCode", Integer.valueOf(this.CQ));
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= 3) {
                return a.toString();
            }
            Result result = this.SQ.get(Integer.valueOf(i2));
            a.a("TimesSpan", TimeSpan.cm(i2));
            a.a("Result", result == null ? "null" : result.toString());
            i = i2 + 1;
        }
    }
}
