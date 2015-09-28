package com.kirkplace.spellit.utils;

import static com.kirkplace.spellit.constants.Data.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kirkplace.spellit.R;
import com.kirkplace.spellit.constants.SpellitException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by kirkplace on 5/31/2015.
 */
public class Database extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "Spellit.db";
    private static final int DATABASE_VERSION = 1;
    private static InputStream wordsInputStream;
    private static InputStream playersInputStream;

    private static final String CREATE_PLAYERS = "CREATE TABLE " + PLAYER_TABLE_NAME + "("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NICKNAME + " TEXT NOT NULL,"
            + CURRENT_LEVEL + " INTEGER NOT NULL,"
            + TOTAL_POINTS + " INTEGER NOT NULL);";

    private static final String CREATE_WORDS = "CREATE TABLE " + WORDS_TABLE_NAME + "("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + WORD + " TEXT NOT NULL,"
            + USAGE + " TEXT NOT NULL,"
            + WORD_LEVEL + " INTEGER NOT NULL);";

    public Database(Context ctx){
        super(ctx,DATABASE_NAME, null, DATABASE_VERSION);
        wordsInputStream = ctx.getResources().openRawResource(R.raw.words);
        playersInputStream = ctx.getResources().openRawResource(R.raw.players);
        SQLiteDatabase work = this.getWritableDatabase();
        work.execSQL("DROP TABLE IF EXISTS " + WORDS_TABLE_NAME);
        work.execSQL("DROP TABLE IF EXISTS " + PLAYER_TABLE_NAME);
        this.onCreate(this.getWritableDatabase());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PLAYER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + WORDS_TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        JSONObject wordsJson;
        JSONObject playersJson;
        JSONArray wArray;
        JSONArray pArray;
        db.execSQL(CREATE_PLAYERS);
        db.execSQL(CREATE_WORDS);
        try {
            wordsJson = new JSONObject(getJson(wordsInputStream));
            playersJson = new JSONObject(getJson(playersInputStream));
            wArray = wordsJson.getJSONArray("words");
            pArray = playersJson.getJSONArray("players");
            for(int i = 0; i < wArray.length(); i++) {
                db.execSQL("INSERT INTO " + WORDS_TABLE_NAME + " (" + WORD + ", "+ USAGE + ", " + WORD_LEVEL + ") VALUES('" + wArray.getJSONObject(i).get("word")+"','"+wArray.getJSONObject(i).get("usage")+"',"+wArray.getJSONObject(i).getInt("word_level")+");");
            }
            for(int i=0; i < pArray.length(); i++){
                db.execSQL("INSERT INTO " + PLAYER_TABLE_NAME + " (" + NICKNAME + ", " + CURRENT_LEVEL + "," + TOTAL_POINTS + ") VALUES('" + pArray.getJSONObject(i).get("nickname")+"','"+pArray.getJSONObject(i).getInt("current_level")+"','"+pArray.getJSONObject(i).getInt("total_points")+"');");
            }

        }catch(SpellitException ex){
            //TODO: Handle exception
        }catch(JSONException ex){
            //TODO: Handle exception
        }
    }

    private String getJson(InputStream is)throws SpellitException{
        try {
            int size = is.available();
            byte[] b = new byte[size];
            is.read(b);
            is.close();
            String json = new String(b,"UTF-8");
            return json;
        }catch(IOException ex){
            throw new SpellitException("database connection error");
        }

    }
}
