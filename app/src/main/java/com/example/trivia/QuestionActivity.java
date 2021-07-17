package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity
{
    private ImageView m_QuestionIV;
    private AnswerButton m_Btn1;
    private AnswerButton m_Btn2;
    private AnswerButton m_Btn3;
    private AnswerButton m_Btn4;

    private ArrayList<Question> m_Questions;
    private int m_Lives = 3;
    private int m_CurrentQuestion = 0;
    private int m_Score = 0;

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

        View.OnClickListener answerListener =  new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                answerPressed((AnswerButton)v);
            }
        };

        m_Btn1.setOnClickListener(answerListener);
        m_Btn2.setOnClickListener(answerListener);
        m_Btn3.setOnClickListener(answerListener);
        m_Btn4.setOnClickListener(answerListener);

        startGameSession();
    }

    private void startGameSession()
    {
        //TODO play start animation
        nextQuestion();
    }

    private void nextQuestion()
    {
        calculateScore();

        if((m_CurrentQuestion + 1) <=  m_Questions.size())
        {
            //TODO animation for next question
            setNewQuestion(m_Questions.get(m_CurrentQuestion));
            m_CurrentQuestion++;
        }
        else
        {
            endGame(true);
        }
    }

    private void calculateScore()
    {
        m_Score++;
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

    private void loseLifeAndProceed()
    {
        m_Lives -=1;

        if (m_Lives == 0)
        {
            //TODO animation for HP loss + delay
            endGame(false);
        }
        else
        {
            nextQuestion();
        }
    }

    private void timeUp()
    {
        endGame(false);
    }

    private void endGame(boolean i_IsAWin)
    {
        if(i_IsAWin)
        {
            //win
        }
        else
        {
            //lose, check if because of time or lives and send corresponding message.
        }
    }



    private void answerPressed(AnswerButton i_PressedButton)
    {
        if(i_PressedButton.getIsCorrect())
        {
            //play Correct animation and sound
            //lock timer and UI
            //delay
            nextQuestion();
        }
        else
        {
            //play InCorrect animation and sound
            //lock timer and UI
            //delay
            loseLifeAndProceed();
        }
    }
}