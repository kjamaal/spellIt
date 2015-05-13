package com.kirkplace.spellit.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kirkplace on 4/21/2015.
 */
public class GradeDTO {

    private boolean isCorrect;
    private Map<Integer,Character> charMap;

    public void setCorrect(boolean check){
        this.isCorrect = check;
    }

    public void setCharMap(Map checkMap){
        this.charMap = checkMap;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public Map<Integer, Character> getCharMap() {
        return charMap;
    }
}
