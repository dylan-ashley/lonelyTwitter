package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by dashley on 2015-09-16.
 */
public class NormalTweet extends Tweet {

    public NormalTweet(String text, Date date) throws TweetTooLongException {
        super(text, date);
    }

    public NormalTweet(String text) throws TweetTooLongException {
        super(text);
    }

    @Override
    public Boolean isImportant() {
        return Boolean.FALSE;
    }
}
