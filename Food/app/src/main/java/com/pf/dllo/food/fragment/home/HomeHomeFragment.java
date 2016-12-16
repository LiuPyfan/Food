package com.pf.dllo.food.fragment.home;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.android.volley.VolleyError;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import com.google.gson.Gson;
import com.pf.dllo.food.R;
import com.pf.dllo.food.activity.home.HomeHomeAdAty;
import com.pf.dllo.food.activity.home.HomeHomeAty;
import com.pf.dllo.food.adapter.home.HomeAdapter;
import com.pf.dllo.food.bean.home.HomeHomeBean;
import com.pf.dllo.food.fragment.BaseFragment;
import com.pf.dllo.food.tools.OnRvClick;
import com.pf.dllo.food.tools.VolleyInstance;
import com.pf.dllo.food.tools.VolleyResult;
import com.pf.dllo.food.tools.net.NetHelper;
import com.pf.dllo.food.tools.net.NetListener;
import com.pf.dllo.food.values.NetValues;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */

public class HomeHomeFragment extends BaseFragment implements NetListener<HomeHomeBean> {

    private LRecyclerView mRv;
    private LRecyclerViewAdapter lRecyclerViewAdapter;

    private int mCount = 1;
    private HomeAdapter mHomeAdapter;

    private List<HomeHomeBean.FeedsBean> mData;

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
//        mData = new ArrayList<>();
        mHomeAdapter = new HomeAdapter(mContext);
        startUrl(getMid(1));
        lRecyclerViewAdapter = new LRecyclerViewAdapter(mHomeAdapter);
        mRv.setAdapter(lRecyclerViewAdapter);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, 1);
        mRv.setLayoutManager(manager);

        mHomeAdapter.setOnRvClick(new OnRvClick() {
            @Override
            public void onRvClick(int pos, int type) {
                int pos1 = pos -1;
                if (type == 6){
                    Intent intent = new Intent(mContext, HomeHomeAdAty.class);
                    intent.putExtra("adUrl",mData.get(pos1).getLink());
                    startActivity(intent);
                }else if (type == 5){

                    Intent intent = new Intent(mContext,HomeHomeAty.class);
                    intent.putExtra("ava",mData.get(pos1).getPublisher_avatar());
                    intent.putExtra("cardimg",mData.get(pos1).getCard_image());
                    intent.putExtra("publish",mData.get(pos1).getPublisher());
                    intent.putExtra("like",mData.get(pos1).getLike_ct() +"");
                    startActivity(intent);
                }

            }

            @Override
            public void onRvClick(int pos) {

            }
        });
        // 下拉刷新
        mRv.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCount = 1;
                mHomeAdapter.setClear();
                startUrl(getMid(mCount));
                mRv.refreshComplete();
                mHomeAdapter.notifyDataSetChanged();
            }
        });
        // 上拉加载
        mRv.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mCount += 1;
                startUrl(getMid(mCount));
            }
        });

    }

    public String getMid(int i) {
        String mid = NetValues.HOME_HOME_HEAD + i + NetValues.HOME_HOME_TAIL;

        return mid;
    }

    private void startUrl(String url) {
        NetHelper.MyRequest(getMid(mCount), HomeHomeBean.class, this);
    }

    @Override
    public void successListener(HomeHomeBean response) {
        List<HomeHomeBean.FeedsBean>data =response.getFeeds();

        if (mData == null){
            mData= data;
        }else {
            for (int j = 0; j < data.size(); j++) {
                mData.add(data.get(j));
            }
        }

        mHomeAdapter.setDatas(mData);
    }

    @Override
    public void errorListener(VolleyError error) {

    }
}
