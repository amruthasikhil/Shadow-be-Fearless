package com.example.shadow_be_fearless;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Customer_user_view_emergency_no extends BaseAdapter {
    private final Context context;
    String[] name,phoneno,id;

    public Customer_user_view_emergency_no(Context applicationContext, String[] name, String[] phoneno, String[] idd) {
        this.context=applicationContext;
        this.name=name;
        this.phoneno=phoneno;
        this.id=idd;
    }

    @Override
    public int getCount() {
        return name.length;
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
            gridView = inflator.inflate(R.layout.custom_user_view_emergency_no, null);

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView81);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView83);
        Button bb2 = (Button) gridView.findViewById(R.id.button27);

        Button bb3 = (Button) gridView.findViewById(R.id.button29);

        bb2.setTag(phoneno[i]);
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

        bb3.setTag(id[i]);
        bb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String eid = v.getTag().toString();


                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
                String hu = sh.getString("ip", "");
                String url = "http://" + hu + ":5000/erno_delete";



                RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                                // response
                                try {
                                    JSONObject jsonObj = new JSONObject(response);
                                    if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                        Toast.makeText(context.getApplicationContext(), "Deleted..!!", Toast.LENGTH_SHORT).show();




                                        // l1.setAdapter(new Custom(getApplicationContext(),gamecode,name,type,discription,image,status));
                                    }


                                    // }
                                    else {
                                        Toast.makeText(context.getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                                    }

                                }    catch (Exception e) {
                                    Toast.makeText(context.getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Toast.makeText(context.getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() {
                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
                        Map<String, String> params = new HashMap<String, String>();


                        params.put("eid",eid);



                        return params;
                    }
                };

                int MY_SOCKET_TIMEOUT_MS=100000;

                postRequest.setRetryPolicy(new DefaultRetryPolicy(
                        MY_SOCKET_TIMEOUT_MS,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                requestQueue.add(postRequest);










            }
        });



        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);


        tv1.setText(name[i]);
        tv2.setText(phoneno[i]);


        return gridView;

    }
}



