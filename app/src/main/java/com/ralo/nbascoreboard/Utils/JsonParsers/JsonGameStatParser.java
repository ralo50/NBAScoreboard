package com.ralo.nbascoreboard.Utils.JsonParsers;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonGameStatParser {

    private JSONObject jsonObject;
    private JSONObject newJasonObject;

    public JsonGameStatParser(JSONObject object) {
        this.jsonObject = object;
    }


    private JSONObject getBaseJsonObject(String homeOrVisitor) {
        JSONObject baseJsonObject = new JSONObject();
        try {
            baseJsonObject = this.jsonObject.getJSONObject("sports_content");
            baseJsonObject = baseJsonObject.getJSONObject("game");
            baseJsonObject = baseJsonObject.getJSONObject(homeOrVisitor);
            baseJsonObject = baseJsonObject.getJSONObject("stats");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return baseJsonObject;
    }

    public String getTeamStat(String homeOrVisitor, String statName) {
        try {
            return getBaseJsonObject(homeOrVisitor).getString(statName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getCompoundTeamStat(String homeOrVisitor, String statName){
        if(statName.equals("field goals")){
           return getTeamStat(homeOrVisitor, "field_goals_made")
                   + "-" + getTeamStat(homeOrVisitor, "field_goals_attempted")
                   + "   " + getTeamStat(homeOrVisitor, "field_goals_percentage") + "%";
        }
        else if(statName.equals("free throws")){
            return getTeamStat(homeOrVisitor, "free_throws_made")
                    + "-" + getTeamStat(homeOrVisitor, "free_throws_attempted")
                    + "   " + getTeamStat(homeOrVisitor, "free_throws_percentage") + "%";
        }
        else if(statName.equals("three pointers")){
            return getTeamStat(homeOrVisitor, "three_pointers_made")
                    + "-" + getTeamStat(homeOrVisitor, "three_pointers_attempted")
                    + "   " + getTeamStat(homeOrVisitor, "three_pointers_percentage") + "%";
        }
        else if(statName.equals("rebounds")){
            return getTeamStat(homeOrVisitor, "rebounds_defensive")
                    + " (" + getTeamStat(homeOrVisitor, "rebounds_offensive") + ")";
        }
        else{
            return getTeamStat(homeOrVisitor, statName);
        }
    }
}