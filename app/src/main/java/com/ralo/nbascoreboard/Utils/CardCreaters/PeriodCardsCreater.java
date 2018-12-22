package com.ralo.nbascoreboard.Utils.CardCreaters;

import com.ralo.nbascoreboard.Utils.JsonParsers.JsonPeriodParser;
import com.ralo.nbascoreboard.Utils.DataClasses.Period;

import org.json.JSONObject;

import java.util.ArrayList;

public class PeriodCardsCreater {

    private ArrayList<Period> periodArrayList;
    private JsonPeriodParser jsonPeriodParser;
    private String homeOrAway;

    public PeriodCardsCreater(JSONObject response, String homeOrAway) {
        this.jsonPeriodParser = new JsonPeriodParser(response);
        periodArrayList = new ArrayList<>();
        this.homeOrAway = homeOrAway;
    }

    private int getNumberOfPeriods() {
        if (jsonPeriodParser.getNumberOfPeriods() == 0) {
            return 4;
        } else {
            return jsonPeriodParser.getNumberOfPeriods();
        }
    }

    public void populateCards() {
        int numberOfPeriods = getNumberOfPeriods();
        int homeScore = 0;
        int awayScore = 0;
        for (int i = 0; i < numberOfPeriods; i++) {
            Period period = new Period();
            period.setPeriodNumber(jsonPeriodParser.getPeriodDetails("period_name", i));
            period.setAwayTeamPeriodScore(jsonPeriodParser.getPeriodScore("score", i, "visitor"));
            awayScore += jsonPeriodParser.getPeriodScore("score", i, "visitor");
            period.setHomeTeamPeriodScore(jsonPeriodParser.getPeriodScore("score", i, "home"));
            homeScore += jsonPeriodParser.getPeriodScore("score", i, "home");
            periodArrayList.add(period);
        }
        Period period = new Period();
        period.setPeriodNumber("Total");
        period.setAwayTeamPeriodScore(awayScore);
        period.setHomeTeamPeriodScore(homeScore);
        periodArrayList.add(period);
    }

    public ArrayList<Period> getPeriodArrayList() {
        return periodArrayList;
    }
}
