package com.example.trivia.model;

import android.net.Uri;

import java.util.ArrayList;

public final class QuestionDataBase {

    private QuestionDataBase(){}

    public static ArrayList<Question> loadEasyQuestions(){

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

    public static ArrayList<Question> loadMediumQuestions(){

        ArrayList<Question> mediumQuestions = new ArrayList<>();



        return mediumQuestions;
    }

    public static ArrayList<Question> loadHardQuestions(){
        ArrayList<Question> hardQuestions = new ArrayList<>();

        return hardQuestions;
    }
}
