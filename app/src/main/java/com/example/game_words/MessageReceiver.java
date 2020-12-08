package com.example.game_words;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MessageReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Message detected: " +
                        intent.getStringExtra("com.example.game_words.broadcast.Message"),
                Toast.LENGTH_LONG).show();
    }
}
