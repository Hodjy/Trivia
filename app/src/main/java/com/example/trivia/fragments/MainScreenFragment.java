package com.example.trivia.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.trivia.R;
import com.example.trivia.enums.eMainActivityFragmentTags;

public class MainScreenFragment extends Fragment
{

    public MainScreenFragment() {}


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.main_screen_choices_fragment,
                container, false);

        Button playBtn = root.findViewById(R.id.main_screen_choices_fragment_play_btn);

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getParentFragmentManager();
                DifficultyFragment dF = new DifficultyFragment();
                fm.beginTransaction().replace(R.id.main_activity_fragment_container, dF, eMainActivityFragmentTags.DIFFICULTY_FRAGMENT.toString())
                        .addToBackStack(null)
                        .setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left
                        , R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }
        });

        return root;
    }
}
