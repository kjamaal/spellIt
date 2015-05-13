package com.kirkplace.spellit.utils;

import com.kirkplace.spellit.dto.GradeDTO;
import com.kirkplace.spellit.dto.WordDTO;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kirkplace on 4/21/2015.
 */
public class Checker {

    private static GradeDTO grade = new GradeDTO();
    private static Map<Integer,Character> map = new HashMap<Integer,Character>();

    private Checker(){

    }

    public static GradeDTO checkAnswer(WordDTO word, WordDTO answer){
        for(int i=0;i<word.getChars().length;i++){
            if(word.getChars()[i] != answer.getChars()[i]){
                grade.setCorrect(false);
                map.put(i,word.getChars()[i]);
                grade.setCharMap(map);
            }else{
                grade.setCorrect(true);
            }
        }
        grade.setCharMap(map);
        return grade;
    }
}
