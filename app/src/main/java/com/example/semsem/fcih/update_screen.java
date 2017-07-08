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

public class update_screen extends AppCompatActivity {
    EditText id,name,gpa,hours;
    Button Btn;
    String S_id,S_Name,S_gpa,S_hours;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_screen);
        id=(EditText)findViewById(R.id.id_update);
        name=(EditText)findViewById(R.id.name_update);
        gpa=(EditText)findViewById(R.id.gpa_upddate);
        hours=(EditText)findViewById(R.id.hours_update);
        Btn=(Button)findViewById(R.id.btn_update);
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                S_id = id.getText().toString();
                S_Name = name.getText().toString();
                S_gpa = gpa.getText().toString();
                S_hours = hours.getText().toString();
                RequestQueue queue = Volley.newRequestQueue(update_screen.this);
                StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.43.203/dssproject/FCIH.php?f=update", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //Log.d("result",response);
                            // Toast.makeText(update_screen.this, "on response", Toast.LENGTH_SHORT).show();

                            JSONObject response_jason = new JSONObject(response);
                            String my_response = response_jason.getString("response");
                            if (my_response.equals("done")) {
                                Toast.makeText(update_screen.this, "updated Successfully", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(update_screen.this, "failed", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(update_screen.this, error.toString(), Toast.LENGTH_LONG).show();


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
