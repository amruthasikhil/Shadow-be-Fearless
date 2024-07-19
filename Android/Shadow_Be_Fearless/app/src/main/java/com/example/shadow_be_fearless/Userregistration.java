package com.example.shadow_be_fearless;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Userregistration extends AppCompatActivity implements View.OnClickListener {
    EditText name,dob,place,post,pin,cno,dist,home,email,hn,city;
    Button  button1;
    ImageView img;
    TextView kk;
    RadioButton m,f;

    String path, atype, fname, attach, attatch1;
    byte[] byteArray = null;
    String url3="";
    String gg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userregistration);
        name=(EditText)findViewById(R.id.name);
        place=(EditText)findViewById(R.id.dob);
        dist=(EditText)findViewById(R.id.editText2);
        home=(EditText)findViewById(R.id.editText);
        post=(EditText)findViewById(R.id.place);
        pin=(EditText)findViewById(R.id.post);
        email=(EditText)findViewById(R.id.pinaddress);
        cno=(EditText)findViewById(R.id.phno);
        hn=(EditText)findViewById(R.id.editText30);
        city=(EditText)findViewById(R.id.editText30);


        img=(ImageView) findViewById(R.id.pics);

        m=(RadioButton) findViewById(R.id.radioButton);
        f=(RadioButton) findViewById(R.id.radioButton2);

        button1=(Button) findViewById(R.id.button78);
        kk=(TextView) findViewById(R.id.textView58);


        final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MM/dd/yy"; //In which you need put here


                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                home.setText(sdf.format(myCalendar.getTime()));
            }

        };

        home.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Userregistration.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        img.setOnClickListener(this);
        button1.setOnClickListener(this);
        kk.setOnClickListener(this);


//        Checking set permission


    }



    @Override
    public void onClick(View view) {
        if (view == img) {
            showfilechooser(1);
        }
        if(view==kk){
            Intent i=new Intent(getApplicationContext(),Login.class);
            startActivity(i);
        }


        if (view == button1) {


            String name22= name.getText().toString();
            String place22=place.getText().toString();
            String post22= post.getText().toString();
            String dob22= home.getText().toString() ;
            String pin22= pin.getText().toString() ;
            String email22=email .getText().toString() ;
            String phone22= cno.getText().toString() ;
            String hn22=hn.getText().toString();
            String city22=city.getText().toString();


            if (name22.equalsIgnoreCase("")) {
                name.setError("Missing");
            } else if (place22.equalsIgnoreCase("")) {
                place.setError("Missing");
            } else if (post22.equalsIgnoreCase("")) {
                post.setError("Missing");
            }
         else if (dob22.equalsIgnoreCase("")) {
            home.setError("Missing");
        }

         else if (pin22.equalsIgnoreCase("")) {
            pin.setError("Missing");
        }

            else if (email22.equalsIgnoreCase("")) {
                email.setError("Missing");
            }


            else if (phone22.equalsIgnoreCase("")) {
                cno.setError("Missing");
            }


            else if (hn22.equalsIgnoreCase("")) {
                hn.setError("Missing");
            }


            else if (city22.equalsIgnoreCase("")) {
                city.setError("Missing");
            }







            else {


                if (m.isChecked() == true) {

                    gg = "male";
                }

                if (f.isChecked() == true) {

                    gg = "female";
                }


                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String hu = sh.getString("ip", "");
                String url = "http://" + hu + ":5000/and_user_registration";


                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                                // response
                                try {
                                    JSONObject jsonObj = new JSONObject(response);
                                    if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                                        Toast.makeText(Userregistration.this, "Successfully Registred", Toast.LENGTH_SHORT).show();


                                        Intent ii = new Intent(getApplicationContext(), Public_home.class);
                                        startActivity(ii);


                                    } else {
                                        Toast.makeText(Userregistration.this, "Not Registered", Toast.LENGTH_SHORT).show();
                                    }


                                    // }


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


                        params.put("name", name.getText().toString());
                        params.put("gen", gg);
                        params.put("place", place.getText().toString());
                        params.put("post", post.getText().toString());
                        params.put("dob", home.getText().toString());
                        params.put("pin", pin.getText().toString());
                        params.put("email", email.getText().toString());
                        params.put("phone", cno.getText().toString());
                        params.put("hn", hn.getText().toString());
                        params.put("city", city.getText().toString());

                        params.put("img", attach);


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






        private void showfilechooser(int string) {


        // TODO Auto-generated method stub
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //getting all types of files

        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), string);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
//            Toast.makeText(getApplicationContext(), "Please install a File Manager.", Toast.LENGTH_SHORT).show();

        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                ////
                Uri uri = data.getData();

                try {
                    path = FileUtils.getPath(this, uri);

                    File fil = new File(path);
                    float fln = (float) (fil.length() / 1024);
                    atype = path.substring(path.lastIndexOf(".") + 1);


                    fname = path.substring(path.lastIndexOf("/") + 1);
//                    ed15.setText(fname);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }

                try {

                    File imgFile = new File(path);

                    if (imgFile.exists()) {

                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                        img.setImageBitmap(myBitmap);

                    }


                    File file = new File(path);
                    byte[] b = new byte[8192];
//                    Log.d("bytes read", "bytes read");

                    InputStream inputStream = new FileInputStream(file);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();

                    int bytesRead = 0;

                    while ((bytesRead = inputStream.read(b)) != -1) {
                        bos.write(b, 0, bytesRead);
                    }
                    byteArray = bos.toByteArray();

                    String str = Base64.encodeToString(byteArray, Base64.NO_WRAP);
                    attach = str;


                } catch (Exception e) {
//                    Toast.makeText(this, "String :" + e.getMessage().toString(), Toast.LENGTH_LONG).show();
                }

                ///

            }
        }
}


}
