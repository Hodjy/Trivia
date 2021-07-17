package com.example.trivia.model;

import android.net.Uri;

import java.util.ArrayList;

public class Question
{
    private final Uri m_ImgUri;
    private final ArrayList<Answer> m_Answers;

    //TODO make sure user can only insert 1 correct and 3 false questions. And only that.
    public Question(Uri i_ImgUri, Answer i_Answer1,Answer i_Answer2,
                    Answer i_Answer3,Answer i_Answer4)
    {
        m_ImgUri = i_ImgUri;
        m_Answers = new ArrayList<Answer>();
        m_Answers.add(i_Answer1);
        m_Answers.add(i_Answer2);
        m_Answers.add(i_Answer3);
        m_Answers.add(i_Answer4);
    }

    public Uri getImgUri()
    {
        return m_ImgUri;
    }

    public ArrayList<Answer> getAnswers()
    {
        return  m_Answers;
    }
}
