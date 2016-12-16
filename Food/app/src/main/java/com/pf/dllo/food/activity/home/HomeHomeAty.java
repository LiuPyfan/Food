package com.pf.dllo.food.activity.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pf.dllo.food.R;
import com.pf.dllo.food.activity.BaseActivity;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import de.hdodenhof.circleimageview.CircleImageView;

public class HomeHomeAty extends BaseActivity implements View.OnClickListener {

    private ImageView ivBack, ivShare, ivCard;
    private CircleImageView civAva;
    private TextView tvPublish, tvLike;

    @Override
    protected int setLayout() {
        return R.layout.activity_home_home_aty;
    }

    @Override
    protected void initView() {
        ivBack = bindView(R.id.iv_home_aty_back);
        ivShare = bindView(R.id.iv_home_aty_share);
        ivCard = bindView(R.id.iv_home_aty_card);
        civAva = bindView(R.id.civ_home_aty_ava);
        tvPublish = bindView(R.id.tv_home_aty_publisher);
        tvLike = bindView(R.id.tv_home_aty_like);
        setClick(this, ivBack, ivShare, ivCard, civAva, tvPublish, tvLike);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_home_aty_back:
                finish();
                // 返回
                break;
            case R.id.iv_home_aty_share:
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
        Intent intent = getIntent();

        tvPublish.setText(intent.getStringExtra("publish"));
        tvLike.setText(intent.getStringExtra("like"));
        Glide.with(this).load(intent.getStringExtra("ava")).into(civAva);
        Glide.with(this).load(intent.getStringExtra("cardimg")).into(ivCard);


    }
}
