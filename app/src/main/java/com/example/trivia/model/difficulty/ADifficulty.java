package com.example.trivia.model.difficulty;

import java.io.Serializable;

public abstract class ADifficulty implements Serializable
{
    protected String m_Difficulty;
    protected int m_TimeForQuestion;

    public String getDifficulty()
    {
        return m_Difficulty;
    }

    public int getTimeForQuestion()
    {
        return m_TimeForQuestion;
    }
}
