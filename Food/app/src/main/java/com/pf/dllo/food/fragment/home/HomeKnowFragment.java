package com.pf.dllo.food.fragment.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.pf.dllo.food.R;
import com.pf.dllo.food.adapter.home.KnowAdapter;
import com.pf.dllo.food.bean.home.HomeEvaBean;
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
public class HomeKnowFragment extends BaseFragment implements NetListener<HomeKnowBean> {

    private List<HomeKnowBean.FeedsBean> datas;
    private ListView mLv;

    @Override
    protected int setLayout() {
        return R.layout.fragment_home_know;
    }

    @Override
    protected void initView(View view) {
        mLv = (ListView) view.findViewById(R.id.lv_know);
    }

    @Override
    protected void initData() {

        NetHelper.MyRequest(NetValues.HOME_KNOW, HomeKnowBean.class, this);
    }

    @Override
    public void successListener(HomeKnowBean response) {
        datas = response.getFeeds();
        KnowAdapter adapter = new KnowAdapter(mContext);
        adapter.setDatas(datas);
        mLv.setAdapter(adapter);
    }

    @Override
    public void errorListener(VolleyError error) {

    }

}
