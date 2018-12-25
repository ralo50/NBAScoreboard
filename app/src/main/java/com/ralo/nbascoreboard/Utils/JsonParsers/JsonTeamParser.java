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

//    public int getAwayTeamScore(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("visitor");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("points");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public int getHomeTeamScore(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("home");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("points");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public int getAwayTeamFieldGoalsMade(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("visitor");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("field_goals_made");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public int getHomeTeamFieldGoalsMade(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("home");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("field_goals_made");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public int getAwayTeamFieldGoalsAttempted(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("visitor");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("field_goals_attempted");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public int getHomeTeamFieldGoalsAttempted(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("home");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("field_goals_attempted");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public float getAwayTeamFieldGoalsPercentage(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("visitor");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return (float) newJasonObject.getDouble("field_goals_percentage");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public float getHomeTeamFieldGoalsPercentage(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("home");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return (float) newJasonObject.getDouble("field_goals_percentage");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public int getAwayTeamFreeThrowsMade(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("visitor");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("free_throws_made");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public int getHomeTeamFreeThrowsMade(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("home");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("free_throws_made");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//
//    public int getAwayTeamFreeThrowsAttempted(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("visitor");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("free_throws_attempted");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public int getHomeTeamFreeThrowsAttempted(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("home");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("free_throws_attempted");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public float getAwayTeamFreeThrowsPercentage(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("visitor");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return (float) newJasonObject.getDouble("free_throws_percentage");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public float getHomeTeamFreeThrowsPercentage(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("home");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return (float) newJasonObject.getDouble("free_throws_percentage");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//
//    public int getAwayTeamThreePointersMade(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("visitor");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("three_pointers_made");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public int getHomeTeamThreePointersMade(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("home");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("three_pointers_made");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//
//    public int getAwayTeamThreePointersAttempted(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("visitor");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("three_pointers_attempted");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public int getHomeTeamThreePointersAttempted(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("home");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("three_pointers_attempted");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public float getAwayTeamThreePointersPercentage(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("visitor");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return (float) newJasonObject.getDouble("three_pointers_percentage");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public float getHomeTeamThreePointersPercentage(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("home");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return (float) newJasonObject.getDouble("three_pointers_percentage");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public int getAwayTeamOffensiveRebounds(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("visitor");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("rebounds_offensive");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public int getHomeTeamOffensiveRebounds(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("home");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("rebounds_offensive");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//
//    public int getAwayTeamDefensiveRebounds(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("visitor");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("rebounds_defensive");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public int getHomeTeamDefensiveRebounds(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("home");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("rebounds_defensive");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//
//    public int getAwayTeamTeamRebounds(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("visitor");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("team_rebounds");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public int getHomeTeamTeamRebounds(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("home");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("team_rebounds");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public int getAwayTeamAssists(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("visitor");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("assists");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public int getHomeAssists(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("home");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("assists");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//
//    public int getAwayTeamFouls(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("visitor");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("fouls");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public int getHomeTeamFouls(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("home");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("fouls");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public int getAwayTeamTeamFouls(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("visitor");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("team_fouls");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public int getHomeTeamTeamFouls(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("home");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("team_fouls");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public int getAwayTeamTechnicalFouls(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("visitor");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("technical_fouls");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public int getHomeTeamTechnicalFouls(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("home");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("technical_fouls");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public int getAwayTeamSteals(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("visitor");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("steals");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public int getHomeTeamSteals(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("home");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("steals");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public int getAwayTeamTurnovers(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("visitor");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("turnovers");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public int getHomeTeamTurnovers(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("home");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("turnovers");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public int getAwayTeamTeamTurnovers(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("visitor");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("team_turnovers");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public int getHomeTeamTeamTurnovers(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("home");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("team_turnovers");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public int getAwayTeamBlocks(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("visitor");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("blocks");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public int getHomeTeamBlocks(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("home");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("blocks");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public int getAwayTeamShortTimeoutsRemaining(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("visitor");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("short_timeout_remaining");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public int getHomeTeamShortTimeoutsRemaining(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("home");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("short_timeout_remaining");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//
//    public int getAwayTeamFullTimeoutsRemaining(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("visitor");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("full_timeout_remaining");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    public int getHomeTeamFullTimeoutsRemaining(){
//        try {
//            newJasonObject = this.getBaseJsonObject().getJSONObject("home");
//            newJasonObject = newJasonObject.getJSONObject("stats");
//            return newJasonObject.getInt("full_timeout_remaining");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//}
