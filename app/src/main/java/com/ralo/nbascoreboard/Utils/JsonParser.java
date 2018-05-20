package com.ralo.nbascoreboard.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {

    private JSONObject jsonObject;
    private String jsonString = "failed";

    public JsonParser(JSONObject object){
         this.jsonObject =  object;
    }

    public String getHomeTeamScore(){
        try {
            jsonString = this.jsonObject.getString("games");
            JSONArray jsonObject1 = this.jsonObject.getJSONArray("games");
            JSONObject jsonObject2 = jsonObject1.getJSONObject(0);
            JSONObject jsonObject3 = jsonObject2.getJSONObject("hTeam");

            jsonString = jsonObject3.getString("score");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public String getAwayTeamScore(){
        try {
            jsonString = this.jsonObject.getString("games");
            JSONArray jsonObject1 = this.jsonObject.getJSONArray("games");
            JSONObject jsonObject2 = jsonObject1.getJSONObject(0);
            JSONObject jsonObject3 = jsonObject2.getJSONObject("vTeam");

            jsonString = jsonObject3.getString("score");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public String getHomeTeamName(){
        try {
            jsonString = this.jsonObject.getString("games");
            JSONArray jsonObject1 = this.jsonObject.getJSONArray("games");
            JSONObject jsonObject2 = jsonObject1.getJSONObject(0);
            JSONObject jsonObject3 = jsonObject2.getJSONObject("hTeam");

            jsonString = jsonObject3.getString("triCode");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public String getAwayTeamName(){
        try {
            jsonString = this.jsonObject.getString("games");
            JSONArray jsonObject1 = this.jsonObject.getJSONArray("games");
            JSONObject jsonObject2 = jsonObject1.getJSONObject(0);
            JSONObject jsonObject3 = jsonObject2.getJSONObject("vTeam");

            jsonString = jsonObject3.getString("triCode");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonString;
    }




    public int getNumberOfGames(){
        String numOfGames = "";
        try {
            numOfGames =  this.jsonObject.getString("numGames");
            return Integer.valueOf(numOfGames);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean isGameNight(){
        return getNumberOfGames() <= 1;
    }


    public JSONObject getBaseJsonObject(){

        return null;
    }

}
