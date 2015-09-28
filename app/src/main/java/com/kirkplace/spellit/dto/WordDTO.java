package com.kirkplace.spellit.dto;

/**
 * Created by kirkplace on 4/21/2015.
 */
public class WordDTO {
    private int length;
    private char[] chars;
    private char[] usage;

    public void setLength(int length){
        this.length = length;
    }

    public void setChars(String word){
        chars = word.toLowerCase().toCharArray();
    }

    public void setUsage(String use){usage = use.toLowerCase().toCharArray();}

    public int getLength(){
        return length;
    }

    public char[] getWordChars(){
        return chars;
    }

    public char[] getUsageChars(){
        return usage;
    }
}
