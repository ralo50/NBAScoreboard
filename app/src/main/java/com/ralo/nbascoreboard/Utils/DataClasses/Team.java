package com.ralo.nbascoreboard.Utils.DataClasses;

public class Team {

    //Team stats for the current game
    private int points;
    private int fieldGoalsMade;
    private int fieldGoalsAttempted;
    private float fieldGoalsPercentage;
    private int freeThrowsMade;
    private int freeThrowsAttempted;
    private float freeThrowsPercentage;
    private int threePointersMade;
    private int threePointersAttempted;
    private float threePointersPercentage;
    private int reboundsOffensive;
    private int reboundsDefensive;
    private int teamRebounds;
    private int assists;
    private int fouls;
    private int teamFouls;
    private int technicalFouls;
    private int steals;
    private int turnovers;
    private int teamTurnovers;
    private int blocks;
    private int shortTimeoutRemaining;
    private int fullTimeoutRemaining;

    //Team stats for the whole season
    private int id;
    private String name;
    private String nickname;
    private String teamcode;
    private String teamKey;
    private float minutesPerGame;
    private float fieldGoalPercentage;
    private float threePointsPercentage;
    private float freeThrowPercentage;
    private float offensiveReboundsPerGame;
    private float defensiveReboundsPerGame;
    private float totalReboundsPerGame;
    private float assistsPerGame;
    private float turnoversPerGame;
    private float stealsPerGame;
    private float blocksPerGame;
    private float personalFoulsPerGame;
    private float pointsPerGame;
    private float pointsAllowedPerGame;
    private float efficiency;
    private float offensiveRating;
    private float defensiveRating;
    private float pace;


    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getFieldGoalsMade() {
        return fieldGoalsMade;
    }

    public void setFieldGoalsMade(int fieldGoalsMade) {
        this.fieldGoalsMade = fieldGoalsMade;
    }

    public int getFieldGoalsAttempted() {
        return fieldGoalsAttempted;
    }

    public void setFieldGoalsAttempted(int fieldGoalsAttempted) {
        this.fieldGoalsAttempted = fieldGoalsAttempted;
    }

    public float getFieldGoalsPercentage() {
        return fieldGoalsPercentage;
    }

    public void setFieldGoalsPercentage(float fieldGoalsPercentage) {
        this.fieldGoalsPercentage = fieldGoalsPercentage;
    }

    public int getFreeThrowsMade() {
        return freeThrowsMade;
    }

    public void setFreeThrowsMade(int freeThrowsMade) {
        this.freeThrowsMade = freeThrowsMade;
    }

    public int getFreeThrowsAttempted() {
        return freeThrowsAttempted;
    }

    public void setFreeThrowsAttempted(int freeThrowsAttempted) {
        this.freeThrowsAttempted = freeThrowsAttempted;
    }

    public float getFreeThrowsPercentage() {
        return freeThrowsPercentage;
    }

    public void setFreeThrowsPercentage(float freeThrowsPercentage) {
        this.freeThrowsPercentage = freeThrowsPercentage;
    }

    public int getThreePointersMade() {
        return threePointersMade;
    }

    public void setThreePointersMade(int threePointersMade) {
        this.threePointersMade = threePointersMade;
    }

    public int getThreePointersAttempted() {
        return threePointersAttempted;
    }

    public void setThreePointersAttempted(int threePointersAttempted) {
        this.threePointersAttempted = threePointersAttempted;
    }

    public float getThreePointersPercentage() {
        return threePointersPercentage;
    }

    public void setThreePointersPercentage(float threePointersPercentage) {
        this.threePointersPercentage = threePointersPercentage;
    }

    public int getReboundsOffensive() {
        return reboundsOffensive;
    }

    public void setReboundsOffensive(int reboundsOffensive) {
        this.reboundsOffensive = reboundsOffensive;
    }

    public int getReboundsDefensive() {
        return reboundsDefensive;
    }

    public void setReboundsDefensive(int reboundsDefensive) {
        this.reboundsDefensive = reboundsDefensive;
    }

    public int getTeamRebounds() {
        return teamRebounds;
    }

    public void setTeamRebounds(int teamRebounds) {
        this.teamRebounds = teamRebounds;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getFouls() {
        return fouls;
    }

    public void setFouls(int fouls) {
        this.fouls = fouls;
    }

    public int getTeamFouls() {
        return teamFouls;
    }

    public void setTeamFouls(int teamFouls) {
        this.teamFouls = teamFouls;
    }

    public int getTechnicalFouls() {
        return technicalFouls;
    }

    public void setTechnicalFouls(int technicalFouls) {
        this.technicalFouls = technicalFouls;
    }

    public int getSteals() {
        return steals;
    }

    public void setSteals(int steals) {
        this.steals = steals;
    }

    public int getTurnovers() {
        return turnovers;
    }

    public void setTurnovers(int turnovers) {
        this.turnovers = turnovers;
    }

    public int getTeamTurnovers() {
        return teamTurnovers;
    }

    public void setTeamTurnovers(int teamTurnovers) {
        this.teamTurnovers = teamTurnovers;
    }

    public int getBlocks() {
        return blocks;
    }

    public void setBlocks(int blocks) {
        this.blocks = blocks;
    }

    public int getShortTimeoutRemaining() {
        return shortTimeoutRemaining;
    }

    public void setShortTimeoutRemaining(int shortTimeoutRemaining) {
        this.shortTimeoutRemaining = shortTimeoutRemaining;
    }

    public int getFullTimeoutRemaining() {
        return fullTimeoutRemaining;
    }

    public void setFullTimeoutRemaining(int fullTimeoutRemaining) {
        this.fullTimeoutRemaining = fullTimeoutRemaining;
    }

    //----------------------------------------------------------------------------------//

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTeamcode() {
        return teamcode;
    }

    public void setTeamcode(String teamcode) {
        this.teamcode = teamcode;
    }

    public String getTeamKey() {
        return teamKey;
    }

    public void setTeamKey(String teamKey) {
        this.teamKey = teamKey;
    }

    public float getMinutesPerGame() {
        return minutesPerGame;
    }

    public void setMinutesPerGame(float minutesPerGame) {
        this.minutesPerGame = minutesPerGame;
    }

    public float getFieldGoalPercentage() {
        return fieldGoalPercentage;
    }

    public void setFieldGoalPercentage(float fieldGoalPercentage) {
        this.fieldGoalPercentage = fieldGoalPercentage;
    }

    public float getThreePointsPercentage() {
        return threePointsPercentage;
    }

    public void setThreePointsPercentage(float threePointsPercentage) {
        this.threePointsPercentage = threePointsPercentage;
    }

    public float getFreeThrowPercentage() {
        return freeThrowPercentage;
    }

    public void setFreeThrowPercentage(float freeThrowPercentage) {
        this.freeThrowPercentage = freeThrowPercentage;
    }

    public float getOffensiveReboundsPerGame() {
        return offensiveReboundsPerGame;
    }

    public void setOffensiveReboundsPerGame(float offensiveReboundsPerGame) {
        this.offensiveReboundsPerGame = offensiveReboundsPerGame;
    }

    public float getDefensiveReboundsPerGame() {
        return defensiveReboundsPerGame;
    }

    public void setDefensiveReboundsPerGame(float defensiveReboundsPerGame) {
        this.defensiveReboundsPerGame = defensiveReboundsPerGame;
    }

    public float getTotalReboundsPerGame() {
        return totalReboundsPerGame;
    }

    public void setTotalReboundsPerGame(float totalReboundsPerGame) {
        this.totalReboundsPerGame = totalReboundsPerGame;
    }

    public float getAssistsPerGame() {
        return assistsPerGame;
    }

    public void setAssistsPerGame(float assistsPerGame) {
        this.assistsPerGame = assistsPerGame;
    }

    public float getTurnoversPerGame() {
        return turnoversPerGame;
    }

    public void setTurnoversPerGame(float turnoversPerGame) {
        this.turnoversPerGame = turnoversPerGame;
    }

    public float getStealsPerGame() {
        return stealsPerGame;
    }

    public void setStealsPerGame(float stealsPerGame) {
        this.stealsPerGame = stealsPerGame;
    }

    public float getBlocksPerGame() {
        return blocksPerGame;
    }

    public void setBlocksPerGame(float blocksPerGame) {
        this.blocksPerGame = blocksPerGame;
    }

    public float getPersonalFoulsPerGame() {
        return personalFoulsPerGame;
    }

    public void setPersonalFoulsPerGame(float personalFoulsPerGame) {
        this.personalFoulsPerGame = personalFoulsPerGame;
    }

    public float getPointsPerGame() {
        return pointsPerGame;
    }

    public void setPointsPerGame(float pointsPerGame) {
        this.pointsPerGame = pointsPerGame;
    }

    public float getPointsAllowedPerGame() {
        return pointsAllowedPerGame;
    }

    public void setPointsAllowedPerGame(float pointsAllowedPerGame) {
        this.pointsAllowedPerGame = pointsAllowedPerGame;
    }

    public float getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(float efficiency) {
        this.efficiency = efficiency;
    }

    public float getOffensiveRating() {
        return offensiveRating;
    }

    public void setOffensiveRating(float offensiveRating) {
        this.offensiveRating = offensiveRating;
    }

    public float getDefensiveRating() {
        return defensiveRating;
    }

    public void setDefensiveRating(float defensiveRating) {
        this.defensiveRating = defensiveRating;
    }

    public float getPace() {
        return pace;
    }

    public void setPace(float pace) {
        this.pace = pace;
    }
}
