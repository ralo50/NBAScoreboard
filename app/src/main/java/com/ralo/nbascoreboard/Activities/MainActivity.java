package com.ralo.nbascoreboard.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ralo.nbascoreboard.Adapters.GameAdapter;
import com.ralo.nbascoreboard.R;
import com.ralo.nbascoreboard.Utils.Game;
import com.ralo.nbascoreboard.Utils.JsonParser;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public TextView textView;
    public String url;
    public String dateUrl;
    public DatePicker datePicker;
    public JsonParser gameStats;
    RecyclerView myView;
    ArrayList<String> myValues;
    ArrayList<Game> gameArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();
        setUrl();





    }


    private void setViews(){
        textView = findViewById(R.id.textView);
        myView =  findViewById(R.id.recyclerview);
        myValues = new ArrayList<>();
        gameArrayList = new ArrayList<>();

        Game game = new Game();
        game.setAwayTeamImage(R.drawable.gsw);
        game.setAwayTeamName("gsw");
        game.setAwayTeamScore(105);
        game.setAwayTeamWins(4);

        game.setHomeTeamImage(R.drawable.hou);
        game.setHomeTeamName("hou");
        game.setHomeTeamScore(127);
        game.setHomeTeamWins(2);
        game.setNugget("This actually works");


        gameArrayList.add(game);

        game = new Game();
        game.setAwayTeamImage(R.drawable.hou);
        game.setAwayTeamName("lal");
        game.setAwayTeamScore(92);
        game.setAwayTeamWins(4);

        game.setHomeTeamImage(R.drawable.gsw);
        game.setHomeTeamName("dal");
        game.setHomeTeamScore(88);
        game.setHomeTeamWins(2);

        gameArrayList.add(game);
        gameArrayList.add(game);
        gameArrayList.add(game);


        //Toast.makeText(this, gameArrayList.get(0).getAwayTeamName(), Toast.LENGTH_SHORT).show();

        myValues.add("Golden State");
        myValues.add("Houston");
        myValues.add("Los Angeles");
        myValues.add("Dalas");


        GameAdapter adapter = new GameAdapter(gameArrayList);
        myView.setHasFixedSize(true);
        myView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        myView.setLayoutManager(llm);

    }

    private void setUrl(){
        dateUrl = "20180516";





        url = "http://data.nba.net/10s/prod/v1/" + dateUrl + "/scoreboard.json";
        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                gameStats = new JsonParser(response);


                textView.setText(gameStats.getHomeTeamScore() +  gameStats.getHomeTeamName());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(objectRequest);
    }
}
