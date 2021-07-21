package com.example.trivia.model.difficulty;

import com.example.trivia.enums.eDifficulty;

public interface IDifficulty
{
    public eDifficulty getDifficulty();
    public int getTimeForQuestion();
}
