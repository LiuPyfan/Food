package com.pf.dllo.food.fragment;


import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mob.tools.utils.UIHandler;
import com.pf.dllo.food.R;
import com.pf.dllo.food.activity.MyCollectAty;
import com.pf.dllo.food.activity.MySetActivity;
import com.pf.dllo.food.adapter.MyAdapter;
import com.pf.dllo.food.bean.LoginBean;
import com.pf.dllo.food.bean.MyBean;
import com.pf.dllo.food.tools.ScreenSetUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends BaseFragment implements PlatformActionListener, Handler.Callback {


    private ListView mListView;
    private ImageView ivUser,ivSet;
    private TextView tvUser, tvEdit;
    private boolean hasLogin = false ;

    // 第三方 登录
    private static final int MSG_TOAST = 1;
    private static final int MSG_ACTION_CCALLBACK = 2;
    private static final int MSG_CANCEL_NOTIFY = 3;

    private int[] left = new int[]{R.mipmap.ic_my_photo, R.mipmap.ic_my_collect, R.mipmap.ic_my_upload, R.mipmap.ic_my_order};
    private int[] right = new int[]{R.mipmap.ic_arrow_right_default, R.mipmap.ic_arrow_right_default, R.mipmap.ic_arrow_right_default, R.mipmap.ic_arrow_right_default};
    private String[] title = new String[]{"我的照片", "我的收藏", "上传食物数据", "我的订单"};


    @Override
    protected int setLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView(View view) {
        mListView = bindView(R.id.lv_my);
        ivUser = bindView(R.id.my_icon);
        tvUser = bindView(R.id.my_login);
        tvEdit = bindView(R.id.my_edit);
        ivSet = bindView(R.id.my_setting);
    }

    @Override
    protected void initData() {

        ivSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MySetActivity.class);
                startActivity(intent);
            }
        });

        SharedPreferences sp = mContext.getSharedPreferences("user", Context.MODE_PRIVATE);
        if (sp.getString("icon", "abc").equals("abc")) {
            ivUser.setImageResource(R.mipmap.img_default_avatar);

        } else {
            sp.getBoolean("hasLogin",false);
            Picasso.with(mContext).load(sp.getString("icon", null)).resize(180, 180).into(ivUser);

        }
        if (hasLogin){
            ivUser.setBackground(null);
            tvEdit.setVisibility(View.VISIBLE);
        }
        tvUser.setText(sp.getString("name", "点击登录"));

        // lv
        setLv();
        // tv登录点击事件
        tvUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
//            }
        });
        // iv登录点击事件
        ivUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thirdSinaLogin();
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        // 收藏页
                        Intent intent = new Intent(mContext, MyCollectAty.class);
                        startActivity(intent);
                        break;
                }

            }
        });

    }

    private void setLv() {
        MyAdapter adapter = new MyAdapter(getContext());
        adapter.setDatas(left, right, title);
        mListView.setAdapter(adapter);
    }

    /**
     * 微博
     */
    private void back() {
        Platform wbPlf = ShareSDK.getPlatform(mContext, SinaWeibo.NAME);
        ShareSDK.removeCookieOnAuthorize(true);
        wbPlf.removeAccount();
    }

    /**
     * 新浪微博授权、获取用户信息页面
     */
    private void thirdSinaLogin() {
        //初始化新浪平台
        Platform pf = ShareSDK.getPlatform(mContext, SinaWeibo.NAME);
        pf.SSOSetting(true);
        //设置监听
        pf.setPlatformActionListener(this);
        //获取登陆用户的信息，如果没有授权，会先授权，然后获取用户信息
        pf.authorize();

    }

    /**
     * 新浪微博授权成功回调页面
     */
    @Override
    public void onComplete(Platform platform, int action, HashMap<String, Object> hashMap) {
        /** res是返回的数据，例如showUser(null),返回用户信息，对其解析就行
         *   http://sharesdk.cn/androidDoc/cn/sharesdk/framework/PlatformActionListener.html
         *   1、不懂如何解析hashMap的，可以上网搜索一下
         *   2、可以参考官网例子中的GetInforPage这个类解析用户信息
         *   3、相关的key-value,可以看看对应的开放平台的api
         *     如新浪的：http://open.weibo.com/wiki/2/users/show
         *     腾讯微博：http://wiki.open.t.qq.com/index.php/API%E6%96%87%E6%A1%A3/%E5%B8%90%E6%88%B7%E6%8E%A5%E5%8F%A3/%E8%8E%B7%E5%8F%96%E5%BD%93%E5%89%8D%E7%99%BB%E5%BD%95%E7%94%A8%E6%88%B7%E7%9A%84%E4%B8%AA%E4%BA%BA%E8%B5%84%E6%96%99
         *
         */
        Message msg = new Message();
        msg.what = MSG_ACTION_CCALLBACK;
        msg.arg1 = 1;
        msg.arg2 = action;
        msg.obj = platform;
        UIHandler.sendMessage(msg, this);
    }

    /**
     * 取消授权
     */
    @Override
    public void onCancel(Platform platform, int action) {
        Message msg = new Message();
        msg.what = MSG_ACTION_CCALLBACK;
        msg.arg1 = 3;
        msg.arg2 = action;
        msg.obj = platform;
        UIHandler.sendMessage(msg, this);
    }

    /**
     * 授权失败
     */
    @Override
    public void onError(Platform platform, int action, Throwable t) {
        t.printStackTrace();
        t.getMessage();
        Message msg = new Message();
        msg.what = MSG_ACTION_CCALLBACK;
        msg.arg1 = 2;
        msg.arg2 = action;
        msg.obj = t;
        UIHandler.sendMessage(msg, this);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_TOAST: {
                String text = String.valueOf(msg.obj);
                Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
            }
            break;
            case MSG_ACTION_CCALLBACK: {
                switch (msg.arg1) {
                    case 1: {
                        // 成功, successful notification
                        //授权成功后,获取用户信息，要自己解析，看看oncomplete里面的注释
                        //ShareSDK只保存以下这几个通用值
                        Platform pf = ShareSDK.getPlatform(mContext, SinaWeibo.NAME);
                        Log.e("sharesdk use_id", pf.getDb().getUserId()); //获取用户id
                        Log.e("sharesdk use_name", pf.getDb().getUserName());//获取用户名称
                        Log.e("sharesdk use_icon", pf.getDb().getUserIcon());//获取用户头像
//                        mThirdLoginResult.setText("授权成功"+"\n"+"用户id:" + pf.getDb().getUserId() + "\n" + "获取用户名称" + pf.getDb().getUserName() + "\n" + "获取用户头像" + pf.getDb().getUserIcon());


                        //mPf.author()这个方法每一次都会调用授权，出现授权界面
                        //如果要删除授权信息，重新授权
                        //mPf.getDb().removeAccount();
                        //调用后，用户就得重新授权，否则下一次就不用授权
                    }
                    break;
                    case 2: {
//                        mThirdLoginResult.setText("登录失败");
                    }
                    break;
                    case 3: {
                        // 取消, cancel notification
//                        mThirdLoginResult.setText("取消授权");
                    }
                    break;
                }
            }
            break;
            case MSG_CANCEL_NOTIFY: {
                NotificationManager nm = (NotificationManager) msg.obj;
                if (nm != null) {
                    nm.cancel(msg.arg1);
                }
            }
            break;
        }
        return false;
    }

    /**
     * QQ登录
     */
    private void login() {
        // 获取第三方平台

        Platform platform = ShareSDK.getPlatform(QQ.NAME);
        //回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行


        // 授权
        platform.authorize();
        // 获取用户信息
        platform.setPlatformActionListener(new PlatformActionListener() {

            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Toast.makeText(mContext, "完成", Toast.LENGTH_SHORT).show();

                String name, icon;
                try {
                    PlatformDb platformDb = platform.getDb();
                    name = platformDb.getUserName();
                    icon = platformDb.getUserIcon();

                    if (!TextUtils.isEmpty(name)) {
//                        tvEdit.setVisibility(View.VISIBLE);

                        tvEdit.setText(getResources().getString(R.string.edit_person_info));
                        tvUser.setText(name);
                        tvUser.setBackground(null);
                        Picasso.with(mContext).load(icon).resize(180, 180).into(ivUser);
                        SharedPreferences.Editor editor = mContext.getSharedPreferences("user", Context.MODE_PRIVATE).edit();
                        editor.putString("name", name);
                        editor.putString("icon", icon);
                        editor.putBoolean("hasLogin",true);
                        editor.commit();
                        hasLogin = true;

                    }
                } catch (NullPointerException e) {

                }

            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Toast.makeText(mContext, "错误", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Toast.makeText(mContext, "取消", Toast.LENGTH_SHORT).show();
            }

        });
    }
}
