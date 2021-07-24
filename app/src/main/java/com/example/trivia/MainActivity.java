package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;
;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.trivia.enums.eMainActivityFragmentTags;
import com.example.trivia.enums.eSoundsIdentifier;
import com.example.trivia.fragments.DifficultyFragment;
import com.example.trivia.fragments.HelpDialogFragment;
import com.example.trivia.fragments.MainScreenFragment;
import com.example.trivia.model.SoundManager;
import com.example.trivia.model.difficulty.ADifficulty;

public class MainActivity extends AppCompatActivity implements DifficultyFragment.OnDifficultyClickFragmentListener, MainScreenFragment.MainScreenFragmentListener
{
    private Button m_SoundBtn;
    private FragmentManager m_fragmentManager;


    @Override
    public void onEncyclopediaClick(){
        SoundManager.getInstance().playMainSound(MainActivity.this, eSoundsIdentifier.BTN_CLICK_SOUND);
        Intent intent = new Intent(MainActivity.this, EncyclopediaActivity.class);
        startActivity(intent);
    }

    @Override
    public void onLeaderBoardClick() {
        SoundManager.getInstance().playMainSound(MainActivity.this, eSoundsIdentifier.BTN_CLICK_SOUND);
        Intent intent = new Intent(MainActivity.this, LeaderboardActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tapHereTV = findViewById(R.id.main_activity_tap_hereTV);
        m_SoundBtn = findViewById(R.id.main_activity_sound_btn);
        Button helpBtn = findViewById(R.id.main_activity_how_to_play_btn);
        m_fragmentManager = getSupportFragmentManager();

        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.getInstance().playMainSound(MainActivity.this, eSoundsIdentifier.BTN_CLICK_SOUND);
                HelpDialogFragment helpDialogFragment = new HelpDialogFragment();
                helpDialogFragment.show(m_fragmentManager, eMainActivityFragmentTags.HELP_DIALOG_FRAGMENT.toString());
            }
        });

       tapHereTV.setOnClickListener(v -> disableTextViewAndShowMainFragment((TextView) v));

        m_SoundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SoundManager.getInstance().isPlayMusic())
                {
                    SoundManager.getInstance().pauseBackgroundSound();
                    m_SoundBtn.setBackground(getResources().getDrawable(R.drawable.ic_baseline_volume_off_24, ApplicationContext.getContext().getTheme()));
                }
                else
                {
                    SoundManager.getInstance().resumeBackgroundSound();
                    m_SoundBtn.setBackground(getResources().getDrawable(R.drawable.ic_outline_volume_up, ApplicationContext.getContext().getTheme()));
                }

                SoundManager.getInstance().setPlayMusic(!SoundManager.getInstance().isPlayMusic());
            }
        });
    }

    private void disableTextViewAndShowMainFragment(TextView i_TextView) {
        i_TextView.setEnabled(false);
        i_TextView.setVisibility(View.INVISIBLE);
        m_fragmentManager.beginTransaction().add(R.id.main_activity_fragment_container, new MainScreenFragment(),
                eMainActivityFragmentTags.MAIN_SCREEN_FRAGMENT.toString())
                .commit();
    }

    @Override
    public void onDifficultyClick(ADifficulty i_Difficulty)
    {
        SoundManager.getInstance().playMainSound(MainActivity.this, eSoundsIdentifier.BTN_CLICK_SOUND);
        getSupportFragmentManager().popBackStack();
        startGame(i_Difficulty);
    }

    private void startGame(ADifficulty i_Difficulty)
    {
        Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
        intent.putExtra("Difficulty", i_Difficulty);
        startActivity(intent);
    }

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

    @Override
    protected void onPause() {
        super.onPause();
        SoundManager.getInstance().pauseBackgroundSound();
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(SoundManager.getInstance().isPlayMusic())
        {
            SoundManager.getInstance().playBackgroundSound(MainActivity.this, eSoundsIdentifier.MAIN_ACTIVITY_MUSIC);
            m_SoundBtn.setBackground(getResources().getDrawable(R.drawable.ic_outline_volume_up, ApplicationContext.getContext().getTheme()));
        }
        else
        {
            SoundManager.getInstance().setPlayMusic(true);
            SoundManager.getInstance().playBackgroundSound(MainActivity.this, eSoundsIdentifier.MAIN_ACTIVITY_MUSIC);
            SoundManager.getInstance().pauseBackgroundSound();
            SoundManager.getInstance().setPlayMusic(false);
            m_SoundBtn.setBackground(getResources().getDrawable(R.drawable.ic_baseline_volume_off_24, ApplicationContext.getContext().getTheme()));
        }
    }
}