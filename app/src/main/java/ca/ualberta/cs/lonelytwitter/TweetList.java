package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;

import static java.util.Collections.sort;

/**
 * Created by dashley on 2015-09-30.
 */
public class TweetList {

    ArrayList<Tweet> tweets;

    public TweetList() {
        tweets = new ArrayList<Tweet>();
    }

    public Boolean hasTweet(Tweet tweet) {
        return tweets.contains(tweet);
    }

    public void addTweet(Tweet tweet) throws IllegalArgumentException {
        if (tweets.contains(tweet)) {
            throw new IllegalArgumentException("tweet already in TweetList");
        } else {
            tweets.add(tweet);
        }
    }

    public void removeTweet(Tweet tweet) {
        tweets.remove(tweet);
    }

    public ArrayList<Tweet> getTweets() {
        sort(tweets);
        return tweets;
    }

    public int getCount() {
        return tweets.size();
    }
}
