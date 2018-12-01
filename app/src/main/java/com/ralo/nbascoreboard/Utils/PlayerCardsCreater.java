package com.ralo.nbascoreboard.Utils;

import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;

public class PlayerCardsCreater {
    private ArrayList<Player> playerArrayList;
    private JsonPlayerParser jsonPlayerParser;
    private String homeOrAway;

    public PlayerCardsCreater(JSONObject response, String homeOrAway){
        this.jsonPlayerParser = new JsonPlayerParser(response);
        playerArrayList = new ArrayList<>();
        this.homeOrAway = homeOrAway;
    }


    private int getNumberOfPlayers(String homeOrAway){
        return this.jsonPlayerParser.getNumberOfPlayers(homeOrAway);
    }

    public void populateCards(){
        int numberOfPlayers = getNumberOfPlayers(homeOrAway);
        for(int i = 0; i < numberOfPlayers; i++){
            Player player = new Player();
            if(jsonPlayerParser.hasPlayerPlayed(homeOrAway, i)){
                Log.d(jsonPlayerParser.getPlayerTeamStatsString("minutes", i, homeOrAway), String.valueOf(i));
                player.setLastName(jsonPlayerParser.getPlayerTeamStatsString("last_name", i, homeOrAway));
                player.setPoints(jsonPlayerParser.getPlayerTeamStatsInt("points", i, homeOrAway));
                player.setReboundsTotal(jsonPlayerParser.getPlayerTeamStatsInt("rebounds_offensive", i, homeOrAway) +
                        jsonPlayerParser.getPlayerTeamStatsInt("rebounds_defensive", i, homeOrAway));
                player.setAssists(jsonPlayerParser.getPlayerTeamStatsInt("assists", i, homeOrAway));
                player.setFieldGoalsMade(jsonPlayerParser.getPlayerTeamStatsInt("field_goals_made", i, homeOrAway));
                player.setFieldGoalsAttempted(jsonPlayerParser.getPlayerTeamStatsInt("field_goals_attempted", i, homeOrAway));
                player.setFreeThrowsMade(jsonPlayerParser.getPlayerTeamStatsInt("free_throws_made", i, homeOrAway));
                player.setFreeThrowsAttempted(jsonPlayerParser.getPlayerTeamStatsInt("free_throws_attempted", i, homeOrAway));
                player.setThreePointersMade(jsonPlayerParser.getPlayerTeamStatsInt("three_pointers_made", i, homeOrAway));
                player.setThreePointersAttempted(jsonPlayerParser.getPlayerTeamStatsInt("three_pointers_attempted", i, homeOrAway));
                player.setMinutesPlayed(jsonPlayerParser.getPlayerTeamStatsInt("minutes", i, homeOrAway));

                playerArrayList.add(player);

            }
        }
    }

    public ArrayList<Player> getPlayerArrayList(){
        return playerArrayList;
    }

}
