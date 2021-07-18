package com.example.trivia;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.trivia.model.Answer;

public class AnswerButton extends LinearLayout
{
    private TextView m_AnswerTv;
    private Boolean m_IsCorrect;
    private ImageView m_AnswerImage;


    public AnswerButton(Context i_Context, AttributeSet i_Attrs) {
        super(i_Context, i_Attrs);

        setOrientation(LinearLayout.HORIZONTAL);
        setClickable(true);
        setBackground(getResources().getDrawable(R.drawable.answer_btn_selector, i_Context.getApplicationContext().getTheme()));
        setTextView(i_Context);
        setImageView(i_Context);
    }

    private void setImageView(Context i_Context) {
        m_AnswerImage = new ImageView(i_Context);
        m_AnswerImage.setLayoutParams(new LinearLayout.LayoutParams(0, 150,1));
        this.addView(m_AnswerImage);
    }

    private void setTextView(Context i_Context) {

        m_AnswerTv = new TextView(i_Context);
        m_AnswerTv.setTextSize(20);
        m_AnswerTv.setTextColor(getResources().getColor(R.color.white, i_Context.getApplicationContext().getTheme()));
        m_AnswerTv.setGravity(Gravity.CENTER);
        m_AnswerTv.setLayoutParams(new LinearLayout.LayoutParams(0, 150,4));
        this.addView(m_AnswerTv);
    }

    private void setAnswerImage() {
        if(m_IsCorrect)
        {
            m_AnswerImage.setImageResource(R.drawable.correct_icon);
        }
        else
        {
            m_AnswerImage.setImageResource(R.drawable.incorrect_icon);
        }

        m_AnswerImage.setVisibility(INVISIBLE);
    }

    public void showAnswerImage()
    {
        m_AnswerImage.setVisibility(VISIBLE);
    }

    public void setAnswerText(String i_Text){
        m_AnswerTv.setText(i_Text);
    }

    private void setIsCorrect(Boolean i_IsCorrect){
        m_IsCorrect = i_IsCorrect;
    }


    public Boolean getIsCorrect(){
        return m_IsCorrect;
    }

    public void setAnswerButton(Answer i_Answer){

        setAnswerText(i_Answer.getAnswerText());
        setIsCorrect(i_Answer.getIsCorrect());
        setAnswerImage();
    }
}
