package com.example.shadow_be_fearless;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class user_profile extends AppCompatActivity {
    ImageView img;
    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        img=(ImageView)findViewById(R.id.imageView2);
        t1 = (TextView) findViewById(R.id.textView3);
        t2 = (TextView) findViewById(R.id.name);
        t3 = (TextView) findViewById(R.id.textView10);
        t4 = (TextView) findViewById(R.id.address);
        t5 = (TextView) findViewById(R.id.textView12);
        t6 = (TextView) findViewById(R.id.phone);
        t7=(TextView)findViewById(R.id.textView14);
        t8=(TextView)findViewById(R.id.dob);
        t9=(TextView)findViewById(R.id.textView16);
        t10=(TextView)findViewById(R.id.gen);


        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String User_id=sh.getString("lid","");
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":5000/us_view_profile";



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

                                String uname=jsonObj.getString("Name");
                                String ugender=jsonObj.getString("Gender");
                                String uDOB=jsonObj.getString("DOB");
                                String uaddress=jsonObj.getString("housenumber")+"\n"+jsonObj.getString("City")+"\n"+jsonObj.getString("District")+"\n"+jsonObj.getString("Pin");
                                String ucontactinfo=jsonObj.getString("Phonenumber")+"\n"+jsonObj.getString("Emailid");
                                String uimage=jsonObj.getString("Image");

                                t2.setText(uname);
                                t10.setText(ugender);
                                t8.setText(uDOB);
                                t4.setText(uaddress);
                                t6.setText(ucontactinfo);

                                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                String ip=sh.getString("ip","");

                                String url="http://" + ip + ":5000"+uimage;


                                Picasso.with(getApplicationContext()).load(url).transform(new CircleTransform()). into(img);



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

                params.put("lid",sh.getString("lid",""));
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
    @Override
    public void onBackPressed() {
        Intent ii=new Intent(getApplicationContext(),Home.class);
        startActivity(ii);
            super.onBackPressed();
        }

}



