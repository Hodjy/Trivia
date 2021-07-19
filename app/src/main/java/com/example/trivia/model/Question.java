package com.example.trivia.model;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Question
{
    private final Uri m_ImgUri;
    private final ArrayList<Answer> m_Answers;

    /**
     * Takes a foliage object, unpacks its uri, name, and generates 3 other incorrect answers.
     * @param i_Foliage The correct answer will be generated from this object.
     * @param i_FoliageNames MUST contain at least 4 elements!!! will generate a
     *                       copy of this list in order to preserve it.
     */
    public Question(Foliage i_Foliage, ArrayList<String> i_FoliageNames)
    {
        ArrayList<String> foliageNamesCopy = new ArrayList<>(i_FoliageNames);
        m_Answers = new ArrayList<>();
        m_ImgUri = i_Foliage.getImgeUri();
        Answer correctAnswer = new Answer(i_Foliage.getFoliageName(), true);

        m_Answers.add(correctAnswer);
        generateIncorrectAnswers(foliageNamesCopy, correctAnswer.getAnswerText());
    }

    /**
     * Iterate 3 times, and creates 3 incorrect answers, every time it takes an answer it will
     * remove the answer's name from the list, to prevent duplicates.
     * @param i_FoliageNames
     * @param i_CorrectAnswerText removes this name from the list in order to not have a duplicate
     *                            of the correct answer as an incorrect one.
     */
    private void generateIncorrectAnswers(ArrayList<String> i_FoliageNames, String i_CorrectAnswerText)
    {
        Random rand = new Random();
        Answer wrongAnswer;
        i_FoliageNames.remove(i_CorrectAnswerText);

        for (int i = 0 ,random = 0; i < 3; i++)
        {
            random = rand.nextInt(i_FoliageNames.size());
            wrongAnswer = new Answer(i_FoliageNames.get(random),false);
            m_Answers.add(wrongAnswer);
            i_FoliageNames.remove(wrongAnswer.getAnswerText());
        }
    }


    public Uri getImgUri()
    {
        return m_ImgUri;
    }

    public ArrayList<Answer> getAnswers()
    {
        return  m_Answers;
    }
}
