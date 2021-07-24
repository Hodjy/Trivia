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

    private Button m_BackBtn;
    private Button m_SoundBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encyclopedia);


        m_BackBtn = findViewById(R.id.encyclopedia_activity_backBtn);
        m_BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.getInstance().playMainSound(EncyclopediaActivity.this, eSoundsIdentifier.BTN_CLICK_SOUND);
                finish();
            }
        });

        m_SoundBtn = findViewById(R.id.encyclopedia_activity_sound_btn);
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

    @Override
    protected void onPause() {
        super.onPause();
        SoundManager.getInstance().pauseBackgroundSound();
    }

    @Override
    protected void onResume(){
        super.onResume();

        SoundManager.getInstance().onActivityResume(EncyclopediaActivity.this, eSoundsIdentifier.ENCYCLOPEDIA_MUSIC,
                m_SoundBtn, getResources().getDrawable(R.drawable.ic_outline_volume_up, ApplicationContext.getContext().getTheme()),
                getResources().getDrawable(R.drawable.ic_baseline_volume_off_24, ApplicationContext.getContext().getTheme()));

    }
}