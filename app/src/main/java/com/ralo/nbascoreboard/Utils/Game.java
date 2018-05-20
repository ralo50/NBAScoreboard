package com.ralo.nbascoreboard.Utils;

public class Game {

    String homeTeamName;
    String awayTeamName;
    int homeTeamScore;
    int awayTeamScore;
    int homeTeamWins;
    int awayTeamWins;
    int homeTeamImage;
    int awayTeamImage;
    String nugget;

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public void setHomeTeamWins(int homeTeamWins) {
        this.homeTeamWins = homeTeamWins;
    }

    public void setAwayTeamWins(int awayTeamWins) {
        this.awayTeamWins = awayTeamWins;
    }

    public void setHomeTeamImage(int homeTeamImage) {
        this.homeTeamImage = homeTeamImage;
    }

    public void setAwayTeamImage(int awayTeamImage) {
        this.awayTeamImage = awayTeamImage;
    }

    public void setNugget(String nugget) {
        this.nugget = nugget;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public String getHomeTeamScore() {
        return String.valueOf(homeTeamScore);
    }

    public String getAwayTeamScore() {
        return String.valueOf(awayTeamScore);
    }

    public String getHomeTeamWins() {
        return String.valueOf(homeTeamWins);
    }

    public String getAwayTeamWins() {
        return String.valueOf(awayTeamWins);
    }

    public int getHomeTeamImage() {
        return homeTeamImage;
    }

    public int getAwayTeamImage() {
        return awayTeamImage;
    }

    public String getNugget() {
        return nugget;
    }

    public String get(int position){
        return "swag";
    }
}
