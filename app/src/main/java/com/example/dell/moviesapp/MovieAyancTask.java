package com.example.dell.moviesapp;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class MovieAyancTask extends AsyncTaskLoader<ArrayList<ResultsBean>> {
    HttpURLConnection httpURLConnection ;
    InputStream inputStream ;
    URL url;
    StringBuilder stringBuilder = new StringBuilder();

    public MovieAyancTask(Context context) {
        super(context);
    }

    @Override
    public ArrayList<ResultsBean> loadInBackground() {
        String api = "https://api.themoviedb.org/3/movie/popular?api_key=e9ac1a9d68fa0be1742309e2d22eba8f&language=en-US&page=1";
        String stringAttach = "https://image.tmdb.org/t/p/w500";
        ArrayList<ResultsBean> arrayList = new ArrayList<>();
        try {
            url = new URL(api);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.connect();
            inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                line = bufferedReader.readLine();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson json=new Gson();
        Root  root = json.fromJson(stringBuilder.toString(),Root.class);
        arrayList=root.getResults();
        /*
        try {




          JSONObject  root=new JSONObject(String.valueOf(stringBuilder));

               JSONArray arrayInfo = root.getJSONArray("results");

            for (int i = 0; i < arrayInfo.length(); i++) {
                JSONObject object = arrayInfo.getJSONObject(i);

                   String title = object.getString("title");

              String  image = object.getString("poster_path");
                arrayList.add(new Information(title, stringAttach + image));


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        */
        return arrayList;
    }
}