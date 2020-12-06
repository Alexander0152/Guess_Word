package com.example.game_words;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.game_words.adapters.DataAdapter;
import com.example.game_words.businessLayer.Level;
import com.example.game_words.serviceLayer.LevelService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    int level = 0;
    String fileName = "/List.txt";
    List<Level> levelList = null;
    List<String> guesedWords = new ArrayList<>();

    private TextView mSelectText;
    private DataAdapter mAdapter;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        TextView textVieResult = (TextView)findViewById(R.id.textViewResult);
        outState.putString("result",textVieResult.getText().toString());

        outState.putStringArrayList("guessedWords", (ArrayList<String>) guesedWords);
        outState.putInt("level", level);

        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);

        outState.putCharSequence("button1Text", button1.getText());
        outState.putCharSequence("button2Text", button2.getText());
        outState.putCharSequence("button3Text", button3.getText());
        outState.putCharSequence("button4Text", button4.getText());
        outState.putCharSequence("button5Text", button5.getText());
        outState.putCharSequence("button6Text", button6.getText());

        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onRestoreInstanceState(savedInstanceState);

        TextView textVieResult = (TextView)findViewById(R.id.textViewResult);
        textVieResult.setText(savedInstanceState.getString("result"));

        guesedWords = savedInstanceState.getStringArrayList("guessedWords");
        mSelectText = (TextView) findViewById(R.id.info);
        final GridView g = (GridView) findViewById(R.id.gridViewGuessedWords);
        mAdapter = new DataAdapter(getApplicationContext(),
                android.R.layout.simple_list_item_1, guesedWords);
        g.setAdapter(mAdapter);

        level = savedInstanceState.getInt("level");

        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);

        button1.setText(savedInstanceState.getCharSequence("button1Text"));
        button2.setText(savedInstanceState.getCharSequence("button2Text"));
        button3.setText(savedInstanceState.getCharSequence("button3Text"));
        button4.setText(savedInstanceState.getCharSequence("button4Text"));
        button5.setText(savedInstanceState.getCharSequence("button5Text"));
        button6.setText(savedInstanceState.getCharSequence("button6Text"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu); //запуск меню
        return true;//super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.settingsMenuItem:
                //показать диалоговое окно с настройкой уровня игры
                break;
            case R.id.showHistoryMenuItem:
                //показать активити-визитку
                break;
            case R.id.settingsMenuHome:
                showBackHomeDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position,
                               long id) {
//        mSelectText.setText("Выбранный элемент: " + mAdapter.getItem(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
//        mSelectText.setText("Выбранный элемент: ничего");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            getLevels(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        final Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);

        final Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);

        final Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);

        final Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(this);

        final Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(this);

        final Button button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(this);

        final ImageButton buttonErase = (ImageButton) findViewById(R.id.buttonErase);
        buttonErase.setOnClickListener(this);

        final ImageButton buttonDelete = (ImageButton) findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(this);

        final Button buttonEnter = (Button) findViewById(R.id.buttonEnter);
        buttonEnter.setOnClickListener(this);

        if(savedInstanceState == null) {
            showChooseLevelDialog();
        }

        mSelectText = (TextView) findViewById(R.id.info);
        final GridView g = (GridView) findViewById(R.id.gridViewGuessedWords);
        mAdapter = new DataAdapter(getApplicationContext(),
                android.R.layout.simple_list_item_1, guesedWords);
        g.setAdapter(mAdapter);
//        g.setOnItemSelectedListener(this);
//        g.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View v,
//                                    int position, long id) {
//                // TODO Auto-generated method stub
//                mSelectText.setText("Выбранный элемент: "
//                        + mAdapter.getItem(position));
//            }
//        });
    }

    public void showChooseLevelDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.chooseLevel);

        builder.setItems(R.array.level_array, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                int selectedLevel = 0;
                // The 'which' argument contains the index position of the selected item
                if (which == 0) {
                    selectedLevel = 0;
                } else if (which == 1) {
                    selectedLevel = 1;
                } else if (which == 2) {
                    selectedLevel = 2;
                } else if (which == 3) {
                    selectedLevel = 3;
                } else if (which == 4) {
                    selectedLevel = 4;
                }
                level = selectedLevel;
                fillButtonsWithLetters(levelList.get(level));
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showPassedLevelDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.congratulations);
        builder.setMessage(R.string.theLevelPassed);
        builder.setPositiveButton(R.string.nextLevel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
        builder.setNegativeButton(R.string.exit, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                System.exit(0);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void showBackHomeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.home);
        builder.setMessage("Are you sure you want to exit? All progress will be lost");
        builder.setPositiveButton(R.string.home_button, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
        builder.setNegativeButton(R.string.back_to_game, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void updateTable() {
        final GridView g = (GridView) findViewById(R.id.gridViewGuessedWords);
        mAdapter = new DataAdapter(getApplicationContext(),
                android.R.layout.simple_list_item_1, guesedWords);
        g.setAdapter(mAdapter);
    }

    public void fillButtonsWithLetters(Level level) {

        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);

        List<Character> lettersList = level.getLettersList();

        button1.setText(lettersList.get(0).toString());
        button2.setText(lettersList.get(1).toString());
        button3.setText(lettersList.get(2).toString());
        button4.setText(lettersList.get(3).toString());
        button5.setText(lettersList.get(4).toString());
        button6.setText(lettersList.get(5).toString());
    }

    public void getLevels(String fileName) throws IOException, ClassNotFoundException {

        String fullFilePath = this.getFilesDir().getPath().toString() + fileName;
        File file = new File(fullFilePath);

        LevelService levelService = new LevelService();
        levelList = levelService.getAllLevels(file);
    }

    @Override
    public void onClick(View v) {
        TextView textViewResult = (TextView) findViewById(R.id.textViewResult);
        String result = textViewResult.getText().toString();

        String buttonText = new String();
        String newResult = new String();

        switch (v.getId()) {
            case R.id.button1:
//                showPassedLevelDialog();
                Button button1 = (Button) v;
                buttonText = button1.getText().toString();
                newResult = result + buttonText;
                textViewResult.setText(newResult);
                button1.setEnabled(false);
                button1.setText("");
                break;
            case R.id.button2:
                Button button2 = (Button) v;
                buttonText = button2.getText().toString();
                newResult = result + buttonText;
                textViewResult.setText(newResult);
                button2.setEnabled(false);
                button2.setText("");
                break;
            case R.id.button3:
                Button button3 = (Button) v;
                buttonText = button3.getText().toString();
                newResult = result + buttonText;
                textViewResult.setText(newResult);
                button3.setEnabled(false);
                button3.setText("");
                break;
            case R.id.button4:
                Button button4 = (Button) v;
                buttonText = button4.getText().toString();
                newResult = result + buttonText;
                textViewResult.setText(newResult);
                button4.setEnabled(false);
                button4.setText("");
                break;
            case R.id.button5:
                Button button5 = (Button) v;
                buttonText = button5.getText().toString();
                newResult = result + buttonText;
                textViewResult.setText(newResult);
                button5.setEnabled(false);
                button5.setText("");
                break;
            case R.id.button6:
                Button button6 = (Button) v;
                buttonText = button6.getText().toString();
                newResult = result + buttonText;
                textViewResult.setText(newResult);
                button6.setEnabled(false);
                button6.setText("");
                break;
            case R.id.buttonErase:
                if (result != null && result.length() > 0) {
                    String erasedLetter = result.substring(result.length() - 1, result.length());
                    newResult = result.substring(0, result.length() - 1);
                    textViewResult.setText(newResult);
                    returnTextToButton(erasedLetter);
                }
                break;
            case R.id.buttonDelete:
                textViewResult.setText("");
                fillButtonsWithLetters(levelList.get(level));
                makeLettersButtonsEnable();
                break;
            case R.id.buttonEnter:
                if (checkIfWordExist(result.toLowerCase()) && !checkIfWordAlreadyGuessed(result)){
                    showGuessTost();
                    textViewResult.setText("");
                    fillButtonsWithLetters(levelList.get(level));
                    makeLettersButtonsEnable();
                    guesedWords.add(result);
                    updateTable();
                    checkLevelPassed();
                    break;
                } else {
                    showNoSuchWordTost();
                }
                break;
            default:
                break;
        }
    }

    public void checkLevelPassed(){
        if(guesedWords.size() == levelList.get(level).getWordsList().size()){
            showPassedLevelDialog();
        }
        else return;
    }
    public void showGuessTost() {
        CharSequence text = getString(R.string.guessTost);
        int duration = Toast.LENGTH_SHORT;

        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void showNoSuchWordTost() {
        CharSequence text = getString(R.string.noSuchWordTost);
        int duration = Toast.LENGTH_SHORT;

        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public boolean checkIfWordExist(String word) {
        List<String> wordsList = levelList.get(level).getWordsList();
        for (String str : wordsList) {
            if (str != null && str.compareTo(word) == 0) {
                return true;
            }
        }
        return false;
    }
    public boolean checkIfWordAlreadyGuessed(String word) {
        for (String str : guesedWords) {
            if (str.compareTo(word) == 0) {
                return true;
            }
        }
        return false;
    }

    public void returnTextToButton(String text) {
        int count = 0;
        List<Character> lettersList = levelList.get(level).getLettersList();
        for (Character ch : lettersList) {
            if (ch.toString().compareTo(text) == 0) {
                break;
            }
            count++;
        }
        switch (count) {
            case 0:
                Button button1 = (Button) findViewById(R.id.button1);
                button1.setText(text);
                button1.setEnabled(true);
                break;
            case 1:
                Button button2 = (Button) findViewById(R.id.button2);
                button2.setText(text);
                button2.setEnabled(true);
                break;
            case 2:
                Button button3 = (Button) findViewById(R.id.button3);
                button3.setText(text);
                button3.setEnabled(true);
                break;
            case 3:
                Button button4 = (Button) findViewById(R.id.button4);
                button4.setText(text);
                button4.setEnabled(true);
                break;
            case 4:
                Button button5 = (Button) findViewById(R.id.button5);
                button5.setText(text);
                button5.setEnabled(true);
                break;
            case 5:
                Button button6 = (Button) findViewById(R.id.button6);
                button6.setText(text);
                button6.setEnabled(true);
                break;
            default:
                break;
        }
    }

    public void makeLettersButtonsEnable() {
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);

        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
        button5.setEnabled(true);
        button6.setEnabled(true);
    }
}