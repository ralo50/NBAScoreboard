package com.ralo.nbascoreboard.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.ralo.nbascoreboard.Fragments.GameFragmentOld;
import com.ralo.nbascoreboard.Fragments.GameFragment;
import com.ralo.nbascoreboard.R;

public class GameActivity extends BaseActivity {
    public static String gameDate;
    public static String gameId;
    public static String homeTeamWins;
    public static String awayTeamWins;
    public static boolean isGameActivated;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        GameFragmentOld gameFragmentOld = new GameFragmentOld();
        gameFragmentOld.setArguments(extras);

        gameDate = extras.getString("gameDate","");
        gameId = extras.getString("gameId","");
        isGameActivated = extras.getBoolean("isGameActivated");
        Log.d("gameDate", gameDate);
        Log.d("gameId", gameId);
        homeTeamWins = extras.getString("homeTeamWins");
        awayTeamWins = extras.getString("awayTeamWins");

        GameFragment gameFragment = new GameFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, gameFragment)
                .commit();
    }


}
