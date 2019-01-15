package com.ralo.nbascoreboard.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.ralo.nbascoreboard.R;

public class TeamActivity extends BaseActivity {
    private ImageView imageView;
    private int awayTeamLogoImageView;
    private int homeTeamLogoImageView;
    private int teamId;
    private String url;

    private Intent intent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        getIntents();
        setupViews();
    }

    private void getIntents() {
        intent = getIntent();
        teamId = intent.getIntExtra("teamId", 0);
        awayTeamLogoImageView = intent.getIntExtra("awayTeamLogoImageView", 0);
        homeTeamLogoImageView = intent.getIntExtra("homeTeamLogoImageView", 0);
    }

    private void setupViews() {
        if (intent.getBooleanExtra("isHome", false)) {
            imageView = findViewById(R.id.teamLogoHome);
            imageView.setImageResource(homeTeamLogoImageView);
        } else {
            imageView = findViewById(R.id.teamLogoAway);
            imageView.setImageResource(awayTeamLogoImageView);
        }
        url = "https://stats.nba.com/stats/teamyearbyyearstats/?teamId=" + teamId + "&leagueId=00&seasonType=Regular+Season&perMode=PerGame";
    }
}
