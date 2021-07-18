package com.example.trivia.enums;

import com.example.trivia.R;

public enum eSoundsIdentifier {
    BTN_CLICK_SOUND(R.raw.btn_click_sound),
    TIME_TICKING_SOUND(R.raw.time_ticking_sound);

    public final int id;

    private eSoundsIdentifier(int id) {
        this.id = id;
    }
}
