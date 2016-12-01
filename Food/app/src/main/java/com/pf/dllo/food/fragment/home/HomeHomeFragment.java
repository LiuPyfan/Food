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
        StartUrl(getMid(1));
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
        });
        // 下拉刷新
        mRv.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCount = 1;
                mHomeAdapter.setClear();
                StartUrl(getMid(mCount));
                mRv.refreshComplete();
                mHomeAdapter.notifyDataSetChanged();
            }
        });
        // 上拉加载
        mRv.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mCount += 1;
                StartUrl(getMid(mCount));
            }
        });

    }

    public String getMid(int i) {
        String mid = NetValues.HOME_HOME_HEAD + i + NetValues.HOME_HOME_TAIL;
        return mid;
    }

    private void StartUrl(String url) {
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
     //public class HomeHomeFragment extends BaseFragment implements NetListener<HomeHomeBean> {
//
//    private PullLoadMoreRecyclerView mRv;
//    private int mCount =1 ;
//    private HomeAdapter mHomeAdapter;
//    private List<HomeHomeBean.FeedsBean> mData;
//
//    public static HomeHomeFragment newInstance(String url) {
//
//        Bundle args = new Bundle();
//        args.putString("key",url);
//        HomeHomeFragment fragment = new HomeHomeFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//
//
//    @Override
//    protected int setLayout() {
//        return R.layout.fragment_home_home;
//    }
//
//    @Override
//    protected void initView(View view) {
//        mRv = bindView(R.id.rv_home_home);
//
//    }
//
//    @Override
//    protected void initData() {
//        mData = new ArrayList<>();
//        mHomeAdapter = new HomeAdapter(mContext);
//        StartUrl(getMid(mCount));
//        mRv.setAdapter(mHomeAdapter);
//        mRv.setStaggeredGridLayout(2);
//        mRv.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
//            @Override
//            public void onRefresh() {
////                new PutDataTask().execute();
//            }
//            @Override
//            public void onLoadMore() {
//                mCount += 1;
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        StartUrl(getMid(mCount));
//                        mRv.setStaggeredGridLayout(2);
////                        mHomeAdapter.notifyDataSetChanged();
//                        mRv.setPullLoadMoreCompleted();
//
//                    }
//                },1000);
////                new GetDataTask().execute();
//            }
//        });
//    }
//
//    public String getMid(int i) {
//        String mid = NetValues.HOME_HOME_HEAD + i + NetValues.HOME_HOME_TAIL;
//        return mid;
//    }
//
//    private void StartUrl(String url) {
//        NetHelper.MyRequest(getMid(mCount),HomeHomeBean.class,this);
//    }
//
//
//    @Override
//    public void successListener(HomeHomeBean response) {
//
//        mData = response.getFeeds();
//        mHomeAdapter.setDatas(mData);
//        mRv.setStaggeredGridLayout(2);
//    }
//
//    @Override
//    public void errorListener(VolleyError error) {
//
//    }
//
//
//    // 上拉加载的异步任务
//    private class GetDataTask extends AsyncTask<Integer, Void, Integer> {
//
//        @Override
//        protected Integer doInBackground(Integer... params) {
//            try {
//                Thread.sleep(1000);
//                mCount += 1;
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return mCount;
//        }
//
//        @Override
//        protected void onPostExecute(Integer integer) {
//            super.onPostExecute(integer);
//            StartUrl(getMid(integer));
//            mHomeAdapter.notifyDataSetChanged();
//            // Call onRefreshComplete when the list has been refreshed.
//            mRv.setPullLoadMoreCompleted();
//        }
//
//    }
//
//    // 下拉刷新的异步任务
//    private class PutDataTask extends AsyncTask<Integer, Void, Integer> {
//
//        @Override
//        protected Integer doInBackground(Integer... params) {
//            try {
//                Thread.sleep(2000);
//                mCount = 1;
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return mCount;
//        }
//
//        @Override
//        protected void onPostExecute(Integer integer) {
//            super.onPostExecute(integer);
//
////            mHomeAdapter.setClear();
//            StartUrl(getMid(integer));
//            mHomeAdapter.notifyDataSetChanged();
//            // Call onRefreshComplete when the list has been refreshed.
//            mRv.setPullLoadMoreCompleted();
//
//        }
//
//    }
//
//    private void setRefresh(){
////        mHomeAdapter.getDatas().clear();
////        mHomeAdapter.setClear();
//        mCount =1;
//    }
//
//}
}
