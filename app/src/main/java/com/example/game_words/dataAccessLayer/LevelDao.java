package com.example.game_words.dataAccessLayer;


import com.example.game_words.businessLayer.Level;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import com.example.game_words.businessLayer.Level;

public class LevelDao {
    private File filePath;

    public LevelDao(File f) {
        this.filePath = f;
    }

    public List<Level> getAllLevels(File f) throws IOException, ClassNotFoundException {
        List<Level> levelList = new ArrayList<>();
        List<Level> newLevelList = new ArrayList<>();
        Level level = new Level();
        int number = 1;
        level.setNumber(number);

        List<Character> charList = new ArrayList<>();
        charList.add('C');
        charList.add('W');
        charList.add('N');
        charList.add('E');
        charList.add('I');
        charList.add('A');
        level.setLettersList(charList);

        List<String> wordsList = new ArrayList<>();
        wordsList.add("ace");
        wordsList.add("anc");
        wordsList.add("awe");
        wordsList.add("awn");
        wordsList.add("can");
        wordsList.add("caw");
        wordsList.add("cia");
        wordsList.add("ice");
        wordsList.add("inc");
        wordsList.add("new");
        wordsList.add("wan");
        wordsList.add("wen");
        wordsList.add("win");
        wordsList.add("acne");
        wordsList.add("anew");
        wordsList.add("cane");
        wordsList.add("inca");
        wordsList.add("nice");
        wordsList.add("wain");
        wordsList.add("wane");
        wordsList.add("wean");
        wordsList.add("wine");
        wordsList.add("wince");
        level.setWordsListr(wordsList);

        levelList.add(level);


        try {
            FileOutputStream fileOut = new FileOutputStream(f);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(levelList);
            out.close();
            fileOut.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

         //Let's deserialize an Object
        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            newLevelList = (ArrayList<Level>)in.readObject();
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return levelList;
    }
}
