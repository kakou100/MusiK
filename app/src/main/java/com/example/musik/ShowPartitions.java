package com.example.musik;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowPartitions extends AppCompatActivity {

    TextView textView;
    ImageView imageview;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_partitions);
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
                countPage = intent.getIntExtra("CountPage",1);
            }
            textView = (TextView) findViewById(R.id.textView);
            textView.setText(title);
            imageview = (ImageView) findViewById(R.id.image); // bug of id here !!!
            AsyncBitmapDownloader task = new AsyncBitmapDownloader(imageview);

            // The good URL seems to be:
            // https://img.scores.peachnote.com/id/page.png
            //task.execute("www.peachnote.com/rest/api/v0/image?sid=" + scoreId + "&page=1");
            task.execute("https://img.scores.peachnote.com/" + scoreId + "/1.png/600");
        }
    }
}
