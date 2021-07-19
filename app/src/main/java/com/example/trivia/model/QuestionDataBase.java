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
    public QuestionDataBase(){}

    //TODO make its a properly sealed class (only access with a facade)
    public Hashtable<String, ArrayList<Question>> getAllQuestions(Context i_ApplicationContext)
    {
        m_Context = i_ApplicationContext;
        String easy = "easy", medium = "medium", hard = "hard";

        FoliageDatabase.setContext(m_Context);
        Hashtable<String,ArrayList<Foliage>> foliages = FoliageDatabase.getFoliages();

        Hashtable<String, ArrayList<Question>> hashtable = new Hashtable<>();
        hashtable.put(easy, loadEasyQuestions(foliages.get(easy)));
        hashtable.put(medium, loadMediumQuestions(foliages.get(medium)));
        hashtable.put(hard, loadHardQuestions(foliages.get(hard)));

        return hashtable;
    }

    //The Load methods will iterate for each foliage and a the corresponding names string
    //and attach them to the new questions. the new questions will handle the creation of
    //incorrect answers.
    private ArrayList<Question> loadEasyQuestions(ArrayList<Foliage> i_EasyFoliages)
    {
        ArrayList<Question> easyQuestions = new ArrayList<>();
        ArrayList<String> easyNames = new ArrayList<>(Arrays.asList(
                m_Context.getResources().getStringArray(R.array.answers_easy)));

        for (Foliage foliage : i_EasyFoliages)
        {
            easyQuestions.add(new Question(foliage, easyNames));
        }

        return easyQuestions;
    }

    private ArrayList<Question> loadMediumQuestions(ArrayList<Foliage> i_MediumFoliages)
    {
        ArrayList<Question> mediumQuestions = new ArrayList<>();
        ArrayList<String> mediumNames = new ArrayList<>(Arrays.asList(
                m_Context.getResources().getStringArray(R.array.answers_medium)));

        for(Foliage foliage : i_MediumFoliages)
        {
            mediumQuestions.add(new Question(foliage, mediumNames));
        }

        return mediumQuestions;
    }

    private ArrayList<Question> loadHardQuestions(ArrayList<Foliage> i_HardFoliages)
    {
        ArrayList<Question> hardQuestions = new ArrayList<>();
        ArrayList<String> hardNames = new ArrayList<>(Arrays.asList(
                m_Context.getResources().getStringArray(R.array.answers_hard)));

        for (Foliage foliage : i_HardFoliages)
        {
            hardQuestions.add(new Question(foliage, hardNames));
        }

        return hardQuestions;
    }
}
