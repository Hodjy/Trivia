package com.example.trivia.model;

/**
 * This class encapsulates all the data related to the game session, in order to send it to the
 * View.
 */
public class GameState
{
    private Question m_CurrentQuestion;
    private int m_CurrentScore;
    private int m_CurrentLife;
    private boolean m_IsGameRunning;

    public GameState(Question i_CurrentQuestion, int i_CurrentScore,
                     int i_CurrentLife, boolean i_IsGameRunning)
    {
        m_CurrentQuestion = i_CurrentQuestion;
        m_CurrentScore = i_CurrentScore;
        m_CurrentLife = i_CurrentLife;
        m_IsGameRunning = i_IsGameRunning;
    }

    public Question getCurrentQuestion()
    {
        return m_CurrentQuestion;
    }

    public void setCurrentQuestion(Question i_CurrentQuestion)
    {
        this.m_CurrentQuestion = i_CurrentQuestion;
    }

    public int getCurrentScore()
    {
        return m_CurrentScore;
    }

    public void setCurrentScore(int i_CurrentScore)
    {
        this.m_CurrentScore = i_CurrentScore;
    }

    public int getCurrentLife()
    {
        return m_CurrentLife;
    }

    public void setCurrentLife(int i_CurrentLife)
    {
        this.m_CurrentLife = i_CurrentLife;
    }

    public boolean get_IsGameRunning() {
        return m_IsGameRunning;
    }

    public void set_IsGameRunning(boolean i_IsGameRunning) {
        this.m_IsGameRunning = i_IsGameRunning;
    }
}
