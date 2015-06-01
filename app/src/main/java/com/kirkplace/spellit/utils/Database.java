package com.kirkplace.spellit.utils;

import static com.kirkplace.spellit.constants.Data.*;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kirkplace on 5/31/2015.
 */
public class Database extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "Spellit.db";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_PLAYERS = "CREATE TABLE " + PLAYER_TABLE_NAME + "("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NICKNAME + " TEXT NOT NULL,"
            + CURRENT_LEVEL + " INTEGER NOT NULL,"
            + TOTAL_POINTS + " INTEGER NOT NULL);";

    private static final String CREATE_WORDS = "CREATE TABLE " + WORDS_TABLE_NAME + "("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + WORD + " TEXT NOT NULL,"
            + WORD_LEVEL + " INTEGER NOT NULL);";

    private static final String TEST_INSERT = "INSERT INTO " + WORDS_TABLE_NAME + " VALUES(1,'TEST',1);";

    public Database(Context ctx){
        super(ctx,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PLAYER_TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PLAYERS);
        db.execSQL(CREATE_WORDS);
        db.execSQL(TEST_INSERT);
    }
}
