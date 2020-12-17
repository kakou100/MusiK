package com.example.musik;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.musik.MainActivity.getDefaults;

public class Favorites extends AppCompatActivity {
    private TextView textView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites);
        String title;
        title = getDefaults("title",Favorites.this);
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(title);

    }
}
