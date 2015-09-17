package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by dashley on 2015-09-16.
 */
public class BadMood extends Mood {

    public BadMood(Date date) {
        super(date);
    }

    public BadMood() {
        super();
    }

    @Override
    public String getMood() {
        return ":(";
    }
}
