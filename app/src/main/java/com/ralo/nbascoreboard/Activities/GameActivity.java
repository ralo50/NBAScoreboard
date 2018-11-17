package com.ralo.nbascoreboard.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.ralo.nbascoreboard.Fragments.GameFragment;
import com.ralo.nbascoreboard.R;

public class GameActivity extends AppCompatActivity {




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        GameFragment gameFragment = new GameFragment();
        gameFragment.setArguments(extras);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, gameFragment)
                    .commit();
        }
    }


}
