package com.example.trivia.model;

import com.example.trivia.model.difficulty.ADifficulty;
import com.example.trivia.model.difficulty.DifficultyEasy;
import com.example.trivia.model.difficulty.DifficultyHard;
import com.example.trivia.model.difficulty.DifficultyMedium;

import java.util.ArrayList;
import java.util.Random;

/**
 * Game session class, must be initialised with initGameSession after creation.
 * Must contain questions.
 */
public class GameSessionManager
{
    private ArrayList<Question> m_Questions;
    private GameState m_GameState;
    private int m_CurrentQuestionCounter;
    private int m_QuestionScoreWeight;
    private ADifficulty m_Difficulty;

    public GameSessionManager(ADifficulty i_Difficulty)
    {
        m_Difficulty = i_Difficulty;
        calculateScoreWeight();
        loadAndShuffleQuestions();
    }

    private void calculateScoreWeight() {
        int score = 0;
        m_QuestionScoreWeight = 1;
        ArrayList<ADifficulty> difficulties = new ArrayList<>();
        difficulties.add(new DifficultyEasy());
        difficulties.add(new DifficultyMedium());
        difficulties.add(new DifficultyHard());

        for (ADifficulty difficulty : difficulties)
        {
            score +=100;
            if(m_Difficulty.getDifficulty().equals(difficulty.getDifficulty()))
            {
                m_QuestionScoreWeight = score;
                break;
            }
        }
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


    public GameState answerPressed(Boolean isCorrectAnswer)
    {
        if(m_GameState.get_IsGameRunning())
        {
            if(isCorrectAnswer)
            {
                //play Correct animation and sound
                //lock timer and UI
                //delay
                calculateScore();
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

    private void calculateScore()
    {
        m_GameState.setCurrentScore(m_GameState.getCurrentScore() + m_QuestionScoreWeight);
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

    public int getTimeForQuestion()
    {
        return m_Difficulty.getTimeForQuestion();
    }

    public String getDifficulty()
    {
        return m_Difficulty.getDifficulty();
    }

    private void loadAndShuffleQuestions()
    {
        ArrayList<Question> questions = QuestionsCreator.
                getQuestionsByDifficulty(m_Difficulty.getDifficulty());
        m_Questions = shuffleQuestions(questions);
    }

    private ArrayList<Question> shuffleQuestions(ArrayList<Question> i_Questions)
    {
        int length = i_Questions.size();
        ArrayList<Question> randomizedQuestions = new ArrayList<>();

        if(length > 0)
        {
            Random rand = new Random();
            int randomIndex = 0;

            for (int i = 0; i< length; i++)
            {
                randomIndex = rand.nextInt(i_Questions.size());
                Question question = i_Questions.get(randomIndex);
                randomizedQuestions.add(question);
                i_Questions.remove(question);
            }
        }
         return randomizedQuestions;
    }

}
