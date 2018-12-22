package com.ralo.nbascoreboard.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.ralo.nbascoreboard.R;
import com.ralo.nbascoreboard.Utils.DataClasses.Player;
import com.squareup.picasso.Picasso;

public class PlayerActivity extends BaseActivity {

    TextView playerName;
    ImageView playerImage;
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

    private void getIntents() {
        intent = getIntent();
        player = (Player) intent.getSerializableExtra("Player");
    }

    private void setupViews() {
        playerName = findViewById(R.id.playerName);
        playerNameString = player.getFirstName() + " " + player.getLastName();
        playerName.setText(playerNameString);
        playerImage = findViewById(R.id.playerImage);
        Picasso.get().load("https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/" + player.getPlayerId() + ".png").into(playerImage);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
