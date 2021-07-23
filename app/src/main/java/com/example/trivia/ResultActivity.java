package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trivia.enums.eSoundsIdentifier;
import com.example.trivia.model.SoundManager;
import com.example.trivia.model.difficulty.ADifficulty;
import com.example.trivia.model.save.SaveManager;
import com.example.trivia.model.save.UserScore;

public class ResultActivity extends AppCompatActivity {

    private TextView m_ResultTv;
    private TextView m_YourScoreTv;
    private TextView m_PlayerScoreTv;
    private EditText m_EnterNameEd;
    private Button m_SaveBtn;
    private ImageButton m_HomeIB;
    private ImageButton m_LeaderBoardIB;
    private ImageButton m_PlayAgainIB;
    private Button m_SoundBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        initViewID();
        setPlayerScore();

        m_SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.getInstance().playMainSound(ResultActivity.this, eSoundsIdentifier.BTN_CLICK_SOUND);
                String userName = m_EnterNameEd.getText().toString();

                if(userName.equals(""))
                {
                    Toast.makeText(ResultActivity.this, getResources().getString(R.string.result_activity_please_enter_name),
                            Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //TODO save on file and make strings not HARD-CODED

                    SaveManager.AddUserScoreToFile(new UserScore(userName, Integer.parseInt(m_PlayerScoreTv.getText().toString())));

                    m_SaveBtn.setText(getResources().getString(R.string.result_activity_saved));
                    m_SaveBtn.setEnabled(false);
                }
            }
        });

        m_HomeIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.getInstance().playMainSound(ResultActivity.this, eSoundsIdentifier.BTN_CLICK_SOUND);
                finish();
            }
        });

        m_LeaderBoardIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.getInstance().playMainSound(ResultActivity.this, eSoundsIdentifier.BTN_CLICK_SOUND);
                Intent intent = new Intent(ResultActivity.this, LeaderboardActivity.class);
                startActivity(intent);
            }
        });

        m_PlayAgainIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.getInstance().playMainSound(ResultActivity.this, eSoundsIdentifier.BTN_CLICK_SOUND);
                Intent intent = new Intent(ResultActivity.this, QuestionActivity.class);

                Bundle bundle = getIntent().getExtras();
                ADifficulty difficulty = (ADifficulty)bundle.getSerializable("Difficulty");
                intent.putExtra("Difficulty", difficulty);

                startActivity(intent);
                finish();
            }
        });

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
    }

    private void initViewID(){

        m_ResultTv = findViewById(R.id.result_activity_resultTv);
        m_PlayerScoreTv = findViewById(R.id.result_activity_playerScoreTv);
        m_YourScoreTv = findViewById(R.id.result_activity_yourScoreTv);
        m_EnterNameEd = findViewById(R.id.result_activity_enterNameEd);
        m_SaveBtn = findViewById(R.id.result_activity_saveBtn);
        m_HomeIB = findViewById(R.id.result_activity_homeIB);
        m_LeaderBoardIB = findViewById(R.id.result_activity_leaderboardIB);
        m_PlayAgainIB = findViewById(R.id.result_activity_playAgainIB);
        m_SoundBtn = findViewById(R.id.result_activity_sound_btn);
    }

    private void setPlayerScore(){

        SharedPreferences sp = getSharedPreferences("match details", MODE_PRIVATE);
        m_PlayerScoreTv.setText(sp.getString("score","0"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        SoundManager.getInstance().pauseBackgroundSound();
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(SoundManager.getInstance().isPlayMusic())
        {
            SoundManager.getInstance().playBackgroundSound(ResultActivity.this, eSoundsIdentifier.MAIN_ACTIVITY_MUSIC);
            m_SoundBtn.setBackground(getResources().getDrawable(R.drawable.ic_outline_volume_up, ApplicationContext.getContext().getTheme()));
        }
        else
        {
            SoundManager.getInstance().setPlayMusic(true);
            SoundManager.getInstance().playBackgroundSound(ResultActivity.this, eSoundsIdentifier.MAIN_ACTIVITY_MUSIC);
            SoundManager.getInstance().pauseBackgroundSound();
            m_SoundBtn.setBackground(getResources().getDrawable(R.drawable.ic_baseline_volume_off_24, ApplicationContext.getContext().getTheme()));
        }
    }
}