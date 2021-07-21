package com.example.trivia.model.difficulty;

import com.example.trivia.enums.eDifficulty;

public class DifficultyHard implements IDifficulty
{
    private final eDifficulty m_Difficulty = eDifficulty.HARD;
    private final int m_TimeForQuestion = 15;

    @Override
    public eDifficulty getDifficulty() {
        return m_Difficulty;
    }

    @Override
    public int getTimeForQuestion() {
        return m_TimeForQuestion;
    }
}
