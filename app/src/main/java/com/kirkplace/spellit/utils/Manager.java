package com.kirkplace.spellit.utils;

import com.kirkplace.spellit.constants.SpellitException;
import com.kirkplace.spellit.dao.GradeDAO;
import com.kirkplace.spellit.dao.WordDAO;
import com.kirkplace.spellit.dto.GradeDTO;
import com.kirkplace.spellit.dto.WordDTO;

/**
 * Created by kirkplace on 4/21/2015.
 */
public class Manager {

    private WordDAO wordManager = new WordDAO();
    private WordDTO word;
    private WordDTO answer;
    private GradeDAO grader = new GradeDAO();

    public void setAnswer(String answer){
        this.answer = wordManager.setAnswer(answer);
    }

    public void getNextWord(){
        this.word = wordManager.getNewWord();
    }

    public WordDTO getWord(){
        return word;
    }

    public WordDTO getAnswer(){
        return answer;
    }

    public GradeDTO checkAnswer() throws SpellitException{
        if(answer.getLength()>0 && answer.getChars().length>0)
            return grader.getGrade(word,answer);
        else{
            throw new SpellitException("No answer supplied");
        }
    }
}
