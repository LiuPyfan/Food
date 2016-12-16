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

import com.pf.dllo.food.R;
import com.pf.dllo.food.activity.BaseActivity;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class HomeKnowAty extends BaseActivity implements View.OnClickListener {
    private WebView mWebView;
    private ImageView ivBack,ivCollect,ivShare;

    @Override
    protected int setLayout() {
        return R.layout.activity_home_know_aty;
    }

    @Override
    protected void initView() {
        mWebView = bindView(R.id.wv_know_aty);
        ivBack = bindView(R.id.iv_know_aty_back);
        ivCollect =bindView(R.id.iv_know_aty_collect);
        ivShare = bindView(R.id.iv_know_aty_share);
        setClick(this, ivBack, ivShare);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_know_aty_back:
                finish();
                // 返回
                break;
            case R.id.iv_know_aty_share:
                // 分享
                showShare();
                break;
        }
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://www.baidu.com");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是sdfjdsklf");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://www.baidu.com");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是kalsdfdsla");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://www.baidu.com");

        // 启动分享GUI
        oks.show(this);
    }

    @Override
    protected void initData() {
        webViewParse();
    }
    private void webViewParse() {
        Intent intent = getIntent();
        if (intent != null) {
            // 网址
            String  url = intent.getStringExtra("url");


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


            // 2.加载html的字符串
            // jquery
            // 设置编码格式
            // 使用WebView加载数据
            // 参数1:字符串
            // 参数2:html格式的文本,编码格式是UTF-8

//             artFastWv.loadData(url, "text/html; charset=UTF-8", null);

            // 拓展内容,可显示图片
            // webView.loadDataWithBaseURL();

        }
    }

}

