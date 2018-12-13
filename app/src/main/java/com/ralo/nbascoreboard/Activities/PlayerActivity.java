package com.ralo.nbascoreboard.Activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.ralo.nbascoreboard.R;
import com.ralo.nbascoreboard.Utils.Player;

public class PlayerActivity extends BaseActivity {

    TextView playerName;
    String playerNameString;
    Intent intent;
    Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        getIntents();
        setupViews();
    }

    private void setupViews() {
        playerName = findViewById(R.id.playerName);
        playerNameString = player.getFirstName() + " " + player.getLastName();
        playerName.setText(playerNameString);
    }

    private void getIntents() {
        intent = getIntent();
        player = (Player) intent.getSerializableExtra("Player");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
