package com.ralo.nbascoreboard.Utils.JsonParsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonTeamStatParser {
    private JSONObject jsonObject;
    private JSONObject newJasonObject;
    private JSONArray jsonArray;

    public JsonTeamStatParser(JSONObject object) {
        this.jsonObject = object;
    }

    private JSONArray getBaseJsonArray() {
        JSONArray baseJsonArray;
        JSONObject baseJsonObject;
        jsonArray = new JSONArray();
        try {
            baseJsonArray = this.jsonObject.getJSONArray("resultSets");
            baseJsonObject = baseJsonArray.getJSONObject(0);
            baseJsonArray = baseJsonObject.getJSONArray("rowSet");
            jsonArray = baseJsonArray.getJSONArray(baseJsonArray.length()-1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public String getTeamStat(int index) {
        String teamStat = "";
        try {
            teamStat = this.getBaseJsonArray().getString(index);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return teamStat;
    }
}
