package com.ralo.nbascoreboard.Utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonPlayerParser {


    private JSONObject jsonObject;
    private JSONObject newJasonObject;
    private JSONArray visitorJsonArray;
    private JSONArray homeJsonArray;
    private JSONArray jsonArray;



    public JsonPlayerParser(JSONObject object){
        this.jsonObject =  object;
    }

    public JSONArray getBaseJsonArray(String homeOrAway){
        JSONObject baseJsonObject = new JSONObject();
        visitorJsonArray = new JSONArray();

        try {
            baseJsonObject = this.jsonObject.getJSONObject("sports_content");
            baseJsonObject = baseJsonObject.getJSONObject("game");
            baseJsonObject = baseJsonObject.getJSONObject(homeOrAway);
            baseJsonObject = baseJsonObject.getJSONObject("players");
            visitorJsonArray = baseJsonObject.getJSONArray("player");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return visitorJsonArray;
    }

//    public JSONArray getAwayBaseJsonArray() {
//        JSONObject baseJsonObject = new JSONObject();
//        visitorJsonArray = new JSONArray();
//
//        try {
//            baseJsonObject = this.jsonObject.getJSONObject("sports_content");
//            baseJsonObject = baseJsonObject.getJSONObject("game");
//            baseJsonObject = baseJsonObject.getJSONObject("visitor");
//            baseJsonObject = baseJsonObject.getJSONObject("players");
//            visitorJsonArray = baseJsonObject.getJSONArray("player");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return visitorJsonArray;
//    }
//
//    public JSONArray getHomeBaseJsonArray() {
//        JSONObject baseJsonObject = new JSONObject();
//        homeJsonArray = new JSONArray();
//        try {
//            baseJsonObject = this.jsonObject.getJSONObject("sports_content");
//            baseJsonObject = baseJsonObject.getJSONObject("game");
//            baseJsonObject = baseJsonObject.getJSONObject("home");
//            baseJsonObject = baseJsonObject.getJSONObject("players");
//            homeJsonArray = baseJsonObject.getJSONArray("player");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return homeJsonArray;
//    }

    public int getPlayerTeamStatsInt(String playerStat, int playerIndex, String homeOrAway){
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



//
//    public int getHomePlayerTeamStatsInt(String playerStat, int playerIndex){
//        newJasonObject = new JSONObject();
//        int currentPlayerStat = 0;
//        try {
//
//            newJasonObject = this.getHomeBaseJsonArray().getJSONObject(playerIndex);
//            currentPlayerStat = newJasonObject.getInt(playerStat);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        return currentPlayerStat;
//    }
//
//    public int getAwayPlayerTeamStatsInt(String playerStat, int playerIndex){
//        newJasonObject = new JSONObject();
//        int currentPlayerStat = 0;
//        try {
//
//            newJasonObject = this.getAwayBaseJsonArray().getJSONObject(playerIndex);
//            currentPlayerStat = newJasonObject.getInt(playerStat);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        return currentPlayerStat;
//    }


    public String getPlayerTeamStatsString(String playerStat, int playerIndex, String homeOrAway){
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


//
//    public String getHomePlayerTeamStatsIntString(String playerStat, int playerIndex){
//        newJasonObject = new JSONObject();
//        String currentPlayerStat = "";
//        try {
//
//            newJasonObject = this.getHomeBaseJsonArray().getJSONObject(playerIndex);
//            currentPlayerStat = newJasonObject.getString(playerStat);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        return currentPlayerStat;
//    }
//
//    public String getAwayPlayerTeamStatsString(String playerStat, int playerIndex){
//        newJasonObject = new JSONObject();
//        String currentPlayerStat = "";
//        try {
//
//            newJasonObject = this.getAwayBaseJsonArray().getJSONObject(playerIndex);
//            currentPlayerStat = newJasonObject.getString(playerStat);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        return currentPlayerStat;
//    }

    public int getNumberOfPlayers(String homeOrAway){
        if(homeOrAway.equals("home")){
            return this.getBaseJsonArray(homeOrAway).length();
        }
        else if(homeOrAway.equals("visitor")){
            return this.getBaseJsonArray(homeOrAway).length();
        }
        return 0;
    }

    public boolean hasPlayerPlayed(String homeOrAway, int playerIndex){
        if(this.getPlayerTeamStatsInt("minutes", playerIndex, homeOrAway) == 0 && this.getPlayerTeamStatsInt("seconds", playerIndex, homeOrAway) ==0){
            return false;
        }
        return true;
    }

//    public int getHomePlayerCount(){
//        return this.getHomeBaseJsonArray().length();
//    }
//    public int getAwayPlayerCount(){
//        return this.getAwayBaseJsonArray().length();
//    }
}
