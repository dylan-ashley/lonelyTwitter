package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by dashley on 2015-09-16.
 */
public class GoodMood extends Mood {

    public GoodMood(Date date) {
        super(date);
    }

    public GoodMood() {
        super();
    }

    @Override
    public String getMood() {
        return ":)";
    }
}
