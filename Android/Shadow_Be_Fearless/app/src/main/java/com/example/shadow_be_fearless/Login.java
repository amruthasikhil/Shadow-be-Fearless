package com.example.shadow_be_fearless;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.PrecomputedText;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText ed1, ed2;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ed1 = (EditText) findViewById(R.id.username);
        ed2 = (EditText) findViewById(R.id.password);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        final String uname = ed1.getText().toString();
        final String pswd = ed2.getText().toString();
        if (uname.equalsIgnoreCase("")) {
            ed1.setError("Missing");
        } else if (pswd.equalsIgnoreCase("")) {
            ed2.setError("Missing");
        } else {


            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String hu = sh.getString("ip", "");
            String url = "http://" + hu + ":5000/android_login_post";


            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // response
                            try {
                                JSONObject jsonObj = new JSONObject(response);
                                if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                    String types = jsonObj.getString("type");
                                    if (types.equalsIgnoreCase("user")) {


                                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                        SharedPreferences.Editor ed = sh.edit();
                                        ed.putString("lid", jsonObj.getString("lid"));
                                        ed.commit();

                                        Intent jk = new Intent(getApplicationContext(), LocationService.class);
                                        startService(jk);


                                        Intent jkk = new Intent(getApplicationContext(), Shake.class);
                                        startService(jkk);

                                        Intent ii = new Intent(getApplicationContext(), Home.class);
                                        startActivity(ii);


                                        Toast.makeText(Login.this, "Successfully Logined", Toast.LENGTH_SHORT).show();

                                    }


                                    if (types.equalsIgnoreCase("police")) {


                                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                        SharedPreferences.Editor ed = sh.edit();
                                        ed.putString("lid", jsonObj.getString("lid"));
                                        ed.commit();
                                        Intent ii = new Intent(getApplicationContext(), Policehomepage.class);
                                        startActivity(ii);

                                        Toast.makeText(Login.this, "Successfully Logined", Toast.LENGTH_SHORT).show();

                                    }

                                    // l1.setAdapter(new Custom(getApplicationContext(),gamecode,name,type,discription,image,status));
                                }


                                // }
                                else {
                                    Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_LONG).show();
                                }

                            } catch (Exception e) {
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


                    params.put("username", uname);
                    params.put("password", pswd);


                    return params;
                }
            };

            int MY_SOCKET_TIMEOUT_MS = 100000;

            postRequest.setRetryPolicy(new DefaultRetryPolicy(
                    MY_SOCKET_TIMEOUT_MS,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(postRequest);

        }
    }
}