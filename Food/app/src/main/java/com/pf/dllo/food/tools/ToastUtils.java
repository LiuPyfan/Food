package com.pf.dllo.food.tools;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by dllo on 16/12/2.
 */
public class ToastUtils {

    public static void ToastMessage(Context context, String msg) {
        Toast.makeText(context,msg, Toast.LENGTH_SHORT).show();
    }
}
