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
        level.setWordsList(wordsList);

        levelList.add(level);

        Level level1 = new Level();

        level1.setNumber(1);

        List<Character> charList1 = new ArrayList<>();
        charList1.add('F');
        charList1.add('R');
        charList1.add('W');
        charList1.add('A');
        charList1.add('E');
        charList1.add('E');
        level1.setLettersList(charList1);

        List<String> wordsList1 = new ArrayList<>();
        wordsList1.add("are");
        wordsList1.add("awe");
        wordsList1.add("ear");
        wordsList1.add("era");
        wordsList1.add("ere");
        wordsList1.add("ewe");
        wordsList1.add("far");
        wordsList1.add("fee");
        wordsList1.add("few");
        wordsList1.add("raw");
        wordsList1.add("war");
        wordsList1.add("wee");
        wordsList1.add("ewer");
        wordsList1.add("fare");
        wordsList1.add("fear");
        wordsList1.add("free");
        wordsList1.add("reef");
        wordsList1.add("ware");
        wordsList1.add("wear");
        wordsList1.add("were");
        wordsList1.add("fewer");
        wordsList1.add("wafer");
        level1.setWordsList(wordsList1);

        levelList.add(level1);

        Level level2 = new Level();
        level2.setNumber(2);
        List<Character> charList2 = new ArrayList<>();
        charList2.add('J');
        charList2.add('T');
        charList2.add('S');
        charList2.add('E');
        charList2.add('U');
        charList2.add('A');
        level2.setLettersList(charList2);

        List<String> wordsList2 = new ArrayList<>();
        wordsList2.add("sea");

        level2.setWordsList(wordsList2);

        levelList.add(level2);

        List<Level> newLevelList = new ArrayList<>();
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
