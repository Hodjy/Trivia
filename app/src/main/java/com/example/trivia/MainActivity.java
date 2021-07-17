package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
        startActivity(intent);
    }

    //TODO When pressing a button in-game, stop the timer in order to prevent bugs.
    //TODO 1 Explicit intent ExtraData
    //TODO SharedPreferences
}