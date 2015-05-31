package com.kirkplace.spellit.dao;

import com.kirkplace.spellit.dto.PlayerDTO;
import com.kirkplace.spellit.utils.Database;
import static com.kirkplace.spellit.constants.Data.*;

import static android.provider.BaseColumns._ID;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.SyncStateContract;

import java.util.ArrayList;

/**
 * Created by kirkplace on 5/31/2015.
 */
public class PlayerDAO {

    private static String[] COLUMNS = { _ID, NICKNAME, CURRENT_LEVEL, TOTAL_POINTS};

    private Database data;
    private ContentValues values;
    private SQLiteDatabase db;

    private int getID(String name){
        int id = 0;
        return id;
    }

    public PlayerDAO(Context ctx){
        this.data = new Database(ctx);
        this.values = new ContentValues();
    }

    public PlayerDTO getPlayer(String name){
        PlayerDTO newPlayer = new PlayerDTO();
        int id = getID(name);
        db = data.getReadableDatabase();
        String selection = SyncStateContract.Constants._ID + "=" + id;
        Cursor cursor = db.query(PLAYER_TABLE_NAME, COLUMNS, selection, null, null, null, null);
        cursor.moveToFirst();
        newPlayer.setNickName(cursor.getString(NICKNAME_INDEX));
        newPlayer.setCurrentLevel(cursor.getInt(CURRENT_LEVEL_INDEX));
        newPlayer.setTotalPoints(cursor.getInt(TOTAL_POINTS_INDEX));
        
        return newPlayer;
    }

    public int getCurrentLevel(String name){
        return 0;
    }

    public int getTotalPoints(String name){
        return 0;
    }
}
