package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.trivia.model.Answer;
import com.example.trivia.model.GameSessionManager;
import com.example.trivia.model.GameState;
import com.example.trivia.model.Question;

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

        m_Questions = new ArrayList<>();

        View.OnClickListener answerListener =  new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int time = 0;
                m_GameState = m_GameSessionManager.answerPressed(((AnswerButton)v).getIsCorrect(), time);
            }
        };

        m_Btn1.setOnClickListener(answerListener);
        m_Btn2.setOnClickListener(answerListener);
        m_Btn3.setOnClickListener(answerListener);
        m_Btn4.setOnClickListener(answerListener);

        Question question = new Question(Uri.parse("android.resource://com.example.trivia/drawable/correct_icon"),
                new Answer("Aba", false),
                new Answer("Aba", false),
                new Answer("Aba", false),
                new Answer("nahon", true));

        m_Questions.add(question);
        m_GameSessionManager = new GameSessionManager(m_Questions);

        m_GameState = m_GameSessionManager.initGameSession();

        setNewQuestion(question);
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

    /*
        private GameState nextQuestion()
    {
        calculateScore();

        if((m_CurrentQuestionCounter + 1) <=  m_Questions.size())
        {
            //TODO animation for next question
            setNewQuestion(m_Questions.get(m_CurrentQuestionCounter));
            m_CurrentQuestionCounter++;
        }
        else
        {
            endGame(true);
        }
    }
     */
}