package com.pf.dllo.food.tools;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by dllo on 16/11/25.
 */
public class ScreenSetUtils {


    public static int getScreenHeight(Context context) {
        // 获取窗口管理者
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        // 创建显示尺寸类
        DisplayMetrics metrics = new DisplayMetrics();
        // 将窗口(屏幕)的尺寸设置给 显示尺寸类
        manager.getDefaultDisplay().getMetrics(metrics);
        // 返回屏幕高度
        return metrics.heightPixels;
    }

    public enum ScreenState{
        WIDTH,HEIGHT
    }

    public static int getScreenSize(Context context,ScreenState state){
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        if (state.equals(ScreenState.WIDTH)) {
            return metrics.widthPixels;
        }else if (state.equals(ScreenState.HEIGHT)){
            return metrics.heightPixels;
        }
        return metrics.heightPixels;

    }



}
