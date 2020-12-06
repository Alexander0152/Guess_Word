package com.example.game_words;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        getDataFromDatabase();
    }

        public void getDataFromDatabase(){
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS achievements (date_time TEXT, level INTEGER)");
        Cursor query = db.rawQuery("SELECT * FROM achievements;", null);
        TextView textView = (TextView) findViewById(R.id.textViewHistory);
        if(query.moveToFirst()){
            do{
                String name = query.getString(0);
                int level = query.getInt(1);
                String info = "DATE:  " + name + "  LEVEL: " + level + "\n\n";
                textView.append(info);
            }
            while(query.moveToNext());
        }
        query.close();
        db.close();
    }
}
