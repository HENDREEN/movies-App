package com.example.dell.moviesapp;


import android.graphics.Movie;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<ResultsBean>> {
  GridView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.list_item);
            LoaderManager loaderManager = getSupportLoaderManager();
            loaderManager.initLoader(0, null, this).forceLoad();

    }
    @Override
    public Loader<ArrayList<ResultsBean>> onCreateLoader(int id, Bundle args) {
       MovieAyancTask movieAyancTask=new MovieAyancTask(this);
        return movieAyancTask;
    }
    @Override
    public void onLoadFinished(Loader<ArrayList<ResultsBean>> loader, ArrayList<ResultsBean> data) {
        InfoAdapter adapter = new InfoAdapter(MainActivity.this, R.layout.single_row, data);
        listView.setAdapter(adapter);
    }
    @Override
    public void onLoaderReset(Loader<ArrayList<ResultsBean>> loader) {

    }

    /*
    InfoAdapter adapter = new InfoAdapter(MainActivity.this, R.layout.single_row, data);
        listView.setAdapter(adapter);
    class MoviesAsyncTask extends AsyncTask<URL, Void, StringBuilder> {
        GridView listView=findViewById(R.id.list_item);
        HttpURLConnection httpURLConnection=null;
        InputStream inputStream=null;
        @Override
        protected StringBuilder doInBackground(URL... urls) {
            StringBuilder stringBuilder=new StringBuilder();
            try {
                httpURLConnection = (HttpURLConnection) urls[0].openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setReadTimeout(15000);
                httpURLConnection.connect();
                inputStream=httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String line=bufferedReader.readLine();
                while(line!=null){
                    stringBuilder.append(line);
                    line=bufferedReader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return stringBuilder;
        }

        @Override
        protected void onPostExecute(StringBuilder s) {
            super.onPostExecute(s);
            String stringAttach="https://image.tmdb.org/t/p/w500";
            ArrayList<Information> arrayList=new ArrayList<>();
            try {
                JSONObject root=new JSONObject(String.valueOf(s));
                JSONArray arrayInfo=root.getJSONArray("results");
                for (int i = 0; i <arrayInfo.length() ; i++) {
                    JSONObject object=arrayInfo.getJSONObject(i);
                    String title=object.getString("title");
                    String image=object.getString("poster_path");
                    arrayList.add(new Information(title,stringAttach+image));
                }
                InfoAdapter adapter = new InfoAdapter(MainActivity.this, R.layout.single_row, arrayList);
                listView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    }
    */

}

