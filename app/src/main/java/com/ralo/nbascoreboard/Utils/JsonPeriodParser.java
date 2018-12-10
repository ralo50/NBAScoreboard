package com.ralo.nbascoreboard.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonPeriodParser {

    private JSONObject jsonObject;
    private JSONObject newJasonObject;
    private JSONArray jsonArray;

    public JsonPeriodParser(JSONObject object) {
        this.jsonObject = object;
    }

    private JSONArray getBaseJsonArray(String homeOrAway) {
        JSONObject baseJsonObject = new JSONObject();
        jsonArray = new JSONArray();

        try {
            baseJsonObject = this.jsonObject.getJSONObject("sports_content");
            baseJsonObject = baseJsonObject.getJSONObject("game");
            baseJsonObject = baseJsonObject.getJSONObject(homeOrAway);
            baseJsonObject = baseJsonObject.getJSONObject("linescores");
            jsonArray = baseJsonObject.getJSONArray("period");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public String getPeriodDetails(String periodDetail, int periodIndex) {
        String currentPeriodStat = "";
        newJasonObject = new JSONObject();
        try {
            newJasonObject = this.getBaseJsonArray("home").getJSONObject(periodIndex);
            currentPeriodStat = newJasonObject.getString(periodDetail);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return currentPeriodStat;
    }

    public int getPeriodScore(String periodScore, int periodIndex, String homeOrAway) {
        newJasonObject = new JSONObject();
        int currentPeriodScore = 0;
        try {
            newJasonObject = this.getBaseJsonArray(homeOrAway).getJSONObject(periodIndex);
            currentPeriodScore = newJasonObject.getInt(periodScore);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return currentPeriodScore;
    }

    public int getNumberOfPeriods() {
        return this.getBaseJsonArray("home").length();
    }
}
