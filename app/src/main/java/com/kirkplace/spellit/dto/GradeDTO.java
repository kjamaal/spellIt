package com.kirkplace.spellit.dto;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kirkplace on 4/21/2015.
 */
public class GradeDTO implements Parcelable{

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
