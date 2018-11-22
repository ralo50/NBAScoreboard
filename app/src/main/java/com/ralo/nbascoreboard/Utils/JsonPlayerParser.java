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



    public JsonPlayerParser(JSONObject object){
        this.jsonObject =  object;
        Log.d("Testing", "TESTING!");

    }

    public JSONArray getAwayBaseJsonArray() {
        JSONObject baseJsonObject = new JSONObject();
        visitorJsonArray = new JSONArray();

        try {
            baseJsonObject = this.jsonObject.getJSONObject("sports_content");
            baseJsonObject = baseJsonObject.getJSONObject("game");
            baseJsonObject = baseJsonObject.getJSONObject("visitor");
            baseJsonObject = baseJsonObject.getJSONObject("players");
            visitorJsonArray = baseJsonObject.getJSONArray("player");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return visitorJsonArray;
    }

    public JSONArray getHomeBaseJsonArray() {
        JSONObject baseJsonObject = new JSONObject();
        homeJsonArray = new JSONArray();
        try {
            baseJsonObject = this.jsonObject.getJSONObject("sports_content");
            baseJsonObject = baseJsonObject.getJSONObject("game");
            baseJsonObject = baseJsonObject.getJSONObject("home");
            baseJsonObject = baseJsonObject.getJSONObject("players");
            visitorJsonArray = baseJsonObject.getJSONArray("player");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return homeJsonArray;
    }

    public int getHomePlayerTeamStats(String playerStat, int playerIndex){
        newJasonObject = new JSONObject();
        int currentPlayerStat = 5;
        try {
            newJasonObject = this.getHomeBaseJsonArray().getJSONObject(playerIndex);
            currentPlayerStat = newJasonObject.getInt(playerStat);
            Log.d("Testing", "TESTING!");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return currentPlayerStat;
    }
}
