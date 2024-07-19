package com.example.shadow_be_fearless;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class user_edit_dang extends AppCompatActivity {
    EditText ed1,ed2,ed3;
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit_dang);
        ed1=(EditText)findViewById(R.id.editText10);
        ed2=(EditText)findViewById(R.id.editText12);
        ed3=(EditText)findViewById(R.id.editText13);
        bt=(Button)findViewById(R.id.button3);
    }
}
