package com.ralo.nbascoreboard.Utils;

import org.json.JSONObject;
import java.util.ArrayList;

public class GameCardsCreater {

    private ArrayList<Game> gameArrayList;
    private JsonGameParser jsonGameParser;

    public GameCardsCreater(JSONObject response){
        this.jsonGameParser = new JsonGameParser(response);
        gameArrayList = new ArrayList<>();
    }


    private int getNumberOfGames(){
        return this.jsonGameParser.getNumberOfGames();
    }

    public void populateCards(){
        for(int i = 0; i < getNumberOfGames(); i++){
            Game game = new Game();
            this.jsonGameParser.setCurrentGame(i);
            game.setHomeTeamName(jsonGameParser.getHomeTeamName());
            game.setAwayTeamName(jsonGameParser.getAwayTeamName());
            game.setHomeTeamScore(jsonGameParser.getHomeTeamScore());
            game.setAwayTeamScore(jsonGameParser.getAwayTeamScore());
            game.setHomeTeamWins(jsonGameParser.getHomeTeamWins());
            game.setAwayTeamWins(jsonGameParser.getAwayTeamWins());
            game.setHomeTeamImage(jsonGameParser.getHomeTeamImage());
            game.setAwayTeamImage(jsonGameParser.getAwayTeamImage());
            game.setNugget(jsonGameParser.getNugget());
            game.setGameTime(jsonGameParser.getGameTime());
            game.setGameId(jsonGameParser.getGameId());
            game.setGameDate(jsonGameParser.getGameDate());
            game.setGameActive(jsonGameParser.isGameActivated());
            game.setGameOver(jsonGameParser.isGameOverHelper());

            gameArrayList.add(game);
        }
    }

    public ArrayList<Game> getGameArrayList(){
        return gameArrayList;
    }

    public boolean isGameNight(){
        return jsonGameParser.isGameNight();
    }
}
