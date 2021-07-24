package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.trivia.R;
import com.example.trivia.enums.eSoundsIdentifier;
import com.example.trivia.model.SoundManager;
import com.example.trivia.model.UserScoreAdapter;
import com.example.trivia.model.save.SaveManager;
import com.example.trivia.model.save.UserScore;

import java.util.ArrayList;

public class LeaderboardActivity extends AppCompatActivity {

    private Button m_BackBtn;
    private Button m_SoundBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        m_BackBtn = findViewById(R.id.leaderboard_activity_backBtn);
        m_BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.getInstance().playMainSound(LeaderboardActivity.this, eSoundsIdentifier.BTN_CLICK_SOUND);
                finish();
            }
        });

        m_SoundBtn = findViewById(R.id.leaderboard_activity_sound_btn);
        m_SoundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SoundManager.getInstance().isPlayMusic())
                {
                    SoundManager.getInstance().pauseBackgroundSound();
                    m_SoundBtn.setBackground(getResources().getDrawable(R.drawable.ic_baseline_volume_off_24, ApplicationContext.getContext().getTheme()));
                }
                else
                {
                    SoundManager.getInstance().resumeBackgroundSound();
                    m_SoundBtn.setBackground(getResources().getDrawable(R.drawable.ic_outline_volume_up, ApplicationContext.getContext().getTheme()));
                }

                SoundManager.getInstance().setPlayMusic(!SoundManager.getInstance().isPlayMusic());
            }
        });

        ListView userScoreList = findViewById(R.id.leaderboard_activity_userScoreList);

        ArrayList<UserScore> usersScores;

        usersScores = SaveManager.LoadFromFile();

        if(usersScores != null)
        {
            UserScoreAdapter userScoreAdapter = new UserScoreAdapter(usersScores);
            userScoreList.setAdapter(userScoreAdapter);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SoundManager.getInstance().pauseBackgroundSound();
    }

    @Override
    protected void onResume(){
        super.onResume();

        SoundManager.getInstance().onActivityResume(LeaderboardActivity.this, eSoundsIdentifier.LEADERBOARD_MUSIC,
                m_SoundBtn, getResources().getDrawable(R.drawable.ic_outline_volume_up, ApplicationContext.getContext().getTheme()),
                getResources().getDrawable(R.drawable.ic_baseline_volume_off_24, ApplicationContext.getContext().getTheme()));

    }
}
