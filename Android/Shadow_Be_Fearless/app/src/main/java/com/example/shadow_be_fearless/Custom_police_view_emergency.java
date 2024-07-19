package com.example.shadow_be_fearless;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class Custom_police_view_emergency extends BaseAdapter {

    private final Context context;
    String[] emergency_id,date,lattitude,longitude,location,username;

    public Custom_police_view_emergency(Context applicationContext, String[] emergency_id, String[] date, String[] lattitude, String[] longitude, String[] location, String[] username) {
        this.context=applicationContext;
        this.emergency_id=emergency_id;
        this.date=date;
        this.lattitude=lattitude;
        this.longitude=longitude;
        this.location=location;
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
            gridView = inflator.inflate(R.layout.custom_view_emergency_by_police, null);

        } else {
            gridView = (View) view;

        }

        TextView tv3 = (TextView) gridView.findViewById(R.id.textView110);
        TextView tv4 = (TextView) gridView.findViewById(R.id.textView111);
        TextView tv5 = (TextView) gridView.findViewById(R.id.textView112);
        Button bb2 = (Button) gridView.findViewById(R.id.button22);
        bb2.setTag(lattitude[i]);
        bb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phno=v.getTag().toString();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+phno));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);


            }
        });





        tv5.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);


        tv5.setText(lattitude[i]);
        tv3.setText(username[i]);
        tv4.setText(location[i]);


        return gridView;

    }
}

