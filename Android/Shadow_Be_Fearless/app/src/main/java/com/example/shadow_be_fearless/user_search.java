package com.example.shadow_be_fearless;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

public class user_search extends AppCompatActivity {
    EditText ed;
    ListView li;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search);
        ed=(EditText)findViewById(R.id.editText22);
        li=(ListView)findViewById(R.id.list45);
    }
}
