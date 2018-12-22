package com.ralo.nbascoreboard.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.ralo.nbascoreboard.Fragments.MainFragments.GameFragment;
import com.ralo.nbascoreboard.R;

public class GameActivity extends BaseActivity {
    public static String gameDate;
    public static String gameId;
    public static String homeTeamWins;
    public static String awayTeamWins;
    public static String homeTeamName;
    public static String awayTeamName;
    public static boolean isGameActivated;
    public static boolean isGameOver;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        GameFragment gameFragment = new GameFragment();
        gameFragment.setArguments(extras);

        gameDate = extras.getString("gameDate", "");
        gameId = extras.getString("gameId", "");
        isGameActivated = extras.getBoolean("isGameActivated");
        isGameOver = extras.getBoolean("isGameOver");
        Log.d("gameDate", gameDate);
        Log.d("gameId", gameId);
        homeTeamWins = extras.getString("homeTeamWins");
        awayTeamWins = extras.getString("awayTeamWins");
        homeTeamName = extras.getString("homeTeamName");
        awayTeamName = extras.getString("awayTeamName");

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, gameFragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
