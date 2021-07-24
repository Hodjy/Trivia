package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;
;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
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

public class MainActivity extends AppCompatActivity implements DifficultyFragment.IDifficultyFragmentListener, MainScreenFragment.IMainScreenFragmentListener
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

    private void disableTextViewAndShowMainFragment(TextView i_TextView)
    {
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

    @Override
    protected void onPause() {
        super.onPause();
        SoundManager.getInstance().onActivityPause();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onResume(){
        super.onResume();

        SoundManager.getInstance().onActivityResume(MainActivity.this, eSoundsIdentifier.MAIN_ACTIVITY_MUSIC,
                m_SoundBtn, getResources().getDrawable(R.drawable.ic_outline_volume_up, ApplicationContext.getContext().getTheme()),
                getResources().getDrawable(R.drawable.ic_baseline_volume_off_24, ApplicationContext.getContext().getTheme()));

    }
}