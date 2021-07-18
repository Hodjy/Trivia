package com.example.trivia.model;

import android.net.Uri;

import java.util.ArrayList;
import java.util.Hashtable;

public final class QuestionDataBase {

    private QuestionDataBase(){}

    public static Hashtable<String, ArrayList<Question>> getAllQuestions()
    {
        Hashtable<String, ArrayList<Question>> hashtable = new Hashtable<>();
        hashtable.put("easy", loadEasyQuestions());
        hashtable.put("medium", loadMediumQuestions());
        hashtable.put("hard", loadHardQuestions());

        return hashtable;
    }

    private static ArrayList<Question> loadEasyQuestions(){

        ArrayList<Question> easyQuestions = new ArrayList<>();

        easyQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/daisy"),
                new Answer("tt", false),
                new Answer("ss", false),
                new Answer("Aba", false),
                new Answer("Daisy", true)));

        easyQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/cyclamen"),
                new Answer("Aba", false),
                new Answer("Aba", false),
                new Answer("Aba", false),
                new Answer("Cyclamen", true)));

        easyQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/rose"),
                new Answer("Aba", false),
                new Answer("Aba", false),
                new Answer("Rose", true),
                new Answer("sss", false)));

        easyQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/sunflower"),
                new Answer("Aba", false),
                new Answer("Sunflower", true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        easyQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/anemone"),
                new Answer("Aba", false),
                new Answer("anemone", true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        easyQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/bleeding_heart"),
                new Answer("Aba", false),
                new Answer("bleeding_heart", true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        easyQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/iris"),
                new Answer("Aba", false),
                new Answer("iris", true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        easyQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/jasmine"),
                new Answer("Aba", false),
                new Answer("jasmin", true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        easyQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/lilac"),
                new Answer("Aba", false),
                new Answer("lilac", true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        easyQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/lotus"),
                new Answer("Aba", false),
                new Answer("lotus", true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        easyQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/narcissus"),
                new Answer("Aba", false),
                new Answer("narcissus", true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        easyQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/orchid"),
                new Answer("Aba", false),
                new Answer("orchid", true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        return easyQuestions;
    }

    private static ArrayList<Question> loadMediumQuestions(){

        ArrayList<Question> mediumQuestions = new ArrayList<>();

        mediumQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/lily"),
                new Answer("tt", false),
                new Answer("ss", false),
                new Answer("Aba", false),
                new Answer("Lily", true)));

        mediumQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/nepeta"),
                new Answer("Aba", false),
                new Answer("Aba", false),
                new Answer("Aba", false),
                new Answer("nepeta", true)));

        mediumQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/nicotiana"),
                new Answer("Aba", false),
                new Answer("Aba", false),
                new Answer("nicotiana", true),
                new Answer("sss", false)));

        mediumQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/nymphea"),
                new Answer("Aba", false),
                new Answer("nymphea", true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        mediumQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/passion_flower"),
                new Answer("Aba", false),
                new Answer("passion flower", true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        mediumQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/peony"),
                new Answer("Aba", false),
                new Answer("peony", true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        mediumQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/petunia"),
                new Answer("Aba", false),
                new Answer("petunia", true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        mediumQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/phlox"),
                new Answer("Aba", false),
                new Answer("phlox", true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        mediumQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/snowdrop"),
                new Answer("Aba", false),
                new Answer("snowdrop", true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        mediumQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/spider_flower"),
                new Answer("Aba", false),
                new Answer("spider flower", true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        mediumQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/tiger_flower"),
                new Answer("Aba", false),
                new Answer("tiger flower", true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        mediumQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/tulip"),
                new Answer("tulip", true),
                new Answer("orchid", false),
                new Answer("Aba", false),
                new Answer("Aba", false)));


        return mediumQuestions;
    }

    private static ArrayList<Question> loadHardQuestions(){
        ArrayList<Question> hardQuestions = new ArrayList<>();

        hardQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/dandelion"),
                new Answer("tt", false),
                new Answer("ss", false),
                new Answer("Aba", false),
                new Answer("dandelion", true)));

        hardQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/echinacea"),
                new Answer("Aba", false),
                new Answer("Aba", false),
                new Answer("Aba", false),
                new Answer("echinacea", true)));

        hardQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/forsythia"),
                new Answer("Aba", false),
                new Answer("Aba", false),
                new Answer("forsythia", true),
                new Answer("sss", false)));

        hardQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/heliotrope"),
                new Answer("Aba", false),
                new Answer("heliotrope", true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        hardQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/hibiscus"),
                new Answer("Aba", false),
                new Answer("hibiscus", true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        hardQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/magnolia"),
                new Answer("Aba", false),
                new Answer("magnolia", true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        hardQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/mallow"),
                new Answer("Aba", false),
                new Answer("mallow", true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        hardQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/nemesia"),
                new Answer("Aba", false),
                new Answer("nemesia", true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        hardQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/nerine"),
                new Answer("Aba", false),
                new Answer("nerine", true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        hardQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/saponaria"),
                new Answer("Aba", false),
                new Answer("saponaria", true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        hardQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/sedum"),
                new Answer("Aba", false),
                new Answer("sedum", true),
                new Answer("Aba", false),
                new Answer("Aba", false)));

        hardQuestions.add(new Question(Uri.parse("android.resource://com.example.trivia/drawable/snapdragons"),
                new Answer("snapdragons", true),
                new Answer("orchid", false),
                new Answer("Aba", false),
                new Answer("Aba", false)));


        return hardQuestions;
    }
}
