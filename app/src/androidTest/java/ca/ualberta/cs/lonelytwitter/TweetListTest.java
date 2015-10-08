package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;
import android.text.BoringLayout;

import java.util.ArrayList;
import java.util.Date;

import static java.util.Collections.sort;

/**
 * Created by dashley on 2015-09-30.
 */
public class TweetListTest extends ActivityInstrumentationTestCase2 implements  MyObserver {

    private boolean wasNotified;

    public void myNotify() {
        wasNotified = true;
    }

    public TweetListTest() {
        super(LonelyTwitterActivity.class);
    }

    public void testRemoveTweet() {
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("hihihihi");
        tweetList.addTweet(tweet);
        assertTrue(tweetList.hasTweet(tweet));
        tweetList.removeTweet(tweet);
        assertFalse(tweetList.hasTweet(tweet));
    }

    public void testHasTweet() {
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("hihihihi");
        assertFalse(tweetList.hasTweet(tweet));
        tweetList.addTweet(tweet);
        assertTrue(tweetList.hasTweet(tweet));
    }

    public void testAddTweet() {
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("hihihihi");
        tweetList.addTweet(tweet);
        assertTrue(tweetList.hasTweet(tweet));
    }

    public void testGetCount() {
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("hihihihi");
        assertEquals(tweetList.getCount(), 0);
        tweetList.addTweet(tweet);
        assertEquals(tweetList.getCount(), 1);
        tweetList.removeTweet(tweet);
        assertEquals(tweetList.getCount(), 0);
    }

    public void testGetTweets() {
        Tweet tweet1 = new NormalTweet("hihihihi", new Date(1000));
        Tweet tweet2 = new NormalTweet("hihihi", new Date(100));
        Tweet tweet3 = new NormalTweet("hihi", new Date(10));
        Tweet tweet4 = new NormalTweet("hi", new Date(1));
        ArrayList<Tweet> orderedTweets = new ArrayList<Tweet>();
        orderedTweets.add(tweet1);
        orderedTweets.add(tweet2);
        orderedTweets.add(tweet3);
        orderedTweets.add(tweet4);
        sort(orderedTweets);
        TweetList tweetList = new TweetList();
        tweetList.addTweet(tweet4);
        tweetList.addTweet(tweet3);
        tweetList.addTweet(tweet2);
        tweetList.addTweet(tweet1);
        ArrayList<Tweet> returnedTweets = tweetList.getTweets();
        assertEquals(orderedTweets, returnedTweets);
    }

    public void testTweetListChanged() {
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("hihihihi");
        tweetList.addObserver(this);
        wasNotified = false;
        tweetList.addTweet(tweet);
        assertTrue(wasNotified);
    }
}
