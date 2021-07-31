package com.example.trivia.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.trivia.ApplicationContext;
import com.example.trivia.R;
import com.example.trivia.enums.eSoundsIdentifier;
import com.example.trivia.model.SoundManager;
import com.example.trivia.model.difficulty.DifficultyEasy;
import com.example.trivia.model.difficulty.DifficultyHard;
import com.example.trivia.model.difficulty.DifficultyMedium;
import com.example.trivia.model.difficulty.ADifficulty;

public class DifficultyFragment extends Fragment
{
    public DifficultyFragment() {}

    public interface IDifficultyFragmentListener
    {
        void onDifficultyClick(ADifficulty i_Difficulty);
    }

    IDifficultyFragmentListener m_Callback;

    @Override
    public void onAttach(@NonNull Context i_Context)
    {
        super.onAttach(i_Context);

        try {
            m_Callback = (IDifficultyFragmentListener)i_Context;
        }
        catch (ClassCastException ex)
        {
            throw new ClassCastException("The activity must implement the interface 'OnDifficultyClickFragmentListener'.");
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.difficulty_fragment, container, false);

        Button easyBtn = root.findViewById(R.id.difficulty_popup_easy_btn);
        Button mediumBtn = root.findViewById(R.id.difficulty_popup_medium_btn);
        Button hardBtn = root.findViewById(R.id.difficulty_popup_hard_btn);
        Button backBtn = root.findViewById(R.id.difficulty_fragment_back_btn);

        easyBtn.setOnClickListener(v -> m_Callback.onDifficultyClick(new DifficultyEasy()));
        mediumBtn.setOnClickListener(v -> m_Callback.onDifficultyClick(new DifficultyMedium()));
        hardBtn.setOnClickListener(v -> m_Callback.onDifficultyClick(new DifficultyHard()));
        backBtn.setOnClickListener(v -> {
            SoundManager.getInstance().playMainSound(ApplicationContext.getContext(), eSoundsIdentifier.BTN_CLICK_SOUND);
            getParentFragmentManager().popBackStack();
        });
        return root;
    }
}
