package com.ralo.nbascoreboard.Utils;

import org.json.JSONObject;
import java.util.ArrayList;

public class CardsCreater {

    private ArrayList<Game> gameArrayList;
    private JsonParser jsonParser;

    public CardsCreater (JSONObject response){
        this.jsonParser = new JsonParser(response);
        gameArrayList = new ArrayList<>();
    }


    private int getNumberOfGames(){
        return this.jsonParser.getNumberOfGames();
    }

    public void populateCards(){
        for(int i = 0; i < getNumberOfGames(); i++){
            Game game = new Game();
            this.jsonParser.setCurrentGame(i);
            game.setHomeTeamName(jsonParser.getHomeTeamName());
            game.setAwayTeamName(jsonParser.getAwayTeamName());
            game.setHomeTeamScore(jsonParser.getHomeTeamScore());
            game.setAwayTeamScore(jsonParser.getAwayTeamScore());
            game.setHomeTeamWins(jsonParser.getHomeTeamWins());
            game.setAwayTeamWins(jsonParser.getAwayTeamWins());
            game.setHomeTeamImage(jsonParser.getHomeTeamImage());
            game.setAwayTeamImage(jsonParser.getAwayTeamImage());
            game.setNugget(jsonParser.getNugget());
            game.setGameTime(jsonParser.getGameTime());

            game.setGameId(jsonParser.getGameId());
            game.setGameDate(jsonParser.getGameDate());

            gameArrayList.add(game);
        }
    }

    public ArrayList<Game> getGameArrayList(){
        return gameArrayList;
    }

    public boolean isGameNight(){
        return jsonParser.isGameNight();
    }
}
