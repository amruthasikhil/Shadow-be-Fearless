package com.example.shadow_be_fearless;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Custom_user_view_reply extends BaseAdapter {

    private final Context context;
    String[] complaint,status,reply,date;

    public Custom_user_view_reply(Context applicationContext, String[] complaint, String[] status, String[] reply, String[] date) {

    this.context = applicationContext;
    this.complaint=complaint;
    this.status=status;
    this.reply=reply;
    this.date=date;
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
            gridView = inflator.inflate(R.layout.custom_user_view_reply,null);

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView64);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView71);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView73);
        TextView tv4 = (TextView) gridView.findViewById(R.id.textView75);

        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);


        tv1.setText(complaint[i]);
        tv2.setText(status[i]);
        tv3.setText(reply[i]);
        tv4.setText(date[i]);



        return gridView;

    }
}




