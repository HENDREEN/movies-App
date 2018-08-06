package com.example.dell.moviesapp;

import android.content.Context;
import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class InfoAdapter extends ArrayAdapter<ResultsBean> {
    Context context;
    int resource;
    ArrayList<ResultsBean> objects;

    public InfoAdapter(@NonNull Context context, int resource, @NonNull List<ResultsBean> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects= (ArrayList<ResultsBean>) objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView==null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(resource, parent,false);
        }
        ResultsBean info=objects.get(position);
        TextView title=convertView.findViewById(R.id.title);
        ImageView image=convertView.findViewById(R.id.image);
        title.setText(info.getTitle());
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+info.getPosterPath()).into(image);
        return convertView;
    }
}
