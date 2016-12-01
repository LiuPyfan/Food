package com.pf.dllo.food.activity.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.pf.dllo.food.R;
import com.pf.dllo.food.activity.BaseActivity;

public class HomeHomeAdAty extends BaseActivity implements View.OnClickListener{


    private WebView mWebView;
    private ImageView ivBack, ivShare, ivCard;

    @Override
    protected int setLayout() {
        return R.layout.activity_home_home_ad_aty;
    }

    @Override
    protected void initView() {
        ivBack = bindView(R.id.iv_home_aty_ad_back);
        mWebView = bindView(R.id.wv_home_aty_ad);
    }

    @Override
    protected void initData() {
        setClick(this,ivBack);

        Intent intent = getIntent();
        if (intent != null) {
            // 网址
            String url = intent.getStringExtra("adUrl");


            mWebView.loadUrl(url);
            mWebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false;
                }

                @Override
                public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
                    return false;
                }
            });
            // 设置webview加载网页的属性 websettings
            WebSettings set = mWebView.getSettings();
//            artFastWv.loadUrl(url);
            // 让WebView能够执行javaScript
            set.setJavaScriptEnabled(true);
            // 让JavaScript可以自动打开windows
            set.setJavaScriptCanOpenWindowsAutomatically(true);

            set.setAllowFileAccess(true);

            // 设置缓存
            set.setAppCacheEnabled(true);
            // 设置缓存模式,一共有四种模式
            set.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            // 设置缓存路径
//            set.setAppCachePath("");
            // 支持缩放(适配到当前屏幕)
            set.setSupportZoom(true);
            // 将图片调整到合适的大小
            set.setUseWideViewPort(true);
            // 支持内容重新布局,一共有四种方式
            // 默认的是NARROW_COLUMNS
            set.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            // 设置可以被显示的屏幕控制
            set.setDisplayZoomControls(true);
            // 设置默认字体大小
            set.setDefaultFontSize(12);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_home_aty_ad_back:
                break;
        }
    }
}
