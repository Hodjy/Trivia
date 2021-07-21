package com.example.trivia.model.save;

import java.io.Serializable;

public class UserScore implements Serializable
{
    //TODO probably needs to store difficulty.
    private String m_UserName;
    private int m_Score;

    public UserScore()
    {
        m_UserName = "";
        m_Score = 0;
    }

    public UserScore(String i_UserName, int i_Score)
    {
        m_UserName = i_UserName;
        m_Score = i_Score;
    }

    public void setUserName(String i_UserName) {
        m_UserName = i_UserName;
    }

    public void setScore(int i_Score) {
        m_Score = i_Score;
    }

    public String getUserName() {
        return m_UserName;
    }

    public int getScore() {
        return m_Score;
    }
}
