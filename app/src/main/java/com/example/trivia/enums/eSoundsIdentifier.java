package com.example.trivia.enums;

import com.example.trivia.R;

public enum eSoundsIdentifier {
    BTN_CLICK_SOUND(R.raw.btn_click_sound),
    TIME_TICKING_SOUND(R.raw.time_ticking_sound),
    CORRECT_ANSWER_SOUND(R.raw.correct_answer_sound),
    INCORRECT_ANSWER_SOUND(R.raw.incorrect_answer_sound),
    SCORE_UP_SOUND(R.raw.score_up_sound),
    END_GAME_MUSIC(R.raw.ost_game_over_ambience),
    MAIN_ACTIVITY_MUSIC(R.raw.ost_main_screen_ambience),
    ENCYCLOPEDIA_MUSIC(R.raw.ost_encyclopedia_ambience),
    LEADERBOARD_MUSIC(R.raw.ost_leaderboard_ambience),
    TIME_UP_SOUND(R.raw.time_up_sound);

    public final int id;

    private eSoundsIdentifier(int id) {
        this.id = id;
    }
}
