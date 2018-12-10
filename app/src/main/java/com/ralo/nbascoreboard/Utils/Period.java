package com.ralo.nbascoreboard.Utils;

public class Period {

    private String periodNumber;
    private int homeTeamPeriodScore = 0;
    private int awayTeamPeriodScore = 0;

    public String getPeriodNumber() {
        return periodNumber;
    }

    public void setPeriodNumber(String periodNumber) {
        this.periodNumber = periodNumber;
    }

    public int getHomeTeamPeriodScore() {
        return homeTeamPeriodScore;
    }

    public void setHomeTeamPeriodScore(int homeTeamPeriodScore) {
        this.homeTeamPeriodScore = homeTeamPeriodScore;
    }

    public int getAwayTeamPeriodScore() {
        return awayTeamPeriodScore;
    }

    public void setAwayTeamPeriodScore(int awayTeamPeriodScore) {
        this.awayTeamPeriodScore = awayTeamPeriodScore;
    }
}
