package com.example.semsem.fcih;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Asmaa on 17-May-17.
 */

public class MySingletone {
    private static MySingletone Instance;
    private static RequestQueue requestQueue;
    private static Context mctx;


    private MySingletone(Context context){
        this.mctx=context;
        requestQueue=getRequestQueue();
    }




    public RequestQueue getRequestQueue(){
        if(requestQueue==null){


            requestQueue= Volley.newRequestQueue(mctx.getApplicationContext());


        }
        return requestQueue;
    }





    public static synchronized MySingletone getIstance(Context context){
        Instance =new MySingletone(context);
        return Instance;
    }


    public<T>  void addtorequest(Request<T> request){
        requestQueue.add(request);

    }




}
