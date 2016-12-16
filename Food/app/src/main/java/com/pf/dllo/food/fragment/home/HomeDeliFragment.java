package com.pf.dllo.food.fragment.home;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.pf.dllo.food.R;
import com.pf.dllo.food.activity.home.HomeDeliAty;
import com.pf.dllo.food.activity.home.HomeKnowAty;
import com.pf.dllo.food.adapter.home.DeliAdapter;
import com.pf.dllo.food.bean.home.HomeDeliBean;
import com.pf.dllo.food.fragment.BaseFragment;
import com.pf.dllo.food.tools.VolleyInstance;
import com.pf.dllo.food.tools.VolleyResult;
import com.pf.dllo.food.tools.net.NetHelper;
import com.pf.dllo.food.tools.net.NetListener;
import com.pf.dllo.food.values.NetValues;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeDeliFragment extends BaseFragment {

    private List<HomeDeliBean.FeedsBean> datas;

    private PullToRefreshListView mPullRefreshListView;
    private int i = 1;
    private DeliAdapter mAdapter;


    @Override
    protected int setLayout() {
        return R.layout.fragment_home_deli;
    }

    @Override
    protected void initView(View view) {
        mPullRefreshListView = (PullToRefreshListView) view.findViewById(R.id.lv_deli);
        mPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
    }

    @Override
    protected void initData() {
        mAdapter = new DeliAdapter(mContext);
        startUrl(getMid(1));
        mPullRefreshListView.setAdapter(mAdapter);
        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //这里写下拉刷新的任务
                new PutDataTask().execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                //这里写上拉加载更多的任务
                new GetDataTask().execute();

            }
        });
    }


    public String getMid(int i) {
        String mid = NetValues.HOME_DELI_HEAD + i + NetValues.HOME_DELI_TAIL;
        return mid;

    }

    private void startUrl(String url) {


        NetHelper.MyRequest(getMid(i), HomeDeliBean.class, new NetListener<HomeDeliBean>() {
            @Override
            public void successListener(HomeDeliBean response) {
                List<HomeDeliBean.FeedsBean> data = response.getFeeds();
                if (datas == null) {
                    datas = data;
                } else {
                    for (int j = 0; j < data.size(); j++) {
                        datas.add(data.get(j));
                    }
                }
                mAdapter.setDatas(datas);
                mPullRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(mContext, HomeKnowAty.class);
                        intent.putExtra("url", datas.get(i - 1).getLink());
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });

    }

    // 上拉加载的异步任务
    private class GetDataTask extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected Integer doInBackground(Integer... params) {
            try {
                Thread.sleep(1000);
                i += 1;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return i;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            startUrl(getMid(integer));

            mAdapter.notifyDataSetChanged();
            mPullRefreshListView.onRefreshComplete();
        }
    }

    // 下拉刷新的异步任务
    private class PutDataTask extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected Integer doInBackground(Integer... params) {
            try {
                Thread.sleep(1000);
                i = 1;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return i;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

            mAdapter.clean();
            startUrl(getMid(integer));

            mAdapter.notifyDataSetChanged();
            mPullRefreshListView.onRefreshComplete();
        }

    }


}
