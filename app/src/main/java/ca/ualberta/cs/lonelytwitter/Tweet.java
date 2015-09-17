package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by dashley on 2015-09-16.
 */
public abstract class Tweet {

    private String text;
    private Date date;

    public Tweet(String text, Date date) {
        this.setText(text);
        this.setDate(date);
    }

    public Tweet(String text) {
        this.setText(text);
        this.setDate(new Date());
    }

    public abstract Boolean isImportant();

    public String getText() {
        return text;
    }

    public void setText(String text) {
        if (text.length() <= 140) {
            this.text = text;
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
