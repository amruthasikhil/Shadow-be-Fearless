package com.example.shadow_be_fearless;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Custom_police_view_complaints  extends BaseAdapter {
    private final Context context;
    String[] complaint, user, date, complaint_id;


    public Custom_police_view_complaints(Context applicationContext, String[] complaint, String[] user, String[] date, String[] complaint_id) {

        this.context = applicationContext;
        this.complaint = complaint;
        this.user = user;
        this.date = date;
    }

    @Override
    public int getCount() {
        return complaint.length;
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
            gridView = inflator.inflate(R.layout.custom_police_view_complaints, null);

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView24);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView26);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView28);



        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);


        tv1.setText(complaint[i]);
        tv2.setText(user[i]);
        tv3.setText(date[i]);


        return gridView;

    }
}