package com.ralo.nbascoreboard.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ralo.nbascoreboard.NbaApp;
import com.ralo.nbascoreboard.R;
import com.ralo.nbascoreboard.Utils.JsonParsers.JsonTeamStatParser;

import org.json.JSONException;
import org.json.JSONObject;

public class TeamActivity extends BaseActivity {
    private ImageView imageView;
    private int awayTeamLogoImageView;
    private int homeTeamLogoImageView;
    private int teamId;
    private String url;
    private JSONObject teamDetailsJsonObject;
    private JsonTeamStatParser jsonTeamStatParser;
    private TextView teamNameTextField;

    private static final int TEAM_ID = 0;
    private static final int TEAM_CITY = 1;
    private static final int TEAM_NAME = 2;
    private static final int YEAR = 3;
    private static final int GP = 4;
    private static final int WINS = 5;
    private static final int LOSSES = 6;
    private static final int WIN_PCT = 7;
    private static final int CONF_RANK = 8;
    private static final int DIV_RANK = 9;
    private static final int PO_WINS = 10;
    private static final int PO_LOSSES = 11;
    private static final int CONF_COUNT = 12;
    private static final int DIV_COUNT = 13;
    private static final int NBA_FINALS_APPEARANCE = 14;
    private static final int FGM = 15;
    private static final int FGA = 16;
    private static final int FG_PCT = 17;
    private static final int FG3M = 18;
    private static final int FG3A = 19;
    private static final int FG3_PCT = 20;
    private static final int FTM = 21;
    private static final int FTA = 22;
    private static final int FT_PCT = 23;
    private static final int OREB = 24;
    private static final int DREB = 25;
    private static final int REB = 26;
    private static final int AST = 27;
    private static final int PF = 28;
    private static final int STL = 29;
    private static final int TOV = 30;
    private static final int BLK = 31;
    private static final int PTS = 32;
    private static final int PTS_RANK = 33;
;
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
        teamNameTextField = findViewById(R.id.team_name);
        if (intent.getBooleanExtra("isHome", false)) {
            imageView = findViewById(R.id.teamLogoHome);
            imageView.setImageResource(homeTeamLogoImageView);
        } else {
            imageView = findViewById(R.id.teamLogoAway);
            imageView.setImageResource(awayTeamLogoImageView);
        }
        getTeamDetails();
    }

    private void getTeamDetails() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                url = "https://stats.nba.com/stats/teamyearbyyearstats/?teamId=" + teamId + "&leagueId=00&seasonType=Regular+Season&perMode=PerGame";
                final RequestQueue requestQueue = Volley.newRequestQueue(NbaApp.getCurrentActivity());
                JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        teamDetailsJsonObject = response;
                        jsonTeamStatParser = new JsonTeamStatParser(teamDetailsJsonObject);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                populateHeaderDetails(jsonTeamStatParser);
                            }
                        });
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
                requestQueue.add(objectRequest);
            }
        }).start();
    }

    private void populateHeaderDetails(JsonTeamStatParser jsonTeamStatParser) {
        String teamNameString = jsonTeamStatParser.getTeamStat(TEAM_CITY) + " " + jsonTeamStatParser.getTeamStat(TEAM_NAME);
        teamNameTextField.setText(teamNameString);
    }
}
