package com.example.shadow_be_fearless;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_changepassword) {

            Intent ii=new Intent(getApplicationContext(),User_chng_pwd.class);
            startActivity(ii);

        } else if (id == R.id.nav_user_view_dang) {
            Intent ii = new Intent(getApplicationContext(), user_view_dang.class);
            startActivity(ii);
        }

//        } else if (id == R.id.nav_user_edit_dang) {
//            Intent ii=new Intent(getApplicationContext(),user_edit_dang.class);
//            startActivity(ii);
//
//
//

         else if (id == R.id.nav_user_nearby) {

            Intent ii=new Intent(getApplicationContext(),user_nearby.class);
            startActivity(ii);


        }

         else if (id == R.id.nav_user_profile) {
            Intent ii=new Intent(getApplicationContext(),user_profile.class);
            startActivity(ii);


        }


        else if (id == R.id.tt) {
            Intent ii=new Intent(getApplicationContext(),public_view_police.class);
            startActivity(ii);


        }




        else if (id == R.id.ll) {
            Intent ii=new Intent(getApplicationContext(),Login.class);
            startActivity(ii);


        }




          else if (id == R.id.nav_user_send_compl) {
            Intent ii=new Intent(getApplicationContext(),user_send_compl.class);
            startActivity(ii);

        } else if (id == R.id.nav_user_view_reply) {
            Intent ii = new Intent(getApplicationContext(), user_view_reply.class);
            startActivity(ii);
        }else if (id == R.id.nav_user_add_dangerspot) {
                Intent ii=new Intent(getApplicationContext(),user_add_dangerspot.class);
                startActivity(ii);



        } else if (id == R.id.nav_user_safe) {
            Intent ii=new Intent(getApplicationContext(),user_safe.class);
            startActivity(ii);

        } else if (id == R.id.nav_user_emer_no) {
            Intent ii=new Intent(getApplicationContext(),user_emer_no.class);
            startActivity(ii);
        } else if (id == R.id.nav_user_view_emergency_no) {
            Intent ii=new Intent(getApplicationContext(),user_view_emergency_no.class);
            startActivity(ii);

        }

        else if (id == R.id.nn) {
            Intent ii=new Intent(getApplicationContext(),user_view_notif.class);
            startActivity(ii);




        }



        else if (id == R.id.nav_User_Share_idea) {
            Intent ii = new Intent(getApplicationContext(), User_Share_idea.class);
            startActivity(ii);
        }

        else if (id == R.id.nav_User_view_Share_idea) {
                Intent ii=new Intent(getApplicationContext(),user_view_share_idea.class);
                startActivity(ii);
        }

        else if (id == R.id.nav_user_chat) {
            Intent ii=new Intent(getApplicationContext(),user_chat.class);
            startActivity(ii);




        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
