package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import junit.framework.TestCase;

/**
 * Created by wz on 14/09/15.
 */
public class LonelyTwitterActivityTest extends ActivityInstrumentationTestCase2 {

    private Button saveButton;
    private Button clearButton;
    private EditText bodyText;
    private ListView oldTweetsList;
    private EditText tweetText;
    private Button editTweetSaveButton;
    private Tweet tweet;

    public LonelyTwitterActivityTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    public void testStart() throws Exception {
        Activity activity = getActivity();
        activity.finish();
    }

    public void testEditTweet() {
        // when you call getActivity android will start the app and the activity
        LonelyTwitterActivity activity = (LonelyTwitterActivity) getActivity();

        // reset the app to a known state
        clearButton = activity.getClearButton();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                clearButton.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();

        // add a tweet using the UI
        bodyText = activity.getBodyText();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                bodyText.setText("test tweet");
            }
        });
        getInstrumentation().waitForIdleSync();

        // save tweet
        saveButton = activity.getSaveButton();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                saveButton.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();

        // make sure the tweet was actually added
        oldTweetsList = activity.getOldTweetsList();
        tweet = (Tweet) oldTweetsList.getItemAtPosition(0);

        // https://developer.android.com/training/activity-testing/activity-functional-testing.html; 2015-10-14
        // Set up an ActivityMonitor
        ActivityMonitor receiverActivityMonitor = getInstrumentation().addMonitor(EditTweetActivity.class.getName(), null, false);

        // click on tweet to edit
        activity.runOnUiThread(new Runnable() {
            public void run() {
                View v = oldTweetsList.getChildAt(0);
                oldTweetsList.performItemClick(v, 0, v.getId());
            }
        });
        getInstrumentation().waitForIdleSync();

        // Validate that ReceiverActivity is started
        EditTweetActivity tweetActivity = (EditTweetActivity) receiverActivityMonitor.waitForActivityWithTimeout(1000);
        assertNotNull("ReceiverActivity is null", tweetActivity);
        assertEquals("Monitor for ReceiverActivity has not been called", 1, receiverActivityMonitor.getHits());
        assertEquals("Activity is of wrong type", EditTweetActivity.class, tweetActivity.getClass());

        // Remove the ActivityMonitor
        getInstrumentation().removeMonitor(receiverActivityMonitor);

        // test that the editor starts up with the right tweet in it to edit
        tweetText = tweetActivity.getTweetText();
        assertEquals(tweetText.getText(), "test tweet");

        // test that we can edit that tweet
        activity.runOnUiThread(new Runnable() {
            public void run() {
                tweetText.setText("hi potato");
            }
        });
        getInstrumentation().waitForIdleSync();
        tweetText = tweetActivity.getTweetText();
        assertEquals(tweetText.getText(), "hi potato");

        // test that we can push some kind of save button for that tweet
        editTweetSaveButton = tweetActivity.getSaveButton();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                editTweetSaveButton.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();

        // the new modified tweet text was actually saved
        Tweet newTweet = (Tweet) oldTweetsList.getItemAtPosition(0);
        assertEquals(newTweet.getText(), "hi potato");

        // the new modified tweet text is displayed on the other activity
        oldTweetsList = activity.getOldTweetsList();
        tweet = (Tweet) oldTweetsList.getItemAtPosition(0);
        assertEquals(tweet.getText(), "hi potato");

        // clean up our activities at the end of our test
        tweetActivity.finish();
    }
}
