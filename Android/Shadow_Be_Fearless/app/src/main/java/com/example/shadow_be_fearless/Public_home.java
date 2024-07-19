package com.example.shadow_be_fearless;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Public_home extends AppCompatActivity implements View.OnClickListener {
    private TextView mTextMessage;
    Button bb1,bb2;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent ii=new Intent(getApplicationContext(), Login.class);
                    startActivity(ii);
                    return true;
                case R.id.navigation_dashboard:


                    AlertDialog.Builder builder = new AlertDialog.Builder(Public_home.this);
                    builder.setTitle("options");
                    builder.setItems(new CharSequence[]
                                    { "Dangerous Spot","Safe spot"},
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // The 'which' argument contains the index position
                                    // of the selected item
                                    switch (which) {
                                        case 0:

                                            Intent i=new Intent(getApplicationContext(),dang_manag.class);
                                            startActivity(i);

                                            break;

                                        case 1:

                                            Intent i1l=new Intent(getApplicationContext(), user_safe.class);
                                            startActivity(i1l);

                                            break;





                                    }
                                }
                            });
                    builder.create().show();







                    return true;

                case R.id.navigation_notifications:
                    Intent kk=new Intent(getApplicationContext(), Pubviewpolice.class);
                    startActivity(kk);


                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_home);
        bb1 = (Button) findViewById(R.id.button24);
        bb2 = (Button) findViewById(R.id.button25);
        bb1.setOnClickListener(this);
        bb2.setOnClickListener(this);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onClick(View v) {

        if(v==bb1) {
            Intent ii = new Intent(getApplicationContext(), Login.class);
            startActivity(ii);
        }
        if(v==bb2) {
            Intent ii = new Intent(getApplicationContext(), Userregistration.class);
            startActivity(ii);
        }

    }
}
