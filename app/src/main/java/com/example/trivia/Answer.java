package com.example.trivia;

public class Answer
{
    private final String m_Answer;
    private final boolean m_IsCorrect;

    public Answer(String i_Answer, boolean i_IsCorrect)
    {
        m_Answer = i_Answer;
        m_IsCorrect = i_IsCorrect;
    }

    public String getAnswer()
    {
        return m_Answer;
    }

    public boolean getIsCorrect()
    {
        return m_IsCorrect;
    }
}
