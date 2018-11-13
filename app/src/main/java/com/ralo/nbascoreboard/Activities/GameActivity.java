package com.ralo.nbascoreboard.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String gameDate = extras.getString("gameDate");
        String gameId = extras.getString("gameId");
        Log.d("hi", "gamedate:" + gameDate);
        Log.d("hi", "gameid:" + gameId);
    }
}
