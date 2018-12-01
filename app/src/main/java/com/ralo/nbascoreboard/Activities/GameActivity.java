package com.ralo.nbascoreboard.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.ralo.nbascoreboard.Fragments.GameFragment;
import com.ralo.nbascoreboard.Prototype.Game2Fragment;
import com.ralo.nbascoreboard.R;

public class GameActivity extends BaseActivity {
    public static String gameDate;
    public static String gameId;
    public static String homeTeamWins;
    public static String awayTeamWins;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        GameFragment gameFragment = new GameFragment();
        gameFragment.setArguments(extras);

        gameDate = extras.getString("gameDate","");
        gameId = extras.getString("gameId","");
        homeTeamWins = extras.getString("homeTeamWins");
        awayTeamWins = extras.getString("awayTeamWins");

        Game2Fragment game2Fragment = new Game2Fragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, game2Fragment)
                .commit();

        /*if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, gameFragment)
                    .commit();
        }*/
    }


}
