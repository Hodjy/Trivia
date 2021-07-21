package com.example.trivia.model;

import android.os.Handler;
import android.os.Looper;

/**
 * This class will be used as the hanlder with the singleton design pattern.
 */
public class AppDelayer //TODO rename
{
    static final Handler handler = new Handler(Looper.getMainLooper());

    private AppDelayer()
    {

    }

    public static void DelayApp(int i_Seconds,Runnable i_Runnable)
    {
        int delay = i_Seconds*1000;

        handler.postDelayed(i_Runnable, delay);
    }

    public static void Post(Runnable i_Runnable)
    {
        handler.post(i_Runnable);
    }

    public static void RemoveCallbacks(Runnable i_Runnable)
    {
        handler.removeCallbacks(i_Runnable);
    }

}
