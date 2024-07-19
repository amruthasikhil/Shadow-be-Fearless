package com.example.shadow_be_fearless;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText ed1;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1=(EditText)findViewById(R.id.editText2);
        btn=(Button)findViewById(R.id.button);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String  nn=ed1.getText().toString();

        if(nn.equalsIgnoreCase("")){
            ed1.setError("Missing");
        }
        else{
        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor ed=sh.edit();
        ed.putString("ip",ed1.getText().toString());
        ed.commit();
        Intent ii=new Intent(getApplicationContext(),Public_home.class);
        startActivity(ii);
    }}
}
