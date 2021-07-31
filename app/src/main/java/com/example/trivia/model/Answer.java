package com.example.trivia.model;

public class Answer
{
    private final String m_AnswerText;
    private final boolean m_IsCorrect;

    public Answer(String i_Answer, boolean i_IsCorrect)
    {
        m_AnswerText = i_Answer;
        m_IsCorrect = i_IsCorrect;
    }

    public String getAnswerText()
    {
        return m_AnswerText;
    }

    public boolean getIsCorrect()
    {
        return m_IsCorrect;
    }
}
