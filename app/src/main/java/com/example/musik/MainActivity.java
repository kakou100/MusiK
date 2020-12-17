package com.example.musik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class MainActivity extends AppCompatActivity {
    public String research;
    private TextView mGreetingText;
    private EditText mResearchInput;
    private Button mSearchButton;
    private Button mFavoritesButton;
    ListView listview;

    public static class InfoScore {
        public String title;
        public String scoreId;
        public int countPage;
        //constructor
        public InfoScore(String s, String id, int p) {
            title = s;
            scoreId = id;
            countPage = p;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGreetingText = (TextView) findViewById(R.id.activity_main_greeting_txt);
        mResearchInput = (EditText) findViewById(R.id.activity_main_research_input);
        mSearchButton = (Button) findViewById(R.id.activity_main_search_btn);
        mFavoritesButton = (Button) findViewById(R.id.activity_main_favorites_btn);
        mSearchButton.setEnabled(false);
        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setVisibility(View.INVISIBLE);


        mResearchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // This is where we'll check the user input
            }

            @Override
            public void afterTextChanged(Editable s) {
                mSearchButton.setEnabled(s.toString().length() != 0);
            }
        });
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                research = mResearchInput.getText().toString();
                AsyncPeachnnoteJSONData task = new AsyncPeachnnoteJSONData(MainActivity.this);
                task.execute("http://www.peachnote.com/rest/api/v0/scoreSearchMeta"
                + "?q=" + research);
            }
        });

        mFavoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent favorites = new Intent(MainActivity.this, Favorites.class);
                startActivity(favorites);
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                InfoScore infoscore = (InfoScore) listview.getAdapter().getItem(position);
                Log.i("ScoreId =", infoscore.scoreId);
                Intent showPartitions = new Intent(MainActivity.this, ShowPartitions.class);
                showPartitions.putExtra("Title", infoscore.title);
                showPartitions.putExtra("ScoreId", infoscore.scoreId);
                showPartitions.putExtra("CountPage", infoscore.countPage);
                startActivity(showPartitions);
            }
        });


    }
    public static void setDefaults(String key, String value, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }
}