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
    BitmapAdapter adapter_ = null;
   // private ImageView imageView;
   // public AsyncBitmapDownloader(ImageView imageView) {
   //     this.imageView = imageView;
   // }

    public AsyncBitmapDownloader(BitmapAdapter adapter) {

        adapter_ = adapter;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        URL url = null;
        HttpURLConnection urlConnection = null;
        Bitmap bitmap = null;
        try {
            url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection(); // Open
            Log.i("JFL", "URL:" + String.valueOf(url));
            InputStream in = urlConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(in);
            if (bitmap == null)
            {
                Log.i("JFL", "Error decoding image !");
            }
            in.close();
        }
        catch (MalformedURLException e) { e.printStackTrace(); }
        catch (IOException e) {
                e.printStackTrace();
            }
        finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }
        return bitmap;
    }
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (bitmap != null) {
            //imageView.setImageBitmap(bitmap);
            Log.i("CIO", "Image received !");
            adapter_.add(bitmap);
            adapter_.notifyDataSetChanged();
        }
    }
}