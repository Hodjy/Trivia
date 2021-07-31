package com.example.trivia.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.trivia.R;
import com.example.trivia.model.save.UserScore;

import java.util.List;

public class UserScoreAdapter extends BaseAdapter {

    List<UserScore> m_UserScores;

    public UserScoreAdapter(List<UserScore> i_UserScores) {
        m_UserScores = i_UserScores;
    }

    @Override
    public int getCount() {
        return m_UserScores.size();
    }

    @Override
    public Object getItem(int position) {
        return m_UserScores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_score_layout, parent, false);

        TextView nameTv = convertView.findViewById(R.id.user_score_nameTv);
        TextView scoreTv = convertView.findViewById(R.id.user_score_scoreTv);

        UserScore userScore = m_UserScores.get(position);

        nameTv.setText(userScore.getUserName());
        scoreTv.setText(userScore.getScore() + "");

        return convertView;
    }
}
