package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

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
        setHighestScore();

        m_SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO save on file

                m_SaveBtn.setText("Saved");
                m_SaveBtn.setEnabled(false);
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
                //TODO open score board intent
            }
        });

        m_PlayAgainIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, QuestionActivity.class);

                //TODO PUT EXTRA DIFFICULTY

                startActivity(intent);
                finish();
            }
        });

    }

    private void initViewID(){

        m_ResultTv = findViewById(R.id.result_activity_resultTv);
        m_YourScoreTv = findViewById(R.id.result_activity_playerScoreTv);
        m_HighestScoreTv = findViewById(R.id.result_activity_highestScoreTv);
        m_EnterNameEd = findViewById(R.id.result_activity_enterNameEd);
        m_SaveBtn = findViewById(R.id.result_activity_saveBtn);
        m_HomeIB = findViewById(R.id.result_activity_homeIB);
        m_LeaderBoardIB = findViewById(R.id.result_activity_leaderboardIB);
        m_PlayAgainIB = findViewById(R.id.result_activity_playAgainIB);
    }

    private void setPlayerScore(){
        //TODO shared preference extract score

        //m_YourScoreTv.setText(m_YourScoreTv.getText().toString() + " " +  );
    }

    private void setHighestScore(){
        // TODO extract from file highest score

        //m_HighestScoreTv.setText(m_HighestScoreTv.getText().toString() + " " +  );
    }
}