package com.pf.dllo.food.tools.net;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2016/11/28.
 * 这个请求可以直接将数据进行解析,所以可以直接使用请求后的数据
 */

public class GsonRequest<T> extends Request<T> {
    private Gson mGson;
    private Response.Listener<T> mListener;
    private Class<T> mClass;
    //这是用于post请求的map
    private HashMap<String,String> map;


    public GsonRequest(int method, String url, Response.ErrorListener listener) {
        super(method, url, listener);
    }
    //用于get请求
    public GsonRequest(int method, String url, Response.ErrorListener listener, Response.Listener<T> listener1, Class<T> aClass) {
        super(method, url, listener);
        mListener = listener1;
        mClass = aClass;
        mGson = new Gson();
    }

    //这是用于post接口数据的请求
    public GsonRequest(int method, String url, Response.ErrorListener listener, Response.Listener<T> listener1, Class<T> aClass, HashMap<String, String> map) {
        super(method, url, listener);
        mGson = new Gson();
        mListener = listener1;
        mClass = aClass;
        this.map = map;
    }

    //请求网络数据
    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            //这是我们请求下来的数据
            String data = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            //我们直接将请求好的数据写在请求成功的方法之中
            //第一参数是解析好的的数据
            //第二参数是缓存类
            return Response.success(mGson.fromJson(data, mClass),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            //这是网络请求失败的返回结果
            return Response.error(new ParseError(e));
        }

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

    //分发事件
    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }
    //数据缓存
    @Override
    public void deliverError(VolleyError error) {
        //判断 如果没有网络连接 这时我们要使用缓存数据
        if(error instanceof NoConnectionError){
//            Log.d("GsonRequest", "我是缓存的数据");
            //获取缓存数据
            Cache.Entry entry = this.getCacheEntry();
            //创建请求 请求的数据就是我们自己缓存的数据
            Response<T> response = parseNetworkResponse
                    (new NetworkResponse(entry.data,entry.responseHeaders));
            deliverResponse(response.result);
        }
        super.deliverError(error);
    }
}
