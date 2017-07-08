package com.example.semsem.fcih;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class create_screen extends AppCompatActivity {
    EditText id,name,gpa,hours;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_screen);
        id=(EditText)findViewById(R.id.id);
        name=(EditText)findViewById(R.id.name);
        gpa=(EditText)findViewById(R.id.gpa);
        hours=(EditText)findViewById(R.id.hour);
        save=(Button)findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String S_id = id.getText().toString();
                final String S_Name = name.getText().toString();
                final String S_gpa = gpa.getText().toString();
                final String S_hours = hours.getText().toString();


                RequestQueue queue = Volley.newRequestQueue(create_screen.this);
                StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.43.203/dssproject/FCIH.php?f=create", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //Log.d("result",response);
                            // Toast.makeText(create_screen.this,response.toString(),Toast.LENGTH_SHORT).show();

                            JSONObject response_jason = new JSONObject(response);
                            String my_response = response_jason.getString("response");
                            //Toast.makeText(create_screen.this,"on response",Toast.LENGTH_SHORT).show();
                            if (my_response.equals("done")) {
                                Toast.makeText(create_screen.this, "Saved Successfully", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(create_screen.this, "failed", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ;


                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("ID", S_id);
                        params.put("name", S_Name);
                        params.put("GPA", S_gpa);
                        params.put("hours", S_hours);

                        return params;
                    }
                };
                queue.add(request);


            }
        });
    }
}
