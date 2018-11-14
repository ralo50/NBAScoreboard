package com.ralo.nbascoreboard.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.ralo.nbascoreboard.R;

public class GameActivity extends AppCompatActivity {
    AdView adView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        adView = findViewById(R.id.adView);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String gameDate = extras.getString("gameDate");
        String gameId = extras.getString("gameId");
        Log.d("hi", "gamedate:" + gameDate);
        Log.d("hi", "gameid:" + gameId);
        initAd();
    }

    private void initAd() {
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("8623F0A041C7A59B4C3BA8CAD7B28F64").build();
        adView.loadAd(adRequest);
    }
}
