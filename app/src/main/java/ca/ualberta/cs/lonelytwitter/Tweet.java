package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by dashley on 2015-09-16.
 */
public abstract class Tweet implements Tweetable {

    private String text;
    private Date date;

    private ArrayList<Mood> moodArrayList;

    public Tweet(String text, Date date) throws TweetTooLongException {
        this.setText(text);
        this.setDate(date);
        this.setMoodArrayList(new ArrayList<Mood>());
    }

    public Tweet(String text) throws TweetTooLongException {
        this.setText(text);
        this.setDate(new Date());
        this.setMoodArrayList(new ArrayList<Mood>());
    }

    public abstract Boolean isImportant();

    public String getText() {
        return text;
    }

    public void setText(String text) throws TweetTooLongException {
        if (text.length() <= 140) {
            this.text = text;
        } else {
            throw new TweetTooLongException("Tweet was too long!");
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private ArrayList<Mood> getMoodArrayList() {
        return this.moodArrayList;
    }

    private void setMoodArrayList(ArrayList<Mood> moodArrayList) {
        this.moodArrayList = moodArrayList;
    }
}
