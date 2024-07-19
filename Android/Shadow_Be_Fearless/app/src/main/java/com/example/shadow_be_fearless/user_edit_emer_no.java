package com.example.shadow_be_fearless;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class user_edit_emer_no extends AppCompatActivity {
    EditText ed;
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit_emer_no);
        ed=(EditText)findViewById(R.id.editText23);
        bt=(Button)findViewById(R.id.button11);
    }
}
