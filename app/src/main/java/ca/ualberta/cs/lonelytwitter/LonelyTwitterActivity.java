package ca.ualberta.cs.lonelytwitter;  // model

import java.io.BufferedReader;  // model
import java.io.BufferedWriter;  // model
import java.io.FileInputStream;  // model
import java.io.FileNotFoundException;  // model
import java.io.FileOutputStream;  // model
import java.io.IOException;  // model
import java.io.InputStreamReader;  // model
import java.io.OutputStreamWriter;  // model
import java.lang.reflect.Type;  // model
import java.util.ArrayList;  // model

import android.app.Activity;  // model
import android.content.Context;  // model
import android.content.Intent;
import android.os.Bundle;  // model
import android.view.View;  // model
import android.widget.AdapterView;
import android.widget.ArrayAdapter;  // model
import android.widget.Button;  // model
import android.widget.EditText;  // model
import android.widget.ListView;  // model

import com.google.gson.Gson;  // model
import com.google.gson.reflect.TypeToken;  // model

public class LonelyTwitterActivity extends Activity {  // view/controller

    private static final String FILENAME = "file.sav"; // model
    private EditText bodyText; // view/controller
    private TweetList tweets = new TweetList(); // model
    private ListView oldTweetsList;  // view
    private ArrayAdapter<Tweet> adapter;  // controller
    private Button saveButton;
    private Button clearButton;
    private Activity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {  // controller

        super.onCreate(savedInstanceState);  // controller
        setContentView(R.layout.main);  // view

        bodyText = (EditText) findViewById(R.id.body);  // view/controller
        saveButton = (Button) findViewById(R.id.save);  // view/controller
        clearButton = (Button) findViewById(R.id.clear);  // view/controller
        oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);  // view

        saveButton.setOnClickListener(new View.OnClickListener() {  // controller

            public void onClick(View v) {  // controller
                setResult(RESULT_OK);  // model
                String text = bodyText.getText().toString();  // controller
                tweets.addTweet(new NormalTweet(text));  // model
                saveInFile();  // model
                adapter.notifyDataSetChanged();  // controller
                bodyText.setText("");  // view
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {  // controller

            public void onClick(View v) {  // controller
                setResult(RESULT_OK);  // model
                tweets.clear();  // model
                saveInFile();  // model
                adapter.notifyDataSetChanged();  // controller
                bodyText.setText("");  // view
            }
        });

        activity = this;
        oldTweetsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(activity, EditTweetActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {  // controller
        super.onStart();  // controller
        loadFromFile();  // model
        adapter = new ArrayAdapter<Tweet>(this, R.layout.list_item, tweets.getTweets());  // model
        oldTweetsList.setAdapter(adapter);  // model
        adapter.notifyDataSetChanged();  // controller
    }

    private void loadFromFile() {  // model
        try {  // controller
            FileInputStream fis = openFileInput(FILENAME);  // model
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));  // model
            Gson gson = new Gson();  // model
            // https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html; 2015-09-23
            Type arrayListType = new TypeToken<ArrayList<NormalTweet>>() {}.getType();  // model
            ArrayList<NormalTweet> tweetArrayList = gson.fromJson(in, arrayListType);  // model
            tweets = new TweetList();
            for (Tweet tweet: tweetArrayList) {
                tweets.addTweet(tweet);
            }
        } catch (FileNotFoundException e) {  // controller
            tweets = new TweetList();  // model
        }
    }

    private void saveInFile() {  // model
        try {  // controller
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);  // model
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));  // model
            Gson gson = new Gson();  // model
            gson.toJson(tweets.getTweets(), out);  // model
            out.flush();  // model
            fos.close();  // model
        } catch (FileNotFoundException e) {  // controller
            throw new RuntimeException(e);  // view
        } catch (IOException e) {  // controller
            throw new RuntimeException(e);  // view
        }
    }

    public TweetList getTweetList() {
        return tweets;
    }

    public EditText getBodyText() {
        return bodyText;
    }

    public Button getClearButton() {
        return clearButton;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public ListView getOldTweetsList() {
        return oldTweetsList;
    }
}
