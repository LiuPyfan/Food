package com.pf.dllo.food.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dllo on 16/11/22.
 */
public abstract class BaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 订制流程
         */
        /**
         * 设置布局
         */
        setContentView(setLayout());
        // 初始化
        initView();
        initData();
    }

    protected abstract int setLayout();
    protected abstract void initView();
    protected abstract void initData();

    /**
     * 简化findViewById
     */
    public <T extends View> T bindView(int id){
        return (T)findViewById(id);
    }


    protected void setClick(View.OnClickListener onClickListener,View ... views){
        for (View view :views) {
            view.setOnClickListener(onClickListener);
        }
    }

    /**
     * 跳转不传值
     */
//    protected void goTo(Context from,Class<? extends AbsBaseActivity> to){
//        startActivity(new Intent(from,to));
//        overridePendingTransition();
//    }
    /**
     * 跳转传值
     * Bundle: 轻量级的存储类
     * 存储一些key-value形式的数据
     */
//    protected void goTo(Context from,Class<? extends AbsBaseActivity> to, Bundle extras){
//        Intent intent = new Intent();
//        intent.putExtras(extras);
//        startActivity(intent);
//        overridePendingTransition();
//    }
}
