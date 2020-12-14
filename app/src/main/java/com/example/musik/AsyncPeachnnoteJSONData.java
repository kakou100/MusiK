package com.example.musik;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.musik.R.id.montexte;

public class AsyncPeachnnoteJSONData extends AsyncTask<String, Void, String> {

    private AppCompatActivity myActivity;


    public AsyncPeachnnoteJSONData(AppCompatActivity mainActivity) {
            myActivity = mainActivity;
    }

    @Override
    protected String doInBackground(String... strings) {
        URL url = null;
        HttpURLConnection urlConnection = null;
        String line = null;
        String s = null;
        try {
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection(); // Open
            InputStream inputStream = urlConnection.getInputStream(); // Stream
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
            }
            s = stringBuilder.toString();
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }


    @Override
    protected void onPostExecute(String s) {
        ListView listview = (ListView) myActivity.findViewById(R.id.listview);
        listview.setVisibility(View.VISIBLE);
        ArrayAdapter<String> tableau = new ArrayAdapter<String>(
                listview.getContext(), R.layout.line, R.id.monTexte);

        try {
            Log.i("s=",s);
            JSONObject jsonObject = new JSONObject(s);
            JSONArray items = jsonObject.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                JSONObject obj = new JSONObject(items.getString(i));
                String title = obj.getString("title");
                tableau.add(title);
            }
            listview.setAdapter(tableau);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}

