package com.example.musik;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AsyncBitmapDownloader extends AsyncTask<String, Void, Bitmap> {
    private final static String TAG = "AsyncBitmapDownloader";
    private ImageView imageView;
    public AsyncBitmapDownloader(ImageView imageView) {
        this.imageView = imageView;
    }
    @Override
    protected Bitmap doInBackground(String... params) {
        URL url = null;
        HttpURLConnection urlConnection = null;
        Bitmap bitmap = null;
        try {
            url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection(); // Open
            Log.i("url =", String.valueOf(url));
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            Log.i("inputStream", String.valueOf(in));
            bitmap = BitmapFactory.decodeStream(in);
            in.close();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
        finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }
        return bitmap;
    }
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}

