package com.example.trivia.model;

import android.content.Context;
import android.net.Uri;

import com.example.trivia.ApplicationContext;
import com.example.trivia.R;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Holds all the foliage data, need to call "setContext" before using in order to
 */
public class FoliageDatabase
{
    private static Hashtable<String, ArrayList<Foliage>> m_Foliage = null;

    //TODO remove and implement this https://stackoverflow.com/questions/2002288/static-way-to-get-context-in-android

    public static Hashtable<String, ArrayList<Foliage>> getFoliages()
    {
        Hashtable<String, ArrayList<Foliage>> foliages = new Hashtable<>();

        if(m_Foliage == null)
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
        Context appContext = ApplicationContext.getContext();
        ArrayList<Foliage> easyFoliage = new ArrayList<>();

        easyFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/daisy"),
                appContext.getString(R.string.answer_easy_daisy)));

        easyFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/cyclamen"),
                appContext.getString(R.string.answer_easy_cyclamen)));

        easyFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/rose"),
                appContext.getString(R.string.answer_easy_rose)));

        easyFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/sunflower"),
                appContext.getString(R.string.answer_easy_sunflower)));

        easyFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/anemone"),
                appContext.getString(R.string.answer_easy_anemone)));

        easyFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/bleeding_heart"),
                appContext.getString(R.string.answer_easy_bleeding_heart)));

        easyFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/iris"),
                appContext.getString(R.string.answer_easy_iris)));

        easyFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/jasmine"),
                appContext.getString(R.string.answer_easy_jasmine)));

        easyFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/lilac"),
                appContext.getString(R.string.answer_easy_lilac)));

        easyFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/lotus"),
                appContext.getString(R.string.answer_easy_lotus)));

        easyFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/narcissus"),
                appContext.getString(R.string.answer_easy_narcissus)));

        easyFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/orchid"),
                appContext.getString(R.string.answer_easy_orchid)));

        return easyFoliage;
    }

    private static ArrayList<Foliage> loadMediumFoliage()
    {
        Context appContext = ApplicationContext.getContext();
        ArrayList<Foliage> mediumFoliage = new ArrayList<>();

        mediumFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/lily"),
                appContext.getString(R.string.answer_medium_lily)));

        mediumFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/nepeta"),
                appContext.getString(R.string.answer_medium_nepeta)));

        mediumFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/nicotiana"),
                appContext.getString(R.string.answer_medium_nicotiana)));

        mediumFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/nymphea"),
                appContext.getString(R.string.answer_medium_nymphea)));

        mediumFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/passion_flower"),
                appContext.getString(R.string.answer_medium_passion_flower)));

        mediumFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/peony"),
                appContext.getString(R.string.answer_medium_peony)));

        mediumFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/petunia"),
                appContext.getString(R.string.answer_medium_petunia)));

        mediumFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/phlox"),
                appContext.getString(R.string.answer_medium_phlox)));

        mediumFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/snowdrop"),
                appContext.getString(R.string.answer_medium_snowdrop)));

        mediumFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/spider_flower"),
                appContext.getString(R.string.answer_medium_spider_flower)));

        mediumFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/tiger_flower"),
                appContext.getString(R.string.answer_medium_tiger_flower)));

        mediumFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/tulip"),
                appContext.getString(R.string.answer_medium_tulip)));

        return mediumFoliage;
    }

    private static ArrayList<Foliage> loadHardFoliage()
    {
        Context appContext = ApplicationContext.getContext();
        ArrayList<Foliage> hardFoliage = new ArrayList<>();

        hardFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/dandelion"),
                appContext.getString(R.string.answer_hard_dandelion)));

        hardFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/echinacea"),
                appContext.getString(R.string.answer_hard_echinacea)));

        hardFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/forsythia"),
                appContext.getString(R.string.answer_hard_forsythia)));

        hardFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/heliotrope"),
                appContext.getString(R.string.answer_hard_heliotrope)));

        hardFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/hibiscus"),
                appContext.getString(R.string.answer_hard_hibiscus)));

        hardFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/magnolia"),
                appContext.getString(R.string.answer_hard_magnolia)));

        hardFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/mallow"),
                appContext.getString(R.string.answer_hard_mallow)));

        hardFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/nemesia"),
                appContext.getString(R.string.answer_hard_nemesia)));

        hardFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/nerine"),
                appContext.getString(R.string.answer_hard_nerine)));

        hardFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/saponaria"),
                appContext.getString(R.string.answer_hard_saponaria)));

        hardFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/sedum"),
                appContext.getString(R.string.answer_hard_sedum)));

        hardFoliage.add(new Foliage(Uri.parse("android.resource://com.example.trivia/drawable/snapdragons"),
                appContext.getString(R.string.answer_hard_snapdragons)));

        return hardFoliage;
    }

}
