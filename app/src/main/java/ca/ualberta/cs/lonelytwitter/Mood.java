package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by dashley on 2015-09-16.
 */
public abstract class Mood {

    private Date date;

    public Mood(Date date) {
        this.setDate(date);
    }

    public Mood() {
        this.setDate(new Date());
    }

    public abstract String getMood();

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
