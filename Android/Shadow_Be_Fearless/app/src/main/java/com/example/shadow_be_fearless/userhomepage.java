package com.example.shadow_be_fearless;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class userhomepage extends AppCompatActivity implements View.OnClickListener {
   Button bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userhomepage);
        bt1=(Button)findViewById(R.id.button7);
        bt2=(Button)findViewById(R.id.button8);
        bt3=(Button)findViewById(R.id.button9);
        bt4=(Button)findViewById(R.id.button17);
        bt5=(Button)findViewById(R.id.button18);
        bt6=(Button)findViewById(R.id.button19);
        bt7=(Button)findViewById(R.id.button20);
        bt8=(Button)findViewById(R.id.button21);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
        bt6.setOnClickListener(this);
        bt7.setOnClickListener(this);
        bt8.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==bt1){
            Intent ii=new Intent(getApplicationContext(),Police_view_profile.class);
            startActivity(ii);
        }
        if(view==bt2){
            Intent ii=new Intent(getApplicationContext(),change_password.class);
            startActivity(ii);
        }
        if(view==bt3) {
            Intent ii = new Intent(getApplicationContext(), view_emerg.class);
            startActivity(ii);
        }
        if(view==bt4){
                Intent ii=new Intent(getApplicationContext(),view_complaint.class);
                startActivity(ii);
        }
        /*if(view==bt5){
            Intent ii=new Intent(getApplicationContext(),send_reply.class);
            startActivity(ii);
        }*/
        if(view==bt6){
            Intent ii=new Intent(getApplicationContext(),add_dangerous_manag.class);
            startActivity(ii);
        }
        if(view==bt7){
            Intent ii=new Intent(getApplicationContext(),dang_manag.class);
            startActivity(ii);
        }
        if(view==bt8){
            Intent ii=new Intent(getApplicationContext(),view_added_dang_user.class);
            startActivity(ii);
        }
    }
}
