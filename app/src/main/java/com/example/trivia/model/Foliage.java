package com.example.trivia.model;

import android.net.Uri;

public class Foliage
{
    private final Uri m_ImgUri;
    private final String m_FoliageName;

    Foliage(Uri i_ImgUri, String i_FoliageName)
    {
        m_ImgUri = i_ImgUri;
        m_FoliageName = i_FoliageName;
    }

    public Uri getImgeUri()
    {
        return m_ImgUri;
    }

    public String getFoliageName()
    {
        return m_FoliageName;
    }

}
