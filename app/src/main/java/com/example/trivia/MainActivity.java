package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;
;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.trivia.enums.eMainActivityFragmentTags;
import com.example.trivia.fragments.DifficultyFragment;
import com.example.trivia.fragments.MainScreenFragment;
import com.example.trivia.model.difficulty.ADifficulty;

public class MainActivity extends AppCompatActivity implements DifficultyFragment.OnDifficultyClickFragmentListener
{
    private AlertDialog.Builder m_DialogBuilder;
    private AlertDialog m_Dialog;
    private Button mBtn1;
    private int flipflop = 0;
    private FragmentManager m_fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button tstBtn = findViewById(R.id.aatest);
        m_fragmentManager = getSupportFragmentManager();

       tstBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m_fragmentManager.beginTransaction().add(R.id.main_activity_fragment_container, new MainScreenFragment(),
                        eMainActivityFragmentTags.MAIN_SCREEN_FRAGMENT.toString())
                        .commit();

            }
        });
    }

    @Override
    public void onDifficultyClick(ADifficulty i_Difficulty)
    {
        getSupportFragmentManager().popBackStack();
        startGame(i_Difficulty);
    }

    private void startGame(ADifficulty i_Difficulty)
    {
        Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
        intent.putExtra("Difficulty", i_Difficulty);
        startActivity(intent);
    }

    //TODO When pressing a button in-game, stop the timer in order to prevent bugs.
    //TODO 1 Explicit intent ExtraData
    //TODO SharedPreferences

/*    private class MainScreenFragmentAdapter extends FragmentStateAdapter
    {
        private final int AMOUNT_OF_FRAGMENTS = 2;

        public MainScreenFragmentAdapter(@NonNull  FragmentManager fragmentManager,
                                         @NonNull  Lifecycle lifecycle)
        {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch(position)
            {
                case 0:
                    return new MainScreenFragment();
                case 1:
                    return new DifficultyFragment();
                default:
                    Toast.makeText(MainActivity.this, "Please send help", Toast.LENGTH_LONG).show();
                    return null;
            }
        }

        @Override
        public int getItemCount() {
            return AMOUNT_OF_FRAGMENTS;
        }
    }*/

/*    private void createDifficultyDialog()
    {
        m_DialogBuilder = new AlertDialog.Builder(this);
        final View difficultyDialog = getLayoutInflater().inflate(R.layout.difficulty_popup, null);

        mBtn1 = findViewById(R.id.difficulty_popup_easy_btn);

        m_DialogBuilder.setView(difficultyDialog);
        m_Dialog = m_DialogBuilder.create();
        m_Dialog.show();

        mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Easy peasy!", Toast.LENGTH_SHORT).show();
                m_Dialog.dismiss();
            }
        });

    }*/

}