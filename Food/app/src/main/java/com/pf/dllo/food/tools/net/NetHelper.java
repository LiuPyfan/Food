package com.pf.dllo.food.tools.net;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pf.dllo.food.app.Food;

import java.util.HashMap;

/**
 * Created on 2016/11/28.
 */
public class NetHelper {
    private RequestQueue mRequestQueue;

    private static NetHelper ourInstance = new NetHelper();

    //在单例模式中我们对外提供了一个方法来获取对象
    public static NetHelper getInstance() {
        return ourInstance;
    }
    //为什么要把构造方法私有化?
    //不想让别人创建出对象,所以私有化 我们只使用对外提供的方法获取本类的对象
    private NetHelper() {
        getRequestQueue();
    }

    //双重校验锁
    private RequestQueue getRequestQueue(){
        //第一个判断是用于提高代码效率的
        //如果多个地方使用了这个单例,那么第一层判断就可以直接创建队列,
        // 不为空的情况就直接返回队列
        if (mRequestQueue == null){
            //加锁 保证线程不会拥挤阻塞
            synchronized (NetHelper.class){
                //如果队列为空 创建我们自己的队列
                if (mRequestQueue == null){
                    mRequestQueue = Volley.newRequestQueue(Food.getContext());
                }
            }
        }
        return mRequestQueue;
    }
    /************************私有化的方法************************************/
    //这是最最基本的请求方式
    private <T> void baseRequest(Request<T> request){
        mRequestQueue.add(request);
    }
    private <T> void baseGsonRequest(String url , Class<T> mClass, final NetListener<T> listener){
        GsonRequest<T> gsonRequest = new GsonRequest<T>(Request.Method.GET, url, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.errorListener(error);
            }
        }, new Response.Listener<T>() {
            @Override
            public void onResponse(T response) {
                listener.successListener(response);
            }
        },mClass);
        mRequestQueue.add(gsonRequest);
    }
    private <T> void baseGsonPostRequest(String url, Class<T> mClass,
                                         final NetListener<T> listener,
                                         HashMap<String,String> map){
        GsonRequest<T> gsonRequest = new GsonRequest<T>(Request.Method.POST, url, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.errorListener(error);
            }
        }, new Response.Listener<T>() {
            @Override
            public void onResponse(T response) {
                listener.successListener(response);
            }
        },mClass,map);
        mRequestQueue.add(gsonRequest);
    }
    private void baseStringRequest(String url, final NetListener<String> listener){
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.successListener(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.errorListener(error);
            }
        });
          mRequestQueue.add(stringRequest);

    }
    /******************对外提供的方法************************************/

    public static  void MySRequest(String url,NetListener<String> listener){
        getInstance().baseStringRequest(url,listener);
    }
    public static <T> void MyRequest(String url,Class<T> mClass,NetListener<T> listener){
        getInstance().baseGsonRequest(url,mClass,listener);
    }
    public static <T> void MyRequest(String url,Class<T> mClass,NetListener<T> listener,HashMap<String
            ,String> map){
        getInstance().baseGsonPostRequest(url,mClass,listener,map);

    }
}
