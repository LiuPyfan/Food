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
import com.pf.dllo.food.activity.home.HomeKnowAty;
import com.pf.dllo.food.adapter.home.KnowAdapter;

import com.pf.dllo.food.bean.home.HomeKnowBean;
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
public class HomeKnowFragment extends BaseFragment  {

    private List<HomeKnowBean.FeedsBean> datas;
//    private ListView mLv;

    /**
     * 上拉刷新的控件
     */
    private PullToRefreshListView mLv;
    private int i = 1;
    private KnowAdapter mAdapter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_home_know;
    }

    @Override
    protected void initView(View view) {
        mLv = bindView(R.id.lv_know);
        mLv.setMode(PullToRefreshBase.Mode.BOTH);
    }

    @Override
    protected void initData() {
        mAdapter = new KnowAdapter(mContext);
        parseUrl(getMid(1));
        mLv.setAdapter(mAdapter);
        mLv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(
                    PullToRefreshBase<ListView> refreshView) {

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
        String mid = NetValues.HOME_KNOW_HEAD + i + NetValues.HOME_KNOW_TAIL;
        return mid;

    }

    private void parseUrl(String url) {


        NetHelper.MyRequest(getMid(i), HomeKnowBean.class, new NetListener<HomeKnowBean>() {
            @Override
            public void successListener(HomeKnowBean response) {
                List<HomeKnowBean.FeedsBean> data = response.getFeeds();
                if (datas == null) {
                    datas = data;
                } else {
                    for (int j = 0; j < data.size(); j++) {
                        datas.add(data.get(j));
                    }
                }
                mAdapter.setDatas(datas);

                mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

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
                Thread.sleep(2000);
                i += 1;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return i;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            parseUrl(getMid(integer));

            mAdapter.notifyDataSetChanged();
            // Call onRefreshComplete when the list has been refreshed.
            mLv.onRefreshComplete();
        }

    }


    // 下拉刷新的异步任务
    private class PutDataTask extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected Integer doInBackground(Integer... params) {
            try {
                Thread.sleep(2000);
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
            parseUrl(getMid(integer));

            mAdapter.notifyDataSetChanged();
            // Call onRefreshComplete when the list has been refreshed.
            mLv.onRefreshComplete();
        }

    }

}
