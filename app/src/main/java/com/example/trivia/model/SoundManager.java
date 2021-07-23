package com.example.trivia.model;

import android.content.Context;
import android.media.MediaPlayer;
import com.example.trivia.enums.eSoundsIdentifier;

public class SoundManager {

    private static boolean m_PlayMusic = true;
    private MediaPlayer m_MainMediaPlayer;
    private MediaPlayer m_BackgroundMediaPlayer;
    private static SoundManager m_Instance;

    private SoundManager(){}

    public static SoundManager getInstance(){
        if(m_Instance == null)
        {
            m_Instance = new SoundManager();
        }

        return m_Instance;
    }

    public void playMainSound(Context context, eSoundsIdentifier soundIdentifier){
        if(m_PlayMusic)
        {
            if(m_MainMediaPlayer != null)
            {
                m_MainMediaPlayer.release();
            }
            m_MainMediaPlayer = MediaPlayer.create(context, soundIdentifier.id);
            m_MainMediaPlayer.start();
        }
    }

    public void playBackgroundSound(Context context, eSoundsIdentifier soundIdentifier){
        if(m_PlayMusic)
        {
            if(m_BackgroundMediaPlayer != null)
            {
                m_BackgroundMediaPlayer.release();
            }
            m_BackgroundMediaPlayer = MediaPlayer.create(context, soundIdentifier.id);
            m_BackgroundMediaPlayer.start();
        }
    }

    public void pauseBackgroundSound(){
        if(m_BackgroundMediaPlayer != null){
            m_BackgroundMediaPlayer.pause();
        }
    }

    public void resumeBackgroundSound(){
        if(m_BackgroundMediaPlayer != null){
            m_BackgroundMediaPlayer.start();
        }
    }

    public boolean isPlayMusic() {
        return m_PlayMusic;
    }

    public void setPlayMusic(boolean i_PlayMusic) {
        m_PlayMusic = i_PlayMusic;
    }
}
