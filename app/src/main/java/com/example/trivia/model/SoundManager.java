package com.example.trivia.model;

import android.content.Context;
import android.media.MediaPlayer;
import com.example.trivia.enums.eSoundsIdentifier;

public class SoundManager {

    private MediaPlayer m_MediaPlayer;
    private static SoundManager m_Instance;

    private SoundManager(){}

    public static SoundManager getInstance(){
        if(m_Instance == null)
        {
            m_Instance = new SoundManager();
        }

        return m_Instance;
    }

    public void playSound(Context context, eSoundsIdentifier soundIdentifier){
        if(m_MediaPlayer != null)
        {
            m_MediaPlayer.release();
        }
        m_MediaPlayer = MediaPlayer.create(context, soundIdentifier.id);
        m_MediaPlayer.start();
    }

    public void pauseSound(){
        if(m_MediaPlayer != null){
            m_MediaPlayer.pause();
        }
    }

    public void resumeSound(){
        if(m_MediaPlayer != null){
            m_MediaPlayer.start();
        }
    }
}
