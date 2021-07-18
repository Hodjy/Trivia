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

}
