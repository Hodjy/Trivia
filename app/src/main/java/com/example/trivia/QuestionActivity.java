package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.trivia.enums.eSoundsIdentifier;
import com.example.trivia.model.Answer;
import com.example.trivia.model.AppDelayer;
import com.example.trivia.model.GameSessionManager;
import com.example.trivia.model.GameState;
import com.example.trivia.model.Question;
import com.example.trivia.model.SoundManager;
import com.example.trivia.model.difficulty.ADifficulty;

import java.util.ArrayList;
import java.util.Random;

public class QuestionActivity extends AppCompatActivity
{
    //View
    private ImageView m_QuestionIV;
    private ArrayList<AnswerButton> m_AnswerBtns;
    private TextView m_LivesTv;
    private TextView m_ScoreTv;
    private TextView m_TimerCounterTv;
    private ProgressBar m_ProgressBar;
    private Button  m_SoundBtn;

    //Non-View
    private Runnable m_ClockRunnable;
    private int m_SecondsLeft;
    private final int m_ReactionDelaySecs = 2;
    private GameSessionManager m_GameSessionManager;
    private GameState m_GameState;
    private ADifficulty m_Difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        initViewID();
        setClockRunnable();
        setAnswerButtonsListener();
        Bundle bundle = getIntent().getExtras();
        m_Difficulty = (ADifficulty)bundle.getSerializable("Difficulty");
        m_GameSessionManager = new GameSessionManager(m_Difficulty);
        m_GameState = m_GameSessionManager.initGameSession();
        m_ProgressBar.setMax(m_GameSessionManager.getTimeForQuestion());
        setSoundBackground();
        m_SoundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.getInstance().setPlayMusic(!SoundManager.getInstance().isPlayMusic());

                setSoundBackground();
            }
        });

        continueGame();
    }

    @Override
    protected void onPause() {
        AppDelayer.ClearAllRunnables();
        finish();
        super.onPause();
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
        m_TimerCounterTv = findViewById(R.id.question_activity_timerTv);
        m_ProgressBar = findViewById(R.id.question_activity_progress_bar);
        m_SoundBtn = findViewById(R.id.question_activity_sound_btn);
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

    /**
     * Unpacks the Question obj and randomly assigns the Answers to the AnswerButtons.
     * IMPORTANT: buttons and answers must be of the same amount. Which we decided to be 4.
     * @param i_Question
     */
    private void setNewQuestion(Question i_Question)
    {
        int randIndex = 0;
        Random rand = new Random();
        ArrayList<Answer> Answers = i_Question.getAnswers();

        Glide.with(this).load(i_Question.getImgUri()).into(m_QuestionIV); //load pic


        for (AnswerButton btn : m_AnswerBtns)
        {
            randIndex = rand.nextInt(Answers.size());
            Answer answer = Answers.get(randIndex);
            btn.setAnswerButton(answer);
            Answers.remove(answer);
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
        playAnswerButtonSound(i_Btn.getIsCorrect());
        enableUserInput(false);

        revealButtonToUser(i_Btn);
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

    /**
     * Will reveal if the answer was correct, if not will reveal the other correct one.
     * @param i_Btn
     */
    private void revealButtonToUser(AnswerButton i_Btn)
    {
        if(!i_Btn.getIsCorrect()) //if not correct, look for the correct one and reveal it.
        {
            for(AnswerButton answerButton : m_AnswerBtns)
            {
                if(answerButton.getIsCorrect())
                {
                    answerButton.showAnswerImage();
                    break;
                }
            }
        }

        i_Btn.showAnswerImage();
    }

    private void playAnswerButtonSound(Boolean i_IsCorrect) {
        if(i_IsCorrect)
        {
            SoundManager.getInstance().playSound(QuestionActivity.this, eSoundsIdentifier.CORRECT_ANSWER_SOUND);
        }
        else
        {
            SoundManager.getInstance().playSound(QuestionActivity.this, eSoundsIdentifier.INCORRECT_ANSWER_SOUND);
        }
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
        Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
        intent.putExtra("Difficulty", m_Difficulty);

        SharedPreferences sp = getSharedPreferences("match details", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("score", m_ScoreTv.getText().toString());
        editor.commit();

        AppDelayer.ClearAllRunnables();

        startActivity(intent);
        finish();
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
                setTimeLeftUI(m_SecondsLeft);

                if (m_SecondsLeft <= 5 && m_SecondsLeft > 0)
                {
                    SoundManager.getInstance().playSound(QuestionActivity.this, eSoundsIdentifier.TIME_TICKING_SOUND);
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
        m_SecondsLeft = m_GameSessionManager.getTimeForQuestion();
        setTimeLeftUI(m_SecondsLeft);
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

    private void setTimeLeftUI(int i_Seconds)
    {
        m_TimerCounterTv.setText(i_Seconds + "");
        m_ProgressBar.setProgress(i_Seconds);
    }

    private void setSoundBackground()
    {
        if(SoundManager.getInstance().isPlayMusic())
            m_SoundBtn.setBackground(getResources().getDrawable(R.drawable.ic_outline_volume_up, ApplicationContext.getContext().getTheme()));
        else
            m_SoundBtn.setBackground(getResources().getDrawable(R.drawable.ic_baseline_volume_off_24, ApplicationContext.getContext().getTheme()));
    }
}