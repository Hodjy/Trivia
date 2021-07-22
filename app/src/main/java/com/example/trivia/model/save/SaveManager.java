package com.example.trivia.model.save;

import com.example.trivia.ApplicationContext;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
//TODO probabaly needs to get a UserScoreAdapter in order to save a list of UserScores.
public class SaveManager
{
    private static final String FILE_NAME = "SavedScores";

    public static void SaveToFile(ArrayList<UserScore> i_DataToSave)
    {
        try {
            FileOutputStream fos = ApplicationContext.getContext()
                    .openFileOutput(FILE_NAME, MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(i_DataToSave);
            oos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<UserScore> LoadFromFile()
    {
        ArrayList<UserScore>  userData = new ArrayList<> ();
        try {
            FileInputStream fis = ApplicationContext.getContext().openFileInput(FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            userData = (ArrayList<UserScore>)ois.readObject();
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
