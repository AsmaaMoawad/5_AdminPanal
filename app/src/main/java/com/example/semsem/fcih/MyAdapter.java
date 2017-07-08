package com.example.semsem.fcih;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by semsem on 5/6/2017.
 */
public class MyAdapter extends BaseAdapter {
    String[] id;
    String[] name;
    String[]gpa;
    String[] hours;
    Activity activity;

    public MyAdapter(String[] id, String[] name, String[] gpa,String[] hours,Activity activity) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
        this.activity = activity;
        this.hours = hours;
    }

    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int position) {
        return name[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=activity.getLayoutInflater();
        View v=inflater.inflate(R.layout.row,null);
        TextView tv_id=(TextView) v.findViewById(R.id.tv_id);
        TextView tv_name=(TextView) v.findViewById(R.id.tv_name);
        TextView tv_gpa=(TextView) v.findViewById(R.id.tv_gpa);
        TextView tv_hour=(TextView) v.findViewById(R.id.tv_hour);
        tv_id.setText(id[position]);
        tv_name.setText(name[position]);
        tv_gpa.setText(gpa[position]);
        tv_hour.setText(hours[position]);
        return v;
    }
}
