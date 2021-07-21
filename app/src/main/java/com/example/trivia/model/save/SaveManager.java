package com.example.trivia.model.save;

import android.content.Context;

import com.example.trivia.ApplicationContext;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static android.content.Context.MODE_PRIVATE;
//TODO probabaly needs to get a UserScoreAdapter in order to save a list of UserScores.
public class SaveManager
{
    public static void SaveToFile(String i_FileName,
                                  UserScore i_DataToSave)
    {
        try {
            FileOutputStream fos = ApplicationContext.getContext()
                    .openFileOutput(i_FileName, MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(i_DataToSave);
            oos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // returns a
    public static UserScore LoadFromFile(String i_FileName)
    {
        UserScore userData = new UserScore();
        try {
            FileInputStream fis = ApplicationContext.getContext().openFileInput(i_FileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            userData = (UserScore)ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return userData;
    }

}
