package com.ralo.nbascoreboard.Utils.CardCreaters;

import com.ralo.nbascoreboard.Utils.DataClasses.GameStat;
import com.ralo.nbascoreboard.Utils.JsonParsers.JsonTeamParser;

import org.json.JSONObject;

import java.util.ArrayList;

public class GameStatCardsCreater {

    private ArrayList<GameStat> gameStatArrayList;
    private JsonTeamParser jsonTeamParser;
    private String homeOrAway;
    private static final int NUMBER_OF_STATS = 9;

    public GameStatCardsCreater(JSONObject response, String homeOrAway) {
        this.jsonTeamParser = new JsonTeamParser(response);
        gameStatArrayList = new ArrayList<>();
        this.homeOrAway = homeOrAway;
    }

    private int getNumberOfStats() {
        return NUMBER_OF_STATS;
    }

    public void populateCards() {
        int numberOfStats = getNumberOfStats();
        for (int i = 0; i < numberOfStats; i++) {
            GameStat gameStat = new GameStat();
            gameStat.setAwayTeamStat("awayTeamStat");
            gameStat.setStatDescription("statDescription");
            gameStat.setHomeTeamStat("homeTeamStat");

            gameStatArrayList.add(gameStat);
        }
    }

    public ArrayList<GameStat> getGameStatArrayList() {
        return gameStatArrayList;
    }

    public ArrayList<String> getGameStatNames() {
        ArrayList<String> names = new ArrayList<>();
        names.add("field_goals_made");
        names.add("field_goals_attempted");
        names.add("field_goals_percentage");
        names.add("free_throws_made");
        names.add("free_throws_attempted");
        names.add("free_throws_percentage");
        names.add("three_pointers_made");
        names.add("three_pointers_attempted");
        names.add("three_pointers_percentage");
        names.add("rebounds_offensive");
        names.add("rebounds_defensive");
        names.add("assists");
        names.add("fouls");
        names.add("steals");
        names.add("turnovers");
        names.add("blocks");
        return names;
    }
}
