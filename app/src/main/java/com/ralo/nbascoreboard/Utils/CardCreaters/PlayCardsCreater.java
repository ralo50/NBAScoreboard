package com.ralo.nbascoreboard.Utils.CardCreaters;


import com.ralo.nbascoreboard.Utils.DataClasses.Play;
import com.ralo.nbascoreboard.Utils.JsonParsers.JsonPlayParser;

import org.json.JSONObject;

import java.util.ArrayList;

public class PlayCardsCreater {

    private ArrayList<Play> playArrayList;
    private JsonPlayParser jsonPlayParser;

    public PlayCardsCreater(JSONObject response) {
        this.jsonPlayParser = new JsonPlayParser(response);
        playArrayList = new ArrayList<>();
    }

    private int getNumberOfPlays() {
        return this.jsonPlayParser.getNumberOfPlays();
    }

    public void populateCards() {
        int numberOfPlays = getNumberOfPlays();
        for (int i = numberOfPlays - 1; i >= 0; i--) {
            Play play = new Play();
            play.setHomeScore(jsonPlayParser.getPlayInfoInt("home_score", i));
            play.setVisitorScore(jsonPlayParser.getPlayInfoInt("visitor_score", i));
            play.setEventNumber(jsonPlayParser.getPlayInfoInt("event", i));
            play.setPeriod(jsonPlayParser.getPlayInfoInt("period", i));
            play.setPersonId(jsonPlayParser.getPlayInfoInt("person_id", i));
            play.setClockTime(jsonPlayParser.getPlayInfoString("clock", i));
            play.setPlayDescription(jsonPlayParser.getPlayInfoString("description", i));
            play.setTeamCode(jsonPlayParser.getPlayInfoString("team_abr", i));
            if (i > 0) {
                if ((jsonPlayParser.getPlayInfoInt("home_score", i) != jsonPlayParser.getPlayInfoInt("home_score", i - 1)) ||
                        (jsonPlayParser.getPlayInfoInt("visitor_score", i)) != (jsonPlayParser.getPlayInfoInt("visitor_score", i - 1))) {
                    String playDescription = play.getPlayDescription();
                    playDescription = playDescription.substring(playDescription.indexOf("]") + 2, playDescription.length());
                    play.setPlayDescription(playDescription);
                    play.setShotMade(true);
                } else
                    play.setShotMade(false);
            }
            playArrayList.add(play);
        }
    }

    public ArrayList<Play> getPlayArrayList() {
        return playArrayList;
    }
}
