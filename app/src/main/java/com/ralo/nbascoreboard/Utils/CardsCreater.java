package com.ralo.nbascoreboard.Utils;

import com.ralo.nbascoreboard.Adapters.GameAdapter;

import org.json.JSONObject;

import java.util.ArrayList;

public class CardsCreater {

    private ArrayList<Game> gameArrayList;
    private Game game;
    private GameAdapter gameAdapter;
    private int numberOfCards;
    private JSONObject gamestats;

    public CardsCreater (JSONObject response){
        this.gamestats = response;
    }


    public ArrayList<Game> getGameArrayList() {
        return gameArrayList;
    }
}
