package com.example.trivia;

import android.os.Handler;
import android.os.Looper;

public class AppDelayer
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
