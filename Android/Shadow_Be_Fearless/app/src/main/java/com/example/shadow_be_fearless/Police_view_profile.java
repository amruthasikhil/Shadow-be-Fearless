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

public class Police_view_profile extends AppCompatActivity {
    TextView t1,t2,t6,t7,t8,t9,t10,t11,t13,t15,t18,t19,t20;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_view_profile);
        t2 = (TextView) findViewById(R.id.name);
        t6 = (TextView) findViewById(R.id.phone);
        t15 = (TextView) findViewById(R.id.address);
        t18 = (TextView) findViewById(R.id.dob);
        t20 = (TextView) findViewById(R.id.email);
        img = (ImageView) findViewById(R.id.imageView);

        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String policeid=sh.getString("lid","");
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":5000/pc_view_profile";



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

                                String pname=jsonObj.getString("Name");
                                String pDOB=jsonObj.getString("Dob");
                                String pstationname=jsonObj.getString("Stationname")+"\n"+jsonObj.getString("City")+"\n"+jsonObj.getString("District")+"\n"+jsonObj.getString("State")+"\n"+jsonObj.getString("Pin");
                                String pphonenumber=jsonObj.getString("Phonenumber");
                                String pemail=jsonObj.getString("Emailid");
                                String pimage=jsonObj.getString("Image");

                                t2.setText(pname);
                                t18.setText(pDOB);
                                t15.setText(pstationname);

                                t6.setText(pphonenumber);
                                t20.setText(pemail);

                                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                String ip=sh.getString("ip","");

                                String url="http://" + ip + ":5000"+pimage;


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
        Intent ii=new Intent(getApplicationContext(),Policehomepage.class);
        startActivity(ii);
        super.onBackPressed();
    }

}

