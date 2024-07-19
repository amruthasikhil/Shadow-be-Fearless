package com.example.shadow_be_fearless;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Custom_user_view_share_idea extends BaseAdapter {
    private final Context context;

    String[]ideatitle,ideadetails;

    public Custom_user_view_share_idea(Context applicationContext, String[] ideatitle, String[] ideadetails) {
        this.context = applicationContext;
        this.ideatitle= ideatitle;
        this.ideadetails = ideadetails;
    }

    @Override
    public int getCount() {
        return ideatitle.length;
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
            gridView = inflator.inflate(R.layout.custom_view_share_idea, null);

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView99);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView101);

        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);



        tv1.setText(ideatitle[i]);
        tv2.setText(ideadetails[i]);



        return gridView;

    }
}

