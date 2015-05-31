package com.kirkplace.spellit.dao;

import com.kirkplace.spellit.dto.PlayerDTO;

/**
 * Created by kirkplace on 5/31/2015.
 */
public class PlayerDAO {

    public PlayerDTO getPlayer(String name){
        PlayerDTO newPlayer = new PlayerDTO();
        newPlayer.setNickName(name);
        newPlayer.setCurrentLevel(getCurrentLevel(name));
        newPlayer.setTotalPoints(getTotalPoints(name));
        return newPlayer;
    }

    public int getCurrentLevel(String name){
        return 0;
    }

    public int getTotalPoints(String name){
        return 0;
    }
}
