package com.kirkplace.spellit.dto;

/**
 * Created by kirkplace on 4/21/2015.
 */
public class WordDTO {
    private int length;
    private char[] chars;

    public void setLength(int length){
        this.length = length;
    }

    public void setChars(String word){
        chars = word.toLowerCase().toCharArray();
    }

    public int getLength(){
        return length;
    }

    public char[] getChars(){
        return chars;
    }
}
