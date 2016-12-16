package com.pf.dllo.food.activity;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.pf.dllo.food.R;
import com.pf.dllo.food.adapter.MyCollectAdapter;
import com.pf.dllo.food.fragment.collect.ArticleCollectFragment;

import java.util.ArrayList;
import java.util.List;

public class MyCollectAty extends BaseActivity implements View.OnClickListener {

    private List<Fragment> mFragments;
    private ViewPager mViewPager;
    private TabLayout tlCollect;

    private ImageView mImageView;


    @Override
    protected int setLayout() {
        return R.layout.activity_my_collect_aty;
    }

    @Override
    protected void initView() {
        tlCollect = bindView(R.id.tl_my_collect);
        mViewPager = bindView(R.id.vp_my_collect);
        mImageView = bindView(R.id.iv_my_collect_back);
        setClick(this,mImageView);
    }

    @Override
    protected void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(new ArticleCollectFragment());
        mFragments.add(new ArticleCollectFragment());

        MyCollectAdapter adapter = new MyCollectAdapter(getSupportFragmentManager(),mFragments);
        mViewPager.setAdapter(adapter);

        tlCollect.setTabTextColors(Color.BLACK,getResources().getColor(R.color.default_dark_title_orange));
        tlCollect.setSelectedTabIndicatorColor(getResources().getColor(R.color.default_dark_title_orange));
        tlCollect.setupWithViewPager(mViewPager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_my_collect_back:
                finish();
                break;
        }
    }
}
