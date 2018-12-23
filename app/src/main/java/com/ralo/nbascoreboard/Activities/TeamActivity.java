package com.ralo.nbascoreboard.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.ralo.nbascoreboard.R;

public class TeamActivity extends BaseActivity {
    private ImageView imageView;
    private int awayTeamLogoImageView;
    private int homeTeamLogoImageView;
    private Intent intent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        getIntents();
        setupViews();
    }

    private void setupViews() {
        if (intent.getBooleanExtra("isHome", false)) {
            imageView = findViewById(R.id.teamLogoHome);
            imageView.setImageResource(homeTeamLogoImageView);
        } else {
            imageView = findViewById(R.id.teamLogoAway);
            imageView.setImageResource(awayTeamLogoImageView);
        }
    }

    private void getIntents() {
        intent = getIntent();
        awayTeamLogoImageView = intent.getIntExtra("awayTeamLogoImageView", 0);
        homeTeamLogoImageView = intent.getIntExtra("homeTeamLogoImageView", 0);
    }
}
