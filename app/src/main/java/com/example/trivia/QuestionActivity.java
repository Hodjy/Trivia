package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.trivia.model.Answer;
import com.example.trivia.model.GameSessionManager;
import com.example.trivia.model.GameState;
import com.example.trivia.model.Question;
import com.example.trivia.model.QuestionDataBase;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class QuestionActivity extends AppCompatActivity
{
    private ImageView m_QuestionIV;
    private AnswerButton m_Btn1;
    private AnswerButton m_Btn2;
    private AnswerButton m_Btn3;
    private AnswerButton m_Btn4;
    private TextView m_LivesTv;
    private TextView m_ScoreTv;

    private TextView m_TimerCounterTv;
    private Runnable m_Timer_Tick;
    private int m_SecondsLeft = 15;
    private final int m_QuestionSecounds = 15;
    private Timer m_gameTimer;

    private GameSessionManager m_GameSessionManager;
    private GameState m_GameState;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        m_QuestionIV = findViewById(R.id.questionActivity_questionIV);
        m_Btn1 = findViewById(R.id.questionActivity_btn1);
        m_Btn2 = findViewById(R.id.questionActivity_btn2);
        m_Btn3 = findViewById(R.id.questionActivity_btn3);
        m_Btn4 = findViewById(R.id.questionActivity_btn4);
        m_LivesTv = findViewById(R.id.questionActivity_livesTV);
        m_ScoreTv = findViewById(R.id.questionActivity_scoreTV);
        m_TimerCounterTv = findViewById(R.id.level_counter_time_view);

        setTimerTick();
        setTimer();

        View.OnClickListener answerListener =  new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int time = 0;
                AnswerButton btn = (AnswerButton)v;

                btn.showAnswerImage();
                m_GameState = m_GameSessionManager.answerPressed(btn.getIsCorrect(), time);
                checkGameStateAndUpdateUI();
            }
        };

        m_Btn1.setOnClickListener(answerListener);
        m_Btn2.setOnClickListener(answerListener);
        m_Btn3.setOnClickListener(answerListener);
        m_Btn4.setOnClickListener(answerListener);

        m_GameSessionManager = new GameSessionManager(QuestionDataBase.getAllQuestions().get("hard"));

        m_GameState = m_GameSessionManager.initGameSession();

        updateUI();
    }

    private void setNewQuestion(Question i_Question)
    {
        ArrayList<Answer> Answers = i_Question.getAnswers();
        //TODO check if works correctly
        Glide.with(this).load(i_Question.getImgUri()).into(m_QuestionIV);
        //TODO make it random
        m_Btn1.setAnswerButton(Answers.get(0));
        m_Btn2.setAnswerButton(Answers.get(1));
        m_Btn3.setAnswerButton(Answers.get(2));
        m_Btn4.setAnswerButton(Answers.get(3));
    }

    private void checkGameStateAndUpdateUI()
    {
        updateUI();

        if(!m_GameState.get_IsGameRunning())
        {
            endGame();
        }
    }

    private void updateUI()
    {
        m_LivesTv.setText(m_GameState.getCurrentLife() + "");
        m_ScoreTv.setText(m_GameState.getCurrentScore() + "");

        if(m_GameState.get_IsGameRunning())
        {
            setNewQuestion(m_GameState.getCurrentQuestion());
        }
    }


    private void endGame()
    {
        m_TimerCounterTv.setBackground(getResources().getDrawable(R.drawable.timer_backgraund, this.getApplication().getTheme()));
        Toast.makeText(this, "Game Ended", Toast.LENGTH_LONG).show();
    }

    private void setTimer() {
        m_gameTimer = new Timer();
        m_gameTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                TimerMethod();
            }

        }, 0, 1000);
    }

    private void setTimerTick() {

        m_Timer_Tick = new Runnable() {
            public void run() {
                m_SecondsLeft--;
                m_TimerCounterTv.setText(m_SecondsLeft +"");

                if(m_SecondsLeft <= 5 && m_SecondsLeft>0)
                {
                    //TimerTickingSound();
                    if(m_SecondsLeft ==5)
                        TimeTikcingEffect();
                }

                if(m_SecondsLeft == 0) {
                    //TimeUpSound();
                    m_gameTimer.cancel();
                    endGame();
                    //m_lastRoundScore = m_Compleate_counter;
                    //m_TimeUpDialog.show();
                }
            }
        };
    }

    //@SuppressLint("WrongConstant")
    private void TimeTikcingEffect()
    {
        new CountDownTimer(5000, 500) {
            int count = 0;
            @Override
            public void onTick(long millisUntilFinished) {
                if(count == 0) {
                    m_TimerCounterTv.setBackground(getResources().getDrawable(R.drawable.timer_red_backgraund, QuestionActivity.this.getApplication().getTheme()));
                    count = 1;
                } else {
                    m_TimerCounterTv.setBackground(getResources().getDrawable(R.drawable.timer_red_stroke,QuestionActivity.this.getApplication().getTheme()));
                    count = 0;
                }
            }
            @Override
            public void onFinish() {
                m_TimerCounterTv.setBackground(getResources().getDrawable(R.drawable.timer_backgraund, QuestionActivity.this.getApplication().getTheme()));
            }
        }.start();
    }

    private void TimerMethod()
    {
        this.runOnUiThread(m_Timer_Tick);
    }

    public void resetQuestionTimer()
    {
        m_SecondsLeft = m_QuestionSecounds;
    }
}