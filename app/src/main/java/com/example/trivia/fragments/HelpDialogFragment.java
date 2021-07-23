package com.example.trivia.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.trivia.R;

public class HelpDialogFragment extends DialogFragment
{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.help_dialog_fragment, container);

        TextView okTv = root.findViewById(R.id.help_dialog_fragment_okTv);
        okTv.setOnClickListener(v -> getDialog().dismiss());

        return root;
    }
}
