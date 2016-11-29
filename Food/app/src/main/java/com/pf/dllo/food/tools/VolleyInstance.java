package com.pf.dllo.food.tools;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pf.dllo.food.app.Food;
import com.pf.dllo.food.fragment.home.HomeKnowFragment;

/**
 * Created by dllo on 16/11/24.
 */
public class VolleyInstance {
    private static VolleyInstance sInstance;
    private RequestQueue mQueue;
    private VolleyInstance(){
        mQueue = Volley.newRequestQueue(Food.getContext());
    }
    public static VolleyInstance getInstance(){
        if (sInstance == null) {
            synchronized (VolleyInstance.class){
                if (sInstance == null) {
                    sInstance = new VolleyInstance();
                }
            }
        }
        return sInstance;
    }
    public void startRequest(String url,final VolleyResult result){
        StringRequest sr = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                result.getSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                result.getFailure();
            }
        });
        mQueue.add(sr);
    }



}
