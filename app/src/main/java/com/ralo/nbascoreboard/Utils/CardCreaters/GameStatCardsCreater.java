package com.ralo.nbascoreboard.Utils.CardCreaters;

import com.ralo.nbascoreboard.Utils.DataClasses.GameStat;
import com.ralo.nbascoreboard.Utils.JsonParsers.JsonGameStatParser;
import com.ralo.nbascoreboard.Utils.JsonParsers.JsonTeamParser;

import org.json.JSONObject;

import java.util.ArrayList;

public class GameStatCardsCreater {

    private ArrayList<GameStat> gameStatArrayList;
    private JsonGameStatParser jsonGameStatParser;
    private static final int NUMBER_OF_STATS = 9;

    public GameStatCardsCreater(JSONObject response) {
        this.jsonGameStatParser = new JsonGameStatParser(response);
        gameStatArrayList = new ArrayList<>();
    }

    private int getNumberOfStats() {
        return NUMBER_OF_STATS;
    }

    public void populateCards() {
        int numberOfStats = getNumberOfStats();
        for (int i = 0; i < numberOfStats; i++) {
            GameStat gameStat = new GameStat();
            gameStat.setAwayTeamStat(jsonGameStatParser.getCompoundTeamStat("visitor", getGameStatCodes().get(i)));
            gameStat.setStatDescription(getGameStatNames().get(i));
            gameStat.setHomeTeamStat(jsonGameStatParser.getCompoundTeamStat("home", getGameStatCodes().get(i)));

            gameStatArrayList.add(gameStat);
        }
    }

    public ArrayList<GameStat> getGameStatArrayList() {
        return gameStatArrayList;
    }

    private ArrayList<String> getGameStatCodes() {
        ArrayList<String> names = new ArrayList<>();
        names.add("field goals");
        names.add("free throws");
        names.add("three pointers");
        names.add("rebounds");
        names.add("assists");
        names.add("steals");
        names.add("blocks");
        names.add("turnovers");
        names.add("fouls");
        return names;
    }

    private ArrayList<String> getGameStatNames() {
        ArrayList<String> names = new ArrayList<>();
        names.add("Field Goals");
        names.add("Free Throws");
        names.add("3 pointers");
        names.add("Rebounds (Off)");
        names.add("Assists");
        names.add("Steals");
        names.add("Blocks");
        names.add("Turnovers");
        names.add("Personal Fouls");
        return names;
    }
}
