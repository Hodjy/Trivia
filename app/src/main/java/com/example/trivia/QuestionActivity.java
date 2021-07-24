package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.trivia.enums.eSoundsIdentifier;
import com.example.trivia.model.Answer;
import com.example.trivia.model.AppHandler;
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
    private TextView m_ScoreTv;
    private TextView m_TimerCounterTv;
    private ProgressBar m_ProgressBar;
    private Button m_SoundBtn;
    private ArrayList<ImageView> m_LivesIV;
    private RelativeLayout m_LivesContainerRL;

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

        Bundle bundle = getIntent().getExtras();
        m_Difficulty = (ADifficulty)bundle.getSerializable("Difficulty");
        m_GameSessionManager = new GameSessionManager(m_Difficulty);

        initViewID();
        setClockRunnable();
        setAnswerButtonsListener();

        m_GameState = m_GameSessionManager.initGameSession();
        m_ProgressBar.setMax(m_GameSessionManager.getTimeForQuestion());
        setSoundBackground();

        m_SoundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.getInstance().setPlayMusic(!SoundManager.getInstance().isPlayMusic());

                if(SoundManager.getInstance().isPlayMusic())
                    SoundManager.getInstance().resumeBackgroundSound();
                else
                    SoundManager.getInstance().pauseBackgroundSound();
                setSoundBackground();
            }
        });

        continueGame();

        SoundManager.getInstance().playBackgroundSound(QuestionActivity.this, eSoundsIdentifier.MAIN_ACTIVITY_MUSIC);
    }

    @Override
    protected void onPause() {
        AppHandler.ClearAllRunnables();
        SoundManager.getInstance().pauseBackgroundSound();
        finish();
        super.onPause();
    }

    private void initViewID()
    {
        m_AnswerBtns = new ArrayList<>();
        m_LivesIV = new ArrayList<>();
        m_QuestionIV = findViewById(R.id.questionActivity_questionIV);
        m_AnswerBtns.add(findViewById(R.id.questionActivity_btn1));
        m_AnswerBtns.add(findViewById(R.id.questionActivity_btn2));
        m_AnswerBtns.add(findViewById(R.id.questionActivity_btn3));
        m_AnswerBtns.add(findViewById(R.id.questionActivity_btn4));
        m_LivesIV.add(findViewById(R.id.question_activity_heartIv1));
        m_LivesIV.add(findViewById(R.id.question_activity_heartIv2));
        m_LivesIV.add(findViewById(R.id.question_activity_heartIv3));
        m_ScoreTv = findViewById(R.id.questionActivity_scoreTV);
        m_TimerCounterTv = findViewById(R.id.question_activity_timerTv);
        m_ProgressBar = findViewById(R.id.question_activity_progress_bar);
        m_SoundBtn = findViewById(R.id.question_activity_sound_btn);
        m_LivesContainerRL = findViewById(R.id.question_activity_hearts_containerRL);
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

        loadNewImageAndAnimate(this, i_Question.getImgUri());
        //Glide.with(this).load(i_Question.getImgUri()).into(m_QuestionIV); //load pic

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
        enableUserInput(false);
        playAnswerButton(i_Btn);
        revealButtonToUser(i_Btn);
        removeHeart(i_Btn.getIsCorrect());
        pauseClockRunnable();
        m_GameState = m_GameSessionManager.answerPressed(i_Btn.getIsCorrect());
        updateScore(i_Btn.getIsCorrect());

        Runnable runnable = () -> checkGameStateAndProgress();

        AppHandler.DelayApp(m_ReactionDelaySecs, runnable);
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

    /**
     * Plays animation and sound accordingly to correct or incorrect.
     * @param i_Btn Pressed button.
     */
    private void playAnswerButton(AnswerButton i_Btn)
    {
        if(i_Btn.getIsCorrect())
        {
            SoundManager.getInstance().playMainSound(QuestionActivity.this, eSoundsIdentifier.CORRECT_ANSWER_SOUND);
            YoYo.with(Techniques.Pulse)
                    .duration(200)
                    .playOn(i_Btn);
        }
        else
        {
            SoundManager.getInstance().playMainSound(QuestionActivity.this, eSoundsIdentifier.INCORRECT_ANSWER_SOUND);
            YoYo.with(Techniques.Shake)
                    .duration(200)
                    .playOn(i_Btn);
        }
    }

    private void loadNewImageAndAnimate( Context i_Context, Uri i_ImageUri)
    {
        long animationDuration = 200;
        long animationDelay = 600;

        YoYo.AnimatorCallback animatorCallback = new YoYo.AnimatorCallback() {
            @Override
            public void call(Animator animator) {
                Glide.with(i_Context)
                        .load(i_ImageUri)
                        .dontAnimate()
                        .skipMemoryCache(true)
                        .into(m_QuestionIV); //load pic

                YoYo.with(Techniques.FadeIn)
                        .duration(1)
                        .delay(animationDelay)
                        .playOn(m_QuestionIV);
            }
        };

        YoYo.with(Techniques.FadeOut)
                .duration(animationDuration)
                .onEnd(animatorCallback)
                .playOn(m_QuestionIV);
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

    private void updateScore(boolean i_IsCorrect)
    {
        if(i_IsCorrect)
        {
            m_ScoreTv.setText(m_GameState.getCurrentScore() + "");
            YoYo.with(Techniques.Pulse)
                    .duration(200)
                    .playOn(m_ScoreTv);
        }
    }


    private void endGame()
    {

        //m_TimerCounterTv.setBackground(getResources().getDrawable(R.drawable.timer_backgraund, this.getApplication().getTheme()));
        String gameOver = getString(R.string.game_over);
        Toast.makeText(this, gameOver, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
        intent.putExtra("Difficulty", m_Difficulty);

        SharedPreferences sp = getSharedPreferences("match details", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("score", m_ScoreTv.getText().toString());
        editor.commit();

        AppHandler.ClearAllRunnables();

        startActivity(intent);
        finish();
    }

    private void continueGame()
    {
        setNewQuestion(m_GameState.getCurrentQuestion());
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
                    SoundManager.getInstance().playMainSound(QuestionActivity.this, eSoundsIdentifier.TIME_TICKING_SOUND);
                    //TODO ANIMATION / EFFECT
                    //if(m_SecondsLeft ==5)
                    // TimeTikcingEffect();
                }

                // If theres time left keep running, else stop.
                if (m_SecondsLeft > 0)
                {
                    AppHandler.DelayApp(ticksBySeconds, this);
                }
                else
                {
                    timeUp();
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

    /**
     * If incorrect, will hide a imageview that represents the players health.
     * assumes that list has proper size.
     * @param i_IsCorrect
     */
    public void removeHeart(boolean i_IsCorrect)
    {
        int currentLife = m_GameState.getCurrentLife();

        if(!i_IsCorrect && currentLife > 0)
        {
            YoYo.with(Techniques.Shake)
                    .duration(200)
                    .playOn(m_LivesContainerRL);
            m_LivesIV.get(currentLife - 1).setVisibility(View.INVISIBLE);
        }
    }

    public void pauseClockRunnable(){
        AppHandler.RemoveCallbacks(m_ClockRunnable);
    }

    public void resumeClockRunnable(){
        AppHandler.Post(m_ClockRunnable);
    }

    private void timeUp()
    {
        String timeUp = getString(R.string.time_up);
        removeHeart(false);
        SoundManager.getInstance().playMainSound(QuestionActivity.this, eSoundsIdentifier.TIME_UP_SOUND);
        Toast.makeText(this, timeUp, Toast.LENGTH_SHORT).show();
        pauseClockRunnable();
        m_GameState = m_GameSessionManager.timeUp();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                checkGameStateAndProgress();
            }
        };

        AppHandler.DelayApp(m_ReactionDelaySecs, runnable);
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