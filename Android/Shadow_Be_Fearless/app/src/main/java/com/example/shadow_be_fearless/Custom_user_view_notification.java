package com.example.shadow_be_fearless;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Custom_user_view_notification extends BaseAdapter {
    private final Context context;
    String[] notification_id,message,date;

    public Custom_user_view_notification(Context applicationContext, String[] notification_id, String[] message, String[] date) {
       this.context=applicationContext;
       this.notification_id=notification_id;
       this.message=message;
       this.date=date;
    }

    @Override
    public int getCount() {
        return message.length;
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
            gridView = inflator.inflate(R.layout.custom_user_view_notification, null);

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView90);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView100);

        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);



        tv1.setText(message[i]);
        tv2.setText(date[i]);

        return gridView;

    }
}
