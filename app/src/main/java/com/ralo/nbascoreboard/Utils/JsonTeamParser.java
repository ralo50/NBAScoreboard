package com.ralo.nbascoreboard.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonTeamParser {

    private JSONObject jsonObject;
    private JSONObject newJasonObject;

    public JsonTeamParser(JSONObject object){
        this.jsonObject =  object;
    }

    public JSONObject getBaseJsonObject() {
        JSONObject baseJsonObject = new JSONObject();
        try {
            baseJsonObject = this.jsonObject.getJSONObject("sports_content");
            baseJsonObject = baseJsonObject.getJSONObject("game");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return baseJsonObject;
    }

    public int getAwayTeamImage(){
        try {
            newJasonObject = this.getBaseJsonObject().getJSONObject("visitor");
            return JsonGameParser.getImageId(newJasonObject.getString("team_key"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getHomeTeamImage(){
        try {
            newJasonObject = this.getBaseJsonObject().getJSONObject("home");
            return JsonGameParser.getImageId(newJasonObject.getString("team_key"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getAwayTeamName(){
        try {
            newJasonObject = this.getBaseJsonObject().getJSONObject("visitor");
            return newJasonObject.getString("nickname");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getHomeTeamName(){
        try {
            newJasonObject = this.getBaseJsonObject().getJSONObject("home");
            return newJasonObject.getString("nickname");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getAwayTeamScore(){
        try {
            newJasonObject = this.getBaseJsonObject().getJSONObject("visitor");
            newJasonObject = newJasonObject.getJSONObject("stats");
            return newJasonObject.getString("points");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getHomeTeamScore(){
        try {
            newJasonObject = this.getBaseJsonObject().getJSONObject("home");
            newJasonObject = newJasonObject.getJSONObject("stats");
            return newJasonObject.getString("points");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }


}
