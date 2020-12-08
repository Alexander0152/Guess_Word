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

    public List<Level> getAllLevels(File file) throws IOException, ClassNotFoundException {
//        List<Level> levelList = new ArrayList<>();
//
//        Level level = new Level();
//        int number = 1;
//        level.setNumber(number);
//
//        List<Character> charList = new ArrayList<>();
//        charList.add('C');
//        charList.add('W');
//        charList.add('N');
//        charList.add('E');
//        charList.add('I');
//        charList.add('A');
//        level.setLettersList(charList);
//
//        List<String> wordsList = new ArrayList<>();
//        wordsList.add("ace");
//        wordsList.add("anc");
//        wordsList.add("awe");
//        wordsList.add("awn");
//        wordsList.add("can");
//        wordsList.add("caw");
//        wordsList.add("cia");
//        wordsList.add("ice");
//        wordsList.add("inc");
//        wordsList.add("new");
//        wordsList.add("wan");
//        wordsList.add("wen");
//        wordsList.add("win");
//        wordsList.add("acne");
//        wordsList.add("anew");
//        wordsList.add("cane");
//        wordsList.add("inca");
//        wordsList.add("nice");
//        wordsList.add("wain");
//        wordsList.add("wane");
//        wordsList.add("wean");
//        wordsList.add("wine");
//        wordsList.add("wince");
//        level.setWordsList(wordsList);
//
//        levelList.add(level);
//
//        Level level1 = new Level();
//
//        level1.setNumber(1);
//
//        List<Character> charList1 = new ArrayList<>();
//        charList1.add('D');
//        charList1.add('S');
//        charList1.add('H');
//        charList1.add('U');
//        charList1.add('E');
//        charList1.add('A');
//        level1.setLettersList(charList1);
//
//        List<String> wordsList1 = new ArrayList<>();
//        wordsList1.add("ash");
//        wordsList1.add("due");
//        wordsList1.add("eds");
//        wordsList1.add("had");
//        wordsList1.add("has");
//        wordsList1.add("hud");
//        wordsList1.add("hue");
//        wordsList1.add("sad");
//        wordsList1.add("sea");
//        wordsList1.add("she");
//        wordsList1.add("sue");
//        wordsList1.add("usa");
//        wordsList1.add("use");
//        wordsList1.add("dash");
//        wordsList1.add("head");
//        wordsList1.add("hued");
//        wordsList1.add("shad");
//        wordsList1.add("shed");
//        wordsList1.add("used");
//        wordsList1.add("hades");
//        wordsList1.add("shade");
//        level1.setWordsList(wordsList1);
//
//        levelList.add(level1);
//
//        Level level2 = new Level();
//        level2.setNumber(2);
//        List<Character> charList2 = new ArrayList<>();
//        charList2.add('J');
//        charList2.add('T');
//        charList2.add('S');
//        charList2.add('E');
//        charList2.add('U');
//        charList2.add('A');
//        level2.setLettersList(charList2);
//
//        List<String> wordsList2 = new ArrayList<>();
//        wordsList2.add("sea");
//
//        level2.setWordsList(wordsList2);
//
//        levelList.add(level2);
//
        List<Level> newLevelList = new ArrayList<>();
//        try {
//            FileOutputStream fileOut = new FileOutputStream(file);
//            ObjectOutputStream out = new ObjectOutputStream(fileOut);
//            out.writeObject(levelList);
//            out.close();
//            fileOut.close();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

         //Let's deserialize an Object
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            newLevelList = (ArrayList<Level>)in.readObject();
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newLevelList;
    }
}
