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
        Hashtable<String, ArrayList<Question>> hashtable = new Hashtable<>();
        hashtable.put("easy", loadEasyQuestions());
        hashtable.put("medium", loadMediumQuestions());
        hashtable.put("hard", loadHardQuestions());

        return hashtable;
    }

    private ArrayList<Question> loadEasyQuestions()
    {
        ArrayList<Question> easyQuestions = new ArrayList<>();

        easyQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/daisy"),
                new Answer("tt", false),
                new Answer("ss", false),
                new Answer("Aba", false),
                new Answer(m_Context.getString(R.string.answer_easy_daisy), true)));

        easyQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/cyclamen"),
                new Answer("Aba", false),
                new Answer("Aba", false),
                new Answer("Aba", false),
                new Answer(m_Context.getString(R.string.answer_easy_cyclamen), true)));

        easyQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/rose"),
                new Answer("Aba", false),
                new Answer("Aba", false),
                new Answer(m_Context.getString(R.string.answer_easy_rose), true),
                new Answer("sss", false)));

        easyQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/sunflower"),
                new Answer("Aba", false),
                new Answer(m_Context.getString(R.string.answer_easy_sunflower), true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        easyQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/anemone"),
                new Answer("Aba", false),
                new Answer(m_Context.getString(R.string.answer_easy_anemone), true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        easyQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/bleeding_heart"),
                new Answer("Aba", false),
                new Answer(m_Context.getString(R.string.answer_easy_bleeding_heart), true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        easyQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/iris"),
                new Answer("Aba", false),
                new Answer(m_Context.getString(R.string.answer_easy_iris), true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        easyQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/jasmine"),
                new Answer("Aba", false),
                new Answer(m_Context.getString(R.string.answer_easy_jasmine), true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        easyQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/lilac"),
                new Answer("Aba", false),
                new Answer(m_Context.getString(R.string.answer_easy_lilac), true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        easyQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/lotus"),
                new Answer("Aba", false),
                new Answer(m_Context.getString(R.string.answer_easy_lotus), true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        easyQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/narcissus"),
                new Answer("Aba", false),
                new Answer(m_Context.getString(R.string.answer_easy_narcissus), true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        easyQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/orchid"),
                new Answer("Aba", false),
                new Answer(m_Context.getString(R.string.answer_easy_orchid), true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        return easyQuestions;
    }

    private ArrayList<Question> loadMediumQuestions()
    {

        ArrayList<Question> mediumQuestions = new ArrayList<>();

        mediumQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/lily"),
                new Answer("tt", false),
                new Answer("ss", false),
                new Answer("Aba", false),
                new Answer(m_Context.getString(R.string.answer_medium_lily), true)));

        mediumQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/nepeta"),
                new Answer("Aba", false),
                new Answer("Aba", false),
                new Answer("Aba", false),
                new Answer(m_Context.getString(R.string.answer_medium_nepeta), true)));

        mediumQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/nicotiana"),
                new Answer("Aba", false),
                new Answer("Aba", false),
                new Answer(m_Context.getString(R.string.answer_medium_nicotiana), true),
                new Answer("sss", false)));

        mediumQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/nymphea"),
                new Answer("Aba", false),
                new Answer(m_Context.getString(R.string.answer_medium_nymphea), true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        mediumQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/passion_flower"),
                new Answer("Aba", false),
                new Answer(m_Context.getString(R.string.answer_medium_passion_flower), true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        mediumQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/peony"),
                new Answer("Aba", false),
                new Answer(m_Context.getString(R.string.answer_medium_peony), true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        mediumQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/petunia"),
                new Answer("Aba", false),
                new Answer(m_Context.getString(R.string.answer_medium_petunia), true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        mediumQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/phlox"),
                new Answer("Aba", false),
                new Answer(m_Context.getString(R.string.answer_medium_phlox), true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        mediumQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/snowdrop"),
                new Answer("Aba", false),
                new Answer(m_Context.getString(R.string.answer_medium_snowdrop), true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        mediumQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/spider_flower"),
                new Answer("Aba", false),
                new Answer(m_Context.getString(R.string.answer_medium_spider_flower), true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        mediumQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/tiger_flower"),
                new Answer("Aba", false),
                new Answer(m_Context.getString(R.string.answer_medium_tiger_flower), true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        mediumQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/tulip"),
                new Answer("tulip", true),
                new Answer(m_Context.getString(R.string.answer_medium_tulip), false),
                new Answer("Aba", false),
                new Answer("Aba", false)));


        return mediumQuestions;
    }

    private ArrayList<Question> loadHardQuestions()
    {
        ArrayList<Question> hardQuestions = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>(Arrays.asList(m_Context.getResources().getStringArray(R.array.answers_hard)));


        hardQuestions.add(new Question(
                    new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/dandelion"),
                            m_Context.getString(R.string.answer_hard_dandelion)), names));

        hardQuestions.add(new Question(
                new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/echinacea"),
                        m_Context.getString(R.string.answer_hard_echinacea)), names));

        hardQuestions.add(new Question(
                new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/forsythia"),
                        m_Context.getString(R.string.answer_hard_forsythia)), names));

        hardQuestions.add(new Question(
                new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/heliotrope"),
                        m_Context.getString(R.string.answer_hard_heliotrope)), names));

        hardQuestions.add(new Question(
                new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/hibiscus"),
                        m_Context.getString(R.string.answer_hard_hibiscus)), names));

        hardQuestions.add(new Question(
                new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/magnolia"),
                        m_Context.getString(R.string.answer_hard_magnolia)), names));


        hardQuestions.add(new Question(
                new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/mallow"),
                        m_Context.getString(R.string.answer_hard_mallow)), names));

        hardQuestions.add(new Question(
                new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/nemesia"),
                        m_Context.getString(R.string.answer_hard_nemesia)), names));

        hardQuestions.add(new Question(
                new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/nerine"),
                        m_Context.getString(R.string.answer_hard_nerine)), names));

        hardQuestions.add(new Question(
                new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/saponaria"),
                        m_Context.getString(R.string.answer_hard_saponaria)), names));

        hardQuestions.add(new Question(
                new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/sedum"),
                        m_Context.getString(R.string.answer_hard_sedum)), names));

        hardQuestions.add(new Question(
                new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/snapdragons"),
                        m_Context.getString(R.string.answer_hard_snapdragons)), names));

        /* Old format
        hardQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/snapdragons"),
                new Answer(m_Context.getString(R.string.answer_hard_snapdragons), true),
                new Answer("orchid", false),
                new Answer("Aba", false),
                new Answer("Aba", false)));*/


        return hardQuestions;
    }
}
