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
import android.widget.ListView;
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

public class user_emerg_help extends AppCompatActivity implements View.OnClickListener {
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_emerg_help);

        bt=(Button) findViewById(R.id.button14);
        bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {


        Intent ii=new Intent(getApplicationContext(),Shake.class);
        startService(ii);


           }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    @Override
    public void onBackPressed() {
        Intent ii=new Intent(getApplicationContext(),Home.class);
        startActivity(ii);
        super.onBackPressed();
    }


}


