package com.pf.dllo.food.app;

import android.app.Application;
import android.content.Context;

import com.pf.dllo.food.db.DaoMaster;
import com.pf.dllo.food.db.DaoSession;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by dllo on 16/11/23.
 */
public class Food extends Application{

    public static Context sContext;

    public static DaoMaster sDaoMaster;
    public static DaoSession sDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        // 生成时提供context,可用于数据库,数据库需要context,而每个应用只有一个数据库
        sContext = getApplicationContext();
//        sContext = this;
        ShareSDK.initSDK(this,"19cd9cb33da33");


    }
    public static Context getContext(){
        return sContext;
    }

    // 对外提供DaoMaster对象
    public static DaoMaster getDaoMaster() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getContext(),"Person.db",null);
        // 初始化Daoaster对象
        sDaoMaster = new DaoMaster(helper.getWritableDb());
        return sDaoMaster;
    }

    // 对外提供DaoSession对象
    public static DaoSession getDaoSession() {
        if (sDaoSession == null) {
            if (sDaoMaster == null) {
                sDaoMaster = getDaoMaster();
            }
            // 初始化DaoSession对象
            sDaoSession = sDaoMaster.newSession();
        }
        return sDaoSession;
    }
}
