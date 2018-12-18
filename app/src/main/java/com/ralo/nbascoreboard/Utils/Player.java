package com.ralo.nbascoreboard.Utils;

import java.io.Serializable;

public class Player implements Serializable {

    private String firstName;
    private String lastName;
    private int jerseyNumber;
    private int playerId;
    private String playerCode;
    private String positionLong;
    private String position;
    private int minutesPlayed;
    private int points;
    private int reboundsOffensive;
    private int reboundsDefensive;
    private int reboundsTotal;
    private int fieldGoalsMade;
    private int fieldGoalsAttempted;
    private float fieldGoalsPercentage;
    private int threePointersMade;
    private int threePointersAttempted;
    private float threePointersPercentage;
    private int freeThrowsMade;
    private int freeThrowsAttempted;
    private float freeThrowsPercentage;
    private int assists;
    private int steals;
    private int turnovers;
    private int blocks;
    private String plusMinus;
    private int personalFouls;
    private boolean onCourt;
    private String startingPosition;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getPlayerCode() {
        return playerCode;
    }

    public String getPositionLong() {
        return positionLong;
    }

    public String getPosition() {
        return position;
    }

    public int getMinutesPlayed() {
        return minutesPlayed;
    }

    public int getPoints() {
        return points;
    }

    public int getReboundsOffensive() {
        return reboundsOffensive;
    }

    public int getReboundsDefensive() {
        return reboundsDefensive;
    }

    public int getReboundsTotal() {
        return reboundsTotal;
    }

    public int getFieldGoalsMade() {
        return fieldGoalsMade;
    }

    public int getFieldGoalsAttempted() {
        return fieldGoalsAttempted;
    }

    public float getFieldGoalsPercentage() {
        return fieldGoalsPercentage;
    }

    public int getThreePointersMade() {
        return threePointersMade;
    }

    public int getThreePointersAttempted() {
        return threePointersAttempted;
    }

    public float getThreePointersPercentage() {
        return threePointersPercentage;
    }

    public int getFreeThrowsMade() {
        return freeThrowsMade;
    }

    public int getFreeThrowsAttempted() {
        return freeThrowsAttempted;
    }

    public float getFreeThrowsPercentage() {
        return freeThrowsPercentage;
    }

    public int getAssists() {
        return assists;
    }

    public int getSteals() {
        return steals;
    }

    public int getTurnovers() {
        return turnovers;
    }

    public int getBlocks() {
        return blocks;
    }

    public String getPlusMinus() {
        return plusMinus;
    }

    public int getPersonalFouls() {
        return personalFouls;
    }

    public boolean isOnCourt() {
        return onCourt;
    }

    public String getStartingPosition() {
        return startingPosition;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public void setPlayerCode(String playerCode) {
        this.playerCode = playerCode;
    }

    public void setPositionLong(String positionLong) {
        this.positionLong = positionLong;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setMinutesPlayed(int minutesPlayed) {
        this.minutesPlayed = minutesPlayed;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setReboundsOffensive(int reboundsOffensive) {
        this.reboundsOffensive = reboundsOffensive;
    }

    public void setReboundsDefensive(int reboundsDefensive) {
        this.reboundsDefensive = reboundsDefensive;
    }

    public void setReboundsTotal(int reboundsTotal) {
        this.reboundsTotal = reboundsTotal;
    }

    public void setFieldGoalsMade(int fieldGoalsMade) {
        this.fieldGoalsMade = fieldGoalsMade;
    }

    public void setFieldGoalsAttempted(int fieldGoalsAttempted) {
        this.fieldGoalsAttempted = fieldGoalsAttempted;
    }

    public void setFieldGoalsPercentage(float fieldGoalsPercentage) {
        this.fieldGoalsPercentage = fieldGoalsPercentage;
    }

    public void setThreePointersMade(int threePointersMade) {
        this.threePointersMade = threePointersMade;
    }

    public void setThreePointersAttempted(int threePointersAttempted) {
        this.threePointersAttempted = threePointersAttempted;
    }

    public void setThreePointersPercentage(float threePointersPercentage) {
        this.threePointersPercentage = threePointersPercentage;
    }

    public void setFreeThrowsMade(int freeThrowsMade) {
        this.freeThrowsMade = freeThrowsMade;
    }

    public void setFreeThrowsAttempted(int freeThrowsAttempted) {
        this.freeThrowsAttempted = freeThrowsAttempted;
    }

    public void setFreeThrowsPercentage(float freeThrowsPercentage) {
        this.freeThrowsPercentage = freeThrowsPercentage;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public void setSteals(int steals) {
        this.steals = steals;
    }

    public void setTurnovers(int turnovers) {
        this.turnovers = turnovers;
    }

    public void setBlocks(int blocks) {
        this.blocks = blocks;
    }

    public void setPlusMinus(String plusMinus) {
        this.plusMinus = plusMinus;
    }

    public void setPersonalFouls(int personalFouls) {
        this.personalFouls = personalFouls;
    }

    public void setOnCourt(boolean onCourt) {
        this.onCourt = onCourt;
    }

    public void setStartingPosition(String startingPosition) {
        this.startingPosition = startingPosition;
    }
}
