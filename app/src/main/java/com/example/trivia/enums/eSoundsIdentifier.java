package com.example.trivia.enums;

import com.example.trivia.R;

public enum eSoundsIdentifier {
    BTN_CLICK_SOUND(R.raw.btn_click_sound),
    TIME_TICKING_SOUND(R.raw.time_ticking_sound),
    CORRECT_ANSWER_SOUND(R.raw.correct_answer_sound),
    INCORRECT_ANSWER_SOUND(R.raw.incorrect_answer_sound),
    SCORE_UP_SOUND(R.raw.score_up_sound),
    END_GAME_SOUND(R.raw.end_game_sound);

    public final int id;

    private eSoundsIdentifier(int id) {
        this.id = id;
    }
}
