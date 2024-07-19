package com.example.shadow_be_fearless;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class user_nearby extends AppCompatActivity {
    ListView  li;
    String[] location_id,location,latitude,longitude,date,username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_nearby);
        li=(ListView)findViewById(R.id.list11);

        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":5000/us_near_by_person";



        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                JSONArray js= jsonObj.getJSONArray("data");
                                location_id=new String[js.length()];
                                location=new String[js.length()];
                                latitude=new String[js.length()];
                                longitude=new String[js.length()];
                                date=new String[js.length()];
                                username=new String[js.length()];
//                                type=new String[js.length()];
//                                discription=new String[js.length()];
//                                image=new String[js.length()];
//                                status=new String[js.length()];
//
//                                JSONArray js1= jsonObj.getJSONArray("rating");
//                                rating=new String[js1.length()];

                                for(int i=0;i<js.length();i++)
                                {
                                    JSONObject u=js.getJSONObject(i);
                                    location_id[i]=u.getString("Location_id");
                                    location[i]=u.getString("Location");
                                    latitude[i]=u.getString("Latitude");
                                    longitude[i]=u.getString("Longitude");
                                    date[i]=u.getString("Date");
                                    username[i]=u.getString("Name");
//                                    type[i]=u.getString("type");
//                                    discription[i]=u.getString("description");
//                                    image[i]=u.getString("image");
//                                    status[i]=u.getString("status");


                                }
//                                for(int i=0;i<js1.length();i++)
//                                {
//                                    JSONObject u=js1.getJSONObject(i);
//                                    rating[i]=u.getString("rating");
//
//                                }

                                // ArrayAdapter<String> adpt=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,name);
                                li.setAdapter(new Custom_user_view_nearby(getApplicationContext(),location_id,location,latitude,longitude,date,username));
                                // l1.setAdapter(new Custom(getApplicationContext(),gamecode,name,type,discription,image,status));
                            }


                            // }
                            else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                            }

                        }    catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Map<String, String> params = new HashMap<String, String>();

//                String id=sh.getString("uid","");
                params.put("lati",LocationService.lati);
                params.put("logi",LocationService.logi);

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
}

