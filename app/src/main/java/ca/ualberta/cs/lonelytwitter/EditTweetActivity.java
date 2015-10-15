package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class EditTweetActivity extends Activity {

    private EditText tweetText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tweet);

        tweetText = (EditText) findViewById(R.id.editText);
        saveButton = (Button) findViewById(R.id.save_button);
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public EditText getTweetText() {
        return tweetText;
    }
}
