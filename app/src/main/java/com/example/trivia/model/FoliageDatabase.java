package com.example.trivia.model;

import android.content.Context;
import android.net.Uri;

import com.example.trivia.R;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Holds all the foliage data, need to call "setContext" before using in order to
 */
public class FoliageDatabase
{
    private static Context m_AppContext = null;
    private static Hashtable<String, ArrayList<Foliage>> m_Foliage = null;

    public static void setContext(Context i_AppContext)
    {
        m_AppContext = i_AppContext;
    }

    public static Hashtable<String, ArrayList<Foliage>> getFoliages()
    {
        Hashtable<String, ArrayList<Foliage>> foliages = new Hashtable<>();

        if(m_Foliage == null && m_AppContext != null)
        {
            foliages.put("easy", loadEasyFoliage());
            foliages.put("medium", loadMediumFoliage());
            foliages.put("hard", loadHardFoliage());
            m_Foliage = foliages;
        }

        return m_Foliage;
    }

    private static ArrayList<Foliage> loadEasyFoliage()
    {
        ArrayList<Foliage> easyFoliage = new ArrayList<>();

        easyFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/daisy"),
                m_AppContext.getString(R.string.answer_easy_daisy)));

        easyFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/cyclamen"),
                m_AppContext.getString(R.string.answer_easy_cyclamen)));

        easyFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/rose"),
                m_AppContext.getString(R.string.answer_easy_rose)));

        easyFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/sunflower"),
                m_AppContext.getString(R.string.answer_easy_sunflower)));

        easyFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/anemone"),
                m_AppContext.getString(R.string.answer_easy_anemone)));

        easyFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/bleeding_heart"),
                m_AppContext.getString(R.string.answer_easy_bleeding_heart)));

        easyFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/iris"),
                m_AppContext.getString(R.string.answer_easy_iris)));

        easyFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/jasmine"),
                m_AppContext.getString(R.string.answer_easy_jasmine)));

        easyFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/lilac"),
                m_AppContext.getString(R.string.answer_easy_lilac)));

        easyFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/lotus"),
                m_AppContext.getString(R.string.answer_easy_lotus)));

        easyFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/narcissus"),
                m_AppContext.getString(R.string.answer_easy_narcissus)));

        easyFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/orchid"),
                m_AppContext.getString(R.string.answer_easy_orchid)));

        return easyFoliage;
    }

    private static ArrayList<Foliage> loadMediumFoliage()
    {
        ArrayList<Foliage> mediumFoliage = new ArrayList<>();

        mediumFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/lily"),
                m_AppContext.getString(R.string.answer_medium_lily)));

        mediumFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/nepeta"),
                m_AppContext.getString(R.string.answer_medium_nepeta)));

        mediumFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/nicotiana"),
                m_AppContext.getString(R.string.answer_medium_nicotiana)));

        mediumFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/nymphea"),
                m_AppContext.getString(R.string.answer_medium_nymphea)));

        mediumFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/passion_flower"),
                m_AppContext.getString(R.string.answer_medium_passion_flower)));

        mediumFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/peony"),
                m_AppContext.getString(R.string.answer_medium_peony)));

        mediumFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/petunia"),
                m_AppContext.getString(R.string.answer_medium_petunia)));

        mediumFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/phlox"),
                m_AppContext.getString(R.string.answer_medium_phlox)));

        mediumFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/snowdrop"),
                m_AppContext.getString(R.string.answer_medium_snowdrop)));

        mediumFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/spider_flower"),
                m_AppContext.getString(R.string.answer_medium_spider_flower)));

        mediumFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/tiger_flower"),
                m_AppContext.getString(R.string.answer_medium_tiger_flower)));

        mediumFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/tulip"),
                m_AppContext.getString(R.string.answer_medium_tulip)));

        return mediumFoliage;
    }

    private static ArrayList<Foliage> loadHardFoliage()
    {
        ArrayList<Foliage> hardFoliage = new ArrayList<>();

        hardFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/dandelion"),
                m_AppContext.getString(R.string.answer_hard_dandelion)));

        hardFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/echinacea"),
                m_AppContext.getString(R.string.answer_hard_echinacea)));

        hardFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/forsythia"),
                m_AppContext.getString(R.string.answer_hard_forsythia)));

        hardFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/heliotrope"),
                m_AppContext.getString(R.string.answer_hard_heliotrope)));

        hardFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/hibiscus"),
                m_AppContext.getString(R.string.answer_hard_hibiscus)));

        hardFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/magnolia"),
                m_AppContext.getString(R.string.answer_hard_magnolia)));

        hardFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/mallow"),
                m_AppContext.getString(R.string.answer_hard_mallow)));

        hardFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/nemesia"),
                m_AppContext.getString(R.string.answer_hard_nemesia)));

        hardFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/nerine"),
                m_AppContext.getString(R.string.answer_hard_nerine)));

        hardFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/saponaria"),
                m_AppContext.getString(R.string.answer_hard_saponaria)));

        hardFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/sedum"),
                m_AppContext.getString(R.string.answer_hard_sedum)));

        hardFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/snapdragons"),
                m_AppContext.getString(R.string.answer_hard_snapdragons)));

        return hardFoliage;
    }

}
