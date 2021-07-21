package com.example.trivia.model.difficulty;

import com.example.trivia.enums.eDifficulty;

public class DifficultyEasy implements IDifficulty
{
    private final eDifficulty m_Difficulty = eDifficulty.EASY;
    private final int m_TimeForQuestion = 30;
    @Override
    public eDifficulty getDifficulty() {
        return m_Difficulty;
    }

    @Override
    public int getTimeForQuestion() {
        return m_TimeForQuestion;
    }
}
