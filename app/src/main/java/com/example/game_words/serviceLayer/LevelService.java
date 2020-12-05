package com.example.game_words.serviceLayer;

import com.example.game_words.businessLayer.Level;
import com.example.game_words.dataAccessLayer.LevelDao;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class LevelService {

    public List<Level> getAllLevels(File file) throws IOException, ClassNotFoundException {
        LevelDao dao = new LevelDao(file);
        return dao.getAllLevels(file);
    }
}
