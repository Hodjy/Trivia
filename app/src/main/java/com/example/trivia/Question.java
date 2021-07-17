package com.example.trivia;

import java.util.ArrayList;

public class Question {
    private final String m_ImgSrc;
    private final ArrayList<Answer> m_Answers;

    //TODO make sure user can only insert 1 correct and 3 false questions. And only that.
    public Question(String i_ImgSrc, Answer i_Answer1,Answer i_Answer2,
                    Answer i_Answer3,Answer i_Answer4) {
        m_ImgSrc = i_ImgSrc;
        m_Answers = new ArrayList<Answer>();
        m_Answers.add(i_Answer1);
        m_Answers.add(i_Answer2);
        m_Answers.add(i_Answer3);
        m_Answers.add(i_Answer4);
    }

    public String GetImgSrc()
    {
        return m_ImgSrc;
    }

    public ArrayList<Answer> GetAnswers()
    {
        return  m_Answers;
    }
}
