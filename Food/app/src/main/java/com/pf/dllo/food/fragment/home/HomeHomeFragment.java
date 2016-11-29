package com.pf.dllo.food.fragment.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.pf.dllo.food.R;
import com.pf.dllo.food.adapter.home.HomeAdapter;
import com.pf.dllo.food.bean.home.HomeHomeBean;
import com.pf.dllo.food.fragment.BaseFragment;
import com.pf.dllo.food.tools.VolleyInstance;
import com.pf.dllo.food.tools.VolleyResult;
import com.pf.dllo.food.tools.net.NetHelper;
import com.pf.dllo.food.tools.net.NetListener;
import com.pf.dllo.food.values.NetValues;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeHomeFragment extends BaseFragment implements NetListener<HomeHomeBean> {

    private RecyclerView mRv;


    public static HomeHomeFragment newInstance(String url) {

        Bundle args = new Bundle();
        args.putString("key",url);
        HomeHomeFragment fragment = new HomeHomeFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    protected int setLayout() {
        return R.layout.fragment_home_home;
    }

    @Override
    protected void initView(View view) {
        mRv = bindView(R.id.rv_home_home);

    }

    @Override
    protected void initData() {
        NetHelper.MyRequest(NetValues.HOME_HOME,HomeHomeBean.class,this);
    }

    @Override
    public void successListener(HomeHomeBean response) {
        List<HomeHomeBean.FeedsBean> mData = response.getFeeds();
        HomeAdapter homeAdapter = new HomeAdapter(mContext);
        homeAdapter.setDatas(mData);
        mRv.setAdapter(homeAdapter);
        mRv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));    }

    @Override
    public void errorListener(VolleyError error) {

    }



}
