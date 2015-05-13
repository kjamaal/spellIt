package com.kirkplace.spellit.dao;

import com.kirkplace.spellit.dto.GradeDTO;
import com.kirkplace.spellit.dto.WordDTO;

import java.util.Map;

/**
 * Created by kirkplace on 4/21/2015.
 */
public class GradeDAO {

    private GradeDTO grade;

    public GradeDTO getGrade(WordDTO word, WordDTO answer){
        grade = com.kirkplace.spellit.utils.Checker.checkAnswer(word,answer);
        return grade;
    }
}
