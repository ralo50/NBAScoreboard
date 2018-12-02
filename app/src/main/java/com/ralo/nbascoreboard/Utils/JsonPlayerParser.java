package com.ralo.nbascoreboard.Utils;
//http://nbasense.com/nba-api/Data/MobileTeams/Player/PlayerCard
//http://nbasense.com/nba-api/Data/Cms/Game/Boxscore

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonPlayerParser {


    private JSONObject jsonObject;
    private JSONObject newJasonObject;
    private JSONArray jsonArray;

    public JsonPlayerParser(JSONObject object) {
        this.jsonObject = object;
    }

    private JSONArray getBaseJsonArray(String homeOrAway) {
        JSONObject baseJsonObject = new JSONObject();
        jsonArray = new JSONArray();

        try {
            baseJsonObject = this.jsonObject.getJSONObject("sports_content");
            baseJsonObject = baseJsonObject.getJSONObject("game");
            baseJsonObject = baseJsonObject.getJSONObject(homeOrAway);
            baseJsonObject = baseJsonObject.getJSONObject("players");
            jsonArray = baseJsonObject.getJSONArray("player");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public int getPlayerTeamStatsInt(String playerStat, int playerIndex, String homeOrAway) {
        newJasonObject = new JSONObject();
        int currentPlayerStat = 0;
        try {

            newJasonObject = this.getBaseJsonArray(homeOrAway).getJSONObject(playerIndex);
            currentPlayerStat = newJasonObject.getInt(playerStat);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return currentPlayerStat;
    }

    public String getPlayerTeamStatsString(String playerStat, int playerIndex, String homeOrAway) {
        newJasonObject = new JSONObject();
        String currentPlayerStat = "";
        try {

            newJasonObject = this.getBaseJsonArray(homeOrAway).getJSONObject(playerIndex);
            currentPlayerStat = newJasonObject.getString(playerStat);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return currentPlayerStat;
    }


    public float getPlayerTeamStatsFloat(String playerStat, int playerIndex, String homeOrAway) {
        newJasonObject = new JSONObject();
        float currentPlayerStat = 0;
        try {

            newJasonObject = this.getBaseJsonArray(homeOrAway).getJSONObject(playerIndex);
            currentPlayerStat = (float) newJasonObject.getDouble(playerStat);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return currentPlayerStat;
    }


    public boolean getPlayerTeamStatsBoolean(String playerStat, int playerIndex, String homeOrAway) {
        newJasonObject = new JSONObject();
        boolean currentPlayerStat = false;
        try {

            newJasonObject = this.getBaseJsonArray(homeOrAway).getJSONObject(playerIndex);
            currentPlayerStat = newJasonObject.getBoolean(playerStat);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return currentPlayerStat;
    }


    public int getNumberOfPlayers(String homeOrAway) {
        if (homeOrAway.equals("home")) {
            return this.getBaseJsonArray(homeOrAway).length();
        } else if (homeOrAway.equals("visitor")) {
            return this.getBaseJsonArray(homeOrAway).length();
        }
        return 0;
    }

    public boolean hasPlayerPlayed(String homeOrAway, int playerIndex) {
        if (this.getPlayerTeamStatsInt("minutes", playerIndex, homeOrAway) == 0 && this.getPlayerTeamStatsInt("seconds", playerIndex, homeOrAway) == 0) {
            return false;
        }
        return true;
    }

}
