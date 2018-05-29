package com.ralo.nbascoreboard.Utils;

import com.ralo.nbascoreboard.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {

    private JSONObject jsonObject;
    private String jsonString = "failed";
    private int imageId;
    private int currentGame;
    private int homeTeamPoint = 0;
    private int awayTeamPoints = 0;

    JsonParser(JSONObject object){
         this.jsonObject =  object;
         imageId = R.drawable.nba;
    }

    public String getHomeTeamScore(){
        try {
            jsonString = this.jsonObject.getString("games");
            JSONArray jsonObject1 = this.jsonObject.getJSONArray("games");
            JSONObject jsonObject2 = jsonObject1.getJSONObject(currentGame);
            JSONObject jsonObject3 = jsonObject2.getJSONObject("hTeam");

            jsonString = jsonObject3.getString("score");
            if(!jsonString.isEmpty())
                homeTeamPoint = Integer.valueOf(jsonString);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public String getAwayTeamScore(){
        try {
            jsonString = this.jsonObject.getString("games");
            JSONArray jsonObject1 = this.jsonObject.getJSONArray("games");
            JSONObject jsonObject2 = jsonObject1.getJSONObject(currentGame);
            JSONObject jsonObject3 = jsonObject2.getJSONObject("vTeam");

            jsonString = jsonObject3.getString("score");
            if(!jsonString.isEmpty())
                awayTeamPoints = Integer.valueOf(jsonString);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public String getHomeTeamName(){
        try {
            jsonString = this.jsonObject.getString("games");
            JSONArray jsonObject1 = this.jsonObject.getJSONArray("games");
            JSONObject jsonObject2 = jsonObject1.getJSONObject(currentGame);
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
            JSONObject jsonObject2 = jsonObject1.getJSONObject(currentGame);
            JSONObject jsonObject3 = jsonObject2.getJSONObject("vTeam");

            jsonString = jsonObject3.getString("triCode");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public String getHomeTeamWins(){
        try {
            jsonString = this.jsonObject.getString("games");
            JSONArray jsonObject1 = this.jsonObject.getJSONArray("games");
            JSONObject jsonObject2 = jsonObject1.getJSONObject(currentGame);
            if(!jsonObject2.has("playoffs")){
                JSONObject jsonObject3 = jsonObject2.getJSONObject("hTeam");

                jsonString = jsonObject3.getString("win");
                jsonString += " : ";
                jsonString += jsonObject3.getString("loss");
            }
            else {
                JSONObject jsonObject3 = jsonObject2.getJSONObject("hTeam");

                jsonString = jsonObject3.getString("seriesWin");
                int wins;
                if(homeTeamPoint > awayTeamPoints && homeTeamPoint != 0 && awayTeamPoints != 0) {
                    wins = Integer.valueOf(jsonString);
                    wins++;
                    jsonString = String.valueOf(wins);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonString;
    }

    public String getAwayTeamWins() {
        try {
            jsonString = this.jsonObject.getString("games");
            JSONArray jsonObject1 = this.jsonObject.getJSONArray("games");
            JSONObject jsonObject2 = jsonObject1.getJSONObject(currentGame);
            if (!jsonObject2.has("playoffs")) {
                JSONObject jsonObject3 = jsonObject2.getJSONObject("vTeam");

                jsonString = jsonObject3.getString("win");
                jsonString += " : ";
                jsonString += jsonObject3.getString("loss");


            } else {
                JSONObject jsonObject3 = jsonObject2.getJSONObject("vTeam");

                jsonString = jsonObject3.getString("seriesWin");
                int wins;
                if(homeTeamPoint < awayTeamPoints && homeTeamPoint != 0 && awayTeamPoints != 0) {
                    wins = Integer.valueOf(jsonString);
                    wins++;
                    jsonString = String.valueOf(wins);
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonString;
    }

    public int getHomeTeamImage(){
        try {
            jsonString = this.jsonObject.getString("games");
            JSONArray jsonObject1 = this.jsonObject.getJSONArray("games");
            JSONObject jsonObject2 = jsonObject1.getJSONObject(currentGame);
            JSONObject jsonObject3 = jsonObject2.getJSONObject("hTeam");
            jsonString = jsonObject3.getString("triCode");
            return getImageId(jsonString);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return imageId;
    }

    public int getAwayTeamImage(){
        try {
            jsonString = this.jsonObject.getString("games");
            JSONArray jsonObject1 = this.jsonObject.getJSONArray("games");
            JSONObject jsonObject2 = jsonObject1.getJSONObject(currentGame);
            JSONObject jsonObject3 = jsonObject2.getJSONObject("vTeam");
            jsonString = jsonObject3.getString("triCode");
            return getImageId(jsonString);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return imageId;
    }

    private int getImageId(String jsonString) {
        switch (jsonString){
            case "ATL":
                return R.drawable.atl;

            case "MIN":
                return R.drawable.min;

            case "BOS":
                return R.drawable.bos;

            case "PHI":
                return R.drawable.philadelphia;

            case "MIL":
                return R.drawable.mil;

            case "CHI":
                return R.drawable.bulls;

            case "CLE:":
                return R.drawable.bulls;

            case "MIA":
                return R.drawable.mia;

            case "MEM":
                return R.drawable.mem;

            case "SAC":
                return R.drawable.sac;

            case "OKC":
                return R.drawable.okc;

            case "POR":
                return R.drawable.portland;

            case "WAS":
                return R.drawable.washingtonm;

            case "GSW":
                return R.drawable.gsw;

            case "HOU":
                return R.drawable.hou;

            case "SAS":
                return R.drawable.sanantonio;

            case "DET":
                return R.drawable.pistons;

            case "PHX":
                return R.drawable.phx;

            case "NOP":
                return R.drawable.pelicans;

            case "TOR":
                return R.drawable.tor;

            case "BKN":
                return R.drawable.brooklyn;

            case "DEN":
                return R.drawable.nuggets;

            case "IND":
                return R.drawable.pacers;

            case "NYK":
                return R.drawable.knicks;

            case "CHA":
                return R.drawable.hornets;

            case "UTA":
                return R.drawable.utah;

            case "CLE":
                return R.drawable.clevelend;

            case "LAC":
                return R.drawable.clippers;

            case "LAL":
                return R.drawable.lal;

            case "DAL":
                return R.drawable.dallas;

            case "ORL":
                return R.drawable.orl;

            default:
                return R.drawable.nba;
        }
    }


    public int getNumberOfGames(){
        String numOfGames;
        try {
            numOfGames =  this.jsonObject.getString("numGames");
            return Integer.valueOf(numOfGames);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getNugget(){
        try {
            jsonString = this.jsonObject.getString("games");
            JSONArray jsonObject1 = this.jsonObject.getJSONArray("games");
            JSONObject jsonObject2 = jsonObject1.getJSONObject(currentGame);
            JSONObject jsonObject3 = jsonObject2.getJSONObject("nugget");
            jsonString = jsonObject3.getString("text");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    private boolean isGameOver(){
        try {
            jsonString = this.jsonObject.getString("games");
            JSONArray jsonObject1 = this.jsonObject.getJSONArray("games");
            JSONObject jsonObject2 = jsonObject1.getJSONObject(currentGame);
            if(!jsonObject2.has("endTimeUTC"))
                return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getGameTime(){
        if(isGameOver()) {
            try {
                jsonString = this.jsonObject.getString("games");
                JSONArray jsonObject1 = this.jsonObject.getJSONArray("games");
                JSONObject jsonObject2 = jsonObject1.getJSONObject(currentGame);
                jsonString = jsonObject2.getString("startTimeEastern");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jsonString;
        }
        return ":";
    }

    public boolean isGameNight(){
        return getNumberOfGames() >= 1;
    }


    public JSONObject getBaseJsonObject(){

        return null;
    }

    public void setCurrentGame(int currentGame) {
        this.currentGame = currentGame;
    }
}
