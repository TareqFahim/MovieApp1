package com.example.icpc.movieapp.app;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ICPC on 4/1/2016.
 */
public class ImageCustomAdapter extends BaseAdapter {
    List <String> poster_paths = new ArrayList<String>();
    private Context context;

    public ImageCustomAdapter(Context cont, ArrayList pos_paths) {
        this.context = cont;
        this.poster_paths = new ArrayList(pos_paths);
    }

    @Override
    public int getCount() {
        return poster_paths.size();
    }

    @Override
    public Object getItem(int position) {
        return poster_paths.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public void clear(){

    }
    public void add(String url){
        poster_paths.add(url);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //poster_paths = (List<String>) getItem(position);
        ViewHolder viewHolder = new ViewHolder();
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_movies, parent, false);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.list_item_movies_imageview);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        Log.v("sssssss",poster_paths.get(position));
        Picasso.with(context).load(poster_paths.get(position)).into(viewHolder.imageView);
        //this.notifyDataSetChanged();
        return convertView;
    }
}
class ViewHolder{
    ImageView imageView;
}
