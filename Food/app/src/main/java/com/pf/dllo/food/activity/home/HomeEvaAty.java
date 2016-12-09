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
import com.pf.dllo.food.db.CollectBean;
import com.pf.dllo.food.db.DBTool;

public class HomeEvaAty extends BaseActivity implements View.OnClickListener {


    private WebView mWebView;
    private ImageView ivCollect;

    @Override
    protected int setLayout() {
        return R.layout.activity_home_eva_aty;
    }

    @Override
    protected void initView() {

        mWebView = bindView(R.id.wv_home_eva);
        ivCollect = bindView(R.id.iv_show_aty_collect);

    }

    @Override
    protected void initData() {

        webViewParse();
  setClick(this,ivCollect);
    }



    private void webViewParse() {
        Intent intent = getIntent();
        if (intent != null) {
            // 网址
            String url = intent.getStringExtra("url");


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
            set.setAppCachePath("");
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            // 点击收藏
            case R.id.iv_show_aty_collect:
                Intent intent = getIntent();
                String title = intent.getStringExtra("title");
                String url = intent.getStringExtra("url");
                ivCollect.setImageResource(R.mipmap.ic_news_keep_heighlight);
                CollectBean bean  = new CollectBean(null,title,url,null,null);
                DBTool.getInstance().insertCollectBean(bean);
                break;
        }
    }
}
