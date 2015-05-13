package com.kirkplace.spellit.constants;

/**
 * Created by kirkplace on 4/21/2015.
 */
public class SpellitException extends Exception {

    private String message;

    public SpellitException(String detailMessage) {
        this.message = detailMessage;
    }

    @Override
    public String toString() {
        return message;
    }
}
