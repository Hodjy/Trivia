package com.example.trivia.model;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.trivia.R;
import com.example.trivia.model.save.UserScore;

import java.util.ArrayList;

public class Leaderboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        Button backBtn = findViewById(R.id.leaderboard_activity_backBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ListView userScoreList = findViewById(R.id.leaderboard_activity_userScoreList);

        ArrayList<UserScore> usersScores = new ArrayList<>();

        //TODO extract users scores from file
        //usersScores =

        UserScoreAdapter userScoreAdapter = new UserScoreAdapter(usersScores);

        userScoreList.setAdapter(userScoreAdapter);
    }
}
