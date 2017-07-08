package com.example.semsem.fcih;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class read_screen extends AppCompatActivity {
    String[] ids;
    String[] gpas;
    String[] names;
    String[] hours;
    ListView LV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_screen);
        LV=(ListView)findViewById(R.id.lv) ;
        RequestQueue queue = Volley.newRequestQueue(read_screen.this);
        StringRequest request = new StringRequest(Request.Method.POST,"http://192.168.43.203/dssproject/FCIH.php?f=select", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    //Toast.makeText(read_screen.this,response.toString(),Toast.LENGTH_SHORT).show();
                    JSONObject JsnObj=new JSONObject(response);
                    JSONArray arr=JsnObj.getJSONArray("array");
                    // JSONArray arr=new JSONArray("array");

                    ids=new String[arr.length()];
                    names=new String[arr.length()];
                    gpas=new String[arr.length()];
                    hours=new String[arr.length()];

                    for(int i=0;i<arr.length();i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        String Sid=obj.getString("ID");
                        String Sname=obj.getString("Name");
                        String Sgpa=obj.getString("GPA");
                        String Shours=obj.getString("hours");
                        ids[i]=Sid;
                        names[i]=Sname;
                        gpas[i]=Sgpa;
                        hours[i]=Shours;
                    }
                    // Toast.makeText(read_screen.this,ids[0],Toast.LENGTH_SHORT).show();

                    MyAdapter adapter=new MyAdapter(ids,names,gpas,hours,read_screen.this);
                    LV.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(read_screen.this, error.toString(), Toast.LENGTH_LONG).show();


            }
        });
        queue.add(request);


    }
}
