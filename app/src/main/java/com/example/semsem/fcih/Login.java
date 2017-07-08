package com.example.semsem.fcih;

import android.content.*;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    Button login;
    EditText username, password;
    String JSON_URL = "http://192.168.43.203/dssproject/FCIH.php?f=login";
    String usrn, pas;
    AlertDialog.Builder builder;

    android.content.SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        preferences = getSharedPreferences("MyPREFERENCES", this.MODE_PRIVATE);

        password = (EditText) findViewById(R.id.password);
        username = (EditText) findViewById(R.id.email);
        login = (Button) findViewById(R.id.login);
        builder = new AlertDialog.Builder(Login.this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usrn = username.getText().toString();
                pas = password.getText().toString();
                if (usrn.equals("") || pas.equals("")) {
                    builder.setTitle("Something is Wrong ....");
                    builder.setMessage("Fill The Inputs ...");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();


                }
                else {

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {

                                JSONObject jsonObect= new JSONObject(response);
                                String message = jsonObect.getString("response");
                                if (message.equals("done")) {
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putString("message","done");
                                    editor.commit();
                                    Intent intent = new Intent(Login.this, Home.class);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(Login.this, "Erorr Email or Password", Toast.LENGTH_SHORT).show();
                                }



                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override

                        public void onErrorResponse(VolleyError error) {


                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String, String> Prams = new HashMap<String, String>();
                            Prams.put("email", usrn);
                            Prams.put("password", pas);

                            return Prams;

                        }

                    };

                    MySingletone.getIstance(getApplicationContext()).addtorequest(stringRequest);

                }

            }
        });
    }

}
