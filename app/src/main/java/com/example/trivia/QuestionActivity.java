package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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

public class QuestionActivity extends AppCompatActivity
{
    //View
    private ImageView m_QuestionIV;
    private ArrayList<AnswerButton> m_AnswerBtns;
    private TextView m_LivesTv;
    private TextView m_ScoreTv;
    private TextView m_TimerCounterTv;

    //Non-View
    private Runnable m_ClockRunnable;
    private int m_SecondsLeft;
    private final int m_QuestionSeconds = 15;
    private final int m_ReactionDelaySecs = 2;
    private GameSessionManager m_GameSessionManager;
    private GameState m_GameState;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        initViewID();
        setClockRunnable();
        setAnswerButtonsListener();

        //TODO remove this and get Questions externally (either intent or something else)
        QuestionDataBase questionDataBase = new QuestionDataBase();
        m_GameSessionManager = new GameSessionManager(questionDataBase.getAllQuestions(
                                    getApplicationContext()).get("hard"));
        m_GameState = m_GameSessionManager.initGameSession();

        continueGame();
    }

    private void initViewID()
    {
        m_AnswerBtns = new ArrayList<>();

        m_QuestionIV = findViewById(R.id.questionActivity_questionIV);
        m_AnswerBtns.add(findViewById(R.id.questionActivity_btn1));
        m_AnswerBtns.add(findViewById(R.id.questionActivity_btn2));
        m_AnswerBtns.add(findViewById(R.id.questionActivity_btn3));
        m_AnswerBtns.add(findViewById(R.id.questionActivity_btn4));
        m_LivesTv = findViewById(R.id.questionActivity_livesTV);
        m_ScoreTv = findViewById(R.id.questionActivity_scoreTV);
        m_TimerCounterTv = findViewById(R.id.level_counter_time_view);
    }

    private void setAnswerButtonsListener()
    {
        View.OnClickListener answerListener =  new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                answerButtonPressed((AnswerButton)v);
            }
        };

        for (AnswerButton btn : m_AnswerBtns)
        {
            btn.setOnClickListener(answerListener);
        }
    }

    private void enableUserInput(Boolean i_IsEnabled)
    {
        for (AnswerButton btn : m_AnswerBtns)
        {
            btn.setClickable(i_IsEnabled);
        }
    }


    private void setNewQuestion(Question i_Question)
    {
        ArrayList<Answer> Answers = i_Question.getAnswers();
        //TODO check if works correctly
        Glide.with(this).load(i_Question.getImgUri()).into(m_QuestionIV);
        //TODO make it random

        int i = 0;
        for (AnswerButton btn : m_AnswerBtns)
        {
            btn.setAnswerButton(Answers.get(i));
            i++;
        }
    }

    /**
     * If user input is enabled, will check the Users answer and properly progress in game.
     * Stops the timer, updates the GameSessionManager then the health and score. After that
     * Delay and decides if to end the game or continue.
     * @param i_Btn
     */
    private void answerButtonPressed(AnswerButton i_Btn)
    {
        enableUserInput(false);

        i_Btn.showAnswerImage();
        pauseClockRunnable();
        m_GameState = m_GameSessionManager.answerPressed(i_Btn.getIsCorrect(), m_SecondsLeft);
        updateLivesAndScore();

        Runnable runnable = new Runnable()
        {
            @Override
            public void run()
            {
                checkGameStateAndProgress();
            }
        };

        AppDelayer.DelayApp(m_ReactionDelaySecs, runnable);
    }


    private void checkGameStateAndProgress()
    {
        if(!m_GameState.get_IsGameRunning())
        {
            endGame();
        }
        else
        {
            continueGame();
        }
    }

    private void updateLivesAndScore()
    {
        m_LivesTv.setText(m_GameState.getCurrentLife() + "");
        m_ScoreTv.setText(m_GameState.getCurrentScore() + "");
    }


    private void endGame()
    {

        //m_TimerCounterTv.setBackground(getResources().getDrawable(R.drawable.timer_backgraund, this.getApplication().getTheme()));
        Toast.makeText(this, "Game Ended", Toast.LENGTH_LONG).show();
    }

    private void continueGame()
    {
        setNewQuestion(m_GameState.getCurrentQuestion());
        updateLivesAndScore();
        resetAndStartQuestionTimer();
        enableUserInput(true);
    }

    /**
     * Creates a Runnable that implements a method which
     * decreases m_SecondsLeft by 1 for every second, needs to be called with a handler.
     */
    private void setClockRunnable()
    {
        int ticksBySeconds = 1;
        m_ClockRunnable = new Runnable()
        {
            public void run()
            {
                m_SecondsLeft--;
                setTimerView(m_SecondsLeft);

                if (m_SecondsLeft <= 5 && m_SecondsLeft > 0)
                {
                    //TimerTickingSound();
                    //TODO ANIMATION / EFFECT
                    //if(m_SecondsLeft ==5)
                    // TimeTikcingEffect();
                }

                // If theres time left keep running, else stop.
                if (m_SecondsLeft > 0)
                {
                    AppDelayer.DelayApp(ticksBySeconds, this);
                }
                else
                {
                    //TimeUpSound
                    timeUp();
                    //m_TimeUpDialog.show();
                }
            }
        };
    }

    private void startClockRunnable()
    {
        AppDelayer.Post(m_ClockRunnable);
    }
//    private void TimeTikcingEffect()
//    {
//        new CountDownTimer(5000, 500) {
//            int count = 0;
//            @Override
//            public void onTick(long millisUntilFinished) {
//                if(count == 0) {
//                    m_TimerCounterTv.setBackground(getResources().getDrawable(R.drawable.timer_red_backgraund, QuestionActivity.this.getApplication().getTheme()));
//                    count = 1;
//                } else {
//                    m_TimerCounterTv.setBackground(getResources().getDrawable(R.drawable.timer_red_stroke,QuestionActivity.this.getApplication().getTheme()));
//                    count = 0;
//                }
//            }
//            @Override
//            public void onFinish() {
//                m_TimerCounterTv.setBackground(getResources().getDrawable(R.drawable.timer_backgraund, QuestionActivity.this.getApplication().getTheme()));
//            }
//        }.start();
//    }

    public void resetAndStartQuestionTimer()
    {
        m_SecondsLeft = m_QuestionSeconds;
        setTimerView(m_SecondsLeft);
        resumeClockRunnable();
    }

    public void pauseClockRunnable(){
        AppDelayer.RemoveCallbacks(m_ClockRunnable);
    }

    public void resumeClockRunnable(){
        AppDelayer.Post(m_ClockRunnable);
    }

    private void timeUp()
    {
        Toast.makeText(this, "Time Up!!!!!", Toast.LENGTH_SHORT).show();
        pauseClockRunnable();
        m_GameState = m_GameSessionManager.timeUp();
        updateLivesAndScore();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                checkGameStateAndProgress();
            }
        };

        AppDelayer.DelayApp(m_ReactionDelaySecs, runnable);
    }

    private void setTimerView(int i_Seconds)
    {
        m_TimerCounterTv.setText(i_Seconds + "");
    }
}