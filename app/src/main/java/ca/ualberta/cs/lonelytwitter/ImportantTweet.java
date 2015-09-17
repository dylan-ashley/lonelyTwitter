package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by dashley on 2015-09-16.
 */
public class ImportantTweet extends Tweet {

    public ImportantTweet(String text, Date date) {
        super(text, date);
    }

    public ImportantTweet(String text) {
        super(text);
    }

    @Override
    public Boolean isImportant() {
        return Boolean.TRUE;
    }

    @Override
    public String getText() {
        return "!!!" + super.getText();
    }
}
