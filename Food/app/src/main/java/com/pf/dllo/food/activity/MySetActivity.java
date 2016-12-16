package com.pf.dllo.food.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pf.dllo.food.R;
import com.pf.dllo.food.tools.DataCleanManger;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

public class MySetActivity extends BaseActivity {

    private RelativeLayout mCacheRl;
    private TextView mTextView, showTv;
    private Button mExitBtn;
    private ImageView mImageView;

    @Override
    protected int setLayout() {
        return R.layout.activity_my_set;
    }

    @Override
    protected void initView() {
        mCacheRl = bindView(R.id.rl_cache);
        mTextView = bindView(R.id.tv_cache_count);
        mExitBtn = bindView(R.id.exit_login);
        showTv = bindView(R.id.tv_cache_show);
        mImageView = bindView(R.id.my_set_back);

    }


    @Override
    protected void initData() {
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        try {
            mTextView.setText(DataCleanManger.getTotalCacheSize(MySetActivity.this));
        } catch (Exception e) {
            e.printStackTrace();
        }
        showTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    DataCleanManger.getTotalCacheSize(MySetActivity.this);
                    DataCleanManger.clearAllCache(MySetActivity.this);
                    Log.d("MySetActivity", DataCleanManger.getTotalCacheSize(MySetActivity.this));
                    mTextView.setText(DataCleanManger.getTotalCacheSize(MySetActivity.this));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        mExitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn();
            }

            private void logIn() {
                Platform platform = ShareSDK.getPlatform(QQ.NAME);
                if (platform.isAuthValid()) {
                    platform.removeAccount(true);
//                    userNameTv.setText("用户名");
//                    userPicIv.setImageResource(R.mipmap.ic_launcher);
                } else {
//                    Toast.makeText(this, "退出登录", Toast.LENGTH_SHORT).show();
                }
//                platform.setPlatformActionListener(platformActionListener);


                // authorize与showUser单独调用一个即可
//                platform.authorize();//单独授权，OnComplete返回的hashmap是空的
//                platform.showUser(null);//授权并获取用户信息
                // isValid和removeAccount不开启线程，会直接返回。
                // qq.removeAccount(true);
                setResult(-1);
            }
        });
    }
}
