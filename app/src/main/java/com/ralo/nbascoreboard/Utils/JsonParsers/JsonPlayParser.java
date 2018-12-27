package com.ralo.nbascoreboard.Utils.JsonParsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonPlayParser {

    private JSONObject jsonObject;
    private JSONObject newJasonObject;
    private JSONArray jsonArray;

    public JsonPlayParser(JSONObject object) {
        this.jsonObject = object;
    }

    private JSONArray getBaseJsonArray() {
        JSONObject baseJsonObject;
        jsonArray = new JSONArray();
        try {
            baseJsonObject = this.jsonObject.getJSONObject("sports_content");
            baseJsonObject = baseJsonObject.getJSONObject("game");
            jsonArray = baseJsonObject.getJSONArray("play");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public int getPlayInfoInt(String playInfo, int playIndex) {
        newJasonObject = new JSONObject();
        int currentPlayInfo = 0;
        try {
            newJasonObject = this.getBaseJsonArray().getJSONObject(playIndex);
            if(!newJasonObject.getString(playInfo).isEmpty())
                currentPlayInfo = newJasonObject.getInt(playInfo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return currentPlayInfo;
    }

    public String getPlayInfoString(String playInfo, int playIndex) {
        newJasonObject = new JSONObject();
        String currentPlayInfo = "";
        try {
            newJasonObject = this.getBaseJsonArray().getJSONObject(playIndex);
            currentPlayInfo = newJasonObject.getString(playInfo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return currentPlayInfo;
    }

    public int getNumberOfPlays() {
        return this.getBaseJsonArray().length();
    }
}
