package com.example.trivia.model;

import android.content.Context;
import android.net.Uri;

import com.example.trivia.ApplicationContext;
import com.example.trivia.R;
import com.example.trivia.model.difficulty.DifficultyEasy;
import com.example.trivia.model.difficulty.DifficultyHard;
import com.example.trivia.model.difficulty.DifficultyMedium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

/**
 * The Questions database. creating the questions here.
 * Must get the applications context in order to properly work (needed for the string resources)
 */
public class QuestionsCreator {

    private static Hashtable<String, ArrayList<Question>> m_Questions = null;
    public QuestionsCreator(){}

    public static ArrayList<Question> getQuestionsByDifficulty(String i_Difficulty)
    {
        ArrayList<Question> questions;

        if(m_Questions == null)
        {
            loadAllQuestions();
        }

        questions = m_Questions.get(i_Difficulty);
        //if not null, create a copy (done this way to avoid exception).
        if (questions != null)
        {
            questions = new ArrayList<Question>(questions);
        }

        return questions;
    }

    private static void loadAllQuestions()
    {
        String easy = new DifficultyEasy().getDifficulty(),
                medium = new DifficultyMedium().getDifficulty(),
                hard = new DifficultyHard().getDifficulty();

        Hashtable<String,ArrayList<Foliage>> foliages = FoliageDatabase.getFoliages();
        Hashtable<String, ArrayList<Question>> questionsHashtable = new Hashtable<>();

        questionsHashtable.put(easy, loadEasyQuestions(foliages.get(easy)));
        questionsHashtable.put(medium, loadMediumQuestions(foliages.get(medium)));
        questionsHashtable.put(hard, loadHardQuestions(foliages.get(hard)));

        m_Questions = questionsHashtable;
    }

    private static  ArrayList<Question> loadEasyQuestions(ArrayList<Foliage> i_EasyFoliages)
    {
        return createQuestionsList(i_EasyFoliages, R.array.answers_easy);
    }

    private static ArrayList<Question> loadMediumQuestions(ArrayList<Foliage> i_MediumFoliages)
    {
        return createQuestionsList(i_MediumFoliages, R.array.answers_medium);
    }

    private static ArrayList<Question> loadHardQuestions(ArrayList<Foliage> i_HardFoliages)
    {
        return createQuestionsList(i_HardFoliages, R.array.answers_hard);
    }

    /**
     *
     * Will iterate for each foliage and a the corresponding names string
     * and attach them to the new questions. the new questions will handle the creation of
     * incorrect answers.
     * @param i_Foliages
     * @param i_DifficultyNamesStringArrayID
     * @return
     */
    private static ArrayList<Question> createQuestionsList(ArrayList<Foliage> i_Foliages, int i_DifficultyNamesStringArrayID)
    {
        Context appContext = ApplicationContext.getContext();
        ArrayList<Question> createdQuestions = new ArrayList<>();
        ArrayList<String> difficultyFoliagesNames = new ArrayList<>(Arrays.asList(
                appContext.getResources().getStringArray(i_DifficultyNamesStringArrayID)));

        for (Foliage foliage : i_Foliages) {
            createdQuestions.add(new Question(foliage, difficultyFoliagesNames));
        }

        return createdQuestions;
    }


}
