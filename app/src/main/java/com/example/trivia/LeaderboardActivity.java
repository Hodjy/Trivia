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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        Button backBtn = findViewById(R.id.leaderboard_activity_backBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.getInstance().playMainSound(LeaderboardActivity.this, eSoundsIdentifier.BTN_CLICK_SOUND);
                finish();
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
}
