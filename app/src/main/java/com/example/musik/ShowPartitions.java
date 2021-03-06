package com.example.musik;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.musik.MainActivity.setDefaults;


public class ShowPartitions extends AppCompatActivity {

    TextView textView;
    ListView list;
    Button mFavoritesButton;
    //ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_partitions);
        mFavoritesButton = (Button) findViewById(R.id.activity_main_favorites_btn);

        Intent intent = getIntent();

        if (intent != null) {
            String title = "";
            String scoreId = "";
            Integer countPage = null;
            if (intent.hasExtra("Title")) {
                title = intent.getStringExtra("Title");
            }
            if (intent.hasExtra("ScoreId")) {
                scoreId = intent.getStringExtra("ScoreId");
            }
            if (intent.hasExtra("CountPage")) {
                countPage = intent.getIntExtra("CountPage", 1);
            }
            textView = (TextView) findViewById(R.id.textView);
            textView.setText(title);
            list = (ListView) findViewById(R.id.listview);
            BitmapAdapter tableau = new BitmapAdapter(list.getContext());
            list.setAdapter(tableau);
            //imageview = (ImageView) findViewById(R.id.image);
            for (int i = 1; i < countPage; i++) {
                AsyncBitmapDownloader task = new AsyncBitmapDownloader(tableau);
                //task.execute("http://www.peachnote.com/rest/api/v0/image?sid="
                //+ scoreId + "&page=1");
                task.execute("https://img.scores.peachnote.com/" + scoreId + "/" + i + ".png/600");
            }
            String finalTitle = title;
            mFavoritesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setDefaults("title", finalTitle, ShowPartitions.this);
                }
            });
        }
    }
}




