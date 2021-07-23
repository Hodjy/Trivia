package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.trivia.enums.eSoundsIdentifier;
import com.example.trivia.model.Foliage;
import com.example.trivia.model.FoliageAdapter;
import com.example.trivia.model.FoliageDatabase;
import com.example.trivia.model.SoundManager;
import com.example.trivia.model.UserScoreAdapter;
import com.example.trivia.model.difficulty.DifficultyEasy;
import com.example.trivia.model.difficulty.DifficultyHard;
import com.example.trivia.model.difficulty.DifficultyMedium;
import com.example.trivia.model.save.SaveManager;
import com.example.trivia.model.save.UserScore;

import java.util.ArrayList;
import java.util.Hashtable;

public class EncyclopediaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encyclopedia);

        Button backBtn = findViewById(R.id.encyclopedia_activity_backBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.getInstance().playMainSound(EncyclopediaActivity.this, eSoundsIdentifier.BTN_CLICK_SOUND);
                finish();
            }
        });

        RecyclerView foliageRecyclerView = findViewById(R.id.encyclopedia_activity_foliageList);
        foliageRecyclerView.setHasFixedSize(true);
        foliageRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Foliage> foliages = new ArrayList<>();

        Hashtable<String, ArrayList<Foliage>> foliagesHash = FoliageDatabase.getFoliages();

        foliages.addAll(foliagesHash.get((new DifficultyEasy().getDifficulty())));
        foliages.addAll(foliagesHash.get((new DifficultyMedium().getDifficulty())));
        foliages.addAll(foliagesHash.get((new DifficultyHard().getDifficulty())));

        FoliageAdapter foliageAdapter = new FoliageAdapter(foliages);
        foliageRecyclerView.setAdapter(foliageAdapter);
    }
}