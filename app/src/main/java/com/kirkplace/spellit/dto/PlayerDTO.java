package com.kirkplace.spellit.dto;

/**
 * Created by kirkplace on 5/31/2015.
 */
public class PlayerDTO {

    private String nickName;
    private int currentLevel;
    private int totalPoints;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }
}
