package com.example.j14007_m.postservice3;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by J14007_m on 2017/02/10.
 */
public class AsyncHttp extends AsyncTask<String, Integer, Boolean > {
    HttpURLConnection urlConnection = null;
    Boolean flg = false;

    String latitude;
    String longitude;

    public AsyncHttp(String latitude, String longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    //非同期処理ここから
    @Override
    protected  Boolean doInBackground(String... contents){
        String urlinput = "http://10.250.0.58/upload/post.php";
        try{
            URL url = new URL(urlinput);
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);

            //POST用パラメータ
            String postDataSample = "latitude=" + latitude + "&longitude=" + longitude;
            //POSTパラメータ設定
            OutputStream out = urlConnection.getOutputStream();
            out.write(postDataSample.getBytes());
            out.flush();
            out.close();
            Log.d("test", postDataSample);
            //レスポンスを受け取る
            urlConnection.getInputStream();

            flg = true;
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return flg;
    }
}