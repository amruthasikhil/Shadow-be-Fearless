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

public class Custom_view_police_by_user extends BaseAdapter {

    private final Context context;
    String[] image, name, phonenumber, ulid;

    public Custom_view_police_by_user(Context applicationContext, String[] image, String[] name, String[] phonenumber, String[] ulid) {
        this.context = applicationContext;
        this.image = image;
        this.name = name;
        this.ulid = ulid;
        this.phonenumber = phonenumber;


    }


    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if (view == null) {
            gridView = new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView = inflator.inflate(R.layout.custom_userviewpolice, null);

        } else {
            gridView = (View) view;

        }
        ImageView im1 = (ImageView) gridView.findViewById(R.id.imageView4);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView104);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView105);


        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);


        tv2.setText(name[i]);
        tv3.setText(phonenumber[i]);

        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        String ip = sh.getString("ip", "");

        String url = "http://" + ip + ":5000" + image[i];


        Picasso.with(context.getApplicationContext()).load(url).transform(new CircleTransform()).into(im1);


        return gridView;


    }
}