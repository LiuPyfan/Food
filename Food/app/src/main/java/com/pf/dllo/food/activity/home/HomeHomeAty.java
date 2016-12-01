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
                break;
        }
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
