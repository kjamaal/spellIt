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
        grade.clear();
        for(int i=0;i<word.getLength();i++){
            if(i < answer.getLength()){
                if (word.getWordChars()[i] != answer.getWordChars()[i]) {
                    grade.setCorrect(false);
                    map.put(i, word.getWordChars()[i]);
                } else {
                    if(map.size() == 0 && word.getLength() == answer.getLength()) {
                        grade.setCorrect(true);
                    }
                }
            }else{
                map.put(i, word.getWordChars()[i]);
            }
        }
        grade.setCharMap(map);
        return grade;
    }
}
