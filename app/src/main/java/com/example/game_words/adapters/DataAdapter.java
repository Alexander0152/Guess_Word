package com.example.game_words.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends ArrayAdapter<String> {

    private static String[] mContacts = new String[24];
//    = { "Рыжик", "Барсик", "Мурзик",
//            "Мурка", "Васька", "Полоса", "Матрос", "Лизка", "Томоси",
//            "Бегемо", "Ческий", "Дивуар", "Тигра", "Лаура", "Рыжик", "Барсик", "Мурзик",
//            "Мурка", "Васька", "Полоса", "Матрос", "Лизка", "Томоси",
//            "Бегемо" };

    Context mContext;

    // Конструктор
    public DataAdapter(Context context, int textViewResourceId, List<String> words) {
        super(context, textViewResourceId, mContacts);
        int count = 0;
        for(String word: words) {
            mContacts[count] = word;
            count++;
        }
        // TODO Auto-generated constructor stub
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        TextView label = (TextView) convertView;

        if (convertView == null) {
            convertView = new TextView(mContext);
            label = (TextView) convertView;
        }
        label.setText(mContacts[position]);
        return (convertView);
    }

    // возвращает содержимое выделенного элемента списка
    public String getItem(int position) {
        return mContacts[position];
    }

}