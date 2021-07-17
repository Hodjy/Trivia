package com.example.trivia;

import android.content.Context;

import androidx.annotation.NonNull;

public class AnswerButton extends androidx.appcompat.widget.AppCompatButton
{
    private Answer m_Answer;

    public AnswerButton(@NonNull Context context)
    {
        super(context);
    }

    public void setAnswerButton(Answer i_Answer)
    {
        m_Answer = i_Answer;
        this.setText(i_Answer.getAnswer());
    }

    public Boolean getIsCorrect()
    {
        return m_Answer.getIsCorrect();
    }

}
