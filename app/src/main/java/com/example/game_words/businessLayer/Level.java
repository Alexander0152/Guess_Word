package com.example.game_words.businessLayer;

import java.io.Serializable;
import java.util.List;

public class Level implements Serializable {
    private int number;
    private List<Character> lettersList;
    private List<String> wordsList;

    public int getNumber(){
        return this.number;
    }
    public void setNumber(int number){
        this.number = number;
    }

    public List<Character> getLettersList(){
        return this.lettersList;
    }
    public void setLettersList(List<Character> lettersList){
        this.lettersList = lettersList;
    }

    public List<String> getWordsList(){
        return this.wordsList;
    }
    public void setWordsList(List<String> wordsList){
        this.wordsList = wordsList;
    }
}
