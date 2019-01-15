package com.ralo.nbascoreboard.Utils.JsonParsers;
//http://nbasense.com/nba-api/Stats/Stats/Teams/TeamsYearByYearStats#request-example

import org.json.JSONException;
import org.json.JSONObject;

public class JsonTeamParser {

    private JSONObject jsonObject;
    private JSONObject newJasonObject;

    public JsonTeamParser(JSONObject object) {
        this.jsonObject = object;
    }


    private JSONObject getBaseJsonObject(String homeOrAway) {
        JSONObject baseJsonObject = new JSONObject();
        try {
            baseJsonObject = this.jsonObject.getJSONObject("sports_content");
            baseJsonObject = baseJsonObject.getJSONObject("game");
            baseJsonObject = baseJsonObject.getJSONObject(homeOrAway);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return baseJsonObject;
    }

    public int getTeamImage(String homeOrVisitor) {
        try {
            newJasonObject = this.getBaseJsonObject(homeOrVisitor);
            return JsonGameParser.getImageId(newJasonObject.getString("team_key"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getTeamName(String homeOrAway) {
        try {
            return getBaseJsonObject(homeOrAway).getString("nickname");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public int getTeamScore(String homeOrAway) {
        try {
            newJasonObject = this.getBaseJsonObject(homeOrAway);
            return getBaseJsonObject(homeOrAway).getInt("score");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getTeamId(String homeOrAway) {
        try {
            newJasonObject = this.getBaseJsonObject(homeOrAway);
            return getBaseJsonObject(homeOrAway).getInt("id");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getTeamStatInt(String homeOrAway, String teamStat) {
        try {
            newJasonObject = this.getBaseJsonObject(homeOrAway).getJSONObject("visitor");
            newJasonObject = newJasonObject.getJSONObject("stats");
            return newJasonObject.getInt(teamStat);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public float getTeamStatFloat(String homeOrAway, String teamStat) {
        try {
            newJasonObject = this.getBaseJsonObject(homeOrAway).getJSONObject("visitor");
            newJasonObject = newJasonObject.getJSONObject("stats");
            return (float) newJasonObject.getDouble(teamStat);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getTeamStatString(String homeOrAway, String teamStat) {
        try {
            newJasonObject = this.getBaseJsonObject(homeOrAway).getJSONObject("visitor");
            newJasonObject = newJasonObject.getJSONObject("stats");
            return newJasonObject.getString(teamStat);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }
}
