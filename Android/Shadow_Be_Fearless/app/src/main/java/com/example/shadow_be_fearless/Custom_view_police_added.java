package com.example.shadow_be_fearless;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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

public class Custom_view_police_added extends BaseAdapter implements View.OnClickListener {
    private final Context context;
    String[] location,latitude,longitude,dangid;

    public Custom_view_police_added(Context applicationContext, String[] location, String[] latitude, String[] longitude,String[] dd) {
        this.context = applicationContext;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dangid=dd;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if (view == null) {
            gridView = new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView = inflator.inflate(R.layout.custom_view_police_added, null);

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView37);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView44);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView51);
        final Button bt1 = (Button) gridView.findViewById(R.id.button12);
        final Button bt2 = (Button) gridView.findViewById(R.id.button13);
        bt1.setTag(dangid[i]);
        bt2.setTag(dangid[i]);

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String dangerousid = v.getTag().toString();


                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
                String hu = sh.getString("ip", "");
                String url = "http://" + hu + ":5000/dang_delete";



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

                                        Toast.makeText(context.getApplicationContext(), "Rejected", Toast.LENGTH_SHORT).show();




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


                        params.put("dangid",dangerousid);



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
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {



                    SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
                    String hu = sh.getString("ip", "");
                    String url = "http://" + hu + ":5000/dang_approve";



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

                                            Toast.makeText(context.getApplicationContext(), "Approved", Toast.LENGTH_SHORT).show();




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


                             params.put("dangid",view.getTag().toString());

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
        tv3.setTextColor(Color.BLACK);
        bt1.setTextColor(Color.BLACK);
        bt2.setTextColor(Color.BLACK);


        tv1.setText(location[i]);
        tv2.setText(latitude[i]);
        tv3.setText(longitude[i]);



        return gridView;

    }


    @Override
    public void onClick(View view) {

    }
}



