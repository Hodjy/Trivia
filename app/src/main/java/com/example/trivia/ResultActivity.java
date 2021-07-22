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

import com.example.trivia.model.difficulty.ADifficulty;

public class ResultActivity extends AppCompatActivity {

    private TextView m_ResultTv;
    private TextView m_YourScoreTv;
    private TextView m_HighestScoreTv;
    private EditText m_EnterNameEd;
    private Button m_SaveBtn;
    private ImageButton m_HomeIB;
    private ImageButton m_LeaderBoardIB;
    private ImageButton m_PlayAgainIB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        initViewID();
        setPlayerScore();


        m_SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(m_EnterNameEd.getText().toString().equals(""))
                {
                    Toast.makeText(ResultActivity.this, getResources().getString(R.string.result_activity_please_enter_name),
                            Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //TODO save on file and make strings not HARD-CODED

                    m_SaveBtn.setText(getResources().getString(R.string.result_activity_saved));
                    m_SaveBtn.setEnabled(false);
                }
            }
        });

        m_HomeIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        m_LeaderBoardIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ResultActivity.this, LeaderboardActivity.class);
                startActivity(intent);
            }
        });

        m_PlayAgainIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ResultActivity.this, QuestionActivity.class);

                Bundle bundle = getIntent().getExtras();
                ADifficulty difficulty = (ADifficulty)bundle.getSerializable("Difficulty");
                intent.putExtra("Difficulty", difficulty);

                startActivity(intent);
                finish();
            }
        });

    }

    private void initViewID(){

        m_ResultTv = findViewById(R.id.result_activity_resultTv);
        m_YourScoreTv = findViewById(R.id.result_activity_playerScoreTv);
        m_EnterNameEd = findViewById(R.id.result_activity_enterNameEd);
        m_SaveBtn = findViewById(R.id.result_activity_saveBtn);
        m_HomeIB = findViewById(R.id.result_activity_homeIB);
        m_LeaderBoardIB = findViewById(R.id.result_activity_leaderboardIB);
        m_PlayAgainIB = findViewById(R.id.result_activity_playAgainIB);
    }

    private void setPlayerScore(){

        SharedPreferences sp = getSharedPreferences("match details", MODE_PRIVATE);
        m_YourScoreTv.setText(m_YourScoreTv.getText().toString() + " " + sp.getString("score","") );
    }
}