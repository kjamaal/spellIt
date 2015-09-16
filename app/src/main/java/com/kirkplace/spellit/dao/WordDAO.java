package com.kirkplace.spellit.dao;

import com.kirkplace.spellit.dto.WordDTO;
import com.kirkplace.spellit.utils.Database;
import static com.kirkplace.spellit.constants.Data.*;

import static android.provider.BaseColumns._ID;

import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by kirkplace on 4/21/2015.
 */
public class WordDAO {

    private static String[] SELECT_COLUMNS = { WORD, WORD_LEVEL};
    private static String[] IDS = {"_id"};
    private ArrayList<Integer> wordIndexes = new ArrayList<>();
    private Database data;

    public WordDAO(Context ctx){
        this.data = new Database(ctx);
    }

    public WordDTO getNewWord(){
        WordDTO word = new WordDTO();
        Random rand = new Random();
        SQLiteDatabase db = data.getReadableDatabase();
        Cursor ids = db.query(WORDS_TABLE_NAME, IDS, null, null, null, null, null);
        int selectedID;
        ids.moveToFirst();

        while(ids.moveToNext()){
            wordIndexes.add(ids.getInt(0));
        }

        ids.close();
        selectedID = wordIndexes.size() > 1 ? wordIndexes.get(rand.nextInt(wordIndexes.size()-1)) : wordIndexes.get(0);
        String selection = _ID + "=" + selectedID;
        Cursor cursor = db.query(WORDS_TABLE_NAME, SELECT_COLUMNS, selection, null, null, null, null);
        cursor.moveToFirst();
        word.setChars(cursor.getString(WORD_INDEX));
        word.setLength(cursor.getString(WORD_INDEX).length());
        cursor.close();
        return word;
    }

    public WordDTO setAnswer(String answer){
        WordDTO word = new WordDTO();
        word.setChars(answer);
        word.setLength(answer.length());
        return word;
    }
}
