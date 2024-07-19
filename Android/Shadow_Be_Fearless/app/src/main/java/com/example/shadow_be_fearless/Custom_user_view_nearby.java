package com.example.shadow_be_fearless;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Custom_user_view_nearby extends BaseAdapter {
    private final Context context;
    String[] location_id,location,latitude,longitude,date,username;

    public Custom_user_view_nearby(Context applicationContext, String[] location_id, String[] location, String[] latitude, String[] longitude, String[] date,String[] username) {

        this.context=applicationContext;
        this.location_id=location_id;
        this.location=location;
        this.latitude=latitude;
        this.longitude=longitude;
        this.date=date;
        this.username=username;
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
            gridView = inflator.inflate(R.layout.custom_user_view_nearby, null);

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView50);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView58);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView61);
        TextView tv4 = (TextView) gridView.findViewById(R.id.textView66);
        TextView tv5 = (TextView) gridView.findViewById(R.id.textView68);

        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);
        tv5.setTextColor(Color.BLACK);


        tv1.setText(location[i]);
        tv2.setText(latitude[i]);
        tv3.setText(longitude[i]);
        tv4.setText(date[i]);
        tv5.setText(username[i]);



        return gridView;

    }
}
