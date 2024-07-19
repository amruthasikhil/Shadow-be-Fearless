package com.example.shadow_be_fearless;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Custom_police_view_dangerous extends BaseAdapter {

    private final Context context;
    String[] location,latitude,longitude;

    public Custom_police_view_dangerous(Context applicationContext, String[] location, String[] latitude, String[] longitude) {

        this.context = applicationContext;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    @Override
    public int getCount() {
        return location.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if (view == null) {
            gridView = new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView = inflator.inflate(R.layout.custom_police_view_dangerous, null);

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView32);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView34);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView36);

        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);


        tv1.setText(location[i]);
        tv2.setText(latitude[i]);
        tv3.setText(longitude[i]);


        return gridView;

    }
}
