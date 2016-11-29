package com.pf.dllo.food.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 16/11/23.
 */
public class Food extends Application{

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        // 生成时提供context,可用于数据库,数据库需要context,而每个应用只有一个数据库
        sContext = getApplicationContext();
//        sContext = this;


    }
    public static Context getContext(){
        return sContext;
    }
}
