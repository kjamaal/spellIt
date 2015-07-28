package com.kirkplace.spellit.constants;

import android.provider.BaseColumns;
/**
 * Created by kirkplace on 5/31/2015.
 */
public interface Data extends BaseColumns{

    public static final String PLAYER_TABLE_NAME = "players";
    public static final String NICKNAME = "name";
    public static final String CURRENT_LEVEL = "current_level";
    public static final String TOTAL_POINTS = "total_points";
    public static final int NICKNAME_INDEX = 0;
    public static final int CURRENT_LEVEL_INDEX = 1;
    public static final int TOTAL_POINTS_INDEX = 2;

    public static final String WORDS_TABLE_NAME = "words";
    public static final String WORD = "word";
    public static final String WORD_LEVEL = "level";
    public static final int WORD_INDEX = 0;
    public static final int WORD_LEVEL_INDEX = 1;
}
