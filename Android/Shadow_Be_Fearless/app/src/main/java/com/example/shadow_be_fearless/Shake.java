package com.example.shadow_be_fearless;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.widget.Toast;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static  com.example.shadow_be_fearless.LocationService.lati;
import static  com.example.shadow_be_fearless.LocationService.place;
import static  com.example.shadow_be_fearless.LocationService.logi;



public class Shake extends Service implements SensorListener {



    String url="";
    SharedPreferences sh;
    private SensorManager sensorMgr;
    private static final int SHAKE_THRESHOLD = 2000;
    private long lastUpdate = -1;
    private float x, y, z;
    private float last_x, last_y, last_z;
String[]contact;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
        boolean accelSupported = sensorMgr.registerListener(this,SensorManager.SENSOR_ACCELEROMETER,SensorManager.SENSOR_DELAY_GAME);

        if (!accelSupported) {
            // on accelerometer on this device
            sensorMgr.unregisterListener((SensorListener) this,
                    SensorManager.SENSOR_ACCELEROMETER);
        }
//        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        url = sh.getString("url", "") + "add_distruption/";




    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


    public void onAccuracyChanged(int arg0, int arg1) {
        // TODO Auto-generated method stub
    }

    public void onSensorChanged(int sensor, float[] values) {
        if (sensor == SensorManager.SENSOR_ACCELEROMETER) {
            long curTime = System.currentTimeMillis();
            // only allow one update every 100ms.
            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                x = values[SensorManager.DATA_X];
                y = values[SensorManager.DATA_Y];
                z = values[SensorManager.DATA_Z];

                if(Round(x,4)>10.0000){
                 //   Log.d("sensor", "X Right axis: " + x);
                    //     Toast.makeText(this, "Right shake detected", Toast.LENGTH_SHORT).show();
                }
                else if(Round(y,4)>10.0000){
                 //   Log.d("sensor", "X Right axis: " + x);
                    //    Toast.makeText(this, "Top shake detected", Toast.LENGTH_SHORT).show();
                }
                else if(Round(y,4)>-10.0000){
                  //  Log.d("sensor", "X Right axis: " + x);
                    //     Toast.makeText(this, "Bottom shake detected", Toast.LENGTH_SHORT).show();
                }
                else if(Round(x,4)<-10.0000){
              //      Log.d("sensor", "X Left axis: " + x);
                    //    Toast.makeText(this, "Left shake detected", Toast.LENGTH_SHORT).show();
                }

                float speed = Math.abs(x+y+z - last_x - last_y - last_z) / diffTime * 10000;

                //Toast.makeText(this, speed+"", Toast.LENGTH_SHORT).show();
                // Log.d("sensor", "diff: " + diffTime + " - speed: " + speed);
                if (speed > SHAKE_THRESHOLD)
                {
                    Toast.makeText(this, "obstacle detected", Toast.LENGTH_SHORT).show();

                    try {
//                        JSONParser js = new JSONParser();
//                        List<NameValuePair> param = new ArrayList<NameValuePair>();
//                        param.add(new BasicNameValuePair("latitude", LocactionService.lati));
//                        param.add(new BasicNameValuePair("longitude", LocactionService.logi));
//
//                        JSONObject json = js.makeHttpRequest(url, "POST", param);
//
//                        String response = json.getString("success");

                        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                        String hu = sh.getString("ip", "");
                        String url = "http://" + hu + ":5000/and_emergency_request";



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

                                                JSONArray js= jsonObj.getJSONArray("data");

                                                contact=new String[js.length()];

//
                                                for(int i=0;i<js.length();i++)
                                                {
                                                    JSONObject u=js.getJSONObject(i);
                                                    contact[i]=u.getString("Phone_Number");


                                                    Intent intent = new Intent(Intent.ACTION_CALL);
                                                    intent.setData(Uri.parse("tel:"+contact));
                                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                                                    startActivity(intent);



                                                    SmsManager smsManager = SmsManager.getDefault();
                                                    smsManager.sendTextMessage(u.getString("Phone_Number"), null, "Need Help Urgently", null, null);


                                                }
                                            }

                                            // }
                                            else {
                                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                                            }

                                        }    catch (Exception e) {
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

                                params.put("lat",LocationService.lati);
                                params.put("lon",LocationService.logi);
                                params.put("place",LocationService.place);
                                params.put("lid",sh.getString("lid",""));

                                return params;
                            }
                        };

                        int MY_SOCKET_TIMEOUT_MS=100000;

                        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                                MY_SOCKET_TIMEOUT_MS,
                                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                        requestQueue.add(postRequest);


                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "" + e.getMessage(), Toast.LENGTH_LONG).show();
                    }



                }

                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }

    public static float Round(float Rval, int Rpl) {
        float p = (float)Math.pow(10,Rpl);
        Rval = Rval * p;
        float tmp = Math.round(Rval);
        return (float)tmp/p;
    }

    @Override
    public int onStartCommand(Intent intent,int flags,int startId) {

        Toast.makeText(this,"start Service.",Toast.LENGTH_SHORT).show();



        return START_REDELIVER_INTENT;

    }
}
