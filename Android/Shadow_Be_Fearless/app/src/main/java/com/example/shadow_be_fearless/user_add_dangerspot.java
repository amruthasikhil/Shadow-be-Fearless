package com.example.shadow_be_fearless;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class user_add_dangerspot extends AppCompatActivity implements View.OnClickListener {
    EditText ed1,ed2,ed3;
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_add_dangerspot);
        ed1=(EditText)findViewById(R.id.editText10);
        ed2=(EditText)findViewById(R.id.editText12);
        ed3=(EditText)findViewById(R.id.editText13);
        bt=(Button)findViewById(R.id.button3);
        bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {


        final String location=ed1.getText().toString();
        final String latitude=ed2.getText().toString();
        final String longitude=ed3.getText().toString();


        if (location.equalsIgnoreCase("")) {
            ed1.setError("Missing");
        } else if (latitude.equalsIgnoreCase("")) {
            ed2.setError("Missing");
        } else if (longitude.equalsIgnoreCase("")) {
            ed3.setError("Missing");
        } else {


        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":5000/android_us_add_dangoures_spot";



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


                                Toast.makeText(user_add_dangerspot.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                                Intent ii=new Intent(getApplicationContext(),Home.class);
                                startActivity(ii);





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


                params.put("Location",location);
                params.put("Latitude",latitude);
                params.put("Longitude",longitude);
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
    }}
    @Override
    public void onBackPressed() {
        Intent ii=new Intent(getApplicationContext(),Home.class);
        startActivity(ii);
        super.onBackPressed();
    }


}


