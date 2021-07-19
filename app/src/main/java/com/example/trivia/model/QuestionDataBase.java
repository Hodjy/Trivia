package com.example.trivia.model;

import android.content.Context;
import android.net.Uri;

import com.example.trivia.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

/**
 * The Questions database. creating the questions here.
 * Must get the applications context in order to properly work (needed for the string resources)
 */
public final class QuestionDataBase {

    private Context m_Context;
    private Hashtable<String, ArrayList<Question>> m_Questions = null;
    public QuestionDataBase(){}

    //TODO make its a properly sealed class (only access with a facade)
    public Hashtable<String, ArrayList<Question>> getAllQuestions(Context i_ApplicationContext)
    {
        if(m_Questions == null)
        {
            m_Context = i_ApplicationContext;
            String easy = "easy", medium = "medium", hard = "hard";

            FoliageDatabase.setContext(m_Context);
            Hashtable<String,ArrayList<Foliage>> foliages = FoliageDatabase.getFoliages();
            Hashtable<String, ArrayList<Question>> questionsHashtable = new Hashtable<>();

            questionsHashtable.put(easy, loadEasyQuestions(foliages.get(easy)));
            questionsHashtable.put(medium, loadMediumQuestions(foliages.get(medium)));
            questionsHashtable.put(hard, loadHardQuestions(foliages.get(hard)));

            m_Questions = questionsHashtable;
        }

        return m_Questions;
    }

    private ArrayList<Question> loadEasyQuestions(ArrayList<Foliage> i_EasyFoliages)
    {
        return createQuestionsList(i_EasyFoliages, R.array.answers_easy);
    }

    private ArrayList<Question> loadMediumQuestions(ArrayList<Foliage> i_MediumFoliages)
    {
        return createQuestionsList(i_MediumFoliages, R.array.answers_medium);
    }

    private ArrayList<Question> loadHardQuestions(ArrayList<Foliage> i_HardFoliages)
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
    private ArrayList<Question> createQuestionsList(ArrayList<Foliage> i_Foliages, int i_DifficultyNamesStringArrayID)
    {
        ArrayList<Question> createdQuestions = new ArrayList<>();
        ArrayList<String> difficultyFoliagesNames = new ArrayList<>(Arrays.asList(
                m_Context.getResources().getStringArray(i_DifficultyNamesStringArrayID)));

        for (Foliage foliage : i_Foliages) {
            createdQuestions.add(new Question(foliage, difficultyFoliagesNames));
        }

        return createdQuestions;
    }


}
