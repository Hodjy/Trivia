package com.example.trivia.model;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.ArrayList;
import java.util.Stack;

/**
 * This class will be used as the hanlder with the singleton design pattern.
 */
public class AppDelayer //TODO rename
{
    static final Handler m_Handler = new Handler(Looper.getMainLooper());

    private AppDelayer()
    {

    }

    public static void DelayApp(int i_Seconds,Runnable i_Runnable)
    {
        int delay = i_Seconds*1000;

        m_Handler.postDelayed(i_Runnable, delay);
    }

    public static void Post(Runnable i_Runnable)
    {
        m_Handler.post(i_Runnable);
    }

    public static void RemoveCallbacks(Runnable i_Runnable)
    {
        m_Handler.removeCallbacks(i_Runnable);
    }

    public static void ClearAllRunnables()
    {
        m_Handler.removeCallbacksAndMessages(null);
    }
}
