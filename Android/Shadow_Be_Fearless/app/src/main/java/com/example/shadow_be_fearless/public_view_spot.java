package com.example.shadow_be_fearless;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class public_view_spot extends AppCompatActivity {
    ListView li;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_view_spot);
        li=(ListView)findViewById(R.id.list77);
    }
}
