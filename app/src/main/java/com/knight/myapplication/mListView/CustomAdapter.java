package com.knight.myapplication.mListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.knight.myapplication.R;
import com.knight.myapplication.mData.MallList;

import java.util.ArrayList;

/**
 * Created by knight on 6/18/2017.
 */

public class CustomAdapter extends BaseAdapter{


    Context c;
    ArrayList<MallList> mallLists;
    LayoutInflater inflater;

    public CustomAdapter(Context c, ArrayList<MallList> mallLists) {
        this.c = c;
        this.mallLists = mallLists;
    }

    @Override
    public int getCount() {
        return mallLists.size();
    }

    @Override
    public Object getItem(int position) {
        return mallLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(inflater==null){

            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.model, parent, false);

        }

        ImageView mallImage = (ImageView) convertView.findViewById(R.id.mallImage);
        TextView mallTitle = (TextView) convertView.findViewById(R.id.mallTitle);
        TextView mallDistance = (TextView) convertView.findViewById(R.id.mallDistance);

        int image = mallLists.get(position).getImage();
        String title = mallLists.get(position).getTitle();
        String distance = mallLists.get(position).getDistance();

        mallImage.setImageResource(image);
        mallTitle.setText(title);
        mallDistance.setText(distance);


        return convertView;
    }
}
