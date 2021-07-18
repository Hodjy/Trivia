package com.example.trivia.model;

import com.example.trivia.AnswerButton;

import java.util.ArrayList;

/**
 * Game session class, must be initialised with initGameSession after creation.
 * Must contain questions.
 */
public class GameSessionManager
{
    private ArrayList<Question> m_Questions;
    private GameState m_GameState;
    private int m_CurrentQuestionCounter;

    public GameSessionManager(ArrayList<Question> i_Questions)
    {
        m_Questions = i_Questions;
    }

    public GameState initGameSession()
    {
        int startingLife = 3;
        int startingScore = 0;
        boolean gameIsRunning = true;
        m_CurrentQuestionCounter = 0;
        if(m_Questions.size() != 0)
        {
            m_GameState = new GameState(m_Questions.get(0), startingScore, startingLife, gameIsRunning);
        }
        else
        {
            m_GameState = new GameState(null, 0, 0, !gameIsRunning);
        }

        return  m_GameState;
    }


    public GameState answerPressed(Boolean isCorrectAnswer, int i_TimeLeftForAnswer)
    {
        if(m_GameState.get_IsGameRunning())
        {
            if(isCorrectAnswer)
            {
                //play Correct animation and sound
                //lock timer and UI
                //delay
                calculateScore(i_TimeLeftForAnswer);
                nextQuestion();
            }
            else
            {
                //play InCorrect animation and sound
                //lock timer and UI
                //delay
                loseLifeAndProceed();
            }
        }

        return m_GameState;
    }

    private void nextQuestion()
    {

        if(m_CurrentQuestionCounter <  (m_Questions.size() - 1))
        {
            m_CurrentQuestionCounter++;
            m_GameState.setCurrentQuestion(m_Questions.get(m_CurrentQuestionCounter));
        }
        else
        {
            endGame(true);
        }
    }

    private void calculateScore(int i_TimeLeftForAnswer)
    {
        m_GameState.setCurrentScore(m_GameState.getCurrentScore() + 1);
    }

    private void loseLifeAndProceed()
    {
        m_GameState.setCurrentLife(m_GameState.getCurrentLife() - 1);

        if (m_GameState.getCurrentLife() == 0)
        {
            //TODO animation for HP loss + delay
            endGame(false);
        }
        else
        {
            nextQuestion();
        }
    }

    public GameState timeUp()
    {
        loseLifeAndProceed();
        return m_GameState;
    }

    private void endGame(boolean i_IsAWin)
    {
        m_GameState.set_IsGameRunning(false);

        if(i_IsAWin)
        {
            //win
        }
        else
        {
            //lose, check if because of time or lives and send corresponding message.
        }
    }
}
