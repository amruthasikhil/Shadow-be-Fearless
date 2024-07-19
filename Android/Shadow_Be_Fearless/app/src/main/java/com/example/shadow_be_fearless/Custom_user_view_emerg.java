package com.example.shadow_be_fearless;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Custom_user_view_emerg extends BaseAdapter {
    private final Context context;
    String[] emergency_id,date,lattitude,longitude,location,username;

    public Custom_user_view_emerg(Context applicationContext, String[] emergency_id, String[] date, String[] lattitude, String[] longitude, String[] location) {
      this.context=applicationContext;
        this.emergency_id=emergency_id;
        this.date=date;
        this.lattitude=lattitude;
        this.longitude=longitude;
        this.location=location;

    }

    @Override
    public int getCount() {
        return lattitude.length;
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
            gridView = inflator.inflate(R.layout.custom_user_view_emerg, null);

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView87);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView91);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView93);
        TextView tv4 = (TextView) gridView.findViewById(R.id.textView95);



        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);



        tv1.setText(date[i]);
        tv2.setText(lattitude[i]);
        tv3.setText(longitude[i]);
        tv4.setText(location[i]);



        return gridView;

    }
}

