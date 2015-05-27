package com.kirkplace.spellit.dao;

import com.kirkplace.spellit.dto.WordDTO;

/**
 * Created by kirkplace on 4/21/2015.
 */
public class WordDAO {

    public WordDTO getNewWord(){
        WordDTO word = new WordDTO();
        word.setChars(getWord());
        word.setLength(getWord().length());
        return word;
    }

    public WordDTO setAnswer(String answer){
        WordDTO word = new WordDTO();
        word.setChars(answer);
        word.setLength(answer.length());
        return word;
    }

    private String getWord(){
        /*Go get a word from the datastore*/
        return "New Text";
    }
}
