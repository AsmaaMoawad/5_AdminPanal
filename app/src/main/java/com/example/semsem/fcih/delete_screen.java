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

public class delete_screen extends AppCompatActivity {
    EditText id;
    Button btn;
    String s_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_screen);
        id=(EditText)findViewById(R.id.delete_id);
        btn=(Button)findViewById(R.id.delete_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s_id = id.getText().toString();
                RequestQueue queue = Volley.newRequestQueue(delete_screen.this);
                StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.43.203/dssproject/FCIH.php?f=delete", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //Log.d("result",response);
                            //Toast.makeText(delete_screen.this, "on response", Toast.LENGTH_SHORT).show();

                            JSONObject response_jason = new JSONObject(response);
                            String my_response = response_jason.getString("response");
                            if (my_response.equals("done")) {
                                Toast.makeText(delete_screen.this, "deleted Successfully", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(delete_screen.this, "failed", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(delete_screen.this, error.toString(), Toast.LENGTH_LONG).show();


                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("ID", s_id);

                        return params;
                    }
                };
                queue.add(request);

            }
        });


    }
}
